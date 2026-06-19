package com.logistics.md.domain;

import com.logistics.common.dto.CommonDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class McommaDTO extends CommonDTO {
	/*
	 ********************************************
	 - DTO Name : McommaDTO
	 - Description : 회사 마스터 테이블
	 - Made By : Park T. S.
	 - Creation Date :  2023.07.23 
	 ------------------------------------------
	 ********************************************
	 */
	// P.K.
	private String compkey; // VARCHAR(20) - Company Key

	// Columns
	private String couseyn; // VARCHAR(1) - 사용여부(Y : 사용)
	private String conamlc; // VARCHAR(60) - Company name
	private String conamko; // VARCHAR(50) - 한글 Company name
	private String conamen; // VARCHAR(50) - 영어 Company name
	private String coaddr1; // VARCHAR(100) - 주소1
	private String coaddr2; // VARCHAR(100) - 주소2
	private String coaddr3; // VARCHAR(100) - 주소3
	private double complat; // DECIMAL(15,10) - 위도
	private double complon; // DECIMAL(15,10) - 경도
	private String cociynm; // VARCHAR(100) - City
	private String coregnm; // VARCHAR(100) - Region
	private String coteln1; // VARCHAR(20) - 전화번호1
	private String coteln2; // VARCHAR(20) - 전화번호2
	private String coteln3; // VARCHAR(20) - 전화번호3
	private String cotaxcd; // VARCHAR(20) - tax code
	private String coposbx; // VARCHAR(10) - PO box
	private String coposcd; // VARCHAR(10) - postal code
	private String corepnm; // VARCHAR(60) - 대표자 이름
	private String coreptl; // VARCHAR(20) - 대표자 전화번호
	private String corepem; // VARCHAR(60) - 대표자 email
	private String comannm; // VARCHAR(60) - 담당자 이름
	private String comantl; // VARCHAR(20) - 담당자 연락처
	private String comanem; // VARCHAR(60) - 담당자 email
	private int cotmzon; // INT(11) - Time zone by company
}
