package com.logistics.md.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.BflrmaDTO;
import com.logistics.md.domain.BftrmaDTO;
import com.logistics.md.domain.BplrmaDTO;
import com.logistics.md.domain.BvlrmaDTO;
import com.logistics.md.domain.BvtrmaDTO;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.domain.MdocmaDTO;
import com.logistics.md.domain.MowrmaDTO;
import com.logistics.md.domain.MptnmaDTO;
import com.logistics.md.domain.MuommaDTO;
import com.logistics.md.domain.MwarmaDTO;
import com.logistics.md.mapper.BillingMasterMapper;
import com.logistics.md.mapper.CodeMapper;
import com.logistics.md.mapper.DocumentMapper;
import com.logistics.md.mapper.OrganizationMapper;
import com.logistics.md.mapper.PartnerMapper;
import com.logistics.sy.domain.UserVO;
import com.logistics.tm.domain.TMAreaDTO;
import com.logistics.tm.domain.TMMasterDTO;
import com.logistics.tm.domain.TshrhdDTO;
import com.logistics.tm.mapper.TMAreaMapper;
import com.logistics.tm.mapper.TMMasterMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BillingMasterService {
	private final BillingMasterMapper billingMasterMapper;
	private final OrganizationMapper organizationmapper;
	private final PartnerMapper partnerMapper;
	private final CodeMapper codeMapper;
	private final DocumentMapper documentmapper;
	private final TMAreaMapper tmAreaMapper;
	private final TMMasterMapper tmMasterMapper;

	//=========================================================//
	//==========================mdb01==========================//
	//=========================================================//


	/*  getMdb01Init - MDB01 변동물류비 초기 데이터 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : UserVO userInfo - 현재 사용자 정보
	*   출력 PARAMETER : Map<String, Object> - 초기 데이터 맵
	*   설명      	: MDB01 화면의 초기 데이터를 조회하여 Map으로 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public Map<String, Object> getMdb01init(UserVO userInfo){
		Map<String, Object> resultMap = new HashMap<>();
		Map<String, Object> params = new HashMap<>();

	    // 창고 리스트
		MwarmaDTO mwarma = new MwarmaDTO();
		mwarma.setUserData(userInfo);
	    List<CommonDTO> warekeyList = organizationmapper.selectWarehouseUserSelectBox(mwarma);
	    resultMap.put("warekeyList", warekeyList);


		// 화주 리스트
	    MowrmaDTO mowrma = new MowrmaDTO();
	    mowrma.setUserData(userInfo);
	    resultMap.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrma));

	    // 협력사 리스트
	    MptnmaDTO mptnma = new MptnmaDTO();
	    mptnma.setUserData(userInfo);
	    mptnma.setPtnrtyp2("CARRIER");
	    resultMap.put("ptnrkeyList", partnerMapper.getMdp02SelectBoxByAll(mptnma));

	    // 대금구분 BLRPMGB
	    McodemDTO blrpmgbParam = new McodemDTO();
	    blrpmgbParam.setUserData(userInfo);
	    blrpmgbParam.setComcdky("BLRPMGB");
	    resultMap.put("blrpmgbList", codeMapper.selectMcodem(blrpmgbParam));


	    // 계정 BLRCSGB 변동물류비 계정은 보관비, 상하역비만 나오게
	    McodemDTO blrcsgbParam = new McodemDTO();
	    blrcsgbParam.setUserData(userInfo);
	    blrcsgbParam.setComcdky("BLRCSGB");
	    blrcsgbParam.setComcdvls(Arrays.asList("STGCT", "LAUCT"));
	    resultMap.put("blrcsgbList", codeMapper.selectMcodem(blrcsgbParam));

	    // sub계정 BLRTKGB sub계정은 값 4개만 -> FZT,NRT,RFT,RMT
	    McodemDTO blrtkgbParam = new McodemDTO();
	    blrtkgbParam.setUserData(userInfo);
	    blrtkgbParam.setComcdky("BLRTKGB");
	    blrtkgbParam.setComcdvls(Arrays.asList("FZT","NRT","RFT","RMT"));
	    resultMap.put("blrtkgbList", codeMapper.selectMcodem(blrtkgbParam));

	    // 단위
	    McodemDTO unitParam = new McodemDTO();
	    unitParam.setUserData(userInfo);
	    unitParam.setComcdky("BLRUNIT");
 		resultMap.put("blrunitList", codeMapper.selectMcodem(unitParam));

		// 기준일구분 BLRDTGB
 		McodemDTO blrdtgbParam = new McodemDTO();
 		blrdtgbParam.setUserData(userInfo);
 		blrdtgbParam.setComcdky("BLRDTGB");
		resultMap.put("blrdtgbList", codeMapper.selectMcodem(blrdtgbParam));

		// 월구분From(blrmfgb),To(blrmtgb) BLRMTGB
		McodemDTO blrmfgbAndBlrmtgbParam = new McodemDTO();
		blrmfgbAndBlrmtgbParam.setUserData(userInfo);
		blrmfgbAndBlrmtgbParam.setComcdky("BLRMTGB");
		resultMap.put("blrmfgbList", codeMapper.selectMcodem(blrmfgbAndBlrmtgbParam));
		resultMap.put("blrmtgbList", codeMapper.selectMcodem(blrmfgbAndBlrmtgbParam));

		// 기준일From(blrcfdt),To(blrctdt) BLRCTDT
		McodemDTO blrcfdtAndBlrctdtParam = new McodemDTO();
		blrcfdtAndBlrctdtParam.setUserData(userInfo);
		blrcfdtAndBlrctdtParam.setComcdky("BLRCTDT");
		resultMap.put("blrcfdtList", codeMapper.selectMcodem(blrcfdtAndBlrctdtParam));
		resultMap.put("blrctdtList", codeMapper.selectMcodem(blrcfdtAndBlrctdtParam));

		// 문서타입 doctype
		MdocmaDTO doctypeParam = new MdocmaDTO();
		String doctat5 = (String) params.get("doctat5");
		if (doctat5 != null) {
			List<String> doctat5s = Arrays.asList(doctat5.split(","));
			params.put("doctat5s", doctat5s);
		}
		doctypeParam.setUserData(userInfo);
		doctypeParam.setDoctat5s(Arrays.asList("STGCT", "LAUCT"));
		resultMap.put("doctypeList", documentmapper.selectMdocmaDoctypeList(doctypeParam));

	    return resultMap;
		}


	/*  getMdb01List - MDB01 변동물류비 그리드 데이터 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : BvlrmaDTO params - 조회 조건을 담은 변동물류비DTO
	*   출력 PARAMETER : List<BvlrmaDTO> - 그리드 데이터를 담은 DTO 리스트
	*   설명      	: MDB01 화면의 그리드 데이터를 조회하는 메서드. 화면의 조건에 따라 데이터를 조회하여 리스트로 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public List<BvlrmaDTO> getMdb01List(BvlrmaDTO params){
		return billingMasterMapper.selectMdb01List(params);
	}

	/*  saveMdb01List - MDB01 변동물류비 그리드 데이터 저장
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : RequestDTO<BvlrmaDTO> params - 저장할 데이터를 담은 DTO
	*   출력 PARAMETER : int - 저장된 데이터의 건수
	*   설명      	: MDB01 화면의 그리드 데이터를 저장하는 메서드. 신규 추가 및 수정된 데이터를 데이터베이스에 반영하고,
	*   		  	  결과로 저장된 데이터의 건수를 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public int saveMdb01List(RequestDTO<BvlrmaDTO> params) {
	    int insertCnt = 0;
	    if (!params.getAddList().isEmpty()) {
	        params.getAddList().forEach(idx -> {
	            idx.setBvlcode(billingMasterMapper.selectBvlcode());
	            UserVO userData = idx.getUserData();
	        });

	        HashMap<String, Object> insertMap = new HashMap<>();
	        insertMap.put("list", params.getAddList());
	        int insertResult = billingMasterMapper.insertMdb01(insertMap);
	        if (insertResult == 0) {
	            throw new InsertCheckedException();
	        }
	        insertCnt += insertResult;
	    }

	    int updateCnt = 0;
	    if (!params.getUpdateList().isEmpty() && !params.getOldList().isEmpty()) {
	        params.getUpdateList().forEach(idx -> {
	        	UserVO userData = idx.getUserData();
	        });

	        HashMap<String, Object> updateMap = new HashMap<>();
	        updateMap.put("list", params.getUpdateList());
	        int updateResult = billingMasterMapper.updateMdb01(updateMap);
	        if (updateResult == 0) {
	            throw new UpdateCheckedException();
	        }
	        updateCnt += updateResult;
	    }

	    return insertCnt + updateCnt;
	}


    //=========================================================//
	//==========================mdb02==========================//
	//=========================================================//

	/*  getMdb02init - MDB02 고정물류비 초기 데이터 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : UserVO userInfo - 현재 사용자 정보
	*   출력 PARAMETER : Map<String, Object> - 초기 데이터 맵
	*   설명      	: MDB02 화면의 초기 데이터를 조회하여 Map으로 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public Map<String, Object> getMdb02init(UserVO userInfo){
		Map<String, Object> resultMap = new HashMap<>();
		MwarmaDTO mwarma = new MwarmaDTO();
		MowrmaDTO mowrma = new MowrmaDTO();
		MptnmaDTO mptnma = new MptnmaDTO();
		McodemDTO mcodem = new McodemDTO();


	    // 창고 리스트
		mwarma.setUserData(userInfo);
	    resultMap.put("warekeyList", organizationmapper.selectWarehouseUserSelectBox(mwarma));

		// 화주 리스트
	    mowrma.setUserData(userInfo);
	    resultMap.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrma));


	    // 협력사 리스트
	    mptnma.setUserData(userInfo);
	    mptnma.setPtnrtyp2("CARRIER");
	    resultMap.put("ptnrkeyList", partnerMapper.getMdp02SelectBoxByAll(mptnma));

	    // 대금구분 BLRPMGB
	    mcodem.setUserData(userInfo);
	    mcodem.setComcdky("BLRPMGB");
	    resultMap.put("blrpmgbList", codeMapper.selectMcodem(mcodem));


	    // 계정 BLRCSGB 고정물류비 계정은 실비만 나오게
	    mcodem.setComcdky("BLRCSGB");
	    mcodem.setComcdvl("ACTCT");
	    resultMap.put("blrcsgbList", codeMapper.selectMcodem(mcodem));


	    // sub계정 BLRTKGB sub계정은 CDCATE1 CDCATE2 컬럼(부모)을 연결할 것
	    McodemDTO blrtkgbParam = new McodemDTO();
	    blrtkgbParam.setUserData(userInfo);
	    blrtkgbParam.setComcdky("BLRTKGB");
	    blrtkgbParam.setCdcate1("BLRCSGB");
	    blrtkgbParam.setCdcate2("ACTCT");
	    resultMap.put("blrtkgbList", codeMapper.selectMcodem(blrtkgbParam));

	    return resultMap;
		}


	/*  getMdb02List - MDB02 고정물류비 그리드 데이터 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : BflrmaDTO params - 조회 조건을 담은 고정물류비DTO
	*   출력 PARAMETER : List<BflrmaDTO> - 그리드 데이터를 담은 DTO 리스트
	*   설명      	: MDB02 화면의 그리드 데이터를 조회하는 메서드. 화면의 조건에 따라 데이터를 조회하여 리스트로 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public List<BflrmaDTO> getMdb02List(BflrmaDTO param){
		return billingMasterMapper.selectMdb02List(param);
	}

	/*  saveMdb01List - MDB02 고정물류비 그리드 데이터 저장
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : RequestDTO<BflrmaDTO> params - 저장할 데이터를 담은 DTO
	*   출력 PARAMETER : int - 저장된 데이터의 건수
	*   설명      	: MDB02 화면의 그리드 데이터를 저장하는 메서드. 신규 추가 및 수정된 데이터를 데이터베이스에 반영하고,
	*   		  	  결과로 저장된 데이터의 건수를 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public int saveMdb02List(RequestDTO<BflrmaDTO> params) {
	    int insertCnt = 0;

	    if (!params.getAddList().isEmpty()) {
	        params.getAddList().forEach(idx -> {
	            idx.setBflcode(billingMasterMapper.selectBflcode());
	            UserVO userData = idx.getUserData();
	        });

	        HashMap<String, Object> insertMap = new HashMap<>();
	        insertMap.put("list", params.getAddList());
	        int insertResult = billingMasterMapper.insertMdb02(insertMap);

	        if (insertResult == 0) {
	            throw new InsertCheckedException();
	        }

	        insertCnt += insertResult;
	    }

	    int updateCnt = 0;

	    if (!params.getUpdateList().isEmpty() && !params.getOldList().isEmpty()) {
	        params.getUpdateList().forEach(idx -> {
	        	UserVO userData = idx.getUserData();
	        });

	        HashMap<String, Object> updateMap = new HashMap<>();
	        updateMap.put("list", params.getUpdateList());

	        int updateResult = billingMasterMapper.updateMdb02(updateMap);
	        if (updateResult == 0) {
	            throw new UpdateCheckedException();
	        }

	        updateCnt += updateResult;
	    }

	    return insertCnt + updateCnt;
	}


	//=========================================================//
	//==========================mdb03==========================//
	//=========================================================//

	/*  getMdb03init - MDB03 택배비 초기 데이터 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : UserVO userInfo - 현재 사용자 정보
	*   출력 PARAMETER : Map<String, Object> - 초기 데이터 맵
	*   설명      	: MDB03 화면의 초기 데이터를 조회하여 Map으로 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public Map<String, Object> getMdb03init(UserVO userInfo){
		Map<String, Object> resultMap = new HashMap<>();
		MwarmaDTO mwarma = new MwarmaDTO();
		mwarma.setUserData(userInfo);
		MowrmaDTO mowrma = new MowrmaDTO();
		mowrma.setUserData(userInfo);
		MptnmaDTO mptnma = new MptnmaDTO();
		mptnma.setUserData(userInfo);
		McodemDTO mcodem = new McodemDTO();
		mcodem.setUserData(userInfo);
		MuommaDTO muomma = new MuommaDTO();
		muomma.setUserData(userInfo);
		MdocmaDTO mdocma = new MdocmaDTO();
		mdocma.setUserData(userInfo);


		// 창고 리스트
		List<CommonDTO> warekeyList = organizationmapper.selectWarehouseUserSelectBox(mwarma);
		resultMap.put("warekey", warekeyList);

		// 화주 리스트
		resultMap.put("ownerky", partnerMapper.selectOwnerSelectBox(mowrma));

		// 협력사 리스트
		resultMap.put("ptnrkey", partnerMapper.getMdp02SelectBoxByAll(mptnma));

		// 대금구분 BLRPMGB
		mcodem.setComcdky("BLRPMGB");
		resultMap.put("blrpmgb", codeMapper.selectMcodem(mcodem));

		// 계정 BLRCSGB 택배물류비 물류대행비, 시설비, 택배비만 나오게할것
		mcodem.setComcdky("BLRCSGB");
		mcodem.setComcdvls(Arrays.asList("LGACT", "FCLCT", "DLVCT"));
		resultMap.put("blrcsgb", codeMapper.selectMcodem(mcodem));

		// sub계정 BLRTKGB sub계정은 CDCATE1 CDCATE2 컬럼을 연결할 것
		McodemDTO blrtkgbMcodem = new McodemDTO();
		blrtkgbMcodem.setUserData(userInfo);
		blrtkgbMcodem.setComcdky("BLRTKGB");
		blrtkgbMcodem.setCdcate1("BLRCSGB");
		blrtkgbMcodem.setCdcate2s(Arrays.asList("LGACT", "FCLCT", "DLVCT"));
		resultMap.put("blrtkgb", codeMapper.selectMcodem(blrtkgbMcodem));

		// 단위
		McodemDTO blrunitParam = new McodemDTO();
		blrunitParam.setUserData(userInfo);
		blrunitParam.setComcdky("BLRUNIT");
		resultMap.put("blrunit", codeMapper.selectMcodem(blrunitParam));

		// 문서유형 200 수주오더
		MdocmaDTO doccateParam = new MdocmaDTO();
		doccateParam.setUserData(userInfo);
		doccateParam.setDoccate("200");
		resultMap.put("doccate", documentmapper.selectMdocmaDoccateList(doccateParam));

		// 문서타입 280 고객온라인출고
		mdocma.setDoccate("200");
		mdocma.setDoctype("280");
		resultMap.put("doctype", documentmapper.selectMdocmaDoctypeList(mdocma));

		// 기준일구분 BLRDTGB
		mcodem.setComcdvls(null);
		mcodem.setComcdky("BLRDTGB");
		resultMap.put("blrdtgb", codeMapper.selectMcodem(mcodem));

		// 월구분From(blrmfgb),To(blrmtgb) BLRMTGB
		mcodem.setComcdky("BLRMTGB");
		resultMap.put("blrmfgb", codeMapper.selectMcodem(mcodem));
		resultMap.put("blrmtgb", codeMapper.selectMcodem(mcodem));

		// 기준일From(blrcfdt),To(blrctdt) BLRCTDT
		mcodem.setComcdky("BLRCTDT");
		resultMap.put("blrcfdt", codeMapper.selectMcodem(mcodem));
		resultMap.put("blrctdt", codeMapper.selectMcodem(mcodem));

		return resultMap;
	}

	/*  getMdb03List - MDB03 택배비 그리드 데이터 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : BplrmaDTO params - 조회 조건을 담은 택배비DTO
	*   출력 PARAMETER : List<BplrmaDTO> - 그리드 데이터를 담은 DTO 리스트
	*   설명      	: MDB03 화면의 그리드 데이터를 조회하는 메서드. 화면의 조건에 따라 데이터를 조회하여 리스트로 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public List<BplrmaDTO> getMdb03List(BplrmaDTO params){
		return billingMasterMapper.selectMdb03List(params);
	}

	/*  saveMdb01List - MDB03 택배비 그리드 데이터 저장
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : RequestDTO<BplrmaDTO> params - 저장할 데이터를 담은 DTO
	*   출력 PARAMETER : int - 저장된 데이터의 건수
	*   설명      	: MDB03 화면의 그리드 데이터를 저장하는 메서드. 신규 추가 및 수정된 데이터를 데이터베이스에 반영하고,
	*   		  	  결과로 저장된 데이터의 건수를 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public int saveMdb03List(RequestDTO<BplrmaDTO> params) {
	    int resultCnt = 0;

	    if (!params.getAddList().isEmpty()) {
	        params.getAddList().forEach(idx -> {
	            idx.setBplcode(billingMasterMapper.selectBplcode());
	            UserVO userData = idx.getUserData();
	        });

	        HashMap<String, Object> insertMap = new HashMap<>();
	        insertMap.put("list", params.getAddList());
	        int insertResult = billingMasterMapper.insertMdb03(insertMap);

	        if (insertResult == 0) {
	            throw new InsertCheckedException();
	        }

	        resultCnt += insertResult;
	    }

	    if (!params.getUpdateList().isEmpty()) {
	        params.getUpdateList().forEach(idx -> {
	        	UserVO userData = idx.getUserData();
	        });

	        HashMap<String, Object> updateMap = new HashMap<>();
	        updateMap.put("list", params.getUpdateList());

	        int updateResult = billingMasterMapper.updateMdb03(updateMap);
	        if (updateResult == 0) {
	            throw new UpdateCheckedException();
	        }

	        resultCnt += updateResult;
	    }

	    return resultCnt;
	}



	//=========================================================//
	//==========================mdb04==========================//
	//=========================================================//

	/*  getMdb04init - MDB04 변동운송비 초기 데이터 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : UserVO userInfo - 현재 사용자 정보
	*   출력 PARAMETER : Map<String, Object> - 초기 데이터 맵
	*   설명      	: MDB04 화면의 초기 데이터를 조회하여 Map으로 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public Map<String, Object> getMdb04init(UserVO userInfo){
		Map<String, Object> resultMap = new HashMap<>();
		Map<String, Object> params = new HashMap<>();
		MwarmaDTO mwarma = new MwarmaDTO();
		mwarma.setUserData(userInfo);
		MptnmaDTO mptnma = new MptnmaDTO();
		mptnma.setUserData(userInfo);
		McodemDTO mcodem = new McodemDTO();
		mcodem.setUserData(userInfo);
		MdocmaDTO mdocma = new MdocmaDTO();
		mdocma.setUserData(userInfo);
		TMAreaDTO tmAreaDTO = new TMAreaDTO();
		tmAreaDTO.setUserData(userInfo);
		TMMasterDTO tmmaster = new TMMasterDTO();
		tmmaster.setUserData(userInfo);

	    // 창고
		params.put("userData", userInfo);
	    List<CommonDTO> warekeyList = organizationmapper.selectWarehouseUserSelectBox(mwarma);
	    resultMap.put("warekeyList", warekeyList);

	    // 대금구분업체 리스트(협력사)
	    resultMap.put("ptnrkeyList", partnerMapper.getMdp02SelectBoxByAll(mptnma));

		// 차량 리스트 변동운송비
		String vcaltyp = (String) params.get("vcaltyp");
		if (vcaltyp != null) {
			List<String> vcaltyps = Arrays.asList(vcaltyp.split(","));
			params.put("vcaltyps", vcaltyps);
		}
		tmAreaDTO.setUserData(userInfo);
		tmAreaDTO.setVehtpyn("N");
		resultMap.put("vehickyList", tmAreaMapper.selectTvhcmaVehiList(tmAreaDTO));


	    // 대금구분 BLRPMGB
	    mcodem.setComcdky("BTRPMGB");
	    resultMap.put("btrpmgbList", codeMapper.selectMcodem(mcodem));


	    // 변동계정 BTRVSGB 운송비TRPCT, 연장비EXTCT(참조에etc) 우선은 운송비만
	    mcodem.setComcdky("BTRVSGB"); //btrcsgb
	    mcodem.setCdcate1("");
	    resultMap.put("btrvsgbList", codeMapper.selectMcodem(mcodem));

	    // sub계정 BTRTKGB 지급만 (PROVOS 지급, SRVIC 용역)
	    McodemDTO mcodem1 = new McodemDTO();
	    mcodem1.setUserData(userInfo);
	    mcodem1.setComcdky("BTRTKGB");
	    mcodem1.setComcdvl("PROVS");
	    resultMap.put("btrtkgbList", codeMapper.selectMcodem(mcodem1));

	    // 단위 BTRUNIT 단위 횟수만 (COUNT 횟수, TIME 시간)
	    McodemDTO mcodem2 = new McodemDTO();
	    mcodem2.setUserData(userInfo);
	    mcodem2.setComcdky("BTRUNIT");
	    mcodem2.setComcdvl("COUNT");
 		resultMap.put("btrunitList", codeMapper.selectMcodem(mcodem2));

 		// 문서유형 800 운송
		MdocmaDTO doccateParam = new MdocmaDTO();
		doccateParam.setUserData(userInfo);
 		doccateParam.setDoccate("800");
 		resultMap.put("doccateList", documentmapper.selectMdocmaDoccateList(doccateParam));

		// 문서타입 820셔틀납품, 870긴급운송, 829기사정산
		mdocma.setDoccate("800");
		mdocma.setDoctypes(Arrays.asList("820","870","829"));
		resultMap.put("doctypeList", documentmapper.selectMdocmaDoctypeList(mdocma));

		//셔틀노선키-차량
		resultMap.put("shtrtkyList", tmMasterMapper.selectShtrtkyVehicky(tmmaster));
		
		// 기준일구분 BTRDTGB
		mcodem.setComcdvl(null);
		mcodem.setComcdky("BTRDTGB");
		resultMap.put("btrdtgbList", codeMapper.selectMcodem(mcodem));

		// 월구분From(btrmfgb),To(btrmtgb) BTRMTGB
		mcodem.setComcdky("BTRMTGB");
		resultMap.put("btrmfgbList", codeMapper.selectMcodem(mcodem));
		resultMap.put("btrmtgbList", codeMapper.selectMcodem(mcodem));

		// 기준일From(btrcfdt),To(btrctdt) BTRCTDT
		mcodem.setComcdky("BTRCTDT");
		resultMap.put("btrcfdtList", codeMapper.selectMcodem(mcodem));
		resultMap.put("btrctdtList", codeMapper.selectMcodem(mcodem));

	    return resultMap;
		}


	/*  getMdb04List - MDB04 변동운송비 그리드 데이터 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : BvtrmaDTO params - 조회 조건을 담은 변동운송비DTO
	*   출력 PARAMETER : List<BvtrmaDTO> - 그리드 데이터를 담은 DTO 리스트
	*   설명      	: MDB04 화면의 그리드 데이터를 조회하는 메서드. 화면의 조건에 따라 데이터를 조회하여 리스트로 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public List<BvtrmaDTO> getMdb04List(BvtrmaDTO params){
		return billingMasterMapper.selectMdb04List(params);
	}

	/*  saveMdb04List - MDB04 변동운송비 그리드 데이터 저장
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : RequestDTO<BvtrmaDTO> params - 저장할 데이터를 담은 DTO
	*   출력 PARAMETER : int - 저장된 데이터의 건수
	*   설명      	: MDB04 화면의 그리드 데이터를 저장하는 메서드. 신규 추가 및 수정된 데이터를 데이터베이스에 반영하고,
	*   		  	  결과로 저장된 데이터의 건수를 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public int saveMdb04List(RequestDTO<BvtrmaDTO> params) {
	    int insertCnt = 0;

	    if (!params.getAddList().isEmpty()) {
	        params.getAddList().forEach(idx -> {
	            idx.setBvtcode(billingMasterMapper.selectBvtcode());
	            UserVO userData = idx.getUserData();
	        });

	        HashMap<String, Object> insertMap = new HashMap<>();
	        insertMap.put("list", params.getAddList());
	        int insertResult = billingMasterMapper.insertMdb04(insertMap);

	        if (insertResult == 0) {
	            throw new InsertCheckedException();
	        }

	        insertCnt += insertResult;
	    }

	    int updateCnt = 0;

	    if (!params.getUpdateList().isEmpty() && !params.getOldList().isEmpty()) {
	        params.getUpdateList().forEach(idx -> {
	        	UserVO userData = idx.getUserData();
	        });

	        HashMap<String, Object> updateMap = new HashMap<>();
	        updateMap.put("list", params.getUpdateList());

	        int updateResult = billingMasterMapper.updateMdb04(updateMap);
	        if (updateResult == 0) {
	            throw new UpdateCheckedException();
	        }

	        updateCnt += updateResult;
	    }

	    return insertCnt + updateCnt;
	}




    //=========================================================//
	//==========================mdb05==========================//
	//=========================================================//

	/*  getMdb05init - MDB05 고정운송비 초기 데이터 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : UserVO userInfo - 현재 사용자 정보
	*   출력 PARAMETER : Map<String, Object> - 초기 데이터 맵
	*   설명      	: MDB05 화면의 초기 데이터를 조회하여 Map으로 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public Map<String, Object> getMdb05init(UserVO userInfo){
		Map<String, Object> resultMap = new HashMap<>();
		MwarmaDTO mwarma = new MwarmaDTO();
		mwarma.setUserData(userInfo);
		MptnmaDTO mptnma = new MptnmaDTO();
		mptnma.setUserData(userInfo);
		McodemDTO mcodem = new McodemDTO();
		mcodem.setUserData(userInfo);
		TMAreaDTO tmAreaDTO = new TMAreaDTO();
		tmAreaDTO.setUserData(userInfo);

	    // 창고 리스트
	    resultMap.put("warekeyList", organizationmapper.selectWarehouseUserSelectBox(mwarma));


	    // 대금구분업체 리스트(협력사)
	    resultMap.put("ptnrkeyList", partnerMapper.getMdp02SelectBoxByAll(mptnma));

	    // 차량 리스트 고정운송비
	    tmAreaDTO.setVehtpyn("N");
	    resultMap.put("vehickyList", tmAreaMapper.selectTvhcmaVehiList(tmAreaDTO));

	    // 대금구분 BTRPMGB
	    mcodem.setComcdky("BTRPMGB");
	    resultMap.put("btrpmgbList", codeMapper.selectMcodem(mcodem));


	    // 계정(고정) BTRFSGB 100 기본급, 산재비, 특근비..공제비들 /200 기본운송비, 산재비
	    mcodem.setComcdky("BTRFSGB"); //btrcsgb
	    mcodem.setCdcate1(""); //기타비용 제외
	    resultMap.put("btrfsgbList", codeMapper.selectMcodem(mcodem));


	    // sub계정 BTRTKGB 지급만 (PROVS 지급, SRVIC 용역)
	    McodemDTO mcodem1 = new McodemDTO();
	    mcodem1.setUserData(userInfo);
	    mcodem1.setComcdky("BTRTKGB");
	    mcodem1.setComcdvl("PROVS");
	    resultMap.put("btrtkgbList", codeMapper.selectMcodem(mcodem1));

	    return resultMap;
		}


	/*  getMdb05List - MDB05 고정운송비 그리드 데이터 조회
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : BftrmaDTO params - 조회 조건을 담은 변동물류비DTO
	*   출력 PARAMETER : List<BftrmaDTO> - 그리드 데이터를 담은 DTO 리스트
	*   설명      	: MDB05 화면의 그리드 데이터를 조회하는 메서드. 화면의 조건에 따라 데이터를 조회하여 리스트로 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public List<BftrmaDTO> getMdb05List(BftrmaDTO param){
		return billingMasterMapper.selectMdb05List(param);
	}

	/*  saveMdb01List - MDB01 고정운송비 그리드 데이터 저장
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : RequestDTO<BftrmaDTO> params - 저장할 데이터를 담은 DTO
	*   출력 PARAMETER : int - 저장된 데이터의 건수
	*   설명      	: MDB05 화면의 그리드 데이터를 저장하는 메서드. 신규 추가 및 수정된 데이터를 데이터베이스에 반영하고,
	*   		  	  결과로 저장된 데이터의 건수를 반환합니다.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	public int saveMdb05List(RequestDTO<BftrmaDTO> params) {
	    int insertCnt = 0;

	    if (!params.getAddList().isEmpty()) {
	        params.getAddList().forEach(idx -> {
	            idx.setBftcode(billingMasterMapper.selectBftcode());
	            UserVO userData = idx.getUserData();
	        });

	        HashMap<String, Object> insertMap = new HashMap<>();
	        insertMap.put("list", params.getAddList());
	        int insertResult = billingMasterMapper.insertMdb05(insertMap);

	        if (insertResult == 0) {
	            throw new InsertCheckedException();
	        }

	        insertCnt += insertResult;
	    }

	    int updateCnt = 0;

	    if (!params.getUpdateList().isEmpty() && !params.getOldList().isEmpty()) {
	        params.getUpdateList().forEach(idx -> {
	        	UserVO userData = idx.getUserData();
	        });

	        HashMap<String, Object> updateMap = new HashMap<>();
	        updateMap.put("list", params.getUpdateList());

	        int updateResult = billingMasterMapper.updateMdb05(updateMap);
	        if (updateResult == 0) {
	            throw new UpdateCheckedException();
	        }
	        updateCnt += updateResult;
	    }
	    return insertCnt + updateCnt;
	  }


	}
