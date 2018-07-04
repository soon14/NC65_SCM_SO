package nc.vo.so.m30.revise.entity;

import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 销售订单修订VO
 * 
 * @version 6.0
 * @author 刘志伟
 * @time 2010-8-11 下午02:03:24
 */
@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.so.m30.revise.entity.SaleOrderHistoryHVO")
public class SaleOrderHistoryVO extends SaleOrderVO {

  /**
   * 
   */
  private static final long serialVersionUID = -94797137270010951L;

  @Override
  public SaleOrderHistoryBVO[] getChildrenVO() {
    return (SaleOrderHistoryBVO[]) super.getChildrenVO();
  }

  @Override
  public IBillMeta getMetaData() {
    IBillMeta meta =
        BillMetaFactory.getInstance().getBillMeta("so.orderhistory");
    return meta;
  }

  @Override
  public SaleOrderHistoryHVO getParentVO() {
    return (SaleOrderHistoryHVO) super.getParentVO();
  }

  public void setChildrenVO(SaleOrderHistoryBVO[] bodyVO) {
    super.setChildrenVO(bodyVO);
  }

  public void setParentVO(SaleOrderHistoryHVO headVO) {
    this.setParent(headVO);
  }
}
