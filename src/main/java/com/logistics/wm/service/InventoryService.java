package com.logistics.wm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.logistics.wm.domain.*;
import org.springframework.stereotype.Service;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.InitDataDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.ProcedureCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.MaremaDTO;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.domain.MdocmaDTO;
import com.logistics.md.domain.MlocmaDTO;
import com.logistics.md.domain.MowrmaDTO;
import com.logistics.md.domain.MrscmaDTO;
import com.logistics.md.domain.MskuwcDTO;
import com.logistics.md.domain.MwarmaDTO;
import com.logistics.md.domain.MzonmaDTO;
import com.logistics.md.mapper.CodeMapper;
import com.logistics.md.mapper.DocumentMapper;
import com.logistics.md.mapper.OrganizationMapper;
import com.logistics.md.mapper.PartnerMapper;
import com.logistics.md.mapper.UnitsMapper;
import com.logistics.sy.domain.UserVO;
import com.logistics.wm.mapper.InventoryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService{

	private final InventoryMapper inventoryMapper;
	private final PartnerMapper partnerMapper;
	private final OrganizationMapper organizationMapper;
	private final CodeMapper codeMapper;
	private final DocumentMapper documentMapper;
	private final UnitsMapper unitsMapper;
	
	/*  getWmi10SKUList - 재고조회(SKU탭) List호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고DTO
	*   출력 PARAMETA	: List<InventoryDTO> - 재고DTO 배열
	*   설명			: 재고조회페이지에서 SKU기준으로 재고목록을 호출하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	 public List<InventoryDTO> getWmi10SKUList(InventoryDTO param){
		 List<InventoryDTO> dataList = inventoryMapper.selectWmi10SKUList(param);
		 return dataList;
	 }
	 
 	/*  selectWmi10LOCAList - 재고조회(로케이션별탭) List호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고DTO
	*   출력 PARAMETA	: List<InventoryDTO> - 재고DTO 배열
	*   설명			: 재고조회페이지에서 로케이션기준으로 재고목록을 호출하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	 public List<InventoryDTO> selectWmi10LOCAList(InventoryDTO param){
		 List<InventoryDTO> dataList = inventoryMapper.selectWmi10LOCAList(param);
		 return dataList;
	 }
	
 	/*  getWmi10LOTList - 재고조회(LOT탭) List호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고DTO
	*   출력 PARAMETA	: List<InventoryDTO> - 재고DTO 배열
	*   설명			: 재고조회페이지에서 LOT NUMBER기준으로 재고목록을 호출하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	 public List<InventoryDTO> getWmi10LOTList(InventoryDTO param){
		 List<InventoryDTO> dataList = inventoryMapper.selectWmi10LOTList(param);
		 return dataList;
	 }

 	/*  getWmi10Init - 재고조회 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: Map<String, Object> - 호출한 데이터를 담을 Map
	*   설명			: 재고조회 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	 public Map<String, Object> getWmi10Init(CommonDTO common){
		Map<String , Object> returnParam = new HashMap<>();
		UserVO userData = common.getUserData();

		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(userData);
		returnParam.put("item1", partnerMapper.selectOwnerSelectBox(mowrmaDTO)); // 화주

		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(userData);
		returnParam.put("item2", organizationMapper.selectWarehouseUserSelectBox(mwarmaDTO)); // 창고

		McodemDTO dto = new McodemDTO();
		dto.setUserData(userData);
		dto.setComcdky("STKSTAT");
		returnParam.put("item3", codeMapper.selectMcodem(dto)); //재고상태
		return returnParam;
	 }

 	/*  getWmi12WstkkyList - 재고상태변경 List호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고DTO
	*   출력 PARAMETA	: List<InventoryDTO> - 재고DTO 배열
	*   설명			: 재고상태변경처리 페이지에서 상태를 변경할 재고목록을 호출하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public List<InventoryDTO> getWmi12WstkkyList(InventoryDTO param) {
		List<InventoryDTO> dataList = inventoryMapper.selectWmi12WstkkyList(param);
		return dataList;
	}

	/*  getWmi12Init - 재고상태변경처리 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고DTO
	*   출력 PARAMETA	: Map<String, Object> - 호출한 데이터를 담을 Map
	*   설명			: 재고조회 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public Map<String, Object> getWmi12Init(InventoryDTO inventoryDTO){
		Map<String, Object> returnParam = new HashMap<>();
		UserVO userData = inventoryDTO.getUserData();
		//화주
		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(userData);
		returnParam.put("item1", partnerMapper.selectOwnerSelectBox(mowrmaDTO)); // 화주

		//창고
		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(userData);
		List<CommonDTO> warekeyList = organizationMapper.selectWarehouseUserSelectBox(mwarmaDTO);
		returnParam.put("item2", warekeyList);

		//재고상태
		McodemDTO dto = new McodemDTO();
		dto.setUserData(userData);
		dto.setComcdky("STKSTAT");
		returnParam.put("item3", codeMapper.selectMcodem(dto)); //재고상태

		// zonekey 리스트
		MzonmaDTO mzonmaDTO = new MzonmaDTO();
		mzonmaDTO.setUserData(userData);
		mzonmaDTO.setWarekey(inventoryDTO.getWarekey());
		returnParam.put("item4", organizationMapper.selectMzonmaSelectBox(mzonmaDTO));

		// areakey 리스트
		MaremaDTO maremaDTO = new MaremaDTO();
		maremaDTO.setUserData(userData);
		maremaDTO.setWarekey(inventoryDTO.getWarekey());
		returnParam.put("item5", organizationMapper.selectMaremaSelectBox(maremaDTO));

		// locakey 리스트
		MlocmaDTO mlocmaDTO = new MlocmaDTO();
		mlocmaDTO.setUserData(userData);
		mlocmaDTO.setWarekey(inventoryDTO.getWarekey());
		returnParam.put("item6", organizationMapper.selectMlocmaSelectBox(mlocmaDTO));

		//doctype
		MdocmaDTO mdocmaDTO = new MdocmaDTO();
		mdocmaDTO.setUserData(userData);
		mdocmaDTO.setWarekey(inventoryDTO.getWarekey());
		returnParam.put("item7", documentMapper.selectMdocmaDoctypeList(mdocmaDTO));

		//doccate
		returnParam.put("item8", documentMapper.selectMdocmaDoccateGroupList(mdocmaDTO));
		
		return returnParam;
	}

	/*  saveWmi12 - 재고상태변경처리
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<InventoryDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int returnData - 성공시 1 반환
	*   설명			: 재고상태변경처리페이지에서 재고상태를 변경하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public int saveWmi12(RequestDTO<InventoryDTO> param, CommonDTO common){
		UserVO userData = common.getUserData();
		int returnData = 0;

		String adjsoky = inventoryMapper.selectAdjsoky(); 	//문서 번호키 채번
		int index = 0;
		if(param.getUpdateList() != null) {
			for(InventoryDTO inventoryDTO : param.getUpdateList()) {

					String adjgrky = inventoryMapper.selectAdjgrky();
					inventoryDTO.setStockky(param.getUpdateList().get(index++).getStockky());
					
					//상태를 변경하려는 재고의 변경이전(현재) 상태를 가져옴
					List<InventoryDTO> list = inventoryMapper.selectWmi12BeforeData(inventoryDTO);

					InventoryDTO beforeList = new InventoryDTO();
					
					//beforeList에 변경이전(현재) 값을 세팅
					beforeList = list.get(0);
					beforeList.setUserData(userData);
					beforeList.setDoctype("620");
					beforeList.setAdjsoky(adjsoky);
					beforeList.setAdjgrky(adjgrky);
					
					returnData = inventoryMapper.saveWmi12WadjitBeforeList(beforeList);

					if(returnData == 0) {
						throw new InsertCheckedException();
					}
					
					inventoryDTO.setUserData(userData);
					inventoryDTO.setAdjsoky(adjsoky);
					inventoryDTO.setAdjgrky(adjgrky);
					
					//변경한 값을 insert
					returnData = inventoryMapper.saveWmi12WadjitAfterList(inventoryDTO);

					if(returnData == 0) {
						throw new InsertCheckedException();
					}
					
					//재고조정 procedure
					//재고 상태를 변경한 후 원복시키면 해당 페이지에서 조회 시 분리되어있음(ex: 정상 -> 불용 -> 정상 으로 변경시 분리된 상태 유지)
					inventoryMapper.updateWmi22ToWstkkyList( inventoryDTO );
					int oresult = inventoryDTO.getOresult();
					if((oresult == -1)) {
						throw new ProcedureCheckedException((String) inventoryDTO.getOmsgkey());
					}else {
						returnData =1;
					}
			}
		}
		return returnData;
	}

	/*  getWmi20Init -  재고실사지시등록 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고DTO
	*   출력 PARAMETA	: Map<String , Object> returnParam - 호출한 데이터를 담을 Map
	*   설명			: 재고실사지시등록 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	 public Map<String, Object> getWmi20Init(InventoryDTO inventoryDTO) {

		 	Map<String , Object> returnParam = new HashMap<>();
		 	UserVO userData = inventoryDTO.getUserData();

		 	//화주
		 	MowrmaDTO mowrmaDTO = new MowrmaDTO();
		 	mowrmaDTO.setUserData(userData);
			returnParam.put("item1", partnerMapper.selectOwnerSelectBox(mowrmaDTO)); // 화주

			//창고
			MwarmaDTO mwarmaDTO = new MwarmaDTO();
			mwarmaDTO.setUserData(userData);
			List<CommonDTO> warekeyList = organizationMapper.selectWarehouseUserSelectBox(mwarmaDTO);
			returnParam.put("item2", warekeyList);

			//재고상태
			McodemDTO dto = new McodemDTO();
			dto.setUserData(userData);
			dto.setComcdky("STKSTAT");
			returnParam.put("item3", codeMapper.selectMcodem(dto)); //재고상태

			// zonekey 리스트
			MzonmaDTO mzonmaDTO = new MzonmaDTO();
			mzonmaDTO.setUserData(userData);
			mzonmaDTO.setWarekey(inventoryDTO.getWarekey());
			returnParam.put("item4", organizationMapper.selectMzonmaSelectBox(mzonmaDTO));

			// areakey 리스트
			MaremaDTO maremaDTO = new MaremaDTO();
			maremaDTO.setUserData(userData);
			maremaDTO.setWarekey(inventoryDTO.getWarekey());
			returnParam.put("item5", organizationMapper.selectMaremaSelectBox(maremaDTO));

			//doctype
			MdocmaDTO mdocmaDTO = new MdocmaDTO();
			mdocmaDTO.setUserData(userData);
			mdocmaDTO.setWarekey(inventoryDTO.getWarekey());
			returnParam.put("item6", documentMapper.selectMdocmaDoctypeList(mdocmaDTO));

			//doccate
			returnParam.put("item7", documentMapper.selectMdocmaDoccateGroupList(mdocmaDTO));

			return returnParam;
	}

 	/*  saveWphyList - 재고실사지시등록 저장
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<InventoryDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	:  Map<String, String> phyMap - 성공시 재고실사지시서를 출력하기 위해 채번한 실사문서번호를 담을 Map
	*   설명			: 재고실사지시를 등록하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	 public Map<String, String> saveWphyList(RequestDTO<InventoryDTO> param, CommonDTO common) {
		 UserVO userData = common.getUserData();
		 Map<String, String> phyMap = new HashMap<>();

		 if(param.getUpdateList() != null) {
			 String physoky = inventoryMapper.getPhysoky();
			 phyMap.put("physoky", physoky);
			 
			 for(InventoryDTO inventoryDTO : param.getUpdateList()) {
				 inventoryDTO.setUserData(userData);
				 inventoryDTO.setPhysoky(physoky);
				 inventoryDTO.setPhymode("READY");

				if(inventoryMapper.saveWphyList(inventoryDTO) == 0) {
					throw new InsertCheckedException();
				}
			 }
		 }
		 return phyMap;
	 }


 	/*  getWmi20List - 재고 리스트 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고 DTO
	*   출력 PARAMETA	: List<InventoryDTO> - 입하 DTO의 리스트
	*   설명			: 재고실사지시등록 페이지에서 실사지시를 내릴 재고 목록을 보여주는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	 public List<InventoryDTO> getWmi20List(InventoryDTO param){
		 List<InventoryDTO> dataList = inventoryMapper.selectWmi20WstkkyList(param);
		 return dataList;
	 }


 	/*  getWmi21Init - 재고실사등록 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고DTO
	*   출력 PARAMETA	: Map<String , Object> returnParam - 호출한 데이터를 담을 Map
	*   설명			: 재고실사등록 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	 public Map<String, Object> getWmi21Init(InventoryDTO inventoryDTO){

		Map<String , Object> returnParam = new HashMap<>();
		UserVO userData = inventoryDTO.getUserData();
	 	//화주
		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(userData);
	 	List<CommonDTO> ownerkyList = partnerMapper.selectOwnerSelectBox(mowrmaDTO);
		returnParam.put("item1", ownerkyList); // 화주

		//창고
		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(userData);
		List<CommonDTO> warekeyList = organizationMapper.selectWarehouseUserSelectBox(mwarmaDTO);
		returnParam.put("item2", warekeyList);
		
		//doctype
		MdocmaDTO mdocmaDTO = new MdocmaDTO();
		mdocmaDTO.setUserData(userData);
		mdocmaDTO.setWarekey(inventoryDTO.getWarekey());
		returnParam.put("item3", documentMapper.selectMdocmaDoctypeList(mdocmaDTO));

		//doccate
		returnParam.put("item4", documentMapper.selectMdocmaDoccateGroupList(mdocmaDTO));
		
		// areakey 리스트
		MaremaDTO maremaDTO = new MaremaDTO();
		maremaDTO.setUserData(userData);
		maremaDTO.setWarekey(inventoryDTO.getWarekey());
		returnParam.put("item5", organizationMapper.selectMaremaSelectBox(maremaDTO));

		// zonekey 리스트
		MzonmaDTO mzonmaDTO = new MzonmaDTO();
		mzonmaDTO.setUserData(userData);
		mzonmaDTO.setWarekey(inventoryDTO.getWarekey());
		returnParam.put("item6", organizationMapper.selectMzonmaSelectBox(mzonmaDTO));

		// locakey 리스트
		MlocmaDTO mlocmaDTO = new MlocmaDTO();
		mlocmaDTO.setUserData(userData);
		mlocmaDTO.setWarekey(inventoryDTO.getWarekey());
		returnParam.put("item7", organizationMapper.selectMlocmaSelectBox(mlocmaDTO));

		return returnParam;
	 }

 	/*  getWmi21HeadList - 재고실사등록 HeaderList 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고DTO
	*   출력 PARAMETA	: List<InventoryDTO> - 재고DTO 배열
	*   설명			: 재고실사등록 페이지 상단의 재고실사지시서목록을 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	 public List<InventoryDTO> getWmi21HeadList(InventoryDTO param){
		 List<InventoryDTO> dataList = inventoryMapper.selectWmi21HeaderList(param);
		 return dataList;
	 }

 	/*  getWmi21ItemList - 재고실사등록 ItemList 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고DTO
	*   출력 PARAMETA	: List<InventoryDTO> - 재고DTO 배열
	*   설명			: 재고실사등록 페이지 상단의 실사번호를 바탕으로 하단의 재고실사지시서목록을 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	 public List<InventoryDTO> getWmi21ItemList(InventoryDTO param){
		 List<InventoryDTO> dataList = inventoryMapper.selectWmi21ItemList(param);
		 return dataList;
	 }

 	/*  saveWmi21Phy - 재고실사등록
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<InventoryDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int returnData - 성공시 1 반환
	*   설명			: 재고실사등록 페이지에서 하단 itemList에서 수정한 값을 저장하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	 public int saveWmi21Phy(RequestDTO<InventoryDTO> param, CommonDTO common) {
		 UserVO userData = common.getUserData();
		 int returnData = 0;

		 if(param.getUpdateList() != null) {
			 for(InventoryDTO inventoryDTO : param.getUpdateList()) {
				 inventoryDTO.setUserData(userData);
				 returnData = inventoryMapper.saveWmi21Physqty(inventoryDTO);

				 if(returnData == 0) {
					 throw new UpdateCheckedException();
				 }

				 returnData = inventoryMapper.saveWmi21PhyMode(inventoryDTO);

				 if(returnData == 0) {
					 throw new UpdateCheckedException();
				 }
			 }
		 }

		 return returnData;
	 }

 	/*  getWmi22Init - 재고실사조정확정 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고DTO
	*   출력 PARAMETA	: Map<String , Object> returnParam - 호출한 데이터를 담을 Map
	*   설명			: 재고실사조정확정 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	 public Map<String, Object> getWmi22Init(InventoryDTO inventoryDTO){
		 Map<String , Object> returnParam = new HashMap<>();
		 UserVO userData = inventoryDTO.getUserData();

	 	//화주
		MowrmaDTO mowrmaDTO  = new MowrmaDTO();
		mowrmaDTO.setUserData(userData);
		returnParam.put("item1", partnerMapper.selectOwnerSelectBox(mowrmaDTO)); // 화주

		//창고
		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(userData);
		List<CommonDTO> warekeyList = organizationMapper.selectWarehouseUserSelectBox(mwarmaDTO);
		returnParam.put("item2", warekeyList);

		//재고상태
		McodemDTO dto = new McodemDTO();
		dto.setComcdky("STKSTAT");
		dto.setUserData(userData);
		returnParam.put("item3", codeMapper.selectMcodem(dto)); //재고상태

		// zonekey 리스트
		MzonmaDTO mzonmaDTO = new MzonmaDTO();
		mzonmaDTO.setUserData(userData);
		mzonmaDTO.setWarekey(inventoryDTO.getWarekey());
		returnParam.put("item4", organizationMapper.selectMzonmaSelectBox(mzonmaDTO));

		// areakey 리스트
		MaremaDTO maremaDTO = new MaremaDTO();
		maremaDTO.setUserData(userData);
		maremaDTO.setWarekey(inventoryDTO.getWarekey());
		returnParam.put("item5", organizationMapper.selectMaremaSelectBox(maremaDTO));

		// locakey 리스트
		MlocmaDTO mlocmaDTO = new MlocmaDTO();
		mlocmaDTO.setUserData(userData);
		mlocmaDTO.setWarekey(inventoryDTO.getWarekey());
		returnParam.put("item6", organizationMapper.selectMlocmaSelectBox(mlocmaDTO));

		//doctype
		MdocmaDTO mdocmaDTO = new MdocmaDTO();
		mdocmaDTO.setUserData(userData);
		mdocmaDTO.setWarekey(inventoryDTO.getWarekey());
		returnParam.put("item7", documentMapper.selectMdocmaDoctypeList(mdocmaDTO));

		//doccate
		returnParam.put("item8", documentMapper.selectMdocmaDoccateGroupList(mdocmaDTO));

		return returnParam;
	 }


 	/*  saveWmi22 - 재고실사조정확정
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<InventoryDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int returnData - 성공시 1 반환
	*   설명			: 재고실사지시등록에서 등록한 실사수량을 확정하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	 public int saveWmi22(RequestDTO<InventoryDTO> param, CommonDTO common) {
		 UserVO userData = common.getUserData();
		 int returnData = 0;

		 if(param.getUpdateList() != null) {
			 String wajdsoky = inventoryMapper.selectAdjsoky();

			 for(InventoryDTO inventoryDTO : param.getUpdateList()) {
				 //시스템 수량이 확정수량과 다를경우
				 if(inventoryDTO.getSystqty() != inventoryDTO.getCompqty()) {
					 inventoryDTO.setUserData(userData);
					 returnData = inventoryMapper.updateWmi22Comqty(inventoryDTO);

					 if(returnData == 0) {
						 throw new UpdateCheckedException();
					 }
					 String wajgrky = inventoryMapper.selectAdjgrky();

					 //BeforeList
					 //0번째를 넣거나 쿼리를 바꿀것
					 List<InventoryDTO> list = inventoryMapper.selectWmi22ItemList(inventoryDTO);
					 InventoryDTO beforeList = new InventoryDTO();
						beforeList = list.get(0);
						beforeList.setUserData(userData);
						beforeList.setAdjgrky(wajgrky);
						beforeList.setAdjsoky(wajdsoky);

						 returnData = inventoryMapper.saveWmi22WadjitBeforeList(beforeList);
						 if(returnData == 0) {
							 throw new InsertCheckedException();
						 }
						 //AfterList
						 returnData = inventoryMapper.saveWmi22WadjitAfterList(beforeList);
						 if(returnData == 0) {
							 throw new InsertCheckedException();
						 }
						 //Procedure Call
						 inventoryMapper.updateWmi22ToWstkkyList(beforeList);

						 if((beforeList.getOresult()== -1)){
							 throw new ProcedureCheckedException((String) beforeList.getOmsgkey());
						 }
						 if((beforeList.getOresult() == 1)) {
							 String physrmk = beforeList.getOmsgkey(); 
							 beforeList.setPhysrmk(physrmk);
							 //해당 문서 complete 처리
							 int updateStatusCnt = inventoryMapper.updateWmi22NotHaveStockky(beforeList); 

							 if(updateStatusCnt == 0) {
								 throw new UpdateCheckedException();
							 }
						 }

				 } else {
					 //재고조정 확정 완료
					 returnData = inventoryMapper.updateWmi22Comqty(inventoryDTO);
					 if(returnData == 0) {
						 throw new UpdateCheckedException();
					 }
				 }
				 returnData = inventoryMapper.updateWmi22Completed(inventoryDTO);
				 if(returnData == 0) {
					 throw new UpdateCheckedException();
				 }
			 }

		 }
		 return returnData;
	 }

 	/*  updateWmi22HeaderClosing - 재고실사조정확정 HeaderClosing
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<InventoryDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int returnData - 성공시 1 반환
	*   설명			: 재고실사조정확정 페이지에서 상단의 실사문서 전체를 Closing처리하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	 public int updateWmi22HeaderClosing(RequestDTO<InventoryDTO> param) {
		 int returnData = 0;
		 if(param.getUpdateList() != null) {
			 for(InventoryDTO inventoryDTO : param.getUpdateList()) {
				 inventoryDTO.setPhymode("COMPLETED");
				 returnData  = inventoryMapper.updateWmi22HeaderClosing(inventoryDTO);

				 if(returnData == 0) {
					 new UpdateCheckedException();
				 }
			 }
		 }
		 return returnData;
	 }

 	/*  updateWmi22ItemClosing - 재고실사조정확정 ItemClosing
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<InventoryDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int returnData - 성공시 1 반환
	*   설명			: 재고실사조정확정 페이지에서 하단의 선택한 실사문서 일부를 Closing처리하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	 public int updateWmi22ItemClosing(RequestDTO<InventoryDTO> param) {
		 int returnData = 0;
		 if(param.getUpdateList() != null) {
			 for(InventoryDTO inventoryDTO : param.getUpdateList()) {
				 inventoryDTO.setPhymode("COMPLETED");
				 returnData  = inventoryMapper.updateWmi22ItemClosing(inventoryDTO);

				 if(returnData == 0) {
					 new UpdateCheckedException();
				 }
			 }
		 }
		 return returnData;
	 }

 	/*  getWmi22HeaderList - 재고실사조정확정 HeaderList 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고DTO
	*   출력 PARAMETA	: List<InventoryDTO> - 재고DTO 배열
	*   설명			: 재고실사조정확정 페이지 상단의 헤더리스트를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	 public List<InventoryDTO> getWmi22HeaderList(InventoryDTO param){
		 List<InventoryDTO> dataList = inventoryMapper.selectWmi22HeaderList(param);
		 return dataList;
	 }

 	/*  getWmi22ItemList - 재고실사조정확정 ItemList 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고DTO
	*   출력 PARAMETA	: List<InventoryDTO> - 재고DTO 배열
	*   설명			: 재고실사조정확정 페이지 하단의 아이템리스트를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	 public List<InventoryDTO> getWmi22ItemList(InventoryDTO param){
		 List<InventoryDTO> dataList = inventoryMapper.selectWmi22ItemList(param);
		 return dataList;
	 }
	 
 	/*  getWmi32InitData - 재고조정오더확정 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 입하DTO
	*   출력 PARAMETA	: Map<String, Object> - 호출한 데이터를 담을 Map
	*   설명			: 재고조정오더확정 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	 public Map<String, Object> getWmi32InitData(InventoryDTO inventoryDTO){
		 Map<String , Object> returnParam = new HashMap<>();
		 UserVO userData = inventoryDTO.getUserData();
		 
		//창고
		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(userData);
		List<CommonDTO> warekeyList = organizationMapper.selectWarehouseUserSelectBox(mwarmaDTO);
		returnParam.put("warekeyList", warekeyList);
		
		//화주
		MowrmaDTO mowrmaDTO  = new MowrmaDTO();
		mowrmaDTO.setUserData(userData);
		returnParam.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDTO)); // 화주
		
		//doctype
		MdocmaDTO mdocmaDTO = new MdocmaDTO();
		mdocmaDTO.setUserData(userData);
		mdocmaDTO.setWarekey(inventoryDTO.getWarekey());
		returnParam.put("doctypeList", documentMapper.selectMdocmaDoctypeList(mdocmaDTO));

		//doccate
		returnParam.put("doccateList", documentMapper.selectMdocmaDoccateGroupList(mdocmaDTO));
		
		//오더상태
		McodemDTO mcodemDto = new McodemDTO();
		mcodemDto.setUserData(userData);
		mcodemDto.setComcdky("OEINSST");
		returnParam.put("oeinsstList", codeMapper.selectMcodem(mcodemDto)); //재고상태
		
		//재고상태
		mcodemDto.setComcdky("STKSTAT");
		returnParam.put("atkstatList", codeMapper.selectMcodem(mcodemDto));
		
		//조정사유코드
		MrscmaDTO mrscmaDTO = new MrscmaDTO();
		mrscmaDTO.setUserData(userData);
		mrscmaDTO.setWarekey(inventoryDTO.getWarekey());
		returnParam.put("rsncodeList", documentMapper.selectMrscmaSelectBox(mrscmaDTO));
		
		// areakey 리스트
		MaremaDTO maremaDTO = new MaremaDTO();
		maremaDTO.setUserData(userData);
		maremaDTO.setWarekey(inventoryDTO.getWarekey());
		returnParam.put("areakeyList", organizationMapper.selectMaremaSelectBox(maremaDTO));
		
		// zonekey 리스트
		MzonmaDTO mzonmaDTO = new MzonmaDTO();
		mzonmaDTO.setUserData(userData);
		mzonmaDTO.setWarekey(inventoryDTO.getWarekey());
		returnParam.put("zonekeyList", organizationMapper.selectMzonmaSelectBox(mzonmaDTO));

		// locakey 리스트
		MlocmaDTO mlocmaDTO = new MlocmaDTO();
		mlocmaDTO.setUserData(userData);
		mlocmaDTO.setWarekey(inventoryDTO.getWarekey());
		returnParam.put("locakeyList", organizationMapper.selectMlocmaSelectBox(mlocmaDTO));
		
		return returnParam;
	 }
	 
 	/*  getWmi32List - 재고조정오더 목록 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: InventoryDTO - 재고DTO
	*   출력 PARAMETA	: List<InventoryDTO> - 재고DTO 배열
	*   설명			: 재고조정오더확정 페이지에서 조정 오더목록을 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	 public List<InventoryDTO> getWmi32List(InventoryDTO param){
		 List<InventoryDTO> dataList = inventoryMapper.selectWmi32List(param);
		 return dataList;
	 }
	 
 	/*  saveWmi32List - 재고조정오더확정
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<InventoryDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int returnData - 성공시 1 반환
	*   설명			: 재고조정오더확정 페이지에서 조정오더를 확정하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	 public int saveWmi32List(RequestDTO<InventoryDTO> param, CommonDTO common) {
		 UserVO userData = common.getUserData();
		 int returnData = 0;
		 
		 if(param.getUpdateList() != null) {
			 
			 for(InventoryDTO inventoryDTO : param.getUpdateList()) {
				 inventoryDTO.setUserData(userData);
				 inventoryDTO.setAdjsoit(0);
				 
				 inventoryMapper.updateWmi22ToWstkkyList(inventoryDTO);
				 if(inventoryDTO.getOresult() != 0) {
					 throw new ProcedureCheckedException((String) inventoryDTO.getOmsgkey());
				 } else {
					 returnData = 1;
				 }
				 
				 inventoryDTO.setOeinsst("CMP");
				 returnData = inventoryMapper.updateWmi32List(inventoryDTO);
				 
				 if(returnData == 0) {
					 throw new UpdateCheckedException();
				 }
			 }
		 }
		 
		 return returnData;
	 }

	 /*
     * 프로그램 ID : WMI11
     * 프로그램 내용: 재고수량변경처리
     * */
	public InitDataDTO getWmi11InitData(CommonDTO param) {
		InitDataDTO initDataDto = new InitDataDTO();
		Map<String , Object> returnParam = new HashMap<>();
		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(param.getUserData());
		returnParam.put("warekeyList", organizationMapper.selectWarehouseUserSelectBox(mwarmaDTO)); // 창고
		

		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(param.getUserData());
		returnParam.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDTO)); // 화주

		McodemDTO mcodemDto = new McodemDTO();
		mcodemDto.setUserData(param.getUserData());
		mcodemDto.setComcdky("STKSTAT");
		returnParam.put("wstkkyList", codeMapper.selectMcodem(mcodemDto)); //재고상태
		
		initDataDto.setItem(returnParam);
		return initDataDto;
	}

	public List<InventoryDTO> getWmi11WstkkyList(InventoryDTO param) {
		return inventoryMapper.getWmi11WstkkyList(param);
	}

	public int saveWmi11(RequestDTO<InventoryDTO> param){
		List<InventoryDTO> updateList = param.getUpdateList();
		int resultCnt = 0;

		for(InventoryDTO updateRow : updateList) {
			String adjsoky = inventoryMapper.getAdjsoky(); 	//헤더 문서 번호키 채번
			String adjgrky = inventoryMapper.getAdjgrky();
			
			InventoryDTO beforeData = inventoryMapper.getWmi11BeforeData(updateRow);
			beforeData.setAdjsoky(adjsoky);
			beforeData.setAdjgrky(adjgrky);
			beforeData.setDoccate("600");
			beforeData.setDoctype("630");
			beforeData.setCreuser(updateRow.getUserData().getUseract());
			beforeData.setLmouser(updateRow.getUserData().getUseract());
			
			int beforeCnt = inventoryMapper.saveWadjitBEFORELIST(beforeData);
	
			if(beforeCnt == 0) {
				throw new InsertCheckedException();
			}

			updateRow.setAdjsoky(adjsoky);
			updateRow.setAdjgrky(adjgrky);
			updateRow.setDoccate("600");
			updateRow.setDoctype("630");
			updateRow.setCreuser(updateRow.getUserData().getUseract());
			updateRow.setLmouser(updateRow.getUserData().getUseract());
			
			int afterCnt = inventoryMapper.saveWadjitAFTERLIST(updateRow); //변경 수량
	
			if(afterCnt == 0) {
				throw new InsertCheckedException();
			}
			
			inventoryMapper.updateWstkkyList( updateRow );
	
			if(updateRow.getOresult() != 0) {
				throw new ProcedureCheckedException(updateRow.getOmsgkey());
			}else {
				resultCnt++;
			}
		}
		return resultCnt;
	}


	/*
     * 프로그램 ID : WMI30
     * 프로그램 내용: 재고블락처리
     * */

	public InitDataDTO getWmi30InitData(CommonDTO param) {
		InitDataDTO initDataDto = new InitDataDTO();
		Map<String , Object> returnParam = new HashMap<>();
		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(param.getUserData());
		returnParam.put("warekeyList", organizationMapper.selectWarehouseUserSelectBox(mwarmaDTO)); // 창고
		

		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(param.getUserData());
		returnParam.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDTO)); // 화주

		McodemDTO mcodemDto = new McodemDTO();
		mcodemDto.setUserData(param.getUserData());
		mcodemDto.setComcdky("STKSTAT");
		returnParam.put("wstkkyList", codeMapper.selectMcodem(mcodemDto)); //재고상태
		
		initDataDto.setItem(returnParam);
		
		return initDataDto;
	}
	
	public List<InventoryDTO> getWmi30WstkkyList(InventoryDTO param) {
		return inventoryMapper.getWmi30WstkkyList(param);
	}
	
	public int saveWmi30(RequestDTO<InventoryDTO> param) {
		List<InventoryDTO> updateList = param.getUpdateList();
		int resultCnt = 0;
		for (InventoryDTO updateRow : updateList) {
			String adjsoky = inventoryMapper.getAdjsoky(); // 헤더 문서 번호키 채번
			String adjgrky = inventoryMapper.getAdjgrky(); // 조정 그룹키 채번

			InventoryDTO beforeData = inventoryMapper.getWmi30BeforeData(updateRow);
			beforeData.setAdjsoky(adjsoky);
			beforeData.setAdjgrky(adjgrky);
			beforeData.setDoccate("600");
			beforeData.setDoctype("640");
			beforeData.setCreuser(updateRow.getUserData().getUseract());
			beforeData.setLmouser(updateRow.getUserData().getUseract());

			int beforeCnt = inventoryMapper.saveWadjitBEFORELIST(beforeData);
			if(beforeCnt == 0) {
				throw new InsertCheckedException();
			}

			int newSbloqty = updateRow.getScbloqty() + updateRow.getSbloqty();
			updateRow.setSbloqty(newSbloqty);
			updateRow.setAdjsoky(adjsoky);
			updateRow.setAdjgrky(adjgrky);
			updateRow.setDoccate("600");
			updateRow.setDoctype("640");
			updateRow.setCreuser(updateRow.getUserData().getUseract());
			updateRow.setLmouser(updateRow.getUserData().getUseract());

			int afterCnt = inventoryMapper.saveWmi30WadjitAFTERLIST(updateRow);
			if(afterCnt == 0) {
				throw new InsertCheckedException();
			}

			inventoryMapper.updateWstkkyList(updateRow); 

			if(updateRow.getOresult()!= 0) {
				throw new ProcedureCheckedException(updateRow.getOmsgkey());
			}else {
				resultCnt++;
			}
		}
		return resultCnt;
	}

	/*
     * 프로그램 ID : WMI31
     * 프로그램 내용: 블락해제처리
     * */
	
	public InitDataDTO getWmi31InitData(CommonDTO param) {
		Map<String , Object> returnParam = new HashMap<>();
		InitDataDTO initDataDto = new InitDataDTO();
		
		MowrmaDTO mowrmaDto = new MowrmaDTO();
		mowrmaDto.setUserData(param.getUserData());
		returnParam.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDto)); // 화주
		
		MwarmaDTO mwarmaDto = new MwarmaDTO();
		mwarmaDto.setUserData(param.getUserData());
		returnParam.put("warekeyList", organizationMapper.selectWarehouseUserSelectBox(mwarmaDto)); // 창고

		McodemDTO mcodemDto = new McodemDTO();
		mcodemDto.setUserData(param.getUserData());
		mcodemDto.setComcdky("STKSTAT");
		returnParam.put("stkstatList", codeMapper.selectMcodem(mcodemDto)); //재고상태
		
		initDataDto.setItem(returnParam);
		return initDataDto;
	}
	
	public List<InventoryDTO> getWmi31WstkkyList(InventoryDTO param) {
		return inventoryMapper.getWmi31WstkkyList(param);
	}
	
	public int saveWmi31(RequestDTO<InventoryDTO> param) {
		List<InventoryDTO> updateList = param.getUpdateList();
		int resultCount = 0;
		if(updateList != null) {
			for(InventoryDTO updateRow : updateList) {
				InventoryDTO beforeData = inventoryMapper.getWmi31BeforeData(updateRow);
	
				String adjsoky = inventoryMapper.getAdjsoky();
				String adjgrky = inventoryMapper.getAdjgrky();
				beforeData.setAdjsoky(adjsoky);
				beforeData.setAdjgrky(adjgrky);
				beforeData.setDoccate("600");
				beforeData.setDoctype("641");
				beforeData.setCreuser(updateRow.getUserData().getUseract());
				beforeData.setLmouser(updateRow.getUserData().getUseract());
				
				int beforeCnt = inventoryMapper.saveWadjitBEFORELIST(beforeData);
				if(beforeCnt == 0) {
					throw new InsertCheckedException();
				}
	
				int sbloqty = updateRow.getSbloqty() - updateRow.getScbloqty();
				updateRow.setSbloqty(sbloqty); // 블록수량 = 블락수량 - 블락해제수량
				updateRow.setAdjsoky(adjsoky);
				updateRow.setAdjgrky(adjgrky);
				updateRow.setDoccate("600");
				updateRow.setDoctype("641");
				updateRow.setCreuser(updateRow.getUserData().getUseract());
				updateRow.setLmouser(updateRow.getUserData().getUseract());
				
				InventoryDTO afterData = new InventoryDTO(); 
				afterData =	updateRow;
	
				int afterCnt = inventoryMapper.saveWmi31WadjitAFTERLIST(afterData); //블록 수량
				if(afterCnt == 0) {
					throw new InsertCheckedException();
				}
	
				inventoryMapper.updateWstkkyList(afterData);
	
				if(afterData.getOresult()!=0) {
					throw new ProcedureCheckedException(afterData.getOmsgkey());
				}else {
					resultCount++;
				}
			}
		}
		return resultCount;
	}

	/*
     * 프로그램 ID : WMI40
     * 프로그램 내용: 일별재고 리스트
     * */
	
	public List<WstkdyDTO> getWmi40WstkdyList(WstkdyDTO param) {
		return inventoryMapper.getWmi40WstkdyList(param); 
	}
	
	/*
     * 프로그램 ID : WMI41
     * 프로그램 내용: 문서별 재고 추적
     * */
	
	public List<WtrhisDTO> getWmi41WtrhisList(WtrhisDTO param) {
		return inventoryMapper.getWmi41WtrhisList(param); 
	}

	/*
     * 프로그램 ID : WMI42
     * 프로그램 내용: 재고부품변경처리
     * */
	public InitDataDTO getWmi42InitData(CommonDTO param) {
		Map<String , Object> returnParam = new HashMap<>();
		InitDataDTO initDataDto = new InitDataDTO();
		
		MwarmaDTO mwarmaDto = new MwarmaDTO();
		mwarmaDto.setUserData(param.getUserData());
		returnParam.put("warekeyList", organizationMapper.selectMwarmaList(mwarmaDto)); // 창고
		
		MowrmaDTO mowrmaDto = new MowrmaDTO();
		mowrmaDto.setUserData(param.getUserData());
		returnParam.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDto)); // 화주
		
		MrscmaDTO mrscmaDto = new MrscmaDTO();
		mrscmaDto.setDoccate("600");
		mrscmaDto.setDoctype("610");
		mrscmaDto.setUserData(param.getUserData());
		returnParam.put("rsncodeList", documentMapper.selectMrscmaSelectBox(mrscmaDto)); // 창고

		McodemDTO mcodemDto = new McodemDTO();
		mcodemDto.setUserData(param.getUserData());
		mcodemDto.setComcdky("STKSTAT");
		returnParam.put("stkstatList", codeMapper.selectMcodem(mcodemDto)); //재고상태
		
		MskuwcDTO mskuwcdto = new MskuwcDTO();
		mskuwcdto.setUserData(param.getUserData());
		returnParam.put("skumkeyList", unitsMapper.selectSkuwcBoxCustom(mskuwcdto));
		
		initDataDto.setItem(returnParam);
		return initDataDto;
	}

	public List<InventoryDTO> getWmi42List(InventoryDTO param) {
		return inventoryMapper.getWmi42List(param);
	}

	public int saveWmi42List(RequestDTO<InventoryDTO> param) {
		List<InventoryDTO> updateList = param.getUpdateList();
		int resultCount = 0;
		if(updateList != null) {
			for(InventoryDTO updateRow : updateList) {
				InventoryDTO beforeData = inventoryMapper.getWmi42BeforeData(updateRow);
	
				String adjsoky = inventoryMapper.getAdjsoky();
				String adjgrky = inventoryMapper.getAdjgrky();
				beforeData.setAdjsoky(adjsoky);
				beforeData.setAdjgrky(adjgrky);
				beforeData.setDoccate("600");
				beforeData.setDoctype("670");
				beforeData.setCreuser(updateRow.getUserData().getUseract());
				beforeData.setLmouser(updateRow.getUserData().getUseract());
				
				int beforeCnt = inventoryMapper.saveWadjitBEFORELIST(beforeData);
				if(beforeCnt == 0) {
					throw new InsertCheckedException();
				}
	
				updateRow.setAdjsoky(adjsoky);
				updateRow.setAdjgrky(adjgrky);
				updateRow.setDoccate("600");
				updateRow.setDoctype("670");
				updateRow.setCreuser(updateRow.getUserData().getUseract());
				updateRow.setLmouser(updateRow.getUserData().getUseract());
				
				InventoryDTO afterData = new InventoryDTO(); 
				afterData =	updateRow;
	
				int afterCnt = inventoryMapper.saveWmi42WadjitAFTERLIST(afterData); //블록 수량
				if(afterCnt == 0) {
					throw new InsertCheckedException();
				}
	
				inventoryMapper.updateWstkkyList(afterData);
	
				if(afterData.getOresult()!=0) {
					throw new ProcedureCheckedException(afterData.getOmsgkey());
				}else {
					resultCount++;
				}
			}
		}
		return resultCount;
	}
}
