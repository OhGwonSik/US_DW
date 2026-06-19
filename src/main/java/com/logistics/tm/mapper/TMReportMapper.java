package com.logistics.tm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.tm.domain.TMReportDTO;

@Mapper
public interface TMReportMapper {

	//상차지시서(PLT별)
	List<TMReportDTO> getLoadingOrderPLT(TMReportDTO report);
	//상차지시서(ITEM별)
	List<TMReportDTO> getLoadingOrderITEM(TMReportDTO report);

	//프린트 로그 카운트
	int getTmPrintLogCount(TMReportDTO report);
	//프린트 프로시저
	TMReportDTO tmAddPrintList(TMReportDTO report);
}
