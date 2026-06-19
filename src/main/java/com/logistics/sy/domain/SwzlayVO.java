package com.logistics.sy.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SwzlayVO {
	
	//SY 위젯 Layout - SWZLAY
	private String compkey;
	private String useract;
	private String progrid;
	private String laydata;
	private String credate;
	private String cretime;
	private String creuser;
	private String lmodate;
	private String lmotime;
	private String lmouser;
	private String indibzl;
	private String indiarc;
	private int updtchk;
}
