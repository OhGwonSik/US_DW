package com.logistics.om.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class OeretiVO {
	// OeretiVO
	private String rowkey;
	private String compkey;
	private String ownerky;		// VARCHAR(20) 화주
	private String warekey;		// VARCHAR(20) 창고
	private String oeretky;		// VARCHAR(10) 물류오더 문서번호
	
	private int oeretit;		// INT(11) 물류오더 문서아이템
	private String oeinsdt;		// VARCHAR(8) 물류오더 지시일자
	private String oeinstm;		// VARCHAR(6) 물류오더 지시시간
	private String oeinsst;		// VARCHAR(10) 물류오더 처리상태
	private String rsncode;		// VARCHAR(10) 반출 사유코드
	private String rsndesc;		// VARCHAR(1000) 반출 사유상세
	private String recrscd;		// 반출취소 사유코드
	private String recrsnm;		// 반출취소사유
	private String doccate;		// VARCHAR(10) 문서유형
	private String doctype;		// VARCHARA(10) 문서타입 
	private String stockky;		// VARCHAR(10) 재고키 
	private String areakey;		// VARCHAR(20) Area Key 
	private String zonekey;		// VARCHAR(20) Zone Key
	private String locakey;		// VARCHAR(20) Location Key
	private String subsect;		// VARCHAR(20) Sub Section
	private String truntyp;		// VARCHAR(20) Transfer Unit Type
	private String trunpid;		// VARCHAR(20) Transfer Unit ID
	private String lotnmky;		// VARCHAR(10) LOT NUMBER
	private String skumkey;		// VARCHAR(50) 상품
	private String skudesc;		// VARCHAR(100) 상품명
	private String vendkey;		// VARCHAR(20) 입고처
	private String sirbcod;		// VARCHAR(20) 개별관리코드
	private int stotqty;		// INT(11) 총재고 수량 
	private int sallqty;		// INT(11) 할당 슈량
	private int sinbqty;		// INT(11) 입고중 수량 
	private int soubqty;		// INT(11) 출고중 수량 
	private int sbloqty;		// 블락수량
	private String savaqty;		// VARCHAR(10) 가용재고
	private String stkstat;		// VARCHAR(10) 재고상태
	private String suomkey;		// VARCHAR(10) Stock UOM
	private int rtodqty;		// INT(11) 반출지시 수량
	private int rtcfqty;		// INT(11) 반출완료 수량
	private String ptnrkey;		// VARCHAR(20) 반출처
	private String retat01;
	private String retat02;
	private String retat03;
	
	private String credate;		// VARCHAR(8) 생성일자
	private String cretime;		// VARCHAR(6) 생성시간
	private String creuser;		// VARCHAR(60) 생성사용자
	private String lmodate;		// VARCHAR(8) 수정일자
	private String lmotime;		// VARCHAR(6) 수정시간
	private String lmouser;		// VARCHAR(60) 수정사용
	private String indibzl;		// VARCHAR(1) biz logic indicator
	private String indiarc;		// VARCHAR(1) Archive indicator
	private int updtchk;		// INT(1) Update Check
	
	private String cancode;		// 취소사유코드 받기위한 더미 데이터
	private String useract;		// USER ID
	
	// MCODEM 테이블 
	private String comcdvl;		
	private String comkytx;
	private String comcdtx;
	private String comcdky;
	private String comcdor;
	private String combovl;
	private String combonm;
	
	// WSTKKY 테이블
	private String rcvdcdt;      // 실물입고 일자
	private String lotfrky;		 // 최초입고 LOT Num
	private String proddat;      // 생산일자
	private int shelife;      // * 유통기한
	private String expidat;      // * 유통기간
	private String rcvfrdt;      // 최초 RECEVING DATE
	private String lotat01;      // 재고 LOT 속성
	private String lotat02; 
	private String lotat03; 
	private String lotat04; 
	private String lotat05; 
	private String lotat06; 
	private String lotat07; 
	private String lotat08; 
	private String lotat09; 
	private String lotat10; 
	private String lotat11; 
	private String lotat12; 
	private String lotat13; 
	private String lotat14; 
	private String lotat15; 
	private String lotat16; 
	private String lotat17;      // 라벨 갈이 ( TC일 경우에만 값이 들어옴)
	private String lotat18; 	 // 소매명 ( TC일 경우에만 값이 들어옴)	
	private String lotat19;      // 주문 번호
	private String lotat20; 	 // 주문 item 번호
	private String blockid;      // block
	private String physrow;      // ROW X열
	private String physsec;      // SECTION Y행
	private String physflo;      // FLOOR Z단
	private String rcvdcky;      // 입고문서번호
	private int rcvdcit;         // 입고문서 아이템
	private String taskoky;      // TASK KEY
	private int taskoit;         // TASK ITEM KEY
	private String shpdcky;      // 출고문서번호
	private int shpdcit;         // 출고문서 아이템
	private String adjsoky;      // 조정문서 번호
	private int adjsoit;         // 조정문서 ITEM KEY
	private String physoky;      // 실사문서 번호
	private int physoit;         // 실사문서 ITEM KEY
	private String storfky;      // 재고키 참고번호
	
	// MRSCRMA
	private String rsncdnm;		 // 반출사유상세
	private String rscate1;		 // 취소코드
	
	// MPTNMA
	private String ptnamlc;		// 협력업체 명칭
	private String ptnrtyp;		// 협력업체 타입
	private String ptaddr1;		// 주소
	private String ptaddr2;
	private String ptaddr3;
	
	// MSKUWC
	private String skuat03;		// ALC CODE
	private String pacckey;		// 포장키
	private String packqty;		// 입수량
	
	// MDODCMA
	private String doctynm;		// 신청타입의 종류
	
	// MLOCMA,MAREMA,MZONMA
	private String areanam;
	private String locanam;
	private String zonenam;
	
	// MWORMA
	private String ownamlc;		// 화주명칭
	
	// MORDMA
	private String cootype;
	private String cootynm;
	private String coocate;
	private String ococtnm;
	
	// SUSRMA
	private String usernam;
	
	
	private String warekey1;	// 창고키 한글
	private String ownerky1;	// 화주키 한글
	private String stkstat1;	// 재고상태 한글
	
	// 날짜 검색 조건
	private String oeinsdtFrom;
	private String oeinsdtTo;
	
	
}
