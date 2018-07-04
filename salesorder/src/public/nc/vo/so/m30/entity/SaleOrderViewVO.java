package nc.vo.so.m30.entity;

import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

public class SaleOrderViewVO extends AbstractDataView {

  private static final long serialVersionUID = 5034364940097761927L;

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getBillViewMeta(SaleOrderVO.class);
  }

  public SaleOrderHVO getHead() {
    return (SaleOrderHVO) this.getVO(SaleOrderHVO.class);
  }

  public void setHead(SaleOrderHVO head) {
    this.setVO(head);
  }

  public SaleOrderBVO getBody() {
    return (SaleOrderBVO) this.getVO(SaleOrderBVO.class);
  }

  public void setBody(SaleOrderBVO body) {
    this.setVO(body);
  }

  public SaleOrderVO changeToSaleOrderVO() {
    SaleOrderVO vo = new SaleOrderVO();
    vo.setParent(this.getHead());
    SaleOrderBVO[] bodys = new SaleOrderBVO[] {
      this.getBody()
    };
    vo.setChildrenVO(bodys);
    return vo;
  }
}
