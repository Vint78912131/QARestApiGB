package ru.gb.restassured.endpoints.shop;

import ru.gb.restassured.dto.shop.PhoneDto;

import java.util.List;

import static io.restassured.RestAssured.given;

@Endpoint("/catalog")
public class ApiCatalogEndpoint extends BaseEndpoint {

    public List<PhoneDto> getAllPhones() {
        return List.of(given()
                .get(endpoint)
                .then()
                .statusCode(200)
                .extract()
                .as(PhoneDto[].class));
    }
}
