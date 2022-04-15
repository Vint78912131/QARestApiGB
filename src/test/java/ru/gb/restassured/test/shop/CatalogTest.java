package ru.gb.restassured.test.shop;

import org.junit.jupiter.api.Test;
import ru.gb.restassured.endpoints.shop.ApiCatalogEndpoint;
import ru.gb.restassured.extensions.MobileShopApiTest;

import static org.assertj.core.api.Assertions.assertThat;

@MobileShopApiTest
public class CatalogTest {

    @Test
    void catalogTest() {
        assertThat(new ApiCatalogEndpoint().getAllPhones()
                .stream().map(phoneDto -> phoneDto.getInfo().getName())
                .toList())
                .contains("Apple iPhone 8 Plus", "Apple iPhone X");
    }
}
