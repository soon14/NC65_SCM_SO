package nc.bs.so.m30.rule.sobalance;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.enumeration.SoBalanceType;
import nc.vo.so.m30.sobalance.util.SoBalanceUtil;

/**
 * @description
 * 收款核销关系数据检查规则
 * 1、订单收款核销金额的约束
 * @scene
 * 销售订单保存收款核销关系前
 * @param
 * 无
 */
public class CheckOrderbalmnyRule implements IRule<SoBalanceVO> {
  public CheckOrderbalmnyRule() {
    //
  }

  @Override
  public void process(SoBalanceVO[] vos) {
    for (SoBalanceVO bill : vos) {
      this.checkNorigordbalmny(bill);
    }
  }

  /**
   * 1、订单核销关系金额不能为0
   * 2、订单核销关系金额不能小于已财务核销金额（同方向，绝对值比较）
   * 3、同订单的收款关系金额合计小于订单价税合计（同方向，绝对值比较）
   *
   * @param bill
   */
  private void checkNorigordbalmny(SoBalanceVO bill) {
    SoBalanceHVO headvo = bill.getParentVO();
    SoBalanceBVO[] bodyvos = bill.getChildrenVO();
    UFDouble ntotalorigtaxmny = headvo.getNtotalorigtaxmny();
    for (SoBalanceBVO bodyvo : bodyvos) {
      if (bodyvo.getStatus() == VOStatus.DELETED) {
        continue;
      }
      UFDouble norigordbalmny = bodyvo.getNorigordbalmny();
      UFDouble norigaccbalmny = bodyvo.getNorigaccbalmny();
      int fibaltype = bodyvo.getFibaltype().intValue();
      if (SoBalanceType.SOBALANCE_ORDERBAL.getIntValue() == fibaltype) {
        if ((norigordbalmny == null) || MathTool.isZero(norigordbalmny)) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0122")/*@res "不允许建立订单核销金额为0的订单核销关系。"*/);
        }
        if (!(((MathTool.compareTo(norigordbalmny, UFDouble.ZERO_DBL) <= 0) && (MathTool
            .compareTo(norigaccbalmny, UFDouble.ZERO_DBL) <= 0)) || ((MathTool
            .compareTo(norigordbalmny, UFDouble.ZERO_DBL) >= 0) && (MathTool
            .compareTo(norigaccbalmny, UFDouble.ZERO_DBL) >= 0)))) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0123")/*@res "订单核销金额与已财务核销金额方向必须一致。"*/);
        }
        if (MathTool.absCompareTo(norigordbalmny, norigaccbalmny) < 0) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0124")/*@res "订单核销金额不允许小于已财务核销金额。"*/);
        }
      }
    }
    UFDouble totalbodymny =
        SoBalanceUtil.getInstance().caculateSoBalanceTotalBodymny(bill,
            SoBalanceBVO.NORIGORDBALMNY);
    if (!(((MathTool.compareTo(ntotalorigtaxmny, UFDouble.ZERO_DBL) <= 0) && (MathTool
        .compareTo(totalbodymny, UFDouble.ZERO_DBL) <= 0)) || ((MathTool
        .compareTo(ntotalorigtaxmny, UFDouble.ZERO_DBL) >= 0) && (MathTool
        .compareTo(totalbodymny, UFDouble.ZERO_DBL) >= 0)))) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0109")/*@res "订单收款关系金额合计与订单价税合计方向必须一致。"*/);
    }
    
    Boolean listenerflag = bill.getListenerflag() == null ? false : bill.getListenerflag().booleanValue();
    if (!listenerflag && MathTool.absCompareTo(ntotalorigtaxmny, totalbodymny) < 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0110")/*@res "订单收款关系金额合计不允许大于订单价税合计。"*/);
    }
  }
}