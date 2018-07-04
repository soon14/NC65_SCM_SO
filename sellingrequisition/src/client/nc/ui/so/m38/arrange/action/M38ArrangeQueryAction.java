package nc.ui.so.m38.arrange.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.billref.push.BillPushConst;
import nc.ui.pubapp.billref.push.PushQueryAction;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.pub.enumeration.FuncodeType;

public class M38ArrangeQueryAction extends PushQueryAction {

  private static final long serialVersionUID = -1674419101919736104L;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    super.doAction(e);
    try {
      this.getBillContext().getMultiModel(BillPushConst.DEST)
          .getModelByBillType(SOBillType.Order.getCode()).initModel(null);
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

  // ¸´Ð´chenylµÄ·½·¨
  @Override
  public boolean isTPAMonitor() {
    // return isTPAMonitor
    // && getTpaProgressUtil().getContext().getEntranceUI() instanceof
    // ToftPanelAdaptor;
    return false;
  }

  @Override
  public String getFunNode() {
    return FuncodeType.PREORDER_ARRANGE;
  }
}
