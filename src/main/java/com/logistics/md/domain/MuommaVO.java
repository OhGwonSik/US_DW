package com.logistics.md.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MuommaVO {
	/*
	 * ********************************************
	  - VO Name   : MuommaVO
	  - Description    : 유닛단위 마스터 테이블
	  - Made By        : Park T. S.
	  - Creation Date  : 2023.07.23
	  ------------------------------------------
	* ********************************************
	*/
	// P.K.
	private String compkey; // VARCHAR(20) - Company key
	private String uomekey; // VARCHAR(20) - Unit of measure key

	// Columns
	private String uomenam; // VARCHAR(20) - 단위 이름
	private String uouseyn; // VARCHAR(10) - 사용여부

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

