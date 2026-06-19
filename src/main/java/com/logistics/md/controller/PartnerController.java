package com.logistics.md.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.InitDataDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.domain.MdesmaDTO;
import com.logistics.md.domain.MlocmaDTO;
import com.logistics.md.domain.MowrmaDTO;
import com.logistics.md.domain.MptnmaDTO;
import com.logistics.md.service.MDC07Service;
import com.logistics.md.service.MDO05Service;
import com.logistics.md.service.MDP10Service;
import com.logistics.md.service.MDP11Service;
import com.logistics.md.service.MDP20Service;
import com.logistics.md.service.MDP21Service;
import com.logistics.md.service.MDP30Service;
import com.logistics.sy.domain.UserVO;

import lombok.RequiredArgsConstructor;

/*
*   클래스명 - PartnerController
*   최초생성일시	: 2023.07
*   최초생성자 : Park T. S.
*   설명 : 화주, 업체 컨트롤러 클래스
*/

@RestController
@RequiredArgsConstructor
@RequestMapping("/md/partner")
public class PartnerController {
	// organization
	private final MDO05Service mdo05Service; // Location

	// partner
	private final MDP10Service mdp10Service; // 화주맵핑
	private final MDP11Service mdp11Service; // 화주관리
	private final MDP20Service mdp20Service; // 거래처매핑
	private final MDP21Service mdp21Service; // 거래처관리
	private final MDP30Service mdp30Service; // 도착지등록

	// code
	private final MDC07Service mdc07Service; // commnon code

	// ********************************************//
	// *****************-MDP10-*****************//
	// *******************************************//
	// mdp10 MOWRMA 화주 데이터
	@GetMapping("/mdp10/grids/2")
	public List<MowrmaDTO> getMowrmaListForMapping(@ModelAttribute MowrmaDTO params) {
		return mdp11Service.getMowrmaList(params);
	}

	// mdp10 MDM 화주 데이터 저장
	@PostMapping("/mdp10/grids/2")
	public int saveMDMOwnerList(@RequestBody RequestDTO<MowrmaDTO> saveList) {
		return mdp10Service.saveMDMOwnerList(saveList);
	}

	// ********************************************//
	// *****************-MDP11-*****************//
	// *******************************************//
	// 화주 List 조회(grid용)
	@GetMapping("/mdp11/grids/1")
	public List<MowrmaDTO> getMowrmaList(@ModelAttribute MowrmaDTO params) {
		return mdp11Service.getMowrmaList(params);
	}

	// 화주 List(grid data) 저장
	@PostMapping("/mdp11/grids/1")
	public int saveMdp11(@RequestBody RequestDTO<MowrmaDTO> saveList) {
		return mdp11Service.saveMdp11(saveList);
	}

	// mdp11 초기화 작업(기본 zone, location)
	@GetMapping("/mdp11/init")
	public InitDataDTO getMdp11InitData(@ModelAttribute CommonDTO common) {
		Map<String, Object> item = new HashMap<>();
		UserVO userData = common.getUserData();

		MlocmaDTO mlocmaDTO = new MlocmaDTO();
		mlocmaDTO.setUserData(userData);
		mlocmaDTO.setAreakey("STG");
		item.put("sputzon", mdo05Service.selectWareAreaZoneRelations(mlocmaDTO)); // 기본적치 zone (location 관계 포함)
		item.put("sputloc", mdo05Service.selectWareAreaZoneLocationRelations(mlocmaDTO)); // 기본적치 location(location 관계 포함)
		item.put("spikloc", mdo05Service.getMultiLocationSelectBox(mlocmaDTO)); // 기본피킹 location = 모든 기본적치 로케이션

		InitDataDTO initDataDTO = new InitDataDTO();
		initDataDTO.setItem(item);

		return initDataDTO;
	}

	// ********************************************//
	// *****************-MDP20-*****************//
	// *******************************************//
	// 거래처 List Mptnma 조회(매핑용)
	@GetMapping("/mdp20/grids/2")
	public List<MptnmaDTO> getMptnmaListForMapping(@ModelAttribute MptnmaDTO params) {
		return mdp21Service.getMptnmaList(params);
	}

	// mdp20 MDM 거래처 데이터 저장
	@PostMapping("/mdp20/grids/2")
	public int saveMDMPartnerList(@RequestBody RequestDTO<MptnmaDTO> saveList) {
		return mdp20Service.saveMDMPartnerList(saveList);
	}

	// mdp20 초기화 작업(거래처)
	@GetMapping("/mdp20/init")
	public InitDataDTO getMdp20InitData(@ModelAttribute CommonDTO common) {
		Map<String, Object> item = new HashMap<>();
		UserVO userData = common.getUserData();

		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(userData);
		mcodemDTO.setComcdky("PTNRTYP");
		item.put("ptnrtyp", mdc07Service.getCommonCodeSelectBox(mcodemDTO));

		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(userData);
		item.put("ownerky", mdp11Service.getOwnerSelectBox(mowrmaDTO));

		InitDataDTO initDataDTO = new InitDataDTO();
		initDataDTO.setItem(item);

		return initDataDTO;
	}

	// ********************************************//
	// *****************-MDP21-*****************//
	// *******************************************//
	// 거래처 List 조회(grid용)
	@GetMapping("/mdp21/grids/1")
	public List<MptnmaDTO> getMptnmaList(@ModelAttribute MptnmaDTO params) {
		return mdp21Service.getMptnmaList(params);
	}

	// 거래처 List(grid data) 저장
	@PostMapping("/mdp21/grids/1")
	public int saveMdp21(@RequestBody RequestDTO<MptnmaDTO> saveList) {
		return mdp21Service.saveMdp21(saveList);
	}

	// 거래처 key List(자동채우기용) 저장(현재 미사용)
	@GetMapping("/mdp21/partnerkey")
	public Map<String, String> getMdp21Ptnrkey() {
		Map<String, String> item = new HashMap<>();
		item.put("ptnrkey", mdp21Service.mdp21Ptnrkey());
		return item;
	}

	// mdp21 초기화 작업(화주 selectbox, code, 거래처)
	// getMdp20InitData와 기능이 같지만 mdp20은 grid만 쓰이고
	// mdp21은 grid용과 검색영역용 둘다 사용하여 달라질 수 있어 나누었음
	@GetMapping("/mdp21/init")
	public InitDataDTO getMdp21InitData(@ModelAttribute CommonDTO common) {
		Map<String, Object> item = new HashMap<>();
		UserVO userData = common.getUserData();

		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(userData);
		mcodemDTO.setComcdky("PTNRTYP");
		item.put("ptnrtyp", mdc07Service.getCommonCodeSelectBox(mcodemDTO));

		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(userData);
		item.put("ownerky", mdp11Service.getOwnerSelectBox(mowrmaDTO));

		InitDataDTO initDataDTO = new InitDataDTO();
		initDataDTO.setItem(item);

		return initDataDTO;
	}

	// ********************************************//
	// *****************-MDP30-*****************//
	// *******************************************//
	// mdp30 초기화 작업(selectbox)
	@GetMapping("/mdp30/init")
	public InitDataDTO getMdp30InitData(@ModelAttribute CommonDTO common) {
		Map<String, Object> item = new HashMap<>();
		UserVO userData = common.getUserData();

		MptnmaDTO mptnmaDTO = new MptnmaDTO();
		mptnmaDTO.setUserData(userData);
		item.put("ptnrkey", mdp21Service.getPartnerSelectBox(mptnmaDTO));

		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(userData);
		mcodemDTO.setComcdky("VHCTNCD");
		item.put("vhctncd", mdc07Service.getCommonCodeSelectBox(mcodemDTO));
		InitDataDTO initDataDTO = new InitDataDTO();
		initDataDTO.setItem(item);

		return initDataDTO;
	}

	// 도착지등록 List 조회(grid용)
	@GetMapping("/mdp30/grids/1")
	public List<MdesmaDTO> getMdesmaList(@ModelAttribute MdesmaDTO params) {
		return mdp30Service.getMdesmaList(params);
	}

	// 도착지등록 List(grid data) 저장
	@PostMapping("/mdp30/grids/1")
	public int saveMdp30(@RequestBody RequestDTO<MdesmaDTO> saveList) {
		return mdp30Service.saveMdp30(saveList);
	}
}
