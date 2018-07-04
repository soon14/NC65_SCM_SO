package nc.bs.so.m32.pf.function;

import java.util.List;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.so.m32.entity.SaleInvoiceBVO;

import nc.pubitf.ic.split.ISplitBillByStoreAdmin;

import nc.bs.framework.common.NCLocator;

/**
 * 提供给库存的分单函数（按仓库分单）
 * 
 * @since 6.0
 * @version 2011-7-11 下午01:41:55
 * @author 么贵敬
 */
public class SplitByStoAdminFor4C {

  /**
   * 
   * @param vo
   * @return 分单
   * @throws BusinessException
   */
  public List<String> splitByStoreAdmin(AggregatedValueObject vo)
      throws BusinessException {
    ISplitBillByStoreAdmin service =
        NCLocator.getInstance().lookup(ISplitBillByStoreAdmin.class);
    return service.splitByStoreAdmin(vo, new String[] {
      SaleInvoiceBVO.CSENDSTOCKORGID, SaleInvoiceBVO.CSENDSTORDOCID,
      SaleInvoiceBVO.CMATERIALID
    });
  }

}
