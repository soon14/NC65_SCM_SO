package nc.bs.so.m30.revise.rule;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.ic.m4c.m30.IRewrite4CPriceFor30;
import nc.pubitf.ic.m4c.m30.ISaleOutPriceParaFor30;
import nc.pubitf.so.m30.ic.m4c.PriceParaFor4C;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @description
 * 销售订单修订保存后回写出库单改价规则
 * @scene
 * 销售订单修订保存后
 * @param
 * map key：销售订单子表id value：销售订单修订时，更改出库单的价格相关参数
 * @since 6.0
 * @version 2011-6-13 上午08:48:42
 * @author 刘志伟
 */
public class Rewrite4CWhenReviseRule implements IRule<SaleOrderVO> {

  private Map<String, ISaleOutPriceParaFor30> map;

  @Override
  public void process(SaleOrderVO[] vos) {
	if(!SysInitGroupQuery.isICEnabled()) {
		return;
	}
    this.map = new HashMap<String, ISaleOutPriceParaFor30>();
    for (SaleOrderVO vo : vos) {
      SaleOrderBVO[] bvos = vo.getChildrenVO();
      for (SaleOrderBVO bvo : bvos) {
        String bid = bvo.getCsaleorderbid();
        ISaleOutPriceParaFor30 para = new PriceParaFor4C(bvo);
        this.map.put(bid, para);
      }
    }

    if (this.map.size() > 0) {
      IRewrite4CPriceFor30 service =
          NCLocator.getInstance().lookup(IRewrite4CPriceFor30.class);
      try {
        service.renovatePrice(this.map);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

}
