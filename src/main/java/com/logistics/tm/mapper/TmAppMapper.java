package com.logistics.tm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.logistics.bm.domain.BMReportDTO;
import com.logistics.md.domain.MdesmaDTO;
import com.logistics.md.domain.MskuwcDTO;
import com.logistics.om.domain.OcopurVO;
import com.logistics.om.domain.OcosalVO;
// import com.logistics.sy.domain.UserVO;
import com.logistics.tm.domain.TMAppDTO;
// import com.logistics.tm.domain.TplnhdDTO;
// import com.logistics.tm.domain.TplnitDTO;
// import com.logistics.tm.domain.TshrvhDTO;

@Mapper
public interface TmAppMapper {

  // UserVO selectMyUserInfo(UserVO params);

  List<TMAppDTO> selectTplnhdList(TMAppDTO tmAppDTO);

  List<TMAppDTO> selectTplnitList(TMAppDTO tmAppDTO);

  List<TMAppDTO> selectTplnitListByVhplnkyList(TMAppDTO vhplnkyList);

  List<Map<String, Object>> selectOcosalDestkeyGroup(TMAppDTO tmAppDTO);

  List<OcosalVO> selectOcosalForTPLList(TMAppDTO tmAppDTO);

  List<OcopurVO> selectOcopurForTPLList(TMAppDTO tmAppDTO);

  List<MdesmaDTO> selectMdesmaList(TMAppDTO tmAppDTO);

  List<MskuwcDTO> selectSkuwcList(TMAppDTO tmAppDTO);

  int deliveryLoad(TMAppDTO tmAppDTO);

  int deliveryLoad2(TMAppDTO tmAppDTO);

  int deliveryLoad3(TMAppDTO tmAppDTO);

  int deliveryLoad4(TMAppDTO tmAppDTO);

  int deliveryStart(TMAppDTO tmAppDTO);

  // int deliveryDest(TMAppDTO tmAppDTO);
  int getCountTvhent(TMAppDTO tmAppDTO);

  int deliveryArriveRequestNew(TMAppDTO tmAppDTO);

  int deliveryArriveRequest(TMAppDTO tmAppDTO);

  List<TMAppDTO> selectTvhentList(TMAppDTO tmAppDTO);

  void checkEntering(TMAppDTO tmAppDTO);

  int deliveryArrive(TMAppDTO tmAppDTO);

  int deliveryUnload(TMAppDTO tmAppDTO);

  int deliveryUnload2(TMAppDTO tmAppDTO);

  int deliveryUnload3(TMAppDTO tmAppDTO);

  int deliveryUnload4(TMAppDTO tmAppDTO);

  int deliveryCertify(TMAppDTO tmAppDTO);

  int deliveryFinish(TMAppDTO tmAppDTO);

  int deliveryPosition(TMAppDTO tmAppDTO);

  String getNewVhplnky();

  String getNewCopodky();

  String getNewEoasnky();

  void deliveryOutStart(TMAppDTO listOcosalVos);

  int deliveryOutSuspend(List<TMAppDTO> tmAppDTOList);

  List<TMAppDTO> deliveryShuttleVehicleStatusList(TMAppDTO tmAppDTO);

  void sp_tm_inbound_ord(TMAppDTO oneOfListO);

  void sp_tm_inbound(TMAppDTO oneOfListO);

  int hasBtgthdCount(TMAppDTO tmAppDTO);

  TMAppDTO sp_tm_plan_billing(TMAppDTO tmAppDTO);

  String selectBthdckyForMonthlyPayReport(TMAppDTO tmAppDTO);

  // 쓰지 않습니다.
  void deliveryOutStartList(Map<String, Object> listOcosalVos);
}
