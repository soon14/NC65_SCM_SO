package nc.bs.so.m30.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;
import nc.vo.so.pub.enumeration.SOComponent;

/**
 * 订单状态的插入点定义
 */
public enum StatePlugInPoint implements IPluginPoint {
  /**
   * 销售订单收入结算关
   */
  ArSettleCloseState("nc.vo.so.m30.plugin.StatePlugInPoint.ArSettleClose"),

  /**
   * 销售订单收入结算关
   */
  ArSettleOpenState("nc.vo.so.m30.plugin.StatePlugInPoint.ArSettleOpen"),

  /**
   * 订单整单审批
   */
  BillAuditState("nc.vo.so.m30.plugin.StatePlugInPoint.BillAuditState"),

  /**
   * 订单整单关闭
   */
  BillCloseState("nc.vo.so.m30.plugin.StatePlugInPoint.BillClose"),

  /**
   * 订单整单冻结
   */
  BillFreezeState("nc.vo.so.m30.plugin.StatePlugInPoint.BillFreeze"),

  /**
   * 订单整单打开
   */
  BillOpenState("nc.vo.so.m30.plugin.StatePlugInPoint.BillOpen"),

  /**
   * 订单整单解冻
   */
  BillUnFreezeState("nc.vo.so.m30.plugin.StatePlugInPoint.BillUnFreeze"),

  /**
   * 销售订单成本结算关闭
   */
  CostSettleCloseState("nc.vo.so.m30.plugin.StatePlugInPoint.CostSettleClose"),

  /**
   * 销售订单成本结算打开
   */
  CostSettleOpenState("nc.vo.so.m30.plugin.StatePlugInPoint.CostSettleOpen"),

  /**
   * 销售订单发票关闭
   */
  InvoiceCloseState("nc.vo.so.m30.plugin.StatePlugInPoint.InvoiceClose"),

  /**
   * 销售订单发票打开
   */
  InvoiceOpenState("nc.vo.so.m30.plugin.StatePlugInPoint.InvoiceOpen"),

  /**
   * 销售订单出库关闭
   */
  OutCloseState("nc.vo.so.m30.plugin.StatePlugInPoint.OutClose"),

  // -- ====================== 销售订单行状态 ===========================
  /**
   * 销售订单出库打开
   */
  OutOpenState("nc.vo.so.m30.plugin.StatePlugInPoint.OutOpen"),

  /**
   * 销售订单行关闭
   */
  RowCloseState("nc.vo.so.m30.plugin.StatePlugInPoint.RowClose"),

  /**
   * 销售订单行打开
   */
  RowOpenState("nc.vo.so.m30.plugin.StatePlugInPoint.RowOpen"),

  /**
   * 销售订单发货关闭
   */
  SendCloseState("nc.vo.so.m30.plugin.StatePlugInPoint.SendClose"),

  /**
   * 销售订单发货关闭
   */
  SendOpenState("nc.vo.so.m30.plugin.StatePlugInPoint.SendOpen");

  // 插入点
  private String point;

  private StatePlugInPoint(String point) {
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
