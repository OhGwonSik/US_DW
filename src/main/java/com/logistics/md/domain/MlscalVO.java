package com.logistics.md.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MlscalVO {
	/*
	 * ********************************************
	  - VO Name   : MlscalVO
	  - Description    : 물류달력 마스터 테이블
	  - Made By        : Park T. S.
	  - Creation Date  : 2023.08.04
	  ------------------------------------------
	* ********************************************
	*/
	// P.K.
	private String compkey; // VARCHAR(20) - Company Key
	private String warekey;  // VARCHAR(20) - 창고 키
	private String stddate;  // VARCHAR(8) - 일자

	// Columns
	private String logyear; // VARCHAR(4) - 년도
	private String logmont; // VARCHAR(2) - 월
	private String logdate; // VARCHAR(2) - 일
	private String logweek; // VARCHAR(10) - 요일
	private String weknum7; // VARCHAR(6) - 주차(월요일 기준) - MUL
	private String weknum0; // VARCHAR(0) - 주차(일요일 기준)
	private String natholi; // VARCHAR(1) - 휴일여부(Y = yes)

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
