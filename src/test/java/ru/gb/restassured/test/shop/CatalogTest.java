package ru.gb.restassured.test.shop;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.gb.restassured.dto.shop.PhoneDto;
import ru.gb.restassured.endpoints.shop.ApiCatalogEndpoint;
import ru.gb.restassured.extensions.MobileShopApiTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.gb.restassured.db.DbTestDataHelper.getAllPhones;

@MobileShopApiTest
public class CatalogTest {
    static List<PhoneDto> phoneDtoList;

    @BeforeAll
    static void beforeAll() {
        phoneDtoList = getAllPhones();
    }

    @Test
    void catalogTest() {
        assertThat(new ApiCatalogEndpoint().getAllPhones())
                .containsExactlyElementsOf(phoneDtoList);
    }
}
