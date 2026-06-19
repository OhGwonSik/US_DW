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
public class BvtrmaDTO extends CommonDTO {

	/* 
	 * ******************************************** 
	  - DTO Name   : BvtrmaDTO
	  - Description    : 변동운송비요율 마스터 테이블
	  - Made By        : 고은별
	  - Creation Date  : 2023.07.19
	  ------------------------------------------
	 
	* ********************************************  
	*/
	
	private String compkey; // varchar(20) 회사
	private String warekey; // varchar(20) 창고	
	private String bvtcode; // varchar(20) 변동운송요율코드
	private String ownerky; // varchar(20) 화주
	private String vehicky; // varchar(10) 차량코드
	private String btrpmgb; // varchar(10) 대금구분
	private String ptnrkey; // varchar(20) 대금구분업체
	private String btrcsgb; // varchar(10) 계정	
	private String btrtkgb; // varchar(10) sub계정	
	private String btrunit; // varchar(10) 단위	
	private String doccate; // varchar(10) 문서유형	
	private String doctype; // varchar(10) 문서타입
	private int btrcost = 0; // int(11) 단가
	private int btrsutx = 0; // int(11) 부가세
	private String btrdate; // varchar(6) 적용시작년월
	private String btrdtgb; // varchar(1) 기준일구분
	private String btrmfgb; // varchar(10) 월구분From
	private String btrcfdt; // varchar(2) 기준일From
	private String btrmtgb; // varchar(10) 월구분To
	private String btrctdt; // varchar(2) 기준일To
	private String btrtknm; // varchar(60) 명세표구분명
	private String btuseyn; // varchar(1) 사용여부
	private String shtrtky; // varchar(8) 셔틀노선키

	// customize
	private String bilgcnt; //정산마감이력 count
	private String selectpoint;	//조회형태 APP적용위주, HIS내역위주
}
