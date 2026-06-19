package com.logistics.om.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.common.dto.RequestDTO;
import com.logistics.md.domain.MptnmaVO;
import com.logistics.md.domain.MskuwcDTO;
import com.logistics.om.domain.OMReportDTO;
import com.logistics.om.domain.PurchaseCancelDTO;
import com.logistics.om.domain.PurchaseDTO;
import com.logistics.om.service.PurchaseService;
import com.logistics.pt.MdesmaVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PurchaseController {

	private final PurchaseService purchaseService;
	
	/*  getPartnerSelectBox - 파트너 셀렉트박스 호출 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수
	*   입력 PARAMETA	: PurchaseDTO purchaseDTO - OM-입고관리파트 DTO, 
	*                     List<String> partners   - 파트너 타입(CUSTOMER, WH, CARRIER)
	*   출력 PARAMETA	: List<MptnmaVO> - 파트너마스터 VO
	*   설명			: MPTNMA(파트너마스터) 내 파트너 타입에 따른 셀렉트박스를 구성하기 위한 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		:  
	*/
	@GetMapping("/om/purchase/common/partner/{partners}")
	public List<MptnmaVO> getPartnerSelectBox(PurchaseDTO purchaseDTO, @PathVariable("partners") List<String> partners) {
		purchaseDTO.setPartners(partners);
		return purchaseService.getPartnerSelectBox(purchaseDTO);
	}
	
	/*  getDestSelectBox - 도착지 셀렉트박스 호출 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수
	*   입력 PARAMETA	: PurchaseDTO purchaseDTO - OM-입고관리파트 DTO, 
	*                     String ptnrkey   - 파트너 키
	*   출력 PARAMETA	: List<MdesmaVO> - 도착지마스터 VO
	*   설명			: MDESMA(도착지 마스터) 내 파트너 키에 따른 셀렉트박스를 구성하기 위한 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		:  
	*/
	@GetMapping("/om/purchase/common/destination/{ptnrkey}")
	public List<MdesmaVO> getDestSelectBox(PurchaseDTO purchaseDTO, @PathVariable("ptnrkey") String ptnrkey) {
		purchaseDTO.setPtnrkey(ptnrkey);
		return purchaseService.getDestkeySelectBox(purchaseDTO);
	}
	
	/*  getSkuwcList - 부품(상품) 셀렉트박스 호출 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수
	*   입력 PARAMETA	: MskuwcDTO mskuwcDTO - 상품마스터 DTO, 
	*   출력 PARAMETA	: List<MskuwcDTO> - 도착지마스터 VO
	*   설명			: MSKUWC(부품(상품) 마스터) 부품 셀렉트박스를 구성하기 위한 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		:  
	*/
	@GetMapping("/om/purchase/common/skuwc")
	public List<MskuwcDTO> getSkuwcList(MskuwcDTO mskuwcDTO) {
		return purchaseService.getSkuwcList(mskuwcDTO);
	}
	
	/*  getOmp01Init - omp01 입고등록(발주) 초기 세팅 데이터 호출 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수
	*   입력 PARAMETA	: PurchaseDTO purchaseDTO - OM-입고관리파트 DTO
	*   출력 PARAMETA	: PurchaseDTO - OM-입고관리파트 DTO
	*   설명			: omp01 입고등록(발주)에 필요한 부품(상품), 벤더 및 문서타입 데이터를 수신하는 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		:  
	*/
	@GetMapping("/om/purchase/omp01/init")
	public PurchaseDTO getOmp01Init(PurchaseDTO purchaseDTO) {
		return purchaseService.getOmp01Init(purchaseDTO);
	}
	
	/*  getOmp01Init - omp01 입고등록(발주) 데이터 저장 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수
	*   입력 PARAMETA	: RequestDTO<PurchaseDTO> requestDTO - 화면단 그리드 데이터를 수신하는 DTO
	*   출력 PARAMETA	: int - 저장된 row 수
	*   설명			: omp01 입고등록(발주) 시 데이터를 저장하는 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		:  
	*/
	@PostMapping("/om/purchase/omp01/save")
	public int saveOmp01Data(@RequestBody RequestDTO<PurchaseDTO> requestDTO) {
		return purchaseService.saveOmp01Data(requestDTO);
	}
	
	/*  getOmp02Init - omp02 ASN등록 초기 세팅 데이터 호출 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수
	*   입력 PARAMETA	: PurchaseDTO purchaseDTO - OM-입고관리파트 DTO
	*   출력 PARAMETA	: PurchaseDTO - OM-입고관리파트 DTO
	*   설명			: omp02 ASN등록에 필요한 부품(상품), 벤더, 운송사, 입고차량톤수 및 문서타입 데이터를 수신하는 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		:  
	*/
	@GetMapping("/om/purchase/omp02/init")
	public PurchaseDTO getOmp02Init(PurchaseDTO purchaseDTO) {
		return purchaseService.getOmp02Init(purchaseDTO);
	}
	
	/*  saveOmp02Data - omp02 ASN등록 데이터 저장 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수
	*   입력 PARAMETA	: RequestDTO<PurchaseDTO> requestDTO - 화면단 그리드 데이터를 수신하는 DTO
	*   출력 PARAMETA	: OMReportDTO - OM 출력물 DTO
	*   설명			: omp02 ASN등록 시 데이터를 저장하는 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		:  
	*/
	@PostMapping("/om/purchase/omp02/save")
	public OMReportDTO saveOmp02Data(@RequestBody RequestDTO<PurchaseDTO> requestDTO) {
		return purchaseService.saveOmp02Data(requestDTO);
	}
	
	/*  getOmp04Init - omp04 ASN현황조회 초기 세팅 데이터 호출 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수
	*   입력 PARAMETA	: PurchaseDTO purchaseDTO - OM-입고관리파트 DTO
	*   출력 PARAMETA	: PurchaseDTO - OM-입고관리파트 DTO
	*   설명			: omp04 ASN현황조회에 필요한 문서타입, ASN상태 데이터를 수신하는 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		:  
	*/
	@GetMapping("/om/purchase/omp04/init")
	public PurchaseDTO getOmp04Init(PurchaseDTO purchaseDTO) {
		return purchaseService.getOmp04Init(purchaseDTO);
	}
	
	/*  getWasnifList - omp04 ASN현황조회 그리드 조회 데이터 호출 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수
	*   입력 PARAMETA	: PurchaseDTO purchaseDTO - OM-입고관리파트 DTO
	*   출력 PARAMETA	: List<PurchaseDTO> - OM-입고관리파트 DTO
	*   설명			: omp04 ASN현황조회에 필요한 ASN(입고예정등록) 데이터를 수신하는 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		:  
	*/
	@GetMapping("/om/purchase/omp04/grids")
	public List<PurchaseDTO> getWasnifList(PurchaseDTO purchaseDTO) {
		return purchaseService.getWasnifList(purchaseDTO);
	}
	
	/*  getOmp06HeaderList - omp06 ASN취소등록 헤더 그리드 조회 데이터 호출 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수
	*   입력 PARAMETA	: PurchaseDTO purchaseDTO - OM-입고관리파트 DTO
	*   출력 PARAMETA	: List<PurchaseDTO> - OM-입고관리파트 DTO
	*   설명			: omp06 ASN취소등록에 필요한 ASN 상태가 NEW인 ASN 데이터를 group by하여 수신하는 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		:  
	*/
	@GetMapping("/om/purchase/omp06/grids/1") 
	public List<PurchaseDTO> getOmp06HeaderList(PurchaseDTO purchaseDTO) {
		return purchaseService.getOmp06HeaderList(purchaseDTO);
	}
	
	/*  getOmp06ItemList - omp06 ASN취소등록 아이템 그리드 조회 데이터 호출 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수
	*   입력 PARAMETA	: PurchaseDTO purchaseDTO - OM-입고관리파트 DTO
	*   출력 PARAMETA	: List<PurchaseDTO> - OM-입고관리파트 DTO
	*   설명			: omp06 ASN취소등록에 필요한 ASN 상태가 NEW인 ASN 데이터를 수신하는 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		:  
	*/
	@GetMapping("/om/purchase/omp06/grids/2")
	public PurchaseCancelDTO getOmp06ItemList(PurchaseDTO purchaseDTO) {
		return purchaseService.getOmp06ItemList(purchaseDTO);
	}
	
	/*  updateStatCancel - omp06 ASN취소등록한 데이터 업데이트 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수
	*   입력 PARAMETA	: PurchaseDTO purchaseDTO - OM-입고관리파트 DTO
	*   출력 PARAMETA	: List<PurchaseDTO> - OM-입고관리파트 DTO
	*   설명			: omp06 ASN취소등록한 데이터의 상태를 'CANCEL'로 업데이트 하는 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		:  
	*/
	@PatchMapping("/om/purchase/omp06/cancel")
	public int updateStatCancel(@RequestBody RequestDTO<PurchaseDTO> requestDTO) {
		return purchaseService.updateStatCancel(requestDTO);
	}
}
