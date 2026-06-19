package com.logistics.bm.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.bm.domain.BMReportDTO;
import com.logistics.bm.domain.BMReportListDTO;
import com.logistics.bm.service.BMReportService;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequiredArgsConstructor
public class BMReportController {
	private final BMReportService reportService;
	
	/*  generateBmReports - 리포트 호출
	*   최초 생성일시	: 2023-12-15 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: BMReportListDTO - BM 리포트 출력을 위해 필요한 필드들을 담은 DTO
	*   출력 PARAMETA	: ResponseEntity<byte[]> - 출력물을 byte로 가져오기 위함
	*   설명			: BM 출력물을 출력하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	
	@PostMapping("/bm/report/reportlist")
	public ResponseEntity<byte[]> generateBmReports(@RequestBody BMReportListDTO report) throws JRException {
		return reportService.generateBmReports(report);
	}
	
	/*  generateBmb09Report - 차량명세서
	*   최초 생성일시	: 2023-12-15 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: BMReportDTO - BM 리포트 출력을 위해 필요한 필드들을 담은 DTO
	*   출력 PARAMETA	: ResponseEntity<byte[]> - 출력물을 byte로 가져오기 위함
	*   설명			: BM 출력물을 출력하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	
	@PostMapping("/bm/report/bmb09")
	public ResponseEntity<byte[]> generateBmb09Report(@RequestBody BMReportListDTO report) throws JRException {
		return reportService.generateBmb09Report(report);
	}
}
