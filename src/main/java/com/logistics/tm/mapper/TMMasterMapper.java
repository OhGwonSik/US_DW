package com.logistics.tm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.md.domain.MdesmaDTO;
import com.logistics.md.domain.MptnmaDTO;
import com.logistics.tm.domain.TMMasterDTO;
import com.logistics.tm.domain.TshrhdDTO;

@Mapper
public interface TMMasterMapper {

	// List<TvhcmaVO> getVehicleList(TvhcmaVO tvhcma);

	List<TMMasterDTO> selectTmm01List(TMMasterDTO tmMasterDTO);

	int insertVehicleList(Map<String, Object> params);

	int updateVehicleList(Map<String, Object> params);

	// int deleteVehicleList(Map<String, Object> param);

	List<TMMasterDTO> selectTpvassList(Map<String, Object> aparamMap);

	int insertTpvassList(Map<String, Object> params);

	int updateTpvassList(Map<String, Object> params);

	//
	List<TMMasterDTO> selectTshrhdList(TMMasterDTO params);

	int insertTshrhdList(List<TMMasterDTO> params);

	int updateTshrhdList(List<TMMasterDTO> params);

	// int deleteTshrhdList(List<TshrhdDTO> params);

	//
	List<TMMasterDTO> selectTshritList(TMMasterDTO params);

	int insertTshritList(List<TMMasterDTO> params);

	int updateTshritList(List<TMMasterDTO> params);

	int deleteTshritList(List<TMMasterDTO> params);

	//
	List<TMMasterDTO> selectTshrvhList(TMMasterDTO params);

	int insertTshrvhList(List<TMMasterDTO> params);

	int updateTshrvhList(List<TMMasterDTO> params);

	int deleteTshrvhList(List<TMMasterDTO> params);

	//
	// use this
	// List<_YOURS_DTO> select_YOURS_List(_YOURS_DTO params);

	// int insert_YOURS_List(List<_YOURS_DTO> params);

	// int update_YOURS_List(List<_YOURS_DTO> params);

	// int delete_YOURS_List(List<_YOURS_DTO> params);

	List<MptnmaDTO> selectCustomerList(TMMasterDTO tmMasterDTO);

	List<MdesmaDTO> selectDestList(TMMasterDTO tMasterDTO);
	
	//셔틀노선키 셀렉트박스
	List<TshrhdDTO> selectTshrhdSelectBox(TshrhdDTO params);
	
	//셔틀노선-차량 (변동운송비)
	List<TMMasterDTO> selectShtrtkyVehicky(TMMasterDTO params);
}
