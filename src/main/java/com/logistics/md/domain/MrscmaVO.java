package com.logistics.md.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MrscmaVO {
	/*
	 * ********************************************
	  - VO Name   : 사유코드 VO
	  - Description    : 사유코드 마스터 테이블
	  - Made By        : Park T. S.
	  - Creation Date  : 2023.07.25
	  ------------------------------------------
	* ********************************************
	*/
	// P.K.
	private String compkey; // VARCHAR(20) - Company Key
	private String warekey; // VARCHAR(20) - Warehouse Key
	private String doccate; // VARCHAR(10) - 문서유형
	private String doctype; // VARCHAR(10) - 문서타입
	private String rsncode; // VARCHAR(10) - 사유코드

	// Columns
	private String rsncdnm; // VARCHAR(10) - 사유코드명
	private String rsnmlky; // VARCHAR(10) - 사유코드명 다국어키
	private double rsncdod; // DECIMAL(10,3) - 사유코드 표시 순서
	private String rscate1; // VARCHAR(10) - 사유분류1 -> normal, cancel
	private String rscate2; // VARCHAR(10) - 사유분류2
	private String rscate3; // VARCHAR(10) - 사유분류3
	private String rsattr1; // VARCHAR(10) - 사유속성1
	private String rsattr2; // VARCHAR(10) - 사유속성2
	private String rsattr3; // VARCHAR(10) - 사유속성3
	private String rsuseyn; // VARCHAR(1) - 사용여부

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
