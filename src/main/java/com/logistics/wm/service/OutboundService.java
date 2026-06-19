package com.logistics.wm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.InitDataDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.ProcedureCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.McodemDTO;
import com.logistics.md.domain.MdesmaDTO;
import com.logistics.md.domain.MowrmaDTO;
import com.logistics.md.domain.MptnmaDTO;
import com.logistics.md.domain.MrscmaDTO;
import com.logistics.md.domain.MskuwcDTO;
import com.logistics.md.domain.MwarmaDTO;
import com.logistics.md.mapper.CodeMapper;
import com.logistics.md.mapper.DocumentMapper;
import com.logistics.md.mapper.OrganizationMapper;
import com.logistics.md.mapper.PartnerMapper;
import com.logistics.md.mapper.UnitsMapper;
import com.logistics.wm.domain.AllocChangeDTO;
import com.logistics.wm.domain.PartShipOutDTO;
import com.logistics.wm.domain.PickingDTO;
import com.logistics.wm.domain.ReturnShipOutDTO;
import com.logistics.wm.domain.ShipAllocDTO;
import com.logistics.wm.domain.ShipEtcDTO;
import com.logistics.wm.domain.ShipOutDTO;
import com.logistics.wm.mapper.OutboundMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OutboundService {
	private final CodeMapper codeMapper;
	private final OutboundMapper outboundMapper;
	private final PartnerMapper partnerMapper;
	private final OrganizationMapper organizationMapper;
	private final DocumentMapper documentMapper;
	private final UnitsMapper unitsMapper;
	
	private static final String WM_PICKING_TYPE_SINGLE = "wmd10_1";
	private static final String WM_PICKING_TYPE_TOTAL = "wmd10_2";

	// ====================================
	// ProgramId : WMO10
	// ProgramName : 출고지시등록
	// ====================================

	public InitDataDTO getWmo10InitDataSelect(CommonDTO param) {
		InitDataDTO initDto = new InitDataDTO();
		
		Map<String, Object> returnData = new HashMap<>();
		MwarmaDTO mwarmaDto = new MwarmaDTO();
		mwarmaDto.setUserData(param.getUserData());
		returnData.put("warekeyList", organizationMapper.selectWarehouseUserSelectBox(mwarmaDto));

		MowrmaDTO mowrmaDto = new MowrmaDTO();
		mowrmaDto.setUserData(param.getUserData());
		returnData.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDto));

		McodemDTO mcodemDto = new McodemDTO();
		mcodemDto.setUserData(param.getUserData());
		mcodemDto.setComcdky("PICKTYP");
		returnData.put("picktypList", codeMapper.selectMcodem(mcodemDto));
		
		initDto.setItem(returnData);
		return initDto;
	}

	public ShipAllocDTO getWmo10ListSelect(ShipAllocDTO param) {
		ShipAllocDTO dto = new ShipAllocDTO();
		
		String[] shpdcstArr = ((String) param.getShpdcst()).split(",");

		if (!(shpdcstArr.length == 1 && "".equals(shpdcstArr[0]))) {
			param.setShpdcstList(shpdcstArr);
		}

		dto.setHeadList(outboundMapper.selectWshpitHeadList(param));
		dto.setItemList(outboundMapper.selectWshpitItemList(param));

		return dto;
	}

	public ShipAllocDTO saveWmo10(RequestDTO<ShipAllocDTO> saveData) {
		RequestDTO<ShipAllocDTO> itemList = saveData.getItemGrid();
		RequestDTO<ShipAllocDTO> headList = saveData.getHeadGrid();

		List<ShipAllocDTO> itemUpdateList = itemList.getUpdateList();
		List<ShipAllocDTO> headUpdateList = headList.getUpdateList();
		
		ShipAllocDTO shipAllocDTO = new ShipAllocDTO();
		
		if(itemList != null && headList != null) {
			String allgrky = outboundMapper.getAllgrky();

			itemUpdateList.stream().forEach(t->t.setAllgrky(allgrky));
			// 출고문서번호 아이템 update
			int itemResultCnt = outboundMapper.updateWshpit(itemList);
			if (itemResultCnt == 0) {
				throw new UpdateCheckedException();
			}
			
			// 출고문서번호 헤더 update
			int headResultCount = outboundMapper.updateWshpitHead(headList);
			if (headResultCount == 0) {
				throw new UpdateCheckedException();
			}
			
			for(ShipAllocDTO allocDTO : headUpdateList) {
				
				// wmd10_1 : 오더(order) , wmd10_2 : 총괄(total)
				String picktyp = "SINGLE".equals(allocDTO.getPicktyp()) ? WM_PICKING_TYPE_SINGLE : WM_PICKING_TYPE_TOTAL; 
				allocDTO.setAllgrky(allgrky);
				allocDTO.setTasksts("TASK");
				allocDTO.setType(picktyp);
				allocDTO.setLmouser(allocDTO.getUserData().getUseract());
				allocDTO.setUser(allocDTO.getUserData().getUseract());
			}
			
			shipAllocDTO.setCompkey(headUpdateList.get(0).getCompkey());
			shipAllocDTO.setWarekey(headUpdateList.get(0).getWarekey());
			shipAllocDTO.setType(headUpdateList.get(0).getType());
			shipAllocDTO.setLmouser(headUpdateList.get(0).getUserData().getUseract());
			shipAllocDTO.setAllgrky(allgrky);
			shipAllocDTO.setPrintList(headUpdateList);
			
			outboundMapper.callSpWmAllocateGrp(shipAllocDTO);
			if (shipAllocDTO.getOresult()!= 0) {
				throw new ProcedureCheckedException(shipAllocDTO.getOmsgkey());
			}
		}
		return shipAllocDTO;
	}

	public int saveWmo10Cancel(RequestDTO<ShipAllocDTO> param) {
		int resultCount = 0;
		List<ShipAllocDTO> updateList = param.getUpdateList();
		for(ShipAllocDTO updateDto : updateList) {
			updateDto.setDoccate("500");
			updateDto.setDoctype("535");
			updateDto.setPicktyp("");
			outboundMapper.callSpWmCtrstokkyAllocCancelAtWmo10(updateDto);
			if (updateDto.getOresult()!=0) {
				throw new ProcedureCheckedException(updateDto.getOmsgkey());
			}else {
				resultCount++;
			}
		}
		return resultCount;
	}
	
	public int getWmo10AllgrkyByPrint(ShipAllocDTO param) {
		return outboundMapper.getWmo10AllgrkyByPrint(param);
	}

	// ====================================
	// ProgramId : WMO11
	// ProgramName : 할당변경처리
	// ====================================

	public InitDataDTO getWmo11InitData(CommonDTO param) {
		Map<String, Object> returnData = new HashMap<>();
		InitDataDTO initDataDto = new InitDataDTO();
		MwarmaDTO mwarmaDto = new MwarmaDTO();
		mwarmaDto.setUserData(param.getUserData());
		returnData.put("warekeyList", organizationMapper.selectWarehouseUserSelectBox(mwarmaDto));

		MowrmaDTO mowrmaDto = new MowrmaDTO();
		mowrmaDto.setUserData(param.getUserData());
		returnData.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDto));

		McodemDTO mcodemDto = new McodemDTO();
		mcodemDto.setUserData(param.getUserData());
		mcodemDto.setComcdky("PICKTYP");
		returnData.put("picktypList", codeMapper.selectMcodem(mcodemDto));
		
		initDataDto.setItem(returnData);
		return initDataDto;
	}

	public InitDataDTO getWmo11GridInitData(CommonDTO param) {
		Map<String, Object> returnData = new HashMap<>();
		InitDataDTO initDataDto = new InitDataDTO();
		
		MrscmaDTO dto = new MrscmaDTO();
		dto.setUserData(param.getUserData());
		dto.setDoctype("710");
		dto.setRscate1("NORMAL");
		returnData.put("parsncdList", documentMapper.selectMrscmaSelectBox(dto));
		
		initDataDto.setItem(returnData);
		return initDataDto;
	}

	public List<AllocChangeDTO> getWmo11HeadList(AllocChangeDTO param) {
		return outboundMapper.selectWmo11HeadList(param);
	}

	public List<AllocChangeDTO> getWmo11ItemList(AllocChangeDTO param) {
		return outboundMapper.selectWmo11ItemList(param);
	}

	// 취소 프로시저 호출 -> 재할당 프로시저 호출로 로직 변경
	public int saveWmo11Change(RequestDTO<AllocChangeDTO> param) {
		int resultCount = 0;
		List<AllocChangeDTO> headUpdateList = param.getHeadGrid().getUpdateList();
		List<AllocChangeDTO> itemUpdateList = param.getItemGrid().getUpdateList();
			
		if(headUpdateList != null && itemUpdateList != null) {
			AllocChangeDTO headDto = headUpdateList.get(0);
			headDto.setDoccate("500");
			headDto.setDoctype("537");
			
			outboundMapper.callSpWmAllocateCancel(headDto);
			if (headDto.getOresult() != 0) {
				throw new ProcedureCheckedException((String) headDto.getOmsgkey());
			}

			String allgrky = outboundMapper.getAllgrky();
			// task 생성 (선) -> 재할당 프로시저 호출 (후)
			for (AllocChangeDTO updateRow : itemUpdateList) {
				String taskoky = outboundMapper.getTaskoky();
				updateRow.setNewTaskoky(taskoky);
				updateRow.setAllgrky(allgrky);
				updateRow.setDoccate("500");
				updateRow.setDoctype("530");
	
				int result = outboundMapper.insertWmo11Wtakit(updateRow);
				if (result == 0) {
					new InsertCheckedException();
				}
	
				AllocChangeDTO paramDto = new AllocChangeDTO();
				paramDto.setCompkey(updateRow.getUserData().getCompkey());
				paramDto.setWarekey(updateRow.getWarekey());
				paramDto.setDoccate("500");
				paramDto.setDoctype("538");
				paramDto.setTaskoky(taskoky);
				paramDto.setTaskoit(1);
				paramDto.setLmouser(updateRow.getUserData().getUseract());
				
				outboundMapper.callSpWmAllocateChange(paramDto);
				if (updateRow.getOresult() != 0) {
					throw new ProcedureCheckedException((String) updateRow.getOmsgkey());
				}
				resultCount++;
			}
		}
		return resultCount;
	}

	// ====================================
	// ProgramId : WMO20
	// ProgramName : 피킹처리
	// ====================================

	public InitDataDTO getWmo20Init(CommonDTO param) {
		Map<String, Object> returnData = new HashMap<>();
		InitDataDTO initDataDto = new InitDataDTO();
		
		MwarmaDTO mwarmaDto = new MwarmaDTO();
		mwarmaDto.setUserData(param.getUserData());
		returnData.put("warekeyList", organizationMapper.selectWarehouseUserSelectBox(mwarmaDto));
		
		MowrmaDTO mowrmaDto = new MowrmaDTO();
		mowrmaDto.setUserData(param.getUserData());
		returnData.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDto));

		McodemDTO mcodemDto = new McodemDTO();
		mcodemDto.setUserData(param.getUserData());
		mcodemDto.setComcdky("PICKTYP");
		returnData.put("picktypList", codeMapper.selectMcodem(mcodemDto));
		
		initDataDto.setItem(returnData);
		return initDataDto;
	}

	public PickingDTO getWmo20List(PickingDTO param) {
		PickingDTO pickingDto = new PickingDTO();
		String[] taskstsArr = ((String) param.getTasksts()).split(",");

		if (!(taskstsArr.length == 1 && "".equals(taskstsArr[0]))) {
			param.setTaskstsList(taskstsArr);
		}

		pickingDto.setHeadList(outboundMapper.selectWmo20HeadPickingList(param));
		pickingDto.setItemList(outboundMapper.selectWmo20ItemPickingList(param));

		return pickingDto;
	}

	public int saveWmo20(RequestDTO<PickingDTO> param) {
		int resultCount = outboundMapper.updateWtakit(param);
		if (resultCount == 0) {
			throw new UpdateCheckedException();
		}

		List<PickingDTO> updateList = param.getUpdateList();
		if(updateList != null) {
			for (PickingDTO updateRow : updateList) {
				if (updateRow.getDiffqty() > 0) {
					outboundMapper.callSpWmDiffPicking(updateRow);
					if (updateRow.getOresult() != 0) {
						throw new ProcedureCheckedException(updateRow.getOmsgkey());
					}
				}
			}
		}
		return resultCount;
	}

	public int saveWmo20Pick(RequestDTO<PickingDTO> param) {
		int resultCount = outboundMapper.updateWtakit(param);
		if (resultCount == 0) {
			throw new UpdateCheckedException();
		}

		List<PickingDTO> updateList = param.getUpdateList();
		if(updateList != null) {
			for (PickingDTO updateRow : updateList) {
				updateRow.setDoccate("500");
				updateRow.setDoctype("530");
				if (updateRow.getFordqty() == updateRow.getTcmpqty()) {
					outboundMapper.callSpWmPicking(updateRow);
					if (updateRow.getOresult() != 0) {
						throw new ProcedureCheckedException(updateRow.getOmsgkey());
					}
				}
			}
		}
		return resultCount;
	}

	public int saveWmo20PickCancel(RequestDTO<PickingDTO> param) {
		int resultCount = 0;
		List<PickingDTO> updateList = param.getUpdateList();
		if(updateList != null) {
			for (PickingDTO updateRow : updateList) {
				updateRow.setDoccate("500");
				updateRow.setDoctype("539");
				outboundMapper.callSpWmCtrstokkyPickCancel(updateRow);
				if (updateRow.getOresult() != 0) {
					throw new ProcedureCheckedException(updateRow.getOmsgkey());
				} 
				resultCount++;
			}
		}
		return resultCount;
	}
	// ====================================
	// ProgramId : WMO30
	// ProgramName : 출고확정
	// ====================================

	public InitDataDTO getWmo30Init(CommonDTO param) {
		Map<String, Object> returnData = new HashMap<>();
		InitDataDTO initDTO = new InitDataDTO();
		
		MwarmaDTO mwarmaDTO = new MwarmaDTO();
		mwarmaDTO.setUserData(param.getUserData());
		returnData.put("warekeyList", organizationMapper.selectWarehouseUserSelectBox(mwarmaDTO));
		
		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(param.getUserData());
		returnData.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDTO));

		McodemDTO mcodemDTO = new McodemDTO();
		mcodemDTO.setUserData(param.getUserData());
		mcodemDTO.setComcdky("PICKTYP");
		returnData.put("picktypList", codeMapper.selectMcodem(mcodemDTO));

		initDTO.setItem(returnData);
		return initDTO;
	}

	public List<ShipOutDTO> getWmo30HeadList(ShipOutDTO param) {
		return outboundMapper.getWmo30HeadList(param);
	}

	public List<ShipOutDTO> getWmo30ItemList(ShipOutDTO param) {
		return outboundMapper.getWmo30ItemList(param);
	}

	public int saveWmo30HeadShipout(RequestDTO<ShipOutDTO> param) {
		int resultCnt = 0;
		List<ShipOutDTO> updateList = param.getUpdateList();
		if(updateList != null) {
			for (ShipOutDTO updateDto : updateList) {
				List<ShipOutDTO> targetList = outboundMapper.getWmo30ItemList(updateDto);
				if(targetList != null) {
					for (ShipOutDTO targetArr : targetList) {
						targetArr.setLmouser(updateDto.getUserData().getUseract());
						
						outboundMapper.callSpWmShiping(targetArr);
						if (targetArr.getOresult() != 0) {
							throw new ProcedureCheckedException(targetArr.getOmsgkey());
						}
						resultCnt++;
					}
				}
			}
		}

		return resultCnt;
	}

	public int saveWmo30ItemShipout(RequestDTO<ShipOutDTO> param) {
		int resultCnt = 0;
		List<ShipOutDTO> updateList = param.getUpdateList();
		if(updateList != null) {
			for (ShipOutDTO updateDto : updateList) {
				updateDto.setLmouser(updateDto.getUserData().getUseract());
				outboundMapper.callSpWmShiping(updateDto);
				if (updateDto.getOresult() != 0) {
					throw new ProcedureCheckedException((String) updateDto.getOmsgkey());
				}
				resultCnt++;
			}
		}
		return resultCnt;
	}

	// ====================================
	// ProgramId : WMO31
	// ProgramName : 부분출고 처리
	// ====================================

	public InitDataDTO getWmo31Init(CommonDTO param) {
		Map<String, Object> returnData = new HashMap<>();
		InitDataDTO initDataDto = new InitDataDTO();
		
		MwarmaDTO mwarmaDto = new MwarmaDTO();
		mwarmaDto.setUserData(param.getUserData());
		returnData.put("warekeyList", organizationMapper.selectWarehouseUserSelectBox(mwarmaDto));
		
		MowrmaDTO mowrmaDto = new MowrmaDTO();
		mowrmaDto.setUserData(param.getUserData());
		returnData.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDto));

		McodemDTO dto = new McodemDTO();
		dto.setUserData(param.getUserData());
		dto.setComcdky("PICKTYP");
		returnData.put("picktypList", codeMapper.selectMcodem(dto));
		
		initDataDto.setItem(returnData);
		return initDataDto;
	}

	public List<PartShipOutDTO> getWmo31List(PartShipOutDTO param) {
		return outboundMapper.getWmo31List(param);
	}


	public PartShipOutDTO saveWmo31ResoPartShipout(RequestDTO<PartShipOutDTO> param) {
		List<PartShipOutDTO> updateList = param.getUpdateList();
		PartShipOutDTO returnParam = new PartShipOutDTO();
		if(updateList != null) {
			for (PartShipOutDTO paramDto : updateList) {
				outboundMapper.callSpWmShipingAtWmo31(paramDto);
				if (paramDto.getOresult() != 0) {
					throw new ProcedureCheckedException((String) paramDto.getOmsgkey());
				}
				returnParam.setPrintList(updateList);
			}
		}
		return returnParam;
	}

	// ====================================
	// ProgramId : WMO40
	// ProgramName : 기타출고
	// ====================================

	// 상품 정보 가져오기 위해 창고키를 parameter로 받아야 하므로 CommonDTO를 상속받은 DTO로 변경 ( 2023.09.30 )
	public InitDataDTO getWmo40Init(CommonDTO param) {
		InitDataDTO initDataDTO = new InitDataDTO();
		Map<String, Object> returnData = new HashMap<>();
		MowrmaDTO mowrmaDTO = new MowrmaDTO();
		mowrmaDTO.setUserData(param.getUserData());
		returnData.put("ownerkyList", partnerMapper.selectOwnerSelectBox(mowrmaDTO));

		MrscmaDTO mrscmaDTO = new MrscmaDTO();
		mrscmaDTO.setUserData(param.getUserData());
		mrscmaDTO.setDoctype("790");
		mrscmaDTO.setDoccate("700");
		mrscmaDTO.setRscate1("NORMAL");
		returnData.put("shprscdList", documentMapper.selectMrscmaSelectBox(mrscmaDTO));

		MptnmaDTO martnerDTO = new MptnmaDTO();
		martnerDTO.setPtnrtyp("CUSTOMER");
		martnerDTO.setUserData(param.getUserData());
		returnData.put("custkeyList", partnerMapper.getMdp02SelectBoxByAll(martnerDTO));

		MdesmaDTO mdesmaDTO = new MdesmaDTO();
		mdesmaDTO.setUserData(param.getUserData());
		returnData.put("destkeyList", partnerMapper.getMdp30SelectBoxByAll(mdesmaDTO));

		MskuwcDTO mskuwcdto = new MskuwcDTO();
		mskuwcdto.setUserData(param.getUserData());
		mskuwcdto.setWarekey(param.getWarekey());
		returnData.put("skumkeyList", unitsMapper.selectSkuwcBoxCustom(mskuwcdto));
		
		initDataDTO.setItem(returnData);
		return initDataDTO;
	}

	// 기타출고 ( 오더 역순 생성  OM -> WM )
	public int saveWmo40(RequestDTO<ShipEtcDTO> param) {
 		int resultCnt = 0;
		List<ShipEtcDTO> addList = param.getAddList();
		
		if(addList != null) {
			String cooutky = outboundMapper.getCooutkyByWshipt();
			
			for (ShipEtcDTO etcDTO : addList) {
				etcDTO.setCooutky(cooutky);
				etcDTO.setDoccate("200");
				etcDTO.setDoctype("290");
				etcDTO.setSalstat("ORDER");
				
				// 화면에서 saatc06(팔레트번호) 필드에 대하여 사용자가 미입력 시 , 수주번호 넘김
				if(etcDTO.getSaatc06() == null || "".equals(etcDTO.getSaatc06().trim())) {
					etcDTO.setSaatc06(cooutky);
				}
				
				boolean result = outboundMapper.saveSalesOrder(etcDTO);
				if (result == false) {
					throw new InsertCheckedException();
				}
				resultCnt++;
				
				if(addList.size() == resultCnt) {
					outboundMapper.callOcosalOrder(etcDTO);
					if (etcDTO.getOresult() != 0) {
						throw new ProcedureCheckedException((String) etcDTO.getOmsgkey());
					}
				}
			}
		}
		return resultCnt;
	}

	// ====================================
	// ProgramId : WMO50
	// ProgramName : 반출출고 지시등록
	// ====================================

	public ReturnShipOutDTO getWmo50List(ReturnShipOutDTO param) {
		ReturnShipOutDTO dto = new ReturnShipOutDTO();
		String[] shpdcstArr = ((String) param.getShpdcstArr()).split(",");

		if (!(shpdcstArr.length == 1 && "".equals(shpdcstArr[0]))) {
			param.setShpdcstList(shpdcstArr);
		}
		dto.setHeadList(outboundMapper.getWmo50HeadList(param));
		dto.setItemList(outboundMapper.getWmo50ItemList(param));
		return dto;
	}

	public List<ReturnShipOutDTO> getWmo50HeadList(ReturnShipOutDTO param) {
		return outboundMapper.getWmo50HeadList(param);
	}

	public List<ReturnShipOutDTO> getWmo50ItemList(ReturnShipOutDTO param) {
		return outboundMapper.getWmo50ItemList(param);
	}
	
	
	public ReturnShipOutDTO saveWmo50(RequestDTO<ReturnShipOutDTO> param) {
		RequestDTO<ReturnShipOutDTO> itemList = param.getItemGrid();
		RequestDTO<ReturnShipOutDTO> headList = param.getHeadGrid();
		ReturnShipOutDTO allgrkyRow = new ReturnShipOutDTO();
		
		if(itemList != null && headList != null) {
			int resultCount = 0;
			String allgrky = outboundMapper.getAllgrky();
			
			if(itemList != null && headList != null) {
				List<ReturnShipOutDTO> headUpdateList = headList.getUpdateList();
				List<ReturnShipOutDTO> itemUpdateList = itemList.getUpdateList();
				
				if(headUpdateList != null && itemUpdateList != null) {
					itemUpdateList.stream().forEach(t->t.setAllgrky(allgrky));
	
					// 출고문서번호 아이템 update
					resultCount = outboundMapper.updateWshpitByReturnShipOut(itemList);
					if (resultCount == 0) {
						throw new UpdateCheckedException();
					}
	
					// 출고문서번호 헤더 update			
					resultCount = outboundMapper.updateWshpitHeadByReturnShipOut(headList);
					if (resultCount == 0) {
						throw new UpdateCheckedException();
					}
				
					for(ReturnShipOutDTO returnDTO : headUpdateList) {
						returnDTO.setAllgrky(allgrky);
						returnDTO.setTasksts("TASK");
						returnDTO.setType("pickingOrder");
						returnDTO.setLmouser(returnDTO.getUserData().getUseract());
						returnDTO.setUser(returnDTO.getUserData().getUseract());
					}
					
					allgrkyRow.setCompkey(headUpdateList.get(0).getCompkey());
					allgrkyRow.setWarekey(headUpdateList.get(0).getWarekey());
					allgrkyRow.setType(headUpdateList.get(0).getType());
					allgrkyRow.setProgrid(headUpdateList.get(0).getProgrid());
					allgrkyRow.setLmouser(headUpdateList.get(0).getUserData().getUseract());
					allgrkyRow.setAllgrky(allgrky);
					allgrkyRow.setPrintList(headUpdateList);
					
					outboundMapper.callSpWmAllocateGrpByReturnShipOut(allgrkyRow);
					if (allgrkyRow.getOresult() != 0) {
						throw new ProcedureCheckedException(allgrkyRow.getOmsgkey());
					}
				}
			}
		}
		return allgrkyRow;
	}

	public int saveWmo50Cancel(RequestDTO<ReturnShipOutDTO> param) {
		List<ReturnShipOutDTO> updateList = param.getUpdateList();
		int resultCount = outboundMapper.updateWshpitByReturnShipOut(param);
		if (resultCount == 0) {
			throw new UpdateCheckedException();
		}

		if(updateList != null) {
			for (ReturnShipOutDTO updateDto : updateList) {
				outboundMapper.callSpWmCtrstokkyAllocCancelByReturnShipOut(updateDto);
				if (updateDto.getOresult() != 0) {
					throw new ProcedureCheckedException(updateDto.getOmsgkey());
				}
			}
		}
		return resultCount;
	}

	public int getWmo50AllgrkyByPrint(ReturnShipOutDTO param) {
		return outboundMapper.getWmo50AllgrkyByPrint(param);
	}
	
	// ====================================
	// ProgramId : WMO51
	// ProgramName : 반출출고확정
	// ====================================

	public InitDataDTO getWmo51Init(CommonDTO param) {
		Map<String, Object> returnData = new HashMap<>();
		InitDataDTO initDataDto = new InitDataDTO();
		
		MwarmaDTO mwarmaDto = new MwarmaDTO();
		mwarmaDto.setUserData(param.getUserData());
		returnData.put("warekeyList", organizationMapper.selectWarehouseUserSelectBox(mwarmaDto));

		McodemDTO mcodemDto = new McodemDTO();
		mcodemDto.setUserData(param.getUserData());
		mcodemDto.setComcdky("PICKTYP");
		returnData.put("picktypList", codeMapper.selectMcodem(mcodemDto));
		
		initDataDto.setItem(returnData);
		return initDataDto;
	}

	public List<ReturnShipOutDTO> getWmo51HeadList(ReturnShipOutDTO param) {
		return outboundMapper.getWmo51HeadList(param);
	}

	public List<ReturnShipOutDTO> getWmo51ItemList(ReturnShipOutDTO param) {
		return outboundMapper.getWmo51ItemList(param);
	}

	public int saveWmo51HeadShipout(RequestDTO<ReturnShipOutDTO> param) {
		int resultCount = 0;
		List<ReturnShipOutDTO> updateList = param.getUpdateList();
		if(updateList != null) {
			for (ReturnShipOutDTO updateDto : updateList) {
				List<ReturnShipOutDTO> targetList = outboundMapper.getWmo51ItemList(updateDto);
				if(targetList != null) {
					for (ReturnShipOutDTO targetArr : targetList) {
						targetArr.setLmouser(updateDto.getUserData().getUseract());
						outboundMapper.callSpWmShipingByReturn(targetArr);
						if (targetArr.getOresult() != 0) {
							throw new ProcedureCheckedException(targetArr.getOmsgkey());
						}else {
							resultCount++;
						}
					}
				}
			}
		}
		return resultCount;
	}
	
	public int saveWmo51ItemShipout(RequestDTO<ReturnShipOutDTO> param) {
		int resultCount = 0;
		List<ReturnShipOutDTO> updateList = param.getUpdateList();
		if(updateList != null) {
			for (ReturnShipOutDTO updateDto : updateList) {
				updateDto.setLmouser(updateDto.getUserData().getUseract());
				outboundMapper.callSpWmShipingByReturn(updateDto);
				if (updateDto.getOresult() != 0) {
					throw new ProcedureCheckedException((String) updateDto.getOmsgkey());
				}
				resultCount++;
			}
		}
		return resultCount;
	}


	public List<ShipOutDTO> getWmo60WshpitList(ShipOutDTO param) {
		return outboundMapper.getWmo60WshpitList(param);
	}

	public int saveWmo60ShipoutCancel(RequestDTO<ShipOutDTO> param) {
		int resultCount = 0;
		List<ShipOutDTO> updateList = param.getUpdateList();

		if(updateList != null) {
			for (ShipOutDTO updateDto : updateList) {
				updateDto.setLmouser(updateDto.getUserData().getUseract());
				outboundMapper.callSpWmShipoutCancel(updateDto);
				if (updateDto.getOresult() != 0) {
					throw new ProcedureCheckedException((String) updateDto.getOmsgkey());
				}
				resultCount++;
			}
		}
		return resultCount;
	}
}
