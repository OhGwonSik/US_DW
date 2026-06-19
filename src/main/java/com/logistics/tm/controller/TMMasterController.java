package com.logistics.tm.controller;

import java.util.Map;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.common.dto.CommonDTO;
import com.logistics.common.dto.RequestDTO;
import com.logistics.md.domain.MdesmaDTO;
import com.logistics.md.domain.MptnmaDTO;
import com.logistics.md.domain.MwarmaDTO;
import com.logistics.md.mapper.OrganizationMapper;
import com.logistics.sy.domain.UserVO;
import com.logistics.tm.service.TMMasterService;
// import com.logistics.tm.domain.TvhcmaDTO;
import com.logistics.tm.domain.TMMasterDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TMMasterController {

  private final TMMasterService tmMasterService;

  private final OrganizationMapper organizationMapper;

  // @GetMapping("/tm/tmm01List")
  // public List<TvhcmaVO> getTmm01List(TvhcmaVO tvhcma) {
  // return tmMasterService.getTmm01List(tvhcma);
  // }

  @GetMapping(value = { "/tm/tmm01List", "/tm/tmm02List" })
  // public List<TvhcmaDTO> getTmm01List(@RequestParam Map<String, Object> param,
  // @AuthenticationPrincipal UserVO userInfo) {
  public List<TMMasterDTO> getTmm01List(TMMasterDTO param) {
    return tmMasterService.getTmm01List(param);
  }

  @PostMapping("/tm/tmm01Save")
  public int saveTmm01Data(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVO userInfo) {
    // return tmMasterService.saveTmm01Data(param, userInfo);
    return tmMasterService.saveTmm01List(param, userInfo);
  }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("/tm/tpvassList")
  public List<TMMasterDTO> getTpvassList(@RequestParam Map<String, Object> param,
      @AuthenticationPrincipal UserVO userInfo) {
    return tmMasterService.getTpvassList(param, userInfo);
  }

  @PostMapping("/tm/tpvassList")
  public int saveTpvassList(@RequestBody Map<String, Object> param, @AuthenticationPrincipal UserVO userInfo) {
    return tmMasterService.saveTpvassList(param, userInfo);
  }

  /*
   * 셔틀 : TSHRHD , TSHRIT , TSHRVH
   * 
   */
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping(value = "/tm/shuttle")
  public List<TMMasterDTO> getShuttleList(@ModelAttribute TMMasterDTO params) {
    // , @AuthenticationPrincipal UserVO userInfo) {
    // params.setUserData(userInfo);
    return tmMasterService.getTshrhdList(params);
  }

  @PostMapping("/tm/shuttle")
  public int saveTshrhdList(@RequestBody RequestDTO<TMMasterDTO> dataObj) {
    return tmMasterService.saveTshrhdList(dataObj);
  }

  @GetMapping("/tm/shuttle/detail")
  public List<TMMasterDTO> getShuttleDetailList(@ModelAttribute TMMasterDTO params) {
    return tmMasterService.getTshritList(params);
  }

  @PostMapping("/tm/shuttle/detail")
  public int saveTshritList(@RequestBody RequestDTO<TMMasterDTO> dataObj) {
    return tmMasterService.saveTshritList(dataObj);
  }

  // @ResponseBody
  @DeleteMapping("/tm/shuttle/detail")
  // @PostMapping("/tm/shuttle/detail/delete")
  public int deleteShuttleDetail(@RequestBody List<TMMasterDTO> deleteList) {
    return tmMasterService.deleteTshritList(deleteList);
  }

  @GetMapping("/tm/shuttle/vehicle")
  public List<TMMasterDTO> getShuttleVehicleList(@ModelAttribute TMMasterDTO params) {
    return tmMasterService.getTshrvhList(params);
  }

  @PostMapping("/tm/shuttle/vehicle")
  public int saveTshrvhList(@RequestBody RequestDTO<TMMasterDTO> dataObj) {
    return tmMasterService.saveTshrvhList(dataObj);
  }

  @DeleteMapping("/tm/shuttle/vehicle")
  public int deleteTshrvhList(@RequestBody List<TMMasterDTO> deleteList) {
    return tmMasterService.deleteTshrvhList(deleteList);
  }

  /*
   * ...
   * 
   */

  // 대신 /md/partner/mdp21/grids/1 으로
  @GetMapping(value = "/tm/code/carrier")
  public List<MptnmaDTO> getMptnmaList(@ModelAttribute MptnmaDTO params) {
    return tmMasterService.getMptnmaList(params);
  }

  @GetMapping(value = "/tm/code/warehouse")
  public List<CommonDTO> getMwarmaList(@ModelAttribute MwarmaDTO params) {
    // public List<MwarmaDTO> getMwarmaList(@RequestParam Map<String, Object> param,
    // @AuthenticationPrincipal UserVO userInfo) {
    // param.put("userData", userInfo);
    // return tmMasterService.getMwarmaList(params);
    // ...
    return organizationMapper.selectWarehouseUserSelectBox(params);
    // return organizationMapper.selectWarehouseUserSelectBox();
  }

  @GetMapping("/tm/code/custkey")
  public List<MptnmaDTO> getCustkeyList(TMMasterDTO param) {
    return tmMasterService.getCustomerList(param);

    // List<Map<String, Object>> res = tmMasterService.getCustomerList(params,
    // userInfo);
    // return res;
  }

  @GetMapping("/tm/code/destkey")
  public List<MdesmaDTO> getDestKeyList(TMMasterDTO tmMasterDTO) {
    return tmMasterService.getDestmaList(tmMasterDTO);
  }
}
