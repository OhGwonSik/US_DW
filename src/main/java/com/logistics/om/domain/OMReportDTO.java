package com.logistics.om.domain;

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
public class OMReportDTO extends CommonDTO {
	
	/* 
	 * ******************************************** 
	  - DTO Name       : OMReportDTO
	  - Description    : OMReportDTO - OM 리포트 출력을 위해 필요한 필드들을 담은 DTO
	  - Made By        : 하주영
	  - Creation Date  : 2023.07.06
	  ------------------------------------------
	 
	* ********************************************  
	*/
	
	//반출증
	private String oeretky;	//물류오더 문서번호 - 반출증NO
	private String ptnamlc;	//협력업체 명칭 - 상호
	private String ptaddr1;	//협력업체 주소1
	private String ptaddr2;	//협력업체 주소2
	private String ptaddr3;	//협력업체 주소3
	private String ptaddr;	//협력업체 주소 - 주소
	private int rtodqty;	//반출지시수량 - 반출수량
	private String suomkey;	//Stock UOM - 단위
	private String oeinsdt;	//물류오더 지시일자 - 반출일자
	private String rsncdnm;	//반출사유
	private String creuser; //반출자
	private String owaddr;
	
	//납입카드
	private String copodky;	//발주오더번호	- 납품서NO
	private String reqdate;	//영업일자 - 입고요청일
	private String denamlc;	//도착지명칭 - 납품처
	private int asndqty;	//입고예정수량 - 납입수량
	private String auomkey;	//입고예정단위 - 단위
	private String boxcnt;	//용기당수량
	private String asnmemo;	//ASN특이사항 - 특이사항
	private String eoasnky;	//ASN번호
	private String truntyp; //용기구분타입 - 용기
	private int totalAsndqty;	//총 갯수
	private int totalColumn; // 총 아이템 갯수
	
	//반출증, 납입카드 공통
	private String compkey;	//company Code
	private String skumkey;	//운영사SKU - 부품코드 OR 부품번호
	private String skudesc;	//운영사SKU명 - 부품명
	private String skuat03;	//ALC
	private int rownum;
	private String type; //구분 type
	private String ownerky;	//화주 - 공급자
	private String ownamlc;	//화주명칭
	private int printcount;
	
	//프린트 로그
	private String doccate;
	private String doctype;
	private String warekey;
	private String progrid;
	private String dochkey;
	private int docitem;
	private String loguser;
	private String user;
}
