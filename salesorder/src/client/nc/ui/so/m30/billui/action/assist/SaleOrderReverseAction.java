package nc.ui.so.m30.billui.action.assist;

import java.awt.event.ActionEvent;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.ic.pub.ReserveUIService;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.actions.RefreshSingleAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.billui.view.SaleOrderBillListView;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.rule.OrderReverseConditionRule;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.DirectType;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.util.AggVOUtil;

/**
 * 销售订单预留
 * 
 * @since 6.0
 * @version 2011-1-21 下午03:50:30
 * @author 祝会征
 */
@SuppressWarnings("restriction")
public class SaleOrderReverseAction extends NCAction {
  private static final long serialVersionUID = -4490586267001887381L;

  private SaleOrderBillForm editor;

  private SaleOrderBillListView listView;

  private BillManageModel model;

  private RefreshSingleAction refreshAction;

  public SaleOrderReverseAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {

    if (!SysInitGroupQuery.isICEnabled()) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0438")/*请先启用库存模块！*/);
    }
    SaleOrderBVO[] bvos = this.getReverseVO();
    if(bvos == null || bvos.length == 0 || bvos[0] == null){
      ExceptionUtils
      .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0011")/*@res "必须选中表体行"*/);
    }
    OrderReverseConditionRule rule = new OrderReverseConditionRule(bvos);
    rule.checkReverse();
    int flag = this.reverse(bvos);
    if (flag == 1) {
      this.refreshAction.doAction(e);
    }
  }

  public SaleOrderBillForm getEditor() {
    return this.editor;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public RefreshSingleAction getreFreshAction() {
    return this.refreshAction;
  }

  public void setEditor(SaleOrderBillForm editor) {
    this.editor = editor;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  public void setRefreshAction(RefreshSingleAction refreshAction1) {
    this.refreshAction = refreshAction1;
  }

  @Override
  protected boolean isActionEnable() {

    SaleOrderVO vo = (SaleOrderVO) this.getModel().getSelectedData();
    if (this.getModel().getUiState() != UIState.NOT_EDIT || null == vo) {
      return false;
    }
    Integer billstatus = vo.getParentVO().getFstatusflag();
    if (BillStatus.CLOSED.equalsValue(billstatus)) {
      return false;
    }
    int[] rows = null;
    if (this.editor.isComponentVisible()) {
      rows = this.editor.getBillCardPanel().getBillTable().getSelectedRows();
    }
    else {
      UITable table = this.listView.getBillListPanel().getBodyTable();
      rows = table.getSelectedRows();
    }
    if (null == rows || rows.length == 0) {
      return false;
    }
    return true;
  }

  /*
   * 检查单据状态，已经整单关闭的单据不能进行预留
   */
  private void checkBillState(SaleOrderHVO hvo) {
    Integer billstatus = hvo.getFstatusflag();
    if (BillStatus.CLOSED.equalsValue(billstatus)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0015")/*@res "单据已经整单关闭，不能做预留。"*/);
    }
    if (BillStatus.FREEZE.equalsValue(billstatus)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0016")/*@res "单据已经冻结，不能做预留。"*/);
    }
    // 交易类型为直运的销售订单不支持预留
    String tranTypeCode = hvo.getVtrantypecode();
    String pk_group = hvo.getPk_group();
    M30TranTypeVO mVO = this.getM30TranTypeVO(tranTypeCode, pk_group);
    if (DirectType.DIRECTTRAN_NO.getIntValue() != mVO.getFdirecttype()
        .intValue()) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0017")/*@res "交易类型为直运的销售订单，不能做预留。"*/);
    }
  }

  private M30TranTypeVO getM30TranTypeVO(String tranTypeCode, String pk_group) {
    return this.editor.getM30ClientContext().getTransType(tranTypeCode,
        pk_group);
  }

  /*
   *  获得要预留的销售订单vo
   * @return
   */
  private SaleOrderBVO[] getReverseVO() {
    boolean flag = this.editor.isComponentVisible();
    SaleOrderBVO[] bvos = null;
    if (!flag) {
      UITable table = this.listView.getBillListPanel().getBodyTable();
      int[] rows = table.getSelectedRows();
      BillModel billmodel = this.listView.getBillListPanel().getBodyBillModel();
      bvos = new SaleOrderBVO[rows.length];
      int i = 0;
      for (int row : rows) {
        bvos[i] =
            (SaleOrderBVO) billmodel.getBodyValueRowVO(row,
                SaleOrderBVO.class.getName());
      }
    }
    else {
      int[] rows =
          this.editor.getBillCardPanel().getBillTable().getSelectedRows();
      bvos = new SaleOrderBVO[rows.length];
      for (int i = 0; i < rows.length; i++) {
        bvos[i] =
            (SaleOrderBVO) this.editor.getBillCardPanel().getBillModel()
                .getBodyValueRowVO(rows[i], SaleOrderBVO.class.getName());
      }
    }
    SaleOrderVO vo = (SaleOrderVO) this.getModel().getSelectedData();
    SaleOrderHVO hvo = vo.getParentVO();
    this.checkBillState(hvo);
    return bvos;
  }

  private void initializeAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_RESERVE);
  }

  /*
   * 预留
   */
  private int reverse(SaleOrderBVO[] bvos) {
    SaleOrderVO vo = (SaleOrderVO) this.getModel().getSelectedData();
    SaleOrderHVO hvo = vo.getParentVO();
    // 构造销售订单聚合vo
    SaleOrderVO[] vos =
        (SaleOrderVO[]) AggVOUtil.createBillVO(new SaleOrderHVO[] {
          hvo
        }, bvos, SaleOrderVO.class);
    ReserveUIService service = new ReserveUIService(this.editor);
    int flag = service.reserveBill(SOBillType.Order.getCode(), vos);
    return flag;
  }

  public void setListView(SaleOrderBillListView listview) {
    this.listView = listview;
  }

  public SaleOrderBillListView getListView() {
    return this.listView;
  }
}
