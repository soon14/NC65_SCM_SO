package nc.ui.so.m32.billui.editor.bodyevent;

import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterMaterialRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.pub.SaleInvoiceKeyRela;
import nc.vo.so.m32.rule.SrcBillInfoRule;
import nc.vo.so.m32.rule.UnitChangeRateRule;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>物料编辑事件处理
 * </ul>
 * 
 * <p>
 * 
 * @version 本版本号 6.0
 * @since
 * @author fengjb
 * @time 2010-9-10 上午10:22:44
 */
public class MaterialEditHandler {
  /**
   * 方法功能描述：物料编辑后事件。
   * <p>
   * <b>参数说明</b>
   * 
   * @param e
   *          <p>
   * @author 冯加滨
   * @time 2010-4-26 上午10:50:10
   */
  public void afterEdit(CardBodyAfterEditEvent e) {
    // 编辑后设置业务单位和报价单位为物料默认销售单位
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int row = e.getRow();
    String materialid =
        keyValue.getBodyStringValue(row, SaleInvoiceBVO.CMATERIALVID);
    Map<String, String> mapunit = null;
    mapunit = MaterialPubService.querySaleMeasdocIDByPks(new String[] {
      materialid
    });

    String defaultunit = null;
    if (null != mapunit) {
      defaultunit = mapunit.get(materialid);
    }

    if (VOChecker.isEmpty(defaultunit)) {
      String unitid = keyValue.getBodyStringValue(row, SaleInvoiceBVO.CUNITID);
      keyValue.setBodyValue(row, SaleInvoiceBVO.CASTUNITID, unitid);
      keyValue.setBodyValue(row, SaleInvoiceBVO.CQTUNITID, unitid);
    }
    else {
      keyValue.setBodyValue(row, SaleInvoiceBVO.CASTUNITID, defaultunit);
      keyValue.setBodyValue(row, SaleInvoiceBVO.CQTUNITID, defaultunit);
    }
    // 计算换算率
    UnitChangeRateRule changeraterule = new UnitChangeRateRule(keyValue);
    changeraterule.calcAstChangeRate(row);
    changeraterule.calcQtChangeRate(row);

    SaleInvoiceKeyRela invoicerela = new SaleInvoiceKeyRela();
    // 询税
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue, invoicerela);
    taxInfo.setTaxInfoByHeadPos(new int[] {
      row
    });
  }

  /**
   * 方法功能描述：销售发票物料编辑前事件。
   * <p>
   * <b>参数说明</b>
   * 
   * @param e
   *          <p>
   * @author 冯加滨
   * @time 2010-3-12 下午03:35:46
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    // 是否存在上游单据
    CardKeyValue cardkeyvalue = new CardKeyValue(e.getBillCardPanel());
    SrcBillInfoRule srcbillrule = new SrcBillInfoRule(cardkeyvalue);
    boolean value = srcbillrule.isExitSrc(e.getRow());
    e.setReturnValue(Boolean.valueOf(value));
    
    // 物料只能参照服务/折扣类存货
    BillItem materialitem =
        e.getBillCardPanel().getBodyItem(SaleInvoiceBVO.CMATERIALVID);
    UIRefPane ref = (UIRefPane) materialitem.getComponent();
    FilterMaterialRefUtils filtermaterial = new FilterMaterialRefUtils(ref);
    filtermaterial.filterIsSealedShow(UFBoolean.FALSE);
    filtermaterial.filterRefByFeeOrDiscount(UFBoolean.TRUE, UFBoolean.TRUE);
  }
}
