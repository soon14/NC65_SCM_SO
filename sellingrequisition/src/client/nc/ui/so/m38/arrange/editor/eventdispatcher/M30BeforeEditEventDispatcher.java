package nc.ui.so.m38.arrange.editor.eventdispatcher;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.push.BillContext;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.so.m38.arrange.editor.eidthandler.AstUnitEditHanlder;
import nc.ui.so.m38.arrange.editor.eidthandler.BatchCodeEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.CalTaxMnyEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.ChangeRateEditHanlder;
import nc.ui.so.m38.arrange.editor.eidthandler.DeptEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.ExchangerateEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.FreeCustEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.GlobalExchgRateEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.GroupExchgRateEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.OrigAreaEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.QtUnitEditHanlder;
import nc.ui.so.m38.arrange.editor.eidthandler.QtUnitRateEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.SaleOrgEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.SendStockOrgEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.SendStordocEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.SettleOrgEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.TaxCodeEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.TrafficOrgEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.TrantypeEditHandler;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m38.entity.PreOrderBVO;

/**
 * 预订单安排界面销售订单编辑前事件派发类
 * 
 * @since 6.0
 * @version 2012-4-6 下午03:50:28
 * @author fengjb
 */
public class M30BeforeEditEventDispatcher {

  private BillListPanel listPanel;

  private BillContext billcontext;

  public M30BeforeEditEventDispatcher(BillListPanel billListPanel,
      BillContext billcontext) {
    this.listPanel = billListPanel;
    this.billcontext = billcontext;
  }

  public void handleEvent(PushBillEvent e) {

    String editKey = e.getEditEvent().getKey();
    // 销售组织
    if (SaleOrderHVO.PK_ORG.equals(editKey)) {
      SaleOrgEditHandler handler = new SaleOrgEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // 交易类型
    if (SaleOrderHVO.CTRANTYPEID.equals(editKey)) {
      TrantypeEditHandler handler = new TrantypeEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // 部门
    else if (SaleOrderHVO.CDEPTVID.equals(editKey)) {
      DeptEditHandler handler = new DeptEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // 散户
    else if (SaleOrderHVO.CFREECUSTID.equals(editKey)) {
      FreeCustEditHandler handler = new FreeCustEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // ------表体------
    // 单位
    else if (SaleOrderBVO.CASTUNITID.equals(editKey)) {
      AstUnitEditHanlder handler = new AstUnitEditHanlder();
      handler.beforeEdit(this.listPanel, e);
    }
    // 报价单位
    else if (SaleOrderBVO.CQTUNITID.equals(editKey)) {
      QtUnitEditHanlder handler = new QtUnitEditHanlder();
      handler.beforeEdit(this.listPanel, e);
    }
    // 换算率
    else if (SaleOrderBVO.VCHANGERATE.equals(editKey)) {
      ChangeRateEditHanlder handler = new ChangeRateEditHanlder();
      handler.beforeEdit(this.listPanel, e);
    }
    // 报价单位换算率
    else if (PreOrderBVO.VQTUNITRATE.equals(editKey)) {
      QtUnitRateEditHandler handler = new QtUnitRateEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // 发货库存组织
    else if (SaleOrderBVO.CSENDSTOCKORGVID.equals(editKey)) {
      SendStockOrgEditHandler handler = new SendStockOrgEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // 发货仓库
    else if (SaleOrderBVO.CSENDSTORDOCID.equals(editKey)) {
      SendStordocEditHandler handler = new SendStordocEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // 物流组织
    else if (SaleOrderBVO.CTRAFFICORGVID.equals(editKey)) {
      TrafficOrgEditHandler handler = new TrafficOrgEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // 结算财务组织
    else if (SaleOrderBVO.CSETTLEORGVID.equals(editKey)) {
      SettleOrgEditHandler handler = new SettleOrgEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // 折本汇率
    else if (SaleOrderBVO.NEXCHANGERATE.equals(editKey)) {
      ExchangerateEditHandler handler = new ExchangerateEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // 全局折本汇率
    else if (SaleOrderBVO.NGLOBALEXCHGRATE.equals(editKey)) {
      GlobalExchgRateEditHandler handler = new GlobalExchgRateEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // 集团折本汇率
    else if (SaleOrderBVO.NGROUPEXCHGRATE.equals(editKey)) {
      GroupExchgRateEditHandler handler = new GroupExchgRateEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // 计税金额
    else if (SaleOrderBVO.NCALTAXMNY.equals(editKey)) {
      CalTaxMnyEditHandler handler = new CalTaxMnyEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // 税码
    else if (SaleOrderBVO.CTAXCODEID.equals(editKey)) {
      TaxCodeEditHandler handler = new TaxCodeEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // 原产地区
    else if (SaleOrderBVO.CORIGAREAID.equals(editKey)) {
      OrigAreaEditHandler handler = new OrigAreaEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // 批次号
    else if (SaleOrderBVO.VBATCHCODE.equals(editKey)) {
      BatchCodeEditHandler handler = new BatchCodeEditHandler();
      handler.beforeEdit(this.listPanel, e, this.billcontext);
    }
  }
}
