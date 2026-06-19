package com.logistics.om.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.logistics.jasper.JasperService;
import com.logistics.om.domain.OMReportDTO;
import com.logistics.om.service.OMReportService;
import com.logistics.sy.domain.UserVO;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequiredArgsConstructor
public class OMReportController {

	private final OMReportService reportService;

	/*  generateOmReports - 리포트 호출
	*   최초 생성일시	: 2023-12-15 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: List<OMReportDTO> - OM 리포트 출력을 위해 필요한 필드들을 담은 DTO List
	*   출력 PARAMETA	: ResponseEntity<byte[]> - 출력물을 byte로 가져오기 위함
	*   설명			: OM 출력물을 출력하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		:
	*/

	@PostMapping("/om/report/reportlist")
	public ResponseEntity<byte[]> generateOmReports(@RequestBody List<OMReportDTO> report) throws JRException {
		return reportService.generateOmReports(report);
	}
}
