package com.logistics.wm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class WstkkyVO {
	
	private String rowkey;
	private String compkey;       
	private String stockky;      // 재고키
	private String warekey;      
	private String areakey;      
	private String zonekey;      
	private String locakey;      
	private String subsect;      // SUB section
	private String truntyp;      // transfer unit type
	private String trunpid;      // transfer unit id
	private String lotnmky;      // lot number
	private String ownerky;
	private String skumkey;      // 상품
	private String skudesc;      // 상품명
	private String vendkey;      
	private String sirbcod;      // 개별 관리 코드
	private int stotqty;      // 총재고 수량
	private int sallqty;      // 할당 수량
	private int sinbqty;      // 입고중 수량
	private int soubqty;      // 출고중 수량
	private int sbloqty;      // 블락 수량
	private int avalqty;	  // 가용 재고 수량
	private String suomkey;      // Stock UOM
	private String proddat;      // 생산일자
	private int shelife;      // * 유통기한
	private String expidat;      // * 유통기간
	private String rcvdcdt;      // 실물입고 일자
	private String rcvfrdt;      // 최초 RECEVING DATE
	private String lotfrky;      // 최초 LOT NUMBER
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
	private String stkstat;      // 재고상태
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
	private String credate; 
	private String cretime; 
	private String creuser; 
	private String lmodate; 
	private String lmotime; 
	private String lmouser; 
	private String indibzl; 
	private String indiarc; 
	private int updtchk; 
	
	private String doccate;
	private String doctype;
	private String adjmode;
	private String adjgrky;
	private String rsncode;
	private String adjsrmk;
	
	// mskuwc
	private String skugr02;
	private String skugr03;
	private String skugr04;
	private String skugr05;
	private String skuat01;
	private String roinqty;
	
	//블록 해제 수량
	private int scbloqty;
	//변경 수량
	private int schnqty;
	
	//재고 이동
	private String toareky;
	private String tozonky;
	private String tolocky;
	private int fordqty;
	private String frareky;
	private String frzonky;
	private String frlocky;
	private String parsncd;
	private String parsnnm;
	private Double gpakcnt;	//절
	private String skuwabc;	//WH SKU ABC analyzed value
	private String brandcd;	//브랜드
	private String packkey;	//포장키
	private String pastrky;	//적치전략키
	
	//재고 실사 요청
	private int systqty;
	private int physqty; 
	private int compqty; 
	private String physrmk;
	
	private String ownamlc;
	private String whnamlc;
	
	// WPHYIT 실사 요청 시 실사문서title 입력
	private String phyname; 
	
	//MPTNMA
	private String ptnamlc;		//파트너명
	
	//Ocosal
	private String salrqdt;		//소매 도착요청일
	private String salrqtm;		//소매 도착요청시간
	private String salmemo;		//소매 요구사항

	//OMW4
	private int stksamp;
	private int stpoqty;
	private int stkgood;
	private int stkbads;
	private int soitqty;
	private String skuskey;
	
	//OMW5
	private String allgrky;		//그룹키
	private int	ordhqty;
	private int	ordwqty;
	private int ordmqty;
	private String refsodc;
	private int refsoit;
	
	private String custkey;		//도매
	private String custnam;		//도매명
	private String rcvrscd;		//입고사유 코드
	private String rcvrsnm;		//입고사유 내용
	
	//wmvs1 (재고조회)
	private String areanam;
	private String zonenam;
	private String locanam;
	
	//wmr60 (적치 지시)
	private String rcvdcdtfrom;
	private String rcvdcdtto;
	
	private int tchnqty;
}
