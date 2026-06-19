package com.logistics.om.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.ProcedureCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.mapper.CodeMapper;
import com.logistics.om.domain.MordmaDTO;
import com.logistics.om.domain.OcosalVO;
import com.logistics.om.domain.OeretiVO;
import com.logistics.om.domain.SalesOrderDTO;
import com.logistics.om.mapper.OMReturnMapper;
import com.logistics.om.mapper.SalesMapper;
import com.logistics.sy.domain.UserVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OMReturnService {

	private final OMReturnMapper omrMapper;
	private final SalesMapper salesMapper;
	private final CodeMapper codeMapper;
		
	// SonarLint 반영 
	private static final String OWNER_TYPE = "OWNER";
	private static final String COMPKEY_TYPE = "compkey";
	private static final String CREUSER_TYPE = "creuser";
	private static final String OERETKY_TYPE = "oeretky";

	/*  getOmr01List - OMR01 반출등록 페이지 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 안성민 
	*   입력 PARAMETA	: OeretiVO oereti - OM-반출파트 VO
	*				: UserVO userInfo - User 정보 VO
	*   출력 PARAMETA	: Map<String, Object> 형식
	*   설명			: OMR01 반출등록 페이지에 필요한 메소드 호출   
	*   			  omr01List = 재고리스트
	* 				  commonList = 반출사유코드 리스트
	* 				  mcodemList = 재고상태코드 리스트
	*                 mordmaList = 물류오더타입 리스트
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자			: 
	*	변경 사항		: 
	*/
	public Map<String, Object> getOmr01List(OeretiVO oereti, UserVO userInfo) {
		Map<String, Object> map = new HashMap<>();
		MordmaDTO mordmaDTO = new MordmaDTO();
		List<String> cootypes = new ArrayList<>();
		
		 if (OWNER_TYPE.equals(userInfo.getUsertyp())) {
			 oereti.setOwnerky(userInfo.getOwnerky());
		 }
		
		mordmaDTO.setCompkey(userInfo.getCompkey());
		mordmaDTO.setCoocate("300");
		cootypes.add("330");
		cootypes.add("340");
		mordmaDTO.setCootypes(cootypes);
		
		map.put("omr01List", omrMapper.selectOmr01List(oereti));
		map.put("commonList", omrMapper.selectCommonCode(oereti));
		map.put("mcodemList", omrMapper.selectMcodemList(oereti));
		map.put("mordmaList", omrMapper.selectMordmaType(mordmaDTO));

		return map;
	}
	
	/*  saveOmr01Data - OMR01 반출오더등록
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 안성민 
	*   입력 PARAMETA	: Map<String, Object> param
	*				: UserVO userInfo - User 정보 VO
	*   출력 PARAMETA	: Map<String, Object> 형식
	*   설명			: OMR01 반출오더의 등록   
	*   			: insertOmr01Data - 반출오더등록
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자			: 
	*	변경 사항		: 
	*/
	public Map<String, Object> saveOmr01Data(Map<String, Object> param, UserVO userInfo) {
		Map<String, Object> list = (Map<String, Object>) param.get("list");
		List<Map<String, Object>> addList = (List<Map<String, Object>>) list.get("addList");
		int insertedCount = 0; // 삽입된 데이터 개수를 카운트

		// Oeretky 채번
		String oeretky = omrMapper.selectOeretky();
		String useract = userInfo.getUseract();
		List<Map<String, Object>> oeretiDataList = new ArrayList<>();

		for (int i = 0; i < addList.size(); i++) {
			Map<String, Object> addData = addList.get(i);
			// 반출사유 폐기/그 외 구분에 따른 문서데이터 셋팅
			String rsncode = (String) addData.get("rsncode");
			if ("340A".equals(rsncode)) {
				addData.put("doccate", "300");
				addData.put("doctype", "340");
			} else {
				addData.put("doccate", "300");
				addData.put("doctype", "330");
			}
			int rtodqty = (Integer) addData.get("poitqty");

			addData.put(OERETKY_TYPE, oeretky);
			addData.put(CREUSER_TYPE, useract);
			addData.put("rtodqty", rtodqty);

			omrMapper.insertOmr01Data(addData);
			oeretiDataList.add(addData);

			insertedCount++;
		}
		
		if (insertedCount == 0 ) {
		    throw new InsertCheckedException();
		}

		Map<String, Object> call = new HashMap<>();
		if (!oeretiDataList.isEmpty()) {
			Map<String, Object> oeretiData = oeretiDataList.get(0);
			call.put(COMPKEY_TYPE, oeretiData.get(COMPKEY_TYPE));
			call.put(OERETKY_TYPE, oeretiData.get(OERETKY_TYPE));
			call.put(CREUSER_TYPE, oeretiData.get(CREUSER_TYPE));
			omrMapper.sp_om_oereti(call);
			if (!call.get("O_ORESULT").equals(0)) {
				throw new ProcedureCheckedException((String) call.get("O_OMSGKEY"));
			}
		}

		Map<String, Object> result = new HashMap<>();
		result.put("insertedCount", insertedCount);
		result.put("data", addList); // 혹은 필요한 데이터를 직접 추가합니다.
		result.put("type", "omd20");
		return result;
	}

	/*  getOmr03List - OMR03 반출등록된 상품의 현황 조회
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 안성민 
	*   입력 PARAMETA	: OeretiVO oereti - OM-반출파트 VO
	*				: UserVO userInfo - User 정보 VO
	*				: CommonDTO common - 공통파트 DTO
	*   출력 PARAMETA	: Map<String, Object> 형식
	*   설명			: OMR03 반출등록된 상품 조회 
	*   			: selectOmr03List 반출등록된 상품 조회 -(O.OEINSST = 'NEW' OR O.OEINSST = 'CMP')
	*   			: selectMcodem - 재고상태와 지시등록 상태 코드의 조회
	*   			: selectRsncdnmList - 반출사유코드의 조회
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자			: 
	*	변경 사항		: 
	*/
	public Map<String, Object> getOmr03List(OeretiVO oereti, UserVO userInfo, CommonDTO common) {
		Map<String, Object> map = new HashMap<>();
		
		 if (OWNER_TYPE.equals(userInfo.getUsertyp())) {
			 oereti.setOwnerky(userInfo.getOwnerky());
		 }
		 
		map.put("omr03List", omrMapper.selectOmr03List(oereti));
		
		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(common.getUserData());
		mcodemDTO.setComcdky("oeinsst");
		map.put("oeinsstList", codeMapper.selectMcodem(mcodemDTO));

		mcodemDTO.setComcdky("stkstat");
		map.put("stkstatList", codeMapper.selectMcodem(mcodemDTO));
		
		oereti.setRscate1("NORMAL");
		map.put("rsncdnmList", omrMapper.selectRsncdnmList(oereti));

		return map;
	}

	/*  getOmr04List - OMR04 반출등록 취소된 상품의 현황 조회
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 안성민 
	*   입력 PARAMETA	: OeretiVO oereti - OM-반출파트 VO
	*				: UserVO userInfo - User 정보 VO
	*   출력 PARAMETA	: Map<String, Object> 형식
	*   설명			: OMR04 반출등록이후 취소된 상품 조회 
	*   			: selectOmr04List - 반출취소된 상품 조회 (OEINSST = 'CAN')
	*   			: selectMcodem - 재고상태코드의 조회
	*   			: selectRsncdnmList - 반출사유코드의 조회
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자			: 
	*	변경 사항		: 
	*/
	public Map<String, Object> getOmr04List(OeretiVO oereti, UserVO userInfo) {
		Map<String, Object> map = new HashMap<>();
		 if (OWNER_TYPE.equals(userInfo.getUsertyp())) {
			 oereti.setOwnerky(userInfo.getOwnerky());
		 }
		 
		map.put("omr04List", omrMapper.selectOmr04List(oereti));
		map.put("mcodemList", omrMapper.selectMcodemList(oereti));
		
		oereti.setRscate1("CANCEL");
		map.put("recrsnmList", omrMapper.selectRsncdnmList(oereti));

		return map;
	}

	/*  getOmr05List - OMR05 반출취소를 등록할 상품의 조회
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 안성민 
	*   입력 PARAMETA	: OeretiVO oereti - OM-반출파트 VO
	*				: UserVO userInfo - User 정보 VO
	*   출력 PARAMETA	: Map<String, Object> 형식
	*   설명			: OMR05 반출취소를 등록할 상품의 조회
	*   			: selectOmr05List - 반출등록&지시등록 상품 조회 (OEINSST = 'NEW')
	*   			: selectCommonCode - 반출사유코드의 조회
	*   			: selectCancelCode - 반출취소사유코드의 조회
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자			: 
	*	변경 사항		: 
	*/
	public Map<String, Object> getOmr05List(OeretiVO oereti, UserVO userInfo) {
		Map<String, Object> map = new HashMap<>();
		
		 if (OWNER_TYPE.equals(userInfo.getUsertyp())) {
			 oereti.setOwnerky(userInfo.getOwnerky());
		 }
		 
		map.put("omr05List", omrMapper.selectOmr05List(oereti));
		map.put("commonList", omrMapper.selectCommonCode(oereti));
		map.put("cancelList", omrMapper.selectCancelCode(oereti));

		return map;
	}

	/*  saveOmr05Data - OMR05 반출취소등록
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 안성민 
	*   입력 PARAMETA	: Map<String, Object> param 형식
	*				: UserVO userInfo - User 정보 VO
	*   출력 PARAMETA	: Map<String, Object> 형식
	*   설명			: OMR05 반출등록된 상품의 취소등록
	*   			: updateOmr05Data - 반출취소등록
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자			: 
	*	변경 사항		: 
	*/
	public int saveOmr05Data(Map<String, Object> param, UserVO userInfo) {
		Map<String, Object> list = (Map<String, Object>) param.get("list");
		List<Map<String, Object>> addList = (List<Map<String, Object>>) list.get("addList");

		// Oeretky 채번
		String useract = userInfo.getUseract();
		List<Map<String, Object>> oeretiDataList = new ArrayList<>();

		int insertedCount = 0; // 삽입된 데이터 개수를 카운트

		for (Map<String, Object> addData : addList) {
			String oeretky = (String) addData.get(OERETKY_TYPE);
			String rsncdnm = (String) addData.get("rsncdnm");
			int oeretit = (Integer) addData.get("oeretit");

			addData.put("coocate", "300");
			addData.put("cootype", "330");
			addData.put(OERETKY_TYPE, oeretky);
			addData.put("rsncdnm", rsncdnm);
			addData.put("oeretit", oeretit);
			addData.put(CREUSER_TYPE, useract);

			omrMapper.updateOmr05Data(addData);
			oeretiDataList.add(addData);

			insertedCount++;
		}
		
		if (insertedCount == 0 ) {
		    throw new UpdateCheckedException();
		}

		Map<String, Object> call = new HashMap<>();
		if (!oeretiDataList.isEmpty()) {
			Map<String, Object> oeretiData = oeretiDataList.get(0);
			call.put(COMPKEY_TYPE, oeretiData.get(COMPKEY_TYPE));
			call.put(OERETKY_TYPE, oeretiData.get(OERETKY_TYPE));
			call.put(CREUSER_TYPE, oeretiData.get(CREUSER_TYPE));
			omrMapper.sp_om_oereti_cancel(call);
			if (!call.get("O_ORESULT").equals(0)) {
				throw new ProcedureCheckedException((String) call.get("O_OMSGKEY"));
			}
		}

		return insertedCount;
	}

	/*  setOmr11Save - OMR11 회수등록
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 안성민 
	*   입력 PARAMETA	: RequestDTO<SalesOrderDTO> params - 공통 List DTO
	*				: CommonDTO common - 공통파트 DTO
	*   출력 PARAMETA	: Map<String, Object> 형식
	*   설명			: OMR11 회수 등록
	*   			: insertOmr11Data - 회수오더등록
	*   수정 내역		:
	*   수정 일시		: 
	*	수정자			: 
	*	변경 사항		: 
	*/
	public int setOmr11Save(RequestDTO<SalesOrderDTO> params, CommonDTO common) {
		List<SalesOrderDTO> addList = params.getAddList();
		Map<String, Object> call = new HashMap<>();
		
		int resultCnt = 0;
		String coocate = "200";
		String cootype = "230";
		String salstat = "ORDER";

		// 오더번호 채번
		String cooutky = salesMapper.getCooutky();
		// OCOSAL and WSHPIT Insert
		for (SalesOrderDTO order : addList) {
			order.setUserData(common.getUserData());
			order.setCooutky(cooutky);
			order.setCoocate(coocate);
			order.setSalstat(salstat);
			order.setCootype(cootype);

			resultCnt = omrMapper.insertOmr11Data(order);

			if (resultCnt == 0) {
				throw new InsertCheckedException();
			}

			call.put(COMPKEY_TYPE, order.getCompkey());
			call.put("useract", order.getUserData().getUseract());
		}

		call.put("cooutky", cooutky);
		call.put("coocate", coocate);
		call.put("cootype", cootype);

		return resultCnt;
	}

	// OMR11 Item Grid 조회 + 회수 사유코드 조회 (납품취소 버전 230801 회의 후 일단 보류)
//	public List<OcosalVO> getOms04ItemList(SalesOrderDTO param) {
//		param.setDoccate("200");
//		param.setWarekey(param.getTowarky());
//		return salesMapper.selectOms04ItemList(param);
//	}

}
