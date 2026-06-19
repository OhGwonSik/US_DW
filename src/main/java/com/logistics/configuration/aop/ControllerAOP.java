package com.logistics.configuration.aop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.sy.domain.UserVO;

import lombok.extern.slf4j.Slf4j;
@Component
@Aspect
@Slf4j
public class ControllerAOP {
	/*
	 * 메소드명 : controllerUserPointcut - AOP Join point(pointcut)
	 * 최초생성자 : WM
	 * 생성일자 : 2023.06
	 * 설명 : AOP를 위한 pointcut으로 RestController annotation이 붙은 com.logistics 하위의 *Controller.* 에 대해 작동
	 */
	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *) && execution( * com.logistics..*Controller.*(..))")
	public void controllerUserPointcut() {}

	@Autowired
	public HttpServletRequest req;
	/*
	 * 메소드명 : beforeControllerForUser - controllerUserPointcut() 시점 실행 전에 작동
	 * 생성자 : WM
	 * 입력 파라미터 : jp(aop join point)
	 * 생성일자 : 2023.06
	 * 설명 : 특정 시점(controllerUserPointcut())에 Spring SecurityContextHolder에 담긴 유저 정보를 가져와 저장 함
	 */
	@Before("controllerUserPointcut()")
	public void beforeControllerForUser(JoinPoint jp) {
		Object[] args = jp.getArgs();
		if(SecurityContextHolder.getContext()!=null && SecurityContextHolder.getContext().getAuthentication()!=null && SecurityContextHolder.getContext().getAuthentication().getPrincipal()!=null) {
			Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(!(obj instanceof UserVO)) {
				return;
			}
			UserVO user = (UserVO) obj;
			for(Object arg:args) {
				if(arg instanceof CommonDTO common) {
//					log.info("before arg=>{}", args);
					common.setUserData(user);
//					log.info("after arg=>{}", args);
					log.info("arg class=>{}", arg.getClass());
				} else if(arg instanceof RequestDTO<?> requestDTO) {
					setUserDataToRequestDTO(requestDTO, user);
					setUserDataToRequestDTO(requestDTO.getHeadGrid(), user);
					setUserDataToRequestDTO(requestDTO.getItemGrid(), user);
					setUserDataToRequestDTO(requestDTO.getSubItemGrid(), user);
				}
			}
		}
	}

	/*
	 * 메소드명 : setUserDataToRequestDTO - 저장 로직
	 * 생성자 : WM
	 * 생성일자 : 2023.06
	 * 입력 파라미터 : requestDTO - 그리드 데이터(requestDTO 참조), user - 유저정보
	 * 설명 : 유저 정보 저장 로직 메소드
	 */
	private void setUserDataToRequestDTO(RequestDTO<?> requestDTO, UserVO user) {
		if(requestDTO==null) {
			return;
		}
		setUserDataToList(requestDTO.getAddList(), user);
		setUserDataToList(requestDTO.getDeleteList(), user);
		setUserDataToList(requestDTO.getOldList(), user);
		setUserDataToList(requestDTO.getUpdateList(), user);
	}

	/*
	 * 메소드명 : setUserDataToList - 유저정보 저장 로직
	 * 생성자 : WM
	 * 생성일자 : 2023.06
	 * 입력파라미터 : list - list, user - 유저 정보
	 * 설명 : 유저 정보 저장 로직 메소드
	 */
	private void setUserDataToList(List<?> list, UserVO user) {
		if(list==null) {
			return;
		}
		for(Object obj :list) {
			CommonDTO common = (CommonDTO) obj;
			common.setUserData(user);
		}
	}
}
