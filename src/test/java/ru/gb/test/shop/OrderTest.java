package ru.gb.test.shop;

import org.assertj.core.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.gb.dto.shop.OrderDto;
import ru.gb.dto.shop.OrderItem;
import ru.gb.dto.shop.PhoneDto;
import ru.gb.dto.shop.UserDto;
import ru.gb.endpoints.shop.ApiAuthRegisterEndpoint;
import ru.gb.endpoints.shop.ApiCatalogEndpoint;
import ru.gb.endpoints.shop.ApiOrderEndpoint;
import ru.gb.endpoints.shop.ApiUserEndpoint;
import ru.gb.extensions.MobileShopApiTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.gb.util.TestDataHelper.generateUserDto;

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
