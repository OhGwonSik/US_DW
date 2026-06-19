package com.logistics.md.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.common.dto.CommonDTO;
import com.logistics.md.domain.MdmAccountCustomerDTO;
import com.logistics.md.domain.MdmSkuDTO;
import com.logistics.md.domain.MaremaDTO;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.domain.MdocmaDTO;
import com.logistics.md.domain.MlocmaDTO;
import com.logistics.md.domain.MowrmaDTO;
import com.logistics.md.domain.MpakmaDTO;
import com.logistics.md.domain.MptnmaDTO;
import com.logistics.md.domain.MrscmaDTO;
import com.logistics.md.domain.MskuwcDTO;
import com.logistics.md.domain.MtutmaDTO;
import com.logistics.md.domain.MuommaDTO;
import com.logistics.md.domain.MwarmaDTO;
import com.logistics.md.domain.MzonmaDTO;
import com.logistics.md.service.MDC06Service;
import com.logistics.md.service.MDC07Service;
import com.logistics.md.service.MDO02Service;
import com.logistics.md.service.MDO03Service;
import com.logistics.md.service.MDO04Service;
import com.logistics.md.service.MDO05Service;
import com.logistics.md.service.MDP10Service;
import com.logistics.md.service.MDP11Service;
import com.logistics.md.service.MDP21Service;
import com.logistics.md.service.MDU01Service;
import com.logistics.md.service.MDU02Service;
import com.logistics.md.service.MDU03Service;
import com.logistics.md.service.MDU04Service;
import com.logistics.md.service.MDU05Service;

import lombok.RequiredArgsConstructor;

/*
*   클래스명 - CommonController
*   최초생성일시	: 2023.07
*   최초생성자 : Park T. S.
*   설명 : 공통 data(select box 등) 컨트롤러 클래스
*/

@RestController
@RequiredArgsConstructor
@RequestMapping("/md/common")
public class CommonController { // 공통 컨트롤러(Select Box)
	// organization
	private final MDO02Service mdo02Service; // 창고
	private final MDO03Service mdo03Service; // 에어리어
	private final MDO04Service mdo04Service; // 존
	private final MDO05Service mdo05Service; // 로케이션

	// code
	private final MDC06Service mdc06Service; // 사유코드
	private final MDC07Service mdc07Service; // 공통코드

	// partner
	private final MDP10Service mdp10Service; // 화주매핑
	private final MDP11Service mdp11Service; // 화주등록
	private final MDP21Service mdp21Service; // 거래처등록

	// unit
	private final MDU01Service mdu01Service; // SKU수신
	private final MDU02Service mdu02Service; // SKU관리
	private final MDU03Service mdu03Service; // 단위
	private final MDU04Service mdu04Service; // 이동용기
	private final MDU05Service mdu05Service; // 입수량 관리

	// ********************************************//
	// *****************-MDO02-*****************//
	// *******************************************//
	// 창고 SelectBox 조회
	@GetMapping("/organization/warehouse")
	public List<CommonDTO> getWarehouseSelectBox(@ModelAttribute MwarmaDTO params) {
		return mdo02Service.getWarehouseSelectBox(params);
	}

	// 사용자별 창고 SelectBox 조회
	@GetMapping("/organization/warehouse-user")
	public List<CommonDTO> getWarehouseUserSelectBox(@ModelAttribute MwarmaDTO params) {
		return mdo02Service.getWarehouseUserSelectBox(params);
	}

	// ********************************************//
	// *****************-MDO03-*****************//
	// *******************************************//
	// 에어리어 SelectBox 조회
	@GetMapping("/organization/area")
	public List<CommonDTO> getAreaSelectBox(@ModelAttribute MaremaDTO params) {
		return mdo03Service.getAreaSelectBox(params);
	}

	// ********************************************//
	// *****************-MDO04-*****************//
	// *******************************************//
	// Zone SelectBox 조회
	@GetMapping("/organization/zone")
	public List<CommonDTO> getZoneSelectBox(@ModelAttribute MzonmaDTO params) {
		return mdo04Service.getZoneSelectBox(params);
	}

	// ********************************************//
	// *****************-MDO05-*****************//
	// *******************************************//
	// 로케이션 SelectBox 조회(다중건)
	@GetMapping("/organization/location/multi")
	public List<CommonDTO> getMultiLocationSelectBox(@ModelAttribute MlocmaDTO params) {
		return mdo05Service.getMultiLocationSelectBox(params);
	}

	// 로케이션 SelectBox 조회(단건)
	@GetMapping("/organization/location/single")
	public List<MlocmaDTO> getSingleLocationSelectBox(@ModelAttribute MlocmaDTO params) {
		return mdo05Service.getSingleLocationSelectBox(params);
	}

	// ********************************************//
	// *****************-MDC06-*****************//
	// *******************************************//
	// doccate SelectBox
	@GetMapping("/code/doccate")
	public List<MdocmaDTO> getDoccateSelectBox(@ModelAttribute MdocmaDTO params) {
		return mdc06Service.getDoccateGroupListSelect(params);
	}

	// doctype SelectBox
	@GetMapping("/code/doctype")
	public List<MdocmaDTO> getDoctypeSelectBox(@ModelAttribute MdocmaDTO params) {
		return mdc06Service.getDoctypeList(params);
	}

	// reason code SelectBox
	@GetMapping("/code/reason-code")
	public List<CommonDTO> getReasonCodeSelectBox(@ModelAttribute MrscmaDTO params) {
		return mdc06Service.getReasonCodeSelectBox(params);
	}

	// ********************************************//
	// *****************-MDC07-*****************//
	// *******************************************//
	// common code SelectBox 조회
	@GetMapping("/code/common-code")
	public List<McodemDTO> getCommonCodeSelectBox(@ModelAttribute McodemDTO params) {
		return mdc07Service.getCommonCodeSelectBox(params);
	}

	// ********************************************//
	// *****************-MDP11-*****************//
	// *******************************************//
	// Owner SelectBox
	@GetMapping("/partner/owner")
	public List<CommonDTO> getOwnerSelectBox(@ModelAttribute MowrmaDTO params) {
		return mdp11Service.getOwnerSelectBox(params);
	}

	// ********************************************//
	// *****************-MDP21-*****************//
	// *******************************************//
	// Partner(vender) SelectBox 조회
	@GetMapping("/partner/vender")
    public List<CommonDTO> getVenderSelectBox(@ModelAttribute MptnmaDTO params) {
        return mdp21Service.getVenderSelectBox(params);
    }

	// Partners SelectBox 조회
	@GetMapping("/partner/partners")
    public List<CommonDTO> getPartnerSelectBox(@ModelAttribute MptnmaDTO params) {
		return mdp21Service.getPartnerSelectBox(params);
    }

	// ********************************************//
	// *****************-MDU03-*****************//
	// *******************************************//
    // Unit of measure SelectBox 조회
	@GetMapping("/unit/uom")
	public List<CommonDTO> getUnitOfMeasureSelectBox(@ModelAttribute MuommaDTO params) {
    	return mdu03Service.getUnitOfMeasureSelectBox(params);
    }

	// ********************************************//
	// *****************-MDU04-*****************//
	// *******************************************//
	// 이동용기 SelectBox 조회
	@GetMapping("/unit/transfer")
	public List<CommonDTO> getTransferUnitSelectBox(@ModelAttribute MtutmaDTO params) {
    	return mdu04Service.getTransferUnitSelectBox(params);
    }

	// ********************************************//
	// *****************-MDU05-*****************//
	// *******************************************//
	// pack master & transfer unit type SelectBox 조회
	@GetMapping("/unit/pack-transfer")
	public List<CommonDTO> getPackTransferSelectBox(@ModelAttribute MpakmaDTO params) {
    	return mdu05Service.getPackTransferSelectBox(params);
    }

	// packBox SelectBox 조회
	@GetMapping("/unit/pack")
	public List<CommonDTO> getPackBoxSelectBox(@ModelAttribute MpakmaDTO params) {
    	return mdu05Service.getPackBoxSelectBox(params);
    }

	//pack Relation 2023.09.15 최강호
	@GetMapping("/unit/pack-relation")
	public List<MpakmaDTO> getPackRelationSelectBox(@ModelAttribute MpakmaDTO params){
		return mdu05Service.selectMpakmaRelations(params);
	}

	// ********************************************//
	// ******************-MDU02-****************//
	// *******************************************//
    // Skuwc SelectBox 조회 (OM 호출용 / SKU명 + 수량)
	@GetMapping("/unit/skuwc-qty")
	public List<MskuwcDTO> getSkuwcSelectBoxQty(@ModelAttribute MskuwcDTO params) {
		return mdu02Service.getSkuwcSelectBoxQty(params);
    }

	// Skuwc SelectBox 조회
	@GetMapping("/unit/skuwc")
	public List<MskuwcDTO> skuwcSelectBox(@ModelAttribute MskuwcDTO params) {
		return mdu02Service.getSkuwcSelectBox(params);
    }

	// Skuwc SelectBox 속성값 포함 조회(parameter 다수 포함(length 이슈로 post method 사용) ( 공장별 )
    @PostMapping("/unit/skuwc")
    public List<MskuwcDTO> skuwcSelectBoxPost(@ModelAttribute MskuwcDTO params){
    	return mdu02Service.getSkuwcSelectBox(params);
    }

    // Skuwc SelectBox 속성값 포함 조회 ( OMS2 주문등록 화면용 )
    @PostMapping("/unit/skuwc-oms2")
    public List<MskuwcDTO> skuwcOms2SB(@ModelAttribute MskuwcDTO params){
    	return mdu02Service.getSkuwcOms2SB(params);
    }

    // Skuwc SelectBox 조회 (OMB1)
	@GetMapping("/unit/skuwc-omb1")
	public List<MskuwcDTO> skuwcSelectBoxOmb1(@ModelAttribute MskuwcDTO params) {
		return mdu02Service.getSkuwcSelectBoxOmw1(params);
    }

    @PostMapping("/unit/skuwc-omw1")
    public List<MskuwcDTO> skuwcSelectBoxOmb1Post(@ModelAttribute MskuwcDTO params){
    	return mdu02Service.getSkuwcSelectBoxOmw1(params);
    }

	// combo text 선택 시 화면에서 다른 컬럼의 value 값 자동 변경
    @GetMapping("/unit/skuwc-warekey")
    public MskuwcDTO skuwcSelectBoxByWarekey(@ModelAttribute MskuwcDTO params) {
    	return mdu02Service.getSkucSelectBoxByWarekey(params);
    }

    @GetMapping("/unit/skuwc-outbound")
    public List<MskuwcDTO> skuwcSelectBoxtByOutbound(@ModelAttribute MskuwcDTO params) {
    	return mdu02Service.getSkucSkuwcBoxByOutbound(params);
    }

    // Skuwc SelectBox(WMR30/40 기타입고 및 반품등록)
    @GetMapping("/unit/skuwc-inbound")
    public List<MskuwcDTO> skuwcSelectBoxtByInbound(@ModelAttribute MskuwcDTO params){
    	return mdu02Service.getSkuSelectBoxByInbound(params);
    }

	// ********************************************//
	// **-MDM ACCUPT + CUSTTB + PARTTB-**//
	// *******************************************//
	// MDM SKU List 조회
	@GetMapping("/units/mdm")
	public List<MdmSkuDTO> getMDMSkuList(@ModelAttribute MdmSkuDTO params){
		return mdu01Service.getMDMSkuList(params);
	}

	// ********************************************//
	// **************-MDM CUSTTB-*************//
	// *******************************************//
	// MDM 협력사 데이터
	@GetMapping("/partner/mdm")
	public List<MdmAccountCustomerDTO> getMDMOwnerList(@ModelAttribute MdmAccountCustomerDTO params) {
		return mdp10Service.getMDMOwnerList(params);
	}
}
