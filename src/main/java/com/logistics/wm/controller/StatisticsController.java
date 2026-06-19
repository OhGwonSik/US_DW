 package com.logistics.wm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.common.dto.InitDataDTO;
import com.logistics.md.domain.MrscmaDTO;
import com.logistics.md.domain.MwarmaDTO;
import com.logistics.md.service.MDC06Service;
import com.logistics.md.service.MDO02Service;
import com.logistics.sy.domain.UserVO;
import com.logistics.wm.domain.StatisticsDTO;
import com.logistics.wm.service.StatisticsService;

import lombok.RequiredArgsConstructor;

/*
*   클래스명 : StatisticsController
*   최초생성일시	: 2023.10
*   최초생성자 : Park T. S.
*   설명 : 세트구성 컨트롤러 클래스
*/

@RestController
@RequiredArgsConstructor
@RequestMapping("/wm/statistics")
public class StatisticsController {
	// statistics
	private final StatisticsService statisticsService;

	// organization
	private final MDO02Service mdo02Service;

	// code
	private final MDC06Service mdc06Service;

	//********************************************//
	//*****************-WMS10-*****************//
	//*******************************************//
	// 물류통계 리스트 조회
	@GetMapping("/wms10/grids/1")
	public List<StatisticsDTO> getReasonCodeStatisticsList(@ModelAttribute StatisticsDTO params){
		return statisticsService.getReasonCodeStatisticsList(params);
	}

	// 페이지 초기화
	@GetMapping("/wms10/init")
	public InitDataDTO getWms10InitData(@ModelAttribute StatisticsDTO params) {
		Map<String, Object> item = new HashMap<>(); // response
		UserVO userData = params.getUserData();

		// 창고정보
		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(userData);
		item.put("warekey", mdo02Service.getWarehouseSelectBox(mwarmaDTO));

		// 사유코드
		MrscmaDTO mrscmaDTO = new MrscmaDTO();
		mrscmaDTO.setUserData(userData);
		item.put("rsncode", mdc06Service.getReasonCodeRelations(mrscmaDTO));

		InitDataDTO initDataDTO = new InitDataDTO();
		initDataDTO.setItem(item);

		return initDataDTO;
	}
}
