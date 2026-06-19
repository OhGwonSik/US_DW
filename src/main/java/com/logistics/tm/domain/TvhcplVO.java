package com.logistics.tm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TvhcplVO {
	
	private int no;
	private String compkey;  //VARCHAR(20)
	private String vhplnky;  //VARCHAR(10) 배차계획 헤더키
	private int vhplnit;     //INT(11)     배차계획 아이템
	private String vhpdate;  //VARCHAR(8)  배차계획 일자
	private String vhptime;  //VARCHAR(6)  배차계획 시간
	private String doccate;  //VARCHAR(10) 문서유형
	private String doctype;  //VARCHAR(10) 문서타입
	private String shtrsts;  //VARCHAR(10) 셔틀상태
	private String vehicky;  //VARCHAR(10) 차량코드
	private String shtrtky;  //VARCHAR(10) 셔틀노선키
	private int shtrtit;     //INT(11)     셔틀노선순번
	private String repitdt;  //VARCHAR(8)  보고 일자
	private String repittm;  //VARCHAR(6)  보고 시간
	private String rsncode;  //VARCHAR(10) 보고 사유코드
	private String rsnremk;  //VARCHAR(1000) 보고 사유내용
	private String credate;  //VARCHAR(8) 
	private String cretime;  //VARCHAR(6)
	private String creuser;  //VARCHAR(60)
	private String lmodate;  //VARCHAR(8)
	private String lmotime;  //VARCHAR(6)
	private String lmouser;  //VARCHAR(60)
	private String indibzl;  //VARCHAR(1)
	private String indiarc;  //VARCHAR(1)
	private int updtchk;     //INT(11)
	
	private String shtrpnm;  //운송보고명
	private int datacnt;
	private int cntdata;
	private int cntsnew;
	private int cntsing;
	private int cntscmp;
	
	private String pos01ct;
	private String pos02ct;
	private String pos03ct;
	private String pos04ct;
	private String pos05ct;
	private String pos06ct;
	private String pos07ct;
	private String pos08ct;
	private String pos01st;
	private String pos02st;
	private String pos03st;
	private String pos04st;
	private String pos05st;
	private String pos06st;
	private String pos07st;
	private String pos08st;
	private String pos01nm;
	private String pos02nm;
	private String pos03nm;
	private String pos04nm;
	private String pos05nm;
	private String pos06nm;
	private String pos07nm;
	private String pos08nm;
	private String pos01ty;
	private String pos02ty;
	private String pos03ty;
	private String pos04ty;
	private String pos05ty;
	private String pos06ty;
	private String pos07ty;
	private String pos08ty;
	private int coldcnt;
	
	private String vhpStrDate;
	private String vhpEndDate;
}
