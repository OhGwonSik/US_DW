package com.logistics.configuration.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.logistics.common.dto.CommonDTO;
import com.logistics.configuration.I18nConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class ServiceAOP {
	private final I18nConfig i18Config;

	/*
	 * 메소드명 : afterReturningForProcedure - 메소드 실행 후 시점에 작동
	 * 생성자 : WM
	 * 입력 파라미터 : jp(aop join point), result - 메소드를 실행한 후의 리턴 값
	 * 생성일자 : 2023.10
	 * 설명 : 서비스 클래스들의 특정 시점(afterReturningForProcedure())에 특정 메소드를 실행한 후 프로시저 결과 값에 수정을 가함
	 */
	// AOP for Procedure(success case)
	// save로 시작하는 method의 return type이 CommonDTO 일 때
	@AfterReturning(pointcut = "execution( * com.logistics..*Service.save*(..))", returning = "result")
	public void afterReturningForProcedure(JoinPoint jp, CommonDTO result) {
		if(result != null && result.getOmsgkey() != null) {
			log.info("ServiceAOP arg class=>{}", result.getClass());
			result.setOmsgkey(i18Config.getMessageSourceAccessor().getMessage(result.getOmsgkey()));
		}
	}

//	// AOP for Procedure(throw error case)
//	@AfterThrowing(pointcut = "execution( * com.logistics..*Service.save*(..))", throwing = "e")
//	public void afterReturningForProcedure(JoinPoint jp, RuntimeException e) {
//		log.info("jp=>{}, e=>{}", jp.getSignature(), e);
//	}
}
