package nc.ui.so.m4331.billui.action.returnaction;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.pubitf.so.m4331.qc.mc003.IQueryReportVOForC003;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.bill.BillModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m4331.billui.model.DeliveryManageModel;
import nc.ui.so.m4331.billui.view.DeliveryEditor;
import nc.ui.so.m4331.billui.view.DeliveryListView;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.qc.c003.entity.ReportVO;
import nc.vo.qc.pub.constant.QCNodeCode;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.enumeration.BillStatus;

public class CheckBillReportAction extends NCAction {
  private static final long serialVersionUID = -4490586267001887381L;

  private DeliveryEditor editor;

  private DeliveryListView listview;

  private DeliveryManageModel model;

  public CheckBillReportAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    DeliveryBVO[] bvos = this.getBodyVOs();
    if (null == bvos) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006002_0", "04006002-0009")/*@res "请选中表体行进行质检报告联查。"*/);
      return;
    }
    this.check(bvos);
    ReportVO[] vos = this.getQualityInfos(bvos);
    this.linkQualityReport(vos);
  }

  public DeliveryEditor getEditor() {
    return this.editor;
  }

  public DeliveryListView getListview() {
    return this.listview;
  }

  public DeliveryManageModel getModel() {
    return this.model;
  }

  public void setEditor(DeliveryEditor editor) {
    this.editor = editor;
  }

  public void setListview(DeliveryListView listview) {
    this.listview = listview;
  }

  public void setModel(DeliveryManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
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

  private void check(DeliveryBVO[] bvos) {
    StringBuffer errMsg = new StringBuffer();
    for (DeliveryBVO bvo : bvos) {
      UFBoolean flag = bvo.getBcheckflag();
      if (null == flag || !flag.booleanValue()) {
        errMsg.append(NCLangRes.getInstance().getStrByID("4006002_0",
            "04006002-0111", null, new String[] {
              bvo.getCrowno()
            })/*发货单表体下列行：{0}没有质检报告*/);
        errMsg.append("\n");
        continue;
      }
    }
    if (errMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
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

  private ReportVO[] getQualityInfos(DeliveryBVO[] bvos) {
    Set<String> bidSet = new HashSet<String>();
    for (DeliveryBVO bvo : bvos) {
      bidSet.add(bvo.getCdeliverybid());
    }
    String[] bids = new String[bidSet.size()];
    bidSet.toArray(bids);
    IQueryReportVOForC003 service =
        NCLocator.getInstance().lookup(IQueryReportVOForC003.class);
    try {
      ReportVO[] vos = service.queryReportVOs(bids);
      return vos;
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private void initializeAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_QULITYREPORT);
  }

  private void linkQualityReport(ReportVO[] vos) {
    FuncletInitData initData = new FuncletInitData();
    initData.setInitType(30);
    initData.setInitData(vos);
    WorkbenchEnvironment instance = WorkbenchEnvironment.getInstance();
    // 节点编码C06005
    FuncRegisterVO funvo =
        instance.getFuncRegisterVO(QCNodeCode.REPORT_NODECODE);
    int screenWidth =
        Toolkit.getDefaultToolkit().getScreenSize().width;
    int screenHeight =
        Toolkit.getDefaultToolkit().getScreenSize().height - 1;
    FuncletWindowLauncher.openFuncNodeForceModalDialog(this.getModel().getContext()
        .getEntranceUI(), funvo, initData, null, true, new Dimension(
        screenWidth, screenHeight),true);

  }
}
