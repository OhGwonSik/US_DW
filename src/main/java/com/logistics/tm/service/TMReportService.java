package com.logistics.tm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.logistics.configuration.error.ProcedureCheckedException;
import com.logistics.jasper.JasperService;
import com.logistics.tm.domain.TMReportDTO;
import com.logistics.tm.mapper.TMReportMapper;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
@RequiredArgsConstructor
public class TMReportService {

	private final TMReportMapper reportMapper;
	private final JasperService jasperService;
	
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
	public ResponseEntity<byte[]> generateTmReports(List<TMReportDTO> report) throws JRException {
		List<TMReportDTO> reportData = null;

		JasperReport jasperReport = null;
		JRBeanCollectionDataSource jrBeanCollectionDataSource = null;
		Map<String, Object> parameters = new HashMap<>();

		List<JasperPrint> jasperPrintList = new ArrayList<>();

		for(int i=0; i<report.size(); i++) {
			if(report.get(i).getType().equals("tmw09_1")) {
				reportData = reportMapper.getLoadingOrderPLT(report.get(i));
				
				int printCount = reportMapper.getTmPrintLogCount(report.get(i));
				parameters.put("reissue", printCount);

				report.get(i).setLoguser(report.get(i).getUser());
				report.get(i).setDocitem(0);
				report.get(i).setDochkey(report.get(i).getVhplnky());
				report.get(i).setDoccate(report.get(i).getDoccate());
				report.get(i).setDoctype(report.get(i).getDoctype());

				reportMapper.tmAddPrintList(report.get(i));
				
				if(report.get(i).getOresult() != 0) {
					throw new ProcedureCheckedException(report.get(i).getOmsgkey());
				}
				
			}else if(report.get(i).getType().equals("tmw09_2")) {
				reportData = reportMapper.getLoadingOrderITEM(report.get(i));
				
				int printCount = reportMapper.getTmPrintLogCount(report.get(i));
				parameters.put("reissue", printCount);

				report.get(i).setLoguser(report.get(i).getUser());
				report.get(i).setDocitem(0);
				report.get(i).setDochkey(report.get(i).getVhplnky());
				report.get(i).setDoccate(report.get(i).getDoccate());
				report.get(i).setDoctype(report.get(i).getDoctype());

				reportMapper.tmAddPrintList(report.get(i));
				
				if(report.get(i).getOresult() != 0) {
					throw new ProcedureCheckedException(report.get(i).getOmsgkey());
				}
			}

			jrBeanCollectionDataSource = new JRBeanCollectionDataSource(reportData);
			
			jasperReport = jasperService.getJasperReport(report.get(i).getType());

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);

			jasperPrintList.add(jasperPrint);
		}

		return jasperService.getJasperResonse(jasperPrintList);
	}
}
