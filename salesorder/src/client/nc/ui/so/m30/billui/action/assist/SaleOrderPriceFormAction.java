package nc.ui.so.m30.billui.action.assist;

import java.awt.event.ActionEvent;

import nc.itf.price.priceform.IPriceFormDialog;
import nc.itf.scmpub.reference.uap.para.SysParaInitQuery;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.billui.view.SaleOrderBillListView;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.price.pub.entity.FindPriceResultVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.res.ParameterList;

/**
 * 价格组成按钮
 * 
 * @version 6.0
 * @author 刘志伟
 * @time 2010-9-2 下午08:00:29
 */
public class SaleOrderPriceFormAction extends NCAction {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private SaleOrderBillForm editor;

  private SaleOrderBillListView listview;

  private AbstractAppModel model;

  public SaleOrderPriceFormAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_PRICEFORM);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {

    BillCardPanel cardPanel = this.editor.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    String pk_org = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);

    int[] rows = this.getSelectRows();
    // 没有选择行提示，选择多行只按第一行处理
    if (rows.length == 0 || PubAppTool.isNull(pk_org)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0011")/*@res "必须选中表体行"*/);
    }

    String priceFormID = this.getPriceFormID();
    if (PubAppTool.isNull(priceFormID)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0012")/*@res "当前行没有价格组成信息"*/);
    }
    IPriceFormDialog dialog =
        NCUILocator.getInstance()
            .lookup(IPriceFormDialog.class, NCModule.PRICE);
    FindPriceResultVO[] resultVOs = new FindPriceResultVO[1];
    boolean bEditFlag = false;
    String trantypecode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    M30TranTypeVO m30trantypevo = null;
    if (!PubAppTool.isNull(trantypecode)) {
      String pk_group = AppContext.getInstance().getPkGroup();
      m30trantypevo =
          this.editor.getM30ClientContext()
              .getTransType(trantypecode, pk_group);
    }
    if ((this.getModel().getUiState() == AppUiState.ADD.getUiState() || this
        .getModel().getUiState() == AppUiState.EDIT.getUiState())
        && null != m30trantypevo) {
      UFBoolean bmodigy = m30trantypevo.getBmodifyaskedqt();
      if (null != bmodigy) {
        bEditFlag = bmodigy.booleanValue();
      }
    }
    boolean bSO22 =
        SysParaInitQuery.getParaBoolean(pk_org, ParameterList.SO22.getCode())
            .booleanValue();
    String pk_currtype =
        keyValue.getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID);
    if (dialog.showModal(cardPanel, bSO22, bEditFlag, priceFormID, pk_currtype) == UIDialog.ID_OK) {
      resultVOs[0] = dialog.getFindPriceResultVO();
      // 卡片上设值并计算
      if (null != resultVOs[0]) {
        SaleOrderFindPriceConfig config =
            new SaleOrderFindPriceConfig(cardPanel, m30trantypevo);
        FindSalePrice findPrice = new FindSalePrice(cardPanel, config);
        findPrice.setResultAfterPriceFormEdit(rows[0], resultVOs[0]);
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
    piceFormID = (String) billmodel.getValueAt(row, SaleOrderBVO.CPRICEFORMID);
    return piceFormID;
  }

  public SaleOrderBillForm getEditor() {
    return this.editor;
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setEditor(SaleOrderBillForm editor) {
    this.editor = editor;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
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

  public SaleOrderBillListView getListview() {
    return listview;
  }

  public void setListview(SaleOrderBillListView listview) {
    this.listview = listview;
  }

}
