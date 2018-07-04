package nc.ui.so.salequotation.scale;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.scale.BillModelScaleProcessor;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.ui.pubapp.scale.TotalValueScaleProcessor;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.BillVOScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TableScaleProcessor;
import nc.vo.pubapp.scale.TotalValueScale;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class SalequoScaleProcessor {

  // 数量
  private static final String[] ASTNUMKEYS = new String[] {
    SalequotationBVO.NASSISTNUM
  };

  // 换算率
  private static final String[] CONVERTRATEKEYS = new String[] {
    SalequotationBVO.NQTCHANGERATE, SalequotationBVO.NCHANGERATE
  };

  // 表体折扣
  private static final String[] DISCOUNTRATE = new String[] {
    SalequotationBVO.NDISCOUNTRATE, SalequotationBVO.NITEMDISCOUNTRATE
  };

  // 表头折扣
  private static final String[] HEADDISCOUNTRATE = new String[] {
    SalequotationHVO.NDISCOUNT
  };

  // 表头价税合计
  private static final String[] HEADTAXMNY = new String[] {
    SalequotationHVO.NTOTALMNY
  };

  private static SalequoScaleProcessor instance = new SalequoScaleProcessor();

  // 原币金额
  private static final String[] MNYKEYS = new String[] {
    SalequotationBVO.NORIGMNY, SalequotationBVO.NORIGTAXMNY,
    SalequotationBVO.NORIGDISCOUNT,
  };

  // 主数量
  private static final String[] NUMKEYS = new String[] {
    SalequotationBVO.NNUM, SalequotationBVO.NORDERNUM,
    SalequotationBVO.NCONTRACTNUM
  };

  // 单价
  private static final String[] PRICEKEYS = new String[] {
    SalequotationBVO.NQTORIGPRICE, SalequotationBVO.NQTORIGTAXPRICE,
    SalequotationBVO.NQTORIGNETPRICE, SalequotationBVO.NQTORIGTAXNETPRC,
    SalequotationBVO.NORIGPRICE, SalequotationBVO.NORIGTAXPRICE,
    SalequotationBVO.NORIGNETPRICE, SalequotationBVO.NORIGTAXNETPRICE
  };

  // 报价数量
  private static final String[] QTNUMKEYS = new String[] {
    SalequotationBVO.NQTNUM
  };

  // 表体税率
  private static final String[] TAXRATEKEY = new String[] {
    SalequotationBVO.NTAXRATE
  };

  public static SalequoScaleProcessor getInstance() {
    return SalequoScaleProcessor.instance;
  }

  private SalequoScaleProcessor() {
    // do nothing
  }

  private void setBillPrecision(BillScaleProcessor scaleprocess) {
    this.setBillPrecision(scaleprocess, null);
  }

  private void setBillPrecision(BillScaleProcessor scaleprocess,
      TotalValueScale totalscale) {
    // 整单折扣（表头）
    scaleprocess.setSaleDiscountCtlInfo(SalequoScaleProcessor.HEADDISCOUNTRATE,
        PosEnum.head, null);
    // 原币价税合计（表头）
    scaleprocess.setMnyCtlInfo(SalequoScaleProcessor.HEADTAXMNY, PosEnum.head,
        null, SalequotationHVO.PK_CURRTYPE, PosEnum.head, null);

    // 税率
    scaleprocess.setTaxRateCtlInfo(SalequoScaleProcessor.TAXRATEKEY,
        PosEnum.body, null);
    // 折扣
    scaleprocess.setSaleDiscountCtlInfo(SalequoScaleProcessor.DISCOUNTRATE,
        PosEnum.body, null);
    // 换算率
    scaleprocess.setHslCtlInfo(SalequoScaleProcessor.CONVERTRATEKEYS,
        PosEnum.body, null);
    // 数量
    scaleprocess.setNumCtlInfo(SalequoScaleProcessor.ASTNUMKEYS, PosEnum.body,
        null, SalequotationBVO.CASTUNITID, PosEnum.body, null);
    // 主数量
    scaleprocess.setNumCtlInfo(SalequoScaleProcessor.NUMKEYS, PosEnum.body,
        null, SalequotationBVO.PK_UNIT, PosEnum.body, null);
    // 报价数量
    scaleprocess.setNumCtlInfo(SalequoScaleProcessor.QTNUMKEYS, PosEnum.body,
        null, SalequotationBVO.CQTUNITID, PosEnum.body, null);
    // 单价
    scaleprocess.setPriceCtlInfo(SalequoScaleProcessor.PRICEKEYS, PosEnum.body,
        null,SalequotationHVO.PK_CURRTYPE,PosEnum.head,null);
    scaleprocess.setMnyCtlInfo(SalequoScaleProcessor.MNYKEYS, PosEnum.body,
        null, SalequotationHVO.PK_CURRTYPE, PosEnum.head, null);
    // 表头合计精度处理
    if (totalscale != null) {
      totalscale.setHeadTailKeys(new String[] {
        SalequotationHVO.NTOTALNUM
      });
    }
    scaleprocess.process();

  }

  public void setCardPrecision(String pk_group, BillCardPanel cardpanel) {
    BillScaleProcessor scaleprocess =
        new CardPaneScaleProcessor(pk_group, cardpanel);
    TotalValueScale totalscale = new TotalValueScaleProcessor(cardpanel);
    this.setBillPrecision(scaleprocess, totalscale);
  }

  public void setListPrecision(String pk_group, BillListPanel listPanel) {
    BillScaleProcessor scaleprocess =
        new ListPaneScaleProcessor(pk_group, listPanel);
    TotalValueScale totalscale = new TotalValueScaleProcessor(listPanel);
    this.setBillPrecision(scaleprocess, totalscale);
  }

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

  private void setTablePrecision(TableScaleProcessor scaleprocess) {
    // 原币价税合计（表头）
    scaleprocess.setMnyCtlInfo(SalequoScaleProcessor.HEADTAXMNY,
        SalequotationHVO.PK_CURRTYPE);
    // 税率
    scaleprocess.setTaxRateCtlInfo(SalequoScaleProcessor.TAXRATEKEY);
    // 换算率
    scaleprocess.setHslCtlInfo(SalequoScaleProcessor.CONVERTRATEKEYS);
    // 数量
    scaleprocess.setNumCtlInfo(SalequoScaleProcessor.ASTNUMKEYS,
        SalequotationBVO.CASTUNITID);
    // 主数量
    scaleprocess.setNumCtlInfo(SalequoScaleProcessor.NUMKEYS,
        SalequotationBVO.PK_UNIT);
    // 报价数量
    scaleprocess.setNumCtlInfo(SalequoScaleProcessor.QTNUMKEYS,
        SalequotationBVO.CQTUNITID);
    // 单价
    scaleprocess.setPriceCtlInfo(SalequoScaleProcessor.PRICEKEYS,SalequotationHVO.PK_CURRTYPE);
    // 金额
    scaleprocess.setMnyCtlInfo(SalequoScaleProcessor.MNYKEYS,
        SalequotationHVO.PK_CURRTYPE);

    scaleprocess.process();
  }

  public void setVOPrecision(String pk_group, AggregatedValueObject[] bills) {
    BillScaleProcessor scaleprocess = new BillVOScaleProcessor(pk_group, bills);
    this.setBillPrecision(scaleprocess);
  }
}
