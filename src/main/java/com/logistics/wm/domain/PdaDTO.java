package com.logistics.wm.domain;

import com.logistics.common.dto.CommonDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PdaDTO extends CommonDTO{
	
	/* 
	 * ******************************************** 
	  - DTO Name       : PdaDTO
	  - Description    : PDA 기능페이지에 필요한 필드 모아둔 DTO
	  - Made By        : 오권식
	  - Creation Date  : 2023.08.04
	  ------------------------------------------
	 
	* ********************************************  
	*/
	
	//WSTKKY조회 용 WMP80
	private String compkey;
	private String warekey;
	private String ownerky;
	private String skumkey;
	private String skudesc;
	private String locakey;
	private String tab;
	
	//WMP20 부품조회
	private String upccode; //부품 바코드
	//WASNIF WMP30 입하 조회용
	private String eoasnky;
	
	//WMP30 입하등록, WMP70 이동확정
	private String doctype;//문서타입
	private String rcvdcky;//입고문서번호
	private int rcvdcit;//입고문서아이템
	private int eoasnit;//입고예정아이템
	private int rcveqty;//입하대상 수량
	private String rcvrscd;//입고사유코드
	private String rcvrsnm;//입고사유내용
	private String expidat;//유통기한
	
	//WMP50 적치처리, WMP70 이동확정
	private String taskoky; //작업번호
	private int  taskoit; //작업문서번호
	private String doccate;
	private String stockky;//재고키
	private String truntyp;
	private String trunpid;
	private int fordqty;//적치처리 지시수량
	private String toareky;
	private String tozonky;
	private String tolocky;
	private String zonekey;
	//WMP60 피킹처리
	private String allgrky;//할당그룹키
	private int tcmpqty;//완료수량
	private int diffqty;//작업부족수량
	
	//WMP90 키오스크-입하등록 완료 조회
	private String rcvdcst;
	private String rcvitst;
	private String rcvdcdt;
	private String rcvdctm;
	private String asndqty;
	private String rchsqty;
	private String asndate;
	private String asnstat;
	private String vendkey;
	//날짜 검색용
	private String rcvdcdtfrom; //날짜(from)
	private String rcvdcdtto; //날짜(to)
	
	//ocopur 키오스크 수동입하용
	private String copodky; //VARCHAR(10) 발주문서
	private int copodit; //INT(11) 발주 아이템
	private String purstat; //VARCHAR(10) 발주상태
	
	//화주 한글
	private String ownamlc;
	//창고 한글
	private String whnamlc;
	//로케이션 한글
	private String locanam;
	//상태값 한글
	private String comcdtx;
	
	//tmp05 상차-PDA
	private String shtrsts;
	private String cooutky;
	private String cooutit;
	private String vhplnky;
	private String saatc06;
	private String saatc07;
	private String saatc08;
	private String vehicky;
	private String postdat;
	private String shtrtky;
	private int updtchk;
	private String vhcfnam;
	private String trnstat;
	
	private String mcatenm;
	
	//tmp07 배차 취소-PDA
	private String vhplnit;
	
	//커스텀
	private int tpupchk;//TPLNIT updtchk
	private int ocupchk;//OCOSAL updtchk
	private int tdupchk;//TPLNHD updtchk
	private String eofdtyn;//상차 마지막 저장될떄 N -> Y로 변환
	private String exctype;// ASN / 작업문서 별 실행 구분값
	// WMP92 
	private String salstat;
	
	
}
