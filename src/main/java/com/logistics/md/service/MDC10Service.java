package com.logistics.md.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.NoSaveDataException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.MlscalDTO;
import com.logistics.md.mapper.CodeMapper;

import lombok.RequiredArgsConstructor;

/*
*   클래스명 : MDC10Service
*   최초생성일시	: 2023.08.18
*   최초생성자 : Park T. S.
*   설명 : 물류달력 서비스 클래스
*/

@Service
@RequiredArgsConstructor
public class MDC10Service {
	private final CodeMapper codeMapper;

	// 물류달력 리스트 조회
	public List<MlscalDTO> getMlscalList(MlscalDTO params) {
		return codeMapper.selectMlscalList(params);
	}

	// mdc10 물류달력 데이터 저장
	public int saveMdc10(RequestDTO<MlscalDTO> saveList) {
		if(saveList == null) {
			throw new NoSaveDataException();
		}

		int updateCnt = 0;

		if(saveList.getUpdateList() != null && saveList.getOldList() != null) {
    		for(MlscalDTO data : saveList.getUpdateList()) {
        		if(codeMapper.updateMlscal(data)==0) {
            		throw new UpdateCheckedException();
            	}

        		updateCnt++;
    		}
        }

		return updateCnt;
	}
}
