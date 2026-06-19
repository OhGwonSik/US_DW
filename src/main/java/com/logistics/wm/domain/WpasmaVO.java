package com.logistics.wm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WpasmaVO {
	//WM 적지전략 마스터
	private String compkey; //Company Key
	private String pastrmd; //적치방키
	private String pastrnm; //적치방식명칭
	private int pastrod; //적치방식순서
	private String rcvloyn; //입하 Loc사용
	private String rcvznyn; //입하 Zone사용
	private String rcvabyn; //입하 SKU ABC사용
	private String pawlcyn; //적치 LOC사용
	private String pawznyn; //적치 ZONE사용
	private String schmeyn; //탐색순서 사용
	private String schsfyn; //탐색시작단 사용
	private String schefyn; //탐색종료단 사용
	private String schmxyn; //탐색혼적옵션 사용
	private String schlryn; //탑색적재율 채크방법 사용
	private String padelyn; //Deletion YN
	private String credate; //
	private String cretime; //
	private String creuser; //
	private String lmodate; //
	private String lmotime; //
	private String lmouser; //
	private String indibzl; //
	private String indiarc; //
	private int updtchk; //
	
}
