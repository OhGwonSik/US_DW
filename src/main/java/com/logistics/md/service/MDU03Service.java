package com.logistics.md.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.NoSaveDataException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.MuommaDTO;
import com.logistics.md.mapper.UnitsMapper;

import lombok.RequiredArgsConstructor;

/*
*   클래스명 : MDU03Service
*   최초생성일시	: 2023.07.21
*   최초생성자 : Park T. S.
*   설명 : 단위 서비스 클래스
*/

@Service
@RequiredArgsConstructor
public class MDU03Service {
	private final UnitsMapper unitsMapper;

	// 단위 리스트 조회
	public List<MuommaDTO> selectMuommaList(MuommaDTO params) {
		return unitsMapper.selectMuomma(params);
	}

	// mdu03 단위 데이터 저장
	public int saveMdu03(RequestDTO<MuommaDTO> saveList) {
		if(saveList == null) {
			throw new NoSaveDataException();
		}

		int insertCnt = 0;

		if(saveList.getAddList() != null) {
			for(MuommaDTO saveData : saveList.getAddList()){
	    		if(unitsMapper.insertMuomma(saveData)==0) {
	    			throw new InsertCheckedException();
	    		}

	    		insertCnt++;
			}
        }

		int updateCnt = 0;

		if(saveList.getUpdateList() != null && saveList.getOldList() != null) {
    		for(int i=0; i<saveList.getUpdateList().size(); i++) {
    			saveList.getUpdateList().get(i).setOldUomekey(saveList.getOldList().get(i).getUomekey());

        		if(unitsMapper.updateMuomma(saveList.getUpdateList().get(i))==0) {
            		throw new UpdateCheckedException();
            	}

        		updateCnt++;
    		}
        }

		return insertCnt + updateCnt;
	}

	// 단위 select box 데이터 조회
	public List<CommonDTO> getUnitOfMeasureSelectBox(MuommaDTO params) {
		return unitsMapper.selectUnitsSelectBox(params);
	}
}

