package com.logistics.wm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.logistics.md.domain.MskuwcDTO;
import com.logistics.md.domain.MwarmaDTO;
import com.logistics.md.domain.MzonmaDTO;
import com.logistics.md.mapper.CodeMapper;
import com.logistics.md.mapper.DocumentMapper;
import com.logistics.md.mapper.OrganizationMapper;
import com.logistics.md.mapper.PartnerMapper;
import com.logistics.sy.domain.UserVO;
import com.logistics.wm.domain.SetOrderDTO;
import com.logistics.wm.domain.TaskDTO;
import com.logistics.wm.mapper.TaskMapper;

import lombok.RequiredArgsConstructor;  

@Service
@RequiredArgsConstructor
public class TaskService{
	/* 
	 * ******************************************** 
	  - Service Name   : TaskService
	  - Description    : 작업 Service
	  - Made By        : Choi Kangho
	  - Creation Date  : 2023.07.25
	  ------------------------------------------
	* ********************************************  
	*/
	
	private final TaskMapper taskMapper;
	private final OrganizationMapper organizationMapper;
	private final PartnerMapper partnerMapper;
	private final DocumentMapper documentMapper;
	private final CodeMapper codeMapper;
	
	/*  getWmt10Init - 이동지시 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: TaskDTO - 작업DTO
	*   출력 PARAMETA	: Map<String, Object> - 호출한 데이터를 담을 Map
	*   설명			: 이동지시 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public Map<String, Object> getWmt10Init(TaskDTO taskDTO){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserVO userData = taskDTO.getUserData();
		
		// 창고 리스트
		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(userData);
		List<CommonDTO> warekeyList = organizationMapper.selectWarehouseUserSelectBox(mwarmaDTO);
		resultMap.put("warekeyList", warekeyList);

		// 화주 리스트
		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(userData);
		resultMap.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDTO));

		//doctype 리스트
		MdocmaDTO mdocmaDTO = new MdocmaDTO();
		mdocmaDTO.setUserData(userData);
		mdocmaDTO.setWarekey(taskDTO.getWarekey());
		resultMap.put("doctypeList", documentMapper.selectMdocmaDoctypeList(mdocmaDTO));
		resultMap.put("doccateList", documentMapper.selectMdocmaDoccateList(mdocmaDTO));
		
		//Areakey 리스트
		MaremaDTO maremaDTO = new MaremaDTO();
		maremaDTO.setUserData(userData);
		maremaDTO.setWarekey(taskDTO.getWarekey());
		resultMap.put("areakeyList", organizationMapper.selectMaremaSelectBox(maremaDTO));

		//Zonekey 리스트
		MzonmaDTO mzonmaDTO = new MzonmaDTO();
		mzonmaDTO.setUserData(userData);
		mzonmaDTO.setWarekey(taskDTO.getWarekey());
		resultMap.put("zonekeyList", organizationMapper.selectMzonmaSelectBox(mzonmaDTO));

		//Locakey 리스트
		MlocmaDTO mlocmaDTO = new MlocmaDTO();
		mlocmaDTO.setUserData(userData);
		mlocmaDTO.setWarekey(taskDTO.getWarekey());
		resultMap.put("locakeyList", organizationMapper.selectMlocmaSelectBox(mlocmaDTO));
		
		//재고상태
		McodemDTO dto = new McodemDTO();
		dto.setComcdky("STKSTAT");
		dto.setUserData(userData);
		resultMap.put("stkstatList", codeMapper.selectMcodem(dto)); //재고상태
		
		return resultMap;
	}
	
	/*  getWmt10List - 이동지시 List호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: TaskDTO - 작업DTO
	*   출력 PARAMETA	: List<TaskDTO> - 작업DTO 배열
	*   설명			: 이동지시 페이지에서 이동지시를 내릴 재고를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public List<TaskDTO> getWmt10List(TaskDTO param){
		List<TaskDTO> dataList = taskMapper.selectWmt10List(param);
		return dataList;
	}
	
	/*  getWmt10ZoneList - 이동지시 to zone List호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: MlocmaDTO - 로케이션DTO
	*   출력 PARAMETA	: List<MlocmaDTO> - 로케이션DTO 배열
	*   설명			: 이동지시를 내릴 zone의 셀렉트박스를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public List<MlocmaDTO> getWmt10ZoneList(MlocmaDTO param){
		List<MlocmaDTO> dataList = taskMapper.selectWmt10WareAreaZoneRelations(param);
		return dataList;
	}
	
	/*  getWmt10LocaKeyList - 이동지시 to Loc List호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: MlocmaDTO - 로케이션DTO
	*   출력 PARAMETA	: List<MlocmaDTO> - 로케이션DTO 배열
	*   설명			: 이동지시를 내릴 location의 셀렉트박스를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public List<MlocmaDTO> getWmt10LocaKeyList(MlocmaDTO param){
		List<MlocmaDTO> dataList = organizationMapper.selectWareAreaZoneLocationRelationsWmt10(param);
		return dataList;
	}
	
	
	/*  saveWmt10List - 이동지시 저장
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<TaskDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int returnData - 성공시 1 반환
	*   설명			: 이동지시를 내리는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public int saveWmt10List(RequestDTO<TaskDTO> param, CommonDTO common) {
		UserVO userData = common.getUserData();
		int returnData = 0;
		
		if(param.getUpdateList() != null) {
			String taskoky = taskMapper.getTaskoky();
			
			for(TaskDTO taskDTO : param.getUpdateList()) {
				taskDTO.setUserData(userData);
				taskDTO.setTaskoky(taskoky);
				int taskoit = taskMapper.selectTaskoit(taskDTO);
				
				taskDTO.setTaskoit(taskoit);
				taskDTO.setTasksts("NEW");
				
				returnData = taskMapper.saveWmt10List(taskDTO);
				
				if(returnData == 0) {
					throw new InsertCheckedException(); 
				}
				
				taskMapper.updateWtakitList(taskDTO);
				
				if(!(taskDTO.getOresult() == 0)) {
					throw new ProcedureCheckedException((String) taskDTO.getOmsgkey());
				} else {
					returnData += 0;
				}
				
			}
		}
		
		return returnData;
	}
	
	/*  getWmt11Init - 이동확정 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: TaskDTO - 작업DTO
	*   출력 PARAMETA	: Map<String, Object> - 호출한 데이터를 담을 Map
	*   설명			: 이동확정 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public Map<String, Object> getWmt11Init(TaskDTO taskDTO){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserVO userData = taskDTO.getUserData();
		
		// 창고 리스트
		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(userData);
		List<CommonDTO> warekeyList = organizationMapper.selectWarehouseUserSelectBox(mwarmaDTO);
		resultMap.put("warekeyList", warekeyList);

		// 화주 리스트
		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(userData);
		resultMap.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDTO));

		//doctype 리스트
		MdocmaDTO mdocmaDTO = new MdocmaDTO();
		mdocmaDTO.setUserData(userData);
		mdocmaDTO.setDoccate("500");
		mdocmaDTO.setWarekey(taskDTO.getWarekey());
		resultMap.put("doctypeList", documentMapper.selectMdocmaDoctypeList(mdocmaDTO));
		
		//Areakey 리스트
		MaremaDTO maremaDTO = new MaremaDTO();
		maremaDTO.setUserData(userData);
		maremaDTO.setWarekey(taskDTO.getWarekey());
		resultMap.put("areakeyList", organizationMapper.selectMaremaSelectBox(maremaDTO));

		//Zonekey 리스트
		MzonmaDTO mzonmaDTO = new MzonmaDTO();
		mzonmaDTO.setUserData(userData);
		mzonmaDTO.setWarekey(taskDTO.getWarekey());
		resultMap.put("zonekeyList", organizationMapper.selectMzonmaSelectBox(mzonmaDTO));

		//Locakey 리스트
		MlocmaDTO mlocmaDTO = new MlocmaDTO();
		mlocmaDTO.setUserData(userData);
		mlocmaDTO.setWarekey(taskDTO.getWarekey());
		resultMap.put("locakeyList", organizationMapper.selectMlocmaSelectBox(mlocmaDTO));
		
		//재고상태
		McodemDTO dto = new McodemDTO();
		dto.setComcdky("STKSTAT");
		dto.setUserData(userData);
		resultMap.put("stkstatList", codeMapper.selectMcodem(dto)); //재고상태
		
		return resultMap;
	}
	
	/*  getWmt11List - 이동확정 List호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: TaskDTO - 작업DTO
	*   출력 PARAMETA	: List<TaskDTO> - 작업DTO 배열
	*   설명			: 이동지시를 내린 재고를 불러오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public List<TaskDTO> getWmt11List(TaskDTO param){
		List<TaskDTO> dataList = taskMapper.selectWmt11List(param);
		return dataList;
	}
	
	/*  saveWmt11List - 이동확정 저장
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<TaskDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int returnData - 성공시 1 반환
	*   설명			: 이동지시가 내려진 재고의 이동을 확정하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public int saveWmt11List(RequestDTO<TaskDTO> param, CommonDTO common) {
		UserVO userData = common.getUserData();
		int returnData = 0;
		
		if(param.getUpdateList() != null) {
			for(TaskDTO taskDTO: param.getUpdateList()) {
				taskDTO.setUserData(userData);
				returnData = taskMapper.updateWmt11List(taskDTO);
				
				if(returnData == 0) {
					throw new UpdateCheckedException(); 
				}
				
				taskMapper.updateWtakitList(taskDTO);
				
				if(!(taskDTO.getOresult() == 0)) {
					throw new ProcedureCheckedException((String) taskDTO.getOmsgkey());
				} else {
					returnData += 0;
				}
			}
		}
		
		return returnData;
	}
	
	/*  saveWmt11Cancel - 이동지시 취소
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<TaskDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int returnData - 성공시 1 반환
	*   설명			: 이동지시가 내려진 재고의 이동을 취소하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public int saveWmt11Cancel(RequestDTO<TaskDTO> param, CommonDTO common) {
		UserVO userData = common.getUserData();
		int returnData = 0;
		
		if(param.getUpdateList() != null) {
			for(TaskDTO taskDTO: param.getUpdateList()) {
				taskDTO.setUserData(userData);
				returnData = taskMapper.updateWmt11WtakitCancel(taskDTO);
				
				if(returnData == 0) {
					throw new UpdateCheckedException();
				}
				
				taskDTO.setDoctype("569");
				taskMapper.updateWtakitCancelList(taskDTO);
				
				if(!(taskDTO.getOresult() == 0)) {
					throw new ProcedureCheckedException((String) taskDTO.getOmsgkey());
				} else {
					returnData += 0;
				}
			}
		}
		return returnData;
	}
	/*
	 * wmt20: 세트작업 지시등록
	 */

	public InitDataDTO getWmt20Init(CommonDTO param) {
		InitDataDTO initDataDTO = new InitDataDTO();
		Map<String, Object> returnData = new HashMap<>();
		MskuwcDTO mskuDTO = new MskuwcDTO();
		mskuDTO.setUserData(param.getUserData());
		returnData.put("skumkeyList", taskMapper.getWmt20SetSkuInit(mskuDTO));
		
		MaremaDTO maremaDTO = new MaremaDTO();
		maremaDTO.setUserData(param.getUserData());
		maremaDTO.setWarekey(param.getWarekey());
		maremaDTO.setAreakey("TAK");
		returnData.put("areakeyList", organizationMapper.selectMaremaSelectBox(maremaDTO));
		
		MzonmaDTO mzonmaDTO = new MzonmaDTO();
		mzonmaDTO.setUserData(param.getUserData());
		mzonmaDTO.setWarekey(param.getWarekey());
		mzonmaDTO.setAreakey("TAK");
		mzonmaDTO.setZonekey("SETZ");
		returnData.put("zonekeyList", organizationMapper.selectMzonmaSelectBox(mzonmaDTO));

		MlocmaDTO mlocmaDTO = new MlocmaDTO();
		mlocmaDTO.setUserData(param.getUserData());
		mlocmaDTO.setWarekey(param.getWarekey());
		returnData.put("locakeyList", organizationMapper.selectMlocmaList(mlocmaDTO));
		initDataDTO.setItem(returnData);
		return initDataDTO;
	}
	
	public int saveWmt20(RequestDTO<SetOrderDTO> param) {
		int resultCnt = 0;
		if(param.getAddList() != null) {
			String taskoky = taskMapper.getTaskoky();
			for(SetOrderDTO skustDTO : param.getAddList()) {
				skustDTO.setTaskoky(taskoky);
				skustDTO.setTolocky(skustDTO.getLocakey());
				int result = taskMapper.saveWmt20(skustDTO);
				if(result == 0) {
					throw new InsertCheckedException(); 
				}
				resultCnt++;
			}
		}
		return resultCnt;
	}

	/*
	 * wmt21: 세트작업처리
	 */
	public InitDataDTO getWmt21Init(CommonDTO param) {
		InitDataDTO initDataDto = new InitDataDTO();
		Map<String , Object> map = new HashMap<>();
		MwarmaDTO mwarmaDto =  new MwarmaDTO();
		mwarmaDto.setUserData(param.getUserData());
		map.put("warekeyList", organizationMapper.selectWarehouseUserSelectBox(mwarmaDto));
		
		MowrmaDTO mowrmaDto =  new MowrmaDTO();
		mowrmaDto.setUserData(param.getUserData());
		map.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDto));
		
		initDataDto.setItem(map);
		return initDataDto;
	}

	public List<SetOrderDTO> getWmt21HeadList(SetOrderDTO param) {
		return taskMapper.getWmt21HeadList(param);
	}
	
	public List<SetOrderDTO> getWmt21ItemList(SetOrderDTO param) {
		return taskMapper.getWmt21ItemList(param);
	}

	public void setWmt21Save(RequestDTO<SetOrderDTO> param) {
		if(param.getUpdateList() != null) {
			for(SetOrderDTO updateRow : param.getUpdateList()) {
				int resultCnt = taskMapper.setWmt21Wtakit(updateRow);
				if(resultCnt == 0) {
					throw new UpdateCheckedException();
				}
				
				// 500 550
				taskMapper.setWmt21Save(updateRow);
				if(updateRow.getOresult() != 0) {
					throw new ProcedureCheckedException((String) updateRow.getOmsgkey());
				}
				// 600 660
				updateRow.setDoccate("600");
				updateRow.setDoctype("660");
				taskMapper.setWmt21Save(updateRow);
				if(updateRow.getOresult() != 0) {
					throw new ProcedureCheckedException((String) updateRow.getOmsgkey());
				}
			}
		}
	}
	
	public int setWmt21Cancel(RequestDTO<SetOrderDTO> param) {
		int resultCnt = 0;
		resultCnt = taskMapper.setWmt21Cancel(param);
		if(resultCnt == 0) {
			new InsertCheckedException();
		}
		return resultCnt;
	}
	
	/*
	 * wmt22: 세트해제작업지시
	 */
	public InitDataDTO getWmt22Init(CommonDTO param) {
		InitDataDTO initDataDto = new InitDataDTO();
		Map<String , Object> map = new HashMap<>();
		MwarmaDTO mwarmaDto =  new MwarmaDTO();
		mwarmaDto.setUserData(param.getUserData());
		map.put("warekeyList", organizationMapper.selectWarehouseUserSelectBox(mwarmaDto));
		
		MowrmaDTO mowrmaDto =  new MowrmaDTO();
		mowrmaDto.setUserData(param.getUserData());
		map.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDto));
		
		initDataDto.setItem(map);
		return initDataDto;
	}
	
	public List<SetOrderDTO> getWmt22List(SetOrderDTO param) {
		return taskMapper.getWmt22List(param);
	}

	public int setWmt22Save(RequestDTO<SetOrderDTO> param) {
		int resultCnt = 0;
		String taskoky = taskMapper.getTaskoky();
		if(param.getUpdateList() != null) {
			for(SetOrderDTO skustDTO : param.getUpdateList()) {
				skustDTO.setDoccate("500");
				skustDTO.setDoctype("551");
				skustDTO.setTasksts("NEW");
				skustDTO.setTaskoky(taskoky);
				skustDTO.setToareky("TAK");
				skustDTO.setTozonky("SETZ");
				skustDTO.setTolocky("SETLOC");
	 			int result = taskMapper.saveWmt20(skustDTO);
				if(result == 0) {
					throw new InsertCheckedException(); 
				}
				resultCnt++;
			}
		}
		return resultCnt;
	}
	
	
	/*
	 * wmt23: 세트해제처리
	 */
	public List<SetOrderDTO> getWmt23List(SetOrderDTO param) {
		return taskMapper.getWmt23List(param);
	}


	public void setWmt23Save(RequestDTO<SetOrderDTO> param) {
		if(param.getUpdateList() != null) {
			for(SetOrderDTO updateRow : param.getUpdateList()) {
				int resultCnt = taskMapper.setWmt21Wtakit(updateRow);
				if(resultCnt == 0) {
					throw new UpdateCheckedException();
				}
				
				// 500 551
				taskMapper.setWmt21Save(updateRow);
				if(updateRow.getOresult() != 0) {
					throw new ProcedureCheckedException((String) updateRow.getOmsgkey());
				}
				// 600 661
				updateRow.setDoccate("600");
				updateRow.setDoctype("661");
				taskMapper.setWmt21Save(updateRow);
				if(updateRow.getOresult() != 0) {
					throw new ProcedureCheckedException((String) updateRow.getOmsgkey());
				}
			}
		}
	}


	public int setWmt23Cancel(RequestDTO<SetOrderDTO> param) {
		int resultCnt = 0;
		resultCnt = taskMapper.setWmt21Cancel(param);
		if(resultCnt == 0) {
			new InsertCheckedException();
		}
		return resultCnt;
	}
	
}
