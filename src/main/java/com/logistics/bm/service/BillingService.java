package com.logistics.bm.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.logistics.bm.domain.BillingDTO;
import com.logistics.bm.mapper.BillingMapper;
import com.logistics.common.dto.InitDataDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.ProcedureCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.domain.MdocmaDTO;
import com.logistics.md.domain.MowrmaDTO;
import com.logistics.md.domain.MptnmaDTO;
import com.logistics.md.domain.MwarmaDTO;
import com.logistics.md.mapper.CodeMapper;
import com.logistics.md.mapper.DocumentMapper;
import com.logistics.md.mapper.OrganizationMapper;
import com.logistics.md.mapper.PartnerMapper;
import com.logistics.sy.domain.UserVO;
import com.logistics.tm.domain.TMAreaDTO;
import com.logistics.tm.domain.TshrhdDTO;
import com.logistics.tm.mapper.TMAreaMapper;
import com.logistics.tm.mapper.TMMasterMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BillingService {
	private final BillingMapper billingMapper;
	private final OrganizationMapper organizationMapper;
	private final PartnerMapper partnerMapper;
	private final CodeMapper codeMapper;
	private final DocumentMapper documentMapper;
	private final TMAreaMapper tmAreaMapper;
	private final TMMasterMapper tmMasterMapper;

	//bmb01 init 데이터 조회
	public InitDataDTO getBmb01init(UserVO userInfo){
		Map<String, Object> resultMap = new HashMap<>();

		//창고리스트
		MwarmaDTO mwarma = new MwarmaDTO();
		mwarma.setUserData(userInfo);
	    resultMap.put("warekey", organizationMapper.selectWarehouseUserSelectBox(mwarma));

		// 화주 리스트
	    MowrmaDTO mowrma = new MowrmaDTO();
	    mowrma.setUserData(userInfo);
	    resultMap.put("ownerky", partnerMapper.selectOwnerSelectBox(mowrma));

	    // 협력사 리스트
	    MptnmaDTO mptnma = new MptnmaDTO();
	    mptnma.setUserData(userInfo);
	    resultMap.put("ptnrkey", partnerMapper.getMdp02SelectBoxByAll(mptnma));

	    // 대금구분 BLRPMGB
	    McodemDTO mcodem = new McodemDTO();
	    mcodem.setUserData(userInfo);
	    mcodem.setComcdky("BLRPMGB");
	    resultMap.put("blrpmgb", codeMapper.selectMcodem(mcodem));

	    // 계정 BLRCSGB 변동물류비 계정 변동(보관비, 상하역비) 고정(실비)
	    mcodem.setComcdky("BLRCSGB");
	    resultMap.put("blrcsgb", codeMapper.selectMcodem(mcodem));

	    // sub계정 BLRTKGB sub계정은 값 4개만
	    McodemDTO mcodem1 = new McodemDTO();
	    mcodem1.setUserData(userInfo);
	    mcodem1.setComcdkys(Arrays.asList("BLRTKGB","SKUGR01"));
	    resultMap.put("blrtkgb", codeMapper.selectMcodem(mcodem1));

	    MdocmaDTO mdocmaDTO = new MdocmaDTO();
	    mdocmaDTO.setUserData(userInfo);
	    resultMap.put("doccate", documentMapper.selectMdocmaAllDoccateList(mdocmaDTO)); // doccate
	    resultMap.put("doctype", documentMapper.selectMdocmaDoccateDoctypeRelations(mdocmaDTO)); // doccate + doctype

		InitDataDTO initDataDTO = new InitDataDTO();
		initDataDTO.setItem(resultMap);
		return initDataDTO;
	}

	//bmb01 그리드 조회
	public List<BillingDTO> getBmb01List(BillingDTO params){
		return billingMapper.selectBmb01List(params);
	}

	//bmb01 마감 (프로시저 호출)
	public int finishBmb01(BillingDTO params) {
		int resultCnt = 0;
		billingMapper.sp_bm_clean(params);
		if(params.getOresult()!=0) {
			throw new ProcedureCheckedException(params.getOmsgkey());
		} else {
			resultCnt++;
		}

		billingMapper.sp_bm_deadline(params);
		if(params.getOresult()!=0) {
			throw new ProcedureCheckedException(params.getOmsgkey());
		} else {
			resultCnt++;
		}

		return resultCnt;
	}

	// bmb02 초기화
	public InitDataDTO getBmb02Init(BillingDTO param) {
		InitDataDTO initDto = new InitDataDTO();
		Map<String, Object> returnData = new HashMap<>();

		// 창고 리스트
		MwarmaDTO mwarmaDto = new MwarmaDTO();
		mwarmaDto.setUserData(param.getUserData());
		returnData.put("warekey", organizationMapper.selectWarehouseSelectBox(mwarmaDto));

		// 화주 리스트
		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(param.getUserData());
		returnData.put("ownerky", partnerMapper.selectOwnerSelectBox(mowrmaDTO));

		// 대금구분업체 리스트
		MptnmaDTO mptnmaDTO = new MptnmaDTO();
		mptnmaDTO.setUserData(param.getUserData());
		returnData.put("ptnrkey", partnerMapper.getMdp02SelectBoxByAll(mptnmaDTO));

		// 대금구분 리스트
		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(param.getUserData());
		mcodemDTO.setComcdky("BLRPMGB");
		returnData.put("blrpmgb", codeMapper.selectMcodem(mcodemDTO));

		// 계정 리스트
		mcodemDTO.setComcdky("BLRCSGB");
		returnData.put("blrcsgb", codeMapper.selectMcodem(mcodemDTO));

		// sub계정 리스트
		mcodemDTO.setComcdky("BLRTKGB");
		returnData.put("blrtkgb", codeMapper.selectMcodem(mcodemDTO));

		// 단위 리스트
		mcodemDTO.setComcdky("BLRUNIT");
		returnData.put("blrunit", codeMapper.selectMcodem(mcodemDTO));

		// 문서유형, 문서타입 리스트

	    MdocmaDTO mdocmaDTO = new MdocmaDTO();
	    mdocmaDTO.setUserData(param.getUserData());
	    returnData.put("doccate", documentMapper.selectMdocmaAllDoccateList(mdocmaDTO)); // doccate
	    returnData.put("doctype", documentMapper.selectMdocmaDoccateDoctypeRelations(mdocmaDTO)); // doccate + doctype

		initDto.setItem(returnData);
		return initDto;
	}

	public List<BillingDTO> getBmb02HeadListSelect(BillingDTO param) {
		return billingMapper.selectBmb02HeadList(param);
	}

	public List<BillingDTO> getBmb02ItemList(BillingDTO params){
		return billingMapper.selectBmb02ItemList(params);
	}

	public int modifyBmb02ForVerify(BillingDTO data) {
		int insertCnt = 0;
		if(data.getEtcList() != null && !data.getEtcList().isEmpty()) {
			insertCnt = billingMapper.insertBmb02EtcCost(data);
		}

		int updateCnt = 0;
		if(data.getUpdateBlrremkList() != null && !data.getUpdateBlrremkList().isEmpty()) {
			updateCnt = billingMapper.updateBmb02Blrremk(data);
		}

//		if(updateCnt == 0) {
//			throw new UpdateCheckedException();
//		}

		billingMapper.sp_bm_verify(data);
		if(data.getOresult()!=0){
			throw new ProcedureCheckedException(data.getOmsgkey());
		}

		return insertCnt + updateCnt;
	}

	public int modifyBmb02ForCancelDeadLine(BillingDTO data) {
		billingMapper.sp_bm_cancel(data);
		if(data.getOresult()!=0){
			throw new ProcedureCheckedException(data.getOmsgkey());
		}
		return data.getOresult();
	}

	public InitDataDTO getBmb03Init(BillingDTO param) {
		InitDataDTO initDto = new InitDataDTO();
		Map<String, Object> returnData = new HashMap<>();

		// 창고 리스트
		MwarmaDTO mwarmaDto = new MwarmaDTO();
		mwarmaDto.setUserData(param.getUserData());
		mwarmaDto.setWarekey(param.getWarekey());
		returnData.put("warekeyList", organizationMapper.selectWarehouseSelectBox(mwarmaDto));

		// 화주 리스트
		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(param.getUserData());
		returnData.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDTO));

		// 대금구분업체 리스트
		MptnmaDTO mptnmaDTO = new MptnmaDTO();
		mptnmaDTO.setUserData(param.getUserData());
		returnData.put("ptnrkeyList", partnerMapper.getMdp02SelectBoxByAll(mptnmaDTO));

		// 대금구분 리스트
		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(param.getUserData());
		mcodemDTO.setComcdky("BLRPMGB");
		returnData.put("blrpmgbList", codeMapper.selectMcodem(mcodemDTO));

		// 계정 리스트
		mcodemDTO.setComcdky("BLRCSGB");
		returnData.put("blrcsgbList", codeMapper.selectMcodem(mcodemDTO));

		// sub계정 리스트
		mcodemDTO.setComcdky("BLRTKGB");
		returnData.put("blrtkgbList", codeMapper.selectMcodem(mcodemDTO));

		// 단위 리스트
		mcodemDTO.setComcdky("BLRUNIT");
		returnData.put("blrunitList", codeMapper.selectMcodem(mcodemDTO));

		// 문서유형, 문서타입 리스트
		MdocmaDTO mdocmaDTO = new MdocmaDTO();
		mdocmaDTO.setUserData(param.getUserData());
		mdocmaDTO.setWarekey(param.getWarekey());
		returnData.put("doccateList", documentMapper.selectMdocmaDoccateList(mdocmaDTO));
		returnData.put("doctypeList", documentMapper.selectMdocmaDoctypeList(mdocmaDTO));

		initDto.setItem(returnData);
		return initDto;
	}

	public List<BillingDTO> getBmb03HeadListSelect(BillingDTO param) {
		return billingMapper.selectBmb03HeadList(param);
	}

	public List<BillingDTO> getBmb03ItemListSelect(BillingDTO param) {
		return billingMapper.selectBmb03ItemList(param);
	}

	public int saveBmb03Data(RequestDTO<BillingDTO> requestDTO){
		int resultCount = 0;
		for(BillingDTO billingDTO : requestDTO.getUpdateList()){
			billingMapper.sp_bm_confirm(billingDTO);
			if(billingDTO.getOresult()!=0){
				throw new ProcedureCheckedException(billingDTO.getOmsgkey());
			}else{
				resultCount++;
			}
		}
		return resultCount;
	}

	public InitDataDTO getBmb04Init(BillingDTO param) {
		InitDataDTO initDto = new InitDataDTO();
		Map<String, Object> returnData = new HashMap<>();

		// 창고 리스트
		MwarmaDTO mwarmaDto = new MwarmaDTO();
		mwarmaDto.setUserData(param.getUserData());
		mwarmaDto.setWarekey(param.getWarekey());
		returnData.put("warekeyList", organizationMapper.selectWarehouseSelectBox(mwarmaDto));

		// 화주 리스트
		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(param.getUserData());
		returnData.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDTO));

		// 대금구분업체 리스트
		MptnmaDTO mptnmaDTO = new MptnmaDTO();
		mptnmaDTO.setUserData(param.getUserData());
		returnData.put("ptnrkeyList", partnerMapper.getMdp02SelectBoxByAll(mptnmaDTO));

		// 대금구분 리스트
		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(param.getUserData());
		mcodemDTO.setComcdky("BLRPMGB");
		returnData.put("blrpmgbList", codeMapper.selectMcodem(mcodemDTO));

		// 계정 리스트
		mcodemDTO.setComcdky("BLRCSGB");
		returnData.put("blrcsgbList", codeMapper.selectMcodem(mcodemDTO));

		// sub계정 리스트
		mcodemDTO.setComcdky("BLRTKGB");
		returnData.put("blrtkgbList", codeMapper.selectMcodem(mcodemDTO));

		// 단위 리스트
		mcodemDTO.setComcdky("BLRUNIT");
		returnData.put("blrunitList", codeMapper.selectMcodem(mcodemDTO));

		// 문서유형, 문서타입 리스트
		MdocmaDTO mdocmaDTO = new MdocmaDTO();
		mdocmaDTO.setUserData(param.getUserData());
		mdocmaDTO.setWarekey(param.getWarekey());
		returnData.put("doccateList", documentMapper.selectMdocmaDoccateList(mdocmaDTO));
		returnData.put("doctypeList", documentMapper.selectMdocmaDoctypeList(mdocmaDTO));

		initDto.setItem(returnData);
		return initDto;
	}

	public List<BillingDTO> getBmb04HeadListSelect(BillingDTO param) {
		return billingMapper.selectBmb04HeadList(param);
	}

	public List<BillingDTO> getBmb04ItemListSelect(BillingDTO param) {
		return billingMapper.selectBmb04ItemList(param);
	}

	/*  getBmb05Init - BMB05운송비정산처리 화면의 초기 데이터를 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 최강호
	*   입력 PARAMETER : BillingDTO param - 조회 조건을 담은 DTO
	*   출력 PARAMETER : InitDataDTO - BMB10 화면의 초기 데이터를 담은 DTO
	*   설명      	: BMB05 화면의 초기 데이터를 조회. 입력된 조회 조건에 맞게 데이터를 가져와
	*                 초기 데이터를 InitDataDTO 형태로 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public InitDataDTO getBmb05Init(BillingDTO param) {
		InitDataDTO initDto = new InitDataDTO();
		Map<String, Object> returnData = new HashMap<>();

		// 창고 리스트
		MwarmaDTO mwarmaDto = new MwarmaDTO();
		mwarmaDto.setUserData(param.getUserData());
		mwarmaDto.setWarekey(param.getWarekey());
		returnData.put("warekeyList", organizationMapper.selectWarehouseSelectBox(mwarmaDto));

		// 대금구분업체 리스트
		MptnmaDTO mptnmaDTO = new MptnmaDTO();
		mptnmaDTO.setUserData(param.getUserData());
		returnData.put("ptnrkeyList", partnerMapper.getMdp02SelectBoxByAll(mptnmaDTO));

		//doctype 리스트
		MdocmaDTO mdocmaDTO = new MdocmaDTO();
		mdocmaDTO.setUserData(param.getUserData());
		mdocmaDTO.setWarekey(param.getWarekey());
		mdocmaDTO.setDoccate("800");
		returnData.put("doctypeList", documentMapper.selectMdocmaDoctypeList(mdocmaDTO));

		// 대금구분 리스트
		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(param.getUserData());
		mcodemDTO.setComcdky("BTRPMGB");
		returnData.put("btrpmgbList", codeMapper.selectMcodem(mcodemDTO));
		
		//계정 리스트
		McodemDTO btrcsgbMcodemDTO = new McodemDTO();
		btrcsgbMcodemDTO.setUserData(param.getUserData());
		List<String> comcdkysList = new ArrayList<>();
		comcdkysList.add("BTRFSGB");
		comcdkysList.add("BTRVSGB");
		btrcsgbMcodemDTO.setComcdkys(comcdkysList);
		returnData.put("btrcsgbList", codeMapper.selectMcodem(btrcsgbMcodemDTO));

		//도착지 리스트
		mcodemDTO.setComcdky("MDESTKY");
		returnData.put("mdestkyList", codeMapper.selectMcodem(mcodemDTO));

		// 차량 리스트
		TMAreaDTO tmAreaDTO = new TMAreaDTO();
		tmAreaDTO.setUserData(param.getUserData());
		tmAreaDTO.setWarekey(param.getWarekey());
		returnData.put("tmList", tmAreaMapper.selectTvhcmaVehiList(tmAreaDTO));


		initDto.setItem(returnData);
		return initDto;
	}

	/*  getBmb05List - BMB05운송비정산처리 그리드 리스트 데이터 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 최강호
	*   입력 PARAMETER : BillingDTO param - 조회 조건을 담은 정산 DTO
	*   출력 PARAMETER : List<BillingDTO> - 그리드 리스트
	*   설명      	: 전달된 조회 조건을 기반으로 그리드 리스트를 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public List<BillingDTO> getBmb05List(BillingDTO param){
		return billingMapper.selectBmb05List(param);
	}

	/*  finishBmb05 - BMB05운송비정산처리 화면에서 마감 procedure를 호출
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 최강호
	*   입력 PARAMETER : BillingDTO data - 요청 데이터를 담은 DTO
	*   출력 PARAMETER : int - 처리 결과
	*   설명      	: BMB05 화면에서 마감 procedure를 호출하여 데이터를 마감 처리.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public int finishBmb05(BillingDTO param) {
		int resultCnt = 0;

		billingMapper.sp_bm_t_clean(param);
		if(param.getOresult()!=0) {
			throw new ProcedureCheckedException(param.getOmsgkey());
		} else {
			resultCnt++;
		}

		billingMapper.sp_bm_t_deadline(param);
		if(param.getOresult()!=0) {
			throw new ProcedureCheckedException(param.getOmsgkey());
		} else {
			resultCnt++;
		}

		return resultCnt;
	}

	/*  getBmb06Init - BMB06운송비검증처리 화면의 초기 데이터를 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 최강호
	*   입력 PARAMETER : BillingDTO param - 조회 조건을 담은 DTO
	*   출력 PARAMETER : InitDataDTO - BMB10 화면의 초기 데이터를 담은 DTO
	*   설명      	: BMB06 화면의 초기 데이터를 조회. 입력된 조회 조건에 맞게 데이터를 가져와
	*                 초기 데이터를 InitDataDTO 형태로 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public InitDataDTO getBmb06Init(BillingDTO param) {
		InitDataDTO initDto = new InitDataDTO();
		Map<String, Object> returnData = new HashMap<>();

		// 창고 리스트
		MwarmaDTO mwarmaDto = new MwarmaDTO();
		mwarmaDto.setUserData(param.getUserData());
		mwarmaDto.setWarekey(param.getWarekey());
		returnData.put("warekeyList", organizationMapper.selectWarehouseSelectBox(mwarmaDto));

		// 화주 리스트
		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(param.getUserData());
		returnData.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDTO));

		// 대금구분업체 리스트
		MptnmaDTO mptnmaDTO = new MptnmaDTO();
		mptnmaDTO.setUserData(param.getUserData());
		returnData.put("ptnrkeyList", partnerMapper.getMdp02SelectBoxByAll(mptnmaDTO));

		// 대금구분 리스트
		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(param.getUserData());
		mcodemDTO.setComcdky("BTRPMGB");
		returnData.put("btrpmgbList", codeMapper.selectMcodem(mcodemDTO));

		//계정 리스트
		McodemDTO btrcsgbMcodemDTO = new McodemDTO();
		btrcsgbMcodemDTO.setUserData(param.getUserData());
		List<String> comcdkysList = new ArrayList<>();
		comcdkysList.add("BTRFSGB");
		comcdkysList.add("BTRVSGB");
		btrcsgbMcodemDTO.setComcdkys(comcdkysList);
		returnData.put("btrcsgbList", codeMapper.selectMcodem(btrcsgbMcodemDTO));

		// sub계정 리스트
		mcodemDTO.setComcdky("BTRTKGB");
		returnData.put("btrtkgbList", codeMapper.selectMcodem(mcodemDTO));

		// 단위 리스트
		mcodemDTO.setComcdky("BTRUNIT");
		returnData.put("btrunitList", codeMapper.selectMcodem(mcodemDTO));

		//도착지
		mcodemDTO.setComcdky("MDESTKY");
		returnData.put("mdestkyList", codeMapper.selectMcodem(mcodemDTO));

		// 문서유형, 문서타입 리스트
		MdocmaDTO mdocmaDTO = new MdocmaDTO();
		mdocmaDTO.setUserData(param.getUserData());
		mdocmaDTO.setWarekey(param.getWarekey());
		returnData.put("doccateList", documentMapper.selectMdocmaDoccateList(mdocmaDTO));
		returnData.put("doctypeList", documentMapper.selectMdocmaDoctypeList(mdocmaDTO));

		// 차량 리스트
		TMAreaDTO tmAreaDTO = new TMAreaDTO();
		tmAreaDTO.setUserData(param.getUserData());
		tmAreaDTO.setWarekey(param.getWarekey());
		returnData.put("tmList", tmAreaMapper.selectTvhcmaVehiList(tmAreaDTO));

		initDto.setItem(returnData);
		return initDto;
	}

	/*  getBmb06HeadList - BMB06운송비검증처리 화면의 그리드 헤더 데이터를 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 최강호
	*   입력 PARAMETER : BillingDTO param - 조회 조건을 담은 DTO
	*   출력 PARAMETER : List<BillingDTO> - BMB06 화면의 그리드 헤더 데이터를 담은 DTO 리스트
	*   설명      	: BMB06 화면의 그리드 헤더 데이터를 조회. 입력된 조회 조건에 맞게 헤더 데이터를 가져와
	*                 List<BillingDTO> 형태로 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public List<BillingDTO> getBmb06HeadList(BillingDTO param){
		return billingMapper.selectBmb06HeadList(param);
	}

	/*  getBmb06ItemList - BMB06운송비검증처리 화면의 그리드 아이템 데이터를 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 최강호
	*   입력 PARAMETER : BillingDTO param - 조회 조건을 담은 DTO
	*   출력 PARAMETER : List<BillingDTO> - BMB06 화면의 초기 데이터를 담은 DTO 리스트
	*   설명      	: BMB06 화면의 그리드 아이템 데이터를 조회 입력된 조회 조건에 맞게 아이템 데이터를 가져와
	*                 List<BillingDTO> 형태로 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public List<BillingDTO> getBmb06ItemList(BillingDTO param){
		return billingMapper.selectBmb06ItemList(param);
	}


	/*  modifyBmb06ForVerify - BMB06운송비검증처리 화면에서 기타비용을 insert하고 검증완료 procedure를 호출
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 최강호
	*   입력 PARAMETER : BillingDTO data - 요청 데이터를 담은 DTO
	*   출력 PARAMETER : int - 처리 결과
	*   설명      	: BMB06 화면에서 기타비용을 insert하고, 검증완료 procedure를 호출하여 처리 결과를 반환
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public int modifyBmb06ForVerify(BillingDTO data) {
		int insertCnt = 0;
		int updateCnt = 0;

		if(data.getUpdateBtrremkList() != null && !data.getUpdateBtrremkList().isEmpty()) {
			updateCnt = billingMapper.updateBmb06Btrremk(data);
			if(updateCnt == 0) {
				throw new UpdateCheckedException();
			}
		}

		if(data.getEtcList() != null && !data.getEtcList().isEmpty()) {
			insertCnt = billingMapper.insertBmb06EtcCost(data);
			if(insertCnt == 0) {
				throw new InsertCheckedException();
			}
		}

		billingMapper.sp_bm_t_verify(data);
		if(data.getOresult()!=0){
			throw new ProcedureCheckedException(data.getOmsgkey());
		}
		return insertCnt;
	}

	/*  modifyBmb06ForCancelDeadLine - BMB06운송비검증처리 화면에서 마감을 취소하는 procedure를 호출
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 최강호
	*   입력 PARAMETER : BillingDTO data  - 요청 데이터를 담은 DTO
	*   출력 PARAMETER : int - 처리 결과
	*   설명      	: BMB06 화면에서 마감 취소 procedure를 호출하여 처리 결과를 반환
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public int modifyBmb06ForCancelDeadLine(BillingDTO data) {
		billingMapper.sp_bm_t_cancel(data);
		if(data.getOresult()!=0){
			throw new ProcedureCheckedException(data.getOmsgkey());
		}
		return data.getOresult();
	}


	/*  getBmb07Init - BMB07운송비정산확정 화면의 초기 데이터를 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 최강호
	*   입력 PARAMETER : BillingDTO param - 조회 조건을 담은 DTO
	*   출력 PARAMETER : InitDataDTO - BMB07 화면의 초기 데이터를 담은 DTO
	*   설명      	: BMB07 화면의 초기 데이터를 조회. 입력된 조회 조건에 맞게 데이터를 가져와
	*                 초기 데이터를 InitDataDTO 형태로 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public InitDataDTO getBmb07Init(BillingDTO param) {
		InitDataDTO initDto = new InitDataDTO();
		Map<String, Object> returnData = new HashMap<>();

		// 창고 리스트
		MwarmaDTO mwarmaDto = new MwarmaDTO();
		mwarmaDto.setUserData(param.getUserData());
		mwarmaDto.setWarekey(param.getWarekey());
		returnData.put("warekeyList", organizationMapper.selectWarehouseSelectBox(mwarmaDto));

		// 화주 리스트
		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(param.getUserData());
		returnData.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDTO));

		// 대금구분업체 리스트
		MptnmaDTO mptnmaDTO = new MptnmaDTO();
		mptnmaDTO.setUserData(param.getUserData());
		returnData.put("ptnrkeyList", partnerMapper.getMdp02SelectBoxByAll(mptnmaDTO));

		// 대금구분 리스트
		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(param.getUserData());
		mcodemDTO.setComcdky("BTRPMGB");
		returnData.put("btrpmgbList", codeMapper.selectMcodem(mcodemDTO));

		//계정 리스트
		McodemDTO btrcsgbMcodemDTO = new McodemDTO();
		btrcsgbMcodemDTO.setUserData(param.getUserData());
		List<String> comcdkysList = new ArrayList<>();
		comcdkysList.add("BTRFSGB");
		comcdkysList.add("BTRVSGB");
		btrcsgbMcodemDTO.setComcdkys(comcdkysList);
		returnData.put("btrcsgbList", codeMapper.selectMcodem(btrcsgbMcodemDTO));

//		// sub계정 리스트
//		mcodemDTO.setComcdky("BTRTKGB");
//		returnData.put("btrtkgbList", codeMapper.selectMcodem(mcodemDTO));

		// 단위 리스트
		mcodemDTO.setComcdky("BTRUNIT");
		returnData.put("btrunitList", codeMapper.selectMcodem(mcodemDTO));

		// 문서유형, 문서타입 리스트
		MdocmaDTO mdocmaDTO = new MdocmaDTO();
		mdocmaDTO.setUserData(param.getUserData());
		mdocmaDTO.setWarekey(param.getWarekey());
		returnData.put("doccateList", documentMapper.selectMdocmaDoccateList(mdocmaDTO));
		returnData.put("doctypeList", documentMapper.selectMdocmaDoctypeList(mdocmaDTO));

		// 차량 리스트
		TMAreaDTO tmAreaDTO = new TMAreaDTO();
		tmAreaDTO.setUserData(param.getUserData());
		tmAreaDTO.setWarekey(param.getWarekey());
		returnData.put("tmList", tmAreaMapper.selectTvhcmaVehiList(tmAreaDTO));

		initDto.setItem(returnData);
		return initDto;
	}

	/*  getBmb07HeadList - BMB07운송비정산확정 화면의 그리드 헤더 데이터를 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 최강호
	*   입력 PARAMETER : BillingDTO param - 조회 조건을 담은 DTO
	*   출력 PARAMETER : List<BillingDTO> - BMB07 화면의 그리드 헤더 데이터를 담은 DTO 리스트
	*   설명      	: BMB07 화면의 그리드 헤더 데이터를 조회. 입력된 조회 조건에 맞게 헤더 데이터를 가져와
	*                 List<BillingDTO> 형태로 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public List<BillingDTO> getBmb07HeadList(BillingDTO params){
		return billingMapper.selectBmb07HeadList(params);
	}

	/*  getBmb07ItemList - BMB07운송비정산확정 화면의 그리드 아이템 데이터를 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 최강호
	*   입력 PARAMETER : BillingDTO param - 조회 조건을 담은 DTO
	*   출력 PARAMETER : List<BillingDTO> - BMB07 화면의 초기 데이터를 담은 DTO 리스트
	*   설명      	: BMB07 화면의 그리드 아이템 데이터를 조회 입력된 조회 조건에 맞게 아이템 데이터를 가져와
	*                 List<BillingDTO> 형태로 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public List<BillingDTO> getBmb07ItemList(BillingDTO params){
		return billingMapper.selectBmb07ItemList(params);
	}

	/*  saveBmb07Data - BMB07운송비정산확정 데이터 저장
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 최강호
	*   입력 PARAMETER : RequestDTO<BillingDTO> requestDTO - 저장할 데이터를 담은 DTO
	*   출력 PARAMETER : int - 처리 결과
	*   설명      	: BMB07화면의 데이터를 저장.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public int saveBmb07Data(RequestDTO<BillingDTO> requestDTO){
		int resultCount = 0;
		for(BillingDTO billingDTO : requestDTO.getUpdateList()){
			billingMapper.sp_bm_t_confirm(billingDTO);
			if(billingDTO.getOresult()!=0){
				throw new ProcedureCheckedException(billingDTO.getOmsgkey());
			}else{
				resultCount++;
			}
		}
		return resultCount;
	}


	/*  getBmb08Init - BMB08운송정산내역조회 화면의 초기 데이터를 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 최강호
	*   입력 PARAMETER : BillingDTO param - 조회 조건을 담은 DTO
	*   출력 PARAMETER : InitDataDTO - BMB08 화면의 초기 데이터를 담은 DTO
	*   설명      	: BMB08 화면의 초기 데이터를 조회. 입력된 조회 조건에 맞게 데이터를 가져와
	*                 초기 데이터를 InitDataDTO 형태로 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public InitDataDTO getBmb08Init(BillingDTO param) {
		InitDataDTO initDto = new InitDataDTO();
		Map<String, Object> returnData = new HashMap<>();

		// 창고 리스트
		MwarmaDTO mwarmaDto = new MwarmaDTO();
		mwarmaDto.setUserData(param.getUserData());
		mwarmaDto.setWarekey(param.getWarekey());
		returnData.put("warekeyList", organizationMapper.selectWarehouseSelectBox(mwarmaDto));

		// 대금구분업체 리스트
		MptnmaDTO mptnmaDTO = new MptnmaDTO();
		mptnmaDTO.setUserData(param.getUserData());
		returnData.put("ptnrkeyList", partnerMapper.getMdp02SelectBoxByAll(mptnmaDTO));

		// 대금구분 리스트
		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(param.getUserData());
		mcodemDTO.setComcdky("BTRPMGB");
		returnData.put("btrpmgbList", codeMapper.selectMcodem(mcodemDTO));

		//계정 리스트
		McodemDTO btrcsgbMcodemDTO = new McodemDTO();
		btrcsgbMcodemDTO.setUserData(param.getUserData());
		List<String> comcdkysList = new ArrayList<>();
		comcdkysList.add("BTRFSGB");
		comcdkysList.add("BTRVSGB");
		btrcsgbMcodemDTO.setComcdkys(comcdkysList);
		returnData.put("btrcsgbList", codeMapper.selectMcodem(btrcsgbMcodemDTO));

		// sub계정 리스트
		mcodemDTO.setComcdky("BTRTKGB");
		returnData.put("btrtkgbList", codeMapper.selectMcodem(mcodemDTO));

		// 단위 리스트
		mcodemDTO.setComcdky("BTRUNIT");
		returnData.put("btrunitList", codeMapper.selectMcodem(mcodemDTO));

		//도착지 리스트
		mcodemDTO.setComcdky("MDESTKY");
		returnData.put("mdestkyList", codeMapper.selectMcodem(mcodemDTO));

		// 문서유형, 문서타입 리스트
		MdocmaDTO mdocmaDTO = new MdocmaDTO();
		mdocmaDTO.setUserData(param.getUserData());
		mdocmaDTO.setWarekey(param.getWarekey());
		returnData.put("doccateList", documentMapper.selectMdocmaDoccateList(mdocmaDTO));
		returnData.put("doctypeList", documentMapper.selectMdocmaDoctypeList(mdocmaDTO));

		// 차량 리스트
		TMAreaDTO tmAreaDTO = new TMAreaDTO();
		tmAreaDTO.setUserData(param.getUserData());
		tmAreaDTO.setWarekey(param.getWarekey());
		returnData.put("tmList", tmAreaMapper.selectTvhcmaVehiList(tmAreaDTO));

		initDto.setItem(returnData);
		return initDto;
	}

	
	/*  getBmb08HeadList - BMB08운송정산내역조회 화면의 그리드 헤더 데이터를 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 최강호
	*   입력 PARAMETER : BillingDTO param - 조회 조건을 담은 DTO
	*   출력 PARAMETER : List<BillingDTO> - BMB08 화면의 그리드 헤더 데이터를 담은 DTO 리스트
	*   설명      	: BMB08 화면의 그리드 헤더 데이터를 조회. 입력된 조회 조건에 맞게 헤더 데이터를 가져와
	*                 List<BillingDTO> 형태로 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public List<BillingDTO> getBmb08HeadList(BillingDTO params){
		return billingMapper.selectBmb08HeadList(params);
	}

	
	/*  getBmb08ItemList - BMB08운송정산내역조회 화면의 그리드 아이템 데이터를 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 최강호
	*   입력 PARAMETER : BillingDTO param - 조회 조건을 담은 DTO
	*   출력 PARAMETER : List<BillingDTO> - BMB08 화면의 초기 데이터를 담은 DTO 리스트
	*   설명      	: BMB08 화면의 그리드 아이템 데이터를 조회 입력된 조회 조건에 맞게 아이템 데이터를 가져와
	*                 List<BillingDTO> 형태로 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public List<BillingDTO> getBmb08ItemList(BillingDTO params){
		return billingMapper.selectBmb08ItemList(params);
	}
	
	/*  getBmb09Init - BMB09차량별정산내역조회 화면의 초기 데이터를 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 최강호
	*   입력 PARAMETER : BillingDTO param - 조회 조건을 담은 DTO
	*   출력 PARAMETER : InitDataDTO - BMB09 화면의 초기 데이터를 담은 DTO
	*   설명      	: BMB09 화면의 초기 데이터를 조회. 입력된 조회 조건에 맞게 데이터를 가져와
	*                 초기 데이터를 InitDataDTO 형태로 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public InitDataDTO getBmb09Init(BillingDTO param) {
		InitDataDTO initDto = new InitDataDTO();
		Map<String, Object> returnData = new HashMap<>();

		// 창고 리스트
		MwarmaDTO mwarmaDto = new MwarmaDTO();
		mwarmaDto.setUserData(param.getUserData());
		mwarmaDto.setWarekey(param.getWarekey());
		returnData.put("warekeyList", organizationMapper.selectWarehouseSelectBox(mwarmaDto));

		// 대금구분 리스트
		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(param.getUserData());
		mcodemDTO.setComcdky("BTRPMGB");
		returnData.put("btrpmgbList", codeMapper.selectMcodem(mcodemDTO));

		//계정 리스트
		McodemDTO btrcsgbMcodemDTO = new McodemDTO();
		btrcsgbMcodemDTO.setUserData(param.getUserData());
		List<String> comcdkysList = new ArrayList<>();
		comcdkysList.add("BTRFSGB");
		comcdkysList.add("BTRVSGB");
		btrcsgbMcodemDTO.setComcdkys(comcdkysList);
		returnData.put("btrcsgbList", codeMapper.selectMcodem(btrcsgbMcodemDTO));
		
		// 문서유형, 문서타입 리스트
		MdocmaDTO mdocmaDTO = new MdocmaDTO();
		mdocmaDTO.setUserData(param.getUserData());
		mdocmaDTO.setWarekey(param.getWarekey());
		returnData.put("doccateList", documentMapper.selectMdocmaDoccateList(mdocmaDTO));
		returnData.put("doctypeList", documentMapper.selectMdocmaDoctypeList(mdocmaDTO));

		// 차량 리스트
		TMAreaDTO tmAreaDTO = new TMAreaDTO();
		tmAreaDTO.setUserData(param.getUserData());
		tmAreaDTO.setWarekey(param.getWarekey());
		returnData.put("tmList", tmAreaMapper.selectTvhcmaVehiList(tmAreaDTO));
		
		//셔틀노선
		TshrhdDTO tshrhd = new TshrhdDTO();
		tshrhd.setUserData(param.getUserData());
		tshrhd.setWarekey(param.getWarekey());
		returnData.put("shtrtkyList", tmMasterMapper.selectTshrhdSelectBox(tshrhd));
		
		initDto.setItem(returnData);
		return initDto;
	}
	
	/*  getBmb09HeadList - BMB09차량별정산내역조회 화면의 그리드 헤더 데이터를 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 최강호
	*   입력 PARAMETER : BillingDTO param - 조회 조건을 담은 DTO
	*   출력 PARAMETER : List<BillingDTO> - BMB09 화면의 그리드 헤더 데이터를 담은 DTO 리스트
	*   설명      	: BMB09 화면의 그리드 헤더 데이터를 조회. 입력된 조회 조건에 맞게 헤더 데이터를 가져와
	*                 List<BillingDTO> 형태로 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public List<BillingDTO> getBmb09HeadList(BillingDTO param){
		return billingMapper.selectBmb09HeadList(param);
	}
	
	/*  getBmb09ItemList - BMB09차량별정산내역조회 화면의 그리드 아이템 데이터를 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 최강호
	*   입력 PARAMETER : BillingDTO param - 조회 조건을 담은 DTO
	*   출력 PARAMETER : List<BillingDTO> - BMB09 화면의 초기 데이터를 담은 DTO 리스트
	*   설명      	: BMB09 화면의 그리드 아이템 데이터를 조회 입력된 조회 조건에 맞게 아이템 데이터를 가져와
	*                 List<BillingDTO> 형태로 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public List<BillingDTO> getBmb09ItemList(BillingDTO param){
		return billingMapper.selectBmb09ItemList(param);
	}
	
	
	
	/*  getBmb10Init - BMB10 차량별공제비등록 초기 데이터 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : BillingDTO param - 조회 조건을 담은 DTO
	*   출력 PARAMETER : InitDataDTO - BMB10 화면의 초기 데이터를 담은 DTO
	*   설명      	: BMB10 화면의 초기 데이터를 조회. 입력된 조회 조건에 맞게 데이터를 가져와
	*                 초기 데이터를 InitDataDTO 형태로 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	//bmb10 init 데이터 조회
	public InitDataDTO getBmb10Init(BillingDTO param){
		Map<String, Object> resultMap = new HashMap<>();

		//창고리스트
		MwarmaDTO mwarma = new MwarmaDTO();
		mwarma.setUserData(param.getUserData());
	    resultMap.put("warekeyList", organizationMapper.selectWarehouseUserSelectBox(mwarma));

	    // 거래처 리스트
	    MptnmaDTO mptnma = new MptnmaDTO();
	    mptnma.setUserData(param.getUserData());
	    resultMap.put("ptnrkeyList", partnerMapper.getMdp02SelectBoxByAll(mptnma));

	    //차량리스트
		TMAreaDTO tmarea = new TMAreaDTO();
		tmarea.setUserData(param.getUserData());
		tmarea.setVehtpyn("N");
		resultMap.put("vehickyList", tmAreaMapper.selectTvhcmaVehiList(tmarea));

		InitDataDTO initDataDTO = new InitDataDTO();
		initDataDTO.setItem(resultMap);
		return initDataDTO;
	}
	
	
	
	/*  saveBmb10Data - BMB10 차량별공제비등록 데이터 저장
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : RequestDTO<BillingDTO> requestDTO - 저장할 데이터를 담은 DTO
	*   출력 PARAMETER : int - 처리 결과
	*   설명      	: BMB10화면의 데이터를 저장. 공제비 등록 프로시저 호출.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	//더미데이터 insert 프로시저도 호출하기
	public int saveBmb10Data(RequestDTO<BillingDTO> requestDTO){
		int resultCount = 0;
		String bftdtky = billingMapper.fn_bm_bftdtky(); //헤드번호 채번 bftdtky
		for(BillingDTO billingDTO : requestDTO.getAddList()){
			billingDTO.setBftdtky(bftdtky);
			
			resultCount += billingMapper.insertBmb10Cost(billingDTO);
			
			if(resultCount == 0) {
				throw new InsertCheckedException();
			}
			//프로시저 호출
			billingDTO.setBtrdate(billingDTO.getBtrdate().replace("-",""));;
			billingMapper.sp_bm_t_deduction(billingDTO);
			if(billingDTO.getOresult()!=0) {
				throw new ProcedureCheckedException(billingDTO.getOmsgkey());
			}
		}
		return resultCount;
	}

}
