package com.logistics.wm.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.ProcedureCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.MaremaDTO;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.domain.MdocmaDTO;
import com.logistics.md.domain.MlocmaDTO;
import com.logistics.md.domain.MowrmaDTO;
import com.logistics.md.domain.MpakmaDTO;
import com.logistics.md.domain.MptnmaDTO;
import com.logistics.md.domain.MrscmaDTO;
import com.logistics.md.domain.MskuwcDTO;
import com.logistics.md.domain.MwarmaDTO;
import com.logistics.md.domain.MzonmaDTO;
import com.logistics.md.mapper.CodeMapper;
import com.logistics.md.mapper.DocumentMapper;
import com.logistics.md.mapper.OrganizationMapper;
import com.logistics.md.mapper.PartnerMapper;
import com.logistics.md.mapper.UnitsMapper;
import com.logistics.om.mapper.PurchaseMapper;
import com.logistics.sy.domain.UserVO;
import com.logistics.wm.domain.RecvDTO;
import com.logistics.wm.domain.RecvEtcDTO;
import com.logistics.wm.domain.RecvPutDTO;
import com.logistics.wm.domain.TaskDTO;
import com.logistics.wm.mapper.InboundMapper;
import com.logistics.wm.mapper.TaskMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InboundService {
	
	private final InboundMapper inboundMapper;
	private final OrganizationMapper organizationMapper;
	private final PartnerMapper partnerMapper;
	private final DocumentMapper documentMapper;
	private final CodeMapper codeMapper;
	private final PurchaseMapper purchaseMapper;
	private final TaskMapper taskMapper;
	private final UnitsMapper unitsMapper;

	/*  getWmr20Init - 입하조회 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvDTO - 입하DTO
	*   출력 PARAMETA	: Map<String, Object> resultMap - 호출한 데이터를 담을 Map
	*   설명			: 입하조회 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public Map<String, Object> getWmr20Init(RecvDTO recvDTO) {
		Map<String, Object> resultMap = new HashMap<>();
		UserVO userData = recvDTO.getUserData();

		// 창고 리스트
		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(userData);
		List<CommonDTO> warekeyList = organizationMapper.selectWarehouseUserSelectBox(mwarmaDTO);
		resultMap.put("warekeyList", warekeyList);

		// 화주 리스트
		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(userData);
		resultMap.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDTO));

		// doctype 리스트
		MdocmaDTO mdocmaDTO = new MdocmaDTO();
		mdocmaDTO.setUserData(userData);
		mdocmaDTO.setDoccate("400");
		mdocmaDTO.setDouseyn("Y");
		mdocmaDTO.setWarekey(recvDTO.getWarekey());
		resultMap.put("doctypeList", documentMapper.selectMdocmaDoccateDoctypeRelations(mdocmaDTO));
		resultMap.put("doccateList", documentMapper.selectMdocmaDoccateGroupList(mdocmaDTO));
		resultMap.put("gridDoctypeList", documentMapper.selectMdocmaDoctypeList(mdocmaDTO));

		// 입고문서 상태 리스트
		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setComcdky("RCVDCST");
		mcodemDTO.setUserData(userData);
		resultMap.put("rcvdcstList", codeMapper.selectMcodem(mcodemDTO));
		
		//입고사유코드 리스트
		MrscmaDTO mrscmaDTO = new MrscmaDTO();
		mrscmaDTO.setUserData(userData);
		mrscmaDTO.setWarekey(recvDTO.getWarekey());
		mrscmaDTO.setRsuseyn("Y");
		mrscmaDTO.setDoccate("400");
		resultMap.put("rcvrscdList", documentMapper.selectMrscmaSelectBox(mrscmaDTO));

		mcodemDTO.setComcdky("RCVITST");
		resultMap.put("rcvitstList", codeMapper.selectMcodem(mcodemDTO));
		
		//입고처 리스트
		MptnmaDTO mptnmaDTO = new MptnmaDTO();
		mptnmaDTO.setUserData(userData);
		resultMap.put("vendkeyList", partnerMapper.getMdp02SelectBox(mptnmaDTO));
		
		//location 리스트
		MlocmaDTO mlocmaDTO = new MlocmaDTO();
		mlocmaDTO.setUserData(userData);
		mlocmaDTO.setWarekey(recvDTO.getWarekey());
		resultMap.put("lockyList", organizationMapper.selectMlocmaSelectBox(mlocmaDTO));

		return resultMap;
	}

	/*  getWmr20HeaderList - 입하조회 HeaderList 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvDTO - 입하DTO
	*   출력 PARAMETA	: List<RecvDTO> - 입하DTO 배열
	*   설명			: 입하조회 페이지 상단의 헤더리스트를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public List<RecvDTO> getWmr20HeaderList(RecvDTO param) {
		if (!param.getRcvitsts().isEmpty()) {
			String rcvitst = (String) param.getRcvitst();
			if (rcvitst != null && !"".equals(rcvitst)) {
				List<String> rcvitsts = Arrays.asList(rcvitst.split(","));
				param.setRcvitsts(rcvitsts);
			}
		}
		List<RecvDTO> dataList = inboundMapper.selectWmr20HeaderList(param);
		return dataList;
	}

	/*  getWmr20ItemList - 입하조회 ItemList 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvDTO - 입하DTO
	*   출력 PARAMETA	: List<RecvDTO> - 입하DTO 리스트
	*   설명			: 입하조회 페이지 하단의 아이템리스트를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public List<RecvDTO> getWmr20ItemList(RecvDTO params) {
		List<RecvDTO> dataList = inboundMapper.selectWmr20ItemList(params);
		return dataList;
	}

	/*  getWmr21Init - 입하취소 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvDTO - 입하DTO
	*   출력 PARAMETA	: Map<String, Object> - 호출한 데이터를 담을 Map
	*   설명			: 입하취소 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public Map<String, Object> getWmr21Init(RecvDTO recvDTO){
		Map<String, Object> resultMap = new HashMap<>();
		UserVO userData = recvDTO.getUserData();

		// 창고 리스트
		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(userData);
		List<CommonDTO> warekeyList = organizationMapper.selectWarehouseUserSelectBox(mwarmaDTO);
		resultMap.put("warekeyList", warekeyList);

		// 화주 리스트
		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(userData);
		resultMap.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDTO));

		// doctype 리스트
		MdocmaDTO mdocmaDTO = new MdocmaDTO();
		mdocmaDTO.setUserData(userData);
		mdocmaDTO.setWarekey(recvDTO.getWarekey());
		mdocmaDTO.setDoccate("400");
		mdocmaDTO.setDouseyn("Y");
		resultMap.put("doctypeList", documentMapper.selectMdocmaDoccateDoctypeRelations(mdocmaDTO));
		resultMap.put("gridDoctypeList", documentMapper.selectMdocmaDoctypeList(mdocmaDTO));
		
		//입고문서 상태 리스트
		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setComcdky("RCVDCST");
		mcodemDTO.setUserData(userData);
		resultMap.put("rcvdcstList", codeMapper.selectMcodem(mcodemDTO));
		
		//location 리스트
		MlocmaDTO mlocmaDTO = new MlocmaDTO();
		mlocmaDTO.setUserData(userData);
		mlocmaDTO.setWarekey(recvDTO.getWarekey());
		resultMap.put("lockyList", organizationMapper.selectMlocmaSelectBox(mlocmaDTO));

		return resultMap;
	}
	
	/*  getWmr21RcarscdList - 입하취소 사유코드 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: MrscmaDTO - 사유코드 DTO
	*   출력 PARAMETA	: CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   설명			: 입하취소 페이지의 취소 사유코드 셀렉트박스를 구성하기 위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public List<CommonDTO> getWmr21RcarscdList(MrscmaDTO param){
		return documentMapper.selectWmr21MrscmaSelectBox(param);
	}

	
	/*  getWmr21List - 입하취소 리스트 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvDTO - 입하 DTO
	*   출력 PARAMETA	: List<RecvDTO> - 입하 DTO의 리스트
	*   설명			: 입하취소 페이지에서 적치되지 않은 재고 목록을 보여주는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public List<RecvDTO> getWmr21List(RecvDTO param) {
		List<RecvDTO> dataList = inboundMapper.selectWmr21List(param);
		return dataList;
	}

	/*  saveWmr21List - 입하취소
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<RecvDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int returnData - 성공시 1 반환
	*   설명			: 입하취소 페이지에서 적치되지 않은 재고의 입하를 취소하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public int saveWmr21List(RequestDTO<RecvDTO> param, CommonDTO common) {

		int returnData = 0;

		if(param.getUpdateList() != null) {
			for(RecvDTO recvDTO : param.getUpdateList()) {
				recvDTO.setUserData(common.getUserData());
				//입하취소 procedure -> 입하장에 있는 수량보다 많은 수량 취소시 오류발생
				inboundMapper.saveWmr21List(recvDTO);

				if (!(recvDTO.getOresult() == 0)) {
					throw new ProcedureCheckedException(recvDTO.getOmsgkey());
				} else {
					returnData =0;
				}
			}
		}
		return returnData;
	}

	/*  getWmr22Init - 입하등록 페이지 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvDTO - 입하DTO
	*   출력 PARAMETA	: InitDataDTO - 호출한 데이터를 담을 DTO
	*   설명			: 입하등록 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public Map<String, Object> getWmr22Init(RecvDTO param) {
		Map<String, Object> resultMap = new HashMap<>();
		UserVO userData = param.getUserData();

		// 창고 리스트
		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(userData);
		List<CommonDTO> warekeyList = organizationMapper.selectWarehouseUserSelectBox(mwarmaDTO);
		resultMap.put("warekeyList", warekeyList);

		// 화주 리스트
		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(userData);
		resultMap.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDTO));

		// doctype 리스트
		MdocmaDTO mdocmaDTO = new MdocmaDTO();
		mdocmaDTO.setUserData(userData);
		String doctype = mdocmaDTO.getDoctype();
		if (doctype != null) {
			List<String> doctypes = Arrays.asList(doctype.split(","));
			mdocmaDTO.setDoctypes(doctypes);
		}
		mdocmaDTO.setDoccate("400");
		mdocmaDTO.setDouseyn("Y");
		mdocmaDTO.setWarekey(param.getWarekey());
		resultMap.put("doctypeList", documentMapper.selectMdocmaDoccateDoctypeRelations(mdocmaDTO));
		resultMap.put("gridDoctypeList", documentMapper.selectMdocmaDoctypeList(mdocmaDTO));

		//asn상태 리스트
		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(userData);
		mcodemDTO.setComcdky("ASNSTAT");
		resultMap.put("asnstatList", codeMapper.selectMcodem(mcodemDTO));
		
		//location 리스트
		MlocmaDTO mlocmaDTO = new MlocmaDTO();
		mlocmaDTO.setUserData(userData);
		mlocmaDTO.setWarekey(param.getWarekey());
		resultMap.put("lockyList", organizationMapper.selectMlocmaSelectBox(mlocmaDTO));
		
		//입고처 리스트
		MptnmaDTO mptnmaDTO = new MptnmaDTO();
		mptnmaDTO.setUserData(userData);
		resultMap.put("vendkeyList", partnerMapper.getMdp02SelectBox(mptnmaDTO));
		
		return resultMap;
	}

	/*  getWmr22HeaderList - 입하등록 상단 헤더 리스트 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvDTO - 입하 DTO
	*   출력 PARAMETA	: List<RecvDTO> - 입하 DTO의 리스트
	*   설명			: 입하등록페이지에서 상단 헤더리스트를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public List<RecvDTO> getWmr22HeaderList(RecvDTO param) {
		if (param.getAsnstat() != null) {
			String asnstat = (String) param.getAsnstat();
			if (asnstat != null && !"".equals(asnstat)) {
				List<String> asnstats = Arrays.asList(asnstat.split(","));
				param.setAsnstats(asnstats);
			}
		}
		List<RecvDTO> dataList = inboundMapper.selectWmr22HeaderList(param);
		return dataList;
	}

	/*  getWmr22ItemList - 입하등록 하단 아이템 리스트 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvDTO - 입하 DTO
	*   출력 PARAMETA	: List<RecvDTO> - 입하 DTO의 리스트
	*   설명			: 입하등록페이지에서 하단 아이템리스트를 가져오는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public List<RecvDTO> getWmr22ItemList(RecvDTO param) {
		List<RecvDTO> dataList = inboundMapper.selectWmr22ItemList(param);
		return dataList;
	}

	/*  saveWmr22ItemList - 입하등록
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<RecvDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int returnData - 성공시 1 반환
	*   설명			: 입하등록페이지에서 입하등록을 실행하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public int saveWmr22ItemList(RequestDTO<RecvDTO> param, CommonDTO common) {
		UserVO userData = common.getUserData();
		int returnData = 0;

		if(param.getUpdateList() != null) {
			// 1. 입하 번호 채번
			String rcvdcky = inboundMapper.selectRcvdcky();

			for (RecvDTO recvDTO : param.getUpdateList()) {
				recvDTO.setRcvdcky(rcvdcky);
				
				if(recvDTO.getExpidat() != null) {
					recvDTO.setExpidat(recvDTO.getExpidat().replace("-", ""));
				}
				
				recvDTO.setUserData(userData);
				//같은 ASN에 있는 SKU를 따로 입하처리 -> 입하번호는 나뉘어지지만 같은 재고로 합쳐짐
				inboundMapper.saveCtrwrcvit(recvDTO);

				if (!(recvDTO.getOresult() == 0)) {
					throw new ProcedureCheckedException(recvDTO.getOmsgkey());
				} else {
					returnData = 0;
				}
			}
		}
		return returnData;
	}

	/*  getWmr30Init - 기타입고 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: Map<String, Object> resultMap - 호출한 데이터를 담을 Map
	*   설명			: 기타입고 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public Map<String, Object> getWmr30Init(CommonDTO common) {
		Map<String, Object> resultMap = new HashMap<>();
		UserVO userData = common.getUserData();

		// 창고 리스트
		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(userData);
		resultMap.put("warekeyList", organizationMapper.selectWarehouseUserSelectBox(mwarmaDTO));

		// 화주 리스트
		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(userData);
		resultMap.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDTO));

		MdocmaDTO mdocmaDTO = new MdocmaDTO();
		mdocmaDTO.setUserData(userData);
		//문서유형
		resultMap.put("doccateList", documentMapper.selectMdocmaDoccateGroupList(mdocmaDTO));

		//doctype 리스트
		mdocmaDTO.setDoccate("400");
		resultMap.put("doctypeList", documentMapper.selectMdocmaDoctypeList(mdocmaDTO));

		return resultMap;
	}

	/*  saveWmr30List - 기타입고등록
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<RecvEtcDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int returnData - 성공시 1 반환
	*   설명			: 기타입고등록페이지에서 기타입고를 실행하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public int saveWmr30List(RequestDTO<RecvEtcDTO> param, CommonDTO common) {
		int returnData = 0;
		UserVO userData = common.getUserData();

		if(param.getAddList() != null) {
			int itemNum = 0;
			// 1.ASN 번호 채번
			String eoasnky = inboundMapper.selectEoasnky();
			// 2. 입하 번호 채번
			String rcvdcky = inboundMapper.selectRcvdcky();
			// 3.Ocopur에 들어가는 COPODKY 채번
			String copodky = purchaseMapper.selectCopodky();
			// 4.ASN & 입하 번호 Item별 번호 채번
			for(RecvEtcDTO etcDTO : param.getAddList()) {
				itemNum++;
				etcDTO.setEoasnky(eoasnky);
				etcDTO.setEoasnit(itemNum);
				etcDTO.setRcvdcky(rcvdcky);
				etcDTO.setRcvdcit(itemNum);
				etcDTO.setCopodky(copodky);
				etcDTO.setCopodit(itemNum);
				etcDTO.setPurstat("VNDASN");
				etcDTO.setDoccate("100");
				etcDTO.setDoctype("190");
				etcDTO.setUserData(userData);
				
				returnData = inboundMapper.saveStoreReturnToOcopur(etcDTO);

				if(returnData == 0) {
					throw new InsertCheckedException();
				}
				if(etcDTO.getExpidat() != null){
					etcDTO.setExpidat(etcDTO.getExpidat().replace("-", ""));
				}
				etcDTO.setAsndate(etcDTO.getAsndate().replace("-", ""));
				etcDTO.setDoccate("400");
				etcDTO.setDoctype("490");
				inboundMapper.saveStoreReturnList(etcDTO);

				if (!(etcDTO.getOresult() == 0)) {
					throw new ProcedureCheckedException(etcDTO.getOmsgkey());
				} else {
					returnData += 0;
				}
			}
		}
		return returnData;
	}

	/*  getInboundBoxQty - 기타입고/반품등록의 박스수량 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: MskuwcDTO - 부품DTO
	*   출력 PARAMETA	: List<MskuwcDTO> - 부품DTO 리스트
	*   설명			: 기타입고/반품등록 페이지에서 등록수량 입력시 해당 SKU에 해당하는 packkey와 truntyp으로 박스수량 갱신
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public List<MskuwcDTO> getInboundBoxQty(MskuwcDTO param) {
		List<MskuwcDTO> returnList = inboundMapper.selectInboundBoxQty(param);
		return returnList;
	}

	/*  getWmr40Init - 반품등록 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: Map<String, Object> resultMap - 호출한 데이터를 담을 Map
	*   설명			: 반품등록 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public Map<String, Object> getWmr40Init(CommonDTO common) {
		Map<String, Object> resultMap = new HashMap<>();
		UserVO userData = common.getUserData();

		// 창고 리스트
		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(userData);
		resultMap.put("warekeyList", organizationMapper.selectWarehouseUserSelectBox(mwarmaDTO));

		// 화주 리스트
		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(userData);
		resultMap.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDTO));

		MdocmaDTO mdocmaDTO = new MdocmaDTO();
		mdocmaDTO.setUserData(userData);
		//문서유형
		resultMap.put("doccateList", documentMapper.selectMdocmaDoccateGroupList(mdocmaDTO));

		//doctype 리스트
		mdocmaDTO.setDoccate("400");
		resultMap.put("doctypeList", documentMapper.selectMdocmaDoctypeList(mdocmaDTO));

		return resultMap;
	}
	
	/*  getWmr40List - 회수오더 리스트 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvEtcDTO - 기타입고/반품등록 DTO
	*   출력 PARAMETA	: List<RecvEtcDTO> - 기타입고/반품등록 DTO의 리스트
	*   설명			: 반품등록 화면에서 화주선택시 회수오더리스트 호출
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public List<RecvEtcDTO> getWmr40List(RecvEtcDTO param){
		List<RecvEtcDTO> returnList = inboundMapper.selectWmr40List(param);
		return returnList;
	}

	/*  saveWmr40List - 반품등록
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<RecvEtcDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int returnData - 성공시 1 반환
	*   설명			: 반품등록페이지에서 반품등록을 실행하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public int saveWmr40List(RequestDTO<RecvEtcDTO> param, CommonDTO common) {
		int returnData = 0;
		UserVO userData = common.getUserData();

		if(param.getAddList() != null) {
			int itemNum = 0;
			// 1.ASN 번호 채번
			String eoasnky = inboundMapper.selectEoasnky();
			// 2. 입하 번호 채번
			String rcvdcky = inboundMapper.selectRcvdcky();
			// 3.Ocopur에 들어가는 COPODKY 채번
			String copodky = purchaseMapper.selectCopodky();
			// 4.ASN & 입하 번호 Item별 번호 채번
			for(RecvEtcDTO etcDTO : param.getAddList()) {
				itemNum++;
				etcDTO.setEoasnky(eoasnky);
				etcDTO.setEoasnit(itemNum);
				etcDTO.setRcvdcky(rcvdcky);
				etcDTO.setRcvdcit(itemNum);
				etcDTO.setCopodky(copodky);
				etcDTO.setCopodit(itemNum);
				etcDTO.setPurstat("VNDASN");
				etcDTO.setDoccate("100");
				etcDTO.setDoctype("180");
				etcDTO.setUserData(userData);

				returnData = inboundMapper.saveStoreReturnToOcopur(etcDTO);

				if(returnData == 0) {
					throw new InsertCheckedException();
				}
				
				etcDTO.setAsndate(etcDTO.getAsndate().replace("-", ""));
				
				if(etcDTO.getRetinyn().equals("Y")) {
					returnData = inboundMapper.saveWmr40Tplnit(etcDTO);
					if(returnData == 0) {
						throw new UpdateCheckedException();
					}
				}
					
				
				if(etcDTO.getExpidat() != null) {
					etcDTO.setExpidat(etcDTO.getExpidat().replace("-", ""));
				}
				
				etcDTO.setDoccate("400");
				etcDTO.setDoctype("480");
				//doctype이 480일경우 반입입고처리
				inboundMapper.saveStoreReturnList(etcDTO);

				if (!(etcDTO.getOresult() == 0)) {
					throw new ProcedureCheckedException(etcDTO.getOmsgkey());
				} else {
					returnData += 0;
				}
			}
		}
		return returnData;
	}

	/*  getWmr50Init - 팔렛타이징처리 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvDTO - 입하DTO
	*   출력 PARAMETA	: Map<String, Object> resultMap - 데이터를 담을 Map
	*   설명			: 팔렛타이징처리 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public Map<String, Object> getWmr50Init(RecvDTO recvDTO){
		Map<String, Object> resultMap = new HashMap<>();
		UserVO userData = recvDTO.getUserData();

		// 창고 리스트
		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(userData);
		List<CommonDTO> warekeyList = organizationMapper.selectWarehouseUserSelectBox(mwarmaDTO);
		resultMap.put("warekeyList", warekeyList);

		// 화주 리스트
		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(userData);
		resultMap.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDTO));

		//areakey 리스트
		MaremaDTO maremaDTO = new MaremaDTO();
		maremaDTO.setUserData(userData);
		maremaDTO.setWarekey(recvDTO.getWarekey());
		resultMap.put("areakeyList", organizationMapper.selectMaremaSelectBox(maremaDTO));

		//zonekey 리스트
		MzonmaDTO mzonmaDTO = new MzonmaDTO();
		mzonmaDTO.setUserData(userData);
		mzonmaDTO.setWarekey(recvDTO.getWarekey());
		resultMap.put("zonekeyList", organizationMapper.selectMzonmaSelectBox(mzonmaDTO));

		//locakey 리스트
		MlocmaDTO mlocmaDTO = new MlocmaDTO();
		mlocmaDTO.setUserData(userData);
		mlocmaDTO.setWarekey(recvDTO.getWarekey());
		resultMap.put("locakeyList", organizationMapper.selectMlocmaSelectBox(mlocmaDTO));

		return resultMap;
	}


	/*  getWmr50List - 팔렛타이징처리 리스트 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvDTO - 입하 DTO
	*   출력 PARAMETA	: List<RecvDTO> - 입하 DTO의 리스트
	*   설명			: 팔렛타이징처리 페이지에서 적치되지 않은 재고 목록을 보여주는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public List<RecvDTO> getWmr50List(RecvDTO param){
		//재고에서 로케이션이 rcv인 재고 조회
		return inboundMapper.selectWmr50List(param);
	}

	/*  getWmr50Packqty - 팔렛타이징 입수량 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvDTO - 입하DTO
	*   출력 PARAMETA	: List<RecvDTO> - 입하DTO목록
	*   설명			: 팔렛타이징처리 페이지에서 팔렛타이징 버튼 클릭시 해당 재고의 입수량을 호출하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public List<RecvDTO> getWmr50Packqty(RecvDTO param){
		return inboundMapper.selectWmr50Packqty(param);
	}

	/*  getWmr50Pakblky - 팔렛타이징 id 채번
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: Map<String, String> param
	*   출력 PARAMETA	: Map<String, String> resultMap - 채번한 id를 담을 Map
	*   설명			: 팔렛타이징처리 페이지에서 팔렛타이징 버튼 클릭시 팔렛타이징 id를 채번하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public Map<String, String> getWmr50Pakblky(Map<String, String> param) {
		Map<String, String> resultMap = new HashMap<>();

		resultMap.put("trunpid", inboundMapper.selectPakblky());
		return resultMap;
	}

	/*  saveWmr50 - 팔렛타이징 처리 저장
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<RecvPutDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int returnData - 성공시 1 반환
	*   설명			: 팔렛타이징처리 페이지에서 팔렛타이징 처리된 재고를 저장하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public int saveWmr50(RequestDTO<RecvPutDTO> param, CommonDTO common) {
		UserVO userData = common.getUserData();
		AtomicInteger returnData = new AtomicInteger();
		returnData.set(0);

		if(param.getUpdateList() != null && param.getAddList() != null) {
			// 1. 작업 번호 채번
			String taskoky = inboundMapper.selectTaskoky();
			// 1-1. 상수
			String toareky = "RCV";
			String tozonky = "RCVZ";
			String tolocky = "RCVLOC";
			String doccate = "500";
			String doctype = "510";

			// 2. WTAKIT 작업아이템 테이블 저장\
			for(RecvPutDTO updateData : param.getUpdateList()) {
				updateData.setTaskoky(taskoky);
				updateData.setToareky(toareky);
				updateData.setTozonky(tozonky);
				updateData.setTolocky(tolocky);
				updateData.setDoccate(doccate);
				updateData.setDoctype(doctype);
				updateData.setUserData(userData);
				
				inboundMapper.saveWmrTolocky(updateData);

				if(updateData.getOresult() != 0) {
					throw new ProcedureCheckedException(updateData.getOmsgkey());
				}

				param.getAddList().stream().forEach( t -> {
					if(updateData.getLotnmky().equals(t.getLotnmky())
						&& updateData.getSkumkey().equals(t.getSkumkey())
						&& updateData.getStockky().equals(t.getStockky())
						&& updateData.getRcvdcky().equals(t.getRcvdcky())
						&& updateData.getRcvdcit() == t.getRcvdcit()) {

						t.setTaskoky(taskoky);
						t.setToareky(toareky);
						t.setTozonky(tozonky);
						t.setTolocky(tolocky);
						t.setDoccate(doccate);
						t.setDoctype(doctype);
						t.setUserData(userData);

						inboundMapper.saveWmrTolocky(t);
						if (t.getOresult() != 0) {
							 throw new ProcedureCheckedException(t.getOmsgkey());
						}
						returnData.set(returnData.get()+1);
					}
				});
			}
		}
		return returnData.get();
	}

	/*  saveWmr50Cancel - 팔렛타이징 처리 취소
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<RecvPutDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int returnData - 성공시 0 반환
	*   설명			: 팔렛타이징처리 페이지에서 팔렛타이징 처리된 재고에 대해 팔렛타이징을 취소하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public int saveWmr50Cancel(RequestDTO<RecvPutDTO> param, CommonDTO common) {
		UserVO userData = common.getUserData();
		int returnData = 0;
		if(param.getUpdateList() != null) {
			String taskoky = inboundMapper.selectTaskoky();

			for(RecvPutDTO recvPutDTO : param.getUpdateList()) {
				recvPutDTO.setTaskoky(taskoky);
				recvPutDTO.setDoccate("500");
				recvPutDTO.setDoctype("510");
				recvPutDTO.setToareky("RCV");
				recvPutDTO.setTozonky("RCVZ");
				recvPutDTO.setTolocky("RCVLOC");
				recvPutDTO.setTruntyp(" ");
				recvPutDTO.setTrunpid(" ");
				recvPutDTO.setUserData(userData);
				
				inboundMapper.saveWmrTolocky(recvPutDTO);

				if(recvPutDTO.getOresult() != 0) {
					throw new ProcedureCheckedException(recvPutDTO.getOmsgkey());
				}

			}
		}
		return returnData;
	}


	/*  getWmr60Init - 적치처리 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvPutDTO - 적치DTO
	*   출력 PARAMETA	: Map<String, Object> resultMap - 호출한 데이터를 담을 Map
	*   설명			: 적치처리 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public Map<String, Object> getWmr60Init(RecvPutDTO recvPutDTO) {
		Map<String, Object> resultMap = new HashMap<>();
		UserVO userData = recvPutDTO.getUserData();

		// 창고 리스트
		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(userData);
		List<CommonDTO> warekeyList = organizationMapper.selectWarehouseUserSelectBox(mwarmaDTO);
		resultMap.put("warekeyList", warekeyList);

		// 화주 리스트
		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(userData);
		resultMap.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDTO));

		//doctype 리스트
		MdocmaDTO mdocmaDTO = new MdocmaDTO();

		String doctype = mdocmaDTO.getDoctype();
		if (doctype != null) {
			List<String> doctypes = Arrays.asList(doctype.split(","));
			mdocmaDTO.setDoctypes(doctypes);
		}
		mdocmaDTO.setUserData(userData);
		mdocmaDTO.setDoccate("400");
		mdocmaDTO.setWarekey(recvPutDTO.getWarekey());
		resultMap.put("doctypeList", documentMapper.selectMdocmaDoccateDoctypeRelations(mdocmaDTO));


		//toAreaky 리스트
		MaremaDTO maremaDTO = new MaremaDTO();
		maremaDTO.setUserData(userData);
		maremaDTO.setWarekey(recvPutDTO.getWarekey());
		resultMap.put("toarekyList", organizationMapper.selectMaremaSelectBox(maremaDTO));

		//toZonky 리스트
		MzonmaDTO mzonmaDTO = new MzonmaDTO();
		mzonmaDTO.setUserData(userData);
		mzonmaDTO.setWarekey(recvPutDTO.getWarekey());
		resultMap.put("tozonkyList", organizationMapper.selectMzonmaSelectBox(mzonmaDTO));

		MlocmaDTO mlocmaDTO = new MlocmaDTO();
		mlocmaDTO.setUserData(userData);
		mlocmaDTO.setWarekey(recvPutDTO.getWarekey());
		resultMap.put("frlockyList", organizationMapper.selectMlocmaSelectBox(mlocmaDTO));
		
		// tolocky 리스트
		mlocmaDTO.setAreakey("STG");
		mlocmaDTO.setWarekey(recvPutDTO.getWarekey());
		resultMap.put("tolockyList", inboundMapper.selectWmr60LocationSelectBox(mlocmaDTO));

		// 재고상태 리스트
		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(userData);
		mcodemDTO.setComcdky("STKSTAT");
		resultMap.put("stkstatList", codeMapper.selectMcodem(mcodemDTO));
		
		//이동용기
		MpakmaDTO mpakmaDTO = new MpakmaDTO();
		mpakmaDTO.setUserData(userData);
		mpakmaDTO.setWarekey(recvPutDTO.getWarekey());
		resultMap.put("truntypList", unitsMapper.selectMpakmaSelectBox(mpakmaDTO));

		return resultMap;
	}

	/*  getWmr60List - 적치처리 리스트 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvPutDTO - 적치 DTO
	*   출력 PARAMETA	: List<RecvPutDTO> - 적치 DTO의 리스트
	*   설명			: 적치처리 페이지에서 입하 후 적치되지 않은 재고 목록을 보여주는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public List<RecvPutDTO> getWmr60List(RecvPutDTO param) {
		List<RecvPutDTO> dataList = inboundMapper.selectWmr60List(param);
		return dataList;
	}

	/*  saveWmr60List - 적치처리
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<RecvPutDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int returnData - 성공시 0 반환
	*   설명			: 적치처리 페이지에서 적치되지 않은 재고를 적치하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public int saveWmr60List(RequestDTO<RecvPutDTO> param, CommonDTO common) {
		int returnData = 0;
		UserVO userData = common.getUserData();

		if(param.getUpdateList() != null) {
			// 1. 작업 번호 채번
			String taskoky = inboundMapper.selectTaskoky();
			// 2. WTAKIT 작업아이템 테이블 저장
			for(RecvPutDTO recvPutDTO : param.getUpdateList()) {
				recvPutDTO.setTaskoky(taskoky);
				recvPutDTO.setDoccate("500");
				recvPutDTO.setDoctype("510");
				recvPutDTO.setUserData(userData);

				inboundMapper.saveWmrTolocky(recvPutDTO);

				if (!(recvPutDTO.getOresult() == 0)) {
					throw new ProcedureCheckedException(recvPutDTO.getOmsgkey());
				} else {
					returnData += 0;
				}
			}
		}
		return returnData;
	}


	/*  getWmr61Init - 적치조회 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvPutDTO - 적치DTO
	*   출력 PARAMETA	: Map<String, Object> resultMap - 호출한 데이터를 담기위한 Map
	*   설명			: 적치조회 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public Map<String, Object> getWmr61Init(RecvPutDTO recvPutDTO) {
		Map<String, Object> resultMap = new HashMap<>();
		UserVO userData = recvPutDTO.getUserData();

		// 창고 리스트
		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(userData);
		resultMap.put("warekeyList", organizationMapper.selectWarehouseUserSelectBox(mwarmaDTO));

		// 화주 리스트
		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(userData);
		resultMap.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDTO));

		// 작업상태 리스트
		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(userData);
		mcodemDTO.setComcdky("TASKSTS");
		resultMap.put("taskstsList", codeMapper.selectMcodem(mcodemDTO));
		
		//재고상태 리스트
		mcodemDTO.setComcdky("STKSTAT");
		resultMap.put("stkstatList", codeMapper.selectMcodem(mcodemDTO));
		
		//Areakey 리스트
		MaremaDTO maremaDTO = new MaremaDTO();
		maremaDTO.setUserData(userData);
		maremaDTO.setWarekey(recvPutDTO.getWarekey());
		resultMap.put("areakeyList", organizationMapper.selectMaremaSelectBox(maremaDTO));

		//Zonekey 리스트
		MzonmaDTO mzonmaDTO = new MzonmaDTO();
		mzonmaDTO.setUserData(userData);
		mzonmaDTO.setWarekey(recvPutDTO.getWarekey());
		resultMap.put("zonekeyList", organizationMapper.selectMzonmaSelectBox(mzonmaDTO));

		//Locakey 리스트
		MlocmaDTO mlocmaDTO = new MlocmaDTO();
		mlocmaDTO.setUserData(userData);
		mlocmaDTO.setWarekey(recvPutDTO.getWarekey());
		resultMap.put("locakeyList", organizationMapper.selectMlocmaSelectBox(mlocmaDTO));
		
		//doctype 리스트
		MdocmaDTO mdocmaDTO = new MdocmaDTO();
		mdocmaDTO.setUserData(userData);
		mdocmaDTO.setWarekey(recvPutDTO.getWarekey());
		resultMap.put("gridDoctypeList", documentMapper.selectMdocmaDoctypeList(mdocmaDTO));

		//이동용기
		MpakmaDTO mpakmaDTO = new MpakmaDTO();
		mpakmaDTO.setUserData(userData);
		mpakmaDTO.setWarekey(recvPutDTO.getWarekey());
		resultMap.put("truntypList", unitsMapper.selectMpakmaSelectBox(mpakmaDTO));
		
		return resultMap;
	}

	/*  getWmr61List - 적치조회 리스트 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvPutDTO - 적치 DTO
	*   출력 PARAMETA	: List<RecvPutDTO> - 적치 DTO의 리스트
	*   설명			: 적치조회페이지에서 적치된 재고를 호출하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public List<RecvPutDTO> getWmr61List(RecvPutDTO param) {
		if (!param.getTasksts().isEmpty()) {
			String tasksts = (String) param.getTasksts();
			if (tasksts != null && !"".equals(tasksts)) {
				List<String> taskstss = Arrays.asList(tasksts.split(","));
				param.setTaskstss(taskstss);
			}
		}

		List<RecvPutDTO> dataList = inboundMapper.selectWmr61List(param);
		return dataList;
	}

	/*  saveWmr61List - 적치취소
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<RecvPutDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int returnData - 성공시 0 반환
	*   설명			: 적치조회 페이지에서 적치된 재고의 적치를 취소하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public int saveWmr61List(RequestDTO<RecvPutDTO> param, CommonDTO common) {
		UserVO userData = common.getUserData();
		int returnData = 0;

		if(param.getUpdateList() != null) {
			for(RecvPutDTO recvPutDTO: param.getUpdateList()) {
				recvPutDTO.setUserData(userData);
				inboundMapper.saveTolockyCancel(recvPutDTO);

				if (!(recvPutDTO.getOresult() == 0)) {
					throw new ProcedureCheckedException((String) recvPutDTO.getOmsgkey());
				} else {
					returnData += 0;
				}
			}
		}
		return returnData;
	}

	/*  getWmr62Init - 적치지시 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvPutDTO - 적치DTO
	*   출력 PARAMETA	: Map<String, Object> resultMap - 호출한 데이터를 담을 Map
	*   설명			: 적치지시 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public Map<String, Object> getWmr62Init(RecvPutDTO recvPutDTO) {
		Map<String, Object> resultMap = new HashMap<>();
		UserVO userData = recvPutDTO.getUserData();

		// 창고 리스트
		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(userData);
		List<CommonDTO> warekeyList = organizationMapper.selectWarehouseUserSelectBox(mwarmaDTO);
		resultMap.put("warekeyList", warekeyList);

		// 화주 리스트
		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(userData);
		resultMap.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDTO));

		//doctype 리스트
		MdocmaDTO mdocmaDTO = new MdocmaDTO();
		mdocmaDTO.setUserData(userData);
		mdocmaDTO.setDoccate("400");
		mdocmaDTO.setWarekey(recvPutDTO.getWarekey());
		resultMap.put("doctypeList", documentMapper.selectMdocmaDoccateDoctypeRelations(mdocmaDTO));

		//toAreaky 리스트
		MaremaDTO maremaDTO = new MaremaDTO();
		maremaDTO.setUserData(userData);
		maremaDTO.setWarekey(recvPutDTO.getWarekey());
		resultMap.put("toarekyList", organizationMapper.selectMaremaSelectBox(maremaDTO));

		//toZonky 리스트
		MzonmaDTO mzonmaDTO = new MzonmaDTO();
		mzonmaDTO.setUserData(userData);
		mzonmaDTO.setWarekey(recvPutDTO.getWarekey());
		resultMap.put("tozonkyList", organizationMapper.selectMzonmaSelectBox(mzonmaDTO));

		//frlocky 리스트
		MlocmaDTO mlocmaDTO = new MlocmaDTO();
		mlocmaDTO.setUserData(userData);
		mlocmaDTO.setWarekey(recvPutDTO.getWarekey());
		resultMap.put("frlockyList", organizationMapper.selectMlocmaSelectBox(mlocmaDTO));

		// tolocky 리스트
		mlocmaDTO.setAreakey("STG");
		resultMap.put("tolockyList", organizationMapper.selectMlocmaSelectBox(mlocmaDTO));

		// 재고상태 리스트
		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(userData);
		mcodemDTO.setComcdky("STKSTAT");
		resultMap.put("stkstatList", codeMapper.selectMcodem(mcodemDTO));
		
		//이동용기
		MpakmaDTO mpakmaDTO = new MpakmaDTO();
		mpakmaDTO.setUserData(userData);
		mpakmaDTO.setWarekey(recvPutDTO.getWarekey());
		resultMap.put("truntypList", unitsMapper.selectMpakmaSelectBox(mpakmaDTO));

		return resultMap;
	}


	/*  getWmr62List - 적치지시 리스트 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvPutDTO - 적치 DTO
	*   출력 PARAMETA	: List<RecvPutDTO> - 입하 DTO의 리스트
	*   설명			: 적치지시페이지에서 입하등록되고 적치되지 않은 재고 목록을 보여주는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public List<RecvPutDTO> getWmr62List(RecvPutDTO param) {
		List<RecvPutDTO> dataList = inboundMapper.selectWmr62List(param);
		return dataList;
	}

	/*  saveWmr62List - 적치지시
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<TaskDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: Map<String, String> taskMap - 적치지시 후 적치지시서 출력을 위해 채번된 작업번호를 담을 Map
	*   설명			: 적치지시페이지에서 입하된 후 적치되지 않은 재고의 적치를 지시하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public Map<String, String> saveWmr62List(RequestDTO<TaskDTO> param, CommonDTO common) {
		UserVO userData = common.getUserData();
		Map<String, String> taskMap = new HashMap<>();

		if(param.getUpdateList() != null) {
			// 1. 작업 번호 채번
			String taskoky = inboundMapper.selectTaskoky();
			taskMap.put("taskoky", taskoky);
			// 2. WTAKIT 작업아이템 테이블 저장
			for(TaskDTO taskDTO : param.getUpdateList()) {
				taskDTO.setTaskoky(taskoky);
				taskDTO.setDoccate("500");
				taskDTO.setDoctype("510");
				taskDTO.setUserData(userData);
				int taskoit = taskMapper.selectTaskoit(taskDTO);

				taskDTO.setTaskoit(taskoit);
				taskDTO.setTasksts("NEW");
				
			 	if(taskMapper.saveWmt10List(taskDTO) == 0) {
			 		throw new InsertCheckedException();
			 	}

				taskMapper.updateWtakitList(taskDTO);

				if (taskDTO.getOresult() != 0) {
					throw new ProcedureCheckedException(taskDTO.getOmsgkey());
				}
			}
		}
		return taskMap;
	}

	/*  getWmr63Init - 적치완료 InitData 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvPutDTO - 적치DTO
	*   출력 PARAMETA	: Map<String, Object> resultMap - 호출한 데이터를 담을 Map
	*   설명			: 적치완료 페이지의 셀렉트박스를 구성하기위한 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public Map<String, Object> getWmr63Init(RecvPutDTO recvPutDTO) {
		Map<String, Object> resultMap = new HashMap<>();
		UserVO userData = recvPutDTO.getUserData();

		// 창고 리스트
		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(userData);
		List<CommonDTO> warekeyList = organizationMapper.selectWarehouseUserSelectBox(mwarmaDTO);
		resultMap.put("warekeyList", warekeyList);

		//화주 리스트
		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(userData);
		resultMap.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDTO));

		//작업상태 리스트
		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(userData);
		mcodemDTO.setComcdky("TASKSTS");
		resultMap.put("taskstsList", codeMapper.selectMcodem(mcodemDTO));
		
		//재고상태 리스트
		mcodemDTO.setComcdky("STKSTAT");
		resultMap.put("stkstatList", codeMapper.selectMcodem(mcodemDTO));
		
		//Areakey 리스트
		MaremaDTO maremaDTO = new MaremaDTO();
		maremaDTO.setUserData(userData);
		maremaDTO.setWarekey(recvPutDTO.getWarekey());
		resultMap.put("areakeyList", organizationMapper.selectMaremaSelectBox(maremaDTO));

		//Zonekey 리스트
		MzonmaDTO mzonmaDTO = new MzonmaDTO();
		mzonmaDTO.setUserData(userData);
		mzonmaDTO.setWarekey(recvPutDTO.getWarekey());
		resultMap.put("zonekeyList", organizationMapper.selectMzonmaSelectBox(mzonmaDTO));

		//Locakey 리스트
		MlocmaDTO mlocmaDTO = new MlocmaDTO();
		mlocmaDTO.setUserData(userData);
		mlocmaDTO.setWarekey(recvPutDTO.getWarekey());
		resultMap.put("locakeyList", organizationMapper.selectMlocmaSelectBox(mlocmaDTO));
		
		//doctype 리스트
		MdocmaDTO mdocmaDTO = new MdocmaDTO();
		mdocmaDTO.setUserData(userData);
		mdocmaDTO.setWarekey(recvPutDTO.getWarekey());
		resultMap.put("doctypeList", documentMapper.selectMdocmaDoctypeList(mdocmaDTO));
		resultMap.put("gridDoctypeList", documentMapper.selectMdocmaDoctypeList(mdocmaDTO));
		

		return resultMap;
	}

	/*  getWmr63List - 적치완료 리스트 호출
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RecvPutDTO - 적치 DTO
	*   출력 PARAMETA	: List<RecvPutDTO> - 적치 DTO의 리스트
	*   설명			: 적치완료 페이지에서 적치지시가 내려진 재고 목록을 보여주는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public List<RecvPutDTO> getWmr63List(RecvPutDTO param) {
		if (param.getTasksts() != null) {
			String tasksts = (String) param.getTasksts();
			if (tasksts != null && !"".equals(tasksts)) {
				List<String> taskstss = Arrays.asList(tasksts.split(","));
				param.setTaskstss(taskstss);
			}
		}

		List<RecvPutDTO> dataList = inboundMapper.selectWmr63List(param);
		return dataList;
	}

	/*  saveWmr63CancelList - 적치취소
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<TaskDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int returnData - 성공시 0 반환
	*   설명			: 적치지시가 내려진 재고의 적치를 취소하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public int saveWmr63CancelList(RequestDTO<TaskDTO> param, CommonDTO common) {
		UserVO userData = common.getUserData();
		int returnData = 0;

		if(param.getUpdateList() != null) {
			for(TaskDTO taskDTO: param.getUpdateList()) {
				taskDTO.setUserData(userData);
				taskDTO.setDoctype("519");
				taskMapper.updateWtakitCancelList(taskDTO);

				if (!(taskDTO.getOresult() == 0)) {
					throw new ProcedureCheckedException(taskDTO.getOmsgkey());
				} else {
					returnData += 0;
				}
			}
		}
		return returnData;
	}

	/*  saveWmr63List - 적치완료
	*   최초 생성일시	: 2023-12-12 11:30
	*   최초 생성자	: 최강호 
	*   입력 PARAMETA	: RequestDTO<TaskDTO> - 화면에서 데이터를 받아올DTO, CommonDTO - 데이터 전송시 사용할 일반적인 필드를 모아놓은 DTO
	*   출력 PARAMETA	: int returnData - 성공시 1 반환
	*   설명			: 적치지시가 내려진 재고의 적치를 완료하는 메소드
	*   수정 내역		:
	*   수정일시		:
	*	수정자		:
	*	변경 사항		: 
	*/
	public int saveWmr63List(RequestDTO<TaskDTO> param, CommonDTO common) {
		UserVO userData = common.getUserData();
		int returnData = 0;

		if(param.getUpdateList() != null) {
			for(TaskDTO taskDTO : param.getUpdateList()) {
				taskDTO.setUserData(userData);
				returnData = inboundMapper.updateWmr63List(taskDTO);

				taskMapper.updateWtakitList(taskDTO);

				if(!(taskDTO.getOresult() == 0)) {
					throw new ProcedureCheckedException((String) taskDTO.getOmsgkey());
				} else {
					returnData += 0;
				}
			}
		}
		return returnData;
	}

}
