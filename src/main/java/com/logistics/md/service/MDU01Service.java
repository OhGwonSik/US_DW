package com.logistics.md.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.NoSaveDataException;
import com.logistics.md.domain.MdmSkuDTO;
import com.logistics.md.domain.MowrmaDTO;
import com.logistics.md.domain.MskuwcDTO;
import com.logistics.md.mapper.PartnerMapper;
import com.logistics.md.mapper.UnitsMapper;
import com.logistics.sy.domain.UserVO;

import lombok.RequiredArgsConstructor;

/*
*   클래스명 : MDU01Service
*   최초생성일시	: 2023.11
*   최초생성자 : Park T. S.
*   설명 : 부품수신 서비스 클래스
*/

@Service
@RequiredArgsConstructor
public class MDU01Service {
	private final UnitsMapper unitsMapper;
	private final PartnerMapper partnerMapper;

	// MDM 부품 리스트 조회
	public List<MdmSkuDTO> getMDMSkuList(MdmSkuDTO params){
		return unitsMapper.selectMDMSkuList(params);
	}

	// WM 부품 리스트 조회
	public List<MskuwcDTO> getMdu01ItemList(MskuwcDTO params){
		return unitsMapper.selectMskuwcWithOwnerName(params);
	}

	// mdu01 부품 데이터 저장
	public int saveMDMSkuList(RequestDTO<MskuwcDTO> saveList){
		if(saveList == null) {
			throw new NoSaveDataException();
		}

		MowrmaDTO mowrmaDTO = new MowrmaDTO();

		mowrmaDTO.setUserData((UserVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

		List<String> ownerList = partnerMapper.selectMowrmaList(mowrmaDTO).stream().map(t -> t.getOwnerky()).toList();

		int insertCnt = 0;

		if(saveList.getAddList() != null) {
			for(MskuwcDTO data : saveList.getAddList()){
				if(!ownerList.contains(data.getOwnerky())) {
		    		throw new InsertCheckedException();
				}

				if(unitsMapper.insertMDMSkuListToMskuwc(data)==0) {
		    		throw new InsertCheckedException();
				}

				insertCnt++;
			}
        }

		return insertCnt;
	}
}
