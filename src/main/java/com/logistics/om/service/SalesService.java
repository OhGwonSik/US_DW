package com.logistics.om.service;



import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.ProcedureCheckedException;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.domain.MdocmaVO;
import com.logistics.md.domain.MowrmaDTO;
import com.logistics.md.domain.MptnmaDTO;
import com.logistics.md.domain.MrscmaDTO;
import com.logistics.md.domain.MskuwcDTO;
import com.logistics.md.mapper.CodeMapper;
import com.logistics.md.mapper.DocumentMapper;
import com.logistics.md.mapper.PartnerMapper;
import com.logistics.om.domain.MordmaDTO;
import com.logistics.om.domain.OcosalVO;
import com.logistics.om.domain.SalesOrderDTO;
import com.logistics.om.mapper.OMDocumentMapper;
import com.logistics.om.mapper.SalesMapper;
import com.logistics.pt.MdesmaVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalesService {

	private final PartnerMapper partnerMapper;
	private final SalesMapper salesMapper;
	private final DocumentMapper documentMapper;
	private final CodeMapper codeMapper;
	private final OMDocumentMapper omDocumentMapper;
	

    /*
	* oms02 : 납품등록
	*/
	
	/*  함수명 - getOms02Init
	*   최초 생성일시	: 2023-08-21 23:18
	*   최초 생성자	: 정민경
	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
	*   출력 PARAMETER	: SalesOrderDTO  - 납품테이블 데이터
	*   설명			: 납품등록 페이지 진입시 고객, 문서, 화주를 세팅하기 위한 메소드.
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		:
	*/
	public SalesOrderDTO getOms02Init(SalesOrderDTO salesOrder) {
		//고객 리스트 custkeyList
		salesOrder.setCustkeyList(salesMapper.selectCustomer(salesOrder));
		
		MowrmaDTO mowrmaParam = new MowrmaDTO();
		mowrmaParam.setUserData(salesOrder.getUserData());
		salesOrder.setOwnerkyList(partnerMapper.selectOwnerSelectBox(mowrmaParam));;
		
		//문서타입 리스트 doctypeList
		MordmaDTO mordmaParam = new MordmaDTO();
		mordmaParam.setUserData(salesOrder.getUserData());	
		mordmaParam.setCoocate("200");
		salesOrder.setDoctypeList(omDocumentMapper.selectMordmaList(mordmaParam));		//문서타입
		return salesOrder;
	}
	
	/*  함수명 - getOms02SkuwcSelectBox
	*   최초 생성일시	: 2023-07-07 09:02
	*   최초 생성자	: 정민경
	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
	*   출력 PARAMETER	: List<MskuwcDTO> - 상품 마스터 DTO
	*   설명			: 그리드 내 셀렉트박스에 상품 리스트를 세팅하기 위한 메소드.
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		:
	*/
	public List<MskuwcDTO> getOms02SkuwcSelectBox(SalesOrderDTO salesOrder){
		return salesMapper.selectOms02SkuwcSelectBox(salesOrder);
	}
	
	/*  함수명 - saveSalesOms02
	*   최초 생성일시	: 2023-07-07 09:02
	*   최초 생성자	: 정민경
	*   입력 PARAMETER	: RequestDTO<SalesOrderDTO> (RequestDTO - 화면단 그리드 데이터를 수신하는 DTO/OM- 납품관리파트 DTO)
	*   출력 PARAMETER	: 납품/수주 테이블에 오더 등록(insert) 된 데이터 행 수 출력
	*   설명			: 납품을 등록하기 위한 메소드.(sp_om_ocosal-납품 등록확인 후 출고테이블에 해당 납품오더를 insert하는 프로시저)
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		:
	*/
    public int saveSalesOms02(RequestDTO<SalesOrderDTO> requestPararm) {
    	List<SalesOrderDTO> addList = requestPararm.getAddList();
    	int resultCnt = 0;
    	String doccate = "200";
    	String salstat ="ORDER";
    	
		//오더번호 채번
    	String cooutky = salesMapper.getCooutky();
    	
    	//OCOSAL
		for(SalesOrderDTO order : addList) {
			order.setCooutky(cooutky);
    		order.setDoccate(doccate);       		
    		order.setSalstat(salstat);
    		resultCnt += salesMapper.insertOcosal(order);
    		
    		if(resultCnt == 0) {
    			throw new InsertCheckedException();
    		}else if(resultCnt == addList.size()) {
    			//등록된 행의 결과값과 addList의 사이즈가 같을때  procedure 호출
    			salesMapper.sp_om_ocosal(order);
        		
        		if(order.getOresult() != 0) {
        			throw new ProcedureCheckedException(order.getOmsgkey());
        		}
    		}
		}
		return resultCnt;
    }
    
    /*  함수명 - getCustomer
	*   최초 생성일시	: 2023-07-07 11:54
	*   최초 생성자	: 정민경
	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
	*   출력 PARAMETER	: List<MptnmaDTO> - 파트너 마스터 DTO
	*   설명			: 그리드 내 고객 셀렉트 박스를 구성하기 위한 메소드.
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		:
	*/
    public List<MptnmaDTO> getCustomer(SalesOrderDTO salesOrder){
    	return salesMapper.selectCustomer(salesOrder);
    }
    
    /*  함수명 - getDestkey
	*   최초 생성일시	: 2023-07-11 14:50
	*   최초 생성자	: 정민경
	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
	*   출력 PARAMETER	: List<MdesmaVO> - 도착지 마스터 DTO
	*   설명			: 그리드 내 도착지 셀렉트 박스를 구성하기 위한 메소드.
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		:
	*/
    public List<MdesmaVO> getDestkey(SalesOrderDTO salesOrder){
    	return salesMapper.selectDestkey(salesOrder);
    }
    
    /*  함수명 - getCootypeList
	*   최초 생성일시	: 2023-07-11 14:50
	*   최초 생성자	: 정민경
	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
	*   출력 PARAMETER	:SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
	*   설명			: 검색 조건 내 문서타입 체크박스를 세팅하기 위한 메소드.
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		:
	*/
    public List<MordmaDTO> getCootypeList(MordmaDTO mordmaParam) {
    	mordmaParam.setUserData(mordmaParam.getUserData());
    	return omDocumentMapper.selectMordmaList(mordmaParam);
    }
    
    /*  함수명 - getPosdate
	*   최초 생성일시	: 2023-07-10 18:07
	*   최초 생성자	: 정민경
	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
	*   출력 PARAMETER	:String 타입 영업일자
	*   설명			: 창고에 따른 영업일자를 가져오는 메소드.
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		:
	*/
    public String getPosdate(SalesOrderDTO salesOrder) {
		return salesMapper.getPosdate(salesOrder);
	}
    
    
    /* 
    * 프로그램 ID : OMS03
    * 프로그램 내용: 납품취소조회
    */
    
    /*  함수명 - getOms03CancelList
	*   최초 생성일시	: 2023-07-10 18:07
	*   최초 생성자	: 정민경
	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
	*   출력 PARAMETER	: List<OcosalVO> (OM - 수주/납품 테이블 VO)
	*   설명			: 납품의 상태가 '취소'인 데이터를 조회하는 메소드.
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		:
	*/
    public List<OcosalVO> getOms03CancelList(SalesOrderDTO salesOrder){
    	return salesMapper.selectOms03CancelList(salesOrder);
    }
    
    /* 
     * 프로그램 ID : OMS04
     * 프로그램 내용: 납품취소등록
     */
    /*  함수명 - getOms04CancelRsnList
	*   최초 생성일시	: 2023-07-19 18:02
	*   최초 생성자	: 정민경
	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
	*   출력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
	*   설명			: 그리드 내 셀렉트박스에 취소사유 데이터를 세팅하기 위한 메소드.
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		:
	*/
    public SalesOrderDTO getOms04CancelRsnList(SalesOrderDTO salesOrder) {
    	//취소사유코드 리스트
    	MrscmaDTO mrscmaParam = new MrscmaDTO();
    	mrscmaParam.setUserData(salesOrder.getUserData());
    	mrscmaParam.setRscate1("CANCEL");
    	mrscmaParam.setDoccate(salesOrder.getDoccate());
    	mrscmaParam.setDoctype(salesOrder.getDoctype());
    	salesOrder.setRsncodeList(documentMapper.selectMrscmaSelectBox(mrscmaParam)); 
    	
    	return salesOrder;
    }
    
    /*  함수명 - getOms04HeadList
	*   최초 생성일시	: 2023-07-19 18:02
	*   최초 생성자	: 정민경
	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
	*   출력 PARAMETER	: List<SalesOrderDTO> (OM- 납품관리파트 DTO)
	*   설명			: 취소 가능한 수주/납품 데이터를 조회하는 메소드.
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		:
	*/
    public List<SalesOrderDTO> getOms04HeadList(SalesOrderDTO salesOrder){
    	return salesMapper.selectOms04HeadList(salesOrder);
    }
    
    /*  함수명 - updateSalesCancel
   	*   최초 생성일시	: 2023-07-19 18:02
   	*   최초 생성자	: 정민경
   	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
   	*   출력 PARAMETER	: 취소 등록(update)된 행의 수
   	*   설명			: 납품 처리상태를 '취소'로 수정하기 위한 메소드.
   	*   수정 내역		:
   	*   수정일시		:
   	*	수정자		:
   	*	변경 사항		:
   	*/
    public int updateSalesCancel(RequestDTO<SalesOrderDTO> requestParam) {
    	List<SalesOrderDTO> updateList = requestParam.getUpdateList();
    	int resultCnt = 0;
    	
    	for(SalesOrderDTO list : updateList) {
    		salesMapper.sp_om_ocosal_cancel(list);

        	if(list.getOresult() != 0) {
        		throw new ProcedureCheckedException(list.getOmsgkey());
        	}else {
        		resultCnt += list.getOresult();
    		}
    		 
    	}
    	return resultCnt;
    }
    
    /* 
    * 프로그램 ID : OMR11
    * 프로그램 내용: 회수오더등록
    * 코드 추가 : SMA (230802)
    */
    /*  함수명 - getOmsStatusCheckBoxInit
   	*   최초 생성일시	: 2023-08-02 10:29
   	*   최초 생성자	: 안성민
   	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
   	*   출력 PARAMETER	: List<MrscmaDTO>(사유 마스터 DTO)
   	*   설명			: 그리드 내 셀렉트 박스에 회수 사유 데이터를 세팅하기 위한 메소드.
   	*   수정 내역		:
   	*   수정일시		:
   	*	수정자		:
   	*	변경 사항		:
   	*/
    public List<MrscmaDTO> getReturnRsncodeSb(SalesOrderDTO param){
    	return salesMapper.selectReturnRsncodeSb(param);
    }
    
    /*  함수명 - getOmsStatusCheckBoxInit
   	*   최초 생성일시	: 2023-08-28 18:02
   	*   최초 생성자	: 정민경
   	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
   	*   출력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
   	*   설명			: 회수현황조회 페이지의 검색조건에 '상태'체크박스를 세팅하기 위한 메소드.
   	*   수정 내역		:
   	*   수정일시		:
   	*	수정자		:
   	*	변경 사항		:
   	*/
	/* OMR13 상태 체크박스 */
	public SalesOrderDTO getOmsStatusCheckBoxInit(SalesOrderDTO salesOrder){
    	
    	McodemDTO mcodemDTO = new McodemDTO();
    	
    	mcodemDTO.setUserData(salesOrder.getUserData());		
    	mcodemDTO.setComcdky("salstat");
		salesOrder.setMcodemList(codeMapper.selectMcodem(mcodemDTO));
		
		mcodemDTO.setComcdky("inbstat");
		salesOrder.setInbstatList(codeMapper.selectMcodem(mcodemDTO));
		
		mcodemDTO.setComcdky("oubstat");
		salesOrder.setOubstatList(codeMapper.selectMcodem(mcodemDTO));
		
		mcodemDTO.setComcdky("trnstat");
		salesOrder.setTrnstatList(codeMapper.selectMcodem(mcodemDTO));
		
		return salesOrder;
    }
	
	/*  함수명 - getDoctypeAndStateInit
   	*   최초 생성일시	: 2023-08-28 18:02
   	*   최초 생성자	: 정민경
   	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
   	*   출력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
   	*   설명			: 출고,운송 오더 조회 페이지의 검색조건에 상테/문서타입 체크박스를 세팅하기 위한 공통 메소드.
   	*   수정 내역		:
   	*   수정일시		:
   	*	수정자		:
   	*	변경 사항		:
   	*/
    public SalesOrderDTO getDoctypeAndStateInit(SalesOrderDTO salesOrder) {
    	MordmaDTO mordmaDTO = new MordmaDTO();
		mordmaDTO.setUserData(salesOrder.getUserData());
		mordmaDTO.setWarekey(salesOrder.getWarekey());
		mordmaDTO.setCoocate("200");
		salesOrder.setCootypeList(omDocumentMapper.selectMordmaList(mordmaDTO));

    	McodemDTO mcodemDTO = new McodemDTO();
    	
    	mcodemDTO.setUserData(salesOrder.getUserData());		
    	mcodemDTO.setComcdky("salstat");
		salesOrder.setMcodemList(codeMapper.selectMcodem(mcodemDTO));
		
		mcodemDTO.setComcdky("inbstat");
		salesOrder.setInbstatList(codeMapper.selectMcodem(mcodemDTO));
		
		mcodemDTO.setComcdky("oubstat");
		salesOrder.setOubstatList(codeMapper.selectMcodem(mcodemDTO));
		
		mcodemDTO.setComcdky("trnstat");
		salesOrder.setTrnstatList(codeMapper.selectMcodem(mcodemDTO));
		
		return salesOrder;
    }
    
    /* 
    * 프로그램 ID : OMS06
    * 프로그램 내용: 출고오더조회
    */
    
    /*  함수명 - getOms06List
   	*   최초 생성일시	: 2023-07-26 18:13
   	*   최초 생성자	: 정민경
   	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
   	*   출력 PARAMETER	: List<SalesOrderDTO>(OM- 납품관리파트 DTO)
   	*   설명			: 수주/납품 데이터의 출고상태를 확인하기 위한 메소드.
   	*   수정 내역		:
   	*   수정일시		:
   	*	수정자		:
   	*	변경 사항		:
   	*/
    public List<SalesOrderDTO> getOms06List(SalesOrderDTO salesOrder){
    	return salesMapper.selectOms06List(salesOrder);
    }
    
    
    /* 
    * 프로그램 ID : OMS08
    * 프로그램 내용: 운송오더조회
    */
    
    /*  함수명 - getOms08List
   	*   최초 생성일시	: 2023-07-26 18:13
   	*   최초 생성자	: 정민경
   	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
   	*   출력 PARAMETER	: List<SalesOrderDTO>(OM- 납품관리파트 DTO)
   	*   설명			: 수주/납품 데이터의 운송상태를 확인하기 위한 메소드.
   	*   수정 내역		:
   	*   수정일시		:
   	*	수정자		:
   	*	변경 사항		:
   	*/
    public List<SalesOrderDTO> getOms08List(SalesOrderDTO salesOrder){
    	return salesMapper.selectOms08List(salesOrder);
    }
    
    /* 
    * 프로그램 ID : OMS10
    * 프로그램 내용: 납품현황조회
    */
    
    /*  함수명 - getOms10Init
   	*   최초 생성일시	: 2023-07-30 21:21
   	*   최초 생성자	: 한지수
   	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
   	*   출력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
   	*   설명			: 페이지 초기 진입시 검색 조건 내 '오더 상태'/'문서타입' 체크박스를 세팅하기 위한 메소드.
   	*   수정 내역		:
   	*   수정일시		:
   	*	수정자		:
   	*	변경 사항		:
   	*/
    public SalesOrderDTO getOms10Init(SalesOrderDTO salesOrderDTO) {
		
		MordmaDTO mordmaDTO = new MordmaDTO();
		mordmaDTO.setUserData(salesOrderDTO.getUserData());
		mordmaDTO.setWarekey(salesOrderDTO.getWarekey());
		mordmaDTO.setCoocate("200");
		salesOrderDTO.setCootypeList(omDocumentMapper.selectMordmaList(mordmaDTO));

		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(salesOrderDTO.getUserData());
		mcodemDTO.setComcdky("oubstat");
		salesOrderDTO.setOubstatList(codeMapper.selectMcodem(mcodemDTO));
		
		mcodemDTO.setComcdky("trnstat");
		salesOrderDTO.setTrnstatList(codeMapper.selectMcodem(mcodemDTO));
		
		List<String> comcdvls = Arrays.asList("ORDER", "ORDFM"); 
		mcodemDTO.setComcdvls(comcdvls);
		mcodemDTO.setComcdky("salstat");
		salesOrderDTO.setMcodemList(codeMapper.selectMcodem(mcodemDTO));
		
		return salesOrderDTO;
    }
    
    /*  함수명 - getOms10List
   	*   최초 생성일시	: 2023-07-30 21:21
   	*   최초 생성자	: 한지수
   	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
   	*   출력 PARAMETER	: List<SalesOrderDTO>(OM- 납품관리파트 DTO)
   	*   설명			: 등록된 납품 데이터의 현황(오더 상태 등)을 조회하기 위한 메소드.
   	*   수정 내역		:
   	*   수정일시		:
   	*	수정자		:
   	*	변경 사항		:
   	*/
    public List<SalesOrderDTO> getOms10List(SalesOrderDTO salesOrderDTO) {
    	return salesMapper.selectOms10List(salesOrderDTO);
    }
    
    
    /* 
     * 프로그램 ID : OMR11
     * 프로그램 내용: 회수오더등록 (SMA 230802 추가)
     */
    
    /*  함수명 - getReturnDoctype
   	*   최초 생성일시	: 2023-08-02 10:29
   	*   최초 생성자	: 안성민
   	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
   	*   출력 PARAMETER	: MdocmaVO(문서 마스터 VO)
   	*   설명			: 회수등록 그리드 내 문서코드를 이름으로 렌더하기 위해 가져오는 메소드.
   	*   수정 내역		:
   	*   수정일시		:
   	*	수정자		:
   	*	변경 사항		:
   	*/
    public MdocmaVO getReturnDoctype(SalesOrderDTO param) {
    	param.setDoccate("200");
    	param.setDoctype("230");
    	return salesMapper.selectReturnDoctype(param);
    }
    
    
    /* 
    * 프로그램 ID : OMR13
    * 프로그램 내용: 회수현황조회
    */
	
    /*  함수명 - getOmr13List
   	*   최초 생성일시	: 2023-08-02 16:55
   	*   최초 생성자	: 정민경
   	*   입력 PARAMETER	: SalesOrderDTO salesOrder(OM- 납품관리파트 DTO)
   	*   출력 PARAMETER	: List<OcosalVO>(OM- 납품관리 파트 VO)
   	*   설명			: 회수 등록된 데이터를 조회하기 위한 메소드.
   	*   수정 내역		:
   	*   수정일시		:
   	*	수정자		:
   	*	변경 사항		:
   	*/
	public List<OcosalVO> getOmr13List(SalesOrderDTO salesOrder){
		return salesMapper.selectOmr13List(salesOrder);
	}
    
}
