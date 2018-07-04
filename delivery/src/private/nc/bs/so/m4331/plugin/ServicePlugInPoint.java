package nc.bs.so.m4331.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

public enum ServicePlugInPoint implements IPluginPoint {
  /**
   * 销售订单修改 修改发货单的价格
   */
  Renovate4331PriceBP("nc.pubimpl.so.m4331.so.m30.bp.Renovate4331PriceBP"),
 
  /**
   * 途损回写发货单
   */
  rewrite4331For4435("nc.pubimpl.so.m4331.ic.m4435."
      + "Rewrite4331For4435Impl.rewrite"),

  /**
   * 销售出库单回写发货单
   */
  rewrite4331outNumFor4C("nc.pubimpl.so.m4331.ic.m4c.Rewrite4331For4CImpl"),
  
  /**
   * 调拨出回写发货单
   */
  rewrite4331OutNumFor4Y(
      "nc.pubimpl.so.m4331.ic.m4y.Rewrite4331For4YImpl.rewrite4331OutNumFor4Y"),
  /**
   * 回写发货单对冲数量
   */
  rewriteRushnumFor33("nc.pubimpl.so.m4331.so.m33.bp.RewriteRushnumFor33"),
      
  /**
   * 结算回写确认应收数量
   */
  RewriteArnumFor33("nc.pubimpl.so.m4331.so.m33.bp.RewriteArnumFor33"),

  /**
   * 结算回写暂估应收数量
   */
  RewriteEstArnumFor33("nc.pubimpl.so.m4331.so.m33.bp.RewriteEstArnumFor33");

  // 插入点
  private String point;

  private ServicePlugInPoint(String point) {
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
