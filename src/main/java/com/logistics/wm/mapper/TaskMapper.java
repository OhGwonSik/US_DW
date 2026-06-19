package com.logistics.wm.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.md.domain.MlocmaDTO;
import com.logistics.wm.domain.SetOrderDTO;
import com.logistics.wm.domain.TaskDTO;

@Mapper
public interface TaskMapper {
	
	//wmt10: 이동지시등록
	List<TaskDTO> selectWmt10List(TaskDTO param);
	String getTaskoky();
	int selectTaskoit(TaskDTO param);
	void updateWtakitList(TaskDTO param);
	int saveWmt10List(TaskDTO param);
	List<MlocmaDTO> selectWmt10WareAreaZoneRelations(MlocmaDTO param);
	
	//wmt11: 이동확정
	List<TaskDTO> selectWmt11List(TaskDTO param);
	int updateWmt11List(TaskDTO param);
	int updateWmt11WtakitCancel(TaskDTO param);
	void updateWtakitCancelList(TaskDTO param);
	
	//wmt20: 세트작업지시
	List<SetOrderDTO> getWmt20SetSkuInit(CommonDTO param);
	int saveWmt20(SetOrderDTO skustDTO);
	
	//wmt21: 세트작업처리
	List<SetOrderDTO> getWmt21Init(SetOrderDTO param);
	List<SetOrderDTO> getWmt21HeadList(SetOrderDTO param);
	List<SetOrderDTO> getWmt21ItemList(SetOrderDTO param);
	void setWmt21Save(SetOrderDTO updateRow);
	int setWmt21Cancel(RequestDTO<SetOrderDTO> param);
	int setWmt21Wtakit(SetOrderDTO updateRow);
	
	//wmt22: 세트해체작업지시
	List<SetOrderDTO> getWmt22List(SetOrderDTO param);
	
	//wmt22: 세트해체처리
	List<SetOrderDTO> getWmt23List(SetOrderDTO param);
}
