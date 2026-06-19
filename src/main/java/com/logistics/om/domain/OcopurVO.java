package com.logistics.om.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties
public class OcopurVO {

	private String recindx;	// recindx
	private String compkey; // Company Key
	private String copodky;	// 고객발주 오더번호
	private int copodit;	// 고객발주 아이템번호
	private String doccate;	// 문서유형
	private String doctype; // 문서타입
	private String purstat;	// 발주상태
	private String purdate;	// 발주일자
	private String purtime;	// 발주시간
	private String custkey; // 고객
	private String destkey; // 도착지
	private String vendkey; // 입고처
	private String ownerky; // 화주
	private String skuskey; // 대표상품
	private String skumkey; // 상품
	private String skudesc; // 상품명
	private int poitqty; 	// 발주수량
	private String puomkey; // 발주UOM
	private int rchsqty; 	// ASN 누적수량
	private String podmemo; // 발주 요구사항
	private String podelyn; // 발주 삭제여부
	private String rcvwhky; // 입고 창고
	private String reqdate; // 입고 요청일
	private String reqtime; // 입고 요청시간
	private String reqmemo; // 입고 요구사항
	private String cooutky; // 수주 문서번호
	private int cooutit; 	// 수주 아이템번호
	private String vndcfyn; // 입고처 확인여부
	private String vndcfdt; // 입고처 확인일자
	private String vndcftm; // 입고처 확인시간
	private String dctctyp; // 주문유형
	private String credate;	// 생성일자
	private String cretime;	// 생성시간
	private String creuser; // 수정사용자
	private String lmodate; // 수정일자
	private String lmotime; // 수정시간
	private String lmouser; // 수정사용자
	private String indibzl; // Business logic indicator
	private String indiarc; // Archieve indicator;
	private int updtchk; 	// Update check
	
	//WASNIF 
	private String eoasnky;
	private int eoasnit;
	private String purdatefrom;
	private String purdateto;
	
}
