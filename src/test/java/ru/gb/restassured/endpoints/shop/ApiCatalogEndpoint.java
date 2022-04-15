package ru.gb.restassured.endpoints.shop;

import io.qameta.allure.Step;
import ru.gb.restassured.dto.shop.PhoneDto;

import java.util.List;

import static io.restassured.RestAssured.given;

@Endpoint("/catalog")
public class ApiCatalogEndpoint extends BaseEndpoint {

    @Step("{this.endpoint}: Получить все телефоны")
    public List<PhoneDto> getAllPhones() {
        return List.of(given()
                .get(endpoint)
                .then()
                .statusCode(200)
                .extract()
                .as(PhoneDto[].class));
    }
}
