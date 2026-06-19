package com.logistics.wm.mapper;

import java.util.List;

import com.logistics.wm.domain.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InventoryMapper {
	
	/* wmi10: 재고조회 */
	List<InventoryDTO>selectWmi10SKUList(InventoryDTO param);
	List<InventoryDTO>selectWmi10LOCAList(InventoryDTO param);
	List<InventoryDTO>selectWmi10LOTList(InventoryDTO param);
	
	/*  프로그램 ID : WMI12 - 프로그램 내용: 재고상태변경처리 */
	List<InventoryDTO> selectWmi12WstkkyList(InventoryDTO param);
	List<InventoryDTO> selectWmi12BeforeData(InventoryDTO param);
	int saveWmi12WadjitBeforeList(InventoryDTO param);
	int saveWmi12WadjitAfterList(InventoryDTO param);
	
	/* wmi20: 재고실사지시등록 */
	List<InventoryDTO> selectWmi20WstkkyList(InventoryDTO param);
	int saveWphyList(InventoryDTO param);
	String getPhysoky();
	
	/* wmi21: 재고실사등록 */
	List<InventoryDTO> selectWmi21HeaderList(InventoryDTO param);
	List<InventoryDTO> selectWmi21ItemList(InventoryDTO param);
	int saveWmi21PhyMode(InventoryDTO param);
	int saveWmi21Physqty(InventoryDTO param);
	
	/* wmi22: 재고실사조정확정 */
	List<InventoryDTO> selectWmi22HeaderList(InventoryDTO param);
	List<InventoryDTO> selectWmi22ItemList(InventoryDTO param);
	int updateWmi22Comqty(InventoryDTO param);
	String selectAdjsoky();
	String selectAdjgrky();
	int saveWmi22WadjitBeforeList(InventoryDTO param);
	int saveWmi22WadjitAfterList(InventoryDTO param);
	int updateWmi22NotHaveStockky(InventoryDTO param);
	void updateWmi22ToWstkkyList(InventoryDTO param);
	int updateWmi22Completed(InventoryDTO param);
	int updateWmi22HeaderClosing(InventoryDTO param);
	int updateWmi22ItemClosing(InventoryDTO param);
	
	
	/*  프로그램 ID : WMI11 - 프로그램 내용: 재고수량변경처리 */
	List<InventoryDTO> getWmi11WstkkyList(InventoryDTO param);
	InventoryDTO getWmi11BeforeData(InventoryDTO updateRow);

	/*  프로그램 ID : WMI30 - 프로그램 내용: 재고블락처리 */
	List<InventoryDTO> getWmi30WstkkyList(InventoryDTO updateRow);
	InventoryDTO getWmi30BeforeData(InventoryDTO updateRow);
	int saveWmi30WadjitAFTERLIST(InventoryDTO updateRow);
	
	/* 프로그램 ID : WMI31 - 프로그램 내용: 블락해제처리 */
	List<InventoryDTO> getWmi31WstkkyList(InventoryDTO param);
	InventoryDTO getWmi31BeforeData(InventoryDTO updateRow);
	int saveWmi31WadjitAFTERLIST(InventoryDTO afterData);
	
	/* 재고 조정키 , 조정그룹키 생성 */
	String getAdjsoky();
	String getAdjgrky();
	
	/* 재고 조정 전후 데이터 */
	int saveWadjitBEFORELIST(InventoryDTO beforeData);
	int saveWadjitAFTERLIST(InventoryDTO updateRow);
	
	/*  프로그램 내용: 재고 조정 프로시저 */
	void updateWstkkyList(InventoryDTO updateRow);
	
	/* wmi32: 재고조정 오더처리 */
	List<InventoryDTO> selectWmi32List(InventoryDTO param);
	int updateWmi32List(InventoryDTO param);
	
	/* wmi40: 일별재고 리스트 */
	List<WstkdyDTO> getWmi40WstkdyList(WstkdyDTO param);
	
	/* wmi41: 문서별 재고추적 */
	List<WtrhisDTO> getWmi41WtrhisList(WtrhisDTO param);

	/* wmi42: 재고부품변경처리 */
	List<InventoryDTO> getWmi42List(InventoryDTO param);
	InventoryDTO getWmi42BeforeData(InventoryDTO updateRow);
	int saveWmi42WadjitAFTERLIST(InventoryDTO afterData);
}
