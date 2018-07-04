package nc.vo.so.m32.scale;

import org.apache.commons.lang.ArrayUtils;

import nc.scmmm.vo.scmpub.scale.BillVOScaleCheckProcessor;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.FieldInfo;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TotalValueScale;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * 销售发票精度处理类
 * 
 * @since 6.3
 * @version 2013-6-18 下午07:35:03
 * @author tianft
 */
public class SaleInvoiceScaleProcessor {

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
    SaleInvoiceBVO.NTOTALINCOMEMNY, SaleInvoiceBVO.NTOTALPAYMNY
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
  
  private static final String[] netpricekeys=new String[]{
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

  /**
   * SaleInvoiceScaleProcessor 的构造子
   */
  public SaleInvoiceScaleProcessor() {
    // 缺省构造方法
  }

  public void setBillPrecision(BillScaleProcessor scaleprocess,
      TotalValueScale totalscale) {
    this.setBillPrecision(scaleprocess, totalscale, null, false);
  }

  /**
   * 精度检查
   * 
   * @param vos
   */
  public void checkBillPrecision(SaleInvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    String pk_group = vos[0].getParentVO().getPk_group();
    BillVOScaleCheckProcessor scaleChecker =
        new BillVOScaleCheckProcessor(pk_group, vos);
    this.setBillPrecision(scaleChecker, null, null, true);
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
  protected void setBillPrecision(BillScaleProcessor scaleprocess,
      TotalValueScale totalscale, PosEnum singlePos, boolean forScaleCheck) {
    // 集团本币金额
    scaleprocess.setGroupLocMnyCtlInfo(SaleInvoiceScaleProcessor.GROUPMNYKEY,
        PosEnum.body, null);
    // 本币金额
    scaleprocess.setOrgLocMnyCtlInfo(SaleInvoiceScaleProcessor.MNYKEY,
        PosEnum.body, null);
    // 全局本币金额
    scaleprocess.setGlobalLocMnyCtlInfo(SaleInvoiceScaleProcessor.GLOBALMNYKEY,
        PosEnum.body, null);
    if (!forScaleCheck) {
      // 换算率精度
      scaleprocess.setHslCtlInfo(SaleInvoiceScaleProcessor.HSLKEY,
          PosEnum.body, null);
    }
    // 单价
    scaleprocess.setPriceCtlInfo(SaleInvoiceScaleProcessor.PRICEKEY,
        PosEnum.body, null,SaleInvoiceHVO.CORIGCURRENCYID,PosEnum.head,null);
    
    // 本币单价
    scaleprocess.setPriceCtlInfo(SaleInvoiceScaleProcessor.netpricekeys,
        PosEnum.body, null,SaleInvoiceHVO.CCURRENCYID,PosEnum.head,null);
    
    
    // 数量
    scaleprocess.setNumCtlInfo(SaleInvoiceScaleProcessor.ASTNUMKEY,
        PosEnum.body, null, SaleInvoiceBVO.CASTUNITID, PosEnum.body, null);
    // 主数量
    scaleprocess.setNumCtlInfo(SaleInvoiceScaleProcessor.NUMKEY, PosEnum.body,
        null, SaleInvoiceBVO.CUNITID, PosEnum.body, null);
    // 报价数量
    scaleprocess.setNumCtlInfo(SaleInvoiceScaleProcessor.QTNUMKEY,
        PosEnum.body, null, SaleInvoiceBVO.CQTUNITID, PosEnum.body, null);
    // 表头折扣
    scaleprocess.setSaleDiscountCtlInfo(
        SaleInvoiceScaleProcessor.HEAD_DISRATEKEYS, PosEnum.head, null);
    // 原币金额
    scaleprocess.setMnyCtlInfo(SaleInvoiceScaleProcessor.ORIGMNYKEY,
        PosEnum.body, null, SaleInvoiceHVO.CORIGCURRENCYID, PosEnum.head, null);

    // 原币金额（表头）
    scaleprocess.setMnyCtlInfo(SaleInvoiceScaleProcessor.ORIGMNYKEYHEAD,
        PosEnum.head, null, SaleInvoiceHVO.CORIGCURRENCYID, PosEnum.head, null);
    // 扣率
    scaleprocess.setSaleDiscountCtlInfo(
        SaleInvoiceScaleProcessor.DISCOUNTRATEKEY, PosEnum.body, null);

    // 折本汇率
    this.setOrgExchangeCtlInfo(scaleprocess, singlePos);
    // 全局本位币汇率
    this.setGlobalExchangeCtlInfo(scaleprocess, singlePos);
    // 集团本位币汇率
    this.setGroupExchangeCtlInfo(scaleprocess, singlePos);

    // 税率
    scaleprocess.setTaxRateCtlInfo(SaleInvoiceScaleProcessor.TAXRATEKEY,
        PosEnum.body, null);
    // 表头数量合计
    if (totalscale != null) {
      totalscale.setHeadTailKeys(new String[] {
        SaleInvoiceHVO.NTOTALASTNUM
      });
    }
    scaleprocess.process();

  }

  private void setGroupExchangeCtlInfo(BillScaleProcessor scaleprocess,
      PosEnum pos) {
    // 集团折本汇率
    FieldInfo groupExchgRate =
        new FieldInfo(SaleInvoiceHVO.NGROUPEXCHGRATE,
            pos == null ? PosEnum.head.getCode() : pos.getCode(), null);
    // 原币
    FieldInfo localOrigCurr =
        new FieldInfo(SaleInvoiceHVO.CORIGCURRENCYID,
            pos == null ? PosEnum.head.getCode() : pos.getCode(), null);
    // 组织本币
    FieldInfo orgCurr =
        new FieldInfo(SaleInvoiceHVO.CCURRENCYID,
            pos == null ? PosEnum.head.getCode() : pos.getCode(), null);
    scaleprocess
        .setGroupExchangeCtlInfo(groupExchgRate, localOrigCurr, orgCurr);
  }

  private void setGlobalExchangeCtlInfo(BillScaleProcessor scaleprocess,
      PosEnum pos) {
    // 全局折本汇率
    FieldInfo globalExchgRate =
        new FieldInfo(SaleInvoiceHVO.NGLOBALEXCHGRATE,
            pos == null ? PosEnum.head.getCode() : pos.getCode(), null);
    // 原币
    FieldInfo localOrigCurr =
        new FieldInfo(SaleInvoiceHVO.CORIGCURRENCYID,
            pos == null ? PosEnum.head.getCode() : pos.getCode(), null);
    // 组织本币
    FieldInfo orgCurr =
        new FieldInfo(SaleInvoiceHVO.CCURRENCYID,
            pos == null ? PosEnum.head.getCode() : pos.getCode(), null);

    scaleprocess.setGlobalExchangeCtlInfo(globalExchgRate, localOrigCurr,
        orgCurr);
  }

  private void setOrgExchangeCtlInfo(BillScaleProcessor scaleprocess,
      PosEnum pos) {

    // 折本汇率
    FieldInfo exchangeRate =
        new FieldInfo(SaleInvoiceHVO.NEXCHANGERATE,
            pos == null ? PosEnum.head.getCode() : pos.getCode(), null);
    // 原币
    FieldInfo localOrigCurr =
        new FieldInfo(SaleInvoiceHVO.CORIGCURRENCYID,
            pos == null ? PosEnum.head.getCode() : pos.getCode(), null);
    // 组织本币
    FieldInfo orgCurr =
        new FieldInfo(SaleInvoiceHVO.CCURRENCYID,
            pos == null ? PosEnum.head.getCode() : pos.getCode(), null);

    // 财务组织
    FieldInfo settleOrg =
        new FieldInfo(SaleInvoiceHVO.PK_ORG,
            pos == null ? PosEnum.head.getCode() : pos.getCode(), null);

    scaleprocess.setOrgExchangeCtlInfo(exchangeRate, localOrigCurr, orgCurr,
        settleOrg);
  }

}
