package com.logistics.wm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.InitDataDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.NoSaveDataException;
import com.logistics.configuration.error.ProcedureCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.MdesmaDTO;
import com.logistics.md.domain.MowrmaDTO;
import com.logistics.md.domain.MptnmaDTO;
import com.logistics.md.domain.MskuwcDTO;
import com.logistics.md.domain.MskuwcVO;
import com.logistics.md.mapper.DocumentMapper;
import com.logistics.md.mapper.PartnerMapper;
import com.logistics.md.mapper.UnitsMapper;
import com.logistics.om.mapper.PurchaseMapper;
import com.logistics.tm.domain.TMDispatchDTO;
import com.logistics.tm.domain.TvhcmaDTO;
import com.logistics.wm.domain.InventoryDTO;
import com.logistics.wm.domain.KioskDTO;
import com.logistics.wm.domain.PdaDTO;
import com.logistics.wm.domain.PickingDTO;
import com.logistics.wm.domain.RecvDTO;
import com.logistics.wm.domain.RecvPutDTO;
import com.logistics.wm.domain.ShipEtcDTO;
import com.logistics.wm.domain.TaskDTO;
import com.logistics.wm.mapper.PdaMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PdaService {

	private final PdaMapper pdaMapper;

	private final PurchaseMapper purchaseMapper;
	
	private final PartnerMapper partnerMapper;
	
	private final DocumentMapper documentMapper;
	
	private final UnitsMapper unitsMapper;
	
	/*  getSkuList - 부품을(상품)을 조회하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: MskuwcDTO - 부품마스터 DTO
	*   출력 PARAMETA	: List<MskuwcVO> - 부품마스터 VO
	*   설명			: MSKUWC(부품마스터) 내에서 부품(상품)의 정보를 조회하는 메소드  
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	public List<MskuwcVO> getSkuList(MskuwcDTO mskuwcDTO){
		return pdaMapper.getSkuList(mskuwcDTO);
	}
	
	/*  getWasnifList - 입고 예정정보 조회하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: List<RecvDTO> - 입고-공통 DTO
	*   설명			: WASNIF(입고 예정정보)에서 입고 예정정보를 조회하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	public List<RecvDTO> getWasnifList(PdaDTO pdaDTO){
		return pdaMapper.getWasnifList(pdaDTO);
	}
	
	/*  saveWrcvit - 창고 안으로 입고 하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: RequestDTO<PdaDTO> - PDA-파트 DTO
	*   출력 PARAMETA	: int - 저장결과 Cnt
	*   설명			: 창고 안으로 입고 하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	public int saveWrcvit(RequestDTO<PdaDTO> paramList) {
		int resultCnt = 0;
		String rcvdcky = pdaMapper.selectRcvdcky();
		
		for(PdaDTO pdaDTO : paramList.getAddList()) {
			pdaDTO.setRcvdcky(rcvdcky);
			//사유코드협의후 set 예정(현재 임시로 '')
			pdaDTO.setRcvrscd("");
			
			pdaMapper.sp_wm_ctrwrcvit(pdaDTO);
			
			if (pdaDTO.getOresult() != 0) {
				throw new ProcedureCheckedException((String) pdaDTO.getOmsgkey());
			}else {
				resultCnt += pdaDTO.getOresult();
			}
		}
		return resultCnt;
	}
	
	/*  selectWmp40List - 창고에 입고한 부품(상품)을 조회하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: List<RecvDTO> - 입고-공통 DTO
	*   설명			: 창고에 입고한 부품(상품)을 조회하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	public List<RecvDTO>selectWmp40List(PdaDTO pdaDTO){
		return pdaMapper.selectWmp40List(pdaDTO);
	}
	
	/*  saveWmp40List - 창고에 입고한 부품(상품)을 팔레타이징 처리 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: List<RecvDTO> - 입고-공통 DTO
	*   설명			: 창고에 입고한 부품(상품)을 팔레타이징 처리 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	public int saveWmp40List(RequestDTO<PdaDTO> pdaDTO){
		int resultCnt = 0;
		//작업 번호 채번
		String taskoky = pdaMapper.getTaskoky();
		
		String toareky = "RCV";
		String tozonky = "RCVZ";
		String tolocky = "RCVLOC";
		String doccate = "500";
		String doctype = "510";
		
		for(PdaDTO insertPda : pdaDTO.getAddList()) {
			insertPda.setTaskoky(taskoky);
			insertPda.setToareky(toareky);
			insertPda.setTozonky(tozonky);
			insertPda.setTolocky(tolocky);
			insertPda.setDoccate(doccate);
			insertPda.setDoctype(doctype);
			
			pdaMapper.saveWmpTolocky(insertPda);
			
			if (insertPda.getOresult() != 0) {
				throw new ProcedureCheckedException((String) insertPda.getOmsgkey());
			}else {
				resultCnt += insertPda.getOresult();
			}
		}
		return resultCnt;
	}	
	
	/*  getWrcvitList - 창고에 입고한 부품(상품)을 조회하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: List<RecvPutDTO> - 적치 DTO
	*   설명			: 창고에 입고한 부품(상품)을 조회하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/	
	public List<RecvPutDTO> getWrcvitList(PdaDTO pdaDTO){
		return pdaMapper.getWrcvitList(pdaDTO);
	}
	
	/*  saveWmpTolocky - 창고에 입고한 부품(상품)의 적치처리하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: RequestDTO<PdaDTO> - PDA-파트 DTO
	*   출력 PARAMETA	: int - 저장결과 Cnt
	*   설명			: 창고에 입고한 부품(상품)의 적치처리하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	public int saveWmpTolocky(RequestDTO<PdaDTO> paramList) {
		int resultCnt = 0;
		String taskoky = "";

		//첫 데이터가 taskoky가 없을 시 ASN번호로 스캔
		if("eoasnky".equals(paramList.getAddList().get(0).getExctype())){
			//작업 번호 채번
			taskoky = pdaMapper.getTaskoky();

			for(PdaDTO pdaDTO : paramList.getAddList()) {
				if(pdaDTO.getTaskoky().isBlank()) {
					pdaDTO.setTaskoky(taskoky);
				}
				pdaDTO.setToareky("STG");
				pdaDTO.setDoccate("500");
				pdaDTO.setDoctype("510");

				String zone = pdaMapper.selectZoneByLocation(pdaDTO).getZonekey();

				pdaDTO.setTozonky(zone);

				pdaMapper.saveWmpTolocky(pdaDTO);

				if (pdaDTO.getOresult() != 0) {
					throw new ProcedureCheckedException((String) pdaDTO.getOmsgkey());
				}else {
					resultCnt += pdaDTO.getOresult();
				}
			}
		}

		//첫 데이터가 taskoky가 있을 경우 작업지시번호로 스캔한 경우
		if("taskoky".equals(paramList.getAddList().get(0).getExctype())){
			for(PdaDTO pdaDTO : paramList.getAddList()) {
				pdaMapper.updateWtakitWmp50List(pdaDTO);		//Task 업데이트
				pdaMapper.updateWtakitWmp50StokkList(pdaDTO);	//재고이동처리

				if (pdaDTO.getOresult() != 0) {
					throw new ProcedureCheckedException((String) pdaDTO.getOmsgkey());
				}else {
					resultCnt += pdaDTO.getOresult();
				}
			}
		}

		return resultCnt;
	}
	
	/*  getWshpitList - 피킹처리할 부품(상품)의 정보를 조회하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: List<PickingDTO> - 출고-피킹 DTO
	*   설명			: 피킹처리할 부품(상품)의 정보를 조회하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	public List<PickingDTO> getWshpitList(PdaDTO pdaDTO){
		return pdaMapper.getWshpitList(pdaDTO);
	}
	
	/*  saveWmp60List - 피킹처리할 부품(상품)을 저장하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: int - 저장결과 Cnt
	*   설명			: 피킹처리할 부품(상품)을 저장하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	public int saveWmp60List(RequestDTO<PdaDTO> paramList) {
		int resultCnt = 0;
		
		for(PdaDTO pdaDTO : paramList.getAddList()) {
			resultCnt += pdaMapper.updateWmp60List(pdaDTO);
			
			if(resultCnt == 0) {
				throw new UpdateCheckedException();
			}
			pdaDTO.setDoccate("500");
			pdaDTO.setDoctype("530");
			pdaMapper.sp_wm_ctrstokky(pdaDTO);
			
			if(pdaDTO.getOresult() != 0) {
				throw new ProcedureCheckedException((String) pdaDTO.getOmsgkey());
			}else {
				resultCnt += 0;
			}
		}
		return resultCnt;
	}
	
	/*  getWtakitList - 이동 처리할 부품(상품)의 정보를 조회하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: List<TaskDTO> - 작업 - 공통 DTO
	*   설명			: 이동 처리할 부품(상품)의 정보를 조회하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	public List<TaskDTO> getWtakitList (PdaDTO pdaDTO){
		return pdaMapper.getWtakitList(pdaDTO);
	}
	
	/*  saveWmp70List - 이동 처리 완료할 부품(상품)의 정보를 저장하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: RequestDTO<PdaDTO> - PDA-파트 DTO
	*   출력 PARAMETA	: int - 저장결과 Cnt
	*   설명			: 이동 처리 완료할 부품(상품)의 정보를 저장하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	public int saveWmp70List(RequestDTO<PdaDTO> requestParam) {
		int resultCnt = 0;
		
		for(PdaDTO pdaDTO : requestParam.getAddList()) {
			resultCnt = pdaMapper.updateWmp70List(pdaDTO);
			
			if(resultCnt == 0) {
				throw new UpdateCheckedException();
			}
			
			pdaDTO.setDoccate("500");
			pdaDTO.setDoctype("560");
			
			pdaMapper.updateWmp70ListSp(pdaDTO);
			
			if(pdaDTO.getOresult() != 0) {
				throw new ProcedureCheckedException((String) pdaDTO.getOmsgkey());
			}else {
				resultCnt += 0;
			}
		}
		return resultCnt;
	}
	
	/*  getWstkkyList - 부품(상품)의 현 재고량을 보여주는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: List<InventoryDTO> - 출고-공통 DTO
	*   설명			: 부품(상품)의 현 재고량을 보여주는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	public List<InventoryDTO> getWstkkyList(PdaDTO pdaDTO){
		return pdaMapper.getWstkkyList(pdaDTO);
	}
	
	/*  getWmp90WasnifList - 키오스크 입고 예정인 정보를 조회하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: List<RecvDTO> - 입고-공통 DTO
	*   설명			: 키오스크 입고 예정인 정보를 조회하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	public List<RecvDTO> getWmp90WasnifList(PdaDTO pdaDTO){
		List<RecvDTO> recvDTO = pdaMapper.selectWasnifList(pdaDTO);
		
		if(recvDTO.isEmpty()) {
			return recvDTO;
		}
		
		pdaDTO.setCompkey(recvDTO.get(0).getCompkey());
		pdaDTO.setWarekey(recvDTO.get(0).getWarekey());
		return pdaMapper.getWmp90WasnifList(pdaDTO);
	}
	
	/*  saveWmp90 - 키오스크 입고 예정인 정보를 저장하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: int - 저장결과 Cnt
	*   설명			: 키오스크 입고 예정인 정보를 저장하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	public int saveWmp90(KioskDTO kioskDTO) {
		int resultCnt = 0;
		//입하 번호 채번
		String rcvdcky = pdaMapper.selectRcvdcky();
		
		if("inbound".equals(kioskDTO.getTabFlag())) {
			for(PdaDTO pdaDTO : kioskDTO.getAddList()) {
				pdaDTO.setRcvdcky(rcvdcky);
				pdaDTO.setDoctype("430");
				//사유코드 협의후 set 예정(현재 임시로 테이블에 있는 값 'ETC')
				pdaDTO.setRcvrscd("ETC");
				pdaDTO.setRcvrsnm("");
//				pdaDTO.setUserData(kioskDTO.getUserData()); useract xml에서 하드코딩(kiosk)
				
				pdaMapper.sp_wm_ctrwrcvit_wmp90(pdaDTO);
				
				if (pdaDTO.getOresult() != 0) {
					throw new ProcedureCheckedException((String) pdaDTO.getOmsgkey());
				}else {
					resultCnt += pdaDTO.getOresult();
				}
			}
		}else if("manual".equals(kioskDTO.getTabFlag())) {
			int itemNum = 0;
			//ASN 번호 채번
			String eoasnky = pdaMapper.selectWmp90Eoasnky();
			//Ocopur에 들어가는 COPODKY 채번
			String copodky = purchaseMapper.selectCopodky();
			
			for(PdaDTO pdaDTO : kioskDTO.getAddList()) {
				itemNum++;
				pdaDTO.setEoasnky(eoasnky);
				pdaDTO.setEoasnit(itemNum);
				pdaDTO.setRcvdcky(rcvdcky);
				pdaDTO.setRcvdcit(itemNum);
				pdaDTO.setCopodky(copodky);
				pdaDTO.setCopodit(itemNum);
				pdaDTO.setPurstat("VNDASN");
				pdaDTO.setDoccate("100");
				pdaDTO.setDoctype("190");
				pdaDTO.setUserData(kioskDTO.getUserData());
				
				resultCnt = pdaMapper.saveWmp90Ocopur(pdaDTO);
				
				if(resultCnt == 0) {
					throw new InsertCheckedException();
				}
				
				pdaDTO.setAsndate(pdaDTO.getAsndate().replace("-", ""));
				pdaDTO.setDoccate("400");
				pdaDTO.setDoctype("430");
				pdaDTO.setRcvrscd("KK");
				pdaDTO.setRcvrsnm("");
				pdaDTO.setExpidat("");
				pdaMapper.saveWmp90Wasnif(pdaDTO);
				
				if (pdaDTO.getOresult() != 0) {
					throw new ProcedureCheckedException(pdaDTO.getOmsgkey());
				} else {
					resultCnt += 0;
				}
			}
		}
		return resultCnt;
	}
	
	/*  selectWrcvitList - 키오스크 입고된 정보를 조회하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: List<RecvDTO> - 입고-공통 DTO
	*   설명			: 키오스크 입고된 정보를 조회하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	public List<RecvDTO> selectWrcvitList(PdaDTO pdaDTO){
		List<RecvDTO> recvDTO = pdaMapper.selectWasnifList(pdaDTO);
		
		if(recvDTO.isEmpty()) {
			return recvDTO;
		}
		pdaDTO.setCompkey(recvDTO.get(0).getCompkey());
		pdaDTO.setWarekey(recvDTO.get(0).getWarekey());
		return pdaMapper.selectWrcvitList(pdaDTO);
	}
	
	/*  getWmp91List - 키오스크 입고된 정보를 조회하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: List<RecvPutDTO> - 적치 DTO
	*   설명			: 키오스크 입고된 정보를 조회하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	public List<RecvPutDTO> getWmp91List(PdaDTO pdaDTO){
		return pdaMapper.getWmp91List(pdaDTO);
	}
	
	/*  getDispatchPLTList - PDA 상차 리스트 정보 조회하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: List<TMDispatchDTO> - TM-셔틀배차 DTO
	*   설명			: PDA 상차 리스트 정보 조회하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	public List<TMDispatchDTO> getDispatchPLTList(PdaDTO pdaDTO){
		return pdaMapper.getDispatchPLTList(pdaDTO);
	}
	
	/*  getTmp05TvhcmaList - PDA 차량의 정보를 조회하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: List<TvhcmaDTO> - 차량마스터 DTO
	*   설명			: PDA 차량의 정보를 조회하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	public List<TvhcmaDTO> getTmp05TvhcmaList(PdaDTO pdaDTO){
		return pdaMapper.getTmp05TvhcmaList(pdaDTO);
	}
	
	/*  saveTmp05List - PDA 상차 리스트 정보 저장하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: RequestDTO<PdaDTO> - PDA-파트 DTO
	*   출력 PARAMETA	: int - 저장결과 Cnt
	*   설명			: PDA 상차 리스트 정보 저장하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	public int saveTmp05List(RequestDTO<PdaDTO> params) {
		
		int resultCnt = 0;
		int checkIndx = 1;
		
		for(PdaDTO pdaDTO : params.getAddList()){
			pdaDTO.setEofdtyn("N");
			if(params.getAddList().size() == checkIndx) {
				pdaDTO.setEofdtyn("Y");
			}
			//유저데이터 들어오나 확인후 주석 삭제
			pdaMapper.sp_tm_loading(pdaDTO); //상차PLAN 프로시저
			
			if (pdaDTO.getOresult() != 0) {
				throw new ProcedureCheckedException((String) pdaDTO.getOmsgkey());
			}else{
				resultCnt += pdaDTO.getOresult();
			}
			checkIndx++;
		}
		return resultCnt;
	}
	
	/*  getTmp05NoneDispatchPlt - PDA 상차할 팔레트 정보 조회하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: TMDispatchDTO - TM-셔틀배차 DTO
	*   설명			: PDA 상차할 팔레트 정보 조회하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	public TMDispatchDTO getTmp05NoneDispatchPlt(PdaDTO pdaDTO){
		return pdaMapper.getTmp05NoneDispatchPlt(pdaDTO);
	}
	
	/*  saveTmp06List - PDA 배차계획 및 상차 처리 하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: RequestDTO<PdaDTO> - PDA-파트 DTO
	*   출력 PARAMETA	: int - 저장결과 Cnt
	*   설명			: PDA 배차계획 및 상차 처리 하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	public int saveTmp06List(RequestDTO<PdaDTO> params) {
		int resultCnt = 0;
		
		String vhplnky = pdaMapper.selectVhplnky();
		
		for(PdaDTO pdaDTO : params.getAddList()) {
			pdaDTO.setVhplnky(vhplnky);
			pdaMapper.sp_tm_plan_loading(pdaDTO);
			
			if (pdaDTO.getOresult() != 0) {
				throw new ProcedureCheckedException((String) pdaDTO.getOmsgkey());
			}else{
				resultCnt += pdaDTO.getOresult();
			}
		}
		return resultCnt;
	}
	
	/*  getTmp07DispatchPLTList - PDA 배차계획이 상차인 상태인 정보를 조회하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: PdaDTO - PDA-파트 DTO
	*   출력 PARAMETA	: List<TMDispatchDTO> - TM-셔틀배차 DTO
	*   설명			: PDA 배차계획이 상차인 상태인 정보를 조회하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	public List<TMDispatchDTO> getTmp07DispatchPLTList(PdaDTO pdaDTO){
		return pdaMapper.getTmp07DispatchPLTList(pdaDTO);
	}
	
	/*  saveTmp07List - PDA 배차계획이 상차인 상태인 계획을 취소 하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: RequestDTO<PdaDTO> - PDA-파트 DTO
	*   출력 PARAMETA	: int - 저장결과 Cnt
	*   설명			: 배차계획이 상차인 상태인 계획을 취소 하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	public int saveTmp07List(@RequestBody RequestDTO<PdaDTO> params) {
		
		int resultCnt = 0;
		
		for(PdaDTO pdaDTO : params.getAddList()) {
			//배차 번호 및 팔레트번호로 아이템 번호들을 조회
			List<PdaDTO> selectVhplnitList = pdaMapper.selectVhplnitList(pdaDTO);
			
			if(selectVhplnitList.isEmpty()) {
				throw new NoSaveDataException();
			}
			
			for(PdaDTO param : selectVhplnitList) {
				
				param.setUserData(pdaDTO.getUserData());
				param.setWarekey(pdaDTO.getWarekey());
				pdaMapper.spTmPlanCancelTmp07(param);
				
				if (pdaDTO.getOresult() != 0) {
					throw new ProcedureCheckedException((String) param.getOmsgkey());
				}else{
					resultCnt += pdaDTO.getOresult();
				}
			}
		}
		return resultCnt;
	}
	
	/*  selectWmp91InitList - 키오스크 수동입고 초기 데이터 호출하는 메소드
	*   최초 생성일시	: 2023-12-25 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: pdaDTO - PDA DTO
	*   출력 PARAMETA	: MskuwcDTO - 부품 마스터 DTO
	*   설명			: 키오스크 수동입고 초기 데이터 호출하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	public List<MskuwcDTO> selectWmp91InitList(PdaDTO pdaDTO){
		return pdaMapper.selectWmp91InitList(pdaDTO);
	}
	
	/*  getWmp92InitDataSelect, getWmp93Init - 키오스크 긴급출고 초기 데이터 호출하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: CommonDTO - 공통 DTO
	*   출력 PARAMETA	: InitDataDTO - 초기 세팅 데이터 DTO
	*   설명			: 키오스크 긴급출고 초기 데이터 호출하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	public InitDataDTO getWmp93Init(CommonDTO param) {
		InitDataDTO initDataDTO = new InitDataDTO();
		Map<String, Object> returnData = new HashMap<>();
		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(param.getUserData());
		returnData.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDTO));

//		MrscmaDTO mrscmaDTO = new MrscmaDTO();
//		mrscmaDTO.setUserData(param.getUserData());
//		mrscmaDTO.setDoctype("790");
//		mrscmaDTO.setDoccate("700");
//		mrscmaDTO.setRscate1("NORMAL");
//		returnData.put("shprscdList", documentMapper.selectMrscmaSelectBox(mrscmaDTO));

		MptnmaDTO martnerDTO = new MptnmaDTO();
		martnerDTO.setPtnrtyp("CUSTOMER");
		martnerDTO.setUserData(param.getUserData());
		returnData.put("custkeyList", partnerMapper.getMdp02SelectBoxByAll(martnerDTO));

		MdesmaDTO mdesmaDTO = new MdesmaDTO();
		mdesmaDTO.setPtnrkey(param.getDestkey());
		mdesmaDTO.setUserData(param.getUserData());
		returnData.put("destkeyList", partnerMapper.getMdp30SelectBoxByAll(mdesmaDTO));

		MskuwcDTO mskuwcdto = new MskuwcDTO();
		mskuwcdto.setUserData(param.getUserData());
		mskuwcdto.setWarekey(param.getWarekey());
		returnData.put("skumkeyList", unitsMapper.selectSkuwcBoxCustom(mskuwcdto));
		
		initDataDTO.setItem(returnData);
		return initDataDTO;
	}
	
	/*  saveWmp92, saveWmp93Order - 키오스크 긴급출고처리할 데이터를 저장하는 메소드
	*   최초 생성일시	: 2023-12-11 09:00
	*   최초 생성자	: 오권식
	*   입력 PARAMETA	: RequestDTO<ShipEtcDTO> - 출고-기타출고 DTO
	*   출력 PARAMETA	: int - 저장결과 Cnt
	*   설명			: 키오스크 긴급출고처리할 데이터를 저장하는 메소드
	*   수정 내역
	*   수정일시		: 
	*   수정자		: 
	*   변경 사항		: 
	*/
	public int saveWmp93Order(RequestDTO<ShipEtcDTO> param) {
		List<ShipEtcDTO> addList = param.getAddList();
		int resultCnt = 0;
		String cooutky = pdaMapper.getCooutky();
		for(ShipEtcDTO order : addList) {
			order.setCooutky(cooutky);
			order.setDoccate("200");
			order.setDoctype("240");
			order.setSalstat("ORDFM");
			
			// 화면에서 saatc06(팔레트번호) 필드에 대하여 사용자가 미입력하는 경우 , 수주번호 넘김
			if(order.getSaatc06() == null || "".equals(order.getSaatc06().trim())) {
				order.setSaatc06(cooutky);
			}
			
			order.setParsncd("");
			
			boolean result = pdaMapper.saveSalesOrder(order);
			if(result == false) {
				throw new InsertCheckedException();
			}
			resultCnt++;
			
			if(addList.size() == resultCnt) {
				// 프로시저 안에서 출고 문서 생성 후 할당 , 피킹 , 출고완료까지 한번에 처리함.
				pdaMapper.pdaSpOmOcosalWms(order);
				if(order.getOresult() != 0) {
					throw new ProcedureCheckedException(order.getOmsgkey());
				}
			}
		}
		
		if(resultCnt == 0) {
			throw new InsertCheckedException();
		}

		return resultCnt;
	}
}
