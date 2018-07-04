package nc.ui.so.m30.billui.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;

/**
 * 
 * @since 6.0
 * @version 2010-11-22 下午12:14:11
 * @author 苏建文
 */

public class BillDelLargessRelation {

  public void process(BillCardPanel cardPanel) {
    // 建立ID-行索引的关系
    Map<String, Integer> idrowmap = this.getIdRowMap(cardPanel);

    // 找出附属的赠品行ID
    List<String> pertainrowids = this.getLargessRowids(cardPanel);

    // 按行索引从大到小删除附属件
    this.deleterows(cardPanel, pertainrowids, idrowmap);
  }

  private Map<String, Integer> getIdRowMap(BillCardPanel cardPanel) {
    Map<String, Integer> idrowmap = new HashMap<String, Integer>();
    CardPanelValueUtils cpvu = new CardPanelValueUtils(cardPanel);
    int rowCount = cardPanel.getBillModel().getRowCount();
    for (int i = 0; i < rowCount; i++) {
      String id = cpvu.getBodyStringValue(i, SaleOrderBVO.CSALEORDERBID);
      if (!PubAppTool.isNull(id)) {
        idrowmap.put(id, Integer.valueOf(i));
      }
    }
    return idrowmap;
  }

  private List<String> getLargessRowids(BillCardPanel cardPanel) {
    CardPanelValueUtils cpvu = new CardPanelValueUtils(cardPanel);
    List<String> rowids = new ArrayList<String>();
    int rowcount = cardPanel.getRowCount();
    for (int i = 0; i < rowcount; i++) {
      String clargesssrcid =
          cpvu.getBodyStringValue(i, SaleOrderBVO.CLARGESSSRCID);
      String csaleorderbid =
          cpvu.getBodyStringValue(i, SaleOrderBVO.CSALEORDERBID);
      if (!PubAppTool.isNull(clargesssrcid)) {
        rowids.add(csaleorderbid);
      }
    }

    return rowids;
  }

  private void deleterows(BillCardPanel cardPanel, List<String> pertainrowids,
      Map<String, Integer> idrowmap) {
    List<Integer> needdelrows = this.needDeleterows(pertainrowids, idrowmap);
    if (needdelrows.size() > 0) {
      int[] delrowindexs = new int[needdelrows.size()];
      for (int i = 0; i < needdelrows.size(); i++) {
        delrowindexs[i] = needdelrows.get(i).intValue();
      }
      cardPanel.getBodyPanel().delLine(delrowindexs);
    }
  }

  private List<Integer> needDeleterows(List<String> pertainrowids,
      Map<String, Integer> idrowmap) {
    List<Integer> needdelrows = new ArrayList<Integer>();
    if (pertainrowids.size() > 0) {
      for (String rowid : pertainrowids) {
        Integer rowindex = idrowmap.get(rowid);
        if (rowindex != null) {
          needdelrows.add(rowindex);
        }
      }
    }
    return needdelrows;
  }

}
