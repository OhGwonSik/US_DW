package com.logistics.bm.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.bm.domain.BillingDTO;
import com.logistics.bm.service.BillingService;
import com.logistics.common.dto.InitDataDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.sy.domain.UserVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bm/billing")
public class BillingController {
	private final BillingService billingService;
	
	//=========================================================//
	//==========================bmb01==========================//
	//=========================================================//
	// init
	@GetMapping("/bmb01/init")
	public InitDataDTO getBmb01Init(@AuthenticationPrincipal UserVO userInfo){
		return billingService.getBmb01init(userInfo);
	}
	
	// 그리드 조회
	@GetMapping("/bmb01/grids/1")
	public List<BillingDTO> getBmb01GridList(@ModelAttribute BillingDTO param){
		return billingService.getBmb01List(param);
	}
	
	// 마감
	@PostMapping("/bmb01/grids/1")
	public int finishBmb01List(@RequestBody BillingDTO params) {
		return billingService.finishBmb01(params);
	}

	//=========================================================//
	//==========================bmb02==========================//
	//=========================================================//
	// init
	@GetMapping("/bmb02/init")
	public InitDataDTO getBmb02Init(BillingDTO param){
		return billingService.getBmb02Init(param);
	}

	// 물류비 검증 head list 
	@GetMapping("/bmb02/grid-head")
	public List<BillingDTO> getBmb02HeadListSelect(BillingDTO params){
		return billingService.getBmb02HeadListSelect(params);
	}
	
	// 물류비 검증 item list 	
	@GetMapping("/bmb02/grid-item")
	public List<BillingDTO> getBmb02ItemListSelect(BillingDTO params){
		return billingService.getBmb02ItemList(params);
	}
	
	// 기타비용 insert 및 검증완료 procedure 호출
	@PostMapping("/bmb02/verify")
	public int modifyBmb02ForVerify(@RequestBody BillingDTO data){
		return billingService.modifyBmb02ForVerify(data);
	}

	// 마감취소 procedure 호출
	@PostMapping("/bmb02/cancel-deadline")
	public int modifyBmb02ForCancelDeadLine(@RequestBody BillingDTO data){
		return billingService.modifyBmb02ForCancelDeadLine(data);
	}
	
	//=========================================================//
	//==========================bmb03==========================//
	//=========================================================//
	@GetMapping("/bmb03/init")
	public InitDataDTO getBmb03Init(BillingDTO param){
		return billingService.getBmb03Init(param);
	}

	@GetMapping("/bmb03/grid-head")
	public List<BillingDTO> getBmb03HeadListSelect(BillingDTO param){
		return billingService.getBmb03HeadListSelect(param);
	}

	@GetMapping("/bmb03/grid-item")
	public List<BillingDTO> getBmb03ItemListSelect(BillingDTO param){
		return billingService.getBmb03ItemListSelect(param);
	}

	@PostMapping("/bmb03/confirm")
	public int saveBmb03Data(@RequestBody RequestDTO<BillingDTO> requestDTO) {
		return billingService.saveBmb03Data(requestDTO);
	}

	//=========================================================//
	//==========================bmb04==========================//
	//=========================================================//

	//init
	@GetMapping("/bmb04/init")
	public InitDataDTO getBmb04Init(BillingDTO param){
		return billingService.getBmb04Init(param);
	}

	@GetMapping("/bmb04/grid-head")
	public List<BillingDTO> getBmb04HeadListSelect(BillingDTO param){
		return billingService.getBmb04HeadListSelect(param);
	}

	@GetMapping("/bmb04/grid-item")
	public List<BillingDTO> getBmb04ItemListSelect(BillingDTO param){
		return billingService.getBmb04ItemListSelect(param);
	}
	
	//=========================================================//
	//==========================bmb05==========================//
	//=========================================================//
	
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
	@GetMapping("/bmb05/init")
	public InitDataDTO getBmb05Init(@ModelAttribute BillingDTO param){
		return billingService.getBmb05Init(param);
	}
	
	/*  getBmb05ListSelect - BMB05운송비정산처리 그리드 리스트 데이터 조회
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
	@GetMapping("/bmb05/grids/1")
	public List<BillingDTO> getBmb05ListSelect(@ModelAttribute BillingDTO param){
		return billingService.getBmb05List(param);
	}
	
	/*  finishBmb05List - BMB05운송비정산처리 화면에서 마감 procedure를 호출
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
	@PostMapping("/bmb05/grids/1")
	public int finishBmb05List(@RequestBody BillingDTO params) {
		return billingService.finishBmb05(params);
	}
	
	//=========================================================//
	//==========================bmb06==========================//
	//=========================================================//
	
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
	@GetMapping("/bmb06/init")
	public InitDataDTO getBmb06Init(BillingDTO param){
		return billingService.getBmb06Init(param);
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
	@PostMapping("/bmb06/verify")
	public int modifyBmb06ForVerify(@RequestBody BillingDTO data){
		return billingService.modifyBmb06ForVerify(data);
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
	@PostMapping("/bmb06/cancel-deadline")
	public int modifyBmb06ForCancelDeadLine(@RequestBody BillingDTO data){
		return billingService.modifyBmb06ForCancelDeadLine(data);
	}
	
	/*  getBmb06HeadListSelect - BMB06운송비검증처리 화면의 그리드 헤더 데이터를 조회
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
	@GetMapping("/bmb06/grid-head")
	public List<BillingDTO> getBmb06HeadListSelect(@ModelAttribute BillingDTO param){
		return billingService.getBmb06HeadList(param);
	}
	
	/*  getBmb06ItemListSelect - BMB06운송비검증처리 화면의 그리드 아이템 데이터를 조회
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
	@GetMapping("/bmb06/grid-item")
	public List<BillingDTO> getBmb06ItemListSelect(@ModelAttribute BillingDTO param){
		return billingService.getBmb06ItemList(param);
	}
	
	//=========================================================//
	//==========================bmb07==========================//
	//=========================================================//
	
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
	@GetMapping("/bmb07/init")
	public InitDataDTO getBmb07Init(@ModelAttribute BillingDTO param){
		return billingService.getBmb07Init(param);
	}
	
	/*  getBmb07HeadListSelect - BMB07운송비정산확정 화면의 그리드 헤더 데이터를 조회
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
	@GetMapping("/bmb07/grid-head")
	public List<BillingDTO> getBmb07HeadListSelect(@ModelAttribute BillingDTO param){
		return billingService.getBmb07HeadList(param);
	}

	/*  getBmb07ItemListSelect - BMB07운송비정산확정 화면의 그리드 아이템 데이터를 조회
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
	@GetMapping("/bmb07/grid-item")
	public List<BillingDTO> getBmb07ItemListSelect(@ModelAttribute BillingDTO param){
		return billingService.getBmb07ItemList(param);
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
	@PostMapping("/bmb07/confirm")
	public int saveBmb07Data(@RequestBody RequestDTO<BillingDTO> requestDTO) {
		return billingService.saveBmb07Data(requestDTO);
	}
	
	//=========================================================//
	//==========================bmb08==========================//
	//=========================================================//
	
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
	@GetMapping("/bmb08/init")
	public InitDataDTO getBmb08Init(@ModelAttribute BillingDTO param){
		return billingService.getBmb08Init(param);
	}
	
	/*  getBmb08HeadListSelect - BMB08운송정산내역조회 화면의 그리드 헤더 데이터를 조회
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
	@GetMapping("/bmb08/grid-head")
	public List<BillingDTO> getBmb08HeadListSelect(@ModelAttribute BillingDTO param){
		return billingService.getBmb08HeadList(param);
	}

	/*  getBmb08ItemListSelect - BMB08운송정산내역조회 화면의 그리드 아이템 데이터를 조회
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
	@GetMapping("/bmb08/grid-item")
	public List<BillingDTO> getBmb08ItemListSelect(@ModelAttribute BillingDTO param){
		return billingService.getBmb08ItemList(param);
	}
	
	//=========================================================//
	//==========================bmb09==========================//
	//=========================================================//
	/*  getBmb09HeadListSelect - BMB09차량별정산내역조회 화면의 그리드 헤더 데이터를 조회
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
	@GetMapping("/bmb09/grid-head")
	public List<BillingDTO> getBmb09HeadListSelect(@ModelAttribute BillingDTO param){
		return billingService.getBmb09HeadList(param);
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
	@GetMapping("/bmb09/init")
	public InitDataDTO getBmb09Init(@ModelAttribute BillingDTO param){
		return billingService.getBmb09Init(param);
	}
	
	/*  getBmb09ItemListSelect - BMB09차량별정산내역조회 화면의 그리드 아이템 데이터를 조회
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
	@GetMapping("/bmb09/grid-item")
	public List<BillingDTO> getBmb09ItemListSelect(@ModelAttribute BillingDTO param){
		return billingService.getBmb09ItemList(param);
	}
	
	//=========================================================//
	//==========================bmb10==========================//
	//=========================================================//
	
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
	@GetMapping("/bmb10/init")
	public InitDataDTO getBmb10Init(@ModelAttribute BillingDTO param){
		return billingService.getBmb10Init(param);
	}
	
	/*  saveBmb10Data - BMB10 차량별공제비등록 데이터 저장
	*   최초 생성일시   : 2023-12-12 10:30
	*   최초 생성자      : 고은별
	*   입력 PARAMETER : RequestDTO<BillingDTO> requestDTO - 저장할 데이터를 담은 DTO
	*   출력 PARAMETER : int - 처리 결과
	*   설명      	: BMB10화면의 데이터를 저장.
	*   수정 내역  	:
	*   수정일시		:
	*   수정자		:
	*   변경 사항		:
	*/
	@PostMapping("/bmb10/confirm")
	public int saveBmb10Data(@RequestBody RequestDTO<BillingDTO> requestDTO) {
		return billingService.saveBmb10Data(requestDTO);
	}
	
}
