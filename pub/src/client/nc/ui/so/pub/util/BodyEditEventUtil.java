package nc.ui.so.pub.util;

import java.util.List;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.ui.scmpub.ref.FilterPriceTypeRefUtils;

public class BodyEditEventUtil {

  private static BodyEditEventUtil util = new BodyEditEventUtil();

  private BodyEditEventUtil() {
    // 单例模式
  }

  public static BodyEditEventUtil getInstance() {
    return BodyEditEventUtil.util;
  }

  public int[] getAfterEditRow(CardBodyAfterEditEvent e) {

    // 在编辑后进行批拖拽处理
    int[] rows = null;
    if (CardBodyAfterEditEvent.BATCHCOPYEND == e.getAfterEditEventState()) {
      List<Integer> listrow = e.getAfterEditIndexList();
      rows = new int[listrow.size()];
      for (int i = 0, iloop = listrow.size(); i < iloop; i++) {
        rows[i] = listrow.get(i).intValue();
      }
    }
    else if (CardBodyAfterEditEvent.NOTBATCHCOPY == e.getAfterEditEventState()) {
      rows = new int[] {
        e.getRow()
      };
    }
    return rows;
  }

  /**
   * 根据价目表过滤价格项参照
   * 
   * 仅支持价目表和价格项都在表体的情况
   * 
   * @param e 表体编辑前事件
   * @param tariffdef 价目表pk
   * @param pk_pricetype 价格项pk
   */
  public void filterPricetype(CardBodyBeforeEditEvent e, String tariffdef,
      String pricetype) {
    // 根据价目表过滤价格项
    CardPanelValueUtils util = new CardPanelValueUtils(e.getBillCardPanel());
    String tariff = util.getBodyStringValue(e.getRow(), tariffdef);
    if (tariff != null) {
      UIRefPane refPane =
          (UIRefPane) util.getBodyItem(pricetype).getComponent();
      FilterPriceTypeRefUtils utils = new FilterPriceTypeRefUtils(refPane);
      utils.filterByTariffDefID(tariff);
    }
  }

}
