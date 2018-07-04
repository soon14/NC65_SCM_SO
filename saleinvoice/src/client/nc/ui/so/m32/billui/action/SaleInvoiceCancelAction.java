package nc.ui.so.m32.billui.action;

import java.awt.event.ActionEvent;

import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.ui.pubapp.uif2app.actions.CancelAction;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;

/**
 * 销售发票取消按钮
 * 
 * @since 6.3
 * @version 2012-12-21 上午11:17:21
 * @author yaogj
 */
public class SaleInvoiceCancelAction extends CancelAction {

  /**
   * 
   */
  private static final long serialVersionUID = -9105317849207882831L;

  @Override
  public void doAction(ActionEvent e) {
    try {
      super.doAction(e);
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }

    SaleInvoiceEditor edtitor = (SaleInvoiceEditor) this.getEditor();
    edtitor.setTempvo(null);
  }
}
