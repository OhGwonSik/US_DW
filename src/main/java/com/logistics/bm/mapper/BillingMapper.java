package com.logistics.bm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.bm.domain.BillingDTO;

@Mapper
public interface BillingMapper {

	//bmb01 리스트
	List<BillingDTO> selectBmb01List(BillingDTO billingDTO);

	//bmb01 마감 프로시저
	void sp_bm_clean(BillingDTO billingDTO);
	void sp_bm_deadline(BillingDTO billingDTO);

	// bmb02 headList 물류비 데이터 조회
	List<BillingDTO> selectBmb02HeadList(BillingDTO dto);

	// bmb02 마감취소 프로시저
	void sp_bm_cancel(BillingDTO data);

	// bmb02 검증완료 프로시저
	void sp_bm_verify(BillingDTO billingDTO);

	// bmb02 기타비용 입력
	int insertBmb02EtcCost(BillingDTO data);

	// bmb02 기타비용 비고 업데이트
	int updateBmb02Blrremk(BillingDTO data);

	List<BillingDTO> selectBmb03HeadList(BillingDTO dto);
	List<BillingDTO> selectBmb03ItemList(BillingDTO dto);
	void sp_bm_confirm(BillingDTO billingDTO);

	List<BillingDTO> selectBmb04HeadList(BillingDTO dto);
	List<BillingDTO> selectBmb04ItemList(BillingDTO dto);

	//bmb02 요율코드 조건에 따른 리스트 검색
	List<BillingDTO> selectBmb02ItemList(BillingDTO params);

	//bmb05 운송비 마감
	List<BillingDTO> selectBmb05List(BillingDTO param);
	void sp_bm_t_deadline(BillingDTO param);
	void sp_bm_t_clean(BillingDTO param);

	//bmb06운송비 검증
	List<BillingDTO> selectBmb06HeadList(BillingDTO param);
	List<BillingDTO> selectBmb06ItemList(BillingDTO param);
	void sp_bm_t_verify(BillingDTO param);
	void sp_bm_t_cancel(BillingDTO param);
	int insertBmb06EtcCost(BillingDTO param);
	int updateBmb06Btrremk(BillingDTO param);


	//bmb07 운송비 확정
	List<BillingDTO> selectBmb07HeadList(BillingDTO param);
	List<BillingDTO> selectBmb07ItemList(BillingDTO param);
	void sp_bm_t_confirm(BillingDTO param);

	//bmb08 운송비 정산내역
	List<BillingDTO> selectBmb08HeadList(BillingDTO param);
	List<BillingDTO> selectBmb08ItemList(BillingDTO param);
	
	//bmb09 차량별정산내역조회
	List<BillingDTO> selectBmb09HeadList(BillingDTO param);
	List<BillingDTO> selectBmb09ItemList(BillingDTO param);
	
	//bmb10 차량별공제비등록
	int insertBmb10Cost(BillingDTO param);
	String fn_bm_bftdtky(); //헤드 번호 채번
	void sp_bm_t_deduction(BillingDTO param); //공제비등록
}
