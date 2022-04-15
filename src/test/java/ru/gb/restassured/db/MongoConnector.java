package ru.gb.restassured.db;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import lombok.SneakyThrows;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import ru.gb.restassured.dto.shop.PhoneDto;

public class MongoConnector {

    @SneakyThrows
    public static MongoDatabase getDataBase() {
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );

        return MongoClients.create("mongodb://root:password@localhost:27017")
                .getDatabase("mobileShop")
                .withCodecRegistry(codecRegistry);
    }

}
