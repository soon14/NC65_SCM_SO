package nc.ui.so.m30.billui.action.link;

import java.awt.event.ActionEvent;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.credit.billcreditquery.IBillCreditQueryMessage;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.NCAction;
import nc.vo.credit.billcreditquery.para.BillQueryPara;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.trade.checkrule.VOChecker;

public class SaleOrderCreditQueryAction extends NCAction {

  private static final long serialVersionUID = 1L;

  private SaleOrderBillForm editor;

  private BillManageModel model;

  public SaleOrderCreditQueryAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_CREDITQUERY);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!SysInitGroupQuery.isCREDITEnabled()) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0450")/*请启用信用管理模块*/);
    }
    Object obj = this.getModel().getSelectedData();

    AppUiState uistate = this.getModel().getAppUiState();
    if (uistate == AppUiState.EDIT || uistate == AppUiState.ADD
        || uistate == AppUiState.COPY_ADD) {
      obj = this.getEditor().getValue();
    }

    IKeyValue keyValue = new CardKeyValue(this.getEditor().getBillCardPanel());
    int len = keyValue.getBodyCount();
    SaleOrderVO vo = null;
    SaleOrderHVO head = null;
    SaleOrderBVO[] bodys = new SaleOrderBVO[len];
    if (VOChecker.isEmpty(obj)) {
      head = new SaleOrderHVO();
      head.setCchanneltypeid(keyValue
          .getHeadStringValue(SaleOrderHVO.CCHANNELTYPEID));
      head.setCcustomerid(keyValue.getHeadStringValue(SaleOrderHVO.CCUSTOMERID));
      head.setCemployeeid(keyValue.getHeadStringValue(SaleOrderHVO.CEMPLOYEEID));
      head.setCdeptid(keyValue.getHeadStringValue(SaleOrderHVO.CDEPTID));
      head.setPk_org(keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG));
      head.setCtrantypeid(keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID));
      if (len > 0) {
        for (int i = 0; i < len; i++) {
          bodys[i] = new SaleOrderBVO();
          bodys[i].setCsettleorgid(keyValue.getBodyStringValue(i,
              SaleOrderBVO.CSETTLEORGID));
          bodys[i].setCmaterialid(keyValue.getBodyStringValue(i,
              SaleOrderBVO.CMATERIALID));
          bodys[i].setCprodlineid(keyValue.getBodyStringValue(i,
              SaleOrderBVO.CPRODLINEID));
        }
      }
    }
    else {
      vo = (SaleOrderVO) obj;
      head = vo.getParentVO();
      bodys = vo.getChildrenVO();
    }

    int i = 0;
    BillQueryPara[] bqpS = new BillQueryPara[1];
    if (bodys.length == 0) {
      bqpS[i] = new BillQueryPara();
      // 渠道类型
      bqpS[i].setCchanneltypeid(head.getCchanneltypeid());
      // 客户
      bqpS[i].setCcustomerid(head.getCcustomerid());
      // 销售业务员
      bqpS[i].setCemployeeid(head.getCemployeeid());
      // 销售部门
      bqpS[i].setCsaledeptid(head.getCdeptid());
      // 销售组织
      bqpS[i].setCsaleorgid(head.getPk_org());
      // 订单类型
      bqpS[i].setVtrantypecode(head.getCtrantypeid());
    }
    else {
      // 数据封装为BillQueryPara[]
      bqpS = new BillQueryPara[bodys.length];
      for (SaleOrderBVO body : bodys) {
        bqpS[i] = new BillQueryPara();
        // 财务组织
        bqpS[i].setCfinanceorgid(body.getCsettleorgid());
        // 物料
        bqpS[i].setCinvtoryid(body.getCmaterialid());
        // 产品线
        bqpS[i].setCprodlineid(body.getCprodlineid());
        // 渠道类型
        bqpS[i].setCchanneltypeid(head.getCchanneltypeid());
        // 客户
        bqpS[i].setCcustomerid(head.getCcustomerid());
        // 销售业务员
        bqpS[i].setCemployeeid(head.getCemployeeid());
        // 销售部门
        bqpS[i].setCsaledeptid(head.getCdeptid());
        // 销售组织
        bqpS[i].setCsaleorgid(head.getPk_org());
        // 订单类型
        bqpS[i].setVtrantypecode(head.getCtrantypeid());
        i++;
      }
    }

    try {
      // 调用接口
      IBillCreditQueryMessage service =
          NCUILocator.getInstance().lookup(IBillCreditQueryMessage.class,
              NCModule.CREDIT);
      // 参数为：Container,billType，BillQueryPara[]
      service.showMessage(WorkbenchEnvironment.getInstance().getWorkbench()
          .getParent(), "30", bqpS);
    }
    catch (Exception e1) {
      ExceptionUtils.wrappException(e1);
    }

  }

  public SaleOrderBillForm getEditor() {
    return this.editor;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setEditor(SaleOrderBillForm editor) {
    this.editor = editor;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    AppUiState uistate = this.getModel().getAppUiState();
    boolean broenable =
        uistate == AppUiState.NOT_EDIT && this.model.getSelectedData() != null;

    return broenable || uistate == AppUiState.EDIT || uistate == AppUiState.ADD
        || uistate == AppUiState.COPY_ADD
        || uistate == AppUiState.TRANSFERBILL_ADD;

  }

}
