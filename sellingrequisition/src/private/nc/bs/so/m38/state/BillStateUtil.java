package nc.bs.so.m38.state;

import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.m38.entity.PreOrderViewVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 预订单状态判断工具类
 * @author 刘志伟
 *
 * @time 2010-04-08 上午10:37:16
 */
public class BillStateUtil {

  private boolean canBeExecuteState(PreOrderHVO head) {
    // 只有审批过的，冻结的，关闭的单据才可以处于执行状态
    Integer status = head.getFstatusflag();
    boolean flag =
        BillStatus.AUDIT.equalsValue(status)
            || BillStatus.CLOSED.equalsValue(status)
            || BillStatus.FREEZE.equalsValue(status);
    return flag;
  }

  public boolean canBeExecuteState(PreOrderViewVO view) {
    boolean flag = this.canBeExecuteState(view.getHead());
    return flag;
  }

  public boolean canBeExecuteState(PreOrderVO bill) {
    boolean flag = this.canBeExecuteState(bill.getParentVO());
    return flag;
  }
}
