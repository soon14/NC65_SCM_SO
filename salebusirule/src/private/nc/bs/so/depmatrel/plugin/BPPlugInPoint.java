package nc.bs.so.depmatrel.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

public enum BPPlugInPoint implements IPluginPoint {
  /**
   * 新增保存
   */
  InsertBP("nc.vo.so.depmatrel.plugin.BPPlugInPoint.Insert"),

  /**
   * 修改保存
   */
  UpdateBP("nc.vo.so.depmatrel.plugin.BPPlugInPoint.Update"),

  /**
   * 删除
   */
  DeleteBP("nc.vo.so.depmatrel.plugin.BPPlugInPoint.Delete");

  // 插入点
  private String point;

  private BPPlugInPoint(String point) {
    this.point = point;
  }

  @Override
  public String getModule() {
    return NCModule.SO.getName();
  }

  @Override
  public String getComponent() {
    return "salebusirule";
  }

  @Override
  public String getPoint() {
    return this.point;
  }
}
