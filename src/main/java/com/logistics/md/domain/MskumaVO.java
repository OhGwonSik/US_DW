package com.logistics.md.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MskumaVO {
	/*
	 * ********************************************
	  - VO Name   : SKU VO
	  - Description    : SKU 마스터 테이블
	  - Made By        : Park T. S.
	  - Creation Date  : 2023.07.23
	  ------------------------------------------
	* ********************************************
	*/
	// P.K.
	private String compkey; // VARCHAR(20) - Company Key
	private String skumkey; // VARCHAR(50) - 상품코드
	private String ownerky; // VARCHAR(20) - 화주

	// Columns
	private String skudesc; // VARCHAR(100) - 상품명
	private String skutype; // VARCHAR(10) -
	private String cuskuky; // VARCHAR(50) - Customer SKU
	private String cuskunm; // VARCHAR(100) - Customer SKU Desc
	private String vdskuky; // VARCHAR(50) - Vender SKU
	private String vdskunm; // VARCHAR(50) - Vender SKU Desc
	private String skuskey; // VARCHAR(50) - 대표SKU
	private String skusdsc; // VARCHAR(50) - 대표SKU Desc
	private String vendkey; // VARCHAR(20) - 입고처
	private String vndfday; // INT(11) - 생산소요일
	private String packkey; // VARCHAR(10) - 포장 키
	private String eancode; // VARCHAR(20) - EAN 코드
	private String upccode; // VARCHAR(20) - UPC 코드
	private String brandcd; // VARCHAR(20) - 브랜드
	private String skugr01; // VARCHAR(20) - SKU Group 1
	private String skugr02; // VARCHAR(20) - SKU Group 2
	private String skugr03; // VARCHAR(20) - SKU Group 3
	private String skugr04; // VARCHAR(20) - SKU Group 4
	private String skugr05; // VARCHAR(20) - SKU Group 5
	private String skualt1; // VARCHAR(50) - SKU Alternative1
	private String skualt2; // VARCHAR(50) - SKU Alternative2
	private String skualt3; // VARCHAR(50) - SKU Alternative3
	private String skuat01; // VARCHAR(10) - SKU Attribute1
	private String skuat02; // VARCHAR(10) - SKU Attribute2
	private String skuat03; // VARCHAR(10) - SKU Attribute3
	private String skuat04; // VARCHAR(10) - SKU Attribute4
	private String skuat05; // VARCHAR(10) - SKU Attribute5
	private int sgrweig; // INT(11) - Gross weight
	private int sneweig; //// INT(11) Net weight
	private int skuleng; //// INT(11) Length
	private int skuwidh; //// INT(11) Width
	private int skuheig; //// INT(11) Height
	private int skucubi; //// INT(11) Cubic meter
	private String suomkey; // VARCHAR(10) - Default unit of measure
	private String skuimg1; // VARCHAR(100) - 상품이미지1
	private String skuimg2; // VARCHAR(100) - 상품이미지2
	private String skuimg3; // VARCHAR(100) - 상품이미지3
	private String skuimg4; // VARCHAR(100) - 상품이미지4
	private String skuimg5; // VARCHAR(100) - 상품이미지5
	private String skumabc; // VARCHAR(10) - SKU ABC analyzed value
	private String skustat; // VARCHAR(20) - SKU 상태

	// Common columns
	private String credate; // VARCHAR(8) create date
	private String cretime; // VARCHAR(6) create time
	private String creuser; // VARCHAR(60) create user
	private String lmodate; // VARCHAR(8) last modify date
	private String lmotime; // VARCHAR(6) last modify time
	private String lmouser; // VARCHAR(60) last modify user
	private String indibzl; // VARCHAR(1) biz logic indicator
	private String indiarc; // VARCHAR(1) Archive indicator
	private int updtchk; // INT(11) Update check
}
