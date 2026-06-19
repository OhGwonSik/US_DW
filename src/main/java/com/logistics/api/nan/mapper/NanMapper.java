package com.logistics.api.nan.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.api.nan.vo.NanStockDTO;

@Mapper
public interface NanMapper {

	String selectIfstksq();
	int insertNanStockInformation(NanStockDTO nanStockDTO);
}
