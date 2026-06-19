package com.logistics.om.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.om.domain.MordmaDTO;

@Mapper
public interface OMDocumentMapper {

	List<MordmaDTO> selectMordmaList(MordmaDTO mordmaDTO);
}
