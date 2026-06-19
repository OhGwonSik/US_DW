package com.logistics.md.domain;

import java.util.List;

import com.logistics.common.dto.CommonDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MptnmaDTO extends CommonDTO {
	/*
	 * ********************************************
	  - DTO Name   : 고객사DTO
	  - Description    : 고객사 마스터 테이블
	  - Made By        : Park T. S.
	  - Creation Date  : 2023.07.25
	  ------------------------------------------
	* ********************************************
	*/
	// P.K.
	private String compkey; // VARCHAR(20) - Company Key
	private String ptnrkey; // VARCHAR(20) - 협력업체 key

	// Columns
	private String ownerky; // VARCHAR(20) - 화주 key // 23.10.05 현재 DB 컬럼은 NOT NULL 이므로 SQL에서 값 관련 체크 요망
	private String ptnatcd; // VARCHAR(3) - 협력업체 국가코드
	private String ptstpky; // VARCHAR(20) - Sold to party
	private String ptnrtyp; // VARCHAR(10) - 협력업체 타입
	private String ptnamlc; // VARCHAR(60) - 협력업체 명칭
	private String ptnamko; // VARCHAR(60) - 협력업체 한글
	private String ptnamen; // VARCHAR(60) - 협력업체 영어
	private String ptaddr1; // VARCHAR(100) - 주소1
	private String ptaddr2; // VARCHAR(100) - 주소2
	private String ptaddr3; // VARCHAR(100) - 주소3
	private double ptnrlat; // decimal(15,10) - 위도
	private double ptnrlon; // decimal(15,10) - 경도
	private String ptciynm; // VARCHAR(100) - city
	private String ptregnm; // VARCHAR(100) - region
	private String ptteln1; // VARCHAR(20) - 전화번호1
	private String ptteln2; // VARCHAR(20) - 전화번호2
	private String ptteln3; // VARCHAR(20) - 전화번호3
	private String pttaxcd; // VARCHAR(20) - tax code
	private String ptposbx; // VARCHAR(10) - PO box
	private String ptposcd; // VARCHAR(10) - postal code
	private String ptrepnm; // VARCHAR(60) - 대표자 이름
	private String ptreptl; // VARCHAR(20) - 대표자 전화번호
	private String ptrepem; // VARCHAR(60) - 대표자 email
	private String ptmannm; // VARCHAR(60) - 담당자 이름
	private String ptmantl; // VARCHAR(20) - 담당자 연락처
	private String ptmanem; // VARCHAR(60) - 담당자 email
	private String ptngro1; // VARCHAR(10) - Partner 그룹1
	private String ptngro2; // VARCHAR(10) - Partner 그룹2
	private String ptngro3; // VARCHAR(10) - Partner 그룹3
	private String ptinmag; // VARCHAR(60) - 파트너 내부담당자
	private String poststm; // VARCHAR(6) - 통계기준시간
	private String ptblctc; // VARCHAR(20) - 사업자등록번호
	private int ptnrord; // INT(11) - 협력업체 조회순서
	private String ptuseyn; // VARCHAR(1) - 사용여부

	// Data
	private String oldPtnrkey;

	private List<String> ptnrtyps;
	private String ptnrtyp2; // VARCHAR(10) - 협력업체 타입
}
