package com.logistics.tm.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.logistics.common.dto.CommonDTO;
import com.logistics.md.domain.MdesmaDTO;
import com.logistics.md.domain.MptnmaDTO;
import com.logistics.md.domain.MwarmaDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TMAreaDTO extends CommonDTO{
	
	/* 
	 * ******************************************** 
	  - DTO Name       : TMAreaDTO
	  - Description    : TM 권역, 마스터관리에 필요한 필드들을 모아둔 DTO
	  - Made By        : 최재환
	  - Creation Date  : 2023.09.06
	  ------------------------------------------
	 
	* ********************************************  
	*/

	// TAREHD
	private String rowkey;
	private String warekey;
	private String trareky;
	private String tragrnm;
	private String trarenm;
	private String trvehky;
	private String tramemo;
	private String truseyn;
	private String credate;
	private String cretime;
	private String creuser;
	private String lmodate;
	private String lmotime;
	private String lmouser;
	private String indibzl;
	private String indiarc;
	private int updtchk;

	// TAREIT
	private int trareit;
	private String postcod;

	// TPLNHD
	private String compkey; // varchar(20) Company Key ' '
	private String vhplnky; // varchar(10) 운송계획 키 ' '
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
	private String tclosyn;

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
	private int destord;
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
	private String skudesc;
	private String custkey;
	private String ownerky;
	private String skumkey;
	private String skuskey;
	private String soitqty;
	private String suomkey;
	private int saatn01;
	private int saatn02;
	private int saatn03;
	private int saatn04;
	private int saatn05;
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

	// join Data

	// OCOSAL
	private String saldate;
	private String saltime;
	private String vendkey;
	private String salmemo;
	private String salrqdt;
	private String salrqtm;
	private int shcfqty;
	private int diffqty;
	private String dctctyp;
	private String rsndesc;
	private String ordgr01;
	private String ordgr02;
	private String ordgr03;
	private String closlyn;
	private String clodate;
	private String clotime;
	private String clouser;
	private String bthdcky;
	private int bthdcit;
	private String oubstat;
	private String towarky;
	private int rgitqty;
	private String trnstat;

	// TVHCMA
	private String vhcsnam;
	private String vcaltyp;
	private String vehtpyn;
	private String vhcfnam; //차량번호

	// MPTNMA
	private String ptnrkey;
	private String ptnamlc;

	// MOWRMA
	private String ownamlc;
	private String owuseyn;

	// MDESMA
	private String denamlc;
	private String routeky;
	private String deposcd;

	// MDOCMA
	private String doctynm;
	private String docctnm;

	// VIEW MPOSTM
	private String nzipcd; // 신우편번호
	private String npck; // 시도한글
	private String ncak; // 시군구한글
	private String vtk; // 읍면한글
	private String strcd; // 도로명코드
	private String strnk; // 도로명한글
	private String bmyn; // 지하여부
	private String bldmn; // 건물번호본번
	private String bldsm; // 건물번호부번
	private String cabulnm; // 시군구용건물명
	private String ccadcd; // 법정동코드
	private String ccadnm; // 법정동명
	private String rinm; // 리명
	private String admnm; // 행정동명
	private String sannm; // 산여부
	private String rdnm; // 지번본번
	private String vtdnm; // 읍면동일련번호
	private String rdsnm; // 지번부번
	private String ozipcd; // 구우편번호
	private String odtadrk; // 상세주소한글
	private String actyn; // 사용여부

	// OCODMA
	private String cartype;
	private String cartynm;

	// TVHENT
	private String entstat;
	private String entuser;
	private String entrqdt;
	private String entrqtm;
	private String entepdt;
	private String enteptm;
	private String entpmdt;
	private String entpmtm;
	private String entavdt;
	private String entavtm;
	private String entperm;

	// TPLFIT
	private int ftftkno;
	private String picsvip;
	private String picflph;
	private String picflnm;

	// OCATMA
	private String scatecd; // varchar(20) NOT NULL DEFAULT ' ' COMMENT '서브아이템 카테코드'
	private String scatenm; // varchar(60) NOT NULL DEFAULT ' ' COMMENT '서브아이템 카테명칭'
	private String mcatecd; // varchar(20) NOT NULL DEFAULT ' ' COMMENT '메인아이템 카테코드'
	private String mcatenm; // varchar(60) NOT NULL DEFAULT ' ' COMMENT '메인아이템 카테명칭'
	private String causeyn; // varchar(1) NOT NULL DEFAULT 'Y' COMMENT '대표카테사용여부'

	// custom
	private String ccadnmAdmnmVtk; // ccadnm + admnm + vtk 읍면동 데이터 검색 컬럼
	private String bldmnBldsm; // bldmn + bldsm 건물번호 본번 + 부번 데이터
	private String rdnmRdsnm; // rdsm + rdsnm 지번주소 본번 + 부번 데이터
	private String ccadnmVtk; // ccadnm, vtk 읍면동 데이터
	private String regichk; // 권역설정여부 'Y', or 'N
	private String whnamlc; // 창고명칭 화면용
	private String groupnm; // 그룹명칭 화면용
	private int destcnt; // 권역키별 도착지 개수
	private int pltcnt; // 권역키별 plt 개수
	private String postdatFrom; // 영업일 검색조건
	private String postdatTo; // 영업일 검색조건
	private String saatc05before;
	private String saatc05after;
	private String oubstnm; // 출고상태 value text
	private List<MptnmaDTO> customerList;
	private String thcredt;
	private String thcretm;
	private String thcreus;
	private String thlmodt;
	private String thlmotm;
	private String thlmous;
	private int thupdat;
	private String ticredt;
	private String ticretm;
	private String ticreus;
	private String tilmodt;
	private String tilmotm;
	private String tilmous;
	private int tiupdat;
	private String areakey; // 도착지마스터의 우편번호로 매핑된 임시 권역키
	private List<String> vcaltyps; // 차량조회시 차량정산타입추가
	private String picmemo; // tmm13 인증메모 조회(입차승인메모와 구분)
	private String picture; // 사진 조회시 경로+파일명 데이터
	private String creusnm; // tmm13 생성사용자 usernam
	private String lmousnm; // tmm13 수정사용자 usernam
	private String entusnm; // tmm13 입차요청자 usernam
	private String enpusnm; // tmm13 입차승인자 usernam
	private List<String> entstatList; // 검색시 입차승인상태 리스트
	private List<TMAreaDTO> tvhentList; // 입차승인시 입차목록
	private String trarenmOrigin; // 권역명칭 수정전 데이터 TMM06 그리드 내부 정보
	private String vhcsnamOrigin; // tma15 권역배차 모달 차량명 기존데이터
	private String progrid; // 쿼리 분기용 프로그램 id
	private int skumkeyCnt; // tma15 권역배차 상품수 컬럼
	private List<String> deposcdList; // tma15 권역배차 모달 검색 시 도착지 우편번호리스트
	private String cdcate1; // V_MCODEM 시도
	private String comcdtx; // V_MCODEM 시군구
	private String fromvhplnky; // tma05 권역변경 변경 전 배차번호
	private int fromvhplnit; // tma05 권역변경 변경 전 배차아이템
	private String tovhplnky; // tma05 권역변경 변경할 배차번호
	// Procedure Outparam
	private String omsgkey;
	private int oresult;
	private String scatecdOrigin; // tmm08 서브아이템 카테코드 수정 전 데이터
	
	//init custom
	private List<TvhcmaDTO> vehiList;
	private List<MwarmaDTO> warehouseList;
	private List<TMAreaDTO> destkeyList;
	private List<TMAreaDTO> ownerList;
	private List<TMAreaDTO> doctypeList;
	private List<TMAreaDTO> doccateList;
	private List<TMAreaDTO> plnstatList;
	private List<TMAreaDTO> trarekyList;
	private List<TMAreaDTO> ocatmaList;
	private List<MdesmaDTO> destList;
	private List<TMOrderDTO> custkeyList;
}