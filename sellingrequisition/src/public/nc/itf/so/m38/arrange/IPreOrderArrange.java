package nc.itf.so.m38.arrange;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m38.entity.PreOrderViewVO;

/**
 * <p>
 * <b>预订单安排服务接口，功能：</b>
 * 
 * <ul>
 * <li>查询预订单viewVO
 * <li>
 * <li>...
 * </ul>
 * 
 * @since 6.0
 * @version 2010-6-29 上午09:36:43
 * @author 刘志伟
 */
public interface IPreOrderArrange {

  /**
   * 查询预订单viewVO
   * 
   * @param queryScheme
   * @return
   * @throws BusinessException
   */
  PreOrderViewVO[] queryPreOrderViewVO(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * 方法功能描述：查询预订单viewVO。
   * 
   * @throws BusinessException
   * @since 6.0
   */
  PreOrderViewVO[] queryPreOrderViewVO(String sql) throws BusinessException;
  
  PreOrderViewVO[] queryPreOrderViewVO(String[] prebids) throws BusinessException;
}
