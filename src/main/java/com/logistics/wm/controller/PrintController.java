package com.logistics.wm.controller;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.logistics.sy.domain.UserVO;
import com.logistics.wm.service.PrintService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PrintController {
	
	private final PrintService printService;

	@PostMapping(value = "/wm/wmpt6InvoiceCall.do")
    @ResponseBody
	public ModelAndView getWmpt6InvoiceCall(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVO userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("compkey", userInfo.getCompkey());
		mv.addObject("invhseq", printService.getReceiptInvoiceCallList(param, userInfo));
		return mv;
	}
	
	@PostMapping(value = "/wm/wmpt6PickingListCall.do")
    @ResponseBody
	public ModelAndView getWmpt6PickingListCall(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVO userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("compkey", userInfo.getCompkey());
		mv.addObject("invhseq", printService.getReceiptPickingListCallList(param, userInfo));
		return mv;
	}
	
	//WMPT6 리스트 조회
    @ResponseBody
    @GetMapping(value = "/wm/wmpt6List.do")    
	public ModelAndView getWmpt6ListSelect(@RequestParam Map<String, Object> param, @AuthenticationPrincipal UserVO userInfo) {
    	
    	ModelAndView mv = new ModelAndView("jsonView");

    	mv.addObject("list", printService.getReceiptList(param, userInfo));
    	
    	//List<WshpitVO> itemList 	= outboundService.getWmpt6ListSelect(param, userInfo);
		//mv.addObject("item", itemList);
    	mv.addObject("useract", userInfo.getUseract());
    	mv.addObject("code", "200");
    	
        return mv;
    }
	
	
	@GetMapping("/wm/wmpt8PrintList.do")
	public ModelAndView getWmpt8PrintList(@RequestParam Map<String, Object> param , @AuthenticationPrincipal UserVO userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("list", printService.getWmpt8PrintList(param, userInfo));
		return mv;
	}
	
	@PostMapping("/wm/wmpt8SaveList.do")
	public ModelAndView wmpt8SaveList(@RequestBody Map<String, Object> param , @AuthenticationPrincipal UserVO userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("list", printService.setWmpt8List(param, userInfo));
		return mv;
	}
	
	@GetMapping("/wm/wmpt9HeaderList.do")
	public ModelAndView wmpt9List(@RequestParam Map<String, Object> param , @AuthenticationPrincipal UserVO userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("list", printService.getWmpt9HeaderList(param, userInfo));
		return mv;
	}
	
	
	@GetMapping("/wm/wmpt9ItemList.do")
	public ModelAndView wmpt9ItemList(@RequestParam Map<String, Object> param , @AuthenticationPrincipal UserVO userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("list", printService.getWmpt9ItemList(param, userInfo));
		return mv;
	}
	
	@GetMapping("/wm/wmpt9PrintList.do")
	public ModelAndView wmpt9PrintList(@RequestParam Map<String, Object> param , @AuthenticationPrincipal UserVO userInfo){
		ModelAndView mv = new ModelAndView("jsonView");
		mv.addObject("list", printService.getWmpt9PrintList(param, userInfo));
		return mv;
	}
}
