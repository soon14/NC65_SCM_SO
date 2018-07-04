package nc.ui.so.m30.billui.rule;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pubapp.pattern.log.Log;

/**
 * 
 * @since 6.0
 * @version 2010-11-22 下午12:26:57
 * @author 苏建文
 */

public class BillCancelLargessApportion {
  public void process(BillCardPanel cardPanel) {
    // TODO 整单取消赠品价格分摊
    Log.info(NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0258", null, new String[]{String.valueOf(cardPanel.getRowCount())})/*整单取消赠品价格分摊{0}*/);
  }

}
