package nc.ui.so.m38.billui.view;

import java.util.List;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyRowChangedEvent;
import nc.ui.pubapp.uif2app.model.IAppModelEx;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmf.ic.batchcode.ref.ScmBatchAdaptor;
import nc.ui.scmf.ic.onhand.OnhandPanelAdaptor;
import nc.ui.scmf.ic.onhand.OnhandPanelSrc;
import nc.ui.scmpub.ref.FilterCustomerRefUtils;
import nc.ui.scmpub.util.BillCardPanelUtils;
import nc.ui.so.m38.billui.pub.PreOrderCardEditSetter;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.listener.SOBillTotalListener;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmf.ic.onhand.OnhandDimParamVO;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 预订单表单卡片编辑界面
 * 
 * @author 刘志伟
 * 
 */
public class PreOrderEditor extends ShowUpableBillForm implements
    OnhandPanelSrc {
  // 表体不允许编辑字段
  private static final String[] BODY_NOEDIT = new String[] {
    // 整单折扣
    PreOrderBVO.NDISCOUNTRATE,
    // 主单位
    PreOrderBVO.CUNITID,
    // 本币
    PreOrderBVO.CCURRENCYID,
    // 本币单价
    PreOrderBVO.NQTTAXPRICE,
    PreOrderBVO.NQTPRICE,
    PreOrderBVO.NQTTAXNETPRICE,
    PreOrderBVO.NQTNETPRICE,
    // 主本币单价
    PreOrderBVO.NTAXPRICE,
    PreOrderBVO.NPRICE,
    PreOrderBVO.NTAXNETPRICE,
    PreOrderBVO.NNETPRICE,
    // 本币金额
    PreOrderBVO.NDISCOUNT,

    // 原币折扣额
    PreOrderBVO.NORIGDISCOUNT,

    // 集团本币
    PreOrderBVO.NGROUPMNY,
    PreOrderBVO.NGROUPTAXMNY,
    // 全局本币
    PreOrderBVO.NGLOBALTAXMNY,
    PreOrderBVO.NGLOBALMNY,
    // 价格项字段
    PreOrderBVO.CPRICEFORMID,
    PreOrderBVO.CPRICEITEMTABLEID,
    PreOrderBVO.CPRICEPOLICYID,
    // 询价字段
    PreOrderBVO.NASKQTORIGTAXPRC, PreOrderBVO.NASKQTORIGPRICE,
    PreOrderBVO.NASKQTORIGTXNTPRC,
    PreOrderBVO.NASKQTORIGNETPRICE,
    // 安排字段
    PreOrderBVO.NARRNUM,
    PreOrderBVO.CARRANGEID,
    PreOrderBVO.DARRDATE,
    // 行状态
    PreOrderBVO.FROWSTATUS,

    // 各类参照OID
    PreOrderBVO.CSETTLEORGID, PreOrderBVO.CARORGID,
    PreOrderBVO.CPROFITCENTERID, PreOrderBVO.CSENDSTOCKORGID,
    PreOrderBVO.CTRAFFICORGID,
    // 购销类型、三角贸易
    PreOrderBVO.FBUYSELLFLAG, PreOrderBVO.BTRIATRADEFLAG,
    // 本币价税合计、本币无税金额
    PreOrderBVO.NTAXMNY, PreOrderBVO.NMNY
  };

  private static final int DEFDABATEDATE = 3;

  // 表头不允许编辑字段
  private static final String[] HEAD_NOEDIT = new String[] {
    PreOrderHVO.VBILLCODE, PreOrderHVO.NTOTALNUM, PreOrderHVO.NHEADSUMMNY,
    PreOrderHVO.FSTATUSFLAG, PreOrderHVO.CDEPTID
  };

  private static final long serialVersionUID = -4607945892107326567L;

  /**
   * 用户现存量面板
   */
  private OnhandPanelAdaptor adaptor;

  private List<String> clearHyperlink;

  private PreOrderCardEditSetter editsetter;

  @Override
  public void addCardBodyRowChangedEvent(
      IAppEventHandler<CardBodyRowChangedEvent> l) {
    ((IAppModelEx) this.getModel()).addAppEventListener(
        CardBodyRowChangedEvent.class, l);
  }

  public List<String> getClearHyperlink() {
    return this.clearHyperlink;
  }

  public OnhandPanelAdaptor getExtendedPanel() {
    return this.adaptor;
  }

  @Override
  public OnhandDimParamVO getQryOnhandDim(int row) {
    OnhandDimParamVO paravo = null;
    IKeyValue keyValue = new CardKeyValue(this.getBillCardPanel());
    String marterialvid =
        keyValue.getBodyStringValue(row, PreOrderBVO.CMATERIALVID);
    if (PubAppTool.isNull(marterialvid)) {
      return paravo;
    }

    paravo = this.getOnhandDimParamVO(keyValue, row);
    return paravo;
  }

  @Override
  public void initUI() {
    super.initUI();
    // 初始化界面编辑性
    this.initEditEnable();
    if (SysInitGroupQuery.isICEnabled()) {
      ScmBatchAdaptor scmbach =
          new ScmBatchAdaptor("nc.ui.ic.batchcode.ref.BatchRefPane");
      UIRefPane uiref = scmbach.getRefPane();

      this.getBillCardPanel().getBodyItem(PreOrderBVO.VBATCHCODE)
          .setComponent(uiref);
    }
    // 清除卡片超链接
    this.clearHyperlink();
    // 参照过滤初始化
    this.initRefCondition();

    // 编辑性控制
    this.editsetter = new PreOrderCardEditSetter();
    this.editsetter.cacheEditEnable(this.getBillCardPanel());
    // 合计行
    BillCardPanel cardPanel = this.getBillCardPanel();
    cardPanel.getBodyPanel().setTotalRowShow(true);
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    SOBillTotalListener totallis = new SOBillTotalListener(keyValue);
    cardPanel.getBillModel().addTotalListener(totallis);
    // 初始化不能批改字段
    this.initFillEnabled(cardPanel);
  }

  /**
   * 初始化界面的编辑性
   * 
   * @param cardPanel
   */
  private void initFillEnabled(BillCardPanel cardPanel) {
    BillCardPanelUtils util = new BillCardPanelUtils(cardPanel);
    util.disableItemsFill();
    util.enableItemsFill(SOConstant.FILLENABLEDKEY);
    // 自定义项都可以批编辑
    for (int i = 1; i < 21; i++) {
      BillItem bodyitem =
          this.getBillCardPanel().getBodyItem(SOConstant.VBDEF + i);
      bodyitem.setFillEnabled(true);
    }
  }

  public void setCardEdit() {
    this.editsetter.setEditEnable(this.getBillCardPanel());
  }

  public void setClearHyperlink(List<String> clearHyperlink) {
    this.clearHyperlink = clearHyperlink;
  }

  public void setExtendedPanel(OnhandPanelAdaptor adaptor) {
    this.adaptor = adaptor;
  }

  public void setReviseEdit() {
    this.editsetter.setReviseCardEdit(this.getBillCardPanel());
  }

  @Override
  protected void onAdd() {
    super.onAdd();
    if (this.isEditable()) {
      this.setCardEdit();
    }
  }

  @Override
  protected void setDefaultValue() {

    IKeyValue keyValue = new CardKeyValue(this.billCardPanel);
    int irowcount = this.billCardPanel.getRowCount();
    // -- 表头 ----------
    // 销售组织
    String pk_org = this.getModel().getContext().getPk_org();
    if (!PubAppTool.isNull(pk_org)) {
      keyValue.setHeadValue(PreOrderHVO.PK_ORG, pk_org);
      this.billCardPanel.getBillData().loadEditHeadRelation(PreOrderHVO.PK_ORG);
      // 集团
      String pk_group = AppContext.getInstance().getPkGroup();
      keyValue.setHeadValue(PreOrderHVO.PK_GROUP, pk_group);
      // 单据日期
      UFDate busidate = AppContext.getInstance().getBusiDate();
      keyValue.setHeadValue(PreOrderHVO.DBILLDATE, busidate);
      // 失效日期
      keyValue.setHeadValue(PreOrderHVO.DABATEDATE,
          busidate.getDateAfter(PreOrderEditor.DEFDABATEDATE).asLocalEnd());

      // 发货、收货日期
      UFDate localend = busidate.asLocalEnd();
      // -- 表体 ----------
      for (int i = 0; i < irowcount; i++) {
        // 集团
        keyValue.setBodyValue(i, PreOrderBVO.PK_GROUP, pk_group);
        // 销售组织
        keyValue.setBodyValue(i, PreOrderBVO.PK_ORG, pk_org);
        // 单据日期
        keyValue.setBodyValue(i, PreOrderBVO.DBILLDATE, busidate);
        // 单品折扣
        keyValue.setBodyValue(i, PreOrderBVO.NITEMDISCOUNTRATE, new UFDouble(
            100));
        // 计划发货日期、要求收货日期
        keyValue.setBodyValue(i, PreOrderBVO.DSENDDATE, localend);
        keyValue.setBodyValue(i, PreOrderBVO.DRECEIVEDATE, localend);
      }
    }
  }

  private void clearHyperlink() {
    for (String key : this.getClearHyperlink()) {
      BillItem item = this.getBillCardPanel().getBillData().getHeadItem(key);
      item.getCaptionLabel().setHyperlinkLabel(false);
    }
  }

  private OnhandDimParamVO getOnhandDimParamVO(IKeyValue keyValue, int row) {
    OnhandDimParamVO paravo = new OnhandDimParamVO();
    // 集团
    String pk_group = AppContext.getInstance().getPkGroup();
    paravo.setPk_group(pk_group);

    // 物料
    String cmarterialvid =
        keyValue.getBodyStringValue(row, PreOrderBVO.CMATERIALVID);
    paravo.setCmaterialvid(cmarterialvid);
    String cmarterialid =
        keyValue.getBodyStringValue(row, PreOrderBVO.CMATERIALID);
    paravo.setCmaterialoid(cmarterialid);
    // 业务单位
    String castunitid =
        keyValue.getBodyStringValue(row, PreOrderBVO.CASTUNITID);
    paravo.setCastunitid(castunitid);

    // 换算率
    String vchangerate =
        keyValue.getBodyStringValue(row, PreOrderBVO.VCHANGERATE);
    paravo.setVchangerate(vchangerate);
    // 生产厂商
    String cproductorid =
        keyValue.getBodyStringValue(row, PreOrderBVO.CPRODUCTORID);
    paravo.setCproductorid(cproductorid);
    // 项目
    String cprojectid =
        keyValue.getBodyStringValue(row, PreOrderBVO.CPROJECTID);
    paravo.setCprojectid(cprojectid);
    // 供应商
    String cvendorid = keyValue.getBodyStringValue(row, PreOrderBVO.CVENDORID);
    paravo.setCvendorid(cvendorid);

    // 批次号
    String vbatchcode =
        keyValue.getBodyStringValue(row, PreOrderBVO.VBATCHCODE);
    paravo.setVbatchcode(vbatchcode);

    // 库存组织
    String csendstockorgid =
        keyValue.getBodyStringValue(row, PreOrderBVO.CSENDSTOCKORGID);
    paravo.setPk_org(csendstockorgid);

    // 库存组织版本
    String csendstockorgvid =
        keyValue.getBodyStringValue(row, PreOrderBVO.CSENDSTOCKORGVID);
    paravo.setPk_org_v(csendstockorgvid);

    // 仓库
    String cwarehouseid =
        keyValue.getBodyStringValue(row, PreOrderBVO.CSENDSTORDOCID);
    paravo.setCwarehouseid(cwarehouseid);

    // 自由项
    String vfree1 = keyValue.getBodyStringValue(row, PreOrderBVO.VFREE1);
    paravo.setVfree1(vfree1);

    String vfree2 = keyValue.getBodyStringValue(row, PreOrderBVO.VFREE2);
    paravo.setVfree1(vfree2);

    String vfree3 = keyValue.getBodyStringValue(row, PreOrderBVO.VFREE3);
    paravo.setVfree1(vfree3);

    String vfree4 = keyValue.getBodyStringValue(row, PreOrderBVO.VFREE4);
    paravo.setVfree1(vfree4);

    String vfree5 = keyValue.getBodyStringValue(row, PreOrderBVO.VFREE5);
    paravo.setVfree1(vfree5);

    String vfree6 = keyValue.getBodyStringValue(row, PreOrderBVO.VFREE6);
    paravo.setVfree1(vfree6);

    String vfree7 = keyValue.getBodyStringValue(row, PreOrderBVO.VFREE7);
    paravo.setVfree1(vfree7);

    String vfree8 = keyValue.getBodyStringValue(row, PreOrderBVO.VFREE8);
    paravo.setVfree1(vfree8);

    String vfree9 = keyValue.getBodyStringValue(row, PreOrderBVO.VFREE9);
    paravo.setVfree1(vfree9);

    String vfree10 = keyValue.getBodyStringValue(row, PreOrderBVO.VFREE10);
    paravo.setVfree1(vfree10);

    return paravo;
  }

  /**
   * 卡片界面初始化能否编辑属性
   */
  private void initEditEnable() {
    // 表头不可编辑项
    for (String key : PreOrderEditor.HEAD_NOEDIT) {
      BillItem headitem = this.getBillCardPanel().getHeadTailItem(key);
      if (null != headitem) {
        headitem.setEdit(false);
      }
    }
    // 表体不可编辑项
    for (String key : PreOrderEditor.BODY_NOEDIT) {
      BillItem bodyitem = this.getBillCardPanel().getBodyItem(key);
      if (null != bodyitem) {
        bodyitem.setEdit(false);
      }
    }

  }

  private void initRefCondition() {
    // 订单客户
    BillItem customeritem =
        this.getBillCardPanel().getHeadTailItem(PreOrderHVO.CCUSTOMERID);
    FilterCustomerRefUtils filterutils =
        new FilterCustomerRefUtils(customeritem);
    filterutils.filterRefByFrozenFlag(UFBoolean.FALSE);
    // 开票客户
    BillItem invoicecustomeritem =
        this.getBillCardPanel().getHeadTailItem(PreOrderHVO.CINVOICECUSTID);
    FilterCustomerRefUtils invoicefilterutils =
        new FilterCustomerRefUtils(invoicecustomeritem);
    invoicefilterutils.filterRefByFrozenFlag(UFBoolean.FALSE);

  }
}
