package nc.vo.so.m33.m4453.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.so.m33.m4453.entity.SquareWasHVO")
public class SquareWasVO extends AbstractBill {

  private static final long serialVersionUID = -7432015419974031851L;

  public SquareWasViewVO[] changeToSquareWasViewVO() {
    int len = this.getChildrenVO().length;
    SquareWasViewVO[] svvos = new SquareWasViewVO[len];
    SquareWasViewVO sviewvo = null;
    for (int i = 0; i < len; i++) {
      sviewvo = new SquareWasViewVO();
      sviewvo.setHead(this.getParentVO());
      sviewvo.setItem(this.getChildrenVO()[i]);
      svvos[i] = sviewvo;
    }
    return svvos;
  }

  @Override
  public SquareWasBVO[] getChildrenVO() {
    return (SquareWasBVO[]) super.getChildrenVO();
  }

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =
        BillMetaFactory.getInstance().getBillMeta("so.SquareWasHVO");
    return billMeta;
  }

  @Override
  public SquareWasHVO getParentVO() {
    return (SquareWasHVO) super.getParentVO();
  }

}
