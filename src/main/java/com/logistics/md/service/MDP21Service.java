package com.logistics.md.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.NoSaveDataException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.MptnmaDTO;
import com.logistics.md.mapper.PartnerMapper;

import lombok.RequiredArgsConstructor;

/*
*   클래스명 : MDP21Service
*   최초생성일시	: 2023.08.04
*   최초생성자 : Park T. S.
*   설명 : 업체등록 서비스 클래스
*/

@Service
@RequiredArgsConstructor
public class MDP21Service {
	private final PartnerMapper partnerMapper;

	// 업체 리스트 조회
	public List<MptnmaDTO> getMptnmaList(MptnmaDTO params) {
		return partnerMapper.selectMptnmaList(params);
	}

	// mdp21 업체 데이터 저장
	public int saveMdp21(RequestDTO<MptnmaDTO> saveList) {
		if(saveList == null) {
			throw new NoSaveDataException();
		}

		int insertCnt = 0;

		if(saveList.getAddList() != null) {
			for(MptnmaDTO saveData : saveList.getAddList()){
	    		if(partnerMapper.insertMptnma(saveData)==0) {
	    			throw new InsertCheckedException();
	    		}

	    		insertCnt++;
			}
		}

		int updateCnt = 0;

		if(saveList.getUpdateList() != null && saveList.getOldList() != null) {
			for(int i=0; i< saveList.getUpdateList().size(); i++) {
				saveList.getUpdateList().get(i).setOldPtnrkey(saveList.getOldList().get(i).getPtnrkey());

	    		if(partnerMapper.updateMptnma(saveList.getUpdateList().get(i)) == 0) {
	        		throw new UpdateCheckedException();
	        	}

	    		updateCnt++;
			}
		}

		return insertCnt + updateCnt;
	}

	// 업체(vender) select box 데이터 조회
	public List<CommonDTO> getVenderSelectBox(MptnmaDTO params) {
		return partnerMapper.getMdp02SelectBox(params);
	}

	// 업체 select box 데이터 조회
	public List<CommonDTO> getPartnerSelectBox(MptnmaDTO params) {
		if(params.getPtnrtyp() != null) {
			params.setPtnrtyps(Arrays.asList(params.getPtnrtyp().split(",")));
			params.setPtnrtyp(null);
		}
		return partnerMapper.getMdp02SelectBoxByAll(params);
	}

	// 파트너키 채번
	public String mdp21Ptnrkey() {
		return partnerMapper.getPtnrkey();
	}

}
