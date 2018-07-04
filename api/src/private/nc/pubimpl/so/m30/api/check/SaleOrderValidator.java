package nc.pubimpl.so.m30.api.check;

import nc.pubimpl.so.m30.api.check.SaleOrderVONullValidate;
import nc.vo.scmpub.check.vovalidate.BillVOValidator;
import nc.vo.scmpub.check.vovalidate.IVOValidate;

/**
 * @description
 *
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-10-26 ÏÂÎç2:41:08
 * @author Áõ¾°
 */
public class SaleOrderValidator extends BillVOValidator{

  @Override
  public IVOValidate[] getVOValidators() {
    IVOValidate[] validators = new IVOValidate[] {
      new SaleOrderVONullValidate()
    };
    return validators;
  }

}
