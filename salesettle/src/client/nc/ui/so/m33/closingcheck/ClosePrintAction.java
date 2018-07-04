package nc.ui.so.m33.closingcheck;

import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.uif2app.actions.PrintDirectAction;

/**
 * @description
 * 
 * @scene
 * 
 * @param
 * 
 * 
 * @since 6.36
 * @version 2015-6-15 上午9:35:10
 * @author 刘俊杰
 */

public class ClosePrintAction extends PrintDirectAction {
  
  public ClosePrintAction() {
    super();
    this.setTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006010_0", "04006010-0153")/*@res 未结算销售出库单*/);
  }

  /**
   * 
   */
  private static final long serialVersionUID = -8602469533582977665L;

 /* @Override
  protected void processHead() {

    this.setTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006010_0", "04006010-0153")@res 未结算销售出库单);
  }
*/
}
