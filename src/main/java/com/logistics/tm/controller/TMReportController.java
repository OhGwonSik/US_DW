package com.logistics.tm.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.tm.domain.TMReportDTO;
import com.logistics.tm.service.TMReportService;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequiredArgsConstructor
public class TMReportController {

	private final TMReportService reportService;
	
	/*  generateTmReports - 리포트 호출
	*   최초 생성일시	: 2023-12-15 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: List<TMReportDTO> - TM 리포트 출력을 위해 필요한 필드들을 담은 DTO List
	*   출력 PARAMETA	: ResponseEntity<byte[]> - 출력물을 byte로 가져오기 위함
	*   설명			: TM 출력물을 출력하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@PostMapping("/tm/report/reportlist")
	public ResponseEntity<byte[]> generateTmReports(@RequestBody List<TMReportDTO> report) throws JRException {
		return reportService.generateTmReports(report);
	}
}
