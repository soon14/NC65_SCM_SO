package nc.pf.so.function.split;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.split.ISplitBillByStoreAdmin;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderBVO;

/**
 * 销售订单给出库单 提供的分单函数
 * 
 * @since 6.0
 * @version 2011-7-12 下午04:04:01
 * @author fengjb
 */
public class M30For4CSplitFunc {

  /**
   * 按照库管员分单
   * 
   * @param vo
   * @return
   * @throws BusinessException
   */
  public List<String> splitByStoreAdmin(AggregatedValueObject vo)
      throws BusinessException {

    ISplitBillByStoreAdmin service =
        NCLocator.getInstance().lookup(ISplitBillByStoreAdmin.class);
    return service.splitByStoreAdmin(vo, new String[] {
      SaleOrderBVO.CSENDSTOCKORGID, SaleOrderBVO.CSENDSTORDOCID,
      SaleOrderBVO.CMATERIALID
    });
  }
}
