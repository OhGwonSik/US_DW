package com.logistics.wm.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.wm.domain.PutawayStrategyDTO;
import com.logistics.wm.domain.WpaordVO;
import com.logistics.wm.domain.WpasmaVO;

@Mapper
public interface StrategyMapper { 
	
	// WMS1 : 적치전략 Head List 조회
	List<PutawayStrategyDTO> selectWpashdList(Map<String, Object> map);
	
	// WMS1 : 적치전략 Item List 조회
	List<PutawayStrategyDTO> selectWpasitList(Map<String, Object> map);
	
	// WMS1 : 적치전략 마스터 List 조회
	List<WpasmaVO> selectWpasmaList(Map<String, Object> map);
	
	// WMS1 : 적치전략 Head insert
	int insertWpashd(PutawayStrategyDTO dto);
	
	// WMS1 : 적치전략 Head update
	int updateWpashd(PutawayStrategyDTO dto);

	// WMS1 : 적치전략 Item insert
	int insertWpasit(HashMap<String, Object> map);
	
	// WMS1 : 적치전략 Item update
	int updateWpasit(HashMap<String, Object> map);
	
	// WMS1 : 적치전략 Head Select Box 조회
	List<Map<String, Object>> selectWpashdBox(Map<String, Object> map);
	
	// WMS2 : 적치오더 연결 List 조회
	List<WpaordVO> selectWpaordList(Map<String, Object> map);
	
	// WMS2 : 적치오더 연결 insert
	int insertWpaord(HashMap<String, Object> map);
	
	// WMS2 : 적치오더 연결 update
	int updateWpaord(HashMap<String, Object> map);
	
	// WMS2 : 적치오더 연결 delete
	int deleteWpaord(HashMap<String, Object> map);
}
