package nc.ui.so.m30.billui.cash.editor.bodyevent;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m30.billui.cash.model.CashSaleArsubModel;
import nc.ui.so.m30.billui.cash.view.CashSaleTopPanel;
import nc.vo.so.m35.entity.ArsubBVO;

public class ArsubBodyAfterEditHandler implements
    IAppEventHandler<CardBodyAfterEditEvent> {

  private CashSaleTopPanel topPanel;

  private CashSaleArsubModel arsubModel;

  @Override
  public void handleAppEvent(CardBodyAfterEditEvent e) {
    String editKey = e.getKey();
    // ±¾´Î³å¼õ½ð¶î
    if (ArsubBVO.NTHISSUBMNY.equals(editKey)) {
      ThisSubMnyEditHandler handler = new ThisSubMnyEditHandler();
      handler.setTopPanel(this.topPanel);
      handler.setArsubModel(this.arsubModel);
      handler.afterEdit(e);
    }
  }

  public CashSaleTopPanel getTopPanel() {
    return this.topPanel;
  }

  public void setTopPanel(CashSaleTopPanel topPanel) {
    this.topPanel = topPanel;
  }

  public CashSaleArsubModel getArsubModel() {
    return this.arsubModel;
  }

  public void setArsubModel(CashSaleArsubModel arsubModel) {
    this.arsubModel = arsubModel;
  }

}
