package com.logistics.bm.domain;

import java.util.List;

import com.logistics.common.dto.CommonDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BillingDTO extends CommonDTO{
	
	/* 
	 * ******************************************** 
	  - DTO Name   : BillingDTO
	  - Description    : 정산에 필요한 필드 모아둔 DTO
	  - Made By        : 고은별
	  - Creation Date  : 2023.07.19
	  ------------------------------------------
	 
	* ********************************************  
	*/
	

	private String compkey; // varchar(20) 회사
	private String warekey; // varchar(20) 창고
	private String blhdcky;	//정산문서
	private String blhyymm; //정산년월

	private String blsdate;	//정산대상시작일자
	private String bledate;	//정산대상종료일자
	private String lcddate;	//정산마감일시
	private String lcduser;	//정산마감사용자
	private String lcvrfyn;	//정산검증여부
	private String lcvdate;	//정산검증일시
	private String lcvuser;	//정산검증사용자
	private String lccnfyn;	//정산확정여부
	private String lccdate;	//정산확정일시
	private String lccuser;	//정산확정사용자

	private String bflcode;	//고정비요율코드
	private String bvlcode;	//변동비요율코드
	private String bplcode;	//택배비요율코드

	private String ownerky; //화주
	private String ptnrkey; //대금구분업체
	private String blrpmgb; //대금구분
	private String blrcsgb; //계정
	private String blrtkgb; //sub계정
	private String blrunit; //단위
	private int blrtqty;	//내품수량(이하)
	private String doccate; //문서유형
	private String doctype; //문서타입
	private int refcqty;    //수량
	private int blrcost;	//단가(원)
	private int blrsutx;	//부가세(%)
	private int blramot;    //공급가액
	private String blrtknm; //명세표구분명
	private String blrremk;	//비고

	private int blhdcit;	//정산문서 아이템
	private String blitype;	//데이터그룹방식
	private String postdat;	//영업일자
	private String skumkey;	//SKU
	private String skudesc;	//SKU명
	private String suomkey;	//문서실적단위
	private String rcvdcky;	//입고문서
	private int rcvdcit;	//입고문서 아이템
	private String taskoky;	//작업문서
	private int taskoit;	//작업문서 아이템
	private String shpdcky;	//출고문서
	private int shpdcit;	//출고문서 아이템
	private String vhplnky;	//운송계획
	private int vhplnit;	//운송계획 아이템
	private String cooutky;	//수주번호
	private int cooutit;	//수주아이템번호
	private String invoiky;	//송장번호
	private int invoiit;	//송장아이템번호

	// customize
	private String blhyymmfrom;	//정산년월From
	private String blhyymmto;	//정산년월To
	private String blrstat;		//정산상태
	private int blramtx;		//세액
	private int blrsumy;		//합계
	private String ownamlc;		//화주명
	private String ptnamlc;		//대금구분업체명
	private String blrpmnm;		//대금구분명
	private String destkey;     //도착지
	private String btrstat; 	//정산상태

	//운송비 관련 data
	private String bthyymmfrom; //운송비정산년월From
	private String bthyymmto;   //운송비정산년월To
	private String startdate; 	//시작일자
	private String lastdate;
	private int trpctmt;		//회당운임비
	private int dftctmt;		//기본운임비
	private int ovtctmt;		//특근비
	private int idactmt;		//산재비
	private String denamlc;     //도착지
	private String vhcfnam;     //차량번호
	private String useract;     //기사명
	private int sumctmt;		//합계
	private String btrpmnm;     //대금구분명

	//btgthd
	private String bthdcky; //정산문서번호
	private String bthyymm; //정산년월
	private String btsdate; //정산대상시작일자
	private String btedate; //정산대상종료일자
	private String tcddate; //정산마감일시
	private String tcduser; //정산마감사용자
	private String tcvrfyn; //정산검증여부
	private String tcvdate; //정산검증일시
	private String tcvuser; //정산검증사용자
	private String tccnfyn; //정산확정여부
	private String tccdate; //정산확정일시
	private String tccuser; //정산확정사용자
	private String credate; //생성일자
	private String cretime; //생성시간
	private String creuser; //생성자
	private String lmodate; //수정일자
	private String lmotime; //수정시간
	private String lmouser; //수정자
	private String indibzl; //Business logic indicator
	private String indiarc; //Archive indicator
	private int updtchk; //Update check

	//btgtit
	private String btrpmgb; //대금구분
	private String btrcsgb; //계정
	private String btrtkgb; //sub계정
	private String btrunit; //단위
	private String bftcode; //고정운송요율코드
	private int btrcost; //단가(원)
	private int btramot; //공급가액
	private int btramtx; //세액
	private int btrsumy; //합계
	private String btrtknm; //명세표 구분명
	private String btrremk; //비고
	private String btitype; //데이터 그룹방식
	private String mdestky; //도착지
	private int bthdcit; 	//정산문서 아이템

	//bvtrma
	private String bvtcode; //변동운송요율코드
	private String shtrtky; //*셔틀노선키
	private String vehicky; //차량코드
	private int btrsutx; //부가세
	private String btrdtgb; //기준일구분
	private String btrmfgb; //월구분From
	private String btrcfdt; //기준일From
	private String btrdate; //적용시작년월
	
	//bftdtl 월/차량별 공제액
	private String bftdtky; //HEAD 번호
	private int bftdtit; //ITEM 번호
	private int btmitct; //관리비
	private int btprmct; //보험료
	private int btvatct; //부가세
	private int btenvct; //환경세
	private int btcarct; //자동차세
	private int btoduct; //기타공제
	private int btdpsct; //예치금
	private int btcvict; //종합소득세
	private int btiaict; //산재보험료

	//tplnhd
	private String vhpdate; //배차계획일자
	private String vhptime; //배차계획시간
	
	// Procedure Outparam
	private String omsgkey;
	private int oresult;

	// data
	private List<BillingDTO> etcList; // 검증 완료 중 추가할 데이터
	private List<BillingDTO> updateBlrremkList; //검증완료에서 수정된 비고
	private List<BillingDTO> updateBtrremkList; //운송비검증완료에서 수정된 비고
	private String btrcsnm; //운송비검증처리에서 보여줄 계정명칭
	private String ownernm; //기사명
}
