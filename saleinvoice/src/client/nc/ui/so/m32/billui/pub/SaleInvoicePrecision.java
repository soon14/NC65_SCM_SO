package nc.ui.so.m32.billui.pub;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.scale.BillModelScaleProcessor;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.ui.pubapp.scale.TotalValueScaleProcessor;
import nc.ui.so.pub.listener.ListHeadNumTrimZeroPrecisonUtil;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.FieldInfo;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TotalValueScale;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

/**
 * 销售发票精度处理类
 * 
 * @since 6.3
 * @version 2012-12-21 上午11:03:37
 * @author yaogj
 */
public class SaleInvoicePrecision {

  /**
   * 表头页签编码
   */
  public static final String BODY_TABLECODE = "saleinvoice_b";

  /**
   * 表头
   */
  public static final int HEAD_POS = 0;

  /**
   * 表头页签编码
   */
  public static final String HEAD_TABLECODE = "saleinvoice_h";

  // 数量
  private static final String[] ASTNUMKEY = new String[] {
    SaleInvoiceBVO.NASTNUM
  };

  // 扣率
  private static final String[] DISCOUNTRATEKEY = new String[] {
    SaleInvoiceBVO.NDISCOUNTRATE, SaleInvoiceBVO.NITEMDISCOUNTRATE,
    SaleInvoiceBVO.NINVOICEDISRATE
  };

  // 全局本币金额
  private static final String[] GLOBALMNYKEY = new String[] {
    SaleInvoiceBVO.NGLOBALTAXMNY, SaleInvoiceBVO.NGLOBALMNY
  };

  // 集团本币金额
  private static final String[] GROUPMNYKEY = new String[] {
    SaleInvoiceBVO.NGROUPTAXMNY, SaleInvoiceBVO.NGROUPMNY
  };

  // 表头折扣
  private static final String[] HEAD_DISRATEKEYS = new String[] {
    SaleInvoiceHVO.NHVOICEDISRATE
  };

  // 换算率精度
  private static final String[] HSLKEY = new String[] {
    SaleInvoiceBVO.VCHANGERATE, SaleInvoiceBVO.VQTUNITRATE
  };

  private static SaleInvoicePrecision instance = new SaleInvoicePrecision();

  // 本币金额
  private static final String[] MNYKEY = new String[] {
    SaleInvoiceBVO.NTAX, SaleInvoiceBVO.NMNY, SaleInvoiceBVO.NTAXMNY,
    SaleInvoiceBVO.NDISCOUNT, SaleInvoiceBVO.NCALTAXMNY
  };

  // 主数量\累计应发未出库数量\累计成本结算数量\累计确认应收数量\累计出库数量
  private static final String[] NUMKEY = new String[] {
    SaleInvoiceBVO.NNUM, SaleInvoiceBVO.NSHOULDOUTNUM,
    SaleInvoiceBVO.NTOTALCOSTNUM, SaleInvoiceBVO.NTOTALINCOMENUM,
    SaleInvoiceBVO.NTOTALOUTNUM
  };

  // 原币金额
  private static final String[] ORIGMNYKEY = new String[] {
    SaleInvoiceBVO.NORIGMNY, SaleInvoiceBVO.NORIGTAXMNY,
    SaleInvoiceBVO.NORIGDISCOUNT, SaleInvoiceBVO.NORIGSUBMNY,
    SaleInvoiceBVO.NTOTALINCOMEMNY, SaleInvoiceBVO.NTOTALPAYMNY,
    "nbforigsubmny"
  };

  // 表头原币金额
  private static final String[] ORIGMNYKEYHEAD = new String[] {
    SaleInvoiceHVO.NTOTALORIGMNY, SaleInvoiceHVO.NTOTALORIGSUBMNY,"ntotalmny"
  };

  // 单价
  private static final String[] PRICEKEY = new String[] {
    SaleInvoiceBVO.NORIGTAXPRICE, SaleInvoiceBVO.NORIGPRICE,
    SaleInvoiceBVO.NORIGTAXNETPRICE, SaleInvoiceBVO.NORIGNETPRICE,
    SaleInvoiceBVO.NQTORIGTAXPRICE, SaleInvoiceBVO.NQTORIGPRICE,
    SaleInvoiceBVO.NQTORIGTAXNETPRC, SaleInvoiceBVO.NQTORIGNETPRICE,
  };

  private static final String[] NETPRICEKEYS=new String[]{
    SaleInvoiceBVO.NTAXPRICE, SaleInvoiceBVO.NPRICE,
    SaleInvoiceBVO.NTAXNETPRICE, SaleInvoiceBVO.NNETPRICE,
    SaleInvoiceBVO.NQTTAXPRICE, SaleInvoiceBVO.NQTPRICE,
    SaleInvoiceBVO.NQTTAXNETPRICE, SaleInvoiceBVO.NQTNETPRICE
  };
  
  // 报价数量
  private static final String[] QTNUMKEY = new String[] {
    SaleInvoiceBVO.NQTUNITNUM
  };

  // 表体税率
  private static final String[] TAXRATEKEY = new String[] {
    SaleInvoiceBVO.NTAXRATE
  };

  // 折本汇率
  private FieldInfo exchangeRate = new FieldInfo(SaleInvoiceHVO.NEXCHANGERATE,
      SaleInvoicePrecision.HEAD_POS, SaleInvoicePrecision.HEAD_TABLECODE);

  // 全局折本汇率
  private FieldInfo globalExchgRate = new FieldInfo(
      SaleInvoiceHVO.NGLOBALEXCHGRATE, SaleInvoicePrecision.HEAD_POS,
      SaleInvoicePrecision.HEAD_TABLECODE);

  // 集团折本汇率
  private FieldInfo groupExchgRate = new FieldInfo(
      SaleInvoiceHVO.NGROUPEXCHGRATE, SaleInvoicePrecision.HEAD_POS,
      SaleInvoicePrecision.HEAD_TABLECODE);

  // 原币
  private FieldInfo localOrigCurr = new FieldInfo(
      SaleInvoiceHVO.CORIGCURRENCYID, SaleInvoicePrecision.HEAD_POS, null);

  // 组织本币
  private FieldInfo orgCurr = new FieldInfo(SaleInvoiceHVO.CCURRENCYID,
      SaleInvoicePrecision.HEAD_POS, null);

  // 财务组织
  private FieldInfo settleOrg = new FieldInfo(SaleInvoiceHVO.PK_ORG,
      SaleInvoicePrecision.HEAD_POS, null);

  /**
   * SaleInvoicePrecision 的构造子
   */
  private SaleInvoicePrecision() {
    // 缺省构造方法
  }

  /**
   * 
   * @return 初始化类
   */
  public static SaleInvoicePrecision getInstance() {
    return SaleInvoicePrecision.instance;
  }

  /**
   * 方法功能描述：設置卡片界面精度。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_group
   *          集团ID
   * @param cardpanel
   *          卡片panel
   *          <p>
   * @author 冯加滨
   * @time 2010-4-1 下午02:14:33
   */
  public void setCardPrecision(String pk_group, BillCardPanel cardpanel) {
    BillScaleProcessor scaleprocess =
        new CardPaneScaleProcessor(pk_group, cardpanel);
    TotalValueScale totalscale = new TotalValueScaleProcessor(cardpanel);
    this.setBillPrecision(scaleprocess, totalscale);
  }

  /**
   * 方法功能描述：设置列表界面精度。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_group
   * @param listpanel
   *          <p>
   * @author 冯加滨
   * @time 2010-4-1 下午02:17:10
   */
  public void setListPrecision(String pk_group, BillListPanel listpanel) {
    BillScaleProcessor scaleprocess =
        new ListPaneScaleProcessor(pk_group, listpanel);
    TotalValueScale totalscale = new TotalValueScaleProcessor(listpanel);
    this.setBillPrecision(scaleprocess, totalscale);
    // 设置列表界面的合计数量精度
    this.setListHeadNumTrimZeroPrecison(listpanel);
  }

  private void setListHeadNumTrimZeroPrecison(BillListPanel listpanel) {
    ListHeadNumTrimZeroPrecisonUtil.addHeadNumTrimZeroPrecisonListener(
        listpanel.getHeadBillModel(), new String[] {
          SaleInvoiceHVO.NTOTALASTNUM
        });
  }

  /**
   * 方法功能描述：设置拉平界面的精度。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_group
   * @param model
   *          <p>
   * @author fengjb
   * @time 2010-5-27 上午09:59:48
   */
  public void setModelPrecision(String pk_group, BillModel model) {
    BillModelScaleProcessor scaleprocess =
        new BillModelScaleProcessor(pk_group, model);
    this.setTablePrecision(scaleprocess);
  }

  /**
   * 
   * @param scale 单表精度处理方法
   */
  public void setScaleForSingleTable(BillScaleProcessor scale) {
    if (scale != null) {
      this.setBillPrecision(scale);
    }
  }

  /**
   * 单表精度处理
   * 
   * @param pk_group 集团
   * @param panel 单表
   */
  public void setSingleTableScale(String pk_group, BillListPanel panel) {
    this.setModelPrecision(pk_group, panel.getHeadBillModel());
    // this.setScaleForSingleTable(new ListPaneScaleProcessor(pk_group, panel));
  }

  private void setBillPrecision(BillScaleProcessor scaleprocess) {
    this.setBillPrecision(scaleprocess, null);
  }

  /**
   * 方法功能描述：设置单据精度。
   * <p>
   * <b>参数说明</b>
   * 
   * @param scaleprocess
   *          <p>
   * @author fengjb
   * @time 2010-8-17 下午08:19:49
   */
  private void setBillPrecision(BillScaleProcessor scaleprocess,
      TotalValueScale totalscale) {
    // 集团本币金额
    scaleprocess.setGroupLocMnyCtlInfo(SaleInvoicePrecision.GROUPMNYKEY,
        PosEnum.body, null);
    // 本币金额
    scaleprocess.setOrgLocMnyCtlInfo(SaleInvoicePrecision.MNYKEY, PosEnum.body,
        null);
    // 全局本币金额
    scaleprocess.setGlobalLocMnyCtlInfo(SaleInvoicePrecision.GLOBALMNYKEY,
        PosEnum.body, null);
    // 换算率精度
    scaleprocess.setHslCtlInfo(SaleInvoicePrecision.HSLKEY, PosEnum.body, null);
    // 单价
    scaleprocess.setPriceCtlInfo(SaleInvoicePrecision.PRICEKEY, PosEnum.body,
        null,SaleInvoiceHVO.CORIGCURRENCYID,PosEnum.head,null);
    
    // 单价
    scaleprocess.setPriceCtlInfo(SaleInvoicePrecision.NETPRICEKEYS, PosEnum.body,
        null,SaleInvoiceHVO.CCURRENCYID,PosEnum.head,null);
    
    // 数量
    scaleprocess.setNumCtlInfo(SaleInvoicePrecision.ASTNUMKEY, PosEnum.body,
        null, SaleInvoiceBVO.CASTUNITID, PosEnum.body, null);
    // 主数量
    scaleprocess.setNumCtlInfo(SaleInvoicePrecision.NUMKEY, PosEnum.body, null,
        SaleInvoiceBVO.CUNITID, PosEnum.body, null);
    // 报价数量
    scaleprocess.setNumCtlInfo(SaleInvoicePrecision.QTNUMKEY, PosEnum.body,
        null, SaleInvoiceBVO.CQTUNITID, PosEnum.body, null);
    // 表头折扣
    scaleprocess.setSaleDiscountCtlInfo(SaleInvoicePrecision.HEAD_DISRATEKEYS,
        PosEnum.head, null);
    // 原币金额
    scaleprocess.setMnyCtlInfo(SaleInvoicePrecision.ORIGMNYKEY, PosEnum.body,
        null, SaleInvoiceHVO.CORIGCURRENCYID, PosEnum.head, null);

    // 原币金额（表头）
    scaleprocess.setMnyCtlInfo(SaleInvoicePrecision.ORIGMNYKEYHEAD,
        PosEnum.head, null, SaleInvoiceHVO.CORIGCURRENCYID, PosEnum.head, null);
    // 扣率
    scaleprocess.setSaleDiscountCtlInfo(SaleInvoicePrecision.DISCOUNTRATEKEY,
        PosEnum.body, null);

    // 折本汇率
    scaleprocess.setOrgExchangeCtlInfo(this.exchangeRate, this.localOrigCurr,
        this.orgCurr, this.settleOrg);
    // 全局本位币汇率
    scaleprocess.setGlobalExchangeCtlInfo(this.globalExchgRate,
        this.localOrigCurr, this.orgCurr);
    // 集团本位币汇率
    scaleprocess.setGroupExchangeCtlInfo(this.groupExchgRate,
        this.localOrigCurr, this.orgCurr);

    // 税率
    scaleprocess.setTaxRateCtlInfo(SaleInvoicePrecision.TAXRATEKEY,
        PosEnum.body, null);
    // 表头数量合计
    if (totalscale != null) {
      totalscale.setHeadTailKeys(new String[] {
        SaleInvoiceHVO.NTOTALASTNUM
      });
    }
    scaleprocess.process();

  }

  /**
   * 方法功能描述：设置表格精度。
   * <p>
   * <b>参数说明</b>
   * 
   * @param scaleprocess
   *          <p>
   * @author fengjb
   * @time 2010-5-27 上午10:13:35
   */
  private void setTablePrecision(BillModelScaleProcessor scaleprocess) {
    // 集团本币金额
    scaleprocess.setGroupLocMnyCtlInfo(SaleInvoicePrecision.GROUPMNYKEY);
    // 本币金额
    scaleprocess.setMnyCtlInfo(SaleInvoicePrecision.MNYKEY,
        SaleInvoiceHVO.CCURRENCYID);
    // 全局本币金额
    scaleprocess.setGlobalLocMnyCtlInfo(SaleInvoicePrecision.GLOBALMNYKEY);
    // 换算率
    scaleprocess.setHslCtlInfo(SaleInvoicePrecision.HSLKEY);
    // 单价
    scaleprocess.setPriceCtlInfo(SaleInvoicePrecision.PRICEKEY,SaleInvoiceHVO.CORIGCURRENCYID);
    
    // 本币单价
    scaleprocess.setPriceCtlInfo(SaleInvoicePrecision.NETPRICEKEYS,SaleInvoiceHVO.CCURRENCYID);
    
    
    // 数量
    scaleprocess.setNumCtlInfo(SaleInvoicePrecision.ASTNUMKEY,
        SaleInvoiceBVO.CASTUNITID);
    // 主数量
    scaleprocess.setNumCtlInfo(SaleInvoicePrecision.NUMKEY,
        SaleInvoiceBVO.CUNITID);
    // 报价数量
    scaleprocess.setNumCtlInfo(SaleInvoicePrecision.QTNUMKEY,
        SaleInvoiceBVO.CQTUNITID);

    // 表头折扣
    scaleprocess.setSaleDiscountCtlInfo(SaleInvoicePrecision.HEAD_DISRATEKEYS);

    // 原币金额
    scaleprocess.setMnyCtlInfo(SaleInvoicePrecision.ORIGMNYKEY,
        SaleInvoiceHVO.CORIGCURRENCYID);

    // 原币金额（表头）
    scaleprocess.setMnyCtlInfo(SaleInvoicePrecision.ORIGMNYKEYHEAD,
        SaleInvoiceHVO.CORIGCURRENCYID);
    // 折扣率
    scaleprocess.setSaleDiscountCtlInfo(SaleInvoicePrecision.DISCOUNTRATEKEY);

    // // 折本汇率
    // scaleprocess.setOrgExchangeCtlInfo(this.exchangeRate, this.localOrigCurr,
    // this.orgCurr, this.settleOrg);
    // // 全局本位币汇率
    // scaleprocess.setGlobalExchangeCtlInfo(this.globalExchgRate,
    // this.localOrigCurr, this.orgCurr);
    // // 集团本位币汇率
    // scaleprocess.setGroupExchangeCtlInfo(this.groupExchgRate,
    // this.localOrigCurr, this.orgCurr);

    // 税率
    scaleprocess.setTaxRateCtlInfo(SaleInvoicePrecision.TAXRATEKEY);

    scaleprocess.process();
  }
}
