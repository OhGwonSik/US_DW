package com.logistics.om.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.logistics.om.domain.MordmaDTO;
import com.logistics.om.domain.OcosalVO;
import com.logistics.om.domain.OeretiVO;
import com.logistics.om.domain.SalesOrderDTO;

@Mapper
public interface OMReturnMapper {
	// OMR01 재고조회 + 반품등록
	List<OeretiVO> selectOmr01List (OeretiVO oereti);
	
	// OMR01 상품명 + 상품코드 조회
	List<OeretiVO> selectOmr01SkuList (OeretiVO oereti);
	List<OeretiVO> selectCommonCode (OeretiVO oereti);
	List<OeretiVO> selectMcodemList (OeretiVO oereti);
	List<MordmaDTO> selectMordmaType(MordmaDTO mordmaDTO);
	List<OeretiVO> selectOeretiList (OeretiVO params);
	
	// OMR01 반출오더등록
	int insertOmr01Data (Map<String,Object> param);
	String selectOeretky();		// Oeretky 채번
	String checkOeadjiByStockky (String stockky);
	
	// 반출오더전송
	void sp_om_oereti (Map<String,Object> map);
	
	// 반출취소전송
	void sp_om_oereti_cancel (Map<String,Object> map);
	
	// OMR03 반출현황조회
	List<OeretiVO> selectOmr03List (OeretiVO oereti);
	
	// 반출사유/반출취소사유 조회
	List<OeretiVO> selectRsncdnmList (OeretiVO oereti);
	
	// OMR04 반출취소조회
	List<OeretiVO> selectOmr04List (OeretiVO oereti);
	
	// OMR05 반출취소등록
	List<OeretiVO> selectOmr05List (OeretiVO oereti);
	List<OeretiVO> selectCancelCode (OeretiVO oereti);
	int updateOmr05Data (Map<String,Object> param);
	
	// mdu02 : Skuwc customize Select Box 
	List<OeretiVO> selectSkuwcBoxCustom(OeretiVO oereti);
	
	// OMR11 회수오더등록
	int insertOmr11Data(SalesOrderDTO params);	
	
	// OMR11 회수오더등록 Ver2 (납품취소버전 0801 피드백 후 보류)
	List<OcosalVO> selectOms04ItemList(SalesOrderDTO param);	
	


}
