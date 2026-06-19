package com.logistics.webjis.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.logistics.configuration.db.MybatisConfig;
import com.logistics.webjis.config.WebjisConfig;
import com.logistics.webjis.jsoup.WebjisJsoup;
import com.logistics.webjis.mapper.WebjisMapper;
import com.logistics.webjis.vo.WebjisVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebjisService {

	private final WebjisConfig webjisConfig;
	private final WebjisJsoup webjisJsoup;
	private final WebjisMapper webjisMapper;
	
	@Autowired
	@Qualifier(MybatisConfig.WEBJIS_SESSION_TEMPLATE)
	private final SqlSessionTemplate sessionTemplate;

	/*  getWebjisList - 일일 웹지스 데이터 크롤링 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수 
	*   입력 PARAMETA	: String webjisDate - 금일 날짜
	*   출력 PARAMETA	: List<WebjisVO> - 웹지스 데이터수신 VO 
	*   설명			: 금일 웹지스 데이터를 수신하는 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		: 
	*/
	public List<WebjisVO> getWebJisList(String webjisDate) throws IOException {
		List<WebjisVO> webjisList = new ArrayList<>();
		Map<String, Map<String, String>> params = webjisConfig.getParams();
		Iterator<String> paramsIt = params.keySet().iterator();
		String ifsalsq = webjisMapper.selectIfsalsq();
		boolean isUpdate = false;
		while (paramsIt.hasNext()) {
 			String paramKey = paramsIt.next();
			Map<String, String> data = params.get(paramKey);
			webjisDate = webjisDate.replace("\"", "");
			data.put("K_FROM_DATE", webjisDate);
			data.put("K_TO_DATE", webjisDate);
			String[] kJisItems = data.get("K_JIS_ITEMS").split(",");
			for (String kJisItem : kJisItems) {
				data.put("K_JIS_ITEM", kJisItem);
				List<WebjisVO> webjisCrawlList = webjisJsoup.getWebjisList(getWebjisConnection(), data, isUpdate);
				for (WebjisVO webjis : webjisCrawlList) {
					webjis.setIfsalsq(ifsalsq);
					webjis.setLine(data.get("K_JIS_LINE"));
					webjis.setPlant(data.get("K_JIS_PLANT"));
					webjis.setItemcate(kJisItem);
					webjis.setCompkey(webjisConfig.getCompkey());
					webjis.setIfrcvur(webjisConfig.getIfrcvur());
					webjisList.add(webjis);
				}
			}
		}
		return webjisList;
	}

	/*  saveWebjisData - 일일 웹지스 데이터 저장 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수 
	*   입력 PARAMETA	: List<WebjisVO> webjisList - 웹지스 데이터수신 VO
	*   출력 PARAMETA	: 
	*   설명			: 금일 웹지스 데이터를 BATCH를 사용하여 저장하는 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		: 
	*/
	public void saveWebjisData(List<WebjisVO> webjisList){
		SqlSession sqlSession = sessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH);
		for (WebjisVO webjis : webjisList) { 
			sqlSession.insert("com.logistics.webjis.mapper.WebjisMapper.insertWebjis", webjis);
		}
		sqlSession.flushStatements();
		sqlSession.close();
	}

	/*  getCheckDateIsEmpty - 이종미확인 데이터 수신 후 크롤링 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수 
	*   입력 PARAMETA	: 
	*   출력 PARAMETA	: List<WebjisVO> webjisList - 웹지스 데이터수신 VO
	*   설명			: 이종미확인 데이터를 품목, 공장, 라인, 날짜별로 group by 하여 조회 후 크롤링 하는 메소드.
	*                 바디넘버를 추가하여 특정한 데이터만 크롤링이 가능하지만 크롤링 횟수 줄이기 위해 전부 크롤링 후 정제.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		: 
	*/
	public List<WebjisVO> getCheckDateIsEmpty() throws IOException { 
		String compkey = webjisConfig.getCompkey();
		List<WebjisVO> emptyCheckDateList = webjisMapper.selectCheckDateTimeIsEmpty(compkey);
		Map<String, Map<String, String>> params = webjisConfig.getParams();
		List<WebjisVO> webjisList = new ArrayList<>();
		for (WebjisVO emptyCheckDate : emptyCheckDateList) {
			boolean isUpdate = true;
			Map<String, String> data = params.get("plant" + emptyCheckDate.getPlant());
			data.put("K_FROM_DATE", emptyCheckDate.getReceiveDateTime());
			data.put("K_TO_DATE", emptyCheckDate.getCreateDateTime());
			data.put("K_JIS_ITEM", emptyCheckDate.getItemcate());
			List<WebjisVO> webjisCrawlList = webjisJsoup.getWebjisList(getWebjisConnection(), data, isUpdate);
			int matchCnt = 0;
			for (WebjisVO webjis : webjisCrawlList) {
				if((emptyCheckDate.getSeq()).equals(webjis.getSeq())) {
					matchCnt++;
				}
				if(!webjis.getCheckDateTime().isEmpty()) {
					webjis.setCompkey(compkey);
					webjis.setItemcate(emptyCheckDate.getItemcate());
					webjisList.add(webjis);
				}
			}
			
			if(matchCnt == 0) {
				data.put("K_TO_DATE", "");
				data.put("K_BODY_NUM", emptyCheckDate.getBodyNo());
				isUpdate = false;
				List<WebjisVO> webjisCrawlData = webjisJsoup.getWebjisList(getWebjisConnection(), data, isUpdate);
				if(webjisCrawlData.size() != 1) {
					log.info("emptyCheckDate => {}", emptyCheckDate);
					throw new IOException("no crawl data error!");
				}else if(webjisCrawlData.get(0).getCheckDateTime().isEmpty()) {
					continue;
				}
				webjisCrawlData.get(0).setCompkey(compkey);
				webjisCrawlData.get(0).setItemcate(emptyCheckDate.getItemcate());
				webjisList.add(webjisCrawlData.get(0));
				data.remove("K_BODY_NUM");
			}
		}
		return webjisList;
	}
	
	/*  updateWebjisData - 이종미확인 데이터 업데이트 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수 
	*   입력 PARAMETA	: List<WebjisVO> webjisList - 웹지스 데이터수신 VO
	*   출력 PARAMETA	: 
	*   설명			: 금일 웹지스 데이터를 BATCH를 사용하여 업데이트하는 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		: 
	*/
	public void updateWebjisData(List<WebjisVO> webjisList) {
		SqlSession sqlSession = sessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH);
		for (WebjisVO webjis : webjisList) { 
			sqlSession.update("com.logistics.webjis.mapper.WebjisMapper.updateCheckDateTime", webjis);
		}
		sqlSession.flushStatements();
		sqlSession.close();
	}

	/*  getWebjisConnection - 웹지스 크롤링 커넥션 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수 
	*   입력 PARAMETA	: 
	*   출력 PARAMETA	: Connection - JSOUP Connection
	*   설명			: 금일 웹지스 데이터를 BATCH를 사용하여 업데이트하는 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		: 
	*/
	private Connection getWebjisConnection() {
		return Jsoup.connect(webjisConfig.getUrl()).headers(webjisConfig.getHeaders());
	}
}
