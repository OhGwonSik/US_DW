package com.logistics.wm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class WpaordVO {
	
	private String rowkey;	//rowkey
	private String compkey;	//Company Key
	private String warekey;	//Warehouse Key
	private int paordit; //입고전략번호
	private int paordod; //우선순위
	private String pastrky; //적치전략키
	private String doccate; //문서유형
	private String doctype; //문서타입
	private String ownerky; //입고 화주
	private String skumkey; //입고 상품
	private String vendkey; //입고 공장
	private String carrkey; //입고 운송사
	private String skuwabc; //WH SKU ABC analyzed value
	private String brandcd; //SKU Brand
	private String skugr01; //SKU Group 1
	private String skugr02; //SKU Group 2 Season
	private String skugr03; //SKU Group 3 Size
	private String skugr04; //SKU Group 4 Color
	private String skugr05; //SKU Group 5
	private String padelyn; //Deletion YN
	private String credate; //생성일자
	private String cretime; //생성시간
	private String creuser; //생성사용자
	private String lmodate; //수정일자
	private String lmotime; //수정시간
	private String lmouser; //수정사용자
	private String indibzl; //biz logic indicator
	private String indiarc; //Archive indicator
	private int updtchk; //Update check
	                          
}
