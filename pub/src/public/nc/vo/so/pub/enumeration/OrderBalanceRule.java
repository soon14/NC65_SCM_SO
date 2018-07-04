package nc.vo.so.pub.enumeration;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.so.pub.SOItemKey;

/**
 * 订单收款核销条件
 * 
 * @since 6.0
 * @version 2011-1-6 下午06:46:36
 * @author 祝会征
 */

public enum OrderBalanceRule {
  CARORGID(SOItemKey.CARORGID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0041")/*应收组织*/),
  CORIGCURRENCYID(SOItemKey.CORIGCURRENCYID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0043")/*币种*/),
  CINVOICECUSTID(SOItemKey.CINVOICECUSTID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0044")/*开票客户*/),
  PK_ORG(SOItemKey.PK_ORG, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0106")/*销售组织*/),
  VTRANTYPECODE(SOItemKey.VTRANTYPECODE, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0174")/*销售订单类型*/),
  CCUSTOMERID(SOItemKey.CCUSTOMERID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0048")/*客户*/),
  CDEPTID(SOItemKey.CDEPTID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0088")/*销售部门*/),
  CEMPLOYEEID(SOItemKey.CEMPLOYEEID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0089")/*销售业务员*/),
  CCHANNELTYPEID(SOItemKey.CCHANNELTYPEID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0175")/*渠道类型*/),
  CSETTLEORGID(SOItemKey.CSETTLEORGID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0176")/*结算财务组织*/),
  CPRODLINEID(SOItemKey.CPRODLINEID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0098")/*产品线*/);
  // 单据类型
  private String key;

  // 单据名称
  private String name;

  private OrderBalanceRule(String key, String name) {
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
