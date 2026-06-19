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
import com.logistics.sy.domain.UserMenuDTO;
import com.logistics.sy.domain.UserVO;
import com.logistics.sy.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SYU2Service {
	private final UserMapper userMapper;


    public List<UserMenuDTO> getHeaderMenuList(Map<String, Object> params, UserVO userInfo)  {
		params.put("userData", userInfo);
        return userMapper.selectHeaderMenu(params);
    }

	public List<UserMenuDTO> getItemMenuList(Map<String, Object> params, UserVO userInfo)  {
        return userMapper.selectItemMenu(params);
    }

    public void setSYU2HeadDelete(Map<String, Object> param) {
		List<UserMenuDTO> head = convertDTO((ArrayList) param.get("data"));
	    HashMap<String, Object> hmap = new HashMap<>();
	    hmap.put("list", head);
	    int deleteHeadCnt = userMapper.deleteHeaderMenu(hmap);
	    if(deleteHeadCnt == 0) {
    		throw new DeleteCheckedException();
    	}

	    List<UserMenuDTO> item = new ArrayList<>();
	    UserMenuDTO vo = new UserMenuDTO();
	    vo.setCompkey(head.get(0).getCompkey());
	    vo.setMenukey(head.get(0).getMenukey());
	    item.add(vo);

	    HashMap<String, Object> imap = new HashMap<>();
	    imap.put("list", item);
	    if(userMapper.deleteItemMenu(imap) == 0) {
	    	throw new DeleteCheckedException();
	    }
    }

    public void setSYU2ItemDelete(Map<String, Object> param) {
		List<UserMenuDTO> list = convertDTO((ArrayList) param.get("data"));
	    HashMap<String, Object> map = new HashMap<>();
	    map.put("list", list);
	    if(userMapper.deleteItemMenu(map) == 0) {
	    	throw new DeleteCheckedException();
	    }
    }


	public void setSYU2Save(Map<String, Object> param, UserVO userInfo) {
		List<UserMenuDTO> head = convertDTO((ArrayList)param.get("head"));
		Map item = (Map) param.get("item");
		List<UserMenuDTO> updateList = convertDTO((ArrayList)item.get("updateList"));
		List<UserMenuDTO> addList = convertDTO((ArrayList)item.get("addList"));

		head.forEach(dto -> {
			dto.setLmouser(userInfo.getUseract());
			if(dto.getRowkey() == null) {
				dto.setCreuser(userInfo.getUseract());
				if(userMapper.insertHeaderMenu(dto) == 0) {
	    			throw new InsertCheckedException();
	    		}
			} else {
				if(userMapper.updateHeaderMenu(dto) == 0) {
	        		throw new UpdateCheckedException();
	        	}
			}
    	});

		//item List
		if(addList != null && !addList.isEmpty()) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("userData", userInfo);
			map.put("list", addList);
			if(userMapper.insertItemMenu(map) == 0) {
    			throw new InsertCheckedException();
    		}
		}
		if(updateList != null && !updateList.isEmpty()) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("userData", userInfo);
	    	map.put("list", updateList);
	    	if(userMapper.updateItemMenu(map) == 0) {
        		throw new UpdateCheckedException();
        	}
		}
	}

	private List<UserMenuDTO> convertDTO(ArrayList arr){
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, UserMenuDTO.class));
    }

    public List<Map<String, Object>> getMenuSelectBox(Map<String, Object> params, UserVO userInfo) {
		params.put("compkey", userInfo.getCompkey());
		return userMapper.selectMenuBox(params);
	}
}
