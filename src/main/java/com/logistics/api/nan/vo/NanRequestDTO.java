package com.logistics.api.nan.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NanRequestDTO {

	@JsonProperty("StandardDate")
	private String standardDate;
	
	@JsonProperty("WareHousePCode")
	private String wareHousePCode;
	
	@JsonProperty("WareHouseLCode")
	private String wareHouseLCode;
	
	@JsonProperty("ProductCode")
	private String productCode;
}
