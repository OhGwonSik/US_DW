package com.logistics.tm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.logistics.common.dto.CommonDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TMReportDTO extends CommonDTO{
	
	/* 
	 * ******************************************** 
	  - DTO Name       : TMReportDTO
	  - Description    : TMReportDTO - TM 리포트 출력을 위해 필요한 필드들을 담은 DTO
	  - Made By        : 하주영
	  - Creation Date  : 2023.09.26
	  ------------------------------------------
	 
	* ********************************************  
	*/
	
	private String vhplnky;
	private String postdat;
	private String printday;
	private String warekey;
	private String vhcfnam;
	private String saatc06;
	private String saatc08;
	private String saatc07;
	private String saatc10;
	private String saatc01;
	private String saatc05;
	private String saatc03;
	private String skumkey;
	private int tritqty;
	private String suomkey;
	private String skudesc;

	private String compkey;
	private String loguser;
	private int docitem;
	private String dochkey;
	private String doccate;
	private String doctype;
	private String user;
	private String progrid;
	private int printcount;

	private int rownum;
	private String type;
	private String pltnum;
	private int pltcnt;
	private int itemcnt;
}
