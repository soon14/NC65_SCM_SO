package nc.vo.so.m30.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * SaleOrderExternalVO<br>
 * 单子表聚合VO<br>
 * <b>与销售订单VO结构一致</b>
 * 
 * 
 * @since:2013-05-25 08:39:32
 * 
 * @author dongli2
 * @version 6.3
 */

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.so.m30.entity.SaleOrderExternalHVO")
public class SaleOrderExternalVO extends AbstractBill {

  /**
   * 序列化
   */
  private static final long serialVersionUID = 5427476164524405831L;

  /* SaleOrder主表名称 */
  public static final String SaleOrderTable = "so_saleorder";

  /* SaleOrder子表名称 */
  public static final String SaleOrderTable_B = "so_saleorder_b";

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.pattern.model.entity.bill.IBill#getBillMeta()
   */
  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =
        BillMetaFactory.getInstance().getBillMeta("so.so_saleorder");
    return billMeta;
  }

  /***
   * 获取表头VO
   * 
   * @return表头VO
   */
  public SaleOrderExternalHVO getParentVO() {
    return (SaleOrderExternalHVO) super.getParentVO();
  }

  /**
   * 设置表头VO
   * 
   * @param headVO
   */
  public void setParentVO(SaleOrderExternalHVO headVO) {
    super.setParentVO(headVO);
  }

  /**
   * 获取表体VO
   * 
   * @return表体VO
   */
  public SaleOrderExternalBVO[] getChildrenVO() {
    return (SaleOrderExternalBVO[]) super.getChildrenVO();
  }

  /**
   * 设置表体VO
   * 
   * @param bodyVO
   */
  public void setChildrenVO(SaleOrderExternalBVO[] bodyVO) {
    super.setChildrenVO(bodyVO);
  }

}
