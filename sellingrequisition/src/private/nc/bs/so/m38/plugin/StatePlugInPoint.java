package nc.bs.so.m38.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

public enum StatePlugInPoint implements IPluginPoint {

  /**
   * 预订单整单关闭
   */
  BillCloseState("nc.bs.so.m38.state.bill.BillCloseState"),

  /**
   * 预订单整单打开
   */
  BillOpenState("nc.bs.so.m38.state.bill.BillOpenState"),

  /**
   * 预订单行关闭
   */
  RowCloseState("nc.bs.so.m38.state.row.RowCloseState"),

  /**
   * 预订单行打开
   */
  RowOpenState("nc.bs.so.m38.state.row.RowOpenState");

  // 插入点
  private String point;

  private StatePlugInPoint(String point) {
    this.point = point;
  }

  @Override
  public String getComponent() {
    return "m38";
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
