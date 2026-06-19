package com.logistics.md.domain;

import java.util.List;

import com.logistics.common.dto.CommonDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MdocmaDTO extends CommonDTO {
	/*
	 ********************************************
	 - DTO Name : MdocmaDTO
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
	private String douseyn; // varchar(1) 문서 사용여부

	// Data
	private List<String> doctat5s; // 정산 가름
	private List<String> doctypes; // doctype 여러개
	private List<String> doccates; // doccate 여러개
}
