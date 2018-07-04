package nc.pubimpl.so.m30.opc.mecc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;

import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.so.m30.ISaleOrgPubService;

import nc.pubitf.so.m30.opc.mecc.IPushSave30ForOPCArrange;

import nc.bs.framework.common.NCLocator;

import nc.impl.pubapp.env.BSContext;

/**
 * 销售订单提供给订单统一处理中心的推式保存ERP销售订单接口实现
 * 
 * @since 6.1
 * @version 2012-02-23 下午04:37:51
 * @author 刘景
 */
public class PushSave30ForOPCArrangeImpl implements IPushSave30ForOPCArrange {

  @Override
  public void pushSave(SaleOrderVO[] paravo,
      Map<String, Boolean> businessCheckMap) throws BusinessException {
    try {
      List<SaleOrderBVO> needidlist = new ArrayList<SaleOrderBVO>();
      Map<String, SaleOrderBVO> cfirstbidMap =
          new HashMap<String, SaleOrderBVO>();
      for (SaleOrderVO vo : paravo) {
        SaleOrderBVO[] bvos = vo.getChildrenVO();
        for (SaleOrderBVO bvo : bvos) {
          bvo.setStatus(VOStatus.NEW);
          needidlist.add(bvo);
          cfirstbidMap.put(bvo.getCfirstbid(), bvo);
        }
      }
      // 销售管理补全表体ID add zhangby5
      this.fillBID(needidlist);
      // 设置赠品行对应来源订单行ID add zhangby5
      this.changeClargesssrcidForESO(paravo, cfirstbidMap);
      PfUserObject[] userobjs = new PfUserObject[paravo.length];
      for (int i = 0; i < paravo.length; i++) {
        userobjs[i] = new PfUserObject();
        userobjs[i].setBusinessCheckMap(businessCheckMap);
      }
      // 处理交互异常 add by zhangby5
      if (businessCheckMap != null && businessCheckMap.size() > 0) {
        for (Entry<String, Boolean> entry : businessCheckMap.entrySet()) {
          if (entry.getKey().equals(BusinessCheck.ATPCheck.getCheckCode())) {
            if (entry.getValue().booleanValue()) {
              BSContext.getInstance()
                  .setSession(entry.getKey(), UFBoolean.TRUE);
            }
            else {
              BSContext.getInstance().setSession(entry.getKey(),
                  UFBoolean.FALSE);
            }
          }
          else {
            BSContext.getInstance()
                .setSession(entry.getKey(), entry.getValue());
          }
        }
      }
      // 处理交互异常 end

      // 调用销售订单保存脚本
      PfServiceScmUtil.processBatch("WRITE", SOBillType.Order.getCode(),
          paravo, userobjs, null);
    }
    catch (Exception exception) {
      ExceptionUtils.marsh(exception);
    }
  }

  /**
   * 补全表体ID，目的是为了给赠品行设置对应的来源订单行ID
   * 
   * @param needidlist
   */
  private void fillBID(List<SaleOrderBVO> needidlist) {
    if (needidlist.size() > 0) {
      ISaleOrgPubService service =
          NCLocator.getInstance().lookup(ISaleOrgPubService.class);
      try {
        String[] ids = service.getOIDArray(needidlist.size());
        for (int i = 0; i < needidlist.size(); i++) {
          needidlist.get(i).setCsaleorderbid(ids[i]);
        }
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

  /**
   * 电子销售在设置赠品行对应来源订单行ID(clargesssrcid)时，由于没有子表ID，所以将clargesssrcid设为了源头子表ID，
   * 此方法是将赠品行的clargesssrcid设为对应的来源订单行ID
   * 
   * @param paravo
   * @param cfirstbidMap
   */
  private void changeClargesssrcidForESO(SaleOrderVO[] paravo,
      Map<String, SaleOrderBVO> cfirstbidMap) {
    for (SaleOrderVO vo : paravo) {
      SaleOrderBVO[] bvos = vo.getChildrenVO();
      for (SaleOrderBVO bvo : bvos) {
        if (this.isNotFromESO(bvo)) {
          continue;
        }
        if (this.isNotBuylargesses(bvo)) {
          continue;
        }
        SaleOrderBVO buyVO = cfirstbidMap.get(bvo.getClargesssrcid());
        bvo.setClargesssrcid(buyVO.getCsaleorderbid());
      }
    }
  }

  private boolean isNotFromESO(SaleOrderBVO bvo) {
    if (!PubAppTool.isNull(bvo.getVfirsttype())
        && "ECC1".equals(bvo.getVfirsttype())) {
      return false;
    }
    return true;
  }

  private boolean isNotBuylargesses(SaleOrderBVO bvo) {
    if (bvo.getBlargessflag() != null && bvo.getBlargessflag().booleanValue()) {
      String largesssrcid = bvo.getClargesssrcid();
      if (PubAppTool.isNull(largesssrcid) || largesssrcid.equals("~")) {
        return true;
      }
      return false;
    }
    return true;
  }
}
