package com.logistics.tm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.logistics.common.dto.CommonDTO;

import lombok.Getter;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TpvassDTO extends CommonDTO {

  private String compkey; // 1 varchar(20) Company Key ' '
  private String vehicky; // 2 varchar(10) 차량코드 ' '
  private String useract; // 3 varchar(60) 기사 계정ID ' '
  private String warekey; // 4 varchar(20) 창고 ' '
  private String vaumemo; // 5 varchar(100) 기사메모 NULL
  private String vhuseyn; // 6 varchar(1) 사용여부 'Y'
  private String vhownyn;
  private String credate; // 7 varchar(8) 생성일자 'to_char(current_timestamp(),''YYYYMMDD'')'
  private String cretime; // 8 varchar(6) 생성시간 'to_char(current_timestamp(),''HH24MISS'')'
  private String creuser; // 9 varchar(60) 생성사용자 ' '
  private String lmodate; // 10 varchar(8) 수정일자 ' '
  private String lmotime; // 11 varchar(6) 수정시간 ' '
  private String lmouser; // 12 varchar(60) 수정사용자 ' '
  private String indibzl; // 13 varchar(1) biz logic indicator ' '
  private String indiarc; // 14 varchar(1) Archive indicator ' '
  // private String UPDTCHK; // 15 int(11) Update check 0

  // user dto
  // private String telphnm; // 휴대전화번호
  // private String usernam; // 사용자이름

  // // ComboBox
  // private String combovl;
  // private String combonm;
}
