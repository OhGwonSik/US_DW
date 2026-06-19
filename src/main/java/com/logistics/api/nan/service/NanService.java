package com.logistics.api.nan.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.logistics.api.nan.config.NanConfig;
import com.logistics.api.nan.mapper.NanMapper;
import com.logistics.api.nan.vo.NanRequestDTO;
import com.logistics.api.nan.vo.NanResponseDTO;
import com.logistics.api.nan.vo.NanStockDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NanService {

	private final NanConfig nanConfig;
	private final SqlSessionTemplate sessionTemplate;
	private final NanMapper nanMapper;
	
	public NanResponseDTO getStockInformation() throws IOException {
		NanRequestDTO nanRequest = new NanRequestDTO();
		String receiveDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		nanRequest.setStandardDate(receiveDate);
		nanRequest.setWareHousePCode(nanConfig.getWareHousePCode());
		nanRequest.setWareHouseLCode(nanConfig.getWareHouseLCode());
		nanRequest.setProductCode(nanConfig.getProductCode());
		NanResponseDTO nanResponse = WebClient.builder().codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(100 * 1024 * 1024))
				            .build().post().uri(nanConfig.getUrl())
							.accept(MediaType.APPLICATION_JSON).header("ApiKey", nanConfig.getApiKey())
							.contentType(MediaType.APPLICATION_JSON).bodyValue(nanRequest)
							.retrieve().bodyToMono(NanResponseDTO.class).block();
		if(nanResponse == null) {
			throw new IOException("nan-api receive error!");
		}else if(!nanResponse.getResultCode().equals(nanConfig.getSuccessCode())) {
			throw new IOException(nanResponse.getResultMessage());
		}
		
		nanResponse.setIfstksq(nanMapper.selectIfstksq());
		
		return nanResponse;
	}
	
	public void saveNanStockInformation(List<NanStockDTO> nanStockList) {
		SqlSession sqlSession = sessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH);
		for(NanStockDTO nanStock : nanStockList) {
			nanStock.setCompkey(nanConfig.getCompkey());
			nanStock.setIfrcvur(nanConfig.getIfrcvur());
			sqlSession.insert("com.logistics.api.nan.mapper.NanMapper.insertNanStockInformation", nanStock);
		}
		sqlSession.flushStatements();
		sqlSession.close();
	}
}
