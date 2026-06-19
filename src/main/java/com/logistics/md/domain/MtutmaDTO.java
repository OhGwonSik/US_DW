package com.logistics.md.domain;

import com.logistics.common.dto.CommonDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class MtutmaDTO extends CommonDTO {
	/* 
	 * ******************************************** 
	  - DTO Name   : MtutmaDTO
	  - Description    : 이동용기 마스터 테이블
	  - Made By        : Park T. S.
	  - Creation Date  : 2023.07.25
	  ------------------------------------------
	* ********************************************  
	*/
	// P.K.
	private String compkey; // VARCHAR(20) - Company Key
	private String truntyp; // VARCHAR(10) - 이동용기타입
	
	// Columns
	private String trunnam; // VARCHAR(20) - 이동용기명칭
	private String truseyn; // VARCHAR(10) - 사용여부
	private int tutleng; // INT(11) - 길이
	private int tutwidh; // INT(11) -  폭
	private int tutheig; // INT(11) - 높이
	private int tutcubi; // INT(11) - 입방미터
	private int tutweig; // INT(11) - 중량
	private String uomekey; // VARCHAR(10) - 중량단위

	// data
	private String oldTruntyp;
}
