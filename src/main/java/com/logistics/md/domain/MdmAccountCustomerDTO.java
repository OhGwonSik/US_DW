package com.logistics.md.domain;

import com.logistics.common.dto.CommonDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MdmAccountCustomerDTO extends CommonDTO{
	/*
	 * ********************************************
	  - 클래스 명 : MdmAcountCustomerDTO
	  - 설명 : MDM 어카운트 고객 정보 조인 테이블 뷰 DTO 
	  - 생성자 : Park T. S.
	  - 생성일자  : 2023.08.25
	  ------------------------------------------
	  - 수정자 : Park T. S.
	  - 수정일자 : 2023.12.13
	  - 내용 : 참조테이블 변경으로 DTO명 및 필드 추가
	* ********************************************
	*/
	// P.K.
	private String actcd; // VARCHAR(7) - 어카운트 업체코드
	private String custcd; // VARCHAR(7) - 업체코드

	// Columns
	private String custnm; // VARCHAR(30) - 업체명
	private String regno; // VARCHAR(12) - 사업자등록번호
	private String natcd; // VARCHAR(2) - 국가코드
	private String adr; // VARCHAR(100) - 사업장소재지
	private String zipcd; // VARCHAR(5) - 우편번호
	private String rpnm; // VARCHAR(50) - 대표자명
	private String pchg; // VARCHAR(50) - 담당자
	private String phnm; // VARCHAR(15) - 연락처
	private String preml; // VARCHAR(50) - email
}
