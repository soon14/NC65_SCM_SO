package nc.ui.so.m32.billui.action.link;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.credit.billcreditquery.IBillCreditQueryMessage;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;
import nc.ui.uif2.NCAction;
import nc.vo.credit.billcreditquery.para.BillQueryPara;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

public class QueryCustCreditAction extends NCAction {

  /**
     * 
     */
  private static final long serialVersionUID = -7841185707254140620L;

  private BillManageModel model;

  private SaleInvoiceEditor editor;

  public SaleInvoiceEditor getEditor() {
    return this.editor;
  }

  public void setEditor(SaleInvoiceEditor editor) {
    this.editor = editor;
  }

  public QueryCustCreditAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!SysInitGroupQuery.isCREDITEnabled()) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0450")/*请启用信用管理模块*/);
    }
    SaleInvoiceVO voInvoice = (SaleInvoiceVO) this.getModel().getSelectedData();
    AppUiState uistate = this.getModel().getAppUiState();
    if (uistate == AppUiState.EDIT || uistate == AppUiState.ADD
        || uistate == AppUiState.COPY_ADD
        || uistate == AppUiState.TRANSFERBILL_ADD) {
      voInvoice = (SaleInvoiceVO) this.getEditor().getValue();
    }

    SaleInvoiceHVO voHead = voInvoice.getParentVO();
    SaleInvoiceBVO[] voBodys = voInvoice.getChildrenVO();

    // 准备过滤客户为空的行，封装进ArrayList<BillQueryPara>
    List<BillQueryPara> paraList = new ArrayList<BillQueryPara>();
    BillQueryPara bqpS = null;
    for (SaleInvoiceBVO bvo : voBodys) {
      if (null != bvo.getCordercustid()) {
        bqpS = new BillQueryPara();
        bqpS.setCcustomerid(bvo.getCordercustid());
        bqpS.setCemployeeid(bvo.getCemployeeid());
        bqpS.setCfinanceorgid(voHead.getPk_org());
        bqpS.setCinvtoryid(bvo.getCmaterialid());
        bqpS.setCprodlineid(bvo.getCprodlineid());
        bqpS.setCsaledeptid(bvo.getCdeptid());
        bqpS.setCsaleorgid(bvo.getCsaleorgid());
        bqpS.setVtrantypecode(bvo.getVfirsttrantype());
        bqpS.setCchanneltypeid(bvo.getCchanneltypeid());
        paraList.add(bqpS);
      }
    }
    // 按照过滤后的行封装BillQueryPara
    BillQueryPara[] bqpSArray = new BillQueryPara[paraList.size()];
    paraList.toArray(bqpSArray);

    // 调用接口
    IBillCreditQueryMessage service =
        NCUILocator.getInstance().lookup(IBillCreditQueryMessage.class,
            NCModule.CREDIT.getName());
    service.showMessage(WorkbenchEnvironment.getInstance().getWorkbench()
        .getParent(), SOBillType.Invoice.getCode(), bqpSArray);

  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    boolean isEnable = false;
    AppUiState uistate = this.getModel().getAppUiState();
    if (uistate == AppUiState.EDIT || uistate == AppUiState.ADD
        || uistate == AppUiState.COPY_ADD
        || uistate == AppUiState.TRANSFERBILL_ADD) {
      return true;
    }
    SaleInvoiceVO voInvoice = (SaleInvoiceVO) this.getModel().getSelectedData();
    if (null != voInvoice && null != voInvoice.getChildrenVO()
        && voInvoice.getChildrenVO().length > 0) {
      isEnable = true;
    }
    return isEnable;
  }

  private void initializeAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_CREDITQUERY);
  }

}
