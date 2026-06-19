package com.logistics.md.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MtutmaVO {
	/*
	 * ********************************************
	  - VO Name   : MtutmaVO
	  - Description    : 이동용기 마스터 테이블
	  - Made By        : Park T. S.
	  - Creation Date  : 2023.07.25
	  ------------------------------------------
	* ********************************************
	*/
	// P.K.
	private String compkey; // VARCHAR(20) - Company Key
	private String truntyp; // VARCHAR(10) - 이동용기타입

	// Columns
	private String trunnam; // VARCHAR(20) - 이동용기명칭
	private String truseyn; // VARCHAR(10) - 사용여부
	private int tutleng; // INT(11) - 길이
	private int tutwidh; // INT(11) -  폭
	private int tutheig; // INT(11) - 높이
	private int tutcubi; // INT(11) - 입방미터
	private int tutweig; // INT(11) - 중량
	private String uomekey; // VARCHAR(10) - 중량단위

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
