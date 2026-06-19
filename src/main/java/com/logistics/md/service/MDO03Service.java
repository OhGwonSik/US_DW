package com.logistics.md.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.NoSaveDataException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.MaremaDTO;
import com.logistics.md.mapper.OrganizationMapper;

import lombok.RequiredArgsConstructor;

/*
*   클래스명 : MDO03Service
*   최초생성일시	: 2023.07.07
*   최초생성자 : Park T. S.
*   설명 : 에어리어 서비스 클래스
*/

@Service
@RequiredArgsConstructor
public class MDO03Service {
	private final OrganizationMapper organizationMapper;

	// 에어리어 리스트 조회
	public List<MaremaDTO> getMaremaList(MaremaDTO params) {
		return organizationMapper.selectMaremaList(params);
	}

	// mdo03 에어리어 데이터 저장
	public int saveMdo3(RequestDTO<MaremaDTO> saveList) {
		if(saveList == null) {
			throw new NoSaveDataException();
		}

		int insertCnt = 0;

		if(saveList.getAddList() != null) {
			for(MaremaDTO data : saveList.getAddList()){
	    		if(organizationMapper.insertMarema(data)==0) {
	    			throw new InsertCheckedException();
	    		}

	    		insertCnt++;
			}
        }

		int updateCnt = 0;

		if(saveList.getUpdateList() != null && saveList.getOldList() != null) {
    		for(int i=0; i<saveList.getUpdateList().size(); i++) {
    			saveList.getUpdateList().get(i).setOldWarekey(saveList.getOldList().get(i).getWarekey());
    			saveList.getUpdateList().get(i).setOldAreakey(saveList.getOldList().get(i).getAreakey());

        		if(organizationMapper.updateMarema(saveList.getUpdateList().get(i)) == 0) {
            		throw new UpdateCheckedException();
            	}

        		updateCnt++;
    		}
        }

		return insertCnt + updateCnt;
	}

	// 에어리어 select box 데이터 조회
	public List<CommonDTO> getAreaSelectBox(MaremaDTO params) {
		return organizationMapper.selectMaremaSelectBox(params);
	}
}
