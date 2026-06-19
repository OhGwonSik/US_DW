package com.logistics.wm.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReturnShipOutDTO extends ShipDTO{
	
	//==============================
    //======= 반출출고-공통 / DTO ========
    //==============================
	
	//WSHPIT
	private String compkey;
	private int rgitqty;
	
	// CUSTOMIZE
	private int refscnt;
	private String shppdayfrom;
	private String shppdayto;
	private String stockct;
	private List<ReturnShipOutDTO> updateHeadList;
	private String refagky;
	private int soutqty;
	private int soutqty2;
	private int skkycnt;
	private String locanam;
	private String shpdcstArr;
    private String stockyn;
    private String picknam;
	

	private List<ReturnShipOutDTO> headList;
	private List<ReturnShipOutDTO> itemList;
	
}
