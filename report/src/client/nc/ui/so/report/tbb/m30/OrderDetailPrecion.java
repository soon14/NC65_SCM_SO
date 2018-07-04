package nc.ui.so.report.tbb.m30;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;

/**
 * 预算联查订单明细处理
 * 
 * @since 6.0
 * @version 2011-6-13 上午11:11:05
 * @author 祝会征
 */
public class OrderDetailPrecion {
  public static final int BODY_POS = 1;

  public static final String BODY_TABLECODE = "bodytable1";

  public static final int HEAD_POS = 0;

  public static final String HEAD_TABLECODE = "head";

  // 数量
  private static final String[] ASTNUMKEYS = new String[] {
    SaleOrderBVO.NASTNUM
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
    SaleOrderBVO.NDISCOUNT
  };

  // 原币数量（表头）
  /* private static final String[] NUMHEAD = new String[] {
   * SaleOrderHVO.NTOTALNUM, SaleOrderHVO.NTOTALVOLUME,
   * SaleOrderHVO.NTOTALWEIGHT,
   * }; */

  // 主数量
  private static final String[] NUMKEYS = new String[] {
    SaleOrderBVO.NNUM
  };

  // 原币金额
  private static final String[] ORIGMNYKEYS = new String[] {
    SaleOrderBVO.NORIGMNY, SaleOrderBVO.NORIGTAXMNY, SaleOrderBVO.NORIGDISCOUNT
  };

  // 原币金额（表头）
  private static final String[] ORIGMNYKEYSHEAD = new String[] {
    SaleOrderHVO.NTOTALMNY, SaleOrderHVO.NTOTALORIGMNY,
    SaleOrderHVO.NTOTALORIGSUBMNY, SaleOrderHVO.NPRECEIVEMNY,
    SaleOrderHVO.NPRECEIVEQUOTA, SaleOrderHVO.NRECEIVEDMNY,
    SaleOrderHVO.NTHISRECEIVEMNY
  };

  private static OrderDetailPrecion precision = new OrderDetailPrecion();

  // 单价
  private static final String[] PRICEKEYS = new String[] {
    SaleOrderBVO.NORIGTAXPRICE, SaleOrderBVO.NORIGPRICE,
    SaleOrderBVO.NORIGTAXNETPRICE, SaleOrderBVO.NORIGNETPRICE,
    SaleOrderBVO.NQTORIGTAXPRICE, SaleOrderBVO.NQTORIGPRICE,
    SaleOrderBVO.NQTORIGTAXNETPRC, SaleOrderBVO.NQTORIGNETPRICE,
  
  };
  
  private static final String[] NETPRICEKEYS=new String[]{
    SaleOrderBVO.NTAXPRICE, SaleOrderBVO.NPRICE, SaleOrderBVO.NTAXNETPRICE,
    SaleOrderBVO.NNETPRICE, SaleOrderBVO.NQTTAXPRICE, SaleOrderBVO.NQTPRICE,
    SaleOrderBVO.NQTTAXNETPRICE, SaleOrderBVO.NQTNETPRICE
  };

  // 报价数量
  private static final String[] QTNUMKEYS = new String[] {
    SaleOrderBVO.NQTUNITNUM
  };

  // 表体税率
  private static final String[] TAXRATEKEY = new String[] {
    SaleOrderBVO.NTAXRATE
  };

  public static OrderDetailPrecion getInstance() {
    return OrderDetailPrecion.precision;
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
    this.setBillPrecision(scaleprocess);
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
  private void setBillPrecision(BillScaleProcessor scaleprocess) {
    // 集团本币金额
    scaleprocess.setGroupLocMnyCtlInfo(OrderDetailPrecion.GROUPMNYKEYS,
        PosEnum.body, null);
    // 全局本币金额
    scaleprocess.setGlobalLocMnyCtlInfo(OrderDetailPrecion.GLOBALMNYKEYS,
        PosEnum.body, null);
    // 换算率
    scaleprocess.setHslCtlInfo(OrderDetailPrecion.HSLKEYS, PosEnum.body, null);
    // 原币单价
    scaleprocess.setPriceCtlInfo(OrderDetailPrecion.PRICEKEYS, PosEnum.body,
        null,SaleOrderHVO.CORIGCURRENCYID,PosEnum.head,null);
    // 本位币单价
    scaleprocess.setPriceCtlInfo(OrderDetailPrecion.NETPRICEKEYS, PosEnum.body,
        null,SaleOrderBVO.CCURRENCYID,PosEnum.body,null);
    
    // 数量
    scaleprocess.setNumCtlInfo(OrderDetailPrecion.ASTNUMKEYS, PosEnum.body,
        null, SaleOrderBVO.CASTUNITID, PosEnum.body, null);
    // 主数量
    scaleprocess.setNumCtlInfo(OrderDetailPrecion.NUMKEYS, PosEnum.body, null,
        SaleOrderBVO.CUNITID, PosEnum.body, null);
    // 报价数量
    scaleprocess.setNumCtlInfo(OrderDetailPrecion.QTNUMKEYS, PosEnum.body,
        null, SaleOrderBVO.CQTUNITID, PosEnum.body, null);
    // 本币金额
    scaleprocess.setMnyCtlInfo(OrderDetailPrecion.MNYKEYS, PosEnum.body, null,
        SaleOrderBVO.CCURRENCYID, PosEnum.body, null);
    // 原币金额
    scaleprocess.setMnyCtlInfo(OrderDetailPrecion.ORIGMNYKEYS, PosEnum.body,
        null, SaleOrderHVO.CORIGCURRENCYID, PosEnum.body, null);

    // 原币金额（表头）
    scaleprocess.setMnyCtlInfo(OrderDetailPrecion.ORIGMNYKEYSHEAD,
        PosEnum.body, null, SaleOrderHVO.CORIGCURRENCYID, PosEnum.body, null);
    // 表头折扣
    scaleprocess.setSaleDiscountCtlInfo(OrderDetailPrecion.HEAD_DISRATEKEYS,
        PosEnum.body, null);
    // 表体折扣
    scaleprocess.setSaleDiscountCtlInfo(OrderDetailPrecion.BODY_DISRATEKEYS,
        PosEnum.body, null);
    // 税率
    scaleprocess.setTaxRateCtlInfo(OrderDetailPrecion.TAXRATEKEY, PosEnum.body,
        null);
    scaleprocess.process();
  }
}
