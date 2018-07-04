package nc.pubitf.so.m30.mm.mmpac;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 销售订单提供给生产任务管理接口服务
 * 
 * @since 6.0
 * @version 2011-5-6 上午09:25:06
 * @author 刘志伟
 */
public interface ISaleOrderForMMPac {
  /**
   * 根据FromWhere语句查询销售订单视图VO
   * 
   * @param fromwheresql
   * @return SaleOrderViewVO[] 销售订单视图VO[]
   * @throws BusinessException
   */
  SaleOrderViewVO[] querySaleOrderViews(String fromwheresql)
      throws BusinessException;

}
