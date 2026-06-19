package com.logistics.bm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BlgthdVO {
	/* 
	 * ******************************************** 
	  - DTO Name   : BlgthdVO
	  - Description    : 물류비정산 헤더 테이블
	  - Made By        : 고은별
	  - Creation Date  : 2023.07.19
	  ------------------------------------------
	 
	* ********************************************  
	*/
	
    //BLGTHD
    private String compkey;
    private String warekey;
    private String blhdcky;
    private String blhyymm;
    private String blsdate;
    private String bledate;
    private String lcddate;
    private String lcduser;
    private String lcvrfyn;
    private String lcvdate;
    private String lcvuser;
    private String lccnfyn;
    private String lccdate;
    private String lccuser;
    private String credate;
    private String cretime;
    private String creuser;
    private String lmodate;
    private String lmotime;
    private String lmouser;
    private String indibzl;
    private String indiarc;
    private int updtchk;
}
