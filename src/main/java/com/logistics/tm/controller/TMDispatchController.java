package com.logistics.tm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.common.dto.RequestDTO;
import com.logistics.md.domain.MdocmaDTO;
import com.logistics.md.domain.MptnmaDTO;
import com.logistics.md.service.MDC06Service;
import com.logistics.tm.domain.TMDispatchDTO;
import com.logistics.tm.service.TMDispatchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TMDispatchController{

	private final TMDispatchService tmDispatchService;

	private final MDC06Service mdc06Service;

	/*
	 * 프로그램 ID: TMA08, TMA10, TMA11, TMA12
	 *프로그램 내용: 공통
	 */
	
	/*  getTmDispatchSearchList - 공통 헤드 그리드 조회
	*   최초 생성일시	: 2023-12-13 13:50
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMDispatchDTO - 배차관리 DTO
	*   출력 PARAMETA	: List<TMDispatchDTO> - 배차관리 DTO List
	*   설명			: 배차취소, 배차현황, 운송상태수정에서 공통으로 헤드 그리드에 들어갈 데이터를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/*  배차현황 데이터 */
	@GetMapping("/tm/dispatch/common/grids/1")
	public List<TMDispatchDTO> getTmDispatchSearchList(TMDispatchDTO tmDispatch){
		return tmDispatchService.getTmDispatchSearchList(tmDispatch);
	}
	
	/*  getTmDispatchSearchItemList - 공통 아이템 그리드 조회
	*   최초 생성일시	: 2023-12-13 13:50
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMDispatchDTO - 배차관리 DTO
	*   출력 PARAMETA	: List<TMDispatchDTO> - 배차관리 DTO List
	*   설명			: 배차취소, 배차현황에서 공통으로 아이템 그리드에 들어갈 데이터를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* 배차아이템 데이터 */
	@GetMapping("/tm/dispatch/common/grids/2")
	public List<TMDispatchDTO> getTmDispatchSearchItemList(TMDispatchDTO tmDispatch){
		return tmDispatchService.getTmDispatchSearchItemList(tmDispatch);
	}

	/*  getTmDispatchInitData - 공통 Init Data
	*   최초 생성일시	: 2023-12-13 13:50
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMDispatchDTO - 배차관리 DTO
	*   출력 PARAMETA	: TMDispatchDTO - 배차관리 DTO
	*   설명			: 배차취소, 배차현황, 운송상태수정에서 사용되는 Init Data를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* init 데이터 */
	@GetMapping("/tm/dispatch/common/init")
	public TMDispatchDTO getTmDispatchInitData(TMDispatchDTO tmDispatch){
		return tmDispatchService.getTmDispatchInitData(tmDispatch);
	}
	
	/*  getDoctypeList - 배차타입 List
	*   최초 생성일시	: 2023-12-13 13:50
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: MdocmaDTO - 문서유형 마스터 테이블 DTO
	*   출력 PARAMETA	: List<MdocmaDTO> - 문서유형 마스터 테이블 DTO List
	*   설명			: 배차현황, 운송상태수정, 일별실적통계에서 검색조건에서 사용하는 배차타입 SelectBox를 구성하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* 배차타입 리스트 조회 */
	@GetMapping("/tm/dispatch/common/doctypelist")
	public List<MdocmaDTO> getDoctypeList(MdocmaDTO mdocma) {
		mdocma.setDoccate("800");
		return mdc06Service.getDoctypeList(mdocma);
	}
	
	/*  getCarrierList - 운송사 List
	*   최초 생성일시	: 2023-12-13 13:50
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMDispatchDTO - 배차관리 DTO
	*   출력 PARAMETA	: List<MptnmaDTO> - 협력업체 마스터 테이블 DTO List
	*   설명			: 일별실적통계에서 검색조건에서 사용하는 운송사 SelectBox를 구성하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* 운송사 리스트 조회 */
	@GetMapping("/tm/dispatch/common/carrierlist")
	public List<MptnmaDTO> getCarrierList(TMDispatchDTO tmDispatch) {
		return tmDispatchService.getCarrierList(tmDispatch);
	}

	/*
	 * 프로그램 ID: TMA08
	 *프로그램 내용: 배차취소
	 */
	
	/*  getTma08DispatchDoctypeList - 배차타입 List(권역납품 제외)
	*   최초 생성일시	: 2023-12-13 13:50
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMDispatchDTO - 배차관리 DTO
	*   출력 PARAMETA	: List<TMDispatchDTO> - 배차관리 DTO List
	*   설명			: 배차취소에서 검색조건에서 사용되는 배차타입 권역납품을 제외한 SelectBox를 구성하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma08: 배차취소, 권역납품 제외 DOCTYPE LIST */
	@GetMapping("/tm/dispatch/tma08/doctypelist")
	public List<TMDispatchDTO> getTma08DispatchDoctypeList(TMDispatchDTO tmDispatch) {
		tmDispatch.setDoccate("800");
		return tmDispatchService.getTma08DispatchDoctypeList(tmDispatch);
	}

	/*  deleteTma08SearchList - 배차 취소
	*   최초 생성일시	: 2023-12-13 13:50
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: RequestDTO, TMDispatchDTO - 그리드 List DTO / 배차관리 DTO
	*   출력 PARAMETA	: int
	*   설명			: 배차취소에서 배차취소시 동작하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma08: 배차 취소 */
	@PostMapping("/tm/dispatch/tma08/delete")
	public int deleteTma08SearchList(@RequestBody RequestDTO<TMDispatchDTO> tmDispatch) {
		return tmDispatchService.deleteTma08SearchList(tmDispatch);
	}

	/*
	 * 프로그램 ID: TMA10
	 *프로그램 내용: 배차현황
	 */
	
	/*  updateTma10SearchList - 메모 저장(update)
	*   최초 생성일시	: 2023-12-13 13:50
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: RequestDTO, TMDispatchDTO - 그리드 List DTO / 배차관리 DTO
	*   출력 PARAMETA	: int
	*   설명			: 배차현황페이지에서 메모를 수정하고 저장할 때 동작하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma10: 메모 수정 후 저장 */
	@PostMapping("/tm/dispatch/tma10/update")
	public int updateTma10SearchList(@RequestBody RequestDTO<TMDispatchDTO> tmDispatch) {
		return tmDispatchService.updateTma10SearchList(tmDispatch);
	}

	/*
	 * 프로그램 ID: TMA11
	 *프로그램 내용: 운송상태수정
	 */
	
	/*  getTma11DestItemList - 아이템 그리드 조회
	*   최초 생성일시	: 2023-12-13 13:50
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMDispatchDTO - 배차관리 DTO
	*   출력 PARAMETA	: List<TMDispatchDTO> - 배차관리 DTO List
	*   설명			: 운송상태수정 페이지에서 아이템 그리드에 들어갈 데이터를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma11: 도착지별 배차 아이템 데이터 */
	@GetMapping("/tm/dispatch/tma11/grids/2")
	public List<TMDispatchDTO> getTma11DestItemList(TMDispatchDTO tmDispatch){
		return tmDispatchService.getTma11DestItemList(tmDispatch);
	}
	
	/*  updateTma11SearchList - 운송상태수정(update)
	*   최초 생성일시	: 2023-12-13 13:50
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: RequestDTO, TMDispatchDTO - 그리드 List DTO / 배차관리 DTO
	*   출력 PARAMETA	: int
	*   설명			: 운송상태수정에서 체크된 데이터들을 수정해주는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma11: 데이터 수정 */
	@PostMapping("/tm/dispatch/tma11/update")
	public int updateTma11SearchList(@RequestBody RequestDTO<TMDispatchDTO> tmDispatch, HttpServletRequest request) {
		return tmDispatchService.updateTma11SearchList(tmDispatch, request);
	}
	
	/*  updateTma11SearchListAll - 운송상태수정(update)
	*   최초 생성일시	: 2023-12-13 13:50
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMDispatchDTO - 배차관리 DTO
	*   출력 PARAMETA	: int
	*   설명			: 운송상태수정에서 헤더 그리드에 체크된 데이터를 기준으로 헤더, 아이템 전부다 일괄적으로 수정해주는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma11: 데이터 수정 */
	@PostMapping("/tm/dispatch/tma11/updateall")
	public int updateTma11SearchListAll(@RequestBody TMDispatchDTO tmDispatch) {
		return tmDispatchService.updateTma11SearchListAll(tmDispatch);
	}

	/*
	 * 프로그램 ID: TMA12
	 *프로그램 내용: 일별실적통계
	 */

	/*  getTma12StaticsList - 일별실적통계 데이터
	*   최초 생성일시	: 2023-12-13 13:50
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMDispatchDTO - 배차관리 DTO
	*   출력 PARAMETA	: List<TMDispatchDTO> - 배차관리 DTO List
	*   설명			: 일별실적통계에서 그리드에 들어가는 데이터를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma12: 일별실적통계 데이터 */
	@GetMapping("/tm/dispatch/tma12/grids/1")
	public List<TMDispatchDTO> getTma12StaticsList(TMDispatchDTO tmDispatch){
		return tmDispatchService.getTma12StaticsList(tmDispatch);
	}
}