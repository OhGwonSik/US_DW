package com.logistics.bm.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.DecoderException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.logistics.bm.domain.BMReportDTO;
import com.logistics.bm.domain.BMReportListDTO;
import com.logistics.bm.mapper.BMReportMapper;
import com.logistics.jasper.JasperService;
import com.logistics.util.CrypoUtil;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
@RequiredArgsConstructor
public class BMReportService {
	
	private final JasperService jasperService;
	private final BMReportMapper reportMapper;
	
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
	
	public ResponseEntity<byte[]> generateBmReports(BMReportListDTO report) throws JRException {
		List<BMReportDTO> reportData = null;
		
		JasperReport jasperReport = null;
		JRBeanCollectionDataSource jrBeanCollectionDataSource = null;
		Map<String, Object> parameters = new HashMap<>();
		
		List<JasperPrint> jasperPrintList = new ArrayList<>();
		
		//재발행은 정산번호 기준으로
		
		Set<String> bmKeyList = new LinkedHashSet<>();
		
		for(int i=0; i<report.getReportList().size(); i++) {
			//물류비 거래명세서
			if(report.getType().equals("bmb10")) {
				report.getReportList().get(i).setUserData(report.getUserData());
				
				reportData = reportMapper.getDcDealStatement(report.getReportList().get(i));
				
				int printCount = reportMapper.getBmPrintLogCount(report.getReportList().get(i));
				parameters.put("reissue", printCount);
				
				bmKeyList.add(report.getReportList().get(i).getBlhdcky());
			//운송비 거래명세서
			}else if(report.getType().equals("bmb20")) {
				report.getReportList().get(i).setUserData(report.getUserData());
				
				reportData = reportMapper.getTcDealStatement(report.getReportList().get(i));				
				
				int printCount = reportMapper.getBmPrintLogCount(report.getReportList().get(i));
				parameters.put("reissue", printCount);
				
				bmKeyList.add(report.getReportList().get(i).getBthdcky());
			}
			
			jasperReport = jasperService.getJasperReport(report.getType());
			jrBeanCollectionDataSource = new JRBeanCollectionDataSource(reportData);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
			jasperPrintList.add(jasperPrint);
		}
		
		report.setDoccate("900");
		report.setDocitem(0);
		
		if(report.getType().equals("bmb10")) {
			report.setDoctype("910");
		}else if(report.getType().equals("bmb20")) {
			report.setDoctype("920");
		}
		
		Iterator<String> it = bmKeyList.iterator();
		
		while(it.hasNext()) {
			report.setDochkey(it.next());
			reportMapper.bmAddPrintList(report);
		}
		
		return jasperService.getJasperResonse(jasperPrintList);
	}
	
	/*  generateBmReport - 차량명세서
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
	
	public ResponseEntity<byte[]> generateBmb09Report(BMReportListDTO report) throws JRException{
		List<JasperPrint> jasperPrintList = new ArrayList<>();
		
		for(int i=0; i<report.getReportList().size(); i++) {
			report.getReportList().get(i).setCompkey(report.getCompkey());
			BMReportDTO parameterData = reportMapper.bmb09ParameterData(report.getReportList().get(i));
		
			String cryptedTel = "";
			//암호화된 전화번호 가져옴
			String encryptedTel = reportMapper.getBmPassword(report.getReportList().get(i));
			if(encryptedTel != null) {
				try {
					//암호화 해제
					cryptedTel = CrypoUtil.decrypto(encryptedTel);
				}catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException |
				        IllegalBlockSizeException | UnsupportedEncodingException | DecoderException e) {
				    e.printStackTrace();
				}
			}
			
			//왼쪽 혹은 오른쪽 데이터가 없을 경우 빈 데이터 가져옴
			List<BMReportDTO> subData1 = reportMapper.bmb09SubData1(report.getReportList().get(i));
			if(subData1.size() == 0) {
				subData1 = reportMapper.bmb09SubData1Zero(report.getReportList().get(i));
			}
			List<BMReportDTO> subData2 = reportMapper.bmb09SubData2(report.getReportList().get(i));
			if(subData2.size() == 0) {
				subData2 = reportMapper.bmb09SubData2Zero(report.getReportList().get(i));
			}
			
		
			JasperReport jasperReport = jasperService.getJasperReport(report.getType());
			JasperReport subReport1 = jasperService.getJasperReport(report.getType() + "_1");
			JasperReport subReport2 = jasperService.getJasperReport(report.getType() + "_2");
			JasperReport subReport3 = jasperService.getJasperReport(report.getType() + "_3");
			
			JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(subData1);
			
			Map<String, Object> parameters = new HashMap<>();
			
			//재발행 여부
			report.getReportList().get(i).setUserData(report.getUserData());
			int printCount = reportMapper.getBmPrintLogCount(report.getReportList().get(i));
			parameters.put("reissue", printCount);
			
			parameters.put("vhcfnam", parameterData.getVhcfnam());
			parameters.put("usernam", parameterData.getUsernam());
			parameters.put("telphnm", cryptedTel);
			parameters.put("btramot", parameterData.getBtramot());
			
			parameters.put("subReport1", subReport1);
			parameters.put("subData1", subData1);
			parameters.put("subReport2", subReport2);
			parameters.put("subData2", subData2);
			parameters.put("subReport3", subReport3);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
			jasperPrintList.add(jasperPrint);
			
			//프린트 프로시저
			report.setDoccate("900");
			report.setDocitem(report.getReportList().get(i).getDocitem());
			report.setDoctype("920");
			report.setDochkey(report.getReportList().get(i).getBthdcky());
			reportMapper.bmAddPrintList(report);
		}

		return jasperService.getJasperResonse(jasperPrintList);
	}

}
