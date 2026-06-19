package com.logistics.om.domain;

import java.util.List;
import java.util.Map;

import lombok.Data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OcosalVO {
	
	private String rowkey;		//rowkey
	private String compkey;		//varchar(20) 			Company Key
	private String cooutky;		//varchar(40) 			주문장끼번호
	private int	   cooutit;		//int(11) 				주문아이템번호
	private String doccate;		//varchar(10)			문서유형
	private String doctype;		//varchar(10)			문서타입
	private String postdat;		//varchar(8)			영업일자
	private String salstat;		//varchar(10)			수주상태
	private String saldate;		//varchar(8)			수주일자
	private String saltime;		//varchar(6)			수주시간
	private String custkey;		//varchar(20)			고객
	private String vendkey;		//varchar(20)			입고처
	private String towarky;		//varchar(20)			출고창고키
	private String salmemo;		//varchar(2000)			고객 요구사항
	private String salrqdt;		//varchar(8)			고객 도착요청일
	private String salrqtm;		//varchar(6)			고객 도착요청시간
	private String destkey;		//varchar(20)			도착지 key  
	private String ownerky;		//varchar(20)			화주
	private String skumkey;		//varchar(50)			상품
	private String skudesc;		//varchar(100)			상품명
	private String skuskey;		//varchar(50)			대표 SKU
	private int	   soitqty;		//int(11)				주문수량
	private int	   rgitqty;		//int(11) 				출고지시 수량
	private int	   shcfqty;		//int(11)				출고완료 수량
	private int	   diffqty;		//int(11)				출고부족 수량
	private String suomkey;		//varchar(10)			기본 UOM
	private String dctctyp;		//varchar(10)			주문유형
	private String rsncode;		//varchar(10)			사유코드
	private String rsndesc;		//varchar(1000)			사유상세
	private String inbstat;		//varchar(10)			입고처리상태
	private String oubstat;		//varchar(10)			출고처리상태
	private String trnstat;		//varchar(10)			운송처리상태
	private String ordgr01;		//varchar(100)			오더사용자구분1
	private String ordgr02;		//varchar(100)			오더사용자구분2
	private String ordgr03;		//varchar(100)			오더사용자구분3
	private int saatn01;		//int(11)				수주속성N01 ProcessNo
	private int saatn02;		//int(11)				수주속성N02 GroupNo
	private int saatn03;		//int(11)				수주속성N03 CellNo
	private int saatn04;		//int(11)				수주속성N04
	private int saatn05;		//int(11)				수주속성N05
	private String saatc01;   	//varchar(10)			수주속성C01 고유서열번호
	private String saatc02;   	//varchar(20)			수주속성C02 차대번호
	private String saatc03;   	//varchar(40)			수주속성C03 ALC 코드
	private String saatc04;   	//varchar(20)			수주속성C04 ALC Index
	private String saatc05;   	//varchar(20)			수주속성C05 차종
	private String saatc06;   	//varchar(20)			수주속성C06 PalletNo
	private String saatc07;   	//varchar(10)			수주속성C07 작업영역순번
	private String saatc08;   	//varchar(100)			수주속성C08 작업영역명칭
	private String saatc09;   	//varchar(10)			수주속성C09 자동배차차량순번
	private String saatc10;   	//varchar(100)			수주속성C10
	private String closlyn;		//varchar(1)			CloseOrder여부
	private String clodate;		//varchar(8)			CloseOrder일자
	private String clotime;		//varchar(6)			CloseOrder시간
	private String clouser;		//varchar(60)			CloseOrder사용자
	private String bthdcky;		//varchar(10)			오더마감 정산번호
	private String bthdcit;		//int(11)				오더마감 정산아이템
	private String credate;		//varchar(8)			생성일자
	private String cretime;		//varchar(6)			생성시간
	private String creuser;		//varchar(60)			생성사용자
	private String lmodate;		//varchar(8)			수정일자
	private String lmotime;		//varchar(6)			수정시간
	private String lmouser;		//varchar(60)			수정사용자
	private String indibzl;		//varchar(1)			Business logic indicator
	private String indiarc;		//varchar(1)			Archive indicator
	private int	   updtchk;		//int(11)				Update check
	
	//20230712 다른 코드에서 사용중 이후 삭제 예정(ocosal 사용 X)
	private String salmode;
	private String salgrky;
	private String sallbyn;
	private String paytype;
	private int sedpric;
	private int seprice;
	private String retakey;
	private String mngwhky;
	private String spretyp;
	private String pmtsayn;
	private String pmtsadt;
	private String pmtsatm;
	private String pmtsaur;
	
	//OMS02 2023.07.10 정민경
	private String warekey;
	private String ptnrkey;
	private List<OcosalVO> ocosalData;
	private Map<String, List<OcosalVO>> data;
	
	//OMW1,6 화면처리 From, To 값
	private String frwhovl;
	private String frwhonm;
	private String towhovl;
	private String towhonm;
	private String toretak;
	private String toretnm;
	
	//OMW1, OWM3 프로시저 콜값
	private String pmtinno;
	
	//WMO1 Head
	private int	   oitmcnt;
	private int	   witmcnt;
	private int	   skukcnt;
	private int	   odqtcnt;
	private int	   rgqtcnt;
	private int	   dctccnt;
	private int	   lalbcnt;
	private int	   paytcnt;
	private String retanam;	
	
	//WMO1 Item
	private String paytypb;
	private String paytypa;
	private String skumkeb;
	private String skumkea;
	private String soitqtb;
	private String shcuqta;
	private String rgitqtb;
	private String rgitqta;
	private String sallbyb;
	private String sallbya;
	private String dctctyb;
	private String dctctya;
	private String doccatb;
	private String doccata;
	private String doctypb;
	private String doctypa;
	
	
	//OMW5
	private String docname;
	private String vendnam;
	private int	   scitqty;		//int(11) 확정수량
	private String comptyp;
	private String credafr;
	private String credato;
	private String salrqfr;
	private String salrqto;
}
