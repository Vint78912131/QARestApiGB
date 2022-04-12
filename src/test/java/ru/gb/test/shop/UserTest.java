package ru.gb.test.shop;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.gb.dto.shop.UserDto;
import ru.gb.endpoints.shop.ApiAuthRegisterEndpoint;
import ru.gb.endpoints.shop.ApiUserEndpoint;
import ru.gb.extensions.MobileShopApiTest;

import static io.restassured.RestAssured.given;
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
