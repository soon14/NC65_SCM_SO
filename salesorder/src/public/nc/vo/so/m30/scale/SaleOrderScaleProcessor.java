package nc.vo.so.m30.scale;

import org.apache.commons.lang.ArrayUtils;

import nc.scmmm.vo.scmpub.scale.BillVOScaleCheckProcessor;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.FieldInfo;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TotalValueScale;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 销售订单精度处理类（前台从此继承）
 * 
 * @since 6.3
 * @version 2013-6-14 上午10:38:43
 * @author tianft
 */
public class SaleOrderScaleProcessor {

  /** 表体 */
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
    SaleOrderBVO.NDISCOUNT, SaleOrderBVO.NCALTAXMNY
  };

  // 主数量
  private static final String[] NUMKEYS = new String[] {
    SaleOrderBVO.NNUM, SaleOrderBVO.NTOTALARNUM, SaleOrderBVO.NTOTALCOSTNUM,
    SaleOrderBVO.NTOTALESTARNUM, SaleOrderBVO.NTOTALINVOICENUM,
    SaleOrderBVO.NTOTALNOTOUTNUM, SaleOrderBVO.NTOTALOUTNUM,
    SaleOrderBVO.NTOTALRETURNNUM, SaleOrderBVO.NTOTALRUSHNUM,
    SaleOrderBVO.NTOTALSENDNUM, SaleOrderBVO.NTOTALSIGNNUM,
    SaleOrderBVO.NTOTALTRADENUM, SaleOrderBVO.NREQRSNUM
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
    SaleOrderBVO.NTOTALTRADENUM,
  };

  // 原币数量（表头）
  private static final String[] NUMHEAD = new String[] {
    SaleOrderHVO.NTOTALNUM, SaleOrderHVO.NTOTALVOLUME,
    SaleOrderHVO.NTOTALWEIGHT,
  };

  // 原币金额
  private static final String[] ORIGMNYKEYS = new String[] {
    SaleOrderBVO.NORIGMNY, SaleOrderBVO.NORIGTAXMNY,
    SaleOrderBVO.NORIGDISCOUNT, SaleOrderBVO.NBFORIGSUBMNY,
    SaleOrderBVO.NTOTALARMNY, SaleOrderBVO.NTOTALESTARMNY,
    SaleOrderBVO.NTOTALPAYMNY,SaleOrderBVO.NORIGSUBMNY
  };

  // 原币金额（表头）
  private static final String[] ORIGMNYKEYSHEAD = new String[] {
    SaleOrderHVO.NTOTALMNY, SaleOrderHVO.NTOTALORIGMNY,
    SaleOrderHVO.NTOTALORIGSUBMNY, SaleOrderHVO.NPRECEIVEMNY,
    SaleOrderHVO.NPRECEIVEQUOTA, SaleOrderHVO.NRECEIVEDMNY,
    SaleOrderHVO.NTHISRECEIVEMNY
  };

  // 件数
  private static final String[] PIECEKEY = new String[] {
    SaleOrderBVO.NPIECE
  };

  // 单价
  private static final String[] PRICEKEYS = new String[] {
    SaleOrderBVO.NORIGTAXPRICE, SaleOrderBVO.NORIGPRICE,
    SaleOrderBVO.NORIGTAXNETPRICE, SaleOrderBVO.NORIGNETPRICE,
    SaleOrderBVO.NQTORIGTAXPRICE, SaleOrderBVO.NQTORIGPRICE,
    SaleOrderBVO.NQTORIGTAXNETPRC, SaleOrderBVO.NQTORIGNETPRICE,

  };

  private static final String[] netpricekeys = new String[] {
    SaleOrderBVO.NTAXPRICE, SaleOrderBVO.NPRICE, SaleOrderBVO.NTAXNETPRICE,
    SaleOrderBVO.NNETPRICE, SaleOrderBVO.NQTTAXPRICE, SaleOrderBVO.NQTPRICE,
    SaleOrderBVO.NQTTAXNETPRICE, SaleOrderBVO.NQTNETPRICE,
    SaleOrderBVO.NASKQTORIGNETPRICE, SaleOrderBVO.NASKQTORIGPRICE,
    SaleOrderBVO.NASKQTORIGTAXPRC, SaleOrderBVO.NASKQTORIGTXNTPRC
  };

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

  public void setBillPrecision(BillScaleProcessor scaleprocess,
      TotalValueScale totalscale) {
    this.setBillPrecision(scaleprocess, totalscale, null, false);
  }

  /**
   * 精度检查
   * 
   * @param vos
   */
  public void checkBillPrecision(SaleOrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    String pk_group = vos[0].getParentVO().getPk_group();
    BillVOScaleCheckProcessor scaleChecker =
        new BillVOScaleCheckProcessor(pk_group, vos);
    this.setBillPrecision(scaleChecker, null, null, true);
  }

  protected void setBillPrecision(BillScaleProcessor scaleprocess,
      TotalValueScale totalscale, PosEnum singlePos, boolean forScaleCheck) {
    PosEnum head = PosEnum.head;
    PosEnum body = PosEnum.body;
    if (singlePos != null) {
      head = singlePos;
      body = singlePos;
    }
    // 集团本币金额
    scaleprocess.setGroupLocMnyCtlInfo(SaleOrderScaleProcessor.GROUPMNYKEYS,
        body, null);
    // 全局本币金额
    scaleprocess.setGlobalLocMnyCtlInfo(SaleOrderScaleProcessor.GLOBALMNYKEYS,
        body, null);
    // 精度检查不需要处理的
    if (!forScaleCheck) {
      // 换算率
      scaleprocess.setHslCtlInfo(SaleOrderScaleProcessor.HSLKEYS, body, null);
      // 重量
      scaleprocess.setWeightCtlInfo(SaleOrderScaleProcessor.WEIGHTKEY, body,
          null);
      // 体积
      scaleprocess.setVolumnCtlInfo(SaleOrderScaleProcessor.VOLUMEKEY, body,
          null);
      // 件数
      scaleprocess.setUnitCtlInfo(SaleOrderScaleProcessor.PIECEKEY, body, null,
          SaleOrderBVO.CMATERIALID, body, null);
    }
    // 单价
    scaleprocess.setPriceCtlInfo(SaleOrderScaleProcessor.PRICEKEYS, body, null,
        SaleOrderHVO.CORIGCURRENCYID, head, null);

    // 本币单价
    scaleprocess.setPriceCtlInfo(SaleOrderScaleProcessor.netpricekeys, body,
        null, SaleOrderBVO.CCURRENCYID, body, null);

    // 数量
    scaleprocess.setNumCtlInfo(SaleOrderScaleProcessor.ASTNUMKEYS, body, null,
        SaleOrderBVO.CASTUNITID, body, null);
    // 主数量
    scaleprocess.setNumCtlInfo(SaleOrderScaleProcessor.NUMKEYS, body, null,
        SaleOrderBVO.CUNITID, body, null);
    // 累计***主数量
    scaleprocess.setNumCtlInfo(SaleOrderScaleProcessor.GATHERNUMKEYS, body,
        null, SaleOrderBVO.CUNITID, body, null);
    // 报价数量
    scaleprocess.setNumCtlInfo(SaleOrderScaleProcessor.QTNUMKEYS, body, null,
        SaleOrderBVO.CQTUNITID, body, null);
    // 本币金额
    scaleprocess.setMnyCtlInfo(SaleOrderScaleProcessor.MNYKEYS, body, null,
        SaleOrderBVO.CCURRENCYID, body, null);
    // 原币金额
    scaleprocess.setMnyCtlInfo(SaleOrderScaleProcessor.ORIGMNYKEYS, body, null,
        SaleOrderHVO.CORIGCURRENCYID, head, null);

    // 原币金额（表头）
    scaleprocess.setMnyCtlInfo(SaleOrderScaleProcessor.ORIGMNYKEYSHEAD, head,
        null, SaleOrderHVO.CORIGCURRENCYID, head, null);
    // 表头折扣
    scaleprocess.setSaleDiscountCtlInfo(
        SaleOrderScaleProcessor.HEAD_DISRATEKEYS, head, null);
    // 表体折扣
    scaleprocess.setSaleDiscountCtlInfo(
        SaleOrderScaleProcessor.BODY_DISRATEKEYS, body, null);
    if (totalscale != null) {
      // 表头合计数量
      totalscale.setHeadTailKeys(SaleOrderScaleProcessor.NUMHEAD);
    }

    // 折本汇率
    this.setOrgExchangeCtlInfo(scaleprocess, singlePos);
    // 全局本位币汇率
    this.setGlobalExchangeCtlInfo(scaleprocess, singlePos);
    // 集团本位币汇率
    this.setGroupExchangeCtlInfo(scaleprocess, singlePos);

    // 税率
    scaleprocess.setTaxRateCtlInfo(SaleOrderScaleProcessor.TAXRATEKEY, body,
        null);

    scaleprocess.process();
  }

  private void setGroupExchangeCtlInfo(BillScaleProcessor scaleprocess,
      PosEnum pos) {
    // 原币
    FieldInfo localOrigCurr =
        new FieldInfo(SaleOrderHVO.CORIGCURRENCYID,
            pos == null ? PosEnum.head.getCode() : pos.getCode(), null);
    // 组织本币
    FieldInfo orgCurr =
        new FieldInfo(SaleOrderBVO.CCURRENCYID,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    // 集团折本汇率
    FieldInfo groupExchgRate =
        new FieldInfo(SaleOrderBVO.NGROUPEXCHGRATE,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);

    scaleprocess
        .setGroupExchangeCtlInfo(groupExchgRate, localOrigCurr, orgCurr);
  }

  private void setGlobalExchangeCtlInfo(BillScaleProcessor scaleprocess,
      PosEnum pos) {
    // 全局折本汇率
    FieldInfo globalExchgRate =
        new FieldInfo(SaleOrderBVO.NGLOBALEXCHGRATE,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    // 原币
    FieldInfo localOrigCurr =
        new FieldInfo(SaleOrderHVO.CORIGCURRENCYID,
            pos == null ? PosEnum.head.getCode() : pos.getCode(), null);
    // 组织本币
    FieldInfo orgCurr =
        new FieldInfo(SaleOrderBVO.CCURRENCYID,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);

    scaleprocess.setGlobalExchangeCtlInfo(globalExchgRate, localOrigCurr,
        orgCurr);
  }

  private void setOrgExchangeCtlInfo(BillScaleProcessor scaleprocess,
      PosEnum pos) {
    // 原币
    FieldInfo localOrigCurr =
        new FieldInfo(SaleOrderHVO.CORIGCURRENCYID,
            pos == null ? PosEnum.head.getCode() : pos.getCode(), null);
    // 组织本币
    FieldInfo orgCurr =
        new FieldInfo(SaleOrderBVO.CCURRENCYID,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    // 财务组织
    FieldInfo settleOrg =
        new FieldInfo(SaleOrderBVO.CSETTLEORGID,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    /** 汇率精度 */
    // 折本汇率
    FieldInfo exchangeRate =
        new FieldInfo(SaleOrderBVO.NEXCHANGERATE,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    scaleprocess.setOrgExchangeCtlInfo(exchangeRate, localOrigCurr, orgCurr,
        settleOrg);
  }

}
