package nc.bs.so.m30.state;

import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 销售订单状态判断工具类
 * @author 刘志伟
 *
 * @time 2010-01-28 下午13:49:07
 */
public class BillStateUtil {

  private boolean canBeExecuteState(SaleOrderHVO head) {
    // 只有审批过的，冻结的，关闭的单据才可以处于执行状态
    Integer status = head.getFstatusflag();
    boolean flag =
        BillStatus.AUDIT.equalsValue(status)
            || BillStatus.CLOSED.equalsValue(status)
            || BillStatus.FREEZE.equalsValue(status);
    return flag;
  }

  public boolean canBeExecuteState(SaleOrderViewVO view) {
    boolean flag = this.canBeExecuteState(view.getHead());
    return flag;
  }

  public boolean canBeExecuteState(SaleOrderVO bill) {
    boolean flag = this.canBeExecuteState(bill.getParentVO());
    return flag;
  }
}
