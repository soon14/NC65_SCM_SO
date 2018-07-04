package nc.ui.so.m30.billui.editor.headevent;

import nc.ui.bd.ref.model.CustAddressDefaultRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.ReceiveCustDefAddrRule;

/**
 * 收货地址
 * 编辑后要影响收货国家
 * 
 * @since 6.0
 * @version 2012-2-7 下午03:52:49
 * @author 么贵敬
 */
public class HeadReceiveaddrEditHandler {

  /**
   * 
   * 
   * @param e
   */
  public void afterEdit(CardHeadTailAfterEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
    int[] rows = countutil.getMarNotNullRows();
    
    // 根据收货地址设置表体收货地址、收货地点，收货地区 modify by zhangby5 表体收获地址和地点、地区放在一起，少一次循环
    ReceiveCustDefAddrRule defaddrule = new ReceiveCustDefAddrRule(keyValue);
    defaddrule.setCustAddDocByAddr(rows);
  }

  /**
   * 
   * 
   * @param e
   */
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    BillItem item = cardPanel.getHeadItem(SaleOrderHVO.CHRECEIVEADDID);
    UIRefPane uirefpane = (UIRefPane) item.getComponent();
    CustAddressDefaultRefModel model =
        (CustAddressDefaultRefModel) uirefpane.getRefModel();

    // 按照客户和组织过滤
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String pk_org = keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    String customer = keyValue.getHeadStringValue(SaleOrderHVO.CHRECEIVECUSTID);
    model.setPk_org(pk_org);
    model.setPk_customer(customer);

  }
}
