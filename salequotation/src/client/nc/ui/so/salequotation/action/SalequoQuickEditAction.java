package nc.ui.so.salequotation.action;

import java.awt.event.ActionEvent;
import java.util.Map;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.salequotation.model.QuickPriceData;
import nc.ui.so.salequotation.model.SalequoModel;
import nc.ui.so.salequotation.pub.SalequoCalculator;
import nc.ui.so.salequotation.view.QuickEditPriceDialog;
import nc.ui.so.salequotation.view.SalequoBillForm;
import nc.ui.uif2.NCAction;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m4310trantype.entity.M4310TranTypeVO;
import nc.vo.so.m4310trantype.entity.SalequoDataSource;
import nc.vo.so.pub.enumeration.PriceDiscountType;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class SalequoQuickEditAction extends NCAction {

  /**
   *
   */
  private static final long serialVersionUID = -4710914018634247176L;

  private SalequoBillForm editor;

  private QuickEditPriceDialog quickDL;

  public SalequoQuickEditAction() {
    super();
    SCMActionInitializer.initializeAction(this,
        SCMActionCode.SO_QUICKEIDTPPRICE);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    SalequoModel model = (SalequoModel) this.editor.getModel();
    String tranType =
        (String) this.editor.getBillCardPanel()
            .getHeadItem(SalequotationHVO.VTRANTYPE).getValueObject();
    if (PubAppTool.isNull(tranType)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0077")/* @res "报价单类型不为空的时候，才可快速改价" */);
    }
    M4310TranTypeVO tranTypeVO = model.getM4310TranTypeBuffer().get(tranType);
    if (null == tranTypeVO
        || !SalequoDataSource.HISTORY_PRICE.equalsValue(tranTypeVO
            .getFsourceflag())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006009_0", "04006009-0076")/*
                                                                  * @res
                                                                  * "报价数据来源为历史报价的时候，才可快速改价"
                                                                  */);
      return;
    }
    String pk_currtype =
        (String) this.editor.getBillCardPanel()
            .getHeadItem(SalequotationHVO.PK_CURRTYPE).getValueObject();

    QuickEditPriceDialog quickDL1 = this.getQuickDL();
    if (quickDL1.showModal() == UIDialog.ID_OK) {
      this.quickEditPrice(quickDL1.getValue(), pk_currtype);
    }
  }

  public SalequoBillForm getEditor() {
    return this.editor;
  }

  public void setEditor(SalequoBillForm editor) {
    this.editor = editor;
  }

  private QuickEditPriceDialog getQuickDL() {
    if (this.quickDL == null) {
      this.quickDL =
          new QuickEditPriceDialog(null, NCLangRes.getInstance().getStrByID(
              "4006009_0", "04006009-0046")/* 快速改价 */, true);
    }
    return this.quickDL;
  }

  private SalequoCalculator getSalequoCalculator(BillCardPanel cardPanel) {
    SalequoCalculator calculator = new SalequoCalculator(cardPanel);
    Map<String, M4310TranTypeVO> m4310TranTypeBuffer =
        ((SalequoModel) this.getEditor().getModel()).getM4310TranTypeBuffer();
    String trantype =
        (String) cardPanel.getHeadItem(SalequotationHVO.VTRANTYPE)
            .getValueObject();
    if (StringUtil.isEmptyWithTrim(trantype)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0008")/* @res "交易类型不能为空" */);
    }
    M4310TranTypeVO tranTypeVO = m4310TranTypeBuffer.get(trantype);
    // 设置调单价方式
    boolean isChgPriceOrDiscount = false;
    String modifymny = tranTypeVO.getFmodifymny();
    if (PriceDiscountType.PRICETYPE.getStringValue().equals(modifymny)) {
      isChgPriceOrDiscount = true;
    }
    calculator.setIsChgPriceOrDiscount(isChgPriceOrDiscount);
    return calculator;
  }

  /**
   * 快速改价
   * 
   * @param value
   */
  private void quickEditPrice(QuickPriceData value, String pk_currtype) {
    BillCardPanel cardPanel = this.editor.getBillCardPanel();
    SalequoCalculator calculator = this.getSalequoCalculator(cardPanel);

    for (int row = 0; row < cardPanel.getRowCount(); row++) {
      String pk_group = this.editor.getModel().getContext().getPk_group();
      // 含税优先
      if (SOSysParaInitUtil.getSO23(pk_group).booleanValue()) {
        // 含税净价
        this.updatePrice(value, row, SalequotationBVO.NQTORIGTAXNETPRC,
            pk_currtype);
        calculator.calculate(new int[] {
          row
        }, SalequotationBVO.NQTORIGTAXNETPRC);
      }
      else {
        // 无税净价
        this.updatePrice(value, row, SalequotationBVO.NQTORIGNETPRICE,
            pk_currtype);
        calculator.calculate(new int[] {
          row
        }, SalequotationBVO.NQTORIGNETPRICE);
      }
    }
  }

  private void updatePrice(QuickPriceData value, int row, String priceItemName,
      String pk_currtype) {
    ScaleUtils scale = UIScaleUtils.getScaleUtils();
    BillCardPanel cardPanel = this.editor.getBillCardPanel();
    BillModel billModel = cardPanel.getBillModel();
    UFDouble orgPrice = (UFDouble) billModel.getValueAt(row, priceItemName);
    if (orgPrice == null) {
      orgPrice = new UFDouble(0);
    }
    // 处理精度
    UFDouble oldprice =
        orgPrice.multiply(value.getFactorValue()).add(value.getAddValue());
    UFDouble newprice = scale.adjustSoPuPriceScale(oldprice, pk_currtype);
    billModel.setValueAt(newprice, row, priceItemName);
  }
}
