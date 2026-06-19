package com.logistics.api.nan.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Configuration
@PropertySource("classpath:/nan-param.properties")
@ConfigurationProperties(prefix = "nan")
@Getter
@Setter
@ToString
public class NanConfig {

	private String url;
	private String apiKey;
	private String wareHousePCode;
	private String wareHouseLCode;
	private String productCode;
	private String successCode;
	private String compkey;
	private String ifrcvur;
}
