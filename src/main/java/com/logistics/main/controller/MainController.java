package com.logistics.main.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.sy.domain.UserVO;
import com.logistics.sy.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MainController {

    @Autowired
    LocaleResolver localeResolver;

    @Autowired
    MessageSource messageSource;

    @Autowired
    UserService userService;

    //private final UserService userService;
    //private final UserVO userVO;

    //CustomAuthenticationSuccessHandler.onAuthenticationSuccess
    //<A href="/main/mainPage1LeftSD.do">[01 Left Single Doc 메인] </A>  MAIN1SD
    //<A href="/main/mainPage2LeftTAB.do">[02 Left TAB Doc 메인] </A>  MAIN2TAB
    //<A href="/main/mainPage3TopTAB.do">[03 Top TAB Doc 메인] </A> MAIN3TAB


    /* ********************************************
     * iFrame
     * 메인 페이지 이동
     ******************************************** */
    @GetMapping("/main/mainPageSD")
    public ModelAndView mainSD(@RequestParam Map<String, Object> params, HttpServletRequest request, @AuthenticationPrincipal UserVO userVO ){

        Map<String, String> resUserObject = new HashMap<String, String>();

        if(userVO != null){
            resUserObject.put("useract", userVO.getUseract() != null ? userVO.getUseract() : null);
            resUserObject.put("compkey", userVO.getCompkey() != null ? userVO.getCompkey() : null);
            resUserObject.put("warekey", userVO.getWaredky() != null ? userVO.getWaredky() : null);
            resUserObject.put("ownerky", userVO.getOwnerky() != null ? userVO.getOwnerky() : null);
            resUserObject.put("ptnrkey", userVO.getPtnrkey() != null ? userVO.getPtnrkey() : null);
        }

        ModelAndView mv = new ModelAndView("jsonView");				//jsonView = 데이터를 JSON형태의 응답으로 반환하기 위한 뷰 이름
        mv.addObject("gprogcmd", params.get("gprogcmd"));			//데이터 전송, 데이터를 보낼때는 addObject( ) 메소드 이용, HTTP요청에서 gprogcmd 파라미터 값을 가져옴
        mv.addObject("gprogrid", params.get("gprogrid"));           //프로그램 ID
        mv.addObject("gnatitle", params.get("gnatitle"));           //프로그램 타이틀
        mv.addObject("menuInfo", userService.getMenuInfo(params.get("gprogrid").toString()));           //프로그램 타이틀
        mv.addObject("userattr", resUserObject);                    //Response UserData

        mv.setViewName("/main/mainPageSD");						                //뷰의 이름, mainPageSD.html
        return mv;
    }

    /* ********************************************
     * KIOSK
     * 메인 페이지 이동
     ******************************************** */
    @GetMapping("/main/mainPageDeviceSD")
    public ModelAndView mainDeviceSD(@RequestParam Map<String, Object> params, HttpServletRequest request, @AuthenticationPrincipal UserVO userVO ){
        HttpSession session = request.getSession();
        session.setAttribute("themety", "MAINSD");

        Map<String, String> resUserObject = new HashMap<String, String>();

        if(userVO != null){
            resUserObject.put("useract", userVO.getUseract() != null ? userVO.getUseract() : null);
            resUserObject.put("compkey", userVO.getCompkey() != null ? userVO.getCompkey() : null);
            resUserObject.put("warekey", userVO.getWaredky() != null ? userVO.getWaredky() : null);
            resUserObject.put("ownerky", userVO.getOwnerky() != null ? userVO.getOwnerky() : null);
            resUserObject.put("ptnrkey", userVO.getPtnrkey() != null ? userVO.getPtnrkey() : null);
        }else{
            resUserObject.put("useract", null);
            resUserObject.put("compkey", null);
            resUserObject.put("warekey", null);
            resUserObject.put("ownerky", null);
            resUserObject.put("ptnrkey", null);
        }

        ModelAndView mv = new ModelAndView("jsonView");				//jsonView = 데이터를 JSON형태의 응답으로 반환하기 위한 뷰 이름
        mv.addObject("gprogcmd", params.get("gprogcmd"));			//데이터 전송, 데이터를 보낼때는 addObject( ) 메소드 이용, HTTP요청에서 gprogcmd 파라미터 값을 가져옴
        mv.addObject("gprogrid", params.get("gprogrid"));           //프로그램 ID
        mv.addObject("gnatitle", params.get("gnatitle"));           //프로그램 타이틀
        mv.addObject("userattr", resUserObject);                    //Response UserData

        mv.setViewName("/main/mainPageDeviceSD");						                //뷰의 이름, mainPageSD.html
        return mv;
    }

    /* ********************************************
     * PDA
     * 메인 페이지 이동
     ******************************************** */
    @GetMapping("/main/mainPage1LeftSD.do")
    public ModelAndView leftMainSD(@RequestParam Map<String, Object> params, HttpServletRequest request, @AuthenticationPrincipal UserVO userVO ){
        HttpSession session = request.getSession();
        session.setAttribute("themety", "MAIN1SD");

        Map<String, String> resUserObject = new HashMap<String, String>();

        if(userVO != null){
            resUserObject.put("useract", userVO.getUseract() != null ? userVO.getUseract() : null);
            resUserObject.put("compkey", userVO.getCompkey() != null ? userVO.getCompkey() : null);
            resUserObject.put("warekey", userVO.getWaredky() != null ? userVO.getWaredky() : null);
            resUserObject.put("ownerky", userVO.getOwnerky() != null ? userVO.getOwnerky() : null);
            resUserObject.put("ptnrkey", userVO.getPtnrkey() != null ? userVO.getPtnrkey() : null);
        }

        ModelAndView mv = new ModelAndView("jsonView");				//jsonView = 데이터를 JSON형태의 응답으로 반환하기 위한 뷰 이름
        mv.addObject("gprogcmd", params.get("gprogcmd"));			//데이터 전송, 데이터를 보낼때는 addObject( ) 메소드 이용, HTTP요청에서 gprogcmd 파라미터 값을 가져옴
        mv.addObject("userattr", resUserObject);                    //Response UserData

        mv.setViewName("/main/mainPage1SD");						//뷰의 이름, mainPage1SD.html
        return mv;
    }

    /* ********************************************
     * 02 Left layout [TAB Document]
     * 메인 페이지 이동 - 2023.10.20 미사용
     ******************************************** */
    @GetMapping("/main/mainPage2LeftTAB.do")
    public ModelAndView leftMainTab(@RequestParam Map<String, Object> params, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("themety", "MAIN2TAB");

        ModelAndView mv = new ModelAndView("jsonView");
        mv.setViewName("/main/mainPage2TAB");
        return mv;
    }

    /* ********************************************
     * 03 Top layout [Tab Document ]
     * 메인 페이지 이동 - 2023.10.20 미사용
     ******************************************** */
    @GetMapping("/main/mainPage3TopTAB.do")
    public ModelAndView topMainTab(@RequestParam Map<String, Object> params, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("themety", "MAIN3TAB");

        ModelAndView mv = new ModelAndView("jsonView");
        mv.setViewName("/main/mainPage3TAB");
        return mv;
    }

    /* ********************************************
     * 04 Left layout [TAB Document] - 울산용
     * 메인 페이지 이동
     ******************************************** */
    @GetMapping("/main/mainPageTAB.do")
    public ModelAndView leftMainIndustryTab(@RequestParam Map<String, Object> params, HttpServletRequest request, @AuthenticationPrincipal UserVO userVO){
        HttpSession session = request.getSession();
        session.setAttribute("themety", "MAINTAB");

        ModelAndView mv = new ModelAndView("jsonView");
        mv.setViewName("/main/mainPageTAB");

        Map<String, String> resUserObject = new HashMap<String, String>();

        if(userVO != null){
            resUserObject.put("useract", userVO.getUseract() != null ? userVO.getUseract() : null);
            resUserObject.put("compkey", userVO.getCompkey() != null ? userVO.getCompkey() : null);
            resUserObject.put("warekey", userVO.getWaredky() != null ? userVO.getWaredky() : null);
            resUserObject.put("ownerky", userVO.getOwnerky() != null ? userVO.getOwnerky() : null);
            resUserObject.put("ptnrkey", userVO.getPtnrkey() != null ? userVO.getPtnrkey() : null);
        }

        mv.addObject("userattr", resUserObject);                    //Response UserData
        return mv;
    }

    /* ********************************************
     * 04 Left layout [TAB Document] - 광주용
     * 메인 페이지 이동
     ******************************************** */
    @GetMapping("/main/mainPageTABGJ.do")
    public ModelAndView leftMainIndustryGjTab(@RequestParam Map<String, Object> params, HttpServletRequest request, @AuthenticationPrincipal UserVO userVO){
        HttpSession session = request.getSession();
        session.setAttribute("themety", "MAINTABGJ");

        ModelAndView mv = new ModelAndView("jsonView");
        mv.setViewName("/main/mainPageTABGJ");

        Map<String, String> resUserObject = new HashMap<String, String>();

        if(userVO != null){
            resUserObject.put("useract", userVO.getUseract() != null ? userVO.getUseract() : null);
            resUserObject.put("compkey", userVO.getCompkey() != null ? userVO.getCompkey() : null);
            resUserObject.put("warekey", userVO.getWaredky() != null ? userVO.getWaredky() : null);
            resUserObject.put("ownerky", userVO.getOwnerky() != null ? userVO.getOwnerky() : null);
            resUserObject.put("ptnrkey", userVO.getPtnrkey() != null ? userVO.getPtnrkey() : null);
        }

        mv.addObject("userattr", resUserObject);

        return mv;
    }


    /* ********************************************
     * PDA
     * 메뉴클릭시 페이지 이동 공통 컨트롤러 Get 방식
     ******************************************** */
    @GetMapping("/main/singlePageCMD.do")
    public ModelAndView menuSinglPage(@RequestParam Map<String, Object> params, @AuthenticationPrincipal UserVO userVO) {
        ModelAndView mv = new ModelAndView("jsonView");
        Map<String, String> resUserObject = new HashMap<String, String>();

        if(userVO != null){
            resUserObject.put("useract", userVO.getUseract() != null ? userVO.getUseract() : null);
            resUserObject.put("compkey", userVO.getCompkey() != null ? userVO.getCompkey() : null);
            resUserObject.put("warekey", userVO.getWaredky() != null ? userVO.getWaredky() : null);
            resUserObject.put("ownerky", userVO.getOwnerky() != null ? userVO.getOwnerky() : null);
            resUserObject.put("ptnrkey", userVO.getPtnrkey() != null ? userVO.getPtnrkey() : null);
        }

        // 사용자 접속 프로그램 이력 저장 
        userService.insertUserProgram(params, userVO);

        mv.addObject("gprogrid", params.get("gprogrid"));
        mv.addObject("gprogcmd", params.get("gprogcmd"));
        mv.addObject("gnatitle", params.get("gnatitle"));
        mv.addObject("gprodata", params.get("gprodata"));
        mv.addObject("userattr", resUserObject);

        params.remove("gprogrid");
        params.remove("gprogcmd");
        params.remove("gnatitle");
        params.remove("gprodata");
        if(params.size() > 0) {
            mv.addObject("params", params);
        }

        mv.setViewName("/main/mainPage1SD");

        return mv;
    }

    /* ********************************************
     * 01. 페이지 이동 공통 컨트롤러 Post 방식 - 2023.10.20 미사용
     ******************************************** */
    @ResponseBody
    @PostMapping(value = "/main/singlePagePostCMD.do")
    public ModelAndView menuSinglPagePost(@RequestBody MultiValueMap<String, Object> params, @AuthenticationPrincipal UserVO userInfo, Model model) {
        ModelAndView mv = new ModelAndView("jsonView");
        mv.setViewName("redirect:/main/mainPage1SD");

        Map<String, Object> map = new HashMap<>();
        map.put("gprogrid", params.get("gprogrid").get(0));
        map.put("gnatitle", params.get("gnatitle").get(0));

        // 사용자 접속 프로그램 이력 저장
        userService.insertUserProgram(map, userInfo);

        mv.addObject("gprogrid", params.get("gprogrid").get(0));
        mv.addObject("gprogcmd", params.get("gprogcmd").get(0));
        mv.addObject("gnatitle", params.get("gnatitle").get(0));

        mv.addObject("userRole", userInfo.getUserRoleVOMap().get(params.get("gprogrid").get(0)));

        params.remove("gprogrid");
        params.remove("gprogcmd");
        params.remove("gnatitle");
        if(params.size() > 0) {
            mv.addObject("params", params);
        }

        return mv;
    }

    /* ********************************************
     * 02 OPENTYP = TAB
     * 메뉴클릭시 페이지 이동 공통 컨트롤러 - 2023.10.20 (개발용)
     ******************************************** */
    @GetMapping("/main/tabPageCMD.do")
    public ModelAndView menuTabPage(@RequestParam Map<String, Object> params, @AuthenticationPrincipal UserVO userVO) {
        ModelAndView mv = new ModelAndView("jsonView");
        mv.setViewName((String) params.get("gprogcmd"));

        Map<String, String> resUserObject = new HashMap<String, String>();

        if(userVO != null){
            resUserObject.put("useract", userVO.getUseract() != null ? userVO.getUseract() : null);
            resUserObject.put("compkey", userVO.getCompkey() != null ? userVO.getCompkey() : null);
            resUserObject.put("warekey", userVO.getWaredky() != null ? userVO.getWaredky() : null);
            resUserObject.put("ownerky", userVO.getOwnerky() != null ? userVO.getOwnerky() : null);
            resUserObject.put("ptnrkey", userVO.getPtnrkey() != null ? userVO.getPtnrkey() : null);
        }

        // 사용자 접속 프로그램 이력 저장 
        userService.insertUserProgram(params, userVO);

        mv.addObject("gprogrid", params.get("gprogrid"));
        mv.addObject("gprogcmd", params.get("gprogcmd"));
        mv.addObject("gnatitle", params.get("gnatitle"));
        mv.addObject("gprodata", params.get("gprodata"));
        mv.addObject("userRole", userVO.getUserRoleVOMap().get(params.get("gprogrid")));
        mv.addObject("userattr", resUserObject);

        params.remove("gprogrid");
        params.remove("gprogcmd");
        params.remove("gnatitle");
        params.remove("gprodata");
        if(params.size() > 0) {
            mv.addObject("params", params);
        }

        return mv;
    }


    /* ********************************************
     * 공통 : Modal 페이지 링크
     ******************************************** */
    @GetMapping("/main/modalLoad")
    public ModelAndView modalLoadPage(@RequestParam Map<String, Object> params) {
        ModelAndView mv = new ModelAndView("jsonView");
        mv.setViewName((String) params.get("gprogurl"));
        mv.addObject("params", params);
        return mv;
    }

    /* ********************************************
     * 메뉴
     ******************************************** */
    /*
     * 사용자 메뉴 조회
     * */
    @ResponseBody
    @GetMapping("/main/userMenu.do")
    public String userMenu() throws JsonProcessingException{
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(obj instanceof UserVO) {
            UserVO userVo = (UserVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(userVo.getUserMenuVOList());
        }else {
            return "{}";
        }
    }

    /*
     * 사용자 즐겨찾기 추가/삭제 토글
     *
     */
    @ResponseBody
    @GetMapping("/main/userMenuBookmark.do")
    public ModelAndView userBookmarkToggle(@RequestParam Map<String, Object> params, @AuthenticationPrincipal UserVO userInfo){

        userService.toggleUserBookmarkMenu(params, userInfo);

        ModelAndView mv = new ModelAndView("jsonView");
        mv.addObject("result", "Y");
        return mv;

    }
}
