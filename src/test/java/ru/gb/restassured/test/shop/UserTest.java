package ru.gb.restassured.test.shop;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.gb.restassured.dto.shop.UserDto;
import ru.gb.restassured.endpoints.shop.ApiAuthRegisterEndpoint;
import ru.gb.restassured.endpoints.shop.ApiUserEndpoint;
import ru.gb.restassured.extensions.MobileShopApiTest;

import static org.assertj.core.api.Assertions.assertThat;

@MobileShopApiTest
public class UserTest {

    private UserDto userDto;

    @BeforeEach
    void setUp() {
        Faker faker = new Faker();
        userDto = new ApiAuthRegisterEndpoint().registerUser(UserDto.builder()
                .address(faker.address().fullAddress())
                .password(faker.internet().password())
                .username(faker.name().fullName())
                .email(faker.internet().emailAddress())
                .phone(faker.phoneNumber().phoneNumber())
                .build());
    }

    @Test
    void getUserTest() {
        UserDto actualUser = new ApiUserEndpoint().getUser(userDto.getToken());
        assertThat(actualUser)
                .isEqualTo(userDto);
    }
}
