package nc.impl.so.para;

import nc.impl.so.para.rule.so25.CheckSO25ForSORule;
import nc.itf.scmpub.para.ICheckParaForSCM12;
import nc.vo.pub.BusinessException;

public class CheckParaForSCM12Impl implements ICheckParaForSCM12 {

  @Override
  public boolean checkSCM12(String pk_group, String para)
      throws BusinessException {
    boolean isEnableForSO = new CheckSO25ForSORule().isEnable(pk_group, para);
    if (isEnableForSO) {
      return true;
    }

    return false;
  }

}
