package com.logistics.bm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.bm.domain.BMReportDTO;
import com.logistics.bm.domain.BMReportListDTO;

@Mapper
public interface BMReportMapper {
	
	//물류비 거래명세서
	List<BMReportDTO> getDcDealStatement(BMReportDTO report);
	//운송비 거래명세서
	List<BMReportDTO> getTcDealStatement(BMReportDTO report);
	
	//차량비 명세서 - 파라미터 데이터
	BMReportDTO bmb09ParameterData(BMReportDTO report);
	List<BMReportDTO> bmb09SubData1(BMReportDTO report);
	List<BMReportDTO> bmb09SubData2(BMReportDTO report);
	List<BMReportDTO> bmb09SubData1Zero(BMReportDTO report);
	List<BMReportDTO> bmb09SubData2Zero(BMReportDTO report);
	
	//프린트 로그 카운트
	int getBmPrintLogCount(BMReportDTO report);
	//프린트 프로시저
	BMReportDTO bmAddPrintList(BMReportListDTO report);
	
	//운전자계정
	String getBmPassword(BMReportDTO report);
}
