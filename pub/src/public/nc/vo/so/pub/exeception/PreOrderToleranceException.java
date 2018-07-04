package nc.vo.so.pub.exeception;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.res.BusinessCheck;

/**
 * 预订单容差异常
 * 
 * @since 6.0
 * @version 2010-12-1 下午02:25:23
 * @author 刘志伟
 */
public class PreOrderToleranceException extends BusinessException implements
    IResumeException {

  private static final long serialVersionUID = 1L;

  public PreOrderToleranceException(String msg) {
    super(msg);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pubapp.pub.exception.IResumeException#getBusiExceptionType()
   */
  @Override
  public String getBusiExceptionType() {
    return BusinessCheck.PreOrderToleranceCheck.getCheckCode();
  }
}
