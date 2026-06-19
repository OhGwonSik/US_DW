package com.logistics.wm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.wm.domain.StatisticsDTO;

/*
*   클래스명 : StatisticsMapper
*   최초생성일시	: 2023.10
*   최초생성자 : Park T. S.
*   설명 : 물류통계 서비스 매퍼
*/

@Mapper
public interface StatisticsMapper {
	// 물류통계 리스트 조회
	List<StatisticsDTO> selectReasonCodeStatisticsList(StatisticsDTO params);
}
