package com.logistics.wm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WshpitVO {
	//WSHPIT
	private String rowkey;		//rowkey 조합
	private String compkey;		//varchar(20) 	Company Key
	private String shpdcky;		//varchar(10) 	출고문서 번호
	private int	   shpdcit;		//int(11) 		출고문서 아이템
	private String warekey;		//varchar(20)	Warehouse Key
	private String doccate;		//varchar(10)	Document Category
	private String doctype;		//varchar(10)	Document Type
	private String shpdcst;		//varchar(10)	출고문서 상태
	private String shpitst;		//varchar(10)	출고문서 아이템 상태
	private String picktyp;		//varchar(20)	출고작업유형
	private String ownerky;		//varchar(20)	화주
	private String skuskey;		//varchar(20)	대표 SKU
	private String skumkey;		//varchar(50)	출고 상품
	private String skudesc;		//varchar(100)	출고 상품명
	private String shppday;		//varchar(8)	출고 예정일
	private String shpptim;		//varchar(6)	출고 예정시간
	private String shpsday;		//varchar(8)	출고 시작일
	private String shpstim;		//varchar(6)	출고 시작시간
	private String spehdat;     //varchar(8)	출고 완료일
	private String spehtim;		//varchar(14)	출고완료TIME
	private String speitim;		//varchar(14)	출고아이템완료TIME
	private String saldate;		//varchar(8)	수주일자
	private String custkey;		//varchar(20)	수주처 매장
	private String retakey;		//varchar(20)	소매
	private String salmemo;		//varchar(2000)	소매 요구사항
	private String salrqdt;		//varchar(8)	소매 도착요청일
	private String salrqtm;		//varchar(6)	소매 도착요청시간
	private int	   shcuqty;		//int(11)		주문 수량
	private int	   rgitqty;		//int(11)		출고지시 수량
	private int	   shalqty;		//int(11)		할당 수량
	private int	   shpkqty;		//int(11) 		피킹 수량
	private int	   shdvsqt;		//int(11)		분배 예정수량
	private int	   shdveqt;		//int(11)		분배 완료수량
	private int	   shcfqty;		//int(11) 		완료 수량
	private String suomkey;		//varchar(10)	기본 UOM
	private String paytype;		//varchar(10)	결재방식
	private String dctctyp;		//varchar(10)	DC/TC 구분
	private String allgrky;		//varchar(20)	할당그룹키
	private String movteam;		//varchar(10)	작업 조(AutoStore CATEGORY)
	private String refpodc;		//varchar(10)	ref ERP PO
	private String refpoit;		//varchar(10)	ref ERP PO Item
	private String refsodc;		//varchar(10)	ref ERP SO
	private String refsoit;		//varchar(10)	ref ERP SO Item
	private String refdndc;		//varchar(10)	ref ERP DN
	private String refdnit;		//varchar(10)	ref ERP DN Item
	private String refsddc;		//varchar(10)	ref ERP SD
	private String refsdit;		//varchar(10)	ref ERP SD Item
	private String destkey;		//varchar(10)	ref ERP SD Item
	
	private String credate;     //varchar(8)        생성일자
	private String cretime;     //varchar(6)        생성시간
	private String creuser;     //varchar(60)       생성사용자
	private String crename;		//생성자 이름
	
	private String lmodate;     //varchar(8)        수정일자
	private String lmotime;     //varchar(6)        수정시간
	private String lmouser;     //varchar(60)       수정사용자
	private String lmoname;		//수정자 이름
	
	private String indibzl;     //varchar(1)        biz logic indicator
	private String indiarc;     //varchar(1)        Archive indicator
	private int	   updtchk;     //int(11)           Update check
	
	private int    fordqty2;  	//int(11) wmop3 - 상품스캔
	private int    tcmpqty2;  	//int(11) wmop3 - DAS분배
	private String shsdate;
	private String shstime;
	private String shsuser;
	private String shsname;
	
	private int    soutqty;
	private String shedate;
	private String shetime;
	private String sheuser;
	private String shename;
	private String shprscd;
	private String shprsnm;
	
	
	//통계
	private int    lockcnt;		//int
	private int    resocnt;		//int
	private int    skkycnt;		//int
	private int    ctkycnt;		//int
	private int    rtkycnt;		//int
	
	//MDOCMA
	private String doctynm;
	
	//MCUSMA
	private String custnam;
	private String retanam;
	
	//MLOCMA
	private String areakey; //Area key
	private String areanam; //Area Name<커스텀>
	private String zonekey; //Zone Key
	private String zonenam; //Zone Name<커스텀>
	private String locakey; //Location Key
	private String lodelyn; //Location Deletion YN
	private String locanam; //Location Name
	
	
	//WTAKIT
	private int	   fordqty;		//현재피킹수량
	private int	   tcmpqty;		//피킹완료수량
	private String frzonky;
	private String frlocky;
	private String skugr03;
	private String skugr04;
	private String toareky;
	private String tozonky;
	private String tolocky;
	private String tasksts;
	
	//WMOA1
	private String retname;
	private int	   refscnt;
	private String stockyn;
	private String stockct;
	private String cusname;
	private String paytnam;
	
	//WMOA2, WMOP3
	private String refagky;
	
	//WMLT3
	private String brandcd;
	
	//WMO10 출고지시등록
	private String cooutky;		//VARCHAR(50) 수주번호
	private int cooutit;		//INT(11) 수주번호 아이템

	//WMO11 할당변경
	private int tchnqty;
	
	//WMO31 부분출고처리
	private int diffqty;
	
	//WMO50 반출지시등록
	private String oeretky;		// VARCHAR(10) 반출오더번호
	private int oeretit;		// INT(11) 반출오더번호 아이템
	
	// WMO11 할당변경관리
	private String taskoky;
	private int taskoit;
	private String stkotky;
}   

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    