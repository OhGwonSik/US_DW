package com.logistics.wm.domain;

import com.logistics.md.domain.MskuwcDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SetOrderDTO extends MskuwcDTO{

	// CUSTOMIZE
	private String doccate;
	private String doctype;
	private String doctynm;
	private int fordqty;
	private int tcmpqty;
	private int ltotqty; // setLocation 세트 수량
	private int psbtqty; // 세트 부품의 구성 수량
	private int tordqty; // task 지시수량
	private String setarea;
	private String setzone;
	private String setloca;
	
	// WTAKIT
	private String taskoky;
	private int taskoit;
	private String tasksts;
	
	// WSTKKY
	private String stockky;
	private int stotqty;
	private String areakey;
	private String zonekey;
	private String locakey;
	private String toareky;
	private String tozonky;
	private String tolocky;
	private String suomkey;
	private String lotnmky;
	private String proddat;
	private String shelife;
	private String expidat;
	private String rcvdcdt ; 
	private String rcvfrdt;
	private String rcvdcky;
	private int rcvdcit;
	private String shpdcky;
	private int shpdcit;
	
	// MWARMA
	private String whnamlc;
	
	// MOWRMA
	private String ownamlc;
	
	// MSKUST
	private String skumkey;
	private String sbskuky;
	private String sbstqty;
	
}
