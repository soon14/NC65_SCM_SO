package nc.ui.so.m38.arrange.editor.eventdispatcher;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.so.m38.arrange.editor.eidthandler.AstNumEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.AstUnitEditHanlder;
import nc.ui.so.m38.arrange.editor.eidthandler.BatchCodeEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.NumEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.OrigCurrencyEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.QtUnitEditHanlder;
import nc.ui.so.m38.arrange.editor.eidthandler.QtUnitNumEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.SaleOrgEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.SendStockOrgEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.SendStordocEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.SettleOrgEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.TaxCodeEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.TaxTypeFlagEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.TrantypeEditHandler;
import nc.ui.so.m38.arrange.pub.M38ArrangeModelCalculator;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m38.entity.PreOrderBVO;

/**
 * 预订单安排界面销售订单编辑后事件派发类
 * 
 * @since 6.0
 * @version 2012-4-6 下午03:53:56
 * @author fengjb
 */
public class M30AfterEditEventDispatcher {

  private BillListPanel listPanel;

  public M30AfterEditEventDispatcher(BillListPanel billListPanel) {
    this.listPanel = billListPanel;
  }

  public void handleEvent(PushBillEvent e) {

    String editKey = e.getEditEvent().getKey();
    // 销售组织
    if (SaleOrderHVO.PK_ORG.equals(editKey)) {
      SaleOrgEditHandler handler = new SaleOrgEditHandler();
      handler.afterEdit(this.listPanel, e);
    }
    // 交易类型
    else if (SaleOrderHVO.CTRANTYPEID.equals(editKey)) {
      TrantypeEditHandler handler = new TrantypeEditHandler();
      handler.afterEdit(this.listPanel, e);
    }
    // 币种
    else if (SaleOrderHVO.CORIGCURRENCYID.equals(editKey)) {
      OrigCurrencyEditHandler handler = new OrigCurrencyEditHandler();
      handler.afterEdit(this.listPanel, e);
    }
    // --------表体--------
    // 单位
    else if (SaleOrderBVO.CASTUNITID.equals(editKey)) {
      AstUnitEditHanlder handler = new AstUnitEditHanlder();
      handler.afterEdit(this.listPanel, e);
    }
    // 报价计量单位
    else if (SaleOrderBVO.CQTUNITID.equals(editKey)) {
      QtUnitEditHanlder handler = new QtUnitEditHanlder();
      handler.afterEdit(this.listPanel, e);
    }
    // 数量
    else if (SaleOrderBVO.NASTNUM.equals(editKey)) {
      AstNumEditHandler handler = new AstNumEditHandler();
      handler.afterEdit(this.listPanel, e);
    }
    // 主数量
    else if (SaleOrderBVO.NNUM.equals(editKey)) {
      NumEditHandler handler = new NumEditHandler();
      handler.afterEdit(this.listPanel, e);
    }
    // 报价数量
    else if (SaleOrderBVO.NQTUNITNUM.equals(editKey)) {
      QtUnitNumEditHandler handler = new QtUnitNumEditHandler();
      handler.afterEdit(this.listPanel, e);
    }
    // 发货库存组织
    else if (SaleOrderBVO.CSENDSTOCKORGVID.equals(editKey)) {
      SendStockOrgEditHandler handler = new SendStockOrgEditHandler();
      handler.afterEdit(this.listPanel, e);
    }
    // 发货仓库
    else if (SaleOrderBVO.CSENDSTORDOCID.equals(editKey)) {
      SendStordocEditHandler handler = new SendStordocEditHandler();
      handler.afterEdit(this.listPanel, e);
    }
    // 税码
    else if (SaleOrderBVO.CTAXCODEID.equals(editKey)) {
      TaxCodeEditHandler handler = new TaxCodeEditHandler();
      handler.afterEdit(this.listPanel, e);
    }
    // 扣税类别
    else if (SaleOrderBVO.FTAXTYPEFLAG.equals(editKey)) {
      TaxTypeFlagEditHandler handler = new TaxTypeFlagEditHandler();
      handler.afterEdit(this.listPanel, e);
    }
    // 结算财务组织
    else if (SaleOrderBVO.CSETTLEORGVID.equals(editKey)) {
      SettleOrgEditHandler handler = new SettleOrgEditHandler();
      handler.afterEdit(this.listPanel, e);
    }
    // 批次号
    else if (PreOrderBVO.VBATCHCODE.equals(editKey)) {
      BatchCodeEditHandler handler = new BatchCodeEditHandler();
      handler.afterEdit(this.listPanel, e);
    }
    else {
      M38ArrangeModelCalculator calculator =
          new M38ArrangeModelCalculator(this.listPanel);
      int[] rows = new int[] {
        e.getEditEvent().getRow()
      };
      // 数量、单价、金额
      calculator.calculate(rows, editKey);
    }
  }
}
