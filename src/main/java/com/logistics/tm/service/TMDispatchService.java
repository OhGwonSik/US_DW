package com.logistics.tm.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.DeleteCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.domain.MptnmaDTO;
import com.logistics.md.service.MDC07Service;
import com.logistics.sy.domain.UserVO;
import com.logistics.tm.domain.TMDispatchDTO;
import com.logistics.tm.mapper.TMDispatchMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TMDispatchService {

	private final TMDispatchMapper tmDispatchMapper;
	
	private final MDC07Service mdc07Service;

	/*
	 * 프로그램 ID: TMA08, TMA10, TMA11, TMA12
	 * 프로그램 내용: 공통
	 */
	
	/*  getTmDispatchSearchList - 공통 헤드 그리드 조회
	*   최초 생성일시	: 2023-12-13 13:50
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMDispatchDTO - 배차관리 DTO
	*   출력 PARAMETA	: List<TMDispatchDTO> - 배차관리 DTO List
	*   설명			: 배차취소, 배차현황, 운송상태수정에서 공통으로 헤드 그리드에 들어갈 데이터를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* 배차 현황 데이터 */
	public List<TMDispatchDTO> getTmDispatchSearchList(TMDispatchDTO tmDispatch) {
		return tmDispatchMapper.selectTmDispatchList(tmDispatch);
	}
	
	/*  getTmDispatchSearchItemList - 공통 아이템 그리드 조회
	*   최초 생성일시	: 2023-12-13 13:50
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMDispatchDTO - 배차관리 DTO
	*   출력 PARAMETA	: List<TMDispatchDTO> - 배차관리 DTO List
	*   설명			: 배차취소, 배차현황에서 공통으로 아이템 그리드에 들어갈 데이터를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* 배차아이템 데이터 */
	public List<TMDispatchDTO> getTmDispatchSearchItemList(TMDispatchDTO tmDispatch) {
		return tmDispatchMapper.selectTmDispatchItem(tmDispatch);
	}
	
	/*  getTmDispatchInitData - 공통 Init Data
	*   최초 생성일시	: 2023-12-13 13:50
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMDispatchDTO - 배차관리 DTO
	*   출력 PARAMETA	: TMDispatchDTO - 배차관리 DTO
	*   설명			: 배차취소, 배차현황, 운송상태수정에서 사용되는 Init Data를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* 공통 init 데이터 */
	public TMDispatchDTO getTmDispatchInitData(TMDispatchDTO tmDispatch) {
		
		/* 공통 품목 리스트 */
		tmDispatch.setOcatmaList(tmDispatchMapper.selectTmDispatchOcatmaList(tmDispatch));
		
		UserVO userData = tmDispatch.getUserData();
		//운송상태
		McodemDTO mcodem = new McodemDTO();
		mcodem.setUserData(userData);
		mcodem.setComcdky("PLNSTAT");
		tmDispatch.setPlnstatCheckBoxes(mdc07Service.getCommonCodeSelectBox(mcodem));

		mcodem.setComcdky("SHTRSTS");
		tmDispatch.setShtrstsList(mdc07Service.getCommonCodeSelectBox(mcodem));
		
		return tmDispatch;
	}
	
	/*  getCarrierList - 운송사 List
	*   최초 생성일시	: 2023-12-13 13:50
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMDispatchDTO - 배차관리 DTO
	*   출력 PARAMETA	: List<MptnmaDTO> - 협력업체 마스터 테이블 DTO List
	*   설명			: 일별실적통계에서 검색조건에서 사용하는 운송사 SelectBox를 구성하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* 운송사 리스트 조회 */
	public List<MptnmaDTO> getCarrierList(TMDispatchDTO tmDispatch) {
		return tmDispatchMapper.selectCarrierList(tmDispatch);
	}

	/*
	 * 프로그램 ID: TMA08 프로그램 내용: 배차취소(수정)
	 */
	
	/*  getTma08DispatchDoctypeList - 배차타입 List(권역납품 제외)
	*   최초 생성일시	: 2023-12-13 13:50
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMDispatchDTO - 배차관리 DTO
	*   출력 PARAMETA	: List<TMDispatchDTO> - 배차관리 DTO List
	*   설명			: 배차취소에서 검색조건에서 사용되는 배차타입 권역납품을 제외한 SelectBox를 구성하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma08: 배차취소, 권역납품 제외 DOCTYPE LIST */
	public List<TMDispatchDTO> getTma08DispatchDoctypeList(TMDispatchDTO tmDispatch) {
		return tmDispatchMapper.selectDoctypeListWithout830(tmDispatch);
	}
	
	/*  deleteTma08SearchList - 배차 취소
	*   최초 생성일시	: 2023-12-13 13:50
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: RequestDTO, TMDispatchDTO - 그리드 List DTO / 배차관리 DTO
	*   출력 PARAMETA	: int
	*   설명			: 배차취소에서 배차취소시 동작하는 메소드
	*   수정 내역		:
	*   수정일시		: 2024. 1. 3.
	*	수정자		: 이재형
	*	변경 사항		: TPLNIT update/delete 및 ocosal update 시 각각 ky 값으로 각각 update/delete 하는 대신, saatc06 및 Cooutky / Vhplnky 키로 update/delete 실행
	*/
	/* tma08: 배차 취소 */
	public int deleteTma08SearchList(RequestDTO<TMDispatchDTO> tmDispatch) {
		TMDispatchDTO dispatchDTO = new TMDispatchDTO();

		int updateCnt = 0;
		int deleteCnt = 0;

		if (tmDispatch.getHeadGrid() != null) {
			// 헤더에서 요청된 데이터의 경우 -> 아이템 all delete, 및 PLNSTAT CANCEL UPDATE
			TMDispatchDTO headGrid = tmDispatch.getHeadGrid().getDeleteList().get(0);
			dispatchDTO.setUserData(headGrid.getUserData()); // userData 설정
			dispatchDTO.setVhplnky(headGrid.getVhplnky()); // 배차계획키 설정

			// 해당 헤더 운송상태업데이트
			if (tmDispatch.getItemGrid() != null) {
				// 아이템그리드가 존재하지 않을 경우 실행 x
				List<TMDispatchDTO> itemGrid = tmDispatch.getItemGrid().getDeleteList();
				for (int i = 0; i < itemGrid.size(); i++) {

					// 배차 취소 plt 기준으로 변경 후 적용 할 로직
					dispatchDTO.setUserData(itemGrid.get(i).getUserData());
					dispatchDTO.setPostdat(itemGrid.get(i).getPostdat()); // 헤더의 영업일
					dispatchDTO.setDestkey(itemGrid.get(i).getDestkey()); // 아이템의 도착지키
					dispatchDTO.setSaatc06(itemGrid.get(i).getSaatc06()); // 아이템의 팔레트키
					dispatchDTO.setCooutky(itemGrid.get(i).getCooutky());
					dispatchDTO.setTrnstat("NEW");
					
					updateCnt += tmDispatchMapper.updateOcosal(dispatchDTO);
				}
				// 배차계획키로 모든 아이템 삭제
				deleteCnt += tmDispatchMapper.deleteTplnitAll(dispatchDTO);
			}
			// 헤더 삭제
			deleteCnt += tmDispatchMapper.deleteTplnhd(dispatchDTO);
			// 헤더 상태만 업데이트할 경우 에러메세지 방지(배차아이템이 없으나 운송상태가 PLAN인 경우)
			// deleteCnt++;
		}else {
			// 배차 아이템만 삭제
			List<TMDispatchDTO> deleteList = tmDispatch.getDeleteList();

			for (int i = 0; i < deleteList.size(); i++) {

				// 배차 취소 plt 기준으로 변경 후 적용할 로직
				dispatchDTO.setUserData(deleteList.get(i).getUserData());
				dispatchDTO.setVhplnky(deleteList.get(i).getVhplnky()); // 배차헤더의 배차계획키
				dispatchDTO.setPostdat(deleteList.get(i).getPostdat()); // 배차헤더의 영업일
				dispatchDTO.setDestkey(deleteList.get(i).getDestkey()); // 배차 아이템의 도착지키
				dispatchDTO.setSaatc06(deleteList.get(i).getSaatc06()); // 배차 아이템의 팔레트 넘버
				dispatchDTO.setCooutky(deleteList.get(i).getCooutky()); //
				dispatchDTO.setTrnstat("NEW");

				updateCnt += tmDispatchMapper.updateOcosal(dispatchDTO);
				deleteCnt += tmDispatchMapper.deleteTplnit(dispatchDTO);

			}
			if(tmDispatchMapper.selectTmDispatchItem(dispatchDTO).isEmpty()) {
				deleteCnt += tmDispatchMapper.deleteTplnhd(dispatchDTO);
			}
		}
		if (updateCnt == 0) {
			throw new UpdateCheckedException();
		}
		if (deleteCnt == 0) {
			throw new DeleteCheckedException();
		}
		return deleteCnt;
	}

	/*
	 * 프로그램 ID: TMA10 프로그램 내용: 배차현황
	 */
	
	/*  updateTma10SearchList - 메모 저장(update)
	*   최초 생성일시	: 2023-12-13 13:50
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: RequestDTO, TMDispatchDTO - 그리드 List DTO / 배차관리 DTO
	*   출력 PARAMETA	: int
	*   설명			: 배차현황페이지에서 메모를 수정하고 저장할 때 동작하는 메소드
	*   수정 내역		:
	*   수정일시		: 2024. 1. 3.
	*	수정자		: 이재형
	*	변경 사항		: TPLNIT update/delete 및 ocosal update 시 각각 ky 값으로 각각 update/delete 하는 대신, saatc06 및 Cooutky / Vhplnky 키로 update/delete 실행
	*/
	/* tma10: 배차현황 메모 수정 */
	public int updateTma10SearchList(RequestDTO<TMDispatchDTO> tmDispatch) {
		List<TMDispatchDTO> updateList = tmDispatch.getUpdateList();
		int resultCnt = 0;
		for (int i = 0; i < updateList.size(); i++) {
			TMDispatchDTO memo = new TMDispatchDTO();
			memo.setUserData(updateList.get(i).getUserData()); // userData 설정
			memo.setTrnmemo(updateList.get(i).getTrnmemo()); // memo 설정
			memo.setVhplnky(updateList.get(i).getVhplnky()); // 배차계획키 설정
			memo.setUpdtchk(updateList.get(i).getUpdtchk()); // updtchk 설정
			resultCnt = tmDispatchMapper.updateTplnhdMemo(memo); // 메모수정
		}
		if (resultCnt == 0) {
			throw new UpdateCheckedException();
		}
		return resultCnt;
	}
	/*
	 * 프로그램 ID: TMA11 프로그램 내용: 운송상태수정
	 */
	
	/*  getTma11DestItemList - 아이템 그리드 조회
	*   최초 생성일시	: 2023-12-13 13:50
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMDispatchDTO - 배차관리 DTO
	*   출력 PARAMETA	: List<TMDispatchDTO> - 배차관리 DTO List
	*   설명			: 운송상태수정 페이지에서 아이템 그리드에 들어갈 데이터를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma11: 도착지별 배차계획아이템 데이터 */
	public List<TMDispatchDTO> getTma11DestItemList(TMDispatchDTO tmDispatch) {
		return tmDispatchMapper.selectTmDispatchDestList(tmDispatch);
	}
	
	/*  updateTma11SearchList - 운송상태수정(update)
	*   최초 생성일시	: 2023-12-13 13:50
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: RequestDTO, TMDispatchDTO - 그리드 List DTO / 배차관리 DTO
	*   출력 PARAMETA	: int
	*   설명			: 운송상태수정에서 체크된 데이터들을 수정해주는 메소드
	*   수정 내역		:
	*   수정일시		: 2024. 1. 3.
	*	수정자		: 이재형
	*	변경 사항		: TPLNIT update/delete 및 ocosal update 시 각각 ky 값으로 각각 update/delete 하는 대신, saatc06 및 Cooutky / Vhplnky 키로 update/delete 실행
	* 
	*   수정일시		: 2024. 1. 4.
	*	수정자		: 이재형
	*	변경 사항		: isEmpty 이전에 null check 추가
	*/
	/* tma11: 배차계획 및 배차 아이템 데이터 수정 */
	public int updateTma11SearchList(RequestDTO<TMDispatchDTO> tmDispatch, HttpServletRequest request) {
		String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
		int resultCnt = 0;
		
		if (tmDispatch.getHeadGrid() != null) {
			// 헤더 수정
			List<TMDispatchDTO> tplnhdUpdateList = tmDispatch.getHeadGrid().getUpdateList(); // 헤더 업데이트 리스트
			for (int i = 0; i < tplnhdUpdateList.size(); i++) {
				TMDispatchDTO tplnhd = new TMDispatchDTO();// 헤더객체
				
				tplnhd.setUserData(tplnhdUpdateList.get(i).getUserData()); // userData 설정
				tplnhd.setTrnmemo(tplnhdUpdateList.get(i).getTrnmemo()); // memo 설정
				

				if(tplnhdUpdateList.get(i).getStpitdt()==null || tplnhdUpdateList.get(i).getStpitdt().isEmpty()) {
					tplnhd.setStpitdt(" ");
				}else {
					tplnhd.setStpitdt(tplnhdUpdateList.get(i).getStpitdt()); // 운송시작일 설정
				}
				if(tplnhdUpdateList.get(i).getStpittm() == null || tplnhdUpdateList.get(i).getStpittm().isEmpty()) {
					tplnhd.setStpittm(" ");
				}else {
					tplnhd.setStpittm(tplnhdUpdateList.get(i).getStpittm()); // 운송시작시간 설정
				}
				if(tplnhdUpdateList.get(i).getTrfindt() == null || tplnhdUpdateList.get(i).getTrfindt().isEmpty()) {
					tplnhd.setTrfindt(" ");
				}else {
					tplnhd.setTrfindt(tplnhdUpdateList.get(i).getTrfindt()); // 운송종료일 설정
				}
				if(tplnhdUpdateList.get(i).getTrfintm().isEmpty()) {
					tplnhd.setTrfintm(" ");
				}else {
					tplnhd.setTrfintm(tplnhdUpdateList.get(i).getTrfintm()); // 운송종료시간 설정
				}
				tplnhd.setUpdtchk(tplnhdUpdateList.get(i).getUpdtchk()); // updtchk 설정
				tplnhd.setVhplnky(tplnhdUpdateList.get(i).getVhplnky()); // 배차계획키 설정
				tplnhd.setPlnstat(tplnhdUpdateList.get(i).getPlnstatOrigin()); // 헤더운행상태 설정
				tplnhd.setVhcsnam(tplnhdUpdateList.get(i).getVhcsnam()); // 차량키 변경 설정
				tplnhd.setPbilpay(tplnhdUpdateList.get(i).getPbilpay()); // 개별운송비 변경 설정
				if(tplnhdUpdateList.get(i).getPlnstatOrigin().equals("FINISH")) {
					// 도착지로 검색 전 헤더키의 모든 배차이이템리스트(헤더 상태변경 체크용)
					List<TMDispatchDTO> tplnitList = tmDispatchMapper.selectTplnitWithDestkey(tplnhd); // 헤더키, COMPKEY로 배차 아이템 조회
					for(int j = 0;j<tplnitList.size();j++) {
						TMDispatchDTO tplnit = new TMDispatchDTO(); // 아이템객체
						tplnit.setUserData(tplnhdUpdateList.get(0).getUserData());
						tplnit.setShtrsts("ARRIVAL");
						tplnit.setVhplnky(tplnitList.get(j).getVhplnky());
						tplnit.setVhplnit(tplnitList.get(j).getVhplnit());
						tplnit.setUpdtchk(tplnitList.get(j).getUpdtchk());
						resultCnt += tmDispatchMapper.updateTplnitTime(tplnit);
					}
				}
				resultCnt += tmDispatchMapper.updateTplnhd(tplnhd); // 아이템 운송상태 업데이트
				resultCnt++;
			}
		}

		if (tmDispatch.getItemGrid() != null) {
			// 아이템 수정
			List<TMDispatchDTO> tplnitUpdateList = tmDispatch.getItemGrid().getUpdateList(); // 아이템 업데이트 리스트
			for (int i = 0; i < tplnitUpdateList.size(); i++) {
				TMDispatchDTO tplnit = new TMDispatchDTO();
				tplnit.setUserData(tplnitUpdateList.get(i).getUserData()); // userData 설정
				tplnit.setVhplnky(tplnitUpdateList.get(i).getVhplnky()); // 헤더키
				tplnit.setDestkey(tplnitUpdateList.get(i).getDestkey()); // 도착지
				tplnit.setCustkey(tplnitUpdateList.get(i).getCustkey()); // 고객키 -> tplfit 조회용
				tplnit.setSaatc06(tplnitUpdateList.get(i).getSaatc06());

				List<TMDispatchDTO> tplnitDestList = tmDispatchMapper.selectTplnitWithDestkey(tplnit); // 헤더키와, 도착지로 배차아이템 및 수주키,수주아이템 조회

				tplnit.setShtrsts(tplnitUpdateList.get(i).getShtrsts()); // 도착지별 아이템 수정될 데이터, 운송상태
				tplnit.setRepitdt(tplnitUpdateList.get(i).getRepitdt()); // 도착지별 아이템 수정될 데이터, 운송상태
				tplnit.setRepittm(tplnitUpdateList.get(i).getRepittm()); // 도착지별 아이템 수정될 데이터, 보고시간
				tplnit.setRsnremk(tplnitUpdateList.get(i).getRsnremk()); // 도착지별 아이템 수정될 데이터, 보고사유
				for (int j = 0; j < tplnitDestList.size(); j++) {
					tplnit.setVhplnit(tplnitDestList.get(j).getVhplnit()); // 같은도착지별 아이템키
					tplnit.setUpdtchk(tplnitDestList.get(j).getUpdtchk()); // 아이템키별 updtchk

					resultCnt += tmDispatchMapper.updateTplnitTime(tplnit); // 배차아이템 운송상태 및 시간설정 update

					// 배차아이템의 수주키와 수주아이템키로 updtchk 을 가진 ocosal 객체 설정
					tplnit.setCooutky(tplnitDestList.get(j).getCooutky()); // 수주키 설정
					tplnit.setSaatc06(tplnitDestList.get(j).getSaatc06());
					tplnit.setTrnstat(tplnit.getShtrsts()); // 배차아이템의 운송상태 -> ocosal plnstat update
					resultCnt += tmDispatchMapper.updateOcosal(tplnit);
				}

				// 인증테이블 TPLFIT에 수정될 데이터 설정
				if(tplnitUpdateList.get(i).getFtftkno() != 0) {

				tplnit.setFtftkno(tplnitUpdateList.get(i).getFtftkno()); // 인증키 설정

				TMDispatchDTO tplfit = tmDispatchMapper.selectTplfitUpdtchk(tplnit);// 인증객체
					if (tplfit != null) {
	
						tplfit.setUpdtchk(tplfit.getUpdtchk()); // updtchk 설정
						tplnit.setCretime(tplnitUpdateList.get(i).getTfcretm()); // 생성시간 설정 -> 인증시간
						tplnit.setTrnmemo(tplnitUpdateList.get(i).getTrnmemo()); // 메모등록
						tplnit.setSigsvip(tplnitUpdateList.get(i).getSigsvip()); // 서명파일서버 설정
						tplnit.setSigflnm(tplnitUpdateList.get(i).getSigflnm()); // 서명파일명 설정
						tplnit.setSigflph(tplnitUpdateList.get(i).getSigflph()); // 서명파일경로 설정
						tplnit.setPicsvip(tplnitUpdateList.get(i).getPicsvip()); // 사진파일서버 설정
						tplnit.setPicflnm(tplnitUpdateList.get(i).getPicflnm()); // 사진파일명 설정
						tplnit.setPicflph(tplnitUpdateList.get(i).getPicflph()); // 사진파일경로 설정
						resultCnt += tmDispatchMapper.updateTplfit(tplnit); // TPLNIT 인증테이블 업데이트
	
						resultCnt++;
					}
				}else {
					TMDispatchDTO tplfit = new TMDispatchDTO(); // 인증객체
					tplfit.setUserData(tplnitUpdateList.get(i).getUserData());
					tplfit.setVhplnky(tplnitUpdateList.get(i).getVhplnky());
					tplfit.setCustkey(tplnitUpdateList.get(i).getCustkey());
					tplfit.setDestkey(tplnitUpdateList.get(i).getDestkey());
					if(StringUtils.hasText(tplnitUpdateList.get(i).getSigflph()) && StringUtils.hasText(tplnitUpdateList.get(i).getSigflnm())) {
						tplfit.setSigflnm(tplnitUpdateList.get(i).getSigflnm());
						tplfit.setSigsvip(path);
						tplfit.setSigflph(tplnitUpdateList.get(i).getSigflph());
					}
					if(StringUtils.hasText(tplnitUpdateList.get(i).getPicflph()) && StringUtils.hasText(tplnitUpdateList.get(i).getPicflnm())) {
						tplfit.setPicsvip(path);
						tplfit.setPicflph(tplnitUpdateList.get(i).getPicflph());
						tplfit.setPicflnm(tplnitUpdateList.get(i).getPicflnm());
					}
					tplfit.setTrnmemo(tplnitUpdateList.get(i).getTrnmemo());

					resultCnt += tmDispatchMapper.insertTplfit(tplfit);
				}
			}
		}
		if (resultCnt == 0) {
			throw new UpdateCheckedException();
		}
		return resultCnt;
	}
	
	/*  updateTma11SearchListAll - 운송상태수정(update)
	*   최초 생성일시	: 2023-12-13 13:50
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMDispatchDTO - 배차관리 DTO
	*   출력 PARAMETA	: int
	*   설명			: 운송상태수정에서 헤더 그리드에 체크된 데이터를 기준으로 헤더, 아이템 전부다 일괄적으로 수정해주는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma11: 배차계획 및 배차 아이템 일괄 수정 */
	public int updateTma11SearchListAll(TMDispatchDTO tmDispatch) {
		int resultCnt = 0;
		UserVO userData = tmDispatch.getUserData();
		String plnstat = tmDispatch.getPlnstat();
		String stpitdt = tmDispatch.getStpitdt();
		String stpittm = tmDispatch.getStpittm();
		String trfindt = tmDispatch.getTrfindt();
		String trfintm = tmDispatch.getTrfintm();
		String shtrsts = tmDispatch.getShtrsts();
		String repitdt = tmDispatch.getRepitdt();
		String repittm = tmDispatch.getRepittm();

		if(tmDispatch.getTplnhdUpdateList() != null) {
			List<TMDispatchDTO> tplnhdList = tmDispatch.getTplnhdUpdateList();
			for(int i = 0;i<tplnhdList.size();i++) {
				TMDispatchDTO tplnhd = new TMDispatchDTO();
				tplnhd.setUserData(userData);
				tplnhd.setVhplnky(tplnhdList.get(i).getVhplnky());
				tplnhd.setUpdtchk(tplnhdList.get(i).getUpdtchk());
				tplnhd.setPlnstat(plnstat);
				tplnhd.setStpitdt(stpitdt);
				tplnhd.setStpittm(stpittm);
				tplnhd.setTrfindt(trfindt);
				tplnhd.setTrfintm(trfintm);

				resultCnt += tmDispatchMapper.updateTplnhdAll(tplnhd);

				List<TMDispatchDTO> tplnitList = tmDispatchMapper.selectTplnitWithDestkey(tplnhd);

				for(int j = 0;j<tplnitList.size();j++) {
					TMDispatchDTO tplnit = new TMDispatchDTO();
					tplnit.setUserData(userData);
					tplnit.setVhplnky(tplnitList.get(j).getVhplnky());
					tplnit.setVhplnit(tplnitList.get(j).getVhplnit());
					tplnit.setUpdtchk(tplnitList.get(j).getUpdtchk());
					tplnit.setShtrsts(shtrsts);
					tplnit.setRepitdt(repitdt);
					tplnit.setRepittm(repittm);

					resultCnt += tmDispatchMapper.updateTplnitTime(tplnit);
				}
			}
		}

		if (resultCnt == 0) {
			throw new UpdateCheckedException();
		}

		return resultCnt;
	}

	/*
	 * 프로그램 ID: TMA12 프로그램 내용: 일별실적통계
	 */
	
	/*  getTma12StaticsList - 일별실적통계 데이터
	*   최초 생성일시	: 2023-12-13 13:50
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMDispatchDTO - 배차관리 DTO
	*   출력 PARAMETA	: List<TMDispatchDTO> - 배차관리 DTO List
	*   설명			: 일별실적통계에서 그리드에 들어가는 데이터를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma12: 일별실적통계 데이터 */
	public List<TMDispatchDTO> getTma12StaticsList(TMDispatchDTO tmDispatch) {
		return tmDispatchMapper.selectTmDispatchStatics(tmDispatch);
	}
}