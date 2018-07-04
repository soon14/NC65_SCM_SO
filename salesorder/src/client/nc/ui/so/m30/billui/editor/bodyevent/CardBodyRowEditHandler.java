package nc.ui.so.m30.billui.editor.bodyevent;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.BodyRowEditType;
import nc.ui.pubapp.uif2app.event.card.CardBodyRowEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class CardBodyRowEditHandler implements
IAppEventHandler<CardBodyRowEditEvent> {

  @Override
  public void handleAppEvent(CardBodyRowEditEvent e) {
    // 获得卡片panel
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    if (BodyRowEditType.ADDLINE == e.getRowEditType()
        || BodyRowEditType.INSERTLINE == e.getRowEditType()
        || BodyRowEditType.COPYLINE == e.getRowEditType()) {
      UFBoolean boffsetflag =
          keyValue.getHeadUFBooleanValue(SaleOrderHVO.BOFFSETFLAG);
      if (null != boffsetflag && boffsetflag.booleanValue()) {
        if (BodyRowEditType.ADDLINE == e.getRowEditType()) {
          ShowStatusBarMsgUtil.showStatusBarMsg(NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0509")/*做过费用冲抵的单据不允许对新增行编辑*/,
              e.getContext());
          e.setReturnValue(true);
        }
        else {
          ExceptionUtils
              .wrappBusinessException(NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0510")/*做过费用冲抵的单据不允许进行此操作，请取消费用冲抵后再操作！*/)/*做过费用冲抵的单据不允许进行此操作，请取消费用冲抵后再操作！*/;
          e.setReturnValue(false);
        }
      }
      else {
        e.setReturnValue(true);
      }
    }
    else {
      e.setReturnValue(true);
    }
  }
}
