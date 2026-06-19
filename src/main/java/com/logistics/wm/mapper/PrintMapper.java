package com.logistics.wm.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.om.domain.OinvthVO;


@Mapper
public interface PrintMapper {

	List<Map<String, Object>> getWmpt8List(Map<String, Object> param);
	Map<String, Object> getWmpt8PrintList(Map<String, Object> param);

	int setWmpt8List(Map<String, Object> param); 
	
	String getInvhseq();
	void sp_om_invoice(Map<String, Object> param);
	void sp_om_invoiceman(Map<String, Object> sqlMap);
	void sp_om_invoicePickingList(Map<String, Object> param);
	List<OinvthVO> getOinvthList(Map<String, Object> param);
	
	List<Map<String, Object>> getWmpt9HeaderList(Map<String, Object> param);
	List<Map<String, Object>> getWmpt9ItemList(Map<String, Object> param);
	List<Map<String, Object>> getWmpt9PrintList(Map<String, Object> param);

}
