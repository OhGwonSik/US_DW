package com.logistics.om.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.common.dto.RequestDTO;
import com.logistics.om.domain.MordmaDTO;
import com.logistics.om.domain.OeadjiDTO;
import com.logistics.om.domain.OeadjiVO;
import com.logistics.om.domain.OmInventoryVO;
import com.logistics.om.service.OmInventoryService;
import com.logistics.sy.domain.UserVO;

@RestController
public class OmInventoryController {
	
	@Autowired
	private OmInventoryService omInventoryService;
	
    /* 
     * 프로그램 ID : OMI01 
     * 프로그램 내용: 재고현황조회
     * */
	/*  selectInventoryList - OMI01 재고현황조회 페이지 호출 (전체, 부품별, 로케이션별 구분)
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 오권식 
	*   입력 PARAMETA	: OmInventoryVO omInventory - OM-재고파트 VO
	*				: UserVO userInfo - User 정보 VO
	*   출력 PARAMETA	: Map<String, Object> 형식
	*   설명			: OMI01 재고현황조회 
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자		: 
	*	변경 사항		: 
	*/
	@GetMapping("/om/inventory/omi01/grids/1")
	public Map<String, Object> selectInventoryList(@ModelAttribute OmInventoryVO omInventory, @AuthenticationPrincipal UserVO userInfo){
		omInventory.setCompkey(userInfo.getCompkey());
		return omInventoryService.selectInventoryList(omInventory, userInfo);
	}
	@GetMapping("/om/inventory/omi01/grids/group-skumkey")
	public List<OmInventoryVO> selectGroupSkudescList(@ModelAttribute OmInventoryVO omInventory, @AuthenticationPrincipal UserVO userInfo){
		omInventory.setCompkey(userInfo.getCompkey());
		return omInventoryService.selectGroupSkumkeyList(omInventory, userInfo);
	}
	@GetMapping("/om/inventory/omi01/grids/group-location")
	public List<OmInventoryVO> selectGroupLocationList(@ModelAttribute OmInventoryVO omInventory, @AuthenticationPrincipal UserVO userInfo){
		omInventory.setCompkey(userInfo.getCompkey());
		return omInventoryService.selectGroupLocationList(omInventory, userInfo);
	}	
    /* 
     * 프로그램 ID : OMI02 
     * 프로그램 내용: 재고조정지시
     * */
	/*  selectWstkkyList - OMI02 재고조정지시 페이지 호출 (로케이션 탭)
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 오권식 
	*   입력 PARAMETA	: OeadjiDTO oeadji - OM-재고파트 DTO
	*   출력 PARAMETA	: OmInventoryVO - OM-재고파트 VO
	*   설명			: 재고테이블에서 조정지시할 상품의 조회
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자		: 
	*	변경 사항		: 
	*/
	@GetMapping("/om/inventory/omi02/grids/1")
	public List<OmInventoryVO> selectWstkkyList(@ModelAttribute OeadjiVO oeadji, @AuthenticationPrincipal UserVO userInfo){
		oeadji.setCompkey(userInfo.getCompkey());
		return omInventoryService.selectWstkkyList(oeadji);
	}	
	
	/*  selectDoctypeSelectbox - OMI02 조정지시 필요 코드 조회 
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 오권식 
	*   입력 PARAMETA	: OeadjiDTO oeadji - OM-재고파트 DTO
	*				: MordmaDTO mordma - 오더타입 DTO
	*   출력 PARAMETA	: OeadjiDTO - OM-재고파트 DTO
	*   설명			: 조정지시 등록할 때 필요한 코드 조회
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자		: 
	*	변경 사항		: 
	*/
	@GetMapping("/om/inventory/omi02/init")
	public OeadjiDTO selectDoctypeSelectbox(@ModelAttribute OeadjiDTO oeadji, MordmaDTO mordma){
		return omInventoryService.selectDoctypeSelectbox(oeadji, mordma);
	}
	
	/*  insertOmi02Save - OMI02 조정지시 등록
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 오권식 
	*   입력 PARAMETA	: RequestDTO<OeadjiDTO> requestDTO - 공통 List DTO
	*   출력 PARAMETA	: OeadjiDTO - OM-재고파트 DTO
	*   설명			: 재고조정지시의 등록
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자		: 
	*	변경 사항		: 
	*/
	@PostMapping("/om/inventory/omi02/save")
	public int insertOmi02Save(@RequestBody RequestDTO<OeadjiDTO> requestDTO) {
		return omInventoryService.insertOmi02Save(requestDTO);
	}
    /* 
     * 프로그램 ID : OMI04 
     * 프로그램 내용: 조정현황조회
     * */
	/*  selectOeadjiList - OMI04 조정현황 페이지 호출 
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 오권식 
	*   입력 PARAMETA	: OeadjiVO oeadji - OM-재고파트 VO
	*   출력 PARAMETA	: UserVO userInfo - User 정보 VO
	*   설명			: 조정지시등록된 상품의 조회
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자		: 
	*	변경 사항		: 
	*/
	@GetMapping("/om/inventory/omi04/grids/1")
	public Map<String, Object> selectOeadjiList(@ModelAttribute OeadjiVO oeadji, @AuthenticationPrincipal UserVO userInfo){
		oeadji.setCompkey(userInfo.getCompkey());
		return omInventoryService.selectOeadjiList(oeadji, userInfo);
	}
    /* 
     * 프로그램 ID : OMI05 
     * 프로그램 내용: 조정취소조회
     * */
	/*  selectOeadjiCancelList - OMI05 조정취소등록된 상품 조회 
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 오권식 
	*   입력 PARAMETA	: OeadjiVO oeadji - OM-재고파트 VO
	*   출력 PARAMETA	: UserVO userInfo - User 정보 VO
	*   설명			: 조정취소등록된 상품 조회 
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자		: 
	*	변경 사항		: 
	*/
	@GetMapping("/om/inventory/omi05/grids/1")
	public Map<String, Object> selectOeadjiCancelList(@ModelAttribute OeadjiVO oeadji, @AuthenticationPrincipal UserVO userInfo){
		oeadji.setCompkey(userInfo.getCompkey());
		return omInventoryService.selectOeadjiCancelList(oeadji, userInfo);
	}
    /* 
     * 프로그램 ID : OMI06 
     * 프로그램 내용: 조정취소등록
     * */	
	/*  selectRsnCodeOmi06List - OMI06 조정취소사유코드 조회
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 오권식 
	*   입력 PARAMETA	: OeadjiDTO oeadjiDTO - OM-재고파트 DTO
	*   출력 PARAMETA	: OeadjiDTO - OM-재고파트 DTO
	*   설명			: 조정취소사유코드 조회
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자		: 
	*	변경 사항		: 
	*/
	@GetMapping("/om/inventory/omi06/rsncode")
	public OeadjiDTO selectRsnCodeOmi06List(@ModelAttribute OeadjiDTO oeadjiDTO){
		return omInventoryService.selectRsnCodeOmi06List(oeadjiDTO);
	}	
	
	/*  selectOeadjiCancelListByNew - OMI06 조정취소를 할 리스트 조회
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 오권식 
	*   입력 PARAMETA	: OeadjiDTO oeadjiDTO - OM-재고파트 DTO
	*   출력 PARAMETA	: OeadjiDTO - OM-재고파트 DTO
	*   설명			: 조정지시가 등록된 상품 조회 (조정취소위해) 조정취소사유코드 조회
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자		: 
	*	변경 사항		: 
	*/
	@GetMapping("/om/inventory/omi06/grids/1")
	public List<OeadjiVO> selectOeadjiCancelListByNew(@ModelAttribute OeadjiVO oeadji, @AuthenticationPrincipal UserVO userInfo){
		oeadji.setCompkey(userInfo.getCompkey());
		return omInventoryService.selectOeadjiCancelListByNew(oeadji, userInfo);
	}
	
	/*  updateOmi06Cancel - OMI06 조정취소 등록
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 오권식 
	*   입력 PARAMETA	: RequestDTO<OeadjiDTO> requestDTO - 공통 List DTO 
	*   출력 PARAMETA	: OeadjiDTO - OM-재고파트 DTO
	*   설명			: 조정지시의 등록
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자		: 
	*	변경 사항		: 
	*/
	@PatchMapping("/om/inventory/omi06/cancel")
	public int updateOmi06Cancel(@RequestBody RequestDTO<OeadjiDTO> requestDTO) {
		return omInventoryService.updateOmi06Cancel(requestDTO);
	}
}
