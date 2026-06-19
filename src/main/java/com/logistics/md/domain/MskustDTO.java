package com.logistics.md.domain;

import com.logistics.common.dto.CommonDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MskustDTO extends CommonDTO {
	/* 
	 * ******************************************** 
	  - DTO Name   : MskustDTO
	  - Description    : 세트구성 마스터 테이블
	  - Made By        : Park T. S.
	  - Creation Date  : 2023.07.27
	  ------------------------------------------
	* ********************************************  
	*/
	// P.K.
	private String compkey; // VARCHAR(20) - company key
	private String ownerky; // VARCHAR(20) - 화주 key
	private String warekey; // VARCHAR(20) - 창고 key
	private String skumkey; // VARCHAR(50) - 운영사 SKU
	private String sbskuky; // VARCHAR(50) - 구성품 SKU
	
	// Columns
	private int sbstqty; // INT(11) - 구성품 수량
	private String uomekey; // VARCHAR(10) - Unit of measure
	private String svendky;  // VARCHAR(20) - sub detail vender key
	private String stuseyn; // VARCHAR(1) - 사용여부
	
	// Data
	private String skudesc;
	private String sbskuds; // custom(sub sku의 skudesc 찾을 때)
	
	private String oldSkumkey;
	private String oldSkudesc;
	private String oldSbskuky;
}
