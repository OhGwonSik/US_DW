package com.logistics.webjis.config;


import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Configuration
@PropertySource("classpath:/webjis-param.properties")
@ConfigurationProperties(prefix = "webjis")
@Getter
@Setter
@ToString
public class WebjisConfig {
	
	private Map<String, Map<String, String>> params;

	private String url;
	
	private String compkey;
	
	private String ifrcvur;
	
	private Map<String, String> headers;
}
