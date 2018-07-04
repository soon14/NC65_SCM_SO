package nc.ui.so.m30.revise.editor.bodyevent;

import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m30.billui.editor.bodyevent.BodyAfterEditHandler;
import nc.vo.so.m30.entity.SaleOrderBVO;

/**
 * 订单修订表体编辑后派发类
 * 
 * @since 6.0
 * @version 2011-8-4 下午06:46:23
 * @author 刘志伟
 */
public class ReviseBodyAfterEditHandler extends BodyAfterEditHandler {

  @Override
  public void handleAppEvent(CardBodyAfterEditEvent e) {
    // 1.修订逻辑
    String editKey = e.getKey();
    // 数量
    if (SaleOrderBVO.NASTNUM.equals(editKey)) {
      ReviseAstNumEditHandler handler = new ReviseAstNumEditHandler();
      handler.afterEdit(e);
    }
    // 主数量
    else if (SaleOrderBVO.NNUM.equals(editKey)) {
      ReviseNumEditHandler handler = new ReviseNumEditHandler();
      handler.afterEdit(e);
    }
    // 报价单位数量
    else if (SaleOrderBVO.NQTUNITNUM.equals(editKey)) {
      ReviseQtUnitNumEditHandler handler = new ReviseQtUnitNumEditHandler();
      handler.afterEdit(e);
    }
    else {
      // 2.父类销售订单逻辑
      super.handleAppEvent(e);
    }
  }
}
