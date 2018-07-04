package nc.vo.so.m38.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * 
 * 预订单聚合VO
 *
 * 创建日期:2010-03-31 13:38:44
 * @author 刘志伟
 * @version NCPrj 6.0
 */
@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.so.m38.entity.PreOrderHVO")
public class PreOrderVO extends AbstractBill {

  private static final long serialVersionUID = 6739698624966642225L;

  /**
   * 获得预订单子表的VO数组
   */
  @Override
  public PreOrderBVO[] getChildrenVO() {
    return (PreOrderBVO[]) super.getChildrenVO();
  }

  @Override
  public IBillMeta getMetaData() {
    IBillMeta meta = BillMetaFactory.getInstance().getBillMeta("so.preorder");
    return meta;
  }

  /**
   * 获得预订单主表的VO
   */
  @Override
  public PreOrderHVO getParentVO() {
    return (PreOrderHVO) super.getParentVO();
  }

}
