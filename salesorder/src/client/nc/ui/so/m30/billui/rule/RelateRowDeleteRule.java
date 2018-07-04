package nc.ui.so.m30.billui.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class RelateRowDeleteRule {

  private IKeyValue keyValue;

  public RelateRowDeleteRule(
      IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public int[] getRelaDeleteRows(String[] srcbids) {

    Set<String> setsrcid = new HashSet<String>();
    for (String bid : srcbids) {
      // 表体主键为空
      if (PubAppTool.isNull(bid)) {
        continue;
      }
      setsrcid.add(bid);
    }
    if (setsrcid.size() == 0) {
      return new int[0];
    }
    List<Integer> alrelarow = new ArrayList<Integer>();
    int bodycount = this.keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {
      // 买赠来源
      String largesssrcid =
          this.keyValue.getBodyStringValue(i, SaleOrderBVO.CLARGESSSRCID);
      if (!PubAppTool.isNull(largesssrcid) && setsrcid.contains(largesssrcid)) {
        alrelarow.add(Integer.valueOf(i));
      }
      // 捆绑来源
      String bindsrcid =
          this.keyValue.getBodyStringValue(i, SaleOrderBVO.CBINDSRCID);
      if (!PubAppTool.isNull(bindsrcid) && setsrcid.contains(bindsrcid)) {
        alrelarow.add(Integer.valueOf(i));
      }
    }
    int[] relrows = new int[alrelarow.size()];
    int i = 0;
    for (Integer row : alrelarow) {
      relrows[i] = row.intValue();
      i++;
    }
    return relrows;

  }

  /**
   * 返回需要关联删除的行
   * 
   * @param srcrows
   * @return
   */
  public int[] getRelaDeleteRows(int[] srcrows) {

    Set<String> setsrcid = new HashSet<String>();
    for (int row : srcrows) {
      // 表体主键为空
      String bid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CSALEORDERBID);
      if (PubAppTool.isNull(bid)) {
        continue;
      }
      // 买赠来源不为空
      String largesssrcid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CLARGESSSRCID);
      if (!PubAppTool.isNull(largesssrcid)) {
        continue;
      }
      // 捆绑来源不为空
      String bindsrcid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CBINDSRCID);
      if (!PubAppTool.isNull(bindsrcid)) {
        continue;
      }
      setsrcid.add(bid);
    }
    if (setsrcid.size() == 0) {
      return new int[0];
    }
    List<Integer> alrelarow = new ArrayList<Integer>();
    int bodycount = this.keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {
      // 买赠来源
      String largesssrcid =
          this.keyValue.getBodyStringValue(i, SaleOrderBVO.CLARGESSSRCID);
      if (!PubAppTool.isNull(largesssrcid) && setsrcid.contains(largesssrcid)) {
        alrelarow.add(Integer.valueOf(i));
      }
      // 捆绑来源
      String bindsrcid =
          this.keyValue.getBodyStringValue(i, SaleOrderBVO.CBINDSRCID);
      if (!PubAppTool.isNull(bindsrcid) && setsrcid.contains(bindsrcid)) {
        alrelarow.add(Integer.valueOf(i));
      }
    }
    int[] relrows = new int[alrelarow.size()];
    int i = 0;
    for (Integer row : alrelarow) {
      relrows[i] = row.intValue();
      i++;
    }
    return relrows;
  }
}
