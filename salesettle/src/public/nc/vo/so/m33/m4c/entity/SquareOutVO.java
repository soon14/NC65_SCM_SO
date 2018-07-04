package nc.vo.so.m33.m4c.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.so.m33.m4c.entity.SquareOutHVO")
public class SquareOutVO extends AbstractBill {

  private static final long serialVersionUID = -7432015419974031851L;

  public SquareOutViewVO[] changeToSquareOutViewVO() {
    SquareOutViewVO[] svvos = new SquareOutViewVO[this.getChildrenVO().length];
    SquareOutViewVO sviewvo = null;
    int len = this.getChildrenVO().length;
    for (int i = 0; i < len; i++) {
      sviewvo = new SquareOutViewVO();
      sviewvo.setHead(this.getParentVO());
      sviewvo.setItem(this.getChildrenVO()[i]);
      svvos[i] = sviewvo;
    }
    return svvos;
  }

  @Override
  public SquareOutBVO[] getChildrenVO() {
    return (SquareOutBVO[]) super.getChildrenVO();
  }

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =
        BillMetaFactory.getInstance().getBillMeta("so.SquareOutHVO");
    return billMeta;
  }

  @Override
  public SquareOutHVO getParentVO() {
    return (SquareOutHVO) super.getParentVO();
  }

}
