package ru.gb.restassured.test.shop;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.gb.restassured.dto.shop.UserDto;
import ru.gb.restassured.extensions.MobileShopApiTest;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@MobileShopApiTest
public class RegisterUserTest {
    private static final Faker faker = new Faker();
    private UserDto userDto;
    String address = faker.address().fullAddress();
    String email = faker.internet().emailAddress();
    String password = faker.internet().password();
    String phone = faker.phoneNumber().phoneNumber();
    String username = faker.name().fullName();

    @BeforeEach
    void setUp() {
        userDto = UserDto.builder()
                .address(address)
                .password(password)
                .username(username)
                .email(email)
                .phone(phone)
                .build();
    }

    @Test
    void registerUserTest() {
        UserDto actualUserDto = given()
                .body(userDto)
                .post("/auth/register")
                .then()
                .statusCode(201)
                .extract()
                .as(UserDto.class);
        assertThat(actualUserDto)
                .usingRecursiveComparison()
                .ignoringFields("password")
                .ignoringExpectedNullFields()
                .isEqualTo(userDto);
    }
}
