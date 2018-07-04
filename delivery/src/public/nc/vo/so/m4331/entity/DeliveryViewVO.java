package nc.vo.so.m4331.entity;

import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

public class DeliveryViewVO extends AbstractDataView {

  /**
   * 
   */
  private static final long serialVersionUID = 959689656644555165L;

  public DeliveryVO changeToBill() {
    DeliveryVO bill = new DeliveryVO();
    bill.setParent(this.getHead());
    DeliveryBVO[] items = new DeliveryBVO[] {
      this.getItem()
    };
    bill.setChildrenVO(items);
    return bill;
  }

  public DeliveryHVO getHead() {
    return (DeliveryHVO) this.getVO(DeliveryHVO.class);
  }

  public DeliveryBVO getItem() {
    return (DeliveryBVO) this.getVO(DeliveryBVO.class);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getBillViewMeta(DeliveryVO.class);
  }

  public void setHead(DeliveryHVO head) {
    this.setVO(head);
  }

  public void setItem(DeliveryBVO item) {
    this.setVO(item);
  }
}
