package nc.ui.so.salequotation.handler.body;

import nc.vo.jcom.lang.StringUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.pub.keyvalue.IKeyRela;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOCustMaterialInfoRule;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;
import nc.vo.so.salequotation.keyrela.SalequoKeyRela;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.util.RefMoreSelectedUtils;
import nc.ui.scmpub.ref.FilterMaterialRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.salequotation.rule.BodyDefaultRule;
import nc.ui.so.salequotation.rule.SaleQuotationUnitChangeRateRule;
import nc.ui.so.salequotation.rule.SaleQuotationUnitDefaultRule;

/**
 * 
 * @since 6.1
 * @version 2012-12-20 16:29:20
 * @author liangjm
 */
public class MaterialEditHandler {

  /**
   * 物料 编辑后
   * 
   * @param e
   */
  public void afterEdit(CardBodyAfterEditEvent e) {
    // --参照多选处理
    RefMoreSelectedUtils utils = new RefMoreSelectedUtils(e.getBillCardPanel());
    int[] rows = utils.refMoreSelected(e.getRow(), e.getKey(), true);

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    IKeyRela keyRela = new SalequoKeyRela();

    // --1.通过物料id获得物料默认单位,
    SaleQuotationUnitDefaultRule unitdef = new SaleQuotationUnitDefaultRule();
    unitdef.setDefaultSaleUnit(keyValue, rows);

    // 2.设置收货国、发货国、报税国信息
    SOCountryInfoRule conutry = new SOCountryInfoRule(keyValue);
    conutry.setCountryInfoByPk_Org(rows);
    // 3.设置购销类型、三角贸易
    SOBuysellTriaRule buysellTria = new SOBuysellTriaRule(keyValue);
    buysellTria.setBuysellAndTriaFlag(rows);
    // 4.询税
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue, keyRela);
    taxInfo.setTaxInfoByBodyPos(rows);

    // 5.设置并计算换算率
    SaleQuotationUnitChangeRateRule unitrate =
        new SaleQuotationUnitChangeRateRule(keyValue);
    for (int row : rows) {
      unitrate.calcAstChangeRate(row);
      unitrate.calcQtChangeRate(row);
    }

    // 6.表体默认值设置
    BodyDefaultRule defaultValue = new BodyDefaultRule(keyValue);
    defaultValue.setBodyDefaultRule(rows);

    // 7.编辑物料后，设置客户物料码(V63新加)
    SOCustMaterialInfoRule socustmar = new SOCustMaterialInfoRule(keyValue);
    socustmar.set4310CustMaterial(rows);
  }

  /**
   * 物料 编辑前
   * 
   * @param e
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String vtrantype = keyValue.getHeadStringValue(SalequotationHVO.VTRANTYPE);
    String customer = keyValue.getHeadStringValue(SalequotationHVO.PK_CUSTOMER);
    if (StringUtil.isEmptyWithTrim(vtrantype)) {
      e.setReturnValue(Boolean.FALSE);
      // fengjb 2012.03.05 UE提示规范
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0012")/*@res "请先输入报价单类型。"*/);
    }
    else if (StringUtil.isEmptyWithTrim(customer)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0073")/*@res "请先输入客户。"*/);
    }
    else {
      // 设置物料可以多选
      UIRefPane refPane =
          (UIRefPane) cardPanel.getBodyItem(SalequotationBVO.PK_MATERIAL_V)
              .getComponent();
      refPane.setMultiSelectedEnabled(true);
      // 设置物料约束条件
      BillItem cmaterialvid =
          cardPanel.getBodyItem(SalequotationBVO.PK_MATERIAL_V);
      FilterMaterialRefUtils utils =
          new FilterMaterialRefUtils((UIRefPane) cmaterialvid.getComponent());
      String pk_org = keyValue.getHeadStringValue(SalequotationHVO.PK_ORG);
      utils.filterItemRefByOrg(pk_org);
      e.setReturnValue(Boolean.TRUE);
    }
  }

}
