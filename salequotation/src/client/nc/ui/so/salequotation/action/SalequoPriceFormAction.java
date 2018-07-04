package nc.ui.so.salequotation.action;

import java.awt.event.ActionEvent;
import java.util.Map;

import nc.itf.price.priceform.IPriceFormDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.salequotation.findprice.ManagementPriceFinder;
import nc.ui.so.salequotation.model.SalequoModel;
import nc.ui.so.salequotation.view.SalequoBillForm;
import nc.ui.so.salequotation.view.SalequoListView;
import nc.ui.uif2.NCAction;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.price.pub.entity.FindPriceResultVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m4310trantype.entity.M4310TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class SalequoPriceFormAction extends NCAction {

  private static final long serialVersionUID = 1L;

  private SalequoBillForm editor;

  private SalequoModel model;
  
  private SalequoListView listview;

  public SalequoPriceFormAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_PRICEFORM);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    BillCardPanel cardPanel = this.editor.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    String pk_org = keyValue.getHeadStringValue(SalequotationHVO.PK_ORG);
    if (StringUtil.isEmptyWithTrim(pk_org)) {
      return;
    }
    int[] selectedRows = this.getSelectRows();
    if (selectedRows.length != 1) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0088")/*@res "请选择要查看价格组成的表体行，且只能选择一行！"*/);
    }
    String priceDetail = this.getPriceFormID();
    if (StringUtil.isEmptyWithTrim(priceDetail)) {
      // fengjb 2012.03.05 UE提示规范
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0006")/*@res "当前行没有价格组成信息"*/);
    }

    Map<String, M4310TranTypeVO> tranTypeBuffer =
        this.getModel().getM4310TranTypeBuffer();
    String vtrantype = keyValue.getHeadStringValue(SalequotationHVO.VTRANTYPE);
    M4310TranTypeVO m4310trantypevo = tranTypeBuffer.get(vtrantype);
    IPriceFormDialog priceFormDialog =
        NCUILocator.getInstance()
            .lookup(IPriceFormDialog.class, NCModule.PRICE);
    boolean bEditFlag = false;
    if ((this.getModel().getUiState() == AppUiState.ADD.getUiState() || this
        .getModel().getUiState() == AppUiState.EDIT.getUiState())
        && null != m4310trantypevo) {
      UFBoolean bmodigy = m4310trantypevo.getBsuccqteditable();
      if (null != bmodigy) {
        bEditFlag = bmodigy.booleanValue();
      }
    }
    FindPriceResultVO[] resultVOs = new FindPriceResultVO[1];
    boolean isSavePriceFormItem =
        SOSysParaInitUtil.getSO22(pk_org).booleanValue();
    String pk_currtype =
        keyValue.getHeadStringValue(SalequotationHVO.PK_CURRTYPE);
    if (priceFormDialog.showModal(cardPanel, isSavePriceFormItem, bEditFlag,
        priceDetail, pk_currtype) == UIDialog.ID_OK) {
      resultVOs[0] = priceFormDialog.getFindPriceResultVO();
      // 卡片上设值并计算
      if (null != resultVOs[0]) {
        ManagementPriceFinder priceFinder =
            new ManagementPriceFinder(m4310trantypevo);
        int[] rows = new int[1];
        rows[0] = Integer.valueOf(selectedRows[0]);
        priceFinder.setResultAfterPriceFormEdit(cardPanel, rows, resultVOs);
      }
    }
  }
  
  private String getPriceFormID() {
    String piceFormID = null;
    BillModel billmodel = null;
    int row = -1;
    boolean flag = this.editor.isComponentVisible();
    if (!flag) {
      UITable table = this.listview.getBillListPanel().getBodyTable();
      row = table.getSelectedRow();
      billmodel = this.listview.getBillListPanel().getBodyBillModel();
    }
    else {
      row = this.editor.getBillCardPanel().getBillTable().getSelectedRow();
      billmodel = this.editor.getBillCardPanel().getBillModel();
    }
    piceFormID = (String) billmodel.getValueAt(row, SalequotationBVO.VPRICEDETAIL);
    return piceFormID;
  }

  public SalequoBillForm getEditor() {
    return this.editor;
  }

  public void setEditor(SalequoBillForm editor) {
    this.editor = editor;
  }

  public SalequoModel getModel() {
    return this.model;
  }

  public void setModel(SalequoModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    int[] rows = this.getSelectRows();
    if (null == rows || rows.length != 1) {
      return false;
    }
    return true;
  }
  
  private int[] getSelectRows() {
    int[] rows = null;
    if (this.editor.isComponentVisible()) {
      rows = this.editor.getBillCardPanel().getBillTable().getSelectedRows();
    }
    else {
      UITable table = this.listview.getBillListPanel().getBodyTable();
      rows = table.getSelectedRows();
    }
    return rows;
  }

  
  public SalequoListView getListview() {
    return listview;
  }

  
  public void setListview(SalequoListView listview) {
    this.listview = listview;
  }
}
