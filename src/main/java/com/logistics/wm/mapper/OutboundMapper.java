package com.logistics.wm.mapper;

import java.util.List;
import java.util.Map;

import com.logistics.wm.domain.*;
import org.apache.ibatis.annotations.Mapper;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.RequestDTO;

@Mapper
public interface OutboundMapper { 
	
	//WMO10
	List<ShipAllocDTO> selectWshpitHeadList(ShipDTO param);
	List<ShipAllocDTO> selectWshpitItemList(ShipDTO param);
	int updateWshpit(RequestDTO<ShipAllocDTO> allocList);
	int updateWshpitHead(RequestDTO<ShipAllocDTO> headList);
	String getAllgrky();
	WshpitDTO callSpWmAllocateGrp(ShipAllocDTO allocDto);
	void callSpWmCtrstokkyAllocCancelAtWmo10(ShipAllocDTO updateDto);
	int getWmo10AllgrkyByPrint(ShipAllocDTO param);
		
	//WMO11
	List<AllocChangeDTO> selectWmo11HeadList(CommonDTO param);
	List<AllocChangeDTO> selectWmo11ItemList(AllocChangeDTO param);
	int updateWmo11Wshpit(Map<String, Object> param);
	int insertWmo11Wtakit(AllocChangeDTO updateRow);
	String getTaskoky();
	void callSpWmAllocateCancel(AllocChangeDTO updateRow);
	
	//WMO20
	List<PickingDTO> selectWmo20HeadPickingList(PickingDTO param);
	List<PickingDTO> selectWmo20ItemPickingList(PickingDTO param);
	int updateWtakit(RequestDTO<PickingDTO> param);
	PickingDTO callSpWmPicking(PickingDTO updateRow);
	PickingDTO callSpWmCtrstokkyPickCancel(PickingDTO updateRow);
	PickingDTO callSpWmDiffPicking(PickingDTO updateRow);
	
	//WMO30
	List<ShipOutDTO> getWmo30HeadList(ShipOutDTO param);
	List<ShipOutDTO> getWmo30ItemList(ShipOutDTO reqDTO);
	void callSpWmAllocateChange(AllocChangeDTO updatRow);
	void callSpWmCtrstokkyAllocCancel(AllocChangeDTO updatRow);
	
	//WMO31 
	List<PartShipOutDTO> getWmo31List(PartShipOutDTO param);
	void callSpWmShiping(ShipOutDTO updateArr);
	void callSpWmShipingAtWmo31(PartShipOutDTO paramArr);
	void callSpWmShipingAtWmo50(ReturnShipOutDTO paramArr);
	
	//WMO40
	int getShpdcit();
	int saveWmo40(ShipEtcDTO param);
	String getCooutkyByWshipt();
	boolean saveSalesOrder(ShipEtcDTO param);
	void callOcosalOrder(ShipEtcDTO etcDTO);
	
	//WMO50
	List<ReturnShipOutDTO> getWmo50HeadList(ReturnShipOutDTO param);
	List<ReturnShipOutDTO> getWmo50ItemList(ReturnShipOutDTO param);
	int updateWshpitByReturnShipOut(RequestDTO<ReturnShipOutDTO> param);
	int updateWshpitHeadByReturnShipOut(RequestDTO<ReturnShipOutDTO> headList);
	void callSpWmAllocateGrpByReturnShipOut(ReturnShipOutDTO updateList);
	void callSpWmCtrstokkyAllocCancelByReturnShipOut(ReturnShipOutDTO updateRow);
	int getWmo50AllgrkyByPrint(ReturnShipOutDTO param);
	
	//WMO51
	List<ReturnShipOutDTO> getWmo51HeadList(ReturnShipOutDTO param);
	List<ReturnShipOutDTO> getWmo51ItemList(ReturnShipOutDTO param);
	void callSpWmShipingByReturn(ReturnShipOutDTO param);

	//WMO60
	List<ShipOutDTO> getWmo60WshpitList(ShipOutDTO param);
	void callSpWmShipoutCancel(ShipOutDTO param);
}
