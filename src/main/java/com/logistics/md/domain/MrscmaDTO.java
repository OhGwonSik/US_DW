package com.logistics.md.domain;

import com.logistics.common.dto.CommonDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MrscmaDTO extends CommonDTO {
	/*
	 * ********************************************
	  - DTO Name   : 사유코드 DTO
	  - Description    : 사유코드 마스터 테이블
	  - Made By        : Park T. S.
	  - Creation Date  : 2023.07.23
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

	// Data
	private String oldWarekey; // warekey
	private String oldRsncode; // 사유코드

	private String rcarscd; //취소사유코드 - 2023.09.15 최강호
	private String pkrsncd; //적치취소사유코드 - 2023.11.20 최강호

	// MDOCMA
	private String docctnm; // VARCHAR(60) - Document category name
	private String doctynm; // VARCHAR(60) - Document type Name
}
