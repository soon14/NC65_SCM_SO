package nc.vo.so.custmatrel.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.so.custmatrel.entity.CustMatRelHVO")
public class CustMatRelVO extends AbstractBill {
  /**
   * 
   */
  public static final String ENTITYNAME = "so.so_custmatrel";

  private static final long serialVersionUID = 6508373186237587912L;

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =
        BillMetaFactory.getInstance().getBillMeta(CustMatRelVO.ENTITYNAME);
    return billMeta;
  }

  @Override
  public CustMatRelHVO getParentVO() {
    return (CustMatRelHVO) super.getParentVO();
  }

  public void setParentVO(CustMatRelHVO headVO) {
    this.setParent(headVO);
  }

  @Override
  public CustMatRelBVO[] getChildrenVO() {
    return (CustMatRelBVO[]) super.getChildrenVO();
  }

  public void setChildrenVO(CustMatRelBVO[] bodyVO) {
    super.setChildrenVO(bodyVO);
  }
}
