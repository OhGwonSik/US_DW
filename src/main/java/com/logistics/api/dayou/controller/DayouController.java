package com.logistics.api.dayou.controller;

import com.logistics.api.dayou.service.DayouService;
import com.logistics.api.dayou.vo.DayouVO;
import java.io.Serializable;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/dayou")
public class DayouController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private final DayouService dayouService;

	@GetMapping("/test")
	public String test(){
		return "test!";
	}

	@PostMapping("/dayou")
	public String saveDayou(@RequestBody List<DayouVO> list, HttpServletResponse res){
		int result = dayouService.saveDayou(list);
		if(result == 0){
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			//TODO Message 공통화 필요
			return "[Fail]잘못된 데이터 포맷입니다.";
		}
		//TODO Message 공통화 필요
		return "[Success]성공-" + result + "건 저장 되었습니다.";
	}
}
