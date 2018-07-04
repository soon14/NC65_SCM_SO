package nc.ui.so.m4331.billui.action.addaction;

import java.awt.event.ActionEvent;

import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.so.m4331.billui.model.DeliveryManageModel;
import nc.ui.so.m4331.billui.view.DeliveryEditor;
import nc.ui.so.pub.editable.SOCardEditableSetter;
import nc.ui.uif2.UIState;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.trade.checkrule.VOChecker;

import org.apache.commons.lang.StringUtils;

public class RefAddAction extends AbstractReferenceAction {
  private static final long serialVersionUID = 8278944084279171430L;

  private DeliveryEditor editor;

  private DeliveryManageModel model;

  /**
   * RefAddAction 的构造子
   */
  public RefAddAction() {
    super();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.model.setSourceType(this.getSourceBillType());
    // 调用流程平台提供的公共转单方法
    PfUtilClient.childButtonClickedNew(this.createPfButtonClickContext());
    DeliveryVO[] newvos = null;
    if (PfUtilClient.isCloseOK()) {
      newvos = (DeliveryVO[]) PfUtilClient.getRetVos();
      if (VOChecker.isEmpty(newvos)) {
        return;
      }
      // 调公共转单处理前
      this.beforeTranProcessor(newvos);
      // 拉单数据界面处理
      this.getTransferViewProcessor().processBillTransfer(newvos);
      this.afterTranProcessor();
    }
  }

  public DeliveryEditor getEditor() {
    return this.editor;
  }

  public DeliveryManageModel getModel() {
    return this.model;
  }

  @Override
  public boolean isEnabled() {
    return this.getModel().getUiState() == UIState.NOT_EDIT;
  }

  public void setEditor(DeliveryEditor view) {
    this.editor = view;
  }

  public void setModel(DeliveryManageModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  protected void afterTranProcessor() {
    if (this.getSourceBillType().equals(TOBillType.TransOrder.getCode())) {
      // 来自调拨订单的发货库存组织不允许编辑
      this.getEditor().getBillCardPanel()
          .getBodyItem(DeliveryBVO.CSENDSTOCKORGVID).setEdit(false);
      this.getEditor().getBillCardPanel().getBodyItem(DeliveryBVO.CORDERCUSTID)
          .setEdit(false);
      this.getEditor().getBillCardPanel().getBodyItem(DeliveryBVO.CORDERCUSTID)
          .setNull(true);
    }
    else {
      // 来自销售订单的发货库存组织允许编辑
      this.getEditor().getBillCardPanel()
          .getBodyItem(DeliveryBVO.CSENDSTOCKORGVID).setEdit(true);
      this.getEditor().getBillCardPanel().getBodyItem(DeliveryBVO.CORDERCUSTID)
          .setEdit(true);
      // 来自销售订单的客户不允许为空
      this.getEditor().getBillCardPanel().getBodyItem(DeliveryBVO.CORDERCUSTID)
          .setNull(true);
    }
    new SOCardEditableSetter().setHeadEditForRef(this.getEditor()
        .getBillCardPanel());
  }

  protected void beforeTranProcessor(DeliveryVO[] newvos) {
    this.setDefaultDate(newvos);
  }

  private PfButtonClickContext createPfButtonClickContext() {
    PfButtonClickContext context = new PfButtonClickContext();
    context.setParent(this.getModel().getContext().getEntranceUI());
    context.setSrcBillType(this.getSourceBillType());
    context.setPk_group(this.getModel().getContext().getPk_group());
    context.setUserId(this.getModel().getContext().getPk_loginUser());
    // 如果该节点是由交易类型发布的，那么这个参数应该传交易类型，否则传单据类型
    // String billtype =
    // TrantypeFuncUtils.getTrantype(this.getModel().getContext());
    String tranType =
        TrantypeFuncUtils.getTrantype(this.getModel().getContext());
    if (StringUtils.isBlank(tranType)) {
      context.setCurrBilltype(SOBillType.Delivery.getCode());
    }
    else {
      context.setCurrBilltype(tranType);
    }
    context.setUserObj(null);
    context.setSrcBillId(null);
    context.setBusiTypes(this.getBusitypes());
    // 上面的参数在原来调用的方法中都有涉及，只不过封成了一个整结构，下面两个参数是新加的参数
    // 上游的交易类型集合
    context.setTransTypes(this.getTranstypes());
    // 标志在交换根据目的交易类型分组时，查找目的交易类型的依据，有三个可设置值：1（根据接口定义）、
    // 2（根据流程配置）、-1（不根据交易类型分组）
    context.setClassifyMode(PfButtonClickContext.ClassifyByBusiflow);
    return context;
  }

  private void setDefaultDate(DeliveryVO[] newvos) {
    for (DeliveryVO vo : newvos) {
      DeliveryHVO hvo = vo.getParentVO();
      UFDate date = AppContext.getInstance().getBusiDate();
      hvo.setDbilldate(date);
      DeliveryBVO[] bvos = vo.getChildrenVO();
      for (DeliveryBVO bvo : bvos) {
        bvo.setDbilldate(date);
        UFDate sendDate = bvo.getDsenddate();
        UFDate receiveDate = bvo.getDreceivedate();
        if (null == sendDate || sendDate.before(date)) {
          bvo.setDsenddate(date.asLocalEnd());
        }
        if (null == receiveDate || receiveDate.before(date)) {
          bvo.setDreceivedate(date.asLocalEnd());
        }
      }
    }
  }
}
