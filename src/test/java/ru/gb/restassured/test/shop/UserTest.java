package ru.gb.restassured.test.shop;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.gb.restassured.dto.shop.UserDto;
import ru.gb.restassured.endpoints.shop.ApiAuthRegisterEndpoint;
import ru.gb.restassured.endpoints.shop.ApiUserEndpoint;
import ru.gb.restassured.extensions.MobileShopApiTest;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.gb.restassured.db.DbTestDataHelper.deleteUser;
import static ru.gb.restassured.util.TestDataHelper.generateUserDto;

@MobileShopApiTest
public class UserTest {

    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new ApiAuthRegisterEndpoint().registerUser(generateUserDto());
    }

    @Test
    void getUserTest() {
        UserDto actualUser = new ApiUserEndpoint().getUser(userDto.getToken());
        assertThat(actualUser)
                .isEqualTo(userDto);
    }

    @AfterEach
    void tearDown() {
        deleteUser(userDto);
    }
}
