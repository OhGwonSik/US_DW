package com.logistics.md.service;

import java.util.Arrays;

import java.util.List;

import org.springframework.stereotype.Service;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.NoSaveDataException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.MdocmaDTO;
import com.logistics.md.domain.MrscmaDTO;
import com.logistics.md.mapper.CodeMapper;
import com.logistics.md.mapper.DocumentMapper;

import lombok.RequiredArgsConstructor;

/*
*   클래스명 : MDC06Service
*   최초생성일시	: 2023.07.18
*   최초생성자 : Park T. S.
*   설명 : 사유코드 서비스 클래스
*/

@Service
@RequiredArgsConstructor
public class MDC06Service {
	private final CodeMapper codeMapper;
	private final DocumentMapper documentMapper;

	/**
	 * 문서 카테고리 그룹 리스트
	 **/
	public List<MdocmaDTO> getDoccateGroupListSelect(MdocmaDTO params) {
        return documentMapper.selectMdocmaDoccateGroupList(params);
	}

	/**
	 * 문서 타입 리스트
	 **/
	public List<MdocmaDTO> getDoctypeList(MdocmaDTO params) {
    	if(params.getDoctype() != null) {
    		params.setDoctypes(Arrays.asList(params.getDoctype().split(",")));
    		params.setDoctype(null);
    	}

    	if(params.getDoccate() != null) {
    		params.setDoccates(Arrays.asList(params.getDoccate().split(",")));
    		params.setDoccate(null);
    	}

        return documentMapper.selectMdocmaDoctypeList(params);
	}

	// 사유코드 리스트 조회
	public List<MrscmaDTO> getMrscmaList(MrscmaDTO params) {
        return codeMapper.selectMrscmaList(params);
	}

	// mdc06 사유코드 데이터 저장
	public int saveMdc06(RequestDTO<MrscmaDTO> saveList) {
		if(saveList == null) {
			throw new NoSaveDataException();
		}

		int insertCnt = 0;

		if(saveList.getAddList() != null) {
			for(MrscmaDTO data : saveList.getAddList()){
	    		if(codeMapper.insertMrscma(data)==0) {
	    			throw new InsertCheckedException();
	    		}

	    		insertCnt++;
			}
        }

		int updateCnt = 0;

		if(saveList.getUpdateList() != null && saveList.getOldList() != null) {
    		for(int i=0; i<saveList.getUpdateList().size(); i++) {
    			saveList.getUpdateList().get(i).setOldWarekey(saveList.getOldList().get(i).getWarekey());
    			saveList.getUpdateList().get(i).setOldRsncode(saveList.getOldList().get(i).getRsncode());

        		if(codeMapper.updateMrscma(saveList.getUpdateList().get(i))==0) {
            		throw new UpdateCheckedException();
            	}

        		updateCnt++;
    		}
        }

		return insertCnt + updateCnt;
	}

	// 사유코드 select box용 리스트 조회
	public List<CommonDTO> getReasonCodeSelectBox(MrscmaDTO params) {
		return documentMapper.selectMrscmaSelectBox(params);
	}

	// 문서 리스트 조회
	public List<MrscmaDTO> getMdocmaAllDoccateList(MdocmaDTO params){
		return documentMapper.selectMdocmaAllDoccateList(params);
	}

	// 문서 관계 리스트 조회
	public List<MrscmaDTO> getMdocmaDoccateDoctypeRelations(MdocmaDTO params){
		return documentMapper.selectMdocmaDoccateDoctypeRelations(params);
	}

	// 사유코드 관계 리스트 조회
	public List<MrscmaDTO> getReasonCodeRelations(MrscmaDTO params){
		return codeMapper.selectReasonCodeRelations(params);
	}
}
