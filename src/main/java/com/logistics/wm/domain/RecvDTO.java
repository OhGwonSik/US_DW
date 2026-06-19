package com.logistics.wm.domain;

import java.util.List;

import com.logistics.common.dto.CommonDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecvDTO extends CommonDTO {
	/*
	 ****************************************************************************************
	 - DTO Name : RecvDTO
	 - Description : 입하 페이지에서 사용할 필드를 모아놓은 DTO
	 - Made By : 최강호
	 - Creation Date :  2023.07.25
	 -------------------------------------------------------------------------------------------------
	 ****************************************************************************************
	 */
	
	//wasnif
	private String compkey; // VARCHAR(20) 회사
	private String warekey; // VARCHAR(20) 창고
	private String doccate; // VARCHAR(10) 문서상태
	private String doctype; // VARCHAR(10) 문서타입
	private String ownerky; // VARCHAR(20) 화주
	private String skuskey; // VARCHAR(50) 대표상품
	private String skumkey; // VARCHAR(50) 부품
	private String skudesc; // VARCHAR(100) 부품명
	private String asndate; // VARCHAR(8) 입고예정일
	private String vendkey; // VARCHAR(20) 입고처
	private String eoasnky; // VARCHAR(10) asn 번호
	private String asnstat; // VARCHAR(10) asn 상태
	private int eoasnit; 	// INT(11) 입고예정 아이템
	
	//wrcvit
	private String doctypnm;
	private String rcvdcky; // VARCHAR(10)입고문서 번호
	private int rcvdcit; // INT(11) 입고문서 아이템 
	private int rcveqty; // INT(11) 입하대상 수량
	private int asndqty; // INT(11) 입고예정 수량
	private String rcvrscd; //VARCHAR(10) 입고사유 코드
	private String rcarscd; //VARCHAR(10) 입고취소 사유코드
	private String rcarsnm; //VARCHAR(200) 입고취소 사유내용
	private String rcvrsnm; //VARCHAR(200) 입고사유 내용
	private String truntyp; //VARCHAR(10) Transfer unit type
	private String trunpid; //VARCHAR(60) Transfer unit ID
	private String rcvdcst; //VARCHAR(10) 입고문서 상태
	private int rchsqty; //VARCHAR(11) 입하완료 수량
	private String rcvitst; //VARCHAR(10) 입고문서 아이템 상태
	private String lotnmky; //VARCHAR(10) LOT NUMBER
	private String rvlocky; //VARCHAR(20) 입하 LOC key
	private String rcadate; //VARCHAR(8) 입고취소 일자
	private String rcatime; //VARCHAR(6) 입고취소 시간
	private String pmtinyn; //VARCHAR(1) 입고마감 여부
	private String pmtindt; //VARCHAR(8) 입고마감 일자
	private String pmtintm; //VARCHAR(6) 입고마감 시간
	private String postdat; //VARCHAR(8) 영업일자
	
	//wstkky
	private String stockky; //VARCHAR(10) 재고키
	private String areakey; //VARCHAR(20) Area key
	private String zonekey; //VARCHAR(20) Zone key
	private String locakey; //VARCHAR(20) Loca key
	private String suomkey; //VARCHAR(10) Stock UOM
	private int stotqty; //INT(11) 총재고 수량
	private int sallqty; //INT(11) 할당 수량
	private int sinbqty; //INT(11) 입고중 수량
	private int soubqty; //INT(11) 출고중 수량
	private int sbloqty; //INT(11) 블락 수량
	private String lotat01; //VARCHAR(20) 재고 LOT 속성 01
	private String lotat02; //VARCHAR(20) 재고 LOT 속성 02
	private String lotat03; //VARCHAR(20) 재고 LOT 속성 03
	private String lotat04; //VARCHAR(20) 재고 LOT 속성 04
	private String lotat05; //VARCHAR(20) 재고 LOT 속성 05
	private String lotat06; //VARCHAR(20) 재고 LOT 속성 06
	private String lotat07; //VARCHAR(20) 재고 LOT 속성 07
	private String lotat08; //VARCHAR(20) 재고 LOT 속성 08
	private String lotat09; //VARCHAR(20) 재고 LOT 속성 09
	private String lotat10; //VARCHAR(20) 재고 LOT 속성 10
	private String lotat11; //VARCHAR(20) 재고 LOT 속성 11
	private String lotat12; //VARCHAR(20) 재고 LOT 속성 12
	private String lotat13; //VARCHAR(20) 재고 LOT 속성 13 
	private String lotat14; //VARCHAR(20) 재고 LOT 속성 14
	private String lotat15; //VARCHAR(20) 재고 LOT 속성 15
	private String lotat16; //VARCHAR(20) 재고 LOT 속성 16
	private String lotat17; //VARCHAR(20) 재고 LOT 속성 17
	private String lotat18; //VARCHAR(20) 재고 LOT 속성 18	
	private String lotat19; //VARCHAR(20) 재고 LOT 속성 19
	private String lotat20; //VARCHAR(20) 재고 LOT 속성 20
	private String rcvdcdt; //VARCHAR(8) 입고일자
	private String rcvdctm; //입고시간
	
	//mdocma
	private String doctynm; //VARCHAR(60) 문서타입명칭
	
	//mcodem
	private String comcdtx; //VARCHAR(100)
	private String comcdvl; //VARCHAR(10)
	
	//mlocma
	private String locanam; //VARCHAR(100) 로케이션 명칭
	private String rvlocna; //wrcvit 로케이션 명칭
	
	//mrscma
	private String rsncdnm; //VARCHAR(10) 사유코드 명칭
	
	//mapkma
	private double packqty; //DECIMAL(10,3) 입수량
	private String packkey; //VARCHAR(10) 포장키
	private String pkuseyn; //VARCHAR(1) - 사용여부
	
	//mtutma
	private String trunnam; //VARCHAR(20) 이동용기 명칭
	private int fordqty;    //INT(11) 지시단위
	
	//tplnit
	private String retodyn; //반품회수 지시여부
	private String shtrsts; //고객운송상태
	private String retinyn; //반품회수 입고여부
	private int tritqty; //운송수량
	private String vhplnky;
	private int vhplnit;
	
	//ocosal
	private String salmemo;
	private String cooutky;
	private int cooutit;
	
	//MSKUWC
	private int propqty; //적정재고수량
	private int salfqty; //안전재고수량
	
	//CUSTOMIZE
	private List<String> rcvitsts;  //입고문서 아이템 상태 배열
	private List<String> rcvdcsts;  //입고문서 상태 배열
	private List<String> asnstats; //asn 상태 배열
	private int asndfty; 		//입하대상 수량
	private int itemcnt; 		//아이템 갯수 확인용
	private String asndateto; 	//입고예정일 to
	private String asndatefrom; //입고예정일 from
	private int paldqty; 		//팔렛타이징 할당수량
	private String paletck; 	//팔렛타이징 여부
	private String rcvdcdtfrom; //입고일자 from
	private String rcvdcdtto;	//입고일자 to
	private String tolocky; 	//to location
	private String upccode; 	//부품 바코드
	private String ownamlc; 	//화주 한글
	private String whnamlc; 	//창고 한글
	private String druntyp; 	//이동용기 타입
	private int shelife; 		//유통기한
	private String expidat;		//유통기간
}
