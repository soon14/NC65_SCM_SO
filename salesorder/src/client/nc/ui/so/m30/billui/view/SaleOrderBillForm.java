package nc.ui.so.m30.billui.view;

import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyRowChangedEvent;
import nc.ui.pubapp.uif2app.model.IAppModelEx;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmf.ic.batchcode.ref.ScmBatchAdaptor;
import nc.ui.scmf.ic.onhand.OnhandPanelAdaptor;
import nc.ui.scmf.ic.onhand.OnhandPanelSrc;
import nc.ui.scmpub.util.BillCardPanelUtils;
import nc.ui.so.m30.pub.CardEditSetter;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.listener.SOBillTotalListener;
import nc.ui.so.pub.rule.SetDeptByLoginUserRule;
import nc.vo.ct.entity.CtBusinessVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmf.ic.onhand.OnhandDimParamVO;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 销售订单卡片控件
 * 编辑性控制
 * 
 * @since 6.0
 * @version 2011-6-16 下午06:57:30
 * @author fengjb
 */
public class SaleOrderBillForm extends ShowUpableBillForm implements
    OnhandPanelSrc {

  /** 缓存的编辑性 */
  private Map<String, Boolean> hmEditEnable;

  /**
   * 
   * @return 获取缓存的编辑性
   */
  public Map<String, Boolean> getHmEditEnable() {
    return this.hmEditEnable;
  }

  /**
   * 设置缓存的编辑性
   * 
   * @param hmEditEnable
   */
  public void setHmEditEnable(Map<String, Boolean> hmEditEnable) {
    this.hmEditEnable = hmEditEnable;
  }

  /**
   * 销售订单表体不允许编辑字段
   */
  private static final String[] BODY_NOEDIT = new String[] {
    // 折扣类
    SaleOrderBVO.BDISCOUNTFLAG,
    // 服务类
    SaleOrderBVO.BLABORFLAG,
    // 整单折扣
    SaleOrderBVO.NDISCOUNTRATE,
    // 主单位
    SaleOrderBVO.CUNITID,
    // 本币
    SaleOrderBVO.CCURRENCYID,
    // 本币单价
    SaleOrderBVO.NQTTAXPRICE,
    SaleOrderBVO.NQTPRICE,
    SaleOrderBVO.NQTTAXNETPRICE,
    SaleOrderBVO.NQTNETPRICE,
    // 主本币单价
    SaleOrderBVO.NTAXPRICE,
    SaleOrderBVO.NPRICE,
    SaleOrderBVO.NTAXNETPRICE,
    SaleOrderBVO.NNETPRICE,
    // 本币金额
    SaleOrderBVO.NDISCOUNT,
    SaleOrderBVO.NTAXMNY,
    SaleOrderBVO.NMNY,

    // 原始询价价格
    SaleOrderBVO.NASKQTORIGNETPRICE,
    SaleOrderBVO.NASKQTORIGPRICE,
    SaleOrderBVO.NASKQTORIGTAXPRC,
    SaleOrderBVO.NASKQTORIGTXNTPRC,

    // 原币折扣额
    SaleOrderBVO.NORIGDISCOUNT,

    // 集团本币
    SaleOrderBVO.NGROUPMNY,
    SaleOrderBVO.NGROUPTAXMNY,
    // 全局本币
    SaleOrderBVO.NGLOBALTAXMNY,
    SaleOrderBVO.NGLOBALMNY,
    // 价格项字段
    SaleOrderBVO.CPRICEFORMID,
    SaleOrderBVO.CPRICEITEMTABLEID,
    SaleOrderBVO.CPRICEPOLICYID,
    SaleOrderBVO.CPROMOTPRICEID,
    // 询价字段
    SaleOrderBVO.NASKQTORIGTAXPRC,
    SaleOrderBVO.NASKQTORIGPRICE,
    SaleOrderBVO.NASKQTORIGTXNTPRC,
    SaleOrderBVO.NASKQTORIGNETPRICE,
    // 关闭字段
    SaleOrderBVO.BBARSETTLEFLAG,
    SaleOrderBVO.BBCOSTSETTLEFLAG,
    SaleOrderBVO.BBINVOICENDFLAG,
    SaleOrderBVO.BBOUTENDFLAG,
    SaleOrderBVO.BBSENDENDFLAG,
    SaleOrderBVO.BBSETTLEENDFLAG,
    // 捆绑标志
    SaleOrderBVO.BBINDFLAG,
    SaleOrderBVO.CBINDSRCID,
    // 货源安排
    SaleOrderBVO.BARRANGEDFLAG,
    SaleOrderBVO.CARRANGEPERSONID,
    SaleOrderBVO.TLASTARRANGETIME,
    // 合同
    SaleOrderBVO.CCTMANAGEBID,
    SaleOrderBVO.CCTMANAGEID,
    SaleOrderBVO.VCTCODE,
    SaleOrderBVO.VCTTYPE,
    // 换货行
    SaleOrderBVO.CEXCHANGESRCRETID,
    SaleOrderBVO.FRETEXCHANGE,
    // 源头信息
    SaleOrderBVO.CFIRSTBID,
    SaleOrderBVO.CFIRSTID,
    SaleOrderBVO.VFIRSTCODE,
    SaleOrderBVO.VFIRSTROWNO,
    SaleOrderBVO.VFIRSTTRANTYPE,
    SaleOrderBVO.VFIRSTTYPE,
    // 买赠行对应来源行ID
    SaleOrderBVO.CLARGESSSRCID,
    // 来源单据信息
    SaleOrderBVO.CSRCBID,
    SaleOrderBVO.CSRCID,
    SaleOrderBVO.VSRCCODE,
    SaleOrderBVO.VSRCROWNO,
    SaleOrderBVO.VSRCTRANTYPE,
    SaleOrderBVO.VSRCTYPE,
    // 赠品价格分摊
    SaleOrderBVO.FLARGESSTYPEFLAG,
    SaleOrderBVO.NLARGESSMNY,
    SaleOrderBVO.NLARGESSTAXMNY,
    // 行状态
    SaleOrderBVO.FROWSTATUS,
    // 累计安排数量
    SaleOrderBVO.NARRANGEMONUM, SaleOrderBVO.NARRANGEPOAPPNUM,
    SaleOrderBVO.NARRANGEPONUM, SaleOrderBVO.NARRANGESCORNUM,
    SaleOrderBVO.NARRANGETOAPPNUM, SaleOrderBVO.NARRANGETOORNUM,
    SaleOrderBVO.NTOTALARMNY, SaleOrderBVO.NTOTALARNUM,
    SaleOrderBVO.NTOTALCOSTNUM,
    SaleOrderBVO.NTOTALESTARMNY,
    SaleOrderBVO.NTOTALESTARNUM,
    SaleOrderBVO.NTOTALINVOICENUM,
    SaleOrderBVO.NTOTALNOTOUTNUM,
    SaleOrderBVO.NTOTALOUTNUM,
    SaleOrderBVO.NTOTALPAYMNY,
    SaleOrderBVO.NTOTALRETURNNUM,
    SaleOrderBVO.NTOTALRUSHNUM,
    SaleOrderBVO.NTOTALSENDNUM,
    SaleOrderBVO.NTOTALSIGNNUM,
    SaleOrderBVO.NTOTALTRADENUM,
    SaleOrderBVO.NTRANSLOSSNUM,

    // 冲减前价税合计
    SaleOrderBVO.NBFORIGSUBMNY,
    // 累计冲抵金额
    SaleOrderBVO.NORIGSUBMNY,
    // 预留数量
    SaleOrderBVO.NREQRSNUM,
    // 退货政策
    SaleOrderBVO.CRETPOLICYID,
    // 退货责任处理方式
    SaleOrderBVO.VRETURNMODE,

    // 各类参照OID
    SaleOrderBVO.CSETTLEORGID, SaleOrderBVO.CARORGID,
    SaleOrderBVO.CPROFITCENTERID, SaleOrderBVO.CSENDSTOCKORGID,
    SaleOrderBVO.CTRAFFICORGID,
    // 产品线\购销类型三角贸易
    SaleOrderBVO.CPRODLINEID,
    // 赠品兑付
    SaleOrderBVO.BLRGCASHFLAG
  };

  /**
   * 销售订单表头不允许编辑项
   */
  private static final String[] HEAD_NOEDIT = new String[] {
    // 各类参照OID
    SaleOrderHVO.CDEPTID,

    // 表头合计数量、总件数、总体积、总重量
    SaleOrderHVO.NTOTALNUM,
    SaleOrderHVO.NTOTALPIECE,
    SaleOrderHVO.NTOTALVOLUME,
    SaleOrderHVO.NTOTALWEIGHT,
    // 单据状态
    SaleOrderHVO.FSTATUSFLAG,
    // 关闭字段
    SaleOrderHVO.BARSETTLEFLAG, SaleOrderHVO.BCOSTSETTLEFLAG,
    SaleOrderHVO.BINVOICENDFLAG, SaleOrderHVO.BOUTENDFLAG,
    SaleOrderHVO.BSENDENDFLAG,
    // 是否冲抵
    SaleOrderHVO.BOFFSETFLAG,
    // 是否散户
    SaleOrderHVO.BFREECUSTFLAG,
    // 协同字段
    SaleOrderHVO.BCOOPTOPOFLAG, SaleOrderHVO.BPOCOOPTOMEFLAG,
    // 修订版本号
    SaleOrderHVO.IVERSION
  };

  private static final long serialVersionUID = 1L;

  List<String> clearHyperlink;

  // private CardEditSetter editsetter;

  // 销售订单前台缓存类
  private SaleOrderClientContext m30ClientContext =
      new SaleOrderClientContext();

  @Override
  public void addCardBodyRowChangedEvent(
      IAppEventHandler<CardBodyRowChangedEvent> l) {
    ((IAppModelEx) this.getModel()).addAppEventListener(
        CardBodyRowChangedEvent.class, l);
  }

  public List<String> getClearHyperlink() {
    return this.clearHyperlink;
  }

  public Map<String, CtBusinessVO> getCtMap() {
    return this.m30ClientContext.getCtMap();
  }

  public boolean getIsCashSale() {
    return this.m30ClientContext.getIsCashSale();
  }

  public SaleOrderClientContext getM30ClientContext() {
    return this.m30ClientContext;
  }

  private UFDate dbilldate;

  /**
   * 获得单据日期
   * 
   * @return UFDate
   */
  public UFDate getDbilldate() {
    return this.dbilldate;
  }

  /**
   * 设置单据日期
   * 
   * @param dbilldate
   */
  public void setDbilldate(UFDate dbilldate) {
    this.dbilldate = dbilldate;
  }

  @Override
  public OnhandDimParamVO getQryOnhandDim(int row) {
    IKeyValue util = new CardKeyValue(this.getBillCardPanel());
    OnhandDimParamVO paraVO = new OnhandDimParamVO();
    paraVO.setPk_group(this.getModel().getContext().getPk_group());
    paraVO.setCastunitid(util.getBodyStringValue(row, SaleOrderBVO.CASTUNITID));
    paraVO.setCmaterialoid(util.getBodyStringValue(row,
        SaleOrderBVO.CMATERIALID));
    paraVO.setCmaterialvid(util.getBodyStringValue(row,
        SaleOrderBVO.CMATERIALVID));
    paraVO.setCproductorid(util.getBodyStringValue(row,
        SaleOrderBVO.CPRODUCTORID));
    paraVO.setCprojectid(util.getBodyStringValue(row, SaleOrderBVO.CPROJECTID));
    paraVO.setCvendorid(util.getBodyStringValue(row, SaleOrderBVO.CVENDORID));
    paraVO.setCwarehouseid(util.getBodyStringValue(row,
        SaleOrderBVO.CSENDSTORDOCID));
    paraVO.setPk_batchcode(util.getBodyStringValue(row,
        SaleOrderBVO.PK_BATCHCODE));
    paraVO.setVbatchcode(util.getBodyStringValue(row, SaleOrderBVO.VBATCHCODE));
    paraVO
        .setPk_org(util.getBodyStringValue(row, SaleOrderBVO.CSENDSTOCKORGID));
    paraVO.setPk_org_v(util.getBodyStringValue(row,
        SaleOrderBVO.CSENDSTOCKORGVID));
    paraVO.setVbatchcode(util.getBodyStringValue(row, SaleOrderBVO.VBATCHCODE));
    paraVO.setVchangerate(util
        .getBodyStringValue(row, SaleOrderBVO.VCHANGERATE));
    paraVO.setVfree1(util.getBodyStringValue(row, SaleOrderBVO.VFREE1));
    paraVO.setVfree2(util.getBodyStringValue(row, SaleOrderBVO.VFREE2));
    paraVO.setVfree3(util.getBodyStringValue(row, SaleOrderBVO.VFREE3));
    paraVO.setVfree4(util.getBodyStringValue(row, SaleOrderBVO.VFREE4));
    paraVO.setVfree5(util.getBodyStringValue(row, SaleOrderBVO.VFREE5));
    paraVO.setVfree6(util.getBodyStringValue(row, SaleOrderBVO.VFREE6));
    paraVO.setVfree7(util.getBodyStringValue(row, SaleOrderBVO.VFREE7));
    paraVO.setVfree8(util.getBodyStringValue(row, SaleOrderBVO.VFREE8));
    paraVO.setVfree9(util.getBodyStringValue(row, SaleOrderBVO.VFREE9));
    paraVO.setVfree10(util.getBodyStringValue(row, SaleOrderBVO.VFREE10));
    return paraVO;
  }

  public SoBalanceVO getSobalancevo() {
    return this.m30ClientContext.getSobalancevo();
  }

  public OffsetTempVO getTempvo() {
    return this.m30ClientContext.getOffsetTempVO();
  }

  public UFDouble getThisGatheringMny() {
    return this.m30ClientContext.getThisGatheringMny();
  }

  @Override
  public void initUI() {
    super.initUI();

    BillCardPanel cardPanel = this.getBillCardPanel();
    cardPanel.getBodyPanel().setTotalRowShow(true);
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    SOBillTotalListener totallis = new SOBillTotalListener(keyValue);
    cardPanel.getBillModel().addTotalListener(totallis);
    // 初始化编辑性
    this.initEditEnable();
    if (SysInitGroupQuery.isICEnabled()) {
      ScmBatchAdaptor scmbach =
          new ScmBatchAdaptor("nc.ui.ic.batchcode.ref.BatchRefPane");
      UIRefPane uiref = scmbach.getRefPane();
      // 设置批次参照
      // BatchRefPane batchref = new BatchRefPane();
      this.getBillCardPanel().getBodyItem(SaleOrderBVO.VBATCHCODE)
          .setComponent(uiref);
    }
    // 缓存编辑性
    CardEditSetter editset = new CardEditSetter(this);
    editset.cacheEditEnable();

    // 清除卡片超链接
    this.clearHyperlink();

    // 初始化支持批改的字段
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

  public void setClearHyperlink(List<String> clearHyperlink) {
    this.clearHyperlink = clearHyperlink;
  }

  public void setCtMap(Map<String, CtBusinessVO> ctMap) {
    this.m30ClientContext.setCtMap(ctMap);
  }

  public void setIsCashSale(boolean isCashSale) {
    this.m30ClientContext.setIsCashSale(isCashSale);
  }

  public void setM30ClientContext(SaleOrderClientContext m30ClientContext) {
    this.m30ClientContext = m30ClientContext;
  }

  public void setSobalancevo(SoBalanceVO sobalancevo) {
    this.m30ClientContext.setSobalancevo(sobalancevo);
  }

  public void setTempvo(OffsetTempVO offvo) {
    this.m30ClientContext.setOffsetTempVO(offvo);
  }

  public void setThisGatheringMny(UFDouble thisGatheringMny) {
    this.m30ClientContext.setThisGatheringMny(thisGatheringMny);
  }

  @Override
  protected void initBillCardPanel() {
    super.initBillCardPanel();

    CardEditSetter editset = new CardEditSetter(this);
    // 设置固定编辑性
    editset.setCardFixEditFalse();
  }

  @Override
  protected void onAdd() {
    super.onAdd();
    // 清除缓存
    this.setTempvo(null);
    this.setSobalancevo(new SoBalanceVO());
    this.setThisGatheringMny(null);
    this.setIsCashSale(false);

    // 设置业务流程
    this.setBizType();
  }

  @Override
  protected void onEdit() {
    super.onEdit();
    this.setTempvo(null);
    this.setSobalancevo(new SoBalanceVO());
    this.setThisGatheringMny(null);
    this.setIsCashSale(false);
  }

  @Override
  protected void onNotEdit() {
    // 设置编辑性
    if (this.isEditable()) {
      CardEditSetter editset = new CardEditSetter(this);
      editset.setOriginalEdit();
    }
    super.onNotEdit();

  }

  @Override
  protected void setDefaultValue() {

    IKeyValue keyValue = new CardKeyValue(this.billCardPanel);
    // 1.表头
    // 销售组织
    String pk_org = this.getModel().getContext().getPk_org();
    keyValue.setHeadValue(SaleOrderHVO.PK_ORG, pk_org);
    String pk_org_v = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG_V);
    if (PubAppTool.isNull(pk_org_v)) {
      String newpkorg_v = OrgUnitPubService.getNewVIDByOrgID(pk_org);
      keyValue.setHeadValue(SaleOrderHVO.PK_ORG_V, newpkorg_v);
    } 
    
    // 集团keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG_V);
    String pk_group = AppUiContext.getInstance().getPkGroup();
    keyValue.setHeadValue(SaleOrderHVO.PK_GROUP, pk_group);

    // 设置部门与人员
    new SetDeptByLoginUserRule(keyValue, this.getModel().getContext(),
        SaleOrderHVO.CEMPLOYEEID, SaleOrderHVO.CDEPTID, SaleOrderHVO.CDEPTVID)
        .setPsnAndDept();

    // 单据日期
    UFDate busidate = AppContext.getInstance().getBusiDate();
    keyValue.setHeadValue(SaleOrderHVO.DBILLDATE, busidate);
    // 整单折扣
    keyValue.setHeadValue(SaleOrderHVO.NDISCOUNTRATE, SOConstant.ONEHUNDRED);
    // 单据状态
    keyValue.setHeadValue(SaleOrderHVO.FSTATUSFLAG,
        BillStatus.FREE.getIntegerValue());

    UFDate localend = busidate.asLocalEnd();
    // 2.表体
    for (int i = 0; i < keyValue.getBodyCount(); i++) {
      // 销售组织
      keyValue.setBodyValue(i, SaleOrderBVO.PK_ORG, pk_org);
      // 单据日期
      keyValue.setBodyValue(i, SaleOrderBVO.DBILLDATE, busidate);
      // 整单折扣
      keyValue.setBodyValue(i, SaleOrderBVO.NDISCOUNTRATE,
          SOConstant.ONEHUNDRED);
      // 单品折扣
      keyValue.setBodyValue(i, SaleOrderBVO.NITEMDISCOUNTRATE,
          SOConstant.ONEHUNDRED);
      // 计划发货日期、要求收货日期
      keyValue.setBodyValue(i, SaleOrderBVO.DSENDDATE, localend);
      keyValue.setBodyValue(i, SaleOrderBVO.DRECEIVEDATE, localend);
    }

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
    for (String key : SaleOrderBillForm.HEAD_NOEDIT) {
      BillItem headitem = this.getBillCardPanel().getHeadTailItem(key);
      if (null != headitem) {
        headitem.setEdit(false);
      }
    }
    // 表体不可编辑项
    for (String key : SaleOrderBillForm.BODY_NOEDIT) {
      BillItem bodyitem = this.getBillCardPanel().getBodyItem(key);
      if (null != bodyitem) {
        bodyitem.setEdit(false);
      }
    }
  }

  /**
   * 用户现存量面板
   */
  private OnhandPanelAdaptor adaptor;

  public OnhandPanelAdaptor getExtendedPanel() {
    return this.adaptor;
  }

  public void setExtendedPanel(OnhandPanelAdaptor adaptor) {
    this.adaptor = adaptor;
  }

  private void setBizType() {

    IKeyValue keyValue = new CardKeyValue(this.billCardPanel);
    String trantypeid = keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    String pk_org = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
    if (PubAppTool.isNull(trantypeid) || PubAppTool.isNull(pk_org)) {
      return;
    }
    this.billCardPanel.getBillData().loadEditHeadRelation(
        SaleOrderHVO.CTRANTYPEID);
    String typecode = keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    // 2.自动匹配业务流程
    String userid = AppContext.getInstance().getPkUser();
    String cbiztypeid = null;
    try {
      cbiztypeid =
          PfUtilClient.retBusitypeCanStart(SOBillType.Order.getCode(),
              typecode, pk_org, userid);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    // 设置业务流程
    keyValue.setHeadValue(SaleOrderHVO.CBIZTYPEID, cbiztypeid);
  }

/* (non Javadoc) 
 * @Title: showMeUp
 * @Description: TODO 
 * @see nc.ui.pubapp.uif2app.view.PubShowUpableBillForm#showMeUp() 
 */
@SuppressWarnings("static-access")
@Override
public void showMeUp() {
	// TODO 自动生成的方法存根
	this.getBillCardPanel().getBillModel().loadLoadRelationItemValue();
	try {
		Thread.currentThread().sleep(200L);
	} catch (InterruptedException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	super.showMeUp();
	this.getBillCardPanel().getBillModel().execLoadFormula();
	//因为有的字段双击后显示pk了，所以统一刷新一遍
}
}
