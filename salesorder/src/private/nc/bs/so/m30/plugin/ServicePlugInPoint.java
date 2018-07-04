package nc.bs.so.m30.plugin;

import nc.vo.pubapp.res.NCModule;
import nc.vo.so.pub.enumeration.SOComponent;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;

/**
 * 销售订单的后台服务的二次开发插入点定义
 * 
 * @author 刘志伟
 * @since 6.0
 * @time 2010-01-28 下午13:49:07
 */
public enum ServicePlugInPoint implements IPluginPoint {
  /**
   * 销售结算回写销售订单确认应收数量
   */
  rewrite30ARFor33(
      "nc.pubimpl.so.m30.so.m33.Rewrite30For33Impl.rewrite30ARFor33"),

  /**
   * 请购单回写销售订单安排请购单数量
   */
  rewrite30ArrangeNumFor20(
      "nc.pubitf.so.m30arrange.po.m20.IRewrite30For20.rewrite30ArrangeNumFor20"),

  /**
   * 采购订单回写销售订单安排采购订单数量
   */
  rewrite30ArrangeNumFor21(
      "nc.pubitf.so.m30arrange.po.m21.IRewrite30For21.rewrite30ArrangeNumFor21"),

  /**
   * 调入申请回写销售订单安排调入申请数量
   */
  rewrite30ArrangeNumFor5A(
      "nc.pubitf.so.m30arrange.to.m5a.IRewrite30For5A.rewrite30ArrangeNumFor5A"),

  /**
   * 调拨订单回写销售订单安排调拨订单数量
   */
  rewrite30ArrangeNumFor5X(
      "nc.pubitf.so.m30arrange.to.m5x.IRewrite30For5X.rewrite30ArrangeNumFor5X"),

  /**
   * 委外订单回写销售订单安排委外订单数量
   */
  rewrite30ArrangeNumFor61(
      "nc.pubitf.so.m30arrange.sc.m61.IRewrite30For61.rewrite30ArrangeNumFor61"),

  /**
   * 生产订单回写销售订单安排生产订单数量
   */
  rewrite30ArrangeNumForA2(
      "nc.pubitf.so.m30arrange.mo.ma2.IRewrite30ForA2.rewrite30ArrangeNumForA2"),
      
  /**
   * 销售结算回写销售订单累计出库对冲数量
   */
  rewrite30OutRushFor33(
      "nc.pubimpl.so.m30.so.m33.Rewrite30For33Impl.rewrite30OutRushFor33"),    

  /**
   * 销售结算回写销售订单暂估应收数量
   */
  rewrite30ETFor33(
      "nc.pubimpl.so.m30.so.m33.Rewrite30For33Impl.rewrite30ETFor33"),

  /**
   * 计划订单回写销售订单累计生成计划订单数量
   */
  rewrite30npolnumFor55B4(
      "nc.pubitf.so.m30arrange.mmpps.plo.IRewrite30For55B4.rewrite30npolnumFor55B4"),

  /**
   * 销售发票回写销售订单开票数量
   */
  rewrite30NumFor32(
      "nc.pubitf.so.m30.so.m32.IRewriteSaleOrderBySaleInvoice.rewrite30NumFor32"),

  /**
   * 销售结算回写销售订单数量
   */
  rewrite30NumFor33("nc.pubimpl.so.m30.so.m33.Rewrite30For33Impl.rewrite"),

  /**
   * 途损单回写销售订单签收数量、途损数量
   */
  rewrite30NumFor4453(
      "nc.pubitf.so.m30.ic.m4453.IRewrite30For4453.rewrite30NumFor4453"),

  /**
   * 销售出库单回写销售订单出库数量
   */
  rewrite30NumFor4C(
      "nc.pubitf.so.m30.ic.m4c.IRewriteSaleOrderBySaleOut.rewrite30NumFor4C"),

  /**
   * 销售退货订单回写销售订单退货数量
   */
  rewrite30NumForWithdraw(
      "nc.pubitf.so.m30.so.withdraw.IRewriteSaleOrderByWithdraw."
          + "rewrite30NumForWithdraw"),

  /**
   * 收款核销回写销售订单实际收款金额
   */
  rewrite30ReceivedMnyForBalance(
      "nc.pubitf.so.m30.so.balance.IRewrite30ForBalance"
          + ".rewrite30ReceivedMnyForBalance"),

  /**
   * 预留回写销售订单预留数量
   */
  rewrite30ReqrsNumFor4480(
      "nc.pubitf.so.m30.ic.m4480.IRewrite30For4480.rewrite30ReqrsNumFor4480"),

  /**
   * 发货单回写销售订单发货数量
   */
  rewrite30SendNumFor4331(
      "nc.pubitf.so.m30.so.m4331.IRewrite30For4331.rewrite30SendNumFor4331"),
  /**
   * 销售合同回写销售订单累计安排进口合同数量
   */
  rewrite30ArrangeItcNumFor5801(
      "nc.pubitf.so.m30.it.m5801.IRewrite30For5801.rewriteNarrangeItcNumFor5801"),

  /**
   * 收款核销监听核销时回写销售订单行累财务核销金额
   */
  rewrite30TotalPayMnyForVerifyListener(
      "nc.pubitf.so.m30.so.balance.IRewrite30ForVerify"
          + ".rewrite30TotalPayMnyVerifyListener");

  // 插入点
  private String point;

  private ServicePlugInPoint(String point) {
    this.point = point;
  }

  @Override
  public String getComponent() {
    return SOComponent.Order.getComponent();
  }

  @Override
  public String getModule() {
    return NCModule.SO.getName();
  }

  @Override
  public String getPoint() {
    return this.point;
  }

}
