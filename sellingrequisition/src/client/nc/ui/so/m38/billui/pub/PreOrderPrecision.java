package nc.ui.so.m38.billui.pub;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.scale.BillModelScaleProcessor;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.ui.pubapp.scale.TotalValueScaleProcessor;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.FieldInfo;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TableScaleProcessor;
import nc.vo.pubapp.scale.TotalValueScale;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;

/**
 * 预订单精度控制类，包括卡片、列表、拉平界面的精度控制
 * 
 * @since 6.0
 * @version 2011-7-11 上午10:12:33
 * @author fengjb
 */
public class PreOrderPrecision {

  public static final int BODY_POS = 1;

  public static final String BODY_TABLECODE = "body";

  public static final int HEAD_POS = 0;

  public static final String HEAD_TABLECODE = "head";

  // 业务数量
  private static final String[] ASTNUMKEYS = new String[] {
    PreOrderBVO.NASTNUM
  };

  // 整单折扣、单品折扣
  private static final String[] BDISCOUNTKEYS = new String[] {
    PreOrderBVO.NDISCOUNTRATE, PreOrderBVO.NITEMDISCOUNTRATE
  };

  // 全局本币金额
  private static final String[] GLOBALMNYKEYS = new String[] {
    PreOrderBVO.NGLOBALTAXMNY, PreOrderBVO.NGLOBALMNY
  };

  // 集团本币金额
  private static final String[] GROUPMNYKEYS = new String[] {
    PreOrderBVO.NGROUPTAXMNY, PreOrderBVO.NGROUPMNY
  };

  /** 表头 */
  // 整单折扣
  private static final String[] HDISCOUNTKEYS = new String[] {
    PreOrderHVO.NDISCOUNTRATE
  };

  // 换算率
  private static final String[] HSLKEYS = new String[] {
    PreOrderBVO.VCHANGERATE, PreOrderBVO.VQTUNITRATE
  };

  // 本币金额
  private static final String[] MNYKEYS = new String[] {
    PreOrderBVO.NTAX, PreOrderBVO.NMNY, PreOrderBVO.NTAXMNY,
    PreOrderBVO.NDISCOUNT, PreOrderBVO.NCALTAXMNY
  };

  // 原币金额（表头）
  private static final String[] NNYHEAD = new String[] {
    PreOrderHVO.NHEADSUMMNY
  };

  // 合计数量（表头）
  private static final String[] NUMHEAD = new String[] {
    PreOrderHVO.NTOTALNUM
  };

  /** 表体 */

  // 主数量 、可安排数量（ncanarrnum）
  private static final String[] NUMKEYS = new String[] {
    PreOrderBVO.NNUM, PreOrderBVO.NARRNUM, "ncanarrnum"
  };

  // 原币金额 norigmny
  private static final String[] ORIGMNYKEYS = new String[] {
    // TODO 刘景 2012-2-17 删除原币税额字段
    // PreOrderBVO.NORIGTAX,

    PreOrderBVO.NORIGMNY, PreOrderBVO.NORIGTAXMNY, PreOrderBVO.NORIGDISCOUNT
  };

  private static PreOrderPrecision precision = new PreOrderPrecision();

  // 单价
  private static final String[] PRICEKEYS = new String[] {
    PreOrderBVO.NORIGTAXPRICE, PreOrderBVO.NORIGPRICE,
    PreOrderBVO.NORIGTAXNETPRICE, PreOrderBVO.NORIGNETPRICE,
    PreOrderBVO.NQTORIGTAXPRICE, PreOrderBVO.NQTORIGPRICE,
    PreOrderBVO.NQTORIGTAXNETPRC, PreOrderBVO.NQTORIGNETPRICE,

    PreOrderBVO.NASKQTORIGNETPRICE, PreOrderBVO.NASKQTORIGPRICE,
    PreOrderBVO.NASKQTORIGTAXPRC, PreOrderBVO.NASKQTORIGTXNTPRC

  };

  private static final String[] netpricekeys = new String[] {
    PreOrderBVO.NTAXPRICE, PreOrderBVO.NPRICE, PreOrderBVO.NTAXNETPRICE,
    PreOrderBVO.NNETPRICE, PreOrderBVO.NQTTAXPRICE, PreOrderBVO.NQTPRICE,
    PreOrderBVO.NQTTAXNETPRICE, PreOrderBVO.NQTNETPRICE,
  };

  // 报价数量
  private static final String[] QTNUMKEYS = new String[] {
    PreOrderBVO.NQTUNITNUM
  };

  // 表体税率
  private static final String[] TAXRATEKEY = new String[] {
    PreOrderBVO.NTAXRATE
  };

  // 折本汇率
  private FieldInfo exchangeRate = new FieldInfo(PreOrderBVO.NEXCHANGERATE,
      PreOrderPrecision.BODY_POS, PreOrderPrecision.BODY_TABLECODE);

  // 全局折本汇率
  private FieldInfo globalExchgRate = new FieldInfo(
      PreOrderBVO.NGLOBALEXCHGRATE, PreOrderPrecision.BODY_POS,
      PreOrderPrecision.BODY_TABLECODE);

  // 集团折本汇率
  private FieldInfo groupExchgRate = new FieldInfo(PreOrderBVO.NGROUPEXCHGRATE,
      PreOrderPrecision.BODY_POS, PreOrderPrecision.BODY_TABLECODE);

  // 原币
  private FieldInfo localOrigCurr = new FieldInfo(PreOrderHVO.CORIGCURRENCYID,
      PreOrderPrecision.HEAD_POS, PreOrderPrecision.HEAD_TABLECODE);

  // 组织本币
  private FieldInfo orgCurr = new FieldInfo(PreOrderBVO.CCURRENCYID,
      PreOrderPrecision.BODY_POS, PreOrderPrecision.BODY_TABLECODE);

  // 财务组织
  private FieldInfo settleOrg = new FieldInfo(PreOrderBVO.CSETTLEORGID,
      PreOrderPrecision.BODY_POS, PreOrderPrecision.BODY_TABLECODE);

  public static PreOrderPrecision getInstance() {
    return PreOrderPrecision.precision;
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
   * @author 刘志伟
   * @time 2010-7-2 下午04:30:23
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
   * @author 刘志伟
   * @time 2010-7-2 下午04:30:23
   */
  public void setListPrecision(String pk_group, BillListPanel listpanel) {
    BillScaleProcessor scaleprocess =
        new ListPaneScaleProcessor(pk_group, listpanel);
    TotalValueScale totalscale = new TotalValueScaleProcessor(listpanel);
    this.setBillPrecision(scaleprocess, totalscale);
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
   * @author 刘志伟
   * @time 2010-5-27 上午09:59:48
   */
  public void setModelPrecision(String pk_group, BillModel model) {
    TableScaleProcessor scaleprocess =
        new BillModelScaleProcessor(pk_group, model);
    this.setTablePrecision(scaleprocess);
  }

  /**
   * 单表精度处理
   * 
   * @param panel
   */
  public void setSingleTableScale(String pk_group, BillListPanel panel) {
    this.setModelPrecision(pk_group, panel.getHeadBillModel());
  }

  private void setBillPrecision(BillScaleProcessor scaleprocess,
      TotalValueScale totalscale) {

    // 集团本币金额
    scaleprocess.setGroupLocMnyCtlInfo(PreOrderPrecision.GROUPMNYKEYS,
        PosEnum.body, null);
    // 本币金额
    scaleprocess.setOrgLocMnyCtlInfo(PreOrderPrecision.MNYKEYS, PosEnum.body,
        null);
    // 全局本币金额
    scaleprocess.setGlobalLocMnyCtlInfo(PreOrderPrecision.GLOBALMNYKEYS,
        PosEnum.body, null);
    // 换算率精度
    scaleprocess.setHslCtlInfo(PreOrderPrecision.HSLKEYS, PosEnum.body, null);
    // 整单折扣
    scaleprocess.setSaleDiscountCtlInfo(PreOrderPrecision.HDISCOUNTKEYS,
        PosEnum.head, null);
    // 整单折扣、单品折扣
    scaleprocess.setSaleDiscountCtlInfo(PreOrderPrecision.BDISCOUNTKEYS,
        PosEnum.body, null);
    // 单价
    scaleprocess.setPriceCtlInfo(PreOrderPrecision.PRICEKEYS, PosEnum.body,
        null, PreOrderBVO.CORIGCURRENCYID, PosEnum.body, null);

    // 单价
    scaleprocess.setPriceCtlInfo(PreOrderPrecision.netpricekeys, PosEnum.body,
        null, PreOrderBVO.CCURRENCYID, PosEnum.body, null);

    // 数量
    scaleprocess.setNumCtlInfo(PreOrderPrecision.ASTNUMKEYS, PosEnum.body,
        null, PreOrderBVO.CASTUNITID, PosEnum.body, null);
    // 主数量
    scaleprocess.setNumCtlInfo(PreOrderPrecision.NUMKEYS, PosEnum.body, null,
        PreOrderBVO.CUNITID, PosEnum.body, null);
    // 报价数量
    scaleprocess.setNumCtlInfo(PreOrderPrecision.QTNUMKEYS, PosEnum.body, null,
        PreOrderBVO.CQTUNITID, PosEnum.body, null);
    // 原币金额
    scaleprocess.setMnyCtlInfo(PreOrderPrecision.ORIGMNYKEYS, PosEnum.body,
        null, PreOrderBVO.CORIGCURRENCYID, PosEnum.body, null);

    // 原币金额（表头）
    scaleprocess.setMnyCtlInfo(PreOrderPrecision.NNYHEAD, PosEnum.head, null,
        PreOrderHVO.CORIGCURRENCYID, PosEnum.head, null);

    // 表头合计数量
    if (totalscale != null) {
      totalscale.setHeadTailKeys(PreOrderPrecision.NUMHEAD);
    }
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
    scaleprocess.setTaxRateCtlInfo(PreOrderPrecision.TAXRATEKEY, PosEnum.body,
        null);

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
   * @author 刘志伟
   * @time 2010-5-27 上午10:13:35
   */
  private void setTablePrecision(TableScaleProcessor scaleprocess) {

    // 业务数量
    scaleprocess.setNumCtlInfo(PreOrderPrecision.ASTNUMKEYS,
        PreOrderBVO.CASTUNITID);
    // 主数量
    scaleprocess.setNumCtlInfo(PreOrderPrecision.NUMKEYS, PreOrderBVO.CUNITID);
    // 报价数量
    scaleprocess.setNumCtlInfo(PreOrderPrecision.QTNUMKEYS,
        PreOrderBVO.CQTUNITID);
    // 本币金额
    scaleprocess.setMnyCtlInfo(PreOrderPrecision.MNYKEYS,
        PreOrderBVO.CCURRENCYID);
    // 原币金额
    scaleprocess.setMnyCtlInfo(PreOrderPrecision.ORIGMNYKEYS,
        PreOrderBVO.CORIGCURRENCYID);

    // 集团本币金额
    scaleprocess.setGroupLocMnyCtlInfo(PreOrderPrecision.GROUPMNYKEYS);
    // 全局本币金额
    scaleprocess.setGlobalLocMnyCtlInfo(PreOrderPrecision.GLOBALMNYKEYS);
    // 换算率
    scaleprocess.setHslCtlInfo(PreOrderPrecision.HSLKEYS);
    // 单价
    scaleprocess.setPriceCtlInfo(PreOrderPrecision.PRICEKEYS,
        PreOrderBVO.CORIGCURRENCYID);

    // 本币单价
    scaleprocess.setPriceCtlInfo(PreOrderPrecision.netpricekeys,
        PreOrderBVO.CCURRENCYID);

    // 税率
    scaleprocess.setTaxRateCtlInfo(PreOrderPrecision.TAXRATEKEY);
    // 折扣
    scaleprocess.setSaleDiscountCtlInfo(PreOrderPrecision.BDISCOUNTKEYS);

    scaleprocess.process();
  }
}
