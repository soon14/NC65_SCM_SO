package nc.pubitf.so.m33.so.m32;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

/**
 * <p>
 * <b>销售发票结算接口</b>
 * 
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * 
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author zhangcheng
 * @time 2010-7-30 上午09:37:14
 */
public interface ISquareAcionFor32 {

  /**
   * 自动成本结算动作
   * 
   * @param vos
   */
  void autoSquareCostSrv(AbstractBill[] vos) throws BusinessException;

  /**
   * 自动应收结算动作
   * 
   * @param vos
   */
  void autoSquareIncomeSrv(AbstractBill[] vos) throws BusinessException;

  /**
   * 差额传应收
   * 
   * @param vos
   */
  void squareAdjustIncomeSrv(AbstractBill[] vos)
      throws BusinessException;

}
