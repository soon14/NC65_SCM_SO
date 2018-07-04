package nc.ui.so.m32.billui.action;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.pubapp.pub.exception.IResumeException;
import nc.pubitf.credit.accountcheck.IAccountCheckMessageService;
import nc.pubitf.credit.creditcheck.ICreditCheckMessageService;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.pub.common.context.PFlowContext;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction;
import nc.ui.scmpub.util.ResumeExceptionUIProcessUtils;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.ui.so.m32.billui.pub.SaleInvoiceCombin;
import nc.vo.credit.exception.CreditCheckException;
import nc.vo.credit.exception.OverPeriodDayCheckException;
import nc.vo.credit.exception.OverPeriodInnerDayCheckException;
import nc.vo.credit.exception.OverPeriodMoneyCheckException;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.exp.AtpNotEnoughException;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.paravo.CombinCacheVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 销售发票审批
 * 
 * @since 6.0
 * @version 2011-7-20 上午09:16:57
 * @author 么贵敬
 */
public class SaleInvoiceApproveAction extends ApproveScriptAction {

  /**
     * 
     */
  private static final long serialVersionUID = 6224810222371029731L;

  /**
   * 合并后的VO
   */
  private SaleInvoiceVO[] oldcombinvo;

  @Override
  protected void fillUpContext(PFlowContext context) {
    super.fillUpContext(context);
    // 处理发票合并显示
    this.processCombinShow();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {

    boolean isEnable =
        this.getModel().getAppUiState() == AppUiState.NOT_EDIT
            && null != this.getModel().getSelectedData();

    if (isEnable) {
      // 批选可用
      Object[] selectedRows = this.getModel().getSelectedOperaDatas();
      // 单选时单据状态为自由和审批中的可用
      SaleInvoiceVO selectedData =
          (SaleInvoiceVO) this.getModel().getSelectedData();
      Integer billstatus = selectedData.getParentVO().getFstatusflag();

      isEnable =
          null != selectedRows && selectedRows.length > 1
              || BillStatus.FREE.equalsValue(billstatus)
              || BillStatus.AUDITING.equalsValue(billstatus);

    }
    return isEnable;
  }

  @Override
  protected boolean isResume(IResumeException resumeInfo) {
    return ResumeExceptionUIProcessUtils.isResume(resumeInfo, getFlowContext());
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
      SaleInvoiceVO[] detainvos =
          combin.splitNoEditSaleInvoice(uicomvos, cachevo.getCombinRela());
      this.getFlowContext().setBillVos(detainvos);
    }

  }
}
