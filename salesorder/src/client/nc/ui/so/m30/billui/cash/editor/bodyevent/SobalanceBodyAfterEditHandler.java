package nc.ui.so.m30.billui.cash.editor.bodyevent;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m30.billui.cash.model.CashSaleSobalanceModel;
import nc.ui.so.m30.billui.cash.view.CashSaleTopPanel;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;

public class SobalanceBodyAfterEditHandler implements
    IAppEventHandler<CardBodyAfterEditEvent> {
  private CashSaleTopPanel topPanel;

  private CashSaleSobalanceModel sobalanceModel;

  @Override
  public void handleAppEvent(CardBodyAfterEditEvent e) {
    String editKey = e.getKey();
    // 本次收款核销金额
    if (SoBalanceBVO.NORIGTHISBALMNY.equals(editKey)) {
      ThisBalMnyEditHandler handler = new ThisBalMnyEditHandler();
      handler.setTopPanel(this.topPanel);
      handler.setSobalanceModel(this.sobalanceModel);
      handler.afterEdit(e);
    }
  }

  public CashSaleTopPanel getTopPanel() {
    return this.topPanel;
  }

  public void setTopPanel(CashSaleTopPanel topPanel) {
    this.topPanel = topPanel;
  }

  public CashSaleSobalanceModel getSobalanceModel() {
    return this.sobalanceModel;
  }

  public void setSobalanceModel(CashSaleSobalanceModel sobalanceModel) {
    this.sobalanceModel = sobalanceModel;
  }

}
