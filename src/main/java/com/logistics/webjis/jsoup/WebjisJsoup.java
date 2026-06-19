package com.logistics.webjis.jsoup;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import com.logistics.webjis.vo.WebjisVO;

@Component
public class WebjisJsoup {
	
	/*  getWebjisList - 일일 웹지스 데이터 크롤링 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수
	*   입력 PARAMETA	: Connection con - JSOUP Connection
	*                     Map<String, String> data - 크롤링을 위한 Form Data
	*                     boolean isUpdate - insert or update flag
	*   출력 PARAMETA	: List<WebjisVO> - 크롤링 List
	*   설명			: 웹지스 데이터 크롤링
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		:  
	*/
	public List<WebjisVO> getWebjisList(Connection webjisCon, Map<String, String> data, boolean isUpdate) throws IOException {
		List<WebjisVO> webjisList = new ArrayList<>();
		Document webjisDoc = getWebjisDocument(webjisCon, data);
		List<Element> trElements = Arrays
				.asList(webjisDoc.select("TABLE.gridRowTable>tbody>tr.gridRow").toArray(new Element[0]));
		for (Element trElement : trElements) {
			List<Element> tdElements = Arrays.asList(trElement.select("td").toArray(new Element[0]));
			if(tdElements.size() != 11) {
				throw new IOException("td data length unmatched!");
			}

			String receiveDateTime = tdElements.get(9).text();
			String checkDateTime = tdElements.get(10).text();
			if(isUpdate && !checkDateTime.isEmpty()) {
				DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
		        LocalDateTime receiveDate = LocalDateTime.parse(receiveDateTime, dateTimeFormatter);
		        LocalDateTime checkDate = LocalDateTime.parse(checkDateTime, dateTimeFormatter);
		        if(receiveDate.toLocalDate().isEqual(checkDate.toLocalDate())) {
		        	continue;
		        }
			}
			WebjisVO webjisVO = new WebjisVO();
			webjisVO.setPalletNo(tdElements.get(0).text());
			webjisVO.setCreateDateTime(tdElements.get(1).text());
			webjisVO.setPrintDateTime(tdElements.get(2).text());
			webjisVO.setSeq(tdElements.get(3).text());
			webjisVO.setAlc(tdElements.get(4).text());
			webjisVO.setCarType(tdElements.get(5).text());
			webjisVO.setGoodsCode(tdElements.get(6).text());
			webjisVO.setGoodsName(tdElements.get(7).text());
			webjisVO.setBodyNo(tdElements.get(8).text());
			webjisVO.setReceiveDateTime(receiveDateTime);
			webjisVO.setCheckDateTime(checkDateTime);
			webjisList.add(webjisVO);
		}
		return webjisList;
	}

	/*  getWebjisDocument - 웹지스 post 통신 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수
	*   입력 PARAMETA	: Connection con - JSOUP Connection
	*                     Map<String, String> data - 크롤링을 위한 Form Data
	*   출력 PARAMETA	: Document
	*   설명			: 웹지스 크롤링을 위한 form data post 통신을 위한 메소드. 
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		:  
	*/
	private Document getWebjisDocument(Connection webjisCon, Map<String, String> data) throws IOException {
		return webjisCon.data(data).post();
	}
}
