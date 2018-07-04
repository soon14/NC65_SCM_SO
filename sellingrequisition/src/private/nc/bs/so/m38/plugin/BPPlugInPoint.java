package nc.bs.so.m38.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

/**
 * 预订单BP的插入点定义
 * 
 * @author 刘志伟
 */
public enum BPPlugInPoint implements IPluginPoint {
  /**
   * 预订单删除
   */
  DeleteBP("nc.bs.so.m38.maintain.DeletePreOrderBP"),

  /**
   * 预订单新增保存
   */
  InsertBP("nc.bs.so.m38.maintain.InsertPreOrderBP"),

  /**
   * 预订单修改保存
   */
  UpdateBP("nc.bs.so.m38.maintain.UpdatePreOrderBP");

  // 插入点
  private String point;

  private BPPlugInPoint(String point) {
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
