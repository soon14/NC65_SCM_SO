package nc.vo.so.depmatrel.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.so.depmatrel.entity.DepMatRelHVO")
public class DepMatRelVO extends AbstractBill {

  /**
   * 
   */
  public static final String ENTITYNAME = "so.so_depmatrel";

  private static final long serialVersionUID = 1835954460207747998L;

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =
        BillMetaFactory.getInstance().getBillMeta(DepMatRelVO.ENTITYNAME);
    return billMeta;
  }

  @Override
  public DepMatRelHVO getParentVO() {
    return (DepMatRelHVO) super.getParentVO();
  }

  public void setParentVO(DepMatRelHVO headVO) {
    this.setParent(headVO);
  }

  @Override
  public DepMatRelBVO[] getChildrenVO() {
    return (DepMatRelBVO[]) super.getChildrenVO();
  }

  public void setChildrenVO(DepMatRelBVO[] bodyVO) {
    super.setChildrenVO(bodyVO);
  }
}
