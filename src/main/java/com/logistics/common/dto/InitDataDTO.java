package com.logistics.common.dto;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InitDataDTO {
	/*
	 ****************************************************************************************
	 - 클래스명 : InitDataDTO
	 - 최초생성자 : Park T. S.
	 - 최초생성일시 :  2023.07.26
	 - 설명 : Response시 사용할 Map Object
	 -------------------------------------------------------------------------------------------------
	 ****************************************************************************************
	 */

	// Response to map object
	private Map<String, Object> item;
}
