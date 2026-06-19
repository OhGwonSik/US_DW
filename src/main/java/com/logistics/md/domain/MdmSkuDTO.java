package com.logistics.md.domain;

import com.logistics.common.dto.CommonDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MdmSkuDTO extends CommonDTO {
	/*
	 * ********************************************
	  - DTO Name   : MdmSkuDTO
	  - Description    : MDM SKU 데이터
	  - Made By        : Park T. S.
	  - Creation Date  : 2023.10.16
	  ------------------------------------------
	  - 수정자 : Park T. S.
	  - 수정일자 : 2023.12.13
	  - 수정내용 : 필드 추가
	* ********************************************
	*/
	// P.K.
	private String actcd; // varchar(7) 어카운트코드
	private String custcd; // varchar(7) 업체코드
	private String prtcd; // varchar(50) 부품코드
	private String aptcd; // varchar(50) 어카운트 부품코드

	// Columns
	private String custnm; // varchar(30) 업체명
	private String prtnm; // varchar(50) 부품명
	private String uncd; // varchar(5) 단위
	private String prtlv1; // varchar(30) 품목(대)
	private String prtlv2; // varchar(30) 품목(소)
	private String barcd; // varchar(20) 바코드

}
