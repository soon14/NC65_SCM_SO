package nc.bs.so.m33.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

/**
 * 销售结算后台服务的二次开发插入点定义
 * 
 * @author zc
 * 
 *         2009-12-19 下午02:47:08
 */
public enum ServicePlugInPoint implements IPluginPoint {

  /**
   * 销售发票推式生成销售结算单
   */
  Push33For32("nc.pubitf.so.m33.so.m32.ISquare33For32.pushSquareSrv"),

  /**
   * 途损单推式生成销售结算单
   */
  Push33For4453("nc.pubitf.so.m33.ic.m4453.ISquareAcionFor4453"),

  /**
   * 销售出库单推式生成销售结算单
   */
  Push33For4C("nc.pubitf.so.m33.so.m32.ISquare33For4C.pushSquareSrv");

  // 插入点
  private String point;

  private ServicePlugInPoint(String point1) {
    this.point = point1;
  }

  @Override
  public String getComponent() {
    return "m33";
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
