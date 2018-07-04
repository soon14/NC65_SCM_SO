package nc.impl.so.m38.action.rule.unapprove;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pub.power.BillPowerChecker;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 * 检查预订单当前状态是否可弃核
 * @scene
 * 销售预订单弃批前
 * @param
 * 无
 */
public class UnApproveBillBeforeRule implements IRule<PreOrderVO> {
  @Override
  public void process(PreOrderVO[] vos) {
    for (PreOrderVO vo : vos) {

      this.checkStatus(vo);
      this.checkArrnum(vo);

      boolean hasappper =
          BillPowerChecker.hasApproverPermission(vo,
              SOBillType.PreOrder.getCode());
      if (!hasappper) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006002_0", "04006002-0176")/*@res "不满足审核者权限。"*/);
      }
    }
  }

  private void checkArrnum(PreOrderVO vo) {
    PreOrderBVO[] bvos = vo.getChildrenVO();
    for (PreOrderBVO bvo : bvos) {
      if (MathTool.compareTo(bvo.getNarrnum(), UFDouble.ZERO_DBL) > 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006012_0", "04006012-0025")/*@res "当前预订单已经销售安排，不可取消审批"*/);
      }
    }

  }

  private void checkStatus(PreOrderVO vo) {
    if (!BillStatus.AUDIT.equalsValue(vo.getParentVO().getFstatusflag())) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006012_0", "04006012-0024")/*@res "当前预订单单据状态，不可进行审批。"*/);
    }
  }
}
