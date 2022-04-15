package ru.gb.restassured.dto.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tags{

	@JsonProperty("color")
	private String color;

	@JsonProperty("os")
	private String os;

	@JsonProperty("displayResolution")
	private String displayResolution;

	@JsonProperty("cpu")
	private String cpu;

	@JsonProperty("internalMemory")
	private String internalMemory;

	@JsonProperty("camera")
	private String camera;

	@JsonProperty("priceRange")
	private String priceRange;

	@JsonProperty("brand")
	private String brand;

	@JsonProperty("displaySize")
	private String displaySize;

	@JsonProperty("ram")
	private String ram;
}