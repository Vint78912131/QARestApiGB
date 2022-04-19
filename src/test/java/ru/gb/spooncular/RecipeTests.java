package ru.gb.spooncular;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static junit.framework.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RecipeTests {
    public String apiKey = "a1eb2ff3a89c40289fe2c3d6af7cd53a";
    public String title_GK = "Garlicky Kale";
    public String component_Garlic = "Garlic";
    public int tr_Garlic = 1765;
    public int id_644218 = 644218;
    public String component_Plum = "Plum";
    public String url = "https://api.spoonacular.com/recipes/";


    @Test
    @Description("Checking offset, number, totalResults with assertThat")
    public void getRecipePositiveTest() throws IOException {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
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
    @Description("Checking offset, number, totalResults with expect()")
    public void getRecipeWithBodyChecksGivenPositiveTest() {
        given()
                .queryParam("apiKey", apiKey)
                .queryParam("cuisine", "Greek")
                .expect()
                .body("offset", is(0))
                .body("number", is(10))
                .body("totalResults", is(24))
                .when()
                .get(url+"complexSearch")
                .then()
                .statusCode(200);
    }



    @Test
    @Description("Checking cuisine, confidence")
    public void postCuisinePositiveAssertTest() {
        JsonPath response = given()
                .queryParam("apiKey", apiKey)
                .when()
                .post(url + "cuisine")
                .body()
                .jsonPath();
        System.out.println(response.prettyPrint());
        assertThat(response.get("cuisine"), equalTo("Mediterranean"));
        assertThat(response.get("confidence"), equalTo((float)(0)));
    }


}
