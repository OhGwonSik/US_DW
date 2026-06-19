package com.logistics.md.domain;

import com.logistics.common.dto.CommonDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MpakmaDTO extends CommonDTO {
	/* 
	 * ******************************************** 
	  - DTO Name   : MpakmaDTO
	  - Description    : 포장용기 마스터 테이블
	  - Made By        : Park T. S.
	  - Creation Date  : 2023.07.25
	  ------------------------------------------
	* ********************************************  
	*/
	// P.K.
	// P.K.
	private String compkey; // VARCHAR(20) - Company Key
	private String warekey; // VARCHAR(20) - 창고
	private String truntyp; // VARCHAR(20) - 이동용기타입
	private String packkey; // VARCHAR(10) - 포장키
	
	// Columns
	private String pkuseyn; // VARCHAR(1) - 사용YN
	private double packqty; // DECIMAL(10,3) - 입수량
	private String puomkey; // VARCHAR(10) - 단위

	// data
	private String trunnam;
	private String oldWarekey;
	private String oldPackkey;
	private String oldTruntyp;
}
