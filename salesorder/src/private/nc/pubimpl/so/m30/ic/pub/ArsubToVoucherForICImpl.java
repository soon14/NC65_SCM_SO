package nc.pubimpl.so.m30.ic.pub;

import nc.bs.so.pub.isolation.me.MEForSOInterfaceIsolate;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.so.m30.ic.pub.ArsubToVoucherData;
import nc.pubitf.so.m30.ic.pub.IArsubToVoucherForIC;
import nc.vo.pub.BusinessException;

/**
 * 销售订单提供给库存管理的销售出库单，签收途损单审批、弃审时影响客户费用单传凭证实现
 * 
 * @since 6.3
 * @version 2014-06-30 19:14:52
 * @author 刘景
 */
public class ArsubToVoucherForICImpl implements IArsubToVoucherForIC {

  @Override
  public void onSaleOutSign(ArsubToVoucherData[] data) throws BusinessException {
    if (!SysInitGroupQuery.isMeEnabled()) {
      return;
    }
    MEForSOInterfaceIsolate.onSaleOutSign(data);
  }

  @Override
  public void onSaleOutUnSign(ArsubToVoucherData[] data)
      throws BusinessException {
    if (!SysInitGroupQuery.isMeEnabled()) {
      return;
    }
    MEForSOInterfaceIsolate.onSaleOutUnSign(data);
  }

  @Override
  public void onWastageSign(ArsubToVoucherData[] data) throws BusinessException {
    if (!SysInitGroupQuery.isMeEnabled()) {
      return;
    }
    MEForSOInterfaceIsolate.onWastageSign(data);
  }

  @Override
  public void onWastageUnSign(ArsubToVoucherData[] data)
      throws BusinessException {
    if (!SysInitGroupQuery.isMeEnabled()) {
      return;
    }
    MEForSOInterfaceIsolate.onWastageUnSign(data);
  }

}
