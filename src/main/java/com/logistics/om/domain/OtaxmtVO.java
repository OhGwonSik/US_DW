package com.logistics.om.domain;

import lombok.Data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OtaxmtVO {
	
	private String rowkey;		//rowkey
	private int    otaxseq;      //int				부가세관리 SEQ
	private int    taxseqi;      //int				부가세관리 SEQ
	private String crudmod;     //varchar(10)		CRUD모두
	private String compkey;     //varchar(20)		Company Key
	private String ownerky;     //varchar(20)		화주
	private String custkey;     //varchar(20)		매장
	private String retakey;     //varchar(20)		소매
	private String pbilldt;     //varchar(8)		청구일자(전기일)
	private String billdat;     //varchar(8)		실제 청구일자
	private String billtim;     //varchar(6)		실제 청구시간
	private int    billamt;     //int				청구금액
	private int    dsctamt;     //int				할인금액
	private String issdtyn;     //varchar(1)		발행여부
	private int    deptbkb;     //int				통장입금액
	private int    deptchs;     //int				현금입금액
	private String depstyn;     //varchar(1)		입금여부
	private int    taxblns;     //int				부가세잔액
	private String dpstdat;     //varchar(8)		실제 입금일자
	private String dpsttim;     //varchar(6)		실제 입금시간
	private String remarks;     //varchar(2000)		비고
	private String remarki;		//varchar(2000)		비고(아이템)
	private String credate;     //varchar(8)		생성일자
	private String cretime;     //varchar(6)		생성시간
	private String creuser;     //varchar(60)		생성사용자
	private String lmodate;     //varchar(8)		수정일자
	private String lmotime;     //varchar(6)		수정시간
	private String lmouser;     //varchar(60)		수정사용자
	private String indibzl;     //varchar(1)		Business logic indicator
	private String indiarc;     //varchar(1)		Archive indicator
	private int    updtchk;     //int				Update check
	
	
	// 커스텀 변수
	private int deptamt;		//납입액
	private int balance;		//잔액
	
	private String pbillfr;
	private String pbillto;
	
}
