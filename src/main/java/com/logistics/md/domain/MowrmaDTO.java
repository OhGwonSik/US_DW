package com.logistics.md.domain;

import com.logistics.common.dto.CommonDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MowrmaDTO extends CommonDTO {
	/*
	 * ********************************************
	  - DTO Name   : MowrmaDTO
	  - Description    : owner 마스터 테이블
	  - Made By        : Park T. S.
	  - Creation Date  : 2023.07.25
	  ------------------------------------------
	* ********************************************
	*/
	// P.K.
	private String compkey; // VARCHAR(20) - Company Key
	private String ownerky; // VARCHAR(20) - 화주 키

	// Columns
	private String ownatcd; // VARCHAR(3) - 화주 국가코드
	private String ownamlc; // VARCHAR(60) - 화주 명칭
	private String ownamko; // VARCHAR(60) - 화주 명칭 한글
	private String ownamen; // VARCHAR(60) - 화주 명칭 영어
	private String owaddr1; // VARCHAR(100) - 주소1
	private String owaddr2; // VARCHAR(100) - 주소2
	private String owaddr3; // VARCHAR(100) - 주소3
	private double ownelat; // DECIMAL(15,10) - 위도
	private double ownelon; // DECIMAL(15,10) - 경도
	private String owciynm; // VARCHAR(100) - City
	private String owregnm; // VARCHAR(100) - Region
	private String owteln1; // VARCHAR(20) - 전화번호1
	private String owteln2; // VARCHAR(20) - 전화번호2
	private String owteln3; // VARCHAR(20) - 전화번호3
	private String owtaxcd; // VARCHAR(20) - tax code
	private String owposbx; // VARCHAR(10) - PO box
	private String owposcd; // VARCHAR(10) - postal code
	private String owrepnm; // VARCHAR(60) - 대표자 이름
	private String owreptl; // VARCHAR(20) - 대표자 전화번호
	private String owmanem; // VARCHAR(60) - 대표자 email
	private String owmannm; // VARCHAR(60) - 담당자 이름
	private String owmantl; // VARCHAR(20) - 담당자 전화번호
	private String owrepem; // VARCHAR(60) - 담당자 email
	private String owblctc; // VARCHAR(20) - 사업자번호
	private String sputzon; // VARCHAR(20) - 기본 적치 zone
	private String sputloc; // VARCHAR(20) - 기본 적치 location
	private String spikloc; // VARCHAR(20) - 기본 피킹 location
	private String owneord; // INT(11) - 화주 조회순서
	private String owuseyn; // VARCHAR(1) - 사용여부(Y : 사용)

	// Data
	private String oldOwnerky;
}