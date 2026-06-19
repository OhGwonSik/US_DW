package com.logistics.configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
// import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * 2023 08 04 이재형 (TM)
 * TM 앱 통신 jwt 변경 
 * JWT 토큰 사용 TokenDataDTO
 * 
 */

@Builder
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class TokenDataDTO {
  private String grantType;
  private String accessToken;
  private String refreshToken;

  // private Long accessTokenValidityInSeconds;
}
