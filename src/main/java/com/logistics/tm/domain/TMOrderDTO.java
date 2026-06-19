package com.logistics.tm.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.logistics.common.dto.CommonDTO;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.domain.MdesmaDTO;
import com.logistics.md.domain.MptnmaDTO;
import com.logistics.md.domain.MskuwcDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TMOrderDTO extends CommonDTO {
	
	/* 
	 * ******************************************** 
	  - DTO Name       : TMOrderDTO
	  - Description    : TM 오더관리에 필요한 필드들을 모아둔 DTO
	  - Made By        : 하주영
	  - Creation Date  : 2023.07.19
	  ------------------------------------------
	 
	* ********************************************  
	*/

	// OCOSAL
	private String compkey;
	private String postdat;
	private String saldate;
	private String saltime;
	private String vendkey;
	private String salmemo;
	private int shcfqty;
	private int diffqty;
	private String dctctyp;
	private String rsncode;
	private String rsndesc;
	private String inbstat;
	private String ordgr01;
	private String ordgr02;
	private String ordgr03;
	private String closlyn;
	private String clodate;
	private String clouser;
	private String bthdcky;
	private int bthdcit;
	private String towarky;
	private String saatc05;
	private String saatc01;
	private String saatc06;
	private String saatc10;
	private String saatc07;
	private String saatc08;
	private String saatc11;
	private String saatc12;
	private String saatc13;
	private int rgitqty;
	private String oubstat;
	private String salstat;
	private String plnstat;
	private String creuser;
	private String cooutky;
	private String cooutit;
	private String skumkey;
	private String ownerky;
	private String saatc03;
	private String custkey;
	private String warekey;
	private String salrqdt;
	private String salrqtm;
	private String saatc02;
	private int saatn01;
	private int saatn02;
	private int saatn03;
	private int saatn04;
	private int saatn05;
	private String saatc04;
	private String saatc09;
	private int soitqty;
	private String credate;
	private String cretime;
	private String lmodate;
	private String lmotime;
	private String lmouser;
	private String indibzl;
	private String indiarc;
	private int updtchk;
	private String trnstat;

	// TPLNIT
	private String vhplnky;
	private String repittm;
	private String retodyn;
	private int tritqty;
	private String truntyp;
	private String trunpid;
	private String shtrsts;
	private int vhplnit;

	// TPLNHD
	private String vhpdate;
	private String vhptime;
	private String trnmemo;
	private String stpitdt;
	private String stpittm;
	private String trareky;

	// TVHCMA
	private String vhcsnam;
	private String vehgrnm;
	private String vehcsnm;
	private String vehtpyn;
	private String inbvhyn; // varchar(1) 입고차량 'N'
	private String oubvhyn; // varchar(1) 출고차량 'Y'
	private String vhcmemo;
	private String ownernm;

	// TSHRHD
	private String shtrtnm;
	private String shtmemo;
	private String shtgrnm;
	private String shtrtky;
	private String pbillyn;
	private int pbilpay;
	private String emerdnm;
	private String emerscd;

	// TSHRVH
	private String shtvcap;
	private String vehicky;
	private String srcehod;
	
	//OCODMA
	private String cartype;
	private String cartynm;

	// join 값들
	private String cunamlc;
	private String whnamlc;
	private String ptnamlc;
	private String cootynm;
	private String ownamlc;
	private String ococtnm;

	// MDESMA
	private String ptnrkey;
	private String destkey;
	private String denamlc;

	// MSKUWC
	private String suomkey;
	private String skudesc;
	private String skuskey;
	private String skustat;
	private String packkey;
	private int packqty;
	
	//WSTKKY
	private int stotqty;

	// MDOCMA
	private String docctnm;
	private String doctynm;
	private String doccate;
	private String doctype;
	
	//OCATMA
	private String scatecd;
	private String scatenm;
	private String mcatecd;
	private String mcatenm;

	// 커스텀
	private String ostrnst;
	private String postdatFrom;
	private String postdatTo;
	private String dtcount;
	private String saatc10min;
	private String saatc10max;
	private String ocoskum;
	private String ordervhplnky;
	private List<String> plnstatCheckedList;
	private List<String> trnstatCheckedList;
	private List<MdesmaDTO> destList;
	private List<TMOrderDTO> custList;
	private List<CommonDTO> ownerList;
	private List<CommonDTO> warekeyList;
	private List<McodemDTO> plnstatList;
	private List<McodemDTO> trnstatList;
	private List<McodemDTO> oubstatList;
	private List<McodemDTO> salstatList;
	private List<McodemDTO> inbstatList;
	private List<TMOrderDTO> orderList;
	private List<String> destkeyList;
	private List<String> postdatList;
	private List<String> wareList;
	private List<TMOrderDTO> updateList;
	private List<TMOrderDTO> ocatmaList;
	
	private String loguser;
	private int tplnitchk;
}
