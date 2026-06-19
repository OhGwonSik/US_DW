package com.logistics.tm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.DeleteCheckedException;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.ProcedureCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.domain.MdesmaDTO;
import com.logistics.md.domain.MowrmaDTO;
import com.logistics.md.domain.MrscmaDTO;
import com.logistics.md.domain.MwarmaDTO;
import com.logistics.md.service.MDC06Service;
import com.logistics.md.service.MDC07Service;
import com.logistics.md.service.MDO02Service;
import com.logistics.md.service.MDP11Service;
import com.logistics.om.domain.MordmaDTO;
import com.logistics.om.mapper.SalesMapper;
import com.logistics.sy.domain.UserVO;
import com.logistics.tm.domain.TMOrderDTO;
import com.logistics.tm.mapper.TMOrderMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TMOrderService {

	private final TMOrderMapper tmOrderMapper;

	private final SalesMapper salesMapper;
	
	private final MDC07Service mdc07Service;
	
	private final MDO02Service mdo02Service;
	
	private final MDP11Service mdp11Service;
	
	private final MDC06Service mdc06Service;
	
	
	/*  getTmaInitData - 공통 InitData
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   설명			: 운송오더현황, 운송오더수정, 셔틀배차, 배차수정 페이지에서 공통으로 들어가는 InitData을 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* init 데이터 */
	public TMOrderDTO getTmaInitData(TMOrderDTO transport) {
		UserVO userData = transport.getUserData();
		
		//도착지
		transport.setDestList(tmOrderMapper.selectTmaCommonDestList(transport));

		//고객
		transport.setCustList(tmOrderMapper.selectTmaCommonCustList(transport));
		
		//ocatma List
		transport.setOcatmaList(tmOrderMapper.selectTmaOcatmaList(transport));
		
		//운송상태
		McodemDTO mcodem = new McodemDTO();
		mcodem.setUserData(userData);
		mcodem.setComcdky("TRNSTAT");
		transport.setTrnstatList(mdc07Service.getCommonCodeSelectBox(mcodem));
				
		//수주상태
		mcodem.setComcdky("SALSTAT");
		transport.setSalstatList(mdc07Service.getCommonCodeSelectBox(mcodem));
				
		//출고상태
		mcodem.setComcdky("OUBSTAT");
		transport.setOubstatList(mdc07Service.getCommonCodeSelectBox(mcodem));
				
		//입고상태
		mcodem.setComcdky("INBSTAT");
		transport.setInbstatList(mdc07Service.getCommonCodeSelectBox(mcodem));
		
		mcodem.setComcdky("PLNSTAT");
		transport.setPlnstatList(mdc07Service.getCommonCodeSelectBox(mcodem));
		
		return transport;
	}
	
	/*  getTmaItemSearch - 공통 아이템 그리드
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: List<TMOrderDTO> - TM 운송오더, 배차관리 DTO List
	*   설명			: 운송오더현황, 운송오더수정, 셔틀배차, 배차수정 페이지에서 아이템 그리드에 들어가는 데이터를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* 아이템 검색 데이터 */
	public List<TMOrderDTO> getTmaItemSearch(TMOrderDTO transport) {
		return tmOrderMapper.selectTmaOcosal(transport);
	}
	
	/*  getTmaDestinationList - 도착지 List 호출
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: List<MdesmaDTO> - 도착지 마스터 테이블 DTO List
	*   설명			: 긴급오더생성 페이지에서 도착지 SelectBox를 구성하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* 도착지 데이터 */
	public List<MdesmaDTO> getTmaDestinationList(TMOrderDTO transport) {
		return tmOrderMapper.selectTmaCommonDestList(transport);
	}
	
	/*  getTmaOcodmaList - 차종 List 호출
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: List<TMOrderDTO> - TM 운송오더, 배차관리 DTO List
	*   설명			: 긴급오더생성, 운송오더수정 페이지에서 차종 SelectBox를 구성하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* 차종 조회 */
	public List<TMOrderDTO> getTmaOcodmaList(TMOrderDTO transport) {
		return tmOrderMapper.selectTmaOcodmaList(transport);
	}
	
	/*  getTmaMrscmaList - 긴급사유 List
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: MrscmaDTO - 사유 마스터 DTO
	*   출력 PARAMETA	: List<CommonDTO> - 공통 DTO List
	*   설명			: 셔틀배차, 배차수정 페이지에서 긴급배차 모달에서 사용되는 긴급사유 SelectBox를 구성하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* 사유 조회 */
	public List<CommonDTO> getTmaMrscmaList(MrscmaDTO mrscma) {
		mrscma.setDoccate("800");
		mrscma.setDoctype("870");
		return mdc06Service.getReasonCodeSelectBox(mrscma);
	}
	
	/*  getCommonMordmaaList - 문서타입 List
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: Mordma - 고객 오더 마스터 DTO
	*   출력 PARAMETA	: List<MordmaDTO> - 고객 오더 마스터 DTO List
	*   설명			: 운송오더현황, 운송오더수정, 배차수정 페이지에서 검색 조건에 들어가는 문서타입 SelectBox를 구성하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public List<MordmaDTO> getCommonMordmaList(MordmaDTO mordma) {
		mordma.setCoocate("200");
		return tmOrderMapper.selectTma02MordmaList(mordma);
	}
	
	/*  getTma01SearchData - 공통 헤드그리드 데이터
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: List<TMOrderDTO> - TM 운송오더, 배차관리 DTO List
	*   설명			: 운송오더현황, 운송오더수정, 셔틀배차, 배차수정 페이지에서 헤드 그리드에 들어가는 데이터 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* 검색 데이터 */
	public List<TMOrderDTO> getTransportList(TMOrderDTO transport) {
		return tmOrderMapper.selectTransportList(transport);
	}

	/*
	 * 프로그램 ID: TMA02
	 * 프로그램 내용: 수기배차생성
	 */
	
	/*  getTma02InitData - 긴급오더생성 페이지 initData
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   설명			: 긴급오더생성 페이지 InitData를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma02: init 데이터 */
	public TMOrderDTO getTma02InitData(TMOrderDTO transport) {
		
		UserVO userData = transport.getUserData();
		
		//고객
		transport.setCustList(tmOrderMapper.selectTmaCommonCustList(transport));
		
		//운송상태
		McodemDTO mcodem = new McodemDTO();
		mcodem.setUserData(userData);
		mcodem.setComcdky("TRNSTAT");
		transport.setTrnstatList(mdc07Service.getCommonCodeSelectBox(mcodem));
		
		//창고
		MwarmaDTO mwarma = new MwarmaDTO();
		mwarma.setUserData(userData);
		transport.setWarekeyList(mdo02Service.getWarehouseSelectBox(mwarma));
		
		//화주
		MowrmaDTO mowrma = new MowrmaDTO();
		mowrma.setUserData(userData);
		transport.setOwnerList(mdp11Service.getOwnerSelectBox(mowrma));
		
		return transport;
	}
	
	/*  tma02GetPostdat - 영업일자 채번
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: String
	*   설명			: 긴급오더생성 페이지에서 영업일자 채번을 하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma02: 영업일자 채번 */
	public String tma02GetPostdat(TMOrderDTO transport) {
		return tmOrderMapper.tma02GetPostdat(transport);
	}
	
	/*  selectTma02SkuwcList - 상품 리스트 조회
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: List<TMOrderDTO> - TM 운송오더, 배차관리 DTO List
	*   설명			: 긴급오더생성 페이지에서 품목 SelectBox를 구성하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma02: 상품리스트 가져오기 */
	public List<TMOrderDTO> selectTma02SkuwcList(TMOrderDTO transport) {
		return tmOrderMapper.selectTma02SkuwcList(transport);
	}
	
	/*  selectTmMordmaList - 문서유형 List
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: MordmaDTO - 고객 오더 마스터 DTO
	*   출력 PARAMETA	: List<MordmaDTO> - 고객 오더 마스터 DTO List
	*   설명			: 긴급오더생성 페이지에서 문서유형 키값이 아닌 Name값으로 보여주기 위해 Render에 필요한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma02: 문서유형 가져오기 */
	public List<MordmaDTO> selectTmMordmaList(MordmaDTO mordma) {
		mordma.setCoocate("200");
		
		List<String> cootypes = new ArrayList<>();
		cootypes.add("210");
		cootypes.add("220");
		cootypes.add("240");
		cootypes.add("290");
		mordma.setCootypes(cootypes);
		
		return tmOrderMapper.selectTma02MordmaList(mordma);
	}
	
	/*  saveTransportOrder - 수기 배차 등록
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: RequestDTO<TMOrderDTO> - 그리드 List DTO / TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: int
	*   설명			: 긴급오더생성 페이지에서 긴급오더 저장하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma02: 운송오더 등록 */
	public int saveTransportOrder(RequestDTO<TMOrderDTO> transport) {
		List<TMOrderDTO> addList = transport.getAddList();
		int resultCnt = 0;

		String cooutky = salesMapper.getCooutky();
		
		if(addList != null) {
			for(TMOrderDTO order : addList) {
				order.setCooutky(cooutky);
				order.setDoccate("200");
				order.setSalstat("ORDFM");

				resultCnt += tmOrderMapper.insertTma02Ocosal(order);
			}
		}

		if(resultCnt == 0) {
			throw new InsertCheckedException();
		}

		TMOrderDTO param = new TMOrderDTO();
		param.setUserData(addList.get(0).getUserData());
		param.setCooutky(cooutky);
		param.setDoccate("200");

		tmOrderMapper.tmaSpOmOcosalWms(param);
		
		if(param.getOresult() != 0) {
			throw new ProcedureCheckedException(param.getOmsgkey());
		}

		return resultCnt;
	}
	
	/*
	 * 프로그램 ID: TMA03
	 * 프로그램 내용: 운송오더수정
	*/
	
	/*  tma03Update - 운송오더수정 Update 로직
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: RequestDTO<TMOrderDTO> - 그리드 List DTO / TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: int
	*   설명			: 운송오더수정 페이지에서 오더를 수정하고 저장할 때 동작하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma03 update */
	public int tma03Update(RequestDTO<TMOrderDTO> transport) {
		int updateOcosal = 0;
		int updateTplnit = 0;
		
		if(transport.getUpdateList() != null) {
			for(TMOrderDTO ocosal : transport.getUpdateList()) {
				updateOcosal += tmOrderMapper.updateTmaCommonOcosal(ocosal);
				
				if(!ocosal.getTrnstat().equals("NEW")) {
					TMOrderDTO tplnit = tmOrderMapper.selectTma03Tplnit(ocosal);
					
					ocosal.setVhplnky(tplnit.getVhplnky());
					ocosal.setVhplnit(tplnit.getVhplnit());
					ocosal.setTplnitchk(tplnit.getUpdtchk());
					
					updateTplnit += tmOrderMapper.updateTma03Tplnit(ocosal);
					
					if(updateTplnit == 0) {
						throw new UpdateCheckedException();
					}
				}
			}
		}
		
		if(updateOcosal != transport.getUpdateList().size()) {
			throw new UpdateCheckedException();
		}
		
		return updateOcosal + updateTplnit;
	}

	
	/*
	 * 프로그램 ID: TMA04
	 * 프로그램 내용: 셔틀배차
	*/
	
	/*  getTma04ShuttleList - 셔틀리스트 검색 List
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: List<TMOrderDTO> - TM 운송오더, 배차관리 DTO List
	*   설명			: 셔틀배차 페이지에서 셔틀배차 모달 내 그리드 데이터 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma04: 셔틀리스트 검색 */
	public List<TMOrderDTO> getTma04ShuttleList(TMOrderDTO transport) {
		return tmOrderMapper.selectTma04ShuttleList(transport);
	}
	
	/*  saveShuttlePlan - 셔틀 배차 등록
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: int
	*   설명			: 셔틀배차 페이지에서 셔틀배차 저장하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma04: 셔틀 배차 등록 */
	public int saveShuttlePlan(TMOrderDTO transport) {
		List<TMOrderDTO> orderList = transport.getOrderList();
		
		UserVO userData = transport.getUserData();

		String vhplnky = tmOrderMapper.selectTma06Vhplnky();

		int tplnhdCnt = 0;
		int tplnitCnt = 0;
		int updateCnt = 0;
		
		transport.setVhplnky(vhplnky);
		transport.setDoctype("820");
		transport.setPlnstat("PLAN");

		tplnhdCnt += tmOrderMapper.insertTmaCommonTplnhd(transport);


		if(tplnhdCnt == 0) {
			throw new InsertCheckedException();
		}
		
		if(orderList != null) {
			for(int i=0; i<orderList.size(); i++) {
				transport.setDestkey(orderList.get(i).getDestkey());
				transport.setPostdat(orderList.get(i).getPostdat().replace("-", ""));
				transport.setSaatc06(orderList.get(i).getSaatc06());
				transport.setDoccate(orderList.get(i).getDoccate());
				transport.setDoctype(orderList.get(i).getDoctype());

				List<TMOrderDTO> ocosalList = tmOrderMapper.selectTmaOcosal(transport);
					
				for(TMOrderDTO item : ocosalList) {
					if(item.getTrnstat().equals("NEW")) {
						item.setVhplnky(vhplnky);
						item.setShtrsts("PLAN");
						
						item.setUserData(userData);

						if(item.getDoctype().equals("230")) {
							item.setRetodyn("Y");
						}

						item.setTrnstat("PLAN");

						tplnitCnt += tmOrderMapper.insertTmaCommonTplnitItem(item);
						updateCnt += tmOrderMapper.updateTmaCommonOcosal(item);
					}
				}
			}
		}

		if(tplnitCnt == 0) {
			throw new InsertCheckedException();
		}
		if(updateCnt == 0) {
			throw new UpdateCheckedException();
		}

		return tplnhdCnt + tplnitCnt + updateCnt;
	}


	/*
	 * 프로그램 ID: TMA06
	 * 프로그램 내용: 긴급오더추가
	 */
	
	/*  getTma06PlanList - 배차 계획 List
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: List<TMOrderDTO> - TM 운송오더, 배차관리 DTO List
	*   설명			: 배차수정 페이지에서 추가배차 모달 내 그리드 데이터 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma06: 배차계획 리스트 검색 */
	public List<TMOrderDTO> getTma06PlanList(TMOrderDTO transport) {
		return tmOrderMapper.selectTma06PlanList(transport);
	}
	
	/*  getTma06CarList - 차량리스트
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: List<TMOrderDTO> - TM 운송오더, 배차관리 DTO List
	*   설명			: 셔틀배차, 배차수정 페이지에서 긴급배차 모달 내 그리드 데이터 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma06: 차량리스트 검색 */
	public List<TMOrderDTO> getTma06CarList(TMOrderDTO transport) {
		return tmOrderMapper.selectTma06CarList(transport);
	}
	
	/*  saveAddPlan - 추가 배차 등록
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: int
	*   설명			: 배차수정 페이지에서 추가배차 저장하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma06: 추가 배차 등록 */
	public int saveAddPlan(TMOrderDTO transport) {
		List<TMOrderDTO> orderList = transport.getOrderList();
		
		UserVO userData = transport.getUserData();

		int deleteCnt = 0;
		int tplnitCnt = 0;
		int updateCnt = 0;
		
		if(orderList != null) {
			for(int i=0; i<orderList.size(); i++) {
				transport.setDestkey(orderList.get(i).getDestkey());
				transport.setPostdat(orderList.get(i).getPostdat().replace("-", ""));
				transport.setSaatc06(orderList.get(i).getSaatc06());
				transport.setDoccate(orderList.get(i).getDoccate());
				transport.setDoctype(orderList.get(i).getDoctype());
				transport.setTrnstat(orderList.get(i).getOstrnst());
				
				List<TMOrderDTO> ocosalList = tmOrderMapper.selectTmaOcosal(transport);

				//배차계획이 없을 경우
				if(orderList.get(i).getVhplnky() == null) {
					for(TMOrderDTO item : ocosalList) {
						item.setVhplnky(transport.getVhplnky());
						item.setShtrsts("PLAN");
						
						item.setUserData(userData);

						if(item.getDoctype().equals("230")) {
							item.setRetodyn("Y");
						}

						item.setTrnstat("PLAN");

						tplnitCnt += tmOrderMapper.insertTmaCommonTplnitItem(item);
						updateCnt += tmOrderMapper.updateTmaCommonOcosal(item);
					}

					if(updateCnt == 0) {
						throw new UpdateCheckedException();
					}
				}else {//배차계획이 있을 경우
					for(TMOrderDTO item : ocosalList) {
						transport.setOrdervhplnky(orderList.get(i).getVhplnky());
						transport.setCooutky(item.getCooutky());
						transport.setCooutit(item.getCooutit());
						deleteCnt += tmOrderMapper.deleteTma06Tplnit(transport);

						item.setVhplnky(transport.getVhplnky());
						item.setShtrsts("PLAN");
						
						item.setUserData(userData);

						if(item.getDoctype().equals("230")) {
							item.setRetodyn("Y");
						}

						tplnitCnt += tmOrderMapper.insertTmaCommonTplnitItem(item);
					}
					
					TMOrderDTO deleteTplnhd = new TMOrderDTO();
					deleteTplnhd.setVhplnky(orderList.get(i).getVhplnky());
					deleteTplnhd.setUserData(userData);
					
					int checkCount = tmOrderMapper.selectCheckTplnit(deleteTplnhd);
					
					if(checkCount == 0) {
						deleteCnt += tmOrderMapper.deleteTmaCommonTplnhd(deleteTplnhd);
					}

					if(deleteCnt == 0) {
						throw new DeleteCheckedException();
					}
				}

			}
		}

		if(tplnitCnt == 0) {
			throw new InsertCheckedException();
		}


		return deleteCnt + tplnitCnt + updateCnt;
	}
	
	/*  saveEmergencyPlan - 긴급 배차 등록
	*   최초 생성일시	: 2023-12-12 16:30
	*   최초 생성자	: 하주영
	*   입력 PARAMETA	: TMOrderDTO - TM 운송오더, 배차관리 DTO
	*   출력 PARAMETA	: int
	*   설명			: 셔틀배차, 배차수정 페이지에서 긴급배차 저장하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* tma06: 긴급 배차 등록 */
	public int saveEmergencyPlan(TMOrderDTO transport) {
		List<TMOrderDTO> orderList = transport.getOrderList();
		
		UserVO userData = transport.getUserData();

		String vhplnky = tmOrderMapper.selectTma06Vhplnky();

		int tplnhdCnt = 0;
		int tplnitCnt = 0;
		int updateCnt = 0;
		int deleteCnt = 0;

		transport.setVhplnky(vhplnky);
		transport.setDoctype("870");
		transport.setPlnstat("PLAN");
		
		tplnhdCnt += tmOrderMapper.insertTmaCommonTplnhd(transport);
		
		if(orderList != null) {
			for(int i=0; i<orderList.size(); i++) {
				transport.setDestkey(orderList.get(i).getDestkey());
				transport.setPostdat(orderList.get(i).getPostdat().replace("-", ""));
				transport.setSaatc06(orderList.get(i).getSaatc06());
				transport.setDoccate(orderList.get(i).getDoccate());
				transport.setDoctype(orderList.get(i).getDoctype());
				transport.setTrnstat(orderList.get(i).getOstrnst());

				List<TMOrderDTO> ocosalList = tmOrderMapper.selectTmaOcosal(transport);

				//배차계획이 없을 경우
				if(orderList.get(i).getVhplnky() == null) {
					for(TMOrderDTO item : ocosalList) {
						item.setVhplnky(vhplnky);
						item.setShtrsts("PLAN");
						
						item.setUserData(userData);

						if(item.getDoctype().equals("230")) {
							item.setRetodyn("Y");
						}
						
						item.setTrnstat("PLAN");
						
						tplnitCnt += tmOrderMapper.insertTmaCommonTplnitItem(item);
						updateCnt += tmOrderMapper.updateTmaCommonOcosal(item);
					}

					if(updateCnt == 0) {
						throw new UpdateCheckedException();
					}
				}else {//배차계획이 있을 경우
					for(TMOrderDTO item : ocosalList) {
						transport.setOrdervhplnky(orderList.get(i).getVhplnky());
						transport.setCooutky(item.getCooutky());
						transport.setCooutit(item.getCooutit());
						deleteCnt += tmOrderMapper.deleteTma06Tplnit(transport);
						

						item.setVhplnky(vhplnky);
						item.setShtrsts("PLAN");
						
						item.setUserData(userData);

						if(item.getDoctype().equals("230")) {
							item.setRetodyn("Y");
						}

						tplnitCnt += tmOrderMapper.insertTmaCommonTplnitItem(item);
					}
					
					TMOrderDTO deleteTplnhd = new TMOrderDTO();
					deleteTplnhd.setVhplnky(orderList.get(i).getVhplnky());
					deleteTplnhd.setUserData(userData);
					
					int checkCount = tmOrderMapper.selectCheckTplnit(deleteTplnhd);
					
					if(checkCount == 0) {
						deleteCnt += tmOrderMapper.deleteTmaCommonTplnhd(deleteTplnhd);
					}

					if(deleteCnt == 0) {
						throw new DeleteCheckedException();
					}
				}
			}
		}

		if(tplnhdCnt == 0 || tplnitCnt == 0) {
			throw new InsertCheckedException();
		}

		return deleteCnt + tplnitCnt + updateCnt + tplnhdCnt;
	}
}
