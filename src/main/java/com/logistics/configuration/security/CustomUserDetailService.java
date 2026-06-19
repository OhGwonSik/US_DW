package com.logistics.configuration.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.logistics.configuration.JwtTokenProvider;
import com.logistics.sy.domain.UserVO;
import com.logistics.sy.service.UserService;

import java.util.Locale;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Autowired
	MessageSourceAccessor messageSourceAccessor;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	/**
	 * 인증 하는 부분
	 */
	@Override
	public UserDetails loadUserByUsername(String useract) {
		UserVO userVo = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (RequestContextHolder.getRequestAttributes() != null && auth == null) {
			HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

			// HttpSession session = req.getSession();

			// if (session != null ) {

			String compkey = (String) req.getSession().getAttribute("compkey");
			String langkey = (String) req.getSession().getAttribute("langkey");
//			String warekey = (String) req.getSession().getAttribute("warekey");

			Locale.setDefault(new Locale(langkey));
			// 사용자 조회
			userVo = userService.getUserInfo(compkey, useract);

		} else if (auth != null) {
			userVo = (UserVO) auth.getPrincipal();
		}
		return userVo;
	}

}