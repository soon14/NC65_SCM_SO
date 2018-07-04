package nc.impl.so.para;

import nc.impl.so.para.rule.so24.CheckSO24ForSORule;
import nc.itf.scmpub.para.ICheckParaForSCM11;
import nc.vo.pub.BusinessException;

public class CheckParaForSCM11Impl implements ICheckParaForSCM11 {

  @Override
  public boolean checkSCM11(String pk_group, String para)
      throws BusinessException {
    boolean isEnableForSO = new CheckSO24ForSORule().isEnable(pk_group, para);
    if (isEnableForSO) {
      return true;
    }
    return false;
  }
}
