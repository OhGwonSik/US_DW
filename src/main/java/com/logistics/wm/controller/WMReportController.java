package com.logistics.wm.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.wm.domain.WMReportDTO;
import com.logistics.wm.service.WMReportService;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequiredArgsConstructor
public class WMReportController {

	private final WMReportService reportService;
	
	/*  generateWmReports - 리포트 호출
	*   최초 생성일시	: 2023-12-15 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: List<WMReportDTO> - WM 리포트 출력을 위해 필요한 필드들을 담은 DTO List
	*   출력 PARAMETA	: ResponseEntity<byte[]> - 출력물을 byte로 가져오기 위함
	*   설명			: WM 출력물을 출력하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	
	@PostMapping("/wm/report/reportlist")
	public ResponseEntity<byte[]> generateWmReports(@RequestBody List<WMReportDTO> report) throws JRException {
		return reportService.generateWmReports(report);
	}
}
