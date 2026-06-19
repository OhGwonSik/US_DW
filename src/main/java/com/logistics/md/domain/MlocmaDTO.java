package com.logistics.md.domain;

import com.logistics.common.dto.CommonDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MlocmaDTO extends CommonDTO {
	/* 
	 * ******************************************** 
	  - DTO Name   : MlocmaDTO
	  - Description    : Location 마스터 테이블
	  - Made By        : Park T. S.
	  - Creation Date  : 2023.07.25
	  ------------------------------------------
	* ********************************************  
	*/
	// P.K
	private String compkey; // VARCHAR(20) - Company Key
	private String warekey; // VARCHAR(20) - Warehouse Key
	private String areakey; // VARCHAR(20) - Area key
	private String zonekey; // VARCHAR(20) - Zone Key
	private String locakey; // VARCHAR(20) - Location Key

	// Columns
	private String louseyn; // VARCHAR(1) - Location Use YN
	private String locanam; // VARCHAR(100) - Location Name
	private String loctype; // VARCHAR(10) - Location type
	private String capachk; // VARCHAR(1) - Capacity check
	private String locstat; // VARCHAR(10) - Location Status
	private int locdept; // INT(10) - depth
	private int locwidh; // INT(10) - Width
	private int locweig; // INT(10) - Max Weight
	private int locheig; // INT(10) - Height
	private int loccubi; // INT(10) - Cubic meter
	private int inbrtno; // INT(10) - Route for inbound
	private int otbrtno; // INT(10) - Route for outbound
	private String mixdsku; // VARCHAR(1) - Mixed stock by SKU
	private String mixdlot; // VARCHAR(1) - Mixed stock by Lot
	private String remtloc; // VARCHAR(1) - Replenishment Loc
	private String usptloc; // VARCHAR(1) - Use for putaway
	private String uspkloc; // VARCHAR(1) - Use for picking
	private String blockid; // VARCHAR(20) - Block
	private String physrow; // VARCHAR(3) - Row X열
	private String physsec; // VARCHAR(3) - Section Y행
	private String physflo; // VARCHAR(1) - floor Z단
	private String equstat; // VARCHAR(10) - Bin 상태 (보관,이동,PORT,제거)

	// Data
	private String whnamlc; // Warehouse Name<커스텀>
	private String areanam; // Area Name<커스텀>
	private String zonenam; // Zone Name<커스텀>

	private String oldWarekey;
	private String oldAreakey;
	private String oldZonekey;
	private String oldLocakey;

	private String stockky; // 재고키
	private String truntyp; // transfer unit type
	private String trunpid; // transfer unit id
	private String lotnmky; // lot number
	private String ownerky;
	private String skumkey; // 상품
	private String skudesc; // 상품명
	private String vendkey;
	private int stotqty; // 총재고 수량
	private Double gpakcnt; // 절
	private int sallqty; // 할당 수량
	private int sinbqty; // 입고중 수량
	private int soubqty; // 출고중 수량
	private int sbloqty; // 블락 수량
	private String suomkey; // Stock UOM
	private String skuwabc; // WH SKU ABC analyzed value
	private String brandcd; // 브랜드
	private String proddat; // 생산일자
	private int shelife; // * 유통기한
	private String expidat; // * 유통기간
	private String rcvdcdt; // 실물입고 일자
	private String rcvfrdt; // 최초 RECEVING DATE
	private String lotfrky; // 최초 LOT NUMBER
	private String stkstat; // 재고상태
	
	//2023.09.08 최강호 추가
	private String tozonky; //To Zone key
	private String tolocky; //To Location key
}
