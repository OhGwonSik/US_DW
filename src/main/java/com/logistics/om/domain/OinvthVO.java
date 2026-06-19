package com.logistics.om.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class OinvthVO {
	
	//OM 장끼 인쇄 이력 - OINVTH
	private String compkey;
	private String invhseq;
	private int invitno;
	private String invcate;
	private String rowgrid;
	private String zzcol01;
	private String zzcol02;
	private String zzcol03;
	private String zzcol04;
	
}
