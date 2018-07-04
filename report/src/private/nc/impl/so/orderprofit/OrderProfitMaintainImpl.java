package nc.impl.so.orderprofit;

import nc.bs.so.profit.util.SaleOrderProfitMetaUtil;
import nc.itf.so.orderprofit.IOrderProfitMaintain;
import nc.pub.smart.context.SmartContext;

public class OrderProfitMaintainImpl implements IOrderProfitMaintain {

  @Override
  public String getTempName(SmartContext content) {
    SaleOrderProfitMetaUtil util = new SaleOrderProfitMetaUtil();
    String retsql = util.getTempTablename(content);
    return retsql;
  }

}
