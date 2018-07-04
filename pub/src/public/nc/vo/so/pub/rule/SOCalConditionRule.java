package nc.vo.so.pub.rule;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.scmpub.parameter.SCMParameterUtils;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.util.SOCurrencyUtil;

/**
 * 销售管理在进行单价金额算法之前 Condition 的处理
 * 
 * 设置的条件只有公共的跟单据业务无关的condition
 * 1、是否进行本币换算
 * 2、含税优先还是无税优先
 * 3、是否启用集团本币
 * 4、是否启用全局本币
 * 5、本币修改是否折算到原币（默认不折算）
 * 
 * @since 6.0
 * @version 2012-2-23 下午02:19:01
 * @author 么贵敬
 */
public class SOCalConditionRule {

  /**
   * 
   * @return
   */
  public static Condition getCondition() {
    Condition cond = new Condition();
    // 设置是否进行本币换算
    cond.setIsCalLocalCurr(true);
    // 本币金额修改折算原币
    cond.setCalOrigCurr(true);

    // 由参数获得含税优先还是无税优先
    boolean isTaxPrior = SOCalConditionRule.isTaxPrior();
    cond.setIsTaxOrNet(isTaxPrior);

    // NC001参数设置
    SOCurrencyUtil currutil = SOCurrencyUtil.getInstance();
    cond.setGroupLocalCurrencyEnable(currutil.isGroupCurrencyEnable());
    cond.setOrigCurToGroupMoney(currutil.isLocalCurToGroupMoney());
    // NC002参数设置
    cond.setGlobalLocalCurrencyEnable(currutil.isGlobalCurrencyEnable());
    cond.setOrigCurToGlobalMoney(currutil.isLocalCurToGlobalMoney());
    return cond;
  }

  public static boolean isTaxPrior() {
    UFBoolean so23 = null;
    String pk_group = AppContext.getInstance().getPkGroup();
    so23 = SCMParameterUtils.getSCM13(pk_group);
    if (null == so23) {
      return false;
    }
    return so23.booleanValue();
  }

  /**
   * 根据SCM13 基价是否含税优先 返回单价字段
   * 含税优先时返回"含税单价"字段，无税优先时返回"无税单价"字段
   * 
   * @return
   */
  public static String getCalPriceKey() {
    if (SOCalConditionRule.isTaxPrior()) {
      return SOItemKey.NQTORIGTAXPRICE;
    }

    return SOItemKey.NQTORIGPRICE;
  }
}
