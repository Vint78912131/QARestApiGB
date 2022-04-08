package ru.gb.test.spoon;

import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.gb.extensions.SpoonApiTest;
import ru.gb.extensions.SpoonApiTestExtension;

import java.util.Map;

import static io.restassured.RestAssured.given;


@SpoonApiTest
public class FoodTest {

    @ParameterizedTest
    @ValueSource(strings = {"Pizza", "Sushi"})
    public void foodSearchTest(String queryParameter) {
        given()
                .queryParams(Map.of("query", queryParameter,
                        "offset", 0,
                        "number", 10))
                .get("/food/search")
                .then()
                .statusCode(200)
                .body("query", Matchers.equalTo(queryParameter));
    }
}
