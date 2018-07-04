package nc.vo.so.pub.precision;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.BillVOScaleProcessor;
import nc.vo.pubapp.scale.FieldInfo;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TotalValueScale;
import nc.vo.pubapp.scale.TotalValueVOScaleProcessor;
import nc.vo.so.pub.SOItemKey;

/**
 * 打印vo精度处理
 * 
 * @since 6.0
 * @version 2010-12-31 上午10:16:36
 * @author 祝会征
 */
public class SoVoPrecionScale {

  private BillScaleProcessor scale;

  private TotalValueScale totalScale;

  /**
   * 返回处理vo表体的精度
   * 
   * @return
   */
  public SoVoPrecionScale(String pk_group, AggregatedValueObject[] bills) {
    this.scale = new BillVOScaleProcessor(pk_group, bills);
    this.totalScale = new TotalValueVOScaleProcessor(bills);
  }

  /**
   * 处理vo精度
   */
  public void setScale() {
    this.setPriceScale();
    this.setNumScale();
    this.setLocalScale();
    this.setMoneyScale();
    this.setRateScale();
    this.setDiscountScale();
    // 进行计算
    this.scale.process();
    this.setHeadDataScale();
  }

  protected BillScaleProcessor getScale() {
    return this.scale;
  }

  /**
   * 返回表头精度
   * 
   * @return
   */
  protected TotalValueScale getTotalScale() {
    return this.totalScale;
  }

  /**
   * 设置折扣精度
   * 
   * @return
   */
  protected void setDiscountScale() {
    /** 表头 */
    // 整单折扣
    String[] headDiscountKeys = new String[] {
      SOItemKey.NDISCOUNTRATE,"nhvoicedisrate"
    };

    // 整单折扣、单品折扣
    String[] bodyDiscountKeys = new String[] {
      SOItemKey.NDISCOUNTRATE, SOItemKey.NITEMDISCOUNTRATE
    };

    // 整单折扣
    this.scale.setSaleDiscountCtlInfo(headDiscountKeys, PosEnum.head, null);
    // 整单折扣、单品折扣
    this.scale.setSaleDiscountCtlInfo(bodyDiscountKeys, PosEnum.body, null);
  }

  protected void setHeadDataScale() {
    // 表头合计
    String[] totalKeys = new String[] {
      SOItemKey.NTOTALNUM/*, SOItemKey.NTOTALORIGMNY, SOItemKey.NTOTALORIGSUBMNY*/
      ,"ntotalastnum","ntotalpiece","ntotalweight","ntotalvolume"
    };
    // 从这里设置不起作用，设置表头精度代码已经注掉
    this.totalScale.setHeadTailKeys(totalKeys);
  }

  /**
   * 设置集团本位币和组织本位币精度
   */
  protected void setLocalScale() {
    // 全局本位币金额
    String[] globalmnykeys = new String[] {
      SOItemKey.NGLOBALMNY, SOItemKey.NGLOBALTAXMNY
    };
    // 集团本位币金额
    String[] groupmnykeys = new String[] {
      SOItemKey.NGROUPMNY, SOItemKey.NGROUPTAXMNY
    };
    // 全局本位币金额精度
    this.scale.setGlobalLocMnyCtlInfo(globalmnykeys, PosEnum.body, null);
    // 集团本位币金额精度
    this.scale.setGroupLocMnyCtlInfo(groupmnykeys, PosEnum.body, null);
  }

  /**
   * 设置金额精度
   */
  protected void setMoneyScale() {
    // 原币金额
    String[] orgmnykeys =
        new String[] {
          SOItemKey.NORIGMNY, SOItemKey.NORIGTAX, SOItemKey.NORIGTAXMNY,
          SOItemKey.NORIGDISCOUNT, SOItemKey.NORIGSUBMNY,
          // 销售费用单“金额”、”订单冲抵金额“、“红字应收金额”、“发票冲抵金额 ”、”余额“
          "norigarsubmny", "nordersubmny", "nredarsubmny", "ninvoicesubmny",
          "nremainmny"
        };

    // 打印模板表头原币金额
    String[] headOrgMnyKeys = new String[] {
      SOItemKey.NTOTALORIGMNY, SOItemKey.NTOTALORIGSUBMNY,
      "ntotalmny","npreceivequota","nreceivedmny","npreceivemny"
    };

    // 表体原币金额
    String[] bodyorgmnykeys =
        new String[] {
          SOItemKey.NORIGTAXMNY, SOItemKey.NORIGSUBMNY, SOItemKey.NCALTAXMNY,
          SOItemKey.NTAX
        };
    // 本币金额
    String[] mnykeys = new String[] {
      SOItemKey.NMNY, SOItemKey.NTAXMNY, SOItemKey.NTAX, SOItemKey.NDISCOUNT
    };
    // 本币金额精度
    this.scale.setMnyCtlInfo(mnykeys, PosEnum.body, null,
        SOItemKey.CCURRENCYID, PosEnum.body, null);
    // 原币金额精度
    this.scale.setMnyCtlInfo(orgmnykeys, PosEnum.body, null,
        SOItemKey.CORIGCURRENCYID, PosEnum.head, null);

    // 原币金额精度 --打印模板表头原币金额 ---yixl 2013-03-07添加
    this.scale.setMnyCtlInfo(headOrgMnyKeys, PosEnum.head, null,
        SOItemKey.CORIGCURRENCYID, PosEnum.head, null);
    // 表体原币金额精度
    this.scale.setMnyCtlInfo(bodyorgmnykeys, PosEnum.body, null,
        SOItemKey.CORIGCURRENCYID, PosEnum.head, null);
  }

  /**
   * 设置数量精度
   */
  protected void setNumScale() {

    // 业务单位数量
    String[] assistNumkeys = new String[] {
      SOItemKey.NASTNUM
    };
    // 主数量
    String[] numkeys = new String[] {
      SOItemKey.NNUM,"noutnotauditnum","nnotarnum","nlossnotauditnum","nnotcostnum",
      "ninvunfinisednum","ninvoiceauditnum","noutunfinisednum","noutauditnum","nsendunfinisednum",
      "nsendunfinisednum","nsendauditnum"
    };
    String[] qtnumkeys = new String[] {
      SOItemKey.NQTUNITNUM
    };
    // 主单位数量精度
    this.scale.setNumCtlInfo(numkeys, PosEnum.body, null, SOItemKey.CUNITID,
        PosEnum.body, null);
    // 业务单位数量精度
    this.scale.setNumCtlInfo(assistNumkeys, PosEnum.body, null,
        SOItemKey.CASTUNITID, PosEnum.body, null);
    // 报价单位数量
    this.scale.setNumCtlInfo(qtnumkeys, PosEnum.body, null,
        SOItemKey.CQTUNITID, PosEnum.body, null);
  }

  /**
   * 设置价格精度
   */
  protected void setPriceScale() {
    // 单价
    String[] pricekeys =
        new String[] {
          SOItemKey.NORIGNETPRICE, SOItemKey.NORIGTAXNETPRICE,
          SOItemKey.NORIGPRICE, SOItemKey.NORIGTAXPRICE,
          SOItemKey.NQTORIGNETPRICE, SOItemKey.NQTORIGTAXNETPRC,
          SOItemKey.NQTORIGPRICE, SOItemKey.NQTORIGTAXPRICE,
        };

    String[] netpricekeys =
        new String[] {
          SOItemKey.NQTNETPRICE, SOItemKey.NQTPRICE, SOItemKey.NQTTAXPRICE,
          SOItemKey.NQTTAXNETPRICE, SOItemKey.NNETPRICE, SOItemKey.NPRICE,
          SOItemKey.NTAXNETPRICE, SOItemKey.NTAXPRICE
        };
    this.scale.setPriceCtlInfo(pricekeys, PosEnum.body, null,
        SOItemKey.CORIGCURRENCYID, PosEnum.head, null);

    this.scale.setPriceCtlInfo(netpricekeys, PosEnum.body, null,
        SOItemKey.CCURRENCYID, PosEnum.body, null);
  }
  
  

  /**
   * 设置税率 折本汇率精度
   */
  protected void setRateScale() {
    // 表体税率
    String[] taxRateKey_B = new String[] {
      SOItemKey.NTAXRATE
    };
    // 表体税率
    this.scale.setTaxRateCtlInfo(taxRateKey_B, PosEnum.body, null);
    
    FieldInfo exchangeRate = new FieldInfo(SOItemKey.NEXCHANGERATE,
        PosEnum.head.getCode(), null);
    
    // 原币
    FieldInfo localOrigCurr = new FieldInfo(
        SOItemKey.CORIGCURRENCYID, PosEnum.head.getCode(), null);

    // 组织本币
    FieldInfo orgCurr = new FieldInfo(SOItemKey.CCURRENCYID,
        PosEnum.head.getCode(), null);

    // 财务组织
    FieldInfo settleOrg = new FieldInfo(SOItemKey.PK_ORG,
        PosEnum.head.getCode(), null);
    
    // 折本汇率
    scale.setOrgExchangeCtlInfo(exchangeRate, localOrigCurr,
        orgCurr, settleOrg);
  }
}
