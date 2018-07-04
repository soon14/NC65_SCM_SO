package nc.ui.so.m30.billui.editor.headevent;

import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.ReceiveCustDefAddrRule;

/**
 * 收货客户编辑事件处理
 * 
 * @since 6.0
 * @version 2012-2-7 下午03:52:49
 * @author 么贵敬
 */
public class HeadReceiveCustEditHandler {

  public void afterEdit(CardHeadTailAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    
    // 设置表头与表体默认收货地址
    // 表体非空行
    BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
    int[] rows = countutil.getMarNotNullRows();
    String custid = (String) e.getValue();
    String pk_org = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
    String[] defadds = CustomerPubService.getDefaultAddresses(new String[] {
      custid
    }, pk_org);
    
    String defaddValue = null;
    if (null != defadds && defadds.length > 0) {
      defaddValue = defadds[0];
    }    
    keyValue.setHeadValue(SaleOrderHVO.CHRECEIVEADDID, defaddValue);
    int length = keyValue.getBodyCount();
    for (int row : rows) {
      keyValue.setBodyValue(row, SaleOrderBVO.CRECEIVECUSTID, e.getValue());
      keyValue.setBodyValue(row, SaleOrderBVO.CRECEIVEADDRID, defaddValue);
    }
    
    // 设置表体默认收货地点，收货地区
    ReceiveCustDefAddrRule defaddrule = new ReceiveCustDefAddrRule(keyValue);
    defaddrule.setCustDefaultAddress(rows);
  }
}
