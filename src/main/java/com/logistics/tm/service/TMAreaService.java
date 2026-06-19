package com.logistics.tm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.DeleteCheckedException;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.ProcedureCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.MptnmaDTO;
import com.logistics.md.domain.MwarmaDTO;
import com.logistics.md.service.MDO02Service;
import com.logistics.sy.domain.UserVO;
import com.logistics.tm.domain.TMAreaDTO;
import com.logistics.tm.domain.TMOrderDTO;
import com.logistics.tm.domain.TvhcmaDTO;
import com.logistics.tm.mapper.TMAreaMapper;
import com.logistics.tm.mapper.TMOrderMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TMAreaService {

	private final TMAreaMapper tmAreaMapper;
	private final MDO02Service mdo02Service;
	private final TMOrderMapper tmOrderMapper;
	
	/*
	 *프로그램 내용: 공통
	 */
	
	/*  getTmAreaInitData - 수주상태 List 호출
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   설명			: 권역변경, 권역배차에서 init Data를 가져오는 메소드 
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public TMAreaDTO getTmAreaInitData(TMAreaDTO tmArea) {
		UserVO userData = tmArea.getUserData();
		
		tmArea.setVehiList(tmAreaMapper.selectTvhcmaVehiList(tmArea));
		
		MwarmaDTO mwarma = new MwarmaDTO();
		mwarma.setUserData(userData);
		tmArea.setWarehouseList(mdo02Service.getMwarmaList(mwarma));
		
		tmArea.setCustomerList(tmAreaMapper.selectCustomerList(tmArea));
		
		tmArea.setDestkeyList(tmAreaMapper.selectMdesma(tmArea));
		
		tmArea.setOwnerList(tmAreaMapper.selectOwnerList(tmArea));
		
		tmArea.setDoctypeList(tmAreaMapper.selectMdocmaDoccateDoctypeList(tmArea));
		
		tmArea.setDoccateList(tmAreaMapper.selectMdocmaList(tmArea));
		
		tmArea.setPlnstatList(tmAreaMapper.selectPlnstatList(tmArea));
		
		tmArea.setTrarekyList(tmAreaMapper.selectTrarekyList(tmArea));
		
		return tmArea;
	}
	
	/*  getUsernam - usernam 생성
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: String
	*   설명			: 운송권역상세설정, 생산차종설정, 대표품목설정에서 usernam을 생성하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* USERNAM 생성 */
	public String getUsernam(TMAreaDTO tmArea) {
		return tmAreaMapper.selectUsernam(tmArea);
	}
	
	/*  getTmAreaMdesmaDestList - 도착지 List
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO List
	*   설명			: 생산차종설정, 배차취소, 배차현황, 운송상태수정, 일별실적통계, 권역배차에서 도착지 SelectBox를 구성하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* MDESMA 도착지리스트 조회 */
	public List<TMAreaDTO> getTmAreaMdesmaDestList(TMAreaDTO tmArea){
		return tmAreaMapper.selectMdesma(tmArea);
	}
	

	/*
	 * 프로그램 ID: TMM06
	 *프로그램 내용: 운송권역설정
	 */
	
	/*  getTmAreaSearchList - 운송권역설정 그리드
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO List
	*   설명			: 권역변경, 권역배차에서 init Data를 가져오는 메소드 
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM06 TAREHD 헤더 조회 */
	public List<TMAreaDTO> getTmAreaSearchList(TMAreaDTO tmArea) {
		return tmAreaMapper.selectTarehd(tmArea);
	}
	
	/*  getTmAreaVehiList - 창고별 차량 List
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TvhcmaDTO> - 권역배차, 마스터 DTO List
	*   설명			: 운송권역상세설정, 운송상태수정, 권역배차에서 창고별 차량 SelectBox, 모달 내 그리드에 들어가는 데이터를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM06 창고별 차량리스트 조회 */
	public List<TvhcmaDTO> getTmAreaVehiList(TMAreaDTO tmArea){
		return tmAreaMapper.selectTvhcmaVehiList(tmArea);
	}
	
	/*  getTmAreaCdcate1List - 시도 List
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO
	*   설명			: 운송권역상세설정에서 시도 SelectBox를 구성하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM06 V_MCODEM 시도 조회 */
	public List<TMAreaDTO> getTmAreaCdcate1List(TMAreaDTO tmArea){
		return tmAreaMapper.selectCdcate1(tmArea);
	}
	
	/*  saveTmArea - 운송권역 헤더 수정 및 저장
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: RequestDTO, TMAreaDTO - 그리드 List DTO / 권역배차, 마스터 DTO
	*   출력 PARAMETA	: int
	*   설명			: 운송권역상세설정에서 운송권역설정 그리드 수정 및 저장하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* 운송권역 헤더 수정 및 저장 */
	public int saveTmArea(RequestDTO<TMAreaDTO> tmArea) {
		int insertCnt = 0;
		int updateCnt = 0;
		if(tmArea.getUpdateList() != null && !tmArea.getUpdateList().isEmpty()) {
			List<TMAreaDTO> updateList = tmArea.getUpdateList();
			TMAreaDTO tarehd = new TMAreaDTO();
			for(int i = 0;i<updateList.size();i++) {
				tarehd.setUserData(updateList.get(i).getUserData());
				tarehd.setTrareky(updateList.get(i).getTrareky());
				tarehd.setWarekey(updateList.get(i).getWarekey());
				tarehd.setTragrnm(updateList.get(i).getTragrnm());
				tarehd.setTrarenm(updateList.get(i).getTrarenm());
				tarehd.setTramemo(updateList.get(i).getTramemo());
				tarehd.setTrvehky(updateList.get(i).getTrvehky());
				tarehd.setTruseyn(updateList.get(i).getTruseyn());
				tarehd.setUpdtchk(updateList.get(i).getUpdtchk());

				updateCnt += tmAreaMapper.updateTarehd(tarehd);
			}
			if (updateCnt == 0) {
				throw new InsertCheckedException();
			}
		}

		if(tmArea.getAddList() != null && !tmArea.getAddList().isEmpty()) {
			List<TMAreaDTO> addList = tmArea.getAddList();
			TMAreaDTO tarehd = new TMAreaDTO();
			for(int i = 0;i<addList.size();i++) {
				tarehd.setUserData(addList.get(i).getUserData());
				tarehd.setWarekey(addList.get(i).getWarekey());
				tarehd.setTrareky(addList.get(i).getTrareky());
				tarehd.setTragrnm(addList.get(i).getTragrnm());
				tarehd.setTrarenm(addList.get(i).getTrarenm());
				tarehd.setTrvehky(addList.get(i).getTrvehky());
				tarehd.setTramemo(addList.get(i).getTramemo());
				tarehd.setTruseyn(addList.get(i).getTruseyn());

				insertCnt += tmAreaMapper.insertTarehd(tarehd);
			}
			if (insertCnt == 0) {
				throw new InsertCheckedException();
			}
		}

		if (insertCnt + updateCnt == 0) {
			throw new InsertCheckedException();
		}

		return insertCnt + updateCnt;
	}
	
	/*  getTmAreaPostalList - 운송권역상세설정 그리드 조회
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO List
	*   설명			: 운송권역상세설정에서 운송권역상세설정 그리드에 들어가는 데이터를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM06 US_DW MPOSTM view 조회 */
	public List<TMAreaDTO> getTmAreaPostalList(TMAreaDTO tmArea){
		return tmAreaMapper.selectUsOpPostal(tmArea);
	}
	
	/*  getTmAreaMpostmSearchList - 운송권역상세설정 모달 내 그리드 조회
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO List
	*   설명			: 운송권역상세설정에서 모달 내 그리드 데이터를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM06 MPOSTM 데이터 조회 */
	public List<TMAreaDTO> getTmAreaMpostmSearchList(TMAreaDTO tmArea){
		if(StringUtils.hasText(tmArea.getRdnmRdsnm())){
			if(!tmArea.getRdnmRdsnm().contains("-")) {
				tmArea.setRdnm(tmArea.getRdnmRdsnm());
			}else {
				String[] rdnmSplit = tmArea.getRdnmRdsnm().split("-");
				tmArea.setRdnm(rdnmSplit[0]);
				tmArea.setRdsnm(rdnmSplit[1]);
				// 검색을 00-0
			}

		}
		if(StringUtils.hasText(tmArea.getBldmnBldsm())){
			if(!tmArea.getBldmnBldsm().contains("-")) {
				tmArea.setBldmn(tmArea.getBldmnBldsm());
			}else {
				String[] bldmnSplit = tmArea.getBldmnBldsm().split("-");
				tmArea.setBldmn(bldmnSplit[0]);
				tmArea.setBldsm(bldmnSplit[1]);
				// 검색을 00-0
			}

		}
		return tmAreaMapper.selectMpostm(tmArea);
	}
	
	/*  getTmAreaTrarekyList - 권역키 리스트 조회
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO List
	*   설명			: 운송권역상세설정에서 권역키 리스트를 initData로 가져오기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM06 권역키리스트 조회 */
	public List<TMAreaDTO> getTmAreaTrarekyList(TMAreaDTO tmArea){
		return tmAreaMapper.selectTrarekyList(tmArea);
	}
	
	/*  getTmAreaComcdtxList - 시군구 List
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO List
	*   설명			: 운송권역상세설정에서 시군구 SelectBox를 구성하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM06 V_MCODEM 시군구 조회 */
	public List<TMAreaDTO> getTmAreaComcdtxList(TMAreaDTO tmArea){
		return tmAreaMapper.selectComcdtx(tmArea);
	}
	
	/*  saveTmAreaPostalList - 운송권역상세설정 그리드 데이터 추가
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: RequestDTO, TMAreaDTO - 그리드 List DTO / 권역배차, 마스터 DTO
	*   출력 PARAMETA	: int
	*   설명			: 운송권역상세설정에서 운송권역상세설정 그리드에서 그리드 데이터 추가시 동작하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM06 운송권역 아이템 저장 */
	public int saveTmAreaPostalList(RequestDTO<TMAreaDTO> tmArea) {
		RequestDTO<TMAreaDTO> headGrid = tmArea.getHeadGrid();
		RequestDTO<TMAreaDTO> itemGrid = tmArea.getItemGrid();

		TMAreaDTO tareit = new TMAreaDTO();
		int resultCnt = 0;

		if(tmArea.getHeadGrid() != null && tmArea.getItemGrid() != null) {

			if(headGrid.getUpdateList() != null && itemGrid.getAddList() != null) {
				List<TMAreaDTO> updateList = headGrid.getUpdateList();

				List<TMAreaDTO> addList = itemGrid.getAddList();
				
				tareit.setUserData(updateList.get(0).getUserData());
				tareit.setWarekey(updateList.get(0).getWarekey());
				tareit.setTrareky(updateList.get(0).getTrareky());
				
				for(int i = 0;i<addList.size();i++) {
					tareit.setPostcod(addList.get(i).getNzipcd());
					tmAreaMapper.insertTareit(tareit);
					resultCnt++;
				}
			}
		}

		if(resultCnt == 0) {
			throw new InsertCheckedException();
		}
		return resultCnt;
	}
	
	/*  deleteTmAreaPostalList - 운송권역상세설정 그리드 데이터 삭제
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: RequestDTO, TMAreaDTO - 그리드 List DTO / 권역배차, 마스터 DTO
	*   출력 PARAMETA	: int
	*   설명			: 운송권역상세설정에서 운송권역상세설정 그리드에서 그리드 데이터 삭제시 동작하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM06 운송권역 아이템 삭제 */
	public int deleteTmAreaPostalList(RequestDTO<TMAreaDTO> tmArea) {
		int resultCnt = 0;

		List<TMAreaDTO> deleteList = tmArea.getDeleteList();

		TMAreaDTO tareit = new TMAreaDTO();
		if(deleteList != null) {
			tareit.setUserData(deleteList.get(0).getUserData());
			for(int i = 0;i<deleteList.size();i++) {

				tareit.setWarekey(deleteList.get(i).getWarekey());
				tareit.setTrareky(deleteList.get(i).getTrareky());
				tareit.setTrareit(deleteList.get(i).getTrareit());

				tmAreaMapper.deleteTareit(tareit);
				resultCnt++;
			}
		}

		if(resultCnt == 0) {
			throw new DeleteCheckedException();
		}
		return resultCnt;
	}

	
	/*
	 * 프로그램 ID: TMM07
	 *프로그램 내용: 생산차종설정
	 */
	
	/*  getTmAreaOcodmaSearchList - 생산차종설정 그리드 조회
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO List
	*   설명			: 생산차종설정에서 그리드 조회할 때 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM07 OCODOM 데이터 조회 */
	public List<TMAreaDTO> getTmAreaOcodmaSearchList(TMAreaDTO tmArea){
		return tmAreaMapper.selectOcodma(tmArea);
	}
	
	/*  getCustomerList - 고객 List
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<MptnmaDTO> - 고객 마스터 테이블 DTO
	*   설명			: 생산차종설정에서 고객키 SelectBox를 구성하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM07 고객사 조회 */
	public List<MptnmaDTO> getCustomerList(TMAreaDTO tmArea) {
		return tmAreaMapper.selectCustomerList(tmArea);
	}
	
	/*  updateDeleteOcodmaList - 데이터 추가 및 수정 및 삭제
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: RequestDTO, TMAreaDTO - 그리드 List DTO / 권역배차, 마스터 DTO
	*   출력 PARAMETA	: int
	*   설명			: 생산차종설정에서 데이터 추가 및 수정 및 삭제할 때 동작하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM07 OCODOM 데이터 수정,삽입 및 삭제 */
	public int updateDeleteOcodmaList(RequestDTO<TMAreaDTO> tmArea) {
		int insertCnt = 0;
		int updateCnt = 0;
		int deleteCnt = 0;
		List<TMAreaDTO> addList = tmArea.getAddList();
		List<TMAreaDTO> updateList = tmArea.getUpdateList();
		List<TMAreaDTO> deleteList = tmArea.getDeleteList();

		if(tmArea.getDeleteList() != null && !tmArea.getDeleteList().isEmpty()) {
			TMAreaDTO ocodma = new TMAreaDTO();
			ocodma.setUserData(deleteList.get(0).getUserData());

			for(int i = 0;i<deleteList.size();i++) {
				ocodma.setWarekey(deleteList.get(i).getWarekey());
				ocodma.setSaatc05before(deleteList.get(i).getSaatc05before());

				tmAreaMapper.deleteOcodma(ocodma);
				deleteCnt++;
			}

			if(deleteCnt == 0) {
				throw new DeleteCheckedException();
			}
		}

		if(tmArea.getAddList() != null && !tmArea.getAddList().isEmpty()) {
			TMAreaDTO ocodma = new TMAreaDTO();
			ocodma.setUserData(addList.get(0).getUserData());

			for(int i = 0;i<addList.size();i++) {
				ocodma.setWarekey(addList.get(i).getWarekey());
				ocodma.setSaatc05after(addList.get(i).getSaatc05after());
				ocodma.setCartype(addList.get(i).getCartype());
				ocodma.setCartynm(addList.get(i).getCartynm());
				ocodma.setDestkey(addList.get(i).getDenamlc());
				ocodma.setCustkey(addList.get(i).getPtnamlc());

				tmAreaMapper.insertOcodma(ocodma);
				insertCnt++;
			}

			if(insertCnt == 0) {
				throw new InsertCheckedException();
			}
		}

		if(tmArea.getUpdateList() != null && !tmArea.getUpdateList().isEmpty()) {
			TMAreaDTO ocodma = new TMAreaDTO();
			ocodma.setUserData(updateList.get(0).getUserData());
			for(int i = 0;i<updateList.size();i++) {

				ocodma.setSaatc05after(updateList.get(i).getSaatc05after());
				ocodma.setCartype(updateList.get(i).getCartype());
				ocodma.setCartynm(updateList.get(i).getCartynm());
				ocodma.setDestkey(updateList.get(i).getDenamlc());
				ocodma.setCustkey(updateList.get(i).getPtnamlc());
				ocodma.setUpdtchk(updateList.get(i).getUpdtchk());
				ocodma.setWarekey(updateList.get(i).getWarekey());
				ocodma.setSaatc05before(updateList.get(i).getSaatc05before());

				tmAreaMapper.updateOcodma(ocodma);
				updateCnt++;
			}

			if(updateCnt == 0) {
				throw new UpdateCheckedException();
			}
		}
		return insertCnt + updateCnt + deleteCnt;
	}

	
	/*
	 * 프로그램 ID: TMM08
	 *프로그램 내용: 대표품목설정
	 */
	
	/*  getTmAreaOcatmaSearchList - 그리드 조회
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO List
	*   설명			: 대표품목설정에서 그리드에 들어가는 데이터 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM08 OCATMA 데이터 조회 */
	public List<TMAreaDTO> getTmAreaOcatmaSearchList(TMAreaDTO tmArea){
		return tmAreaMapper.selectOcatma(tmArea);
	}
	
	/*  getTmAreaDoctypeDoccateList - 문서타입 List
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO List
	*   설명			: 운송권역상세설정에서 문서타입을 initData로 가져오기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM08 DOCTYPE RELATION LIST 조회 */
	public TMAreaDTO getTmm08InitData(TMAreaDTO tmArea){
		UserVO userData = tmArea.getUserData();
		
		TMOrderDTO transport = new TMOrderDTO();
		transport.setUserData(userData);
		
		//도착지
		tmArea.setDestList(tmOrderMapper.selectTmaCommonDestList(transport));
		
		//고객
		tmArea.setCustkeyList(tmOrderMapper.selectTmaCommonCustList(transport));
		
		//doctypeList
		tmArea.setDoctypeList(tmAreaMapper.selectMdocmaDoccateDoctypeList(tmArea));
		
		//doccateList
		tmArea.setDoccateList(tmAreaMapper.selectMdocmaList(tmArea));
		return tmArea;
	}
	
	/*  getTmAreaDoctypeList - 문서유형 List
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO List
	*   설명			: 대표품목설정에서 문서유형 List를 initData로 가져오기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM08 DOCCATE LIST 조회 */
	public List<TMAreaDTO> getTmAreaDoctypeList(TMAreaDTO tmArea){
		return tmAreaMapper.selectMdocmaList(tmArea);
	}
	
	/*  insertUpdateDeleteOcodmaList - 그리드 조회
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: RequestDTO, TMAreaDTO - 그리드 List DTO / 권역배차, 마스터 DTO
	*   출력 PARAMETA	: int
	*   설명			: 대표품목설정에서 그리드 추가, 수정, 삭제할 때 돌아가는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMM08 OCATMA 데이터 수정,삽입 및 삭제 */
	public int insertUpdateDeleteOcodmaList(RequestDTO<TMAreaDTO> tmArea) {
		int insertCnt = 0;
		int updateCnt = 0;
		int deleteCnt = 0;
		List<TMAreaDTO> addList = tmArea.getAddList();
		List<TMAreaDTO> updateList = tmArea.getUpdateList();
		List<TMAreaDTO> deleteList = tmArea.getDeleteList();

		if(tmArea.getDeleteList() != null && !tmArea.getDeleteList().isEmpty()) {
			TMAreaDTO ocatma = new TMAreaDTO();
			ocatma.setUserData(deleteList.get(0).getUserData());

			for(int i = 0;i<deleteList.size();i++) {

				ocatma.setCompkey(deleteList.get(i).getCompkey());
				ocatma.setWarekey(deleteList.get(i).getWarekey());
				ocatma.setDoccate(deleteList.get(i).getDoccate());
				ocatma.setDoctype(deleteList.get(i).getDoctype());
				ocatma.setScatecd(deleteList.get(i).getScatecdOrigin());

				tmAreaMapper.deleteOcatma(ocatma);
				deleteCnt++;
			}

			if(deleteCnt == 0) {
				throw new DeleteCheckedException();
			}
		}

		if(tmArea.getAddList() != null && !tmArea.getAddList().isEmpty()) {
			TMAreaDTO ocatma = new TMAreaDTO();
			ocatma.setUserData(addList.get(0).getUserData());

			for(int i = 0;i<addList.size();i++) {
				ocatma.setCompkey(addList.get(i).getCompkey());
				ocatma.setWarekey(addList.get(i).getWarekey());
				ocatma.setDoctype(addList.get(i).getDoctype());
				ocatma.setDoccate(addList.get(i).getDoccate());
				ocatma.setScatecd(addList.get(i).getScatecd());
				ocatma.setScatenm(addList.get(i).getScatenm());
				ocatma.setMcatecd(addList.get(i).getMcatecd());
				ocatma.setMcatenm(addList.get(i).getMcatenm());
				ocatma.setCauseyn(addList.get(i).getCauseyn());
				ocatma.setDestkey(addList.get(i).getDestkey());

				tmAreaMapper.insertOcatma(ocatma);
				insertCnt++;
			}

			if(insertCnt == 0) {
				throw new InsertCheckedException();
			}
		}

		if(tmArea.getUpdateList() != null && !tmArea.getUpdateList().isEmpty()) {
			TMAreaDTO ocatma = new TMAreaDTO();
			ocatma.setUserData(updateList.get(0).getUserData());
			for(int i = 0;i<updateList.size();i++) {

				ocatma.setScatecd(updateList.get(i).getScatecd());
				ocatma.setScatenm(updateList.get(i).getScatenm());
				ocatma.setMcatecd(updateList.get(i).getMcatecd());
				ocatma.setMcatenm(updateList.get(i).getMcatenm());
				ocatma.setCauseyn(updateList.get(i).getCauseyn());
				ocatma.setUpdtchk(updateList.get(i).getUpdtchk());
				ocatma.setCompkey(updateList.get(i).getCompkey());
				ocatma.setWarekey(updateList.get(i).getWarekey());
				ocatma.setDoctype(updateList.get(i).getDoctype());
				ocatma.setDoccate(updateList.get(i).getDoccate());
				ocatma.setScatecdOrigin(updateList.get(i).getScatecdOrigin());
				ocatma.setDestkey(updateList.get(i).getDestkey());

				tmAreaMapper.updateOcatma(ocatma);
				updateCnt++;
			}

			if(updateCnt == 0) {
				throw new UpdateCheckedException();
			}
		}

		return insertCnt + updateCnt + deleteCnt;
	}
	

	/*
	 * 프로그램 ID: TMA13
	 *프로그램 내용: 입차승인
	 */
	
	/*  getTmAreaTvhentSearchList - 그리드 조회
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO List
	*   설명			: 입차승인에서 그리드에 들어갈 데이터 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMA13 TVHENT 데이터 조회 */
	public List<TMAreaDTO> getTmAreaTvhentSearchList(TMAreaDTO tmArea){
		return tmAreaMapper.selectTvhent(tmArea);
	}
	
	/*  getTmAreaTplfitSearchList - 모달 내 그리드 조회
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO List
	*   설명			: 입차승인에서 모달 내 그리드에 들어갈 데이터 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMA13 TPLFIT 데이터 조회 */
	public List<TMAreaDTO> getTmAreaTplfitSearchList(TMAreaDTO tmArea){
		return tmAreaMapper.selectTplfit(tmArea);
	}
	
	/*  updateTmAreaTvhent - 입차승인
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: int
	*   설명			: 입차승인에서 입차승인할 때 돌아가는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMA13 TVHENT 입차승인 수정 */
	public int updateTmAreaTvhent(TMAreaDTO tmArea) {
		int resultCnt = 0;
		List<TMAreaDTO> updateList = tmArea.getTvhentList();
		TMAreaDTO tvhent = new TMAreaDTO();		

		if(tmArea.getTvhentList() != null && tmArea.getEntstat() != null) {
			String entstat = tmArea.getEntstat();
			tvhent.setUserData(tmArea.getUserData());
			
			for(int i = 0;i<updateList.size();i++) {
				tvhent.setEntstat(entstat);
				tvhent.setEntavdt(updateList.get(i).getEntavdt());
				tvhent.setEntavtm(updateList.get(i).getEntavtm());
				tvhent.setWarekey(updateList.get(i).getWarekey());
				tvhent.setVhplnky(updateList.get(i).getVhplnky());

				resultCnt += tmAreaMapper.updateTvhent(tvhent);
			}
		}
		if(resultCnt == 0) {
			throw new UpdateCheckedException();
		}

		return resultCnt;
	}

	
	/*
	 * 프로그램 ID: TMA05
	 *프로그램 내용: 권역변경
	 */
	
	/*  getTmAreaTplnhdSearchList - 헤더 그리드 조회
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO
	*   설명			: 권역변경에서 헤더 그리드에 들어갈 데이터를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMA05 TPLNHD 데이터 조회 */
	public List<TMAreaDTO> getTmAreaTplnhdSearchList(TMAreaDTO tmArea){
		return tmAreaMapper.selectTma05HeadList(tmArea);
	}
	
	/*  getTmAreaTplnitSearchList - 아이템 그리드 조회
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO
	*   설명			: 권역변경에서 아이템 그리드에 들어갈 데이터를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMA05 TPLNIT 데이터 조회 */
	public List<TMAreaDTO> getTmAreaTplnitSearchList(TMAreaDTO tmArea){
		return tmAreaMapper.selectTma05ItemList(tmArea);
	}
	
	/*  getTmAreaTplnhdPopupSearchList - 모달 내 그리드 조회
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO
	*   설명			: 권역변경에서 모달 내 그리드에 들어갈 데이터를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMA05 TPLNHD 모달 데이터 조회 */
	public List<TMAreaDTO> getTmAreaTplnhdPopupSearchList(TMAreaDTO tmArea){
		return tmAreaMapper.selectTma05PopupList(tmArea);
	}
	
	/*  updateTmAreaTplnhd - 배차 조정
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: RequestDTO, TMAreaDTO - 그리드 List DTO / 권역배차, 마스터 DTO
	*   출력 PARAMETA	: int
	*   설명			: 권역변경에서 배차조정 모달에서 저장을 할 때 돌아가는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMA05 TPLNHD 권역키 조정 */
	public int updateTmAreaTplnhd(RequestDTO<TMAreaDTO> tmArea) {
		int resultCnt = 0;
		List<TMAreaDTO> headList = tmArea.getHeadGrid().getAddList();
		List<TMAreaDTO> updateList = tmArea.getItemGrid().getUpdateList();
		TMAreaDTO tplnit  = new TMAreaDTO();
		UserVO userData = headList.get(0).getUserData();
		String toVhplnky = headList.get(0).getVhplnky();
		
		for(int i = 0;i<updateList.size();i++) {
			tplnit.setCompkey(updateList.get(i).getCompkey());
			tplnit.setWarekey(updateList.get(i).getWarekey());
			tplnit.setFromvhplnky(updateList.get(i).getVhplnky());
			tplnit.setFromvhplnit(updateList.get(i).getVhplnit());
			tplnit.setTovhplnky(toVhplnky);
			tplnit.setUserData(userData);

			tmAreaMapper.sp_tm_plan_change(tplnit);

			if(tplnit.getOresult() != 0) {
				throw new ProcedureCheckedException(tplnit.getOmsgkey());
			}else {
				resultCnt++;
			}
		}
		return resultCnt;
	}
	
	/*  deleteTmAreaTplnitSearchList - 배차 취소
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: RequestDTO, TMAreaDTO - 그리드 List DTO / 권역배차, 마스터 DTO
	*   출력 PARAMETA	: int
	*   설명			: 권역변경에서 배차취소 모달에서 취소(저장)할 때 돌아가는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMA05 TPLNIT 권역아이템 배차취소 */
	public int deleteTmAreaTplnitSearchList(RequestDTO<TMAreaDTO> tmArea) {
		int resultCnt = 0;
		List<TMAreaDTO> deleteList = tmArea.getDeleteList();
		TMAreaDTO tplnit = new TMAreaDTO();
		
		if(tmArea.getDeleteList() != null) {
			UserVO userData = deleteList.get(0).getUserData();
			
			for(int i = 0;i<deleteList.size();i++) {
				tplnit.setUserData(userData);
				tplnit.setCompkey(deleteList.get(i).getCompkey());
				tplnit.setWarekey(deleteList.get(i).getWarekey());
				tplnit.setVhplnky(deleteList.get(i).getVhplnky());
				tplnit.setVhplnit(deleteList.get(i).getVhplnit());

				tmAreaMapper.sp_tm_plan_cancel(tplnit);

				if(tplnit.getOresult() != 0) {
					throw new ProcedureCheckedException(tplnit.getOmsgkey());
				}else {
					resultCnt++;
				}
			}
		}
		return resultCnt;
	}
	

	/*
	 * 프로그램 ID: TMA15
	 *프로그램 내용: 권역배차
	 */
	
	/*  getTmAreaOcosalSearchList - 그리드 조회
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: TMAreaDTO - 권역배차, 마스터 DTO
	*   출력 PARAMETA	: List<TMAreaDTO> - 권역배차, 마스터 DTO LiST
	*   설명			: 권역배차에서 그리드에 들어갈 데이터를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMA15 OCOSAL 권역리스트 조회 */
	public List<TMAreaDTO> getTmAreaOcosalSearchList(TMAreaDTO tmArea){
		return tmAreaMapper.selectOcosalWithTarehd(tmArea);
	}
	
	/*  saveAreaPlan - 권역배차 저장
	*   최초 생성일시	: 2023-12-14 09:15
	*   최초 생성자	: 최재환
	*   입력 PARAMETA	: RequestDTO, TMAreaDTO - 그리드 List DTO / 권역배차, 마스터 DTO
	*   출력 PARAMETA	: int
	*   설명			: 권역배차에서 권역배차 모달에서 저장할 때 돌아가는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	/* TMA15 권역배차 등록 */
	public int saveAreaPlan(RequestDTO<TMAreaDTO> tmArea) {
		List<TMAreaDTO> headList = tmArea.getHeadGrid().getUpdateList();
		List<TMAreaDTO> itemList = tmArea.getItemGrid().getUpdateList();

		String postdat = headList.get(0).getPostdat();
		int pbilpay = itemList.get(0).getPbilpay();
		String vehicky = itemList.get(0).getVehicky();
		TMAreaDTO head = new TMAreaDTO();
		String vhplnky = tmAreaMapper.selectVhplnky();
		int tplnhdCnt = 0;
		int tplnitCnt = 0;
		int updateCnt = 0;
		
		for(int i = 0;i<headList.size();i++) {
			if(postdat.compareTo(headList.get(i).getPostdat()) < 0) {
				postdat = headList.get(i).getPostdat();
			}
		}
		
		String compkey = headList.get(0).getCompkey();
		UserVO userData = headList.get(0).getUserData();

		head.setCompkey(compkey);
		head.setUserData(userData);
		head.setVhplnky(vhplnky);
		head.setDoctype("830");
		head.setDoccate("800");
		head.setPlnstat("PLAN");
		head.setPostdat(postdat.replace("-", ""));
		head.setWarekey(headList.get(0).getTowarky());
		head.setPbilpay(pbilpay);
		head.setVehicky(vehicky);

		tplnhdCnt += tmAreaMapper.insertTplnhd(head);

		if(tplnhdCnt == 0) {
			throw new InsertCheckedException();
		}
		
		for(int i = 0;i<headList.size();i++) {
			head.setCooutky(headList.get(i).getCooutky());
			head.setCooutit(headList.get(i).getCooutit());
			head.setDestkey(headList.get(i).getDestkey());
			head.setPostdat(headList.get(i).getPostdat().replace("-", ""));
			head.setSaatc06(headList.get(i).getSaatc06());
			head.setDoccate(headList.get(i).getDoccate());
			head.setDoctype(headList.get(i).getDoctype());

			List<TMAreaDTO> ocosalList = tmAreaMapper.selectOcosal(head);

			for(TMAreaDTO item : ocosalList) {
				if(item.getTrnstat().equals("NEW")) {
					item.setVhplnky(vhplnky);
					item.setShtrsts("PLAN");
					item.setCompkey(compkey);
					item.setUserData(userData);

					if(item.getDoctype().equals("230")) {
						item.setRetodyn("Y");
					}

					item.setTrnstat("PLAN");

					tplnitCnt += tmAreaMapper.insertTplnit(item);
					updateCnt += tmAreaMapper.updateOcosal(item);
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
}