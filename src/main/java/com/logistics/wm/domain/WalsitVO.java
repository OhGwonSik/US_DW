package com.logistics.wm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WalsitVO {
	//WALFHD
	private String rowkey;		//compkey+warekey+alfilky+alfilit 조합
	private String compkey;		//varchar(20)		Company Key
	private String warekey;		//varchar(20)		Warehouse Key
	private String alsorky;		//varchar(10)		정렬키
	private int	   alsotit;		//int(11)			정렬아이템
	private String alsocol;		//varchar(20) 		재고속성컬럼
	private int	   alsoord;		//int(11) 			오더정렬순서
	private String alsoasc;		//varchar(10) 		정렬방법 
	private String aldelyn;		//varchar(1) 		Deletion YN
	private String credate;		//varchar(8)		생성일자
	private String cretime;		//varchar(6)		생성시간
	private String creuser;		//varchar(60)		생성사용자
	private String lmodate;		//varchar(8)		수정일자
	private String lmotime;		//varchar(6)		수정시간
	private String lmouser;		//varchar(60)		수정사용자
	private String indibzl;		//varchar(1)		biz logic indicator
	private String indiarc;		//varchar(1)		Archive indicator
	private int	   updtchk;		//int(11)			Update check
	
	private String beforealsorky; //varchar(10)     before정렬키
	private int    beforealsotit; //int(11)     	before정렬아이템키
}   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    