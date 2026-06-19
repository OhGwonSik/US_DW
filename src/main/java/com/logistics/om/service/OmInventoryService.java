package com.logistics.om.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.ProcedureCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.MaremaDTO;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.mapper.CodeMapper;
import com.logistics.md.mapper.OrganizationMapper;
import com.logistics.om.domain.MordmaDTO;
import com.logistics.om.domain.OeadjiDTO;
import com.logistics.om.domain.OeadjiVO;
import com.logistics.om.domain.OmInventoryVO;
import com.logistics.om.mapper.OMDocumentMapper;
import com.logistics.om.mapper.OmInventoryMapper;
import com.logistics.sy.domain.UserVO;

@Service
public class OmInventoryService {

	@Autowired
	private OmInventoryMapper omInventoryMapper;
	
	@Autowired
	private OMDocumentMapper omDocumentMapper;
	
	@Autowired
	private OrganizationMapper organizationMapper;
	
	@Autowired
	private CodeMapper codeMapper;
	
	// 재고현황조회(omi01)
	/*  selectInventoryList - OMI01 재고현황조회 페이지 호출 (전체 탭)
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 오권식 
	*   입력 PARAMETA	: OmInventoryVO omInventory - OM-재고파트 VO
	*				: UserVO userInfo - User 정보 VO
	*   출력 PARAMETA	: Map<String, Object> 형식
	*   설명			: OMI01 재고현황조회에 필요한 메소드 호출 
	*   			: selectInventoryList = 재고리스트
	*   			: getOwnerky = 화주 정보 리스트
	* 				: selectMcodem = 재고상태코드 리스트
	* 				: selectMaremaSelectBox = AREA 코드 리스트
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자		: 
	*	변경 사항		: 
	*/
	public Map<String, Object> selectInventoryList(OmInventoryVO omInventory, UserVO userInfo){
		Map<String, Object> map = new HashMap<>();
		
		 if ("OWNER".equals(userInfo.getUsertyp())) {
		        omInventory.setOwnerky(userInfo.getOwnerky());
		 }
		 
		map.put("inventoryList", omInventoryMapper.selectInventoryList(omInventory));
		
		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(userInfo);
		mcodemDTO.setComcdky("stkstat");
		map.put("stkstatList", codeMapper.selectMcodem(mcodemDTO));
		MaremaDTO maremaDTO = new MaremaDTO();
		maremaDTO.setUserData(userInfo);
		map.put("areakeyList", organizationMapper.selectMaremaSelectBox(maremaDTO));
		
		return map;
	}
	
	// 재고현황조회(omi01)
	/*  selectGroupSkumkeyList - OMI01 재고현황조회 페이지 호출 (부품 탭)
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 오권식 
	*   입력 PARAMETA	: OmInventoryVO omInventory - OM-재고파트 VO
	*				: UserVO userInfo - User 정보 VO
	*   출력 PARAMETA	: OmInventoryVO - OM-재고파트 VO
	*   설명			: OMI01 재고현황조회에 필요한 메소드 호출 
	*   			: getOwnerky = 화주 정보 리스트
	*   			: selectGroupSkumkeyList = 부품별 조회
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자		: 
	*	변경 사항		: 
	*/
	public List<OmInventoryVO> selectGroupSkumkeyList(OmInventoryVO omInventory, UserVO userInfo){

		 if ("OWNER".equals(userInfo.getUsertyp())) {
		        omInventory.setOwnerky(userInfo.getOwnerky());
		 }
		
		return omInventoryMapper.selectGroupSkumkeyList(omInventory);
	}
	
	// 재고현황조회(omi01)
	/*  selectGroupLocationList - OMI01 재고현황조회 페이지 호출 (로케이션 탭)
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 오권식 
	*   입력 PARAMETA	: OmInventoryVO omInventory - OM-재고파트 VO
	*				: UserVO userInfo - User 정보 VO
	*   출력 PARAMETA	: OmInventoryVO - OM-재고파트 VO
	*   설명			: OMI01 재고현황조회에 필요한 메소드 호출 
	*   			: getOwnerky = 화주 정보 리스트
	*   			: selectGroupLocationList = 로케이션별 조회
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자		: 
	*	변경 사항		: 
	*/
	public List<OmInventoryVO>selectGroupLocationList(OmInventoryVO omInventory, UserVO userInfo){
		 if ("OWNER".equals(userInfo.getUsertyp())) {
		        omInventory.setOwnerky(userInfo.getOwnerky());
		 }
		 
		return omInventoryMapper.selectGroupLocationList(omInventory);
	}	
	
	// 재고조정지시(omi02)
	/*  selectWstkkyList - OMI02 재고조정지시 페이지 호출 (로케이션 탭)
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 오권식 
	*   입력 PARAMETA	: OeadjiDTO oeadji - OM-재고파트 DTO
	*   출력 PARAMETA	: OmInventoryVO - OM-재고파트 VO
	*   설명			: 재고테이블에서 조정지시할 상품의 조회
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자		: 
	*	변경 사항		: 
	*/
	public List<OmInventoryVO>selectWstkkyList(OeadjiVO oeadji){
		return omInventoryMapper.selectWstkkyList(oeadji);
	}
	
	// 재고조정지시(omi02)
	/*  selectDoctypeSelectbox - OMI02 조정지시 필요 코드 조회 
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 오권식 
	*   입력 PARAMETA	: OeadjiDTO oeadji - OM-재고파트 DTO
	*				: MordmaDTO mordma - 
	*   출력 PARAMETA	: OeadjiDTO - OM-재고파트 DTO
	*   설명			: 조정지시 등록할 때 필요한 코드 조회
	*   			: selectMordmaList = 물류 오더타입코드 조회
	*   			: selectRsnCodeList = 조정지시 사유코드 조회
	*   			: selectMcodem = 재고상태코드 조회
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자		: 
	*	변경 사항		: 
	*/
	public OeadjiDTO selectDoctypeSelectbox(OeadjiDTO oeadji, MordmaDTO mordma){
		
		OeadjiDTO initList = new OeadjiDTO();
		
		// 20230904 MORDMDA 변경위해 추가 by.SMA
		MordmaDTO mordmaDTO = new MordmaDTO();
		List<String> cootypes = new ArrayList<>();
		
		mordmaDTO.setUserData(oeadji.getUserData());
		mordmaDTO.setCoocate("300");
		cootypes.add("310");
		cootypes.add("320");
		mordmaDTO.setCootypes(cootypes);
		
		//문서타입 리스트
		//initList.setDoctypeList(omInventoryMapper.selectDoctypeSelectbox(oeadji));
		initList.setDoctypeList(omDocumentMapper.selectMordmaList(mordmaDTO));
		
		//사유코드 리스트
		oeadji.setRscate1("NORMAL");
		initList.setRsncodeList(omInventoryMapper.selectRsnCodeList(oeadji));
		
		//재고조정지시 재고상태 리스트
		McodemDTO params = new McodemDTO();
		params.setUserData(oeadji.getUserData());
		params.setComcdky("STKSTAT");
		initList.setStkstatList(codeMapper.selectMcodem(params));
		
		return initList;
	}
	
	/*  insertOmi02Save - OMI02 조정지시 등록
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 오권식 
	*   입력 PARAMETA	: RequestDTO<OeadjiDTO> requestDTO - 공통 List DTO
	*   출력 PARAMETA	: OeadjiDTO - OM-재고파트 DTO
	*   설명			: 재고조정지시의 등록
	*   			: insertOmi02Save = 조정지시 등록
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자		: 
	*	변경 사항		: 
	*/
	public int insertOmi02Save(RequestDTO<OeadjiDTO> requestDTO) {
		int resultCnt = 0;
		
		for(OeadjiDTO oeadjiDTO : requestDTO.getAddList()) {
			
			String oeadjky = omInventoryMapper.selectOeadjky();//조정지시 문서번호 채번
			
			oeadjiDTO.setOeinsst("NEW");
			oeadjiDTO.setOeadjky(oeadjky);
			resultCnt = omInventoryMapper.insertOmi02Save(oeadjiDTO);
			
			if(resultCnt == 0) {
				throw new InsertCheckedException();
			}
			
			//조정 지시 전송
			OeadjiDTO oeadjiSp = new OeadjiDTO();
			//임시방편
			oeadjiSp.setUserData(requestDTO.getAddList().get(0).getUserData());
			oeadjiSp.setOeadjky(oeadjky);
			omInventoryMapper.sp_om_oeadji(oeadjiSp);
			
			if (oeadjiSp.getOresult() != 0) {
				throw new ProcedureCheckedException((String) oeadjiSp.getOmsgkey());
			}
		}
		return resultCnt;
	}

	/*  selectOeadjiList - OMI04 조정현황 페이지 호출 
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 오권식 
	*   입력 PARAMETA	: OeadjiVO oeadji - OM-재고파트 VO
	*   출력 PARAMETA	: UserVO userInfo - User 정보 VO
	*   설명			: 조정지시등록된 상품의 조회
	*   			: getOwnerky = 화주 정보 리스트
	*  				: selectOeadjiList = 조정지시등록된 상품의 조회 
	*   			: selectMcodem = 조정지시상태 코드 조회 
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자		: 
	*	변경 사항		: 
	*/
	public Map<String, Object> selectOeadjiList(OeadjiVO oeadji, UserVO userInfo){
				Map<String, Object> map = new HashMap<>();
				
				if("OWNER".equals(userInfo.getUsertyp())) {
					oeadji.setOwnerky(userInfo.getOwnerky());
				}
				map.put("oeadjiList", omInventoryMapper.selectOeadjiList(oeadji));
				
				McodemDTO mcodemDTO = new McodemDTO();
				mcodemDTO.setUserData(userInfo);
				mcodemDTO.setComcdky("oeinsst");
				map.put("oeinsstList", codeMapper.selectMcodem(mcodemDTO));
				
				return map;
	}
	
	/*  selectOeadjiCancelList - OMI05 조정취소등록된 상품 조회 
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 오권식 
	*   입력 PARAMETA	: OeadjiVO oeadji - OM-재고파트 VO
	*   출력 PARAMETA	: UserVO userInfo - User 정보 VO
	*   설명			: 조정취소등록된 상품 조회 
	*   			: getOwnerky = 화주 정보 리스트
	*  				: selectOeadjiCancelList = 조정취소상품 조회
	*   			: selectRsncdnmList = 조정취소사유 코드의 조회 
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자		: 
	*	변경 사항		: 
	*/
	public Map<String, Object> selectOeadjiCancelList(OeadjiVO oeadji, UserVO userInfo){
		Map<String, Object> map = new HashMap<>();
		
		if("OWNER".equals(userInfo.getUsertyp())) {
			oeadji.setOwnerky(userInfo.getOwnerky());
		}
		
		map.put("oeadjiCancelList", omInventoryMapper.selectOeadjiCancelList(oeadji));
		
		
		oeadji.setRscate1("CANCEL");
		map.put("rsncdnmList", omInventoryMapper.selectRsncdnmList(oeadji));
		
		return map;
	}
	
	//조정취소등록(omi06)
	/*  selectRsnCodeOmi06List - OMI06 조정취소사유코드 조회
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 오권식 
	*   입력 PARAMETA	: OeadjiDTO oeadjiDTO - OM-재고파트 DTO
	*   출력 PARAMETA	: OeadjiDTO - OM-재고파트 DTO
	*   설명			: 조정취소사유코드 조회
	*   			: selectRsnCodeList = 조정취소사유 코드의 조회 
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자		: 
	*	변경 사항		: 
	*/
	public OeadjiDTO selectRsnCodeOmi06List(OeadjiDTO oeadjiDTO){
		oeadjiDTO.setRscate1("CANCEL");
		oeadjiDTO.setRsncodeList(omInventoryMapper.selectRsnCodeList(oeadjiDTO)); 
		return oeadjiDTO;
	}

	//조정취소등록(omi06)
	/*  selectOeadjiCancelListByNew - OMI06 조정취소를 할 리스트 조회
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 오권식 
	*   입력 PARAMETA	: OeadjiDTO oeadjiDTO - OM-재고파트 DTO
	*   출력 PARAMETA	: OeadjiDTO - OM-재고파트 DTO
	*   설명			: 조정지시가 등록된 상품 조회 (조정취소위해) 조정취소사유코드 조회
	*   			: getOwnerky - 화주리스트 조회
	*   			: selectOeadjiCancelListByNew = 조정취소할 리스트 조회
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자		: 
	*	변경 사항		: 
	*/
	public List<OeadjiVO> selectOeadjiCancelListByNew(OeadjiVO oeadji, UserVO userInfo){
		
		if("OWNER".equals(userInfo.getUsertyp())) {
			oeadji.setOwnerky(userInfo.getOwnerky());
		}
		
		return omInventoryMapper.selectOeadjiCancelListByNew(oeadji);
	}
	
	//조정취소등록(omi06)
	/*  updateOmi06Cancel - OMI06 조정취소 등록
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 오권식 
	*   입력 PARAMETA	: RequestDTO<OeadjiDTO> requestDTO - 공통 List DTO 
	*   출력 PARAMETA	: OeadjiDTO - OM-재고파트 DTO
	*   설명			: 조정지시의 등록
	*   			: updateOmi06Cancel = 조정지시등록
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자		: 
	*	변경 사항		: 
	*/
	public int updateOmi06Cancel(RequestDTO<OeadjiDTO> requestDTO) {
		int resultCnt = 0;
		List<OeadjiDTO> oeadjiDataList = new ArrayList<>();
				
		for(OeadjiDTO oeadjiDTO : requestDTO.getAddList()) {
			oeadjiDTO.setOeinsst("CAN");
			oeadjiDTO.setOeinsstTwo("NEW"); 
			resultCnt = omInventoryMapper.updateOmi06Cancel(oeadjiDTO);
			oeadjiDataList.add(oeadjiDTO);
			
			if(resultCnt == 0) {
				throw new UpdateCheckedException();
			}
		}
		
		Map<String, Object> call = new HashMap<>();
		if (!oeadjiDataList.isEmpty()) {
			
			OeadjiDTO oeadjiData = oeadjiDataList.get(0);
	        call.put("compkey", oeadjiData.getCompkey()); // 사용하는 필드명으로 수정
	        call.put("oeadjky", oeadjiData.getOeadjky()); // 사용하는 필드명으로 수정
	        call.put("creuser", oeadjiData.getCreuser()); // 사용하는 필드명으로 수정

	        omInventoryMapper.sp_om_oeadji_cancel(call);
	        if (!call.get("oresult").equals(0)) {
	            throw new ProcedureCheckedException((String) call.get("omsgkey"));
	        }
		}else {
			throw new InsertCheckedException();
		}
		return resultCnt;
	}
}
