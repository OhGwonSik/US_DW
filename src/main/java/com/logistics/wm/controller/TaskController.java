package com.logistics.wm.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.InitDataDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.md.domain.MlocmaDTO;
import com.logistics.wm.domain.SetOrderDTO;
import com.logistics.wm.domain.TaskDTO;
import com.logistics.wm.service.TaskService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TaskController implements Serializable{
	/* 
	 * 작업 Controller
	 * 프로그램 ID : TaskController
	 * 프로그램 내용: 작업지시 Controller
	 * */
	
	private static final long serialVersionUID = 1L;
	
	private final TaskService taskService;
    
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
	@GetMapping(value = "/wm/task/wmt10/init/1")
	public Map<String, Object> getWmt10Init(@ModelAttribute TaskDTO taskDTO){
		return taskService.getWmt10Init(taskDTO);
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
    @GetMapping(value = "/wm/task/wmt10/grids/1")
    public List<TaskDTO> getWmt10List(@ModelAttribute TaskDTO param){
    	return taskService.getWmt10List(param);
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
    @GetMapping(value = "/wm/task/wmt10/zone")
    public List<MlocmaDTO> getWmt10ZoneList(@ModelAttribute MlocmaDTO param){
    	return taskService.getWmt10ZoneList(param);
    }
    
    /*  getWmt10Location - 이동지시 to Loc List호출
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
    @GetMapping(value = "/wm/task/wmt10/location")
    public List<MlocmaDTO> getWmt10Location(@ModelAttribute MlocmaDTO param){
    	return taskService.getWmt10LocaKeyList(param);
    }
    
    /*  saveWmt10List - 이동지시 저장
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<TaskDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int - 성공시 1 반환
	*   설명			: 이동지시를 내리는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
    @PostMapping(value = "/wm/task/wmt10/grids/1")
    public int saveWmt10List(@RequestBody RequestDTO<TaskDTO> param, CommonDTO common) {
    	return taskService.saveWmt10List(param, common);
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
    @GetMapping(value = "/wm/task/wmt11/init/1")
    public Map<String, Object> getWmt11Init(@ModelAttribute TaskDTO taskDTO){
    	return taskService.getWmt11Init(taskDTO);
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
    @GetMapping(value = "/wm/task/wmt11/grids/1")
    public List<TaskDTO> getWmt11List(@ModelAttribute TaskDTO param){
    	return taskService.getWmt11List(param);
    }
    
    /*  saveWmt11List - 이동확정 저장
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<TaskDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int - 성공시 1 반환
	*   설명			: 이동지시가 내려진 재고의 이동을 확정하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
     @PostMapping(value = "/wm/task/wmt11/grids/1")
     public int saveWmt11List(@RequestBody RequestDTO<TaskDTO> param, CommonDTO common) {
    	 return taskService.saveWmt11List(param, common);
     }
     
     /*  saveWmt11Cancel - 이동지시 취소
 	*   최초 생성일시	: 2023-12-12 11:30
 	*   최초 생성자	: 최강호 
 	*   입력 PARAMETA	: RequestDTO<TaskDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
 	*   출력 PARAMETA	: int - 성공시 1 반환
 	*   설명			: 이동지시가 내려진 재고의 이동을 취소하는 메소드
 	*   수정 내역		:
 	*   수정일시		:
 	*	수정자		:
 	*	변경 사항		: 
 	*/
     @PostMapping(value = "/wm/task/wmt11/grids/2")
     public int saveWmt11Cancel(@RequestBody RequestDTO<TaskDTO> param, CommonDTO common) {
    	 return taskService.saveWmt11Cancel(param, common);
     }
     /*
      * wmt20 세트작업지시등록
      */
     @GetMapping(value = "/wm/task/wmt20/init")
     public InitDataDTO getWmt20Init(CommonDTO param){
     	return taskService.getWmt20Init(param);
     }
     
     @PatchMapping(value = "/wm/task/wmt20/save")
     public int saveWmt20(@RequestBody RequestDTO<SetOrderDTO> param) {
    	 return taskService.saveWmt20(param);
     }
     /*
      * wmt21 세트작업처리
      */
     @GetMapping(value = "/wm/task/wmt21/init")
     public InitDataDTO getWmt21Init(CommonDTO param){
     	return taskService.getWmt21Init(param);
     }
     
     @GetMapping(value = "/wm/task/wmt21/grids/1")
     public List<SetOrderDTO> getWmt21HeadList(SetOrderDTO param){
     	return taskService.getWmt21HeadList(param);
     }
     
     @GetMapping(value = "/wm/task/wmt21/grids/2")
     public List<SetOrderDTO> getWmt21ItemList(SetOrderDTO param){
    	 return taskService.getWmt21ItemList(param);
     }
     
     @PostMapping(value = "/wm/task/wmt21/save")
     public void setWmt21Save(@RequestBody RequestDTO<SetOrderDTO> param){
    	 taskService.setWmt21Save(param);
     }
     
     @PostMapping(value = "/wm/task/wmt21/cancel")
     public int setWmt21Cancel(@RequestBody RequestDTO<SetOrderDTO> param){
    	 return taskService.setWmt21Cancel(param);
     }
     
     /*
      * wmt22 세트해제지시등록
      */
     @GetMapping(value = "/wm/task/wmt22/init")
     public InitDataDTO getWmt22Init(CommonDTO param){
     	return taskService.getWmt22Init(param);
     }
     
     @GetMapping(value = "/wm/task/wmt22/grids/1")
     public List<SetOrderDTO> getWmt22List(SetOrderDTO param){
     	return taskService.getWmt22List(param);
     }
     
     @PostMapping(value = "/wm/task/wmt22/save")
     public void setWmt22Save(@RequestBody RequestDTO<SetOrderDTO> param){
    	 taskService.setWmt22Save(param);
     }
     
     /*
      * wmt23 세트해제처리
      */
     @GetMapping(value = "/wm/task/wmt23/grids/1")
     public List<SetOrderDTO> getWmt23List(SetOrderDTO param){
     	return taskService.getWmt23List(param);
     }
     
     @PostMapping(value = "/wm/task/wmt23/save")
     public void setWmt23Save(@RequestBody RequestDTO<SetOrderDTO> param){
    	 taskService.setWmt23Save(param);
     }
     
     @PostMapping(value = "/wm/task/wmt23/cancel")
     public int setWmt23Cancel(@RequestBody RequestDTO<SetOrderDTO> param){
    	 return taskService.setWmt23Cancel(param);
     }
}
