package nc.ui.so.m32.billui.action;

import nc.ui.pubapp.pub.common.context.PFlowContext;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.ui.so.m32.billui.pub.SaleInvoiceCombin;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.paravo.CombinCacheVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 销售发票取消审核处理
 * 处理显示汇总状态下的审核
 * 
 * @since 6.0
 * @version 2011-6-28 上午10:14:32
 * @author 么贵敬
 */
public class SaleInvoiceUnApproveAction extends UNApproveScriptAction {

  /** Version */
  private static final long serialVersionUID = 2518373553278631967L;

  private SaleInvoiceVO[] oldcombinvo;

  @Override
  protected void beforeCheck(Object vo) {
    super.beforeCheck(vo);

    // -------------最好能在后台做
    // this.validate(vo);
  }

  @Override
  protected void fillUpContext(PFlowContext context) {
    super.fillUpContext(context);
    // 处理发票合并显示
    this.processCombinShow();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction#isActionEnable()
   */
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
      String approver = selectedData.getParentVO().getApprover();
      if (null == selectedRows) {
        isEnable =
            BillStatus.AUDIT.equalsValue(billstatus)
                || BillStatus.AUDITING.equalsValue(billstatus)
                    || BillStatus.NOPASS.equalsValue(billstatus)
                        && null != approver;
      }
      else {
        isEnable =
            selectedRows.length > 1 || BillStatus.AUDIT.equalsValue(billstatus)
                || BillStatus.AUDITING.equalsValue(billstatus)
                && null != approver;
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
