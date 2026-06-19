package com.logistics.wm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class WtakitVO {
	
	private String rowkey;
	private String compkey; 
	private String taskoky;  //VARCHAR(10) 작업 문서 
	private int    taskoit;  //VARCHAR(11) 작업 문서 아이템 
	private String tasksts;	 //VARCHAR(10) 작업 문서 상태
	private String warekey;  //VARCHAR(20) 창고
	private String doccate;  //VARCHAR(10) 문서유형
	private String doctype;  //VARCHAR(10) 문서타입
	private String truntyp;  //VARCHAR(10) Transfer unit type
	private String trunpid;  //VARCHAR(20) Transfer unit ID
	private String lotnmky;  //VARCHAR(10) LOT NUMBER
	private String ownerky;  //VARCHAR(20) 화주
	private String skumkey;  //VARCHAR(50) 상품
	private String skudesc;  //VARCHAR(100) 상품명
	private String vendkey;  //VARCHAR(20) 공장
	private String frareky;  //VARCHAR(20) FRON AREA
	private String frzonky;  //VARCHAR(20) FROM ZONE
	private String frlocky;  //VARCHAR(20) FROM LOC
	private String toareky;  //VARCHAR(20) TO AREA
	private String tozonky;  //VARCHAR(20) TO ZONE
	private String tolocky;  //VARCHAR(20) TO LOC
	private int    fordqty;  //INT(11) 지시단위
	private String fuomeky;  //VARCHAR(10) 지시수량
	private int    tcmpqty;  //INT(11) 완료수량
	private String tuomeky;  //VARCHAR(10) 완료단위
	private int	   diffqty;	 //INT(11) 차이수량
	private String sirbcod;  //VARCHAR(20) *개별관리번호
	private String proddat;  //VARCHAR(8) 생산일자
	private int    shelife;	 //INT(11) *유통기한
	private String expidat;  //VARCHAR(8) *유통기간
	private String rcvdcdt;  //VARCHAR(8) 입고일자
	private String rcvfrdt;  //VARCHAR(8) 최초입고일자
	private String lotfrky;  //VARCHAR(10) 최초입고 LOT NUM
	private String lotat01;  //VARCHAR(20)
	private String lotat02;  //VARCHAR(20)
	private String lotat03;  //VARCHAR(20)
	private String lotat04;  //VARCHAR(20)
	private String lotat05;  //VARCHAR(20)
	private String lotat06;  //VARCHAR(20)
	private String lotat07;  //VARCHAR(20)
	private String lotat08;  //VARCHAR(20)
	private String lotat09;  //VARCHAR(20)
	private String lotat10;  //VARCHAR(20)
	private String lotat11;  //VARCHAR(20)
	private String lotat12;  //VARCHAR(20)
	private String lotat13;  //VARCHAR(20)
	private String lotat14;  //VARCHAR(20)
	private String lotat15;  //VARCHAR(20)
	private String lotat16;  //VARCHAR(20)
	private String lotat17;  //VARCHAR(20)
	private String lotat18;  //VARCHAR(20)
	private String lotat19;  //VARCHAR(20)
	private String lotat20;  //VARCHAR(20)
	private String stkstat;  //VARCHAR(10) 재고상태
	private String taskg01;  //VARCHAR(10) 작업그룹01
	private String taskg02;  //VARCHAR(10)
	private String taskg03;  //VARCHAR(10)
	private String taskg04;  //VARCHAR(10)
	private String taskg05;  //VARCHAR(10) 
	private String parsncd;  //VARCHAR(10) 이동 사유코드
	private String parsnnm;  //VARCHAR(10) 이동 사유내용
	private String pkrsncd;  //VARCHAR(10) 이동취소 사유코드
	private String pkrsnnm;  //VARCHAR(10) 이동취소 사유내용
	private String movteam;  //VARCHAR(10) 이동작업 조
	private String movusid;  //VARCHAR(20) 이동작업 사용자ID
	private String mvstdat;  //VARCHAR(8)  이동작업 시작일
	private String mvsttim;  //VARCHAR(6)  이동작업 시작시간
	private String mveddat;  //VARCHAR(10) 이동작업 완료일자
	private String mvedtim;  //VARCHAR(6)  이동작업 완료시간
	private String taskrmk;	 //VARCHAR(2000) 작업 비고
	private String packsyn;	 //VARCHAR(100) 소봉포장 작업여부
	private String packsky;	 //VARCHAR(100) 소포장 키
	private String packmky;	 //VARCHAR(100) 중포장 키
	private String packlky;	 //VARCHAR(100) 대포장 키
	private String eoasnky;  //VARCHAR(10) ASN No
	private int    eoasnit;  //INT(11)     ASN Item number
	private String rcvdcky;  //VARCHAR(10) 입고문서 번호
	private int    rcvdcit;  //INT(11)     입고문서 아이템
	private String dctctyp;	 //VARCHAR(10) DC/TC 구분
	private String shpdcky;  //VARCHAR(10) 출고문서 번호
	private int    shpdcit;  //INT(11)     출고문서 아이템
	private String refpodc;  //VARCHAR(10) ref ERP PO
	private String refpoit;  //VARCHAR(10) ref ERP PO Item
	private String refsodc;  //VARCHAR(10) ref ERP SO
	private String refsoit;  //VARCHAR(10) ref ERP SO Item
	private String refdndc;  //VARCHAR(10) ref ERP DN
	private String refdnit;  //VARCHAR(10) ref ERP DN Item
	private String refsddc;  //VARCHAR(10) ref ERP SD
	private String refsdit;  //VARCHAR(10) ref ERP SD Item
	private String storfky;  //VARCHAR(10) 재고키 참고번호
	private String stkotky;  //VARCHAR(10) 테스트용_O
	private String stkinky;  //VARCHAR(10)  테스트용_I
	private String credate;  //VARCHAR(8)
	private String cretime;  //VARCHAR(6)
	private String creuser;  //VARCHAR(60)
	private String lmodate;  //VARCHAR(8)
	private String lmotime;  //VARCHAR(6)
	private String lmouser;  //VARCHAR(60)
	private String lmoname;		//수정자 이름
	private String indibzl;  //VARCHAR(1)
	private String indiarc;  //VARCHAR(1)
	private int    updtchk;  //INT(11)
	private String docdate;	
	
	private int    fordqty2;  //INT(11) wmop3 - 상품스캔
	private int    tcmpqty2;  //INT(11) wmop3 - DAS분배
	
	//WSHPIT
	private int	   shpkqty;	  //int
	private String allgrky;
	private String picktyp;
	
	//MCUSMA
	private String cunamlc;
	private String cunamko;
	private String cunamen;	
	private String custkey;
	private String cuaddr3;
	private String cuteln1;
	private String cuteln2;
	private String invhead;
	private String invfoot;
	private String retakey;
	//MDOCMA
	private String doctynm;
	
	private int stotqty;      // 총재고 수량
	private int sallqty;      // 할당 수량
	private int sinbqty;      // 입고중 수량
	private int soubqty;      // 출고중 수량
	private int sbloqty;      // 블락 수량
	
	private String receipt;		//전표용 파라미터
	
	//
	private int itmcost;
	private int selpric;
	private int itmpric;
	private String custnam;
	private String retanam;
	private String frlonam;
	private String tolonam;
	
	//wmlt2
	private String brandcd;
	
	//WMO20 PICKING 처리
	private String cooutky;		// VARCHAR(50) 수주번호
	private int cooutit;		// INT(11) 수주번호 아이템
}
