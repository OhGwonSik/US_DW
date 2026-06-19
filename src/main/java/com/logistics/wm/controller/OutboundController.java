package com.logistics.wm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.InitDataDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.wm.domain.AllocChangeDTO;
import com.logistics.wm.domain.PartShipOutDTO;
import com.logistics.wm.domain.PickingDTO;
import com.logistics.wm.domain.ReturnShipOutDTO;
import com.logistics.wm.domain.ShipAllocDTO;
import com.logistics.wm.domain.ShipEtcDTO;
import com.logistics.wm.domain.ShipOutDTO;
import com.logistics.wm.service.OutboundService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OutboundController {
	
	private final OutboundService outboundService;
    
   //====================================
   //  ProgramId : WMO10 
   //  ProgramName : 출고지시등록 
   //====================================    
    
    @GetMapping(value = "/wm/outbound/wmo10/init")    
	public InitDataDTO getWmo10InitDataSelect(CommonDTO param) {
		return outboundService.getWmo10InitDataSelect(param);
    }

    //WMO10 리스트 조회
    @GetMapping(value = "/wm/outbound/wmo10/grids-all")    
	public ShipAllocDTO getWmo10ListSelect(ShipAllocDTO param) {
    	return outboundService.getWmo10ListSelect(param);
    }
    
    //WMO10 Alloc
    @PostMapping(value = "/wm/outbound/wmo10/alloc")
    public ShipAllocDTO saveWmo10(@RequestBody RequestDTO<ShipAllocDTO> saveData) {
    	return outboundService.saveWmo10(saveData);
    }
    
    //WMO10 AllocCancel
    @PostMapping(value = "/wm/outbound/wmo10/alloc-cancel")
    public int saveWmo10Cancel(@RequestBody RequestDTO<ShipAllocDTO> param) {
    	return outboundService.saveWmo10Cancel(param);
    }
    
    //WMO10 Print 할당그룹키로 피킹지시서 출력여부 확인
    @GetMapping(value = "/wm/outbound/wmo10/print")    
	public int getWmo10AllgrkyByPrint(ShipAllocDTO param) {
    	return outboundService.getWmo10AllgrkyByPrint(param);
    }
    
  //====================================
  //  ProgramId : WMO11 
  //  ProgramName : 할당변경 처리 
  //====================================

    //WMO11 Init
    @GetMapping(value = "/wm/outbound/wmo11/init")    
	public InitDataDTO getWmo11InitData(CommonDTO param) {
        return outboundService.getWmo11InitData(param);
    }
    
    //WMO11 GridInit
    @GetMapping(value = "/wm/outbound/wmo11/grid/init")    
	public InitDataDTO getWmo11GridInitData(CommonDTO param) {
        return outboundService.getWmo11GridInitData(param);
    }
    
    //WMO11 Head 리스트 조회
    @GetMapping(value = "/wm/outbound/wmo11/grid/1")    
	public List<AllocChangeDTO> getWmo11HeadList(AllocChangeDTO param) {
    	return outboundService.getWmo11HeadList(param);
    }
    
    //WMO11 Sku 리스트 조회
    @GetMapping(value = "/wm/outbound/wmo11/grid/2")    
	public List<AllocChangeDTO> getWmo11ItemList(AllocChangeDTO param) {
    	return outboundService.getWmo11ItemList(param);
    }
    
    //WMO11 저장
    @PatchMapping(value = "/wm/outbound/wmo11/wmo11AllocChange")
    @ResponseBody
    public int saveWmo11Change(@RequestBody RequestDTO<AllocChangeDTO> param){
    	return outboundService.saveWmo11Change(param);
    }
    
    
  //====================================
  //  ProgramId : WMO20 
  //  ProgramName : 피킹처리 
  //====================================
    
    @GetMapping(value = "/wm/outbound/wmo20/init")    
	public InitDataDTO getWmo20InitDataSelect(CommonDTO param) {
    	return outboundService.getWmo20Init(param);
    }
    
    @GetMapping(value = "/wm/outbound/wmo20/grids-all")    
	public PickingDTO getWmo20List(PickingDTO param) {
    	return outboundService.getWmo20List(param);
    }
    
    @PostMapping(value = "/wm/outbound/wmo20/diffSave")
    public int setWmo20Save(@RequestBody RequestDTO<PickingDTO> param) {
    	return outboundService.saveWmo20(param);
    }
    
    @PostMapping(value = "/wm/outbound/wmo20/picking")
    public int saveWmo20Pick(@RequestBody RequestDTO<PickingDTO> param) {
    	return outboundService.saveWmo20Pick(param);
    }
    
    @PatchMapping(value = "/wm/outbound/wmo20/cancel")
    public int setWmo20PickCancel(@RequestBody RequestDTO<PickingDTO> param) {
    	return outboundService.saveWmo20PickCancel(param);
    }
    
   //====================================
   //  ProgramId : WMO30 
   //  ProgramName : 출고확정 
   //====================================
    
    @GetMapping(value = "/wm/outbound/wmo30/init")    
	public InitDataDTO getWmo30InitDataSelect(CommonDTO param) {
        return outboundService.getWmo30Init(param);
    }
    
    //WMO30 Head리스트 조회
    @GetMapping(value = "/wm/outbound/wmo30/grids/1")    
	public List<ShipOutDTO> getWmo30HeadList(ShipOutDTO param) {
    	return outboundService.getWmo30HeadList(param);
    }
    
 	//WMO30 Item리스트 조회
    @GetMapping(value = "/wm/outbound/wmo30/grids/2")    
	public List<ShipOutDTO> getWmo30ItemList(ShipOutDTO param) {
    	return outboundService.getWmo30ItemList(param);
    }
    
    //WMO30 Save ( 그룹완료 ) 
    @PostMapping(value = "/wm/outbound/wmo30/headShipout")
    public int saveWmo30HeadShipout(@RequestBody RequestDTO<ShipOutDTO> param) {
    	return outboundService.saveWmo30HeadShipout(param);
    }
    
    //WMO30 Save ( 오더완료 )
    @PostMapping(value = "/wm/outbound/wmo30/itemShipout")
    public int saveWmo30ItemShipout(@RequestBody RequestDTO<ShipOutDTO> param) {
    	return outboundService.saveWmo30ItemShipout(param);
    }
    
  //====================================
  //  ProgramId : WMO31 
  //  ProgramName : 부분출고처리 
  //====================================
    
    @GetMapping(value = "/wm/outbound/wmo31/init")    
	public InitDataDTO getWmo31InitDataSelect(CommonDTO param) {
        return outboundService.getWmo31Init(param);
    }
    
 	//WMO31 리스트 조회
    @GetMapping(value = "/wm/outbound/wmo31/grids/1")    
	public List<PartShipOutDTO> getWmo31ListSelect(PartShipOutDTO param) {
    	return outboundService.getWmo31List(param);
    }
    
    //WMO31 Save ( 전표별 출고 -> 오더별 출고 변경 예정 )
    @PostMapping(value = "/wm/outbound/wmo31/resoPartShipout")
    public PartShipOutDTO saveWmo31ResoShipout(@RequestBody RequestDTO<PartShipOutDTO> param) {
    	return outboundService.saveWmo31ResoPartShipout(param);
    }
    
  //====================================
  //  ProgramId : WMO40 
  //  ProgramName : 기타출고 
  //====================================
    
    @GetMapping(value = "/wm/outbound/wmo40/init")    
   	public InitDataDTO getWmo40InitDataSelect(CommonDTO param) {
    	return outboundService.getWmo40Init(param);
    }
    
    @PostMapping(value = "/wm/outbound/wmo40/save")
    public int saveWmo40(@RequestBody RequestDTO<ShipEtcDTO> param){
		return outboundService.saveWmo40(param);
    }
    
  //====================================
  //  ProgramId : WMO50 
  //  ProgramName : 반출출고 지시등록 
  //====================================  
    
    //WMO50 Head리스트 조회
    @GetMapping(value = "/wm/outbound/wmo50/grids-all")    
	public ReturnShipOutDTO getWmo50List(ReturnShipOutDTO param) {
    	return outboundService.getWmo50List(param);
    }
    
    //WMO50 Head리스트 조회
    @GetMapping(value = "/wm/outbound/wmo50/grids/1")    
	public List<ReturnShipOutDTO> getWmo50HeadList(ReturnShipOutDTO param) {
    	return outboundService.getWmo50HeadList(param);
    }
    
 	//WMO50 Item리스트 조회
    @GetMapping(value = "/wm/outbound/wmo50/grids/2")    
	public List<ReturnShipOutDTO> getWmo50ItemList(ReturnShipOutDTO param) {
    	return outboundService.getWmo50ItemList(param);
    }
    
    //WMO50 Print 할당그룹키로 피킹지시서 출력여부 확인
    @GetMapping(value = "/wm/outbound/wmo50/print")    
	public int getWmo50AllgrkyByPrint(ReturnShipOutDTO param) {
    	return outboundService.getWmo50AllgrkyByPrint(param);
    }
    
    @PostMapping(value ="/wm/outbound/wmo50/alloc")
    public ReturnShipOutDTO saveWmo50(@RequestBody RequestDTO<ReturnShipOutDTO> param) {
    	return outboundService.saveWmo50(param);
    }
    
    @PostMapping(value ="/wm/outbound/wmo50/alloc-cancel")
    public int saveWmo50Cancel(@RequestBody RequestDTO<ReturnShipOutDTO> param) {
    	return outboundService.saveWmo50Cancel(param);
    }
    
  //====================================
  //  ProgramId : WMO51 
  //  ProgramName : 반출출고 확정 
  //==================================== 
    
    @GetMapping(value = "/wm/outbound/wmo51/init")    
	public InitDataDTO getWmo51InitDataSelect(CommonDTO param) {
        return outboundService.getWmo51Init(param);
    }
    
    @GetMapping(value = "/wm/outbound/wmo51/grids/1")    
	public List<ReturnShipOutDTO> getWmo51HeadList(ReturnShipOutDTO param) {
    	return outboundService.getWmo51HeadList(param);
    }
    
    @GetMapping(value = "/wm/outbound/wmo51/grids/2")    
	public List<ReturnShipOutDTO> getWmo51ItemList(ReturnShipOutDTO param) {
    	return outboundService.getWmo51ItemList(param);
    }
    @PostMapping(value = "/wm/outbound/wmo51/headShipout")
    public int saveWmo51HeadShipout(@RequestBody RequestDTO<ReturnShipOutDTO> param) {
    	return outboundService.saveWmo51HeadShipout(param);
    }
    
    @PostMapping(value = "/wm/outbound/wmo51/itemShipout")
    public int saveWmo51ItemShipout(@RequestBody RequestDTO<ReturnShipOutDTO> param) {
    	return outboundService.saveWmo51ItemShipout(param);
    }

    @GetMapping(value = "/wm/outbound/wmo60/grids/1")
    public List<ShipOutDTO> getwmo60WshpitList(ShipOutDTO param) {
        return outboundService.getWmo60WshpitList(param);
    }

    @PostMapping(value = "/wm/outbound/wmo60/shipoutCancel")
    public int saveWmo60ShipoutCancel(@RequestBody RequestDTO<ShipOutDTO> param) {
        return outboundService.saveWmo60ShipoutCancel(param);
    }
}
