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
public class TplnhdDTO extends CommonDTO {
	
	/* 
	 * ******************************************** 
	  - DTO Name       : TplnhdDTO
	  - Description    : TM TPLNHD 테이블 DTO
	  - Made By        : 하주영
	  - Creation Date  : 2023.08.02
	  ------------------------------------------
	 
	* ********************************************  
	*/

	private String compkey;
	private String vhplnky;
	private String warekey;
	private String vehicky;
	private String doccate;
	private String doctype;
	private String postdat;
	private String vhpdate; // 계획
	private String vhptime;
	private String useract;
	private String retodyn;
	// private String trnstat; // 운송상태 << procedure
	private String trnmemo; // 배송메모
	private String plnstat;

	private String stpitdt; // 운송시작일자 추가 : 2023 08 18
	private String stpittm; // 운송시작시간 추가 : 2023 08 18
	private String trfindt;
	private String trfintm;

	private String emerscd; // 긴급사유코드
	private String emerdnm; // 긴급 메모
	private String pcarscd; // 운송취소사유코드
	private String pcarsnm; // 운송취소 메모
	private String pcadate; // 운송취소 날짜
	private String pcatime; // 운송취소 시각
	private String pcauser;
	private String pbillyn;
	private int pbilpay;
	private String shtrtky; // 셔틀노선키
	private String trareky; // 권역키

	// for pagination
	private int offsetnumber;
	private int limitnumber;

	// for timal search
	private String targetvhpdate; // past 과거 today오늘 future 미래

	// for trnstat search
	private String targetstate;

	// for ware info
	private String wanamlc;
	private String whnamko;
	private String wanamen;
	private String waaddr1;
	private String waaddr2;
	private String waaddr3;

	// for shuttle info
	private String shtrtnm;

	// for trareky info 권역
	private String trarenm;

}
