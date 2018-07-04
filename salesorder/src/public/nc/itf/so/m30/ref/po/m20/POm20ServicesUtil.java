package nc.itf.so.m30.ref.po.m20;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m20.so.m30.IDelete20For30;
import nc.pubitf.pu.m20.so.m30.IPushSave20For30;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>采购——请购单提供的服务接口：</b>
 * 
 * <ul>
 * <li>销售订单推式生成请购单
 * <li>销售订单弃审删除自由态请购单
 * <li>...
 * </ul>
 * 
 * @version 6.0
 * @author 刘志伟
 * @time 2010-9-1 下午03:30:49
 */
public class POm20ServicesUtil {

  /**
   * 订单交易类型为"直运采购"&&消息驱动PUSH5AOR20生成的销售订单弃审删除下游自由态请购单
   * 
   * @version 6.0
   * @author 刘志伟
   * @time 2010-9-1 下午03:30:49
   */
  public static void delete20ByPo(String[] orderIDs) throws BusinessException {
    try {
      IDelete20For30 service =
          NCLocator.getInstance().lookup(IDelete20For30.class);
      service.deleteBills(orderIDs);
    }
    catch (BusinessException e) {
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * 销售订单推式生成请购单
   * 
   * @version 6.0
   * @author 刘志伟
   * @time 2010-9-1 下午03:30:49
   */
  public static void push20ByPo(PraybillVO[] bills) throws BusinessException {
    try {
      IPushSave20For30 service =
          NCLocator.getInstance().lookup(IPushSave20For30.class);
      service.pushSaveBills(bills);
    }
    catch (BusinessException e) {
      ExceptionUtils.marsh(e);
    }
  }
}
