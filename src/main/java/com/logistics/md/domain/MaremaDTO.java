package com.logistics.md.domain;

import com.logistics.common.dto.CommonDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MaremaDTO extends CommonDTO {
	/* 
	 * ******************************************** 
	  - DTO Name   : MaremaDTO
	  - Description    : AREA 마스터 테이블
	  - Made By        : Park T. S.
	  - Creation Date  : 2023.07.25
	  ------------------------------------------
	* ********************************************  
	*/
	// P.K.
	private String compkey; // VARCHAR(20) - Company Key
	private String warekey; // VARCHAR(20) - Warehouse Key
	private String areakey; // VARCHAR(20) - Area key

	// Columns
	private String aruseyn; // VARCHAR(1) - Area use YN
	private String areanam; // VARCHAR(100) - Area Name
	private String areatyp; // VARCHAR(10) - Area type
	private String syonlyn; // VARCHAR(10) - 시스템 기본 Area 여부

	// Data
	private String oldWarekey; // 입력용
	private String oldAreakey; // 입력용
}
