package com.logistics.tm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.logistics.common.dto.CommonDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TshrvhDTO extends CommonDTO {

  private String compkey; // 1 varchar(20) Company Key ' '
  private String warekey; // 2 varchar(20) 창고 ' '
  private String shtrtky; // 3 varchar(10) 셔틀노선키 ' '
  private String vehicky; // 4 varchar(10) 차량코드 ' '
  private int srvehod; // 5 int(11) 셔틀차량 순서 0
  private int shtvcap; // 6 int(11) 상차가능 Pallet수량 0

  private String credate; // 7 varchar(8)

  private String creuser; // 9 varchar(60) 생성사용자 ' '
  private String lmodate; // 10 varchar(8) 수정일자 ' '
  private String lmotime; // 11 varchar(6) 수정시간 ' '
  private String lmouser; // 12 varchar(60) 수정사용자 ' '
  private String indibzl; // 13 varchar(1) biz logic indicator ' '
  private String indiarc; // 14 varchar(1) Archive indicator ' '
  // private int updtchk; // int(11) Update check 0

  // // old
  // private String oldvehicky;

  // // ComboBox
  // private String combovl;
  // private String combonm;
}
