package nc.vo.so.pub.util.biz;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.pfflow01.BillbusinessVO;
import nc.vo.pub.pfflow04.MessagedriveVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billaction.SOBillAction;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.trade.checkrule.VOChecker;

import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;

import nc.bs.pubapp.AppBsContext;

import nc.impl.pubapp.env.BSContext;

public class SOBusiUtil {

  // 是否有发票
  private boolean ifHasInv;

  // 是否有出库
  private boolean ifHasOut;

  /**
   * 根据业务流程ID查询业务流程中所有单据类型
   * 
   * @param busiID -- 业务流程id
   * @return Set<单据类型>
   */
  public Set<String> queryAllBillType(String busiID) {
    Set<String> retSet = new HashSet<String>();
    String pk_group = BSContext.getInstance().getGroupID();
    BillbusinessVO[] vos = null;
    vos = PfServiceScmUtil.findBillbusinessVOs(busiID, pk_group);
    if (vos != null && vos.length > 0) {
      for (BillbusinessVO vo : vos) {
        retSet.add(vo.getPk_billtype());
      }
    }
    return retSet;
  }

  /**
   * 根据业务流程IDs查询业务流程中所有单据类型
   * 
   * @param busiIDs -- 业务流程ids
   * @return Map<业务流程id, Set<单据类型>>
   */
  public Map<String, Set<String>> queryAllBillType(String[] busiIDs) {
    Map<String, Set<String>> retMap = new HashMap<String, Set<String>>();
    for (String busiID : busiIDs) {
      Set<String> typeSet = new HashSet<String>();
      String pk_group = BSContext.getInstance().getGroupID();
      BillbusinessVO[] vos = null;
      vos = PfServiceScmUtil.findBillbusinessVOs(busiID, pk_group);
      if (vos != null && vos.length > 0) {
        for (BillbusinessVO vo : vos) {
          typeSet.add(vo.getPk_billtype());
        }
      }
      retMap.put(busiID, typeSet);
    }
    return retMap;
  }

  /**
   * 根据业务流程ID查询销售业务流程类型
   * 
   * @param busiIDs -- 业务流程id数组
   * @return <业务流程id,SOBusiMDEnum>
   */
  public Map<SOBusiPara, SOBusiMDEnum> querySOBusiType(SOBusiPara[] paras) {
    if (VOChecker.isEmpty(paras)) {
      ExceptionUtils.unSupported();
    }
    Map<SOBusiPara, SOBusiMDEnum> map = new HashMap<SOBusiPara, SOBusiMDEnum>();
    for (SOBusiPara para : paras) {
      this.initBizType(para, map);
    }

    if (VOChecker.isEmpty(map)) {
      ExceptionUtils.unSupported();
    }

    return map;
  }

  /**
   * 根据业务流程ID查询销售业务流程类型
   * 
   * @param busiIDs -- 业务流程id数组
   * @return <业务流程id,SOBusiMDEnum>
   */
  public Map<String, SOBusiMDEnum> querySOBusiType(String[] bizs) {
    if (VOChecker.isEmpty(bizs)) {
      ExceptionUtils.unSupported();
    }
    Map<String, SOBusiMDEnum> map = new HashMap<String, SOBusiMDEnum>();
    for (String para : bizs) {
      this.initBizType(para, map);
    }

    if (VOChecker.isEmpty(map)) {
      ExceptionUtils.unSupported();
    }

    return map;
  }

  /**
   * 查询业务流程中，销售出库单和销售发票是否参与结算（应收或者成本）
   * 
   * @param bizs
   * @return <业务流程,[0]销售出库单是否参与结算 [1]销售发票是否参与结算>
   */
  public Map<String, String[]> querySquareBusiBillType(String[] bizs) {
    // <业务流程,[0]销售出库单交易类型 [1]销售发票交易类型>
    Map<String, String[]> mbizbilltype = new HashMap<String, String[]>();
    for (String busiID : bizs) {
      String pk_group = AppBsContext.getInstance().getPkGroup();
      BillbusinessVO[] vos = null;
      vos = PfServiceScmUtil.findBillbusinessVOs(busiID, pk_group);
      if (vos != null && vos.length > 0) {
        String outTransType = null;
        String invoiceTransType = null;
        for (BillbusinessVO vo : vos) {
          String billType = vo.getPk_billtype();
          if (ICBillType.SaleOut.getCode().equals(billType)) {
            outTransType = vo.getTranstype();
          }
          if (SOBillType.Invoice.getCode().equals(billType)) {
            invoiceTransType = vo.getTranstype();
          }
        }
        mbizbilltype.put(busiID, new String[] {
          outTransType, invoiceTransType
        });
      }
    }
    return this.querySquareBusiBillType(mbizbilltype);
  }

  private String checkInvoiceIFSquareAction(String sourceTransType,
      String sourceBusiType) {
    MessagedriveVO[] mvos =
        this.queryMessagedrive(sourceTransType, SOBillType.Invoice.getCode(),
            sourceBusiType, SOBillAction.SaleInvoiceApprove.getCode());
    String flag = "N";
    if (VOChecker.isEmpty(mvos)) {
      return flag;
    }
    for (MessagedriveVO mvo : mvos) {
      String act = mvo.getActiontype();
      if (!PubAppTool.isNull(act)
          && (SOBillAction.SaleInvoiceSQUAREINCOME.getCode().equals(act) || SOBillAction.SaleInvoiceSQUARECOST
              .getCode().equals(act))) {
        flag = "Y";
        break;
      }
    }
    return flag;
  }

  private String checkOutIFSquareAction(String sourceTransType,
      String sourceBusiType) {
    MessagedriveVO[] mvos =
        this.queryMessagedrive(sourceTransType, ICBillType.SaleOut.getCode(),
            sourceBusiType, SOBillAction.SaleOutSIGN.getCode());
    String flag = "N";
    if (VOChecker.isEmpty(mvos)) {
      return flag;
    }
    for (MessagedriveVO mvo : mvos) {
      String act = mvo.getActiontype();
      if (!PubAppTool.isNull(act)
          && (SOBillAction.SaleOutAutoAR.getCode().equals(act)
              || SOBillAction.SaleOutAutoCost.getCode().equals(act)
              // 2013-5-27 cheney需求确认，流程中配置手工结算也要影响关闭状态（fengjb，yixl，tianft）
              || SOBillAction.SaleOutManualAR.getCode().equals(act) || SOBillAction.SaleOutManualCost
              .getCode().equals(act))) {
        flag = "Y";
        break;
      }
    }
    return flag;
  }

  /**
   * 返回业务流程中销售订单的交易类型
   * 
   * @param biz
   * @return
   */
  private String getM30TransTypeCode(String biz) {
    String pk_group = AppContext.getInstance().getPkGroup();
    BillbusinessVO[] vos = PfServiceScmUtil.findBillbusinessVOs(biz, pk_group);
    BillbusinessVO m30billvo = null;
    for (BillbusinessVO vo : vos) {
      if (SOBillType.Order.getCode().equals(vo.getPk_billtype())) {
        m30billvo = vo;
        break;
      }
    }

    String m30transTypeCode = null;
    if (null == m30billvo) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006004_0", "04006004-0012")/*@res "业务流程中没有销售订单"*/);
    }
    else {
      // 交易类型编码
      m30transTypeCode = m30billvo.getTranstype();
    }

    return m30transTypeCode;
  }

  /**
   * 业务流程中是否有出库或者发票
   * 
   * @param biz
   */
  private void initBill(String biz) {
    BillbusinessVO[] bvos =
        PfServiceScmUtil.findBillbusinessVOs(biz, AppBsContext.getInstance()
            .getPkGroup());
    for (BillbusinessVO bvo : bvos) {
      if (SOBillType.Invoice.getCode().equals(bvo.getPk_billtype())) {
        this.ifHasInv = true;
      }
      else if (ICBillType.SaleOut.getCode().equals(bvo.getPk_billtype())) {
        this.ifHasOut = true;
      }
    }
  }

  private void initBizType(SOBusiPara para, Map<SOBusiPara, SOBusiMDEnum> map) {
    if (VOChecker.isEmpty(para) || PubAppTool.isNull(para.getBusitype())
        || PubAppTool.isNull(para.getM30transType())) {
      ExceptionUtils.unSupported();
    }
    String biz = para.getBusitype();
    String transTypeCode = para.getM30transType();

    boolean bout = false;
    boolean binv = false;
    // 拉式动作
    UFBoolean[] flag = this.initPullBizType(biz);
    if (!VOChecker.isEmpty(flag)) {
      bout = flag[0].booleanValue();
      binv = flag[1].booleanValue();
    }
    // 推式动作
    else {
      flag = this.initPushBizType(biz, transTypeCode);
      if (!VOChecker.isEmpty(flag)) {
        // ExceptionUtils
        // .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
        // .getStrByID("4006004_0", "04006004-0013")/*@res
        // "业务流程中销售订单没有配置下游单据"*/);
        bout = flag[0].booleanValue();
        binv = flag[1].booleanValue();
      }
    }

    this.processReturnResult(bout, binv, para, map);
  }

  private void initBizType(String biz, Map<String, SOBusiMDEnum> map) {
    if (VOChecker.isEmpty(biz)) {
      ExceptionUtils.unSupported();
    }

    boolean bout = false;
    boolean binv = false;
    // 拉式动作
    UFBoolean[] flag = this.initPullBizType(biz);
    if (!VOChecker.isEmpty(flag)) {
      bout = flag[0].booleanValue();
      binv = flag[1].booleanValue();
    }
    // 推式动作
    else {
      String transTypeCode = this.getM30TransTypeCode(biz);
      flag = this.initPushBizType(biz, transTypeCode);
      if (VOChecker.isEmpty(flag)) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006004_0", "04006004-0013")/*@res "业务流程中销售订单没有配置下游单据"*/);
      }
      bout = flag[0].booleanValue();
      binv = flag[1].booleanValue();
    }

    this.processReturnResult(bout, binv, biz, map);
  }

  /**
   * UFBoolean[0] 订单下游存在出库标志
   * UFBoolean[1] 订单下游存在发票标志
   * 
   * @param biz
   * @return
   */
  private UFBoolean[] initPullBizType(String biz) {
    // 业务流程中是否有出库或者发票
    this.initBill(biz);

    // 查询此业务流程中销售订单的下游(拉式动作)
    BillbusinessVO[] afterbinessVos =
        PfServiceScmUtil.queryBillDest(SOBillType.Order.getCode(), biz);

    if (VOChecker.isEmpty(afterbinessVos)) {
      return null;
    }

    UFBoolean[] flag = new UFBoolean[] {
      UFBoolean.FALSE, UFBoolean.FALSE
    };
    // 拉式动作销售订单下游
    if (!VOChecker.isEmpty(afterbinessVos)) {
      for (BillbusinessVO bvo : afterbinessVos) {
        String billDestType = bvo.getPk_billtype();
        // 订单下游是发货单或者出库单
        if (SOBillType.Delivery.getCode().equals(billDestType)
            || ICBillType.SaleOut.getCode().equals(billDestType)) {
          flag[0] = UFBoolean.TRUE;
          continue;
        }
        // 订单下游是销售发票
        if (SOBillType.Invoice.getCode().equals(billDestType)) {
          flag[1] = UFBoolean.TRUE;
          continue;
        }
      }
    }
    return flag;
  }

  /**
   * UFBoolean[0] 订单下游存在出库标志
   * UFBoolean[1] 订单下游存在发票标志
   * 
   * @param biz
   * @return
   */
  private UFBoolean[] initPushBizType(String biz, String transTypeCode) {
    // 业务流程中是否有出库或者发票
    this.initBill(biz);

    MessagedriveVO[] aftermessVos =
        PfServiceScmUtil.queryAllMsgdrvVOs(transTypeCode, biz,
            SOBillAction.SaleOrderApprove.getCode());
    UFBoolean[] flag = new UFBoolean[] {
      UFBoolean.FALSE, UFBoolean.FALSE
    };
    if (VOChecker.isEmpty(aftermessVos)) {
      aftermessVos =
          PfServiceScmUtil.queryAllMsgdrvVOs(SOBillType.Order.getCode(), biz,
              SOBillAction.SaleOrderApprove.getCode());
      if (VOChecker.isEmpty(aftermessVos)) {
        return null;
      }
    }
    for (MessagedriveVO mvo : aftermessVos) {
      String billDestType =
          PfServiceScmUtil.getBillTypeByTransType(mvo.getPk_billtype());
      // 订单下游是发货单或者出库单
      if (SOBillType.Delivery.getCode().equals(billDestType)
          || ICBillType.SaleOut.getCode().equals(billDestType)) {
        flag[0] = UFBoolean.TRUE;
        continue;
      }
      // 订单下游是销售发票
      if (SOBillType.Invoice.getCode().equals(billDestType)) {
        flag[1] = UFBoolean.TRUE;
        continue;
      }
    }
    return flag;
  }

  private void processReturnResult(boolean bout, boolean binv, SOBusiPara para,
      Map<SOBusiPara, SOBusiMDEnum> map) {
    if (bout && binv) {
      map.put(para, SOBusiMDEnum.SOBUSIMDENUM_INVOUTPARALLEL);
    }
    else if (bout && !binv && this.ifHasInv) {
      map.put(para, SOBusiMDEnum.SOBUSIMDENUM_OUTFIRST);
    }
    else if (!bout && binv) {
      map.put(para, SOBusiMDEnum.SOBUSIMDENUM_INVOICEFIRST);
    }
    else {
      map.put(para, SOBusiMDEnum.SOBUSIMDENUM_NO_BUSI);
    }
  }

  private void processReturnResult(boolean bout, boolean binv, String biz,
      Map<String, SOBusiMDEnum> map) {
    if (bout && binv) {
      map.put(biz, SOBusiMDEnum.SOBUSIMDENUM_INVOUTPARALLEL);
    }
    else if (bout && !binv && this.ifHasInv) {
      map.put(biz, SOBusiMDEnum.SOBUSIMDENUM_OUTFIRST);
    }
    else if (!bout && binv) {
      map.put(biz, SOBusiMDEnum.SOBUSIMDENUM_INVOICEFIRST);
    }
  }

  private MessagedriveVO[] queryMessagedrive(String sourceTransType,
      String sourceBillType, String sourceBusiType, String sourceAction) {
    MessagedriveVO[] mvos =
        PfServiceScmUtil.queryAllMsgdrvVOs(sourceTransType, sourceBusiType,
            sourceAction);

    // 交易类型查不到，用单据类型查
    if (VOChecker.isEmpty(mvos)) {
      mvos =
          PfServiceScmUtil.queryAllMsgdrvVOs(sourceBillType, sourceBusiType,
              sourceAction);
    }
    return mvos;
  }

  /**
   * 查询业务流程中，销售出库单和销售发票是否参与结算（应收或者成本）
   * 
   * @param mbizbilltype <业务流程,[0]销售出库单交易类型 [1]销售发票交易类型>
   *          <p>
   *          如果交易类型为空，自动用单据类型代替
   * @return <业务流程,[0]销售出库单是否参与结算 [1]销售发票是否参与结算>
   */
  private Map<String, String[]> querySquareBusiBillType(
      Map<String, String[]> mbizbilltype) {
    if (VOChecker.isEmpty(mbizbilltype)) {
      ExceptionUtils.unSupported();
    }
    Map<String, String[]> mreturn = new HashMap<String, String[]>();
    for (Entry<String, String[]> entry : mbizbilltype.entrySet()) {
      String biz = entry.getKey();
      if (!mreturn.containsKey(biz)) {
        String outTransType = entry.getValue()[0];
        if (PubAppTool.isNull(outTransType)) {
          outTransType = ICBillType.SaleOut.getCode();
        }
        String invoiceTransType = entry.getValue()[1];
        if (PubAppTool.isNull(invoiceTransType)) {
          invoiceTransType = SOBillType.Invoice.getCode();
        }
        // 查询销售出库单结算动作
        String outflag = this.checkOutIFSquareAction(outTransType, biz);
        // 查询销售发票结算动作
        String invoiceflag =
            this.checkInvoiceIFSquareAction(invoiceTransType, biz);
        String[] flags = new String[] {
          outflag, invoiceflag
        };
        mreturn.put(biz, flags);
      }
    }
    return mreturn;
  }

}
