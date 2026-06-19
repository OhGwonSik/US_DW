package com.logistics.wm.domain;

import com.logistics.common.dto.CommonDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StatisticsDTO extends CommonDTO {
	 /*
	 - 클래스명 : StatisticsDTO
	 - 설명 : Reason code 통계성 데이터
	 - 최초생성자 : Park T. S.
	 - 최초생성일자 : 2023.09.15
	 */

	// P.K.
	private String compkey; // VARCHAR(20) - Company Key
	private String warekey; // VARCHAR(20) - warehouse Key
	private String doccate; // VARCHAR(10) - 문서유형
	private String doctype; // VARCHAR(10) - 문서타입
	private String rsncode; // VARCHAR(10) - 사유코드

	// Columns
	private String docctnm; // VARCHAR(60) - 문서유형명칭
	private String doctynm; // VARCHAR(60) - 문서타입명칭
	private String rsncdnm; // VARCHAR(10) - 사유코드명

	// Data
	private String crmonth; // 사유코드발생년월
	private String crMonthFrom; // 연월 검색 from
	private String crMonthTo; // 연월 검색 to
	private int rscount; // 사유코드 발생 개수
	private String wtables; // 테이블 분류
}
