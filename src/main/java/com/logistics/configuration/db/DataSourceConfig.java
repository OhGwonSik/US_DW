package com.logistics.configuration.db;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Primary;

@Configuration
@ConfigurationProperties
public class DataSourceConfig {
	public static final String LOGISTICS_DATASOURCE = "logisticsDs";
	public static final String WEBJIS_DATASOURCE = "webjisDs";
	public static final String CHUNGLIM_DATASOURCE = "chunglimDs";

	@Primary
	@Bean(name = LOGISTICS_DATASOURCE)
	@ConfigurationProperties(prefix = "spring.datasource.hikari.logistics")
	public DataSource logisticsDs() {
		return new HikariDataSource();
	}

	@Bean(name = WEBJIS_DATASOURCE)
	@ConfigurationProperties(prefix = "spring.datasource.hikari.webjis")
	public DataSource webjisDs() {
		return new HikariDataSource();
	}
//  @Bean(name = CHUNGLIM_DATASOURCE)
//  @ConfigurationProperties(prefix = "spring.datasource.hikari.chunglim")
//  public DataSource chunglimDs() {
//     return new HikariDataSource();
//  }
}