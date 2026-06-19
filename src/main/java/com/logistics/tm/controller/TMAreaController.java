package com.logistics.tm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.common.dto.RequestDTO;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.domain.MptnmaDTO;
import com.logistics.md.service.MDC07Service;
import com.logistics.tm.domain.TMAreaDTO;
import com.logistics.tm.domain.TvhcmaDTO;
import com.logistics.tm.service.TMAreaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TMAreaController{

	private final TMAreaService tmAreaService;

	private final MDC07Service mdc07Service;
	
	/*
	 *프로그램 내용: 공통
	 */
	
	/*  getTmAreaInitData - 수주상태 List 호출
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   설명			: 권역변경, 권역배차에서 init Data를 가져오는 메소드 
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@GetMapping("/tm/area/common/initdata")
	public TMAreaDTO getTmAreaInitData(TMAreaDTO tmArea) {
		return tmAreaService.getTmAreaInitData(tmArea);
	}
	
	/*  getTmAreaTrareky - usernam 생성
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: String
	*   설명			: 운송권역상세설정, 생산차종설정, 대표품목설정에서 usernam을 생성하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* USERNAM 생성 */
	@GetMapping("/tm/area/common/usernam")
	public String getTmAreaUsernam(TMAreaDTO tmArea){
		return tmAreaService.getUsernam(tmArea);
	}
	
	/*  getTmAreaMdesmaDestList - 도착지 List
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO List
	*   설명			: 생산차종설정, 배차취소, 배차현황, 운송상태수정, 일별실적통계, 권역배차에서 도착지 SelectBox를 구성하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* MDESMA 도착지리스트 조회 */
	@GetMapping("/tm/area/common/destlist")
	public List<TMAreaDTO> getTmAreaMdesmaDestList(TMAreaDTO tmArea){
		return tmAreaService.getTmAreaMdesmaDestList(tmArea);
	}

	/*
	 * 프로그램 ID: TMM06
	 *프로그램 내용: 운송권역설정
	 */
	
	/*  getTmAreaSearchList - 운송권역설정 그리드
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO List
	*   설명			: 권역변경, 권역배차에서 init Data를 가져오는 메소드 
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM06 TAREHD 헤더 조회 */
	@GetMapping("/tm/area/tmm06/grids/1")
	public List<TMAreaDTO> getTmAreaSearchList(TMAreaDTO tmArea){
		return tmAreaService.getTmAreaSearchList(tmArea);
	}
	
	/*  getTmAreaVehiList - 창고별 차량 List
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TvhcmaDTO> - 권역배차, 마스터 DTO List
	*   설명			: 운송권역상세설정, 운송상태수정, 권역배차에서 창고별 차량 SelectBox, 모달 내 그리드에 들어가는 데이터를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* 창고별 차량리스트 데이터 */
	@GetMapping("/tm/area/tmm06/vehilist")
	public List<TvhcmaDTO> getTmAreaVehiList(TMAreaDTO tmArea){
		return tmAreaService.getTmAreaVehiList(tmArea);
	}
	
	/*  getTmAreaCdcate1List - 시도 List
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO
	*   설명			: 운송권역상세설정에서 시도 SelectBox를 구성하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@GetMapping("/tm/area/tmm06/cdcate1list")
	/* TMM06 V_MCODEM 시도 조회 */
	public List<TMAreaDTO> getTmAreaCdcate1List(TMAreaDTO tmArea){
		return tmAreaService.getTmAreaCdcate1List(tmArea);
	}
	
	/*  saveTmAreaList - 운송권역 헤더 수정 및 저장
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: RequestDTO, TMAreaDTO - 그리드 List DTO / 권역배차, 마스터 DTO
	*   출력 PARAMETA	: int
	*   설명			: 운송권역상세설정에서 운송권역설정 그리드 수정 및 저장하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* 운송권역 헤더 수정 및 저장 */
	@PostMapping("/tm/area/tmm06/tarehdsave")
	public int saveTmAreaList(@RequestBody RequestDTO<TMAreaDTO> tmArea){
		return tmAreaService.saveTmArea(tmArea);
	}
	
	/*  getTmAreaPostalList - 운송권역상세설정 그리드 조회
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO List
	*   설명			: 운송권역상세설정에서 운송권역상세설정 그리드에 들어가는 데이터를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM06 US_DW MPOSTM view 조회 */
	@GetMapping("/tm/area/tmm06/grids/2")
	public List<TMAreaDTO> getTmAreaPostalList(TMAreaDTO tmArea){
		return tmAreaService.getTmAreaPostalList(tmArea);
	}
	
	/*  getTmAreaMpostmSearchList - 운송권역상세설정 모달 내 그리드 조회
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO List
	*   설명			: 운송권역상세설정에서 모달 내 그리드 데이터를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM06 MPOSTM 데이터 조회 */
	@GetMapping("/tm/area/tmm06/mpostmlist")
	public List<TMAreaDTO> getTmAreaMpostmSearchList(TMAreaDTO tmArea){
		return tmAreaService.getTmAreaMpostmSearchList(tmArea);
	}
	
	/*  getTmAreaTrarekyList - 권역키 리스트 조회
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO List
	*   설명			: 운송권역상세설정에서 권역키 리스트를 initData로 가져오기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM06 권역키리스트 조회 */
	@GetMapping("/tm/area/tmm06/trarekylist")
	public List<TMAreaDTO> getTmAreaTrarekyList(TMAreaDTO tmArea){
		return tmAreaService.getTmAreaTrarekyList(tmArea);
	}
	
	/*  getTmAreaComcdtxList - 시군구 List
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO List
	*   설명			: 운송권역상세설정에서 시군구 SelectBox를 구성하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM06 V_MCODEM 시군구 조회 */
	@GetMapping("/tm/area/tmm06/comcdtxlist")
	public List<TMAreaDTO> getTmAreaComcdtxList(TMAreaDTO tmArea){
		return tmAreaService.getTmAreaComcdtxList(tmArea);
	}
	
	/*  saveTmAreaPostalList - 운송권역상세설정 그리드 데이터 추가
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: RequestDTO, TMAreaDTO - 그리드 List DTO / 권역배차, 마스터 DTO
	*   출력 PARAMETA	: int
	*   설명			: 운송권역상세설정에서 운송권역상세설정 그리드에서 그리드 데이터 추가시 동작하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM06 운송권역 아이템 저장 */
	@PostMapping("/tm/area/tmm06/tareitsave")
	public int saveTmAreaPostalList(@RequestBody RequestDTO<TMAreaDTO> tmArea){
		return tmAreaService.saveTmAreaPostalList(tmArea);
	}
	
	/*  deleteTmAreaPostalList - 운송권역상세설정 그리드 데이터 삭제
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: RequestDTO, TMAreaDTO - 그리드 List DTO / 권역배차, 마스터 DTO
	*   출력 PARAMETA	: int
	*   설명			: 운송권역상세설정에서 운송권역상세설정 그리드에서 그리드 데이터 삭제시 동작하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM06 운송권역 아이템 삭제 */
	@PostMapping("/tm/area/tmm06/tareitdelete")
	public int deleteTmAreaPostalList(@RequestBody RequestDTO<TMAreaDTO> tmArea){
		return tmAreaService.deleteTmAreaPostalList(tmArea);
	}

	/*
	 * 프로그램 ID: TMM07
	 *프로그램 내용: 생산차종설정
	 */
	
	/*  getTmAreaOcodmaSearchList - 생산차종설정 그리드 조회
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO List
	*   설명			: 생산차종설정에서 그리드 조회할 때 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM07 OCODMA 데이터 조회 */
	@GetMapping("/tm/area/tmm07/grids/1")
	public List<TMAreaDTO> getTmAreaOcodmaSearchList(TMAreaDTO tmArea){
		return tmAreaService.getTmAreaOcodmaSearchList(tmArea);
	}
	
	/*  getTmAreaMptnmaCustList - 고객 List
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<MptnmaDTO> - 고객 마스터 테이블 DTO
	*   설명			: 생산차종설정에서 고객키 SelectBox를 구성하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM07 MPTNMA 고객사리스트 조회 */
	@GetMapping("/tm/area/tmm07/custlist")
	public List<MptnmaDTO> getTmAreaMptnmaCustList(TMAreaDTO tmArea){
		return tmAreaService.getCustomerList(tmArea);
	}

	/*  updateDeleteOcodmaList - 데이터 추가 및 수정 및 삭제
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: RequestDTO, TMAreaDTO - 그리드 List DTO / 권역배차, 마스터 DTO
	*   출력 PARAMETA	: int
	*   설명			: 생산차종설정에서 데이터 추가 및 수정 및 삭제할 때 동작하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM07 OCODOM 데이터 수정 및 삭제 */
	@PostMapping("/tm/area/tmm07/ocodmasave")
	public int updateDeleteOcodmaList(@RequestBody RequestDTO<TMAreaDTO> tmArea) {
		return tmAreaService.updateDeleteOcodmaList(tmArea);
	}

	/*
	 * 프로그램 ID: TMM08
	 *프로그램 내용: 대표품목설정
	 */
	
	/*  getTmAreaOcatmaSearchList - 그리드 조회
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO List
	*   설명			: 대표품목설정에서 그리드에 들어가는 데이터 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM08 OCATMA 데이터 조회 */
	@GetMapping("/tm/area/tmm08/grids/1")
	public List<TMAreaDTO> getTmAreaOcatmaSearchList(TMAreaDTO tmArea){
		return tmAreaService.getTmAreaOcatmaSearchList(tmArea);
	}
	
	/*  getTmm08InitData - init데이터
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   설명			: 대표품목설정 initData
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM08 DOCTYPE RELATION LIST 조회 */
	@GetMapping("/tm/area/tmm08/init")
	public TMAreaDTO getTmm08InitData(TMAreaDTO tmArea){
		return tmAreaService.getTmm08InitData(tmArea);
	}
	
	/*  insertUpdateDeleteOcodmaList - 그리드 조회
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: RequestDTO, TMAreaDTO - 그리드 List DTO / 권역배차, 마스터 DTO
	*   출력 PARAMETA	: int
	*   설명			: 대표품목설정에서 그리드 추가, 수정, 삭제할 때 돌아가는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM08 데이터 저장 */
	@PostMapping("/tm/area/tmm08/ocatmasave")
	public int insertUpdateDeleteOcodmaList(@RequestBody RequestDTO<TMAreaDTO> tmArea) {
		return tmAreaService.insertUpdateDeleteOcodmaList(tmArea);
	}


	/*
	 * 프로그램 ID: TMA13
	 *프로그램 내용: 입차승인
	 */
	
	/*  getTmAreaTvhentSearchList - 그리드 조회
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO List
	*   설명			: 입차승인에서 그리드에 들어갈 데이터 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMA13 TVHENT 데이터 조회 */
	@GetMapping("/tm/area/tma13/grids/1")
	public List<TMAreaDTO> getTmAreaTvhentSearchList(TMAreaDTO tmArea){
		return tmAreaService.getTmAreaTvhentSearchList(tmArea);
	}
	
	/*  getTmAreaTplfitSearchList - 모달 내 그리드 조회
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO List
	*   설명			: 입차승인에서 모달 내 그리드에 들어갈 데이터 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMA13 TPLFIT 데이터 조회 */
	@GetMapping("/tm/area/tma13/picflphlist")
	public List<TMAreaDTO> getTmAreaTplfitSearchList(TMAreaDTO tmArea){
		return tmAreaService.getTmAreaTplfitSearchList(tmArea);
	}
	
	/*  getEntstatList - 입차요청상태 List
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: McodemDTO - MD Common code DTO
	*   출력 PARAMETA	: List<McodemDTO> - MD Common code DTO List
	*   설명			: 입차승인에서 입차요청상태 체크박스를 구성하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* 입차요청상태 리스트 조회 */
	@GetMapping("/tm/area/tma13/entstatlist")
	public List<McodemDTO> getEntstatList(McodemDTO mcodem) {
		mcodem.setComcdky("ENTSTAT");
		return mdc07Service.getCommonCodeSelectBox(mcodem);
	}
	
	/*  updateTmAreaTvhent - 입차승인
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: int
	*   설명			: 입차승인에서 입차승인할 때 돌아가는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma13 TVHENT 입차승인 수정 */
	@PostMapping("/tm/area/tma13/updateentstat")
	public int updateTmAreaTvhent(@RequestBody TMAreaDTO tmArea) {
		return tmAreaService.updateTmAreaTvhent(tmArea);
	}

	/*
	 * 프로그램 ID: TMA05
	 *프로그램 내용: 권역변경
	 */
	
	/*  getTmAreaTplnhdSearchList - 헤더 그리드 조회
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO
	*   설명			: 권역변경에서 헤더 그리드에 들어갈 데이터를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMA05 TPLNHD 데이터 조회 */
	@GetMapping("/tm/area/tma05/grids/1")
	public List<TMAreaDTO> getTmAreaTplnhdSearchList(TMAreaDTO tmArea){
		return tmAreaService.getTmAreaTplnhdSearchList(tmArea);
	}
	
	/*  getTmAreaTplnitSearchList - 아이템 그리드 조회
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO
	*   설명			: 권역변경에서 아이템 그리드에 들어갈 데이터를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMA05 TPLNIT 데이터 조회 */
	@GetMapping("/tm/area/tma05/grids/2")
	public List<TMAreaDTO> getTmAreaTplnitSearchList(TMAreaDTO tmArea){
		return tmAreaService.getTmAreaTplnitSearchList(tmArea);
	}
	
	/*  getTmAreaTplnhdPopupSearchList - 모달 내 그리드 조회
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO
	*   설명			: 권역변경에서 모달 내 그리드에 들어갈 데이터를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMA05 TPLNHD 모달 데이터 조회 */
	@GetMapping("/tm/area/tma05/grids/3")
	public List<TMAreaDTO> getTmAreaTplnhdPopupSearchList(TMAreaDTO tmArea){
		return tmAreaService.getTmAreaTplnhdPopupSearchList(tmArea);
	}
	
	/*  saveTmAreaTplnhdSearchList - 배차 조정
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: RequestDTO, TMAreaDTO - 그리드 List DTO / 권역배차, 마스터 DTO
	*   출력 PARAMETA	: int
	*   설명			: 권역변경에서 배차조정 모달에서 저장을 할 때 돌아가는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMA05 TPLNHD 권역키 조정 */
	@PostMapping("/tm/area/tma05/tplnhdsave")
	public int saveTmAreaTplnhdSearchList(@RequestBody RequestDTO<TMAreaDTO> tmArea){
		return tmAreaService.updateTmAreaTplnhd(tmArea);
	}
	
	/*  deleteTmAreaTplnitSearchList - 배차 취소
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: RequestDTO, TMAreaDTO - 그리드 List DTO / 권역배차, 마스터 DTO
	*   출력 PARAMETA	: int
	*   설명			: 권역변경에서 배차취소 모달에서 취소(저장)할 때 돌아가는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMA05 TPLNIT 권역아이템 배차취소 */
	@PostMapping("/tm/area/tma05/delete")
	public int deleteTmAreaTplnitSearchList(@RequestBody RequestDTO<TMAreaDTO> tmArea){
		return tmAreaService.deleteTmAreaTplnitSearchList(tmArea);
	}
	

	/*
	 * 프로그램 ID: TMA15
	 *프로그램 내용: 권역배차
	 */
	
	/*  getTmAreaOcosalSearchList - 그리드 조회
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO LiST
	*   설명			: 권역배차에서 그리드에 들어갈 데이터를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMA15 OCOSAL 권역리스트 조회 */
	@GetMapping("/tm/area/tma15/grids/1")
	public List<TMAreaDTO> getTmAreaOcosalSearchList(TMAreaDTO tmArea){
		return tmAreaService.getTmAreaOcosalSearchList(tmArea);
	}
	
	/*  saveAreaPlan - 권역배차 저장
	*   최초 생성일시	: 2023-12-12 15:20
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: RequestDTO, TMAreaDTO - 그리드 List DTO / 권역배차, 마스터 DTO
	*   출력 PARAMETA	: int
	*   설명			: 권역배차에서 권역배차 모달에서 저장할 때 돌아가는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMA15 권역배차 등록 */
	@PostMapping("/tm/area/tma15/tplnhdsave")
	public int saveAreaPlan(@RequestBody RequestDTO<TMAreaDTO> tmArea) {
		return tmAreaService.saveAreaPlan(tmArea);
	}
	
}
