package com.logistics.webjis.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WebjisVO {

	// TABLE : IFSAL21R
	private String compkey;				// Company Key
	private String ifsalsq;				// IF 수주문서번호
	private String plant;				// 공장
	private String line;				// 생산라인
	private String itemcate;			// 품목
	private String palletNo;			// PalletNo
	private String createDateTime;		// 데이터 생성일시
	private String printDateTime;		// 인쇄일시
	private String seq;					// seq
	private String alc;					// ald code
	private String carType;				// carType
	private String goodsCode;			// 부품코드
	private String goodsName;			// 부품
	private String bodyNo;				// bodyNo
	private String receiveDateTime;		// receiveDateTime
	private String checkDateTime;		// checkDateTime
	private String ifprsts;				// 데이터처리상태
	private String ifrcvdt;				// 데이터수신일자
	private String ifrcvtm;				// 데이터수신시간
	private String ifrcvur;				// 데이터수신사용자
	private String iferrnm;				// 작업오류내용
	private String ifcmpdt;				// 작업완료일자
	private String ifcmptm;				// 작업완료시간
	private String ifcmpur;				// 작업완료사용자
	private String indibzl;				// Business Logic Indicator
	private String indiarc;				// Archive Indicator
	private int updtchk;				// Update Check
}
