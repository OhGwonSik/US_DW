package com.logistics.tm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.md.domain.MdesmaDTO;
import com.logistics.md.domain.MptnmaDTO;
import com.logistics.md.domain.MskuwcDTO;
import com.logistics.om.domain.MordmaDTO;
import com.logistics.tm.domain.TMOrderDTO;
import com.logistics.tm.domain.TplnhdDTO;

@Mapper
public interface TMOrderMapper {
	
	
	/*
	 * 프로그램 ID: TMA01, TMA02, TMA03, TMA04, TMA06
	 *프로그램 내용: 운송오더현황, 수기배차생성, 운송상태수정, 셔틀배차, 배차수정
	 */
	//운송오더현황조회
	List<TMOrderDTO> selectTransportList(TMOrderDTO transport);
	//ocosal 데이터 (tplnit insert 하기 위해서 cooutky가 같은 데이터를 가져옴)
	List<TMOrderDTO> selectTmaOcosal(TMOrderDTO transport);
	//도착지 조회
	List<MdesmaDTO> selectTmaCommonDestList(TMOrderDTO transport);
	//차종 조회
	List<TMOrderDTO> selectTmaOcodmaList(TMOrderDTO transport);
	//ocatna list
	List<TMOrderDTO> selectTmaOcatmaList(TMOrderDTO transport);
	//ocosal 업데이트
	int updateTmaCommonOcosal(TMOrderDTO transport);
	//tplnhd delete
	int deleteTmaCommonTplnhd(TMOrderDTO transport);
	//tplnhd insert
	int insertTmaCommonTplnhd(TMOrderDTO transport);
	//tplnit insert
	int insertTmaCommonTplnitItem(TMOrderDTO transport);
	//고객 조회
	List<TMOrderDTO> selectTmaCommonCustList(TMOrderDTO transport);
	//tplnit 체크
	int selectCheckTplnit(TMOrderDTO transport);
	
	
	/*
	 * 프로그램 ID: TMA02
	 *프로그램 내용: 수기배차생성
	 */
	//상품리스트 조회
	List<MskuwcDTO> selectTmaMskuwcList(TMOrderDTO transport);
	//영업일자 채번
	String tma02GetPostdat(TMOrderDTO transport);
	//문서유형 가져오기
	List<MordmaDTO> selectTma02MordmaList(MordmaDTO mordma);
	//상품리스트 가져오기
	List<TMOrderDTO> selectTma02SkuwcList(TMOrderDTO transport);
		
	//운송오더 등록
	int insertTma02Ocosal(TMOrderDTO transport);
	
	/*
	 * 프로그램 ID: TMA03
	 *프로그램 내용: 운송오더수정
	 */
	//tplnit update
	int updateTma03Tplnit(TMOrderDTO transport);
	//tplnit select
	TMOrderDTO selectTma03Tplnit(TMOrderDTO transport);
	
	
	/*
	 * 프로그램 ID: TMA04
	 *프로그램 내용: 셔틀배차
	 */
	//셔틀리스트
	List<TMOrderDTO> selectTma04ShuttleList(TMOrderDTO transport);
	
	
	/*
	 * 프로그램 ID: TMA06
	 *프로그램 내용: 긴급오더추가
	 */
	//운송오더리스트
	List<TMOrderDTO> selectOrderList(TMOrderDTO transport);
	//배차계획 리스트
	List<TMOrderDTO> selectTma06PlanList(TMOrderDTO transport);
	//차량리스트
	List<TMOrderDTO> selectTma06CarList(TMOrderDTO transport);
	//TPLNHD updtchk 확인
	TplnhdDTO selectTma06TplnhdUpdtchk(TplnhdDTO tplnhd);
	//vhplnky 프로시저
	String selectTma06Vhplnky();
	//프로시저
	TMOrderDTO tmaSpOmOcosalWms(TMOrderDTO transport);
	
	//tplnit delete
	int deleteTma06Tplnit(TMOrderDTO transport);
}
