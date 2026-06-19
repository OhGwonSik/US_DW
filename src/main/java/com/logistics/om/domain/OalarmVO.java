package com.logistics.om.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class OalarmVO {
	private String rowkey;
	private String compkey;
	private String alarmky;		// VARCHAR(10) 알람번호
	private String useract;		// VARCHAR(60) 사용자ID
	private String cyclety;		// VARCHAR(10) 알람주기
	private String alarmdt;		// VARCHAR(8) 알람일자
	private String alarmtm;		// VARCHAR(6) 알람시간
	private String alaredt;     // VARCHAR(8) 알람종료일
	private String alarmnm;     // VARCHAR(1000) 알람명
	private String alarmtx;     // VARCHAR(2000) 알람내용
	private String alarmrm;     // VARCHAR(2000) 알람비고
	private String credate;      
	private String cretime;     
	private String creuser;     
	private String lmodate;     
	private String lmotime;     
	private String lmouser;     
	private String indibzl;     
	private String indiarc;     
	private int updtchk;
	
	private String alarmft;		// 알람일자 (from)
	private String alarmtt;		// 알람일자 (to)
}
