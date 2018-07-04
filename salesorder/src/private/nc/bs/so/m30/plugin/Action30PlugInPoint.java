package nc.bs.so.m30.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;
import nc.vo.so.pub.enumeration.SOComponent;

/**
 * 订单操作的插入点定义
 */
public enum Action30PlugInPoint implements IPluginPoint {

  
  /**
   * 订单保存
   */
  SaveAction("nc.vo.so.m30.plugin.ActionPlugInPoint.Save"),

  /**
   * 订单新增保存
   */
  InsertAction("nc.vo.so.m30.plugin.ActionPlugInPoint.Insert"),

  /**
   * 订单删除
   */
  DeleteAction("nc.vo.so.m30.plugin.ActionPlugInPoint.Delete"),

  /**
   * 订单修改保存
   */
  UpdateAction("nc.vo.so.m30.plugin.ActionPlugInPoint.Update"),

  /**
   * 订单送审
   */
  SendApproveAction("nc.vo.so.m30.plugin.ActionPlugInPoint.SendApprove"),

  /**
   * 订单审批
   */
  ApproveAction("nc.vo.so.m30.plugin.ActionPlugInPoint.Approve"),

  /**
   * 订单弃审
   */
  UnApproveAction("nc.vo.so.m30.plugin.ActionPlugInPoint.UnApprove"),

  /**
   * 销售转需求(调入申请、请购单)
   */
  Push5Aor20Action("nc.bs.so.m30.action.Push5Aor20Action"),

  /**
   * 预订单安排推式生成销售订单
   */
  PushSave30For38ArrangeAction(
      "nc.impl.so.m30.action.main.PushSave30For38ArrangeAction"),

  /**
   * 销售订单修订保存
   */
  ReviseSaveAction("nc.impl.so.m30.revise.action.ReviseSaveSaleOrderAction");

  // 插入点
  private String point;

  private Action30PlugInPoint(String point) {
    this.point = point;
  }

  @Override
  public String getModule() {
    return NCModule.SO.getName();
  }

  @Override
  public String getComponent() {
    return SOComponent.Order.getComponent();
  }

  @Override
  public String getPoint() {
    return this.point;
  }
}
