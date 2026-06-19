package com.logistics.tm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TvhcmaVO {

	private int no;
	private String compkey; // 회사코드
	private String warekey;
	private String vehicky;
	private String vhcfnam;
	private String vhcsnam;
	private String dlvtype;
	private String carrier;
	private String vhcopty;
	private String vhctype;
	private int vhctncd;
	private double maxwegt;
	private double maxcapa;
	private String sperfyn;
	private String spelfyn;
	private String specvyn;
	private String spe04yn;
	private String spe05yn;
	private String vehgrnm;
	private String vehcsnm;
	private String vehtpyn;
	private String inbvhyn; // varchar(1) 입고차량 'N'
	private String oubvhyn; // varchar(1) 출고차량 'Y'
	private String gpseqyn;
	private String gpsvhid;
	private String imei; // GPS 단말기 번호
	private String msisdn; // DTG단말기 아이디
	private String vhuseyn;
	private String credate;
	private String cretime;
	private String creuser;
	private String lmodate;
	private String lmotime;
	private String lmouser;
	private String indibzl;
	private int indiarc;
}