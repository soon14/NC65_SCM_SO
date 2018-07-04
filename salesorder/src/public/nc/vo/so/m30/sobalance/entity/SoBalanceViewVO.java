package nc.vo.so.m30.sobalance.entity;

import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

public class SoBalanceViewVO extends AbstractDataView {

  /**
   * 
   */
  private static final long serialVersionUID = -3972767352052240309L;

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getBillViewMeta(SoBalanceVO.class);
  }

  public SoBalanceHVO getHead() {
    return (SoBalanceHVO) this.getVO(SoBalanceHVO.class);
  }

  public void setHead(SoBalanceHVO head) {
    this.setVO(head);
  }

  public SoBalanceBVO getBody() {
    return (SoBalanceBVO) this.getVO(SoBalanceBVO.class);
  }

  public void setBody(SoBalanceBVO body) {
    this.setVO(body);
  }

  public SoBalanceVO changeToSoBalanceVO() {
    SoBalanceVO vo = new SoBalanceVO();
    vo.setParent(this.getHead());
    SoBalanceBVO[] bodys = new SoBalanceBVO[] {
      this.getBody()
    };
    vo.setChildrenVO(bodys);
    return vo;
  }
}
