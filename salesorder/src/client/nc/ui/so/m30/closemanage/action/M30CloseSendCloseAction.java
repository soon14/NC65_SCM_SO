package nc.ui.so.m30.closemanage.action;

import java.awt.event.ActionEvent;

import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.util.ArrayUtil;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.pub.task.IMultiReturnObjProcessor;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.ui.pubapp.pub.task.MultiBillTaskRunner;
import nc.ui.pubapp.uif2app.model.BatchBillTableModel;
import nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction;
import nc.ui.pubapp.uif2app.view.BatchBillTable;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.components.progress.ProgressActionInterface;
import nc.ui.uif2.components.progress.TPAProgressUtil;

public class M30CloseSendCloseAction extends NCAction implements
    IMultiReturnObjProcessor, ProgressActionInterface {

  private static final long serialVersionUID = -7451726865173338707L;

  protected BatchBillTableModel model;

  private BatchBillTable billTable;

  private MultiBillTaskRunner multiBillTaskRunner;

  private ISingleBillService<SaleOrderViewVO> singleBillService;

  public M30CloseSendCloseAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_SENDCLOSE);
  }

  private DefaultRefreshAction refreshAction;

  /**
   * 获得刷新action
   * 
   * @return DefaultRefreshAction
   */
  public DefaultRefreshAction getRefreshAction() {
    return this.refreshAction;
  }

  /**
   * 设置刷新action
   * 
   * @param refreshAction
   */
  public void setRefreshAction(DefaultRefreshAction refreshAction) {
    this.refreshAction = refreshAction;
  }

  private TPAProgressUtil tpaProgressUtil;

  @Override
  public TPAProgressUtil getTpaProgressUtil() {
    if (this.tpaProgressUtil == null) {
      this.tpaProgressUtil = new TPAProgressUtil();
      this.tpaProgressUtil.setContext(this.getModel().getContext());
    }
    return this.tpaProgressUtil;
  }

  @Override
  public void setTpaProgressUtil(TPAProgressUtil tpaProgressUtil) {
    if (this.multiBillTaskRunner != null) {
      this.multiBillTaskRunner.setTpaProgressUtil(tpaProgressUtil);
    }
    this.tpaProgressUtil = tpaProgressUtil;
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    String sReason =
        (String) MessageDialog
            .showInputDlg(this.getModel().getContext().getEntranceUI(),
                NCLangRes.getInstance()
                    .getStrByID("4006011_0", "04006011-0233")/*关闭/打开原因*/,
                NCLangRes.getInstance()
                    .getStrByID("4006011_0", "04006011-0234")/*请输入关闭/打开原因*/,
                null, 120);
    // 取消或关闭原因框返回的都为null,不做关闭/打开处理
    if (sReason==null) {
      return;
    }
    if(PubAppTool.isNull(sReason)){
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance()
          .getStrByID("4006011_0", "04006011-0544")/*操作失败！关闭或打开的原因不能为空。*/);
    }
    Object[] objs = this.model.getSelectedOperaDatas();
    int len = objs.length;
    SaleOrderViewVO[] bills = new SaleOrderViewVO[len];
    for (int i = 0; i < len; i++) {
      bills[i] = (SaleOrderViewVO) objs[i];
    }
    // 设置关闭/打开原因
    this.setCloseOpenReason(bills, sReason);

    SaleOrderViewVO[] ret = null;
    if (this.getSingleBillService() != null) {
      if (bills.length > 1) {
        this.getMultiBillTaskRunner().setOperateObjs(bills);
        // this.getMultiBillTaskRunner().setParent(
        // WorkbenchEnvironment.getInstance().getWorkbench());
        this.getMultiBillTaskRunner()
            .setTitle(
                NCLangRes.getInstance()
                    .getStrByID("4006011_0", "04006011-0291")/*发货关闭*/);
        this.getMultiBillTaskRunner().setMultiReturnObjProcessor(this);
        this.getMultiBillTaskRunner().runTask();
      }
      else if (bills.length == 1) {
        ret = new SaleOrderViewVO[] {
          this.getSingleBillService().operateBill(bills[0])
        };
        this.processReturnObjs(ret);
      }
    }
    this.getRefreshAction().doAction(e);
  }

  private void setCloseOpenReason(SaleOrderViewVO[] views, String sReason) {
    for (SaleOrderViewVO view : views) {
      view.getBody().setVclosereason(sReason);
    }
  }

  public BatchBillTable getBillTable() {
    return this.billTable;
  }

  public BatchBillTableModel getModel() {
    return this.model;
  }

  public MultiBillTaskRunner getMultiBillTaskRunner() {
    if (this.multiBillTaskRunner == null) {
      this.multiBillTaskRunner =
          new MultiBillTaskRunner(this.getSingleBillService());
    }
    this.multiBillTaskRunner.setTpaProgressUtil(this.getTpaProgressUtil());
    return this.multiBillTaskRunner;
  }

  public ISingleBillService<SaleOrderViewVO> getSingleBillService() {

    return this.singleBillService;
  }

  @Override
  public void processReturnObjs(Object[] returnObj) {
    if (ArrayUtil.isEmptyOrNull(returnObj)) {
      return;
    }
    BatchOperateVO vo = new BatchOperateVO();
    vo.setUpdObjs(returnObj);
    try {
      this.model.directSave(vo);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }

    this.getBillTable().getBillCardPanel().getBillModel()
        .loadLoadRelationItemValue();
  }

  public void setBillTable(BatchBillTable billTable) {
    this.billTable = billTable;
  }

  public void setModel(BatchBillTableModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  public void setSingleBillService(
      ISingleBillService<SaleOrderViewVO> singleBillService) {
    this.singleBillService = singleBillService;
  }

  @Override
  protected boolean isActionEnable() {
    // 没有选中单据，置灰
    if (this.model.getSelectedData() == null) {
      return false;
    }
    // 多选什么状态都可以用
    if (this.model.getSelectedOperaRows().length > 1) {
      return true;
    }
    SaleOrderViewVO vo = (SaleOrderViewVO) this.getModel().getSelectedData();

    Integer fstatusflag = vo.getHead().getFstatusflag();
    if (!BillStatus.AUDIT.equalsValue(fstatusflag)) {
      return false;
    }

    UFBoolean bsendendflag = vo.getBody().getBbsendendflag();
    if (bsendendflag.booleanValue()) {
      return false;
    }
    Integer frowstatusflag = vo.getBody().getFrowstatus();
    if (BillStatus.CLOSED.equalsValue(frowstatusflag)) {
      return false;
    }

    return true;
  }
}
