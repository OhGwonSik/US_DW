package com.logistics.om.domain;

import java.util.List;

import com.logistics.common.dto.CommonDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class MordmaDTO extends CommonDTO {
	
	private String compkey;		// VARCHAR(10) Compkey
	private String warekey;		// VARCHAR(20) 창고키
	private String cootype;		// VARCHAR(50) 고객오더타입
	private String coocate;		// VARCHAR(10) 고객오더유형
	private String ococtnm;     // VARCHAR(60) 고객오더유형명
	private String cootynm;		// VARCHAR(60) 고객오더타입명칭
	private String cotylky;		// VARCHAR(20) 고객오더다국어키
	private String couseyn;		// VARCHAR(1) 사용여부
	
	// 검색 조건
	private List<String> cootypes;
}
