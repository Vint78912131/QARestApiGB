package ru.gb.spooncular;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.gb.retrofit.dto.ProductDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static junit.framework.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static ru.gb.retrofit.util.RetrofitUtil.getCategoryService;
import static ru.gb.retrofit.util.RetrofitUtil.getProductsService;

public class RecipeTests {
    public String apiKey = "a1eb2ff3a89c40289fe2c3d6af7cd53a";
    public String title_GK = "Garlicky Kale";
    public String component_Garlic = "Garlic";
    public int tr_Garlic = 1765;
    public int id_644218 = 644218;
    public String component_Plum = "Plum";
    public String url = "https://api.spoonacular.com/recipes/";
    //@SneakyThrows
//    @BeforeEach
//    void setUp() {
//        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
//    }
    @Test
    @Description("Checking offset, number, totalResults with assertThat")
    public void getRecipePositiveTest() throws IOException {
        JsonPath response = given()
                .log()
                .all()
                .queryParam("apiKey", apiKey)
                .expect()
                .body("results[0].title", equalTo("Cauliflower, Brown Rice, and Vegetable Fried Rice"))
                .when()
                .get(url+"complexSearch")
                .body()
                .jsonPath();
        System.out.println(response.prettyPrint());
        assertThat(response.get("offset"), equalTo(0));
        assertThat(response.get("number"), equalTo(10));
        assertThat(response.get("totalResults"), equalTo(5222));
    }

    @Test
    @Description("Checking response with expect()")
    public void getRecipeWithBodyChecksGivenPositiveTest() {
        given()
                .log()
                .all()
                .queryParam("apiKey", apiKey)
                .queryParam("cuisine", "Greek")
                .expect()
                .body("offset", is(0))
                .body("number", is(10))
                .body("totalResults", is(24))
                .body("results[0].title", equalTo("Classic Greek Moussaka"))
                .body("results[1].id", is(664678))
                .when()
                .get(url+"complexSearch")
                .then()
                .statusCode(200);
    }



    @Test
    @Description("Checking cuisine, confidence")
    public void postCuisinePositiveAssertTest() {
        JsonPath response = given()
                .log()
                .all()
                .queryParam("apiKey", apiKey)
                .when()
                .post(url + "cuisine")
                .body()
                .jsonPath();
        System.out.println(response.prettyPrint());
        assertThat(response.get("cuisine"), equalTo("Mediterranean"));
        assertThat(response.get("confidence"), equalTo((float)(0)));
    }

//    @SneakyThrows
//    @AfterEach
//    void tearDown() {
//        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
//    }

}
