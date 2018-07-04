package nc.pf.so.function.split;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m21.pub.ISplitForOrder;
import nc.pubitf.scmf.pu.ordertranstype.pu.IOrderTranstypeSplit;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;

/**
 * 销售订单为采购订单提供的分单函数
 * 
 * @since 6.0
 * @version 2011-7-15 上午10:25:25
 * @author fengjb
 */
public class M30For21SplitFunc {

  public List<String> splitBCcurrencyid(AggregatedValueObject vo)
      throws BusinessException {
    ISplitForOrder service =
        NCLocator.getInstance().lookup(ISplitForOrder.class);
    String[] keys = new String[] {
      SaleOrderBVO.CMATERIALVID, SaleOrderBVO.CSENDSTOCKORGID
    };
    try {
      return service.splitBCcurrencyid(vo, keys);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  public List<String> splitBSupplier(AggregatedValueObject vo)
      throws BusinessException {

    ISplitForOrder service =
        NCLocator.getInstance().lookup(ISplitForOrder.class);
    String[] keys = new String[] {
      SaleOrderBVO.CMATERIALVID, SaleOrderBVO.CSENDSTOCKORGID
    };
    try {
      return service.splitBSupplier(vo, keys);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  public List<String> splitHorgByOrderTrans(AggregatedValueObject vo)
      throws BusinessException {
    IOrderTranstypeSplit service =
        NCLocator.getInstance().lookup(IOrderTranstypeSplit.class);
    String[] keys = new String[] {
      SaleOrderHVO.PK_GROUP, SaleOrderHVO.PK_ORG, SaleOrderBVO.CMATERIALVID
    };
    return service.splitHorgByOrderTrans(vo, keys);
  }

}
