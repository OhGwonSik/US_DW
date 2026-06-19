package com.logistics.wm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.logistics.wm.domain.StatisticsDTO;
import com.logistics.wm.mapper.StatisticsMapper;

import lombok.RequiredArgsConstructor;
/*
*   클래스명 : StatisticsService
*   최초생성일시	: 2023.10
*   최초생성자 : Park T. S.
*   설명 : 물류통계 서비스 클래스
*/

@Service
@RequiredArgsConstructor
public class StatisticsService {
	private final StatisticsMapper statisticsMapper;

	// 물류통계 리스트 조회
	public List<StatisticsDTO> getReasonCodeStatisticsList(StatisticsDTO params){
		return statisticsMapper.selectReasonCodeStatisticsList(params);
	}
}
