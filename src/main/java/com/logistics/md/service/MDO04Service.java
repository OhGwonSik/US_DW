package com.logistics.md.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.NoSaveDataException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.MzonmaDTO;
import com.logistics.md.mapper.OrganizationMapper;

import lombok.RequiredArgsConstructor;

/*
*   클래스명 : MDO04Service
*   최초생성일시	: 2023.07.07
*   최초생성자 : Park T. S.
*   설명 : 존 서비스 클래스
*/

@Service
@RequiredArgsConstructor
public class MDO04Service {
	private final OrganizationMapper organizationMapper;

	// 존 리스트 조회
	public List<MzonmaDTO> getMzonmaList(MzonmaDTO params) {
		return organizationMapper.selectMzonmaList(params);
	}

	// mdo04 존 데이터 저장
	public int saveMdo4(RequestDTO<MzonmaDTO> saveList) {
		if(saveList == null) {
			throw new NoSaveDataException();
		}

		int insertCnt = 0;

		if(saveList.getAddList() != null) {
			for(MzonmaDTO data : saveList.getAddList()){
	    		if(organizationMapper.insertMzonma(data)==0) {
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
    			saveList.getUpdateList().get(i).setOldZonekey(saveList.getOldList().get(i).getZonekey());

        		if(organizationMapper.updateMzonma(saveList.getUpdateList().get(i)) == 0) {
            		throw new UpdateCheckedException();
            	}

        		updateCnt++;
    		}
        }

		return insertCnt+updateCnt;
	}

	// 존 select box 데이터 조회
	public List<CommonDTO> getZoneSelectBox(MzonmaDTO params) {
		return organizationMapper.selectMzonmaSelectBox(params);
	}

	// 창고-에어리어 관계 데이터 조회
	public List<MzonmaDTO> selectWareAreaRelations(MzonmaDTO params) {
		return organizationMapper.selectWareAreaRelations(params);
	}
}
