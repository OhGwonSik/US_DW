package com.logistics.wm.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.md.domain.MrscmaDTO;
import com.logistics.md.domain.MskuwcDTO;
import com.logistics.wm.domain.RecvDTO;
import com.logistics.wm.domain.RecvEtcDTO;
import com.logistics.wm.domain.RecvPutDTO;
import com.logistics.wm.domain.TaskDTO;
import com.logistics.wm.service.InboundService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InboundController implements Serializable {

	private static final long serialVersionUID = 1L;

	private final InboundService inboundService;

	/*  getWmr20Init - 입하조회 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvDTO - 입하DTO
	*   출력 PARAMETA	: Map<String, Object> - 호출한 데이터를 담을 Map
	*   설명			: 입하조회 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@GetMapping(value = "/wm/inbound/wmr20/init/1")
	public Map<String, Object> getWmr20Init(@ModelAttribute RecvDTO param) {
		return inboundService.getWmr20Init(param);
	}

	/*  getWmr20HeaderList - 입하조회 HeaderList 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvDTO - 입하DTO
	*   출력 PARAMETA	: List<RecvDTO> - 입하DTO 배열
	*   설명			: 입하조회 페이지 상단의 헤더리스트를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@GetMapping(value = "/wm/inbound/wmr20/grids/1")
	public List<RecvDTO> getWmr20HeaderList(@ModelAttribute RecvDTO param) {
		return inboundService.getWmr20HeaderList(param);
	}

	/*  getWmr20ItemList - 입하조회 ItemList 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvDTO - 입하DTO
	*   출력 PARAMETA	: List<RecvDTO> - 입하DTO 리스트
	*   설명			: 입하조회 페이지 하단의 아이템리스트를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@GetMapping(value = "/wm/inbound/wmr20/grids/2")
	public List<RecvDTO> getWmr20ItemList(@ModelAttribute RecvDTO param) {
		return inboundService.getWmr20ItemList(param);
	}

	/*  getWmr21Init - 입하취소 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvDTO - 입하DTO
	*   출력 PARAMETA	: Map<String, Object> - 호출한 데이터를 담을 Map
	*   설명			: 입하취소 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@GetMapping(value = "/wm/inbound/wmr21/inits/1")
	public Map<String, Object> getWmr21Init(@ModelAttribute RecvDTO param) {
		return inboundService.getWmr21Init(param);
	}
	
	/*  getWmr21Reason - 입하취소 사유코드 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: MrscmaDTO - 사유코드 DTO
	*   출력 PARAMETA	: CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   설명			: 입하취소 페이지의 취소 사유코드 셀렉트박스를 구성하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@GetMapping(value = "/wm/inbound/wmr21/reason")
	public List<CommonDTO> getWmr21Reason(@ModelAttribute MrscmaDTO param){
		return inboundService.getWmr21RcarscdList(param);
	}

	/*  getWmr21List - 입하취소 리스트 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvDTO - 입하 DTO
	*   출력 PARAMETA	: List<RecvDTO> - 입하 DTO의 리스트
	*   설명			: 입하취소 페이지에서 적치되지 않은 재고 목록을 보여주는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@GetMapping(value = "/wm/inbound/wmr21/grids/1")
	public List<RecvDTO> getWmr21List(@ModelAttribute RecvDTO param) {
		return inboundService.getWmr21List(param);
	}

	/*  saveWmr21 - 입하취소
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<RecvDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int - 성공시 1 반환
	*   설명			: 입하취소 페이지에서 적치되지 않은 재고의 입하를 취소하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@PostMapping(value = "/wm/inbound/wmr21/grids/1")
	public int saveWmr21(@RequestBody RequestDTO<RecvDTO> param, CommonDTO common) {
		return inboundService.saveWmr21List(param, common);
	}

	/*  getWmr22Init - 입하등록 페이지 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvDTO - 입하DTO
	*   출력 PARAMETA	: Map<String, Object> - 호출한 데이터를 담을 Map
	*   설명			: 입하등록 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@GetMapping(value = "/wm/inbound/wmr22/init")
	public Map<String, Object> getWmr22Init(@ModelAttribute RecvDTO param) {
		return inboundService.getWmr22Init(param);
	}

	/*  getWmr22HeaderList - 입하등록 상단 헤더 리스트 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvDTO - 입하 DTO
	*   출력 PARAMETA	: List<RecvDTO> - 입하 DTO의 리스트
	*   설명			: 입하등록페이지에서 상단 헤더리스트를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@ResponseBody
	@GetMapping(value = "/wm/inbound/wmr22/grids/1")
	public List<RecvDTO> getwmr22HeaderList(@ModelAttribute RecvDTO param) {
		return inboundService.getWmr22HeaderList(param);
	}

	/*  getWmr22ItemList - 입하등록 하단 아이템 리스트 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvDTO - 입하 DTO
	*   출력 PARAMETA	: List<RecvDTO> - 입하 DTO의 리스트
	*   설명			: 입하등록페이지에서 하단 아이템리스트를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@ResponseBody
	@GetMapping(value = "/wm/inbound/wmr22/grids/2")
	public List<RecvDTO> getwmr22ItemList(@ModelAttribute RecvDTO param) {
		return inboundService.getWmr22ItemList(param);
	}

	/*  saveWmr22 - 입하등록
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<RecvDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int - 성공시 1 반환
	*   설명			: 입하등록페이지에서 입하등록을 실행하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@ResponseBody
	@PostMapping(value = "/wm/inbound/wmr22/grids/1")
	public int saveWmr22(@RequestBody RequestDTO<RecvDTO> param ,CommonDTO common) {
		return inboundService.saveWmr22ItemList(param, common);
	}


	/*  getWmr30Init - 기타입고 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: Map<String, Object> - 호출한 데이터를 담을 Map
	*   설명			: 기타입고 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@GetMapping(value = "/wm/inbound/wmr30/init/1")
	public Map<String, Object> getWmr30Init(@ModelAttribute CommonDTO common) {
		return inboundService.getWmr30Init(common);
	}

	/*  getWmr30BoxQty - 기타입고/반품등록의 박스수량 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: MskuwcDTO - 부품DTO
	*   출력 PARAMETA	: List<MskuwcDTO> - 부품DTO 리스트
	*   설명			: 기타입고/반품등록 페이지에서 등록수량 입력시 해당 SKU에 해당하는 packkey와 truntyp으로 박스수량 갱신
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@GetMapping(value = "/wm/inbound/boxqty")
	public List<MskuwcDTO> getWmr30BoxQty(@ModelAttribute MskuwcDTO param) {
		return inboundService.getInboundBoxQty(param);
	}

	/*  saveWmr30List - 기타입고등록
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<RecvEtcDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int - 성공시 1 반환
	*   설명			: 기타입고등록페이지에서 기타입고를 실행하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@PostMapping(value = "/wm/inbound/wmr30/grids/1")
	public int savewmr30(@RequestBody RequestDTO<RecvEtcDTO> param, CommonDTO common) {
		return inboundService.saveWmr30List(param, common);
	}

	/*  getWmr40Init - 반품등록 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	:  Map<String, Object> - 호출한 데이터를 담을 Map
	*   설명			: 반품등록 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@GetMapping(value = "/wm/inbound/wmr40/init/1")
	public  Map<String, Object> getWmr40Init(@ModelAttribute CommonDTO common) {
		return inboundService.getWmr40Init(common);
	}
	
	/*  getWmr40List - 회수오더 리스트 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvEtcDTO - 기타입고/반품등록 DTO
	*   출력 PARAMETA	: List<RecvEtcDTO> - 기타입고/반품등록 DTO의 리스트
	*   설명			: 반품등록 화면에서 화주선택시 회수오더리스트 호출
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@GetMapping(value = "/wm/inbound/wmr40/grids/1")
	public List<RecvEtcDTO> getWmr40List(@ModelAttribute RecvEtcDTO param){
		return inboundService.getWmr40List(param);
	}
	

	/*  saveWmr40List - 반품등록
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<RecvEtcDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int - 성공시 1 반환
	*   설명			: 반품등록페이지에서 반품등록을 실행하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@PostMapping(value = "/wm/inbound/wmr40/grids/1")
	public int saveWmr40(@RequestBody RequestDTO<RecvEtcDTO> param, CommonDTO common) {
		return inboundService.saveWmr40List(param, common);
	}

	/*  getWmr50Init - 팔렛타이징처리 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvDTO - 입하DTO
	*   출력 PARAMETA	: Map<String, Object> - 데이터를 담을 Map
	*   설명			: 팔렛타이징처리 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@GetMapping(value = "/wm/inbound/wmr50/init/1")
	public Map<String, Object> getWmr50Init(@ModelAttribute RecvDTO recvDTO){
		return inboundService.getWmr50Init(recvDTO);
	}

	/*  getWmr50Packqty - 팔렛타이징 입수량 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvDTO - 입하DTO
	*   출력 PARAMETA	: List<RecvDTO> - 입하DTO목록
	*   설명			: 팔렛타이징처리 페이지에서 팔렛타이징 버튼 클릭시 해당 재고의 입수량을 호출하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@GetMapping(value = "/wm/inbound/wmr50/packqty")
	public List<RecvDTO> getWmr50Packqty(@ModelAttribute RecvDTO param){
		return inboundService.getWmr50Packqty(param);
	}

	/*  getWmr50Pakblky - 팔렛타이징 id 채번
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: Map<String, String> param
	*   출력 PARAMETA	: Map<String, String> - 채번한 id를 출력할 Map
	*   설명			: 팔렛타이징처리 페이지에서 팔렛타이징 버튼 클릭시 팔렛타이징 id를 채번하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@GetMapping(value = "/wm/inbound/wmr50/pakblky")
	public Map<String, String> getWmr50Pakblky(Map<String, String> param) {
		return inboundService.getWmr50Pakblky(param);
	}

	/*  getWmr50List - 팔렛타이징처리 리스트 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvDTO - 입하 DTO
	*   출력 PARAMETA	: List<RecvDTO> - 입하 DTO의 리스트
	*   설명			: 팔렛타이징처리 페이지에서 적치되지 않은 재고 목록을 보여주는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@GetMapping(value = "/wm/inbound/wmr50/grids/1")
	public List<RecvDTO> getWmr50List(@ModelAttribute RecvDTO param){
		return inboundService.getWmr50List(param);
	}

	/*  saveWmr50 - 팔렛타이징 처리 저장
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<RecvPutDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int - 성공시 1 반환
	*   설명			: 팔렛타이징처리 페이지에서 팔렛타이징 처리된 재고를 저장하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@PostMapping(value = "/wm/inbound/wmr50/grids/1")
	public int saveWmr50List(@RequestBody RequestDTO<RecvPutDTO> param, CommonDTO common) {
		return inboundService.saveWmr50(param, common);
	}

	/*  saveWmr50Cancel - 팔렛타이징 처리 취소
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<RecvPutDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int - 성공시 0 반환
	*   설명			: 팔렛타이징처리 페이지에서 팔렛타이징 처리된 재고에 대해 팔렛타이징을 취소하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@PostMapping(value = "/wm/inbound/wmr50/grids/2")
	public int saveWmr50Cancel(@RequestBody RequestDTO<RecvPutDTO> param, CommonDTO common) {
		return inboundService.saveWmr50Cancel(param, common);
	}

	/*  getWmr60Init - 적치처리 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvPutDTO - 적치DTO
	*   출력 PARAMETA	: Map<String, Object> - 호출한 데이터를 담을 Map
	*   설명			: 적치처리 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@GetMapping(value = "/wm/inbound/wmr60/init/1")
	public Map<String, Object> getWmr60Init(@ModelAttribute RecvPutDTO recvPutDTO) {
		return inboundService.getWmr60Init(recvPutDTO);
	}

	/*  getWmr60List - 적치처리 리스트 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvPutDTO - 적치 DTO
	*   출력 PARAMETA	: List<RecvPutDTO> - 적치 DTO의 리스트
	*   설명			: 적치처리 페이지에서 입하 후 적치되지 않은 재고 목록을 보여주는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@GetMapping(value = "/wm/inbound/wmr60/grids/1")
	public List<RecvPutDTO> getWmr60List(@ModelAttribute RecvPutDTO param) {
		return inboundService.getWmr60List(param);
	}

	/*  saveWmr60List - 적치처리
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<RecvPutDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int - 성공시 0 반환
	*   설명			: 적치처리 페이지에서 적치되지 않은 재고를 적치하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@PostMapping(value = "/wm/inbound/wmr60/grids/1")
	public int saveWmr60List(@RequestBody RequestDTO<RecvPutDTO> param, CommonDTO common) {
		return inboundService.saveWmr60List(param, common);
	}

	/*  getWmr61Init - 적치조회 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvPutDTO - 적치DTO
	*   출력 PARAMETA	: Map<String, Object> resultMap - 호출한 데이터를 담기위한 Map
	*   설명			: 적치조회 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@GetMapping(value = "/wm/inbound/wmr61/init/1")
	public Map<String, Object> getWmr61Init(@ModelAttribute RecvPutDTO recvPutDTO) {
		return inboundService.getWmr61Init(recvPutDTO);
	}

	/*  getWmr61List - 적치조회 리스트 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvPutDTO - 적치 DTO
	*   출력 PARAMETA	: List<RecvPutDTO> - 적치 DTO의 리스트
	*   설명			: 적치조회페이지에서 적치된 재고를 호출하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@ResponseBody
	@GetMapping(value = "/wm/inbound/wmr61/grids/1")
	public List<RecvPutDTO> getWmr61List(@ModelAttribute RecvPutDTO param) {
		return inboundService.getWmr61List(param);
	}

	/*  saveWmr61List - 적치취소
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<RecvPutDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int - 성공시 0 반환
	*   설명			: 적치조회 페이지에서 적치된 재고의 적치를 취소하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@PostMapping(value = "/wm/inbond/wmr61/grids/1")
	public int saveWmr61List(@RequestBody RequestDTO<RecvPutDTO> param, CommonDTO common) {
		return inboundService.saveWmr61List(param, common);
	}

	/*  getWmr62Init - 적치지시 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvPutDTO - 적치DTO
	*   출력 PARAMETA	: Map<String, Object> - 호출한 데이터를 담을 Map
	*   설명			: 적치지시 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@GetMapping(value = "/wm/inbound/wmr62/init/1")
	public Map<String, Object> getWmr62Init(@ModelAttribute RecvPutDTO recvPutDTO) {
		return inboundService.getWmr62Init(recvPutDTO);
	}

	/*  getWmr62List - 적치지시 리스트 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvPutDTO - 적치 DTO
	*   출력 PARAMETA	: List<RecvPutDTO> - 입하 DTO의 리스트
	*   설명			: 적치지시페이지에서 입하등록되고 적치되지 않은 재고 목록을 보여주는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@GetMapping(value = "/wm/inbound/wmr62/grids/1")
	public List<RecvPutDTO> getWmr62List(@ModelAttribute RecvPutDTO param) {
		return inboundService.getWmr62List(param);
	}

	/*  saveWmr62List - 적치지시
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<TaskDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: Map<String, String> - 적치지시 후 적치지시서 출력을 위해 채번된 작업번호를 담을 Map
	*   설명			: 적치지시페이지에서 입하된 후 적치되지 않은 재고의 적치를 지시하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@PostMapping(value = "/wm/inbound/wmr62/grids/1")
	public Map<String, String> saveWmr62List(@RequestBody RequestDTO<TaskDTO> param, CommonDTO common) {
		return inboundService.saveWmr62List(param, common);
	}

	/*  getWmr63Init - 적치완료 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvPutDTO - 적치DTO
	*   출력 PARAMETA	: Map<String, Object> resultMap - 호출한 데이터를 담을 Map
	*   설명			: 적치완료 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@GetMapping(value = "/wm/inbound/wmr63/init/1")
	public Map<String, Object> getWmr63Init(@ModelAttribute RecvPutDTO recvPutDTO) {
		return inboundService.getWmr63Init(recvPutDTO);
	}

	/*  getWmr63List - 적치완료 리스트 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvPutDTO - 적치 DTO
	*   출력 PARAMETA	: List<RecvPutDTO> - 적치 DTO의 리스트
	*   설명			: 적치완료 페이지에서 적치지시가 내려진 재고 목록을 보여주는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@GetMapping(value = "/wm/inbound/wmr63/grids/1")
	public List<RecvPutDTO> getWmr63List(@ModelAttribute RecvPutDTO param) {
		return inboundService.getWmr63List(param);
	}

	/*  saveWmr63Cancel - 적치취소
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<TaskDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int - 성공시 0 반환
	*   설명			: 적치지시가 내려진 재고의 적치를 취소하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@PostMapping(value = "/wm/inbound/wmr63/grids/1")
	public int saveWmr63Cancel(@RequestBody RequestDTO<TaskDTO> param, CommonDTO common) {
		return inboundService.saveWmr63CancelList(param, common);
	}

	/*  saveWmr63List - 적치완료
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<TaskDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int returnData - 성공시 1 반환
	*   설명			: 적치지시가 내려진 재고의 적치를 완료하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	@PostMapping(value = "/wm/inbound/wmr63/grids/2")
	public int saveWmr63List(@RequestBody RequestDTO<TaskDTO> param, CommonDTO common) {
		return inboundService.saveWmr63List(param, common);
	}

}
