package com.logistics.api.nan.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class NanResponseDTO {

	@JsonProperty("ResultCode")
	private String resultCode;
	
	@JsonProperty("ResultMessage")
	private String resultMessage;
	
	@JsonProperty("StockLocationDetails")
	private List<NanStockDTO> stockLocationDetails;
	
	private String ifstksq;
}
