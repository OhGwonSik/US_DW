package com.logistics.common.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestDTO<T extends CommonDTO> { // commonDTO 상속하는 클래스만 가능하도록 함
	/*
	 ****************************************************************************************
	 - 클래스명 : RequestDTO
	 - 최초생성자 : Park T. S.
	 - 최초생성일시 :  2023.07.26
	 - 설명 : Grid Data를 Request시 사용할 클래스
	 -------------------------------------------------------------------------------------------------
 	 - 수정자 : Park T. S.
	 - 수정일시 :  2023.07.30
	 - 내용 : 필드 추가(2-grid 페이지, 3-grid 페이지용)
	 ****************************************************************************************
	 */

	// 1-grid
    private List<T> addList;
    private List<T> updateList;
    private List<T> deleteList;
    private List<T> oldList;

    // 2023.07.30 Park T. S. 추가(2-grid 페이지용)
    private RequestDTO<T> headGrid;
    private RequestDTO<T> itemGrid;

    // 2023.07.30 Park T. S. 추가(3-grid 페이지)
    private RequestDTO<T> subItemGrid;
}

