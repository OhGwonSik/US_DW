package com.logistics.md.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.NoSaveDataException;
import com.logistics.md.domain.MdmAccountCustomerDTO;
import com.logistics.md.domain.MowrmaDTO;
import com.logistics.md.mapper.PartnerMapper;

import lombok.RequiredArgsConstructor;

/*
*   클래스명 : MDP10Service
*   최초생성일시	: 2023.08.11
*   최초생성자 : Park T. S.
*   설명 : 화주수신 서비스 클래스
*/

@Service
@RequiredArgsConstructor
public class MDP10Service {
	private final PartnerMapper partnerMapper;

	// MDM 화주 리스트 조회
	public List<MdmAccountCustomerDTO> getMDMOwnerList(MdmAccountCustomerDTO params){
		return partnerMapper.selectMdmAccuontCustomerList(params);
	}

	// mdp10 화주 데이터 저장
	public int saveMDMOwnerList(RequestDTO<MowrmaDTO> saveList){
		if(saveList == null) {
			throw new NoSaveDataException();
		}

		int insertCnt = 0;

		if(saveList.getAddList() != null) {
			for(MowrmaDTO data : saveList.getAddList()){
				if(partnerMapper.insertMdmOwnerDataToMowrma(data)==0) {
		    		throw new InsertCheckedException();
				}

				insertCnt++;
			}
        }

		return insertCnt;
	}
}
