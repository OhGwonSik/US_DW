package com.logistics.api.dayou.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel(description = "대유 출고 인터페이스 수신 객체")
public class DayouVO {

	// TABLE : IFSAL11R
	/**
	 * Company Key
	 */
	@Size(max= 20)
	@ApiModelProperty(value = "Company Key", allowEmptyValue = true)
	private String compkey;
	/**
	 * IF수주SEQ
	 */
	@ApiModelProperty(value = "IF수주SEQ", allowEmptyValue = true)
	private String ifsalsq;
	/**
	 * 완성차코드
	 */
	@Size(max= 10)
	@ApiModelProperty(value = "완성차코드", example = "NQ")
	private String carCd;
	/**
	 * 완성차바디넘버
	 */
	@Size(max= 10)
	@ApiModelProperty(value = "완성차바디넘버", example = "JWW 056899")
	private String carBodyNo;
	/**
	 * 입출번호
	 */
	@Size(max= 10)
	@ApiModelProperty(value = "입출번호", example = "3959")
	private String entryExitNo;
	/**
	 * 순번
	 */
	@NotNull
	@ApiModelProperty(value = "순번", example = "1")
	private Integer seq;
	/**
	 * 아이템코드1(ALC)
	 */
	@Size(max= 10)
	@ApiModelProperty(value = "아이템코드1(ALC)", example = "05A")
	private String itemCd1;
	/**
	 * 아이템코드2(서열)
	 */
	@Size(max= 10)
	@ApiModelProperty(value = "아이템코드2(서열)", example = "8298")
	private String itemCd2;
	/**
	 * 아이템코드3(스키)
	 */
	@Size(max= 10)
	@ApiModelProperty(value = "아이템코드3(스키)", example = "0565FR")
	private String itemCd3;
	/**
	 * 아이템코드4
	 */
	@Size(max= 10)
	@ApiModelProperty(value = "아이템코드4", allowEmptyValue = true)
	private String itemCd4;
	/**
	 * 아이템코드5
	 */
	@Size(max= 10)
	@ApiModelProperty(value = "아이템코드5", allowEmptyValue = true)
	private String itemCd5;
	/**
	 * 상태
	 */
	@Size(max= 10)
	@ApiModelProperty(value = "상태", example = "1")
	private String itemProcess;
	/**
	 * 상차지
	 */
	@Size(max= 10)
	@ApiModelProperty(value = "상차지", example = "DAYOU01")
	private String whFrom;
	/**
	 * 하차지
	 */
	@Size(max= 10)
	@ApiModelProperty(value = "하차지", example = "KIA02")
	private String whTo;
	/**
	 * 납품일
	 */
	@Size(max= 8)
	@ApiModelProperty(value = "납품일", example = "20230921")
	private String deliveryDt;
	/**
	 * 납품요청일
	 */
	@Size(max= 8)
	@ApiModelProperty(value = "납품요청일", example = "20230920")
	private String deliveryReqDt;
	/**
	 * 출고일
	 */
	@Size(max= 8)
	@ApiModelProperty(value = "출고일", example = "20230925")
	private String departureDt;
	/**
	 * 출고일시
	 */
	@Size(max= 14)
	@ApiModelProperty(value = "출고일시", example = "20230925011043")
	private String departureDtTm;
	/**
	 * 데이터처리상태
	 */
	@Size(max= 1)
	@ApiModelProperty("데이터처리상태")
	private String ifprsts;
	/**
	 * 데이터수신일자
	 */
	@Size(max= 8)
	@ApiModelProperty("데이터수신일자")
	private String ifrcvdt;
	/**
	 * 데이터수신시간
	 */
	@Size(max= 6)
	@ApiModelProperty("데이터수신시간")
	private String ifrcvtm;
	/**
	 * 데이터수신사용자
	 */
	@Size(max= 60)
	@ApiModelProperty("데이터수신사용자")
	private String ifrcvur;
	/**
	 * 작업오류내용
	 */
	@Size(max= 255)
	@ApiModelProperty("작업오류내용")
	private String iferrnm;
	/**
	 * 작업완료일자
	 */
	@Size(max= 8)
	@ApiModelProperty("작업완료일자")
	private String ifcmpdt;
	/**
	 * 작업완료시간
	 */
	@Size(max= 6)
	@ApiModelProperty("작업완료시간")
	private String ifcmptm;
	/**
	 * 작업완료사용자
	 */
	@Size(max= 60)
	@ApiModelProperty("작업완료사용자")
	private String ifcmpur;
	/**
	 * Business logic indicator
	 */
	@Size(max= 1)
	@ApiModelProperty("Business logic indicator")
	private String indibzl;
	/**
	 * Archive indicator
	 */
	@Size(max= 1)
	@ApiModelProperty("Archive indicator")
	private String indiarc;
	/**
	 * Update check
	 */
	@NotNull
	@ApiModelProperty("Update check")
	private Integer updtchk;
}
