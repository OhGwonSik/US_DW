package com.logistics.md.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.md.domain.BflrmaDTO;
import com.logistics.md.domain.BftrmaDTO;
import com.logistics.md.domain.BplrmaDTO;
import com.logistics.md.domain.BvlrmaDTO;
import com.logistics.md.domain.BvtrmaDTO;
import com.logistics.md.domain.MdocmaDTO;

@Mapper
public interface BillingMasterMapper {
	//정산공통코드
	List<MdocmaDTO> selectBillingMdocma(MdocmaDTO params);

	//그리드리스트 조회
	List<BvlrmaDTO> selectMdb01List(BvlrmaDTO params);
	List<BflrmaDTO> selectMdb02List(BflrmaDTO params);
	List<BplrmaDTO> selectMdb03List(BplrmaDTO params);
	List<BvtrmaDTO> selectMdb04List(BvtrmaDTO params);
	List<BftrmaDTO> selectMdb05List(BftrmaDTO params);

	//코드 자동채번
	String selectBflcode();
	String selectBvlcode();
	String selectBplcode();
	String selectBvtcode();
	String selectBftcode();

	//그리드 생성
	int insertMdb01(HashMap<String, Object> list);
	int insertMdb02(HashMap<String, Object> list);
	int insertMdb03(HashMap<String, Object> list);
	int insertMdb04(HashMap<String, Object> list);
	int insertMdb05(HashMap<String, Object> list);

	//그리드 수정
	int updateMdb01(HashMap<String, Object> list);
	int updateMdb02(HashMap<String, Object> list);
	int updateMdb03(HashMap<String, Object> list);
	int updateMdb04(HashMap<String, Object> list);
	int updateMdb05(HashMap<String, Object> list);
}
