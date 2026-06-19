package com.logistics.wm.domain;

import java.util.List;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class RecvPutDTO extends RecvDTO {
	/*
	 ****************************************************************************************
	 - DTO Name : RecvPutDTO
	 - Description : 적치 페이지에서 사용할 필드를 모아놓은 DTO
	 - Made By : 최강호
	 - Creation Date :  2023.07.25
	 -------------------------------------------------------------------------------------------------
	 ****************************************************************************************
	 */
	
	//WTAKIT
	private String taskoky; //VARCHAR(10) 작업 문서 
	private int taskoit; //VARCHAR(11) 작업 문서 아이템 
	private String frareky; //VARCHAR(20) FRON AREA
	private String frzonky; //VARCHAR(20) FROM ZONE
	private String frlocky; //VARCHAR(20) FROM LOC
	private String toareky; //VARCHAR(20) TO AREA
	private String tozonky; //VARCHAR(20) TO ZONE
	private String tolocky; //VARCHAR(20) TO LOC
	private String parsncd; //VARCHAR(10) 이동 사유코드
	private String parsnnm; //VARCHAR(10) 이동 사유내용
	private String pkrsncd; //VARCHAR(10) 이동취소 사유코드
	private String pkrsnnm; //VARCHAR(10) 이동취소 사유내용
	private String tasksts; //VARCHAR(10) 작업 문서 상태
	private List<String> taskstss; //작업상태 리스트(검색용)
	private String fuomeky; //VARCHAR(10) 지시수량
	private int tcmpqty; //INT(11) 완료수량
	private String tuomeky; //VARCHAR(10) 완료단위
	private String eoasnky; //VARCHAR(10) ASN No
	private int    eoasnit; //INT(11)     ASN Item number
	private String storfky; //VARCHAR(10) 재고키 참고번호
	
	private String stkstat; //VARCHAR(10) 재고상태
	//날짜 검색용
	private String docdatefrom; //날짜(from)
	private String docdateto; //날짜(to)
	
	//area, zone, loc 명칭
	private String frlocnm;
	private String tolocnm;
	private String frzonnm;
	private String tozonnm;
	private String frarenm;
	private String toarenm;
	private String locaknm;
	
	private String rclocnm;
	private String rcvdcnm;
	
	//부품 바코드
	private String upccode;
	
}
