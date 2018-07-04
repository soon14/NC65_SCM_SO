package nc.ui.so.m30.billui.rule;

import nc.impl.pubapp.pattern.database.DBTool;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.bd.feature.ffile.entity.AggFFileVO;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.tool.performance.DeepCloneTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 
 * @description
 *              销售订单复制粘贴行后处理特征码规则
 * @scene
 *        销售订单复制粘贴行后
 * @param 无
 * 
 * @since 6.5
 * @version 2015-11-3 上午10:17:45
 * @author zhangby5
 */
public class CmffilePasteRule {

  /**
   * 
   * @param cardPanel
   * @param lastRow 粘贴的最后一行
   */
  public void process(BillCardPanel cardPanel, int lastRow) {
    BillScrollPane bsp = cardPanel.getBodyPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int rowlength = bsp.getTableModel().getPasteLineNumer();
    DeepCloneTool cloneTool = new DeepCloneTool();
    int idcount = 0;
    for (int line = 0; line < rowlength; line++) {
      Object aggffilevo =
          keyValue.getBodyValue(lastRow - line, SOConstant.AGGFFILEVO);
      String cmffileid =
          keyValue.getBodyStringValue(lastRow - line, SaleOrderBVO.CMFFILEID);
      if (aggffilevo == null || PubAppTool.isNull(cmffileid)) {
        continue;
      }
      idcount++;
    }
    String[] ids = new String[idcount];
    if (idcount > 0) {
      DBTool idtool = new DBTool();
      ids = idtool.getOIDs(idcount);
    }

    int coursor = 0;
    for (int line = 0; line < rowlength; line++) {
      Object aggffilevo =
          keyValue.getBodyValue(lastRow - line, SOConstant.AGGFFILEVO);
      if (aggffilevo == null) {
        continue;
      }
      AggFFileVO newAggvo = (AggFFileVO) cloneTool.deepClone(aggffilevo);
      String cmffileid =
          keyValue.getBodyStringValue(lastRow - line, SaleOrderBVO.CMFFILEID);
      if (!PubAppTool.isNull(cmffileid)) {
        newAggvo.getParentVO().setCffileid(ids[coursor++]);
        newAggvo.getParentVO().setVskucode(null);
      }
      keyValue.setBodyValue(lastRow - line, SOConstant.AGGFFILEVO, newAggvo);
    }
  }
}
