package nc.ui.so.m30.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.BodyRowEditType;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterRowEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class CardBodyAfterRowEditHandler implements
    IAppEventHandler<CardBodyAfterRowEditEvent> {

  @Override
  public void handleAppEvent(CardBodyAfterRowEditEvent e) {

    int[] rows = e.getRows();
    // 获得卡片panel
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    if (BodyRowEditType.ADDLINE == e.getRowEditType()
        || BodyRowEditType.INSERTLINE == e.getRowEditType()) {

      // 主组织
      String pk_org = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);

      String pk_group = keyValue.getHeadStringValue(SaleOrderHVO.PK_GROUP);

      UFDate billdate = keyValue.getHeadUFDateValue(SaleOrderHVO.DBILLDATE);
      // 整单折扣
      UFDouble discount =
          keyValue.getHeadUFDoubleValue(SaleOrderHVO.NDISCOUNTRATE);

      String custmoer =
          keyValue.getHeadStringValue(SaleOrderHVO.CHRECEIVECUSTID);

      String receiveadd =
          keyValue.getHeadStringValue(SaleOrderHVO.CHRECEIVEADDID);

      for (int row : rows) {
        keyValue.setBodyValue(row, SaleOrderBVO.PK_ORG, pk_org);
        keyValue.setBodyValue(row, SaleOrderBVO.PK_GROUP, pk_group);
        keyValue.setBodyValue(row, SaleOrderBVO.DBILLDATE, billdate);
        keyValue.setBodyValue(row, SaleOrderBVO.NDISCOUNTRATE, discount);
        keyValue.setBodyValue(row, SaleOrderBVO.NITEMDISCOUNTRATE,
            SOConstant.ONEHUNDRED);
        keyValue.setBodyValue(row, SaleOrderBVO.CRECEIVECUSTID, custmoer);
        keyValue.setBodyValue(row, SaleOrderBVO.CRECEIVEADDRID, receiveadd);
      }
      cardPanel.getBillModel().loadLoadRelationItemValue();
      // add by zhangby5 表体收货地址显示PK，只能全部加载一下显示公式
      cardPanel.getBillModel().execLoadFormula();
      
    }
    else if (BodyRowEditType.PASTELINE == e.getRowEditType()) {
      // 计算表体合计信息1
      HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
      totalrule.calculateHeadTotal();
    }
  }
}
