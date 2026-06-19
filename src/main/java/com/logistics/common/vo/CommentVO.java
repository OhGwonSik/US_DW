package com.logistics.common.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentVO {

	private String label;
	private List<GeneratorVO> fkGenList;
	private List<String> valids;
	private String type;
	private String fkColumn;
	private String[] fkColumns;
	private String commonCode;
	private Boolean search = false;

}
