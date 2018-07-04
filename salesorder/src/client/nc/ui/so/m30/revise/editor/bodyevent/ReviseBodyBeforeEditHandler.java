package nc.ui.so.m30.revise.editor.bodyevent;

import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m30.billui.editor.bodyevent.BodyBeforeEditHandler;
import nc.ui.so.m30.revise.rule.IsEditableCheckRule;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.util.FeatureSelectUtil;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 订单修订表体编辑前派发类
 * 
 * @since 6.0
 * @version 2011-7-10 上午11:56:22
 * @author 刘志伟
 */
public class ReviseBodyBeforeEditHandler extends BodyBeforeEditHandler {

  @Override
  public void handleAppEvent(CardBodyBeforeEditEvent e) {
    // 1.修订逻辑
    // --是否可以编辑
    boolean isEditable =
        new IsEditableCheckRule().check(e.getBillCardPanel(), e.getRow(),
            e.getKey());
    if(e.getKey().equals(SaleOrderBVO.CMFFILEID)){
      IKeyValue keyValue = new CardKeyValue(e.getBillCardPanel());
      if(FeatureSelectUtil.isOut(keyValue, e.getRow())){
        e.setReturnValue(Boolean.FALSE);
      }
    }else{
      if (!isEditable) {
        e.setReturnValue(Boolean.FALSE);
      }
    }

    if (e.getKey().equals(SaleOrderBVO.CSPROFITCENTERVID)) {
      ReviseSProfitCenterHandler handler = new ReviseSProfitCenterHandler();
      handler.beforeEdit(e);
    }

    // 2.父类销售订单逻辑
    super.handleAppEvent(e);
  }

}
