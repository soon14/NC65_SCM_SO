package nc.ui.so.m38.billui.editor.bodyevent;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m38trantype.IM38TranTypeService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38trantype.entity.M38TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class ItemDisRateEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    String ctrantypeid = keyValue.getHeadStringValue(PreOrderHVO.CTRANTYPEID);
    if (PubAppTool.isNull(ctrantypeid)) {
      e.setReturnValue(false);
    }
    else {
      UFBoolean isitemedit = UFBoolean.FALSE;
      IM38TranTypeService m30transrv =
          NCLocator.getInstance().lookup(IM38TranTypeService.class);
      try {
        M38TranTypeVO tranvo = m30transrv.queryTranTypeVO(ctrantypeid);
        if (null == tranvo || null == tranvo.getBmodifydiscount()) {
          isitemedit = UFBoolean.FALSE;
        }
        else {
          isitemedit = tranvo.getBmodifydiscount();
        }
      }
      catch (BusinessException ex) {
        ExceptionUtils.wrappException(ex);
      }
      e.setReturnValue(isitemedit.booleanValue());
    }

  }

}
