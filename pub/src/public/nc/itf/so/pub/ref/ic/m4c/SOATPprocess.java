package nc.itf.so.pub.ref.ic.m4c;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.ic.atp.ATPUpdate;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

/**
 * 
 * <p>
 * 
 * <b>可用量更新接口</b>
 * 
 * <p>
 * 
 * <p>
 * 使用说明：
 * 
 * <p>
 * 需要成对调用，即在更新本单据前调用
 * 
 * <p>
 * modifyATPBefore(String cbilltype,AggregatedValueObject[] bills)
 * 
 * <p>
 * ....单据更新及回写.......
 * 
 * <p>
 * modifyATPAfter(String cbilltype,AggregatedValueObject[] bills)
 * 
 * @version v60
 * 
 * @since v60
 * 
 * @author yangb
 * 
 * @time 2010-6-9 下午09:43:41
 */

public class SOATPprocess {

  private SOATPprocess() {
    super();
  }

  /**
   * 
   * 方法功能描述：设置检查ATP标志，如果用户选择不检查，则设置标志位
   * 
   * @author yangb
   * 
   * @time 2010-6-9 下午09:51:27
   */
  public static void abandonATPCheck() {
    if (SysInitGroupQuery.isICEnabled()) {
      ATPUpdate bo = NCLocator.getInstance().lookup(ATPUpdate.class);
      bo.abandonATPCheck();
    }
  }

  /**
   * 
   * 方法功能描述：动作后置调用(后置调用必须在所有回写完成后，
   * 
   * 尽量后置到动作的末尾，好处在于锁的时间短)，
   * 
   * 该方法会在更新后进行可用量检查，会有ATP缺口异常抛出。
   * 
   * <b>参数说明</b>
   * 
   * @param cbilltype ---单据类型
   * 
   * @param bills --- 单据数组
   * 
   * @return
   * 
   * @throws BusinessException
   * 
   *           <p>
   * 
   * @author yangb
   * 
   * @time 2010-6-9 下午09:51:27
   */

  public static void modifyATPAfter(String cbilltype,
      AggregatedValueObject[] bills) throws BusinessException {
    if (SysInitGroupQuery.isICEnabled()) {
      ATPUpdate bo = NCLocator.getInstance().lookup(ATPUpdate.class);
      bo.modifyATPAfter(cbilltype, bills);
    }
  }

  /**
   * 
   * 方法功能描述：动作前置调用。
   * 
   * <b>参数说明</b>
   * 
   * @param cbilltype ---单据类型
   * 
   * @param bills --- 单据数组
   * 
   * @return
   * 
   * @throws BusinessException
   * 
   *           <p>
   * 
   * @author yangb
   * @throws BusinessException
   * 
   * @time 2010-6-9 下午09:51:27
   */
  public static void modifyATPBefore(String cbilltype,
      AggregatedValueObject[] bills) throws BusinessException {
    if (SysInitGroupQuery.isICEnabled()) {
      ATPUpdate bo = NCLocator.getInstance().lookup(ATPUpdate.class);
      bo.modifyATPBefore(cbilltype, bills);
    }
  }

}
