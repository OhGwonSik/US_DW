package com.logistics.md.domain;

import com.logistics.common.dto.CommonDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MzonmaDTO extends CommonDTO {
	/* 
	 * ******************************************** 
	  - DTO Name   : MzonmaDTO
	  - Description    : Zone 마스터 테이블
	  - Made By        : Park T. S.
	  - Creation Date  : 2023.07.25
	  ------------------------------------------
	* ********************************************  
	*/
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

	// Data
	private String whnamlc; // Warehouse Name
	private String areanam; // Area Name

	private String oldWarekey; // 예전 창고키
	private String oldAreakey; // 예전 areakey
	private String oldZonekey; // 예전 zonekey
}
