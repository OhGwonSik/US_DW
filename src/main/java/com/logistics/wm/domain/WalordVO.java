package com.logistics.wm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WalordVO {
	//WALFHD
	private String rowkey;		//compkey+warekey+alstord+alstrky 조합
	private String compkey;		//varchar(20) 		Company Key
	private String warekey;		//varchar(20) 		Warehouse Key
	private String alstrtx;		//WALSTR alstrnm + alstord 조합
	private int	   walorit;		//int(11) 	 		할당오더아이템
	private int	   alstord;		//int(11) 	 		우선순위
	private String alstrky;		//varchar(10) 		할당전략키
	private String doccate;		//varchar(10) 		문서유형
	private String doctype;		//varchar(10) 		문서타입
	private String custtyp;		//varchar(10) 		Customer Type
	private String cunatcd;		//varchar(10) 		Customer 국가
	private String custkey;		//varchar(20) 		오더 고객코드
	private String cooutky;		//varchar(10) 		오더 고객수주 오더번호
	private String ownerky;		//varchar(20) 		오더 화주
	private String skumkey;		//varchar(50) 		오더 상품
	private String brandcd;		//varchar(20) 		SKU Brand
	private String skugr01;		//varchar(20) 		SKU Group 1
	private String skugr02;		//varchar(20) 		SKU Group 2
	private String skugr03;		//varchar(20) 		SKU Group 3 Size
	private String skugr04;		//varchar(20) 		SKU Group 4 Color
	private String skugr05;		//varchar(20) 		SKU Group 5
	private String aldelyn;     //varchar(1)        Deletion YN
	private String credate;     //varchar(8)        생성일자
	private String cretime;     //varchar(6)        생성시간
	private String creuser;     //varchar(60)       생성사용자
	private String lmodate;     //varchar(8)        수정일자
	private String lmotime;     //varchar(6)        수정시간
	private String lmouser;     //varchar(60)       수정사용자
	private String indibzl;     //varchar(1)        biz logic indicator
	private String indiarc;     //varchar(1)        Archive indicator
	private int	   updtchk;     //int(11)           Update check
	
	private int	   beforealstord; //int				before우선순위
	private String beforealstrky; //varchar(10)     before할당전략키
}   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    