package nc.ui.so.m30.billui.largessapportion;

import java.util.List;

import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pubapp.pattern.log.Log;

/**
 *
 * @since 6.0
 * @version 2010-12-2 上午08:51:36
 * @author 苏建文
 */

public class ApportionForEmpty implements IApportionAction {

  @Override
  public void apportion(BillCardPanel cardPanel, List<Integer> rowlist,
      boolean istaxprior) {
    Log.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0032")/*@res "赠品价格分摊方式为不分摊。"*/);
  }

}