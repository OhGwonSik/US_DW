package com.logistics.md.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.NoSaveDataException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.MwarmaDTO;
import com.logistics.md.mapper.OrganizationMapper;

import lombok.RequiredArgsConstructor;

/*
*   클래스명 : MDO02Service
*   최초생성일시	: 2023.07.07
*   최초생성자 : Park T. S.
*   설명 : 창고 서비스 클래스
*/

@Service
@RequiredArgsConstructor
public class MDO02Service {
	private final OrganizationMapper organizationMapper;

	// 창고 리스트 조회
	public List<MwarmaDTO> getMwarmaList(MwarmaDTO params) {
		return organizationMapper.selectMwarmaList(params);
	}

	// mdo02 창고 데이터 저장
	public int saveMdo02(RequestDTO<MwarmaDTO> saveList) {
		if(saveList == null) {
			throw new NoSaveDataException();
		}

		int insertCnt = 0;

		if(saveList.getAddList() != null) {
			for(MwarmaDTO data : saveList.getAddList()) {
				if(organizationMapper.insertMwarma(data) == 0) {
					throw new InsertCheckedException();
				}

				insertCnt++;
			}
		}

		int updateCnt = 0;

		if (saveList.getUpdateList() != null && saveList.getOldList() != null) {
			for (int i=0; i<saveList.getUpdateList().size(); i++) {
				saveList.getUpdateList().get(i).setOldWarekey(saveList.getOldList().get(i).getWarekey());

				if (organizationMapper.updateMwarma(saveList.getUpdateList().get(i))==0) {
					throw new UpdateCheckedException();
				}

				updateCnt++;
			}
		}

		return insertCnt + updateCnt;
	}

	// 창고 select box 데이터 조회
	public List<CommonDTO> getWarehouseSelectBox(MwarmaDTO params) {
		return organizationMapper.selectWarehouseSelectBox(params);
	}

	// 유저별 창고 select box 데이터 조회
	public List<CommonDTO> getWarehouseUserSelectBox(MwarmaDTO params) {
		return organizationMapper.selectWarehouseUserSelectBox(params);
	}
}
