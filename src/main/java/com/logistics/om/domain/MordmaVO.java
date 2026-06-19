package com.logistics.om.domain;

import lombok.Data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MordmaVO {
	
	private String compkey;		// VARCHAR(10) Compkey
	private String warekey;		// VARCHAR(20) 창고키
	private String cootype;		// VARCHAR(50) 고객오더타입
	private String coocate;		// VARCHAR(10) 고객오더유형
	private String ococtnm;     // VARCHAR(60) 고객오더유형명
	private String cootynm;		// VARCHAR(60) 고객오더타입명칭
	private String cotylky;		// VARCHAR(20) 고객오더다국어키
	private String couseyn;		// VARCHAR(1) 사용여부
	
	//common column
	private String credate; 	// VARCHAR(8) create date
	private String cretime; 	// VARCHAR(6) create time
	private String creuser; 	// VARCHAR(60) create user
	private String lmodate; 	// VARCHAR(8) last modify date
	private String lmotime; 	// VARCHAR(6) last modify time
	private String lmouser; 	// VARCHAR(60) last modify user
	private String indibzl; 	// VARCHAR(1) biz logic indicator
	private String indiarc; 	// VARCHAR(1) Archive indicator
	private int updtchk; 		// INT(11) Update check
}
