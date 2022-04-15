package ru.gb.restassured.endpoints.shop;

import io.qameta.allure.Step;
import ru.gb.restassured.dto.shop.UserDto;

import static io.restassured.RestAssured.given;

@Endpoint("/user")
public class ApiUserEndpoint extends BaseEndpoint {

    @Step("{this.endpoint}: получить юзера")
    public UserDto getUser(String token) {
        return given()
                .header(getAuthHeader(token))
                .get(endpoint)
                .then()
                .statusCode(200)
                .extract()
                .as(UserDto.class);
    }
}
