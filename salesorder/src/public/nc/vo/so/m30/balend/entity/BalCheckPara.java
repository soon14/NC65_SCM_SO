package nc.vo.so.m30.balend.entity;

import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.balend.enumeration.BalBillType;

public class BalCheckPara {

  private BalBillType costbaltype;

  private BalBillType incomebaltype;

  private final String orderbid;

  private String orderid;

  private SaleOutBalVO outbal;

  private boolean virneedcheck;

  private InvoiceBalVO voicebal;

  /**
   * BalCheckPara 的构造子
   * 
   * @param orderbid
   */
  public BalCheckPara(String orderbid) {
    this.orderbid = orderbid;
  }

  /**
   * 方法功能描述：返回成本结算类型。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-20 下午02:22:46
   */
  public BalBillType getCostbaltype() {
    return this.costbaltype;
  }

  /**
   * 方法功能描述：返回收入结算类型。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-20 下午02:22:22
   */
  public BalBillType getIncomebaltype() {
    return this.incomebaltype;
  }

  /**
   * 方法功能描述：返回成本结算关闭检查VO。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-16 下午01:56:33
   */
  public InvoiceBalVO getInvoicebal() {
    return this.voicebal;
  }

  /**
   * 方法功能描述：返回销售订单子表ID。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-20 下午02:21:58
   */
  public String getOrderbid() {
    return this.orderbid;
  }

  public String getOrderid() {
    return this.orderid;
  }

  /**
   * 方法功能描述：返回发票结算关闭检查VO。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-16 下午01:56:15
   */
  public SaleOutBalVO getOutbal() {
    return this.outbal;
  }

  /**
   * 方法功能描述：传入单据类型是否影响结算，包括所有结算类型。
   * <p>
   * <b>参数说明</b>
   * 
   * @param billtype
   * @return <p>
   * @author fengjb
   * @time 2010-7-20 下午02:33:43
   */
  public boolean isAffectBal(String billtype) {
    if (SOBillType.Invoice.isEqual(billtype)) {
      return this.isVoiceAffectBal();
    }
    else if (ICBillType.SaleOut.isEqual(billtype)) {
      return this.isOutAffectBal();
    }
    else if (ICBillType.WastageBill.isEqual(billtype)) {
      return this.isWasAffectBal();
    }
    else {
      return false;
    }
  }

  private boolean isWasAffectBal() {

    if ((null != this.incomebaltype && (BalBillType.OUTINCOME
        .equalsValue(this.incomebaltype)
        || BalBillType.BOTHINCOME.equalsValue(this.incomebaltype) || BalBillType.OnlyVOICEINCOME
        .equalsValue(this.incomebaltype)))

        || (null != this.costbaltype && (BalBillType.OUTCOST
            .equalsValue(this.costbaltype)
            || BalBillType.OUTCOST.equalsValue(this.costbaltype) || BalBillType.OnlyVOICECOST
            .equalsValue(this.costbaltype)))) {
      return true;
    }

    return false;

  }

  /**
   * 方法功能描述：传入单据类型是否影响成本结算。
   * <p>
   * <b>参数说明</b>
   * 
   * @param billtype
   * @return <p>
   * @author fengjb
   * @time 2010-7-20 下午02:35:42
   */
  public boolean isAffectCostBal(String billtype) {
    if (SOBillType.Invoice.isEqual(billtype)) {
      return this.isVoiceAffectCostBal();
    }
    else if (ICBillType.SaleOut.isEqual(billtype)) {
      return this.isOutAffectCostBal();
    }
    else if (ICBillType.WastageBill.isEqual(billtype)) {
      return this.isWasAffectCostBal();
    }
    else {
      return false;
    }
  }

  private boolean isWasAffectCostBal() {

    if (null != this.costbaltype
        && (this.virneedcheck
            || BalBillType.BOTHCOST.equalsValue(this.costbaltype)
            || BalBillType.OUTCOST.equalsValue(this.costbaltype) || BalBillType.OnlyVOICECOST
            .equalsValue(this.costbaltype))) {
      return true;
    }

    return false;

  }

  /**
   * 方法功能描述：传入单据类型是否影响收入结算。
   * <p>
   * <b>参数说明</b>
   * 
   * @param billtype
   * @return <p>
   * @author fengjb
   * @time 2010-7-20 下午02:35:20
   */
  public boolean isAffectIncomeBal(String billtype) {
    if (SOBillType.Invoice.isEqual(billtype)) {
      return this.isVoiceAffectIncomeBal();
    }
    else if (ICBillType.SaleOut.isEqual(billtype)) {
      return this.isOutAffectIncomeBal();
    }
    else if (ICBillType.WastageBill.isEqual(billtype)) {
      return this.isWasAffectIncomeBal();
    }
    else {
      return false;
    }
  }

  private boolean isWasAffectIncomeBal() {
    if (null != this.incomebaltype
        && (this.virneedcheck
            || BalBillType.BOTHINCOME.equalsValue(this.incomebaltype) || BalBillType.OUTINCOME
            .equalsValue(this.incomebaltype))
        || BalBillType.OnlyVOICEINCOME.equalsValue(this.incomebaltype)) {
      return true;
    }

    return false;

  }

  /**
   * 方法功能描述：设置成本结算类型。
   * <p>
   * <b>参数说明</b>
   * 
   * @param costbaltype
   *          <p>
   * @author fengjb
   * @time 2010-7-16 下午01:55:40
   */
  public void setCostbaltype(BalBillType costbaltype) {
    this.costbaltype = costbaltype;
  }

  /**
   * 方法功能描述：设置收入结算类型。
   * <p>
   * <b>参数说明</b>
   * 
   * @param incomebaltype
   *          <p>
   * @author fengjb
   * @time 2010-7-16 下午01:55:21
   */
  public void setIncomebaltype(BalBillType incomebaltype) {
    this.incomebaltype = incomebaltype;
  }

  /**
   * 方法功能描述：设置结算检查VO。
   * <p>
   * <b>参数说明</b>
   * 
   * @param balvo
   *          <p>
   * @author fengjb
   * @time 2010-7-16 下午01:55:53
   */
  public void setInvoicebal(InvoiceBalVO voicebal1) {
    this.voicebal = voicebal1;
  }

  public void setOrderid(String orderid) {
    this.orderid = orderid;
  }

  public void setOutbal(SaleOutBalVO outbal) {
    this.outbal = outbal;
  }

  public void setVirtualCheck(boolean virneedcheck1) {
    this.virneedcheck = virneedcheck1;
  }

  /**
   * 方法功能描述：出库单是否影响结算。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-20 下午02:34:37
   */
  private boolean isOutAffectBal() {
    if ((null != this.incomebaltype && (BalBillType.OUTINCOME
        .equalsValue(this.incomebaltype) || BalBillType.BOTHINCOME
        .equalsValue(this.incomebaltype)))

        || (null != this.costbaltype && (BalBillType.OUTCOST
            .equalsValue(this.costbaltype) || BalBillType.OUTCOST
            .equalsValue(this.costbaltype)))) {
      return true;
    }

    return false;

  }

  /**
   * 方法功能描述：出库单是否影响成本结算。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-20 下午02:36:17
   */
  private boolean isOutAffectCostBal() {
    if (null != this.costbaltype
        && (this.virneedcheck
            || BalBillType.BOTHCOST.equalsValue(this.costbaltype) || BalBillType.OUTCOST
            .equalsValue(this.costbaltype))) {
      return true;
    }

    return false;

  }

  /**
   * 方法功能描述：出库单是否影响收入结算。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-20 下午02:36:44
   */
  private boolean isOutAffectIncomeBal() {
    if (null != this.incomebaltype
        && (this.virneedcheck
            || BalBillType.BOTHINCOME.equalsValue(this.incomebaltype) || BalBillType.OUTINCOME
            .equalsValue(this.incomebaltype))) {
      return true;
    }

    return false;

  }

  /**
   * 方法功能描述：发票是否影响结算。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-20 下午02:34:22
   */
  private boolean isVoiceAffectBal() {
    if ((null != this.incomebaltype && (BalBillType.VOICEINCOME
        .equalsValue(this.incomebaltype)
        || BalBillType.BOTHINCOME.equalsValue(this.incomebaltype) || BalBillType.OnlyVOICEINCOME
        .equalsValue(this.incomebaltype)))

        || (null != this.costbaltype && (BalBillType.VOICECOST
            .equalsValue(this.costbaltype)
            || BalBillType.BOTHCOST.equalsValue(this.costbaltype) || BalBillType.OnlyVOICECOST
            .equalsValue(this.costbaltype)))) {
      return true;
    }
    return false;

  }

  /**
   * 方法功能描述：发票是否影响成本结算。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-20 下午02:36:00
   */
  private boolean isVoiceAffectCostBal() {
    if (null != this.costbaltype
        && (BalBillType.BOTHCOST.equalsValue(this.costbaltype)
            || BalBillType.VOICECOST.equalsValue(this.costbaltype) || BalBillType.OnlyVOICECOST
            .equalsValue(this.costbaltype))) {
      return true;
    }

    return false;

  }

  /**
   * 方法功能描述：发票是否影响收入结算。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-20 下午02:36:32
   */
  private boolean isVoiceAffectIncomeBal() {
    if (null != this.incomebaltype
        && (BalBillType.BOTHINCOME.equalsValue(this.incomebaltype)
            || BalBillType.VOICEINCOME.equalsValue(this.incomebaltype) || BalBillType.OnlyVOICEINCOME
            .equalsValue(this.incomebaltype))) {
      return true;
    }

    return false;

  }
}
