package nc.ui.so.m30.billui.largessapportion;

import java.util.List;

import nc.ui.pub.bill.BillCardPanel;

/**
 * 赠品价格分摊算法
 * 
 * @since 6.0
 * @version 2010-12-2 上午09:19:38
 * @author 苏建文
 */

public interface IApportionAction {

  void apportion(BillCardPanel cardPanel, List<Integer> rowlist,
      boolean istaxprior);

}
