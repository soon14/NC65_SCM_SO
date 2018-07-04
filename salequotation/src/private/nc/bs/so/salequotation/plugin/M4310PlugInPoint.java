package nc.bs.so.salequotation.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;
import nc.vo.so.pub.enumeration.SOComponent;

public enum M4310PlugInPoint implements IPluginPoint {

  /**
   * 报价单删除
   */
  DeleteBP("nc.vo.so.m4310.plugin.BPPlugInPoint.Delete"),

  /**
   * 报价单新增保存
   */
  InsertBP("nc.vo.so.m4310.plugin.BPPlugInPoint.Insert"),

  /**
   * 报价单修改保存
   */
  UpdateBP("nc.vo.so.m4310.plugin.BPPlugInPoint.Update");

  // 插入点
  private String point;

  private M4310PlugInPoint(String point) {
    this.point = point;
  }

  @Override
  public String getComponent() {
    return SOComponent.SaleQuotation.getComponent();
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
