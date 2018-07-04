package nc.pubimpl.so.m30.so.m38;

import nc.impl.so.m30.action.main.PushSave30For38ArrangeAction;
import nc.pubitf.so.m30.so.m38.IPushSave30For38Arrange;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.SOParameterVO;

public class PushSave30For38ArrangeImpl implements IPushSave30For38Arrange {

  @Override
  public SaleOrderVO[] pushSave30For38Arrange(SOParameterVO paravo)
      throws BusinessException {
    // ±£´æµ¥¾Ý
    SaleOrderVO[] retvos = null;
    try {
      PushSave30For38ArrangeAction action = new PushSave30For38ArrangeAction();
      retvos = action.pushSave(paravo);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return retvos;
  }

}
