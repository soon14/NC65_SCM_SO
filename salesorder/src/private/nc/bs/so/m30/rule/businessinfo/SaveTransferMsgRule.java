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
 * 新增保存或修改保存后调用内部交易信息
 * @scene 
 * 销售订单新增、修改保存后
 * @param 
 * 无
 *
 */
public class SaveTransferMsgRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] bills) {
    // 如果内部交易未启用，不处理
    if (!SysInitGroupQuery.isTOEnabled()) {
      return;
    }
    IBusinessinfoSvcFor30 bo =
        NCLocator.getInstance().lookup(IBusinessinfoSvcFor30.class);
    try {
      bo.afterBillSave(bills);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
