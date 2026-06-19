package com.logistics.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
  @Bean
  public CorsFilter corsFilter() {

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOriginPattern("*");
    // config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");

    // source.registerCorsConfiguration("/api/**", config);
    source.registerCorsConfiguration("/**", config);

    CorsConfiguration configForApp = new CorsConfiguration(config);
    config.addAllowedOrigin("http://localhost:8100");
    config.addAllowedOrigin("http://localhost");
    config.addAllowedOrigin("https://localhost");
    config.addAllowedOrigin("http://ec2-3-37-244-7.ap-northeast-2.compute.amazonaws.com:31200");
    config.addAllowedOrigin("http://ec2-3-37-244-7.ap-northeast-2.compute.amazonaws.com:31100");
    config.addAllowedOrigin("https://localhost");
    config.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
    config.setAllowCredentials(true);

    source.registerCorsConfiguration("/**", configForApp);
    source.registerCorsConfiguration("/**", config);

    return new CorsFilter(source);
  }
}
