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
import com.logistics.md.domain.MaremaDTO;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.domain.McommaDTO;
import com.logistics.md.domain.MlocmaDTO;
import com.logistics.md.domain.MwarmaDTO;
import com.logistics.md.domain.MzonmaDTO;
import com.logistics.md.service.MDC07Service;
import com.logistics.md.service.MDO01Service;
import com.logistics.md.service.MDO02Service;
import com.logistics.md.service.MDO03Service;
import com.logistics.md.service.MDO04Service;
import com.logistics.md.service.MDO05Service;
import com.logistics.sy.domain.UserVO;

import lombok.RequiredArgsConstructor;

/*
*   클래스명 - OrganizationController
*   최초생성일시	: 2023.07
*   최초생성자 : Park T. S.
*   설명 : 회사, 창고, 에어리어, 존, 로케이션 컨트롤러 클래스
*/

@RestController
@RequiredArgsConstructor
@RequestMapping("/md/organization")
public class OrganizationController {
	//organization
	private final MDO01Service mdo01Service; // Company master
	private final MDO02Service mdo02Service; // warehouse master
	private final MDO03Service mdo03Service; // area master
	private final MDO04Service mdo04Service; // zone master
	private final MDO05Service mdo05Service; // location master

	// code
	private final MDC07Service mdc07Service; // 공통code master

	// msg
	private static final String WAREHOUSE_KEY_MSG = "warekey";

	// ********************************************//
	// *****************-MDO01-*****************//
	// *******************************************//
	// company List 조회(grid 데이터)
	@GetMapping("/mdo01/grids/1")
	public List<McommaDTO> getMCommaList(@ModelAttribute McommaDTO params){
		return mdo01Service.getMcommaList(params);
	}

	// warehouse List 저장/수정(grid 데이터)
	@PostMapping("/mdo01/grids/1")
	public int saveMdo01(@RequestBody RequestDTO<McommaDTO> saveList) {
		return mdo01Service.saveMdo01(saveList);
	}

	// ********************************************//
	// *****************-MDO02-*****************//
	// *******************************************//
	// warehouse List 조회(grid 데이터)
	@GetMapping("/mdo02/grids/1")
	public List<MwarmaDTO> getMwarmaList(@ModelAttribute MwarmaDTO params) {
		return mdo02Service.getMwarmaList(params);
	}

	// warehouse List 저장/수정(grid 데이터)
	@PostMapping("/mdo02/grids/1")
	public int saveMdo02(@RequestBody RequestDTO<MwarmaDTO> saveList) {
		return mdo02Service.saveMdo02(saveList);
	}

	// ********************************************//
	// *****************-MDO03-*****************//
	// *******************************************//
	// area List 조회(grid용 데이터)
	@GetMapping("/mdo03/grids/1")
	public List<MaremaDTO> getMaremaList(@ModelAttribute MaremaDTO params) {
		return mdo03Service.getMaremaList(params);
	}

	// area List(grid 데이터) 저장/수정
	@PostMapping("/mdo03/grids/1")
	public int saveMdo03(@RequestBody RequestDTO<MaremaDTO> saveList) {
		return mdo03Service.saveMdo3(saveList);
	}

	// mdo03 초기화 작업(selectBox, keyValue)
	@GetMapping("/mdo03/init")
	public InitDataDTO getMdo03InitData(@ModelAttribute CommonDTO common) {
		Map<String, Object> item = new HashMap<>(); // response
		UserVO userData = common.getUserData();

		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(userData);
		item.put(WAREHOUSE_KEY_MSG, mdo02Service.getWarehouseSelectBox(mwarmaDTO));

		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(userData);
		mcodemDTO.setComcdky("AREATYP");
		item.put("areatyp", mdc07Service.getCommonCodeSelectBox(mcodemDTO));

		InitDataDTO initDataDTO = new InitDataDTO();
		initDataDTO.setItem(item);

		return initDataDTO;
	}

	// ********************************************//
	// *****************-MDO04-*****************//
	// *******************************************//
	// zone List 조회(grid용 데이터)
	@GetMapping("/mdo04/grids/1")
	public List<MzonmaDTO> getMzonmaList(@ModelAttribute MzonmaDTO params) {
		return mdo04Service.getMzonmaList(params);
	}

	// zone List(grid 데이터) 저장/수정
	@PostMapping("/mdo04/grids/1")
	public int saveMdo04(@RequestBody RequestDTO<MzonmaDTO> saveList) {
		return mdo04Service.saveMdo4(saveList);
	}

	// mdo04 초기화 작업(selectBox / keyValue)
	@GetMapping("/mdo04/init")
	public InitDataDTO getMdo04InitData(@ModelAttribute CommonDTO common) {
		Map<String, Object> item = new HashMap<>();
		UserVO userData = common.getUserData();

		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(userData);
		mcodemDTO.setComcdky("ZONETYP,SKUGR01"); // in search 조건(properties로 변경)
		item.put("comcdky", mdc07Service.getCommonCodeSelectBox(mcodemDTO));

		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(userData);
		item.put(WAREHOUSE_KEY_MSG, mdo02Service.getWarehouseSelectBox(mwarmaDTO));

		InitDataDTO initDataDTO = new InitDataDTO();
		initDataDTO.setItem(item);

		return initDataDTO;
	}

	// Zone 관계 리스트 가져옴(콤보박스용)
	@GetMapping("/wa-relations")
	public List<MzonmaDTO> selectWareAreaRelations(@ModelAttribute MzonmaDTO params) {
		return mdo04Service.selectWareAreaRelations(params);
	}

	// ********************************************//
	// *****************-MDO05-*****************//
	// *******************************************//
	// location List 조회(grid용 데이터)
	@GetMapping(value = "/mdo05/grids/1")
	public List<MlocmaDTO> getMlocmaList(@ModelAttribute MlocmaDTO params) {
		return mdo05Service.selectMlocmaList(params);
	}

	// location List(grid 데이터) 저장/수정
	@PostMapping(value = "/mdo05/grids/1")
	public int saveMdo05(@RequestBody RequestDTO<MlocmaDTO> saveList) {
		return mdo05Service.saveMdo05(saveList);
	}

	// mdo05 초기화 작업
	@GetMapping(value = "/mdo05/init")
	public InitDataDTO getMdo05InitData(@ModelAttribute CommonDTO common) {
		Map<String, Object> item = new HashMap<>();
		UserVO userData = common.getUserData();

		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(userData);
		mcodemDTO.setComcdky("LOCTYPE,LOCSTAT,EQUSTAT");
		item.put("comcdky", mdc07Service.getCommonCodeSelectBox(mcodemDTO));

		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(userData);
		item.put(WAREHOUSE_KEY_MSG, mdo02Service.getWarehouseSelectBox(mwarmaDTO));

		InitDataDTO initDataDTO = new InitDataDTO();
		initDataDTO.setItem(item);

		return initDataDTO;
	}

	// Location 관계 리스트 가져옴(콤보박스용)
	@GetMapping("/waz-relations")
	public List<MlocmaDTO> selectWareAreaZoneRelations(@ModelAttribute MlocmaDTO params) {
		return mdo05Service.selectWareAreaZoneRelations(params);
	}
}
