package com.logistics.tm.service;

import java.io.File;
// import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
// import java.util.ArrayList;
// import java.util.Iterator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
// import org.springframework.web.multipart.MultipartFile;
// import org.springframework.web.multipart.MultipartHttpServletRequest;
// import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.logistics.bm.domain.BMReportDTO;
import com.logistics.bm.domain.BillingDTO;
import com.logistics.bm.mapper.BMReportMapper;
import com.logistics.bm.mapper.BillingMapper;
import com.logistics.configuration.error.ProcedureCheckedException;
import com.logistics.md.domain.MdesmaDTO;
import com.logistics.md.domain.MskuwcDTO;
import com.logistics.om.domain.OcopurVO;
import com.logistics.om.domain.OcosalVO;
import com.logistics.sy.domain.UserVO;
// import com.logistics.md.mapper.UnitsMapper;
// import com.logistics.md.mapper.CodeMapper;
// import com.logistics.md.mapper.OrganizationMapper;
// import com.logistics.md.mapper.PartnerMapper;
// import com.logistics.sy.domain.UserVO;
import com.logistics.tm.domain.TMAppDTO;
// import com.logistics.tm.mapper.ShuttleMapper;
import com.logistics.tm.mapper.TmAppMapper;
import com.logistics.util.StringUtils;
import com.logistics.util.file.FileResponseVO;
// import com.logistics.util.file.FileUploadUtil;
import com.logistics.util.file.FileUploadUtilProperties;
// import com.logistics.util.file.FileUploadService;
// import com.logistics.util.file.FileUploadUtil;
// import com.logistics.util.file.FileUploadUtilService;
// import com.logistics.util.file.FileUploadVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TMAppService {

  // private final OrganizationMapper organizationMapper;
  // private final CodeMapper codeMapper;
  // private final PartnerMapper partnerMapper;
  // private final OrganizationMapper organizationMapper;
  // private final ShuttleMapper shuttleMapper;
  // private final TmAppMapper tmAppMapper;
  @Autowired
  private TmAppMapper tmAppMapper;

  @Autowired
  private BMReportMapper bmReportMapper;

  @Autowired
  private BillingMapper billingMapper;

  // private UnitsMapper unitsMapper;

  // @Autowired
  // private final FileUploadUtilService fileUploadUtilService;

  // private final FileUploadUtil fileUploadUtil;

  private final Map<String, String> path;
  // private final Map<String, String> fileConfig;
  // private final Map<String, String> imgConfig;
  // private final Map<String, String> typeConfig;

  @Autowired
  public TMAppService(FileUploadUtilProperties properties) {
    this.path = properties.getPath();
    // this.imgConfig = properties.getImgConfig();
    // this.typeConfig = properties.getTypeConfig();
    // this.fileConfig = properties.getFileConfig();
  }

  public List<TMAppDTO> getOrderList(TMAppDTO tmAppDTO) {
    return tmAppMapper.selectTplnhdList(tmAppDTO);
  }

  public List<TMAppDTO> getOrderDetailList(TMAppDTO tmAppDTO) {
    return tmAppMapper.selectTplnitList(tmAppDTO);
  }

  public List<TMAppDTO> getOrderDetailListByVhplnkyList(TMAppDTO tmAppDTO) {
    return tmAppMapper.selectTplnitListByVhplnkyList(tmAppDTO);
  }

  public int deliveryLoad(TMAppDTO tmAppDTO) {
    // return tmAppMapper.deliveryLoad(tmAppDTO) // nhd : 쓰면안됨
    return tmAppMapper.deliveryLoad2(tmAppDTO);
    // + tmAppMapper.deliveryLoad3(tmAppDTO); // ocosal
    // + tmAppMapper.deliveryLoad4(tmAppDTO);
  }

  public int deliveryStart(TMAppDTO tmAppDTO) {
    return tmAppMapper.deliveryStart(tmAppDTO);
  }

  // public int deliveryDest(TMAppDTO tmAppDTO) {
  // return tmAppMapper.deliveryDest(tmAppDTO);
  // }

  public int deliveryArriveRequest(TMAppDTO tmAppDTO) {
    if (tmAppMapper.getCountTvhent(tmAppDTO) == 0)
      return tmAppMapper.deliveryArriveRequestNew(tmAppDTO);
    return tmAppMapper.deliveryArriveRequest(tmAppDTO);
  }

  public List<TMAppDTO> getDeliveryArriveRequest(TMAppDTO tmAppDTO, UserVO userInfo) {
    tmAppDTO.setUserData(userInfo);
    return tmAppMapper.selectTvhentList(tmAppDTO);
  }

  public void checkEntering(TMAppDTO tmAppDTO) {
    tmAppMapper.checkEntering(tmAppDTO);
    if (tmAppDTO.getOresult() != 0) {
      throw new ProcedureCheckedException(tmAppDTO.getOresult() + " : " + tmAppDTO.getOmsgkey());
    }
  }

  public int deliveryArrive(TMAppDTO tmAppDTO) {
    return tmAppMapper.deliveryArrive(tmAppDTO);
  }

  public int deliveryUnload(TMAppDTO tmAppDTO) {
    return tmAppMapper.deliveryUnload(tmAppDTO)
        + tmAppMapper.deliveryUnload2(tmAppDTO); // ocosal
    // + tmAppMapper.deliveryUnload3(tmAppDTO); // ocopur
    // + tmAppMapper.deliveryUnload4(tmAppDTO); //nhd : 쓰면안됨
  }

  public FileResponseVO uploadFile(MultipartHttpServletRequest multiRequest) throws IOException {
    // FileUploadVO fileUpload = new FileUploadVO();
    FileResponseVO res = new FileResponseVO();

    List<MultipartFile> files = multiRequest.getFiles("file");
    // fileUpload.setFiles(filelist);
    // fileUpload.setFiles(multiRequest.getFiles("file"));

    String uploadPath = multiRequest.getParameter("path");
    // fileUpload.setUploadPath(tmAppDTO.getTargetfilepath());
    // fileUpload.setUploadPath(multiRequest.getParameter("path"));

    // 이하 파일 저장
    String uploadPathRoot = path.get("rootpath");
    String uploadPathUrl = path.get("urlpath");
    StringBuilder realPath = new StringBuilder()
        .append(uploadPathRoot).append("umslp/tm/delivery/")
        .append(uploadPath);
    File uploadFolder = new File(realPath.toString());
    if (!uploadFolder.exists()) {
      log.info("make dir=>{}", uploadFolder.mkdirs());
    }
    StringBuilder urlPath = new StringBuilder()
        .append(uploadPathUrl).append("umslp/tm/delivery/")
        .append(uploadPath);
    List<String> urls = new ArrayList<>();
    for (MultipartFile file : files) {
      UUID fileName = UUID.randomUUID();
      log.info("name=>{}", file.getOriginalFilename());
      realPath.append("/").append(fileName).append(StringUtils.getExtention(file.getOriginalFilename()));
      urlPath.append("/").append(fileName).append(StringUtils.getExtention(file.getOriginalFilename()));
      log.info("realPath=>{}", realPath);
      log.info("urlPath=>{}", urlPath);
      urls.add(urlPath.toString());
      file.transferTo(new File(realPath.toString()));
    }
    res.setUrls(urls);
    return res;

    // return fileUploadUtil.simpleFileUpload(fileUpload);
    // return fileUploadUtilService.deliveryFileUpload(fileUpload);
    // return fileUploadUtilService.simpleFileUpload(fileUpload);
  }

  public int deliveryCertify(TMAppDTO tmAppDTO) {
    // MultipartHttpServletRequest multiRequest, UserVO userInfo)
    // throws IOException {

    String fittype = tmAppDTO.getFittype();

    if (fittype.equals("sign")) {
      // sig
      tmAppDTO.setSigsvip(tmAppDTO.getTargetfileip());
      tmAppDTO.setSigflph(tmAppDTO.getFilepath());
      tmAppDTO.setSigflnm(tmAppDTO.getFilename());
    } else if (fittype.equals("photo")) {
      // pic
      tmAppDTO.setPicsvip(tmAppDTO.getTargetfileip());
      tmAppDTO.setPicflph(tmAppDTO.getFilepath());
      tmAppDTO.setPicflnm(tmAppDTO.getFilename());
    }

    // FileUploadVO fileUpload = new FileUploadVO();

    // MultipartFile file = tmAppDTO.getFile();
    // List<MultipartFile> filelist = new ArrayList<MultipartFile>();
    // filelist.add(file);

    // fileUpload.setFiles(filelist);

    // fileUpload.setUploadPath(tmAppDTO.getTargetfilepath());

    // try {
    // fileUploadUtilService.deliveryFileUpload(fileUpload);
    // // fileUploadUtil.simpleFileUpload(fileUploadVO);
    // // targetfilepath
    // // String[] targetfilepatharray = tmAppDTO.getTargetfilepath().split("null")
    // // fittype

    // } catch (Exception e) {

    // }

    return tmAppMapper.deliveryCertify(tmAppDTO);
  }

  public int deliveryFinish(TMAppDTO tmAppDTO) {
    return tmAppMapper.deliveryFinish(tmAppDTO);
  }

  public int deliveryPosition(TMAppDTO tmAppDTO) {
    return tmAppMapper.deliveryPosition(tmAppDTO);
  }

  public List<Map<String, Object>> selectOcosalDestkeyGroup(TMAppDTO tmAppDTO) {
    return tmAppMapper.selectOcosalDestkeyGroup(tmAppDTO);
  }

  public List<OcosalVO> selectOcosalForTPLList(TMAppDTO tmAppDTO) {
    return tmAppMapper.selectOcosalForTPLList(tmAppDTO);
  }

  public List<OcopurVO> selectOcopurForTPLList(TMAppDTO tmAppDTO) {
    return tmAppMapper.selectOcopurForTPLList(tmAppDTO);
  }

  public List<MdesmaDTO> selectMdesmaList(TMAppDTO tmAppDTO) {
    return tmAppMapper.selectMdesmaList(tmAppDTO);
  }

  public List<MskuwcDTO> selectSkuwcList(TMAppDTO tmAppDTO) {
    return tmAppMapper.selectSkuwcList(tmAppDTO);
  }

  // public Map<String, String> deliveryInItemStart(List<TMAppDTO> listOcopurVos)
  // {
  // String copodky = tmAppMapper.getNewCopodky();
  // String eoasnky = tmAppMapper.getNewEoasnky();
  // listOcopurVos.forEach(o -> {
  // // o.setCreuser(userInfo.getUseract());
  // o.setCopodky(copodky);
  // o.setEoasnky(eoasnky);

  // tmAppMapper.sp_tm_inbound_ord(o);
  // });

  // Map<String, String> res = new HashMap<String, String>();
  // res.put("copodky", copodky);
  // res.put("eoasnky", eoasnky);

  // return res;
  // }

  public Map<String, String> deliveryInStart(List<TMAppDTO> listTmAppDTOs, UserVO userInfo) {
    String vhplnky = tmAppMapper.getNewVhplnky();
    String copodky = tmAppMapper.getNewCopodky();
    String eoasnky = tmAppMapper.getNewEoasnky();

    listTmAppDTOs.forEach(o -> {
      o.setUserData(userInfo);
      o.setVhplnky(vhplnky);
      o.setCopodky(copodky);
      o.setEoasnky(eoasnky);
      tmAppMapper.sp_tm_inbound_ord(o);
      if (o.getOresult() != 0) {
        // log.info("::::::::::::: procesure 오류 ::::::::::::::::");
        // log.error(o.getOmsgkey());
        // log.info("::::::::::::: procesure 오류 끝 ::::::::::::::::");

        throw new ProcedureCheckedException(o.getOmsgkey());
      }
    });
    // listTmAppDTOs.forEach(o -> {
    tmAppMapper.sp_tm_inbound(listTmAppDTOs.get(0));
    if (listTmAppDTOs.get(0).getOresult() != 0) {
      // log.info("::::::::::::: procesure 오류 ::::::::::::::::");
      // log.error(o.getOmsgkey());
      // log.info("::::::::::::: procesure 오류 끝 ::::::::::::::::");

      throw new ProcedureCheckedException(listTmAppDTOs.get(0).getOmsgkey());
    }
    // });
    Map<String, String> res = new HashMap<String, String>();
    res.put("copodky", copodky);
    res.put("eoasnky", eoasnky);
    res.put("vhplnky", vhplnky);

    return res;
  }

  public String deliveryOutStart(List<TMAppDTO> listOcosalVos, UserVO userInfo) {

    // Map<String, Object> sqlMap = new HashMap<String, Object>();

    // sqlMap.put("userData", userInfo);
    // sqlMap.put("list", listOcosalVos);
    String vhplnky = tmAppMapper.getNewVhplnky();
    // sqlMap.put("vhplnky", vhplnky);
    // tmAppMapper.deliveryOutStart(sqlMap);

    listOcosalVos.forEach(o -> {
      o.setVhplnky(vhplnky);
      o.setUserData(userInfo);

      tmAppMapper.deliveryOutStart(o);
      if (o.getOresult() != 0) {
        // log.info("::::::::::::: procesure 오류 ::::::::::::::::");
        // log.error(o.getOmsgkey());
        // log.info("::::::::::::: procesure 오류 끝 ::::::::::::::::");

        throw new ProcedureCheckedException(o.getOmsgkey());
      }
    });

    return vhplnky;
  }

  public int deliveryOutSuspend(List<TMAppDTO> tmAppDTO) {
    return tmAppMapper.deliveryOutSuspend(tmAppDTO);
  }

  public List<TMAppDTO> deliveryShuttleVehicleStatusList(TMAppDTO tmAppDTO) {
    String postdat = tmAppDTO.getPostdat();
    if (postdat != null && !postdat.isEmpty() && (tmAppDTO.getVhpdate() == null || tmAppDTO.getVhpdate().isEmpty())) {
      tmAppDTO.setVhpdate(postdat);
    }
    return tmAppMapper.deliveryShuttleVehicleStatusList(tmAppDTO);
  }

  public String deliveryRecertifyHistory(TMAppDTO tmAppDTO) throws Exception {
    int tripcnt = tmAppDTO.getTripcnt();

    int hcount = tmAppMapper.hasBtgthdCount(tmAppDTO);

    if (hcount > 0) {
      throw new Exception("이미 정산된 달입니다.");
    }

    for (int counter = 0; counter < tripcnt; counter++) {
      tmAppMapper.sp_tm_plan_billing(tmAppDTO);
    }

    if (tmAppDTO.getOresult() != 0) {
      throw new ProcedureCheckedException((String) tmAppDTO.getOmsgkey());
    }
    return tmAppDTO.getOvhplnk();
  }

  public Map<String, Object> deliveryMonthlyPayReport(TMAppDTO tmAppDTO) {

    String bthdcky = tmAppMapper.selectBthdckyForMonthlyPayReport(tmAppDTO);

    if (bthdcky == null) {
      // 정산되지 않았습니다.
      return null;
    }

    BMReportDTO bmReportDTO = new BMReportDTO();
    bmReportDTO.setBthdcky(bthdcky);
    bmReportDTO.setCompkey(tmAppDTO.getUserData().getCompkey());
    bmReportDTO.setWarekey(tmAppDTO.getWarekey());
    bmReportDTO.setVehicky(tmAppDTO.getVehicky());

    BillingDTO billingDTO = new BillingDTO();
    // billingDTO.setBthdcky(bthdcky);
    billingDTO.setWarekey(tmAppDTO.getWarekey());
    billingDTO.setBthyymm(tmAppDTO.getBthyymm());
    billingDTO.setUserData(tmAppDTO.getUserData());

    Map<String, Object> resultsMap = new HashMap<>();

    List<BillingDTO> headRes = billingMapper.selectBmb09HeadList(billingDTO);

    resultsMap.put("bthdcky", bthdcky);
    resultsMap.put("head", headRes);
    resultsMap.put("title", bmReportMapper.bmb09ParameterData(bmReportDTO));
    resultsMap.put("subData1", bmReportMapper.bmb09SubData1(bmReportDTO));
    resultsMap.put("subData2", bmReportMapper.bmb09SubData2(bmReportDTO));

    return resultsMap;
  }

  // public MskuwcDTO selectMskuwcData() {
  // return unitsMapper.selectMskuwcData();
  // }
}
