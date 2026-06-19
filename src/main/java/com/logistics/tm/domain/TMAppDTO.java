package com.logistics.tm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.logistics.common.dto.CommonDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
// import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TMAppDTO extends CommonDTO {

	private String compkey;
	private String vhplnky;
	private String warekey;
	private String vehicky;
	private String doccate;
	private String doctype;
	private String postdat;
	private String vhpdate; // 계획
	private String vhptime;
	private String useract;
	private String retodyn;
	private String plnstat; // 운송상태 << procedure
	private String trnmemo; // 배송메모
	private String retmemo; // 회수메모

	private String stpitdt; // 운송시작일자 추가 : 2023 08 18
	private String stpittm; // 운송시작시간 추가 : 2023 08 18
	private String trfindt;
	private String trfintm;

	private String emerscd; // 긴급사유코드
	private String emerdnm; // 긴급 메모
	private String pcarscd; // 운송취소사유코드
	private String pcarsnm; // 운송취소 메모
	private String pcadate; // 운송취소 날짜
	private String pcatime; // 운송취소 시각
	private String pcauser;
	private String pbillyn;
	private int pbilpay;
	private String shtrtky; // 셔틀노선키
	private String trareky; // 권역키

	// item
	// private String compkey;
	// private String vhplnky;
	private int vhplnit;
	// private String warekey;
	private int destord;
	private String custkey;
	private String destkey;
	private String shtrsts;
	private String ownerky;
	private String skumkey;
	private String skudesc;
	private String skuskey;
	private int soitqty;
	private int tritqty;
	private String suomkey;
	private String truntyp;
	private String trunpid;
	private int saatn01;
	private int saatn02;
	private int saatn03;
	private int saatn04;
	private int saatn05;
	private String saatc01;
	private String saatc02;
	private String saatc03;
	private String saatc04;
	private String saatc05;
	private String saatc06;
	private String saatc10min;
	private String saatc10max;
	private String saatc05min;
	private String saatc07;
	private String saatc08;
	private String saatc09;
	private String saatc10;
	private String saatc11;
	private String saatc12;
	private String saatc13;
	private String saatc13not;
	// private String saatc14;
	private String shtrpty;
	private String repitdt;
	private String repittm;
	private String rsncode; // 보고 사유코드 << 필요..?
	private String rsnremk;
	private String copodky;
	private String copodit;
	private String cooutky;
	private int cooutit;
	private String oeretky;
	private int oeretit;
	private String eoasnky;
	private int eoasnit;
	private String rcvdcky;
	private int rcvdcit;
	private String refsodc;
	private String refsoit;
	private String refdndc;
	private String refdnit;
	private String refsddc;
	private String refsdit;

	// tplfit
	private int ftftkno;
	private String hotakty;
	private String targetfileip;
	// private String targetfilepath;
	private String filepath;
	// private String targetfilename;
	private String filename;
	private String fittype;

	private String sigsvip;
	private String sigflph;
	private String sigflnm;

	private String picsvip;
	private String picflph;
	private String picflnm;

	// ocosal
	private String salstat;
	private String trnstat;

	// TVLOHI
	private int vhlogsq;
	// private String repitdt;
	// private String repittm;
	private double vhlolat; // DECIMAL(15,10) - 위도
	private double vhlolon; // DECIMAL(15,10) - 경도

	// TVHENT : 입차승인요청

	private String entstat; // 입차차량상태
	private String entuser; // 입차요청자
	private String entrqdt; // 입차요청 일 / 시각
	private String entrqtm;
	private String entepdt; // 입차예정 일 / 시각
	private String enteptm;
	private String entperm; // 입차승인자
	private String entpmdt; // 입차승인 일 / 시각
	private String entpmtm;
	private String entavdt; // 입차허용 일 / 시각
	private String entavtm;

	// private MultipartFile file;
	// private String file;

	private String usernam;

	// for ocosal and ocopur
	private String loguser;
	// private String doctype;
	private String[] doctype_list;
	private String postdat_from;
	private String[] postdat_in;
	private String towarky;
	// private String
	private String bthyymm; // YYYYMM

	// for list search
	private String[] vhplnky_list;
	private String[] vhplnit_list;

	private int destlimit;
	private int offset;

	// for dest info
	private String denamlc;
	private String denamko;
	private String denamen;

	// for ware info
	private String wanamlc;
	private String whnamko;
	private String wanamen;

	// for mcodem : vhctype
	private String vhctypename;

	// partner name
	private String ptnamlc;
	private String ptaddr1;
	private String ptaddr2;
	private String ptaddr3;

	// for pagination
	private int offsetnumber;
	private int limitnumber;

	// for timal search
	private String targetvhpdate; // past 과거 today오늘 future 미래
	private String targetpostdat; // past 과거 today오늘 future 미래

	private String startdate;
	private String enddate;

	private String vhcsnam;
	private String comcdtx;

	// for or search
	private String vehickyornot;
	private String[] vehicky_list;
	private String useractornot;
	private String[] shtrtky_list;
	private String[] shtrtky_list_ornot;
	private String groupby;

	// for plnstat search
	private String targetstate; // ongoing done reservation

	// for ware info
	private String waaddr1;
	private String waaddr2;
	private String waaddr3;

	// for shuttle info
	private String shtrtnm;

	// for trareky info 권역
	private String trarenm;

	// for procedure
	private String omsgkey;
	private int oresult;
	private String ovhplnk;

	private String vendkey;
	private String truckno;
	private String tructon;

	// private String copodky;
	// private String eoasnky;

	// 실적등록
	private int tripcnt;

	// for OCOSAL
	private String saldate; // varchar(8) 수주일자
	private String saltime; // varchar(6) 수주시간
	private String salmemo; // varchar(2000) 고객 요구사항
	private String salrqdt; // varchar(8) 고객 도착요청일
	private String salrqtm; // varchar(6) 고객 도착요청시간
	private int rgitqty; // int(11) 출고지시 수량
	private int shcfqty; // int(11) 출고완료 수량
	private int diffqty; // int(11) 출고부족 수량
	private String dctctyp; // varchar(10) 주문유형
	private String rsndesc; // varchar(1000) 사유상세
	private String inbstat; // varchar(10) 입고처리상태
	private String oubstat; // varchar(10) 출고처리상태
	private String ordgr01; // varchar(100) 오더사용자구분1
	private String ordgr02; // varchar(100) 오더사용자구분2
	private String ordgr03; // varchar(100) 오더사용자구분3
	private String closlyn; // varchar(1) CloseOrder여부
	private String clodate; // varchar(8) CloseOrder일자
	private String clotime; // varchar(6) CloseOrder시간
	private String clouser; // varchar(60) CloseOrder사용자

	private String credate; // varchar(8) 생성일자
	private String cretime; // varchar(6) 생성시간
	private String creuser; // varchar(60) 생성사용자
	private String lmodate; // varchar(8) 수정일자
	private String lmotime; // varchar(6) 수정시간
	private String lmouser; // varchar(60) 수정사용자


	// 
	private String lasttim; // 최신날짜
}
