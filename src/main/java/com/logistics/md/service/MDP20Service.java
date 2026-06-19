package com.logistics.md.service;

import org.springframework.stereotype.Service;

import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.NoSaveDataException;
import com.logistics.md.domain.MptnmaDTO;
import com.logistics.md.mapper.PartnerMapper;

import lombok.RequiredArgsConstructor;

/*
*   클래스명 : MDP20Service
*   최초생성일시	: 2023.08.11
*   최초생성자 : Park T. S.
*   설명 : 업체수신 서비스 클래스
*/

@Service
@RequiredArgsConstructor
public class MDP20Service {
	private final PartnerMapper partnerMapper;

	// mdp20 업체 데이터 저장
	public int saveMDMPartnerList(RequestDTO<MptnmaDTO> saveList) {
		if(saveList == null) {
			throw new NoSaveDataException();
		}

		int insertCnt = 0;

		if(saveList.getAddList() != null) {
			for(MptnmaDTO data : saveList.getAddList()){
				if(partnerMapper.insertMdmPartnerDataToMptnma(data)==0) {
		    		throw new InsertCheckedException();
				}

				insertCnt++;
			}
        }

		return insertCnt;
	}
}
