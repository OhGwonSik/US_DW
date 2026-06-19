package com.logistics.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ForeignKeyVO {

	private String referencedTableName;
	private String referencedColumnName;

}
