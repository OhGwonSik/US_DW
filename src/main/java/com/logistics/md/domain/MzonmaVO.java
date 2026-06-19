package com.logistics.md.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MzonmaVO {
	/*********************************************
	 * - 클래스명 : MzonmaVO 
	 * - 설명 : Zone 마스터 테이블 VO 
	 * - 최초생성일시 : 2023.07.25 
	 * - 최초생성자 : Park T. S. 
	 **********************************************/
	// P.K.
	private String compkey; // VARCHAR(20) - Company Key
	private String warekey; // VARCHAR(20) - Warehouse Key
	private String areakey; // VARCHAR(20) - Area key
	private String zonekey; // VARCHAR(20) - Zone Key

	// Columns
	private String zouseyn; // VARCHAR(1) - 존 사용여부
	private String zonenam; // VARCHAR(100) - 존이름
	private String zonetyp; // VARCHAR(10) 존타입
	private String zofloor; // VARCHAR(10) - 실제층
	private String zobuild; // VARCHAR(10) - 존 동
	private String putawyn; // VARCHAR(1) - 적치가능여부
	private String allocyn; // VARCHAR(1) - 할당 가능여부
	private String moveoyn; // VARCHAR(1) - 이동출발 가능여부
	private String moveiyn; // VARCHAR(1) - 이동도착 가능여부
	private String miqtyyn; // VARCHAR(1) - minus qty가능여부
	private String zotemty; // VARCHAR(10) - 온도조건타입
	private String syonlyn; // VARCHAR(1) - 시스템존 여부

	// Common columns
	private String credate; // VARCHAR(8) create date
	private String cretime; // VARCHAR(6) create time
	private String creuser; // VARCHAR(60) create user
	private String lmodate; // VARCHAR(8) last modify date
	private String lmotime; // VARCHAR(6) last modify time
	private String lmouser; // VARCHAR(60) last modify user
	private String indibzl; // VARCHAR(1) biz logic indicator
	private String indiarc; // VARCHAR(1) Archive indicator
	private int updtchk; // INT(11) Update check
}
