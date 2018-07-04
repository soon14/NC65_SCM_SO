package nc.ui.so.m30.billui.editor.headevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m30.rule.PayTermRule;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 收款协议编辑事件
 * 
 * @since 6.36
 * @version 2015-4-23 下午1:55:44
 * @author 刘景
 */
public class PayTermEditHandler {

  public void afterEdit(CardHeadTailAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 设置收款协议上信息
    new PayTermRule(keyValue).setPayTermInfo();
  }

  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
  /*  BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 收款协议来源于展开销售合同不允许编辑
    Set<String> ctidset = new HashSet<String>();
    for (int i = 0; i < keyValue.getBodyCount(); i++) {
      String payterm = keyValue.getBodyStringValue(i, SaleOrderBVO.CCTMANAGEID);
      if (!PubAppTool.isNull(payterm)) {
        ctidset.add(payterm);
      }
    }
    if (ctidset.size() == 0) {
      return;
    }
    Map<String, CtSalePayTermVO[]> ctvoMap =
        new HashMap<String, CtSalePayTermVO[]>();
    if ((SysInitGroupQuery.isCTEnabled())) {
      try {
        ctvoMap =
            NCLocator.getInstance().lookup(IZ3QueryForOrder.class)
                .queryIsShowPayterm(ctidset.toArray(new String[0]));
      }
      catch (BusinessException e1) {
        ExceptionUtils.wrappBusinessException(e1.getMessage());
      }
    }
    if (ctvoMap!=null&& ctvoMap.size()>0) {
      e.setReturnValue(Boolean.FALSE);
    }*/
  }
}
