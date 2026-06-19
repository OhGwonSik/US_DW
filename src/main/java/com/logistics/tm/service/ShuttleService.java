package com.logistics.tm.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.sy.domain.UserVO;
import com.logistics.tm.domain.TvhcplVO;
import com.logistics.tm.mapper.ShuttleMapper;

@Service
public class ShuttleService {
	
	@Autowired
	private ShuttleMapper shuttleMapper;

	public int getTmp1Save(Map<String, Object> param, UserVO userInfo) throws Exception {
		param.put("compkey", userInfo.getCompkey());
		int cnt = 0 , insertCnt = 0;
		
			String vehicky = shuttleMapper.getVehicky(param); //차량 번호 채번
			param.put("vehicky", vehicky);
			
			cnt = shuttleMapper.getTmp1ListCount(param); //차량당 운송 미완료 존재 여부 확인 
			
			if(cnt == 0) { // 운송완료인 경우 셔틀배차 시작 
				String vhplnky = shuttleMapper.getTmp1HeaderListWithVhplnky(param); 
				param.put("vhplnky", vhplnky);
				param.put("creuser", userInfo.getUseract());
				param.put("lmouser", userInfo.getUseract());
				
				insertCnt = shuttleMapper.getTmp1SaveList(param); //셔틀배차 계획 INSERT
				
				if(insertCnt == 0) {
					throw new InsertCheckedException();
				}
			}
		
		return cnt;
	}
	

	
	public Map<String, Object> getTmp1HeaderList(Map<String, Object> param, UserVO userInfo) {
		param.put("compkey", userInfo.getCompkey());
		
		if(!param.containsKey("vhplnky")) {
			String vhplnky = shuttleMapper.getTmp1ListWithVhplnky(param); 
			param.put("vhplnky", vhplnky);
		}
		Map<String, Object> map = shuttleMapper.getTmp1HeaderList(param);
		
		if(map.size()==0) {
			map = Collections.emptyMap();
		}
		
		return map; 
	}


	
	public List<Map<String, Object>> getTmp1ItemList(Map<String, Object> param, UserVO userInfo) {
		param.put("compkey", userInfo.getCompkey());
		
		List<Map<String, Object>> listMap = shuttleMapper.getTmp1ItemList(param);
		
		if(listMap.size()==0) {
			listMap = Collections.emptyList();
		}
		
		return listMap; 
	}


	
	
	public int tmp1ShiSuccess(Map<String, Object> param, UserVO userInfo) {
		param.put("compkey", userInfo.getCompkey());
		int cnt = 0;
		
		String vehicky = shuttleMapper.getVehicky(param); //차량 번호 채번
		param.put("vehicky", vehicky);
		String vhplnky = shuttleMapper.getTmp1ListWithVhplnky(param);  //배치계획 번호 채번
		param.put("vhplnky", vhplnky);
		param.put("lmouser", userInfo.getUseract());
		
		cnt = shuttleMapper.updateShtrsts(param);
		
		if(cnt == 0) {
			throw new UpdateCheckedException(); 
		}
		
		return cnt; 
	}


	
	
	public int updateShipTransPlan(Map<String, Object> param, UserVO userInfo) throws Exception {
		param.put("compkey", userInfo.getCompkey());
		param.put("lmouser", userInfo.getUseract());
		
		int cnt =  shuttleMapper.updateShipTranPl(param);
		
		if(cnt == 0) {
			throw new Exception("수정된 데이터 결과가 없습니다.");
		}
		return cnt;
	}


	
	public List<TvhcplVO> tmpCmpCount(Map<String, Object> param, UserVO userInfo) {
		param.put("compkey" , userInfo.getCompkey());
		
		String vehicky = shuttleMapper.getVehicky(param); //차량 번호 채번
		param.put("vehicky", vehicky);
		String vhplnky = shuttleMapper.getTmp1ListWithVhplnky(param);  //배치계획 번호 채번
		param.put("vhplnky", vhplnky);

		List<TvhcplVO> list = shuttleMapper.getCmpCount(param);
		
		if(list.size() == 0) {
			list = Collections.emptyList();
		}
		return list;
	}


	
	public List<Map<String, Object>> getTransSts(Map<String, Object> param, UserVO userInfo) {
		param.put("userInfo", userInfo);
		
		List<Map<String, Object>> list = shuttleMapper.getTransRound(param);
		
		if(list.size() == 0) {
			list = Collections.emptyList();
		}
		
		return list;
	}

}
