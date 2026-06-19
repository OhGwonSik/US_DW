package com.logistics.wm.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PickingDTO extends TaskDTO{
	 //===============================================
    //======= 출고-피킹 DTO / extends : ShipDTO ========
    //===============================================

    private List<PickingDTO> headList;
    private List<PickingDTO> itemList;

    private String[] taskstsList;
    private String shppdayfrom;
    private String shppdayto;
    private String doctynm;
    private int diffqty;
    private String frlonam;
    private String tolonam;
    private String ownamlc;
    
    //print
    private String user;
    private String progrid;
    private String type;
    private String boxcnt;
}
