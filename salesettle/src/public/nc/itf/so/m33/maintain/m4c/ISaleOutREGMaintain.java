package nc.itf.so.m33.maintain.m4c;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;

public interface ISaleOutREGMaintain {
  /**
   * 手工计入发出商品
   * 
   * @param vos
   * @throws BusinessException
   */
  void manualRegister(SquareOutViewVO[] vos) throws BusinessException;

  /**
   * 取消手工计入发出商品
   * 
   * @param vos
   * @throws BusinessException
   */
  void manualUnRegister(SquareOutViewVO[] vos) throws BusinessException;

  /**
   * 查询满足条件的发出商品数据
   * 
   * @param strWhere
   * @return
   * @throws BusinessException
   */
  SquareOutViewVO[] querySquareOutFor4CManualREG(IQueryScheme queryScheme)
      throws BusinessException;
}
