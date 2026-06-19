package com.logistics.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.logistics.common.mapper.GeneratorMapper;
import com.logistics.common.vo.ForeignKeyVO;
import com.logistics.common.vo.GeneratorVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GeneratorService {

	private final GeneratorMapper generatorMapper;
	public  List<GeneratorVO> selectColumnList(String tableName){
		return generatorMapper.selectColumnList(tableName);
	}
	public List<ForeignKeyVO> selectFKList(String tableName){
		return generatorMapper.selectFKList(tableName);
	}
	public List<GeneratorVO> selectColumnListWithForeign(String tableName){
		return generatorMapper.selectColumnListWithForeign(tableName);
	}
	public List<Map<String,String>> selecTableList(){
		return generatorMapper.selecTableList();
	}
	public GeneratorVO selectColumn(String tableName, String columnName) {
		return generatorMapper.selectColumn(tableName, columnName);
	}
	public String selectTableName(String tableName) {
		return generatorMapper.selectTableName(tableName);
	}
}
