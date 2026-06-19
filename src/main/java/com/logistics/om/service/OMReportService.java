package com.logistics.om.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.logistics.configuration.error.ProcedureCheckedException;
import com.logistics.jasper.JasperService;
import com.logistics.om.domain.OMReportDTO;
import com.logistics.om.mapper.OMReportMapper;
import com.logistics.sy.domain.UserVO;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
@RequiredArgsConstructor
public class OMReportService {
	
	private final OMReportMapper reportMapper;
	private final JasperService jasperService;
	
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
	
	public ResponseEntity<byte[]> generateOmReports(List<OMReportDTO> report) throws JRException {
		//납입카드 - payment
		//반출증 - export
		List<OMReportDTO> reportData = null;
		
		//report 파일들이 들어있는 곳
		JasperReport jasperReport = null;
		JasperReport subReport1 = null;
		JasperReport subReport2 = null;
		JRBeanCollectionDataSource jrBeanCollectionDataSource = null;	//DB데이터가 담겨지는 곳
		Map<String, Object> parameters = new HashMap<>();	//출력물 파라미터
		
		List<JasperPrint> jasperPrintList = new ArrayList<>();
		
		for(int i=0; i<report.size(); i++) {
			
			report.get(i).setLoguser(report.get(i).getUser());
			
			//납입카드
			if(report.get(i).getType().equals("omd10")) {
				reportData = reportMapper.getPaymentCard(report.get(i));
				
				//파라미터
				OMReportDTO omd10Parameter = reportMapper.getPaymentCardParameter(report.get(i));
				parameters.put("totalAsndqty", omd10Parameter.getTotalAsndqty());
				parameters.put("totalColumn", omd10Parameter.getTotalColumn());
				
				//프린트 기록 확인
				int printCount = reportMapper.getOmPirntLogCount(report.get(i));
				parameters.put("reissue", printCount);
				
				//프린트 로그
				report.get(i).setDoccate("100");
				report.get(i).setDoctype("110");
				report.get(i).setDochkey(report.get(i).getEoasnky());
				report.get(i).setDocitem(0);
				report.get(i).setLoguser(report.get(i).getUser());
				reportMapper.omAddPrintList(report.get(i));

			//반출증
			}else if(report.get(i).getType().equals("omd20")) {
				reportData = reportMapper.getExportCard(report.get(i));
				
				//프린트 기록 확인
				int printCount = reportMapper.getOmPirntLogCount(report.get(i));
				parameters.put("reissue", printCount);
				
				//프린트 로그
				report.get(i).setDoccate("300");
				report.get(i).setDoctype("330");
				report.get(i).setDochkey(report.get(i).getOeretky());
				report.get(i).setDocitem(0);
				report.get(i).setLoguser(report.get(i).getUser());
				reportMapper.omAddPrintList(report.get(i));
			}
			
			if(report.get(i).getOresult() != 0) {
				throw new ProcedureCheckedException(report.get(i).getOmsgkey());
			}
			
			//메인리포트
			jasperReport = jasperService.getJasperReport(report.get(i).getType());
			
			//서브리포트
			subReport1 =jasperService.getJasperReport(report.get(i).getType()+"_1"); 
			subReport2 = jasperService.getJasperReport(report.get(i).getType()+"_2"); 
			
			jrBeanCollectionDataSource = new JRBeanCollectionDataSource(reportData);
			
			//파라미터 설정
			//메인리포트 안에 서브리포트와 서브리포트 데이터 파라미터들이 존재함.
			parameters.put("subreportParameter1", subReport1);
			parameters.put("subreportParameter2", subReport2);
			parameters.put("reportData", reportData);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
			
			jasperPrintList.add(jasperPrint);
			
		}		
		return jasperService.getJasperResonse(jasperPrintList);
	}

}
