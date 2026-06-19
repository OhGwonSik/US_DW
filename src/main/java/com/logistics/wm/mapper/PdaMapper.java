package com.logistics.wm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.md.domain.MskuwcDTO;
import com.logistics.md.domain.MskuwcVO;
import com.logistics.tm.domain.TMDispatchDTO;
import com.logistics.tm.domain.TvhcmaDTO;
import com.logistics.wm.domain.InventoryDTO;
import com.logistics.wm.domain.PdaDTO;
import com.logistics.wm.domain.PickingDTO;
import com.logistics.wm.domain.RecvDTO;
import com.logistics.wm.domain.RecvPutDTO;
import com.logistics.wm.domain.ShipEtcDTO;
import com.logistics.wm.domain.TaskDTO;


@Mapper
public interface PdaMapper {
	//WMP20 PDA 상품조회
	public List<MskuwcVO> getSkuList(MskuwcDTO mskuwcDTO);
	
	//WMP30 PDA 입하등록
	public List<RecvDTO> getWasnifList(PdaDTO pdaDTO);//입하목록조회
	public String selectRcvdcky();//입고문서번호 채번
	public void sp_wm_ctrwrcvit(PdaDTO pdaDTO);//입하등록
	
	//WMP40 PDA 팔레타이징
	public List<RecvDTO>selectWmp40List(PdaDTO pdaDTO);//팔레타이징 리스트 조회
	
	//WMP50 PDA 적치처리
	public PdaDTO selectZoneByLocation(PdaDTO pdaDTO);// 로케이션에 대한 존 검색
	public List<RecvPutDTO> getWrcvitList(PdaDTO pdaDTO);//적치처리할 리스트
	public String getTaskoky();// 적치 작업 번호 채번
	public void saveWmpTolocky(PdaDTO pdaDTO);//적치처리

	public void updateWtakitWmp50List(PdaDTO pdaDTO);//적치처리

	public void updateWtakitWmp50StokkList(PdaDTO pdaDTO);//적치처리



	//WMP60 PDA 피킹처리
	public List<PickingDTO> getWshpitList(PdaDTO pdaDTO); // 피킹처리할 리스트
	public int updateWmp60List(PdaDTO pdaDTO);//작업량 업데이트
	public void sp_wm_ctrstokky(PdaDTO pdaDTO);//피킹처리 프로시저
	
	//WMP70 PDA 이동확정
	public List<TaskDTO> getWtakitList (PdaDTO pdaDTO);//이동지시를 확정할 리스트
	public int updateWmp70List(PdaDTO pdaDTO); // 완료수량 업데이트
	public void updateWmp70ListSp(PdaDTO pdaDTO); // 이동확정 프로시저
	
	//WMP80 PDA 재고조회
	public List<InventoryDTO> getWstkkyList(PdaDTO pdaDTO);
	
	//WMP90
	public List<RecvDTO> selectWasnifList(PdaDTO pdaDTO);
	public List<RecvDTO> getWmp90WasnifList(PdaDTO pdaDTO); //입고 조회
	//WMP90 키오스크 입하등록
	public void sp_wm_ctrwrcvit_wmp90(PdaDTO pdaDTO);
	
	//WMP90 키오스크-입하등록 완료 조회
	public String selectWmp90Eoasnky();//ASN 번호 채번
	public int saveWmp90Ocopur(PdaDTO pdaDTO);//ocopur 발주오더 등록
	public void saveWmp90Wasnif(PdaDTO pdaDTO);//키오스크 수동입고 프로시저
	public List<RecvDTO> selectWrcvitList(PdaDTO pdaDTO);
	
	//WMP91 키오스크-수동입고 부품 데이터
	public List<MskuwcDTO> selectWmp91InitList(PdaDTO pdaDTO);
	//WMP91키오스크-수동입고 화주별 조회
	public List<RecvPutDTO> getWmp91List(PdaDTO pdaDTO);
	
	//WMP92 긴급출고
	public String getCooutky();
	public void pdaSpOmOcosalWms(ShipEtcDTO order);
	
	//TMP05 상차-PDA
	public List<TvhcmaDTO> getTmp05TvhcmaList(PdaDTO pdaDTO); //셔틀노선키에 해당하는 차키리스트 
	public List<TMDispatchDTO> getDispatchPLTList(PdaDTO pdaDTO);//배차된 PLT 조회
	public TMDispatchDTO getTmp05NoneDispatchPlt(PdaDTO pdaDTO);//미배차된 PLT 조회
	public String selectVhplnky();//운송계획번호 채번
	public void sp_tm_loading(PdaDTO pdaDTO); //상차PLAN 프로시저
	public void sp_tm_plan_loading(PdaDTO pdaDTO);//상차NEW 프로시저
	
	//TMP07 상차 취소-PDA
	public List<TMDispatchDTO> getTmp07DispatchPLTList(PdaDTO pdaDTO); // 배차 PLAN, LOADING 조회
	public List<PdaDTO> selectVhplnitList(PdaDTO pdaDTO); //배차삭제할 아이템리스트 조회
	public void spTmPlanCancelTmp07(PdaDTO pdaDTO); // 상차 취소 프로시저
	
	public boolean saveSalesOrder(ShipEtcDTO order);



}
