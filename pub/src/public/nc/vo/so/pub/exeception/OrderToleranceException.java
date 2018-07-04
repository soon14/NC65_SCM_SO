package nc.vo.so.pub.exeception;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.res.BusinessCheck;

/**
 * 销售订单容差异常
 * 
 * @author 祝会征
 * @time 2010-8-13 下午01:47:06
 */
public class OrderToleranceException extends BusinessException implements
    IResumeException {

  private static final long serialVersionUID = 1L;

  public OrderToleranceException(String msg) {
    super(msg);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pubapp.pub.exception.IResumeException#getBusiExceptionType()
   */
  @Override
  public String getBusiExceptionType() {
    return BusinessCheck.OrderToleranceCheck.getCheckCode();
  }
}
