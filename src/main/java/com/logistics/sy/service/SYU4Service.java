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
import com.logistics.sy.domain.RoleGroupVo;
import com.logistics.sy.domain.UserVO;
import com.logistics.sy.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SYU4Service {
    private final UserMapper userMapper;

    public List<RoleGroupVo> getGroupSelect(Map<String, Object> params, UserVO userInfo)  {
    	params.put("userData", userInfo);
        return userMapper.selectGroup(params);
    }

    public void setSYU4Save(Map<String, Object> params, UserVO userInfo) {
    	Map grid = (Map) params.get("data");
    	List<RoleGroupVo> updateList = convertMaremaDTO((ArrayList)grid.get("updateList"));
    	List<RoleGroupVo> addList = convertMaremaDTO((ArrayList)grid.get("addList"));
    	List<RoleGroupVo> oldList = convertMaremaDTO((ArrayList)grid.get("oldList"));

    	if(addList != null && !addList.isEmpty()) {
    		HashMap<String, Object> map = new HashMap<>();
    		map.put("userData", userInfo);
    		map.put("list", addList);
    		if(userMapper.insertGroup(map) == 0) {
    			throw new InsertCheckedException();
    		}
    	}

    	if(updateList != null && oldList != null && !updateList.isEmpty() && !oldList.isEmpty()) {
        	HashMap<String, Object> map = new HashMap<>();
        	map.put("userData", userInfo);
        	map.put("list", updateList);
        	if(userMapper.updateGroup(map) == 0) {
        		throw new UpdateCheckedException();
        	}
    	}
    }

	public void setSYU4GroupDelete(Map<String, Object> params) {
		List<RoleGroupVo> list = convertMaremaDTO((ArrayList)params.get("data"));
		HashMap<String, Object> map = new HashMap<>();
		map.put("list", list);
		if(userMapper.deleteGroup(map) == 0) {
    		throw new DeleteCheckedException();
    	}
	}

    /*
     * List<RoleGroupVo> convert
     * */
    private List<RoleGroupVo> convertMaremaDTO(ArrayList arr){
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, RoleGroupVo.class));
    }

	public List<Map<String, Object>> getGroupSelectBox(Map<String, Object> params, UserVO userInfo) {
		params.put("compkey", userInfo.getCompkey());
		return userMapper.selectGroupBox(params);
	}
}
