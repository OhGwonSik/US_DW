package com.logistics.om.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.om.domain.OcosalVO;
import com.logistics.om.domain.OeretiVO;
import com.logistics.om.domain.SalesOrderDTO;
import com.logistics.om.service.OMReturnService;
import com.logistics.sy.domain.UserVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OMReturnController {
	private final OMReturnService omrService;

	// OMR01 반출등록시 필요한 재고조회
	/*  getOmr01List - OMR01 반출등록 페이지 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 안성민 
	*   입력 PARAMETA	: OeretiVO oereti - OM-반출파트 VO
	*				: UserVO userInfo - User 정보 VO
	*   출력 PARAMETA	: Map<String, Object> 형식
	*   설명			: 재고테이블을 조회해서 반출을 등록할 상품을 조회  
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자			: 
	*	변경 사항		: 
	*/
	@GetMapping("/om/omr01Select.do")
	public Map<String, Object> getOmr01List(@ModelAttribute OeretiVO oereti, @AuthenticationPrincipal UserVO userInfo) {
		oereti.setCompkey(userInfo.getCompkey());
		return omrService.getOmr01List(oereti, userInfo);
	}

	/*  saveOmr01Data - OMR01 반출오더등록
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 안성민 
	*   입력 PARAMETA	: Map<String, Object> param
	*				: UserVO userInfo - User 정보 VO
	*   출력 PARAMETA	: Map<String, Object> 형식
	*   설명			: OERETI 테이블에 반출오더 등록
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자			: 
	*	변경 사항		: 
	*/
	@PostMapping("/om/omr01/omr1Save.do")
	public Map<String, Object> saveOmr01Data(@RequestBody Map<String, Object> param,
			@AuthenticationPrincipal UserVO userInfo) {
		return omrService.saveOmr01Data(param, userInfo);
	}

	/*  getOmr03List - OMR03 반출등록된 상품의 현황 조회
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 안성민 
	*   입력 PARAMETA	: OeretiVO oereti - OM-반출파트 VO
	*				: UserVO userInfo - User 정보 VO
	*				: CommonDTO common - 공통파트 DTO
	*   출력 PARAMETA	: Map<String, Object> 형식
	*   설명			: 반출등록된 상품 조회 
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자			: 
	*	변경 사항		: 
	*/
	@GetMapping("/om/omr03/omr03Select.do")
	public Map<String, Object> getOmr03List(@ModelAttribute OeretiVO oereti, @AuthenticationPrincipal UserVO userInfo, CommonDTO common) {
		oereti.setCompkey(userInfo.getCompkey());
		return omrService.getOmr03List(oereti,userInfo,common);
	}

	/*  getOmr04List - OMR04 반출등록 취소된 상품의 현황 조회
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 안성민 
	*   입력 PARAMETA	: OeretiVO oereti - OM-반출파트 VO
	*				: UserVO userInfo - User 정보 VO
	*   출력 PARAMETA	: Map<String, Object> 형식
	*   설명			: 반출 등록 이후 취소 등록된 상품 조회
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자			: 
	*	변경 사항		: 
	*/
	@GetMapping("/om/omr04/omr04Select.do")
	public Map<String, Object> getOmr04List(@ModelAttribute OeretiVO oereti, @AuthenticationPrincipal UserVO userInfo) {
		oereti.setCompkey(userInfo.getCompkey());
		return omrService.getOmr04List(oereti, userInfo);
	}

	/*  getOmr05List - OMR05 반출취소를 등록할 상품의 조회
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 안성민 
	*   입력 PARAMETA	: OeretiVO oereti - OM-반출파트 VO
	*				: UserVO userInfo - User 정보 VO
	*   출력 PARAMETA	: Map<String, Object> 형식
	*   설명			: 반출 취소를 등록할 상품의 조회
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자			: 
	*	변경 사항		: 
	*/
	@GetMapping("/om/omr05/omr05Select.do")
	public Map<String, Object> selectOmr05List(@ModelAttribute OeretiVO oereti,
			@AuthenticationPrincipal UserVO userInfo) {
		oereti.setCompkey(userInfo.getCompkey());
		return omrService.getOmr05List(oereti, userInfo);
	}


	/*  saveOmr05Data - OMR05 반출취소등록
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 안성민 
	*   입력 PARAMETA	: Map<String, Object> param 형식
	*				: UserVO userInfo - User 정보 VO
	*   출력 PARAMETA	: Map<String, Object> 형식
	*   설명			: 반출 등록된 상품의 취소등록
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자			: 
	*	변경 사항		: 
	*/
	@PostMapping("/om/omr05/omr5Save.do")
	public int saveOmr05Data(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVO userInfo) {
		return omrService.saveOmr05Data(param, userInfo);
	}

	/*  setOmr11Save - OMR11 회수등록
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 안성민 
	*   입력 PARAMETA	: RequestDTO<SalesOrderDTO> params - 공통 List DTO
	*				: CommonDTO common - 공통파트 DTO
	*   출력 PARAMETA	: Map<String, Object> 형식
	*   설명			: 회수 등록
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자			: 
	*	변경 사항		: 
	*/
	@PostMapping("/om/sales/omr11/save")
	public int oms02Save(@RequestBody RequestDTO<SalesOrderDTO> params, CommonDTO common) {
		return omrService.setOmr11Save(params, common);
	}

//	// OMR11 회수오더등록 (납품취소버전 230801 피드백 후 일단 보류)
//	@GetMapping("/om/sales/omr11/grids/2")
//	public List<OcosalVO> selectOms04ItemList(SalesOrderDTO param) {
//		return omrService.getOms04ItemList(param);
//	}

}
