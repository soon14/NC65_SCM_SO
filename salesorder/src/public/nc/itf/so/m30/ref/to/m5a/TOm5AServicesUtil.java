package nc.itf.so.m30.ref.to.m5a;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.to.m5a.so.IMaintain5AFor30;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.to.m5a.entity.TransInVO;

public class TOm5AServicesUtil {

  /**
   * 方法功能描述：销售订单弃审删除下游的调入申请
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param m30IDs
   * @throws BusinessException
   *           <p>
   * @author zhangcheng
   * @time 2010-6-8 下午03:02:12
   */
  public static void delete5AByTo(String[] m30IDs) throws BusinessException {
    try {
      IMaintain5AFor30 bo =
          NCLocator.getInstance().lookup(IMaintain5AFor30.class);
      bo.delete5AFor30(m30IDs);
    }
    catch (BusinessException e) {
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * 方法功能描述：销售订单跨组织销售转需求组件推式生成调入申请接口调用
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param bills
   * @throws BusinessException
   *           <p>
   * @author zhangcheng
   * @time 2010-6-8 上午11:39:17
   */
  public static void push5AByTo(TransInVO[] bills) throws BusinessException {
    try {
      IMaintain5AFor30 bo =
          NCLocator.getInstance().lookup(IMaintain5AFor30.class);
      bo.save5AFor30(bills);
    }
    catch (BusinessException e) {
      ExceptionUtils.marsh(e);
    }
  }

}
