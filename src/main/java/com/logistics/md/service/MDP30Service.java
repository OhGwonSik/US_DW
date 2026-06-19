package com.logistics.md.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.NoSaveDataException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.MdesmaDTO;
import com.logistics.md.mapper.PartnerMapper;

import lombok.RequiredArgsConstructor;

/*
*   클래스명 : MDP30Service
*   최초생성일시	: 2023.08.18
*   최초생성자 : Park T. S.
*   설명 : 도착지등록 서비스 클래스
*/

@Service
@RequiredArgsConstructor
public class MDP30Service {
	private final PartnerMapper partnerMapper;

	// 도착지 리스트 조회
	public List<MdesmaDTO> getMdesmaList(MdesmaDTO params){
        return partnerMapper.selectMdesmaList(params);
	}

	// mdp30 도착지 데이터 저장
	public int saveMdp30(RequestDTO<MdesmaDTO> saveList)  {
		if(saveList == null) {
			throw new NoSaveDataException();
		}

		int insertCnt = 0;

		if(saveList.getAddList() != null) {
			for(MdesmaDTO saveData : saveList.getAddList()){
	    		if(partnerMapper.insertMdesma(saveData)==0) {
	    			throw new InsertCheckedException();
	    		}

	    		insertCnt++;
			}
        }

		int updateCnt = 0;

		if(saveList.getUpdateList() != null && saveList.getOldList() != null) {
    		for(int i=0; i<saveList.getUpdateList().size(); i++) {
    			saveList.getUpdateList().get(i).setOldPtnrkey(saveList.getOldList().get(i).getPtnrkey());
    			saveList.getUpdateList().get(i).setOldDestkey(saveList.getOldList().get(i).getDestkey());

        		if(partnerMapper.updateMdesma(saveList.getUpdateList().get(i))==0) {
            		throw new UpdateCheckedException();
            	}

        		updateCnt++;
    		}
        }

		return insertCnt + updateCnt;
	}
}
