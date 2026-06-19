package com.logistics.configuration.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket apiV1(){
        //Docket : swagger Bean
        return new Docket(DocumentationType.OAS_30)
            .useDefaultResponseMessages(true) //기본 응답 메시지 표시 여부
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.logistics.api.dayou.controller")) //swagger탐색 대상 패키지
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("스마트 물류플래폼(울산)")
            .description("스마트 물류플랫폼(울산) 물류납품 API 입니다.")
            .version("1.0")
            .build();
    }
}
