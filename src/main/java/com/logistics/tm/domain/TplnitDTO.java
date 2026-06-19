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
public class TplnitDTO extends CommonDTO {
	
	/* 
	 * ******************************************** 
	  - DTO Name       : TplnitDTO
	  - Description    : TM TPLNIT 테이블 DTO
	  - Made By        : 하주영
	  - Creation Date  : 2023.08.02
	  ------------------------------------------
	 
	* ********************************************  
	*/

	private String compkey;
	private String vhplnky;
	private int vhplnit;
	private String warekey;
	private int destord;
	private String custkey;
	private String destkey;
	private String shtrsts;
	private String ownerky;
	private String skumkey;
	private String skudesc;
	private String skuskey;
	private int soitqty;
	private int tritqty;
	private String suomkey;
	private String truntyp;
	private String trunpid;
	private int saatn01;
	private int saatn02;
	private int saatn03;
	private int saatn04;
	private int saatn05;
	private String saatc01;
	private String saatc02;
	private String saatc03;
	private String saatc04;
	private String saatc05;
	private String shtrpty;
	private String repitdt;
	private String repittm;
	private String rsncode; // 보고 사유코드 << 필요..?
	private String rsnremk;
	private String copodky;
	private String copodit;
	private String cooutky;
	private int cooutit;
	private String oeretky;
	private int oeretit;
	private String eoasnky;
	private int eoasnit;
	private String rcvdcky;
	private int rcvdcit;
	private String refsodc;
	private String refsoit;
	private String refdndc;
	private String refdnit;
	private String refsddc;
	private String refsdit;

	// for list search
	private String[] vhplnky_list;

	// for dest info
	private String denamlc;
	private String denamko;
	private String denamen;

	// for ware info
	private String wanamlc;
	private String whnamko;
	private String wanamen;

	// partner name
	private String ptnamlc;
	private String ptaddr1;
	private String ptaddr2;
	private String ptaddr3;
}
