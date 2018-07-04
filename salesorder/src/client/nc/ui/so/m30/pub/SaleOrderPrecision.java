package nc.ui.so.m30.pub;

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
import nc.vo.pubapp.scale.TableScaleProcessor;
import nc.vo.pubapp.scale.TotalValueScale;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;

/**
 * 
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>销售订单精度处理工具类
 * </ul>
 * 
 * <p>
 * 
 * @version 本版本号 6.0
 * @since
 * @author fengjb
 * @time 2010-5-27 上午10:14:55
 */
public class SaleOrderPrecision {

  /**
   * 表体位置常量
   */
  public static final int BODY_POS = 1;

  /**
   * 表体页签
   */
  public static final String BODY_TABLECODE = "bodytable1";

  /**
   * 表头位置常量
   */
  public static final int HEAD_POS = 0;

  /**
   * 表头页签
   */
  public static final String HEAD_TABLECODE = "head";

  /** 表体 */
  // 数量(nunvoicenum为销售订单到发票转单界面上计算字段：可开票主数量)
  private static final String[] ASTNUMKEYS = new String[] {
    SaleOrderBVO.NASTNUM,"nunvoicenum"
  };

  // 表体折扣
  private static final String[] BODY_DISRATEKEYS = new String[] {
    SaleOrderBVO.NDISCOUNTRATE, SaleOrderBVO.NITEMDISCOUNTRATE
  };

  // 全局本币金额
  private static final String[] GLOBALMNYKEYS = new String[] {
    SaleOrderBVO.NGLOBALTAXMNY, SaleOrderBVO.NGLOBALMNY
  };

  // 集团本币金额
  private static final String[] GROUPMNYKEYS = new String[] {
    SaleOrderBVO.NGROUPTAXMNY, SaleOrderBVO.NGROUPMNY
  };

  /** 表头 */
  // 表头折扣
  private static final String[] HEAD_DISRATEKEYS = new String[] {
    SaleOrderHVO.NDISCOUNTRATE
  };

  // 换算率
  private static final String[] HSLKEYS = new String[] {
    SaleOrderBVO.VCHANGERATE, SaleOrderBVO.VQTUNITRATE
  };

  // 本币金额
  private static final String[] MNYKEYS = new String[] {
    SaleOrderBVO.NTAX, SaleOrderBVO.NMNY, SaleOrderBVO.NTAXMNY,
    SaleOrderBVO.NDISCOUNT, SaleOrderBVO.NCALTAXMNY,
  };

  // 主数量
  private static final String[] NUMKEYS = new String[] {
    SaleOrderBVO.NNUM, SaleOrderBVO.NTOTALARNUM, SaleOrderBVO.NTOTALCOSTNUM,
    SaleOrderBVO.NTOTALESTARNUM, SaleOrderBVO.NTOTALINVOICENUM,
    SaleOrderBVO.NTOTALNOTOUTNUM, SaleOrderBVO.NTOTALOUTNUM,
    SaleOrderBVO.NTOTALRETURNNUM, SaleOrderBVO.NTOTALRUSHNUM,
    SaleOrderBVO.NTOTALSENDNUM, SaleOrderBVO.NTOTALSIGNNUM,
    SaleOrderBVO.NTOTALTRADENUM, SaleOrderBVO.NREQRSNUM,
  };

  // 累计***主数量
  private static final String[] GATHERNUMKEYS = new String[] {
    SaleOrderBVO.NTOTALSENDNUM, SaleOrderBVO.NTOTALINVOICENUM,
    SaleOrderBVO.NTOTALOUTNUM, SaleOrderBVO.NTOTALNOTOUTNUM,
    SaleOrderBVO.NTOTALSIGNNUM, SaleOrderBVO.NTRANSLOSSNUM,
    SaleOrderBVO.NTOTALRUSHNUM, SaleOrderBVO.NTOTALESTARNUM,
    SaleOrderBVO.NTOTALARNUM, SaleOrderBVO.NTOTALCOSTNUM,
    SaleOrderBVO.NARRANGESCORNUM, SaleOrderBVO.NARRANGEPOAPPNUM,
    SaleOrderBVO.NARRANGETOORNUM, SaleOrderBVO.NARRANGETOAPPNUM,
    SaleOrderBVO.NARRANGEMONUM, SaleOrderBVO.NARRANGEPONUM,
    SaleOrderBVO.NTOTALPLONUM, SaleOrderBVO.NTOTALRETURNNUM,
    SaleOrderBVO.NTOTALTRADENUM
  };

  // 销售订单关闭管理数量
  private static final String[] CLOSEMAGENUMKEYS = new String[] {
    // 发货单数量
    SaleOrderBVO.NSENDUNFINISEDNUM, SaleOrderBVO.NSENDAUDITNUM,
    // 出库数量
    SaleOrderBVO.NOUTUNFINISEDNUM, SaleOrderBVO.NOUTAUDITNUM,
    // 开票数量
    SaleOrderBVO.NINVUNFINISEDNUM, SaleOrderBVO.NINVOICEAUDITNUM,
    // 结算数量
    SaleOrderBVO.NOUTNOTAUDITNUM, SaleOrderBVO.NNOTARNUM,
    SaleOrderBVO.NLOSSNOTAUDITNUM, SaleOrderBVO.NNOTCOSTNUM
  };

  // 原币数量（表头）
  private static final String[] NUMHEAD = new String[] {
    SaleOrderHVO.NTOTALNUM, SaleOrderHVO.NTOTALVOLUME,
    SaleOrderHVO.NTOTALWEIGHT,
  };

  // 原币金额(nuninvoicemny,可开票金额，销售订单到发票转单界面上计算字段)
  private static final String[] ORIGMNYKEYS = new String[] {
    SaleOrderBVO.NORIGMNY, SaleOrderBVO.NORIGTAXMNY,
    SaleOrderBVO.NORIGDISCOUNT, SaleOrderBVO.NBFORIGSUBMNY,
    SaleOrderBVO.NTOTALARMNY, SaleOrderBVO.NTOTALESTARMNY,
    SaleOrderBVO.NTOTALPAYMNY,SaleOrderBVO.NORIGSUBMNY,"nuninvoicemny"
  };

  // 原币金额（表头）
  private static final String[] ORIGMNYKEYSHEAD = new String[] {
    SaleOrderHVO.NTOTALMNY, SaleOrderHVO.NTOTALORIGMNY,
    SaleOrderHVO.NTOTALORIGSUBMNY, SaleOrderHVO.NPRECEIVEMNY,
    SaleOrderHVO.NPRECEIVEQUOTA, SaleOrderHVO.NRECEIVEDMNY,
    SaleOrderHVO.NTHISRECEIVEMNY, SaleOrderHVO.NLRGTOTALORIGMNY
  };

  // 件数
  private static final String[] PIECEKEY = new String[] {
    SaleOrderBVO.NPIECE
  };

  private static SaleOrderPrecision precision = new SaleOrderPrecision();

  // 单价
  private static final String[] PRICEKEYS = new String[] {
    SaleOrderBVO.NORIGTAXPRICE, SaleOrderBVO.NORIGPRICE,
    SaleOrderBVO.NORIGTAXNETPRICE, SaleOrderBVO.NORIGNETPRICE,
    SaleOrderBVO.NQTORIGTAXPRICE, SaleOrderBVO.NQTORIGPRICE,
    SaleOrderBVO.NQTORIGTAXNETPRC, SaleOrderBVO.NQTORIGNETPRICE,
    SaleOrderBVO.NACCPRICE, SaleOrderBVO.NMFFILEPRICE
  
  };
  
  private static final String[] netpricekeys=new String[]{  SaleOrderBVO.NTAXPRICE, SaleOrderBVO.NPRICE, SaleOrderBVO.NTAXNETPRICE,
    SaleOrderBVO.NNETPRICE, SaleOrderBVO.NQTTAXPRICE, SaleOrderBVO.NQTPRICE,
    SaleOrderBVO.NQTTAXNETPRICE, SaleOrderBVO.NQTNETPRICE,
    SaleOrderBVO.NASKQTORIGNETPRICE, SaleOrderBVO.NASKQTORIGPRICE,
    SaleOrderBVO.NASKQTORIGTAXPRC, SaleOrderBVO.NASKQTORIGTXNTPRC};

  // 报价数量
  private static final String[] QTNUMKEYS = new String[] {
    SaleOrderBVO.NQTUNITNUM
  };

  // 表体税率
  private static final String[] TAXRATEKEY = new String[] {
    SaleOrderBVO.NTAXRATE
  };

  // 体积
  private static final String[] VOLUMEKEY = new String[] {
    SaleOrderBVO.NVOLUME
  };

  // 重量
  private static final String[] WEIGHTKEY = new String[] {
    SaleOrderBVO.NWEIGHT
  };

  /** 汇率精度 */
  // 折本汇率
  private FieldInfo exchangeRate = new FieldInfo(SaleOrderBVO.NEXCHANGERATE,
      SaleOrderPrecision.BODY_POS, SaleOrderPrecision.BODY_TABLECODE);

  // 全局折本汇率
  private FieldInfo globalExchgRate = new FieldInfo(
      SaleOrderBVO.NGLOBALEXCHGRATE, SaleOrderPrecision.BODY_POS,
      SaleOrderPrecision.BODY_TABLECODE);

  // 集团折本汇率
  private FieldInfo groupExchgRate = new FieldInfo(
      SaleOrderBVO.NGROUPEXCHGRATE, SaleOrderPrecision.BODY_POS,
      SaleOrderPrecision.BODY_TABLECODE);

  // 原币
  private FieldInfo localOrigCurr = new FieldInfo(SaleOrderHVO.CORIGCURRENCYID,
      SaleOrderPrecision.HEAD_POS, SaleOrderPrecision.HEAD_TABLECODE);

  // 组织本币
  private FieldInfo orgCurr = new FieldInfo(SaleOrderBVO.CCURRENCYID,
      SaleOrderPrecision.BODY_POS, SaleOrderPrecision.BODY_TABLECODE);

  // 财务组织
  private FieldInfo settleOrg = new FieldInfo(SaleOrderBVO.CSETTLEORGID,
      SaleOrderPrecision.BODY_POS, SaleOrderPrecision.BODY_TABLECODE);

  /**
   * 
   * PreOrderPrecision 的构造子
   */
  private SaleOrderPrecision() {
    //
  }

  /**
   * 初始化
   * 
   * @return SaleOrderPrecision
   */
  public static SaleOrderPrecision getInstance() {
    return SaleOrderPrecision.precision;
  }

  /**
   * 
   * 方法功能描述：设置卡片界面精度。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_group
   * @param cardpanel
   *          <p>
   * @author fengjb
   * @time 2010-5-26 下午04:55:35
   */
  public void setCardPrecision(String pk_group, BillCardPanel cardpanel) {
    BillScaleProcessor scaleprocess =
        new CardPaneScaleProcessor(pk_group, cardpanel);
    TotalValueScale totalscale = new TotalValueScaleProcessor(cardpanel);
    this.setBillPrecision(scaleprocess, totalscale);
  }

  /**
   * 
   * 方法功能描述：设置列表界面精度。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_group
   * @param listpanel
   *          <p>
   * @author fengjb
   * @time 2010-5-26 下午04:55:55
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
        listpanel.getHeadBillModel(), SaleOrderPrecision.NUMHEAD);
  }

  /**
   * 
   * 方法功能描述：设置集中安排界面的精度。
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
   * 单表精度处理
   * 
   * @param pk_group
   * 
   * @param panel
   */
  public void setSingleTableScale(String pk_group, BillListPanel panel) {
    this.setModelPrecision(pk_group, panel.getHeadBillModel());
    // 设置列表界面的合计数量精度
    this.setListHeadNumTrimZeroPrecison(panel);
  }

  /**
   * 
   * 方法功能描述：设置单据精度。
   * <p>
   * <b>参数说明</b>
   * 
   * @param scaleprocess
   *          <p>
   * @author fengjb
   * @time 2010-5-27 上午10:13:48
   */
  private void setBillPrecision(BillScaleProcessor scaleprocess,
      TotalValueScale totalscale) {
    // 集团本币金额
    scaleprocess.setGroupLocMnyCtlInfo(SaleOrderPrecision.GROUPMNYKEYS,
        PosEnum.body, null);
    // 全局本币金额
    scaleprocess.setGlobalLocMnyCtlInfo(SaleOrderPrecision.GLOBALMNYKEYS,
        PosEnum.body, null);
    // 换算率
    scaleprocess.setHslCtlInfo(SaleOrderPrecision.HSLKEYS, PosEnum.body, null);
    // 单价
    scaleprocess.setPriceCtlInfo(SaleOrderPrecision.PRICEKEYS, PosEnum.body,
        null,SaleOrderHVO.CORIGCURRENCYID, PosEnum.head,null);
    
    scaleprocess.setPriceCtlInfo(SaleOrderPrecision.netpricekeys, PosEnum.body,
        null,SaleOrderBVO.CCURRENCYID, PosEnum.body,null);
    
    // 数量
    scaleprocess.setNumCtlInfo(SaleOrderPrecision.ASTNUMKEYS, PosEnum.body,
        null, SaleOrderBVO.CASTUNITID, PosEnum.body, null);
    // 主数量
    scaleprocess.setNumCtlInfo(SaleOrderPrecision.NUMKEYS, PosEnum.body, null,
        SaleOrderBVO.CUNITID, PosEnum.body, null);
    // 累计***主数量
    scaleprocess.setNumCtlInfo(SaleOrderPrecision.GATHERNUMKEYS, PosEnum.body,
        null, SaleOrderBVO.CUNITID, PosEnum.body, null);
    // 报价数量
    scaleprocess.setNumCtlInfo(SaleOrderPrecision.QTNUMKEYS, PosEnum.body,
        null, SaleOrderBVO.CQTUNITID, PosEnum.body, null);
    // 本币金额
    scaleprocess.setMnyCtlInfo(SaleOrderPrecision.MNYKEYS, PosEnum.body, null,
        SaleOrderBVO.CCURRENCYID, PosEnum.body, null);
    // 原币金额
    scaleprocess.setMnyCtlInfo(SaleOrderPrecision.ORIGMNYKEYS, PosEnum.body,
        null, SaleOrderHVO.CORIGCURRENCYID, PosEnum.head, null);

    // 原币金额（表头）
    scaleprocess.setMnyCtlInfo(SaleOrderPrecision.ORIGMNYKEYSHEAD,
        PosEnum.head, null, SaleOrderHVO.CORIGCURRENCYID, PosEnum.head, null);
    // 表头折扣
    scaleprocess.setSaleDiscountCtlInfo(SaleOrderPrecision.HEAD_DISRATEKEYS,
        PosEnum.head, null);
    // 表体折扣
    scaleprocess.setSaleDiscountCtlInfo(SaleOrderPrecision.BODY_DISRATEKEYS,
        PosEnum.body, null);

    // 表头合计数量
    totalscale.setHeadTailKeys(SaleOrderPrecision.NUMHEAD);

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
    scaleprocess.setTaxRateCtlInfo(SaleOrderPrecision.TAXRATEKEY, PosEnum.body,
        null);
    // 重量
    scaleprocess.setWeightCtlInfo(SaleOrderPrecision.WEIGHTKEY, PosEnum.body,
        null);
    // 体积
    scaleprocess.setVolumnCtlInfo(SaleOrderPrecision.VOLUMEKEY, PosEnum.body,
        null);
    // 件数
    scaleprocess.setUnitCtlInfo(SaleOrderPrecision.PIECEKEY, PosEnum.body,
        null, SaleOrderBVO.CMATERIALID, PosEnum.body, null);

    scaleprocess.process();
  }

  /**
   * 
   * 方法功能描述：设置表格精度。
   * <p>
   * <b>参数说明</b>
   * 
   * @param scaleprocess
   *          <p>
   * @author fengjb
   * @time 2010-5-27 上午10:13:35
   */
  private void setTablePrecision(TableScaleProcessor scaleprocess) {
    // 数量
    scaleprocess.setNumCtlInfo(SaleOrderPrecision.ASTNUMKEYS,
        SaleOrderBVO.CASTUNITID);
    // 主数量
    scaleprocess
        .setNumCtlInfo(SaleOrderPrecision.NUMKEYS, SaleOrderBVO.CUNITID);
    // 累计***主数量
    scaleprocess.setNumCtlInfo(SaleOrderPrecision.GATHERNUMKEYS,
        SaleOrderBVO.CUNITID);

    // 订单关闭管理数量
    scaleprocess.setNumCtlInfo(SaleOrderPrecision.CLOSEMAGENUMKEYS,
        SaleOrderBVO.CUNITID);

    // 报价数量
    scaleprocess.setNumCtlInfo(SaleOrderPrecision.QTNUMKEYS,
        SaleOrderBVO.CQTUNITID);
    // 本币金额
    scaleprocess.setMnyCtlInfo(SaleOrderPrecision.MNYKEYS,
        SaleOrderBVO.CCURRENCYID);
    // 原币金额
    scaleprocess.setMnyCtlInfo(SaleOrderPrecision.ORIGMNYKEYS,
        SaleOrderHVO.CORIGCURRENCYID);

    // 集团本币金额
    scaleprocess.setGroupLocMnyCtlInfo(SaleOrderPrecision.GROUPMNYKEYS);
    // 全局本币金额
    scaleprocess.setGlobalLocMnyCtlInfo(SaleOrderPrecision.GLOBALMNYKEYS);
    // 换算率
    scaleprocess.setHslCtlInfo(SaleOrderPrecision.HSLKEYS);
    // 单价
    scaleprocess.setPriceCtlInfo(SaleOrderPrecision.PRICEKEYS,SaleOrderHVO.CORIGCURRENCYID);
    
    // 本币单价
    scaleprocess.setPriceCtlInfo(SaleOrderPrecision.netpricekeys,SaleOrderBVO.CCURRENCYID);
    
    // 税率
    scaleprocess.setTaxRateCtlInfo(SaleOrderPrecision.TAXRATEKEY);
    // 折扣
    scaleprocess.setSaleDiscountCtlInfo(SaleOrderPrecision.BODY_DISRATEKEYS);
    // 体积
    scaleprocess.setVolumnCtlInfo(SaleOrderPrecision.VOLUMEKEY);
    // 重量
    scaleprocess.setWeightCtlInfo(SaleOrderPrecision.WEIGHTKEY);
    // 件数
    scaleprocess.setUnitCtlInfo(SaleOrderPrecision.PIECEKEY,
        SaleOrderBVO.CMATERIALID);
    scaleprocess.setMnyCtlInfo(SaleOrderPrecision.ORIGMNYKEYSHEAD,
        SaleOrderHVO.CORIGCURRENCYID);
    scaleprocess.process();
  }
}
