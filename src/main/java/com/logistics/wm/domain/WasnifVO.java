package com.logistics.wm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class WasnifVO {
	
	private String rowkey;	//rowkey
	private String compkey; //Company Key
	private String eoasnky; //ASN No
	private int eoasnit; //ASN item number
	private String warekey; //창고
	private String doccate; //문서유형
	private String doctype; //문서타입
	private String asnstat; //ASN 상태
	private String asndate; //입고 예정일
	private String asntime;	//입고 예정시간	07.12 한지수
	private String ownerky; //화주
	private String skumkey; //상품
	private String skudesc; //상품명
	private String skuskey; //SKUSKEY
	private String sirbcod; //개별관리 번호
	private int poitqty; //발주수량
	private String puomkey; //발주단위
	private int asndqty; //입고예정 수량
	private String auomkey; //입고예정 단위
	private String truntyp; // Transfer Unit type
	private int rchpqty; //입하가능누적수량
	private int rchsqty; //기입하완료수량
	private String rchsuom; //기입하완료단위
	private String vendkey; //메인 공장
	private String carrkey; //*운송사
	private String truckno; //*입고차량 번호
	private String tructon; //*입고차량 톤수
	private String asnmemo;	//입고 요청사항	07.12 한지수
	private String proddat; //생산일자
	private int shelife; //*유통기한
	private String expidat; //*유통기간
	private String custkey; //수주처 매장
	private String destkey;	//도착지			07.12 한지수
	private String dctctyp; //발주입고유형
	private String lotat01; //재고LOT속성01
	private String lotat02; //재고LOT속성02
	private String lotat03; //재고LOT속성03
	private String lotat04; //재고LOT속성04
	private String lotat05; //재고LOT속성05
	private String lotat06; //재고LOT속성06
	private String lotat07; //재고LOT속성07
	private String lotat08; //재고LOT속성08
	private String lotat09; //재고LOT속성09
	private String lotat10; //재고LOT속성10
	private String lotat11; //재고LOT속성11
	private String lotat12; //재고LOT속성12
	private String lotat13; //재고LOT속성13
	private String lotat14; //재고LOT속성14
	private String lotat15; //재고LOT속성15
	private String lotat16; //재고LOT속성16
	private String lotat17; //LOT17=라벨갈이 여부
	private String lotat18; //LOT18=라벨갈이 요청고객
	private String lotat19; //LOT19=MTO 오더주문번호
	private String lotat20; //LOT20=MTO 오더주문아이템
	private String stkstat; //재고상태
	private String rsncode; //입고사유코드
	private String rcvsrmk; //입고사유내용
	private String copodky; //ref ERP PO
	private String copodit; //ref ERP PO Item
	private String refsodc; //ref ERP SO
	private String refsoit; //ref ERP SO Item
	private String refdndc; //ref ERP DN
	private String refdnit; //ref ERP DN Item
	private String refsddc; //ref ERP SD
	private String refsdit; //ref ERP SD Item
	private String credate; //생성일자
	private String cretime; //생성시간
	private String creuser; //생성사용자
	private String lmodate; //수정일자
	private String lmotime; //수정시간
	private String lmouser; //수정사용자
	private String indibzl; //biz logic indicator
	private String indiarc; //Archive indicator
	private String updtchk; //Update check
	
	private String doctynm;	//Document type Name
	private String vendnam;	//메인 공장명
	private String custnam;	//수주처 매장명
	private String retanam;	//소매명
	private Double gpakcnt;	//절
	private int itemcnt;	//Item Cnt
	private String pastrky;	//적치전략키
	private String asnsnam;	//ASN 상태명
	private String rvlocky;	//입하 Loc Key
	private int rcveqty;	//입하대상 수량
	private String skuwabc;	//WH SKU ABC analyzed value
	private String rcvrscd;	//입고 사유코드
	private String rcvrsnm;	//입고 내용
	private String rcvdcky; //입고문서번호
	private int	   rcvdcit; //입고문서번호 아이템
	private String retakey; //소매
	private String salgrky;	//고객오더 그룹키
	
}
