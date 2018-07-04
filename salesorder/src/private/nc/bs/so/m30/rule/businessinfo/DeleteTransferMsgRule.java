package nc.bs.so.m30.rule.businessinfo;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.to.businessinfo.to.m30.IBusinessinfoSvcFor30;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @description
 * 销售订单删除内部交易信息
 * @scene
 * 销售订单删除后
 * @param 
 * 无
 */
public class DeleteTransferMsgRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] bills) {
    if (!SysInitGroupQuery.isTOEnabled()) {
      return;
    }
    IBusinessinfoSvcFor30 service =
        NCLocator.getInstance().lookup(IBusinessinfoSvcFor30.class);
    try {
      String[] ids = new String[bills.length];
      for (int i = 0; i < bills.length; i++) {
        ids[i] = bills[i].getPrimaryKey();
      }
      service.afterBillDelete(ids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
