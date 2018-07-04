package nc.bs.so.m38.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

public enum ActionPlugInPoint implements IPluginPoint {

  /**
   * 预订单审批
   */
  ApproveAction("nc.impl.so.m38.action.ApprovePreOrderAction"),

  /**
   * 预订单删除
   */
  DeleteAction("nc.impl.so.m38.action.DeletePreOrderAction"),

  /**
   * 预订单新增保存
   */
  InsertAction("nc.impl.so.m38.action.InsertPreOrderAction"),

  /**
   * 预订单取消审批
   */
  UnApproveAction("nc.impl.so.m38.action.UnApprovePreOrderAction"),

  /**
   * 预订单修改保存
   */
  UpdateAction("nc.impl.so.m38.action.UpdatePreOrderAction");

  // 插入点
  private String point;

  private ActionPlugInPoint(String point) {
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
