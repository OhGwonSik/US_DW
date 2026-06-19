package com.logistics.md.domain;

import java.util.List;

import com.logistics.common.dto.CommonDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MskuwcDTO extends CommonDTO {
	/*
	 * ********************************************
	  - DTO Name   : MskuwcDTO
	  - Description    : 부품등록 마스터 테이블
	  - Made By        : Park T. S.
	  - Creation Date  : 2023.09.05
	  ------------------------------------------
	* ********************************************
	*/
	// P.K.
	private String compkey; // VARCHAR(20) - Company Key
	private String warekey; // VARCHAR(20) - 창고키
	private String ownerky; // VARCHAR(20) - 화주
	private String skumkey; // VARCHAR(50) - 운영사 SKU

	// Columns
	private String skudesc; // VARCHAR(100) - 운영사 SKU명
	private String skutype; // VARCHAR(10) - 상품유형
	private String cuskuky; // VARCHAR(50) - Customer SKU
	private String cuskunm; // VARCHAR(100) - Customer SKU Desc
	private String vdskuky; // VARCHAR(50) - Vender SKU
	private String vdskunm; // VARCHAR(100) - Vender SKU Desc
	private String skuskey = " "; // VARCHAR(50) - 대표SKU //(23.09.05 Park T. S. : Set default value for avoid procedure error)
	private String skusdsc; // VARCHAR(100) - 대표상품명
	private String vendkey; // VARCHAR(20) - 입고처
	private String packkey; // VARCHAR(10) 포장 키
	private String truntyp; // VARCHAR(10) - 이동용기타입
	private int propqty; // INT(11) - 적정재고수량
	private int miniqty; // INT(11) - 최소발주수량
	private int salfqty; // INT(11) - 안전재고수량
	private String sputzon; // - VARCHAR(20) 기본 적치 Zone
	private String sputloc; // VARCHAR(10) - 기본 적치 Location
	private String spikloc; // VARCHAR(10) - 기본 피킹 Location
	private int vndfday; // INT(11) - 납입소요일
	private String eancode; // VARCHAR(20) - EAN 코드
	private String upccode; // VARCHAR(20) - UPC 코드
	private String skugr01; // VARCHAR(30) - SKU Group 1 보관온도
	private String skugr02; // VARCHAR(30) - SKU Group 2 대분류
	private String skugr03; // VARCHAR(30) - SKU Group 3 중분류
	private String skugr04; // VARCHAR(30) - SKU Group 4 소분류
	private String skugr05; // VARCHAR(30) - SKU Group 5
	private String skualt1; // VARCHAR(50) - SKU 구분1
	private String skualt2; // VARCHAR(50) - SKU 구분2
	private String skualt3; // VARCHAR(50) - SKU 구분3
	private String skuat01; // VARCHAR(10) - SKU Attribute1 생산 Line type
	private String skuat02; // VARCHAR(10) - SKU Attribute2 완성차 차량코드
	private String skuat03; // VARCHAR(10) - SKU Attribute3 ALC Code
	private String skuat04; // VARCHAR(10) - SKU Attribute4 ALC INDEX
	private String skuat05; // VARCHAR(10) - SKU Attribute5
	private int sgrweig; // INT(11) - 포장중량
	private int sneweig; // INT(11) - 순중량
	private int skuleng; // INT(11) - Length,
	private int skuwidh; // INT(11) - Width,
	private int skuheig; // INT(11) - Height,
	private int skucubi; // INT(11) - Cubic meter
	private String suomkey; // VARCHAR(10) - Default unit of measure
	private String skunote; // VARCHAR(100) - 상품비교
	private String skuwabc; // VARCHAR(10) - WH SKU ABC
	private String skustat; // VARCHAR(20) - SKU상태

	// MPAKMA ( 2023.07.07 박소희 )
	private double packqty; // DECIMAL(10,3) 입수량
	private int stotqty; // INT(11) 총재고 수량  ( 2023.07.30 Park T. S. )

	// Data
	private String objcbid;
	private String oldWarekey;
	private String oldOwnerky;
	private String oldSkumkey;

	private List<String> skumkeys;
	private String notSet;

	private String whnamlc;
	private String ownamlc;
	
	private List<String> skustats;
}
