package nc.itf.so.m30trantype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.aop.Wrapper;
import nc.pubimpl.bdlayer.cache.CacheVOQuery;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.DirectType;

public class M30TranTypeServiceImpl implements IM30TranTypeService, Wrapper {

  @Override
  public Map<String, Integer> queryDirectType(String[] ctrantypeids)
      throws BusinessException {
    Map<String, Integer> returnMap = new HashMap<String, Integer>();
    M30TranTypeVO[] tranTypeVOs = this.queryTranTypeVOs(ctrantypeids);
    if (tranTypeVOs != null) {
      for (M30TranTypeVO vo : tranTypeVOs) {
        Integer directType =
            vo.getFdirecttype() == null ? DirectType.DIRECTTRAN_NO
                .getIntegerValue() : vo.getFdirecttype();
        returnMap.put(vo.getCtrantypeid(), directType);
      }
    }
    return returnMap;
  }

  @Override
  public Map<String, UFBoolean> queryIsDirectPO(String[] ctrantypeids)
      throws BusinessException {
    // Map<交易类型code, 是否直运采购>
    Map<String, UFBoolean> returnMap = new HashMap<String, UFBoolean>();
    M30TranTypeVO[] tranTypeVOs = this.queryTranTypeVOs(ctrantypeids);
    if (tranTypeVOs != null) {
      for (M30TranTypeVO vo : tranTypeVOs) {
        if (DirectType.DIRECTTRAN_PO.equalsValue(vo.getFdirecttype())) {
          returnMap.put(vo.getCtrantypeid(), UFBoolean.TRUE);
        }
        else {
          returnMap.put(vo.getCtrantypeid(), UFBoolean.FALSE);
        }
      }
    }
    return returnMap;
  }

  @Override
  public Map<String, UFBoolean> queryIsDirectTO(String[] ctrantypeids)
      throws BusinessException {
    // Map<交易类型code, 是否直运调拨>
    Map<String, UFBoolean> returnMap = new HashMap<String, UFBoolean>();
    M30TranTypeVO[] tranTypeVOs = this.queryTranTypeVOs(ctrantypeids);
    if (tranTypeVOs != null) {
      for (M30TranTypeVO vo : tranTypeVOs) {
        if (DirectType.DIRECTTRAN_TO.equalsValue(vo.getFdirecttype())) {
          returnMap.put(vo.getCtrantypeid(), UFBoolean.TRUE);
        }
        else {
          returnMap.put(vo.getCtrantypeid(), UFBoolean.FALSE);
        }
      }
    }
    return returnMap;
  }

  @Override
  public String[] queryDirectTypeAllBillTypeCode(String pk_group)
      throws BusinessException {
    List<String> retList = new ArrayList<String>();
    try {
      String[] selectFds = {
        M30TranTypeVO.CTRANTYPEID
      };
      String[] whrFields = {
        M30TranTypeVO.FDIRECTTYPE, M30TranTypeVO.PK_GROUP
      };
      Object[][] whrFdValues = new Object[1][];
      whrFdValues[0] = new Object[2];
      whrFdValues[0][0] = DirectType.DIRECTTRAN_PO.getIntegerValue();
      whrFdValues[0][1] = pk_group;

      // 缓存vo查询
      CacheVOQuery<M30TranTypeVO> cacheVOQuery =
          new CacheVOQuery<M30TranTypeVO>(M30TranTypeVO.class, selectFds);
      M30TranTypeVO[] vos = cacheVOQuery.query(whrFields, whrFdValues);
      if (vos != null && vos.length > 0) {
        for (M30TranTypeVO vo : vos) {
          retList.add(vo.getCtrantypeid());
        }
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return retList.toArray(new String[retList.size()]);
  }

  @Override
  public M30TranTypeVO queryTranType(String pk_group, String vtrantypecode)
      throws BusinessException {
    M30TranTypeVO[] retVOs = this.queryTranTypeVOs(pk_group, new String[] {
      vtrantypecode
    });
    if (retVOs != null && retVOs.length > 0) {
      return retVOs[0];
    }
    return null;
  }

  @Override
  public M30TranTypeVO[] queryTranTypeVOs(String pk_group,
      String[] vtrantypecodes) throws BusinessException {
    M30TranTypeVO[] retVOs = null;
    try {
      String[] selectFds = this.getTranTypeSelectFds();
      String[] whrFields = {
        M30TranTypeVO.VTRANTYPECODE, M30TranTypeVO.PK_GROUP
      };
      int length = vtrantypecodes.length;
      String[][] whrFdValues = new String[length][];
      for (int i = 0; i < length; i++) {
        whrFdValues[i] = new String[2];
        whrFdValues[i][0] = vtrantypecodes[i];
        whrFdValues[i][1] = pk_group;
      }

      // 缓存vo查询
      CacheVOQuery<M30TranTypeVO> cacheVOQuery =
          new CacheVOQuery<M30TranTypeVO>(M30TranTypeVO.class, selectFds);
      retVOs = cacheVOQuery.query(whrFields, whrFdValues);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return retVOs;
  }

  @Override
  public M30TranTypeVO queryTranTypeVO(String trantypeid)
      throws BusinessException {
    M30TranTypeVO[] retVOs = this.queryTranTypeVOs(new String[] {
      trantypeid
    });
    if (retVOs != null && retVOs.length > 0) {
      return retVOs[0];
    }
    return null;
  }

  @Override
  public M30TranTypeVO[] queryTranTypeVOs(String[] ctrantypeids)
      throws BusinessException {
    M30TranTypeVO[] retVOs = null;
    try {
      String[] selectFds = this.getTranTypeSelectFds();
      String[] whrFields = {
        M30TranTypeVO.CTRANTYPEID
      };
      int length = ctrantypeids.length;
      String[][] whrFdValues = new String[length][];
      for (int i = 0; i < length; i++) {
        whrFdValues[i] = new String[1];
        whrFdValues[i][0] = ctrantypeids[i];
      }
      // 缓存vo查询
      CacheVOQuery<M30TranTypeVO> cacheVOQuery =
          new CacheVOQuery<M30TranTypeVO>(M30TranTypeVO.class, selectFds);
      retVOs = cacheVOQuery.query(whrFields, whrFdValues);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return retVOs;
  }

  private String[] getTranTypeSelectFds() {
    // 查询所有的字段(排除dr、ts)
    List<String> attNames = new ArrayList<String>();
    String[] allAtts = new M30TranTypeVO().getAttributeNames();
    for (String att : allAtts) {
      // 排除dr伪列
      if ("dr".equals(att)) {
        continue;
      }
      if ("PSEUDOCOLUMN".equalsIgnoreCase(att)) {
        continue;
      }
      attNames.add(att);
    }
    return attNames.toArray(new String[attNames.size()]);
  }

  @Override
  public void setWrapping(Object wrapping) {
    return;
  }

  @Override
  public Map<String, Integer> queryAskPriceRule(String[] ctrantypeids)
      throws BusinessException {
    // Map<交易类型code, 询价规则>
    Map<String, Integer> returnMap = new HashMap<String, Integer>();
    M30TranTypeVO[] tranTypeVOs = this.queryTranTypeVOs(ctrantypeids);
    if (tranTypeVOs != null) {
      for (M30TranTypeVO vo : tranTypeVOs) {
        returnMap.put(vo.getCtrantypeid(), vo.getFaskqtrule());
      }
    }
    return returnMap;
  }
}
