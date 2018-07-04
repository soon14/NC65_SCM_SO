package nc.ui.so.salequotation.handler;

import java.util.Map;

import javax.swing.JComponent;

import nc.vo.jcom.lang.StringUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m4310trantype.entity.M4310TranTypeVO;
import nc.vo.so.pub.enumeration.PriceDiscountType;
import nc.vo.so.pub.util.SOFreeUtil;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.ui.so.salequotation.findprice.AbstractPriceFinder;
import nc.ui.so.salequotation.findprice.PriceFinderCreator;
import nc.ui.so.salequotation.handler.body.CustMaterialEditHandler;
import nc.ui.so.salequotation.handler.body.FreeEditHandler;
import nc.ui.so.salequotation.handler.body.MaterialEditHandler;
import nc.ui.so.salequotation.handler.body.ProductorEditHandler;
import nc.ui.so.salequotation.handler.body.ReceCountryEditHandler;
import nc.ui.so.salequotation.handler.body.SendCountryEditHandler;
import nc.ui.so.salequotation.handler.body.SupplierEditHandler;
import nc.ui.so.salequotation.handler.body.TaxCodeEditHandler;
import nc.ui.so.salequotation.handler.body.TaxCountryEditHandler;
import nc.ui.so.salequotation.model.FindPriceService;
import nc.ui.so.salequotation.model.SalequoModel;
import nc.ui.so.salequotation.pub.SalequoCalculator;

/**
 * 
 * 
 * @since 6.1
 * @version 2012-12-20 11:19:04
 * @author liangjm
 */
public class SalequoBodyAfterEditHandler implements
    IAppEventHandler<CardBodyAfterEditEvent> {

  private SalequoCalculator calculator;

  private FindPriceService findPriceService;

  private SalequoModel model;

  private void editQtunit(CardBodyAfterEditEvent e) {
    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    String[] pk_units = new String[rows.length];
    String[] pk_material_vs = new String[rows.length];
    String[] values = new String[rows.length];
    int i = 0;
    for (int row : rows) {
      pk_units[i] =
          (String) e.getBillCardPanel().getBodyValueAt(row,
              SalequotationBVO.PK_UNIT);
      pk_material_vs[i] =
          (String) e.getBillCardPanel().getBodyValueAt(row,
              SalequotationBVO.PK_MATERIAL_V);
      values[i] = (String) e.getValue();
      i++;
    }
    this.setRate(e.getBillCardPanel(), pk_material_vs, pk_units, values, rows,
        SalequotationBVO.NQTCHANGERATE);
  }

  private void editUnit(CardBodyAfterEditEvent e) {
    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    String[] pk_units = new String[rows.length];
    String[] pk_material_vs = new String[rows.length];
    String[] values = new String[rows.length];
    int i = 0;
    for (int row : rows) {
      // 报价单位自动带入单位值
      e.getBillCardPanel().setBodyValueAt(e.getValue(), row,
          SalequotationBVO.CQTUNITID);
      pk_units[i] =
          (String) e.getBillCardPanel().getBodyValueAt(row,
              SalequotationBVO.PK_UNIT);
      pk_material_vs[i] =
          (String) e.getBillCardPanel().getBodyValueAt(row,
              SalequotationBVO.PK_MATERIAL_V);
      values[i] = (String) e.getValue();
      i++;
    }

    this.setRate(e.getBillCardPanel(), pk_material_vs, pk_units, values, rows,
        SalequotationBVO.NCHANGERATE);
    this.setRate(e.getBillCardPanel(), pk_material_vs, pk_units, values, rows,
        SalequotationBVO.NQTCHANGERATE);
  }

  private void execCalculate(BillCardPanel cardPanel, int[] rows, String itemKey) {

    if (this.calculator == null) {
      this.calculator = new SalequoCalculator(cardPanel);
      Map<String, M4310TranTypeVO> m4310TranTypeBuffer =
          this.getModel().getM4310TranTypeBuffer();
      String trantype =
          (String) cardPanel.getHeadItem(SalequotationHVO.VTRANTYPE)
              .getValueObject();
      if (StringUtil.isEmptyWithTrim(trantype)) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006009_0", "04006009-0008")/*@res "交易类型不能为空"*/);
      }
      M4310TranTypeVO tranTypeVO = m4310TranTypeBuffer.get(trantype);
      // 设置调单价方式
      boolean isChgPriceOrDiscount = false;
      String modifymny = tranTypeVO.getFmodifymny();
      if (PriceDiscountType.PRICETYPE.getStringValue().equals(modifymny)) {
        isChgPriceOrDiscount = true;
      }
      this.calculator.setIsChgPriceOrDiscount(isChgPriceOrDiscount);
    }
    this.calculator.calculate(rows, itemKey);
  }

  private void execCalculate(CardBodyAfterEditEvent e) {
    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    this.execCalculate(e.getBillCardPanel(), rows, e.getKey());
  }

  /**
   * 询价
   * 
   * @param e
   */
  private void findPrice(CardBodyAfterEditEvent e) {
    BillItem billItem = e.getBillCardPanel().getBodyItem(e.getKey());
    JComponent comp = billItem.getComponent();
    int rows = 1;
    if (comp instanceof UIRefPane) {
      UIRefPane refPane = (UIRefPane) comp;
      if (refPane.getRefPKs() != null) {
        rows = refPane.getRefPKs().length;
      }
    }
    Map<String, M4310TranTypeVO> tranTypeBuffer =
        this.getModel().getM4310TranTypeBuffer();
    String vtrantype =
        (String) e.getBillCardPanel().getHeadItem(SalequotationHVO.VTRANTYPE)
            .getValueObject();
    // 报价单类型空时不询价
    if (StringUtil.isEmptyWithTrim(vtrantype)) {
      return;
    }
    M4310TranTypeVO tranTypeVO = tranTypeBuffer.get(vtrantype);
    if (tranTypeVO == null) {
      return;
    }
    AbstractPriceFinder priceFinder =
        PriceFinderCreator.getInstance().createPriceFinder(
            this.getFindPriceService(), tranTypeVO);
    priceFinder.findPrice(e, rows);
  }

  /**
   * 
   * 
   * @return hh
   */
  public FindPriceService getFindPriceService() {
    return this.findPriceService;
  }

  /**
   * 
   * 
   * @return hh
   */
  public SalequoModel getModel() {
    return this.model;
  }

  @Override
  public void handleAppEvent(CardBodyAfterEditEvent e) {

    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    if (rows == null) {
      return;
    }
    String editKey = e.getKey();
    // 物料
    if (SalequotationBVO.PK_MATERIAL_V.equals(editKey)) {
      MaterialEditHandler materialEdit = new MaterialEditHandler();
      materialEdit.afterEdit(e);
    }// 客户物料码(V63新加)
    else if (SalequotationBVO.CCUSTMATERIALID.equals(editKey)) {
      CustMaterialEditHandler cusrmatedit = new CustMaterialEditHandler();
      cusrmatedit.afterEdit(e);
    }// 生产厂商(V63新加)
    else if (SalequotationBVO.PK_PRODUCTOR.equals(editKey)) {
      ProductorEditHandler handler = new ProductorEditHandler();
      handler.afterEdit(e);
    }// 供应商(V63新加)
    else if (SalequotationBVO.PK_SUPPLIER.equals(editKey)) {
      SupplierEditHandler handler = new SupplierEditHandler();
      handler.afterEdit(e);
    }
    // 自由辅助属性(V63新加)
    else if (SOFreeUtil.isFreeKey(editKey)) {
      FreeEditHandler handler = new FreeEditHandler();
      handler.afterEdit(e);
    }
    else if (SalequotationBVO.CASTUNITID.equals(editKey)) {
      this.editUnit(e);
    }
    else if (SalequotationBVO.CQTUNITID.equals(editKey)) {
      this.editQtunit(e);
    }
    else if (SalequotationBVO.PK_PRICETYPE.equals(editKey)) {
      if (e.getValue() == null) {
        // fengjb 2012.03.05 UE提示规范
        e.getBillCardPanel().setBodyValueAt(e.getOldValue(), e.getRow(),
            SalequotationBVO.PK_PRICETYPE);
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006009_0", "04006009-0013")/*@res "价格项不能为空"*/);

      }
    }
    else if (SalequotationBVO.CRECECOUNTRYID.equals(editKey)) {
      ReceCountryEditHandler receCountryEdit = new ReceCountryEditHandler();
      receCountryEdit.afterEdit(e);
    }
    else if (SalequotationBVO.CSENDCOUNTRYID.equals(editKey)) {
      SendCountryEditHandler sendCountryEdit = new SendCountryEditHandler();
      sendCountryEdit.afterEdit(e);
    }
    else if (SalequotationBVO.CTAXCOUNTRYID.equals(editKey)) {
      TaxCountryEditHandler taxCountryEdit = new TaxCountryEditHandler();
      taxCountryEdit.afterEdit(e);
    }
    else if (SalequotationBVO.CTAXCODEID.equals(editKey)) {
      TaxCodeEditHandler taxCodeEdit = new TaxCodeEditHandler();
      taxCodeEdit.afterEdit(e);
    }
    // 单价金额计算
    else if (SalequotationBVO.NASSISTNUM.equals(editKey)) {
      this.execCalculate(e);
    }
    else if (SalequotationBVO.NNUM.equals(editKey)) {
      this.execCalculate(e);
    }
    else if (SalequotationBVO.NQTNUM.equals(editKey)) {
      this.execCalculate(e);
    }
    else if (SalequotationBVO.NCHANGERATE.equals(editKey)) {
      this.execCalculate(e);
    }
    else if (SalequotationBVO.NQTCHANGERATE.equals(editKey)) {
      this.execCalculate(e);
    }
    else if (SalequotationBVO.NTAXRATE.equals(editKey)) {
      this.execCalculate(e);
    }
    else if (SalequotationBVO.NORIGDISCOUNT.equals(editKey)) {
      this.execCalculate(e);
    }
    else if (SalequotationBVO.NORIGTAXMNY.equals(editKey)) {
      this.execCalculate(e);
    }
    else if (SalequotationBVO.NORIGMNY.equals(editKey)) {
      this.execCalculate(e);
    }
    else if (SalequotationBVO.NQTORIGNETPRICE.equals(editKey)) {
      this.execCalculate(e);
    }
    else if (SalequotationBVO.NQTORIGTAXNETPRC.equals(editKey)) {
      this.execCalculate(e);
    }
    else if (SalequotationBVO.NQTORIGPRICE.equals(editKey)) {
      this.execCalculate(e);
    }
    else if (SalequotationBVO.NQTORIGTAXPRICE.equals(editKey)) {
      this.execCalculate(e);
    }
    else if (SalequotationBVO.NORIGNETPRICE.equals(editKey)) {
      this.execCalculate(e);
    }
    else if (SalequotationBVO.NORIGTAXNETPRICE.equals(editKey)) {
      this.execCalculate(e);
    }
    else if (SalequotationBVO.NORIGPRICE.equals(editKey)) {
      this.execCalculate(e);
    }
    else if (SalequotationBVO.NORIGTAXPRICE.equals(editKey)) {
      this.execCalculate(e);
    }
    else if (SalequotationBVO.NITEMDISCOUNTRATE.equals(editKey)) {
      this.execCalculate(e);
    }
    else if (SalequotationBVO.NDISCOUNTRATE.equals(editKey)) {
      this.execCalculate(e);
    }
    else if (SalequotationBVO.FTAXTYPEFLAG.equals(editKey)) {
      this.execCalculate(e);
    }

    // 询价
    this.findPrice(e);
  }

  /**
   * 
   * 
   * @param findPriceService
   */
  public void setFindPriceService(FindPriceService findPriceService) {
    this.findPriceService = findPriceService;
  }

  /**
   * 
   * 
   * @param model
   */
  public void setModel(SalequoModel model) {
    this.model = model;
  }

  private void setRate(BillCardPanel cardPanel, String[] pk_material_vs,
      String[] pk_units, String[] pk_otherUnits, int[] rows, String rateKey) {
    // 自动带入单位与主单位换算率
    String rate = null;
    int i = 0;
    for (int row : rows) {
      if (PubAppTool.isEqual(pk_units[i], pk_otherUnits[i])) {
        rate = "1/1";
      }
      else {
        rate =
            MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(
                pk_material_vs[i], pk_units[i], pk_otherUnits[i]);
      }
      cardPanel.setBodyValueAt(rate, row, rateKey);
      i++;
    }
    this.execCalculate(cardPanel, rows, rateKey);
  }

}
