package com.logistics.wm.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllocChangeDTO extends ShipDTO{
	
	//WSHPIT
	private int rgitqty; // 출고지시수량

	// CUSTOMIZE
	private int tchnqty; // 입력수량
	private String newTaskoky;
	private String picknam;
	private String areanam;
	private String zonenam;
	private String locanam;
	private String frarenm;
	private String frzonnm;
	private String frlocnm;
	private String shsdatefrom;
	private String shsdateto;
}
