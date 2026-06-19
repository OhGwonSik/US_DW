package com.logistics.md.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.common.dto.CommonDTO;
import com.logistics.md.domain.MdmAccountCustomerDTO;
import com.logistics.md.domain.MdesmaDTO;
import com.logistics.md.domain.MowrmaDTO;
import com.logistics.md.domain.MptnmaDTO;

/*
*   클래스명 : PartnerMapper
*   최초생성일시	: 2023.08.04
*   최초생성자 : Park T. S.
*   설명 : 화주, 업체 매퍼 클래스
*/

@Mapper
public interface PartnerMapper {
	// mdp11 : 화주 리스트 조회
	List<MowrmaDTO> selectMowrmaList(MowrmaDTO params);
	// mdp11 : 화주 생성
	int insertMowrma(MowrmaDTO addData);
	// mdp11 : 화주 수정
	int updateMowrma(MowrmaDTO updateData);
	// 화주 select box data list
	List<CommonDTO> selectOwnerSelectBox(MowrmaDTO params);

	// mdp10 : MDM Owner 조회
	List<MdmAccountCustomerDTO> selectMdmAccuontCustomerList(MdmAccountCustomerDTO params);

	// mdp10 : MDM Owner 수신 데이터 저장
	int insertMdmOwnerDataToMowrma(MowrmaDTO saveData);

	// mdp20 : MDM 거래처 수신데이터 저장
	int insertMdmPartnerDataToMptnma(MptnmaDTO saveData);

	//mdp21 : 업체 조회
	List<MptnmaDTO> selectMptnmaList(MptnmaDTO params);
	//mdp21 : 업체 생성
	int insertMptnma(MptnmaDTO addData);
	//mdp21 : 업체 수정
	int updateMptnma(MptnmaDTO updateData);
	// mdp21 : 파트너 키 채번
	String getPtnrkey();
	// 업체(vender) select box list
	List<CommonDTO> getMdp02SelectBox(MptnmaDTO params);
	// 업체 select box list
	List<CommonDTO> getMdp02SelectBoxByAll(MptnmaDTO params);

	// mdp30 : 도착지 조회
	List<MdesmaDTO> selectMdesmaList(MdesmaDTO params);
	// mdp30 : 도착지 생성
	int insertMdesma(MdesmaDTO addData);
	// mdp30 : 도착지 수정
	int updateMdesma(MdesmaDTO updateData);

	// 도착지 select box list
	List<Map<String, Object>> getMdp30SelectBoxByAll(MdesmaDTO param);

	//Destkey SelectBox 2023.10.11최강호
	List<MdesmaDTO> getDestKeySelectBox(MdesmaDTO params);
}
