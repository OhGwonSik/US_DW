package com.logistics.md.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MskustVO {
	/*
	 * ********************************************
	  - VO Name   : MskustVO
	  - Description    : 세트구성 마스터 테이블
	  - Made By        : Park T. S.
	  - Creation Date  : 2023.07.27
	  ------------------------------------------
	* ********************************************
	*/
	// P.K.
	private String compkey; // VARCHAR(20) - company key
	private String ownerky; // VARCHAR(20) - 화주 key
	private String warekey; // VARCHAR(20) - 창고 key
	private String skumkey; // VARCHAR(50) - 운영사 SKU
	private String sbskuky; // VARCHAR(50) - 구성품 SKU

	// Columns
	private int sbstqty; // INT(11) - 구성품 수량
	private String uomekey; // VARCHAR(10) - Unit of measure
	private String svendky;  // VARCHAR(20) - sub detail vender key
	private String stuseyn; // VARCHAR(1) - 사용여부

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
