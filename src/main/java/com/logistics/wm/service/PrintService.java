package com.logistics.wm.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.ProcedureCheckedException;
import com.logistics.om.domain.OinvthVO;
import com.logistics.sy.domain.UserVO;
import com.logistics.wm.mapper.PrintMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrintService {
	private final PrintMapper printMapper;

	public Map<String, Object> getWmpt8PrintList(Map<String, Object> param, UserVO userInfo) {
		param.put("userInfo" , userInfo);
		return printMapper.getWmpt8PrintList(param);
	}

	public List<Map<String, Object>> setWmpt8List(Map<String, Object> param, UserVO userInfo) {
		param.put("userInfo" , userInfo);

		List<Map<String, Object>> list = printMapper.getWmpt8List(param);

		for(var row : list) {
			int insertCnt = printMapper.setWmpt8List(row);

			if(insertCnt == 0) {
				throw new InsertCheckedException();
			}
		}
		return list;
	}

	public String getInvhseq() {
		return printMapper.getInvhseq();
	}

	public String getReceiptInvoiceCallList(Map<String, Object> params , UserVO userInfo){
		ObjectMapper mapper = new ObjectMapper();
	    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

	    Map<String, Object> dataMap = mapper.convertValue(params.get("list"), Map.class);
	    Map<String, Object> sqlMap = new HashMap<>();

	    List<Map<String, Object>> itemList = mapper.convertValue(dataMap.get("itemList"), new TypeReference<List<Map<String, Object>>>(){});
		String invhseq = printMapper.getInvhseq();

		for(int i=0; i<itemList.size(); i++) {
			sqlMap.put("compkey", itemList.get(i).get("compkey"));
			sqlMap.put("invhseq", invhseq);
			sqlMap.put("invokey", itemList.get(i).get("invokey"));
			sqlMap.put("ownerky", itemList.get(i).get("ownerky"));
			sqlMap.put("custkey", itemList.get(i).get("custkey"));
			sqlMap.put("retakey", itemList.get(i).get("retakey"));
			sqlMap.put("vendkey", itemList.get(i).get("vendkey"));
			sqlMap.put("orderno", itemList.get(i).get("orderno"));
			sqlMap.put("allgrky", itemList.get(i).get("allgrky"));
			sqlMap.put("stadate", itemList.get(i).get("stadate"));
			sqlMap.put("enddate", itemList.get(i).get("enddate"));
			sqlMap.put("loguser", userInfo.getUseract());

			printMapper.sp_om_invoice(sqlMap);

	    	if(!sqlMap.get("O_ORESULT").equals(0)) {
	    		throw new ProcedureCheckedException((String) sqlMap.get("O_OMSGKEY"));
	    	}
		}
		return invhseq;
	}

	public String getReceiptPickingListCallList(Map<String, Object> params , UserVO userInfo){
		ObjectMapper mapper = new ObjectMapper();
	    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

	    Map<String, Object> dataMap = mapper.convertValue(params.get("list"), Map.class);
	    Map<String, Object> sqlMap = new HashMap<>();

	    List<Map<String, Object>> itemList = mapper.convertValue(dataMap.get("itemList"), new TypeReference<List<Map<String, Object>>>(){});
		String invhseq = printMapper.getInvhseq();

		for(int i=0; i<itemList.size(); i++) {
			sqlMap.put("compkey", itemList.get(i).get("compkey"));
			sqlMap.put("invhseq", invhseq);
			sqlMap.put("invokey", itemList.get(i).get("invokey"));
			sqlMap.put("allgrky", itemList.get(i).get("allgrky"));
			sqlMap.put("loguser", userInfo.getUseract());

			printMapper.sp_om_invoicePickingList(sqlMap);

	    	if(!sqlMap.get("O_ORESULT").equals(0)) {
	    		throw new ProcedureCheckedException((String) sqlMap.get("O_OMSGKEY"));
	    	}
		}

		return invhseq;
	}

	public List<OinvthVO> getReceiptList(Map<String, Object> params , UserVO userInfo){
		Map<String, Object> sqlMap = new HashMap<>();
		sqlMap.put("compkey", params.get("compkey"));
		sqlMap.put("invhseq", params.get("invhseq"));

		return printMapper.getOinvthList(sqlMap);
	}



	public List<Map<String, Object>> getWmpt9HeaderList(Map<String, Object> param, UserVO userInfo) {
		param.put("compkey" , userInfo.getCompkey());
		return printMapper.getWmpt9HeaderList(param);
	}



	public List<Map<String, Object>> getWmpt9ItemList(Map<String, Object> param, UserVO userInfo) {
		param.put("compkey" , userInfo.getCompkey());
		return printMapper.getWmpt9ItemList(param);
	}



	public List<Map<String, Object>> getWmpt9PrintList(Map<String, Object> param, UserVO userInfo) {
		param.put("compkey" , userInfo.getCompkey());
		if(param.containsKey("cooutkyStr")) {
			String cooutkyStr = (String)param.get("cooutkyStr");
			List<String> cooutkyList = Arrays.asList(cooutkyStr.split(","));
			param.put("cooutkyStr", cooutkyList);
		}

		return printMapper.getWmpt9PrintList(param);
	}
}
