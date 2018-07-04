package nc.impl.so.m4331;

import nc.impl.so.m4331.action.assist.DeliveryBillCloseAction;
import nc.impl.so.m4331.action.assist.DeliveryBillOpenAction;
import nc.impl.so.m4331.action.assist.DeliveryRowCloseAction;
import nc.impl.so.m4331.action.assist.DeliveryRowOpenAction;
import nc.itf.so.m4331.IDeliveryAssitFunc;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.SOParameterVO;

/**
 * 发货单辅助功能
 * 
 * @since 6.0
 * @version 2011-3-1 下午02:38:36
 * @author 祝会征
 */
public class DeliveryAssitFuncImpl implements IDeliveryAssitFunc {

  @Override
  public DeliveryVO[] closeDelivery(DeliveryVO[] bills)
      throws BusinessException {
    try {
      DeliveryBillCloseAction action = new DeliveryBillCloseAction();
      DeliveryVO[] vos = action.closeBill(bills);
      return vos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public DeliveryVO[] closeDelivery(SOParameterVO paravo)
      throws BusinessException {
    try {
      DeliveryBillCloseAction action = new DeliveryBillCloseAction();
      DeliveryVO[] vos = action.closeBill(paravo);
      return vos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 发货单整单关闭
   * 
   */
  @Override
  public DeliveryVO[] closeDeliveryRows(DeliveryVO originBill, int[] rows)
      throws BusinessException {
    DeliveryVO bill = this.getRowSelectedBill(originBill, rows);
    DeliveryRowCloseAction close = new DeliveryRowCloseAction();
    DeliveryVO[] newbills = close.closeRow(new DeliveryVO[] {
      bill
    });
    return newbills;
  }

  @Override
  public DeliveryVO[] openDeiveryRows(SOParameterVO paravo, int[] rows)
      throws BusinessException {
    try {
      DeliveryVO bill =
          this.getRowSelectedBill((DeliveryVO) paravo.getVo(), rows);
      DeliveryRowOpenAction open = new DeliveryRowOpenAction();
      DeliveryVO[] newbills = open.openRow(new DeliveryVO[] {
        bill
      }, paravo.getBusinessCheckMap());
      return newbills;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public DeliveryVO[] openDelivery(SOParameterVO paravo)
      throws BusinessException {
    try {
      DeliveryBillOpenAction open = new DeliveryBillOpenAction();
      return open.openBill(paravo);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public DeliveryVO[] preKeepDelivery(DeliveryVO[] bills)
      throws BusinessException {
    return null;
  }

  private DeliveryVO getRowSelectedBill(DeliveryVO originBill, int[] rows) {
    int length = rows.length;
    DeliveryBVO[] originItems = originBill.getChildrenVO();
    DeliveryVO bill = new DeliveryVO();
    DeliveryBVO[] items = new DeliveryBVO[length];
    for (int i = 0; i < length; i++) {
      int row = rows[i];
      items[i] = originItems[row];
    }
    bill.setParentVO(originBill.getParentVO());
    bill.setChildrenVO(items);
    return bill;
  }
}
