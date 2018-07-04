package nc.vo.so.m4331.pub;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.pub.util.SOPubParaUtil;
import nc.vo.so.pub.util.SOSysParaInitUtil;

/**
 * 获得发货单单价金额计算的参数
 * 
 * @since 6.0
 * @version 2010-12-30 下午02:47:08
 * @author 祝会征
 */
public class CalculatorCondtionUtil {

  /**
   * 返回计算时用到的参数
   * 
   * @param currvo
   * @param rows
   * 
   * @return
   */
  public Condition getCalcCondition() {
    // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
    Condition cond = new Condition();
    // 设置是否进行本币换算
    cond.setIsCalLocalCurr(true);
    // 设置含税优先还是无税优先
    String pk_group = InvocationInfoProxy.getInstance().getGroupId();
    UFBoolean so23 = SOSysParaInitUtil.getSO23(pk_group);
    cond.setIsTaxOrNet(so23.booleanValue());
    // 设置调单价方式调折扣：发货单默认调整折扣
    cond.setIsChgPriceOrDiscount(false);
    // NC001参数设置
    cond.setGroupLocalCurrencyEnable(SOPubParaUtil.getInstance()
        .isGroupLocalCurrencyEnable(pk_group));
    cond.setOrigCurToGroupMoney(this.isOrigCurToGroupMoney());
    // NC002参数设置
    cond.setGlobalLocalCurrencyEnable(SOPubParaUtil.getInstance()
        .isGlobalLocalCurrencyEnable());
    cond.setOrigCurToGlobalMoney(this.isOrigCurToGlobalMoney());
    return cond;
  }

  /**
   * 方法功能描述：业务单位和主单位是否固定换算率。
   * 
   * @author 祝会征
   * @time 2010-6-7 下午03:27:07
   */
  public boolean isAstFixedRate(CircularlyAccessibleValueObject vo) {
    String material = (String) vo.getAttributeValue(DeliveryBVO.CMATERIALVID);
    String unit = (String) vo.getAttributeValue(DeliveryBVO.CUNITID);
    String castunit = (String) vo.getAttributeValue(DeliveryBVO.CASTUNITID);
    boolean isfixed = false;
    isfixed =
        MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(material, unit,
            castunit);
    return isfixed;
  }

  /**
   * 主单位和报价单位之间是否固定报价换算率
   * 
   * @param index
   * @param unitkey
   * @return
   */
  public boolean isQtFixedRate(CircularlyAccessibleValueObject vo) {
    String material = (String) vo.getAttributeValue(DeliveryBVO.CMATERIALVID);
    String unit = (String) vo.getAttributeValue(DeliveryBVO.CUNITID);
    String castunit = (String) vo.getAttributeValue(DeliveryBVO.CQTUNITID);
    boolean isfixed = false;
    isfixed =
        MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(material, unit,
            castunit);
    return isfixed;

  }

  private boolean isOrigCurToGlobalMoney() {
    return true;
  }

  private boolean isOrigCurToGroupMoney() {
    return true;
  }

  public boolean isBuysellFlagOut(CircularlyAccessibleValueObject vo) {
    Integer buysellflag =
        (Integer) vo.getAttributeValue(DeliveryBVO.FBUYSELLFLAG);
    if (BuySellFlagEnum.OUTPUT.equalsValue(buysellflag)) {
      return true;
    }
    return false;
  }
}
