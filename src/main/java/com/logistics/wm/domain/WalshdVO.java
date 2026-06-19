package com.logistics.wm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WalshdVO {
	//WALSHD
	private String rowkey;		//compkey+warekey+alsorky 조합
	private String compkey; 	//varchar(20) 		Company Key
	private String warekey; 	//varchar(20) 		Warehouse Key
	private String alsorky; 	//varchar(10) 		정렬키
	private String alsornm; 	//varchar(60) 		정렬명
	private String aldelyn; 	//varchar(1)  		Deletion YN
	private String credate;     //varchar(8)        생성일자
	private String cretime;     //varchar(6)        생성시간
	private String creuser;     //varchar(60)       생성사용자
	private String lmodate;     //varchar(8)        수정일자
	private String lmotime;     //varchar(6)        수정시간
	private String lmouser;     //varchar(60)       수정사용자
	private String indibzl;     //varchar(1)        biz logic indicator
	private String indiarc;     //varchar(1)        Archive indicator
	private int	   updtchk;     //int(11)           Update check
	
	private String beforealsorky; //varchar(10)     before필터링키
}   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    