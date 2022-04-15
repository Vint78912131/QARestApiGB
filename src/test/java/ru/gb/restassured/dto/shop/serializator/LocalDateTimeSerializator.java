package ru.gb.restassured.dto.shop.serializator;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

public class LocalDateTimeSerializator extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(Duration.between(LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0),
                localDateTime).toNanos() / 1000);
    }
}
