package nc.pubimpl.so.pub.api.fill;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyRela;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.SOKeyRela;
import nc.vo.so.pub.keyvalue.VOKeyValue;

/**
 * @description
 * 业务流程相关字段填充1
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-10-20 下午9:51:43
 * @author 刘景
 */
public class BusitypeFillRule<E extends IBill> {

  private IKeyRela keyRela;

  private E[] vos;

  /**
   * 构造方法
   * @param e
   */
  public BusitypeFillRule(
      E[] e) {
    this.keyRela = new SOKeyRela();
    this.vos = e;
  }

  /**
   * 构造方法
   * @param e
   * @param keyRela
   */
  public BusitypeFillRule(
      E[] e, IKeyRela keyRela) {
    this.keyRela = keyRela;
    this.vos = e;
  }

  public void setBusitype() {
    Set<String> ctrantypeset = new HashSet<String>();
    Set<String> ctrantypecodeset = new HashSet<String>();
    for (E vo : vos) {
      IKeyValue keyValue = new VOKeyValue<E>(vo);
      String ctrantypeid =
          keyValue.getHeadStringValue(keyRela.getCtrantypeidKey());
      String trantypecode =
          keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
      if (!PubAppTool.isNull(ctrantypeid)) {
        ctrantypeset.add(ctrantypeid);
      }
      if (!PubAppTool.isNull(trantypecode)) {
        ctrantypecodeset.add(trantypecode);
      }
    }
    Map<String, String> idcodemap = new HashMap<String, String>();
    if (ctrantypeset.size() > 0) {
      idcodemap = getTranTypecode(ctrantypeset);
    }
    Map<String, String> codeidmap = new HashMap<String, String>();
    if (ctrantypecodeset.size() > 0) {
      codeidmap = getTranTypeid(ctrantypecodeset);
    }

    Map<String, String> mapbiztype = new HashMap<String, String>();
    for (E vo : vos) {
      IKeyValue keyValue = new VOKeyValue<E>(vo);
      // 设置交易类型编码
      String ctrantypeid =
          keyValue.getHeadStringValue(keyRela.getCtrantypeidKey());
      String trantypecode =
          keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);

      String code = idcodemap.get(ctrantypeid);
      String id = codeidmap.get(trantypecode);
      if (trantypecode != null && ctrantypeid == null) {
        keyValue.setHeadValue(keyRela.getCtrantypeidKey(), id);
      }
      else if (ctrantypeid != null && trantypecode == null) {
        keyValue.setHeadValue(keyRela.getVtrantypecodeKey(), code);
      }
      // 匹配业务流程
      String newtranypecode =
          keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
      String pk_org = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
      String bizkey = pk_org + newtranypecode;
      if (mapbiztype.containsKey(bizkey)) {
        keyValue.setHeadValue(SaleOrderHVO.CBIZTYPEID, mapbiztype.get(bizkey));
      }
      else {
        String userId = AppContext.getInstance().getPkUser();
        String newbiztype =
            PfServiceScmUtil.getBusitype(SOBillType.Order.getCode(),
                newtranypecode, pk_org, userId);
        keyValue.setHeadValue(SaleOrderHVO.CBIZTYPEID, newbiztype);
        mapbiztype.put(bizkey, newbiztype);
      }
    }
  }

  private Map<String, String> getTranTypecode(Set<String> ctrantypeset) {
    IM30TranTypeService service =
        NCLocator.getInstance().lookup(IM30TranTypeService.class);
    Map<String, String> trantypecode = new HashMap<String, String>();
    try {
      M30TranTypeVO[] m30trantypevos =
          service.queryTranTypeVOs(ctrantypeset.toArray(new String[ctrantypeset
              .size()]));
      if (m30trantypevos == null || m30trantypevos.length == 0) {
        ExceptionUtils.wrappBusinessException("交易类型错误！");/* -=notranslate=- */
      }
      if (ctrantypeset.size() != m30trantypevos.length) {
        ExceptionUtils.wrappBusinessException("交易类型错误！");/* -=notranslate=- */
      }
      for (M30TranTypeVO m30trantype : m30trantypevos) {
        trantypecode.put(m30trantype.getCtrantypeid(),
            m30trantype.getVtrantypecode());
      }

    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
    return trantypecode;
  }

  private Map<String, String> getTranTypeid(Set<String> ctrantypeset) {
    IM30TranTypeService service =
        NCLocator.getInstance().lookup(IM30TranTypeService.class);
    Map<String, String> trantypecode = new HashMap<String, String>();
    try {
      String pk_group = AppContext.getInstance().getPkGroup();
      M30TranTypeVO[] m30trantypevos =
          service.queryTranTypeVOs(pk_group,
              ctrantypeset.toArray(new String[ctrantypeset.size()]));
      if (m30trantypevos == null || m30trantypevos.length == 0) {
        ExceptionUtils.wrappBusinessException("交易类型编码错误！");/* -=notranslate=- */
      }
      if (ctrantypeset.size() != m30trantypevos.length) {
        ExceptionUtils.wrappBusinessException("交易类型编码错误！");/* -=notranslate=- */
      }
      for (M30TranTypeVO m30trantype : m30trantypevos) {
        trantypecode.put(m30trantype.getVtrantypecode(),
            m30trantype.getCtrantypeid());
      }

    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
    return trantypecode;
  }
}
