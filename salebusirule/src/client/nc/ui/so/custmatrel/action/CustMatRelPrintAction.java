/**
 *
 */
package nc.ui.so.custmatrel.action;

import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.BillScrollPane.BillTable;
import nc.vo.ml.NCLangRes4VoTransl;

import nc.ui.pubapp.uif2app.actions.PrintDirectAction;

/**
 * 客户与物料关系直接打印，去掉合计行并增加打印标题
 * 
 * @since 6.0
 * @version 2011-11-11 上午09:32:40
 * @modifier 王天文
 */
public class CustMatRelPrintAction extends PrintDirectAction {
  
  private static final long serialVersionUID = -4473489445762830336L;

  @Override
  protected void processTitle() {
    String title_new =
        NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_2",
            "2400600701-0002")/* 客户与物料关系*/;
    this.setTitle(title_new);
    super.processTitle();
  }
  
  @Override
  protected void processBody() throws Exception {
    BillTable[] billTables = this.getBodyTables();
    if(billTables != null && billTables.length > 0) {
      BillModel billModel = (BillModel) billTables[0].getModel();
      billModel.setNeedCalculate(false);
    }
    super.processBody();
  } 
}