package nc.ui.so.m32.billui.view;

import java.util.List;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.view.BillOrgPanel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.ref.FilterCustomerRefUtils;
import nc.ui.scmpub.ref.FilterMaterialRefUtils;
import nc.ui.scmpub.util.BillCardPanelUtils;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.ui.so.m32.billui.pub.CardEditSetter;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.listener.SOBillTotalListener;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.rule.HeadDefaultValue;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.util.SOPubParaUtil;

public class SaleInvoiceEditor extends ShowUpableBillForm {

  // 表体不允许编辑字段:物料基本分类、主单位、集团本币无税金额、集团本币价税合计
  // 全局本币无税金额、全局本币价税合计、销售组织、库存组织、仓库
  // 冲减前金额 、费用冲抵金额、主本币含税单价、主本币无税单价
  // 主本币含税净价、主本币无税净价、本币含税单价
  // 本币无税单价、本币含税净价、本币无税净价
  // 本币税额、本币无税金额、本币价税合计、本币折扣额
  // 成本金额、累计应发未出库数量、累计出库数量
  // 累计确认应收数量、累计确认应收金额、累计成本结算数量
  // 累计收款金额、寄存供应商、消耗汇总、开票日期
  // 消耗汇总主键、来源单据主表、来源单据子表
  // 销售发票主键、销售发票子表主键
  // 源头单据主表、源头单据子表
  private static final String[] NOEDITBODYKEY = new String[] {
    // 各类参照OID
    SaleInvoiceBVO.CARORGID, SaleInvoiceBVO.CARORGVID,
    SaleInvoiceBVO.CPROFITCENTERID, SaleInvoiceBVO.CSENDSTOCKORGID,
    SaleInvoiceBVO.CDEPTID,

    SaleInvoiceBVO.CMARBASCALSSID, SaleInvoiceBVO.CUNITID,
    SaleInvoiceBVO.NGROUPMNY, SaleInvoiceBVO.NGROUPTAXMNY,
    SaleInvoiceBVO.NGLOBALMNY, SaleInvoiceBVO.NGLOBALTAXMNY,
    SaleInvoiceBVO.CSALEORGID, SaleInvoiceBVO.CSENDSTOCKORGID,
    SaleInvoiceBVO.CSENDSTORDOCID, "nbforigsubmny", SaleInvoiceBVO.NORIGSUBMNY,
    SaleInvoiceBVO.NTAXPRICE, SaleInvoiceBVO.NPRICE,
    SaleInvoiceBVO.NTAXNETPRICE, SaleInvoiceBVO.NNETPRICE,
    SaleInvoiceBVO.NQTTAXPRICE, SaleInvoiceBVO.NQTPRICE,
    SaleInvoiceBVO.NQTTAXNETPRICE, SaleInvoiceBVO.NQTNETPRICE,
    SaleInvoiceBVO.NDISCOUNT, SaleInvoiceBVO.NORIGDISCOUNT,
    SaleInvoiceBVO.NSHOULDOUTNUM, SaleInvoiceBVO.NTOTALOUTNUM,
    SaleInvoiceBVO.NTOTALINCOMENUM, SaleInvoiceBVO.NTOTALINCOMEMNY,
    SaleInvoiceBVO.NTOTALCOSTNUM, SaleInvoiceBVO.NTOTALPAYMNY,
    SaleInvoiceBVO.CVMIVENDERID, SaleInvoiceBVO.CSUMID,
    SaleInvoiceBVO.DBILLDATE, SaleInvoiceBVO.CSRCID, SaleInvoiceBVO.CSUMID,
    SaleInvoiceBVO.CSRCBID, SaleInvoiceBVO.CSALEINVOICEID,
    SaleInvoiceBVO.CSALEINVOICEBID, SaleInvoiceBVO.CFIRSTBID,
    SaleInvoiceBVO.CFIRSTID, SaleInvoiceBVO.SRCBTS, SaleInvoiceBVO.SRCTS,
    SaleInvoiceBVO.TS, SaleInvoiceBVO.VFIRSTCODE, SaleInvoiceBVO.VFIRSTROWNO,
    SaleInvoiceBVO.VSRCCODE, SaleInvoiceBVO.VSRCROWNO,
    SaleInvoiceBVO.NITEMDISCOUNTRATE, SaleInvoiceBVO.NDISCOUNTRATE
  };

  // 销售发票表头不允许编辑字段:本币、总数量、价税合计、
  // 是否传金税、最后金税时间、对冲标志、 对冲发票HID、对冲发票号、单据状态,冲抵发票标志
  private static final String[] NOEDITHEADKEY = new String[] {
    SaleInvoiceHVO.CBIZTYPEID, SaleInvoiceHVO.CCURRENCYID,
    SaleInvoiceHVO.CCUSTBANKID, SaleInvoiceHVO.NTOTALASTNUM,
    SaleInvoiceHVO.NTOTALORIGMNY, SaleInvoiceHVO.VGOLDTAXCODE,
    SaleInvoiceHVO.BTOGOLDTAXFLAG, SaleInvoiceHVO.TGOLDTAXTIME,
    SaleInvoiceHVO.FOPPOSEFLAG, SaleInvoiceHVO.COPPOSESRCID,
    SaleInvoiceHVO.VOPPOSESRCCODE, SaleInvoiceHVO.FSTATUSFLAG,
    SaleInvoiceHVO.FOPPOSEFLAG,

    SaleInvoiceHVO.DMAKEDATE, SaleInvoiceHVO.APPROVER,
    SaleInvoiceHVO.TAUDITTIME, SaleInvoiceHVO.CREATOR, SaleInvoiceHVO.MODIFIER,
    SaleInvoiceHVO.MODIFIEDTIME, SaleInvoiceHVO.CREATIONTIME,
    SaleInvoiceHVO.BILLMAKER

  };

  /**
   * 
   */
  private static final long serialVersionUID = -4898097866857994181L;

  List<String> clearHyperlink;

  // 发票编辑性设置类
  private CardEditSetter editsetter = new CardEditSetter();

  // 用于销售费用单冲抵销售订单缓存数据
  private OffsetTempVO tempvo;

  // 销售发票主组织处理，主要是名称显示问题
  private BillOrgPanel billOrgPanel;

  public List<String> getClearHyperlink() {
    return this.clearHyperlink;
  }

  public OffsetTempVO getTempvo() {
    return this.tempvo;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.view.ShowUpableBillForm#initUI()
   */
  @Override
  public void initUI() {
    super.initUI();
    BillCardPanel billcard = this.getBillCardPanel();
    billcard.getBodyPanel().setTotalRowShow(true);
    IKeyValue keyValue = new CardKeyValue(billcard);

    SOBillTotalListener totallis = new SOBillTotalListener(keyValue);
    billcard.getBillModel().addTotalListener(totallis);
    this.initEditorData();
    // 清除卡片超链接
    this.clearHyperlink();

    this.initFillEnabled(billcard);
  }

  @Override
  public BillOrgPanel getBillOrgPanel() {
    if (null == this.billOrgPanel && this.isShowOrgPanel()) {
      this.billOrgPanel = this.createDefaultBillOrgPanel();
      super.setBillOrgPanel(this.billOrgPanel);
    }
    return this.billOrgPanel;

  }

  private BillOrgPanel createDefaultBillOrgPanel() {
    BillOrgPanel orgPanel = new BillOrgPanel();
    orgPanel.setLabelName(NCLangRes.getInstance().getStrByID("4006008_0",
        "04006008-0085")/* 开票组织 */);
    orgPanel.setModel(this.getModel());
    orgPanel.initUI();
    return orgPanel;
  }

  /**
   * 设置卡片编辑性
   */
  public void setCardEditEnable() {
    SaleInvoiceManageModel model = (SaleInvoiceManageModel) this.getModel();
    this.editsetter.setEditEnable(this.getBillCardPanel(),
        model.getCombinCacheVO());
  }

  public void setClearHyperlink(List<String> clearHyperlink) {
    this.clearHyperlink = clearHyperlink;
  }

  public void setTempvo(OffsetTempVO tempvo) {
    this.tempvo = tempvo;
  }

  /**
   * 父类方法重写 禁止编辑开票组织
   */
  @Override
  public void showMeUp() {
    super.showMeUp();
    this.getBillOrgPanel().setEnabled(false);

  }

  @Override
  protected void onAdd() {
    super.onAdd();
    this.setTempvo(null);
    this.setCardEditEnable();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.editor.BillForm#onEdit()
   */
  @Override
  protected void onEdit() {
    super.onEdit();
    this.setTempvo(null);
  }

  /**
   * 父类方法重写 新增时如果带出默认开票组织需要设置默认值
   */
  @Override
  protected void setDefaultValue() {

    CardKeyValue keyValue = new CardKeyValue(this.billCardPanel);
    // 设置表头默认值
    HeadDefaultValue headdefault =
        new HeadDefaultValue(keyValue, this.getModel().getContext());
    headdefault.setDefaultValue();
    // 单据日期
    keyValue.setHeadValue(SaleInvoiceHVO.DBILLDATE, AppContext.getInstance()
        .getBusiDate());

    String pk_org = keyValue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
    if (StringUtil.isEmptyWithTrim(pk_org)) {
      return;
    }

    // // 设置表体默认值(放在主组织改变事件里面)
    // BodyDefaultValue bodydefault = new BodyDefaultValue(keyValue);
    // bodydefault.setAllDefautValue();
    // // 设置币种默认值(放在主组织改变事件里面)
    // DefaultOrgCurrRule orgDefault = new DefaultOrgCurrRule(keyValue);
    // orgDefault.setDefautOrgCurrByPk();
    //
    // // 设置折本汇率
    // ExchangeRateRule exchangerule = new ExchangeRateRule(keyValue);
    // exchangerule.calcExchangeRate();
  }

  private void clearHyperlink() {
    for (String key : this.getClearHyperlink()) {
      BillItem item = this.getBillCardPanel().getBillData().getHeadItem(key);
      // item.setHyperlink(false);
      item.getCaptionLabel().setHyperlinkLabel(false);
    }
  }

  /**
   * 销售发票卡片界面初始化能否编辑属性
   */
  private void initEditEnable() {

    // 表头不可编辑项
    for (String key : SaleInvoiceEditor.NOEDITHEADKEY) {
      BillItem headitem = this.getBillCardPanel().getHeadTailItem(key);
      if (null != headitem) {
        headitem.setEdit(false);
      }
    }
    // 表体不可编辑项
    for (String key : SaleInvoiceEditor.NOEDITBODYKEY) {
      BillItem bodyitem = this.getBillCardPanel().getBodyItem(key);
      if (null != bodyitem) {
        bodyitem.setEdit(false);
      }
    }
    BillItem headitem =
        this.getBillCardPanel().getHeadItem(SaleInvoiceHVO.NGLOBALEXCHGRATE);
    if (null != headitem) {
      if (SOPubParaUtil.getInstance().isGlobalLocalCurrencyEnable()) {
        headitem.setEdit(true);
      }
      else {
        headitem.setEdit(false);
      }
    }
    headitem =
        this.getBillCardPanel().getHeadItem(SaleInvoiceHVO.NGROUPEXCHGRATE);
    if (null != headitem) {
      if (SOPubParaUtil.getInstance().isGroupLocalCurrencyEnable(
          AppContext.getInstance().getPkGroup())) {
        headitem.setEdit(true);
      }
      else {
        headitem.setEdit(false);
      }
    }
  }

  private void initEditorData() {
    // 初始化编辑性
    this.initEditEnable();
    // 缓存编辑性
    // this.editsetter = new CardEditSetter();
    this.editsetter.cacheEditEnable(this.getBillCardPanel());
  }

  /**
   * 初始化界面的编辑性
   * 
   * @param cardPanel
   */
  private void initFillEnabled(BillCardPanel cardPanel) {
    BillCardPanelUtils util = new BillCardPanelUtils(cardPanel);
    util.disableItemsFill();
    util.enableItemsFill(SOConstant.SALEINVOICEFILLENABLEDKEY);
    // 自定义项都可以批编辑
    for (int i = 1; i < 21; i++) {
      BillItem bodyitem =
          this.getBillCardPanel().getBodyItem(SOConstant.VBDEF + i);
      bodyitem.setFillEnabled(true);
    }

  }
}
