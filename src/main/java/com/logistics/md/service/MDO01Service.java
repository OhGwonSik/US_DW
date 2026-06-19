package com.logistics.md.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.NoSaveDataException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.McommaDTO;
import com.logistics.md.mapper.OrganizationMapper;

import lombok.RequiredArgsConstructor;

/*
*   클래스명 : MDO01Service
*   최초생성일시	: 2023.07.07
*   최초생성자 : Park T. S.
*   설명 : 회사 서비스 클래스
*/

@Service
@RequiredArgsConstructor
public class MDO01Service {
	private final OrganizationMapper organizationMapper;

	// 회사 리스트 조회
	public List<McommaDTO> getMcommaList(McommaDTO params) {
		return organizationMapper.selectMcommaList(params);
	}

	// mdo01 회사 데이터 저장
	public int saveMdo01(RequestDTO<McommaDTO> saveList) {
		if(saveList == null) {
			throw new NoSaveDataException();
		}

		int insertCnt = 0;

		if(saveList.getAddList() != null) {
			for(McommaDTO addData : saveList.getAddList()) {
				if(organizationMapper.insertMcomma(addData)==0) {
					throw new InsertCheckedException();
				}

				insertCnt++;
			}
		}

		int updateCnt = 0;

		if(saveList.getUpdateList() != null) {
			for(McommaDTO updateData : saveList.getUpdateList()) {
				if(organizationMapper.updateMcomma(updateData)==0) {
		    		throw new UpdateCheckedException();
				}

				updateCnt++;
			}
		}

		return insertCnt + updateCnt;
	}
}
