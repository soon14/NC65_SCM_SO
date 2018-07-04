package nc.ui.so.m30.billui.editor.bodyevent;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class ItemDisRateEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    String ctrantypeid = keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    if (PubAppTool.isNull(ctrantypeid)) {
      e.setReturnValue(Boolean.FALSE);
    }
    else {
      Boolean isitemedit = Boolean.FALSE;
      IM30TranTypeService m30transrv =
          NCLocator.getInstance().lookup(IM30TranTypeService.class);
      try {
        M30TranTypeVO tranvo = m30transrv.queryTranTypeVO(ctrantypeid);
        if (null != tranvo && null != tranvo.getBmodifydiscount()) {
          isitemedit =
              Boolean.valueOf(tranvo.getBmodifydiscount().booleanValue());
        }
      }
      catch (BusinessException ex) {
        ExceptionUtils.wrappException(ex);
      }
      e.setReturnValue(isitemedit);
    }

  }

}
