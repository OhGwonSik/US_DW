package com.logistics.md.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MdocmaVO {
	/*
	 ********************************************
	 - VO Name : MdocmaVO
	 - Description : Document 마스터 테이블
	 - Made By : Park T. S.
	 - Creation Date :  2023.07.25
	 ------------------------------------------
	 ********************************************
	 */
	// P.K.
	private String compkey; // VARCHAR(20) - Company Key
	private String warekey; //  VARCHAR(20) - warehouse Key
	private String doctype; // VARCHAR(10) - Document type

	// Columns
	private String doccate; // VARCHAR(10) - Document category
	private String docctnm; // VARCHAR(60) - Document category name
	private String doctynm; // VARCHAR(60) - Document type Name
	private String prareky; // VARCHAR(20) - From Area
	private String przonky; // VARCHAR(20) - From Zone
	private String prlocky; // VARCHAR(20) - From Location
	private String toareky; // VARCHAR(20) - to Area
	private String tozonky; // VARCHAR(20) - to Zone
	private String tolocky; // VARCHAR(20) - to Location
	private String doctat1;  // VARCHAR(50) - 타입속성1
	private String doctat2;  // VARCHAR(50) - 타입속성2
	private String doctat3;  // VARCHAR(50) - 타입속성3
	private String doctat4;  // VARCHAR(50) - 타입속성4
	private String doctat5;  // VARCHAR(50) - 타입속성5
	private String doctico; // VARCHAR(100) 문서타입 아이콘
	private String docclky; // VARCHAR(20) 문서유형다국어키
	private String doctlky; // VARCHAR(20) 문서유형다국어키
	private String douseyn; // VARCHAR(1) 문서 사용여부

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
