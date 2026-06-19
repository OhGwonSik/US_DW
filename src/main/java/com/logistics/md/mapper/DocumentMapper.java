package com.logistics.md.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.common.dto.CommonDTO;
import com.logistics.md.domain.MdocmaDTO;
import com.logistics.md.domain.MrscmaDTO;

/*
*   클래스명 : DocumentMapper
*   최초생성일시	: 2023.06
*   최초생성자 : WM
*   설명 : 문서 매퍼 클래스
*/

@Mapper
public interface DocumentMapper {
	// 문서카테고리
	List<MdocmaDTO> selectMdocmaDoccateGroupList(MdocmaDTO params);
	// 문서카테고리 Select Box 용
	List<MdocmaDTO> selectMdocmaDoccateList(MdocmaDTO params);
	// 문서타입 Select Box 용
	List<MdocmaDTO> selectMdocmaDoctypeList(MdocmaDTO params);
	// 사유코드 selectBox
	List<CommonDTO> selectMrscmaSelectBox(MrscmaDTO params);
	//  MDOCMA doccate 조회
	List<MrscmaDTO> selectMdocmaAllDoccateList(MdocmaDTO params);
	//  MDOCMA relation 조회
	List<MrscmaDTO> selectMdocmaDoccateDoctypeRelations(MdocmaDTO params);
	//사유코드 selectBox custmoize 2023.09.15 최강호
	List<CommonDTO> selectWmr21MrscmaSelectBox(MrscmaDTO params);
}