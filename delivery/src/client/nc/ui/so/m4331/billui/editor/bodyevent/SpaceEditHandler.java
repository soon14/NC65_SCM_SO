package nc.ui.so.m4331.billui.editor.bodyevent;

import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.ui.scmpub.ref.FilterRackRefUtils;
import nc.vo.bd.stordoc.StordocVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m4331.entity.DeliveryBVO;

/**
 * 货位编辑事件
 * 
 * @since 6.0
 * @version 2011-3-24 下午02:25:27
 * @author 祝会征
 */
public class SpaceEditHandler {
  /**
   * 货位编辑前处理事件
   * 
   * @param e
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    CardPanelValueUtils util = new CardPanelValueUtils(e.getBillCardPanel());
    int iRow = e.getRow();
    BillItem item = util.getBodyItem(DeliveryBVO.CSPACEID);
    String cwarehouseid =
        util.getBodyStringValue(iRow, DeliveryBVO.CSENDSTORDOCID);
    if (null == cwarehouseid) {
      item.setEdit(false);
      return;
    }
    StordocVO[] vos = StordocPubService.queryStordocByPks(new String[] {
      cwarehouseid
    }, new String[] {
      StordocVO.CSFLAG
    });
    UFBoolean flag = vos[0].getCsflag();
    if (null == flag || !flag.booleanValue()) {
      item.setEdit(false);
      return;
    }
    FilterRackRefUtils filter =
        new FilterRackRefUtils((UIRefPane) item.getComponent());
    filter.filterByWarehouse(cwarehouseid);
  }
}
