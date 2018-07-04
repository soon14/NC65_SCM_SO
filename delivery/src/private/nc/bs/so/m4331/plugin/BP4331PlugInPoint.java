package nc.bs.so.m4331.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

public enum BP4331PlugInPoint implements IPluginPoint {
  /**
   * 发货单审批
   */
  ApproveAction("nc.bs.so.m4331.maintain.ApproveDeliveryBP"),

  /**
   * 发货单删除
   */
  DeleteAction("nc.bs.so.m4331.maintain.DeleteDeliveryBP"),

  /**
   * 发货单质检删除
   */
  DeleteDeliverycheck("nc.bs.so.m4331.maintain.DeleteDeliverycheckBP.delete"),

  /**
   * 发货单新增保存
   */
  InsertAction("nc.bs.so.m4331.maintain.InsertSaleInvoiceBP"),

  /**
   * 发货的弃审
   */
  UnApproveAction("nc.bs.so.m4331.maintain.UnAppDeliveryBP"),

  /**
   * 发货单收回
   */
  UnSendBP("nc.bs.so.m4331.maintain.DeliveryUnSendApproveBP"),

  /**
   * 发货单修改保存
   */
  UpdateAction("nc.bs.so.m4331.maintain.UpdateDeliveryBP"),

  /**
   * 发货单修改保存
   */
  UpdateActionForATP("nc.bs.so.m4331.maintain.UpdateDeliveryBP.forAtP"),

  /**
   * 发货单质检修改
   */
  updateDeliverycheck("nc.bs.so.m4331.maintain.UpdateDeliveryCheckBP.update");

  // 插入点
  private String point;

  private BP4331PlugInPoint(String point) {
    this.point = point;
  }

  @Override
  public String getComponent() {
    return "m4331";
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
