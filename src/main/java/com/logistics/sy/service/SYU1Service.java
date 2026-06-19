package com.logistics.sy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.sy.domain.UserDTO;
import com.logistics.sy.domain.UserVO;
import com.logistics.sy.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SYU1Service {
    private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;

    public List<UserDTO> getUserSelect(Map<String, Object> params, UserVO userInfo)  {
    	params.put("userData", userInfo);
        return userMapper.selectCompanyUser(params);
    }

    public void setSYU1Save(Map<String, Object> params, UserVO userInfo) {
    	Map grid = (Map) params.get("data");
    	List<UserDTO> updateList = convertMaremaDTO((ArrayList)grid.get("updateList"));
    	List<UserDTO> addList = convertMaremaDTO((ArrayList)grid.get("addList"));
    	List<UserDTO> oldList = convertMaremaDTO((ArrayList)grid.get("oldList"));

        if(updateList != null && oldList != null && !updateList.isEmpty() && !oldList.isEmpty()) {
        	for(int i=0; i<updateList.size(); i++) {
        		updateList.get(i).setLmouser(userInfo.getUseract());
        		if(!updateList.get(i).getPasswrd().equals(oldList.get(i).getPasswrd()) && oldList.get(i).getPasswrd() != null) {
        			updateList.get(i).setPasswrd(passwordEncoder.encode(updateList.get(i).getPasswrd()));
        		}
        	}
        	HashMap<String, Object> map = new HashMap<>();
        	map.put("userData", userInfo);
        	map.put("list", updateList);
        	if(userMapper.updateUser(map) == 0) {
        		throw new UpdateCheckedException();
        	}
    	}
    	if(addList != null && !addList.isEmpty()) {
    		addList.forEach(dto -> {
        		dto.setPasswrd(passwordEncoder.encode(dto.getPasswrd()));
        	});
        	HashMap<String, Object> map = new HashMap<>();
        	map.put("userData", userInfo);
        	map.put("list", addList);
        	if(userMapper.insertUser(map) == 0) {
    			throw new InsertCheckedException();
    		}
    	}
    }

    /*
     * List<MaremaDTO> convert
     * */
    private List<UserDTO> convertMaremaDTO(ArrayList arr){
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, UserDTO.class));
    }
}
