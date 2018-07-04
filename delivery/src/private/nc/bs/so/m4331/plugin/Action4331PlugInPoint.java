package nc.bs.so.m4331.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

public enum Action4331PlugInPoint implements IPluginPoint {
  /**
   * 发货单审批
   */
  ApproveAction("nc.impl.so.m4331.action.DeliveryApproveAction"),

  /**
   * 发货单状态检查
   */
  CheckStatusByCredit("nc.impl.so.m4331.pf.DeliveryCheckStatus.byCredit"),

  /**
   * 发货单状态检查
   */
  CheckStatusByATP("nc.impl.so.m4331.pf.DeliveryCheckStatus.byATP"),

  /**
   * 发货单删除
   */
  DeleteAction("nc.impl.so.m4331.action.DeliveryDeleteAction"),

  /**
   * 发货单新增保存
   */
  InsertAction("nc.impl.so.m4331.action.DeliveryInsertAction"),

  /**
   * 发货单质检保存
   */
  InsertDeliverycheck(
      "nc.impl.so.m4331.action.DeliverycheckInsertAction.insert"),

      /**
       * 发货单送审
       */
      SendApproveAction(
          "nc.impl.so.m4331.action.DeliverySendApproveAction.sendApprove"),

          /**
           * 发货单弃审
           */
          UnApproveAction("nc.impl.so.m4331.action.DeliveryUnApproveAction"),

          /**
           * 发货单修改保存
           */
          UpdateAction("nc.impl.so.m4331.action.DeliveryUpdateAction"),

          /**
           * 发货单打开
           */
          BillOpenAction("nc.impl.so.m4331.action.assist.DeliveryBillOpenAction"),


          /**
           * 发货单关闭
           */
          BillCloseAction("nc.impl.so.m4331.action.assist.DeliveryBillCloseAction"),

          /**
           * 发货单关闭
           */
          BillCloseActionByPara("nc.impl.so.m4331.action.assist.DeliveryBillCloseAction.SOParameterVO"),

          /**
           * 发货单行打开
           */
          RowOpenAction("nc.impl.so.m4331.action.assist.DeliveryRowOpenAction"),

          /**
           * 发货单行关闭
           */
          RowCloseAction("nc.impl.so.m4331.action.assist.DeliveryRowCloseAction");
  // 插入点
  private String point;

  private Action4331PlugInPoint(String point) {
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
