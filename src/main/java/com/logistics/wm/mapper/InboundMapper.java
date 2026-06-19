package com.logistics.wm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.common.dto.CommonDTO;
import com.logistics.md.domain.MlocmaDTO;
import com.logistics.md.domain.MskuwcDTO;
import com.logistics.wm.domain.RecvDTO;
import com.logistics.wm.domain.RecvEtcDTO;
import com.logistics.wm.domain.RecvPutDTO;
import com.logistics.wm.domain.TaskDTO;

@Mapper
public interface InboundMapper { 
	
	/*
	 * wmr20 리스트 불러오기
	 */
	List<RecvDTO> selectWmr20HeaderList(RecvDTO param);
	List<RecvDTO> selectWmr20ItemList(RecvDTO param);
	
	/*
	 * wmr22 리스트 불러오기 
	 */
	List<RecvDTO> selectWmr22HeaderList(RecvDTO param);
	List<RecvDTO> selectWmr22ItemList(RecvDTO param);
	
	/*
	 * 입하번호 채번
	 */
	String selectRcvdcky();
	
	/*
	 * wmr22 입하등록 Procedure
	 */
	void saveCtrwrcvit(RecvDTO recvDTO);
	
	/*
	 * wmr21 입하취소
	 */
	List<RecvDTO> selectWmr21List(RecvDTO param);
	void saveWmr21List(RecvDTO param);
	
	/*
	 * wmr30 ASN 번호 채번
	 */
	String selectEoasnky();
	
	/*
	 * wmr30/40: 기타 입하 등록/반품등록 Procedure 및 기타입하 발주오더
	 */
	void saveStoreReturnList(RecvEtcDTO param);
	int saveStoreReturnToOcopur(RecvEtcDTO param);
	
	/*
	 * wmr30/wmr40: 기타입하 물품 박스 수량 및 uom
	 */
	List<MskuwcDTO> selectInboundBoxQty(MskuwcDTO param);
	
	/*
	 * wmr40: 반품등록 회수오더 조회
	 */
	List<RecvEtcDTO> selectWmr40List(RecvEtcDTO param);
	int saveWmr40Tplnit(RecvEtcDTO param);
	
	/*
	 * wmr50: 팔렛타이징 List
	 */
	List<RecvDTO> selectWmr50List(RecvDTO param);
	List<RecvDTO> selectWmr50Packqty(RecvDTO param);
	String selectPakblky();
	
	/*
	 * wmr60/62: 적치지시
	 */
	List<RecvPutDTO> selectWmr60List(RecvPutDTO param);
	List<RecvPutDTO> selectWmr62List(RecvPutDTO param);
	String selectTaskoky();
	List<CommonDTO>selectWmr60LocationSelectBox(MlocmaDTO map);
	int selectTaskoit(Map<String, Object> map);
	void saveWmrTolocky(RecvPutDTO param);
	void saveWmr62Tolocky(RecvPutDTO param);
	
	/*
	 * wmr61: 적치조회 및 취소
	 */
	List<RecvPutDTO> selectWmr61List(RecvPutDTO param);
	void saveTolockyCancel(RecvPutDTO param);
	
	
	/*
	 * wmr63: 적치처리
	 */
	List<RecvPutDTO> selectWmr63List(RecvPutDTO param);
	int updateWmr63List(TaskDTO param);
	
}