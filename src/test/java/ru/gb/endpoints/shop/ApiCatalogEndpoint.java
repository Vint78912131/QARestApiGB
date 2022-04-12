package ru.gb.endpoints.shop;

import ru.gb.dto.shop.PhoneDto;

import java.util.List;

import static io.restassured.RestAssured.given;

@Endpoint("/catalog")
public class ApiCatalogEndpoint extends BaseEndpoint{

    public List<PhoneDto> getAllPhones() {
        return List.of(given()
                .get(endpoint)
                .then()
                .statusCode(200)
                .extract()
                .as(PhoneDto[].class));
    }
}
