package com.logistics.wm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PutawayStrategyDTO {
	
	private String rowkey;	//rowkey
	private String compkey;	//Company Key
	private String warekey;	//Warehouse Key
	private String pastrky;	//적치전략키
	private String pastrnm;	//적치전략명칭
	private String pastrmd;	//적치방식키
	private String padelyn;	//Deletion YN
	private int pastrit;	//적치전략 아이템
	private int pastrod;	//적치전략 순서
	
	private String rcvloca;	//입하 Loc
	private String rcvloyn; //입하 Loc 사용<커스텀>
	
	private String rcvzone;	//입하 Zone
	private String rcvznyn; //입하 Zone 사용<커스텀>
	
	private String rcvsabc;	//입하 SKU ABC
	private String rcvabyn; //입하 SKU ABC 사용<커스텀>
	
	private String pawloca;	//적치 LOC_입력
	private String pawlcyn; //적치 LOC 사용<커스텀>
	
	private String pawzone;	//적치 ZONE_입력
	private String pawznyn; //적치 ZONE 사용<커스텀>
	
	private String schmeth;	//탐색 순서
	private String schmeyn; //탐색 순서 사용<커스텀>
	
	private String schsfno;	//탐색 시작단
	private String schsfyn; //탐색시작단 사용<커스텀>
	
	private String schefno;	//탐색 종료단
	private String schefyn; //탐색종료단 사용<커스텀>
	
	private String schmxop;	//탐색 혼적옵션
	private String schmxyn; //탐색혼적옵션 사용<커스텀>
	
	private String schlrop;	//탑색 적재율 채크방법
	private String schlryn; //탑색적재율 채크방법 사용<커스텀>
	
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
