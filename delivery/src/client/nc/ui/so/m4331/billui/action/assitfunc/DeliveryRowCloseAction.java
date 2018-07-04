package nc.ui.so.m4331.billui.action.assitfunc;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m4331.IDeliveryAssitFunc;
import nc.ui.pubapp.uif2app.actions.RefreshSingleAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m4331.billui.model.DeliveryManageModel;
import nc.ui.so.m4331.billui.view.DeliveryEditor;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.enumeration.BillStatus;

public class DeliveryRowCloseAction extends NCAction {

  private static final long serialVersionUID = 5047874789849821754L;

  private DeliveryEditor editor;

  private DeliveryManageModel model;

  private RefreshSingleAction refreshAction;

  public DeliveryRowCloseAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_OUTCLOSE);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (this.editor.getBillCardPanel().getBillTable().getSelectedRow() == -1) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0",
              "04006002-0005")/*@res "请先选中行"*/;
      ExceptionUtils.wrappBusinessException(message);
    }

    int[] rows =
        this.editor.getBillCardPanel().getBillTable().getSelectedRows();
    DeliveryVO bill = (DeliveryVO) this.model.getSelectedData();

    DeliveryVO[] ret = null;
    IDeliveryAssitFunc service =
        NCLocator.getInstance().lookup(IDeliveryAssitFunc.class);
    try {
      ret = service.closeDeliveryRows(bill, rows);
      // 后台变化VO与前台合并
      new ClientBillCombinServer<DeliveryVO>().combine(new DeliveryVO[] {
        bill
      }, ret);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    this.model.directlyUpdate(new DeliveryVO[] {
      bill
    });
    this.refreshAction.doAction(e);
  }

  public DeliveryEditor getEditor() {
    return this.editor;
  }

  public DeliveryManageModel getModel() {
    return this.model;
  }

  public RefreshSingleAction getreFreshAction() {
    return this.refreshAction;
  }

  public void setEditor(DeliveryEditor editor) {
    this.editor = editor;
  }

  public void setModel(DeliveryManageModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  public void setRefreshAction(RefreshSingleAction refreshAction1) {
    this.refreshAction = refreshAction1;
  }

  @Override
  protected boolean isActionEnable() {
    int selectRow =
        this.editor.getBillCardPanel().getBillTable().getSelectedRow();
    if ((this.model.getSelectedData() == null)
        || (this.model.getUiState() == UIState.EDIT) || (selectRow == -1)) {
      return false;
    }
    DeliveryVO selectedData = (DeliveryVO) this.getModel().getSelectedData();
    Integer fstatusflag = selectedData.getParentVO().getFstatusflag();
    if (BillStatus.AUDIT.equalsValue(fstatusflag)) {
      int[] rows =
          this.editor.getBillCardPanel().getBillTable().getSelectedRows();
      UFBoolean flag = null;
      if (rows.length == 1) {
        DeliveryBVO bvo =
            (DeliveryBVO) this.editor.getBillCardPanel().getBillModel()
                .getBodyValueRowVO(rows[0], DeliveryBVO.class.getName());
        flag = bvo.getBoutendflag();
      }
      boolean expr1 = rows.length > 1;
      boolean expr2 = (flag != null) && !flag.booleanValue();
      boolean expr = expr1 || expr2;
      return expr;
    }
    return false;
  }
}
