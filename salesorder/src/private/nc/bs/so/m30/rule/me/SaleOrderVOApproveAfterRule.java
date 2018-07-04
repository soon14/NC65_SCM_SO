package nc.bs.so.m30.rule.me;

import java.util.ArrayList;

import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

import nc.pubitf.initgroup.InitGroupQuery;
import nc.pubitf.me.collectorder.incomerestore.ISaleOrderProceeds;

import nc.bs.framework.common.NCLocator;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * 销售订单审批后生成订单收益表
 * @scene 
 * 销售订单审批通过后
 * @param 
 * 无
 * @since 6.31
 * @version 2013-7-6下午12:47:58
 * @author 张云枫
 */
public class SaleOrderVOApproveAfterRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    // 生成订单收益表数据
    try {
      if (!InitGroupQuery.isEnabled(AppContext.getInstance().getPkGroup(),
          "4038")) {
        return;
      }
      // 只有单据状态为"审批通过"才生成订单收益表
      ArrayList<SaleOrderVO> voList = new ArrayList<SaleOrderVO>();
      for (SaleOrderVO vo : vos) {
        if (vo.getParentVO().getFstatusflag()
            .equals(BillStatus.AUDIT.getIntegerValue())) {
          voList.add(vo);
        }
      }
      if (voList.size() > 0) {
        NCLocator.getInstance().lookup(ISaleOrderProceeds.class)
            .add(voList.toArray(new SaleOrderVO[voList.size()]));
      }
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
  }
}
