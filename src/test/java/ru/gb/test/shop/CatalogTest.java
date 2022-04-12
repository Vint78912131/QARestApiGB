package ru.gb.test.shop;

import org.junit.jupiter.api.Test;
import ru.gb.endpoints.shop.ApiCatalogEndpoint;
import ru.gb.extensions.MobileShopApiTest;

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
