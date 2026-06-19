package com.logistics.wm.domain;

import java.util.List;

import com.logistics.common.dto.CommonDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KioskDTO extends CommonDTO{
	/* 
	 * ******************************************** 
	  - DTO Name       : KioskDTO
	  - Description    : 키오스크 기능페이지에 필요한 필드 모아둔 DTO
	  - Made By        : 오권식
	  - Creation Date  : 2023.10.05
	  ------------------------------------------
	 
	* ********************************************  
	*/
	private List<PdaDTO> addList;
	private String tabFlag;
}
