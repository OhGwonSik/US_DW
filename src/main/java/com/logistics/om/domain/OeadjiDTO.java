package com.logistics.om.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.logistics.common.dto.CommonDTO;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.domain.MdocmaDTO;
import com.logistics.md.domain.MrscmaDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class OeadjiDTO extends CommonDTO{
	
	/* 
	 * ******************************************** 
	  - DTO Name       : OeadjiDTO
	  - Description    : OM 재고조정기능에 필요한 필드 모아둔 DTO
	  - Made By        : 오권식
	  - Creation Date  : 2023.07.14
	  ------------------------------------------
	 
	* ********************************************  
	*/
	
	private String compkey; //Company Key
	private String oeadjky; //물류오더 문서번호
	private int oeadjit; //물류오더 문서아이템
	private String oeinsdt; //물류오더 지시일자
	private String oeinstm; //물류오더 지시시간
	private String oeinsst; //물류오더 처리상태
	private String rsncode; //조정 사유코드
	private String adjsrmk; //조정 사유내용
	private String warekey; //창고
	private String doccate; //문서유형
	private String doctype; //문서타입
	private String stockky; //재고키
	private String areakey; //Area Key
	private String zonekey; //Zone Key
	private String locakey; //Location Key
	private String subsect; //Sub Section
	private String truntyp; //Transfer unit type
	private String trunpid; //Transfer unit ID
	private String lotnmky; //LOT NUMBER
	private String ownerky; //화주
	private String skumkey; //상품
	private String skudesc; //상품명
	private String vendkey; //입고처
	private String sirbcod; //개별관리코드
	private int stotqty; //총재고 수량
	private int sallqty; //할당 수량
	private int sinbqty; //입고중 수량
	private int soubqty; //출고중 수량
	private int sbloqty; //블락 수량
	private String savaqty; //가용재고
	private String stkstat; //재고상태
	private String suomkey; //Stock UOM
	private int rtodqty; //조정지시수량
	private int rtcfqty; //조정완료수량
	private String atkstat; //조정지시 재고상태
	private String rcvdcdt; //입고일자
	private String rcvfrdt; //최초입고일자
	
	private String adcrscd; //조정지시취소 사유코드
	private String adcrsnm; //조정지시취소 이유
	
	private String oeinsstTwo; //물류오더 처리상태 2번째
	
	private String rscate1; //사유코드 종류
	private String rsncdnm; //사유코드 한글명
	private String doctynm; //문서타입 한글명
	
	private List<McodemDTO> stkstatList; //지시재고상태 리스트
	//private List<MdocmaDTO> doctypeList; //문서타입 리스트
	private List<MrscmaDTO> rsncodeList; //사유코드 리스트
	//20230904 MORDMA 변경위해 추가 by.SMA 
	private List<MordmaDTO> doctypeList;
}
