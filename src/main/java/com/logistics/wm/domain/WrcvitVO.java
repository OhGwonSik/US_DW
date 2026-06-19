package com.logistics.wm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class WrcvitVO {
	
	private String rowkey;
	private String compkey;
	private String rcvdcky;
	private int rcvdcit;
	private String warekey;
	private String doccate;
	private String doctype;
	private String rcvdcst;
	private String rcvitst;
	private String vendkey;
	private String carrkey;
	private String truckno;
	private String tructon;
	private String truntyp;
	private String trunpid;
	private String lotnmky;
	private String ownerky;
	private String skumkey;
	private String skudesc;
	private String sirbcod;
	private int asndqty;
	private String auomkey;
	private int rcveqty;
	private String ruomkey;
	private int rchsqty;
	private String rchsuom;
	private int pawdqty;
	private String puomkey;
	private String rvlocky;
	private String pastrky;
	private String palocky;
	private int sprcprc;	//Main공정 공인비
	private String rcvrscd;	//입고사유코드
	private String rcvrsnm;	//입고사유내용
	private String rcvdcyn;	//입고취소 처리여부
	private String rcarscd;	//입고취소 사유코드
	private String rcarsnm;	//입고취소 사유내용
	private String rcadate;	//입고취소 일자
	private String rcatime;	//입고취소 시간
	private String rcauser;	//입고취소 사용자
	private String proddat;
	private int shelife;
	private String expidat;
	private String rcvdcdt;
	private String rcvfrdt;
	private String lotfrky;
	private String lotat01;
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
	private String stkstat;
	private String rcvteam;
	private String eoasnky;
	private int eoasnit;
	private String copodky;
	private String copodit;
	private String refsodc;
	private String refsoit;
	private String refdndc;
	private String refdnit;
	private String refsddc;
	private String refsdit;
	private String pmtinyn;	//입고수불마감여부
	private String pmtindt;	//입고수불마감일자
	private String pmtintm;	//입고수불마감시간
	private String pmtinur;	//입고수불마감사용자
	private String credate;
	private String cretime;
	private String creuser;
	private String lmodate;
	private String lmotime;
	private String lmouser;
	private String indibzl;
	private String indiarc;
	private int updtchk;
	
	private String rcvrnam;	//입고 사유 코드명
	private String vendnam;	//공장명
	private String doctynm;	//Document type Name
	private Double gpakcnt;	//절
	private int itemcnt;	//Item Cnt
	private int stotqty;    // 총재고 수량
	private String rcarnam;	//입고취소 사유코드명
	private String brandcd;	//브랜드
	private int tproprc;	//매입단가
	private String skuskey;	//상품작업지시 이력KEY
	private String skutkey;	//상품작업지시 KEY
	private String sprofac;	//작업공장
	private String sprotyp;	//공정 구분
	private String spronam;	//공정 명
	private int sprcprc01;	//발주공임비
	private int sprcprc02;	//작지공임비
	private int sprcprc03;	//공임비
	private String custkey;	//도매
	private String custnam; //도매명
	
}
