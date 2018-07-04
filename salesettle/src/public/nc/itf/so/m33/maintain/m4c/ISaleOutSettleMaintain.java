package nc.itf.so.m33.maintain.m4c;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;

public interface ISaleOutSettleMaintain {

  /**
   * 出库对冲
   * 
   * @param vos
   * @throws BusinessException
   */
  void manualOutRush(SquareOutViewVO[] bluevos, SquareOutViewVO[] redvos)
      throws BusinessException;

  /**
   * 方法功能描述：销售出库单手工结算
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return 返回结算后仍然可以再次结算的记录
   * @throws BusinessException
   *           <p>
   * @author zhangcheng
   * @time 2010-7-1 下午01:53:10
   */
  SquareOutViewVO[] manualSquare(SquareOutViewVO[] vos)
      throws BusinessException;

  /**
   * 取消出库对冲
   * 
   * @param vos
   * @throws BusinessException
   */
  void manualUnOutRush(SquareOutViewVO[] vos) throws BusinessException;

  /**
   * 取消手工结算
   * 
   * @param vos
   * @throws BusinessException
   */
  SquareOutViewVO[] manualUnSquare(SquareOutViewVO[] vos)
      throws BusinessException;

  /**
   * 查询满足条件的结算数据
   * 
   * @param strWhere
   * @return
   * @throws BusinessException
   */
  SquareOutViewVO[] querySquareOutFor4CManualSquare(IQueryScheme queryScheme)
      throws BusinessException;
}
