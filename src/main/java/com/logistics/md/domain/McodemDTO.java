package com.logistics.md.domain;

import java.util.List;

import com.logistics.common.dto.CommonDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class McodemDTO extends CommonDTO{
	/*
	 ********************************************
	 - DTO Name : McodemDTO
	 - Description : 공용코드 마스터 테이블(실사용에서는 MDM을 사용)
	 - Made By : Park T. S.
	 - Creation Date :  2023.07.23
	 ------------------------------------------
	 ********************************************
	 */
	// P.K.
	private String compkey; // VARCHAR(20) - Company Key
	private String comcdky; // VARCHAR(20) - Common code Key
	private String comcdvl; // VARCHAR(20) - Common code value

	// Columns
	private String warekey; // VARCHAR(20) - Warehouse key
	private String comkytx; // VARCHAR(100) - Common key Text
	private String comcdtx; // VARCHAR(100) - Common code Text
	private int comcdor; // INT(11) - Code 표시 순서
	private String comcdsy; // VARCHAR(1) - Code 시스템 전용
	private String cdcate1; // VARCHAR(50) - Code 분류1
	private String cdcate2; // VARCHAR(50) - Code 분류2
	private String cdcate3; // VARCHAR(50) - Code 분류3
	private String cdattr1; // VARCHAR(50) - Code Attribute1
	private String cdattr2; // VARCHAR(50) - Code Attribute2
	private String cdattr3; // VARCHAR(50) - Code Attribute3
	private String couseyn; // VARCHAR(1) - 사용YN(Y=사용)

	// Data
	private String oldComcdky;
	private String oldComcdvl;

	private String actyn;

	private String cdky;
	private String cdvl;
	private String cdtx;

	private List<String> comcdkys; // Common code Keys
	private List<String> comcdvls; // Common code values
	private List<String> cdcate2s;
	private String mdp3Comcdvl;
}
