package com.logistics.om.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.md.domain.MdocmaVO;
import com.logistics.md.domain.MptnmaDTO;
import com.logistics.md.domain.MrscmaDTO;
import com.logistics.md.domain.MskuwcDTO;
import com.logistics.om.domain.OcosalVO;
import com.logistics.om.domain.SalesOrderDTO;
import com.logistics.pt.MdesmaVO;

@Mapper
public interface SalesMapper {
	
	//OMS02 사용
	int insertOcosal(SalesOrderDTO salesOrder);									/* 정민경 추가 2023.07.07 수주 등록 */
	int insertWshpitOrder(SalesOrderDTO salesOrder);
	List<MptnmaDTO> selectCustomer(SalesOrderDTO salesOrder);					/* 정민경 추가 2023.07.08 고객 select */	
	List<MdesmaVO> selectDestkey(SalesOrderDTO salesOrder);	
	String getPosdate(SalesOrderDTO salesOrder);								/* 정민경 추가 2023.07.10 영업일자 */	
	String getCooutky();														/* 수주 오더번호 채번 */	
	void sp_om_ocosal(SalesOrderDTO salesOrder);
	List<MskuwcDTO> selectOms02SkuwcSelectBox(SalesOrderDTO salesOrder);
	
	//OMS03 사용
	List<OcosalVO> selectOms03CancelList(SalesOrderDTO salesOrder);					/* 정민경 추가 2023.07.12 취소된 납품 데이터 조회 */	
	
	//OMS04 사용 
	List<SalesOrderDTO> selectOms04HeadList(SalesOrderDTO salesOrder);			/* 헤드그리드 납품오더 조회 */
	
	List<MrscmaDTO> selectCancelRsncodeSb(SalesOrderDTO salesOrder);			/* 취소사유코드 */
	void sp_om_ocosal_cancel(SalesOrderDTO salesOrder); 						/* 납품등록 취소 프로시저 */
	
	//OMS06 사용
	List<SalesOrderDTO> selectOms06List(SalesOrderDTO salesOrder); 				/* 출고오더조회 */
	
	//OMS08 사용
	List<SalesOrderDTO> selectOms08List(SalesOrderDTO salesOrder); 				/* 운송오더조회 */ 
	
	//oms10
	List<SalesOrderDTO> selectOms10List(SalesOrderDTO salesOrderDTO);
	
	//OMR13 사용
	List<OcosalVO> selectOmr13List(SalesOrderDTO salesOrder); 					/* 회수현황조회 */
	
	
	// OMR11 사용 (회수코드, SMA)
	// 20230802 회수관련(SMA)
	List<MrscmaDTO> selectReturnRsncodeSb(SalesOrderDTO param);		// 회수사유코드 
	MdocmaVO selectReturnDoctype(SalesOrderDTO param);				// 회수문서타입 
	//List<MdocmaVO> selectReturnDoctype(SalesOrderDTO param);


}
