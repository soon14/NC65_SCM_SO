package nc.vo.so.m32.rule;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.util.SOCurrencyUtil;
import nc.vo.so.pub.util.SOPubParaUtil;
import nc.vo.trade.checkrule.VOChecker;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售发票折本汇率业务规则，实现获取当前折本汇率
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 冯加滨
 * @time 2010-4-22 下午08:11:18
 */
public class ExchangeRateRule {

  private IKeyValue keyValue;

  public ExchangeRateRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 方法功能描述：获得当前单据日期对应的折本汇率。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @author 冯加滨
   * @time 2010-4-21 上午11:48:13
   */
  public void calcExchangeRate() {
    // 单据日期
    UFDate billdate =
        this.keyValue.getHeadUFDateValue(SaleInvoiceHVO.DBILLDATE);
    // 原币币种
    String orgcurrency =
        this.keyValue.getHeadStringValue(SaleInvoiceHVO.CORIGCURRENCYID);
    // 本位币
    String currency =
        this.keyValue.getHeadStringValue(SaleInvoiceHVO.CCURRENCYID);

    UFDouble changestrate = null;
    UFDouble groupchangestrate = null;
    UFDouble globlchangestrate = null;
    if (!VOChecker.isEmpty(orgcurrency) && !VOChecker.isEmpty(currency)) {
      String pk_org = this.keyValue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
      String pk_group =
          this.keyValue.getHeadStringValue(SaleInvoiceHVO.PK_GROUP);
      changestrate =
          SOCurrencyUtil.getInCurrencyRateByOrg(pk_org, orgcurrency, currency,
              billdate);
      if (this.isCurToGroupMoney(pk_group)) {
        groupchangestrate =
            SOCurrencyUtil.getGroupLocalCurrencyBuyRate(currency, billdate);
      }
      else if (this.isOrigCurToGroupMoney(pk_group)) {
        groupchangestrate =
            SOCurrencyUtil.getGroupLocalCurrencyBuyRate(orgcurrency, billdate);
      }

      if (this.isCurToGlobalMoney()) {
        globlchangestrate =
            SOCurrencyUtil.getGroupLocalCurrencyBuyRate(currency, billdate);
      }
      else if (this.isOrigCurToGlobalMoney()) {
        globlchangestrate =
            SOCurrencyUtil.getGroupLocalCurrencyBuyRate(orgcurrency, billdate);
      }

    }
    this.keyValue.setHeadValue(SaleInvoiceHVO.NEXCHANGERATE, changestrate);
    this.keyValue.setHeadValue(SaleInvoiceHVO.NGLOBALEXCHGRATE,
        globlchangestrate);
    this.keyValue.setHeadValue(SaleInvoiceHVO.NGROUPEXCHGRATE,
        groupchangestrate);
  }

  /**
   * 全局汇率基于组织本位币计算
   * 
   * @return
   */
  private boolean isCurToGlobalMoney() {
    return SOPubParaUtil.getInstance().isGlobalLocalCurrencyEnable()
        && !SOPubParaUtil.getInstance().isOrigCurToGlobalMoney();
  }

  /**
   * 集团汇率基于组织本位币计算
   * 
   * @return
   */
  private boolean isCurToGroupMoney(String pk_group) {
    return SOPubParaUtil.getInstance().isGroupLocalCurrencyEnable(pk_group)
        && !SOPubParaUtil.getInstance().isOrigCurToGroupMoney(pk_group);
  }

  /**
   * 全局汇率基于组织原币计算
   * 
   * @return
   */
  private boolean isOrigCurToGlobalMoney() {
    return SOPubParaUtil.getInstance().isGlobalLocalCurrencyEnable()
        && SOPubParaUtil.getInstance().isOrigCurToGlobalMoney();
  }

  /**
   * 集团汇率基于原币计算
   * 
   * @return
   */
  private boolean isOrigCurToGroupMoney(String pk_group) {
    return SOPubParaUtil.getInstance().isGroupLocalCurrencyEnable(pk_group)
        && SOPubParaUtil.getInstance().isOrigCurToGroupMoney(pk_group);
  }
}
