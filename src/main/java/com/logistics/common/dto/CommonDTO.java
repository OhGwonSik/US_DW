package com.logistics.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.logistics.sy.domain.UserVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommonDTO{
	/*
	 ****************************************************************************************
	 - 클래스명 : CommonDTO
	 - 최초생성자 : Park T. S.
	 - 최초생성일시 :  2023.07.25
	 - 설명 : 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO(상속용)
	 -------------------------------------------------------------------------------------------------
	 ****************************************************************************************
	 */

	// user session data
	@JsonIgnore // json parsing 하지 않게 하는 annotation
	private UserVO userData; // Aop로 controller에서 들어옴

	// PQGrid recIndx;
	private String rowkey;

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

	// Procedure Outparam
	private String omsgkey; // procedure 결과 메세지
	private int oresult; // procedure 결과

	// Combo Data
	private String combovl; // comboBox value
	private String combonm; // comboBox name

	// grid의 setCreate 이벤트 발동 시 검색영역의 value를 Parameter로 넘겨 InitController 호출해야하는 Case
	private String warekey; // 창고키
	private String destkey; // 도착지키
}
