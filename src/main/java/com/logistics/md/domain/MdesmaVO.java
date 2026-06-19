package com.logistics.md.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MdesmaVO {
	/*
	 * ********************************************
	  - VO Name   : MdesmaVO
	  - Description    : 도착지 마스터 테이블
	  - Made By        : Park T. S.
	  - Creation Date  : 2023.08.04
	  ------------------------------------------
	* ********************************************
	*/
	// P.K.
	private String compkey; // VARCHAR(20) - Company Key
	private String ptnrkey; // VARCHAR(20) - 협력사 Key
	private String destkey; // VARCHAR(20) - 도착지 Key

	// Columns
	private String denamlc; // VARCHAR(60) - 도착지 이름
	private String denatcd; // VARCHAR(3) - 도착지 국가코드
	private String denamko; // VARCHAR(60) - 도착지 한글 이름
	private String denamen; // VARCHAR(60) - 도착지 영어 이름
	private String deaddr1; // VARCHAR(100) - 도착지 주소1
	private String deaddr2; // VARCHAR(100) - 도착지 주소2
	private String deaddr3; // VARCHAR(100) - 도착지 주소3
	private String deposcd; // VARCHAR(10) - 도착지 우편번호
	private double destlat; // DECIMAL(15,10) - 도착지 위도
	private double destlon; // DECIMAL(15,10) - 도착지 경도
	private String unavat1; // VARCHAR(10) - 진입불가 차량 톤수1
	private String unavat2; // VARCHAR(10) - 진입불가 차량 톤수2
	private String unavat3; // VARCHAR(10) - 진입불가 차량 톤수3
	private String cusgr01; // VARCHAR(10) - customer 그룹1
	private String cusgr02; // VARCHAR(10) - customer 그룹2
	private String cusgr03; // VARCHAR(10) - customer 그룹3
	private String sundavl; // VARCHAR(1) - 일
	private String mondavl; // VARCHAR(1) - 월
	private String tuedavl; // VARCHAR(1) - 화
	private String weddavl; // VARCHAR(1) - 수
	private String thudavl; // VARCHAR(1) - 목
	private String fridavl; // VARCHAR(1) - 금
	private String satdavl; // VARCHAR(1) - 토
	private String natdavl; // VARCHAR(1) - 국경일
	private String dlvrqtm; // VARCHAR(6) - 납품요청시간
	private String routeky; // VARCHAR(20) - 배송권역 ROUTE
	private String unlodmo; // VARCHAR(10) - 하역 특이사항
	private String cuspecd; // VARCHAR(10) - 고객 특이사항 코드
	private String cusperm; // VARCHAR(10) - 고객 특이사항 내용
	private String deuseyn; // VARCHAR(1) - 도착지 사용여부(Y = use)

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
