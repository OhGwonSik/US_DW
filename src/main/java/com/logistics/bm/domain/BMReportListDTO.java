package com.logistics.bm.domain;

import java.util.List;

import com.logistics.common.dto.CommonDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BMReportListDTO extends CommonDTO{
	
	/* 
	 * ******************************************** 
	  - DTO Name       : BMReportListDTO
	  - Description    : BMReportListDTO - BM 리포트 출력을 위해 필요한 필드들을 담은 DTO
	  - Made By        : 하주영
	  - Creation Date  : 2023.11.01
	  ------------------------------------------
	 
	* ********************************************  
	*/

	private List<BMReportDTO> reportList;
	
	private String compkey;
	private int docitem;
	private String doccate;
	private String doctype;
	private String blddcky;
	private String dochkey;
	private String progrid;
	private String type;
	private String warekey;
}
