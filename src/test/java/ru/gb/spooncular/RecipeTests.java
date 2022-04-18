package ru.gb.spooncular;

import io.restassured.path.json.JsonPath;
import org.junit.Test;

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
    public String url = "https://spoonacular.com/recipes/";



    @Test
    public void getRecipePositiveTest() {
        given()
                .queryParam("apiKey", apiKey)
                .when()
                .get(url + "complexSearch")
                .then()
                .statusCode(200);
    }

    @Test
    public void getRecipePositiveAssertTest() {
        given()
                .queryParam("apiKey", apiKey)
                .when()
                .get(url + "complexSearch")
                .then()
                .assertThat()
                .statusCode(200)
                .and().
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
