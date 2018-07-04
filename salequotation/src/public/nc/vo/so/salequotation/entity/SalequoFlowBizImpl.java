package nc.vo.so.salequotation.entity;

import nc.itf.uap.pf.metadata.IFlowBizItf;
import nc.md.data.access.NCObject;
import nc.uap.pf.metadata.FlowBizImpl;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.pf.IPfRetCheckInfo;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class SalequoFlowBizImpl extends FlowBizImpl {

  public SalequoFlowBizImpl(NCObject ncobject) {
    super(ncobject);
  }

  @Override
  public void setApproveDate(UFDateTime approveDate) {
    UFDate date = null;
    if (null != approveDate) {
      date = approveDate.getDate();
    }
    this.setAttributeValue(IFlowBizItf.ATTRIBUTE_APPROVEDATE, date);
  }

  @Override
  public Integer getApproveStatus() {

    Integer icheckState = super.getApproveStatus();
    if (null == icheckState) {
      return null;
    }
    Integer newicheckState = null;

    switch (icheckState.intValue()) {
      case BillStatusEnum.C_FREE:
        newicheckState = (Integer) IPfRetCheckInfo.NOSTATE;
        break;
      case BillStatusEnum.C_AUDIT:
        newicheckState = IPfRetCheckInfo.PASSING;
        break;
      case BillStatusEnum.C_NOPASS:
        newicheckState = IPfRetCheckInfo.NOPASS;
        break;
      case BillStatusEnum.C_AUDITING:
        newicheckState = IPfRetCheckInfo.GOINGON;
        break;
      default:
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006004_0", "04006004-0009")/*@res "将审批平台状态转为单据状态时出错。"*/);

    }
    return newicheckState;
  }

  @Override
  public void setApproveStatus(Integer icheckState) {
    Integer newState = null;
    switch (icheckState.intValue()) {
      case IPfRetCheckInfo.NOSTATE:
        newState = Integer.valueOf(BillStatusEnum.C_FREE);
        break;
      case IPfRetCheckInfo.COMMIT:
        newState = Integer.valueOf(BillStatusEnum.C_AUDITING);
        break;
      case IPfRetCheckInfo.PASSING:
        newState = Integer.valueOf(BillStatusEnum.C_AUDIT);
        break;
      case IPfRetCheckInfo.NOPASS:
        newState = Integer.valueOf(BillStatusEnum.C_NOPASS);
        break;
      case IPfRetCheckInfo.GOINGON:
        newState = Integer.valueOf(BillStatusEnum.C_AUDITING);
        break;
      default:
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006009_0", "04006009-0044")/*@res "将审批平台状态转为单据状态时出错。"*/);
    }

    super.setApproveStatus(newState);
  }
}
