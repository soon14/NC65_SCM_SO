package nc.impl.so.m30.revise.action.rule;

import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderVO;

import nc.pubitf.initgroup.InitGroupQuery;
import nc.pubitf.me.collectorder.incomerestore.ISaleOrderProceeds;

import nc.bs.framework.common.NCLocator;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * 销售订单修订保存后更新订单收益表数据
 * @scene
 * 销售订单修订保存后
 * @param
 * 无
 * @since 6.31
 * @version 2013-10-25 13:33:29
 * @author zhangyfr
 */
public class UpdateSaleorderProRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    try {
      // 更新相应的订单收益表数据
      if (!InitGroupQuery.isEnabled(AppContext.getInstance().getPkGroup(),
          "4038")) {
        return;
      }
      NCLocator.getInstance().lookup(ISaleOrderProceeds.class).update(vos);
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

}
