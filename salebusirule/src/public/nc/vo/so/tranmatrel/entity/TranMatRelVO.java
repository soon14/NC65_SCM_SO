package nc.vo.so.tranmatrel.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.so.tranmatrel.entity.TranMatRelHVO")
public class TranMatRelVO extends AbstractBill {

  /**
   * 
   */
  public static final String ENTITYNAME = "so.so_tranmatrel";

  private static final long serialVersionUID = -5373079436058169839L;

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =
        BillMetaFactory.getInstance().getBillMeta(TranMatRelVO.ENTITYNAME);
    return billMeta;
  }

  @Override
  public TranMatRelHVO getParentVO() {
    return (TranMatRelHVO) super.getParentVO();
  }

  public void setParentVO(TranMatRelHVO headVO) {
    this.setParent(headVO);
  }

  @Override
  public TranMatRelBVO[] getChildrenVO() {
    return (TranMatRelBVO[]) super.getChildrenVO();
  }

  public void setChildrenVO(TranMatRelBVO[] bodyVO) {
    super.setChildrenVO(bodyVO);
  }
}
