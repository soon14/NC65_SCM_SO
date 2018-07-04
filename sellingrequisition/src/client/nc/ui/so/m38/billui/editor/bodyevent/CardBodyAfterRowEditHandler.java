package nc.ui.so.m38.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.BodyRowEditType;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterRowEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.rule.HeadTotalCalculateRule;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class CardBodyAfterRowEditHandler implements
    IAppEventHandler<CardBodyAfterRowEditEvent> {

  @Override
  public void handleAppEvent(CardBodyAfterRowEditEvent e) {

    int[] rows = e.getRows();
    BodyRowEditType edittype = e.getRowEditType();
    if (BodyRowEditType.ADDLINE == edittype
        || BodyRowEditType.INSERTLINE == edittype) {
      // 获得卡片panel
      BillCardPanel cardPanel = e.getBillCardPanel();
      IKeyValue keyValue = new CardKeyValue(cardPanel);

      // 主组织
      String pk_org = keyValue.getHeadStringValue(PreOrderHVO.PK_ORG);

      String pk_group = keyValue.getHeadStringValue(PreOrderHVO.PK_GROUP);

      // 币种
      String origcurrency =
          keyValue.getHeadStringValue(PreOrderHVO.CORIGCURRENCYID);

      UFDate billdate = keyValue.getHeadUFDateValue(PreOrderHVO.DBILLDATE);
      // 整单折扣
      UFDouble discount =
          keyValue.getHeadUFDoubleValue(PreOrderHVO.NDISCOUNTRATE);

      // 计划发货日期、要求收货日期
      UFDate busidate = AppUiContext.getInstance().getBusiDate();
      busidate = busidate.asLocalEnd();

      for (int row : rows) {
        keyValue.setBodyValue(row, PreOrderBVO.PK_ORG, pk_org);
        keyValue.setBodyValue(row, PreOrderBVO.PK_GROUP, pk_group);
        keyValue.setBodyValue(row, PreOrderBVO.CORIGCURRENCYID, origcurrency);
        keyValue.setBodyValue(row, PreOrderBVO.DBILLDATE, billdate);
        keyValue.setBodyValue(row, PreOrderBVO.NDISCOUNTRATE, discount);
        keyValue.setBodyValue(row, PreOrderBVO.NITEMDISCOUNTRATE,
            SOConstant.ONEHUNDRED);

        keyValue.setBodyValue(row, PreOrderBVO.DSENDDATE, busidate);
        keyValue.setBodyValue(row, PreOrderBVO.DRECEIVEDATE, busidate);

      }
    }
    else if (BodyRowEditType.DELLINE == edittype
        || BodyRowEditType.PASTELINE == edittype
        || BodyRowEditType.PASTELINETOTAIL == edittype) {
      // 获得卡片panel
      BillCardPanel cardPanel = e.getBillCardPanel();
      IKeyValue keyValue = new CardKeyValue(cardPanel);
      HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
      totalrule.calculateHeadTotal();
    }

  }
}
