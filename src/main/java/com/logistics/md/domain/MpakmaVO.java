package com.logistics.md.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MpakmaVO {
	/*
	 * ********************************************
	  - VO Name   : MpakmaVO
	  - Description    : 포장용기 마스터 테이블
	  - Made By        : Park T. S.
	  - Creation Date  : 2023.07.25
	  ------------------------------------------
	* ********************************************
	*/
	// P.K.
	private String compkey; // VARCHAR(20) - Company Key
	private String warekey; // VARCHAR(20) - 창고
	private String truntyp; // VARCHAR(20) - 이동용기타입
	private String packkey; // VARCHAR(10) - 포장키

	// Columns
	private String pkuseyn; // VARCHAR(1) - 사용YN
	private double packqty; // DECIMAL(10,3) - 입수량
	private String puomkey; // VARCHAR(10) - 단위

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
