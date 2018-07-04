package nc.vo.so.salequotation.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;

/**
 * 
 * 单子表/单表头/单表体聚合VO
 * 
 * 创建日期:2009-10-17 11:07:09
 * 
 * @author
 * @version NCPrj ??
 */
@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.so.salequotation.entity.SalequotationHVO")
public class AggSalequotationHVO extends AbstractBill {

  /**
   * 
   */
  private static final long serialVersionUID = 4251481979720904813L;

  @Override
  public SalequotationBVO[] getChildrenVO() {
    return (SalequotationBVO[]) super.getChildrenVO();
  }

  @Override
  public SalequotationMeta getMetaData() {
    return (SalequotationMeta) BillMetaFactory.getInstance().getBillMeta(
        SalequotationMeta.class);
  }

  @Override
  public SalequotationHVO getParentVO() {
    return (SalequotationHVO) super.getParentVO();
  }

}
