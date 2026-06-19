package com.logistics.api.dayou.mapper;

import com.logistics.api.dayou.vo.DayouVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DayouMapper {
  int saveDayou(DayouVO vo);
}
