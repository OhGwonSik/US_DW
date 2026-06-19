package com.logistics.chunglim.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.chunglim.vo.TbAlarmVO;

@Mapper
public interface TbAlarmMapper {
	List<TbAlarmVO> selectTbAlarms();
}