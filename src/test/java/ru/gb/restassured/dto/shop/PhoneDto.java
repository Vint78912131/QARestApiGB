package ru.gb.restassured.dto.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneDto {

    @JsonProperty("_id")
    private ObjectId id;

    @JsonProperty("info")
    private Info info;

    @JsonProperty("tags")
    private Tags tags;
}