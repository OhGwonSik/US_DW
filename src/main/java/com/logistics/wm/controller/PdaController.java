package com.logistics.wm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.InitDataDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.md.domain.MskuwcDTO;
import com.logistics.md.domain.MskuwcVO;
import com.logistics.tm.domain.TMDispatchDTO;
import com.logistics.tm.domain.TvhcmaDTO;
import com.logistics.wm.domain.InventoryDTO;
import com.logistics.wm.domain.KioskDTO;
import com.logistics.wm.domain.PdaDTO;
import com.logistics.wm.domain.PickingDTO;
import com.logistics.wm.domain.RecvDTO;
import com.logistics.wm.domain.RecvPutDTO;
import com.logistics.wm.domain.ShipEtcDTO;
import com.logistics.wm.domain.TaskDTO;
import com.logistics.wm.service.PdaService;

@RestController
public class PdaController {

	@Autowired
	private PdaService pdaService;
	 
	/*  getSkuList - 부품을(상품)을 조회하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: MskuwcDTO - 부품마스터 DTO
	*   출력 PARAMETA	: List<MskuwcVO> - 부품마스터 VO
	*   설명			: MSKUWC(부품마스터) 내에서 부품(상품)의 정보를 조회하는 메소드  
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	@GetMapping("/wm/pda/wmp20/grid")
	public List<MskuwcVO> getSkuList(@ModelAttribute MskuwcDTO mskuwcDTO){
		return pdaService.getSkuList(mskuwcDTO);
	}
	
	/*  getWasnifList - 입고 예정정보 조회하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: List<RecvDTO> - 입고-공통 DTO
	*   설명			: WASNIF(입고 예정정보)에서 입고 예정정보를 조회하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/	
	@GetMapping("/wm/pda/wmp30/grid")
	public List<RecvDTO> getWasnifList(@ModelAttribute PdaDTO pdaDTO){
		return pdaService.getWasnifList(pdaDTO);
	}
	
	/*  saveWrcvit - 창고 안으로 입고 하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: RequestDTO<PdaDTO> - PDA-파트 DTO
	*   출력 PARAMETA	: int - 저장결과 Cnt
	*   설명			: 창고 안으로 입고 하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	@PostMapping("/wm/pda/wmp30/save")
	public int saveWrcvit(@RequestBody RequestDTO<PdaDTO> paramList) {
		return pdaService.saveWrcvit(paramList);
	}
	
	/*  selectWmp40List - 창고에 입고한 부품(상품)을 조회하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: List<RecvDTO> - 입고-공통 DTO
	*   설명			: 창고에 입고한 부품(상품)을 조회하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/	
	@GetMapping("/wm/pda/wmp40/grid")
	public List<RecvDTO> selectWmp40List(@ModelAttribute PdaDTO pdaDTO){
		return pdaService.selectWmp40List(pdaDTO);
	}
	
	/*  saveWmp40List - 창고에 입고한 부품(상품)을 팔레타이징 처리 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: List<RecvDTO> - 입고-공통 DTO
	*   설명			: 창고에 입고한 부품(상품)을 팔레타이징 처리 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/	
	@PostMapping("/wm/pda/wmp40/save")
	public int saveWmp40List(@RequestBody RequestDTO<PdaDTO> pdaDTO){
		return pdaService.saveWmp40List(pdaDTO);
	}
	
	/*  getWrcvitList - 창고에 입고한 부품(상품)을 조회하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: List<RecvPutDTO> - 적치 DTO
	*   설명			: 창고에 입고한 부품(상품)을 조회하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/	
	@GetMapping("/wm/pda/wmp50/grid")
	public List<RecvPutDTO> getWrcvitList(@ModelAttribute PdaDTO pdaDTO){
		return pdaService.getWrcvitList(pdaDTO);
	}
	
	/*  saveWmpTolocky - 창고에 입고한 부품(상품)의 적치처리하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: RequestDTO<PdaDTO> - PDA-파트 DTO
	*   출력 PARAMETA	: int - 저장결과 Cnt
	*   설명			: 창고에 입고한 부품(상품)의 적치처리하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	@PostMapping("/wm/pda/wmp50/save")
	public int saveWmpTolocky(@RequestBody RequestDTO<PdaDTO> paramList) {
		return pdaService.saveWmpTolocky(paramList);
	}
	
	/*  getWshpitList - 피킹처리할 부품(상품)의 정보를 조회하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: List<PickingDTO> - 출고-피킹 DTO
	*   설명			: 피킹처리할 부품(상품)의 정보를 조회하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/	
	@GetMapping("/wm/pda/wmp60/grid")
	public List<PickingDTO> getWshpitList(@ModelAttribute PdaDTO pdaDTO){
		return pdaService.getWshpitList(pdaDTO);
	}
	
	/*  saveWmp60List - 피킹처리할 부품(상품)을 저장하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: int - 저장결과 Cnt
	*   설명			: 피킹처리할 부품(상품)을 저장하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	@PostMapping("/wm/pda/wmp60/save")
	public int saveWmp60List(@RequestBody RequestDTO<PdaDTO> requestParam) {
		return pdaService.saveWmp60List(requestParam);
	}
	
	/*  getWtakitList - 이동 처리할 부품(상품)의 정보를 조회하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: List<TaskDTO> - 작업 - 공통 DTO
	*   설명			: 이동 처리할 부품(상품)의 정보를 조회하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	@GetMapping("/wm/pda/wmp70/grid")
	public List<TaskDTO> getWtakitList (@ModelAttribute PdaDTO pdaDTO){
		return pdaService.getWtakitList(pdaDTO);
	}
	
	/*  saveWmp70List - 이동 처리 완료할 부품(상품)의 정보를 저장하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: RequestDTO<PdaDTO> - PDA-파트 DTO
	*   출력 PARAMETA	: int - 저장결과 Cnt
	*   설명			: 이동 처리 완료할 부품(상품)의 정보를 저장하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	@PostMapping("/wm/pda/wmp70/save")
	public int saveWmp70List(@RequestBody RequestDTO<PdaDTO> requestParam) {
		return pdaService.saveWmp70List(requestParam);
	}

	/*  getWstkkyList - 부품(상품)의 현 재고량을 보여주는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: List<InventoryDTO> - 출고-공통 DTO
	*   설명			: 부품(상품)의 현 재고량을 보여주는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	@GetMapping("/wm/pda/wmp80/grid")
	public List<InventoryDTO> getWstkkyList(@ModelAttribute PdaDTO pdaDTO){
		return pdaService.getWstkkyList(pdaDTO);
	}
	
	/*  getWmp90WasnifList - 키오스크 입고 예정인 정보를 조회하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: List<RecvDTO> - 입고-공통 DTO
	*   설명			: 키오스크 입고 예정인 정보를 조회하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/	
	@GetMapping("/wm/pda/wmp90/grid/1")
	public List<RecvDTO> getWmp90WasnifList(@ModelAttribute PdaDTO pdaDTO){
		return pdaService.getWmp90WasnifList(pdaDTO);
	}
	
	/*  saveWmp90 - 키오스크 입고 예정인 정보를 저장하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: int - 저장결과 Cnt
	*   설명			: 키오스크 입고 예정인 정보를 저장하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/	
	@PostMapping("/wm/pda/wmp90/save")
	public int saveWmp90(@RequestBody KioskDTO kioskDTO) {
		return pdaService.saveWmp90(kioskDTO);
	}
	
	/*  selectWrcvitList - 키오스크 입고된 정보를 조회하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: List<RecvDTO> - 입고-공통 DTO
	*   설명			: 키오스크 입고된 정보를 조회하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	@GetMapping("/wm/pda/wmp90/grid/2")
	public List<RecvDTO> selectWrcvitList(@ModelAttribute PdaDTO pdaDTO){
		return pdaService.selectWrcvitList(pdaDTO);
	}
	
	/*  selectWmp91InitList - 키오스크 수동입고 초기 데이터 호출하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: MskuwcDTO - 부품마스터 DTO
	*   설명			: 키오스크 수동입고 초기 데이터 호출하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	@GetMapping("/wm/pda/wmp91/init")
	public List<MskuwcDTO> selectWmp91InitList(@ModelAttribute PdaDTO pdaDTO){
		return pdaService.selectWmp91InitList(pdaDTO);
	}
	
	/*  getWmp91List - 키오스크 입고된 정보를 조회하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: List<RecvPutDTO> - 적치 DTO
	*   설명			: 키오스크 입고된 정보를 조회하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	@GetMapping("/wm/pda/wmp91/grid")
	public List<RecvPutDTO> getWmp91List(@ModelAttribute PdaDTO pdaDTO){
		return pdaService.getWmp91List(pdaDTO);
	}
	
	/*  getWmp92InitDataSelect - 키오스크 긴급출고 초기 데이터 호출하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: CommonDTO - 공통 DTO
	*   출력 PARAMETA	: InitDataDTO - 초기 세팅 데이터 DTO
	*   설명			: 키오스크 긴급출고 초기 데이터 호출하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/  
    @GetMapping(value = "/wm/pda/wmp92/init")    
   	public InitDataDTO getWmp92InitDataSelect(CommonDTO param) {
    	return pdaService.getWmp93Init(param);
    }
    
	/*  saveWmp92 - 키오스크 긴급출고처리할 데이터를 저장하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: RequestDTO<ShipEtcDTO> - 출고-기타출고 DTO
	*   출력 PARAMETA	: int - 저장결과 Cnt
	*   설명			: 키오스크 긴급출고처리할 데이터를 저장하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/ 
    @PostMapping(value = "/wm/pda/wmp92/save")    
   	public int saveWmp92(@RequestBody RequestDTO<ShipEtcDTO> param) {
    	return pdaService.saveWmp93Order(param);
    }
	
	/*  getWmp93InitDataSelect - PDA 긴급출고 초기 데이터 호출하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: CommonDTO - 공통 DTO
	*   출력 PARAMETA	: InitDataDTO - 초기 세팅 데이터 DTO
	*   설명			: PDA 긴급출고 초기 데이터 호출하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
    @GetMapping(value = "/wm/pda/wmp93/init")    
   	public InitDataDTO getWmp93InitDataSelect(CommonDTO param) {
    	return pdaService.getWmp93Init(param);
    }
    
	/*  saveWmp93 - PDA 긴급출고처리할 데이터를 저장하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: RequestDTO<ShipEtcDTO> - 출고-기타출고 DTO
	*   출력 PARAMETA	: int - 저장결과 Cnt
	*   설명			: PDA 긴급출고처리할 데이터를 저장하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
    @PostMapping(value = "/wm/pda/wmp93/save")    
   	public int saveWmp93(@RequestBody RequestDTO<ShipEtcDTO> param) {
    	return pdaService.saveWmp93Order(param);
    }

	/*  getDispatchPLTList - PDA 상차 리스트 정보 조회하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: List<TMDispatchDTO> - TM-셔틀배차 DTO
	*   설명			: PDA 상차 리스트 정보 조회하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	@GetMapping("/tm/pda/tmp05/grid/1")
	public List<TMDispatchDTO> getDispatchPLTList(@ModelAttribute PdaDTO pdaDTO){
		return pdaService.getDispatchPLTList(pdaDTO);
	}
	
	/*  saveTmp05List - PDA 상차 리스트 정보 저장하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: RequestDTO<PdaDTO> - PDA-파트 DTO
	*   출력 PARAMETA	: int - 저장결과 Cnt
	*   설명			: PDA 상차 리스트 정보 저장하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	@PostMapping("/tm/pda/tmp05/save")
	public int saveTmp05List(@RequestBody RequestDTO<PdaDTO> params) {
		return pdaService.saveTmp05List(params);
	}

	/*  getTmp05NoneDispatchPlt - PDA 상차할 팔레트 정보 조회하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: TMDispatchDTO - TM-셔틀배차 DTO
	*   설명			: PDA 상차할 팔레트 정보 조회하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	@GetMapping("/tm/pda/tmp06/grid/1")
	public TMDispatchDTO getTmp05NoneDispatchPlt(@ModelAttribute PdaDTO pdaDTO){
		return pdaService.getTmp05NoneDispatchPlt(pdaDTO);
	}
	
	/*  getTmp05TvhcmaList - PDA 차량의 정보를 조회하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: List<TvhcmaDTO> - 차량마스터 DTO
	*   설명			: PDA 차량의 정보를 조회하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	@GetMapping("/tm/pda/tmp06/tvhcma")
	public List<TvhcmaDTO> getTmp05TvhcmaList(@ModelAttribute PdaDTO pdaDTO){
		return pdaService.getTmp05TvhcmaList(pdaDTO);
	}
	
	/*  saveTmp06List - PDA 배차계획 및 상차 처리 하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: RequestDTO<PdaDTO> - PDA-파트 DTO
	*   출력 PARAMETA	: int - 저장결과 Cnt
	*   설명			: PDA 배차계획 및 상차 처리 하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	@PostMapping("/tm/pda/tmp06/save")
	public int saveTmp06List(@RequestBody RequestDTO<PdaDTO> params) {
		return pdaService.saveTmp06List(params);
	}
	
	/*  getTmp07DispatchPLTList - PDA 배차계획이 상차인 상태인 정보를 조회하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: List<TMDispatchDTO> - TM-셔틀배차 DTO
	*   설명			: PDA 배차계획이 상차인 상태인 정보를 조회하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	@GetMapping("/tm/pda/tmp07/grid/1")
	public List<TMDispatchDTO> getTmp07DispatchPLTList(PdaDTO pdaDTO){
		return pdaService.getTmp07DispatchPLTList(pdaDTO);
	}
	
	/*  saveTmp07List - PDA 배차계획이 상차인 상태인 계획을 취소 하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: RequestDTO<PdaDTO> - PDA-파트 DTO
	*   출력 PARAMETA	: int - 저장결과 Cnt
	*   설명			: 배차계획이 상차인 상태인 계획을 취소 하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	@PostMapping("/tm/pda/tmp07/save")
	public int saveTmp07List(@RequestBody RequestDTO<PdaDTO> params) {
		return pdaService.saveTmp07List(params);
	}
}
