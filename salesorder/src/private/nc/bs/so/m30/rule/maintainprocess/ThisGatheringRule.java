package nc.bs.so.m30.rule.maintainprocess;

import nc.bs.pub.pf.PfUtilTools;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m30.ref.arap.mf2.ARmF2ServicesUtil;
import nc.vo.arap.gathering.AggGatheringBillVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.pub.SaleOrderVOCalculator;
import nc.vo.so.m30.sobalance.util.GatherbillUtil;

/**
 * @description
 * 销售订单保存后根据本次收款金额生成收款单并保存
 * @scene
 * 销售订单新增、修改保存后本次收款金额不为空
 * @param 
 * 无
 * 
 * @since 6.0
 * @version 2011-5-11 下午02:53:09
 * @author 刘志伟
 */
public class ThisGatheringRule implements IRule<SaleOrderVO> {

  /**
   * 本次收款金额不为空时，生成一张收款单
   */
  @Override
  public void process(SaleOrderVO[] vos) {
    UFDouble thisGatheringMny =
        (UFDouble) BSContext.getInstance().getSession(
            "cashsale.thisGatheringMny");
    if (thisGatheringMny != null
        && MathTool.absCompareTo(thisGatheringMny, UFDouble.ZERO_DBL) > 0) {
      try {
        vos[0].getParentVO().setNthisreceivemny(thisGatheringMny);
        // 检查能否收款
        GatherbillUtil.getInstance().checkBeforeGathering(vos[0]);

        // 准备待VO交换的订单VO
        SaleOrderVO newOrdvo =
            GatherbillUtil.getInstance().prepareOrderBeforeChangeData(vos[0]);
        // 设置收款单金额
        newOrdvo.getParentVO().setNtotalorigmny(thisGatheringMny);
        newOrdvo.getChildrenVO()[0].setNorigtaxmny(thisGatheringMny);
        new SaleOrderVOCalculator(newOrdvo).calculate(0,
            SaleOrderBVO.NORIGTAXMNY);

        // 转换成收款单VO
        AggregatedValueObject[] destVOs =
            PfUtilTools.runChangeDataAry(SOBillType.Order.getCode(), "D2",
                new AggregatedValueObject[] {
                  newOrdvo
                });

        ARmF2ServicesUtil.insertGatheringBill((AggGatheringBillVO[]) destVOs);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

}
