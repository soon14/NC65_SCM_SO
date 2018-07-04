package nc.impl.so.tolerance;

import nc.impl.pubapp.env.BSContext;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.so.pub.tolerance.IAbandonToleranceCheck;

public class AbandonToleranceCheckImpl implements IAbandonToleranceCheck {

  @Override
  public void abandonDeliveryToleranceCheck() {
    BSContext.getInstance().setSession(
        BusinessCheck.DeliveryToleranceCheck.getCheckCode(), Boolean.FALSE);
  }

  @Override
  public void abandonInvoinceToleranceCheck() {
    BSContext.getInstance().setSession(
        BusinessCheck.InvoiceToleranceCheck.getCheckCode(), Boolean.FALSE);
  }

  @Override
  public void abandonOrderToleranceCheck() {
    BSContext.getInstance().setSession(
        BusinessCheck.OrderToleranceCheck.getCheckCode(), Boolean.FALSE);
  }

  @Override
  public void abandonOutToleranceCheck() {
    BSContext.getInstance().setSession(
        BusinessCheck.OutToleranceCheck.getCheckCode(), Boolean.FALSE);
  }

  @Override
  public void abandonPreOrderToleranceCheck() {
    BSContext.getInstance().setSession(
        BusinessCheck.PreOrderToleranceCheck.getCheckCode(), Boolean.FALSE);
  }

  @Override
  public void abandonCtToleranceCheck() {
    BSContext.getInstance().setSession(
        BusinessCheck.CtToleranceCheck.getCheckCode(), Boolean.FALSE);
  }

  @Override
  public void abandonCustomerPOToleranceCheck() {
    BSContext.getInstance().setSession(
        BusinessCheck.CustomerPOToleranceCheck.getCheckCode(), Boolean.FALSE);
  }

}
