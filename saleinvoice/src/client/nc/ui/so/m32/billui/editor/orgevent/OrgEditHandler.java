package nc.ui.so.m32.billui.editor.orgevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.pubapp.uif2app.view.util.BillPanelUtils;
import nc.ui.so.m32.billui.pub.CardPanelClearUtil;
import nc.ui.so.m32.billui.pub.SaleInvoicePrecision;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.UIState;
import nc.ui.uif2.editor.BillForm;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.rule.BodyDefaultValue;
import nc.vo.so.m32.rule.DefaultOrgCurrRule;
import nc.vo.so.m32.rule.ExchangeRateRule;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.util.SOPubParaUtil;
import nc.vo.uif2.LoginContext;

/**
 * 
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>销售发票开票组织编辑事件
 * </ul>
 * 
 * <p>
 * 
 * @version 本版本号 6.0
 * @since
 * @author fengjb
 * @time 2010-8-10 上午10:24:22
 */
public class OrgEditHandler implements IAppEventHandler<OrgChangedEvent> {

  private BillCardPanel billCardPanel;

  private BillForm billfrom;

  private LoginContext context;

  public OrgEditHandler(BillForm bill, LoginContext context) {
    this.billfrom = bill;
    this.billCardPanel = bill.getBillCardPanel();
    this.context = context;
  }

  @Override
  public void handleAppEvent(OrgChangedEvent e) {
    // 设置界面精度
    SaleInvoicePrecision.getInstance().setCardPrecision(
        this.context.getPk_group(), this.billCardPanel);
    // 新增时切换主组织处理
    if (this.billfrom.getModel().getUiState().equals(UIState.ADD)) {
      // 清空单据界面上数值
      CardPanelClearUtil clearutil = new CardPanelClearUtil(this.billCardPanel);
      clearutil.clearValue();

      if (PubAppTool.isNull(e.getNewPkOrg())) {
        return;
      }
      // 设置默认值
      this.setDefValue(e);
      this.setCardEditor();
    }
    BillPanelUtils.setOrgForAllRef(this.billfrom.getBillCardPanel(),
        this.billfrom.getModel().getContext());

  }

  /**
   * 设置集团本位币汇率和全局本位币相关编辑性和字段值
   */
  private void setCardEditor() {
    IKeyValue keyValue = new CardKeyValue(this.billCardPanel);
    BillItem ortheritem =
        this.billCardPanel.getHeadItem(SaleInvoiceHVO.NGLOBALEXCHGRATE);
    // 启用全局本位币

    if (null != ortheritem) {
      if (SOPubParaUtil.getInstance().isGlobalLocalCurrencyEnable()) {
        ortheritem.setEdit(true);
      }
      else {
        ortheritem.setEdit(false);
        keyValue.setHeadValue(SaleInvoiceHVO.NGLOBALEXCHGRATE, null);
      }
    }
    ortheritem = this.billCardPanel.getHeadItem(SaleInvoiceHVO.NGROUPEXCHGRATE);

    String pk_group = keyValue.getHeadStringValue(SaleOrderHVO.PK_GROUP);
    // 启用集团本位币
    if (null != ortheritem) {
      if (SOPubParaUtil.getInstance().isGroupLocalCurrencyEnable(pk_group)) {
        ortheritem.setEdit(true);
      }
      else {
        ortheritem.setEdit(false);
        keyValue.setHeadValue(SaleInvoiceHVO.NGROUPEXCHGRATE, null);
      }
    }
  }

  /**
   * 
   * 方法功能描述：切换主组织时设置单据默认值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param e
   *          <p>
   * @author fengjb
   * @time 2010-8-10 上午10:25:38
   */
  private void setDefValue(OrgChangedEvent e) {
    IKeyValue keyValue = new CardKeyValue(this.billCardPanel);
    // 设置表头开票组织
    keyValue.setHeadValue(SaleInvoiceHVO.PK_ORG, e.getNewPkOrg());

    keyValue.setHeadValue(SaleInvoiceHVO.PK_GROUP, AppContext.getInstance()
        .getPkGroup());

    keyValue.setHeadValue(SaleInvoiceHVO.DBILLDATE, AppContext.getInstance()
        .getBusiDate());
    // 设置表体默认值
    BodyDefaultValue bodydefault = new BodyDefaultValue(keyValue);
    bodydefault.setAllDefautValue();

    // 设置币种默认值
    DefaultOrgCurrRule orgDefault = new DefaultOrgCurrRule(keyValue);
    orgDefault.setDefautOrgCurrByPk();

    // 设置折本汇率
    ExchangeRateRule exchangerule = new ExchangeRateRule(keyValue);
    exchangerule.calcExchangeRate();
  }
}
