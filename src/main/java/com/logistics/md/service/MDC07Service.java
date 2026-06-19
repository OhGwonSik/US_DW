package com.logistics.md.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.NoSaveDataException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.mapper.CodeMapper;

import lombok.RequiredArgsConstructor;

/*
*   클래스명 : MDC07Service
*   최초생성일시	: 2023.07.18
*   최초생성자 : Park T. S.
*   설명 : 공통코드 서비스 클래스
*/

@Service
@RequiredArgsConstructor
public class MDC07Service {
	private final CodeMapper codeMapper;

	// 공통코드 리스트 조회
	public List<McodemDTO> getMcodemList(McodemDTO params) {
		return codeMapper.selectMcodemList(params);
	}

	// mdc07 공통코드 데이터 저장
	public int saveMdc07(RequestDTO<McodemDTO> saveList) {
		if(saveList == null) {
			throw new NoSaveDataException();
		}

		int insertCnt = 0;

		if(saveList.getAddList() != null) {
			for(McodemDTO saveData : saveList.getAddList()){
	    		if(codeMapper.insertMcodem(saveData)==0) {
	    			throw new InsertCheckedException();
	    		}

	    		insertCnt++;
			}
        }

		int updateCnt = 0;

		if(saveList.getUpdateList() != null && saveList.getOldList() != null) {
    		for(int i=0; i<saveList.getUpdateList().size(); i++) {
    			saveList.getUpdateList().get(i).setOldComcdky(saveList.getOldList().get(i).getComcdky());
    			saveList.getUpdateList().get(i).setOldComcdvl(saveList.getOldList().get(i).getComcdvl());

        		if(codeMapper.updateMcodem(saveList.getUpdateList().get(i))==0) {
            		throw new UpdateCheckedException();
            	}

        		updateCnt++;
    		}
        }

		return insertCnt + updateCnt;
	}

	// 공통코드 select box 데이터 조회
	public List<McodemDTO> getCommonCodeSelectBox(McodemDTO params) {
		if(params.getComcdky() != null) {
			params.setComcdkys(Arrays.asList(params.getComcdky().split(",")));
			params.setComcdky(null);
		}

		if(params.getComcdvl() != null) {
			params.setComcdvls(Arrays.asList(params.getComcdvl().split(",")));
			params.setComcdvl(null);
		}

		if(params.getCdcate2() != null) {
			params.setCdcate2s(Arrays.asList(params.getCdcate2().split(",")));
			params.setCdcate2(null);
		}

		return codeMapper.selectMcodem(params);
	}
}
