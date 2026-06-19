package com.logistics.wm.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipEtcDTO extends ShipDTO{

    //===============================================
    //======= 출고-기타출고 DTO / extends : ShipDTO ====
    //===============================================

    //ocosal
    private String cooutky;
    private int cooutit;
    private String salstat;
    private String rgitqty;
    private String saatc03;
    private String saatc06;
    private String salmemo;
    
    //mdesma
    private String destkey;

    //mrscma
    private String shprscd;
    private String shprsnm;
    
}
