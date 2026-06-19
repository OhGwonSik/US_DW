package com.logistics.om.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.common.dto.RequestDTO;
import com.logistics.md.domain.MskuwcDTO;
import com.logistics.om.domain.MordmaDTO;
import com.logistics.om.domain.OcosalVO;
import com.logistics.om.domain.SalesOrderDTO;
import com.logistics.om.service.SalesService;
import com.logistics.pt.MdesmaVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SalesController {
	
	private final SalesService salesService;
	
	
	/* 
    * 프로그램 ID : OMS02
    * 프로그램 내용: 납품등록
    */
    
	/* oms02 init 데이터 */
	/*  함수명 - getOms02Init
	*   최초 생성일시	: 2023-08-21 23:18
	*   최초 생성자	: 정민경
	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
	*   출력 PARAMETER	: SalesOrderDTO  - 납품테이블 데이터
	*   설명			: 납품등록 페이지 진입시 고객, 문서, 화주를 세팅하기 위한 메소드.
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		:
	*/
	@GetMapping("/om/sales/oms02/init")
	public SalesOrderDTO getOms02Init(SalesOrderDTO salesOrder) {
		return salesService.getOms02Init(salesOrder);
	}
	
	/* oms02 상품 select Box */
	/*  함수명 - getOms02SkuwcSelectBox
	*   최초 생성일시	: 2023-07-07 09:02
	*   최초 생성자	: 정민경
	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
	*   출력 PARAMETER	: List<MskuwcDTO> - 상품 마스터 DTO
	*   설명			: 그리드 내 셀렉트박스에 상품 리스트를 세팅하기 위한 메소드.
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		:
	*/
	@GetMapping("/om/sales/common/skuwc")
	public List<MskuwcDTO> getOms02SkuwcSelectBox(SalesOrderDTO salesOrder) {
		return salesService.getOms02SkuwcSelectBox(salesOrder);
	}
	
	/* oms02 납품등록 저장 */
	/*  함수명 - saveSalesOms02
	*   최초 생성일시	: 2023-07-07 09:02
	*   최초 생성자	: 정민경
	*   입력 PARAMETER	: RequestDTO<SalesOrderDTO> (RequestDTO - 화면단 그리드 데이터를 수신하는 DTO/OM- 납품관리파트 DTO)
	*   출력 PARAMETER	: 납품/수주 테이블에 오더 등록(insert) 된 데이터 행 수 출력
	*   설명			: 납품을 등록하기 위한 메소드.
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		:
	*/
	@PostMapping("/om/sales/oms02/save")
	public int saveSalesOrder(@RequestBody RequestDTO<SalesOrderDTO> salesOrder) {
		return salesService.saveSalesOms02(salesOrder);
	}

	/* oms02 영업일자 (화주 변경시 영업일자) */
	/*  함수명 - getPosdate
	*   최초 생성일시	: 2023-07-10 18:07
	*   최초 생성자	: 정민경
	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
	*   출력 PARAMETER	: String 타입 영업일자
	*   설명			: 창고에 따른 영업일자를 가져오는 메소드.
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		:
	*/
    @GetMapping("/om/sales/oms02/postdat")
    public String getPostdat(SalesOrderDTO salesOrder){
    	return salesService.getPosdate(salesOrder);
    }
    	
	/* oms02 init 데이터 (도착지) */
    
    /*  함수명 - getDestkeyChangeOms02
	*   최초 생성일시	: 2023-07-11 14:50
	*   최초 생성자	: 정민경
	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO) , ptnrkey -고객키
	*   출력 PARAMETER	: List<MdesmaVO> - 도착지 마스터 DTO
	*   설명			: 고객키에 따른 도착지데이터를 그리드 내 도착지 셀렉트 박스에 세팅하기 위한 메소드.
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		:
	*/
    @GetMapping("/om/sales/oms02/grid/destkey/{ptnrkey}")
    public List<MdesmaVO> getDestkeyChangeOms02(SalesOrderDTO salesOrder, @PathVariable String ptnrkey){
    	salesOrder.setPtnrkey(ptnrkey);
    	return salesService.getDestkey(salesOrder);
    }
    
    
    /* 
     * 프로그램 ID : OMS03
     * 프로그램 내용: 납품취소조회
     */
    
    /*  함수명 - getCommonCootypeList
	*   최초 생성일시	: 2023-07-11 14:50
	*   최초 생성자	: 정민경
	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
	*   출력 PARAMETER	:SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
	*   설명			: 문서타입 체크박스를 세팅하기 위한 메소드.
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		:
	*/
    @GetMapping("/om/common/cootype")
    public List<MordmaDTO> getCommonCootypeList(MordmaDTO mordmaParam) {
    	return salesService.getCootypeList(mordmaParam);
    }
    
	/* oms03 : 납품오더 리스트 조회  */
    /*  함수명 - getOms03CancelList
	*   최초 생성일시	: 2023-07-10 18:07
	*   최초 생성자	: 정민경
	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
	*   출력 PARAMETER	: List<OcosalVO> (OM - 수주/납품 테이블 VO)
	*   설명			: 납품의 상태가 '취소'인 데이터를 조회하는 메소드.
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		:
	*/
    @GetMapping("/om/sales/oms03/grid/list")
    public List<OcosalVO> getOms03List(SalesOrderDTO salesOrder){
    	return salesService.getOms03CancelList(salesOrder);
    }
	
    /* 
    * 프로그램 ID : OMS04
    * 프로그램 내용: 납품취소등록
    */
  
    /* Item rsnList init data */
    /*  함수명 - getOms04CancelRsnInit
	*   최초 생성일시	: 2023-07-19 18:02
	*   최초 생성자	: 정민경
	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
	*   출력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
	*   설명			: 그리드 내 셀렉트박스에 취소사유 데이터를 세팅하기 위한 메소드.
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		:
	*/
    @GetMapping("/om/sales/oms04/common/rsncode")
    public SalesOrderDTO getOms04CancelRsnInit(SalesOrderDTO salesOrder) {
    	return salesService.getOms04CancelRsnList(salesOrder);
    }
    
	/* 헤드 그리드 리스트 조회 */
    /*  함수명 - getOms04HeadList
	*   최초 생성일시	: 2023-07-19 18:02
	*   최초 생성자	: 정민경
	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
	*   출력 PARAMETER	: List<SalesOrderDTO> (OM- 납품관리파트 DTO)
	*   설명			: 취소 가능한 수주/납품 데이터를 조회하는 메소드.
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		:
	*/
    @GetMapping("/om/sales/oms04/list")
    public List<SalesOrderDTO> getOms04HeadList(SalesOrderDTO salesOrder){
    	return salesService.getOms04HeadList(salesOrder);
    }
    
	/* 납품취소 저장 */
    /*  함수명 - updateSalesCancel
   	*   최초 생성일시	: 2023-07-19 18:02
   	*   최초 생성자	: 정민경
   	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
   	*   출력 PARAMETER	: 취소 등록(update)된 행의 수
   	*   설명			: 납품 처리상태를 '취소'로 수정하기 위한 메소드.
   	*   수정 내역		:
   	*   수정일시		:
   	*	수정자		:
   	*	변경 사항		:
   	*/
    @PatchMapping("/om/sales/oms04/cancel")
    public int updateSalesCancel (@RequestBody RequestDTO<SalesOrderDTO> salesOrder) {
    	return salesService.updateSalesCancel(salesOrder);
    }
	
    /* 
     * 프로그램 ID : OMS06
     * 프로그램 내용: 출고오더조회
     */
    
    /*  함수명 - getOms06Init
   	*   최초 생성일시	: 2023-07-26 18:13
   	*   최초 생성자	: 정민경
   	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
   	*   출력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
   	*   설명			: 페이지 진입시 검색조건 내 체크박스 데이터를 세팅하기 위한 메소드.(문서타입/상태)
   	*   수정 내역		:
   	*   수정일시		:
   	*	수정자		:
   	*	변경 사항		:
   	*/
    @GetMapping("/om/sales/oms06/init")
    public SalesOrderDTO getOms06Init(SalesOrderDTO salesOrder){
    	return salesService.getDoctypeAndStateInit(salesOrder);
    }
    
    /*  함수명 - getOms06List
   	*   최초 생성일시	: 2023-07-26 18:13
   	*   최초 생성자	: 정민경
   	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
   	*   출력 PARAMETER	: List<SalesOrderDTO>(OM- 납품관리파트 DTO)
   	*   설명			: 수주/납품 데이터의 출고상태를 확인하기 위한 메소드.
   	*   수정 내역		:
   	*   수정일시		:
   	*	수정자		:
   	*	변경 사항		:
   	*/
    @GetMapping("/om/sales/oms06/grid/list")
    public List<SalesOrderDTO> getOms06List(SalesOrderDTO salesOrder){
    	return salesService.getOms06List(salesOrder);
    }
    
    /* 
     * 프로그램 ID : OMS08
     * 프로그램 내용: 운송오더조회
     */
    
    /*  함수명 - getOms08Init
   	*   최초 생성일시	: 2023-07-26 18:13
   	*   최초 생성자	: 정민경
   	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
   	*   출력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
   	*   설명			: 페이지 진입시 검색조건 내 체크박스 데이터를 세팅하기 위한 메소드.(문서타입/상태)
   	*   수정 내역		:
   	*   수정일시		:
   	*	수정자		:
   	*	변경 사항		:
   	*/
    @GetMapping("/om/sales/oms08/init")
    public SalesOrderDTO getOms08Init(SalesOrderDTO salesOrder){
    	return salesService.getDoctypeAndStateInit(salesOrder);
    }
    
    
    /*  함수명 - getOms08List
   	*   최초 생성일시	: 2023-07-26 18:13
   	*   최초 생성자	: 정민경
   	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
   	*   출력 PARAMETER	: List<SalesOrderDTO>(OM- 납품관리파트 DTO)
   	*   설명			: 수주/납품 데이터의 운송상태를 확인하기 위한 메소드.
   	*   수정 내역		:
   	*   수정일시		:
   	*	수정자		:
   	*	변경 사항		:
   	*/
    @GetMapping("/om/sales/oms08/grid/list")
    public List<SalesOrderDTO> selectOms08List(SalesOrderDTO salesOrder){
    	return salesService.getOms08List(salesOrder);
    }
    
	 /* 
     * 프로그램 ID : OMS10
     * 프로그램 내용: 납품현황조회
     */
    @GetMapping("/om/sales/oms10/init") 
    public SalesOrderDTO getOms10Init(SalesOrderDTO salesOrderDTO) {
    	return salesService.getOms10Init(salesOrderDTO);
    }
    
    /*  함수명 - getOms10List
   	*   최초 생성일시	: 2023-07-30 21:21
   	*   최초 생성자	: 한지수
   	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
   	*   출력 PARAMETER	: List<SalesOrderDTO>(OM- 납품관리파트 DTO)
   	*   설명			: 등록된 납품 데이터의 현황(오더 상태 등)을 조회하기 위한 메소드.
   	*   수정 내역		:
   	*   수정일시		:
   	*	수정자		:
   	*	변경 사항		:
   	*/
    @GetMapping("/om/sales/oms10/grids")
    public List<SalesOrderDTO> getOms10List(SalesOrderDTO salesOrderDTO) {
    	return salesService.getOms10List(salesOrderDTO);
    }
    
    /* 
     * 프로그램 ID : OMR11
     * 프로그램 내용: 회수오더등록 (SMA 230802 추가)
     */
    
    /*  함수명 - selectOmr11Init
   	*   최초 생성일시	: 2023-08-02 10:29
   	*   최초 생성자	: 안성민
   	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
   	*   출력 PARAMETER	:  SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
   	*   설명			: 회수등록 페이지 진입시 초기 데이터 세팅을 위한 메소드(고객, 문서타입,사유코드 리스트)
   	*   수정 내역		:
   	*   수정일시		:
   	*	수정자		:
   	*	변경 사항		:
   	*/
	@GetMapping("/om/sales/omr11/grid/init")
	public SalesOrderDTO selectOmr11Init(SalesOrderDTO returnRes) {
		returnRes.setCustkeyList(salesService.getCustomer(returnRes));		//고객
		returnRes.setReturnDoctypeList(salesService.getReturnDoctype(returnRes));		//문서타입
		//returnRes.setPostdat(salesService.getPosdate(returnRes));		//영업일자
		returnRes.setReturnCodeList(salesService.getReturnRsncodeSb(returnRes)); // 회수사유코드 (SMA추가 230802) 
		return returnRes;
	}
    
    /* 
     * 프로그램 ID : OMR13
     * 프로그램 내용: 회수현황조회
     */
	
	 /*  함수명 - getOmr13Init
   	*   최초 생성일시	: 2023-08-28 18:02
   	*   최초 생성자	: 정민경
   	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
   	*   출력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
   	*   설명			: 회수현황조회 페이지의 검색조건에 '상태'체크박스를 세팅하기 위한 메소드.
   	*   수정 내역		:
   	*   수정일시		:
   	*	수정자		:
   	*	변경 사항		:
   	*/
	@GetMapping("/om/sales/omr13/init")
	public SalesOrderDTO getOmr13Init(SalesOrderDTO salesOrder) {
		return salesService.getOmsStatusCheckBoxInit(salesOrder);
	}
	
	/*  함수명 - getOmr13List
   	*   최초 생성일시	: 2023-08-02 16:55
   	*   최초 생성자	: 정민경
   	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
   	*   출력 PARAMETER	: List<OcosalVO>(OM- 납품관리 파트 VO)
   	*   설명			: 회수 등록된 데이터를 조회하기 위한 메소드.
   	*   수정 내역		:
   	*   수정일시		:
   	*	수정자		:
   	*	변경 사항		:
   	*/
	@GetMapping("/om/sales/omr13/grid/list")
	public List<OcosalVO> getOmr13List(SalesOrderDTO salesOrder){
		return salesService.getOmr13List(salesOrder);
	}
	
}
