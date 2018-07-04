package nc.ui.so.m30.billui.rule;

import java.util.HashSet;
import java.util.Set;

import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.so.pub.keyvalue.CardKeyValue;

public class MatchBindLargessRule {

  private BillCardPanel cardPanel;

  private IKeyValue keyValue;

  private M30TranTypeVO trantypevo;
  
  public MatchBindLargessRule(BillCardPanel cardPanel, M30TranTypeVO trantypevo) {
    this.cardPanel = cardPanel;
    this.keyValue = new CardKeyValue(cardPanel);
    this.trantypevo = trantypevo;
  }

  public void matchBindLargess(int[] editrows) {

    // 1.缓存编辑表体行号
    Set<String> setEditRowNos = new HashSet<String>();

    for (int row : editrows) {
      setEditRowNos.add(this.keyValue.getBodyStringValue(row,
          SaleOrderBVO.CROWNO));
    }
    // 2.匹配捆绑
    MatchBindRule bindmatch = new MatchBindRule(this.cardPanel);
    bindmatch.matchBind(editrows);
    // 3.计算原始编辑行
    int j = 0;
    for (int i = 0; i < this.keyValue.getBodyCount(); i++) {
      if (setEditRowNos.contains(this.keyValue.getBodyStringValue(i,
          SaleOrderBVO.CROWNO))) {
        if (j >= editrows.length) {
          ExceptionUtils.wrappBusinessException(NCLangRes.getInstance()
              .getStrByID("4006011_0", "04006011-0472")/*销售订单表体行号不能重复。*/);
        }
        editrows[j] = i;
        j++;
      }
    }

    // 4.匹配买赠
    MatchLargessRule matchlarrule = new MatchLargessRule(this.cardPanel, trantypevo);
    matchlarrule.matchLargess(editrows);

  }
}
