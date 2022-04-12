package ru.gb.dto.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.dto.shop.serializator.LocalDateTimeDeserializator;
import ru.gb.dto.shop.serializator.LocalDateTimeSerializator;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem{

	@JsonProperty("quantity")
	private int quantity;

	@JsonProperty("dateCreated")
	@JsonSerialize(using = LocalDateTimeSerializator.class)
	@JsonDeserialize(using = LocalDateTimeDeserializator.class)
	private LocalDateTime dateCreated;

	@JsonProperty("price")
	private int price;

	@JsonProperty("name")
	private String name;
}