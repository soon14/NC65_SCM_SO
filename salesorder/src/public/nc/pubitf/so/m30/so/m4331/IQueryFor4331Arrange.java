package nc.pubitf.so.m30.so.m4331;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderViewVO;

public interface IQueryFor4331Arrange {
  SaleOrderViewVO[] queryArrangeSaleOrder(IQueryScheme queryScheme)
      throws BusinessException;
}
