package com.logistics.wm.domain;

import java.util.List;

import com.logistics.common.dto.CommonDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ShipDTO extends CommonDTO {

    //==============================
    //======= 출고-공통 / DTO ========
    //==============================

    //WSHPIT
    private String compkey;				// VARCHAR(20) 회사코드
    private String warekey;				// VARCHAR(20) 창고키
    private String ownerky;				// VARCHAR(20) 화주
    private String doccate;				// VARCHAR(10) 문서타입
    private String doctype;				// VARCHAR(10) 문서유형
    private String shpdcst;				// VARCHAR(10) 출고문서 상태
    private String shpitst;				// VARCHAR(10) 출고문서 아이템
    private String shpdcky;				// VARCHAR(10) 출고문서번호
    private int shpdcit;				// INT(11) 출고문서번호 아이템
    private String custkey;				// VARCHAR(20) 고객
    private String cooutky;				// VARCHAR(50) OM 수주번호
    private int cooutit;				// INT(11) OM 수주번호 아이템
    private int shcuqty;				// INT(11) 주문수량
    private int shalqty;				// INT(11) 할당수량
    private int shpkqty;				// INT(11) 피킹수량
    private int shcfqty;				// INT(11) 완료수량
    private String shppday;				// VARCHAR(8) 출고 예정일
    private String shpptim;				// VARCHAR(6) 출고 예정시간
    private String shpsday;				// VARCHAR(8) 출고 시작일
    private String shpstim;				// VARCHAR(6) 출고 시작시간
    private String spehtim;				// VARCHAR(6) 출고 완료시간
    private String speitim;				// VARCHAR(6) 출고 아이템완료시간
    private String salrqdt;				// VARCHAR(8) 고객 도착요청일
    private String salrqtm;				// VARCHAR(6) 고객 도착요청시간
    private String allgrky;				// VARCHAR(10) 할당그룹키
    private String movteam;				//	X
    private String refpodc;				// VARCHAR(10) REF ERP PO
    private String refpoit;				// VARCHAR(10) REF ERP PO ITEM
    private String oeretky;				// VARCHAR(10) OM 반출오더 번호
    private int oeretit;				// INT(11) OM 반출오더 아이템
    private String refdndc;				// VARCHAR(10) REF ERP DN
    private String refdnit;				// VARCHAR(10) REF ERP DN ITEM
    private String refsddc;				// VARCHAR(10) REF ERP SD
    private String refsdit;				// VARCHAR(10) REF ERP SD ITEM
    private String picktyp;				// VARCHAR(20) 출고작업 유형
    private String destkey; 			// VARCHAR(20) 도착지
    private String shsdate;				//	X
    private String shstime;				//	X
    private String shsuser;				//	X
    private String shsname;				//	X
    private String shedate;				//	X
    private String shetime;				//	X
    private String sheuser;				//	X
    private String shename;				//	X
    private int updtchk;
    
    
    //WSTKKY
    private String skumkey;				// VARCHAR(50) 부품
    private String skudesc;				// VARCHAR(100) 부품명
    private int stotqty;				// INT(11) 총재고수량
    private String areakey;				// VARCHAR(20) 에어리어
    private String zonekey;				// VARCHAR(20) 존
    private String locakey;				// VARCHAR(20) 로케이션
    
    //WTAKIT
    private String stockky;				// VARCHAR(10) 재고키
	private String storfky;				// VARCHAR(10) 재고키 참고번호
 	private String taskoky;				// VARCHAR(10) 작업문서번호 
 	private int taskoit;				// INT(11) 작업문서번호 아이템
	private String tasksts;				// VARCHAR(10) 작업상태 
	private String truntyp;				// VARCHAR(10) 이동용기 타입
	private String trunpid;				// VARCHAR(10) 이동용기 ID
	private String lotnmky;				// VARCHAR(10) LOT NUMBER 
	private String frareky; 			// VARCHAR(20) From Area
	private String frzonky; 			// VARCHAR(20) From Zone
	private String frlocky;				// VARCHAR(20) From Loc
	private String toareky; 			// VARCHAR(20) To Area
	private String tozonky; 			// VARCHAR(20) To Zone
	private String tolocky; 			// VARCHAR(20) To Loc
	private int fordqty; 				// INT(11) 지시수량
	private String fuomeky;				// VARCHAR(10) 지시단위
	private int diffqty;				// INT(11) 차이수량
	private int tcmpqty;				// INT(11) 차이수량 
	private String tuomeky;				// VARCHAR(10) 완료단위 
	private String sirbcod;				// VARCHAR(20) 개별관리번호 
	private String proddat; 			// VARCHAR(8) 생산일자
	private int shelife; 				// INT(11) 유통기한
	private String expidat; 			// VARCHAR(8) 유통기간
	private String rcvdcdt; 			// VARCHAR(8) 입고일자
	private String rcvfrdt;				// VARCHAR(8) 최초입고일자
	private String lotfrky; 			// VARCHAR(10) 최초입고 LOT Num
	private String lotat01;				// VARCHAR(10) 재고키 참고번호 
	private String lotat02;				// VARCHAR(20) 재고LOT 속성 
	private String lotat03;				// VARCHAR(20) 재고LOT 속성  
	private String lotat04;				// VARCHAR(20) 재고LOT 속성 
	private String lotat05;				// VARCHAR(20) 재고LOT 속성  
	private String lotat06;				// VARCHAR(20) 재고LOT 속성  
	private String lotat07;				// VARCHAR(20) 재고LOT 속성  
	private String lotat08;				// VARCHAR(20) 재고LOT 속성 
	private String lotat09;				// VARCHAR(20) 재고LOT 속성  
	private String lotat10;				// VARCHAR(20) 재고LOT 속성  
	private String lotat11;				// VARCHAR(20) 재고LOT 속성  
	private String lotat12;				// VARCHAR(20) 재고LOT 속성 
	private String lotat13;				// VARCHAR(20) 재고LOT 속성  
	private String lotat14;				// VARCHAR(20) 재고LOT 속성  
	private String lotat15;				// VARCHAR(20) 재고LOT 속성  
	private String lotat16;				// VARCHAR(20) 재고LOT 속성 
	private String lotat17;				// VARCHAR(20) 재고LOT 속성  
	private String lotat18;				// VARCHAR(20) 재고LOT 속성  
	private String lotat19;				// VARCHAR(20) 재고LOT 속성  
	private String lotat20;				// VARCHAR(20) 재고LOT 속성 
	private String parsncd;				// VARCHAR(10) 취소사유코드 
	private String parsnnm;				// VARCHAR(200) 사유내용
	private String pkrsncd;				// VARCHAR(10) 취소사유코드 
	private String pkrsnnm;				// VARCHAR(200) 취소사유내용
	private String rcvdcky;				// VARCHAR(10) 입고문서번호 
	private int rcvdcit;				// INT(11) 입고문서번호 아이템  
	private String refsodc;				// VARCHAR(10) REF ERP SO   
	private String refsoit;				// VARCHAR(10) REF ERP SO ITEM  
	private String stkstat;				// VARCHAR(10) 재고상태

    //MSKUWC
    private String suomkey;					// VARCHAR(10) Stock UOM
    private String skuskey;					// VARCHAR(50) 대표 SKU
    
    //MPAKMA
    private int packqty;					// DECIMAL(10,3) 입수량
    
    //MDOCMA
    private String doctynm;					// VARCHAR(60) 문서타입명칭
    
    //MOWRMA
    private String ownamlc;					// VARCHAR(60) 화주명					
    
    //CUSTOMIZE
    private String[] shpdcstList;			// 출고상태 	사용프로그램명 : WMO10 , WMO50
    private String[] skudescList;			//  		사용프로그램명 : WMO10 
    private String stockct;					
    private String shpdcnm;					// 출고헤드문서상태 name
    private String shpicnm;					// 출고아이템문서상태 name
    
    private List<?> printList;

	// print
	private String type;
	private String user;
	private String progrid;
	private String orhdkey;
	private String denamlc;		// 도착지명
}
