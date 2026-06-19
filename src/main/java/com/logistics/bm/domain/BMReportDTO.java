package com.logistics.bm.domain;

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
public class BMReportDTO extends CommonDTO {
	/*
	 ********************************************
	 - DTO Name : BMReportDTO
	 - Description : BMReportDTO
	 - Made By : Park T. S.
	 - Creation Date :  2023.10.13 
	 ------------------------------------------
	 ********************************************
	 */	
	
	// 공통
	private String compkey; // 회사
	private String warekey; // 창고
	private String ownerky; // 화주
	private String ptnrkey; // 거래처
	
	// 비용, 정산
	private String blhdcky; // 정산번호
	private String blrpmgb; // 대금구분

	//프린트 로그
	private String doccate; // 문서유형
	private String doctype; // 문서타입
	private String progrid; // 프로그램 id
	private String loguser;
	private String user;
	
	private String bltitle;
	private String blsdate;
	private String bledate;
	private String blrcsgb;
	private String blrtkgb;
	private String blrunit;
	private int refcqty;
	private int blrcost;
	private int blramot;
	private int blramtx;
	private int blrsumy;
	private String blrtknm;
	private String test2;
	
	private String bthdcky; //정산문서번호
	private String btrpmgb; //대금구분
	private String btsdate;
	private String btedate;
	private String mdestky;
	private String vhcfnam;
	private String ownernm;
	private String btrcsgb;
	private String btrunit;
	private int btrcost;
	private int btramot;
	private String btrtknm;
	private String bttitle;
	private String btrremk;
	private String blrremk;
	
	//차량별 명세서
	private String usernam;
	private String shtrtky;
	private String shtrtnm;
	private String btrcsnm;
	
	private int rownum;
	private String type;
	private String blddcky;
	private int docitem;
	private String dochkey;
	
	private String useract;
	private String vehicky;
	
	//운전자 계정
	private String telphnm;
}
