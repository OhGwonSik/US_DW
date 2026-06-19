package com.logistics.api.nan.scheduler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.logistics.api.nan.service.NanService;
import com.logistics.api.nan.vo.NanResponseDTO;
import com.logistics.api.nan.vo.NanStockDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NanScheduler {

	private final NanService nanService;

	//@Scheduled(cron = "0 0 0/6 * * * ")
	public void getStockInformation() throws IOException {
		NanResponseDTO nanResponse = nanService.getStockInformation();
		List<NanStockDTO> tempStockList = new ArrayList<>();
		for(int i=0; i<nanResponse.getStockLocationDetails().size(); i++) {
			nanResponse.getStockLocationDetails().get(i).setIfstksq(nanResponse.getIfstksq());
			tempStockList.add(nanResponse.getStockLocationDetails().get(i));
			if(i%5000 == 0) {
				nanService.saveNanStockInformation(tempStockList);
				tempStockList.clear();
			}
		}
		nanService.saveNanStockInformation(tempStockList);
	}
}
