package com.logistics.configuration.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		//여기에 특이한 로직이 있으면  추가

		Cookie cookie = new Cookie("token", null);
		cookie.setPath("/");
		cookie.setHttpOnly(false);
		cookie.setMaxAge(0);
		cookie.setSecure(false);
		response.addCookie(cookie);

		String returnUrl = request.getParameter("returnUrl") != null ? request.getParameter("returnUrl").toString() : "";
		if(!"".equals(returnUrl)){
			response.sendRedirect(request.getContextPath()+returnUrl);
		}
		super.onLogoutSuccess(request, response, authentication);
	}
}