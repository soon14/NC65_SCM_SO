package nc.vo.so.pub.rule;

import nc.itf.uap.pf.metadata.IFlowBizItf;
import nc.md.data.access.NCObject;
import nc.uap.pf.metadata.FlowBizImpl;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.pf.IPfRetCheckInfo;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售管理组平台审批流状态和业务单据状态映射类
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 冯加滨
 * @time 2010-4-30 上午10:29:50
 */
public class SOFlowBizRule extends FlowBizImpl {
  /**
   * SOFlowBizRule 的构造子
   * 
   * @param ncobject
   */
  public SOFlowBizRule(NCObject ncobject) {
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

    Integer pfstatus = super.getApproveStatus();

    if (null == pfstatus) {
      return null;
    }
    Integer newicheckState = null;
    switch (pfstatus.intValue()) {
      case BillStatus.I_FREE:
        newicheckState = (Integer) IPfRetCheckInfo.NOSTATE;
        break;
      case BillStatus.I_AUDIT:
        newicheckState = IPfRetCheckInfo.PASSING;
        break;
      case BillStatus.I_NOPASS:
        newicheckState = IPfRetCheckInfo.NOPASS;
        break;
      case BillStatus.I_AUDITING:
        newicheckState = IPfRetCheckInfo.GOINGON;
        break;
      default:
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4006004_0", "04006004-0009")
        /*@res "将审批平台状态转为单据状态时出错。"*/);

    }
    return newicheckState;
  }

  @Override
  public void setApproveStatus(Integer icheckState) {

    Integer newicheckState = null;

    switch (icheckState.intValue()) {
      case IPfRetCheckInfo.NOSTATE:
        newicheckState = (Integer) BillStatus.FREE.value();
        break;
      case IPfRetCheckInfo.COMMIT:
        newicheckState = (Integer) BillStatus.AUDITING.value();
        break;
      case IPfRetCheckInfo.PASSING:
        newicheckState = (Integer) BillStatus.AUDIT.value();
        break;
      case IPfRetCheckInfo.NOPASS:
        newicheckState = (Integer) BillStatus.NOPASS.value();
        break;
      case IPfRetCheckInfo.GOINGON:
        newicheckState = (Integer) BillStatus.AUDITING.value();
        break;
      default:
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4006004_0", "04006004-0009")
        /*@res "将审批平台状态转为单据状态时出错。"*/);
    }
    super.setApproveStatus(newicheckState);
  }
}
