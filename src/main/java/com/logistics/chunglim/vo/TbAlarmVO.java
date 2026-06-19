package com.logistics.chunglim.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TbAlarmVO {
	private String io;
	private String message;
	private String errTime;
	private String clearTime;
}
