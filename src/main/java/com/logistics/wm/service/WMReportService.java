package com.logistics.wm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.logistics.configuration.error.ProcedureCheckedException;
import com.logistics.jasper.JasperService;
import com.logistics.wm.domain.WMReportDTO;
import com.logistics.wm.mapper.WMReportMapper;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
@RequiredArgsConstructor
public class WMReportService {
	
	private final WMReportMapper reportMapper;
	private final JasperService jasperService;
	
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
	
	public ResponseEntity<byte[]> generateWmReports(List<WMReportDTO> report) throws JRException {
		List<WMReportDTO> reportData = null;
		
		JasperReport jasperReport = null;
		JRBeanCollectionDataSource jrBeanCollectionDataSource = null;	//DB데이터가 담겨지는 곳
		Map<String, Object> parameters = new HashMap<>();	//출력물 파라미터
		
		List<JasperPrint> jasperPrintList = new ArrayList<>();
		
		for(int i=0; i<report.size(); i++) {
			
			report.get(i).setLoguser(report.get(i).getUser());
			//
			// 재고실사지시서
			//
			if(report.get(i).getType().equals("wmd60")) {
				reportData = reportMapper.getStockInspectionOrder(report.get(i));
				
				//프린트 여부 확인
				int printCount = reportMapper.getWmPrintLogCount(report.get(i));
				parameters.put("reissue", printCount);
				 
				//프린트 로그
				report.get(i).setDocitem(0);
				report.get(i).setDochkey(report.get(i).getPhysoky());
				report.get(i).setDoccate("600");
				report.get(i).setDoctype("630");
				
				reportMapper.wmAddPrintList(report.get(i));
			//
			// 피킹지시서 오더
			//
			}else if(report.get(i).getType().equals("wmd10_1")) {
				//doctype 파라미터
				parameters.put("doctype", report.get(i).getDoctype());
				WMReportDTO param = null;
				
				//doctype에 따라서 reportData, param이 달라짐
				if(report.get(i).getDoctype() != null && "780".equals(report.get(i).getDoctype())) {
					if(report.get(i).getCooutky() != null) {
						report.get(i).setOeretky(report.get(i).getCooutky());
					}
					reportData = reportMapper.getPickingOrderByReturn(report.get(i));
					param = reportMapper.getPickingOrderItemByReturn(report.get(i));
				}else {
					reportData = reportMapper.getPickingOrderByNormal(report.get(i));
					param = reportMapper.getPickingOrderItemByNormal(report.get(i));
				}
				
				//파라미터 셋팅
				parameters.put("skuncnt", param.getSkuncnt());
				parameters.put("stotqty", param.getStotqty());
				
				//프린트 기록 확인
				int printCount = reportMapper.getWmPrintLogCount(report.get(i));
				parameters.put("reissue", printCount);
				
				//프린트 로그
				report.get(i).setDochkey(report.get(i).getAllgrky());
				report.get(i).setDocitem(0);
				reportMapper.wmAddPrintList(report.get(i));
			//
			//피킹지시서 총괄
			//
			}else if(report.get(i).getType().equals("wmd10_2")) {
				//doctype 파라미터
				parameters.put("doctype", report.get(i).getDoctype());
				WMReportDTO param = null;
				
				//doctype에 따라서 reportData, param이 달라짐
				if(report.get(i).getDoctype() != null && "780".equals(report.get(i).getDoctype())) {
					reportData = reportMapper.getPickingOrderByReturn(report.get(i));
					param = reportMapper.getPickingOrderItemByReturn(report.get(i));
				}else {
					reportData = reportMapper.getPickingOrderByNormal(report.get(i));
					param = reportMapper.getPickingOrderItemByNormal(report.get(i));
				}
				
				//파라미터 셋팅
				parameters.put("skuncnt", param.getSkuncnt());
				parameters.put("stotqty", param.getStotqty());
				
				//프린트 기록 확인
				int printCount = reportMapper.getWmPrintLogCount(report.get(i));
				parameters.put("reissue", printCount);
				
				//프린트 로그
				report.get(i).setDochkey(report.get(i).getAllgrky());
				report.get(i).setDocitem(0);
				reportMapper.wmAddPrintList(report.get(i));
			//
			//적치지시서
			//
			}else if(report.get(i).getType().equals("wmd70")) {
				report.get(i).setDoccate("500");
				report.get(i).setDoctype("510");
				reportData = reportMapper.getPileOrder(report.get(i));
				
				//프린트 기록 확인
				int printCount = reportMapper.getWmPrintLogCount(report.get(i));
				parameters.put("reissue", printCount);
				
				//파라미터 셋팅
				WMReportDTO param = reportMapper.getPileOrderSummary(report.get(i));
				parameters.put("skuncnt", param.getSkuncnt());
				parameters.put("stotqty", param.getStotqty());
				
				//프린트 로그
				report.get(i).setDochkey(report.get(i).getTaskoky());
				
				if(report.get(i).getTaskoitList() != null) {
					for(int j=0; j<reportData.size(); j++) {
						report.get(i).setDocitem(reportData.get(j).getTaskoit());
						
						reportMapper.wmAddPrintList(report.get(i));
					}
				}else {
					report.get(i).setDocitem(0);
					
					reportMapper.wmAddPrintList(report.get(i));
				}
			//
			//팔렛타이징처리
			//
			}else if(report.get(i).getType().equals("wmd20")) {
				reportData = reportMapper.getPalletLabel(report.get(i));
			//
			// 납품지시서
			//
			}else if(report.get(i).getType().equals("wmd30")) {
				//doctype 파라미터
				parameters.put("doctype", report.get(i).getDoctype());
				
				reportData = reportMapper.getShipOrder(report.get(i));
				
				StringBuffer sb = new StringBuffer();
				for(WMReportDTO reportRow : reportData) {
					sb.append(reportRow.getCooutky()).append(",");
				}
				report.get(i).setOritkey(sb.toString().split(","));
				//파라미터 셋팅
				WMReportDTO param = reportMapper.getShipOrderItem(report.get(i));
				parameters.put("skuncnt", param.getSkuncnt());
				parameters.put("stotqty", param.getStotqty());
				
				//프린트 기록 확인
				int printCount = reportMapper.getWmPrintLogCount(report.get(i));
				parameters.put("reissue", printCount);
				
				//프린트 로그
				report.get(i).setDochkey(report.get(i).getAllgrky());
				report.get(i).setDocitem(0);
				reportMapper.wmAddPrintList(report.get(i));
			}
			
			if(report.get(i).getOresult() != 0) {
				throw new ProcedureCheckedException(report.get(i).getOmsgkey());
			}
			
			//메인리포트
			jasperReport = jasperService.getJasperReport(report.get(i).getType());
			
			jrBeanCollectionDataSource = new JRBeanCollectionDataSource(reportData);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
			
			jasperPrintList.add(jasperPrint);
		}

		return jasperService.getJasperResonse(jasperPrintList);
	}
}
