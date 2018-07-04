package nc.ui.so.salequotation.handler;

import java.util.Arrays;
import java.util.Map;

import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m4310trantype.entity.M4310TranTypeVO;
import nc.vo.so.m4310trantype.entity.SalequoDataSource;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;

import nc.ui.price.priceform.model.PriceFormModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterMeasdocRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.ui.so.salequotation.handler.body.CustMaterialEditHandler;
import nc.ui.so.salequotation.handler.body.MaterialEditHandler;
import nc.ui.so.salequotation.handler.body.TaxCodeEditHandler;
import nc.ui.so.salequotation.model.SalequoModel;

/**
 * 销售报价单表体编辑前事件
 * 
 * @since 6.1
 * @version 2012-12-21 13:13:38
 * @author liangjm
 */
public class SalequoBodyBeforeEditHandler implements
    IAppEventHandler<CardBodyBeforeEditEvent> {

  private SalequoModel model;

  private boolean beforEditPriceType(CardBodyBeforeEditEvent e) {
    boolean retValue = true;
    Object objpk_tariffdef =
        e.getBillCardPanel().getBodyValueAt(e.getRow(),
            SalequotationBVO.PK_TARIFFDEF);
    if (objpk_tariffdef == null
        || StringUtil.isEmptyWithTrim((String) objpk_tariffdef)) {
      retValue = false;
    }
    else {
      e.getBillCardPanel().getBodyItem(SalequotationBVO.PK_TARIFFDEF)
          .setEdit(true);
      BodyEditEventUtil.getInstance().filterPricetype(e,
          SalequotationBVO.PK_TARIFFDEF, SalequotationBVO.PK_PRICETYPE);
    }
    return retValue;

  }

  private boolean beforeEditAstunit(CardBodyBeforeEditEvent e) {
    return this.beforeEditUnit(e, SalequotationBVO.CASTUNITID);
  }

  private boolean beforeEditChangeRate(CardBodyBeforeEditEvent e) {
    boolean retValue = true;
    String pk_unit =
        (String) e.getBillCardPanel().getBodyValueAt(e.getRow(),
            SalequotationBVO.PK_UNIT);
    String castunitid =
        (String) e.getBillCardPanel().getBodyValueAt(e.getRow(),
            SalequotationBVO.CASTUNITID);
    String pk_material_v =
        (String) e.getBillCardPanel().getBodyValueAt(e.getRow(),
            SalequotationBVO.PK_MATERIAL_V);
    BillItem changerateItem =
        e.getBillCardPanel().getBodyItem(SalequotationBVO.NCHANGERATE);
    if (changerateItem != null) {
      if (pk_unit != null && castunitid != null && pk_unit.equals(castunitid)) {
        retValue = false;
      }
      boolean isFixRate =
          MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(
              pk_material_v, pk_unit, castunitid);
      retValue = !isFixRate;
    }
    return retValue;
  }

  private boolean beforeEditDiscount(CardBodyBeforeEditEvent e) {
    boolean retValue = true;
    BillItem item = e.getBillCardPanel().getBodyItem(e.getKey());
    if (item == null) {
      return retValue;
    }
    String vtrantype =
        (String) e.getBillCardPanel().getHeadItem(SalequotationHVO.VTRANTYPE)
            .getValueObject();
    if (!StringUtil.isEmptyWithTrim(vtrantype)) {
      Map<String, M4310TranTypeVO> m4310TranTypeBuffer =
          this.getModel().getM4310TranTypeBuffer();
      M4310TranTypeVO trantypeVO = m4310TranTypeBuffer.get(vtrantype);
      if (trantypeVO != null) {
        boolean bdiscounteditable =
            trantypeVO.getBdiscounteditable().booleanValue();
        retValue = bdiscounteditable;
      }
    }
    return retValue;
  }

  private boolean beforeEditPrice(CardBodyBeforeEditEvent e) {
    BillItem item = e.getBillCardPanel().getBodyItem(e.getKey());
    IKeyValue keyvalue = new CardKeyValue(e.getBillCardPanel());
    boolean editFlag = true;
    if (item != null) {
      String vtrantype =
          (String) e.getBillCardPanel().getHeadItem(SalequotationHVO.VTRANTYPE)
              .getValueObject();
      if (StringUtil.isEmptyWithTrim(vtrantype)) {
        return false;
      }
      Map<String, M4310TranTypeVO> m4310TranTypeBuffer =
          this.getModel().getM4310TranTypeBuffer();
      M4310TranTypeVO trantypeVO = m4310TranTypeBuffer.get(vtrantype);
      if (trantypeVO == null) {
        return false;
      }
      // 不取价或历史报价
      if (SalequoDataSource.HISTORY_PRICE.value().equals(
          trantypeVO.getFsourceflag())
          || SalequoDataSource.NO_PRICE.value().equals(
              trantypeVO.getFsourceflag())) {
        return true;
      }
      // 价格管理
      String priceitem =
          keyvalue
              .getBodyStringValue(e.getRow(), SalequotationBVO.VPRICEDETAIL);
      if (!PubAppTool.isNull(priceitem)) {
        // 询到价可改
        UFBoolean bsuccqteditable = trantypeVO.getBsuccqteditable();
        editFlag = bsuccqteditable.booleanValue();
      }
      else if (PubAppTool.isNull(priceitem)) {
        // 未询到价可改
        UFBoolean bfailqteditable = trantypeVO.getBfailqteditable();
        editFlag = bfailqteditable.booleanValue();
      }
    }

    return editFlag;
  }

  private void beforeEditPriceDetail(CardBodyBeforeEditEvent e, BillModel bm) {
    BillItem vpricedetailItem =
        e.getBillCardPanel().getBodyItem(SalequotationBVO.VPRICEDETAIL);
    if (vpricedetailItem != null) {
      UIRefPane pane = (UIRefPane) vpricedetailItem.getComponent();
      PriceFormModel priceFormModel = (PriceFormModel) pane.getRefModel();
      priceFormModel.setPk_org(e.getContext().getPk_org());
      priceFormModel.setPk_priceform((String) bm.getValueAt(e.getRow(),
          e.getKey()));
    }
  }

  private boolean beforeEditQtchangerate(CardBodyBeforeEditEvent e) {
    boolean retValue = true;
    String pk_unit =
        (String) e.getBillCardPanel().getBodyValueAt(e.getRow(),
            SalequotationBVO.PK_UNIT);
    String cqtunitid =
        (String) e.getBillCardPanel().getBodyValueAt(e.getRow(),
            SalequotationBVO.CQTUNITID);
    String pk_material_v =
        (String) e.getBillCardPanel().getBodyValueAt(e.getRow(),
            SalequotationBVO.PK_MATERIAL_V);
    BillItem qtChangerateItem =
        e.getBillCardPanel().getBodyItem(SalequotationBVO.NQTCHANGERATE);
    if (qtChangerateItem != null) {
      if (pk_unit != null && cqtunitid != null && pk_unit.equals(cqtunitid)) {
        retValue = false;
      }
      boolean isFixRate =
          MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(
              pk_material_v, pk_unit, cqtunitid);
      retValue = !isFixRate;
    }
    return retValue;
  }

  private boolean beforeEditQtunitid(CardBodyBeforeEditEvent e) {
    return this.beforeEditUnit(e, SalequotationBVO.CQTUNITID);
  }

  private boolean beforeEditUnit(CardBodyBeforeEditEvent e,
      String measdocKeyName) {
    String pk_material_v =
        (String) e.getBillCardPanel().getBodyValueAt(e.getRow(),
            SalequotationBVO.PK_MATERIAL_V);
    if (StringUtil.isEmptyWithTrim(pk_material_v)) {
      return false;
    }
    BillItem unitItem = e.getBillCardPanel().getBodyItem(measdocKeyName);
    new FilterMeasdocRefUtils(unitItem).setMaterialPk(pk_material_v);
    return true;
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
  public void handleAppEvent(CardBodyBeforeEditEvent e) {
    boolean retValue = true;
    BillModel bm = e.getBillCardPanel().getBillModel();
    if (SalequotationBVO.VPRICEDETAIL.equals(e.getKey())) {
      this.beforeEditPriceDetail(e, bm);

    }
    if (SalequotationBVO.PK_MATERIAL_V.equals(e.getKey())) {
      new MaterialEditHandler().beforeEdit(e);
    }// 客户物料码(V63新加)
    if (SalequotationBVO.CCUSTMATERIALID.equals(e.getKey())) {
      CustMaterialEditHandler cusrmatedit = new CustMaterialEditHandler();
      cusrmatedit.beforeEdit(e);
    }
    if (SalequotationBVO.PK_TARIFFDEF.equals(e.getKey())) {
      retValue = false;
    }
    if (SalequotationBVO.PK_PRICETYPE.equals(e.getKey())) {
      retValue = this.beforEditPriceType(e);
    }
    if (SalequotationBVO.CASTUNITID.equals(e.getKey())) {
      retValue = this.beforeEditAstunit(e);
    }
    if (SalequotationBVO.NCHANGERATE.equals(e.getKey())) {
      retValue = this.beforeEditChangeRate(e);
    }
    if (SalequotationBVO.CQTUNITID.equals(e.getKey())) {
      this.beforeEditQtunitid(e);
    }
    if (SalequotationBVO.NQTCHANGERATE.equals(e.getKey())) {
      retValue = this.beforeEditQtchangerate(e);
    }
    if (SalequotationBVO.NDISCOUNTRATE.equals(e.getKey())
        || SalequotationBVO.NITEMDISCOUNTRATE.equals(e.getKey())) {
      retValue = this.beforeEditDiscount(e);
    }
    if (SalequotationBVO.CTAXCODEID.equals(e.getKey())) {
      TaxCodeEditHandler editor = new TaxCodeEditHandler();
      editor.beforeEdit(e);
    }
    String[] priceKeys =
        new String[] {
          SalequotationBVO.NQTORIGPRICE, SalequotationBVO.NQTORIGTAXPRICE,
          SalequotationBVO.NQTORIGNETPRICE, SalequotationBVO.NQTORIGTAXNETPRC,
          SalequotationBVO.NORIGPRICE, SalequotationBVO.NORIGTAXPRICE,
          SalequotationBVO.NORIGNETPRICE, SalequotationBVO.NORIGTAXNETPRICE,
          SalequotationBVO.NORIGTAXMNY, SalequotationBVO.NORIGMNY,
        };
    if (Arrays.asList(priceKeys).contains(e.getKey())) {
      retValue = this.beforeEditPrice(e);
    }

    e.setReturnValue(Boolean.valueOf(retValue));

  }

  /**
   * 
   * 
   * @param model
   */
  public void setModel(SalequoModel model) {
    this.model = model;
  }

}
