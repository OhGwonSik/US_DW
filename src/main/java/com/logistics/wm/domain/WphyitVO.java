package com.logistics.wm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class WphyitVO {
	
	private String rowkey;
	private String compkey;
	private String physoky; // VARCHAR(10)
	private int physoit;    // INT(11) 실사문서 번호
	private String warekey; // VARCHAR(20) 
	private String doccate; // VARCHAR(10)
	private String doctype; // VARCHAR(10)
	private String phyname; // VARCHAR(200)
	private String phymode; // VARCHAR(10)
	private String phygrky; // VARCHAR(10)
	private String stockky; // VARCHAR(10)
	private String areakey; // VARCHAR(10)
	private String zonekey; // VARCHAR(10)
	private String locakey; // VARCHAR(20)
	private String subsect; // VARCHAR(10) SUB SECTION
	private String truntyp; // VARCHAR(10) Transfer Unit Type
	private String trunpid; // VARCHAR(20) Transfer Unit ID
	private String lotnmky; // VARCHAR(10)
	private String ownerky; // VARCHAR(20)
	private String skumkey; // VARCHAR(50)
	private String skudesc; // VARCHAR(100)
	private String vendkey; // VARCHAR(20)
	private String sirbcod; // VARCHAR(20)
	private int systqty;    // INT(11) 시스템수량
	private int physqty;    // INT(11) 실사수량
	private int compqty;    // INT(11) 확정수량
	private String suomkey; // VARCHAR(10)
	private String rsncode; // VARCHAR(10)
	private String physrmk; // VARCHAR(10)
	private String stkstat; // VARCHAR(10)
	private String blockid; // VARCHAR(3)
	private String physrow; // VARCHAR(3)
	private String physsec; // VARCHAR(3)
	private String physflo; // VARCHAR(3)
	private String credate;
	private String cretime;
	private String creuser;
	private String lmodate;
	private String lmotime;
	private String lmouser;
	private String indibzl;
	private String indiarc;
	private int updtchk;
	
	private int sbloqty;
	private int diffqty;
	private int comqty;
	
	//wmlt5
	private String brandcd;
	
}  
   