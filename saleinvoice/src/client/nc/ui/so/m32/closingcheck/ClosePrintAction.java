package nc.ui.so.m32.closingcheck;

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
    this.setTitle(NCLangRes.getInstance().getStrByID("4006008_0",
        "04006008-0161")/*未审批销售发票*/);

  }

  /**
   * 
   */
  private static final long serialVersionUID = -8602469533582977665L;

  /* @Override
   protected void processHead() {

     this.setTitle(NCLangRes.getInstance().getStrByID("4006008_0", "04006008-0161")未审批销售发票);
   }*/

}
