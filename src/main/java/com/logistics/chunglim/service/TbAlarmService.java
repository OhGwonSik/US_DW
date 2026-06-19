package com.logistics.chunglim.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.logistics.chunglim.mapper.TbAlarmMapper;
import com.logistics.chunglim.vo.TbAlarmVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TbAlarmService {

	private final TbAlarmMapper tbAlarmMapper;
	
	public List<TbAlarmVO> getTbAlarms(){
		return tbAlarmMapper.selectTbAlarms();
	}
}
