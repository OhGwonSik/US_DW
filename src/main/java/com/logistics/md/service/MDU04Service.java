package com.logistics.md.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.MtutmaDTO;
import com.logistics.md.mapper.UnitsMapper;

import lombok.RequiredArgsConstructor;

/*
*   클래스명 : MDU04Service
*   최초생성일시	: 2023.07.21
*   최초생성자 : Park T. S.
*   설명 : 이동용기 서비스 클래스
*/

@Service
@RequiredArgsConstructor
public class MDU04Service {
	private final UnitsMapper unitsMapper ;

	// 이동용기 리스트 조회
	public List<MtutmaDTO> getMtutmaList(MtutmaDTO params) {
		return unitsMapper.selectMtutmaList(params);
	}

	// mdu04 이동용기 데이터 저장
	public int saveMdu04(RequestDTO<MtutmaDTO> saveList) {
		int insertCnt = 0;

		if(saveList.getAddList() != null) {
			for(MtutmaDTO data : saveList.getAddList()){
	    		if(unitsMapper.insertMtutma(data)==0) {
	    			throw new InsertCheckedException();
	    		}

	    		insertCnt++;
			}
        }

		int updateCnt = 0;

		if(saveList.getUpdateList() != null && saveList.getOldList() != null) {
    		for(int i=0; i<saveList.getUpdateList().size(); i++) {
    			saveList.getUpdateList().get(i).setOldTruntyp(saveList.getOldList().get(i).getTruntyp());

        		if(unitsMapper.updateMtutma(saveList.getUpdateList().get(i))==0) {
            		throw new UpdateCheckedException();
            	}

        		updateCnt++;
    		}
        }

		return insertCnt + updateCnt;
	}

	// 이동용기 select box 데이터 조회
	public List<CommonDTO> getTransferUnitSelectBox(MtutmaDTO params) {
		return unitsMapper.selectMtutmaSelectBox(params);
	}
}

