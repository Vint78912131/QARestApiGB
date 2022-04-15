package ru.gb.restassured.endpoints.shop;

import io.qameta.allure.Step;
import ru.gb.restassured.dto.shop.OrderDto;

import static io.restassured.RestAssured.given;

@Endpoint("/order")
public class ApiOrderEndpoint extends BaseEndpoint {

    @Step("{this.endpoint}: купить телефон")
    public void order(String token, OrderDto orderDto) {
        given()
                .header(getAuthHeader(token))
                .body(orderDto)
                .post(endpoint)
                .then()
                .statusCode(200);
    }
}
