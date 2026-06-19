package com.logistics.md.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.MskustDTO;
import com.logistics.md.mapper.UnitsMapper;

import lombok.RequiredArgsConstructor;

/*
*   클래스명 : MDU10Service
*   최초생성일시	: 2023.08.11
*   최초생성자 : Park T. S.
*   설명 : 세트구성 서비스 클래스
*/

@Service
@RequiredArgsConstructor
public class MDU10Service {
	private final UnitsMapper unitsMapper;

	// 세트 부품 조회
	public List<MskustDTO> getMdu10SetList(MskustDTO params){
		return unitsMapper.selectMskuwcSetList(params);
	}

	// 세트 구성품 조회
	public List<MskustDTO> getMdu10ItemList(MskustDTO params){
		return unitsMapper.selectMskustSetItemList(params);
	}

	// mdu10 데이터 저장
	public int saveMdu10(RequestDTO<MskustDTO> saveData) {
		RequestDTO<MskustDTO> headSaveList = saveData.getHeadGrid();
		RequestDTO<MskustDTO> itemSaveList = saveData.getItemGrid();

		boolean isNullHeadSaveList = headSaveList == null;
		boolean isNullItemSaveList = itemSaveList == null;
		boolean isNullHeadUpdateList = isNullHeadSaveList || ((!isNullHeadSaveList && headSaveList.getUpdateList() == null) && (!isNullHeadSaveList && headSaveList.getOldList() == null));
		boolean isNullItemUpdateList = isNullItemSaveList || ((!isNullItemSaveList && itemSaveList.getUpdateList() == null) && (!isNullItemSaveList && itemSaveList.getOldList() == null));
		AtomicInteger setInsertCnt = new AtomicInteger(0);
		AtomicInteger setItemInsertCnt = new AtomicInteger(0);
		int setUpdateCnt = 0;
		int setItemUpdateCnt = 0;

		// Head Grid insert
		if(!isNullHeadSaveList && headSaveList.getAddList() != null) {
			headSaveList.getAddList().stream()
											     .forEach(t->{
												   this.checkAndThorwInsertException(unitsMapper.insertMskuwcSet(t));
												   setInsertCnt.set(setInsertCnt.get()+1);
											     });
        }

		// Item Grid insert
		if(!isNullItemSaveList && itemSaveList.getAddList() != null) {
			itemSaveList.getAddList().stream()
											    .forEach(t->{
												  this.checkAndThorwInsertException(unitsMapper.insertMskustSetItem(t));
												  setItemInsertCnt.set(setInsertCnt.get()+1);
											    });
        }

		// HeadGrid update
		if(!isNullHeadUpdateList) {
    		for(int i=0; i<headSaveList.getUpdateList().size(); i++) {
    			String oldSkumkey = headSaveList.getOldList().get(i).getSkumkey();

    			headSaveList.getUpdateList().get(i).setOldSkumkey(oldSkumkey); // 넣고

    			String newSkumkey = headSaveList.getUpdateList().get(i).getSkumkey(); // 가져옴

    			headSaveList.getUpdateList().get(i).setOldSkudesc(headSaveList.getOldList().get(i).getSkudesc());
    			this.checkAndThorwUpdateException(unitsMapper.updateMskuwcSet(headSaveList.getUpdateList().get(i)));

    			if(!isNullItemSaveList && newSkumkey != null && oldSkumkey != null) {
    				boolean isExistItemUpdateList = itemSaveList.getOldList().stream().anyMatch(t ->oldSkumkey.equals(t.getSkumkey()));

    				if(!isNullItemUpdateList && isExistItemUpdateList) {
    					itemSaveList.getUpdateList().stream()
															     .forEach(t -> {
										    						if(oldSkumkey.equals(t.getSkumkey())) {
										    							t.setOldSkumkey(oldSkumkey);
										    							t.setSkumkey(newSkumkey);
										    						}
															     });
    				} else {
    					unitsMapper.updateMskustSetItemSkumkey(headSaveList.getUpdateList().get(i));
    				}
    			}

    			setUpdateCnt++;
    		}
        }

		// Item Grid update
		if(!isNullItemUpdateList) {
    		for(int i=0; i<itemSaveList.getUpdateList().size(); i++) {
    			itemSaveList.getUpdateList().get(i).setOldSkumkey(itemSaveList.getOldList().get(i).getSkumkey());
    			itemSaveList.getUpdateList().get(i).setOldSbskuky(itemSaveList.getOldList().get(i).getSbskuky());
    			this.checkAndThorwUpdateException(unitsMapper.updateMskustSetItem(itemSaveList.getUpdateList().get(i)));
    			setItemUpdateCnt++;
    		}
        }

		return setInsertCnt.get() + setUpdateCnt + setItemInsertCnt.get() + setItemUpdateCnt;
	}

	// insert 체크 함수
	public void checkAndThorwInsertException(int cnt) {
		if(cnt == 0) {
    		throw new InsertCheckedException();
		}
	}

	// update 체크 함수
	public void checkAndThorwUpdateException(int cnt) {
		if(cnt == 0) {
    		throw new UpdateCheckedException();
		}
	}
}
