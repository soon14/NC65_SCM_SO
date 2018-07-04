package nc.bs.so.m32.plugin;

import nc.vo.pubapp.res.NCModule;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售发票操作的二次开发插入点
 * </ul>
 * 
 * @version 本版本号6.0
 * @author fengjb
 * @time 2010-1-19 上午10:59:05
 */
public enum Action32PlugInPoint implements IPluginPoint {
  
  /**
   * 销售发票审批
   */
  ApproveAction("nc.impl.so.m32.action.ApproveSaleInvoiceAction"),

  /**
   * 销售发票删除
   */
  DeleteAction("nc.impl.so.m32.action.DeleteSaleInvoiceAction"),

  /**
   * 销售发票新增
   */
  InsertAction("nc.impl.so.m32.action.InsertSaleInvoiceAction"),

  /**
   * 销售发票送审
   */
  SendAppAction("nc.impl.so.m32.action.SendAppSaleInvoiceAction"),

  /**
   * 销售发票取消审核
   */
  UnApproveAction("nc.impl.so.m32.action.UnAppSaleInvoiceAction"),

  /**
   * 销售发票收回
   */
  UnSendAppAction("nc.impl.so.m32.action.UnCommitSaleInvoiceAction"),

  /**
   * 销售发票修改
   */
  UpdateAction("nc.impl.so.m32.action.UpdateSaleInvoiceAction");

  // 插入点
  private String point;

  private Action32PlugInPoint(String point) {
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
