package nc.itf.so.m30.arrange;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;

public interface IM30ArrangeMaintain {

  SaleOrderVO[] querySaleOrder(IQueryScheme queryScheme)
      throws BusinessException;

}
