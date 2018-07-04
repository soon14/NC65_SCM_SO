package nc.ui.so.m32.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.BodyRowEditType;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterRowEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.util.HeadTotalCalcUtil;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class CardBodyAfterRowEditHandler implements
IAppEventHandler<CardBodyAfterRowEditEvent> {

  private final UFDouble hundred = new UFDouble(100);

  @Override
  public void handleAppEvent(CardBodyAfterRowEditEvent e) {
    int[] rows = e.getRows();
    // 获得卡片panel1
    BillCardPanel cardPanel = e.getBillCardPanel();

    if (e.getRowEditType() == BodyRowEditType.ADDLINE
        || e.getRowEditType() == BodyRowEditType.INSERTLINE) {
      CardKeyValue cardkeyvalue = new CardKeyValue(cardPanel);
      // 开票组织、
      String pk_org = cardkeyvalue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
      String pk_group =
          cardkeyvalue.getHeadStringValue(SaleInvoiceHVO.PK_GROUP);
      // 单据日期
      UFDate billdate =
          cardkeyvalue.getHeadUFDateValue(SaleInvoiceHVO.DBILLDATE);
      // 发票折扣
      UFDouble hinvoicedisrate =
          cardkeyvalue.getHeadUFDoubleValue(SaleInvoiceHVO.NHVOICEDISRATE);
      for (int row : rows) {
        if (null == cardkeyvalue.getBodyStringValue(rows[0],
            SaleInvoiceBVO.CARORGID)) {
          cardkeyvalue.setBodyValue(row, SaleInvoiceBVO.CARORGID, pk_org);
          cardkeyvalue.setBodyValue(row, SaleInvoiceBVO.CARORGVID,
              cardkeyvalue.getHeadValue(SaleInvoiceHVO.PK_ORG_V));

        }
        cardkeyvalue.setBodyValue(row, SaleInvoiceBVO.PK_ORG, pk_org);
        cardkeyvalue.setBodyValue(row, SaleInvoiceBVO.PK_GROUP, pk_group);

        cardkeyvalue.setBodyValue(row, SaleInvoiceBVO.DBILLDATE, billdate);
        // 整单折扣、单品折扣

        cardkeyvalue.setBodyValue(row, SaleInvoiceBVO.NDISCOUNTRATE,
            this.hundred);
        cardkeyvalue.setBodyValue(row, SaleInvoiceBVO.NITEMDISCOUNTRATE,
            this.hundred);

        cardkeyvalue.setBodyValue(row, SaleInvoiceBVO.NINVOICEDISRATE,
            hinvoicedisrate);
        // 费用冲抵金额
        cardkeyvalue.setBodyValue(row, SaleInvoiceBVO.NORIGSUBMNY,
            UFDouble.ZERO_DBL);
        cardPanel.getBillModel().loadLoadRelationItemValue();
      }
    }
    else if (BodyRowEditType.PASTELINE == e.getRowEditType()
        || BodyRowEditType.PASTELINETOTAIL == e.getRowEditType()) {
      IKeyValue keyValue = new CardKeyValue(cardPanel);
      HeadTotalCalcUtil.getInstance().calcHeadTotalValue(keyValue);
    }
  }
}
