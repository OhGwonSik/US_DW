package com.logistics.md.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MaremaVO {
	/*
	 * ********************************************
	  - VO Name   : MaremaVO
	  - Description    : area 마스터 테이블
	  - Made By        : Park T. S.
	  - Creation Date  : 2023.07.25
	  ------------------------------------------
	* ********************************************
	*/
	// P.K.
	private String compkey; // VARCHAR(20) - Company Key
	private String warekey; // VARCHAR(20) - Warehouse Key
	private String areakey; // VARCHAR(20) - Area key

	// Columns
	private String aruseyn; // VARCHAR(1) - Area use YN
	private String areanam; // VARCHAR(100) - Area Name
	private String areatyp; // VARCHAR(10) - Area type
	private String syonlyn; // VARCHAR(10) - 시스템 기본 Area 여부

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
