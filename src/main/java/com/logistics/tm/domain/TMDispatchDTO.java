package com.logistics.tm.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.logistics.common.dto.CommonDTO;
import com.logistics.md.domain.McodemDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TMDispatchDTO extends CommonDTO {
	
	/* 
	 * ******************************************** 
	  - DTO Name       : TMDispatchDTO
	  - Description    : TM 배차관리에 필요한 필드들을 모아둔 DTO
	  - Made By        : 최재환
	  - Creation Date  : 2023.07.31
	  ------------------------------------------
	 
	* ********************************************  
	*/

	// TPLNHD
	private String compkey; // varchar(20) Company Key ' '
	private String vhplnky; // varchar(10) 운송계획 키 ' '
	private String warekey; // varchar(20) 창고 ' '
	private String vehicky; // varchar(10) 차량코드 ' '
	private String doccate; // varchar(10) 문서유형 ' '
	private String doctype; // varchar(10) 문서타입 ' '
	private String postdat; // varchar(8) 영업일자 ' '
	private String vhpdate; // varchar(8) 운송계획일자 'to_char(current_timestamp(),''YYYYMMDD'')'
	private String vhptime; // varchar(6) 운송계획시간 'to_char(current_timestamp(),''HH24MISS'')'
	private String stpitdt; // varchar(8) 운송출발일 NULL
	private String stpittm; // varchar(6) 운송출발시간 NULL
	private String useract; // varchar(60) 기사 계정ID ' '
	private String retodyn; // varchar(1) 반품 회수지시여부 'N'
	private String tclosyn; // varchar(1) 운송마감 여부 'N'
	private String plnstat; // new 운송상태 컬럼
	private String trnmemo; // varchar(1000) 배차메모 NULL
	private String pcarscd; // varchar(10) 운송취소 사유코드 ' '
	private String pcarsnm; // varchar(1000) 운송취소 사유내용 ' '
	private String pcadate; // varchar(8) 운송취소 일자 ' '
	private String pcatime; // varchar(6) 운송취소 시간 ' '
	private String pcauser; // varchar(60) 운송취소 사용자 ' '
	private String trfindt; // varchar(8) 운송완료일 NULL
	private String emerscd;
	private String emerdnm;
	private String trfintm; // varchar(6) 운송완료시간 NULL
	private String pbillyn; // varchar(1) 개별운송비발생여부 'N'
	private int pbilpay; // int(11) 개별운송비용 0
	private String shtrtky; // varchar(10) 셔틀노선 키 NULL
	private String trareky;
	private String credate; // varchar(8) 생성일자 'to_char(current_timestamp(),''YYYYMMDD'')'
	private String cretime; // varchar(6) 생성시간 'to_char(current_timestamp(),''HH24MISS'')'
	private String creuser; // varchar(60) 생성사용자 ' '
	private String lmodate; // varchar(8) 수정일자 ' '
	private String lmotime; // varchar(6) 수정시간 ' '
	private String lmouser; // varchar(60) 수정사용자 ' '
	private String indibzl; // varchar(1) biz logic indicator ' '
	private String indiarc; // varchar(1) Archive indicator ' '
	private int updtchk; // int(11) Update check 0

	// join값들
	// TPLNIT
	private int vhplnit; // int(11) 운송계획 아이템 0
	private String destkey; // varchar(20) 도착지 Key ' '
	private String truntyp;
	private String trunpid;
	private String copodky;
	private String copodit;
	private String cooutky; // varchar(50) 수주번호 ' '
	private int cooutit; // int(11) 수주아이템번호 0
	private String destord;
	private String shtrpty;
	private String shtrsts;
	private String oeretky;
	private int oeretit;
	private String eoasnky;
	private int eoasnit;
	private String rcvdcky;
	private int rcvdcit;
	private String refsodc;
	private String refsoit;
	private String refdnit;
	private String refdndc;
	private String refsddc;
	private String refsdit;
	private String repitdt;
	private String repittm;
	private String rsncode;
	private String rsnremk;
	private int tritqty;

	// TPLFIT
	private int ftftkno;
	private String sigsvip;
	private String sigflph;
	private String sigflnm;
	private String picsvip;
	private String picflph;
	private String picflnm;

	// MDOCMA
	private String douseyn; // 사용여부

	// MDESMA
	private String ptnrkey; // varchar(20) 협력업체키 ' '
	private String denamlc; // varchar(60) 도착지 명칭 ' '

	// MWARMA
	private String whnamlc; // varchar(60) 창고명칭 ' '

	// MOWRAM
	private String ownamlc;

	// MPTNMA
	private String ptnamlc; // 고객명

	// TVHCMA
	private String vhcsnam; // varchar(20) 차량명 ' '
	private String carrier; // varchar(20) 운송사코드 ' '
	private String vhctncd; // varchar(10) 차량톤수
	private String vhcfnam; // 차량번호 추가

	// SUSRMA
	private String usernam; // varchar(60) 사용자이름 ' '

	// OCOSAL
	private String trnstat; // varchar(10) 운송상태
	private String oubstat; // varchar(10) 출고처리상태 'NEW'
	private String salstat; // varchar(10) 수주처리상태 'NEW'
	private String saltime;
	private String skudesc;
	private String saldate;
	private String towarky;
	private String custkey;
	private String ownerky;
	private String skumkey;
	private String skuskey;
	private String soitqty;
	private String suomkey;
	private String saatn01;
	private String saatn02;
	private String saatn03;
	private String saatn04;
	private String saatn05;
	private String saatc01; // varchar(10) 수주속성C01 고유서열번호 ' '
	private String saatc02;
	private String saatc03;
	private String saatc04;
	private String saatc05; // varchar(20) 수주속성C05 차종 ' '
	private String saatc06;
	private String saatc07;
	private String saatc08;
	private String saatc09;
	private String saatc10;


	// TSHRHD
	private String shtrtnm; // varchar(60) 셔틀노선명 ' '

	// TSHRVH
	private String shtvcap;

	// TAREHD
	private String trarenm; // 권역명칭 join
	
	//OCATMA
	private String scatecd;
	private String scatenm;
	private String mcatecd;
	private String mcatenm;

	// 커스텀
	private String ownernm; // 화주이름
	private int pltcnts; // PLT 수
	private String saatc10min; // 서열min
	private String saatc10max; // 서열max
	private String postdatFrom; // 영업일 검색데이터
	private String postdatTo; // 영업일 검색데이터
	private List<String> plnstatList; // 검색시 운송상태 체크리스트
	private List<McodemDTO> plnstatCheckBoxes; // 운송상태 체크박스리스트
	private List<TMDispatchDTO> tplnhdUpdateList; // tma11 운송상태 수정 헤더리스트
	private List<TMDispatchDTO> tplnitUpdateList; // tma11 운송상태 수정 아이템리스트
	private List<McodemDTO> shtrstsList; // 셔틀상태리스트
	private String signiture; // 인수서명서버파일
	private String picture; // 인증사진서버파일
	private MultipartFile uploadPic; // 사진 업로드 파일
	private MultipartFile uploadSig; // 인증 사진 업로드 파일
	private String pageId; // 프로그램별 조회기능 분기처리

	// 커스텀 : trfin 일별 집계 날짜 컬럼
	private int postday01; // 1일
	private int postday02; // 2일
	private int postday03; // 3일
	private int postday04; // ..
	private int postday05;
	private int postday06;
	private int postday07;
	private int postday08;
	private int postday09;
	private int postday10;
	private int postday11;
	private int postday12;
	private int postday13;
	private int postday14;
	private int postday15;
	private int postday16;
	private int postday17;
	private int postday18;
	private int postday19;
	private int postday20;
	private int postday21;
	private int postday22;
	private int postday23;
	private int postday24;
	private int postday25;
	private int postday26;
	private int postday27;
	private int postday28;
	private int postday29;
	private int postday30; // ..
	private int postday31; // 31일
	private int totalCount; // 총합계

	// 커스텀
	private int tpupchk;// TPLNIT updtchk
	private int ocupchk;// OCOSAL updtchk
	private int tdupchk;// TPLNHD updtchk
	private String mintc10; // min(saatc10)
	private String maxtc10; // max(saatc10)
	private String shtrstsOrigin;
	private int sumvehi; //배차키 총합
	private String plnstatOrigin; // tma11 운송상태 수정전 데이터
	private String tfcretm; // tma11 인증시간 커스텀
	private String doctynm; // tma10 문서유형 매핑
	private String progrid; // 페이지 아이디 변수, tma08 배차취소 시 doctype 830 제외할 파라미터
	private List<TMDispatchDTO> ocatmaList;
	private String saatc01min;
	private String saatc03min;
	private String saatc05min;
	private String saatc08min;
}