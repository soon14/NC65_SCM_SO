package nc.ui.so.report.tbb.m32;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

public class InvoiceDetailPrecion {

  public static final int BODY_POS = 1;

  public static final String BODY_TABLECODE = "saleinvoice_b";

  public static final int HEAD_POS = 0;

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

  private static InvoiceDetailPrecion instance = new InvoiceDetailPrecion();

  // 本币金额
  private static final String[] MNYKEY = new String[] {
    SaleInvoiceBVO.NTAX, SaleInvoiceBVO.NMNY, SaleInvoiceBVO.NTAXMNY,
    SaleInvoiceBVO.NDISCOUNT
  };

  // 主数量
  private static final String[] NUMKEY = new String[] {
    SaleInvoiceBVO.NNUM
  };

  // 原币金额
  private static final String[] ORIGMNYKEY = new String[] {
    SaleInvoiceBVO.NORIGMNY, SaleInvoiceBVO.NORIGTAXMNY,
    SaleInvoiceBVO.NORIGDISCOUNT, SaleInvoiceBVO.NORIGSUBMNY
  };

  // 表头原币金额
  private static final String[] ORIGMNYKEYHEAD = new String[] {
    SaleInvoiceHVO.NTOTALORIGMNY, SaleInvoiceHVO.NTOTALORIGSUBMNY
  };

  // 单价
  private static final String[] PRICEKEY = new String[] {
    SaleInvoiceBVO.NORIGTAXPRICE, SaleInvoiceBVO.NORIGPRICE,
    SaleInvoiceBVO.NORIGTAXNETPRICE, SaleInvoiceBVO.NORIGNETPRICE,
    SaleInvoiceBVO.NQTORIGTAXPRICE, SaleInvoiceBVO.NQTORIGPRICE,
    SaleInvoiceBVO.NQTORIGTAXNETPRC, SaleInvoiceBVO.NQTORIGNETPRICE,
  };

  private static final String[] NETPRICEKEYS = new String[] {
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

  public static InvoiceDetailPrecion getInstance() {
    return InvoiceDetailPrecion.instance;
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
    this.setBillPrecision(scaleprocess);
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
  private void setBillPrecision(BillScaleProcessor scaleprocess) {
    // 集团本币金额
    scaleprocess.setGroupLocMnyCtlInfo(InvoiceDetailPrecion.GROUPMNYKEY,
        PosEnum.body, null);
    // 本币金额
    scaleprocess.setOrgLocMnyCtlInfo(InvoiceDetailPrecion.MNYKEY, PosEnum.body,
        null);
    // 全局本币金额
    scaleprocess.setGlobalLocMnyCtlInfo(InvoiceDetailPrecion.GLOBALMNYKEY,
        PosEnum.body, null);
    // 换算率精度
    scaleprocess.setHslCtlInfo(InvoiceDetailPrecion.HSLKEY, PosEnum.body, null);
    // 单价
    scaleprocess.setPriceCtlInfo(InvoiceDetailPrecion.PRICEKEY, PosEnum.body,
        null, SaleInvoiceHVO.CORIGCURRENCYID, PosEnum.head, null);

    // 本币单价
    scaleprocess.setPriceCtlInfo(InvoiceDetailPrecion.NETPRICEKEYS,
        PosEnum.body, null, SaleInvoiceHVO.CCURRENCYID, PosEnum.head, null);

    // 数量
    scaleprocess.setNumCtlInfo(InvoiceDetailPrecion.ASTNUMKEY, PosEnum.body,
        null, SaleInvoiceBVO.CASTUNITID, PosEnum.body, null);
    // 主数量
    scaleprocess.setNumCtlInfo(InvoiceDetailPrecion.NUMKEY, PosEnum.body, null,
        SaleInvoiceBVO.CUNITID, PosEnum.body, null);
    // 报价数量
    scaleprocess.setNumCtlInfo(InvoiceDetailPrecion.QTNUMKEY, PosEnum.body,
        null, SaleInvoiceBVO.CQTUNITID, PosEnum.body, null);
    // 表头折扣
    scaleprocess.setSaleDiscountCtlInfo(InvoiceDetailPrecion.HEAD_DISRATEKEYS,
        PosEnum.body, null);
    // 原币金额
    scaleprocess.setMnyCtlInfo(InvoiceDetailPrecion.ORIGMNYKEY, PosEnum.body,
        null, SaleInvoiceHVO.CORIGCURRENCYID, PosEnum.head, null);

    // 原币金额（表头）
    scaleprocess.setMnyCtlInfo(InvoiceDetailPrecion.ORIGMNYKEYHEAD,
        PosEnum.body, null, SaleInvoiceHVO.CORIGCURRENCYID, PosEnum.head, null);
    // 扣率
    scaleprocess.setSaleDiscountCtlInfo(InvoiceDetailPrecion.DISCOUNTRATEKEY,
        PosEnum.body, null);
    // 税率
    scaleprocess.setTaxRateCtlInfo(InvoiceDetailPrecion.TAXRATEKEY,
        PosEnum.body, null);
    scaleprocess.process();

  }
}
