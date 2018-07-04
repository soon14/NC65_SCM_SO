package nc.ui.so.m33.pub.action;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.pubitf.credit.creditcheck.ICreditCheckMessageService;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.vo.credit.exception.CreditCheckException;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.NCModule;

/**
 * 信用检查异常处理公共类
 * 
 * 手工结算、暂估、出库对冲均需要调用，以进行针对信用异常的特殊处理
 * 
 * @since 6.0
 * @version 2011-9-1 上午10:49:47
 * @author zhangcheng
 */
public class SquareCreditExceptionProcess {

  public void processCreditCheckException(CreditCheckException creditex) {
    ICreditCheckMessageService service =
        NCUILocator.getInstance().lookup(ICreditCheckMessageService.class,
            NCModule.CREDIT);
    try {
      service.showMessage(WorkbenchEnvironment.getInstance().getWorkbench()
          .getParent(), creditex);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
