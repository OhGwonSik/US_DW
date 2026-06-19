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
public class BplrmaDTO extends CommonDTO {
	
	/* 
	 * ******************************************** 
	  - DTO Name   : BplrmaDTO
	  - Description    : 택배비요율 마스터 테이블
	  - Made By        : 고은별
	  - Creation Date  : 2023.07.19
	  ------------------------------------------
	 
	* ********************************************  
	*/

	private String compkey; // varchar(20) 회사
	private String warekey; // varchar(20) 창고
	private String bplcode; // varchar(20) 택배요율코드
	private String ownerky; // varchar(20) 화주
	private String ptnrkey; // varchar(20) 협력사
	private String blrpmgb; // varchar(10) 대금구분
	private String blrcsgb; // varchar(10) 계정
	private String blrtkgb; // varchar(10) sub계정
	private String blrunit; // varchar(10) 단위
	private String doccate; // varchar(10) 문서유형
	private String doctype; // varchar(10) 문서타입
	private int blrtqty; // 수량(이하)
	private int blrcost; // int(11) 단가
	private int blrsutx; // int(11) 부가세
	private String blrdate; // varchar(6) 적용시작년월
	private String blrdtgb; // varchar(1) 기준일구분
	private String blrmfgb; // varchar(10) 월구분From
	private String blrcfdt; // varchar(2) 기준일From
	private String blrmtgb; // varchar(10) 월구분To
	private String blrctdt; // varchar(2) 기준일To
	private String blrtknm; // varchar(60) 명세표구분명
	private String bluseyn; // varchar(1) 사용여부

	// customize
	private String bilgcnt; //정산마감이력 count
	private String selectpoint;	//조회형태 APP적용위주, HIS내역위주

}
