package nc.bs.so.buylargess.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

public enum ActionMblargessPlugInPoint implements IPluginPoint {
  /* 调入申请操作的二次开发插入点 */

  /**
   * 买赠设置新增保存
   */
  InsertBuyLargessAction("nc.impl.so.mbuylargess.InsertBuyLargessAction"),

  /**
   * 买赠设置修改保存
   */
  UpdateBuyLargessAction("nc.impl.so.mbuylargess.UpdateBuyLargessAction");
  // 插入点
  private String point;

  private ActionMblargessPlugInPoint(String point) {
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
