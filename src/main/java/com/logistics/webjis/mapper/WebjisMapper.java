package com.logistics.webjis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.webjis.vo.WebjisVO;

@Mapper
public interface WebjisMapper {
	
	String selectIfsalsq();				// IF수주문서번호 채번
	List<WebjisVO> selectCheckDateTimeIsEmpty(String compkey);
	int insertWebjis(WebjisVO webjis);
	int updateCheckDateTime(WebjisVO webjis);
}
