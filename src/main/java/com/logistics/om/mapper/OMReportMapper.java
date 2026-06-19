package com.logistics.om.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.om.domain.OMReportDTO;

@Mapper
public interface OMReportMapper {
	
	//납입카드
	List<OMReportDTO> getPaymentCard(OMReportDTO report);
	//납입카드-파라미터
	OMReportDTO getPaymentCardParameter(OMReportDTO report);
	//반출증
	List<OMReportDTO> getExportCard(OMReportDTO report);
	
	//프린트 프로시저
	OMReportDTO omAddPrintList(OMReportDTO report);
	//프린트 로그 카운트
	int getOmPirntLogCount(OMReportDTO report);
}
