package nc.ui.so.m30.revise.model;

import nc.ui.scmpub.page.model.SCMBillPageMediator;

/**
 * 销售订单修订分页Mediator
 * 
 * @author zhangby5
 * 
 */
@SuppressWarnings("restriction")
public class SaleOrderRevisePageMediator extends SCMBillPageMediator {

  private boolean needPage = true;

  @Override
  public void init() {
    if (isNeedPage()) {
      super.init();
    }
  }

  public boolean isNeedPage() {
    return needPage;
  }

  public void setNeedPage(boolean needPage) {
    this.needPage = needPage;
  }

}
