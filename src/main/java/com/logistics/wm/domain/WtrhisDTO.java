package com.logistics.wm.domain;

import com.logistics.common.dto.CommonDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WtrhisDTO extends CommonDTO {
	
	private String rowkey;
	private String compkey;			
	private String whtrhky;         // 문서이력번호
	private String warekey;         
	private String doccate;         // 문서유형
	private String doctype;         // 문서타입
	private String areakey;         
	private String zonekey;         
	private String locakey;         
	private String subsect;         // SUB section
	private String truntyp;         // transfer unit type
	private String trunpid;         // transfer unit ID
	private String lotnmky;         // LOT NUMBER
	private String ownerky;        
	private String skumkey;         // 상품
	private String skudesc;         // 상품명
	private String vendkey;         //
	private String sirbcod;         // 개별관리코드
	private int stotqty;         // 총재고 수량
	private int sallqty;         // 할당 수량
	private int sinbqty;         // 입고중 수량
	private int soubqty;         // 출고중 수량
	private int sbloqty;         // 블락 수량
	private String suomkey;         // stock UOM
	private String proddat;         // 생산일자
	private int shelife;         // * 유통기한
	private String expidat;         // * 유통기간
	private String rcvdcdt;         // 실물입고 일자
	private String rcvfrdt;         // 최초 RECEVING DATE
	private String lotfrky;         // 최초 LOT NUMBER
	private String lotat01;         // LOT 속성
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
	private String lotat17;        
	private String lotat18;        
	private String lotat19;        
	private String lotat20;        
	private String stkstat;         // 재고상태
	private String blockid;         // block
	private String physrow;         // ROW X행
	private String physsec;         // SECTION Y열
	private String physflo;			// floor Z단
	private String rcvdcky;         // 입고문서번호
	private int rcvdcit;         // 입고문서 item
	private String taskoky;         // TASK KEY
	private int taskoit;         // TASK ITEM KEY
	private String adjsoky;         // 조정문서번호
	private int adjsoit;         // 조정문서 ITEM KEY
	private String physoky;         // 실사문서번호
	private int physoit;         // 실사문서 ITEM KEY
	private String shpdcky;         // 출고문서번호
	private int shpdcit;         // 출고문서 ITEM KEY
	private String copodky;         // REF ERP PO
	private String copodit;         // REF ERP PO ITEM
	private String refsodc;         // REF ERP SO
	private String refsoit;         // REF ERP SO ITEM
	private String refdndc;         // REF ERP DN
	private String refdnit;         // REF ERP DN ITEM
	private String refsddc;         // REF ERP SD
	private String refsdit;         // REF ERP SD ITEM
	private String storfky;         // 재고키 참고번호
	private String credate;        
	private String cretime;        
	private String creuser;        
	private String lmodate;        
	private String lmotime;        
	private String lmouser;        
	private String indibzl;        
	private String indiarc;        
	private int updtchk;        
	
	// CUSTOMIZE
	private String datefrom;
	private String dateto;
	private String doctynm;
}
