package com.logistics.om.domain;

import lombok.Data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OmInventoryVO {
	
	/* 
	 * ******************************************** 
	  - DTO Name       : OmInventoryVO
	  - Description    : OM 재고조정기능에 필요한 필드 모아둔 VO
	  - Made By        : 오권식
	  - Creation Date  : 2023.07.06
	  ------------------------------------------
	 
	* ********************************************  
	*/
	
	//WM WSTKKY
	private String rowkey; // rowkey (not null)
	private String compkey; // company Key (not null)
	private String stockky; // 재고키 (not null)
	private String warekey; // 창고
	private String areakey; // Area key
	private String zonekey; // Zone key
	private String locakey; // Location key
	private String subsect; // SUB Section
	private String truntyp; // Transfer unit type
	private String trunpid; // Transfer unit ID
	private String lotnmky; // LOT NUMBER
	private String ownerky; // 화주
	private String skumkey; // 상품
	private String skudesc; // 상품명
	private String vendkey; // 입고처
	private String sirbcod; // *개별관리코드
	private int stotqty; // 총재고 수량
	private int sallqty; // 할당 수량
	private int sinbqty; // 입고중 수량
	private int soubqty; // 출고중 수량
	private int sbloqty; // 블락 수량
	private String savaqty;//가용수량
	private String suomkey; //Stock UOM
	private String proddat; // 생산일자
	private int shelife; // *유통기한
	private String  expidat; //*유통기간
	private String rcvdcdt; // 입고일자
	private String rcvfrdt; // 최초입고일자
	private String lotfrky; // 최초입고 LOT Num
	private String lotat01; // 재고 LOT속성01
	private String lotat02; // 재고 LOT속성02
	private String lotat03; // 재고 LOT속성03
	private String lotat04; // 재고 LOT속성04
	private String lotat05; // 재고 LOT속성05
	private String lotat06; // 재고 LOT속성06
	private String lotat07; // 재고 LOT속성07
	private String lotat08; // 재고 LOT속성08
	private String lotat09; // 재고 LOT속성09
	private String lotat10; // 재고 LOT속성10
	private String lotat11; // 재고 LOT속성11
	private String lotat12; // 재고 LOT속성12
	private String lotat13; // 재고 LOT속성13
	private String lotat14; // 재고 LOT속성14
	private String lotat15; // 재고 LOT속성15
	private String lotat16; // 재고 LOT속성16
	private String lotat17; // 재고 LOT속성17 (not null)
	private String lotat18; // 재고 LOT속성18 (not null)
	private String lotat19; // MTO 오더주문번호 (not null)
	private String lotat20; // MTO 오더주문아이템 (not null)
	private String stkstat; // 재고상태
	private String blockid; // Block
	private String physrow; // Row X열
	private String physsec; // Section Y열
	private String physflo; // floor Z단
	private String rcvdcky; // 입고문서번호
	private int rcvdcit; // 입고문서아이템
	private String taskoky; // Task key
	private int taskoit; // Task Item key
	private String shpdcky; // 출고문서 번호
	private int shpdcit; // 출고 문서 아이템
	private String adjsoky; // 조정문서 번호
	private int adjsoit; // 조정문서 Item key
	private String physoky; // 실사문서 번호
	private int physoit; // 실사문서 Item key
	private String storfky; // 재고키 참고번호
	private String doccate; // 마지막문서유형
	private String doctype; // 마지막문서타입
	private String credate; // 생성일자
	private String cretime; // 생성시간
	private String creuser; // 생성사용자
	private String lmodate; // 수정일자
	private String lmotime; // 수정시간
	private String lmouser; // 수정사용자
	private String indibzl; // biz logic indicator
	private String indiarc; // Archive indicator
	private String updtchk; // Update check
	
	//커스텀
	private String stkstatgood; //재고상태 정상
	private String stkstatbad; //재고상태 불량
	private String whnamlc; //창고명(한글)
	private String ownamlc; //오너명(한글)
	private String comcdtx; //재고상태(한글)
	private String areanam; //에이리어명(한글)
	private String zonenam; //존명(한글)
	private String locanam; //로케이션명(한글)
	private int rcvsqty;//입하에어리어 수량
	private int stgsqty;//보관에어리어 수량
	private int taksqty;//작업에어리어 수량
	
	// 20230823 SMA 추가 
	private String rcvdcdt_to;
	private String rcvdcdt_from;
	private String cootype;
	private String cootynm;
	private String coocate;
}
