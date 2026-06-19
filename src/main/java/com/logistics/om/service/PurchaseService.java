package com.logistics.om.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.domain.MdocmaDTO;
import com.logistics.md.domain.MptnmaVO;
import com.logistics.md.domain.MrscmaDTO;
import com.logistics.md.domain.MskuwcDTO;
import com.logistics.md.mapper.CodeMapper;
import com.logistics.md.mapper.DocumentMapper;
import com.logistics.md.mapper.UnitsMapper;
import com.logistics.om.domain.MordmaDTO;
import com.logistics.om.domain.OMReportDTO;
import com.logistics.om.domain.PurchaseCancelDTO;
import com.logistics.om.domain.PurchaseDTO;
import com.logistics.om.mapper.OMDocumentMapper;
import com.logistics.om.mapper.PurchaseMapper;
import com.logistics.pt.MdesmaVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseService {
	
	private final PurchaseMapper purchaseMapper;
	private final DocumentMapper documentMapper;
	private final CodeMapper codeMapper;
	private final UnitsMapper unitsMapper;
	private final OMDocumentMapper omDocumentMapper;
	
	/*  getSkuwcList - 부품(상품)마스터 조회 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수
	*   입력 PARAMETA	: MskuwcDTO mskuwcDTO - 부품(상품)마스터 DTO
	*   출력 PARAMETA	: List<MskuwcDTO> - 부품(상품)마스터 DTO 리스트
	*   설명			: 그리드 내 부품(상품) 셀렉트박스 구성을 위해 조회하는 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		:  
	*/
	public List<MskuwcDTO> getSkuwcList(MskuwcDTO mskuwcDTO) {
		return unitsMapper.selectSkuwcBoxCustom(mskuwcDTO);
	}
	
	/*  getPartnerSelectBox - 파트너 조회 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수
	*   입력 PARAMETA	: PurchaseDTO purchaseDTO - (OM- 입고관리파트 DTO)
	*   출력 PARAMETA	: List<MptnmaVO> - 파트너 마스터 VO 리스트
	*   설명			: 그리드 내 파트너(벤더, 운송사, 고객 등) 셀렉트박스 구성을 위해 조회하는 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		:  
	*/
	public List<MptnmaVO> getPartnerSelectBox(PurchaseDTO purchaseDTO) {
		return purchaseMapper.selectPartnerSelectBox(purchaseDTO);
	}
	
	/*  getDestkeySelectBox - 도착지 조회 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수
	*   입력 PARAMETA	: PurchaseDTO purchaseDTO - (OM- 입고관리파트 DTO)
	*   출력 PARAMETA	: List<MdesmaVO> - 도착지 마스터 VO 리스트
	*   설명			: 그리드 내 도착지 셀렉트박스 구성을 위해 조회하는 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		:  
	*/
	public List<MdesmaVO> getDestkeySelectBox(PurchaseDTO purchaseDTO) {
		return purchaseMapper.selectDestkeySelectBox(purchaseDTO);
	}
	
	/*  getOmp01Init - omp01 입고등록(발주) 초기 세팅 데이터 호출 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수
	*   입력 PARAMETA	: PurchaseDTO purchaseDTO - (OM-입고관리파트 DTO)
	*   출력 PARAMETA	: PurchaseDTO - (OM-입고관리파트 DTO)
	*   설명			: omp01 입고등록(발주)에 필요한 부품(상품), 벤더 및 문서타입 데이터를 수신하는 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		:  
	*/
	public PurchaseDTO getOmp01Init(PurchaseDTO purchaseDTO) {
		
		MskuwcDTO mskuwcDTO = new MskuwcDTO();
		mskuwcDTO.setUserData(purchaseDTO.getUserData());
		mskuwcDTO.setWarekey(purchaseDTO.getWarekey());
		mskuwcDTO.setOwnerky(purchaseDTO.getOwnerky());
		//sku select box
		purchaseDTO.setSkuwcList(unitsMapper.selectSkuwcBoxCustom(mskuwcDTO));
		
		//doccate, doctype
		MordmaDTO mordmaDTO = new MordmaDTO();
		mordmaDTO.setUserData(purchaseDTO.getUserData());
		mordmaDTO.setCoocate("100");
		mordmaDTO.setCootype("110");
		purchaseDTO.setCootypeList(omDocumentMapper.selectMordmaList(mordmaDTO));
		
		// vender select box
		purchaseDTO.setPtnrtyp("VENDER");
		purchaseDTO.setVenderList(purchaseMapper.selectPartnerSelectBox(purchaseDTO));
		
		return purchaseDTO;
	}
	
	/*  getOmp01Init - omp01 입고등록(발주) 데이터 저장 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수
	*   입력 PARAMETA	: RequestDTO<PurchaseDTO> requestDTO - 화면단 그리드 데이터를 수신하는 DTO
	*   출력 PARAMETA	: int - 저장된 row 수
	*   설명			: omp01 입고등록(발주) 시 데이터를 저장하는 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		:  
	*/
	public int saveOmp01Data(RequestDTO<PurchaseDTO> requestDTO) {
		int resultCount = 0;
		int itemCnt = 0;
		String copodky = purchaseMapper.selectCopodky();
		String eoasnky = purchaseMapper.selectEoasnky();
		
		for(PurchaseDTO purchaseDTO : requestDTO.getAddList()) {
			itemCnt ++;
			purchaseDTO.setCopodky(copodky);
			purchaseDTO.setCopodit(itemCnt);
			purchaseDTO.setPurstat("ORDER");
			resultCount	+= purchaseMapper.insertOcopur(purchaseDTO);
			
			purchaseDTO.setDoccate("400");
			purchaseDTO.setDoctype("410");
			purchaseDTO.setAsnstat("NEW");
			purchaseDTO.setEoasnky(eoasnky);
			purchaseDTO.setEoasnit(itemCnt);
			purchaseDTO.setAsndate(purchaseDTO.getReqdate());
			purchaseDTO.setAsntime(purchaseDTO.getReqtime());
			purchaseDTO.setWarekey(purchaseDTO.getRcvwhky());
			purchaseDTO.setAuomkey(purchaseDTO.getPuomkey());
			purchaseDTO.setAsndqty(purchaseDTO.getPoitqty());
			purchaseDTO.setAsnmemo(purchaseDTO.getReqmemo());
			resultCount += purchaseMapper.insertWasnif(purchaseDTO);
		}
		
		if(resultCount != requestDTO.getAddList().size() * 2) {
			throw new InsertCheckedException();
		}
		
		return resultCount;
	}

	/*  getOmp02Init - omp02 ASN등록 초기 세팅 데이터 호출 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수
	*   입력 PARAMETA	: PurchaseDTO purchaseDTO - OM-입고관리파트 DTO
	*   출력 PARAMETA	: PurchaseDTO - OM-입고관리파트 DTO
	*   설명			: omp02 ASN등록에 필요한 부품(상품), 벤더, 운송사, 입고차량톤수 및 문서타입 데이터를 수신하는 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		:  
	*/
	public PurchaseDTO getOmp02Init(PurchaseDTO purchaseDTO) {
		
		//sku select box
		MskuwcDTO mskuwcDTO = new MskuwcDTO();
		mskuwcDTO.setUserData(purchaseDTO.getUserData());
		mskuwcDTO.setWarekey(purchaseDTO.getWarekey());
		purchaseDTO.setSkuwcList(unitsMapper.selectSkuwcBoxCustom(mskuwcDTO));
		
		//vender select box
		purchaseDTO.setPtnrtyp("VENDER");
		purchaseDTO.setVenderList(purchaseMapper.selectPartnerSelectBox(purchaseDTO));
		
		//carrier select box
		purchaseDTO.setPtnrtyp("CARRIER");
		purchaseDTO.setCarrierList(purchaseMapper.selectPartnerSelectBox(purchaseDTO));

		//code select box
		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(purchaseDTO.getUserData());
		mcodemDTO.setComcdky("vhctncd");
		purchaseDTO.setMcodemList(codeMapper.selectMcodem(mcodemDTO));
		
		//doccate, doctype
		MdocmaDTO mdocmaDTO = new MdocmaDTO();
		mdocmaDTO.setUserData(purchaseDTO.getUserData());
		mdocmaDTO.setWarekey(purchaseDTO.getWarekey());
		mdocmaDTO.setDoccate("400");
		mdocmaDTO.setDoctype("420");
		purchaseDTO.setDoctypeList(documentMapper.selectMdocmaDoctypeList(mdocmaDTO));
		
		return purchaseDTO;
	}
	
	/*  saveOmp02Data - omp02 ASN등록 데이터 저장 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수
	*   입력 PARAMETA	: RequestDTO<PurchaseDTO> requestDTO - 화면단 그리드 데이터를 수신하는 DTO
	*   출력 PARAMETA	: OMReportDTO - OM 출력물 DTO
	*   설명			: omp02 ASN등록 시 데이터를 저장하는 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		:  
	*/
	public OMReportDTO saveOmp02Data(RequestDTO<PurchaseDTO> requestDTO) {
		int resultCount = 0;
		int itemCnt = 0;
		
		String eoasnky = purchaseMapper.selectEoasnky();				//1. ASN 번호 채번
		String copodky = purchaseMapper.selectCopodky();				//2. 발주 번호 채번
		
		for(PurchaseDTO purchaseDTO : requestDTO.getAddList()) {
			itemCnt ++;
			
			purchaseDTO.setEoasnky(eoasnky);
			purchaseDTO.setCopodky(copodky);
			purchaseDTO.setEoasnit(itemCnt);
			purchaseDTO.setCopodit(itemCnt);
			purchaseDTO.setAsnstat("NEW");
			purchaseDTO.setPuomkey(purchaseDTO.getAuomkey());
			resultCount += purchaseMapper.insertWasnif(purchaseDTO);
			
			purchaseDTO.setDoccate("100");
			purchaseDTO.setDoctype("120");
			purchaseDTO.setPurstat("VNDASN");
			purchaseDTO.setRcvwhky(purchaseDTO.getWarekey());
			purchaseDTO.setReqdate(purchaseDTO.getAsndate());
			purchaseDTO.setReqtime(purchaseDTO.getAsntime());
			purchaseDTO.setPoitqty(purchaseDTO.getAsndqty());
			purchaseDTO.setReqmemo(purchaseDTO.getAsnmemo());
			resultCount += purchaseMapper.insertOcopur(purchaseDTO);
		}
		
		if(resultCount != requestDTO.getAddList().size() * 2) {
			throw new InsertCheckedException();
		}
		
		//납입 카드 출력
		OMReportDTO omReportDTO = new OMReportDTO();
		omReportDTO.setEoasnky(eoasnky);
		omReportDTO.setType("omd10");
		
		return omReportDTO;
	} 
	
	/*  getWasnifList - omp04 ASN현황조회 그리드 조회 데이터 호출 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수
	*   입력 PARAMETA	: PurchaseDTO purchaseDTO - OM-입고관리파트 DTO
	*   출력 PARAMETA	: List<PurchaseDTO> - OM-입고관리파트 DTO
	*   설명			: omp04 ASN현황조회에 필요한 ASN(입고예정등록) 데이터를 수신하는 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		:  
	*/
	public List<PurchaseDTO> getWasnifList(PurchaseDTO purchaseDTO) {
		return purchaseMapper.selectWasnifList(purchaseDTO);
	}
	
	/*  getOmp04Init - omp04 ASN현황조회 초기 세팅 데이터 호출 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수
	*   입력 PARAMETA	: PurchaseDTO purchaseDTO - OM-입고관리파트 DTO
	*   출력 PARAMETA	: PurchaseDTO - OM-입고관리파트 DTO
	*   설명			: omp04 ASN현황조회에 필요한 문서타입, ASN상태 데이터를 수신하는 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		:  
	*/
	public PurchaseDTO getOmp04Init(PurchaseDTO purchaseDTO) {
		MdocmaDTO mdocmaDTO = new MdocmaDTO();
		mdocmaDTO.setUserData(purchaseDTO.getUserData());
		mdocmaDTO.setDoccate("400");
		mdocmaDTO.setWarekey(purchaseDTO.getWarekey());
		
		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(purchaseDTO.getUserData());
		if(purchaseDTO.getUserData().getOwnerky() != null && !(purchaseDTO.getUserData().getOwnerky().isBlank())) {
			List<String> doctypes = Arrays.asList("410", "420", "430");
			mdocmaDTO.setDoctypes(doctypes);
			purchaseDTO.setDoctypes(doctypes);
			
			mcodemDTO.setComcdvls(Arrays.asList("NEW", "CMP", "ING"));
		}
		purchaseDTO.setDoctypeList(documentMapper.selectMdocmaDoctypeList(mdocmaDTO));
		
		//code select box
		mcodemDTO.setComcdky("asnstat");
		purchaseDTO.setAsnstatList(codeMapper.selectMcodem(mcodemDTO));
		
		return purchaseDTO;
	}
	
	/*  getOmp06HeaderList - omp06 ASN취소등록 헤더 그리드 조회 데이터 호출 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수
	*   입력 PARAMETA	: PurchaseDTO purchaseDTO - OM-입고관리파트 DTO
	*   출력 PARAMETA	: List<PurchaseDTO> - OM-입고관리파트 DTO
	*   설명			: omp06 ASN취소등록에 필요한 ASN 상태가 NEW인 ASN 데이터를 group by하여 수신하는 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		:  
	*/
	public List<PurchaseDTO> getOmp06HeaderList(PurchaseDTO purchaseDTO) {
		purchaseDTO.setAsnstat("NEW");
		return purchaseMapper.selectOmp06HeaderList(purchaseDTO);
	}
	
	/*  getOmp06ItemList - omp06 ASN취소등록 아이템 그리드 조회 데이터 호출 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수
	*   입력 PARAMETA	: PurchaseDTO purchaseDTO - OM-입고관리파트 DTO
	*   출력 PARAMETA	: List<PurchaseDTO> - OM-입고관리파트 DTO
	*   설명			: omp06 ASN취소등록에 필요한 ASN 상태가 NEW인 ASN 데이터를 수신하는 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		:  
	*/
	public PurchaseCancelDTO getOmp06ItemList(PurchaseDTO purchaseDTO) {
		purchaseDTO.setAsnstat("NEW");
		PurchaseCancelDTO purchaseCancelDTO = new PurchaseCancelDTO();
		purchaseCancelDTO.setPurchaseList(purchaseMapper.selectWasnifList(purchaseDTO));
		
		//reason-code select box
		MrscmaDTO mrscmaDTO = new MrscmaDTO();
		mrscmaDTO.setUserData(purchaseDTO.getUserData());
		mrscmaDTO.setDoccate(purchaseDTO.getDoccate());
		mrscmaDTO.setDoctype(purchaseDTO.getDoctype());
		mrscmaDTO.setWarekey(purchaseDTO.getWarekey());
		mrscmaDTO.setRscate1("CANCEL");
		purchaseCancelDTO.setReasonList(documentMapper.selectMrscmaSelectBox(mrscmaDTO));
		
		return purchaseCancelDTO;
	}
	
	/*  updateStatCancel - omp06 ASN취소등록한 데이터 업데이트 메소드.
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 한지수
	*   입력 PARAMETA	: PurchaseDTO purchaseDTO - OM-입고관리파트 DTO
	*   출력 PARAMETA	: List<PurchaseDTO> - OM-입고관리파트 DTO
	*   설명			: omp06 ASN취소등록한 데이터의 상태를 'CANCEL'로 업데이트 하는 메소드.
	*   수정 내역
	*   수정일시		: 
	*	수정자		: 
	*	변경 사항		:  
	*/
	public int updateStatCancel(RequestDTO<PurchaseDTO> requestDTO) { 
		int resultCount = 0;
		
		for(PurchaseDTO purchaseDTO : requestDTO.getUpdateList()) {
			resultCount += purchaseMapper.updateAsnstatCancel(purchaseDTO);
			resultCount += purchaseMapper.updatePurstatCancel(purchaseDTO);
		}
		
		if(resultCount != requestDTO.getUpdateList().size() * 2) {
			throw new UpdateCheckedException();
		}
		
		return resultCount;
	}
}
