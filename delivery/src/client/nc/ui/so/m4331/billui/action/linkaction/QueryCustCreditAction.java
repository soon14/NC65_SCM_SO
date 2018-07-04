package nc.ui.so.m4331.billui.action.linkaction;

import java.awt.event.ActionEvent;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.credit.billcreditquery.IBillCreditQueryMessage;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m4331.billui.model.DeliveryManageModel;
import nc.ui.uif2.NCAction;
import nc.vo.credit.billcreditquery.para.BillQueryPara;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;

public class QueryCustCreditAction extends NCAction {

  private static final long serialVersionUID = -7841185707254140620L;

  private DeliveryManageModel model;

  public QueryCustCreditAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!SysInitGroupQuery.isCREDITEnabled()) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0450")/*请启用信用管理模块*/);
    }
    DeliveryVO vo = (DeliveryVO) this.getModel().getSelectedData();
    DeliveryBVO[] bodys = vo.getChildrenVO();
    int i = 0;
    // 数据封装为BillQueryPara[]
    BillQueryPara[] bqpS = new BillQueryPara[bodys.length];
    for (DeliveryBVO body : bodys) {
      bqpS[i] = new BillQueryPara();
      // 渠道类型
      bqpS[i].setCchanneltypeid(body.getCchanneltypeid());
      // 客户
      bqpS[i].setCcustomerid(body.getCordercustid());
      // 销售业务员
      bqpS[i].setCemployeeid(body.getCemployeeid());
      // 财务组织
      bqpS[i].setCfinanceorgid(body.getCsettleorgid());
      // 物料
      bqpS[i].setCinvtoryid(body.getCmaterialid());
      // 产品线
      bqpS[i].setCprodlineid(body.getCprodlineid());
      // 销售部门
      bqpS[i].setCsaledeptid(body.getCdeptid());
      // 销售组织
      bqpS[i].setCsaleorgid(body.getCsaleorgid());
      // 订单类型
      bqpS[i].setVtrantypecode(body.getVsrctrantype());
      i++;
    }

    // 调用接口
    IBillCreditQueryMessage service =
        NCUILocator.getInstance().lookup(IBillCreditQueryMessage.class,
            NCModule.CREDIT);
    // 参数为：Container,billType，BillQueryPara[]
    service.showMessage(WorkbenchEnvironment.getInstance().getWorkbench()
        .getParent(), "4331", bqpS);
  }

  public DeliveryManageModel getModel() {
    return this.model;
  }

  public void setModel(DeliveryManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {

    boolean isEnable = this.model.getSelectedData() != null;
    return isEnable;
  }

  private void initializeAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_CREDITQUERY);

  }

}
