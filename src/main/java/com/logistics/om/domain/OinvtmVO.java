package com.logistics.om.domain;

import lombok.Data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OinvtmVO {
	private String compkey;
	private String invhseq;
	private String invokey;
	private String cooutky;
	private int cooutit;
	private String cotakky;
	private int invsqty;
	private String schopt1;
	private String schopt2;
	private String credate;
	private String cretime;
	private String creuser;
}
