package nc.ui.so.m30.closemanage.action;

import java.awt.event.ActionEvent;

import nc.itf.uap.pf.metadata.IFlowBizItf;
import nc.md.data.access.NCObject;
import nc.ui.pubapp.billgraph.SourceBillFlowDlg;
import nc.ui.pubapp.uif2app.actions.LinkQueryAction.IBillInfo;
import nc.ui.pubapp.uif2app.actions.LinkQueryAction.IBillInfoFactory;
import nc.ui.pubapp.uif2app.model.BatchBillTableModel;
import nc.ui.pubapp.uif2app.view.BatchBillTable;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.trade.billgraph.billflow.control.DefaultBillGraphListener;
import nc.ui.trade.billgraph.billflow.control.IBillGraphListener;
import nc.ui.uif2.NCAction;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.util.CombineViewToAggUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

public class M30CloseLinkQueryAction extends NCAction {

  /**
   *
   */
  private static final long serialVersionUID = -2880555158649701559L;

  protected BatchBillTableModel model;

  private SourceBillFlowDlg sourceBillFlowDlg = null;

  private BatchBillTable billTable;

  String billType;

  public SourceBillFlowDlg getSourceBillFlowDlg() {
    return this.sourceBillFlowDlg;
  }

  public void setSourceBillFlowDlg(SourceBillFlowDlg sourceBillFlowDlg) {
    this.sourceBillFlowDlg = sourceBillFlowDlg;
  }

  public String getBillType() {
    return this.billType;
  }

  public void setBillType(String billType) {
    this.billType = billType;
  }

  public IBillGraphListener getBillGraphListener() {
    return this.billGraphListener;
  }

  public void setBillGraphListener(IBillGraphListener billGraphListener) {
    this.billGraphListener = billGraphListener;
  }

  private IBillInfoFactory<Object> billInfoFactory;

  private IBillGraphListener billGraphListener = null;

  class DefaultBillInfoFactory implements IBillInfoFactory<Object> {
    String billCode;

    String billId;

    @Override
    public IBillInfo getBillInfo(Object o) {
      NCObject ncObject = NCObject.newInstance(o);
      IFlowBizItf flowBizItf = ncObject.getBizInterface(IFlowBizItf.class);
      this.billId = flowBizItf.getBillId();
      this.billCode = flowBizItf.getBillNo();

      return new IBillInfo() {

        @Override
        public String getBillCode() {
          return DefaultBillInfoFactory.this.billCode;
        }

        @Override
        public String getBillId() {
          return DefaultBillInfoFactory.this.billId;
        }

        @Override
        public String getBillType() {
          return M30CloseLinkQueryAction.this.billType;
        }
      };
    }

  }

  public IBillInfoFactory<Object> getBillInfoFactory() {
    if (null == this.billInfoFactory) {
      this.billInfoFactory = new DefaultBillInfoFactory();
    }
    return this.billInfoFactory;
  }

  public void setBillInfoFactory(IBillInfoFactory<Object> billInfoFactory) {
    this.billInfoFactory = billInfoFactory;
  }

  private static final String BILL_FINDER_CLASSNAME =
      "nc.impl.pubapp.linkquery.BillTypeSetBillFinder";

  public M30CloseLinkQueryAction() {
    SCMActionInitializer
        .initializeAction(this, SCMActionCode.SCM_BILLLINKQUERY);
    // 增加默认监听
    this.billGraphListener = new DefaultBillGraphListener();
    this.setOpenMode(1);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {

    SaleOrderViewVO selectedData =
        (SaleOrderViewVO) this.model.getSelectedData();
    if (null == selectedData) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0042")/*@res "请选择一行记录"*/);
    }
    // String billid = selectedData.getHead().getCsaleorderid();
    // String billcode = selectedData.getHead().getVbillcode();

    SaleOrderVO[] vos =
        new CombineViewToAggUtil<SaleOrderVO>(SaleOrderVO.class,
            SaleOrderHVO.class, SaleOrderBVO.class).combineViewToAgg(
            new SaleOrderViewVO[] {
              selectedData
            }, SaleOrderHVO.CSALEORDERID);

    if (vos.length > 1) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0042")/*@res "请选择一行记录"*/);
    }
    // 初始化对话框
    if (this.sourceBillFlowDlg == null) {
      this.sourceBillFlowDlg =
          new SourceBillFlowDlg(this.model.getContext().getEntranceUI());
      this.sourceBillFlowDlg
          .setBillFinderClassname(M30CloseLinkQueryAction.BILL_FINDER_CLASSNAME);
    }

    IBillInfo info = this.getBillInfoFactory().getBillInfo(vos[0]);

    // 设置参数
    this.sourceBillFlowDlg.setBillType(info.getBillType());
    this.sourceBillFlowDlg.setBillID(info.getBillId());
    this.sourceBillFlowDlg.setBillNO(info.getBillCode());

    // 设置监听器
    this.sourceBillFlowDlg.setBillGraphListener(this.billGraphListener);
    // 打开对话框
    this.sourceBillFlowDlg.showModal();
  }

  public BatchBillTable getBillTable() {
    return this.billTable;
  }

  public void setOpenMode(int openMode) {
    // 设置是否权限模式
    if (this.billGraphListener != null
        && this.billGraphListener instanceof DefaultBillGraphListener) {
      ((DefaultBillGraphListener) this.billGraphListener).setOpenMode(openMode);
    }
  }

  public int getOpenMode() {
    if (this.billGraphListener != null
        && this.billGraphListener instanceof DefaultBillGraphListener) {
      return ((DefaultBillGraphListener) this.billGraphListener).getOpenMode();
    }
    else {
      return -1;
    }
  }

  public BatchBillTableModel getModel() {
    return this.model;
  }

  public void setBillTable(BatchBillTable billTable) {
    this.billTable = billTable;
  }

  public void setModel(BatchBillTableModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

}
