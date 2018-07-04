package nc.ui.so.m30.billui.editor.headevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterTransTypeRefUtils;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.DirectStoreRule;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30.util.SaleOrderTranTypeUtil;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;

/**
 * 销售订单交易类型编辑事件
 * 
 * @since 6.0
 * @version 2011-6-8 上午10:38:32
 * @author fengjb
 */
public class TranTypeEditHandler {

  private SaleOrderBillForm billform;

  public SaleOrderBillForm getBillform() {
    return this.billform;
  }

  public void setBillform(SaleOrderBillForm billform) {
    this.billform = billform;
  }

  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String pk_org = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);

    BillItem trantypeitem = cardPanel.getHeadTailItem(SaleOrderHVO.CTRANTYPEID);
    FilterTransTypeRefUtils filterutils =
        new FilterTransTypeRefUtils(trantypeitem, pk_org);
    filterutils.filterTranType(new String[] {
      SOBillType.Order.getCode()
    }, null);
  }

  public void afterEdit(CardHeadTailAfterEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 1.缓存交易类型VO
    String trantypecode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    String pk_group = AppUiContext.getInstance().getPkGroup();
    SaleOrderClientContext clientcontex = this.billform.getM30ClientContext();
    M30TranTypeVO newtrantypevo =
        clientcontex.getTransType(trantypecode, pk_group);

    // 2.自动匹配业务流程
    String csaleorgid = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
    String userid = AppUiContext.getInstance().getPkUser();
    String cbiztypeid = null;
    try {
      cbiztypeid =
          PfUtilClient.retBusitypeCanStart(SOBillType.Order.getCode(),
              trantypecode, csaleorgid, userid);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    // 设置业务流程
    keyValue.setHeadValue(SaleOrderHVO.CBIZTYPEID, cbiztypeid);

    // 3.询价
    SaleOrderFindPriceConfig config =
        new SaleOrderFindPriceConfig(cardPanel, newtrantypevo);
    FindSalePrice findprice = new FindSalePrice(cardPanel, config);
    BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
    int[] rows = countutil.getMarNotNullRows();
    findprice.findPriceAfterEdit(rows, SaleOrderHVO.CTRANTYPEID);
    // 4.直运类型判定
    String oldtrantypeid = (String) e.getOldValue();
    String newtrantypeid = (String) e.getValue();

    SaleOrderTranTypeUtil dirutil = new SaleOrderTranTypeUtil();
    if (dirutil.isDirectTypeChg(oldtrantypeid, newtrantypeid)) {
      DirectStoreRule ditrule = new DirectStoreRule(keyValue);
      ditrule.setSendStordoc(rows);
    }
    // 5.赠品兑付 放开表头赠品兑付类型编辑性
    // 1.取交易类型参数
    String tranTypeCode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    if (null != tranTypeCode) {
      SaleOrderClientContext cache = this.billform.getM30ClientContext();
      M30TranTypeVO m30transvo = cache.getTransType(tranTypeCode, pk_group);
      boolean isBlrgcashflag = m30transvo.getBlrgcashflag().booleanValue();
      BillItem headitem = cardPanel.getHeadItem(SaleOrderHVO.CARSUBTYPEID);
      if (!isBlrgcashflag) {
        headitem.clearViewData();
        this.setBlrgcashflagByTranType(keyValue, rows);
      }
    }
  }

  /**
   * 根据交易类型设置标题赠品字段
   * 
   * @param keyValue
   * @param rows
   */
  private void setBlargessFlagByTranType(IKeyValue keyValue, int[] rows) {
    for (int row : rows) {
      keyValue.setBodyValue(row, SaleOrderBVO.BLARGESSFLAG, false);
    }
  }

  /**
   * 根据交易类型设置标题赠品兑付字段
   * 
   * @param keyValue
   * @param rows
   */
  private void setBlrgcashflagByTranType(IKeyValue keyValue, int[] rows) {
    for (int row : rows) {
      keyValue.setBodyValue(row, SaleOrderBVO.BLRGCASHFLAG, false);
      // modify by wangshu6 for 修改交易类型后 赠品标记不变 只考虑赠品对付 20150411
      // keyValue.setBodyValue(row, SaleOrderBVO.BLARGESSFLAG, false);
    }
  }
}
