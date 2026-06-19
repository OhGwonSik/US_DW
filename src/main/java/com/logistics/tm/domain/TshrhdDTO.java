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
public class TshrhdDTO extends CommonDTO {

  private String compkey; // 1 varchar(20) Company Key ' '
  private String warekey; // 2 varchar(20) 창고 ' '
  private String shtrtky; // 3 varchar(10) 셔틀노선키 ' '

  private String shtgrnm; // 4 varchar(10) 셔틀그룹 ' '
  private String shtrtnm; // 5 varchar(60) 셔틀노선명 ' '
  private int shtcytm; // 6 int(11) 셔틀운행주기 0
  private String shtmemo; // 7 varchar(100) 셔틀설명 ' '
  // private String destkey; // 삭제

  private String tsuseyn; // 7 varchar(1)

  private String credate; // 8 varchar(8) 생성일자 'to_char(current_timestamp(),''YYYYMMDD'')'
  private String cretime; // 9 varchar(6) 생성시간 'to_char(current_timestamp(),''HH24MISS'')'
  private String creuser; // 10 varchar(60) 생성사용자 ' '
  private String lmodate; // 11 varchar(8) 수정일자 ' '
  private String lmotime; // 12 varchar(6) 수정시간 ' '
  private String lmouser; // 13 varchar(60) 수정사용자 ' '
  // private String indibzl; // 14 varchar(1) biz logic indicator ' '
  // private String indiarc; // 15 varchar(1) Archive indicator ' '
  // private int updtchk; // int(11) Update check 0

  // user dto
  // private String telphnm; // 휴대전화번호
  // private String usernam; // 사용자이름

  // join
  // private int itemnumb;
  // private int vehiclenumb;

  // ComboBox
  private String combovl;
  private String combonm;
}
