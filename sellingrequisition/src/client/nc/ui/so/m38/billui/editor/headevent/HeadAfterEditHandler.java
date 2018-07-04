package nc.ui.so.m38.billui.editor.headevent;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.so.m38.entity.PreOrderHVO;

/**
 * 预订单表头编辑后事件派发类
 * 
 * @since 6.0
 * @version 2011-6-8 下午02:13:06
 * @author fengjb
 */
public class HeadAfterEditHandler implements
    IAppEventHandler<CardHeadTailAfterEditEvent> {

  @Override
  public void handleAppEvent(CardHeadTailAfterEditEvent e) {
    String editkey = e.getKey();
    // 币种
    if (PreOrderHVO.CORIGCURRENCYID.equals(editkey)) {
      OrigCurrencyEditHandler handler = new OrigCurrencyEditHandler();
      handler.afteEdit(e);
    }
    // 订货日期
    else if (PreOrderHVO.DBILLDATE.equals(editkey)) {
      BillDateEditHandler hanlder = new BillDateEditHandler();
      hanlder.afterEdit(e);
    }
    // 整单折扣
    else if (PreOrderHVO.NDISCOUNTRATE.equals(editkey)) {
      DiscountRateEditHandler handler = new DiscountRateEditHandler();
      handler.afterEdit(e);
    }
    // 客户
    else if (PreOrderHVO.CCUSTOMERID.equals(editkey)) {
      CustomerEditHandler handler = new CustomerEditHandler();
      handler.afterEdit(e);
    }
    // 销售渠道类型
    else if (PreOrderHVO.CCHANNELTYPEID.equals(editkey)) {
      ChannelTypeEditHandler handler = new ChannelTypeEditHandler();
      handler.afterEdit(e);
    }
    // 运输方式
    else if (PreOrderHVO.CTRANSPORTTYPEID.equals(editkey)) {
      TransportTypeEditHandler handler = new TransportTypeEditHandler();
      handler.afterEdit(e);
    }
  }
}
