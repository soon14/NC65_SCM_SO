package nc.vo.so.m30.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * 
 * 单子表聚合VO
 * 
 * 创建日期:2009-06-19 08:39:32
 * 
 * @author
 * @version 6.0
 */
@SuppressWarnings("serial")
@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.so.m30.entity.SaleOrderHVO")
public class SaleOrderVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta meta =
        BillMetaFactory.getInstance().getBillMeta("so.so_saleorder");
    return meta;
  }

  @Override
  public SaleOrderHVO getParentVO() {
    return (SaleOrderHVO) super.getParentVO();
  }

  public void setParentVO(SaleOrderHVO headVO) {
    this.setParent(headVO);
  }

  @Override
  public SaleOrderBVO[] getChildrenVO() {
    return (SaleOrderBVO[]) super.getChildrenVO();
  }

  public void setChildrenVO(SaleOrderBVO[] bodyVO) {
    super.setChildrenVO(bodyVO);
  }

}
