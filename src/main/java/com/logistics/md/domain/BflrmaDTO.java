package com.logistics.md.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.logistics.common.dto.CommonDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true) 
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BflrmaDTO extends CommonDTO {
	
	/* 
	 * ******************************************** 
	  - DTO Name   : BflrmaDTO
	  - Description    : 고정물류비요율 마스터 테이블
	  - Made By        : 고은별
	  - Creation Date  : 2023.06.30
	  ------------------------------------------
	 
	* ********************************************  
	*/
	
	private String compkey; // varchar(20) 회사
	private String bflcode; // varchar(20) 요율코드
	private String warekey; // varchar(20) 창고
	private String ownerky; // varchar(20) 화주
	private String ptnrkey; // varchar(20) 협력사
	private String blrpmgb; // varchar(10) 대금구분
	private String blrcsgb; // varchar(10) 계정
	private String blrtkgb; // varchar(10) sub계정

	private int blrcost = 0; // int(11) 단가
	private int blrsutx = 0; // int(11) 부가세
	private String blrdate; // varchar(6) 적용시작년월


	private String blrtknm; // varchar(60) 명세표구분명
	private String bluseyn; // varchar(1) 사용여부

	// customize
	private String bilgcnt; //정산마감이력 count
	private String selectpoint;	//조회형태 APP적용위주, HIS내역위주
}
