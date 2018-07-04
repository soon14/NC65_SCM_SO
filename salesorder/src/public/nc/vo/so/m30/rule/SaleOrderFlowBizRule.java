package nc.vo.so.m30.rule;

import nc.itf.uap.pf.metadata.IFlowBizItf;
import nc.md.data.access.NCObject;
import nc.uap.pf.metadata.FlowBizImpl;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;

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
public class SaleOrderFlowBizRule extends FlowBizImpl {
  /**
   * SOFlowBizRule 的构造子
   * 
   * @param ncobject
   */
  public SaleOrderFlowBizRule(NCObject ncobject) {
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
}
