package com.logistics.configuration.db;

import javax.sql.DataSource;

import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@MapperScan(basePackages={
		"com.logistics.common",
		"com.logistics.main",
		"com.logistics.md",
		"com.logistics.bm",
		"com.logistics.om",
		"com.logistics.pt",
		"com.logistics.sy",
		"com.logistics.tm",
		"com.logistics.wm",
		"com.logistics.api"
},sqlSessionFactoryRef=MybatisConfig.LOGISTICS_SESSION_FACTORY)
@MapperScan(basePackages={
		"com.logistics.chunglim",
		"com.logistics.webjis"
},sqlSessionFactoryRef=MybatisConfig.WEBJIS_SESSION_FACTORY)
public class MybatisConfig {
	public static final String LOGISTICS_MYBATIS_SESSION_CONFIG = "logisticsMybatisSessionConfig";
	public static final String LOGISTICS_SESSION_FACTORY = "logisticsSessionFactory";
	public static final String LOGISTICS_SESSION_TEMPLATE = "logisticsSessionTemplate";

	public static final String WEBJIS_MYBATIS_SESSION_CONFIG = "webjisMybatisSessionConfig";
	public static final String WEBJIS_SESSION_FACTORY = "webjisSessionFactory";
	//public static final String CHUNGLIM_SESSION_FACTORY = "chunglimSessionFactory";
	public static final String WEBJIS_SESSION_TEMPLATE = "webjisSessionTemplate";
	//public static final String CHUNGLIM_SESSION_TEMPLATE= "chunglimSessionTemplate";

	@Primary
	@Bean(name = LOGISTICS_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactory logisticsSessionFactory(
			@Qualifier(DataSourceConfig.LOGISTICS_DATASOURCE) final DataSource dataSource,
			final ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mappers/logistics/**/*.xml"));
		/***********************************
		 * DATE: 2023.07.21
		 * CREATE : WH.SHIN
		 * CONTENT : Mybatis CacheEnabled False
		 * *********************************/
		logisticsMybatisSessionConfig().setLocalCacheScope(LocalCacheScope.STATEMENT);
		logisticsMybatisSessionConfig().setCacheEnabled(false);
		sqlSessionFactoryBean.setConfiguration(logisticsMybatisSessionConfig());
		return sqlSessionFactoryBean.getObject();
	}

	@Primary
	@Bean(name = LOGISTICS_SESSION_TEMPLATE)
	public SqlSessionTemplate logisticsSessionTemplate(
			@Qualifier(LOGISTICS_SESSION_FACTORY) SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	//===============================
	// WEBJIS SqlSessionFactory
	//===============================
	@Bean(name = WEBJIS_SESSION_FACTORY, destroyMethod = "")
	public SqlSessionFactory webjisSessionFactory(
			@Qualifier(DataSourceConfig.WEBJIS_DATASOURCE) final DataSource dataSource,
			final ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mappers/webjis/*.xml"));
		/***********************************
		 * DATE: 2023.07.21
		 * CREATE : WH.SHIN
		 * CONTENT : Mybatis CacheEnabled False
		 * *********************************/
		webjisMybatisSessionConfig().setLocalCacheScope(LocalCacheScope.STATEMENT);
		webjisMybatisSessionConfig().setCacheEnabled(false);
		sqlSessionFactoryBean.setConfiguration(webjisMybatisSessionConfig());
		return sqlSessionFactoryBean.getObject();
	}

	//===============================
	// WEBJIS SqlSessionTemplate
	//===============================
	@Bean(name = WEBJIS_SESSION_TEMPLATE)
	public SqlSessionTemplate webjisSessionTemplate(
			@Qualifier(WEBJIS_SESSION_FACTORY) SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}


	//===============================
	// MYBATIS_SESSION_CONFIG
	//===============================
	@Bean(name = LOGISTICS_MYBATIS_SESSION_CONFIG)
	public org.apache.ibatis.session.Configuration logisticsMybatisSessionConfig(){
		return new org.apache.ibatis.session.Configuration();
	}

	@Bean(name = WEBJIS_MYBATIS_SESSION_CONFIG)
	public org.apache.ibatis.session.Configuration webjisMybatisSessionConfig(){
		return new org.apache.ibatis.session.Configuration();
	}

	//  @Bean(name = CHUNGLIM_SESSION_FACTORY, destroyMethod = "")
//  public SqlSessionFactory chunglimSessionFactory(
//        @Qualifier(DataSourceConfig.CHUNGLIM_DATASOURCE) final DataSource dataSource,
//        final ApplicationContext applicationContext) throws Exception {
//     SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//     sqlSessionFactoryBean.setDataSource(dataSource);
//     sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mappers/chunglim/**/*.xml"));
//
//     /***********************************
//      * DATE: 2023.07.21
//      * CREATE : WH.SHIN
//      * CONTENT : Mybatis CacheEnabled False
//      * *********************************/
//
//     mybatisSessionConfig().setLocalCacheScope(LocalCacheScope.STATEMENT);
//     mybatisSessionConfig().setCacheEnabled(false);
//
//     sqlSessionFactoryBean.setConfiguration(mybatisSessionConfig());
//
//     return sqlSessionFactoryBean.getObject();
//  }
//
//  @Bean(name = CHUNGLIM_SESSION_TEMPLATE)
//  public SqlSessionTemplate chunglimSessionTemplate(
//        @Qualifier(CHUNGLIM_SESSION_FACTORY) SqlSessionFactory sqlSessionFactory) {
//     return new SqlSessionTemplate(sqlSessionFactory);
//  }

}
