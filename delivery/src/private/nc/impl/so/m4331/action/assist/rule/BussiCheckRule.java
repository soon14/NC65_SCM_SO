package nc.impl.so.m4331.action.assist.rule;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.pub.ref.ic.m4c.SOATPprocess;
import nc.pubitf.credit.creditcheck.IIgnoreCreditCheck;
import nc.pubitf.to.m5x.pub.IM5XAbandonToleranceCheck;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.so.pub.tolerance.IAbandonToleranceCheck;

/**
 * 设置业务检查类时否进行检查
 * 
 * @since 6.0
 * @version 2011-6-22 下午04:19:11
 * @author 祝会征
 */
public class BussiCheckRule {
  public void setBusiCheckFlag(Map<String, Boolean> map) {
    if ((null == map) || (map.size() == 0)) {
      return;
    }
    Set<Entry<String, Boolean>> entrys = map.entrySet();
    for (Entry<String, Boolean> entry : entrys) {
      String key = entry.getKey();
      boolean flag = entry.getValue().booleanValue();
      if (key.equals(BusinessCheck.ATPCheck.getCheckCode())) {
        this.setAtpFlag(flag);
      }
      else if (key.equals(BusinessCheck.CreditCheck.getCheckCode())) {
        this.setCrediteFlag(flag);
      }
      else if (key.equals(BusinessCheck.OrderToleranceCheck.getCheckCode())) {
        this.setSaleorderFlag(flag);
      }
      else if (key.equals(BusinessCheck.TransDeliToleranceCheck.getCheckCode())) {
        this.setTranOrderFlag(flag);
      }
    }
  }

  /*
   * 可用量检查
   */
  private void setAtpFlag(boolean ischeck) {
    if (!ischeck) {
      // 不做ATP检查
      SOATPprocess.abandonATPCheck();
    }
  }

  /*
   * 信用检查
   */
  private void setCrediteFlag(boolean flag) {
    if (!flag) {
      IIgnoreCreditCheck service =
          NCLocator.getInstance().lookup(IIgnoreCreditCheck.class);
      try {
        service.ignoreCreditCheck();
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

  /*
   * 超销售订单发货检查
   */
  private void setSaleorderFlag(boolean flag) {
    if (!flag) {
      // 不做超订单发货检查
      IAbandonToleranceCheck service =
          NCLocator.getInstance().lookup(IAbandonToleranceCheck.class);
      service.abandonOrderToleranceCheck();
    }
  }

  private void setTranOrderFlag(boolean flag) {
    if (!flag) {
      // 不做超调拨订单发货检查
      IM5XAbandonToleranceCheck service =
          NCLocator.getInstance().lookup(IM5XAbandonToleranceCheck.class);
    try {
      service.abandonTransDeliToleranceCheck();
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
      //service.abandonTransDeliToleranceCheck();
    }
  }
}
