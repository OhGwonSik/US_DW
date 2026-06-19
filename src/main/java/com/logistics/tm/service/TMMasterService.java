
package com.logistics.tm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.common.dto.RequestDTO;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.md.domain.MdesmaDTO;
import com.logistics.md.domain.MptnmaDTO;
import com.logistics.md.domain.MwarmaDTO;
import com.logistics.md.mapper.OrganizationMapper;
import com.logistics.md.mapper.PartnerMapper;
import com.logistics.sy.domain.UserVO;
import com.logistics.tm.domain.TMMasterDTO;
// import com.logistics.tm.domain.TpvassDTO;
// import com.logistics.tm.domain.TshrhdDTO;
// import com.logistics.tm.domain.TshritDTO;
// import com.logistics.tm.domain.TshrvhDTO;
import com.logistics.tm.domain.TvhcmaVO;
import com.logistics.tm.mapper.TMMasterMapper;

@Service
public class TMMasterService {

  @Autowired
  private TMMasterMapper tmMasterMapper;

  // @Autowired
  // private CodeMapper codeMapper;

  @Autowired
  private PartnerMapper partnerMapper;

  @Autowired
  private OrganizationMapper organizationMapper;

  public List<TvhcmaVO> converTvhcmaVO(ArrayList<TvhcmaVO> arr) {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.convertValue(arr, mapper.getTypeFactory().constructCollectionType(List.class, TvhcmaVO.class));
  }

  // public List<TvhcmaVO> getTmm01List(TvhcmaVO tvhcma) {
  // return tmMasterMapper.getVehicleList(tvhcma);
  // }

  public List<TMMasterDTO> getTmm01List(TMMasterDTO param) {
    // param.put("userData", userInfo);
    return tmMasterMapper.selectTmm01List(param);
  }

  public List<TMMasterDTO> getTpvassList(Map<String, Object> param, UserVO userInfo) {
    param.put("userData", userInfo);
    return tmMasterMapper.selectTpvassList(param);
  }

  public List<TMMasterDTO> getTshrhdList(TMMasterDTO params) {
    return tmMasterMapper.selectTshrhdList(params);
  }

  public List<TMMasterDTO> getTshritList(TMMasterDTO params) {
    return tmMasterMapper.selectTshritList(params);
  }

  public List<TMMasterDTO> getTshrvhList(TMMasterDTO params) {
    return tmMasterMapper.selectTshrvhList(params);
  }

  // private List<TvhcmaDTO> convertTvhcmaDTO(ArrayList arr) {
  // ObjectMapper mapper = new ObjectMapper();
  // return mapper.convertValue(arr,
  // mapper.getTypeFactory().constructCollectionLikeType(List.class,
  // TvhcmaDTO.class));
  // }

  // private List<TpvassDTO> convertTpvassDTO(ArrayList arr) {
  // ObjectMapper mapper = new ObjectMapper();
  // return mapper.convertValue(arr,
  // mapper.getTypeFactory().constructCollectionLikeType(List.class,
  // TpvassDTO.class));
  // }

  public int saveTmm01List(Map<String, Object> params, UserVO userInfo) {
    Map grid = (Map) params.get("data");

    // List<TvhcmaDTO> insertList = convertTvhcmaDTO((ArrayList)
    // grid.get("addList"));
    // List<TvhcmaDTO> updateList = convertTvhcmaDTO((ArrayList)
    // grid.get("updateList"));
    List<TMMasterDTO> insertList = ((ArrayList) grid.get("addList"));
    List<TMMasterDTO> updateList = ((ArrayList) grid.get("updateList"));
    // List<TvhcmaDTO> oldList = convertTvhcmaDTO((ArrayList) grid.get("oldList"));
    // // 기본적으로 delete가 없다..

    Map<String, Object> sqlMap = new HashMap<String, Object>();
    sqlMap.put("userData", userInfo);

    int resultCount = 0;

    // insertList
    if (insertList.size() > 0) {
      // for (TvhcmaDTO tvhcmaDTO : insertList) {
      // // 여기서 필요한 데이터 집어넣기
      // }
      sqlMap.put("insertList", insertList);
      int insertCnt = tmMasterMapper.insertVehicleList(sqlMap);

      if (insertCnt == 0) {
        throw new InsertCheckedException();
      }
      resultCount += insertCnt;
    }

    // updateList
    if (updateList.size() > 0) {
      sqlMap.put("updateList", updateList);
      int updateCnt = tmMasterMapper.updateVehicleList(sqlMap);

      if (updateCnt == 0) {
        throw new UpdateCheckedException();
      }
      resultCount += updateCnt;
    }

    return resultCount;

  }

  /*
   * TPVASS
   * 
   */
  public int saveTpvassList(Map<String, Object> params, UserVO userInfo) {
    Map grid = (Map) params.get("data");

    // List<TpvassDTO> insertList = convertTpvassDTO((ArrayList)
    // grid.get("addList"));
    // List<TpvassDTO> updateList = convertTpvassDTO((ArrayList)
    // grid.get("updateList"));
    List<TMMasterDTO> insertList = ((ArrayList) grid.get("addList"));
    List<TMMasterDTO> updateList = ((ArrayList) grid.get("updateList"));

    Map<String, Object> sqlMap = new HashMap<String, Object>();
    sqlMap.put("userData", userInfo);

    int resultCount = 0;

    // insertList
    if (insertList.size() > 0) {
      sqlMap.put("insertList", insertList);
      int insertCnt = tmMasterMapper.insertTpvassList(sqlMap);

      if (insertCnt == 0) {
        throw new InsertCheckedException();
      }
      resultCount += insertCnt;
    }

    // updateList
    if (updateList.size() > 0) {
      sqlMap.put("updateList", updateList);
      int updateCnt = tmMasterMapper.updateTpvassList(sqlMap);

      if (updateCnt == 0) {
        throw new UpdateCheckedException();
      }
      resultCount += updateCnt;
    }

    return resultCount;
  }

  /*
   * 셔틀 : TSHRHD , TSHRIT , TSHRVH
   * 
   */
  public int saveTshrhdList(RequestDTO<TMMasterDTO> saveList) {

    List<TMMasterDTO> insertList = saveList.getAddList();
    List<TMMasterDTO> updateList = saveList.getUpdateList();

    int resultCount = 0;

    if (insertList.size() > 0) {
      int insertCnt = tmMasterMapper.insertTshrhdList(insertList);

      if (insertCnt == 0) {
        throw new InsertCheckedException();
      }
      resultCount += insertCnt;
    }

    if (updateList.size() > 0) {
      int updateCnt = tmMasterMapper.updateTshrhdList(updateList);

      if (updateCnt == 0) {
        throw new UpdateCheckedException();
      }
      resultCount += updateCnt;
    }

    return resultCount;
  }

  public int saveTshritList(RequestDTO<TMMasterDTO> saveList) {

    List<TMMasterDTO> insertList = saveList.getAddList();
    List<TMMasterDTO> updateList = saveList.getUpdateList();

    int resultCount = 0;

    if (insertList.size() > 0) {
      int insertCnt = tmMasterMapper.insertTshritList(insertList);

      if (insertCnt == 0) {
        throw new InsertCheckedException();
      }
      resultCount += insertCnt;
    }

    if (updateList.size() > 0) {
      int updateCnt = tmMasterMapper.updateTshritList(updateList);

      if (updateCnt == 0) {
        throw new UpdateCheckedException();
      }
      resultCount += updateCnt;
    }

    return resultCount;
  }

  public int deleteTshritList(List<TMMasterDTO> deleteList) {
    return tmMasterMapper.deleteTshritList(deleteList);
  }

  public int saveTshrvhList(RequestDTO<TMMasterDTO> saveList) {

    List<TMMasterDTO> insertList = saveList.getAddList();
    List<TMMasterDTO> updateList = saveList.getUpdateList();

    int resultCount = 0;

    if (insertList.size() > 0) {
      int insertCnt = tmMasterMapper.insertTshrvhList(insertList);

      if (insertCnt == 0) {
        throw new InsertCheckedException();
      }
      resultCount += insertCnt;
    }

    if (updateList.size() > 0) {
      int updateCnt = tmMasterMapper.updateTshrvhList(updateList);

      if (updateCnt == 0) {
        throw new UpdateCheckedException();
      }
      resultCount += updateCnt;
    }

    return resultCount;
  }

  public int deleteTshrvhList(List<TMMasterDTO> deleteList) {
    return tmMasterMapper.deleteTshrvhList(deleteList);
  }

  /*
   * ...
   * 
   */

  // partnerContorller 에서 MPATNMA (carrier) 가져오기
  public List<MptnmaDTO> getMptnmaList(@ModelAttribute MptnmaDTO params) {
    return partnerMapper.selectMptnmaList(params);
  }

  // MWARMA (warehouse) 가져오기
  // MDO02Service 참조
  public List<MwarmaDTO> getMwarmaList(MwarmaDTO params) {
    return organizationMapper.selectMwarmaList(params);
  }

  // public UserVO getAppUserData(Map<String, Object> params, UserVO userInfo) {
  // return
  // }

  // public List<Map<String, Object>> getCustomerList(Map<String, Object> param,
  // UserVO userInfo) {
  // // param.put("userData", userInfo);
  // List<Map<String, Object>> res = tmMasterMapper.selectCustomerList(userInfo);
  // return res;
  // }

  public List<MptnmaDTO> getCustomerList(TMMasterDTO tmMasterDTO) {
    // param.put("userData", userInfo);
    List<MptnmaDTO> res = tmMasterMapper.selectCustomerList(tmMasterDTO);
    return res;

  }

  public List<MdesmaDTO> getDestmaList(TMMasterDTO tmMasterDTO) {
    return tmMasterMapper.selectDestList(tmMasterDTO);
  }

  // Map<String, List<Object>>
  // public Map<String, Object> getOrderList(TplnhdDTO tplnhdDTO, TplnitDTO
  // tplnitDTO) {
  // Map<String, Object> res = new HashMap<>();
  // res.put("TPLNHD", tmAppMapper.selectTplnhdList(tplnhdDTO));
  // res.put("TPLNIT", tmAppMapper.selectTplnitList(tplnitDTO));
  // return res;
  // }

  // public List<TplnhdDTO> getOrderList(TplnhdDTO tplnhdDTO) {
  // return tmAppMapper.selectTplnhdList(tplnhdDTO);
  // }

  // public List<TplnitDTO> getOrderDetailList(TplnitDTO tplnitDTO) {
  // return tmAppMapper.selectTplnitList(tplnitDTO);
  // }

  // public List<TplnitDTO> getOrderDetailListByVhplnkyList(TplnitDTO vhplnkyList)
  // {
  // return tmAppMapper.selectTplnitListByVhplnkyList(vhplnkyList);
  // }
}
