package nc.ui.so.m4331.billui.action.returnaction;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.qc.c001.so.IPushSaveForSO;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.actions.RefreshSingleAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m4331.billui.model.DeliveryManageModel;
import nc.ui.so.m4331.billui.view.DeliveryEditor;
import nc.ui.so.m4331.billui.view.DeliveryListView;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.pub.DeliveryVoUtil;
import nc.vo.so.m4331.rule.CheckBillRule;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.util.AggVOUtil;

public class CheckBillAction extends NCAction {

  private static final long serialVersionUID = -4490586267001887381L;

  private final String body = DeliveryBVO.class.getName();

  private DeliveryEditor editor;
  
  private DeliveryListView listview;

  private final String head = DeliveryHVO.class.getName();

  private DeliveryManageModel model;

  private RefreshSingleAction refreshAction;

  public CheckBillAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    DeliveryBVO[] bvos = this.getBodyVOs();
    if (null == bvos) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006002_0", "04006002-0004")/*@res "请选中表体行。"*/);
    }
    if (!SysInitGroupQuery.isQCEnabled()) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006002_0", "04006002-0187")/*未启用质量管理模块，不能进行报检。*/);
    }
    this.checkBill(bvos);
    this.refreshAction.doAction(e);
    ShowStatusBarMsgUtil.showStatusBarMsg(
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0",
            "04006002-0008")/*@res "报检成功。"*/, this.getModel().getContext());
  }
  
  private DeliveryBVO[] getBodyVOs() {
    boolean flag = this.editor.isComponentVisible();
    DeliveryBVO[] bvos = null;
    if (!flag) {
      UITable table = this.listview.getBillListPanel().getBodyTable();
      int[] rows = table.getSelectedRows();
      BillModel billmodel = this.listview.getBillListPanel().getBodyBillModel();
      bvos = new DeliveryBVO[rows.length];
      int i = 0;
      for (int row : rows) {
        bvos[i] =
            (DeliveryBVO) billmodel.getBodyValueRowVO(row,
                DeliveryBVO.class.getName());
      }
    }
    else {
      int[] rows =
          this.editor.getBillCardPanel().getBillTable().getSelectedRows();
      bvos = new DeliveryBVO[rows.length];
      for (int i = 0; i < rows.length; i++) {
        bvos[i] =
            (DeliveryBVO) this.editor.getBillCardPanel().getBillModel()
                .getBodyValueRowVO(rows[i], DeliveryBVO.class.getName());
      }
    }
    return bvos;
  }

  public DeliveryEditor getEditor() {
    return this.editor;
  }

  public DeliveryManageModel getModel() {
    return this.model;
  }

  public RefreshSingleAction getreFreshAction() {
    return this.refreshAction;
  }

  public void setEditor(DeliveryEditor editor) {
    this.editor = editor;
  }

  public void setModel(DeliveryManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  public void setRefreshAction(RefreshSingleAction refreshAction1) {
    this.refreshAction = refreshAction1;
  }
  
  

  @Override
  protected boolean isActionEnable() {
    DeliveryVO vo = (DeliveryVO) this.getModel().getSelectedData();
    if (this.getModel().getUiState() != UIState.NOT_EDIT || null == vo) {
      return false;
    }
    Integer billstatus = vo.getParentVO().getFstatusflag();
    if (!BillStatus.AUDIT.equalsValue(billstatus)) {
      return false;
    }
    UFDouble num = UFDouble.ZERO_DBL;
    int[] rows = null;
    if (this.editor.isComponentVisible()) {
      rows = this.editor.getBillCardPanel().getBillTable().getSelectedRows();
      if (null == rows || rows.length != 1) {
        return false;
      }
      DeliveryBVO bvo =
          (DeliveryBVO) this.editor.getBillCardPanel().getBillModel()
              .getBodyValueRowVO(rows[0], DeliveryBVO.class.getName());
      num = bvo.getNnum();
    }
    else {
      UITable table = this.listview.getBillListPanel().getBodyTable();
      rows = table.getSelectedRows();
      if (null == rows || rows.length != 1) {
        return false;
      }
      DeliveryBVO bvo =
          (DeliveryBVO) this.listview.getBillListPanel().getBodyBillModel()
              .getBodyValueRowVO(rows[0], DeliveryBVO.class.getName());
      num = bvo.getNnum();
    }
    if (MathTool.compareTo(num, UFDouble.ZERO_DBL) >= 0) {
      return false;
    }
    return true;
  }

  private void checkBill(DeliveryBVO[] bvos) {
    CheckBillRule check = new CheckBillRule(bvos);
    check.isCheckBill();
    // 获得发货单表头vo
    DeliveryHVO hvo =
        (DeliveryHVO) this.editor.getBillCardPanel().getBillData()
            .getHeaderValueVO(this.head);

    // 构建发货单聚合vo
    DeliveryVO[] vos = (DeliveryVO[]) AggVOUtil.createBillVO(new DeliveryHVO[] {
      hvo
    }, bvos, DeliveryVO.class);
    this.pushSave(vos);
  }

  private BillCardPanel getBillCardPanel() {
    return this.editor.getBillCardPanel();
  }

  private void initializeAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_SENDTOCHECK);
  }

  /*
   * 调用报检接口
   */
  private void pushSave(DeliveryVO[] vos) {

    IPushSaveForSO service =
        NCLocator.getInstance().lookup(IPushSaveForSO.class);
    try {
      DeliveryVoUtil util = new DeliveryVoUtil();
      util.absDeliveryVO(vos);
      service.pushSave(vos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }
  
  public DeliveryListView getListview() {
    return listview;
  }
  
  public void setListview(DeliveryListView listview) {
    this.listview = listview;
  }
}
