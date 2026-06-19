package com.logistics.wm.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartShipOutDTO extends ShipDTO {

	private String compkey;
	private int rgitqty;
	
	// customize
	private int resocnt;
	private int skkycnt;
	private int soutqty;
	private int fordqty2;
	private int tcmpqty2;
	private String shsname;
	private String shsdatefrom;
	private String shsdateto;
	
}
