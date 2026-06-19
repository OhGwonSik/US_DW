package com.logistics.configuration;

import java.io.IOException;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logistics.sy.domain.UserVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
// UsernamePasswordAuthenticationFilter

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {
	public static final String AUTHORIZATION_HEADER = "Authorization";
	private JwtTokenProvider jwtTokenProvider;

	public JwtFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	/*  doFilterInternal - json web token Filter 처리
	 *   최초 생성일시	: 2023-08-10
	 *   최초 생성자		: 이재형
	 *   입력 PARAMETA	: HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
	 *   출력 PARAMETA	:
	 *   설명			: 물류 운영 에서 생성한 cookie값 (cookie명 : token) 을 복호화 하여 인증처리한다
	 *   수정 내역		:
	 *   수정일시		: 2023-10-31
	 *	 수정자			: 신원혁
	 *	 변경 사항		:
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String returnUrl = "";

		String kioskLoginURI = request.getContextPath()+"/kiosklogin";
		String kioskURI = request.getContextPath()+"/main/mainPageDeviceSD";
		if(kioskURI.equals(request.getRequestURI()) || kioskLoginURI.equals(request.getRequestURI())){
			returnUrl = "/kiosk";
		}

		// for preflight CORS 요청
		if ("OPTIONS".matches(request.getMethod())) {

		} else {
			try {
				String jwt = request.getHeader(AUTHORIZATION_HEADER);
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();

				if(StringUtils.hasText(jwt)) {
					if (Pattern.matches("^Bearer .*", jwt)) {
						jwt = jwt.replaceAll("^Bearer( )*", "");
					}
				}else {
					Cookie[] cookies = request.getCookies();
					if(cookies != null){
						//TODO 쿠키가 없는케이스에서 오류나서 널체크로직 임시부여, 적정성 검토필요
						for(Cookie cookie:cookies) {
							if("token".equals(cookie.getName())) {
								jwt = cookie.getValue();
							}
						}
					}
				}

				if(StringUtils.hasText(jwt)) {
					if (Pattern.matches("^Bearer .*", jwt)) {
						jwt = jwt.replaceAll("^Bearer( )*", "");
					}

					if(jwtTokenProvider.validateToken(jwt)) {
						auth = jwtTokenProvider.getAuthentication(jwt);
						SecurityContextHolder.getContext().setAuthentication(auth);
					}
				}

				// 개발용 계정접근시 처리 부분 추후 삭제 필요 2023.10.27
				if((auth != null && jwt == null)){
					UserVO userVO = (UserVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

					if(!"DEV".equals(userVO.getRolgkey()) && !"BINBLUR_PDA".equals(userVO.getRolgkey())){
						response.sendRedirect(request.getContextPath()+"/logout?returnUrl="+returnUrl);
					}

					if(userVO == null){
						response.sendRedirect(request.getContextPath()+"/logout?returnUrl="+returnUrl);
					}
				}

				//인증정보도 없고 jwt는 있으나 auth 가 null일 경우 인증에러 로그아웃처리
				if(auth == null && jwt != null){
					response.sendRedirect(request.getContextPath()+"/logout?returnUrl="+returnUrl);
				}
			} catch (Exception e) {
				log.error("jwt error=>{}", e);
			}

		}
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));

		filterChain.doFilter(request, response);
	}
}

