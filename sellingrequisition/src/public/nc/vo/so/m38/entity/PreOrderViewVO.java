package nc.vo.so.m38.entity;

import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

public class PreOrderViewVO extends AbstractDataView {

  /**
   * 
   */
  private static final long serialVersionUID = 959689656644555165L;

  public PreOrderVO changeToBill() {
    PreOrderVO bill = new PreOrderVO();
    bill.setParent(this.getHead());
    PreOrderBVO[] items = new PreOrderBVO[] {
      this.getItem()
    };
    bill.setChildrenVO(items);
    return bill;
  }

  public PreOrderHVO getHead() {
    return (PreOrderHVO) this.getVO(PreOrderHVO.class);
  }

  public PreOrderBVO getItem() {
    return (PreOrderBVO) this.getVO(PreOrderBVO.class);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getBillViewMeta(PreOrderVO.class);
  }

  public void setHead(PreOrderHVO head) {
    this.setVO(head);
  }

  public void setItem(PreOrderBVO item) {
    this.setVO(item);
  }
}
