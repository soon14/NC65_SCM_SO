package nc.ui.so.mbuylargess.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.BodyRowEditType;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterRowEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDate;
import nc.vo.scmpub.util.TimeUtils;
import nc.vo.so.mbuylargess.entity.BuyLargessBVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class CardBodyAfterRowEditHandler implements
    IAppEventHandler<CardBodyAfterRowEditEvent> {

  @Override
  public void handleAppEvent(CardBodyAfterRowEditEvent e) {

    int[] rows = e.getRows();
    if (BodyRowEditType.ADDLINE == e.getRowEditType()
        || BodyRowEditType.INSERTLINE == e.getRowEditType()) {
      // »ñµÃ¿¨Æ¬panel
      BillCardPanel cardPanel = e.getBillCardPanel();
      IKeyValue keyValue = new CardKeyValue(cardPanel);

      UFDate busdate = AppUiContext.getInstance().getBusiDate();
      UFDate begindate = busdate.asLocalBegin();
      UFDate enddate = TimeUtils.getEndDate(SOConstant.SYSENDDATE);
      enddate = enddate.asLocalEnd();

      for (int row : rows) {
        keyValue.setBodyValue(row, BuyLargessBVO.FTOPLIMITTYPE, 0);
        keyValue.setBodyValue(row, BuyLargessBVO.DBEGDATE, begindate);
        keyValue.setBodyValue(row, BuyLargessBVO.DENDDATE, enddate);
      }
    }

  }
}
