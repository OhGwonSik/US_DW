package com.logistics.md.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.common.dto.CommonDTO;
import com.logistics.md.domain.MaremaDTO;
import com.logistics.md.domain.McommaDTO;
import com.logistics.md.domain.MlocmaDTO;
import com.logistics.md.domain.MwarmaDTO;
import com.logistics.md.domain.MzonmaDTO;

/*
*   클래스명 : OrganizationMapper
*   최초생성일시	: 2023.07.07
*   최초생성자 : Park T. S.
*   설명 : 회사, 창고, 에어리어, 존, 로케이션 매퍼 클래스
*/

/* xml mapper :  mybatis header */
@Mapper
public interface OrganizationMapper {
    // ***********************************************
    // Company : mcomma
    // ***********************************************
	List<McommaDTO> selectMcommaList(McommaDTO params);
	int insertMcomma(McommaDTO addData);
	int updateMcomma(McommaDTO updateData);

    // ***********************************************
    // Warehouse : mwarma
    // ***********************************************
	//mdo02 : 창고 조회
	List<MwarmaDTO> selectMwarmaList(MwarmaDTO params);
	//mdo02 : 창고 생성
	int insertMwarma(MwarmaDTO addData);
	//mdo02 : 창고 수정
	int updateMwarma(MwarmaDTO updateData);
	//mdo02 : 창고 SelectBox 조회
	List<CommonDTO> selectWarehouseSelectBox(MwarmaDTO params);
	//mdo02 : 사용자별 창고 SelectBox 조회
	List<CommonDTO> selectWarehouseUserSelectBox(MwarmaDTO params);

    // ***********************************************
    // Area : marema
    // ***********************************************
	//mdo03 : 영역 조회
	List<MaremaDTO> selectMaremaList(MaremaDTO params);
	//mdo03 : 영역 수정
	int updateMarema(MaremaDTO updateData);
	//mdo03 : 영역 등록
	int insertMarema(MaremaDTO addData);
	//mdo03 : 영역 SelectBox 조회
	List<CommonDTO> selectMaremaSelectBox(MaremaDTO params);

    // ***********************************************
    // Zone : mzonma
    // ***********************************************
	//mdo04 : 존 조회
	List<MzonmaDTO> selectMzonmaList(MzonmaDTO params);
	//mdo04 : 존 생성
	int insertMzonma(MzonmaDTO addData);
	//mdo04 : 존 수정
	int updateMzonma(MzonmaDTO updateData);
	//mdo04 : 영역 SelectBox 조회
	List<CommonDTO> selectMzonmaSelectBox(MzonmaDTO params);
	//mdo04 : Zone 관계 리스트 조회
	List<MzonmaDTO> selectWareAreaRelations(MzonmaDTO params);

    // ***********************************************
    // Location : mlocma
    // ***********************************************
	List<MlocmaDTO> selectMlocmaList(MlocmaDTO params);
	int updateMlocma(MlocmaDTO addData);
	int insertMlocma(MlocmaDTO updateData);
	List<CommonDTO> selectMlocmaSelectBox(MlocmaDTO params);
	List<MlocmaDTO> selectMlocma(MlocmaDTO params);

	//mdo05 : Location 관계 리스트 조회(존까지)
	List<MlocmaDTO> selectWareAreaZoneRelations(MlocmaDTO params);

	//mdo05 : Location 관계 리스트 조회(로케이션까지)
	List<MlocmaDTO> selectWareAreaZoneLocationRelations(MlocmaDTO params);

	//mdo05 : Location 관계 리스트 조회(wmt10)
	List<MlocmaDTO> selectWareAreaZoneLocationRelationsWmt10(MlocmaDTO params);
}