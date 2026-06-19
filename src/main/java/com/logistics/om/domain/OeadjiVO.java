package com.logistics.om.domain;

import lombok.Data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OeadjiVO {
	
	/* 
	 * ******************************************** 
	  - DTO Name       : OeadjiVO
	  - Description    : OM 재고조정기능에 필요한 필드 모아둔 VO
	  - Made By        : 오권식
	  - Creation Date  : 2023.07.11
	  ------------------------------------------
	 
	* ********************************************  
	*/
	
	private String rowkey;
	private String compkey; //Company Key
	private String oeadjky; //물류오더 문서번호
	private int oeadjit; //물류오더 문서아이템
	private String oeinsdt; //물류오더 지시일자
	private String oeinstm; //물류오더 지시시간
	private String oeinsst; //물류오더 처리상태
	private String rsncode; //조정 사유코드
	private String adjsrmk; //조정 사유내용
	private String adcrscd; //조정취소 사유코드
	private String adcrsnm; //조정취소 사유상세
	private String warekey; //창고
	private String doccate; //문서유형
	private String doctype; //문서타입
	private String stockky; //재고키
	private String areakey; //Area Key
	private String zonekey; //Zone Key
	private String locakey; //Location Key
	private String subsect; //Sub Section
	private String truntyp; //Transfer unit type
	private String trunpid; //Transfer unit ID
	private String lotnmky; //LOT NUMBER
	private String ownerky; //화주
	private String skumkey; //상품
	private String skudesc; //상품명
	private String vendkey; //입고처
	private String sirbcod; //개별관리코드
	private int stotqty; //총재고 수량
	private int sallqty; //할당 수량
	private int sinbqty; //입고중 수량
	private int soubqty; //출고중 수량
	private int sbloqty; //블락 수량
	private String savaqty; //가용재고
	private String stkstat; //재고상태
	private String suomkey; //Stock UOM
	private int rtodqty; //조정지시수량
	private int rtcfqty; //조정완료수량
	private String atkstat; //조정지시 재고상태
	private String credate; //생성일자
	private String cretime; //생성시간
	private String creuser; //생성사용자
	private String lmodate; //수정일자
	private String lmotime; //수정시간
	private String lmouser; //수정사용자
	private String indibzl; //biz logic indicator
	private String indiarc; //Archive indicator
	private String updtchk; //Update Check
	
	private String rscate1; // 20230821 SMA 추가 (조정취소조회)
	private String combovl; // 20230821 SMA 추가 (조정취소조회)
	private String combonm; // 20230821 SMA 추가 (조정취소조회)
	private String rsncdnm; // 20230821 SMA 추가 (조정취소조회)
	
	
	//커스텀
	private String stkstatgood;
	private String stkstatbad;
	//에어리어, 존, 로케이션 한글이름
	private String areanam;//에어리어 한글이름
	private String zonenam;//존 한글이름
	private String locanam;//로케이션 한글이름
	private String ownamlc;//화주명 한글이름
	
	//검색조건 기간
	private String toOeinsdt; // 조정지시 검색 날짜 까지
	private String fromOeinsdt; // 조정지시 검색 날짜 부터
	
	// 231004 SMA 추가 (문서타입 검색)
	private String cootype;
	private String cootynm;
}
