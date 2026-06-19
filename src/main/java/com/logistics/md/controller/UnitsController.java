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
import com.logistics.md.domain.MlocmaDTO;
import com.logistics.md.domain.MowrmaDTO;
import com.logistics.md.domain.MpakmaDTO;
import com.logistics.md.domain.MptnmaDTO;
import com.logistics.md.domain.MskustDTO;
import com.logistics.md.domain.MskuwcDTO;
import com.logistics.md.domain.MtutmaDTO;
import com.logistics.md.domain.MuommaDTO;
import com.logistics.md.domain.MwarmaDTO;
import com.logistics.md.service.MDC07Service;
import com.logistics.md.service.MDO02Service;
import com.logistics.md.service.MDO05Service;
import com.logistics.md.service.MDP11Service;
import com.logistics.md.service.MDP21Service;
import com.logistics.md.service.MDU01Service;
import com.logistics.md.service.MDU02Service;
import com.logistics.md.service.MDU03Service;
import com.logistics.md.service.MDU04Service;
import com.logistics.md.service.MDU05Service;
import com.logistics.md.service.MDU10Service;
import com.logistics.sy.domain.UserVO;

import lombok.RequiredArgsConstructor;

/*
*   클래스명 - UnitsController
*   최초생성일시	: 2023.07
*   최초생성자 : Park T. S.
*   설명 : 부품, 단위, 이동용기, 입수량 컨트롤러 클래스
*/

@RestController
@RequiredArgsConstructor
@RequestMapping("/md/units")
public class UnitsController {
	// msg
	private static final String WAREHOUSE_KEY_MSG = "warekey";

	// unit
	private final MDU01Service mdu01Service; // SKU수신
	private final MDU02Service mdu02Service; // SKU관리
	private final MDU03Service mdu03Service; // 단위
	private final MDU04Service mdu04Service; // 이동용기
	private final MDU05Service mdu05Service; // 입수관리
	private final MDU10Service mdu10Service; // 세트구성

	// organization
	private final MDO02Service mdo02Service; // 창고등록
	private final MDO05Service mdo05Service; // Location

	// partner
	private final MDP11Service mdp11Service; // 화주등록
	private final MDP21Service mdp21Service; // 거래처등록

	// code
	private final MDC07Service mdc07Service; // 공용 코드

	// ********************************************//
	// *****************-MDU01-*****************//
	// *******************************************//
	// mdu01 초기화 작업(selectbox)
		@GetMapping("/mdu01/init")
		public InitDataDTO getMdu01InitData(@ModelAttribute CommonDTO common) {
			Map<String, Object> item = new HashMap<>();
			UserVO userData = common.getUserData();

			MwarmaDTO mwarmaDTO = new MwarmaDTO();
			mwarmaDTO.setUserData(userData);
			item.put(WAREHOUSE_KEY_MSG, mdo02Service.getWarehouseSelectBox(mwarmaDTO)); // 창고정보

			MowrmaDTO mowrmaDTO = new MowrmaDTO();
			mowrmaDTO.setUserData(userData);
			item.put("ownerky", mdp11Service.getOwnerSelectBox(mowrmaDTO)); // 화주정보

			McodemDTO mcodemDTO = new McodemDTO();
			mcodemDTO.setUserData(userData);
			mcodemDTO.setComcdky("SKUGR01");
			item.put("skugr01", mdc07Service.getCommonCodeSelectBox(mcodemDTO));

			InitDataDTO initDataDTO = new InitDataDTO();
			initDataDTO.setItem(item);

			return initDataDTO;
		}

		// mdu01 SKU 데이터 가져오기
		@GetMapping("/mdu01/grids/2")
		public List<MskuwcDTO> getMdu01ItemList(@ModelAttribute MskuwcDTO saveList) {
			return mdu01Service.getMdu01ItemList(saveList);
		}

		// MDM SKU 수신 데이터 저장
		@PostMapping("/mdu01/grids/2")
		public int saveMdu01(@RequestBody RequestDTO<MskuwcDTO> saveList) {
			return mdu01Service.saveMDMSkuList(saveList);
		}

	// ********************************************//
	// *****************-MDU02-*****************//
	// *******************************************//
	// mdu02 초기화 작업(selectbox)
	@GetMapping("/mdu02/init")
	public InitDataDTO getMdu02InitData(@ModelAttribute CommonDTO common) {
		Map<String, Object> item = new HashMap<>();
		UserVO userData = common.getUserData();

		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(userData);
		item.put(WAREHOUSE_KEY_MSG, mdo02Service.getWarehouseSelectBox(mwarmaDTO)); // 창고정보

		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(userData);
		item.put("ownerky", mdp11Service.getOwnerSelectBox(mowrmaDTO)); // 화주정보

		MlocmaDTO mlocmaDTO = new MlocmaDTO();
		mlocmaDTO.setUserData(userData);
		mlocmaDTO.setAreakey("STG");
		item.put("sputzon", mdo05Service.selectWareAreaZoneRelations(mlocmaDTO)); // 기본적치 zone (location 관계 포함)
		item.put("sputloc", mdo05Service.selectWareAreaZoneLocationRelations(mlocmaDTO)); // 기본적치 location(location 관계 포함)

		MtutmaDTO mtutmaDTO = new MtutmaDTO();
		mtutmaDTO.setUserData(userData);
		item.put("truntyp", mdu04Service.getTransferUnitSelectBox(mtutmaDTO)); // 이동용기

		MpakmaDTO mpakmaDTO = new MpakmaDTO();
		mpakmaDTO.setUserData(userData);
		item.put("packkey", mdu05Service.selectMpakmaRelations(mpakmaDTO)); // 포장용기

		MptnmaDTO mptnmaDTO = new MptnmaDTO();
		mptnmaDTO.setUserData(userData);
		mptnmaDTO.setPtnrtyp("VENDER,CARRIER");
		item.put("vendkey", mdp21Service.getPartnerSelectBox(mptnmaDTO)); // CUSTOMER

		MuommaDTO muommaDTO = new MuommaDTO();
		muommaDTO.setUserData(userData);
		item.put("suomkey", mdu03Service.getUnitOfMeasureSelectBox(muommaDTO)); // UoM

		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(userData);
		mcodemDTO.setComcdky("SKUGR01,SKUGR02,SKUSTAT,SKUTYPE");
		item.put("comcdky", mdc07Service.getCommonCodeSelectBox(mcodemDTO));

		InitDataDTO initDataDTO = new InitDataDTO();
		initDataDTO.setItem(item);

		return initDataDTO;
	}

	// SKU관리 List(grid용) 조회
	@GetMapping("/mdu02/grids/1")
	public List<MskuwcDTO> getMdu02List(@ModelAttribute MskuwcDTO params) {
		return mdu02Service.getMdu02List(params);
	}

	// SKU생성(grid 데이터) 저장
	@PostMapping("/mdu02/grids/1")
	public int saveMdu02(@RequestBody RequestDTO<MskuwcDTO> saveList) {
		return mdu02Service.saveMdu02(saveList);
	}

	// SKU uom, vendkey 조회
	@GetMapping("/item-info")
	public List<MskuwcDTO> getMskuwcUomAndSvend(@ModelAttribute MskuwcDTO params) {
		return mdu02Service.getMskuwcUomAndSvend(params);
	}

	@GetMapping("/skustat")
	public List<MskuwcDTO> getSkuForStat(@ModelAttribute MskuwcDTO params) {
		return mdu02Service.getSkuForStat(params);
	}

	// ********************************************//
	// *****************-MDU03-*****************//
	// *******************************************//
	// Unit of measure List 조회
	@GetMapping("/mdu03/grids/1")
	public List<MuommaDTO> selectMuommaList(@ModelAttribute MuommaDTO params) {
		return mdu03Service.selectMuommaList(params);
	}

	// Unit of measure List 저장
	@PostMapping("/mdu03/grids/1")
	public int saveMdu03(@RequestBody RequestDTO<MuommaDTO> saveList) {
		return mdu03Service.saveMdu03(saveList);
	}

	// ********************************************//
	// *****************-MDU04-*****************//
	// *******************************************//
	// 이동용기 List(grid용) 조회
	@GetMapping("/mdu04/grids/1")
	public List<MtutmaDTO> getMtutmaList(MtutmaDTO params) {
		return mdu04Service.getMtutmaList(params);
	}

	// 이동용기 List(grid data) 저장
	@PostMapping("/mdu04/grids/1")
	public int saveMdu04(@RequestBody RequestDTO<MtutmaDTO> saveList) {
		return mdu04Service.saveMdu04(saveList);
	}

	// mdu04 초기화 작업(단위 select box)
	@GetMapping("/mdu04/init")
	public InitDataDTO getMdu04InitData(@ModelAttribute MuommaDTO params) {
		Map<String, Object> item = new HashMap<>();
		item.put("uomekey", mdu03Service.getUnitOfMeasureSelectBox(params));

		InitDataDTO initDataDTO = new InitDataDTO();
		initDataDTO.setItem(item);

		return initDataDTO;
	}

	// ********************************************//
	// *****************-MDU05-*****************//
	// *******************************************//
	// 입수관리 List(grid용) 조회
	@GetMapping("/mdu05/grids/1")
	public List<MpakmaDTO> getMpakmaList(@ModelAttribute MpakmaDTO params) {
		return mdu05Service.getMpakmaList(params);
	}

	// 입수관리 리스트(grid data) 저장
	@PostMapping("/mdu05/grids/1")
	public int saveMdu05(@RequestBody RequestDTO<MpakmaDTO> saveList) {
		return mdu05Service.saveMdu05(saveList);
	}

	// mdu05 초기화작업(warehouse, units)
	@GetMapping("/mdu05/init")
	public InitDataDTO getMdu05InitData(@ModelAttribute CommonDTO common) {
		Map<String, Object> item = new HashMap<>();
		UserVO userData = common.getUserData();

		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(userData);
		item.put(WAREHOUSE_KEY_MSG, mdo02Service.getWarehouseSelectBox(mwarmaDTO));

		MtutmaDTO mtutmaDTO = new MtutmaDTO();
		mtutmaDTO.setUserData(userData);
		item.put("truntyp", mdu04Service.getTransferUnitSelectBox(mtutmaDTO));

		MuommaDTO muommaDTO = new MuommaDTO();
		muommaDTO.setUserData(userData);
		item.put("puomkey", mdu03Service.getUnitOfMeasureSelectBox(muommaDTO));

		InitDataDTO initDataDTO = new InitDataDTO();
		initDataDTO.setItem(item);

		return initDataDTO;
	}

	// ********************************************//
	// *****************-MDU10-*****************//
	// *******************************************//
	// MDU10 초기화
	@GetMapping("/mdu10/init")
	public InitDataDTO getMdu10InitData(@ModelAttribute CommonDTO common) {
		Map<String, Object> item = new HashMap<>();
		UserVO userData = common.getUserData();

		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(userData);
		item.put(WAREHOUSE_KEY_MSG, mdo02Service.getWarehouseSelectBox(mwarmaDTO)); // 창고정보

		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(userData);
		item.put("ownerky", mdp11Service.getOwnerSelectBox(mowrmaDTO)); // 화주정보

		MskuwcDTO mskuwcDTO = new MskuwcDTO();
		mskuwcDTO.setUserData(userData);
		mskuwcDTO.setSkutype("KMAT"); // 아이템만
		List<MskuwcDTO> skudescList = mdu02Service.getSkuwcSelectBox(mskuwcDTO);
		item.put("skudesc", skudescList); // 부품키
		item.put("sbskuds", skudescList.stream().toList()); // 부품명

		MuommaDTO muommaDTO = new MuommaDTO();
		muommaDTO.setUserData(userData);
		item.put("uomekey", mdu03Service.getUnitOfMeasureSelectBox(muommaDTO)); // UoM

		MptnmaDTO mptnmaDTO = new MptnmaDTO();
		mptnmaDTO.setUserData(userData);
		mptnmaDTO.setPtnrtyp("CUSTOMER");
		item.put("svendky", mdp21Service.getPartnerSelectBox(mptnmaDTO)); // CUSTOMER

		InitDataDTO initDataDTO = new InitDataDTO();
		initDataDTO.setItem(item);

		return initDataDTO;
	}

	// 세트구성 List(head grid용) 조회
	@GetMapping("/mdu10/grids/1")
	public List<MskustDTO> getMdu10SetList(@ModelAttribute MskustDTO params) {
		return mdu10Service.getMdu10SetList(params);
	}

	// 세트구성 List(item grid용) 조회
	@GetMapping("/mdu10/grids/2")
	public List<MskustDTO> getMdu10ItemList(@ModelAttribute MskustDTO params) {
		return mdu10Service.getMdu10ItemList(params);
	}

	// 세트구성(grid 데이터) 저장
	@PostMapping("/mdu10/grids/all")
	public int saveMdu10(@RequestBody RequestDTO<MskustDTO> saveData) {
		return mdu10Service.saveMdu10(saveData);
	}
}
