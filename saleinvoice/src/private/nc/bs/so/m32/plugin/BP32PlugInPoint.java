package nc.bs.so.m32.plugin;

import nc.vo.pubapp.res.NCModule;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;

/**
 * BP规则插入点注册
 * 
 * @since 6.3
 * @version 2012-12-21 上午09:19:02
 * @author yaogj
 */
public enum BP32PlugInPoint implements IPluginPoint {
  
  /**
   * 销售发票审批
   */
  ApproveAction("nc.bs.so.m32.maintain.ApproveSaleInvoiceBP"),

  /**
   * 销售发票传凭证
   */
  CreateVoucherAction("nc.bs.so.m32.maintain.CreateVoucherSaleInvoiceBP"),

  /**
   * 销售发票删除
   */
  DeleteAction("nc.bs.so.m32.maintain.DeleteSaleInvoiceBP"),

  /**
   * 销售发票新增保存
   */
  InsertAction("nc.bs.so.m32.maintain.InsertSaleInvoiceBP"),

  /**
   * 销售发票送审
   */
  SendAction("nc.bs.so.m32.maintain.CommitSaleInvoiceBP"),
  
  /**
   * 销售发票弃审
   */
  UnApproveAction("nc.bs.so.m32.maintain.UnAppSaleInvoiceBP"),

  /**
   * 销售发票修改保存
   */
  UpdateAction("nc.bs.so.m32.maintain.UpdateSaleInvoiceBP");

  // 插入点
  private String point;

  private BP32PlugInPoint(String point) {
    this.point = point;
  }

  @Override
  public String getComponent() {
    return "m32";
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
