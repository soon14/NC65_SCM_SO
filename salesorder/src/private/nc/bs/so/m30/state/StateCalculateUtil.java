package nc.bs.so.m30.state;

import java.util.HashMap;
import java.util.Map;

import nc.vo.bd.material.MaterialVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.so.pub.util.SOVOChecker;
import nc.vo.so.pub.util.biz.SOBusiMDEnum;
import nc.vo.so.pub.util.biz.SOBusiPara;
import nc.vo.so.pub.util.biz.SOBusiUtil;

import nc.itf.so.m30trantype.IM30TranTypeService;

import nc.pubitf.so.m30.ic.m4453.Rewrite4453Para;
import nc.pubitf.so.m30.ic.m4c.Rewrite4CPara;
import nc.pubitf.so.m30.so.m32.Rewrite32Para;
import nc.pubitf.so.m30.so.m33.Rewrite33Para;
import nc.pubitf.so.m30.so.m4331.Rewrite4331Para;

import nc.bs.framework.common.NCLocator;

import nc.impl.pubapp.env.BSContext;

import nc.pubimpl.so.m30.ic.m4453.Rewrite30For4453Impl;
import nc.pubimpl.so.m30.ic.m4c.Rewrite30For4CImpl;

public class StateCalculateUtil {

  private Map<SOBusiPara, SOBusiMDEnum> busiTypeMap;

  private SOBusiUtil busiUtil;

  private Map<String, Rewrite32Para> m32Paras;

  private Map<String, Rewrite33Para> m33Paras;

  private Map<String, Rewrite4331Para> m4331Paras;

  private Map<String, Rewrite4453Para> m4453Paras;

  private Map<String, Rewrite4CPara> m4CParas;

  private Map<String, M30TranTypeVO> tranTypeMap;

  private IM30TranTypeService tranTypeService;

  /**
   * 行关闭状态跃迁收入结算关闭状态调用
   */
  public boolean isAutoTransitArSettleClose(SaleOrderViewVO view) {
    // 行关闭会导致收入结算关闭
    return BillStatus.CLOSED.equalsValue(view.getBody().getFrowstatus());
  }

  /**
   * 行打开状态跃迁收入结算打开状态调用
   */
  public boolean isAutoTransitArSettleOpen(SaleOrderViewVO view) {
    SaleOrderBVO bvo = view.getBody();
    boolean baudit =
        BillStatus.AUDIT.equalsValue(view.getBody().getFrowstatus());
    boolean boutopen = !ValueUtils.getBoolean(bvo.getBboutendflag());
    boolean binvoiceopen = !ValueUtils.getBoolean(bvo.getBbinvoicendflag());

    // 行打开会导致成本打开
    return baudit || boutopen || binvoiceopen;
  }

  /**
   * 根据行状态判断是否可以整单关闭。
   * <ul>
   * <li>所有行关闭，则整单关闭(表头转关闭状态)
   * </ul>
   */
  public boolean isAutoTransitBillClose(SaleOrderVO view) {
    SaleOrderBVO[] items = view.getChildrenVO();
    for (SaleOrderBVO item : items) {
      Integer frowstatus = item.getFrowstatus();
      if (!BillStatus.CLOSED.equalsValue(frowstatus)) {
        return false;
      }
    }
    return true;
  }

  /**
   * 根据行状态判断是否可以整单打开。
   * <ul>
   * <li>有一行打开，则整单打开(表头转审批状态)
   * </ul>
   */
  public boolean isAutoTransitBillOpen(SaleOrderVO view) {
    return !this.isAutoTransitBillClose(view);
  }

  /**
   * 行关闭状态跃迁成本结算关闭状态调用
   */
  public boolean isAutoTransitCostSettleClose(SaleOrderViewVO view) {
    // 行关闭会导致成本结算关闭
    return BillStatus.CLOSED.equalsValue(view.getBody().getFrowstatus());
  }

  /**
   * 行打开状态跃迁成本结算打开状态调用
   */
  public boolean isAutoTransitCostSettleOpen(SaleOrderViewVO view) {
    SaleOrderBVO bvo = view.getBody();
    boolean baudit =
        BillStatus.AUDIT.equalsValue(view.getBody().getFrowstatus());
    boolean boutopen = !ValueUtils.getBoolean(bvo.getBboutendflag());
    boolean binvoiceopen = !ValueUtils.getBoolean(bvo.getBbinvoicendflag());

    // 行打开会导致成本打开
    return baudit || boutopen || binvoiceopen;
  }

  /**
   * 行关闭状态跃迁开票关闭状态调用
   */
  public boolean isAutoTransitInvoiceClose(SaleOrderViewVO view) {
    // 行关闭会导致出库关闭
    return BillStatus.CLOSED.equalsValue(view.getBody().getFrowstatus());
  }

  /**
   * 行打开状态跃迁开票打开状态调用
   */
  public boolean isAutoTransitInvoiceOpen(SaleOrderViewVO view) {
    SaleOrderHVO head = view.getHead();
    SOBusiMDEnum busiType =
        this.getBusiTypeByID(head.getVtrantypecode(), head.getCbiztypeid());
    boolean larflag = this.isLargessCanInvoice(view);
    // 赠品行根据参数判断开票打开
    if(larflag){
      return false;
    }
    // --先货后票：如果出库打开，则开票需要打开,
    if (SOBusiMDEnum.SOBUSIMDENUM_OUTFIRST.equals(busiType)) {
      if (!ValueUtils.getBoolean(view.getBody().getBboutendflag())) {
        return true;
      }

      // 见Rewrite30For4453Impl.addRule设置Session说明
      Object flagRewrite30For4453 =
          BSContext.getInstance().getSession(
              Rewrite30For4453Impl.class.getName());
      // 见Rewrite30For4453Impl.addRule设置Session说明
      Object flagRewrite30For4C =
          BSContext.getInstance()
              .getSession(Rewrite30For4CImpl.class.getName());
      // 非手工行打开操作
      if (!SOVOChecker.isEmpty(flagRewrite30For4453)
          || !SOVOChecker.isEmpty(flagRewrite30For4C)) {
        return false;
      }

    }
    // 货票并行、先票后货不能打开
    else if (SOBusiMDEnum.SOBUSIMDENUM_INVOUTPARALLEL.equals(busiType)
        || SOBusiMDEnum.SOBUSIMDENUM_INVOICEFIRST.equals(busiType)) {
      return false;
    }
    // 行打开会导致出库打开
    return BillStatus.AUDIT.equalsValue(view.getBody().getFrowstatus());
  }

  /**
   * 行关闭状态跃迁出库关闭状态调用
   */
  public boolean isAutoTransitOutClose(SaleOrderViewVO view) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();
    boolean flag = false;
    // 行关闭会导致出库关闭
    flag = BillStatus.CLOSED.equalsValue(body.getFrowstatus());
    if (flag) {
      return flag;
    }
    // 先货后票，开票关闭会导致出库关闭，赠品除外
    boolean larflag = ValueUtils.getBoolean(body.getBlargessflag());
    SOBusiMDEnum busiType =
        this.getBusiTypeByID(head.getVtrantypecode(), head.getCbiztypeid());
    if (!larflag && SOBusiMDEnum.SOBUSIMDENUM_OUTFIRST.equals(busiType)
        && body.getBbinvoicendflag().booleanValue()) {
      flag = true;
    }
    return flag;
  }

  /**
   * 行打开状态跃迁出库打开状态调用
   */
  public boolean isAutoTransitOutOpen(SaleOrderViewVO view) {
    SaleOrderBVO body = view.getBody();
    boolean flag = false;
    // 发货打开，出库无条件打开
    flag = !body.getBbsendendflag().booleanValue();
    if (flag) {
      return flag;
    }
    // 行打开会导致出库打开
    return BillStatus.AUDIT.equalsValue(view.getBody().getFrowstatus());
  }

  /**
   * 整单状态、发货、出库、发票、结算状态关闭跃迁行关闭调用
   */
  public boolean isAutoTransitRowClose(SaleOrderViewVO view) {
    // 整单关闭，则行关闭
    if (BillStatus.CLOSED.equalsValue(view.getHead().getFstatusflag())) {
      return true;
    }
    return this.isAutoTransitRowCloseByOtherState(view);
  }

  /**
   * 整单状态、发货、出库、发票、结算状态打开跃迁行打开调用
   */
  public boolean isAutoTransitRowOpen(SaleOrderViewVO view) {
    // 整单打开，则行打开
    if (BillStatus.AUDIT.equalsValue(view.getHead().getFstatusflag())) {
      return true;
    }
    return !this.isAutoTransitRowCloseByOtherState(view);
  }

  /**
   * 行关闭状态&&出库关闭状态跃迁发货关闭状态调用
   */
  public boolean isAutoTransitSendClose(SaleOrderViewVO view) {
    // 行关闭会导致发货关闭
    if (BillStatus.CLOSED.equalsValue(view.getBody().getFrowstatus())) {
      return true;
    }

    // 出库关闭会导致发货关闭
    return view.getBody().getBboutendflag() == null ? false : view.getBody()
        .getBboutendflag().booleanValue();
  }

  /**
   * 行打开状态跃迁发货打开状态调用
   */
  public boolean isAutoTransitSendOpen(SaleOrderViewVO view) {
    // 行打开会导致发货打开
    return BillStatus.AUDIT.equalsValue(view.getBody().getFrowstatus());
  }

  /**
   * 是否可以开票关闭
   * <ul>
   * <li>根据公式：《累计开票数量+累计出库对冲数量 >= 订单数量》判断
   * </ul>
   * 
   * @param views
   * @return 能否关闭
   * @author 刘志伟
   */
  public boolean isInvoiceClose(SaleOrderViewVO view) {
    SaleOrderBVO body = view.getBody();
    Rewrite32Para para = this.getRewrite32Paras().get(body.getCsaleorderbid());
    UFDouble curchangenum = para.getNchangenum();
    return this.isInvoiceClose(view, curchangenum);
  }

  /**
   * 订单修订是否开票关闭
   * 
   * @param view
   * @return
   */
  public boolean isReviseInvoiceClose(SaleOrderViewVO view,
      SaleOrderViewVO originView) {
    // 数量没有改变
    if (!isNumChange(view, originView)) {
      return false;
    }
    return this.isInvoiceClose(view, null);
  }

  /**
   * 订单修订是否开票打开
   * 
   * @param view
   * @return
   */
  public boolean isReviseInvoiceOpen(SaleOrderViewVO view,
      SaleOrderViewVO originView) {
    // 数量没有改变
    if (!isNumChange(view, originView)) {
      return false;
    }
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();
    // 1.非关闭单据不能打开
    if (!body.getBbinvoicendflag().booleanValue()) {
      return false;
    }
    // 2.根据公式判断是否能够开票打开
    UFDouble totalinvnum = body.getNtotalinvoicenum();
    UFDouble totalrushnum = body.getNtotalrushnum();
    UFDouble totaloutnum = body.getNtotaloutnum();
    UFDouble translossnum = body.getNtranslossnum();
    UFDouble num = body.getNnum();

    SOBusiMDEnum busiType =
        this.getBusiTypeByID(head.getVtrantypecode(), head.getCbiztypeid());
    // TODO 修订无法判断自动关闭还是手工
    /*  // 销售订单之前是否自动开票关闭
      boolean bautoClose = this.isAutoInvoiceColse(view, null, busiType);*/

    boolean ret = false;
    boolean laborflag = body.getBlaborflag() == null ? false
        : body.getBlaborflag().booleanValue();
    boolean discountflag = body.getBdiscountflag() == null ? false
        : body.getBdiscountflag().booleanValue();
    // --劳务折扣行:累计开票数量 >= 订单数量
    if (laborflag || discountflag) {
      // 折扣行允许不录入数量，费用类不允许数量为空，对于折扣行数量为空的情况，其实只允许转单一次
      if (discountflag && MathTool.isZero(num)) {
        ret = true;
      }
      else {
        ret = MathTool.absCompareTo(totalinvnum, num) < 0;
      }
    }
    // --先货后票：累计开票数量 + 累计出库对冲数量 != 累计出库数量 - 累计途损数量
    else if (SOBusiMDEnum.SOBUSIMDENUM_OUTFIRST.equals(busiType)) {
      // 如果出库未关闭，则开票不自动关闭
      if (!ValueUtils.getBoolean(view.getBody().getBboutendflag())) {
        return false;
      }
      ret =
          /*bautoClose
              &&*/MathTool.absCompareTo(MathTool.add(totalinvnum, totalrushnum),
              MathTool.sub(totaloutnum, translossnum)) != 0;
    }
    // --先票后货 || 票货并行：累计开票数量 < 订单数量
    else if (SOBusiMDEnum.SOBUSIMDENUM_INVOICEFIRST.equals(busiType)
        || SOBusiMDEnum.SOBUSIMDENUM_INVOUTPARALLEL.equals(busiType)) {
      ret = /*bautoClose &&*/MathTool.absCompareTo(totalinvnum, num) < 0;
    }
    return ret;
  }

  /**
   * 是否可以开票关闭4途损单
   * <ul>
   * <li>根据公式：《累计开票数量+累计出库对冲数量 >= 订单数量》判断
   * </ul>
   * 
   * @param views
   * @return 能否关闭
   * @author 刘志伟
   */
  public boolean isInvoiceCloseFor4453(SaleOrderViewVO view) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();

    // .根据公式逻辑判断是否发票关闭
    UFDouble totalinvnum = body.getNtotalinvoicenum();
    UFDouble totalrushnum = body.getNtotalrushnum();
    UFDouble totaloutnum = body.getNtotaloutnum();
    UFDouble translossnum = body.getNtranslossnum();
    boolean ret = false;
    SOBusiMDEnum busiType =
        this.getBusiTypeByID(head.getVtrantypecode(), head.getCbiztypeid());
    // --先货后票：累计开票数量 + 累计出库对冲数量 == 累计出库数量 - 累计途损数量 并且出库关闭
    if (SOBusiMDEnum.SOBUSIMDENUM_OUTFIRST.equals(busiType)) {
      UFBoolean boutend = ValueUtils.getUFBoolean(body.getBboutendflag());
      ret =
          boutend.booleanValue()
              && MathTool.compareTo(MathTool.add(totalinvnum, totalrushnum),
                  MathTool.sub(totaloutnum, translossnum)) == 0;
    }
    return ret;
  }

  public boolean isInvoiceCloseForOutRush(SaleOrderViewVO view) {
    Rewrite33Para para =
        this.getRewrite33Paras().get(view.getBody().getCsaleorderbid());
    UFDouble curchangenum = para.getNarnum();
    return this.isInvoiceClose(view, curchangenum);
  }

  /**
   * 是否可以开票打开
   * <p>
   * 自动关闭 && (当前累计开票数量 < 主数量) 开票可以自动打开
   * 
   * @param views SaleOrderViewVO
   * @return boolean 能否打开
   * @author 刘志伟
   */
  public boolean isInvoiceOpen(SaleOrderViewVO view) {
    Rewrite32Para para =
        this.getRewrite32Paras().get(view.getBody().getCsaleorderbid());
    UFDouble curchangenum = para.getNchangenum();
    return this.isInvoiceOpen(view, curchangenum);
  }

  /**
   * 是否可以开票打开4途损单
   * 
   * @param views SaleOrderViewVO
   * @return boolean 能否打开
   * @author 刘志伟
   */
  public boolean isInvoiceOpenFor4453(SaleOrderViewVO view) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();
    Rewrite4453Para para =
        this.getRewrite4453Paras().get(body.getCsaleorderbid());

    // .根据公式判断是否能够开票打开
    UFDouble translossnum = para.getNtranslossnum();
    boolean ret = false;
    SOBusiMDEnum busiType =
        this.getBusiTypeByID(head.getVtrantypecode(), head.getCbiztypeid());

    boolean larflag = this.isLargessCanInvoice(view);
    // --先货后票：赠品不开票除外
    if (!larflag && SOBusiMDEnum.SOBUSIMDENUM_OUTFIRST.equals(busiType)) {
      // 途损影响发票打开逻辑,简化公式只要有途损数量就打开票,细节如下：
      // A.isLossReNew=false出库关闭时，发票需要打开来开负票
      // B.isLossReNew=true出库打开时，发票需要打开来正票
      if (!MathTool.isZero(translossnum)) {
        ret = true;
      }
    }
    return ret;
  }

  public boolean isInvoiceOpenForOutRush(SaleOrderViewVO view) {
    Rewrite33Para para =
        this.getRewrite33Paras().get(view.getBody().getCsaleorderbid());
    UFDouble curchangenum = para.getNarnum();
    return this.isInvoiceOpen(view, curchangenum);
  }

  /**
   * 订单行是否满足手工出库打开的条件
   * 
   * @param view
   * @return
   */
  public boolean isManualOutOpen(SaleOrderViewVO view) {
    return this.isManualOutSendOpen(view);
  }

  /**
   * 订单行是否满足手工发货打开的条件
   * 
   * @param view
   * @return
   */
  public boolean isManualSendOpen(SaleOrderViewVO view) {
    return this.isManualOutSendOpen(view);
  }

  /**
   * 是否可以出库关闭
   * <ul>
   * <li>根据出库单关闭标记判断
   * <li>根据交易类型一次出库关闭标记+数量判断
   * <li>公式判断：《|累计出库数量+累计应发未出库数量-is途损补货(累计途损数量,0)| >= |订单数量* (1- 出库关闭下限 /100)|》
   * </ul>
   * <p>
   * 特例:如果当前回写正数而上游已经手工关闭且当前累计数量>=主数量时，处理为手工关闭状态自动更新为自动关闭状态(此不用写代码控制)，
   * 所以下次回写负数时需要自动打开的。
   * 
   * @param views
   * @return 能否关闭
   * @author 刘志伟
   */
  public boolean isOutClose(SaleOrderViewVO view,
      Map<String, MaterialVO> materrialmaps) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();
    Rewrite4CPara para = this.getRewrite4CParas().get(body.getCsaleorderbid());
    // 根据回写数量判断是否需要关闭处理
    if (!this.isNeedCloseByChangeNum(body, para.getNchangenum())) {
      return false;
    }

    // 1.销售出库单出库关闭标记强制关闭上游标记
    if (para.getBclosed().booleanValue()) {
      return true;
    }

    // 2.一次性出库关闭：(|应发数量| > 0) 就关闭
    boolean isOnceOutClose = this.getIsOnceOutClose(head.getCtrantypeid());
    if (isOnceOutClose) {
      if (MathTool.absCompareTo(body.getNtotaloutnum(),
          UFDouble.ZERO_DBL) > 0) {
        return true;
      }
    }

    /*
     * 3. 途损不补货:|累计出库数量+累计应发未出库数量| >= |主数量|,则出库关闭 途损补货: |累计出库数量|
     * =0,只有|累计应发未出库数量|不管都少,出库都不关闭 途损补货:
     * |累计出库数量+累计应发未出库数量|-|途损数量|>=|订单数量|*（1-出库关闭下限%）,则出库关闭
     */
    boolean isLossReNew = this.isLossReNew(head.getCtrantypeid());
    boolean isOutClose = false;
    UFDouble nnum = this.getOrderOutCloseNum(view, materrialmaps);
    UFDouble ntotaloutnum = body.getNtotaloutnum();

    if (MathTool.absCompareTo(ntotaloutnum, UFDouble.ZERO_DBL) == 0) {
      return false;
    }
    // 途损不补货
    if (!isLossReNew) {
      isOutClose = MathTool.absCompareTo(body.getNtotaloutnum(), nnum) >= 0;
    }
    // 途损补货
    else {
      UFDouble ntotallossnum = body.getNtranslossnum();
      isOutClose =
          MathTool
              .absCompareTo(MathTool.sub(ntotaloutnum, ntotallossnum), nnum) >= 0;
    }

    return isOutClose;
  }

  /**
   * 是否可以出库关闭4途损单
   * 
   * @param views
   * @return 能否关闭
   * @author 刘志伟
   */
  public boolean isOutCloseFor4453(SaleOrderViewVO view,
      Map<String, MaterialVO> materrialmaps) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();
    // Rewrite4453Para para =
    // this.getRewrite4453Paras().get(body.getCsaleorderbid());

    // |累计出库数量 + 累计应发未出库数量 - is途损补货(累计途损数量,0)| >= |订单数量* (1- 出库关闭下限/100)|;
    UFDouble closeLowerLimit =
        this.getCloseLowerLimit(body.getCmaterialvid(), materrialmaps);
    UFDouble range = UFDouble.ONE_DBL.sub(closeLowerLimit.multiply(0.01));
    UFDouble nnum = body.getNnum().multiply(range);
    boolean isLossReNew = this.isLossReNew(head.getCtrantypeid());
    UFDouble factOutNum =
        MathTool.sub(
            MathTool.add(body.getNtotaloutnum(), body.getNtotalnotoutnum()),
            isLossReNew ? body.getNtranslossnum() : UFDouble.ZERO_DBL);
    return MathTool.absCompareTo(factOutNum, nnum) >= 0;
  }

  /**
   * 是否可以出库打开
   * <p>
   * 自动关闭 && (|当前累计出库数量 + 累计应发未出库数量 - is途损补货(累计途损数量,0)| < |主数量|)
   * 
   * @param views SaleOrderViewVO
   * @return boolean 能否打开
   * @author 刘志伟
   */
  public boolean isOutOpen(SaleOrderViewVO view,
      Map<String, MaterialVO> materrialmaps) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();
    Rewrite4CPara para = this.getRewrite4CParas().get(body.getCsaleorderbid());
    // 非关闭单据不能打开
    if (!body.getBboutendflag().booleanValue()) {
      return false;
    }
    // 根据回写数量判断是否需要打开处理：累计出库、累计应发
    if (!this.isNeedOpenByChangeNum(body, para.getNchangenum())) {
      if (!this.isNeedOpenByChangeNum(body, para.getNchangenotoutnum())) {
        return false;
      }
    }
    // 1.一次性出库关闭：(|应发数量| == 0 && |实发数量| == 0) 打开;只要有数量一定是关闭的
    boolean isOnceOutClose = this.getIsOnceOutClose(head.getCtrantypeid());
    if (isOnceOutClose) {
      UFDouble outnum = body.getNtotaloutnum();
      return MathTool.absCompareTo(outnum, UFDouble.ZERO_DBL) == 0? true
          : false;
    }

    // 途损是否补货
    boolean isLossReNew = this.isLossReNew(head.getCtrantypeid());
    UFDouble closeLowerLimit =
        this.getCloseLowerLimit(body.getCmaterialvid(), materrialmaps);
    // 销售订单之前是否自动出库关闭
    /*
     * 2013年5月21日和陈恩宇讨论，销售订单出库关闭影响发货单出库关闭，发货单出库打开影响销售订单出库打开。 boolean bautoClose
     * = this.isAutoOutColse(view, para.getNchangenum(),
     * para.getNchangenotoutnum(), isLossReNew, closeLowerLimit);
     */

    // 当前累计出库数量 + 累计应发未出库数量 - is途损补货(累计途损数量,0)<0
    UFDouble nowFactOutNum =
        MathTool.sub(body.getNtotaloutnum(),
            isLossReNew ? body.getNtranslossnum() : UFDouble.ZERO_DBL);
    UFDouble nnum = this.getOrderOutCloseNum(view, closeLowerLimit);
    // 去掉 bautoClose自动出库关闭
    return MathTool.absCompareTo(nowFactOutNum, nnum) < 0;

  }

  /**
   * 是否可以出库打开4途损单
   * <p>
   * 自动关闭 && (|当前累计出库数量 + 累计应发未出库数量 - is途损补货(累计途损数量,0)| < |主数量|) 出库可以自动打开
   * 
   * @param views SaleOrderViewVO
   * @return boolean 能否打开
   * @author 刘志伟
   */
  public boolean isOutOpenFor4453(SaleOrderViewVO view,
      Map<String, MaterialVO> materrialmaps) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();
    Rewrite4453Para para =
        this.getRewrite4453Paras().get(body.getCsaleorderbid());
    // 非关闭单据不能打开
    if (!body.getBboutendflag().booleanValue()) {
      return false;
    }

    // .公式判断是否打开
    // 原累计途损数量 = 当前累计途损数量 - 当前途损数量
    UFDouble origTranslossnum =
        MathTool.sub(body.getNtranslossnum(), para.getNtranslossnum());
    boolean isLossReNew = this.isLossReNew(head.getCtrantypeid());
    // 累计出库数量 + 累计应发未出库数量 - is途损补货(原累计途损数量,0)
    UFDouble origFactOutNum =
        MathTool.sub(
            MathTool.add(body.getNtotaloutnum(), body.getNtotalnotoutnum()),
            isLossReNew ? origTranslossnum : UFDouble.ZERO_DBL);
    // 出库下限数量
    UFDouble closeLowerLimit =
        this.getCloseLowerLimit(body.getCmaterialvid(), materrialmaps);
    UFDouble range = UFDouble.ONE_DBL.sub(closeLowerLimit.multiply(0.01));
    UFDouble lowerLimitNum = body.getNnum().multiply(range);
    // 是否自动关闭
    boolean bautoClose =
        MathTool.absCompareTo(origFactOutNum, lowerLimitNum) >= 0 ? true
            : false;
    // 实际当前累计出库数量 + 累计应发未出库数量 - is途损补货(累计途损数量,0)
    UFDouble nowFactOutNum =
        MathTool.sub(
            MathTool.add(body.getNtotaloutnum(), body.getNtotalnotoutnum()),
            isLossReNew ? body.getNtranslossnum() : UFDouble.ZERO_DBL);
    // 当前累计出库数量 + 累计应发未出库数量
    UFDouble nowTotalOutAndNotNum =
        MathTool.add(body.getNtotaloutnum(), body.getNtotalnotoutnum());
    // 自动关闭 &&(|当前累计出库数量+累计应发未出库数量-is途损补货(累计途损数量,0)|<|订单数量*(1- 出库关闭下限 /100)|)
    // 或者 (当前累计出库数量 + 累计应发未出库数量 = 0,认为下游出库单全删了) 出库可以自动打开
    return bautoClose
        && MathTool.absCompareTo(nowFactOutNum, lowerLimitNum) < 0
        || MathTool.compareTo(nowTotalOutAndNotNum, UFDouble.ZERO_DBL) == 0;
  }

  /**
   * 是否可以出库打开4修订数量
   * <p>
   * 自动关闭 &&(|主数量|>|原主数量|)
   * 
   * @param views SaleOrderViewVO
   * @return boolean 能否打开
   * @author 刘志伟
   */
  public boolean isOutOpenForRevise(SaleOrderViewVO view,
      SaleOrderViewVO originView, Map<String, MaterialVO> materrialmaps) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();

    // 数量没有改变
    if (!isNumChange(view, originView)) {
      return false;
    }

    // 非关闭单据不能打开
    if (!body.getBboutendflag().booleanValue()) {
      return false;
    }

    // 劳务折扣出库不能打开
    if (!isManualOutSendOpen(view)) {
      return false;
    }
    // 1.一次性出库关闭：(|应发数量| == 0 && |实发数量| == 0) 打开;只要有数量一定是关闭的
    boolean isOnceOutClose = this.getIsOnceOutClose(head.getCtrantypeid());
    if (isOnceOutClose) {
      UFDouble outnum = body.getNtotaloutnum();
      return MathTool.absCompareTo(outnum, UFDouble.ZERO_DBL) == 0 ? true
          : false;
    }

    // 途损是否补货
    boolean isLossReNew = this.isLossReNew(head.getCtrantypeid());
    UFDouble closeLowerLimit =
        this.getCloseLowerLimit(body.getCmaterialvid(), materrialmaps);
    // 销售订单之前是否自动发货关闭
    boolean bautoClose =
        this.isAutoOutColse(view, UFDouble.ZERO_DBL, UFDouble.ZERO_DBL,
            isLossReNew, closeLowerLimit);

    /*    // 当前累计出库数量 + 累计应发未出库数量 - is途损补货(累计途损数量,0)<0
        UFDouble nnum = view.getBody().getNnum();
        UFDouble orignNnum = originView.getBody().getNnum();*/
    return !bautoClose /*&& MathTool.absCompareTo(nnum, orignNnum) > 0*/;

  }

  /**
   * 是否可以出库关闭4修订数量
   * <p>
   * 自动关闭 &&(|主数量|>|原主数量|)
   * 
   * @param views SaleOrderViewVO
   * @return boolean 能否打开
   * @author 刘志伟
   */
  public boolean isOutCloseForRevise(SaleOrderViewVO view,
      SaleOrderViewVO originView, Map<String, MaterialVO> materrialmaps) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();

    // 数量没有改变
    if (!isNumChange(view, originView)) {
      return false;
    }

    // 非关闭单据不能打开
    if (body.getBboutendflag().booleanValue()) {
      return false;
    }

    // 1.一次性出库关闭：(|应发数量| == 0 && |实发数量| == 0) 打开;只要有数量一定是关闭的
    boolean isOnceOutClose = this.getIsOnceOutClose(head.getCtrantypeid());
    if (isOnceOutClose) {
      UFDouble outnum = body.getNtotaloutnum();
      return MathTool.absCompareTo(outnum, UFDouble.ZERO_DBL) == 0 ? false
          : true;
    }

    // 途损是否补货
    boolean isLossReNew = this.isLossReNew(head.getCtrantypeid());
    UFDouble closeLowerLimit =
        this.getCloseLowerLimit(body.getCmaterialvid(), materrialmaps);
    // 销售订单之前是否自动发货关闭
    boolean bautoClose =
        this.isAutoOutColse(view, UFDouble.ZERO_DBL, UFDouble.ZERO_DBL,
            isLossReNew, closeLowerLimit);

    /*  // 当前累计出库数量 + 累计应发未出库数量 - is途损补货(累计途损数量,0)<0
      UFDouble nnum = view.getBody().getNnum();
      UFDouble orignNnum = originView.getBody().getNnum();*/
    return bautoClose/* && MathTool.absCompareTo(nnum, orignNnum) < 0*/;

  }

  /**
   * 是否可以发货关闭
   * <ul>
   * <li>根据发货单关闭标记判断
   * <li>根据交易类型一次发货关闭标记+数量判断
   * <li>根据公式：《|累计发货数量| >= |主数量|》判断
   * </ul>
   * <p>
   * 特例:如果当前回写正数而上游已经手工关闭且当前累计数量>=主数量时，处理为手工关闭状态自动更新为自动关闭状态(此不用写代码控制)，
   * 所以下次回写负数时需要自动打开的。
   * 
   * @param views
   * @return 能否关闭
   * @author 刘志伟
   */
  public boolean isSendClose(SaleOrderViewVO view,
      Map<String, MaterialVO> materrialmaps) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();
    Rewrite4331Para para =
        this.getRewrite4331Paras().get(body.getCsaleorderbid());
    // 根据回写数量判断是否需要关闭处理
    if (!this.isNeedCloseByChangeNum(body, para.getNchangenum())) {
      return false;
    }

    // 1.发货单强制关闭上游标记
    if (para.getBclosed().booleanValue()) {
      return true;
    }

    // 2.一次性发货关闭 && |数量|> 0 就关闭
    boolean isOnceSendClose = this.getIsOnceSendClose(head.getCtrantypeid());
    if (isOnceSendClose
        && MathTool.absCompareTo(body.getNtotalsendnum(), UFDouble.ZERO_DBL) > 0) {
      return true;
    }

    /*
     * 3. 途损不补货:|累计发货数量| >= |主数量|*（1-出库关闭下限%）,则发货关闭 途损补货:
     * |发货数量|-|途损数量|>=|订单数量|*（1-出库关闭下限%）,则发货关闭
     */
    boolean isLossReNew = this.isLossReNew(head.getCtrantypeid());
    boolean isSendClose = false;
    UFDouble nnum = this.getOrderOutCloseNum(view, materrialmaps);
    if (!isLossReNew) {
      isSendClose = MathTool.absCompareTo(body.getNtotalsendnum(), nnum) >= 0;
    }
    else {
      UFDouble ntotalsendnum = body.getNtotalsendnum();
      UFDouble ntotallossnum = body.getNtranslossnum();
      isSendClose =
          MathTool.absCompareTo(MathTool.sub(ntotalsendnum, ntotallossnum),
              nnum) >= 0;
    }
    return isSendClose;
  }

  /**
   * 是否可以发货打开
   * <p>
   * 自动关闭 && (当前累计发货数量 < 主数量) 发货可以自动打开
   * 
   * @param views SaleOrderViewVO
   * @return boolean 能否打开
   * @author 刘志伟
   */
  public boolean isSendOpen(SaleOrderViewVO view,
      Map<String, MaterialVO> materrialmaps) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();
    Rewrite4331Para para =
        this.getRewrite4331Paras().get(body.getCsaleorderbid());
    UFDouble nchangenum = para.getNchangenum();
    // 非关闭单据不能打开
    if (!body.getBbsendendflag().booleanValue()) {
      return false;
    }
    // 行关闭或出库关闭发货不能打开
    if (BillStatus.CLOSED.equalsValue(view.getBody().getFrowstatus())
        || body.getBboutendflag().booleanValue()) {
      return false;
    }
    // 根据回写数量判断是否需要打开处理
    if (!this.isNeedOpenByChangeNum(body, nchangenum)) {
      return false;
    }

    // 1.一次性发货关闭 && |数量|== 0 打开,;只要有数量一定是关闭的
    boolean isOnceSendClose = this.getIsOnceSendClose(head.getCtrantypeid());
    if (isOnceSendClose) {
      UFDouble sendnum = body.getNtotalsendnum();
      return MathTool.absCompareTo(sendnum, UFDouble.ZERO_DBL) == 0 ? true
          : false;
    }

    // 途损是否补货
    boolean isLossReNew = this.isLossReNew(head.getCtrantypeid());
    UFDouble closeLowerLimit =
        this.getCloseLowerLimit(body.getCmaterialvid(), materrialmaps);
    // 销售订单之前是否自动发货关闭
    boolean bautoClose =
        this.isAutoSendColse(view, nchangenum, isLossReNew, closeLowerLimit);
    /*
     * 3. 途损不补货:|累计发货数量| < |订单数量|*（1-出库关闭下限%）,则发货关闭 途损补货: |发货数量|-|途损数量| <
     * |订单数量|*（1-出库关闭下限%）,则发货关闭
     */
    boolean isSendOpen = false;
    UFDouble nnum = this.getOrderOutCloseNum(view, closeLowerLimit);
    // 途损不补货
    if (!isLossReNew) {
      isSendOpen =
          bautoClose
              && MathTool.absCompareTo(body.getNtotalsendnum(), nnum) < 0;
    }
    // 途损补货
    else {
      UFDouble ntotalsendnum = body.getNtotalsendnum();
      UFDouble ntotallossnum = body.getNtranslossnum();
      isSendOpen =
          bautoClose
              && MathTool.absCompareTo(
                  MathTool.sub(ntotalsendnum, ntotallossnum), nnum) < 0;
    }
    return isSendOpen;
  }

  public boolean isSendOpenFor4453(SaleOrderViewVO view,
      Map<String, MaterialVO> materrialmaps) {
    boolean isSendOpen = false;
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();
    // 途损是否补货
    boolean isLossReNew = this.isLossReNew(head.getCtrantypeid());
    if (isLossReNew) {
      Rewrite4453Para para =
          this.getRewrite4453Paras().get(body.getCsaleorderbid());
      UFDouble nchangenum = para.getNtranslossnum();
      UFDouble closeLowerLimit =
          this.getCloseLowerLimit(body.getCmaterialvid(), materrialmaps);
      // 销售订单之前是否自动发货关闭
      boolean bautoClose =
          this.isAutoSendColseFor4453(view, nchangenum, isLossReNew,
              closeLowerLimit);
      if (bautoClose) {
        isSendOpen = MathTool.greaterThan(nchangenum, UFDouble.ZERO_DBL);
      }
    }
    return isSendOpen;
  }

  /**
   * 是否可以发货打开
   * <p>
   * 自动关闭 &&(|主数量|>|原主数量|)
   * 
   * @param views SaleOrderViewVO
   * @return boolean 能否打开
   * @author 刘志伟
   */
  public boolean isSendOpenForRevise(SaleOrderViewVO view,
      SaleOrderViewVO originView, Map<String, MaterialVO> materrialmaps) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();

    // 数量没有改变
    if (!isNumChange(view, originView)) {
      return false;
    }

    // 非关闭单据不能打开
    if (!body.getBbsendendflag().booleanValue()) {
      return false;
    }
    // 行关闭或出库关闭发货不能打开
    if (BillStatus.CLOSED.equalsValue(view.getBody().getFrowstatus())
        || body.getBboutendflag().booleanValue()) {
      return false;
    }
    // 劳务折扣出库不能打开
    if (!isManualOutSendOpen(view)) {
      return false;
    }

    // 1.一次性发货关闭 && |数量|== 0 打开,;只要有数量一定是关闭的
    boolean isOnceSendClose = this.getIsOnceSendClose(head.getCtrantypeid());
    if (isOnceSendClose) {
      UFDouble sendnum = body.getNtotalsendnum();
      return MathTool.absCompareTo(sendnum, UFDouble.ZERO_DBL) == 0 ? true
          : false;
    }

    // 途损是否补货
    boolean isLossReNew = this.isLossReNew(head.getCtrantypeid());
    UFDouble closeLowerLimit =
        this.getCloseLowerLimit(body.getCmaterialvid(), materrialmaps);

    /*  UFDouble nnum = view.getBody().getNnum();
      UFDouble originNnum = UFDouble.ZERO_DBL;
      if (null != originView) {
        originNnum = originView.getBody().getNnum();
      }*/
    // 销售订单之前是否自动发货关闭
    boolean bautoClose =
        this.isAutoSendColse(view, UFDouble.ZERO_DBL, isLossReNew,
            closeLowerLimit);
    return !bautoClose;
  }

  /**
   * 是否可以发货关闭修订
   * <p>
   * 自动关闭 &&(|主数量|>|原主数量|)
   * 
   * @param views SaleOrderViewVO
   * @return boolean 能否打开
   * @author 刘志伟
   */
  public boolean isSendColseForRevise(SaleOrderViewVO view,
      SaleOrderViewVO originView, Map<String, MaterialVO> materrialmaps) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();

    // 数量没有改变
    if (!isNumChange(view, originView)) {
      return false;
    }

    // 非打开单据不能关闭
    if (body.getBbsendendflag().booleanValue()) {
      return false;
    }
    /* // 行关闭或出库关闭发货不能打开
     if (BillStatus.CLOSED.equalsValue(view.getBody().getFrowstatus())
         || body.getBboutendflag().booleanValue()) {
       return false;
     }*/

    // 1.一次性发货关闭 && |数量|== 0 打开,;只要有数量一定是关闭的
    boolean isOnceSendClose = this.getIsOnceSendClose(head.getCtrantypeid());
    if (isOnceSendClose) {
      UFDouble sendnum = body.getNtotalsendnum();
      return MathTool.absCompareTo(sendnum, UFDouble.ZERO_DBL) == 0 ? true
          : false;
    }

    // 途损是否补货
    boolean isLossReNew = this.isLossReNew(head.getCtrantypeid());
    UFDouble closeLowerLimit =
        this.getCloseLowerLimit(body.getCmaterialvid(), materrialmaps);

    /* UFDouble nnum = view.getBody().getNnum();
     UFDouble originNnum = UFDouble.ZERO_DBL;
     if (null != originView) {
       originNnum = originView.getBody().getNnum();
     }*/
    // 销售订单之前是否自动发货关闭
    boolean bautoClose =
        this.isAutoSendColse(view, UFDouble.ZERO_DBL, isLossReNew,
            closeLowerLimit);
    return bautoClose/* && MathTool.absCompareTo(nnum, originNnum) > 0*/;
  }

  private SOBusiMDEnum getBusiTypeByID(String m30transTypeCode, String busitype) {
    SOBusiPara[] paras = new SOBusiPara[1];
    paras[0] = new SOBusiPara(m30transTypeCode, busitype);
    if (this.busiTypeMap == null) {
      this.busiTypeMap = new HashMap<SOBusiPara, SOBusiMDEnum>();
    }
    if (this.busiTypeMap.get(paras[0]) == null) {
      if (this.busiUtil == null) {
        this.busiUtil = new SOBusiUtil();
      }
      Map<SOBusiPara, SOBusiMDEnum> map = this.busiUtil.querySOBusiType(paras);
      this.busiTypeMap.putAll(map);
    }
    return this.busiTypeMap.get(paras[0]);
  }

  /**
   * 获得物料档案上的基本信息出库下限
   */
  private UFDouble getCloseLowerLimit(String materialvid,
      Map<String, MaterialVO> materrialmaps) {
    UFDouble closeLowerLimit = UFDouble.ZERO_DBL;
    // 下限
    if (materrialmaps != null && materrialmaps.size() > 0) {
      MaterialVO materialvo = materrialmaps.get(materialvid);
      UFDouble limit = materialvo.getOutcloselowerlimit();
      if (limit != null) {
        closeLowerLimit = limit;
      }
    }
    return closeLowerLimit;
  }

  /**
   * 交易类型——是否一次出库关闭
   * 
   * @author 刘志伟
   * @time 2010-01-28 下午13:49:07
   */
  private boolean getIsOnceOutClose(String ctrantypeid) {
    M30TranTypeVO tranTypeVO = this.getTranTypeVOByID(ctrantypeid);
    return null == tranTypeVO.getBarrangeout() ? false : tranTypeVO
        .getBarrangeout().booleanValue();
  }

  /**
   * 交易类型——是否一次发货关闭
   * 
   * @author 刘志伟
   * @time 2010-01-28 下午13:49:07
   */
  private boolean getIsOnceSendClose(String ctrantypeid) {
    M30TranTypeVO tranTypeVO = this.getTranTypeVOByID(ctrantypeid);
    return null == tranTypeVO.getBarrangeinv() ? false : tranTypeVO
        .getBarrangeinv().booleanValue();
  }

  /**
   * |订单数量|*（1-出库关闭下限%）
   * 
   * @param view
   * @return
   */
  private UFDouble getOrderOutCloseNum(SaleOrderViewVO view,
      Map<String, MaterialVO> materrialmaps) {
    SaleOrderBVO body = view.getBody();
    UFDouble closeLowerLimit =
        this.getCloseLowerLimit(body.getCmaterialvid(), materrialmaps);
    return this.getOrderOutCloseNum(view, closeLowerLimit);
  }

  /**
   * |订单数量|*（1-出库关闭下限%）
   * 
   * @param view
   * @return
   */
  private UFDouble getOrderOutCloseNum(SaleOrderViewVO view,
      UFDouble closeLowerLimit) {
    SaleOrderBVO body = view.getBody();
    UFDouble range = UFDouble.ONE_DBL.sub(closeLowerLimit.multiply(0.01));
    UFDouble nnum = MathTool.nvl(body.getNnum()).multiply(range);
    return nnum;
  }

  @SuppressWarnings("unchecked")
  private Map<String, Rewrite32Para> getRewrite32Paras() {
    if (this.m32Paras == null) {
      this.m32Paras =
          (Map<String, Rewrite32Para>) BSContext.getInstance().getSession(
              Rewrite32Para.class.getName());
    }
    return this.m32Paras;
  }

  @SuppressWarnings("unchecked")
  private Map<String, Rewrite33Para> getRewrite33Paras() {
    if (this.m33Paras == null) {
      this.m33Paras =
          (Map<String, Rewrite33Para>) BSContext.getInstance().getSession(
              Rewrite33Para.class.getName());
    }
    return this.m33Paras;
  }

  @SuppressWarnings("unchecked")
  private Map<String, Rewrite4331Para> getRewrite4331Paras() {
    if (this.m4331Paras == null) {
      this.m4331Paras =
          (Map<String, Rewrite4331Para>) BSContext.getInstance().getSession(
              Rewrite4331Para.class.getName());
    }
    return this.m4331Paras;
  }

  @SuppressWarnings("unchecked")
  private Map<String, Rewrite4453Para> getRewrite4453Paras() {
    if (this.m4453Paras == null) {
      this.m4453Paras =
          (Map<String, Rewrite4453Para>) BSContext.getInstance().getSession(
              Rewrite4453Para.class.getName());
    }
    return this.m4453Paras;
  }

  @SuppressWarnings("unchecked")
  private Map<String, Rewrite4CPara> getRewrite4CParas() {
    if (this.m4CParas == null) {
      this.m4CParas =
          (Map<String, Rewrite4CPara>) BSContext.getInstance().getSession(
              Rewrite4CPara.class.getName());
    }
    return this.m4CParas;
  }

  private IM30TranTypeService getTranTypeService() {
    if (this.tranTypeService == null) {
      this.tranTypeService =
          NCLocator.getInstance().lookup(IM30TranTypeService.class);
    }
    return this.tranTypeService;
  }

  private M30TranTypeVO getTranTypeVOByID(String ctrantypeid) {
    if (this.tranTypeMap == null) {
      this.tranTypeMap = new HashMap<String, M30TranTypeVO>();
    }
    if (this.tranTypeMap.get(ctrantypeid) == null) {
      M30TranTypeVO[] tranTypeVOs = null;
      try {
        tranTypeVOs = this.getTranTypeService().queryTranTypeVOs(new String[] {
          ctrantypeid
        });
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
      if (tranTypeVOs != null && tranTypeVOs.length > 0) {
        this.tranTypeMap.put(tranTypeVOs[0].getCtrantypeid(), tranTypeVOs[0]);
      }
    }
    return this.tranTypeMap.get(ctrantypeid);
  }

  /**
   * 销售订单之前是否自动开票关闭
   * 
   * @param view
   * @param curchangenum -- 当前开票变化量
   * @param busiType
   * @return
   */
  private boolean isAutoInvoiceColse(SaleOrderViewVO view,
      UFDouble curchangenum, SOBusiMDEnum busiType) {
    SaleOrderBVO body = view.getBody();
    // 原累计开票数量 = 当前累计开票数量 - 当前开票数量
    UFDouble totalinvnum =
        MathTool.sub(body.getNtotalinvoicenum(), curchangenum);
    UFDouble totalrushnum = body.getNtotalrushnum();
    UFDouble totaloutnum = body.getNtotaloutnum();
    UFDouble translossnum = body.getNtranslossnum();
    UFDouble num = body.getNnum();
    // 是否自动关闭
    boolean bautoClose = false;
    // --先货后票：累计开票数量 + 累计出库对冲数量 == 累计出库数量 - 累计途损数量
    if (SOBusiMDEnum.SOBUSIMDENUM_OUTFIRST.equals(busiType)) {
      // 如果出库未关闭，则开票不自动关闭
      if (!ValueUtils.getBoolean(view.getBody().getBboutendflag())) {
        return false;
      }
      bautoClose =
          MathTool.compareTo(MathTool.add(totalinvnum, totalrushnum),
              MathTool.sub(totaloutnum, translossnum)) == 0;
    }
    // --先票后货 || 票货并行：累计开票数量 >= 订单数量
    else if (SOBusiMDEnum.SOBUSIMDENUM_INVOICEFIRST.equals(busiType)
        || SOBusiMDEnum.SOBUSIMDENUM_INVOUTPARALLEL.equals(busiType)) {
      bautoClose = MathTool.compareTo(totalinvnum, num) >= 0;
    }
    return bautoClose;
  }

  /**
   * 销售订单之前是否自动出库关闭
   * 
   * @param view
   * @param para
   * @param isLossReNew
   * @param closeLowerLimit
   * @return
   */
  private boolean isAutoOutColse(SaleOrderViewVO view, UFDouble changenum,
      UFDouble changenotoutnum, boolean isLossReNew, UFDouble closeLowerLimit) {
    SaleOrderBVO body = view.getBody();
    // 原累计出库数量 = 当前累计出库数量 - 当前出库数量
    UFDouble origTotalOutnum = MathTool.sub(body.getNtotaloutnum(), changenum);
    // 原累计应发未出库数量 = 当前累计应发未出库数量 - 当前应发未出库数量
    UFDouble origTotalnotoutnum =
        MathTool.sub(body.getNtotalnotoutnum(), changenotoutnum);
    // 原累计出库数量 + 原累计应发未出库数量 - is途损补货(累计途损数量,0)
    UFDouble origFactOutNum =
        MathTool.sub(MathTool.add(origTotalOutnum, origTotalnotoutnum),
            isLossReNew ? body.getNtranslossnum() : UFDouble.ZERO_DBL);
    // 出库下限数量
    UFDouble nnum = this.getOrderOutCloseNum(view, closeLowerLimit);
    // 是否自动关闭
    boolean bautoClose =
        MathTool.absCompareTo(origFactOutNum, nnum) >= 0 ? true : false;
    return bautoClose;
  }

  /**
   * 销售订单之前是否自动发货关闭
   * 
   * @param view
   * @param nchangenum 当前途损变化量
   * @param isLossReNew
   * @return
   */
  private boolean isAutoSendColse(SaleOrderViewVO view, UFDouble nchangenum,
      boolean isLossReNew, UFDouble closeLowerLimit) {
    if (null == view) {
      return false;
    }
    SaleOrderBVO body = view.getBody();
    // 原累计发货数量 = 当前累计发货数量 - 当前发货数量
    UFDouble origTotalSendnum =
        MathTool.sub(body.getNtotalsendnum(), nchangenum);
    UFDouble nnum = this.getOrderOutCloseNum(view, closeLowerLimit);

    boolean bautoClose = true;
    if (!isLossReNew) {
      // 是否自动关闭 (|原累计发货数量| >= |主数量|)
      bautoClose =
          MathTool.absCompareTo(origTotalSendnum, nnum) >= 0 ? true : false;
    }
    else {
      // 是否自动关闭 (|原累计发货数量| >= |主数量|)
      UFDouble ntotallossnum = body.getNtranslossnum();
      bautoClose =
          MathTool.absCompareTo(MathTool.sub(origTotalSendnum, ntotallossnum),
              nnum) >= 0 ? true : false;
    }
    return bautoClose;
  }

  private boolean isAutoSendColseFor4453(SaleOrderViewVO view,
      UFDouble nchangenum, boolean isLossReNew, UFDouble closeLowerLimit) {
    SaleOrderBVO body = view.getBody();
    // 原累计发货数量
    UFDouble origTotalSendnum = body.getNtotalsendnum();
    UFDouble nnum = this.getOrderOutCloseNum(view, closeLowerLimit);
    boolean bautoClose = true;
    if (!isLossReNew) {
      // 是否自动关闭 (|原累计发货数量| >= |主数量|)
      bautoClose =
          MathTool.absCompareTo(origTotalSendnum, nnum) >= 0 ? true : false;
    }
    else {
      // 是否自动关闭 (|原累计发货数量-原累计途损数量| >= |主数量|)
      UFDouble origTotallossnum =
          MathTool.sub(body.getNtranslossnum(), nchangenum);
      bautoClose =
          MathTool.absCompareTo(
              MathTool.sub(origTotalSendnum, origTotallossnum), nnum) >= 0 ? true
              : false;
    }
    return bautoClose;

  }

  private boolean isAutoTransitRowCloseByOtherState(SaleOrderViewVO view) {
    // 发货关闭、出库关闭、发票关闭、结算关闭的行自动关闭
    SaleOrderBVO body = view.getBody();
    return (body.getBbsendendflag() == null ? false : body.getBbsendendflag()
        .booleanValue())
        && (body.getBboutendflag() == null ? false : body.getBboutendflag()
            .booleanValue())
        && (body.getBbinvoicendflag() == null ? false : body
            .getBbinvoicendflag().booleanValue())
        && (body.getBbarsettleflag() == null ? false : body.getBbarsettleflag()
            .booleanValue())
        && (body.getBbcostsettleflag() == null ? false : body
            .getBbcostsettleflag().booleanValue());
  }

  private boolean isInvoiceClose(SaleOrderViewVO view, UFDouble curchangenum) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();

    // 销售业务流程类型
    SOBusiMDEnum busiType =
        this.getBusiTypeByID(head.getVtrantypecode(), head.getCbiztypeid());

    // 根据公式逻辑判断是否发票关闭
    UFDouble totalinvnum = body.getNtotalinvoicenum();
    UFDouble totalrushnum = body.getNtotalrushnum();
    UFDouble totaloutnum = body.getNtotaloutnum();
    UFDouble translossnum = body.getNtranslossnum();
    UFDouble num = body.getNnum();
    boolean laborflag =
        body.getBlaborflag() == null ? false : body.getBlaborflag()
            .booleanValue();
    boolean discountflag =
        body.getBdiscountflag() == null ? false : body.getBdiscountflag()
            .booleanValue();
    boolean ret = false;
    // --劳务折扣行:累计开票数量 >= 订单数量
    if (laborflag || discountflag) {
      ret = MathTool.absCompareTo(totalinvnum, num) >= 0;
    }
    // --先货后票：累计开票数量 + 累计出库对冲数量 == 累计出库数量 - 累计途损数量
    else if (SOBusiMDEnum.SOBUSIMDENUM_OUTFIRST.equals(busiType)) {
      // 如果出库未关闭，则开票不自动关闭
      if (!ValueUtils.getBoolean(view.getBody().getBboutendflag())) {
        return false;
      }
      ret =
          MathTool.absCompareTo(MathTool.add(totalinvnum, totalrushnum),
              MathTool.sub(totaloutnum, translossnum)) == 0;
    }
    // --先票后货 || 票货并行：累计开票数量 >= 订单数量
    else if (SOBusiMDEnum.SOBUSIMDENUM_INVOICEFIRST.equals(busiType)
        || SOBusiMDEnum.SOBUSIMDENUM_INVOUTPARALLEL.equals(busiType)) {
      ret = MathTool.absCompareTo(totalinvnum, num) >= 0;
    }
    return ret;
  }

  /**
   * 
   * @param view
   * @param curchangenum -- 当前开票变化量
   * @return
   */
  private boolean isInvoiceOpen(SaleOrderViewVO view, UFDouble curchangenum) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();
    // 1.非关闭单据不能打开
    if (!body.getBbinvoicendflag().booleanValue()) {
      return false;
    }
    // 2.根据公式判断是否能够开票打开
    UFDouble totalinvnum = body.getNtotalinvoicenum();
    UFDouble totalrushnum = body.getNtotalrushnum();
    UFDouble totaloutnum = body.getNtotaloutnum();
    UFDouble translossnum = body.getNtranslossnum();
    UFDouble num = body.getNnum();

    SOBusiMDEnum busiType =
        this.getBusiTypeByID(head.getVtrantypecode(), head.getCbiztypeid());
    // 销售订单之前是否自动开票关闭
    boolean bautoClose = this.isAutoInvoiceColse(view, curchangenum, busiType);

    boolean ret = false;
    boolean laborflag =
        body.getBlaborflag() == null ? false : body.getBlaborflag()
            .booleanValue();
    boolean discountflag =
        body.getBdiscountflag() == null ? false : body.getBdiscountflag()
            .booleanValue();
    // --劳务折扣行:累计开票数量 >= 订单数量
    if (laborflag || discountflag) {
      // 折扣行允许不录入数量，费用类不允许数量为空，对于折扣行数量为空的情况，其实只允许转单一次
      if (discountflag && MathTool.isZero(num)) {
        ret = true;
      }
      else {
        ret = MathTool.absCompareTo(totalinvnum, num) < 0;
      }
    }
    // --先货后票：累计开票数量 + 累计出库对冲数量 != 累计出库数量 - 累计途损数量
    else if (SOBusiMDEnum.SOBUSIMDENUM_OUTFIRST.equals(busiType)) {
      // 如果出库未关闭，则开票不自动关闭
      if (!ValueUtils.getBoolean(view.getBody().getBboutendflag())) {
        return false;
      }
      ret =
          bautoClose
              && MathTool.absCompareTo(MathTool.add(totalinvnum, totalrushnum),
                  MathTool.sub(totaloutnum, translossnum)) != 0;
    }
    // --先票后货 || 票货并行：累计开票数量 < 订单数量
    else if (SOBusiMDEnum.SOBUSIMDENUM_INVOICEFIRST.equals(busiType)
        || SOBusiMDEnum.SOBUSIMDENUM_INVOUTPARALLEL.equals(busiType)) {
      ret = bautoClose && MathTool.absCompareTo(totalinvnum, num) < 0;
    }
    return ret;
  }

  /**
   * 赠品是否开票
   * 
   * @param view
   */
  private boolean isLargessCanInvoice(SaleOrderViewVO view) {
    SaleOrderBVO body = view.getBody();
    boolean larflag = ValueUtils.getBoolean(body.getBlargessflag());
    String settleorgid = body.getCsettleorgid();
    boolean bSO20 =
        SOSysParaInitUtil.getSO20(settleorgid) == null ? false
            : SOSysParaInitUtil.getSO20(settleorgid).booleanValue();
    return larflag && !bSO20;
  }

  /**
   * 交易类型——是否途损补货
   * 
   * @author 刘志伟
   * @time 2010-01-28 下午13:49:07
   */
  private boolean isLossReNew(String ctrantypeid) {
    M30TranTypeVO tranTypeVO = this.getTranTypeVOByID(ctrantypeid);
    return tranTypeVO.getBlossrenew() == null ? false : tranTypeVO
        .getBlossrenew().booleanValue();
  }

  private boolean isManualOutSendOpen(SaleOrderViewVO view) {
    SaleOrderBVO body = view.getBody();
    boolean flag = true;
    // 劳务折扣行发货不允许手工打开
    boolean laborflag =
        body.getBlaborflag() == null ? false : body.getBlaborflag()
            .booleanValue();
    boolean discountflag =
        body.getBdiscountflag() == null ? false : body.getBdiscountflag()
            .booleanValue();
    if (laborflag || discountflag) {
      flag = false;
    }
    return flag;

  }

  private boolean isNeedCloseByChangeNum(SaleOrderBVO body, UFDouble nchangenum) {
    // 红字行回写正数永远不关闭
    if (this.isRedRow(body)) {
      if (MathTool.compareTo(nchangenum, UFDouble.ZERO_DBL) > 0) {
        return false;
      }
    }
    // 蓝字行回写负数永远不关闭
    else {
      if (MathTool.compareTo(nchangenum, UFDouble.ZERO_DBL) < 0) {
        return false;
      }
    }
    return true;
  }

  private boolean isNeedOpenByChangeNum(SaleOrderBVO body, UFDouble nchangenum) {
    // 红字行回写负数(包含0)永远不打开
    if (this.isRedRow(body)) {
      if (MathTool.compareTo(nchangenum, UFDouble.ZERO_DBL) <= 0) {
        return false;
      }
    }
    // 蓝字行回写正数(包含0)永远不打开
    else {
      if (MathTool.compareTo(nchangenum, UFDouble.ZERO_DBL) >= 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * 是否红字订单行(负主数量)
   */
  private boolean isRedRow(SaleOrderBVO body) {
    return MathTool.compareTo(body.getNnum(), UFDouble.ZERO_DBL) < 0;
  }

  private boolean isNumChange(SaleOrderViewVO vo, SaleOrderViewVO originVO) {
    if (originVO == null) {
      return true;
    }

    UFDouble nnum = vo.getBody().getNnum();

    UFDouble oldnnum = originVO.getBody().getNnum();

    if (MathTool.equals(nnum, oldnnum)) {
      return false;
    }
    else {
      return true;
    }

  }
}
