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

import com.logistics.common.dto.InitDataDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.domain.MdocmaDTO;
import com.logistics.md.domain.MlscalDTO;
import com.logistics.md.domain.MrscmaDTO;
import com.logistics.md.domain.MwarmaDTO;
import com.logistics.md.service.MDC06Service;
import com.logistics.md.service.MDC07Service;
import com.logistics.md.service.MDC10Service;
import com.logistics.md.service.MDO02Service;
import com.logistics.sy.domain.UserVO;

import lombok.RequiredArgsConstructor;

/*
*   클래스명 - CodeController
*   최초생성일시	: 2023.07
*   최초생성자 : Park T. S.
*   설명 : 공통, 사유코드 컨트롤러 클래스
*/

@RestController
@RequiredArgsConstructor
@RequestMapping("/md/code")
public class CodeController {
	// organization
	private final MDO02Service mdo02Service; // 창고 마스터

	// code
	private final MDC06Service mdc06Service; // 사유코드 마스터
	private final MDC07Service mdc07Service; // 공통코드 마스터
	private final MDC10Service mdc10Service; // 물류달력 마스터

	//********************************************//
	//*****************-MDC06-*****************//
	//*******************************************//
	// mdc06 사유코드 페이지 초기화작업
	@GetMapping(value = "/mdc06/init")
	public InitDataDTO getMdc06InitData(@ModelAttribute MdocmaDTO params) {
		Map<String, Object> item = new HashMap<>();
		UserVO userData = params.getUserData();

		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(userData);
		item.put("warekey", mdo02Service.getWarehouseSelectBox(mwarmaDTO)); // 창고정보

		item.put("doccate", mdc06Service.getMdocmaAllDoccateList(params)); // doccate
		item.put("doctype", mdc06Service.getMdocmaDoccateDoctypeRelations(params)); // doccate + doctype

		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(userData);
		mcodemDTO.setComcdky("RSCATE1");
		item.put("rscate1", mdc07Service.getCommonCodeSelectBox(mcodemDTO)); // doctype

		InitDataDTO initDataDTO = new InitDataDTO();
		initDataDTO.setItem(item);

		return initDataDTO;
	}

	// mdc06 리스트 조회(grid용)
	@GetMapping(value = "/mdc06/grids/1")
	public List<MrscmaDTO> getMrscmaList(@ModelAttribute MrscmaDTO params) {
		return mdc06Service.getMrscmaList(params);
	}

	// mdc06 grid 데이터 저장
	@PostMapping(value = "/mdc06/grids/1")
	public int saveMdc06(@RequestBody RequestDTO<MrscmaDTO> saveList) {
		return mdc06Service.saveMdc06(saveList);
	}

	//********************************************//
	//*****************-MDC07-*****************//
	//*******************************************//
	// mdc07 공통코드 List 조회
	@GetMapping(value = "/mdc07/grids/1")
	public List<McodemDTO> getMcodemList(@ModelAttribute McodemDTO params) {
		return mdc07Service.getMcodemList(params);
	}

	// mdc07 공통코드 List 저장
	@PostMapping(value = "/mdc07/grids/1")
	public int saveMdc07(@RequestBody RequestDTO<McodemDTO> saveList) {
		return mdc07Service.saveMdc07(saveList);
	}

	// mdc07 공통코드 init
	@GetMapping(value = "/mdc07/init")
	public InitDataDTO getMdc07InitData(@ModelAttribute McodemDTO params) {
		Map<String, Object> item = new HashMap<>();

		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(params.getUserData());
		item.put("warekey", mdo02Service.getWarehouseSelectBox(mwarmaDTO)); // 창고정보

		InitDataDTO initDataDTO = new InitDataDTO();
		initDataDTO.setItem(item);

		return initDataDTO;
	}
	//********************************************//
	//*****************-MDC10-*****************//
	//*******************************************//
	// mdc10 물류달력 List 조회
	@GetMapping(value = "/mdc10/grids/1")
	public List<MlscalDTO> getMlscalList(@ModelAttribute MlscalDTO params) {
		return mdc10Service.getMlscalList(params);
	}

	// mdc10 물류달력 List 저장
	@PostMapping(value = "/mdc10/grids/1")
	public int saveMdc10(@RequestBody RequestDTO<MlscalDTO> saveList) {
		return mdc10Service.saveMdc10(saveList);
	}

	// mdc10 물류달력 init
	@GetMapping(value = "/mdc10/init")
	public InitDataDTO getMdc10InitData(@ModelAttribute MlscalDTO params) {
		Map<String, Object> item = new HashMap<>();

		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(params.getUserData());
		item.put("warekey", mdo02Service.getWarehouseSelectBox(mwarmaDTO)); // 창고정보

		InitDataDTO initDataDTO = new InitDataDTO();
		initDataDTO.setItem(item);

		return initDataDTO;
	}
}
