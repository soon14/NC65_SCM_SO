package nc.ui.so.m32.billui.action;

import nc.ui.pubapp.pub.common.context.PFlowContext;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.ui.so.m32.billui.pub.SaleInvoiceCombin;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.paravo.CombinCacheVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 收回按钮类
 * 
 * @since 6.0
 * @version 2011-2-22 上午10:37:57
 * @author 么贵敬
 */
public class SaleInvoiceUnCommitAction extends UnCommitScriptAction {

  /** Version */
  private static final long serialVersionUID = 4549098173080532064L;

  private SaleInvoiceVO[] oldcombinvo;

  @Override
  protected void fillUpContext(PFlowContext context) {
    super.fillUpContext(context);
    // 处理发票合并显示
    this.processCombinShow();
  }

  @Override
  protected boolean isActionEnable() {
    boolean isEnable =
        this.getModel().getAppUiState() == AppUiState.NOT_EDIT
            && null != this.getModel().getSelectedData();

    if (isEnable) {
      Object[] selectedRows = this.getModel().getSelectedOperaDatas();
      SaleInvoiceVO selectedData =
          (SaleInvoiceVO) this.getModel().getSelectedData();
      Integer billstatus = selectedData.getParentVO().getFstatusflag();
      String operator = selectedData.getParentVO().getApprover();
      if (null == selectedRows) {
        isEnable =
            null == operator && BillStatus.AUDITING.equalsValue(billstatus);
      }
      else {
        isEnable =
            selectedRows.length > 1 || null == operator
                && BillStatus.AUDITING.equalsValue(billstatus);
      }
    }
    return isEnable;
  }

  @Override
  protected void processReturnObj(Object[] pretObj) throws Exception {
    SaleInvoiceManageModel invoicemodel =
        (SaleInvoiceManageModel) this.getModel();
    CombinCacheVO cachevo = invoicemodel.getCombinCacheVO();
    // 合并显示
    if (null != cachevo && cachevo.getBcombinflag()) {
      SaleInvoiceVO[] oldconbinvos =
          (SaleInvoiceVO[]) super.getFullOldVOs().clone();
      SaleInvoiceCombin combinuitl = new SaleInvoiceCombin();
      SaleInvoiceVO[] olddetailvos =
          combinuitl.getOldDetailVOs(oldconbinvos, cachevo.getCombinRela());

      SaleInvoiceVO[] newcombinvos =
          combinuitl.getNewCombinUIVOS(cachevo, oldconbinvos, olddetailvos,
              pretObj);
      super.setFullOldVOs(this.oldcombinvo);
      super.processReturnObj(newcombinvos);
    }
    else {
      super.processReturnObj(pretObj);
    }
  }

  private void processCombinShow() {
    SaleInvoiceManageModel invoicemodel =
        (SaleInvoiceManageModel) this.getModel();
    CombinCacheVO cachevo = invoicemodel.getCombinCacheVO();
    // 合并显示
    if (null != cachevo && cachevo.getBcombinflag()) {
      Object[] uiobj = this.model.getSelectedOperaDatas();
      int length = uiobj.length;
      SaleInvoiceVO[] uicomvos = new SaleInvoiceVO[length];
      for (int i = 0; i < length; i++) {
        uicomvos[i] = (SaleInvoiceVO) uiobj[i];
      }
      this.oldcombinvo = uicomvos;
      SaleInvoiceCombin combin = new SaleInvoiceCombin();
      SaleInvoiceVO[] detainvo =
          combin.splitNoEditSaleInvoice(uicomvos, cachevo.getCombinRela());
      this.getFlowContext().setBillVos(detainvo);
    }
  }

}
