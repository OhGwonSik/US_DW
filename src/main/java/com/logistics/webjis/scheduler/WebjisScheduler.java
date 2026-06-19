package com.logistics.webjis.scheduler;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.logistics.webjis.service.WebjisService;
import com.logistics.webjis.vo.WebjisVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebjisScheduler {

	private final WebjisService webjisService;

	/*
	 * saveWebjisData - 웹지스 금일 날짜 수신 스케쥴러
	 * 최초 생성일시 : 2023-12-11 09:00
	 * 최초 생성자 : 한지수
	 * 입력 PARAMETA :
	 * 출력 PARAMETA :
	 * 설명 : 09분 59초 기준 10분 주기로 실행되는 스케쥴러.
	 * 수정 내역
	 * 수정일시 :
	 * 수정자 :
	 * 변경 사항 :
	 */
	//@Scheduled(cron = "59 1/2 * * * * ")
	public void saveWebjisData() throws IOException {
		String webjisDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		log.info("webjisDate => {}", webjisDate);
		List<WebjisVO> webjisList = webjisService.getWebJisList(webjisDate);
		List<WebjisVO> tempWebjisList = new ArrayList<>();
		for (int i = 0; i < webjisList.size(); i++) {
			tempWebjisList.add(webjisList.get(i));
			if (i % 5000 == 0) {
				webjisService.saveWebjisData(tempWebjisList);
				tempWebjisList.clear();
			}
		}
		webjisService.saveWebjisData(tempWebjisList);
	}

	/*
	 * saveEmptyCheckWebjisData - 이종 미확인 데이터를 수신하는 스케쥴러.
	 * 최초 생성일시 : 2023-12-11 09:00
	 * 최초 생성자 : 한지수
	 * 입력 PARAMETA :
	 * 출력 PARAMETA :
	 * 설명 : IFSAL21R(웹지스 인터페이스 수신)의 이종미확인 데이터를 확인하여 30분 간격으로 실행되는 스케쥴러.
	 * 수정 내역
	 * 수정일시 :
	 * 수정자 :
	 * 변경 사항 :
	 */
	//@Scheduled(cron = "0 5/2 6-13 * * * ")
	public void saveEmptyCheckDateWebjisData() throws IOException {
		List<WebjisVO> webjisList = webjisService.getCheckDateIsEmpty();
		List<WebjisVO> tempWebjisList = new ArrayList<>();
		for (int i = 0; i < webjisList.size(); i++) {
			tempWebjisList.add(webjisList.get(i));
			if (i % 5000 == 0) {
				webjisService.updateWebjisData(tempWebjisList);
				tempWebjisList.clear();
			}
		}
		webjisService.updateWebjisData(tempWebjisList);
	}
}
