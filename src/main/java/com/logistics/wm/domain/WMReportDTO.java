package com.logistics.wm.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.logistics.common.dto.CommonDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WMReportDTO extends CommonDTO{
	
	/* 
	 * ******************************************** 
	  - DTO Name       : WMReportDTO
	  - Description    : WMReportDTO - WM 리포트 출력을 위해 필요한 필드들을 담은 DTO
	  - Made By        : 하주영
	  - Creation Date  : 2023.08.09
	  ------------------------------------------
	 
	* ********************************************  
	*/
	
	//재고실사지시서
	private String physoky;	//실사문서번호
	private String locakey; //location
	private int systqty; //수량
	private int physqty;	//실사수량
	private List<String> physoitList; //실사문서번호 아이템
	
	//피킹지시서
	private String cooutky;	//수주오더번호
	private String shpdcky; //출고문서번호
	private String allgrky;	//할당그룹키
	private String oeretky;	//반출오더번호 - OM 반출오더
	private String picktyp;	//피킹유형 - 출고작업유형
	private String doccate;	//문서유형
	private String doctype;	//문서타입
	private String zonenam;	//ZONE - Zone Name
	private String locanam;	//Location - Location Name
	private String shppday;	//출고일
	private String tasksts;	//작업상태
	private String[] orhdkey; // 오더 번호
	
	// 납품지시서
	private String destkey; //도착지
	private String denamlc; //도착지명
	private int tcmpqty;    //출고완료수량
	private int shpdcit;    //출고문서아이템
	private String[] grhdkey; // 할당그룹키
	private String[] oritkey; // 수주번호키
	private String dshdkey;
	private String qrcdkey;
	private String shpitst;
	private String shpdcst; 
	
	//적치지시서
	private String taskoky;	//작업문서번호 - 작업문서
	private String frlocky;	//Fr 로케이션 - From Loc
	private String tozonky;	//To 존 - To Zone
	private String tolocky;	//To 로케이션 - To Loc
	private int taskoit;	//작업문서 아이템
	private List<Integer> taskoitList;	//작업문서 아이템 번호 List
	
	//팔레트라벨
	private String trunpid;	//Transfer unit ID
	private String truntyp;	//Transfer unit type
	private String trunnam;	//이동용기명칭
	private List<String> trunpidList;	//Transfer unit ID 리스트
	
	//공통
	private int skuncnt;	//품목수
	private int rownum; //rownum
	private String compkey;	//회사키
	private String warekey;	//창고키
	private String progrid;
	private String user;
	private String printday;	//인쇄일
	private String skumkey; //부품코드
	private String skudesc; //부품명
	private String suomkey; //uom
	private int stotqty;	//총품목수
	private int fordqty;	//수량
	private String fuomeky;	//단위
	private String upccode;	//바코드
	private String ownerky; //화주
	private int printcount;	//프린트 카운트
	private String dochkey;
	private int docitem;
	private int printsq;
	private String loguser;
	private String type; //구분 type
	
	private String boxcnt;
	private String[] allgrkyArr;
}
