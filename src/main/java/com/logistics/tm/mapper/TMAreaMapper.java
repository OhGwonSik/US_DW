package com.logistics.tm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.md.domain.MptnmaDTO;
import com.logistics.tm.domain.TMAreaDTO;
import com.logistics.tm.domain.TvhcmaDTO;

@Mapper
public interface TMAreaMapper {
	
	/* init Data 고객사 조회 */
	List<MptnmaDTO> selectCustomerList(TMAreaDTO tmArea);
	
	/* TMM06 TAREHD 헤더 조회 */
	List<TMAreaDTO> selectTarehd(TMAreaDTO tmArea);

	/* TMM06 창고별 차량리스트 조회 */
	List<TvhcmaDTO> selectTvhcmaVehiList(TMAreaDTO tmArea);

	/* TMM06 US_DW MPOSTM view 조회 */
	List<TMAreaDTO> selectUsOpPostal(TMAreaDTO tmArea);

	/* TMM06 MPOSTM 데이터 조회 */
	List<TMAreaDTO> selectMpostm(TMAreaDTO tmArea);

	/* TMM07 OCODMA 데이터 조회 */
	List<TMAreaDTO> selectOcodma(TMAreaDTO tmArea);

	/* TMMO8 OCATMA 데이터 조회 */
	List<TMAreaDTO> selectOcatma(TMAreaDTO tmArea);

	/* TMA05 TPLNHD 데이터 조회 */
	List<TMAreaDTO> selectTma05HeadList(TMAreaDTO tmArea);

	/* TMA05 TPLNIT 데이터 조회(수정본) */
	List<TMAreaDTO> selectTma05ItemList(TMAreaDTO tmArea);

	/* TMA05 TPLNIT 모달 데이터 조회(수정본) */
	List<TMAreaDTO> selectTma05PopupList(TMAreaDTO tmArea);

	/* TMA05 권역변경 프로시저 호출 */
	void sp_tm_plan_change(TMAreaDTO tmArea);

	/* TMA05 권역변경 배차취소 프로시저 호출 */
	void sp_tm_plan_cancel(TMAreaDTO tmArea);

	/* TMA05 헤더 운행상태 조회 */
	List<TMAreaDTO> selectPlnstatList(TMAreaDTO tmArea);

	/* TMA07 MDESMA 도착지 리스트 조회 */
	List<TMAreaDTO> selectMdesma(TMAreaDTO tmArea);

	/* TMA13 TVHENT 데이터 조회 */
	List<TMAreaDTO> selectTvhent(TMAreaDTO tmArea);

	/* TMA13 TPLFIT 데이터 조회 */
	List<TMAreaDTO> selectTplfit(TMAreaDTO tmArea);

	/* TMA15 OCOSAL 권역리스트 조회 */
	List<TMAreaDTO> selectOcosalWithTarehd(TMAreaDTO tmArea);

	/* TMA15 OWNERLIST 조회 */
	List<TMAreaDTO> selectOwnerList(TMAreaDTO tmArea);

	/* TMA15 권역키 리스트 조회 */
	List<TMAreaDTO> selectTrarekyList(TMAreaDTO tmArea);

	/* TMA15 OCOSAL 조회 */
	List<TMAreaDTO> selectOcosal(TMAreaDTO tmArea);

	/* TMM06 V_MCODEM 시도 조회 */
	List<TMAreaDTO> selectCdcate1(TMAreaDTO tmArea);

	/* TMM06 V_MCODEM 시군구 조회 */
	List<TMAreaDTO> selectComcdtx(TMAreaDTO tmArea);

	/* TMM08 DOCCATE LIST 조회 */
	List<TMAreaDTO> selectMdocmaList(TMAreaDTO tmArea);

	/* TMA15 MDOCMA(DOCTYPE, DOCCATE) LIST 조회 */
	List<TMAreaDTO> selectMdocmaDoccateDoctypeList(TMAreaDTO tmArea);

	/* TMA15 배차계획 헤더 저장 */
	int insertTplnhd(TMAreaDTO tmArea);

	/* TMA15 배차계획 아이템 저장 */
	int insertTplnit(TMAreaDTO tmArea);

	/* TMA15 OCOSAL 운송상태 변경 */
	int updateOcosal(TMAreaDTO tmArea);

	/* TMA15 배차계획키 채번 */
	String selectVhplnky();

	/* TMM05 USERNAM 생성 */
	String selectUsernam(TMAreaDTO tmArea);

	/* TMM05 운송권역 헤더 저장 */
	int insertTarehd(TMAreaDTO tmArea);

	/* TMM05 운송권역 헤더 수정 */
	int updateTarehd(TMAreaDTO tmArea);

	/* TMM06 운송권역 아이템 저장 */
	int insertTareit(TMAreaDTO tmArea);

	/* TMM06 운송권역 아이템 삭제 */
	int deleteTareit(TMAreaDTO tmArea);

	/* TMA07 OCODMA 데이터 삽입 */
	int insertOcodma(TMAreaDTO tmArea);

	/* TMA07 OCODMA 데이터 수정 */
	int updateOcodma(TMAreaDTO tmArea);

	/* TMM07 OCODMA 데이터 삭제 */
	int deleteOcodma(TMAreaDTO tmArea);

	/* TMM08 OCATMA 데이터 수정 */
	int updateOcatma(TMAreaDTO tmArea);

	/* TMM08 OCATMA 데이터 삽입 */
	int insertOcatma(TMAreaDTO tmArea);

	/* TMM08 OCATMA 데이터 삭제 */
	int deleteOcatma(TMAreaDTO tmArea);

	/* TMA13 TVHENT 입차승인 수정 */
	int updateTvhent(TMAreaDTO tmArea);
}