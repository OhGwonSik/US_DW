package com.logistics.tm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.domain.MdesmaDTO;
import com.logistics.md.domain.MrscmaDTO;
import com.logistics.md.service.MDC07Service;
import com.logistics.om.domain.MordmaDTO;
import com.logistics.tm.domain.TMOrderDTO;
import com.logistics.tm.service.TMOrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TMOrderController{
	private final MDC07Service mdc07Service;
	private final TMOrderService tmOrderService;
	
	/*
	 * 프로그램 ID: TMA01, TMA02, TMA03, TMA06
	 * 프로그램 내용: 공통
	 */
	
	/* 수주상태 리스트 조회 */
	
	/*  getTma01SalstatList - 수주상태 List 호출
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: McodemDTO - MD Common code DTO
	*   출력 PARAMETA	: List<McodemDTO> - MD Common code DTO List
	*   설명			: 운송오더현황, 운송오더수정 페이지에서 검색조건에 들어가야 할 수주상태 SelectBox를 구성하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@GetMapping("/tm/order/common/salstatlist")
	public List<McodemDTO> getTma01SalstatList(McodemDTO mcodem) {
		mcodem.setComcdky("SALSTAT");
		return mdc07Service.getCommonCodeSelectBox(mcodem);
	}
	
	/*  getTma01OubstatList - 출고처리상태 List 호출
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: McodemDTO - MD Common code DTO
	*   출력 PARAMETA	: List<McodemDTO> - MD Common code DTO List
	*   설명			: 운송오더현황, 운송오더수정, 배차수정 페이지에서 검색조건에 들어가야 할 출고상태 SelectBox를 구성하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* 출고상태 리스트 조회 */
	@GetMapping("/tm/order/common/oubstatlist")
	public List<McodemDTO> getTma01OubstatList(McodemDTO mcodem) {
		mcodem.setComcdky("OUBSTAT");
		return mdc07Service.getCommonCodeSelectBox(mcodem);
	}
	
	/*  getTmaDestinationList - 도착지 List 호출
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: List<MdesmaDTO> - 도착지 마스터 테이블 DTO List
	*   설명			: 긴급오더생성 페이지에서 도착지 SelectBox를 구성하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* 도착지 데이터 */
	@GetMapping("/tm/order/common/destList")
	public List<MdesmaDTO> getTmaDestinationList(TMOrderDTO transport) {
		return tmOrderService.getTmaDestinationList(transport);
	}
	
	/*  getTma01SearchData - 공통 헤드그리드 데이터
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: List<TMOrderDTO> - TM 운송오더, 배차관리 DTO List
	*   설명			: 운송오더현황, 운송오더수정, 셔틀배차, 배차수정 페이지에서 헤드 그리드에 들어가는 데이터 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* 검색 데이터 */
	@GetMapping("/tm/order/common/grids/1")
	public List<TMOrderDTO> getTma01SearchData(TMOrderDTO transport) {
		return tmOrderService.getTransportList(transport);
	}
	
	/*  getTmaOcodmaList - 차종 List 호출
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: List<TMOrderDTO> - TM 운송오더, 배차관리 DTO List
	*   설명			: 긴급오더생성, 운송오더수정 페이지에서 차종 SelectBox를 구성하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* 차종 조회 */
	@GetMapping("/tm/order/common/saatc05list")
	public List<TMOrderDTO> getTmaOcodmaList(TMOrderDTO transport) {
		return tmOrderService.getTmaOcodmaList(transport);
	}
	
	/*  getTmaInitData - 공통 InitData
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   설명			: 운송오더현황, 운송오더수정, 셔틀배차, 배차수정 페이지에서 공통으로 들어가는 InitData을 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* init 데이터 */
	@GetMapping("/tm/order/common/init")
	public TMOrderDTO getTmaInitData(TMOrderDTO transport) {
		return tmOrderService.getTmaInitData(transport);
	}
	
	/*  getTmaItemSearch - 공통 아이템 그리드
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: List<TMOrderDTO> - TM 운송오더, 배차관리 DTO List
	*   설명			: 운송오더현황, 운송오더수정, 셔틀배차, 배차수정 페이지에서 아이템 그리드에 들어가는 데이터를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* 아이템 데이터 */
	@GetMapping("/tm/order/common/grids/2")
	public List<TMOrderDTO> getTmaItemSearch(TMOrderDTO transport) {
		return tmOrderService.getTmaItemSearch(transport);
	}
	
	/*  getTmaMrscmaList - 긴급사유 List
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: MrscmaDTO - 사유 마스터 DTO
	*   출력 PARAMETA	: List<CommonDTO> - 공통 DTO List
	*   설명			: 셔틀배차, 배차수정 페이지에서 긴급배차 모달에서 사용되는 긴급사유 SelectBox를 구성하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* 사유 조회 */
	@GetMapping("/tm/order/common/reasonlist")
	public List<CommonDTO> getTmaMrscmaList(MrscmaDTO mrscma) {
		return tmOrderService.getTmaMrscmaList(mrscma);
	}
	
	/*  getCommonMordmaaList - 문서타입 List
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: Mordma - 고객 오더 마스터 DTO
	*   출력 PARAMETA	: List<MordmaDTO> - 고객 오더 마스터 DTO List
	*   설명			: 운송오더현황, 운송오더수정, 배차수정 페이지에서 검색 조건에 들어가는 문서타입 SelectBox를 구성하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* 문서타입 리스트 조회 */
	@GetMapping("/tm/order/common/doctypelist")
	public List<MordmaDTO> getCommonMordmaList(MordmaDTO mordma) {
		return tmOrderService.getCommonMordmaList(mordma);
	}
	
	
	/*
	 * 프로그램 ID: TMA02
	 * 프로그램 내용: 긴급오더생성
	 */
	
	/*  getTma02InitData - 긴급오더생성 페이지 initData
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   설명			: 긴급오더생성 페이지 InitData를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma02: init 데이터 */
	@GetMapping("/tm/order/tma02/init")
	public TMOrderDTO getTma02InitData(TMOrderDTO transport) {
		return tmOrderService.getTma02InitData(transport);
	}
	
	/*  tma02GetPostdat - 영업일자 채번
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: String
	*   설명			: 긴급오더생성 페이지에서 영업일자 채번을 하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma02: 영업일자 채번 */
	@GetMapping("/tm/order/tma02/postdat")
	public String tma02GetPostdat(TMOrderDTO transport) {
		return tmOrderService.tma02GetPostdat(transport);
	}
	
	/*  getMsuwcList - 상품 리스트 조회
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: List<TMOrderDTO> - TM 운송오더, 배차관리 DTO List
	*   설명			: 긴급오더생성 페이지에서 품목 SelectBox를 구성하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma02: 상품 리스트 조회 */
	@GetMapping("/tm/order/tma02/mskuwclist")
	public List<TMOrderDTO> getMsuwcList(TMOrderDTO transport) {
		return tmOrderService.selectTma02SkuwcList(transport);
	}
	
	/*  selectTmMordmaList - 문서유형 List
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: MordmaDTO - 고객 오더 마스터 DTO
	*   출력 PARAMETA	: List<MordmaDTO> - 고객 오더 마스터 DTO List
	*   설명			: 긴급오더생성 페이지에서 문서유형 키값이 아닌 Name값으로 보여주기 위해 Render에 필요한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma02: 문서유형 가져오기 */
	@GetMapping("/tm/order/tma02/mordmalist")
	public List<MordmaDTO>selectTmMordmaList(MordmaDTO mordma) {
		return tmOrderService.selectTmMordmaList(mordma);
	}
	
	/*  saveTransportOrder - 수기 배차 등록
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: RequestDTO<TMOrderDTO> - 그리드 List DTO / TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: int
	*   설명			: 긴급오더생성 페이지에서 긴급오더 저장하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma02: 수기 배차 등록 */
	@PostMapping("/tm/order/tma02/transordersave")
	public int saveTransportOrder(@RequestBody RequestDTO<TMOrderDTO> transport) {
		return tmOrderService.saveTransportOrder(transport);
	}
	
	
	/*
	 * 프로그램 ID: TMA03
	 * 프로그램 내용: 운송오더수정
	 */
	
	/*  tma03Update - 운송오더수정 Update 로직
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: RequestDTO<TMOrderDTO> - 그리드 List DTO / TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: int
	*   설명			: 운송오더수정 페이지에서 오더를 수정하고 저장할 때 동작하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* ocosal update */
	@PostMapping("/tm/order/tma03/orderupdate")
	public int tma03Update(@RequestBody RequestDTO<TMOrderDTO> transport) {
		return tmOrderService.tma03Update(transport);
	}

	
	/*
	 * 프로그램 ID: TMA04
	 * 프로그램 내용: 셔틀배차
	 */

	/*  getTma04ShuttleList - 셔틀리스트 검색 List
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: List<TMOrderDTO> - TM 운송오더, 배차관리 DTO List
	*   설명			: 셔틀배차 페이지에서 셔틀배차 모달 내 그리드 데이터 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma04: 셔틀리스트 검색 */
	@GetMapping("/tm/order/tma04/grids/2")
	public List<TMOrderDTO> getTma04ShuttleList(TMOrderDTO transport) {
		return tmOrderService.getTma04ShuttleList(transport);
	}
	
	/*  saveShuttlePlan - 셔틀 배차 등록
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: int
	*   설명			: 셔틀배차 페이지에서 셔틀배차 저장하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma04: 셔틀 배차 등록 */
	@PostMapping("/tm/order/tma04/shuttlesave")
	public int saveShuttlePlan(@RequestBody TMOrderDTO transport) {
		return tmOrderService.saveShuttlePlan(transport);
	}
	
	
	/*
	 * 프로그램 ID: TMA06
	 * 프로그램 내용: 배차생성
	 */
	
	/*  getTma06PlanList - 배차 계획 List
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: List<TMOrderDTO> - TM 운송오더, 배차관리 DTO List
	*   설명			: 배차수정 페이지에서 추가배차 모달 내 그리드 데이터 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma06: 배차계획 리스트 검색 */
	@GetMapping("/tm/order/tma06/grids/2")
	public List<TMOrderDTO> getTma06PlanList(TMOrderDTO transport) {
		return tmOrderService.getTma06PlanList(transport);
	}
	
	/*  getTma06CarList - 차량리스트
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: List<TMOrderDTO> - TM 운송오더, 배차관리 DTO List
	*   설명			: 셔틀배차, 배차수정 페이지에서 긴급배차 모달 내 그리드 데이터 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma06: 차량리스트 검색 */
	@GetMapping("/tm/order/tma06/grids/4")
	public List<TMOrderDTO> getTma06CarList(TMOrderDTO transport) {
		return tmOrderService.getTma06CarList(transport);
	}
	
	/*  saveAddPlan - 추가 배차 등록
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: int
	*   설명			: 배차수정 페이지에서 추가배차 저장하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma06: 추가 배차 등록 */
	@PostMapping("/tm/order/tma06/addplansave")
	public int saveAddPlan(@RequestBody TMOrderDTO transport) {
		return tmOrderService.saveAddPlan(transport);
	}
	
	/*  saveEmergencyPlan - 긴급 배차 등록
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: int
	*   설명			: 셔틀배차, 배차수정 페이지에서 긴급배차 저장하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma06: 긴급 배차 등록 */
	@PostMapping("/tm/order/tma06/emergencysave")
	public int saveEmergencyPlan(@RequestBody TMOrderDTO transport) {
		return tmOrderService.saveEmergencyPlan(transport);
	}
}
