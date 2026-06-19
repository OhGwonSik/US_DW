package com.logistics.tm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.logistics.bm.domain.BMReportDTO;
import com.logistics.md.domain.MdesmaDTO;
import com.logistics.md.domain.MskuwcDTO;
import com.logistics.om.domain.OcopurVO;
import com.logistics.om.domain.OcosalVO;
import com.logistics.sy.domain.UserVO;
// import com.logistics.sy.service.UserService;
import com.logistics.tm.domain.TMAppDTO;
import com.logistics.tm.service.TMAppService;
import com.logistics.util.file.FileResponseVO;
// import com.logistics.util.file.FileUploadUtil;

import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TmAppController {
  // private final UserService userService;
  private final TMAppService tmAppService;
  // private final TransportOrderMapper transportOrderMapper;
  // private final TMOrderService transportOrderService;
  // private final FileUploadService fileUploadService;
  // private final FileUploadUtil fileUploadUtil;

  // private final FileUpload fileUpload;

  // @GetMapping("/ping")
  // public int getServerPing() {
  // return 1;
  // }

  // @ResponseBody
  // @PostMapping("/myIdData")
  // public UserVO getAppUserData(@RequestParam Map<String, Object> param,
  // @AuthenticationPrincipal UserVO userInfo) {
  // // if (userInfo == null) {
  // // // return new UserVO();
  // // }

  // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  // String useract = userInfo.getUseract();
  // // if (useract ==null ) {
  // // // return new UserVO();
  // // }
  // return userService.getUserInfo(useract);
  // }
  @ResponseBody
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/driver/myiddata")
  public Map<String, Object> getAppUserData(@RequestParam Map<String, Object> param,
      @AuthenticationPrincipal UserVO userInfo) {

    if (userInfo == null)
      return null;

    Map<String, Object> responseData = new HashMap<>();
    responseData.put("username", userInfo.getUsernam());
    // responseData.put("telephone", userInfo.getTelphnm());
    responseData.put("usertype", userInfo.getUsertyp());

    return responseData;
  }

  // Map<String, Object>
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/delivery-order")
  public List<TMAppDTO> getOrderList(TMAppDTO tmAppDTO) {
    return tmAppService.getOrderList(tmAppDTO);
  }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/delivery-order/detail")
  public List<TMAppDTO> getOrderDetailList(TMAppDTO tmAppDTO) {
    return tmAppService.getOrderDetailList(tmAppDTO);
  }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/delivery-order/detail-list")
  public List<TMAppDTO> getOrderDetailListByList(TMAppDTO tmAppDTO) {
    // vhplnkyList = new ArrayList<>();
    // String[] vhplnkyList = tplnitDTO.getVhplnky_list().split(",");
    // tplnitDTO.setVhplnky_list(vhplnkyList);
    return tmAppService.getOrderDetailListByVhplnkyList(tmAppDTO);
  }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/delivery/load")
  public int postDeliveryLoad(@RequestBody TMAppDTO tmAppDTO) {
    return tmAppService.deliveryLoad(tmAppDTO);
  }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/delivery/start")
  public int postDeliveryStart(@RequestBody TMAppDTO tmAppDTO) {
    return tmAppService.deliveryStart(tmAppDTO);
  }

  // @CrossOrigin(origins="*", allowedHeaders="*")
  // @PostMapping("/delivery/dest")
  // public int postDeliveryDest(@RequestBody TMAppDTO tmAppDTO) {
  // return tmAppService.deliveryDest(tmAppDTO);
  // }
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/delivery/request-for-arrive")
  public int postDeliveryArriveRequest(@RequestBody TMAppDTO tmAppDTO) {
    return tmAppService.deliveryArriveRequest(tmAppDTO);
  }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("/delivery/request-for-arrive")
  public List<TMAppDTO> getDeliveryArriveRequest(TMAppDTO tmAppDTO, @AuthenticationPrincipal UserVO userInfo) {
    return tmAppService.getDeliveryArriveRequest(tmAppDTO, userInfo);
  }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/delivery/check-enter")
  public void checkEntering(@RequestBody TMAppDTO tmAppDTO) {
    tmAppService.checkEntering(tmAppDTO);
  }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/delivery/arrive")
  public int postDeliveryArrive(@RequestBody TMAppDTO tmAppDTO) {
    return tmAppService.deliveryArrive(tmAppDTO);
  }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/delivery/unload")
  public int postDeliveryUnload(@RequestBody TMAppDTO tmAppDTO) {
    return tmAppService.deliveryUnload(tmAppDTO);
  }

  // file 업로드
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/delivery/fileupload")
  @ResponseBody
  public FileResponseVO fileupload(MultipartHttpServletRequest multiRequest)
      throws IOException {

    FileResponseVO res = new FileResponseVO();
    try {
      // String filename = multiRequest.getMultipartContentType("filename");
      // String filename = multiRequest.getParameter("filename");
      // multiRequest.getfi
      // String filepath = multiRequest.getParameter("filepath");
      // MultipartFile file = multiRequest.getFile(null);
      res = tmAppService.uploadFile(multiRequest);
    } catch (java.io.IOException e) {
      e.printStackTrace();
    }
    return res;
  }

  // after file upload
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/delivery/certify")
  public int postDeliveryCertify(@RequestBody TMAppDTO tmAppDTO)
      throws IOException {
    // MultipartHttpServletRequest multiRequest,
    // @AuthenticationPrincipal UserVO userInfo)
    // int res = 0;
    // try {
    // res = ;
    // return tmAppService.deliveryCertify(tmAppDTO);
    // } catch (Exception e) {

    // }
    // int res = 0;
    // try {
    // res = tmAppService.deliveryCertify(tmAppDTO);
    // } catch (java.io.IOException e) {
    // e.printStackTrace();
    // }
    // return res;
    return tmAppService.deliveryCertify(tmAppDTO);
  }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/delivery/finish")
  public int postDeliveryFinish(@RequestBody TMAppDTO tmAppDTO) {
    return tmAppService.deliveryFinish(tmAppDTO);
  }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/delivery/position")
  public int postDeliveryPosition(@RequestBody TMAppDTO tmAppDTO) {
    return tmAppService.deliveryPosition(tmAppDTO);
  }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/delivery/ocosal")
  public List<OcosalVO> selectOcosalForTPLList(@RequestBody TMAppDTO tmAppDTO) {
    // public List<OcosalVO> selectOcosalForTPLList() {
    return tmAppService.selectOcosalForTPLList(tmAppDTO);
  }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/delivery/ocosal/group")
  public List<Map<String, Object>> selectOcosalDestkeyGroup(
      @RequestBody TMAppDTO tmAppDTO) {
    return tmAppService.selectOcosalDestkeyGroup(tmAppDTO);
  }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/delivery/dest-list-for-order-in")
  public List<MdesmaDTO> selectDestListForOrderIn(@RequestBody TMAppDTO tmAppDTO) {
    return tmAppService.selectMdesmaList(tmAppDTO);
  }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/delivery/skuwc-item-list")
  public List<MskuwcDTO> selectPreOcoItemList(@RequestBody TMAppDTO tmAppDTO) {
    return tmAppService.selectSkuwcList(tmAppDTO);
  }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/delivery/ocopur")
  public List<OcopurVO> selectOcopurForTPLList(@RequestBody TMAppDTO tmAppDTO) {
    return tmAppService.selectOcopurForTPLList(tmAppDTO);
  }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/delivery/in/start")
  public Map<String, String> deliveryInStart(@RequestBody List<TMAppDTO> orderList,
      @AuthenticationPrincipal UserVO userInfo) {
    return tmAppService.deliveryInStart(orderList, userInfo);
  }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/delivery/out/start")
  public String deliveryOutStart(@RequestBody List<TMAppDTO> listOcosalVos, @AuthenticationPrincipal UserVO userInfo) {
    return tmAppService.deliveryOutStart(listOcosalVos, userInfo);
  }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/delivery/out/suspend")
  public int deliveryOutSuspend(@RequestBody List<TMAppDTO> tmAppDTOs,
      @AuthenticationPrincipal UserVO userInfo) {
    return tmAppService.deliveryOutSuspend(tmAppDTOs);
  }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/delivery/shuttle/status")
  public List<TMAppDTO> deliveryShuttleVehicleStatusList(@RequestBody TMAppDTO tmAppDTO,
      @AuthenticationPrincipal UserVO userInfo) {
    return tmAppService.deliveryShuttleVehicleStatusList(tmAppDTO);
  }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @PostMapping("/delivery/recertify-history")
  public String deliveryRecertifyHistory(@RequestBody TMAppDTO tmAppDTO) throws Exception {
    return tmAppService.deliveryRecertifyHistory(tmAppDTO);
  }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("/delivery/monthly-pay-report")
  public Map<String, Object> deliveryMonthlyPayReport(TMAppDTO tmAppDTO) {
    return tmAppService.deliveryMonthlyPayReport(tmAppDTO);
  }

  // @PostMapping("/delivery-order")
  // public int setOrderDeliveryFromApp(TplnitDTO tplnitDTO) {
  // // return tmAppService.updateTplnit(tplnitDTO);
  // return transportOrderService.insertTma06TplnitItem(tplnitDTO);
  // }

  // @PostMapping("/delivery-order/status")
  // public int setOrderDeliveryStatusFromApp(TplnhdDTO tplnhdDTO) {
  // return transportOrderMapper.updateTma06Tplnhd(tplnhdDTO);
  // }
  // @PostMapping("/delivery-order/status")
  // public int setOrderDeliveryStatusFromApp(TplnhdDTO tplnhdDTO) {
  // return transportOrderService.updateTma06Tplnhd(tplnhdDTO);
  // }
  // @PostMapping("/delivery-order/status")
  // public int setOrderDeliveryStatusFromApp(TplnhdDTO tplnhdDTO) {
  // return transportOrderMapper.updateTma06Tplnhd(tplnhdDTO);
  // }

  // file upload 는 FileUpdload.java 그대로 씀
  // @
}
