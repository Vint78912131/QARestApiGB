package ru.gb.restassured.test.shop;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.gb.restassured.dto.shop.UserDto;
import ru.gb.restassured.endpoints.shop.ApiAuthRegisterEndpoint;
import ru.gb.restassured.extensions.MobileShopApiTest;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.gb.restassured.db.DbTestDataHelper.deleteUser;
import static ru.gb.restassured.util.TestDataHelper.generateUserDto;

@MobileShopApiTest
public class RegisterUserTest {
    UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = generateUserDto();
    }

    @Test
    void registerUserTest() {
        UserDto actualUserDto = new ApiAuthRegisterEndpoint().registerUser(userDto);
        assertThat(actualUserDto)
                .usingRecursiveComparison()
                .ignoringFields("password")
                .ignoringExpectedNullFields()
                .isEqualTo(userDto);
    }

    @AfterEach
    void tearDown() {
        deleteUser(userDto);
    }
}
