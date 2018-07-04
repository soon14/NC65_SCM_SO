package nc.pubitf.so.m33.ic.m4c;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

/**
 * <p>
 * <b>销售出库单结算接口</b>
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
 * @time 2010-7-30 上午09:36:41
 */
public interface ISquareAcionFor4C {

  /**
   * 自动成本结算动作
   * 
   * @param vos
   */
  void autoSquareCostSrv(AbstractBill[] vos) throws BusinessException;

  /**
   * 自动暂估收入动作
   * 
   * @param vos
   */
  void autoSquareEstimateSrv(AbstractBill[] vos) throws BusinessException;

  /**
   * 自动应收结算动作
   * 
   * @param vos
   */
  void autoSquareIncomeSrv(AbstractBill[] vos) throws BusinessException;

  /**
   * 自动计入发出商品
   * 
   * @param vos
   */
  void autoSquareRegisterSrv(AbstractBill[] vos) throws BusinessException;

  /**
   * 手工成本结算动作
   * 
   * @param vos
   */
  void manualSquareCostSrv(AbstractBill[] vos) throws BusinessException;

  /**
   * 手工应收结算动作
   * 
   * @param vos
   */
  void manualSquareIncomeSrv(AbstractBill[] vos) throws BusinessException;

  /**
   * 出库对冲
   * 
   * @param bluebids -- 蓝字销售出库单bid
   * @param redbids -- 红字销售出库单bid
   */
  UFDouble outRush(String[] bluebids, String[] redbids)
      throws BusinessException;

}
