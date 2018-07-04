package nc.ui.so.m4331.billui.action.returnaction.dlg;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.FieldInfo;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.so.m4331.entity.DeliveryCheckVO;

public class ChgPricePrecion {

  public static final int BODY_POS = 1;

  public static final String BODY_TABLECODE = "cdeliverycid";

  // 数量
  private static String[] astnumkeys = new String[] {
    DeliveryCheckVO.NASTNUM
  };

  // 整单折扣、单品折扣
  private static String[] bdiscountkeys = new String[] {
    DeliveryCheckVO.NDISCOUNTRATE, DeliveryCheckVO.NITEMDISCOUNTRATE
  };

  // 换算率
  private static String[] hslkeys = new String[] {
    DeliveryCheckVO.VCHANGERATE, DeliveryCheckVO.VQTUNITRATE
  };

  // 本币金额
  private static String[] mnykeys = new String[] {
    DeliveryCheckVO.NTAX, DeliveryCheckVO.NMNY, DeliveryCheckVO.NTAXMNY,
    DeliveryCheckVO.NDISCOUNT
  };

  // 主数量
  private static String[] numkeys = new String[] {
    DeliveryCheckVO.NNUM
  };

  // 原币金额
  private static String[] origmnykeys = new String[] {
    DeliveryCheckVO.NORIGMNY, DeliveryCheckVO.NORIGTAXMNY,
    DeliveryCheckVO.NORIGDISCOUNT
  };

  // 单价
  private static String[] pricekeys = new String[] {
    // 主单位原币含税单价、主单位原币无税单价
    DeliveryCheckVO.NORIGTAXPRICE, DeliveryCheckVO.NORIGPRICE,
    // 主单位原币含税净价、主单位原币无税净价
    DeliveryCheckVO.NORIGTAXNETPRICE, DeliveryCheckVO.NORIGNETPRICE,
    // 报价单位原币含税单价、报价单位原币无税单价
    DeliveryCheckVO.NQTORIGTAXPRICE, DeliveryCheckVO.NQTORIGPRICE,
    // 报价单位原币含税净价、报价单位原币无税净价
    DeliveryCheckVO.NQTORIGTAXNETPRC, DeliveryCheckVO.NQTORIGNETPRICE,

  };

  /**
   * 本币单价
   */
  private static String[] netpricekeys = new String[] {
    DeliveryCheckVO.NTAXPRICE, DeliveryCheckVO.NPRICE,
    DeliveryCheckVO.NTAXNETPRICE, DeliveryCheckVO.NNETPRICE,
    DeliveryCheckVO.NQTTAXPRICE, DeliveryCheckVO.NQTPRICE,
    DeliveryCheckVO.NQTTAXNETPRICE, DeliveryCheckVO.NQTNETPRICE
  };

  // 报价数量
  private static String[] qtnumkeys = new String[] {
    DeliveryCheckVO.NQTUNITNUM
  };

  // 表体税率
  private static String[] taxratekey = new String[] {
    DeliveryCheckVO.NTAXRATE
  };

  // 折本汇率
  private FieldInfo exchangeRate = new FieldInfo(DeliveryCheckVO.NEXCHANGERATE,
      ChgPricePrecion.BODY_POS, ChgPricePrecion.BODY_TABLECODE);

  // 原币
  private FieldInfo localOrigCurr = new FieldInfo(
      DeliveryCheckVO.CORIGCURRENCYID, ChgPricePrecion.BODY_POS,
      ChgPricePrecion.BODY_TABLECODE);

  // 组织本币
  private FieldInfo orgCurr = new FieldInfo(DeliveryCheckVO.CCURRENCYID,
      ChgPricePrecion.BODY_POS, ChgPricePrecion.BODY_TABLECODE);

  // 财务组织
  private FieldInfo settleOrg = new FieldInfo(DeliveryCheckVO.PK_ORG,
      ChgPricePrecion.BODY_POS, ChgPricePrecion.BODY_TABLECODE);

  public void setCardPrecision(String pk_group, BillCardPanel cardpanel) {
    BillScaleProcessor scaleprocess =
        new CardPaneScaleProcessor(pk_group, cardpanel);
    this.setBillPrecision(scaleprocess);
  }

  private void setBillPrecision(BillScaleProcessor scaleprocess) {
    // 换算率
    scaleprocess.setHslCtlInfo(ChgPricePrecion.hslkeys, PosEnum.body, null);
    // 原币单价
    scaleprocess.setPriceCtlInfo(ChgPricePrecion.pricekeys, PosEnum.body,
        BODY_TABLECODE, DeliveryCheckVO.CORIGCURRENCYID, PosEnum.body,
        BODY_TABLECODE);
    // 本币单价
    scaleprocess.setPriceCtlInfo(ChgPricePrecion.netpricekeys, PosEnum.body,
        BODY_TABLECODE, DeliveryCheckVO.CCURRENCYID, PosEnum.body,
        BODY_TABLECODE);
    // 数量
    scaleprocess.setNumCtlInfo(ChgPricePrecion.astnumkeys, PosEnum.body, null,
        DeliveryCheckVO.CASTUNITID, PosEnum.body, null);
    // 主数量
    scaleprocess.setNumCtlInfo(ChgPricePrecion.numkeys, PosEnum.body, null,
        DeliveryCheckVO.CUNITID, PosEnum.body, null);
    // 整单折扣、单品折扣
    scaleprocess.setSaleDiscountCtlInfo(ChgPricePrecion.bdiscountkeys,
        PosEnum.body, null);
    // 报价数量
    scaleprocess.setNumCtlInfo(ChgPricePrecion.qtnumkeys, PosEnum.body, null,
        DeliveryCheckVO.CQTUNITID, PosEnum.body, null);
    // 本币金额
    scaleprocess.setMnyCtlInfo(ChgPricePrecion.mnykeys, PosEnum.body, null,
        DeliveryCheckVO.CCURRENCYID, PosEnum.body, null);
    // 原币金额
    scaleprocess.setMnyCtlInfo(ChgPricePrecion.origmnykeys, PosEnum.body, null,
        DeliveryCheckVO.CORIGCURRENCYID, PosEnum.body, null);
    // 折本汇率
    scaleprocess.setOrgExchangeCtlInfo(this.exchangeRate, this.localOrigCurr,
        this.orgCurr, this.settleOrg);
    // 税率
    scaleprocess.setTaxRateCtlInfo(ChgPricePrecion.taxratekey, PosEnum.body,
        null);
    scaleprocess.process();

  }
}
