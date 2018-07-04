package nc.ui.so.m4331.billui.util;

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
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发货单精度工具类
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since
 * @author fengjb
 * @time 2010-7-6 上午11:24:14
 */
public class DeliveryPrecision {

  public static final int BODY_POS = 1;

  public static final String BODY_TABLECODE = "cdeliverybid";

  public static final int HEAD_POS = 0;

  public static final String HEAD_TABLECODE = "delivery";

  // 数量
  private static String[] astnumkeys = new String[] {
    DeliveryBVO.NASTNUM
  };

  // 整单折扣、单品折扣
  private static String[] bdiscountkeys = new String[] {
    DeliveryBVO.NDISCOUNTRATE, DeliveryBVO.NITEMDISCOUNTRATE
  };

  // 表体件数
  private static String[] bodypieceKey = new String[] {
    DeliveryBVO.NPIECE
  };

  // 表体体积
  private static String[] bodyvolKey = new String[] {
    DeliveryBVO.NVOLUME
  };

  // 表体重量
  private static String[] bodyweightKey = new String[] {
    DeliveryBVO.NWEIGHT
  };

  // 全局本币金额
  private static final String[] GLOBALMNYKEYS = new String[] {
    DeliveryBVO.NGLOBALTAXMNY, DeliveryBVO.NGLOBALMNY
  };

  // 集团本币金额
  private static final String[] GROUPMNYKEYS = new String[] {
    DeliveryBVO.NGROUPTAXMNY, DeliveryBVO.NGROUPMNY
  };

  // 换算率
  private static String[] hslkeys = new String[] {
    DeliveryBVO.VCHANGERATE, DeliveryBVO.VQTUNITRATE
  };

  // 本币金额
  private static String[] mnykeys = new String[] {
    DeliveryBVO.NTAX, DeliveryBVO.NMNY, DeliveryBVO.NTAXMNY,
    DeliveryBVO.NDISCOUNT, DeliveryBVO.NCALTAXMNY
  };

  // 主数量
  private static String[] numkeys = new String[] {
    DeliveryBVO.NNUM, DeliveryBVO.NREQRSNUM, DeliveryBVO.NTOTALREPORTNUM,
    DeliveryBVO.NTOTALUNELIGNUM, DeliveryBVO.NTOTALELIGNUM
  };

  // 数量（表头）
  private static final String[] NUMHEAD = new String[] {
    // 总数量，总件数，总重量，总体积
    DeliveryHVO.NTOTALASTNUM, DeliveryHVO.NTOTALPIECE,
    DeliveryHVO.NTOTALWEIGHT, DeliveryHVO.NTOTALVOLUME
  };

  // 原币金额
  private static String[] origmnykeys = new String[] {
    DeliveryBVO.NORIGMNY, DeliveryBVO.NORIGTAXMNY, DeliveryBVO.NORIGDISCOUNT
  };

  private static DeliveryPrecision precision = new DeliveryPrecision();

  // 单价
  private static String[] pricekeys = new String[] {
    // 主单位原币含税单价、主单位原币无税单价
    DeliveryBVO.NORIGTAXPRICE,
    DeliveryBVO.NORIGPRICE,
    // 主单位原币含税净价、主单位原币无税净价
    DeliveryBVO.NORIGTAXNETPRICE,
    DeliveryBVO.NORIGNETPRICE,
    // 报价单位原币含税单价、报价单位原币无税单价
    DeliveryBVO.NQTORIGTAXPRICE,
    DeliveryBVO.NQTORIGPRICE,
    // 报价单位原币含税净价、报价单位原币无税净价
    DeliveryBVO.NQTORIGTAXNETPRC, DeliveryBVO.NQTORIGNETPRICE,

  
  };
  
  private static String[] netpricekeys=new String[]{
    DeliveryBVO.NTAXPRICE, DeliveryBVO.NPRICE, DeliveryBVO.NTAXNETPRICE,
    DeliveryBVO.NNETPRICE, DeliveryBVO.NQTTAXPRICE, DeliveryBVO.NQTPRICE,
    DeliveryBVO.NQTTAXNETPRICE, DeliveryBVO.NQTNETPRICE
  };

  // 报价数量
  private static String[] qtnumkeys = new String[] {
    DeliveryBVO.NQTUNITNUM
  };

  // 表体税率
  private static String[] taxratekey = new String[] {
    DeliveryBVO.NTAXRATE
  };

  // 折本汇率
  private FieldInfo exchangeRate = new FieldInfo(DeliveryBVO.NEXCHANGERATE,
      DeliveryPrecision.BODY_POS, DeliveryPrecision.BODY_TABLECODE);

  // 全局折本汇率
  private FieldInfo globalExchgRate = new FieldInfo(
      DeliveryBVO.NGLOBALEXCHGRATE, DeliveryPrecision.BODY_POS,
      DeliveryPrecision.BODY_TABLECODE);

  // 集团折本汇率
  private FieldInfo groupExchgRate = new FieldInfo(DeliveryBVO.NGROUPEXCHGRATE,
      DeliveryPrecision.BODY_POS, DeliveryPrecision.BODY_TABLECODE);

  // 原币
  private FieldInfo localOrigCurr = new FieldInfo(DeliveryBVO.CORIGCURRENCYID,
      DeliveryPrecision.BODY_POS, DeliveryPrecision.BODY_TABLECODE);

  // 组织本币
  private FieldInfo orgCurr = new FieldInfo(DeliveryBVO.CCURRENCYID,
      DeliveryPrecision.BODY_POS, DeliveryPrecision.BODY_TABLECODE);

  // 财务组织
  private FieldInfo settleOrg = new FieldInfo(DeliveryBVO.CSETTLEORGID,
      DeliveryPrecision.BODY_POS, DeliveryPrecision.BODY_TABLECODE);

  /**
   * DeliveryPrecision 的构造子
   */
  private DeliveryPrecision() {
    // TODS
  }

  public static DeliveryPrecision getInstance() {
    return DeliveryPrecision.precision;
  }

  /**
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
        listpanel.getHeadBillModel(), DeliveryPrecision.NUMHEAD);
  }

  /**
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
    TableScaleProcessor scaleprocess =
        new BillModelScaleProcessor(pk_group, model);
    this.setTablePrecision(scaleprocess);
  }

  public void setScaleForSingleTable(BillScaleProcessor scale) {
    if (scale != null) {
      this.setBillPrecision(scale);
    }
  }

  /**
   * 单表精度处理
   * 
   * @param panel
   */
  public void setSingleTableScale(String pk_group, BillListPanel panel) {
    this.setModelPrecision(pk_group, panel.getHeadBillModel());
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
   * @time 2010-5-27 上午10:13:48
   */
  private void setBillPrecision(BillScaleProcessor scaleprocess,
      TotalValueScale totalscale) {

    // 换算率
    scaleprocess.setHslCtlInfo(DeliveryPrecision.hslkeys, PosEnum.body, null);
    // 原币单价
    scaleprocess.setPriceCtlInfo(DeliveryPrecision.pricekeys, PosEnum.body,
        BODY_TABLECODE,DeliveryBVO.CORIGCURRENCYID,
        PosEnum.body, DeliveryPrecision.BODY_TABLECODE);
    
    //本位币单价
    scaleprocess.setPriceCtlInfo(DeliveryPrecision.netpricekeys, PosEnum.body,
        BODY_TABLECODE,DeliveryBVO.CCURRENCYID,
        PosEnum.body, DeliveryPrecision.BODY_TABLECODE);
    
    
    // 数量
    scaleprocess.setNumCtlInfo(DeliveryPrecision.astnumkeys, PosEnum.body,
        null, DeliveryBVO.CASTUNITID, PosEnum.body, null);
    // 主数量
    scaleprocess.setNumCtlInfo(DeliveryPrecision.numkeys, PosEnum.body, null,
        DeliveryBVO.CUNITID, PosEnum.body, null);
    // 整单折扣、单品折扣
    scaleprocess.setSaleDiscountCtlInfo(DeliveryPrecision.bdiscountkeys,
        PosEnum.body, null);
    // 报价数量
    scaleprocess.setNumCtlInfo(DeliveryPrecision.qtnumkeys, PosEnum.body, null,
        DeliveryBVO.CQTUNITID, PosEnum.body, null);
    // 本币金额
    scaleprocess.setMnyCtlInfo(DeliveryPrecision.mnykeys, PosEnum.body, null,
        DeliveryBVO.CCURRENCYID, PosEnum.body, null);
    // 原币金额
    scaleprocess.setMnyCtlInfo(DeliveryPrecision.origmnykeys, PosEnum.body,
        null, DeliveryBVO.CORIGCURRENCYID, PosEnum.body, null);
    // 折本汇率
    scaleprocess.setOrgExchangeCtlInfo(this.exchangeRate, this.localOrigCurr,
        this.orgCurr, this.settleOrg);
    // 税率
    scaleprocess.setTaxRateCtlInfo(DeliveryPrecision.taxratekey, PosEnum.body,
        null);
    // 全局本位币汇率
    scaleprocess.setGlobalExchangeCtlInfo(this.globalExchgRate,
        this.localOrigCurr, this.orgCurr);
    // 集团本位币汇率
    scaleprocess.setGroupExchangeCtlInfo(this.groupExchgRate,
        this.localOrigCurr, this.orgCurr);
    scaleprocess.setWeightCtlInfo(DeliveryPrecision.bodyweightKey,
        PosEnum.body, null);
    scaleprocess.setVolumnCtlInfo(DeliveryPrecision.bodyvolKey, PosEnum.body,
        null);
    scaleprocess.setUnitCtlInfo(DeliveryPrecision.bodypieceKey, PosEnum.body,
        null, DeliveryBVO.CMATERIALVID, PosEnum.body, null);
    // 集团本币金额
    scaleprocess.setGroupLocMnyCtlInfo(DeliveryPrecision.GROUPMNYKEYS,
        PosEnum.body, null);
    // 全局本币金额
    scaleprocess.setGlobalLocMnyCtlInfo(DeliveryPrecision.GLOBALMNYKEYS,
        PosEnum.body, null);
    if (totalscale != null) {
      // 表头合计数量
      totalscale.setHeadTailKeys(DeliveryPrecision.NUMHEAD);
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
  private void setTablePrecision(TableScaleProcessor scaleprocess) {
    // 数量
    scaleprocess.setNumCtlInfo(DeliveryPrecision.astnumkeys,
        DeliveryBVO.CASTUNITID);
    // 主数量
    scaleprocess.setNumCtlInfo(DeliveryPrecision.numkeys, DeliveryBVO.CUNITID);
    // 报价数量
    scaleprocess.setNumCtlInfo(DeliveryPrecision.qtnumkeys,
        DeliveryBVO.CQTUNITID);
    // 本币金额
    scaleprocess.setMnyCtlInfo(DeliveryPrecision.mnykeys,
        DeliveryBVO.CCURRENCYID);
    // 原币金额
    scaleprocess.setMnyCtlInfo(DeliveryPrecision.origmnykeys,
        DeliveryBVO.CORIGCURRENCYID);
    scaleprocess.setHslCtlInfo(DeliveryPrecision.hslkeys);
    //原币单价    
    scaleprocess.setPriceCtlInfo(DeliveryPrecision.pricekeys,DeliveryBVO.CORIGCURRENCYID);
    //本币单价
    scaleprocess.setPriceCtlInfo(DeliveryPrecision.netpricekeys,DeliveryBVO.CCURRENCYID);
    scaleprocess.setTaxRateCtlInfo(DeliveryPrecision.taxratekey);
    scaleprocess.setGlobalLocMnyCtlInfo(DeliveryPrecision.GLOBALMNYKEYS);
    scaleprocess.setGroupLocMnyCtlInfo(DeliveryPrecision.GROUPMNYKEYS);
    
    // add by quyt 为了处理发货安排件数精度
    scaleprocess.setUnitCtlInfo(new String[] {
      DeliveryBVO.NPIECE
    }, DeliveryBVO.CMATERIALVID);
    
    scaleprocess.process();
  }

}
