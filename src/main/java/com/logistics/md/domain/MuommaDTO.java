package com.logistics.md.domain;

import com.logistics.common.dto.CommonDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MuommaDTO extends CommonDTO{
	/* 
	 * ******************************************** 
	  - DTO Name   : MuommaDTO
	  - Description    : 유닛단위 마스터 테이블
	  - Made By        : Park T. S.
	  - Creation Date  : 2023.07.25
	  ------------------------------------------
	* ********************************************  
	*/
	// P.K.
	private String compkey; // VARCHAR(20) - Company key
	private String uomekey; // VARCHAR(20) - Unit of measure key
	
	// Columns
	private String uomenam; // VARCHAR(20) - 단위 이름
	private String uouseyn; // VARCHAR(10) - 사용여부
	
	// Data	
	private String oldUomekey;
}
