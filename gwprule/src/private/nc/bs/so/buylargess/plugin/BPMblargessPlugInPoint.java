package nc.bs.so.buylargess.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

public enum BPMblargessPlugInPoint implements IPluginPoint {
  /* 买赠设置的二次开发插入点 */
  /**
   * 买赠设置新增保存
   */
  InsertMblargessInBP("nc.bs.so.maintain.InsertMblargessInBP"),

  /**
   * 买赠设置修改保存
   */
  UpdateMblargessInBP("nc.bs.so.maintain.UpdateMblargessInBP"),
  
  /**
   * 买赠设置删除
   */
  DeleteMblargessInBP("nc.bs.so.maintain.DeleteMblargessInBP");

  // 插入点
  private String point;

  private BPMblargessPlugInPoint(String point) {
    this.point = point;
  }

  @Override
  public String getComponent() {
    return "so";
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
