package ru.gb.endpoints.shop;

import ru.gb.dto.shop.UserDto;

import static io.restassured.RestAssured.given;

@Endpoint("/user")
public class ApiUserEndpoint extends BaseEndpoint {

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
