package com.logistics.common.mapper;

import java.util.List;
import java.util.Map;

import com.logistics.common.vo.ForeignKeyVO;
import com.logistics.common.vo.GeneratorVO;

public interface GeneratorMapper {
	List<GeneratorVO> selectColumnList(String tableName);
	List<ForeignKeyVO> selectFKList(String tableName);
	List<GeneratorVO> selectColumnListWithForeign(String tableName);
	List<Map<String,String>> selecTableList();
	GeneratorVO selectColumn(String tableName, String columnName);
	String selectTableName(String tableName);

}
