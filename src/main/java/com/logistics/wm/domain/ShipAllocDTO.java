package com.logistics.wm.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ShipAllocDTO extends ShipDTO {

    //===============================================
    //======= 출고-할당 DTO / extends : ShipDTO ========
    //===============================================

    // wshpit
    private String picktyp;
    private int rgitqty;
    
    // customize
    private String shppdayfrom;
    private String shppdayto;
    private int refscnt;
    private String stockyn;
    
    private List<ShipAllocDTO> headList;
    private List<ShipAllocDTO> itemList;

    //print
    private String progrid;
    private String type;
    private String user;
}
