package nc.impl.so.mreturnassign.rule;

import nc.bs.uif2.validation.ValidationFailure;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.util.BDUniqueRuleValidate;
import nc.itf.scmpub.reference.uap.util.BizlockDataUtil;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.trade.checkrule.VOChecker;

public class UniqueValidate implements IRule<BatchOperateVO> {

  @Override
  public void process(BatchOperateVO[] vos) {
    for (BatchOperateVO bill : vos) {
      this.process(bill);
    }
  }

  private void process(BatchOperateVO vos) {

    Object[] addVOs = vos.getAddObjs();
    Object[] updateVOs = vos.getUpdObjs();
    if ((VOChecker.isEmpty(updateVOs) || updateVOs.length == 0)
        && (addVOs == null || addVOs.length == 0)) {
      return;
    }
    Object[] newVOs = new Object[updateVOs.length + addVOs.length];
    System.arraycopy(addVOs, 0, newVOs, 0, addVOs.length);
    System.arraycopy(updateVOs, 0, newVOs, addVOs.length, updateVOs.length);
    BizlockDataUtil.lockDataByBizlock(newVOs);
    ValidationFailure failure = BDUniqueRuleValidate.validate(newVOs);
    if (failure != null) {
      ExceptionUtils.wrappBusinessException(failure.getMessage());
    }

  }
}
