package nc.ui.so.m30.billui.action.link;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.impl.scmpub.payterm.RecvPlan;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.pub.dlg.RecPlanDlg;
import nc.ui.uif2.NCAction;
import nc.vo.bd.income.IncomeVO;
import nc.vo.scmpub.payterm.recv.IRecvPlanData;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.entry.RecPlanVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.rule.RecPlanData;
import nc.vo.so.pub.util.SOVOChecker;

/**
 * 收款计划按钮响应类
 * 
 * @since 6.0
 * @version 2011-7-2 上午09:58:09
 * @author 么贵敬
 */
public class SaleOrderRecPlanAction extends NCAction {

  private static final long serialVersionUID = 1L;

  /** 订单卡控件 */
  private SaleOrderBillForm editor;

  private BillManageModel model;

  /** 构造函数 */
  public SaleOrderRecPlanAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    Object[] objs = this.getModel().getSelectedOperaDatas();
    if (SOVOChecker.isEmpty(objs)) {
      return;
    }

    SaleOrderVO[] vos = new SaleOrderVO[objs.length];
    for (int i = 0; i < objs.length; i++) {
      vos[i] = (SaleOrderVO) objs[i];
    }

    IRecvPlanData[] recvplandatas = new RecPlanData[vos.length];
    for (int i = 0; i < vos.length; ++i) {
      recvplandatas[i] = new RecPlanData(vos[i]);
    }
    RecvPlan<RecPlanVO, IRecvPlanData> recvPlan =
        new RecvPlan<RecPlanVO, IRecvPlanData>(RecPlanVO.class, recvplandatas,
            IncomeVO.class);
    List<RecPlanVO[]> payplanList = recvPlan.getPlan();

    List<RecPlanVO> list = new ArrayList<RecPlanVO>();
    for (int i = 0; i < recvplandatas.length; ++i) {
      RecPlanVO[] recplanVOs = payplanList.get(i);
      for (RecPlanVO vo : recplanVOs) {
        list.add(vo);
      }
    }

    RecPlanDlg recplandlg =
        new RecPlanDlg(this.getEditor().getBillCardPanel(),
            list.toArray(new RecPlanVO[list.size()]), true);
    recplandlg.showModal();

  }

  @Override
  protected boolean isActionEnable() {

    return this.model.getSelectedData() != null;

  }

  public SaleOrderBillForm getEditor() {
    return this.editor;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setEditor(SaleOrderBillForm editor) {
    this.editor = editor;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  private void initializeAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.CT_RECEIVEPLAN);
  }

}
