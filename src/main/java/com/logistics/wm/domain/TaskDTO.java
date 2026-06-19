package com.logistics.wm.domain;

import java.util.List;

import com.logistics.common.dto.CommonDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TaskDTO extends CommonDTO {
	/*
	 ****************************************************************************************
	 - DTO Name : TaskDTO
	 - Description : 작업 페이지에서 사용할 필드를 모아놓은 DTO
	 - Made By : 최강호
	 - Creation Date :  2023.07.25
	 -------------------------------------------------------------------------------------------------
	 ****************************************************************************************
	 */
	
	//WSTKKY
	private String compkey; // VARCHAR(20) 회사키
	private String stockky; // VARCHAR(10) 재고키
	private String warekey; // VARCHAR(20) 창고
	private String areakey; // VARCHAR(20) Area key
	private String zonekey; // VARCHAR(20) Zone key
	private String locakey; // VARCHAR(20) Loca key
	private String subsect; // VARCHAR(20) SUB Section
	private String truntyp; // VARCHAR(10) Trasnfer Unit Type
	private String trunpid; // VARCHAR(20) Transfer Unit ID
	private String lotnmky; // VARCHAR(10) LOT Number
	private String ownerky; // VARCHAR(20) 화주
	private String skumkey; // VARCHAR(50) 부품
	private String skudesc; // VARCHAR(100) 부품명
	private String vendkey; // VARCHAR(20) 입고처
	private String sirbcod; // VARCHAR(20) 개별관리코드
	private int stotqty; 	// INT(11) 총재고 수량
	private int sallqty;	// INT(11) 할당 수량
	private int sinbqty; 	// INT(11) 입고중 수량
	private int soubqty; 	// INT(11) 출고중 수량
	private int sbloqty; 	// INT(11) 블락 수량
	private String suomkey; // VARCHAR(10) Stock UOM
	private String proddat; // VARCHAR(8) 생산일자
	private int shelife; 	// INT(11) 유통기한
	private String expidat; // VARCHAR(8) 유통기간
	private String rcvdcdt; // VARCHAR(8) 입고일자
	private String rcvfrdt; // VARCHAR(8) 최초입고일자
	private String lotfrky; // VARCHAR(10) 최초입고 LOT Num
	private String stkstat; // VARCHAR(10) 재고상태
	private String blockid; // VARCHAR(20) Block
	private String physrow; // VARCHAR(3) Row x열
	private String physsec; // VARCHAR(3) Section Y행
	private String physflo; // VARCHAR(3) floor Z단
	private String rcvdcky; // VARCHAR(10)입고문서 번호
	private int rcvdcit; 	// INT(11) 입고문서 아이템
	private String taskoky; // VARCHAR(100) Task Key
	private int taskoit; 	// INT(11) Task Item Key
	private String shpdcky; // VARCHAR(10) 출고문서 번호
	private int shpdcit; 	// INT(11)출고문서 아이템
	private String adjsoky; // VARCHAR(10) 조정문서 번호
	private int adjsoit; 	// INT(11) 조정문서 Item Key
	private String physoky; // VARCHAR(10) 실사문서 번호
	private int physoit; 	// INT(11) 실사문서 Item Key
	private String storfky; // VARCHAR(10) 재고키 참고번호
	private String lotat01; // VARCHAR(20) 재고LOT 속성		
	private String lotat02;	// VARCHAR(20) 재고LOT 속성		
	private String lotat03;	// VARCHAR(20) 재고LOT 속성		
	private String lotat04;	// VARCHAR(20) 재고LOT 속성		
	private String lotat05;	// VARCHAR(20) 재고LOT 속성		
	private String lotat06;	// VARCHAR(20) 재고LOT 속성		
	private String lotat07;	// VARCHAR(20) 재고LOT 속성		
	private String lotat08;	// VARCHAR(20) 재고LOT 속성		
	private String lotat09;	// VARCHAR(20) 재고LOT 속성		
	private String lotat10;	// VARCHAR(20) 재고LOT 속성		
	private String lotat11;	// VARCHAR(20) 재고LOT 속성		
	private String lotat12;	// VARCHAR(20) 재고LOT 속성		
	private String lotat13;	// VARCHAR(20) 재고LOT 속성		
	private String lotat14;	// VARCHAR(20) 재고LOT 속성		
	private String lotat15;	// VARCHAR(20) 재고LOT 속성		
	private String lotat16;	// VARCHAR(20) 재고LOT 속성		
	private String lotat17;	// VARCHAR(20) 재고LOT 속성		
	private String lotat18;	// VARCHAR(20) 재고LOT 속성		
	private String lotat19; // VARCHAR(20) 재고LOT 속성			
	private String lotat20; // VARCHAR(20) 재고LOT 속성
	
	private String doccate; // VARCHAR(10) 문서유형
	private String doctype; // VARCHAR(10) 문서타입
	
	//WTAKIT
	private String tasksts; // VARCHAR(10) 작업상태
	private String frareky; // VARCHAR(20) From Area
	private String frzonky; // VARCHAR(20) From Zone
	private String frlocky; // VARCHAR(20) From Loc
	private String toareky; // VARCHAR(20) To Area
	private String tozonky; // VARCHAR(20) To Zone
	private String tolocky; // VARCHAR(20) To Loc
	private int fordqty; 	// INT(11) 지시수량
	private int tcmpqty; 	// INT(11) 완료수량
	private String parsncd; // VARCHAR(10) 사유코드
	private String parsnnm; // VARCHAR(200) 사유내용
	private String postdat; // VARCHAR(8) 영업일자
	private String pkrsncd; // VARCHAR(10) 취소사유 코드
	private String pkrsnnm; // VARCHAR(200) 취소사유 내용
	private String picktyp; // VARCHAR(20) 출고작업유형
	private String allgrky; // VARCHAR(20) 할당그룹키
	private String cooutky; // VARCHAR(20) OM수주번호
	private int cooutit;	// INT(11) OM수주번호 아이템
	private String fuomeky; // VARCHAR(10) 지시단위
	private String tuomeky; // VARCHAR(10) 완료단위
	
	//WM MASTER
	private String areanam;
	private String zonenam;
	private String locanam;
	private String frarenm;
	private String frzonnm;
	private String frlocnm;
	private String toarenm;
	private String tozonnm;
	private String tolocnm;
	
	//MZONMA
	private String movieyn;
	private String moveoyn;

	//부품 바코드
	private String upccode;
	private List<String> stockyArr;
	
}
