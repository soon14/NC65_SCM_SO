package nc.bs.so.m4331.assist.state;

import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 发货单状态判断工具类
 * 
 * @author 祝会征
 * @time 2010-04-08 上午10:37:16
 */
public class BillStateUtil {
  public boolean canBeExecuteState(DeliveryViewVO view) {
    boolean flag = this.canBeExecuteState(view.getHead());
    return flag;
  }

  public boolean canBeExecuteState(DeliveryVO bill) {
    boolean flag = this.canBeExecuteState(bill.getParentVO());
    return flag;
  }

  private boolean canBeExecuteState(DeliveryHVO head) {
    // 只有审批过的，冻结的，关闭的单据才可以处于执行状态
    Integer status = head.getFstatusflag();
    boolean flag =
        BillStatus.AUDIT.equalsValue(status)
            || BillStatus.CLOSED.equalsValue(status)
            || BillStatus.FREEZE.equalsValue(status);
    return flag;
  }
}
