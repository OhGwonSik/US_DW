package com.logistics.md.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.NoSaveDataException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.MowrmaDTO;
import com.logistics.md.mapper.PartnerMapper;

import lombok.RequiredArgsConstructor;

/*
*   클래스명 : MDP11Service
*   최초생성일시	: 2023.08.04
*   최초생성자 : Park T. S.
*   설명 : 화주등록 서비스 클래스
*/

@Service
@RequiredArgsConstructor
public class MDP11Service {
	private final PartnerMapper partnerMapper;

	// 화주 리스트 조회
	public List<MowrmaDTO> getMowrmaList(MowrmaDTO params){
		return partnerMapper.selectMowrmaList(params);
	}

	// mdp11 화주 데이터 저장
	public int saveMdp11(RequestDTO<MowrmaDTO> saveList) {
		if(saveList == null) {
			throw new NoSaveDataException();
		}

		int insertCnt = 0;

		if(saveList.getAddList() != null) {
			for(MowrmaDTO saveData : saveList.getAddList()){
	    		if(partnerMapper.insertMowrma(saveData)==0) {
	    			throw new InsertCheckedException();
	    		}

	    		insertCnt++;
			}
		}

		int updateCnt = 0;

		if(saveList.getUpdateList() != null && saveList.getOldList() != null) {
			for(int i=0; i< saveList.getUpdateList().size(); i++) {
				saveList.getUpdateList().get(i).setOldOwnerky(saveList.getOldList().get(i).getOwnerky());

	    		if(partnerMapper.updateMowrma(saveList.getUpdateList().get(i)) == 0) {
	        		throw new UpdateCheckedException();
	        	}

	    		updateCnt++;
			}
		}

		return insertCnt + updateCnt;
	}

	// 화주 select box 데이터 조회
    public List<CommonDTO> getOwnerSelectBox(MowrmaDTO params) {
		return partnerMapper.selectOwnerSelectBox(params);
	}
}
