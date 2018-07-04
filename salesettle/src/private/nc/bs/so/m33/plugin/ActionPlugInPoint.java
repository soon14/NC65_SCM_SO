package nc.bs.so.m33.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

/**
 * 销售结算操作逻辑的二次开发插入点定义
 * 
 * @author zc
 * 
 *         2009-12-19 下午02:47:34
 */
public enum ActionPlugInPoint implements IPluginPoint {

  /**
   * 应收结算
   */
  ARIncomeFor32("nc.bs.so.m33.biz.m32.action.ar.ARIncomeFor32Action"),

  /**
   * 差额传应收结算
   */
  ADIncomeFor32("nc.bs.so.m33.biz.m32.action.ar.ADIncomeFor32Action"),

  /**
   * 销售出库单收入结算
   */
  ARIncomeFor4C("nc.bs.so.m33.biz.action.ARIncomeFor4CAction"),

  /**
   * 销售出库单暂估应收结算
   */
  ETIncomeFor4C("nc.bs.so.m33.biz.m4c.action.ar.ETIncomeFor4CAction"),

  /**
   * 销售出库待结算单出库对冲
   */
  OutRushFor4CAction("nc.bs.so.m33.biz.m4c.action.outrush.OutRushFor4CAction");

  // 插入点
  private String point;

  private ActionPlugInPoint(String point1) {
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
