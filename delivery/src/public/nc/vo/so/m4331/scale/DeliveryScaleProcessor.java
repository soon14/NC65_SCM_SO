package nc.vo.so.m4331.scale;

import nc.scmmm.vo.scmpub.scale.BillVOScaleCheckProcessor;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.FieldInfo;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TotalValueScale;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * 发货单精度处理类
 * 
 * @since 6.3
 * @version 2013-6-14 下午04:55:39
 * @author tianft
 */
public class DeliveryScaleProcessor {

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
  
  /**
   * 本币单价
   */
  private static String[] netpricekeys=new String[]{
    DeliveryBVO.NTAXPRICE, DeliveryBVO.NPRICE, DeliveryBVO.NTAXNETPRICE,
    DeliveryBVO.NNETPRICE, DeliveryBVO.NQTTAXPRICE, DeliveryBVO.NQTPRICE,
    DeliveryBVO.NQTTAXNETPRICE, DeliveryBVO.NQTNETPRICE};

  // 报价数量
  private static String[] qtnumkeys = new String[] {
    DeliveryBVO.NQTUNITNUM
  };

  // 表体税率
  private static String[] taxratekey = new String[] {
    DeliveryBVO.NTAXRATE
  };

  /**
   * DeliverySacleProcessor 的构造子
   */
  public DeliveryScaleProcessor() {
    //
  }

  protected void setBillPrecision(BillScaleProcessor scaleprocess,
      TotalValueScale totalscale, PosEnum singlePos, boolean forScaleCheck) {
    PosEnum body = PosEnum.body;
    if (singlePos != null) {
      body = singlePos;
    }
    // 精度检查屏蔽部分
    if (!forScaleCheck) {
      // 换算率
      scaleprocess.setHslCtlInfo(DeliveryScaleProcessor.hslkeys, body, null);
    }
    // 原币单价
    scaleprocess.setPriceCtlInfo(DeliveryScaleProcessor.pricekeys, body, null,DeliveryBVO.CORIGCURRENCYID,body,null);
    // 本币单价
    scaleprocess.setPriceCtlInfo(DeliveryScaleProcessor.netpricekeys, body, null,DeliveryBVO.CCURRENCYID,body,null);
    
    // 数量
    scaleprocess.setNumCtlInfo(DeliveryScaleProcessor.astnumkeys, body, null,
        DeliveryBVO.CASTUNITID, body, null);
    // 主数量
    scaleprocess.setNumCtlInfo(DeliveryScaleProcessor.numkeys, body, null,
        DeliveryBVO.CUNITID, body, null);
    // 整单折扣、单品折扣
    scaleprocess.setSaleDiscountCtlInfo(DeliveryScaleProcessor.bdiscountkeys,
        body, null);
    // 报价数量
    scaleprocess.setNumCtlInfo(DeliveryScaleProcessor.qtnumkeys, body, null,
        DeliveryBVO.CQTUNITID, body, null);
    // 本币金额
    scaleprocess.setMnyCtlInfo(DeliveryScaleProcessor.mnykeys, body, null,
        DeliveryBVO.CCURRENCYID, body, null);
    // 原币金额
    scaleprocess.setMnyCtlInfo(DeliveryScaleProcessor.origmnykeys, body, null,
        DeliveryBVO.CORIGCURRENCYID, body, null);
    // 税率
    scaleprocess.setTaxRateCtlInfo(DeliveryScaleProcessor.taxratekey, body,
        null);
    // 折本汇率
    this.setOrgExchangeCtlInfo(scaleprocess, singlePos);
    // 全局本位币汇率
    this.setGlobalExchangeCtlInfo(scaleprocess, singlePos);
    // 集团本位币汇率
    this.setGroupExchangeCtlInfo(scaleprocess, singlePos);
    scaleprocess.setWeightCtlInfo(DeliveryScaleProcessor.bodyweightKey, body,
        null);
    scaleprocess
        .setVolumnCtlInfo(DeliveryScaleProcessor.bodyvolKey, body, null);
    scaleprocess.setUnitCtlInfo(DeliveryScaleProcessor.bodypieceKey, body,
        null, DeliveryBVO.CMATERIALVID, body, null);
    // 集团本币金额
    scaleprocess.setGroupLocMnyCtlInfo(DeliveryScaleProcessor.GROUPMNYKEYS,
        body, null);
    // 全局本币金额
    scaleprocess.setGlobalLocMnyCtlInfo(DeliveryScaleProcessor.GLOBALMNYKEYS,
        body, null);
    if (totalscale != null) {
      // 表头合计数量
      totalscale.setHeadTailKeys(DeliveryScaleProcessor.NUMHEAD);
    }
    scaleprocess.process();

  }

  /**
   * 
   * 
   * @param scaleprocess
   */
  public void setBillPrecision(BillScaleProcessor scaleprocess,
      TotalValueScale totalscale) {
    this.setBillPrecision(scaleprocess, totalscale, null, false);
  }

  /**
   * 用于精度检查
   * 
   * @param scale
   * @param totalScale
   */
  public void checkBillPrecision(DeliveryVO[] vos) {
    String pk_group = AppContext.getInstance().getPkGroup();
    BillVOScaleCheckProcessor scaleprocess =
        new BillVOScaleCheckProcessor(pk_group, vos);
    this.setBillPrecision(scaleprocess, null, null, true);
  }

  private void setOrgExchangeCtlInfo(BillScaleProcessor scaleprocess,
      PosEnum pos) {
    // 折本汇率
    FieldInfo exchangeRate =
        new FieldInfo(DeliveryBVO.NEXCHANGERATE,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    // 原币
    FieldInfo localOrigCurr =
        new FieldInfo(DeliveryBVO.CORIGCURRENCYID,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    // 组织本币
    FieldInfo orgCurr =
        new FieldInfo(DeliveryBVO.CCURRENCYID,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    // 财务组织
    FieldInfo settleOrg =
        new FieldInfo(DeliveryBVO.CSETTLEORGID,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);

    scaleprocess.setOrgExchangeCtlInfo(exchangeRate, localOrigCurr, orgCurr,
        settleOrg);
  }

  private void setGroupExchangeCtlInfo(BillScaleProcessor scaleprocess,
      PosEnum pos) {
    // 原币
    FieldInfo localOrigCurr =
        new FieldInfo(DeliveryBVO.CORIGCURRENCYID,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    // 组织本币
    FieldInfo orgCurr =
        new FieldInfo(DeliveryBVO.CCURRENCYID,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    // 集团折本汇率
    FieldInfo groupExchgRate =
        new FieldInfo(DeliveryBVO.NGROUPEXCHGRATE,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    scaleprocess
        .setGroupExchangeCtlInfo(groupExchgRate, localOrigCurr, orgCurr);
  }

  private void setGlobalExchangeCtlInfo(BillScaleProcessor scaleprocess,
      PosEnum pos) {
    // 原币
    FieldInfo localOrigCurr =
        new FieldInfo(DeliveryBVO.CORIGCURRENCYID,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    // 组织本币
    FieldInfo orgCurr =
        new FieldInfo(DeliveryBVO.CCURRENCYID,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    // 全局折本汇率
    FieldInfo globalExchgRate =
        new FieldInfo(DeliveryBVO.NGLOBALEXCHGRATE,
            pos == null ? PosEnum.body.getCode() : pos.getCode(), null);
    scaleprocess.setGlobalExchangeCtlInfo(globalExchgRate, localOrigCurr,
        orgCurr);
  }

}
