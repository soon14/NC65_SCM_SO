package nc.bs.so.returnpolicy.plugin;

import nc.vo.pubapp.res.NCModule;
import nc.vo.so.pub.enumeration.SOComponent;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;

/**
 * 退货管理插入点
 * 
 * @since 6.1
 * @version 2013-05-27 15:02:19
 * @author yixl
 */
public enum ReturnpolicyPlugInPoint implements IPluginPoint {
  /**
   * 订单删除
   */
  DeleteBP("deletebp"),

  /**
   * 订单新增保存
   */
  InsertBP("insertbp"),

  /**
   * 订单修改保存
   */
  UpdateBP("updatebp");

  @Override
  public String getComponent() {
    return SOComponent.Returnpolicy.getComponent();
  }

  @Override
  public String getModule() {
    return NCModule.SO.getName();
  }

  @Override
  public String getPoint() {
    return this.point;
  }

  private String point;

  private ReturnpolicyPlugInPoint(String point) {
    this.point = point;
  }

}
