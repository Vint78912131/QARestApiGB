package ru.gb.util;

import com.github.javafaker.Faker;
import ru.gb.dto.shop.UserDto;

public class TestDataHelper {
    private static final Faker faker = new Faker();

    public static UserDto generateUserDto() {
        String address = faker.address().fullAddress();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        String phone = faker.phoneNumber().phoneNumber();
        String username = faker.name().fullName();
        return UserDto.builder()
                .address(address)
                .password(password)
                .username(username)
                .email(email)
                .phone(phone)
                .build();
    }
}
