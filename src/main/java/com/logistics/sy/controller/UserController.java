package com.logistics.sy.controller;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.logistics.configuration.JwtTokenProvider;
import org.apache.logging.log4j.Logger;
import org.jfree.util.Log;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.logistics.sy.domain.UserDTO;
import com.logistics.sy.domain.UserVO;
import com.logistics.sy.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final JwtTokenProvider jwtTokenPrivider;
	private final UserService userService;
	private final MessageSource msg;

	private static final String IS_MOBILE = "MOBI";
	private static final String IS_PC = "PC";
	private static final String G_PROGCMD = "gprogcmd";
	private static final String G_PROGRID = "gprogrid";
	private static final String LANGKEY = "langkey";
	private static final String LANGVAL = "us";

	//====================================================
	//======= 31005 서버 인증 호출 후 리턴데이터로 쿠키 생성 ====
	//====================================================
	@PostMapping("/createAuthCookie")
	public ResponseEntity<Map<String, Object>> createAuthCookie(HttpServletRequest request, @RequestBody Map<String, String> tokenObject) {

		long expiredTime = jwtTokenPrivider.expiredTokenTime(tokenObject.get("token"));

		ResponseCookie responseCookie = ResponseCookie.from("token", tokenObject.get("token")).maxAge(expiredTime).httpOnly(true).secure(false).path("/")
				.build();

		Map<String, Object> tokenMap = new HashMap<>();
		tokenMap.put("token", tokenObject.get("token"));

		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, responseCookie.toString()).body(tokenMap);
	}

	/**
	 * localhost:8080 시 login 으로 redirect
	 * 
	 * @return
	 */
	public static String isDevice(HttpServletRequest req) {
		String userAgent = req.getHeader("User-Agent").toUpperCase();
		if (userAgent.indexOf(IS_MOBILE) > -1) {
			return IS_MOBILE;
		}
		return IS_PC;
	}

	// m로그인에 관한 설정 2023-08-01 오권식
	@GetMapping
	public ModelAndView root(HttpServletRequest request) {
		String checkDevice = isDevice(request);
		ModelAndView mv = new ModelAndView("jsonView");

		if (IS_MOBILE.equals(checkDevice)) {
			mv.setViewName("redirect:mlogin");
		} else if (IS_PC.equals(checkDevice)) {
			mv.setViewName("redirect:kiosk");
		}
		return mv;
	}

	/*
	 * 키오스크 메뉴화면
	 */
	@GetMapping("/kiosk")
	public ModelAndView kiosk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("jsonView");
		String accountCode = request.getParameter("account_code");
		
		mv.setViewName("redirect:/kiosk?account_code=200&langkey=us");
		
		if(accountCode != null && (!accountCode.isEmpty())) {
			mv.setViewName("/kiosk");
		}
		return mv;
	}

	/*
	 * 키오스크 메뉴화면
	 */
	@GetMapping("/kiosklogin")
	public ModelAndView kioskLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("jsonView");
		StringBuilder sb = new StringBuilder("/main/mainPageDeviceSD");
		String gprogcmd = request.getParameter(G_PROGCMD);
		String gprogrid = request.getParameter(G_PROGRID);
		String url = "";
		try {
			sb.append("?" + G_PROGCMD + "=" + gprogcmd);
			sb.append("&" + G_PROGRID + "=" + gprogrid);
			sb.append("&" + LANGKEY + "=" + LANGVAL);
			url = URLEncoder.encode(sb.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		mv.addObject("domain", url);
		mv.setViewName("/kiosklogin");
		return mv;
	}

	/*
	 * 로그인
	 */
	@GetMapping("/login")
	public ModelAndView Login(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.setViewName("/login");
		return mv;
	}

	// 광주용 ** 임시 **
	@GetMapping("/login2")
	public ModelAndView Login2(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.setViewName("/login2");
		return mv;
	}

	/*
	 * 모바일 토큰 로그인 2023.10.19 임시
	 */
	@GetMapping("/mlogintoken")
	public ModelAndView mLoginToken(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.setViewName("/mlogintoken");
		return mv;
	}

	/*
	 * 모바일 로그인 m로그인에 관한 설정 임시 2023-08-01
	 */
	@GetMapping("/mlogin")
	public ModelAndView mLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("jsonView");
		String accountCode = request.getParameter("account_code");
		
		mv.setViewName("redirect:/mlogin?account_code=200");
		
		mv.addObject("warekeyList", userService.selectWarekey());
		if(request.getParameter("account_code") != null && (!accountCode.isEmpty())) {
			mv.setViewName("/mlogin");
		}
		return mv;
	}

	/*
	 * 모바일 로그인 m로그인에 관한 설정 임시(광주) 2023-09-27
	 */
	@GetMapping("/mlogin2")
	public ModelAndView mLogin2(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("warekeyList", userService.selectWarekey());
		mv.setViewName("/mlogin2");
		return mv;
	}

	/*
	 * 로그인 실패
	 */
	@GetMapping("/access_denied")
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.setViewName("/access_denied");
		return mv;
	}

	/*
	 * 회원가입
	 */
	@GetMapping("/signUp")
	public ModelAndView signUpForm(Model model) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.setViewName("/signup");
		return mv;
	}

	/*
	 * 가입자 정책
	 */
	@GetMapping("/policy/accessPrivacy")
	public ModelAndView accessPrivacy() {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.setViewName("/com/access/accessPrivacy");
		return mv;
	}

	@GetMapping("/policy/accessService")
	public ModelAndView accessService() {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.setViewName("/com/access/accessService");
		return mv;
	}

	/**
	 * 회원가입 진행
	 * 
	 * @param userVo
	 * @return
	 */
	@PostMapping("/signUp.do")
	public ModelAndView signUp(UserDTO userDTO) {
		ModelAndView mv = new ModelAndView("jsonView");
		userService.insertUser(userDTO); // 사용자 등록
		mv.addObject("userList", userService.getUserInfo(userDTO.getCompkey(), userDTO.getUseract()));
		mv.addObject("code", "200");
		mv.setViewName("/signup_success");
		return mv;
		// return "/signup_denied";
	}

	/**
	 * 회원가입 진행 : company 리스트
	 * 
	 * @param userVo
	 * @return
	 */
	// @GetMapping("/signUp/companyComboBox.do")
	// @ResponseBody
	// public Map<String, Object> CompanySearch(@RequestParam Map<String, Object>
	// params) {
	// UserVO userInfo = new UserVO();
	// //mv.addObject("commaList", mdo01Service.getMcommaList(param, userInfo));
	// mv.addObject("code", "200");
	// return mv;
	// }

	/**
	 * 유저 페이지
	 * 
	 * @param model
	 * @param authentication
	 * @return
	 */
	@GetMapping("/user_access")
	public ModelAndView userAccess(Model model, @AuthenticationPrincipal UserVO userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("info", userInfo.getUseract() + "의 " + userInfo.getUsernam() + "님"); // 유저 아이디
		mv.addObject("userinfo", userInfo);
		mv.setViewName("/user_access");
		return mv;
	}

	// 로그인 성공 후 페이지
	// 사용자 유형 또는 아이디별 메인페이지 이동 시키기
	@PostMapping("/login_proc")
	public ModelAndView userAccessProc(Model model, @AuthenticationPrincipal UserVO userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("info", userInfo.getUseract() + "의 " + userInfo.getUsernam() + "님"); // 유저 아이디
		mv.addObject("userinfo", userInfo);
		mv.setViewName("/user_access");
		return mv;
	}

	/*
	 * 사용자 Grid Setting & Column Layout 조회
	 */
	@ResponseBody
	@GetMapping(value = "/sy/syu10Select.do")
	public ModelAndView syu10Select(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVO userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("gridSettingLayout", userService.gridSettingLayout(param, userInfo));
		mv.addObject("gridColumnLayout", userService.gridColumnLayout(param, userInfo));
		mv.addObject("layoutSettingName", layoutSettingName());
		return mv;
	}

	/*
	 * 사용자 Grid Setting & Column Layout 저장
	 */
	@ResponseBody
	@PostMapping(value = "/sy/syu10Save.do")
	public ModelAndView syu10Save(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVO userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		userService.setSYU10Save(param, userInfo);
		return mv;
	}

	/*
	 * 사용자 Grid Setting & Column Layout Reset
	 */
	@ResponseBody
	@RequestMapping(value = "/sy/syu10Reset.do", method = RequestMethod.DELETE)
	public ModelAndView syu10Reset(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVO userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		userService.setSYU10Reset(param, userInfo);
		return mv;
	}

	/*
	 * layoutSettingName 다국어
	 */
	public ArrayList<HashMap<String, Object>> layoutSettingName() {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<>();

		map.put("layoutSave", msg.getMessage("sy.layout.layoutSave", null, LocaleContextHolder.getLocale()));
		map.put("layoutSetting", msg.getMessage("sy.layout.layoutSetting", null, LocaleContextHolder.getLocale()));
		map.put("layoutReset", msg.getMessage("sy.layout.layoutReset", null, LocaleContextHolder.getLocale()));

		map.put("export", msg.getMessage("sy.layout.export", null, LocaleContextHolder.getLocale()));
		map.put("exportCsv", msg.getMessage("sy.layout.exportCsv", null, LocaleContextHolder.getLocale()));
		map.put("exportHtml", msg.getMessage("sy.layout.exportHtml", null, LocaleContextHolder.getLocale()));
		map.put("exportJson", msg.getMessage("sy.layout.exportJson", null, LocaleContextHolder.getLocale()));
		map.put("exportXlsx", msg.getMessage("sy.layout.exportXlsx", null, LocaleContextHolder.getLocale()));

		map.put("numberCell", msg.getMessage("sy.layout.numberCell", null, LocaleContextHolder.getLocale()));

		map.put("selectionModel", msg.getMessage("sy.layout.selectionModel", null, LocaleContextHolder.getLocale()));
		map.put("selectionModelCell",
				msg.getMessage("sy.layout.selectionModelCell", null, LocaleContextHolder.getLocale()));
		map.put("selectionModelRow", msg.getMessage("sy.layout.selectionModelRow", null, LocaleContextHolder.getLocale()));

		map.put("freeze", msg.getMessage("sy.layout.freeze", null, LocaleContextHolder.getLocale()));
		map.put("colsCancel", msg.getMessage("sy.layout.colsCancel", null, LocaleContextHolder.getLocale()));
		map.put("rowsCancel", msg.getMessage("sy.layout.rowsCancel", null, LocaleContextHolder.getLocale()));
		map.put("freezeCol", msg.getMessage("sy.layout.freezeCol", null, LocaleContextHolder.getLocale()));
		map.put("freezeRow", msg.getMessage("sy.layout.freezeRow", null, LocaleContextHolder.getLocale()));

		map.put("columnBorders", msg.getMessage("sy.layout.columnBorders", null, LocaleContextHolder.getLocale()));
		map.put("rowBorders", msg.getMessage("sy.layout.rowBorders", null, LocaleContextHolder.getLocale()));
		map.put("stripeRows", msg.getMessage("sy.layout.stripeRows", null, LocaleContextHolder.getLocale()));

		map.put("addNodes", msg.getMessage("sy.layout.addNodes", null, LocaleContextHolder.getLocale()));
		map.put("undo", msg.getMessage("sy.layout.undo", null, LocaleContextHolder.getLocale()));
		map.put("redo", msg.getMessage("sy.layout.redo", null, LocaleContextHolder.getLocale()));
		map.put("groupModel", msg.getMessage("sy.layout.groupModel", null, LocaleContextHolder.getLocale()));
		map.put("copy", msg.getMessage("sy.layout.copy", null, LocaleContextHolder.getLocale()));
		map.put("paste", msg.getMessage("sy.layout.paste", null, LocaleContextHolder.getLocale()));

		map.put("allFields", msg.getMessage("pq.toolbar.allFields", null, LocaleContextHolder.getLocale()));
		map.put("enterYourKeyword", msg.getMessage("pq.toolbar.enterYourKeyword", null, LocaleContextHolder.getLocale()));
		map.put("find", msg.getMessage("bt.find", null, LocaleContextHolder.getLocale()));
		map.put("add", msg.getMessage("bt.add", null, LocaleContextHolder.getLocale()));
		map.put("reset", msg.getMessage("bt.reset", null, LocaleContextHolder.getLocale()));
		map.put("delete", msg.getMessage("bt.delete", null, LocaleContextHolder.getLocale()));
		map.put("refresh", msg.getMessage("bt.refresh", null, LocaleContextHolder.getLocale()));
		map.put("save", msg.getMessage("bt.save", null, LocaleContextHolder.getLocale()));

		list.add(map);

		return list;
	}

	@PostMapping("/sy/findId.do")
	@ResponseBody
	public ModelAndView findId(@RequestBody Map<String, Object> param) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("result", userService.findId(param));
		return mv;
	}

	@GetMapping("/user/list")
	public List<UserVO> userSelect(@ModelAttribute UserVO userInfo) {
		// public ModelAndView userSelect(@ModelAttribute UserVO userInfo) {
		List<UserVO> userlist = userService.getUserInfoFromViewSusrma(userInfo);
		return userlist;
		// ModelAndView mv = new ModelAndView("jsonView");
		// mv.addObject("list", userlist);
		// return mv;
	}

}