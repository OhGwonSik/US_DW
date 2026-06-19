package com.logistics.tm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.logistics.sy.domain.UserVO;
import com.logistics.tm.service.ShuttleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ShuttleController {
	
	private final ShuttleService shuttleService;
	
	@GetMapping("/tm/tmp1SelectBox.do")
	public ModelAndView getTmp1ListSelectBoxByCar(@RequestParam Map<String , Object> param , @AuthenticationPrincipal UserVO userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
		Map<String ,Object> selectBoxParam = new HashMap<>();
		selectBoxParam.put("doccate", "800");
		//mv.addObject("rsncode", mdc6Service.getMrscmaList(selectBoxParam, userInfo));
		return mv;
	}
	
	@PostMapping("/tm/tmp1Save.do")
	public ModelAndView getTmp1Save(@RequestBody Map<String , Object> param , @AuthenticationPrincipal UserVO userInfo) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		Map<String ,Object> returnParam = new HashMap<>();
		param.put("doccate", "800");
		
		shuttleService.getTmp1Save(param,userInfo);
		returnParam.put("item1", shuttleService.getTmp1HeaderList(param , userInfo));
		returnParam.put("item2", shuttleService.getTmp1ItemList(param , userInfo));
		mv.addObject("returnList", returnParam);
		
		return mv;
	}
	
	@GetMapping("/tm/tmp1CmpCount.do")
	public ModelAndView getCmpCount(@RequestParam Map<String, Object> param , @AuthenticationPrincipal UserVO userInfo) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("cnt", shuttleService.tmpCmpCount(param , userInfo));
		return mv;
	}
	
	@PostMapping("/tm/tm1ShipSuccess.do")
	public ModelAndView getTmp1ShiSuccess(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVO userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("item" , shuttleService.tmp1ShiSuccess(param,userInfo));
		return mv;
	}
	
	@PostMapping("/tm/updateShiptransplan.do")
	public ModelAndView updateShipTransPlan(@RequestBody Map<String, Object> param , @AuthenticationPrincipal UserVO userInfo) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		Map<String, Object> returnParam = new HashMap<>();
		param.put("doccate", "800");
		
		shuttleService.updateShipTransPlan(param,userInfo);
		returnParam.put("item1", shuttleService.getTmp1HeaderList(param , userInfo));
		returnParam.put("item2", shuttleService.getTmp1ItemList(param , userInfo));
		mv.addObject("returnList",returnParam);
		
		return mv;
	}
	
	@GetMapping("/tm/getTransSts.do")
	public ModelAndView getTransSts(@RequestParam Map<String, Object> param , @AuthenticationPrincipal UserVO userInfo ,Model model) {
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("item", shuttleService.getTransSts(param , userInfo));
		return mv;
	}
	
	@GetMapping("/tm/tmp2SelectBox.do")
	public ModelAndView getTmp2SelectBoxList(@RequestParam Map<String , Object> param , @AuthenticationPrincipal UserVO userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
		return mv;
	}
}
