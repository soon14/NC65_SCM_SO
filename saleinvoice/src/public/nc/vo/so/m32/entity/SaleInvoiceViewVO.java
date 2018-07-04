package nc.vo.so.m32.entity;

import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售发票视图VO
 * </ul>
 * 
 * @version 本版本号6.0
 * @author fengjb
 * @time 2010-1-19 上午09:06:47
 */
public class SaleInvoiceViewVO extends AbstractDataView {
  /**
   * 
   */
  private static final long serialVersionUID = 2117249433614968884L;

  public SaleInvoiceVO changeToBill() {
    SaleInvoiceVO bill = new SaleInvoiceVO();
    bill.setParent(this.getHead());
    SaleInvoiceBVO[] items = new SaleInvoiceBVO[] {
      this.getItem()
    };
    bill.setChildrenVO(items);
    return bill;
  }

  public SaleInvoiceHVO getHead() {
    return (SaleInvoiceHVO) this.getVO(SaleInvoiceHVO.class);
  }

  public SaleInvoiceBVO getItem() {
    return (SaleInvoiceBVO) this.getVO(SaleInvoiceBVO.class);
  }

  @Override
  public IDataViewMeta getMetaData() {
    IDataViewMeta viewmeta =
        DataViewMetaFactory.getInstance().getDataViewMeta(
            SaleInvoiceViewMeta.class);
    return viewmeta;
  }

  public void setHead(SaleInvoiceHVO head) {
    this.setVO(head);
  }

  public void setItem(SaleInvoiceBVO item) {
    this.setVO(item);
  }
}
