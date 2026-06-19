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
public class TvhcmaDTO extends CommonDTO {

  private String compkey; // varchar(20) Company Key ' '
  private String warekey; // varchar(20) 창고코드 ' '
  private String vehicky; // varchar(10) 차량코드 ' '
  private String vhcfnam; // varchar(20) 차량번호 ' '
  private String vhcsnam; // varchar(20) 내부차량명 ' '
  private String dlvtype; // varchar(10) 차량운행타입 ' '
  private String carrier; // varchar(20) 운송사코드 ' '

  private String vhcopty; // varchar(10) 차량운영형태 ' '
  private String vhctype; // varchar(10) 차량종류 ' '
  private String vhctncd; // varchar(10) 차량톤수 ' '
  private double maxwegt; // decimal 10,3 최대중량 0
  private double maxcapa; // decimal 10,3 최대적재 CBM 0
  private String sperfyn; // varchar(1) 특수차량1 냉동 'N'
  private String spelfyn; // varchar(1) 특수차량2 리프트 'N'
  private String specvyn; // varchar(1) 특수차량3 컨베어 'N'
  private String spe04yn; // varchar(1) 특수차량4 'N'
  private String spe05yn; // varchar(1) 특수차량5 'N'
  private String vehgrnm; // varchar(10) 차량관리그룹 ' '
  private String vehcsnm; // varchar(10) 차량관리등급 ' '
  private String vehtpyn; // varchar(1) 용차여부 'N'
  private String inbvhyn; // varchar(1) 입고차량 'N'
  private String oubvhyn; // varchar(1) 출고차량 'Y'
  private String gpseqyn; // varchar(1) GPS장비여부 'N'
  private String gpsvhid; // varchar(60) GPS고유ID ' '
  private String imei; // GPS 단말기 번호
  private String msisdn; // DTG단말기 아이디

  private String vhrcsts; // varchar(10) 차량상태
  private String vhcmemo; // varchar(100) 메모
  private String vhuseyn; // varchar(1) 사용여부 'Y'
  private String credate; // varchar(8) 생성일자 'to_char(current_timestamp(),''YYYYMMDD'')'
  private String cretime; // varchar(6) 생성시간 'to_char(current_timestamp(),''HH24MISS'')'
  private String creuser; // varchar(60) 생성사용자 ' '
  private String lmodate; // varchar(8) 수정일자 ' '
  private String lmotime; // varchar(6) 수정시간 ' '
  private String lmouser; // varchar(60) 수정사용자 ' '
  // private String indibzl; // varchar(1) biz logic indicator ' '
  // private String indiarc; // varchar(1) Archive indicator ' '
  // private int UPDTCHK; // int(11) Update check 0

  // old
  private String oldwarekey;

  //
  // private String state;
  private String fromdate;
  private String todate;

  // ComboBox
  private String combovl;
  private String combonm;

  // 커스텀
  private int sumvehi; // 배차키 총합
  private String ownernm; //기사명 - 운송비검증처리 사용 2023.11.22 최강호
  private String mdestky; //도착지 - 운송비검증처리 사용 2023.11.22 최강호
}
