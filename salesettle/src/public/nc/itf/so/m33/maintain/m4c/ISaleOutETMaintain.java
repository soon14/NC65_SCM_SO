package nc.itf.so.m33.maintain.m4c;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;

public interface ISaleOutETMaintain {
  /**
   * 手工暂估
   * 
   * @param vos
   * @throws BusinessException
   */
  void manualEstimate(SquareOutViewVO[] vos) throws BusinessException;

  /**
   * 取消手工暂估
   * 
   * @param vos
   * @throws BusinessException
   */
  void manualUnEstimate(SquareOutViewVO[] vos) throws BusinessException;

  /**
   * 查询满足条件的暂估应收数据
   * 
   * @param strWhere
   * @return
   * @throws BusinessException
   */
  SquareOutViewVO[] querySquareOutFor4CManualET(IQueryScheme queryScheme)
      throws BusinessException;
}
