package com.logistics.sy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.configuration.error.DeleteCheckedException;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.sy.domain.SloghiVO;
import com.logistics.sy.domain.SprogmVO;
import com.logistics.sy.domain.SprthiVO;
import com.logistics.sy.domain.SupahiVO;
import com.logistics.sy.domain.UserVO;
import com.logistics.sy.mapper.WorkbenchMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkbenchService{
	private final WorkbenchMapper workbenchmapper;

	public List<SprogmVO> getSyw1List(Map<String, Object> params, UserVO userInfo)  {
		params.put("userData", userInfo);
		return workbenchmapper.selectSyw1List(params);
	}

	public void setSyw1List(Map<String, Object> params, UserVO userInfo)  {
		Map grid = (Map) params.get("data");
		List<SprogmVO> updateList = convertSprogmVODTO((ArrayList)grid.get("updateList"));
		List<SprogmVO> addList = convertSprogmVODTO((ArrayList)grid.get("addList"));
		List<SprogmVO> oldList = convertSprogmVODTO((ArrayList)grid.get("oldList"));

		if(addList != null && !addList.isEmpty()) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("userData", userInfo);
			map.put("list", addList);
			if(workbenchmapper.insertSprogm(map) == 0) {
    			throw new InsertCheckedException();
    		}
		}

		if(updateList  != null && oldList != null && !updateList.isEmpty() && !oldList.isEmpty()) {
			for(int i=0; i<updateList.size(); i++) {
				updateList.get(i).setOldprogrid(oldList.get(i).getProgrid());
			}
			HashMap<String, Object> map = new HashMap<>();
			map.put("userData", userInfo);
			map.put("list", updateList);
			if(workbenchmapper.updateSprogm(map) == 0) {
				throw new UpdateCheckedException();
			}
		}
	}

	public void setSYW1Delete(Map<String, Object> params, UserVO userInfo) {
		List<SprogmVO> list = convertSprogmVODTO((ArrayList)params.get("data"));
		HashMap<String, Object> map = new HashMap<>();
		map.put("list", list);
		if(workbenchmapper.deleteSprogm(map) == 0) {
    		throw new DeleteCheckedException();
    	}
	}

	private List<SprogmVO> convertSprogmVODTO(ArrayList arr){
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, SprogmVO.class));
	}

	public List<SupahiVO> getSyw2List(Map<String, Object> params, UserVO userInfo)  {
		params.put("userData", userInfo);
		return workbenchmapper.selectSyw2List(params);
	}

	public List<SloghiVO> getSyw3List(Map<String, Object> params, UserVO userInfo)  {
		params.put("userData", userInfo);
		return workbenchmapper.selectSyw3List(params);
	}

	public List<SprthiVO> getSyw4List(Map<String, Object> params, UserVO userInfo)  {
		params.put("userData", userInfo);
		return workbenchmapper.selectSyw4List(params);
	}

}
