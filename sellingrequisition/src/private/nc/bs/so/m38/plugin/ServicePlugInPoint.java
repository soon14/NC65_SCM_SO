package nc.bs.so.m38.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

/**
 * 预订单的后台服务的二次开发插入点定义
 * 
 * @author 刘志伟
 * @since 6.0
 * @time 2010-04-08 上午09:27:07
 */
public enum ServicePlugInPoint implements IPluginPoint {

  /**
   * 销售订单回写预订单安排数量
   */
  rewrite38NarrnumFor30(
      "nc.pubitf.so.m38.so.m30.IRewrite38For30.rewrite38NarrnumFor30");

  // 插入点
  private String point;

  private ServicePlugInPoint(String point) {
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
