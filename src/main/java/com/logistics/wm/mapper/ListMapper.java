package com.logistics.wm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.wm.domain.WrcvitVO;
import com.logistics.wm.domain.WstkkyVO;
import com.logistics.wm.domain.WtakitVO;

@Mapper
public interface ListMapper { 
	
	//재고 입고 리스트
	List<WrcvitVO> getWmlt1WrcvitList(Map<String, Object> param);
	
	//재고 이동 리스트
	List<WtakitVO> getWmlt2WtakitList(Map<String, Object> param);

	//재고 출고 리스트
	List<WtakitVO> getWmlt3WtakitList(Map<String, Object> param);
	
	//재고 조정 이력 리스트
	List<Map<String, Object>> getWmlt4WadjitList(Map<String, Object> param);

	//재고 실사 이력 리스트
	List<Map<String, Object>> getWmlt5WphyitList(Map<String, Object> param);

	//재고 마감재고 리스트
	List<WstkkyVO> getWmlt6WstkkyList(Map<String, Object> param);

	//재고 SKU ABC 리스트
	List<Map<String, Object>> getWmlt7MskuwcList(Map<String, Object> param);
	
	
}
