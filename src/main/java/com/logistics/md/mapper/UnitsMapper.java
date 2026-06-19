package com.logistics.md.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.common.dto.CommonDTO;
import com.logistics.md.domain.MdmSkuDTO;
import com.logistics.md.domain.MpakmaDTO;
import com.logistics.md.domain.MskustDTO;
import com.logistics.md.domain.MskuwcDTO;
import com.logistics.md.domain.MtutmaDTO;
import com.logistics.md.domain.MuommaDTO;

/* xml mapper :  mybatis header */
@Mapper
public interface UnitsMapper {
	/**
	 * MDU01
	 **/
	// MDU01 : MDM SKU 데이터 조회
	List<MdmSkuDTO> selectMDMSkuList(MdmSkuDTO param);

	public List<MskuwcDTO> selectMskuwcWithOwnerName(MskuwcDTO params);

	// MDU01 : MDM SKU 수신데이터 저장
	int insertMDMSkuListToMskuwc(MskuwcDTO addData);
	/**
	 * MDU02
	 **/
	Map<String, Object> callSpMdSkuexky(Map<String, Object> param);

	//MDU02 : 상품조회
	List<MskuwcDTO> selectMskuwcList(MskuwcDTO params);

	//MDU02 : 상품체크
	Map<String, Object> selectMskuwcCheck(Map<String, Object> paramMap);

	// MDU02 : 상품데이터 가져오기
	MskuwcDTO selectMskuwcData(Map<String, Object> paramMap);

	//MDU02 : 상품 추가 UPDATE
	int insertMskuwc(MskuwcDTO addData);

	//MDU02 : 상품 관리 UPDATE
	int updateMskuwc(MskuwcDTO updateData);

	// MDU02 Skuwc SelectBox 조회 (상품명 + 수량)
	List<MskuwcDTO> selectSkuwcBoxQty(MskuwcDTO params);

	// MDU02 : Skuwc SelectBox 조회(공장별)
	List<MskuwcDTO> selectSkuwcSelectBox(MskuwcDTO params);

	// MDU02 : OMS2 주문등록 화면용
	List<MskuwcDTO> selectSkuwcOms2SB(MskuwcDTO params);

	// MDU02 : Skuwc SelectBox 조회 OMB1
	List<MskuwcDTO> selectSkuwcBoxOmw1(MskuwcDTO params);

	// MDU02 : Skuwc customize Select Box
	List<MskuwcDTO> selectSkuwcBoxCustom(MskuwcDTO params);

	// MDU02: Skuwc customize WMR30/40
	List<MskuwcDTO> selectInboundSkuwcBox(MskuwcDTO params);

	//MDU02 : 상품의 Uom, vendkey조회(mdu10용)
	List<MskuwcDTO> selectMskuwcUomAndSvend(MskuwcDTO params);

	List<MskuwcDTO> selectMskuwcForSkustat(MskuwcDTO params);
	/**
	 * MDU03
	 **/
	List<MuommaDTO> selectMuomma(MuommaDTO params);
	int insertMuomma(MuommaDTO addData);
	int updateMuomma(MuommaDTO updateData);
	List<CommonDTO> selectUnitsSelectBox(MuommaDTO params);

	/**
	 * MDU04
	 **/
	//mdu04
	int insertMtutma(MtutmaDTO addData);
	int updateMtutma(MtutmaDTO updateData);
	List<MtutmaDTO> selectMtutmaList(MtutmaDTO params);
	List<CommonDTO> selectMtutmaSelectBox(MtutmaDTO params);

	/**
	 * MDU05
	 **/
	//mdu05
	List<MpakmaDTO> selectMpakmaList(MpakmaDTO params);
	int updateMpakma(MpakmaDTO updateData);
	int insertMpakma(MpakmaDTO addData);

	//mdu05 : Pack master & Transfer unit type SelectBox 조회
	List<CommonDTO> selectMpakmaSelectBox(MpakmaDTO params);
	List<CommonDTO> selectMpakmaPackSelectBox(MpakmaDTO params);
	List<MpakmaDTO> selectMpakmaRelations(MpakmaDTO params);
//	List<Map<String, Object>> selectSkwucBoxByWarekey(Map<String, Object> param);
	MskuwcDTO selectSkwucBoxByWarekey(MskuwcDTO params);

	/**
	 * MDU10
	 **/
	// MDU10 : skuwc set list 검색
	List<MskustDTO> selectMskuwcSetList(MskustDTO params);

	// MDU10 : skust set-item list 검색
	List<MskustDTO> selectMskustSetItemList(MskustDTO params);

	// MDU10 : skust set 저장
	int insertMskuwcSet(MskustDTO addData);

	// MDU10 : skust set-item 저장
	int insertMskustSetItem(MskustDTO addData);

	// MDU10 : skust set 갱신
	int updateMskuwcSet(MskustDTO updateData);

	// MDU10 : skust set-item skumkey 갱신
	int updateMskustSetItemSkumkey(MskustDTO updateData);

	// MDU10 : skust set-item 갱신
	int updateMskustSetItem(MskustDTO updateData);
}