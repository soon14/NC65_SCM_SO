package nc.bs.so.m30.rule.sobalance;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * @description
 * 收款核销关系数据检查规则
 * 1、不允许删除已进行财务核销的记录
 * 2、一张订单与一条收款单记录只能建立一条关系
 * @scene
 * 销售订单保存收款核销关系前
 * @param
 * 无
 */
public class CheckSobalanceRule implements IRule<SoBalanceVO> {
  public CheckSobalanceRule() {
    //
  }

  @Override
  public void process(SoBalanceVO[] vos) {
    for (SoBalanceVO bill : vos) {
      this.checkNorigaccbalmny(bill);
      this.checkPaybillrowidUnique(bill);
    }
  }

  private void checkNorigaccbalmny(SoBalanceVO bill) {
    // SoBalanceHVO headvo = bill.getParentVO();
    SoBalanceBVO[] bodyvos = bill.getChildrenVO();
    for (SoBalanceBVO bodyvo : bodyvos) {
      int vostatus = bodyvo.getStatus();
      if (vostatus == VOStatus.DELETED) {
        UFDouble norigaccbalmny = bodyvo.getNorigaccbalmny();
        if (!((norigaccbalmny == null)
            || MathTool.isZero(norigaccbalmny))) {
          ExceptionUtils.wrappBusinessException(
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0125")/*@res "不允许删除已财务核销的订单收款关系。"*/);
        }
      }
    }
  }

  private void checkPaybillrowidUnique(SoBalanceVO bill) {
    // SoBalanceHVO headvo = bill.getParentVO();
    SoBalanceBVO[] bodyvos = bill.getChildrenVO();
    String paybillrowid = null;
    Map<String, String> paybillrowHM = new HashMap<String, String>();
    for (SoBalanceBVO bodyvo : bodyvos) {
      int vostatus = bodyvo.getStatus();
      if (vostatus != VOStatus.DELETED) {
        paybillrowid = bodyvo.getCpaybillrowid();
        if (paybillrowHM.containsKey(paybillrowid)) {
          ExceptionUtils.wrappBusinessException(
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0126")/*@res "不允许重复建立一张订单与一条收款单记录的订单收款关系。"*/);
        }
        paybillrowHM.put(paybillrowid, paybillrowid);
      }
    }
  }

}