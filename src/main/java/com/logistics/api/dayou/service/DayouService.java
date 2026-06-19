package com.logistics.api.dayou.service;

import com.logistics.api.dayou.mapper.DayouMapper;
import com.logistics.api.dayou.vo.DayouVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DayouService {

	private final DayouMapper dayouMapper;
	
	public int saveDayou(List<DayouVO> list) {
		if(list == null || list.isEmpty()) {
			return 0;
		}
//		int result = (int)list.stream().peek(dayouMapper::saveDayou).count();
		int result = 0;
		for (DayouVO dayouVO : list) {
			dayouVO.setCompkey("100");
			dayouMapper.saveDayou(dayouVO);
			result++;
		}

		return result;
	}
}
