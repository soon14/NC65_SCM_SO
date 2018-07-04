package nc.itf.so.ordersummary;

import nc.vo.pub.BusinessException;

import com.ufida.dataset.IContext;

/**
 * 销售订单执行汇总接口
 * 
 * @since 6.3
 * @version 2012-9-24 下午02:26:51
 * @author 梁吉明
 */
public interface IOrderSummaryMaintain {
  /**
   * 
   * @param context 上下文
   * @return String 查询临时表的SQL语句
   * @throws BusinessException
   */
  public String queryOrderSummary(IContext context) throws BusinessException;
}
