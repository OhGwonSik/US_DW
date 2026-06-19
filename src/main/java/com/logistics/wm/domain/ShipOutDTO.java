package com.logistics.wm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShipOutDTO extends ShipDTO{
	private String shsdatefrom;
	private String shsdateto;
	private String compkey;
	private int rgitqty;
	private String speidat;


	// MSKUWC
	private String skuat01;
	private String skuat02;
	private String skuat03;

	// OCOSAL
	private String saatc06;
	private String saatc10;


	
	// CUSTOMIZE
	private int resocnt;
	private int skkycnt;
	private int soutqty;
	private int fordqty2;
	private int tcmpqty2;
	private String shsname;
	private String locakey;
	private String[] allgrkyArr;
	private String locanam;

	private String datefrom;
	private String dateto;
	private String doctynm;
	private List<String> doctypes;
	private List<String> shpitsts;
}
