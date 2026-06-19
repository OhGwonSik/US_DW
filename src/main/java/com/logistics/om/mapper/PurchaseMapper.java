package com.logistics.om.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.md.domain.MptnmaVO;
import com.logistics.om.domain.PurchaseDTO;
import com.logistics.pt.MdesmaVO;

@Mapper
public interface PurchaseMapper { 
/*********************************************************************************************/
	
	//customer select box
	List<MptnmaVO> selectPartnerSelectBox(PurchaseDTO purchaseDTO);
	
	//destination select box
	List<MdesmaVO> selectDestkeySelectBox(PurchaseDTO purchaseDTO);
	
	//발주 omp01
	String selectCopodky();
	String selectEoasnky();
	int insertOcopur(PurchaseDTO purchaseDTO);
	
	//ASN 등록 omp02
	int insertWasnif(PurchaseDTO purchaseDTO);
	
	//ASN List 조회 omp04, omp05
	List<PurchaseDTO> selectWasnifList(PurchaseDTO purchaseDTO);
	
	//omp06 Header List 조회
	List<PurchaseDTO> selectOmp06HeaderList(PurchaseDTO purchaseDTO);
	
	//omp06 asn cancel
	int updateAsnstatCancel(PurchaseDTO purchaseDTO);
	int updatePurstatCancel(PurchaseDTO purchaseDTO);
	
/*********************************************************************************************/
/* BINBLUR 기존 소스 주석 처리(2023.08.28 ~ )
 * 
 * //발주 List<PurchaseDTO> selectPoHead(Map<String, Object> map);
 * List<PurchaseDTO> selectPoItem(Map<String, Object> map); int
 * insertDcSbPo(SalesOrderTempDTO dto); int insertDcPo(SalesOrderTempDTO dto);
 * int insertTcPo(SalesOrderTempDTO dto); int updateSkustat(Map<String, Object>
 * map);
 * 
 * //수주 String selectCopzzky(); void deleteSoTemp(Map<String, Object> map); void
 * insertSoTemp(Map<String, Object> map); List<SalesOrderTempDTO>
 * selectSoTempSum(Map<String, Object> map); List<SalesOrderTempDTO>
 * selectSoTempItem(Map<String, Object> map); List<Map<String, Object>>
 * selectItemChtData(Map<String, Object> map); int
 * updateOcosalList(SalesOrderTempDTO dto);
 * 
 * 
 * List<PurchaseDTO> selectOmp2HeadList(Map<String, Object> map);
 * List<PurchaseDTO> selectOmp2ItemList(PurchaseDTO dto); void
 * sp_om_ctrwasnif(Map<String, Object> map);
 * 
 * List<PurchaseDTO> selectOmp4List(Map<String, Object> map); void
 * sp_om_ctrocopurcancel(Map<String, Object> map);
 */
}
