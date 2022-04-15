package ru.gb.restassured.test.shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.gb.restassured.dto.shop.OrderDto;
import ru.gb.restassured.dto.shop.OrderItem;
import ru.gb.restassured.dto.shop.PhoneDto;
import ru.gb.restassured.dto.shop.UserDto;
import ru.gb.restassured.endpoints.shop.ApiAuthRegisterEndpoint;
import ru.gb.restassured.endpoints.shop.ApiCatalogEndpoint;
import ru.gb.restassured.endpoints.shop.ApiOrderEndpoint;
import ru.gb.restassured.endpoints.shop.ApiUserEndpoint;
import ru.gb.restassured.extensions.MobileShopApiTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.gb.restassured.util.TestDataHelper.generateUserDto;

@MobileShopApiTest
public class OrderTest {
    UserDto userDto;
    PhoneDto phoneDto;
    OrderDto orderDto;

    @BeforeEach
    void setUp() {
        userDto = new ApiAuthRegisterEndpoint().registerUser(generateUserDto());
        phoneDto = new ApiCatalogEndpoint().getAllPhones().get(0);
        orderDto = OrderDto.builder()
                .order(List.of(OrderItem.builder()
                        .name(phoneDto.getInfo().getName())
                        .price(phoneDto.getInfo().getPrice())
                        .quantity(1)
                        .dateCreated(LocalDateTime.now())
                        .build()))
                .build();

    }

    @Test
    void orderTest() {
        assertThat(new ApiUserEndpoint().getUser(userDto.getToken()).getOrders())
                .hasSize(0);

        new ApiOrderEndpoint().order(userDto.getToken(), orderDto);

        assertThat(new ApiUserEndpoint().getUser(userDto.getToken()).getOrders())
                .containsExactlyElementsOf(orderDto.getOrder());
    }
}
