package com.logistics.md.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.common.dto.RequestDTO;
import com.logistics.md.domain.BflrmaDTO;
import com.logistics.md.domain.BftrmaDTO;
import com.logistics.md.domain.BplrmaDTO;
import com.logistics.md.domain.BvlrmaDTO;
import com.logistics.md.domain.BvtrmaDTO;
import com.logistics.md.service.BillingMasterService;
import com.logistics.sy.domain.UserVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BillingMasterController implements Serializable {

	private static final long serialVersionUID = 1L;

	private final BillingMasterService billingMasterService;


	//=========================================================//
	//==========================mdb01==========================//
	//=========================================================//

	/*  getMdb01Init - MDB01 변동물류비 초기 데이터 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : @AuthenticationPrincipal UserVO userInfo - 현재 사용자 정보
	*   출력 PARAMETER : Map<String, Object> - 초기 데이터 맵
	*   설명      	: MDB01 화면의 초기 데이터를 조회
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	@GetMapping(value = "/md/billingMaster/mdb01/init")
    public Map<String, Object> getMdb01Init(@AuthenticationPrincipal UserVO userInfo) {
        return billingMasterService.getMdb01init(userInfo);
    }

	/*  getMdb01GridList - MDB01 변동물류비 그리드 리스트 데이터 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : @ModelAttribute BvlrmaDTO param - 변동물류비요율 DTO
	*   출력 PARAMETER : List<BvlrmaDTO> - 그리드 리스트
	*   설명      	: 전달된 조회 조건을 기반으로 그리드 리스트를 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	@GetMapping("/md/billingMaster/mdb01/grids/1")
	public List<BvlrmaDTO> getMdb01GridList(@ModelAttribute BvlrmaDTO param){
		return billingMasterService.getMdb01List(param);
	}

	/*  saveMdb01List - MDB01 그리드 리스트 저장
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : @RequestBody RequestDTO<BvlrmaDTO> params - 저장할 데이터를 담은 변동물류비요율 DTO
	*   출력 PARAMETER : int - 처리 결과
	*   설명      	: 전달된 데이터를 기반으로 그리드 리스트를 저장하고, 처리 결과를 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	@PostMapping("/md/billingMaster/mdb01/grids/1")
	public int saveMdb01List(@RequestBody RequestDTO<BvlrmaDTO> params) {
		return billingMasterService.saveMdb01List(params);
	}


	//=========================================================//
	//==========================mdb02==========================//
	//=========================================================//

	/*  getMdb02Init - MDB02 고정물류비 초기 데이터 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : @AuthenticationPrincipal UserVO userInfo - 현재 사용자 정보
	*   출력 PARAMETER : Map<String, Object> - 초기 데이터 맵
	*   설명      	: MDB02 화면의 초기 데이터를 조회
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	@GetMapping(value = "/md/billingMaster/mdb02/init")
    public Map<String, Object> getMdb02Init(@AuthenticationPrincipal UserVO userInfo) {
        return billingMasterService.getMdb02init(userInfo);
    }

	/*  getMdb02GridList - MDB02 고정물류비 그리드 리스트 데이터 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : @ModelAttribute BflrmaDTO param - 고정물류비요율 DTO
	*   출력 PARAMETER : List<BflrmaDTO> - 그리드 리스트
	*   설명      	: 전달된 조회 조건을 기반으로 그리드 리스트를 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	@GetMapping("/md/billingMaster/mdb02/grids/1")
	public List<BflrmaDTO> getMdb02GridList(@ModelAttribute BflrmaDTO param){
		return billingMasterService.getMdb02List(param);
	}

	/*  saveMdb02List - MDB02 그리드 리스트 저장
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : @RequestBody RequestDTO<BflrmaDTO> params - 저장할 데이터를 담은 고정물류비요율 DTO
	*   출력 PARAMETER : int - 처리 결과
	*   설명      	: 전달된 데이터를 기반으로 그리드 리스트를 저장하고, 처리 결과를 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	@PostMapping("/md/billingMaster/mdb02/grids/1")
	public int saveMdb02List(@RequestBody RequestDTO<BflrmaDTO> params) {
		return billingMasterService.saveMdb02List(params);
	}

	//=========================================================//
	//==========================mdb03==========================//
	//=========================================================//
	/*  getMdb03Init - MDB03 택배비 초기 데이터 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : @AuthenticationPrincipal UserVO userInfo - 현재 사용자 정보
	*   출력 PARAMETER : Map<String, Object> - 초기 데이터 맵
	*   설명      	: MDB03 화면의 초기 데이터를 조회
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	@GetMapping(value = "/md/billingMaster/mdb03/init")
	public Map<String, Object> getMdb03Init(@AuthenticationPrincipal UserVO userInfo) {
		return billingMasterService.getMdb03init(userInfo);
	}

	/*  getMdb03GridList - MDB03 택배비 그리드 리스트 데이터 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : @ModelAttribute BplrmaDTO param - 택배비 DTO
	*   출력 PARAMETER : List<BplrmaDTO> - 그리드 리스트
	*   설명      	: 전달된 조회 조건을 기반으로 그리드 리스트를 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	@GetMapping(value = "/md/billingMaster/mdb03/grids/1")
	public List<BplrmaDTO> getMdb03GridList(@ModelAttribute BplrmaDTO params) {
		return billingMasterService.getMdb03List(params);
	}

	/*  saveMdb03GridList - MDB03 그리드 리스트 저장
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : @RequestBody RequestDTO<BplrmaDTO> params - 저장할 데이터를 담은 택배비 DTO
	*   출력 PARAMETER : int - 처리 결과
	*   설명      	: 전달된 데이터를 기반으로 그리드 리스트를 저장하고, 처리 결과를 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	@PostMapping(value = "/md/billingMaster/mdb03/grids/1")
	public int saveMdb03GridList(@RequestBody RequestDTO<BplrmaDTO> params) {
		return billingMasterService.saveMdb03List(params);
	}

	//=========================================================//
	//==========================mdb04==========================//
	//=========================================================//

	/*  getMdb04Init - MDB04 변동운송비 초기 데이터 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : @AuthenticationPrincipal UserVO userInfo - 현재 사용자 정보
	*   출력 PARAMETER : Map<String, Object> - 초기 데이터 맵
	*   설명      	: MDB04 화면의 초기 데이터를 조회
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	@GetMapping(value = "/md/billingMaster/mdb04/init")
    public Map<String, Object> getMdb04Init(@AuthenticationPrincipal UserVO userInfo) {
        return billingMasterService.getMdb04init(userInfo);
    }


	/*  getMdb04GridList - MDB04 변동운송비 그리드 리스트 데이터 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : @ModelAttribute BvtrmaDTO param - 변동운송비 DTO
	*   출력 PARAMETER : List<BvtrmaDTO> - 그리드 리스트
	*   설명      	: 전달된 조회 조건을 기반으로 그리드 리스트를 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	@GetMapping("/md/billingMaster/mdb04/grids/1")
	public List<BvtrmaDTO> getMdb04GridList(@ModelAttribute BvtrmaDTO param) {
		return billingMasterService.getMdb04List(param);
	}

	/*  saveMdb04List - MDB04 그리드 리스트 저장
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : @RequestBody RequestDTO<BvtrmaDTO> params - 저장할 데이터를 담은 변동운송비 DTO
	*   출력 PARAMETER : int - 처리 결과
	*   설명      	: 전달된 데이터를 기반으로 그리드 리스트를 저장하고, 처리 결과를 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	@PostMapping("/md/billingMaster/mdb04/grids/1")
	public int saveMdb04List(@RequestBody RequestDTO<BvtrmaDTO> params) {
		return billingMasterService.saveMdb04List(params);
	}

	//=========================================================//
	//==========================mdb05==========================//
	//=========================================================//


	/*  getMdb05Init - MDB05 고정운송비 초기 데이터 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : @AuthenticationPrincipal UserVO userInfo - 현재 사용자 정보
	*   출력 PARAMETER : Map<String, Object> - 초기 데이터 맵
	*   설명      	: MDB05 화면의 초기 데이터를 조회
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	@GetMapping(value = "/md/billingMaster/mdb05/init")
    public Map<String, Object> getMdb05Init(@AuthenticationPrincipal UserVO userInfo) {
        return billingMasterService.getMdb05init(userInfo);
    }


	/*  getMdb05GridList - MDB05 고정운송비 그리드 리스트 데이터 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : @ModelAttribute BftrmaDTO param - 고정운송비 DTO
	*   출력 PARAMETER : List<BftrmaDTO> - 그리드 리스트
	*   설명      	: 전달된 조회 조건을 기반으로 그리드 리스트를 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	@GetMapping("/md/billingMaster/mdb05/grids/1")
	public List<BftrmaDTO> getMdb05GridList(@ModelAttribute BftrmaDTO param) {
		return billingMasterService.getMdb05List(param);
	}

	/*  saveMdb05List - MDB05 그리드 리스트 저장
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : @RequestBody RequestDTO<BftrmaDTO> params - 저장할 데이터를 담은 고정운송비 DTO
	*   출력 PARAMETER : int - 처리 결과
	*   설명      	: 전달된 데이터를 기반으로 그리드 리스트를 저장하고, 처리 결과를 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	@PostMapping("/md/billingMaster/mdb05/grids/1")
	public int saveMdb05List(@RequestBody RequestDTO<BftrmaDTO> params) {
		return billingMasterService.saveMdb05List(params);
	}

}
