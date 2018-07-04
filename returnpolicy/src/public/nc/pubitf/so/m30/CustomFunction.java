package nc.pubitf.so.m30;

import nc.bs.framework.common.NCLocator;
import nc.vo.pub.BusinessException;

/**
 * 公式平台实例化公式类用的Class.forName方法，所以自定义函数类只能放到public下，
 * 所以采用此类的方式重构结构
 * 
 * @author 蒲强华
 * @since 2009-6-11
 */
public class CustomFunction implements ICustomFunction {
  ICustomFunction impl = NCLocator.getInstance().lookup(ICustomFunction.class);

  @Override
  public double getAppRetBillMny(ReturnAssignMatchVO paravo) {
    return this.impl.getAppRetBillMny(paravo);
  }

  @Override
  public double getAppRetNum(ReturnAssignMatchVO paravo) {
    return this.impl.getAppRetNum(paravo);
  }

  @Override
  public String getInvCode(ReturnAssignMatchVO paravo) {
    return this.impl.getInvCode(paravo);
  }

  @Override
  public int getInvLifePrd(ReturnAssignMatchVO paravo) throws BusinessException {
    return this.impl.getInvLifePrd(paravo);
  }

  @Override
  public double getOrderMny(int days, ReturnAssignMatchVO paravo) {
    return this.impl.getOrderMny(days, paravo);
  }

  @Override
  public double getOutNumber(int days, ReturnAssignMatchVO paravo) {
    return this.impl.getOutNumber(days, paravo);
  }

  @Override
  public String getResBillDate(ReturnAssignMatchVO paravo) {
    return this.impl.getResBillDate(paravo);
  }

  @Override
  public int getRetRsnType(ReturnAssignMatchVO paravo) {
    return this.impl.getRetRsnType(paravo);
  }

  @Override
  public String getSaleInvoiceBillDate(ReturnAssignMatchVO paravo) {
    return this.impl.getSaleInvoiceBillDate(paravo);
  }

  @Override
  public String getSaleOrderBillDate(ReturnAssignMatchVO paravo) {
    return this.impl.getSaleOrderBillDate(paravo);
  }

  @Override
  public String getSaleOutBillDate(ReturnAssignMatchVO paravo) {
    return this.impl.getSaleOutBillDate(paravo);
  }

  @Override
  public int getSourceInvoiceDays(ReturnAssignMatchVO paravo) {
    return this.impl.getSourceInvoiceDays(paravo);
  }

  @Override
  public int getSourceOrderDays(ReturnAssignMatchVO paravo) {
    return this.impl.getSourceOrderDays(paravo);
  }

  @Override
  public int getSourceOutDays(ReturnAssignMatchVO paravo) {
    return this.impl.getSourceOutDays(paravo);
  }

  @Override
  public boolean isLargessFlag(ReturnAssignMatchVO paravo) {
    return this.impl.isLargessFlag(paravo);
  }

  @Override
  public boolean judge(String strConditionCode, ReturnAssignMatchVO paravo) {
    return this.impl.judge(strConditionCode, paravo);
  }
}
