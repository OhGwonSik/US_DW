package com.logistics.wm.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.InitDataDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.wm.domain.InventoryDTO;
import com.logistics.wm.domain.WstkdyDTO;
import com.logistics.wm.domain.WtrhisDTO;
import com.logistics.wm.service.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InventoryController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private final InventoryService inventoryService;
	
	/*  getWmi10SKUList - 재고조회(SKU탭) List호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고DTO
	*   출력 PARAMETA	: List<InventoryDTO> - 재고DTO 배열
	*   설명			: 재고조회페이지에서 SKU기준으로 재고목록을 호출하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
    @GetMapping(value = "/wm/inventory/wmi10/grids/1")
    public List<InventoryDTO> getWmi10SKUList(@ModelAttribute InventoryDTO param) {
    	return inventoryService.getWmi10SKUList(param);
    }
    
    /*  selectWmi10LOCAList - 재고조회(로케이션별탭) List호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고DTO
	*   출력 PARAMETA	: List<InventoryDTO> - 재고DTO 배열
	*   설명			: 재고조회페이지에서 로케이션기준으로 재고목록을 호출하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
    @GetMapping(value = "/wm/inventory/wmi10/grids/2")
    public List<InventoryDTO> getWmi10LOCAList(@ModelAttribute InventoryDTO param) {
    	return inventoryService.selectWmi10LOCAList(param);
    }
	
    /*  getWmi10LOTList - 재고조회(LOT탭) List호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고DTO
	*   출력 PARAMETA	: List<InventoryDTO> - 재고DTO 배열
	*   설명			: 재고조회페이지에서 LOT NUMBER기준으로 재고목록을 호출하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
    @GetMapping(value = "/wm/inventory/wmi10/grids/3")
    public List<InventoryDTO> getWmi10LOTList(@ModelAttribute InventoryDTO param) {
    	return inventoryService.getWmi10LOTList(param);
    }
    
    /*  getWmi10Init - 재고조회 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: Map<String, Object> - 호출한 데이터를 담을 Map
	*   설명			: 재고조회 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
    @GetMapping(value = "/wm/inventory/wmi10/init/1")
    public Map<String, Object> getWmi10Init(@ModelAttribute CommonDTO common) {
    	return inventoryService.getWmi10Init(common);
    }
    
    /*  getWmi12Init - 재고상태변경처리 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고DTO
	*   출력 PARAMETA	: Map<String, Object> - 호출한 데이터를 담을 Map
	*   설명			: 재고조회 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
    @GetMapping(value = "/wm/inventory/wmi12/init/1")
    public Map<String, Object> getWmi12Init(@ModelAttribute InventoryDTO inventoryDTO){
    	return inventoryService.getWmi12Init(inventoryDTO);
    }
    
    /*  getWmi12WstkkyList - 재고상태변경 List호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고DTO
	*   출력 PARAMETA	: List<InventoryDTO> - 재고DTO 배열
	*   설명			: 재고상태변경처리 페이지에서 상태를 변경할 재고목록을 호출하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
    @GetMapping(value = "/wm/inventory/wmi12/grids/1")
    public List<InventoryDTO> getWmi12WstkkyList(@ModelAttribute InventoryDTO param){
    	return inventoryService.getWmi12WstkkyList(param);
    }
    
    /*  saveWmi12List - 재고상태변경처리
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<InventoryDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int - 성공시 1 반환
	*   설명			: 재고상태변경처리페이지에서 재고상태를 변경하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
    @PostMapping(value = "/wm/inventory/wmi12/grids/1")
    public int saveWmi12List(@RequestBody RequestDTO<InventoryDTO> param, CommonDTO common) {
    	return inventoryService.saveWmi12(param, common);
    }
    
    /*  getWmi20Init -  재고실사지시등록 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고DTO
	*   출력 PARAMETA	: Map<String , Object> - 호출한 데이터를 담을 Map
	*   설명			: 재고실사지시등록 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
    @GetMapping(value = "/wm/inventory/wmi20/init/1")
    public Map<String, Object> getWmi20Init(@ModelAttribute InventoryDTO inventoryDTO){
    	return inventoryService.getWmi20Init(inventoryDTO);
    }
    
    /*  getWmi20List - 재고 리스트 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고 DTO
	*   출력 PARAMETA	: List<InventoryDTO> - 입하 DTO의 리스트
	*   설명			: 재고실사지시등록 페이지에서 실사지시를 내릴 재고 목록을 보여주는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
    @GetMapping(value = "/wm/inventory/wmi20/grids/1")
    public List<InventoryDTO> getWmi20List(@ModelAttribute InventoryDTO param){
    	return inventoryService.getWmi20List(param);
    }
    
    
    /*  saveWphyList - 재고실사지시등록 저장
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<InventoryDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	:  Map<String, String> - 성공시 재고실사지시서를 출력하기 위해 채번한 실사문서번호를 담을 Map
	*   설명			: 재고실사지시를 등록하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
    @PostMapping(value = "/wm/inventory/wmi20/grids/1")
    public Map<String, String> saveWphyList(@RequestBody RequestDTO<InventoryDTO> param, CommonDTO common) {
    	return inventoryService.saveWphyList(param, common);
    }
    
    /*  getWmi21Init - 재고실사등록 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고DTO
	*   출력 PARAMETA	: Map<String , Object> - 호출한 데이터를 담을 Map
	*   설명			: 재고실사등록 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
    @GetMapping(value = "/wm/inventory/wmi21/init/1")
    public Map<String, Object> getWmi21Init(@ModelAttribute InventoryDTO inventoryDTO){
    	return inventoryService.getWmi21Init(inventoryDTO);
    }
    
    /*  getWmi21HeadList - 재고실사등록 HeaderList 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고DTO
	*   출력 PARAMETA	: List<InventoryDTO> - 재고DTO 배열
	*   설명			: 재고실사등록 페이지 상단의 재고실사지시서목록을 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
    @GetMapping(value = "/wm/inventory/wmi21/grids/1")
    public List<InventoryDTO> getWmi21HeadList(@ModelAttribute InventoryDTO param){
    	return inventoryService.getWmi21HeadList(param);
    }
    
    /*  getWmi21ItemList - 재고실사등록 ItemList 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고DTO
	*   출력 PARAMETA	: List<InventoryDTO> - 재고DTO 배열
	*   설명			: 재고실사등록 페이지 상단의 실사번호를 바탕으로 하단의 재고실사지시서목록을 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
    @GetMapping(value = "/wm/inventory/wmi21/grids/2")
    public List<InventoryDTO> getWmi21ItemList(@ModelAttribute InventoryDTO param){
    	return inventoryService.getWmi21ItemList(param);
    }
    
    /*  saveWmi21Phy - 재고실사등록
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<InventoryDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int returnData - 성공시 1 반환
	*   설명			: 재고실사등록 페이지에서 하단 itemList에서 수정한 값을 저장하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
    @PatchMapping(value = "/wm/inbound/wmi21/grids/1")
    public int saveWmi21(@RequestBody RequestDTO<InventoryDTO> param, CommonDTO common) {
    	return inventoryService.saveWmi21Phy(param, common);
    }
    
    /*  getWmi22Init - 재고실사조정확정 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고DTO
	*   출력 PARAMETA	: Map<String , Object> - 호출한 데이터를 담을 Map
	*   설명			: 재고실사조정확정 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
    @GetMapping(value = "/wm/inventory/wmi22/init/1")
    public Map<String, Object> getWmi22Init(@ModelAttribute InventoryDTO inventoryDTO){
    	return inventoryService.getWmi22Init(inventoryDTO);
    }
    
    /*  saveWmi22 - 재고실사조정확정
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<InventoryDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int - 성공시 1 반환
	*   설명			: 재고실사지시등록에서 등록한 실사수량을 확정하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
    @PostMapping(value = "/wm/inventory/wmi22/grids/1")
    public int saveWmi22(@RequestBody RequestDTO<InventoryDTO> param, CommonDTO common) {
    	return inventoryService.saveWmi22(param, common);
    }
    
    /*  updateWmi22HeaderClosing - 재고실사조정확정 HeaderClosing
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<InventoryDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int - 성공시 1 반환
	*   설명			: 재고실사조정확정 페이지에서 상단의 실사문서 전체를 Closing처리하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
    @PatchMapping(value = "/wm/inventory/wmi22/grids/2")
    public int updateWmi22HeaderClosing(@RequestBody RequestDTO<InventoryDTO> param) {
    	return inventoryService.updateWmi22HeaderClosing(param);
    }
    
    /*  updateWmi22ItemClosing - 재고실사조정확정 ItemClosing
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<InventoryDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int - 성공시 1 반환
	*   설명			: 재고실사조정확정 페이지에서 하단의 선택한 실사문서 일부를 Closing처리하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
    @PatchMapping(value = "/wm/inventory/wmi22/grids/3")
    public int updateWmi22ItemClosing(@RequestBody RequestDTO<InventoryDTO> param) {
    	return inventoryService.updateWmi22ItemClosing(param);
    }
    
    /*  getWmi22HeaderList - 재고실사조정확정 HeaderList 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고DTO
	*   출력 PARAMETA	: List<InventoryDTO> - 재고DTO 배열
	*   설명			: 재고실사조정확정 페이지 상단의 헤더리스트를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
    @GetMapping(value = "/wm/inventory/wmi22/grids/1")
    public List<InventoryDTO> getWmi22HeaderList(@ModelAttribute InventoryDTO param){
    	return inventoryService.getWmi22HeaderList(param);
    }
    
    /*  getWmi22ItemList - 재고실사조정확정 ItemList 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고DTO
	*   출력 PARAMETA	: List<InventoryDTO> - 재고DTO 배열
	*   설명			: 재고실사조정확정 페이지 하단의 아이템리스트를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
    @GetMapping(value = "/wm/inventory/wmi22/grids/2")
    public List<InventoryDTO> getWmi22ItemList(@ModelAttribute InventoryDTO param){
    	return inventoryService.getWmi22ItemList(param);
    }
    
    /*  getWmi32InitData - 재고조정오더확정 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 입하DTO
	*   출력 PARAMETA	: Map<String, Object> - 호출한 데이터를 담을 Map
	*   설명			: 재고조정오더확정 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
    @GetMapping(value = "/wm/inventory/wmi32/init/1")
    public Map<String, Object> getWmi32InitData(@ModelAttribute InventoryDTO inventoryDTO){
    	return inventoryService.getWmi32InitData(inventoryDTO);
    }
    
    /*  getWmi32List - 재고조정오더 목록 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고DTO
	*   출력 PARAMETA	: List<InventoryDTO> - 재고DTO 배열
	*   설명			: 재고조정오더확정 페이지에서 조정 오더목록을 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
    @GetMapping(value = "/wm/inventory/wmi32/grids/1")
    public List<InventoryDTO> getWmi32List(@ModelAttribute InventoryDTO param){
    	return inventoryService.getWmi32List(param);
    }
    
    /*  saveWmi32List - 재고조정오더확정
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<InventoryDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int returnData - 성공시 1 반환
	*   설명			: 재고조정오더확정 페이지에서 조정오더를 확정하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
    @PostMapping(value = "/wm/inventory/wmi32/grids/1")
    public int saveWmi32List(@RequestBody RequestDTO<InventoryDTO> param, CommonDTO common){
    	return inventoryService.saveWmi32List(param, common);
    }
    
    
    /* 
     * 프로그램 ID : WMI11 
     * 프로그램 내용: 재고수량변경처리
     * */
    
    @GetMapping("/wm/inventory/wmi11/grids/1")
	public List<InventoryDTO> getWmi11WstkkyList(InventoryDTO param) {
		return inventoryService.getWmi11WstkkyList(param);
	}
	
	@PostMapping("/wm/inventory/wmi11/adjust")
	public int saveWmi11(@RequestBody RequestDTO<InventoryDTO> param ){
		return inventoryService.saveWmi11(param);
	}
	
	@GetMapping("/wm/inventory/wmi11/init")
	public InitDataDTO getWmvs1InitSelectBoxList(CommonDTO param){
		return inventoryService.getWmi11InitData(param);
	}
	
    /* 
     * 프로그램 ID : WMI30 
     * 프로그램 내용: 재고블락처리
     * */
	
	@GetMapping("/wm/inventory/wmi30/grids/1")
	public List<InventoryDTO> getWmi30WstkkyList(InventoryDTO param) {
		return inventoryService.getWmi30WstkkyList(param);
	}
	
	@PostMapping("/wm/inventory/wmi30/adjust")
	public int getWmi30SaveList(@RequestBody RequestDTO<InventoryDTO> param) {
		return inventoryService.saveWmi30(param);
	}
	
	@GetMapping("/wm/inventory/wmi30/init")
	public InitDataDTO getWmi30InitSelectBoxList(CommonDTO param){
		return inventoryService.getWmi30InitData(param);
	}
	
	 /* 
     * 프로그램 ID : WMI31 
     * 프로그램 내용: 블락해제처리
     * */
	
	@GetMapping("/wm/inventory/wmi31/grids/1")
	public List<InventoryDTO> getWmi31WstkkyList(InventoryDTO param) {
		return inventoryService.getWmi31WstkkyList(param);
	}
	
	@PostMapping("/wm/inventory/wmi31/adjust")
	public int saveWmi31Save(@RequestBody RequestDTO<InventoryDTO> param) throws Exception {
		return inventoryService.saveWmi31(param);
	}
	
	@GetMapping("/wm/inventory/wmi31/init")
	public InitDataDTO getWmi31InitSelectBoxList(CommonDTO param){
		return inventoryService.getWmi31InitData(param);
	}
	
	/* 
     * 프로그램 ID : WMI40 
     * 프로그램 내용: 일별재고 리스트
     * */
	@GetMapping("/wm/inventory/wmi40/grids/1")
	public List<WstkdyDTO> getWmi40WstkdyList(WstkdyDTO param) {
		return inventoryService.getWmi40WstkdyList(param);
	}
	
	/* 
     * 프로그램 ID : WMI41 
     * 프로그램 내용: 문서별 재고 추적
     * */
	@GetMapping("/wm/inventory/wmi41/grids/1")
	public List<WtrhisDTO> getWmi41WtrhisList(WtrhisDTO param) {
		return inventoryService.getWmi41WtrhisList(param);
	}
	
	/* 
     * 프로그램 ID : WMI42 
     * 프로그램 내용: 재고부품변경처리
     * */
    @GetMapping(value = "/wm/inventory/wmi42/init")
    public InitDataDTO getWmi42InitData(@ModelAttribute CommonDTO param){
    	return inventoryService.getWmi42InitData(param);
    }
    
    @GetMapping(value = "/wm/inventory/wmi42/grids/1")
    public List<InventoryDTO> getWmi42List(@ModelAttribute InventoryDTO param){
    	return inventoryService.getWmi42List(param);
    }
    
   
    @PostMapping(value = "/wm/inventory/wmi42/save")
    public int saveWmi42List(@RequestBody RequestDTO<InventoryDTO> param){
    	return inventoryService.saveWmi42List(param);
    }
}
