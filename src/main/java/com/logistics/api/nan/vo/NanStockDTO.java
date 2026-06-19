package com.logistics.api.nan.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class NanStockDTO {

	private String compkey;				//Company key
	private String ifstksq;				//IF재고 SEQ
	
	@JsonProperty("BrandCode")
	private String brandCode;			//브랜드 코드
	
	@JsonProperty("BrandName")
	private String brandName;			//브랜드 이름
	
	@JsonProperty("MainCode")
	private String mainCode;			//대표코드
	
	@JsonProperty("ProductCode")
	private String productCode; 		//상품 코드
	
	@JsonProperty("ProductName")
	private String productName; 		//상품명
	
	@JsonProperty("ColorCode")
	private String colorCode;			//색상 코드
	
	@JsonProperty("ColorName")
	private String colorName;			//색상명
	
	@JsonProperty("SizeCode")
	private String sizeCode;			//사이즈 코드
	
	@JsonProperty("SizeName")
	private String sizeName;			//사이즈명
	
	@JsonProperty("Barcode")
	private String barcode;				//바코드
	
	@JsonProperty("WareHousePCode")
	private String wareHousePCode;		//물류센터 코드
	
	@JsonProperty("WareHousePName")
	private String wareHousePName;		//물류센터명
	
	@JsonProperty("WareHouseLCode")
	private String wareHouseLCode;		//창고코드
	
	@JsonProperty("WareHouseLName")
	private String wareHouseLName;		//창고명
	
	@JsonProperty("Lot")
	private String lot;					//LOT
	
	@JsonProperty("ExpirationDate")
	private String expirationDate;		//유통기한
	
	@JsonProperty("StockQuantity")
	private int stockQuantity;			//재고 수량
	
	@JsonProperty("DeliveryWaitQuantity")
	private int deliveryWaitQuantity;	//배송 대기 수량
	
	@JsonProperty("AvailableStockQuantity")
	private int availableStockQuantity; //가용 재고 수량
	
	@JsonProperty("Location")
	private String location;			//다중로케이션
	
	private String ifprsts;				//데이터처리상태
	private String ifrcvdt;				//데이터수신일자
	private String ifrcvtm;				//데이터수신시간
	private String ifrcvur;				//데이터수신사용자
	private String iferrnm;				//작업오류내용
	private String ifcmpdt;				//작업완료일자
	private String ifcmptm;				//작업완료시간
	private String ifcmpur;				//작업완료사용자
	private String indibzl;				//Business Logic Indicator
	private String indiarc;				//Archive Indicator
	private int updtchk;				//Update Check
}
