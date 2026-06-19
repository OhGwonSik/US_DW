package com.logistics.wm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecvEtcDTO extends RecvDTO {
	/*
	 ****************************************************************************************
	 - DTO Name : RecvEtcDTO
	 - Description : 기타입고등록/반품등록에서 사용할 DTO
	 - Made By : 최강호
	 - Creation Date :  2023.07.25
	 -------------------------------------------------------------------------------------------------
	 ****************************************************************************************
	 */
	
	//ocopur
	private String copodky; //VARCHAR(10) 발주문서
	private int copodit; //INT(11) 발주 아이템
	private String purstat; //VARCHAR(10) 발주상태
	
	
}
