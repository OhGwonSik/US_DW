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
import com.logistics.sy.domain.UserRoleHeaderDTO;
import com.logistics.sy.domain.UserRoleProgramDTO;
import com.logistics.sy.domain.UserRoleWarehouseDTO;
import com.logistics.sy.domain.UserVO;
import com.logistics.sy.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SYU3Service {
	private final UserMapper userMapper;

	public List<UserRoleHeaderDTO> getMasterRoleList(Map<String, Object> params, UserVO userInfo){
		params.put("userData", userInfo);
		return userMapper.selectRoleMaster(params);
	}

	public List<UserRoleProgramDTO> getProgramRoleList(Map<String, Object> params, UserVO userInfo){
		return userMapper.selectRoleProgram(params);
	}

	public List<UserRoleWarehouseDTO> getWarehouseRoleList(Map<String, Object> params, UserVO userInfo){
		return userMapper.selectRoleWarehouse(params);
	}

	public void setSYU3Save(Map<String, Object> params, UserVO userInfo) {
		// Master
		List<UserRoleHeaderDTO> master = convertMsterDTO((ArrayList)params.get("master"));
		master.forEach(dto -> {
			dto.setLmouser(userInfo.getUseract());
			if(dto.getRowkey() == null) {
				dto.setCreuser(userInfo.getUseract());
				userMapper.insertRoleMaste(dto);
			} else {
				userMapper.updateRoleMaste(dto);
			}
		});

		// Program
		Map<String, Object> progrm = (Map)params.get("progrm");
		List<UserRoleProgramDTO> progrmUpdate = convertProgramDTO((ArrayList)progrm.get("updateList"));
		List<UserRoleProgramDTO> progrmAdd = convertProgramDTO((ArrayList)progrm.get("addList"));

		if(progrmAdd != null && !progrmAdd.isEmpty()) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("userData", userInfo);
			map.put("list", progrmAdd);
			if(userMapper.insertRoleProgram(map) == 0) {
    			throw new InsertCheckedException();
    		}
		}

		if(progrmUpdate != null && !progrmUpdate.isEmpty()) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("userData", userInfo);
			map.put("list", progrmUpdate);
			if(userMapper.updateRoleProgram(map) == 0) {
    			throw new InsertCheckedException();
    		}
		}

		// Warehouse
		Map<String, Object> warhse = (Map)params.get("warhse");
		List<UserRoleWarehouseDTO> warhseUpdate = convertWarehouseDTO((ArrayList)warhse.get("updateList"));
		List<UserRoleWarehouseDTO> warhseAdd = convertWarehouseDTO((ArrayList)warhse.get("addList"));

		if(warhseAdd != null && !warhseAdd.isEmpty()) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("userData", userInfo);
			map.put("list", warhseAdd);
			if(userMapper.insertRoleWarehouse(map) == 0) {
    			throw new InsertCheckedException();
    		}
		}

		if(warhseUpdate != null && !warhseUpdate.isEmpty()) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("userData", userInfo);
			map.put("list", warhseUpdate);
			if(userMapper.updateRoleWarehouse(map) == 0) {
        		throw new UpdateCheckedException();
        	}
		}
	}

	public void setSYU3MasterDelete(Map<String, Object> params) {
		List<UserRoleHeaderDTO> list = convertMsterDTO((ArrayList)params.get("data"));
		list.forEach(dto -> {
			userMapper.deleteRoleMaste(dto);
	    	userMapper.deleteALLRoleProgram(dto);
	    	userMapper.deleteALLRoleWarehouse(dto);
		});
	}

	public void setSYU3ProgramDelete(Map<String, Object> params) {
		List<UserRoleProgramDTO> list = convertProgramDTO((ArrayList)params.get("data"));
		HashMap<String, Object> map = new HashMap<>();
		map.put("list", list);
		if(userMapper.deleteRoleProgram(map) == 0) {
    		throw new DeleteCheckedException();
    	}
	}

	public void setSYU3WarehouseDelete(Map<String, Object> params) {
		List<UserRoleWarehouseDTO> list = convertWarehouseDTO((ArrayList)params.get("data"));
		HashMap<String, Object> map = new HashMap<>();
		map.put("list", list);
		if(userMapper.deleteRoleWarehouse(map) == 0) {
    		throw new DeleteCheckedException();
    	}
	}

	private List<UserRoleHeaderDTO> convertMsterDTO(ArrayList arr){
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, UserRoleHeaderDTO.class));
    }

	private List<UserRoleProgramDTO> convertProgramDTO(ArrayList arr){
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, UserRoleProgramDTO.class));
    }

	private List<UserRoleWarehouseDTO> convertWarehouseDTO(ArrayList arr){
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, UserRoleWarehouseDTO.class));
    }

	public List<Map<String, Object>> getRoleSelectBox(Map<String, Object> params, UserVO userInfo) {
		params.put("compkey", userInfo.getCompkey());
		return userMapper.selectRoleBox(params);
	}
}
