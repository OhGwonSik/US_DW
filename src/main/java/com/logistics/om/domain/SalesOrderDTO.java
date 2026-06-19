package com.logistics.om.domain;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.logistics.common.dto.CommonDTO;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.domain.MdocmaVO;
import com.logistics.md.domain.MowrmaDTO;
import com.logistics.md.domain.MptnmaDTO;
import com.logistics.md.domain.MrscmaDTO;
import com.logistics.md.domain.MskuwcDTO;
import com.logistics.pt.MdesmaVO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesOrderDTO extends CommonDTO {
	
	//OM 고객 수주오더 - OCOSAL
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
	
	//20230712 커스텀
	private List<String> salstatList;
	private List<String> tmpDoctypeList;
	private String state;
	private String warekey;
	private List<Map<String,Object>> dctctypList;
	private List<MptnmaDTO> custkeyList;		//고객
	private List<MordmaDTO> doctypeList;	//문서타입
	private List<MskuwcDTO> skuwcList;		//부품
	private List<String> skumkeys;
	private List<CommonDTO> rsncodeList;
	private List<MdesmaVO> destkeyList;
	private List<CommonDTO> ownerkyList;
	private int packbox;
	
	// 20230802 회수오더관련 추가 (SMA)
	private List<MrscmaDTO> returnCodeList;		// 회수사유코드 
	private MdocmaVO returnDoctypeList;			// 회수문서타입
	private String rsncdnm;
	
	
	//MPTNMA
	private String ptnrkey;
	private String ptnrtyp;
	
	//MPAKMA
	private int packqty;
	private String truntyp;
			
	//WSHPIT
	private String shpdcky;		//출고문서 번호
	private int shpdcit;		//출고문서 아이템
	private String shpdcst;		//출고문서 상태
	private String shpitst;		//출고문서 아이템 상태
	private String picktyp;		//출고작업 유형
	
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
	
	// 커스텀 변수
	private String rowkey;
	private String rcvwhky;	//입고창고<커스텀>
	private String custnam;	//도매명 <커스텀>
	private String retanam;	//소매명 <커스텀>
	private int mesoqty;    //미송수량 <커스텀>
	
	private int do230cn;
	private int do290cn;
	private String docctnm;
	private String doctynm;
	private String coocate;
	private String ococtnm;
	private String cootype;
	private String cootynm;
	private int tsoiqty;	//주문총수량, 상품 주문수량
	private int abalqty;	//물류재고, 상품 창고수량
	private int wbalqty;	//매장재고, 상품 매장수량
	
	private int s210qty;	//일반수량
	private int s220qty;	//무상수량
	private int s230qty;	//샘플수량
	private int s240qty;	//반품수량
	private int s250qty;	//미송수량
	private int s260qty;	//샘플회수수량
	private int s280qty;	//매장판매수량
	private int discamt;	//할인금액
	private int cashamt;	//현금입금
	private int bankamt;	//통장입금
	
	//oms04
	private String postdateFrom;	//영업일자
	private String postdateTo;
	
	//oms10
	private String saldttm;	//납품일시
	private int stotqty;
	private String saldateFrom;
	private String saldateTo;
	private List<String> salstats;
	private List<String> cootypes;
	private List<String> inbstats;
	private List<String> oubstats;
	private List<String> trnstats;
	private List<McodemDTO> mcodemList;		
	private List<McodemDTO> inbstatList;	
	private List<MordmaDTO> cootypeList;
	private List<McodemDTO> oubstatList;	
	private List<McodemDTO> trnstatList;
	
	private int untrqty;	//미송수량, 상품 미송수량
	private int asnqty0;	//오늘 입고예정
	private int asnqty1;	//내일 입고예정
	
	private int taksqty;	//회수수량
	private int oms4210;	//매출전환수량
	private int oms4260;	//샘플회수수량
	private int oms4320;	//샘플반출수량
	
	private int itemcnt; 	//상품수
	private int takeqty; 	//인계수량
	private int takcqty; 	//완료수량
	
	private String takcchb;	//인수완료 제외
	private String takcehb;	//영수미송
	
	private String payorty;	//결재방식 OCOSAH
	private String stempyn;	//보류여부
	
	// OMS6 미송관리
	private String cmpltyn; //발송여부
	private int execqty; //발송수량
	private String rmkcoor; //비고
	private int cnclqty; //발송취소수량
	
	private int ordtamt;	//거래액
	private String gbntext;	//상태
	
	// 공통
	private String cooutxt; //전표코드번호

}
