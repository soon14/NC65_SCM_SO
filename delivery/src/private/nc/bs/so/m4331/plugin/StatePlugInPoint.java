package nc.bs.so.m4331.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

public enum StatePlugInPoint implements IPluginPoint {

  /**
   * 发货单整单关闭
   */
  BillCloseState("nc.bs.so.m4331.state.bill.BillCloseState"),

  /**
   * 发货单整单打开
   */
  BillOpenState("nc.bs.so.m4331.state.bill.BillOpenState"),

  /**
   * 发货单出库关闭
   */
  OutCloseState("nc.bs.so.m4331.state.OutCloseState"),

  /**
   * 发货单出库打开
   */
  OutOpenState("nc.bs.so.m4331.state.OutOpenState"),

  /**
   * 发货单行关闭
   */
  RowCloseState("nc.bs.so.m4331.state.row.RowCloseState"),

  /**
   * 收入结算关闭
   */
  ArSettleCloseState("nc.bs.so.m4331.assist.state.row.ArSettleCloseState"),

  /**
   * 发货单行打开
   */
  RowOpenState("nc.bs.so.m4331.state.row.RowOpenState"),

  /**
   * 发货单收入结算打开
   */
  ArSettleOpenState("nc.bs.so.m4331.assist.state.row.ArSettleOpenState");

  // 插入点
  private String point;

  private StatePlugInPoint(String point) {
    this.point = point;
  }

  @Override
  public String getComponent() {
    return "4331";
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
