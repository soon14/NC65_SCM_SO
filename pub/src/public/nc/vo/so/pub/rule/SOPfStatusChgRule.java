package nc.vo.so.pub.rule;

import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.IPfRetCheckInfo;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;

public class SOPfStatusChgRule {

  public void changePfToBillStatus(AbstractBill billvo) {
    IKeyValue keyValue = new VOKeyValue<AbstractBill>(billvo);
    Integer pfstatus = keyValue.getHeadIntegerValue(SOItemKey.FPFSTATUSFLAG);
    Integer billstatus = this.changeToBillStatus(pfstatus);
    keyValue.setHeadValue(SOItemKey.FSTATUSFLAG, billstatus);
    billvo.getParentVO().setStatus(VOStatus.UPDATED);

    int bodycount = keyValue.getBodyCount();
    CircularlyAccessibleValueObject[] bodyvos = billvo.getChildrenVO();
    for (int i = 0; i < bodycount; i++) {
      keyValue.setBodyValue(i, SOItemKey.FROWSTATUS, billstatus);
      bodyvos[i].setStatus(VOStatus.UPDATED);
    }
  }

  private Integer changeToBillStatus(Integer pfappstatus) {

    if (null == pfappstatus) {
      return null;
    }
    Integer newicheckState = null;
    switch (pfappstatus.intValue()) {
      case IPfRetCheckInfo.NOSTATE:
        newicheckState = BillStatus.I_FREE;
        break;
      case IPfRetCheckInfo.COMMIT:
        newicheckState = BillStatus.I_AUDITING;
        break;
      case IPfRetCheckInfo.PASSING:
        newicheckState = BillStatus.I_AUDIT;
        break;
      case IPfRetCheckInfo.NOPASS:
        newicheckState = BillStatus.I_NOPASS;
        break;
      case IPfRetCheckInfo.GOINGON:
        newicheckState = BillStatus.I_AUDITING;
        break;
      default:
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4006004_0", "04006004-0009")
        /*@res "将审批平台状态转为单据状态时出错。"*/);
    }
    return newicheckState;
  }
}
