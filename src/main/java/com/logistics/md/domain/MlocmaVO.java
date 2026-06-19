package com.logistics.md.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MlocmaVO {
	/*
	 * ********************************************
	  - VO Name   : MlocmaVO
	  - Description    : Location 마스터 테이블
	  - Made By        : Park T. S.
	  - Creation Date  : 2023.07.23
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
