package com.logistics.configuration.aop;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.logistics.configuration.db.DataSourceConfig;

/* 
 * 클래스명 : TransactionAOP
 * 생성자 : WM
 * 생성일자 : 2023.06
 * 설명 : data source별 transaction control을 위한 aop 클래스
 */	
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class TransactionAOP {
	private static final int TX_METHOD_TIMEOUT = 300; // 테스트용 : 타임아웃 120초, 운영용 : 20초로 바꿔야함.
	private static final int WEBJIS_TX_METHOD_TIMEOUT = 600; // 웹지스 타임아웃 
	private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.logistics..*Service.*(..))";
	private static final String LOGISTICS_TX_MANAGER = "logisticsTxManager";
	private static final String WEBJIS_TX_MANAGER = "webjisTxManager";
	//private static final String CHUNGLIM_TX_MANAGER = "chunglimTxManager";

	@Bean(name = LOGISTICS_TX_MANAGER)
	public DataSourceTransactionManager logisticsTxManager(
			@Qualifier(DataSourceConfig.LOGISTICS_DATASOURCE) final DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = WEBJIS_TX_MANAGER)
	public DataSourceTransactionManager webjisTxManager(
			@Qualifier(DataSourceConfig.WEBJIS_DATASOURCE) final DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	//  @Bean(name = CHUNGLIM_TX_MANAGER)
//  public DataSourceTransactionManager chunglimTxManager(
//        @Qualifier(DataSourceConfig.CHUNGLIM_DATASOURCE) final DataSource dataSource) {
//     return new DataSourceTransactionManager(dataSource);
//  }
	@Bean
	public TransactionInterceptor logisticsTxInterceptor(DataSourceTransactionManager transactionManager) {
		TransactionInterceptor txAdvice = new TransactionInterceptor();
		Properties txAttributes = new Properties();
		List<RollbackRuleAttribute> rollbackRules = new ArrayList<>();
		rollbackRules.add(new RollbackRuleAttribute(Exception.class));
		DefaultTransactionAttribute readOnlyAttribute = new DefaultTransactionAttribute(
				TransactionDefinition.PROPAGATION_REQUIRED);
		readOnlyAttribute.setReadOnly(true);
		readOnlyAttribute.setTimeout(TX_METHOD_TIMEOUT);
		RuleBasedTransactionAttribute writeAttribute = new RuleBasedTransactionAttribute(
				TransactionDefinition.PROPAGATION_REQUIRES_NEW, rollbackRules);
		writeAttribute.setTimeout(TX_METHOD_TIMEOUT);
		String readOnlyTransactionAttributesDefinition = readOnlyAttribute.toString();
		String writeTransactionAttributesDefinition = writeAttribute.toString();
		txAttributes.setProperty("select*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("get*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("search*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("find*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("count*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("*", writeTransactionAttributesDefinition);
		txAdvice.setTransactionAttributes(txAttributes);
		txAdvice.setTransactionManager(transactionManager);
		return txAdvice;
	}

	@Bean
	public Advisor logisticsTxAdviceAdvisor(@Qualifier(LOGISTICS_TX_MANAGER)DataSourceTransactionManager transactionManager) {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
		return new DefaultPointcutAdvisor(pointcut, logisticsTxInterceptor(transactionManager));
	}

	@Bean
	public TransactionInterceptor webjisTxInterceptor(DataSourceTransactionManager transactionManager) {
		TransactionInterceptor txAdvice = new TransactionInterceptor();
		Properties txAttributes = new Properties();
		List<RollbackRuleAttribute> rollbackRules = new ArrayList<>();
		rollbackRules.add(new RollbackRuleAttribute(Exception.class));
		DefaultTransactionAttribute readOnlyAttribute = new DefaultTransactionAttribute(
				TransactionDefinition.PROPAGATION_REQUIRED);
		readOnlyAttribute.setReadOnly(true);
		readOnlyAttribute.setTimeout(WEBJIS_TX_METHOD_TIMEOUT);
		RuleBasedTransactionAttribute writeAttribute = new RuleBasedTransactionAttribute(
				TransactionDefinition.PROPAGATION_REQUIRES_NEW, rollbackRules);
		writeAttribute.setTimeout(WEBJIS_TX_METHOD_TIMEOUT);
		String readOnlyTransactionAttributesDefinition = readOnlyAttribute.toString();
		String writeTransactionAttributesDefinition = writeAttribute.toString();
		txAttributes.setProperty("select*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("get*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("search*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("find*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("count*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("*", writeTransactionAttributesDefinition);
		txAdvice.setTransactionAttributes(txAttributes);
		txAdvice.setTransactionManager(transactionManager);
		return txAdvice;
	}

	@Bean
	public Advisor webjisTxAdviceAdvisor(@Qualifier(WEBJIS_TX_MANAGER)DataSourceTransactionManager transactionManager) {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
		return new DefaultPointcutAdvisor(pointcut, webjisTxInterceptor(transactionManager));
	}

//  @Bean
//  public TransactionInterceptor chunglimTxInterceptor(DataSourceTransactionManager transactionManager) {
//     TransactionInterceptor txAdvice = new TransactionInterceptor();
//     Properties txAttributes = new Properties();
//     List<RollbackRuleAttribute> rollbackRules = new ArrayList<>();
//     rollbackRules.add(new RollbackRuleAttribute(Exception.class));
//     DefaultTransactionAttribute readOnlyAttribute = new DefaultTransactionAttribute(
//           TransactionDefinition.PROPAGATION_REQUIRED);
//     readOnlyAttribute.setReadOnly(true);
//     readOnlyAttribute.setTimeout(TX_METHOD_TIMEOUT);
//     RuleBasedTransactionAttribute writeAttribute = new RuleBasedTransactionAttribute(
//           TransactionDefinition.PROPAGATION_REQUIRES_NEW, rollbackRules);
//     writeAttribute.setTimeout(TX_METHOD_TIMEOUT);
//     String readOnlyTransactionAttributesDefinition = readOnlyAttribute.toString();
//     String writeTransactionAttributesDefinition = writeAttribute.toString();
//     txAttributes.setProperty("select*", readOnlyTransactionAttributesDefinition);
//     txAttributes.setProperty("get*", readOnlyTransactionAttributesDefinition);
//     txAttributes.setProperty("search*", readOnlyTransactionAttributesDefinition);
//     txAttributes.setProperty("find*", readOnlyTransactionAttributesDefinition);
//     txAttributes.setProperty("count*", readOnlyTransactionAttributesDefinition);
//     txAttributes.setProperty("*", writeTransactionAttributesDefinition);
//     txAdvice.setTransactionAttributes(txAttributes);
//     txAdvice.setTransactionManager(transactionManager);
//     return txAdvice;
//  }
//  @Bean
//  public Advisor chunglimTxAdviceAdvisor(@Qualifier(CHUNGLIM_TX_MANAGER)DataSourceTransactionManager transactionManager) {
//     AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//     pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
//     return new DefaultPointcutAdvisor(pointcut, chunglimTxInterceptor(transactionManager));
//  }
}
