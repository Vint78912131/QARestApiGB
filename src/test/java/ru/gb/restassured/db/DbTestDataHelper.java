package ru.gb.restassured.db;

import com.mongodb.client.model.Filters;
import org.bson.types.ObjectId;
import ru.gb.restassured.dto.shop.PhoneDto;
import ru.gb.restassured.dto.shop.UserDto;

import java.util.ArrayList;
import java.util.List;

import static ru.gb.restassured.db.MongoConnector.getDataBase;

public class DbTestDataHelper {

    public static List<PhoneDto> getAllPhones() {
        List<PhoneDto> phoneDtoList = new ArrayList<>();
        return getDataBase().getCollection("products", PhoneDto.class)
                .find().into(phoneDtoList);
    }

    public static void deleteUser(UserDto userDto) {
        getDataBase().getCollection("users")
                .deleteOne(Filters.eq("id", userDto.getId()));
    }


}
