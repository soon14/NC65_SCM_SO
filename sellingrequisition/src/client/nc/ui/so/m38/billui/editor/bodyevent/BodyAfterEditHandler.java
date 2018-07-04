package nc.ui.so.m38.billui.editor.bodyevent;

import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.pub.util.SOFreeUtil;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m38.billui.pub.PreOrderCalculator;
import nc.ui.so.m38.billui.view.PreOrderEditor;
import nc.ui.so.pub.util.BodyEditEventUtil;

/**
 * 预订单表体编辑后事件
 * 
 * @since 6.0
 * @version 2011-5-24 下午06:12:43
 * @author
 */
public class BodyAfterEditHandler implements
    IAppEventHandler<CardBodyAfterEditEvent> {

  private PreOrderEditor editor;

  /**
   * 
   * 
   * @return gg
   */
  public PreOrderEditor getEditor() {
    return this.editor;
  }

  @Override
  public void handleAppEvent(CardBodyAfterEditEvent e) {

    int[] editrows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    if (null == editrows) {
      return;
    }

    BillCardPanel cardPanel = e.getBillCardPanel();
    boolean istotalshow = cardPanel.getBodyPanel().isTatolRow();
    cardPanel.getBodyPanel().setTotalRowShow(false);

    String editKey = e.getKey();
    // 物料
    if (PreOrderBVO.CMATERIALVID.equals(editKey)) {
      MaterialEditHandler handler = new MaterialEditHandler();
      handler.afterEdit(e);
    }// 客户物料码(V63新加)
    else if (PreOrderBVO.CCUSTMATERIALID.equals(editKey)) {
      CustMaterialEditHandler handler = new CustMaterialEditHandler();
      handler.afterEdit(e);
    }
    // 数量
    else if (PreOrderBVO.NASTNUM.equals(editKey)) {
      AstNumEditHandler handler = new AstNumEditHandler();
      handler.afterEdit(e);
    }
    // 主数量
    else if (PreOrderBVO.NNUM.equals(editKey)) {
      NumEditHandler handler = new NumEditHandler();
      handler.afterEdit(e);
    }
    // 报价单位数量
    else if (PreOrderBVO.NQTUNITNUM.equals(editKey)) {
      QtUnitNumEditHandler handler = new QtUnitNumEditHandler();
      handler.afterEdit(e);
    }
    // 单位
    else if (PreOrderBVO.CASTUNITID.equals(editKey)) {
      AstUnitEditHandler handler = new AstUnitEditHandler();
      handler.afterEdit(e);
    }
    // 报价计量单位
    else if (PreOrderBVO.CQTUNITID.equals(editKey)) {
      QtUnitEditHandler handler = new QtUnitEditHandler();
      handler.afterEdit(e);
    }
    // 赠品标志
    else if (PreOrderBVO.BLARGESSFLAG.equals(editKey)) {
      LargessFlagEditHandler handler = new LargessFlagEditHandler();
      handler.afterEdit(e);
    }
    // 发货库存组织
    else if (PreOrderBVO.CSENDSTOCKORGVID.equals(editKey)) {
      SendStockOrgEditHandler handler = new SendStockOrgEditHandler();
      handler.afterEdit(e);
    }
    // 发货仓库
    else if (PreOrderBVO.CSENDSTORDOCID.equals(editKey)) {
      SendStordocEditHandler handler = new SendStordocEditHandler();
      handler.afterEdit(e);
    }
    // 结算财务组织
    else if (PreOrderBVO.CSETTLEORGVID.equals(editKey)) {
      SettleOrgEditHandler handler = new SettleOrgEditHandler();
      handler.afterEdit(e);
    }
    // 收货地区
    else if (PreOrderBVO.CRECEIVEAREAID.equals(editKey)) {
      ReceiveAreaEditHandler handler = new ReceiveAreaEditHandler();
      handler.afterEdit(e);
    }
    // 价格项
    else if (PreOrderBVO.CPRICEITEMID.equals(editKey)) {
      PriceItemEditHandler handler = new PriceItemEditHandler();
      handler.afterEdit(e);
    }
    // 收货地址
    else if (PreOrderBVO.CRECEIVEADDRID.equals(editKey)) {
      ReceiveAddressEditHandler handler = new ReceiveAddressEditHandler();
      handler.afterEdit(e);
    }
    // 收货客户
    else if (PreOrderBVO.CRECEIVECUSTID.equals(editKey)) {
      ReceiveCustEditHandler handler = new ReceiveCustEditHandler();
      handler.afterEdit(e);
    }
    // 发货国家/地区
    else if (PreOrderBVO.CSENDCOUNTRYID.equals(editKey)) {
      SendCountryEditHandler handler = new SendCountryEditHandler();
      handler.afterEdit(e);
    }
    // 报税国家/地区
    else if (PreOrderBVO.CTAXCOUNTRYID.equals(editKey)) {
      TaxCountryEditHandler handler = new TaxCountryEditHandler();
      handler.afterEdit(e);
    }
    // 收货国家/地区
    else if (PreOrderBVO.CRECECOUNTRYID.equals(editKey)) {
      ReceiveCountryEditHandler handler = new ReceiveCountryEditHandler();
      handler.afterEdit(e);
    }
    // 税码
    else if (PreOrderBVO.CTAXCODEID.equals(editKey)) {
      TaxCodeEditHandler handler = new TaxCodeEditHandler();
      handler.afterEdit(e);
    }
    // 扣税类别
    else if (PreOrderBVO.FTAXTYPEFLAG.equals(editKey)) {
      TaxTypeFlagEditHandler handler = new TaxTypeFlagEditHandler();
      handler.afterEdit(e);
    }// 生产厂商(V63新加)
    else if (PreOrderBVO.CPRODUCTORID.equals(editKey)) {
      ProductorEditHandler handler = new ProductorEditHandler();
      handler.afterEdit(e);
    }// 供应商(V63新加)
    else if (PreOrderBVO.CVENDORID.equals(editKey)) {
      VendorEditHandler handler = new VendorEditHandler();
      handler.afterEdit(e);
    }
    // 自由辅助属性1-10
    else if (SOFreeUtil.isFreeKey(editKey)) {
      FreeEditHandler handler = new FreeEditHandler();
      handler.afterEdit(e);
    }
    // 批次号
    else if (PreOrderBVO.VBATCHCODE.equals(editKey)) {
      BatchCodeEditHandler handler = new BatchCodeEditHandler();
      handler.setEditor(this.editor);
      handler.afterEdit(e);
    }
    // 质量等级
    else if (PreOrderBVO.CQUALITYLEVELID.equals(editKey)) {
      QualitylevelEditHandler handler = new QualitylevelEditHandler();
      handler.afterEdit(e);
    }
    else {
      // 编辑后先执行数量单价金额计算(calculator内会过滤掉不需要计算的字段)
      PreOrderCalculator calculator =
          new PreOrderCalculator(e.getBillCardPanel());
      calculator.calculate(editrows, editKey);
    }
    cardPanel.getBodyPanel().setTotalRowShow(istotalshow);
  }

  /**
   * 
   * 
   * @param editor
   */
  public void setEditor(PreOrderEditor editor) {
    this.editor = editor;
  }
}
