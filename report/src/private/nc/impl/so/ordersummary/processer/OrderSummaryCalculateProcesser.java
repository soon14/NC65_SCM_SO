package nc.impl.so.ordersummary.processer;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.report.ordersummary.OrderSummaryViewVO;

/**
 * 设置出库关闭信息
 * 
 * @since 6.3
 * @version 2012-12-10 14:03:50
 * @author liangjm
 */

public class OrderSummaryCalculateProcesser {

  /**
   * 
   * 
   * @param views
   */
  public void processCal(OrderSummaryViewVO[] views) {
    for (OrderSummaryViewVO view : views) {
      if (view.getBboutendflag().equals(UFBoolean.TRUE)) {
        view.setNwaitoutnum(UFDouble.ZERO_DBL);
      }
      else {
        UFDouble waitoutnum =
            MathTool.add(MathTool.sub(view.getNnum(), view.getNoutnum()),
                view.getNnorwastnum());
        view.setNwaitoutnum(waitoutnum);
      }
    }
  }
}
