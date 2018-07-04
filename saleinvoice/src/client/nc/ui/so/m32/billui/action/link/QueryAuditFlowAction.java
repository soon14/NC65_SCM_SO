package nc.ui.so.m32.billui.action.link;

import nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * 审批流情况查询
 * 
 * @since 6.0
 * @version 2011-2-28 上午10:33:28
 * @author 么贵敬
 */
public class QueryAuditFlowAction extends PFApproveStatusInfoAction {

  /** Version */
  private static final long serialVersionUID = -4490586267001887381L;

  // private AbstractAppModel model;

  /**
   * 构造方法
   */
  public QueryAuditFlowAction() {
    super();
    this.initializeAction();
  }

  private void initializeAction() {
    SCMActionInitializer.initializeAction(this,
        SCMActionCode.SCM_VIEWAPPROVEFLOW);
  }
}
