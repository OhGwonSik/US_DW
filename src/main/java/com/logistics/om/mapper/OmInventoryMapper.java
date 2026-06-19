package com.logistics.om.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.md.domain.MdocmaDTO;
import com.logistics.md.domain.MrscmaDTO;
import com.logistics.om.domain.MordmaDTO;
import com.logistics.om.domain.OeadjiDTO;
import com.logistics.om.domain.OeadjiVO;
import com.logistics.om.domain.OmInventoryVO;

@Mapper
public interface OmInventoryMapper {
	//재고현황조회(omi01)
	public List<OmInventoryVO> selectInventoryList(OmInventoryVO omInventory);
	public List<OmInventoryVO> selectGroupSkumkeyList(OmInventoryVO omInventory);
	public List<OmInventoryVO> selectGroupLocationList(OmInventoryVO omInventory);
	
	//재고조정지시(omi02)
	//public List<MdocmaDTO> selectDoctypeSelectbox(OeadjiDTO oeadji); // select box doctype
	public List<OmInventoryVO> selectWstkkyList(OeadjiVO oeadji);
	public String selectOeadjky(); //문서번호 채번
	public void sp_om_oeadji(OeadjiDTO oeadjiDTO); //조정 지시 전송
	public List<MrscmaDTO> selectRsnCodeList(OeadjiDTO oeadjiDTO);
	public int insertOmi02Save(OeadjiDTO oeadjiDTO); // 재고조정지시
	// 20230904 MORDMA변경위해 추가 by.SMA
	public List<MordmaDTO> selectDoctypeSelectbox(OeadjiDTO oeadji);
	
	//조정현황조회(omi04)
	public List<OeadjiVO> selectOeadjiList(OeadjiVO oeadji);
	
	//조정취소조회(omi05)
	public List<OeadjiVO> selectOeadjiCancelList(OeadjiVO oeadji);
	
	// 20230821 SMA추가 -> 조정취소조회
	List<OeadjiVO> selectRsncdnmList (OeadjiVO oeadji);
	
	//조정취소등록(omi06)
	//조정취소할 리스트
	public List<OeadjiVO> selectOeadjiCancelListByNew(OeadjiVO oeadji);
	public int updateOmi06Cancel(OeadjiDTO oeadjiDTO); //조정취소
	//public void sp_om_oeadji_cancel(OeadjiDTO oeadjiDTO);//조정 취소 전송
	public void sp_om_oeadji_cancel(Map<String, Object> call);
}
