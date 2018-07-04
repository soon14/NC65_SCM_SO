package nc.bs.so.m30.rule.me;

import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderVO;

import nc.pubitf.initgroup.InitGroupQuery;
import nc.pubitf.me.collectorder.incomerestore.ISaleOrderProceeds;

import nc.bs.framework.common.NCLocator;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * 销售订单取消审批后删除订单收益表
 * @scene 
 * 销售订单取消审批后
 * @param 
 * 无
 * @since 6.31
 * @version 2013-7-6下午12:53:38
 * @author 张云枫
 */
public class SaleOrderVOUnApproveAfterRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    // 删除相应的订单收益表数据
    try {
      if (!InitGroupQuery.isEnabled(AppContext.getInstance().getPkGroup(),
          "4038")) {
        return;
      }
      NCLocator.getInstance().lookup(ISaleOrderProceeds.class).delete(vos);
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

}
