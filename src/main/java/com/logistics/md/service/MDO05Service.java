package com.logistics.md.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.NoSaveDataException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.MlocmaDTO;
import com.logistics.md.mapper.OrganizationMapper;

import lombok.RequiredArgsConstructor;

/*
*   클래스명 : MDO05Service
*   최초생성일시	: 2023.07.07
*   최초생성자 : Park T. S.
*   설명 : 로케이션 서비스 클래스
*/

@Service
@RequiredArgsConstructor
public class MDO05Service {
	private final OrganizationMapper organizationMapper;

	// 로케이션 리스트 조회
	public List<MlocmaDTO> selectMlocmaList(MlocmaDTO params){
        return organizationMapper.selectMlocmaList(params);
	}

	// mdo05 로케이션 데이터 저장
	public int saveMdo05(RequestDTO<MlocmaDTO> saveList)  {
		if(saveList == null) {
			throw new NoSaveDataException();
		}

		int insertCnt = 0;

		if(saveList.getAddList() != null) {
			for(MlocmaDTO saveData : saveList.getAddList()){
	    		if(organizationMapper.insertMlocma(saveData)==0) {
	    			throw new InsertCheckedException();
	    		}

	    		insertCnt++;
			}
        }

		int updateCnt = 0;

		if(saveList.getUpdateList() != null && saveList.getOldList() !=null) {
    		for(int i=0; i<saveList.getUpdateList().size(); i++) {
    			saveList.getUpdateList().get(i).setOldWarekey(saveList.getOldList().get(i).getWarekey());
    			saveList.getUpdateList().get(i).setOldAreakey(saveList.getOldList().get(i).getAreakey());
    			saveList.getUpdateList().get(i).setOldZonekey(saveList.getOldList().get(i).getZonekey());
    			saveList.getUpdateList().get(i).setOldLocakey(saveList.getOldList().get(i).getLocakey());

        		if(organizationMapper.updateMlocma(saveList.getUpdateList().get(i))==0) {
            		throw new UpdateCheckedException();
            	}

        		updateCnt++;
    		}
        }

		return insertCnt+updateCnt;
	}

	// 로케이션 select box 조회
	public List<CommonDTO> getMultiLocationSelectBox(MlocmaDTO params) {
		return organizationMapper.selectMlocmaSelectBox(params);
	}

	// 로케이션 단건 select box 조회
	public List<MlocmaDTO> getSingleLocationSelectBox(MlocmaDTO params){
        return organizationMapper.selectMlocma(params);
	}

	// 창고-에어리어-존 관계 데이터 조회
	public List<MlocmaDTO> selectWareAreaZoneRelations(MlocmaDTO params) {
		return organizationMapper.selectWareAreaZoneRelations(params);
	}

	// 창고-에어리어-존-로케이션 관계 데이터 조회
	public List<MlocmaDTO> selectWareAreaZoneLocationRelations(MlocmaDTO params) {
		return organizationMapper.selectWareAreaZoneLocationRelations(params);
	}
}
