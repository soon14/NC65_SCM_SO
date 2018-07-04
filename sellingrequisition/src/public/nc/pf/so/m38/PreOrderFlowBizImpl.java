package nc.pf.so.m38;

import nc.md.data.access.NCObject;
import nc.uap.pf.metadata.FlowBizImpl;
import nc.vo.pub.pf.IPfRetCheckInfo;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.pub.enumeration.BillStatus;

public class PreOrderFlowBizImpl extends FlowBizImpl {

  public PreOrderFlowBizImpl(
      NCObject ncobject) {
    super(ncobject);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void setApproveStatus(Integer icheckState) {
    Integer newcheckState = icheckState;
    switch (icheckState.intValue()) {
      case IPfRetCheckInfo.NOSTATE:
        newcheckState = (Integer) BillStatus.FREE.value();
        break;
      case IPfRetCheckInfo.COMMIT:
        newcheckState = (Integer) BillStatus.FREE.value();
        break;
      case IPfRetCheckInfo.PASSING:
        newcheckState = (Integer) BillStatus.AUDIT.value();
        break;
      case IPfRetCheckInfo.NOPASS:
        newcheckState = (Integer) BillStatus.NOPASS.value();
        break;
      case IPfRetCheckInfo.GOINGON:
        newcheckState = (Integer) BillStatus.AUDITING.value();
        break;
      default:
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0","04006012-0032")/*@res "将审批平台状态转为单据状态时出错。"*/);
    }

    super.setApproveStatus(newcheckState);
  }
}