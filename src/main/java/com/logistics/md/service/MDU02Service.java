package com.logistics.md.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.NoSaveDataException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.MskuwcDTO;
import com.logistics.md.mapper.UnitsMapper;
import com.logistics.sy.domain.UserVO;

import lombok.RequiredArgsConstructor;

/*
*   클래스명 : MDU02Service
*   최초생성일시	: 2023.07.21
*   최초생성자 : Park T. S.
*   설명 : 부품등록 서비스 클래스
*   ---------------------------------
*   수정일시 : 2023.12.21
*   수정자 : Park T. S.
*   내용 : 부품코드 활성화 validation 로직 추가
*/

@Service
@RequiredArgsConstructor
public class MDU02Service {
	private final UnitsMapper unitsMapper;
	private final MessageSource msg;

	// 부품 리스트 조회
	public List<MskuwcDTO> getMdu02List(MskuwcDTO params) {
		return unitsMapper.selectMskuwcList(params);
	}

	// 등록된 부품의 단위와 vender 조회
	public List<MskuwcDTO> getMskuwcUomAndSvend(MskuwcDTO params) {
		return unitsMapper.selectMskuwcUomAndSvend(params);
	}

	// mdu02 부품 데이터 저장
	public int saveMdu02(RequestDTO<MskuwcDTO> saveList) {
		if(saveList == null) {
			throw new NoSaveDataException();
		}

		String exceptionMsg = msg.getMessage("ms.validation.useOnlyOneKeyOnWarehouse", null, LocaleContextHolder.getLocale()).replace("{0}", msg.getMessage("md.mskuwc.skumkey", null, LocaleContextHolder.getLocale()));

		MskuwcDTO params = new MskuwcDTO();
		params.setUserData((UserVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		List<MskuwcDTO> mskuwcListForSkustat = this.getSkuForStat(params); // 창고-화주-sku별 skustat 개수를 얻기 위한 데이터

		// 검사할 리스트에 addList 추가
		if (saveList.getAddList() != null) {
			saveList.getAddList().stream().forEach(mskuwcListForSkustat::add);
		}

		// 검사할 리스트에 update 일치하는게 있다면 변경
		if(saveList.getUpdateList() != null) {
			for (MskuwcDTO updateData : saveList.getUpdateList()) {
				mskuwcListForSkustat.stream().forEach(dbData -> {
					if(dbData.getCompkey().equals(updateData.getCompkey()) && dbData.getWarekey().equals(updateData.getWarekey())
							&& dbData.getOwnerky().equals(updateData.getOwnerky()) && dbData.getSkumkey().equals(updateData.getSkumkey())){

						dbData.setSkustat(updateData.getSkustat());
					}
				});
			}
		}

		int insertCnt = 0;

		if (saveList.getAddList() != null) {
			for (MskuwcDTO insertData : saveList.getAddList()) {
				int skustatCnt = mskuwcListForSkustat.stream()
																		.filter(dbData -> dbData.getCompkey().equals(insertData.getCompkey()) && dbData.getWarekey().equals(insertData.getWarekey()) && dbData.getSkumkey().equals(insertData.getSkumkey()) && "PRODUCT".equals(dbData.getSkustat()))
																		.toList()
																		.size();

				// 창고내 사용되는 부품은 하나만 허용(skustatCnt)
				if (skustatCnt > 1) {
					throw new InsertCheckedException(exceptionMsg + " " + insertData.getSkumkey() + " / " + insertData.getSkudesc());
				}

				if (unitsMapper.insertMskuwc(insertData) == 0) {
					throw new InsertCheckedException();
				}

				insertCnt++;
			}
		}

		int updateCnt = 0;

		if(saveList.getUpdateList() != null && saveList.getOldList() != null) {
			for(int i=0; i<saveList.getUpdateList().size(); i++) {
				MskuwcDTO updateData = saveList.getUpdateList().get(i);
				MskuwcDTO oldData = saveList.getOldList().get(i);

				updateData.setOldWarekey(oldData.getWarekey());
				updateData.setOldOwnerky(oldData.getOwnerky());
				updateData.setOldSkumkey(oldData.getSkumkey());

				int skustatCnt = mskuwcListForSkustat.stream().filter(dbData -> dbData.getCompkey().equals(updateData.getCompkey()) && dbData.getWarekey().equals(updateData.getWarekey()) && dbData.getSkumkey().equals(updateData.getSkumkey()) && "PRODUCT".equals(dbData.getSkustat())).toList().size();

				 // 창고내 사용되는 부품은 하나만 허용(skustatCnt)
				if (skustatCnt > 1) {
					throw new UpdateCheckedException(exceptionMsg + " " + updateData.getSkumkey() + " / " + updateData.getSkudesc());
				}

				if (unitsMapper.updateMskuwc(updateData) == 0) {
					throw new UpdateCheckedException();
				}

				updateCnt++;
			}
		}

		return insertCnt + updateCnt;
	}

	// 부품 Select Box (SKU명 + 수량)
	public List<MskuwcDTO> getSkuwcSelectBoxQty(MskuwcDTO params) {
		if (params.getSkumkey() != null) {
			params.setSkumkeys(Arrays.asList(params.getSkumkey().split(",")));
			params.setSkumkey(null);
		}

		return unitsMapper.selectSkuwcBoxQty(params);
	}

	// 부품 Select Box
	public List<MskuwcDTO> getSkuwcSelectBox(MskuwcDTO params) {
		if (params.getSkumkey() != null) {
			params.setSkumkeys(Arrays.asList(params.getSkumkey().split(",")));
			params.setSkumkey(null);
		}

		return unitsMapper.selectSkuwcSelectBox(params);
	}

	// Skuwc Select Box (OMS2 주문등록 화면용)
	public List<MskuwcDTO> getSkuwcOms2SB(MskuwcDTO params) {
		if (params.getSkumkey() != null) {
			params.setSkumkeys(Arrays.asList(params.getSkumkey().split(",")));
			params.setSkumkey(null);
		}

		return unitsMapper.selectSkuwcOms2SB(params);
	}

	// Skuwc Select Box (OMW1 주문등록 화면용)
	public List<MskuwcDTO> getSkuwcSelectBoxOmw1(MskuwcDTO params) {
		if (params.getSkumkey() != null) {
			params.setSkumkeys(Arrays.asList(params.getSkumkey().split(",")));
			params.setSkumkey(null);
		}
		return unitsMapper.selectSkuwcBoxOmw1(params);
	}

	// 부품 Select Box(창고별)
	public MskuwcDTO getSkucSelectBoxByWarekey(MskuwcDTO params) {
		return unitsMapper.selectSkwucBoxByWarekey(params);
	}

	// 부품 Select Box(출고용)
	public List<MskuwcDTO> getSkucSkuwcBoxByOutbound(MskuwcDTO params) {
		return unitsMapper.selectSkuwcBoxCustom(params);
	}

	// 부품 Select Box(입고용)
	public List<MskuwcDTO> getSkuSelectBoxByInbound(MskuwcDTO params){
		return unitsMapper.selectInboundSkuwcBox(params);
	}

	// 창고-화주-부품 별 SKUSTAT 리스트
	public List<MskuwcDTO> getSkuForStat(MskuwcDTO params){
		return unitsMapper.selectMskuwcForSkustat(params);
	}
}
