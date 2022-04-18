package ru.gb.spooncular;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
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
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class RecipeTests {
    public String apiKey = "a1eb2ff3a89c40289fe2c3d6af7cd53a";
    public String title_GK = "Garlicky Kale";
    public String component_Garlic = "Garlic";
    public int tr_Garlic = 1765;
    public int id_644218 = 644218;
    public String component_Plum = "Plum";
    public String url = "https://spoonacular.com/";
    OkHttpClient okHttpClient = new OkHttpClient()
            .newBuilder()
            .readTimeout(10, TimeUnit.SECONDS)
            .build();



    @Test
    public void getRecipePositiveTest() throws IOException {
//        Request request = new Request.Builder()
//        .addHeader("apiKey", apiKey)
//        .url("https://spoonacular.com/recipes/complexSearch")
//        .get()
//        .build();
//
//        Response response = okHttpClient.newCall(request).execute();
//        System.out.println(response.body().string());
//        ObjectMapper objectMapper = new ObjectMapper();
//        String results = objectMapper.readTree(body).asText();

          String response = given()
                  //.queryParam("apiKey", apiKey)
//                  .queryParam("Host", "<calculated when request is sent>")
//                  .queryParam("User-Agent", "PostmanRuntime/7.29.0")
//                  .queryParam("Accept", "*/*")
//                  .queryParam("Accept-Encoding", "gzip, deflate, br")
//                  .queryParam("apiKeyConnection", "keep-alive")
                  .when()
                  .get("https://spoonacular.com/recipes/complexSearch?apiKey=a1eb2ff3a89c40289fe2c3d6af7cd53a")
                .asPrettyString();


        System.out.println(response);
    }

    @Test
    public void postCuisinePositiveAssertTest() {
        String response = given()
                .queryParam("apiKey", apiKey)
                //.queryParam("includeNutrition","false")
                .when()
                .post(url + "cuisine")
                .then()
                .extract()
                .body().asPrettyString();
        System.out.println(response);

//                .body("offset",is(0))
//                .body("number",is(10))
//                .body("totalResults",equalTo(5222))
        ;
//        System.out.println(response.get("offset").toString());
//        System.out.println(response.get("number").toString());
//        System.out.println(response.get("totalResults").toString());
        //assertEquals(response.get("offset").);
//        assertEquals(response.get("offset"),"0");


    }


}
