package nc.vo.so.pub.enumeration;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.so.pub.SOItemKey;

/**
 * 销售订单分单打印规则
 * 
 * @since 6.0
 * @version 2011-1-6 下午06:46:36
 * @author 祝会征
 */

public enum SaleOrderSplitRule {
  CRECEIVEADDDOCID(SOItemKey.CRECEIVEADDDOCID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0049")/*收货地点*/),
  CRECEIVEAREAID(SOItemKey.CRECEIVEAREAID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0050")/*收货地区*/),
  CRECEIVECUSTID(SOItemKey.CRECEIVECUSTID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0051")/*收货客户*/),
  CSENDSTOCKORGID(SOItemKey.CSENDSTOCKORGID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0052")/*发货库存组织*/),
  CSENDSTORDOCID(SOItemKey.CSENDSTORDOCID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0053")/*发货仓库*/),
  DSENDDATE(SOItemKey.DSENDDATE, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0059")/*发货日期*/), CROWNO(
			SOItemKey.CROWNO, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0240")/*行号*/);
  // 单据类型
  private String key;

  // 单据名称
  private String name;

  private SaleOrderSplitRule(String key, String name) {
    this.key = key;
    this.name = name;
  }

  public String getKey() {
    return this.key;
  }

  public String getName() {
    return this.name;
  }

}
