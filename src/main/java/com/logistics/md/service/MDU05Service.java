package com.logistics.md.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.NoSaveDataException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.MpakmaDTO;
import com.logistics.md.mapper.UnitsMapper;

import lombok.RequiredArgsConstructor;

/*
*   클래스명 : MDU05Service
*   최초생성일시	: 2023.07.28
*   최초생성자 : Park T. S.
*   설명 : 입수관리 서비스 클래스
*/

@Service
@RequiredArgsConstructor
public class MDU05Service {
	private final UnitsMapper unitsMapper;

	// 입수관리 리스트 조회
	public List<MpakmaDTO> getMpakmaList(MpakmaDTO params) {
		return unitsMapper.selectMpakmaList(params);
	}

	// mdu05 입수관리 데이터 저장
	public int saveMdu05(RequestDTO<MpakmaDTO> saveList) {
		if(saveList == null) {
			throw new NoSaveDataException();
		}

		int insertCnt = 0;

		if(saveList.getAddList() != null) {
			for(MpakmaDTO data : saveList.getAddList()){
	    		if(unitsMapper.insertMpakma(data)==0) {
	    			throw new InsertCheckedException();
	    		}

	    		insertCnt++;
			}
        }

		int updateCnt = 0;

		if(saveList.getUpdateList() != null && saveList.getOldList() != null) {
    		for(int i=0; i<saveList.getUpdateList().size(); i++) {
    			saveList.getUpdateList().get(i).setOldWarekey(saveList.getOldList().get(i).getWarekey());
    			saveList.getUpdateList().get(i).setOldPackkey(saveList.getOldList().get(i).getPackkey());
    			saveList.getUpdateList().get(i).setOldTruntyp(saveList.getOldList().get(i).getTruntyp());

        		if(unitsMapper.updateMpakma(saveList.getUpdateList().get(i))==0) {
            		throw new UpdateCheckedException();
            	}

        		updateCnt++;
    		}
        }

		return insertCnt + updateCnt;
	}

	// 이동용기별 입수관리 select box 데이터 조회
	public List<CommonDTO> getPackTransferSelectBox(MpakmaDTO params) {
		return unitsMapper.selectMpakmaSelectBox(params);
	}

	// 입수관리 select box 데이터 조회
   	public List<CommonDTO> getPackBoxSelectBox(MpakmaDTO params) {
   		return unitsMapper.selectMpakmaPackSelectBox(params);
   	}

   	// 이동용기-입수량 관계 리스트 조회
   	public List<MpakmaDTO> selectMpakmaRelations(MpakmaDTO params){
   		return unitsMapper.selectMpakmaRelations(params);
   	}
}

