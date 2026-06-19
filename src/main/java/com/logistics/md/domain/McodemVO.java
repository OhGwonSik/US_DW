package com.logistics.md.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class McodemVO {
	/*
	 ********************************************
	 - DTO Name : McodemDTO
	 - Description : 공용코드 마스터 테이블(실사용에서는 MDM을 사용)
	 - Made By : Park T. S.
	 - Creation Date :  2023.07.25
	 ------------------------------------------
	 ********************************************
	 */
	// P.K.
	private String compkey; // VARCHAR(20) - Company Key
	private String comcdky; // VARCHAR(20) - Common code Key
	private String comcdvl; // VARCHAR(20) - Common code value

	// Columns
	private String warekey; // VARCHAR(20) - Warehouse key
	private String comkytx; // VARCHAR(100) - Common key Text
	private String comcdtx; // VARCHAR(100) - Common code Text
	private int comcdor; // INT(11) - Code 표시 순서
	private String comcdsy; // VARCHAR(1) - Code 시스템 전용
	private String cdcate1; // VARCHAR(50) - Code 분류1
	private String cdcate2; // VARCHAR(50) - Code 분류2
	private String cdcate3; // VARCHAR(50) - Code 분류3
	private String cdattr1; // VARCHAR(50) - Code Attribute1
	private String cdattr2; // VARCHAR(50) - Code Attribute2
	private String cdattr3; // VARCHAR(50) - Code Attribute3
	private String couseyn; // VARCHAR(1) - 사용YN(Y=사용)

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
