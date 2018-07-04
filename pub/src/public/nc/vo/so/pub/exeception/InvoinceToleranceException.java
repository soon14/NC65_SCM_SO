package nc.vo.so.pub.exeception;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.res.BusinessCheck;

/**
 * 发票容差异常检验
 * 
 * @author 祝会征
 * @time 2010-8-13 下午01:47:06
 */
public class InvoinceToleranceException extends BusinessException implements
    IResumeException {

  private static final long serialVersionUID = 1L;

  public InvoinceToleranceException(String msg) {
    super(msg);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pubapp.pub.exception.IResumeException#getBusiExceptionType()
   */
  @Override
  public String getBusiExceptionType() {
    return BusinessCheck.InvoiceToleranceCheck.getCheckCode();
  }
}
