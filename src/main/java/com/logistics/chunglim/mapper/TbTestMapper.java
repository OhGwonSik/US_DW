package com.logistics.chunglim.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.chunglim.vo.TbTestVO;

@Mapper
public interface TbTestMapper {

	List<TbTestVO> selectTbTests(TbTestVO testVO);
	int insertTbTest(TbTestVO testVO);
}
