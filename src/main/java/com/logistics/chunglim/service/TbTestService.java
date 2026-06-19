package com.logistics.chunglim.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.logistics.chunglim.mapper.TbTestMapper;
import com.logistics.chunglim.vo.TbTestVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TbTestService {

	private final TbTestMapper tbTestMapper;
	
	public List<TbTestVO> getTbTests(TbTestVO testVO){
		return tbTestMapper.selectTbTests(testVO);
	}
	
	public int insertTbTest(TbTestVO testVO) {
		return tbTestMapper.insertTbTest(testVO);
	}
}
