package com.logistics.wm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.wm.domain.WMReportDTO;

@Mapper
public interface WMReportMapper {
	
	//재고실사지시서
	List<WMReportDTO> getStockInspectionOrder(WMReportDTO report);
	//피킹지시서(일반)
	List<WMReportDTO> getPickingOrderByNormal(WMReportDTO report);
	//피킹지시서(반출)
	List<WMReportDTO> getPickingOrderByReturn(WMReportDTO report);
	//피킹지시서 - 품목수(일반)
	WMReportDTO getPickingOrderItemByNormal(WMReportDTO report);
	//피킹지시서 - 품목수(반출)
	WMReportDTO getPickingOrderItemByReturn(WMReportDTO report);
	
	//적치지시서
	List<WMReportDTO> getPileOrder(WMReportDTO report);
	//적치지시서 - (품목수)
	WMReportDTO getPileOrderSummary(WMReportDTO report);
	
	//팔레트라벨
	List<WMReportDTO> getPalletLabel(WMReportDTO report);
	
	//프린트 프로시저
	WMReportDTO wmAddPrintList(WMReportDTO report);
	//프린트 로그 카운트
	int getWmPrintLogCount(WMReportDTO report);
	WMReportDTO wmAddPrintList2(WMReportDTO wmReportDTO);
	
	//납품지시서
	List<WMReportDTO> getShipOrder(WMReportDTO wmReportDTO);
	WMReportDTO getShipOrderItem(WMReportDTO wmReportDTO);
}
