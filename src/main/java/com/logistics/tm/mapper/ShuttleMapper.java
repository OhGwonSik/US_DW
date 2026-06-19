package com.logistics.tm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.md.domain.MdesmaDTO;
import com.logistics.sy.domain.UserVO;
import com.logistics.tm.domain.TvhcplVO;

@Mapper
public interface ShuttleMapper {

	Map<String, Object> getTmp1HeaderList(Map<String, Object> param);

	List<Map<String, Object>> getTmp1ItemList(Map<String, Object> param);

	int getTmp1ListCount(Map<String, Object> param);

	List<TvhcplVO> getCmpCount(Map<String, Object> param);

	String getTmp1HeaderListWithVhplnky(Map<String, Object> param);

	String getTmp1ListWithVhplnky(Map<String, Object> param);

	int getTmp1SaveList(Map<String, Object> param);

	int updateShtrsts(Map<String, Object> param);

	String getVehicky(Map<String, Object> param);

	int updateShipTranPl(Map<String, Object> param);

	List<Map<String, Object>> getTransRound(Map<String, Object> param);

	// List<Map<String, Object>> selectCustomerList(UserVO param);

	List<MdesmaDTO> selectDestList(MdesmaDTO mdesmaDTO);
}
