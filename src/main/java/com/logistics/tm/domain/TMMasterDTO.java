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
public class TMMasterDTO extends CommonDTO {

  private String compkey; // varchar(20) Company Key ' '
  private String warekey; // varchar(20) 창고코드 ' '
  private String vehicky; // varchar(10) 차량코드 ' '
  private String vhcfnam; // varchar(20) 차량번호 ' '
  private String vhcsnam; // varchar(20) 내부차량명 ' '
  private String dlvtype; // varchar(10) 차량운행타입 ' '
  private String carrier; // varchar(20) 운송사코드 ' '

  private String vhcopty; // varchar(10) 차량운영형태 ' '
  private String vhctype; // varchar(10) 차량종류 ' '
  private String vhctypename; //
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

  private String useract; // 3 varchar(60) 기사 계정ID ' '

  private String vaumemo; // 5 varchar(100) 기사메모 NULL
  private String vhuseyn; // 6 varchar(1) 사용여부 'Y'
  private String vhownyn;

  private String shtrtky; // 3 varchar(10) 셔틀노선키 ' '
  private String vhrcsts; // varchar(10) 차량상태
  private String vhcmemo; // varchar(100) 메모
  // private String vhuseyn; // varchar(1) 사용여부 'Y'

  private String cretime; // varchar(6) 생성시간 'to_ch

  private String lmouser; // varchar(60) 수정사용자 ' '
  // private String indibzl; // varchar(1) biz logic indicator ' '
  // private String indiarc; // varchar(1) Archive indicator ' '
  // private int UPDTCHK; // int(11) Update check 0

  private String shtrtnm; // 5 varchar(60) 셔틀노선명 ' '
  private int shtcytm; // 6 int(11) 셔틀운행주기 0
  private String shtmemo; // 7 varchar(100) 셔틀설명 ' '
  // private String destkey; // 삭제

  // private String destkey; // 삭제

  private String tsuseyn; // 7 varchar(1)

  private String shtgrnm; // 4 varchar(10) 셔틀그룹 ' '

  private String inbshyn; // 납입노선 여부
  private String oubshyn; // 납품노선 여부

  // private String destkey; // 삭제

  private int shtrtit; // 4 int(11) 셔틀노선순번 0
  private String custkey; // 5 varchar(20) 고객 ' ' << 필요함? point key랑 같이 묶이는 건가?
  private String destkey; // 6 varchar(20) 도착지 ' ' << 거점키로
  private String denamlc; //
  private String shtrpty; // 7 varchar(10) 보고유형 ' '

  // old
  private String oldwarekey;

  // private String state;
  private String fromdate;
  private String todate;

  private int srvehod; // 5 int(11) 셔틀차량 순서 0
  private int shtvcap; // 6 int(11) 상차가능 Pallet수량 0

  private String indibzl; // 13 varchar(1) biz logic indicator ' '
  private String indiarc; // 14 varchar(1) Archive indicator ' '
  // private int updtchk;

  private String oldvehicky;

  // Combo Data
  private String combovl; // comboBox value
  private String combonm; // comboBox name

  private String usernam;
  private String telphnm;

  private int itemnumb;
  private int vehiclenumb;

  private String whnamko; // 창고명 한글

  /* 2023.10.12 최재환 추가 */
  private String ownernm;
  private String mdestky;

}