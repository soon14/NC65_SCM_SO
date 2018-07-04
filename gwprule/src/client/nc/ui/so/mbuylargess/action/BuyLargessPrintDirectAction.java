package nc.ui.so.mbuylargess.action;

import nc.vo.ml.NCLangRes4VoTransl;

import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.BillScrollPane.BillTable;
import nc.ui.pubapp.uif2app.actions.PrintDirectAction;

/**
 * 
 * @since 6.3
 * @version 2013-02-21 14:25:03
 * @author Áº¼ªÃ÷
 */
public class BuyLargessPrintDirectAction extends PrintDirectAction {

  private static final long serialVersionUID = 3653528317425925551L;

  @Override
  protected void processTitle() {
    String title_new =
        NCLangRes4VoTransl.getNCLangRes().getStrByID("40060102",
            "1400601020001")/* ÂòÔùÉèÖÃ±í*/;
    this.setTitle(title_new);
    super.processTitle();
  }

  @Override
  protected void processBody() throws Exception {
    BillTable[] billTables = this.getBodyTables();
    if (billTables != null && billTables.length > 0) {
      BillModel billModel = (BillModel) billTables[0].getModel();
      billModel.setNeedCalculate(false);
    }
    super.processBody();
  }

}
