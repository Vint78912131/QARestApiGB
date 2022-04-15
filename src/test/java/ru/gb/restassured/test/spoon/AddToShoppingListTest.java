package ru.gb.restassured.test.spoon;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.gb.restassured.dto.spoon.AddItemToShoppingListRequest;
import ru.gb.restassured.dto.spoon.CreateUserRequest;
import ru.gb.restassured.dto.spoon.CreateUserResponse;
import ru.gb.restassured.endpoints.spoon.SpoonEndpoints;
import ru.gb.restassured.extensions.SpoonApiTest;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

@SpoonApiTest
public class AddToShoppingListTest {
    private static CreateUserResponse createUserResponse;
    private static RequestSpecification hashParam;

    private int id;

    @BeforeAll
    static void beforeAll() {
        Faker faker = new Faker();
        createUserResponse = given()
                .body(CreateUserRequest.builder()
                        .username(faker.funnyName().name())
                        .firstName(faker.name().firstName())
                        .lastName(faker.name().lastName())
                        .email(faker.internet().emailAddress())
                        .build())
                .post(SpoonEndpoints.USER_CONNECT.getEndpoint())
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .as(CreateUserResponse.class);
        hashParam = new RequestSpecBuilder()
                .addQueryParam("hash", createUserResponse.getHash())
                .build();
    }

    @BeforeEach
    void setUp() {
        given()
                .spec(hashParam)
                .get(SpoonEndpoints.MEALPLANNER_USERNAME_SHOPPING_LIST.getEndpoint(), createUserResponse.getUsername())
                .then()
                .statusCode(200)
                .body("aisles", Matchers.hasSize(0));
    }


    public static Stream<AddItemToShoppingListRequest> shoppingListRequests() {
        return Stream.of(AddItemToShoppingListRequest.builder()
                        .item("1 kg cucumbers")
                        .aisle("Cucumber")
                        .parse(true)
                        .build(),
                AddItemToShoppingListRequest.builder()
                        .item("2 kg tomatos")
                        .aisle("Tomato")
                        .parse(true)
                        .build());
    }

    @ParameterizedTest
    @MethodSource("shoppingListRequests")
    void addToShoppingListTest(AddItemToShoppingListRequest addItemToShoppingListRequest) {
        given()
                .log()
                .all()
                .spec(hashParam)
                .body(addItemToShoppingListRequest)
                .post(SpoonEndpoints.MEALPLANNER_USERNAME_SHOPPING_LIST_ITEMS.getEndpoint(), createUserResponse.getUsername())
                .then()
                .statusCode(200);

        id = given()
                .spec(hashParam)
                .get(SpoonEndpoints.MEALPLANNER_USERNAME_SHOPPING_LIST.getEndpoint(), createUserResponse.getUsername())
                .then()
                .statusCode(200)
                .body("aisles", Matchers.hasSize(1))
                .body("aisles.aisle", Matchers.hasItems(addItemToShoppingListRequest.getAisle()))
                .body("aisles.items", Matchers.hasSize(1))
                .extract()
                .jsonPath()
                .getInt("aisles.items[0].id[0]");
    }

    @AfterEach
    void tearDown() {
        given()
                .spec(hashParam)
                .delete(SpoonEndpoints.MEALPLANNER_USERNAME_SHOPPING_LIST_ITEMS_ID.getEndpoint(), createUserResponse.getUsername(), id)
                .then()
                .statusCode(200);
    }
}
