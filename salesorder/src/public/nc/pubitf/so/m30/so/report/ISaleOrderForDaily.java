package nc.pubitf.so.m30.so.report;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.paravo.OrderFormReportParaVO;
import nc.vo.so.m30.paravo.OrderReturnToReportVO;

/**
 * 销售订单给综合日报的查询数据接口
 * 
 * @since 6.0
 * @version 2011-1-20 下午02:43:06
 * @author 么贵敬
 */

public interface ISaleOrderForDaily {

  /**
   * 给综合日报的查询数据的接口方法
   * 
   * @param paravo OrderFormReportParaVO参数VO
   * @return OrderReturnToReportVO 返回对象
   * @throws BusinessException
   */
  OrderReturnToReportVO[] getDailyDataFromOrder(OrderFormReportParaVO paravo)
      throws BusinessException;

}
