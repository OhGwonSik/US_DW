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
public class TshritDTO extends CommonDTO {

  private String compkey; // 1 varchar(20) Company Key ' '
  private String warekey; // 2 varchar(20) 창고 ' '
  private String shtrtky; // 3 varchar(10) 셔틀노선키 ' '

  private int shtrtit; // 4 int(11) 셔틀노선순번 0
  private String custkey; // 5 varchar(20) 고객 ' ' << 필요함? point key랑 같이 묶이는 건가?
  private String destkey; // 6 varchar(20) 도착지 ' ' << 거점키로
  private String shtrpty; // 7 varchar(10) 보고유형 ' '

  // 이거 그러면 useyn 쓸거임??

  private String credate; // 8 varchar(8) 생성일자 'to_char(current_timestamp(),''YYYYMMDD'')'
  private String cretime; // 9 varchar(6) 생성시간 'to_char(current_timestamp(),''HH24MISS'')'
  private String creuser; // 10 varchar(60) 생성사용자 ' '
  private String lmodate; // 11 varchar(8) 수정일자 ' '
  private String lmotime; // 12 varchar(6) 수정시간 ' '
  private String lmouser; // 13 varchar(60) 수정사용자 ' '
  private String indibzl; // 14 varchar(1) biz logic indicator ' '
  private String indiarc; // 15 varchar(1) Archive indicator ' '
  // private int updtchk; // int(11) Update check 0

  // user dto
  // private String telphnm; // 휴대전화번호
  // private String usernam; // 사용자이름

  // ComboBox
  // private String combovl;
  // private String combonm;
}
