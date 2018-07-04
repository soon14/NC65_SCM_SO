package nc.bs.so.m30.revise.rule;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pub.power.BillPowerChecker;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;

import nc.pubitf.ic.m4c.I4CQueryPubService;
import nc.pubitf.scmf.dm.m4816.IPrePaidServiceForM30;
import nc.pubitf.so.m32.so.m30.IQuery32For30;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 *              销售订单修订取消审批前检查是否可以弃审
 * @scene
 *        销售订单取消审批前
 * @param 无
 * 
 * @since 6.3
 * @version 2014-12-26 下午3:43:12
 * @author wangshu6
 */
public class CheckUnApprovableRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] bills) {
    for (SaleOrderVO bill : bills) {
      SaleOrderHVO header = bill.getParentVO();
      if (!BillStatus.AUDIT.equalsValue(header.getFstatusflag())
          && !BillStatus.AUDITING.equalsValue(header.getFstatusflag())) {

        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0074")/*@res "单据为非审批状态或审批中状态，不可进行弃审！"*/);
      }
      // 审批者权限
      boolean ishaveper =
          BillPowerChecker.hasApproverPermission(bill,
              SOBillType.Order.getCode());
      if (!ishaveper) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4006011_0", "04006011-0432")/*当前用户对单据不具有审批者权限.*/);
      }
    }
  }

}
