package nc.itf.so.pub.ref.ic.m4c;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.reserve.ReserveAdjust;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

public class SoReserveAdjust {

  private SoReserveAdjust() {
    super();
  }

  /**
   * 方法功能描述：单据关闭时预留调整 <b>参数说明</b>
   * 
   * @param billtype
   * @param vo
   *          关闭的单据
   * @throws BusinessException
   *           <p>
   * @author 祝会征
   * @time 2010-5-2 下午04:03:44
   */
  public static void adjustReserveWhenClose(String billtype,
      AggregatedValueObject vo) throws BusinessException {
    ReserveAdjust adjust = NCLocator.getInstance().lookup(ReserveAdjust.class);
    adjust.adjustReserveWhenClose(billtype, vo);
  }

  /**
   * 方法功能描述：单据删除时预留调整 <b>参数说明</b>
   * 
   * @param billtype
   * @param vo
   *          删除的单据
   * @throws BusinessException
   *           <p>
   * @author 祝会征
   * @time 2010-5-2 下午04:03:44
   */
  public static void adjustReserveWhenDelete(String billtype,
      AggregatedValueObject vo) throws BusinessException {
    ReserveAdjust adjust = NCLocator.getInstance().lookup(ReserveAdjust.class);
    adjust.adjustReserveWhenDelete(billtype, vo);
  }

  /**
   * 方法功能描述：单据修改时预留调整 <b>参数说明</b>
   * 
   * @param billtype
   *          单据类型
   * @param vo
   *          修改后的单据
   * @param boolean isuserdel 需求单据修改后，如与预留不一致，会抛出异常
   *        询问是否删除预留?，如用户确认删除，则应当将该标准设置为true;
   * @throws BusinessException
   *           <p>
   * @author 祝会征
   * @time 2010-5-2 下午04:03:44
   */
  public static void adjustReserveWhenEdit(String billtype,
      AggregatedValueObject vo, boolean isuserdel) throws BusinessException {

    ReserveAdjust adjust = NCLocator.getInstance().lookup(ReserveAdjust.class);
    adjust.adjustReserveWhenEdit(billtype, vo, isuserdel);
  }

  /**
   * 方法功能描述：单据修订时预留调整 <b>参数说明</b>
   * 
   * @param billtype
   * @param vo
   *          修订后的单据
   * @param boolean isuserdel 需求单据修改后，如与预留不一致，会抛出异常
   *        询问是否删除预留?，如用户确认删除，则应当将该标准设置为true;
   * @throws BusinessException
   *           <p>
   * @author 祝会征
   * @time 2010-5-2 下午04:03:44
   */
  public static void adjustReserveWhenRevise(String billtype,
      AggregatedValueObject vo, boolean isuserdel) throws BusinessException {
    ReserveAdjust adjust = NCLocator.getInstance().lookup(ReserveAdjust.class);
    adjust.adjustReserveWhenRevise(billtype, vo, isuserdel);
  }
}
