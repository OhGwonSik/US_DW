package com.logistics.tm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.md.domain.MptnmaDTO;
import com.logistics.tm.domain.TMDispatchDTO;

@Mapper
public interface TMDispatchMapper {

	/* 공통 메인그리드 배차헤더 데이터 */
	List<TMDispatchDTO> selectTmDispatchList(TMDispatchDTO tmDispatch);
	
	/* 공통 품목 리스트 */
	List<TMDispatchDTO> selectTmDispatchOcatmaList(TMDispatchDTO tmDispatch);

	/* tma08: 배차아이템 데이터 */
	List<TMDispatchDTO> selectTmDispatchItem(TMDispatchDTO tmDispatch);

	/* tma11: 도착지별 배차계획아이템 데이터 */
	List<TMDispatchDTO> selectTmDispatchDestList(TMDispatchDTO tmDispatch);

	/* tma08: 배차취소 PLT 단위 OCOSAL 데이터 */
	List<TMDispatchDTO> selectTplnitWithOcosal(TMDispatchDTO tmDispatch);

	/* tma11: 배차수정 도착지별 배차아이템 데이터 */
	List<TMDispatchDTO> selectTplnitWithDestkey(TMDispatchDTO tmDispatch);

	/* tma12: 일별실적통계 init 데이터 */
	List<TMDispatchDTO> selectTmDispatchStatics(TMDispatchDTO tmDispatch);

	/* 운송사 리스트 조회 */
	List<MptnmaDTO> selectCarrierList(TMDispatchDTO tmDispatch);

	/* tma08: 배차취소, 권역납품 제외 DOCTYPE LIST */
	List<TMDispatchDTO> selectDoctypeListWithout830(TMDispatchDTO tmDispatch);

	/* tma08: 배차 취소 */
	int deleteTplnit(TMDispatchDTO tmDispatch);

	/* tma08: 배차 헤더 취소 */
	int deleteTplnhd(TMDispatchDTO tmDispatch);

	/* tma08: 배차 아이템 전체 취소 */
	int deleteTplnitAll(TMDispatchDTO tmDispatch);

	/* tma08, tma11 공통: 배차계획 헤더 데이터 수정 */
	int updateTplnhd(TMDispatchDTO tmDispatch);

	/* tma10 : 배차계획 헤더 메모 수정 */
	int updateTplnhdMemo(TMDispatchDTO tmDispatch);

	/* tma08: 배차 취소 후 OCOSAL 운송 상태 UPDATE */
	int updateOcosal(TMDispatchDTO tmDispatch);

	/* tma11: 도착지별 배차아이템 데이터 수정 */
	int updateTplnitTime(TMDispatchDTO tmDispatch);

	/* tma11: 인증 TPLFIT 데이터 수정 */
	int updateTplfit(TMDispatchDTO tmDispatch);

	/* tma11 일괄수정기능 */
	int updateTplnhdAll(TMDispatchDTO tmDispatch);

	/* tma11: 인증 TPLFIT 데이터 삽입 */
	int insertTplfit(TMDispatchDTO tmDispatch);

	/* tma11: 운송상태수정 TPLFIT UPDTCHK 데이터 */
	TMDispatchDTO selectTplfitUpdtchk(TMDispatchDTO tmDispatch);

	/* tma11: 운송상태수정 OCOSAL UPDTCHK 데이터 */
	TMDispatchDTO selectOcosal(TMDispatchDTO tmDispatch);

}