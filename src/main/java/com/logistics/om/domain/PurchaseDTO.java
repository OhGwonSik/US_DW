package com.logistics.om.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.logistics.common.dto.CommonDTO;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.domain.MdocmaDTO;
import com.logistics.md.domain.MptnmaVO;
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
public class PurchaseDTO extends CommonDTO{

	//OCOPUR
	private String rowkey;
	private String compkey;
	private String copodky; 
	private int copodit;	// 고객발주 아이템번호
	private String doccate;	// 문서유형
	private String doctype; // 문서타입
	private String purdate;	// 발주일자
	private String purtime;	// 발주시간
	private String purstat; // 발주상태
	private String puomkey;
	private String custkey; // 고객
	private String destkey; // 도착지
	private String vendkey; // 입고처
	private String ownerky; // 화주
	private String skuskey; // 대표상품
	private String skumkey; // 상품
	private String skudesc; // 상품명
	private int poitqty; 	// 발주수량
	private String podmemo; // 발주 요구사항
	private String rcvwhky; // 입고 창고
	private String reqdate;	// 입고 요청일
	private String reqtime; // 입고 요청시간
	private String reqmemo; // 입고요구사항
	
	//WASNIF
	private String eoasnky;
	private int eoasnit; 	//ASN item number
	private String warekey; //창고
	private String asnstat; //ASN 상태
	private String asndate; //입고 예정일
	private String asntime;	//입고 예정시간
	private int asndqty; 	//입고예정 수량
	private String auomkey; //ASN UOM
	private String carrkey; // 운송사
	private String truntyp;  // Transfer Unit type
	private String truckno; //*입고차량 번호
	private String tructon; //*입고차량 톤수
	private String asnmemo;	//입고 요청사항	
	private String sirbcod; //개별관리 번호
	private String proddat; //생산일자
	private int shelife; 	//*유통기한
	private String expidat; //*유통기간
	private String rsncode; //입고사유코드
	private String rcvsrmk; //입고사유내용
	private int rchpqty; //입하가능누적수량
	private int rchsqty; //기입하완료수량
	private String stkstat; //재고상태
	private String rchsuom; //기입하완료단위
	private String lotat01; //재고LOT속성01
	private String lotat02; //재고LOT속성02
	private String lotat03; //재고LOT속성03
	private String lotat04; //재고LOT속성04
	private String lotat05; //재고LOT속성05
	private String lotat06; //재고LOT속성06
	private String lotat07; //재고LOT속성07
	private String lotat08; //재고LOT속성08
	private String lotat09; //재고LOT속성09
	private String lotat10; //재고LOT속성10
	private String lotat11; //재고LOT속성11
	private String lotat12; //재고LOT속성12
	private String lotat13; //재고LOT속성13
	private String lotat14; //재고LOT속성14
	private String lotat15; //재고LOT속성15
	private String lotat16; //재고LOT속성16
	private String lotat17; //LOT17=라벨갈이 여부
	private String lotat18; //LOT18=라벨갈이 요청고객
	private String lotat19; //LOT19=MTO 오더주문번호
	private String lotat20; //LOT20=MTO 오더주문아이템
	private String refsodc; //ref ERP SO
	private String refsoit; //ref ERP SO Item
	private String refdndc; //ref ERP DN
	private String refdnit; //ref ERP DN Item
	private String refsddc; //ref ERP SD
	private String refsdit; //ref ERP SD Item

	//수량
	private int miniqty; // INT(11) - 최소발주수량
	private int propqty; // INT(11) - 적정재고수량
	private int salfqty; // INT(11) - 안전재고수량
	
	//박스수량
	private int packqty;
	private int asitbox;
	
	//재고수량
	private int stotqty;
	private String whnamlc;
	
	//doc
	private String douseyn;
	
	//검색조건
	private String asndateFrom;
	private String asndateTo;
	private List<String> asnstats;
	private List<String> doctypes;
	private int itemcnt;
	private List<String> partners;
	
	//select box
	private String comcdky;
	private String ptnrkey;
	private String ptnrtyp;
	private String docctnm;
	private String doctynm;
	private String comcdtx;
	private List<MskuwcDTO> skuwcList;
	private List<MptnmaVO> venderList;
	private List<MdesmaVO> destkeyList;
	private List<MptnmaVO> carrierList;
	private List<McodemDTO> mcodemList;
	private List<McodemDTO> asnstatList;
	private List<MdocmaDTO> doctypeList;
	private List<MordmaDTO> cootypeList;
	private List<MdocmaDTO> mdocmaList;
}
