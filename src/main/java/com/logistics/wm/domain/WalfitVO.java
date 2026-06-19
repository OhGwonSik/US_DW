package com.logistics.wm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WalfitVO {
	//WALFHD
	private String rowkey;		//compkey+warekey+alfilky+alfilit 조합
	private String compkey;		//varchar(20)		Company Key
	private String warekey;		//varchar(20)		Warehouse Key
	private String alfilky;		//varchar(10)		필터링키
	private int	   alfilit;		//int(11)			필터링아이템키
	private int	   alftord;		//int(11)			필터순서
	private String alftlog;		//varchar(10)		연속필터 논리연산자	
	private String alftspa;		//varchar(10)		필터중괄호 시작
	private String alftcol;		//varchar(20)		필터컬럼명
	private String alftrel;		//varchar(10)		필터관계연산자
	private String alftval;		//varchar(20)		필터값
	private String alftepa;		//varchar(10)		필터중괄호 종료
	private String alftviw;		//varchar(300)		조건미리보기
	private String alftqry;		//varchar(1000)		추가쿼리
	private String aldelyn;		//varchar(1)		Deletion YN
	private String credate;		//varchar(8)		생성일자
	private String cretime;		//varchar(6)		생성시간
	private String creuser;		//varchar(60)		생성사용자
	private String lmodate;		//varchar(8)		수정일자
	private String lmotime;		//varchar(6)		수정시간
	private String lmouser;		//varchar(60)		수정사용자
	private String indibzl;		//varchar(1)		biz logic indicator
	private String indiarc;		//varchar(1)		Archive indicator
	private int	   updtchk;		//int(11)			Update check
	
	private String beforealfilky; //varchar(10)     before필터링키
	private int    beforealfilit; //int(11)     	before필터링아이템키
}   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    