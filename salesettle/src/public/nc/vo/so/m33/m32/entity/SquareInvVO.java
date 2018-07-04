package nc.vo.so.m33.m32.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.so.m33.m32.entity.SquareInvHVO")
public class SquareInvVO extends AbstractBill {

  private static final long serialVersionUID = -7432015419974031851L;

  public SquareInvViewVO[] changeToSquareInvViewVO() {
    int len = this.getChildrenVO().length;
    SquareInvViewVO[] svvos = new SquareInvViewVO[len];
    SquareInvViewVO sviewvo = null;
    for (int i = 0; i < len; i++) {
      sviewvo = new SquareInvViewVO();
      sviewvo.setHead(this.getParentVO());
      sviewvo.setItem(this.getChildrenVO()[i]);
      svvos[i] = sviewvo;
    }
    return svvos;
  }

  @Override
  public SquareInvBVO[] getChildrenVO() {
    return (SquareInvBVO[]) super.getChildrenVO();
  }

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =
        BillMetaFactory.getInstance().getBillMeta("so.SquareInvHVO");
    return billMeta;
  }

  @Override
  public SquareInvHVO getParentVO() {
    return (SquareInvHVO) super.getParentVO();
  }

}
