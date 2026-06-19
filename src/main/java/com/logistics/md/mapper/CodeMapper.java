package com.logistics.md.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.logistics.md.domain.MrscmaDTO;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.domain.MlscalDTO;

/*
*   클래스명 : CodeMapper
*   최초생성일시	: 2023.07.18
*   최초생성자 : Park T. S.
*   설명 : 공통, 사유코드 매퍼 클래스
*/

@Mapper
public interface CodeMapper {
	//MDC06 : 사유코드 SELECT
	List<MrscmaDTO> selectMrscmaList(MrscmaDTO params);

	//MDC06 : 사유코드 INSERT
	int insertMrscma(MrscmaDTO addData);

	//MDC06 : 사유코드 UPDATE
	int updateMrscma(MrscmaDTO updateData);

	//MDC07 : 공통코드
	List<McodemDTO> selectMcodem(McodemDTO params);
	List<McodemDTO> selectMcodemList(McodemDTO params);

	//MDC07 : 공통코드 INSERT
	int insertMcodem(McodemDTO addData);

	//MDC07 : 공통코드 UPDATE
	int updateMcodem(McodemDTO updateData);

	//MDC10 : 물류달력 SELECT
	List<MlscalDTO> selectMlscalList(MlscalDTO params);

	//MDC10 : 물류달력 INSERT
//	int insertMlscal(MlscalDTO addData);

	//MDC10 : 물류달력 UPDATE
	int updateMlscal(MlscalDTO updateData);

	//WMS10 : 사유코드 selectbox용 데이터
	List<MrscmaDTO> selectReasonCodeRelations(MrscmaDTO params);
}