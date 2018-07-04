package nc.ui.so.m4331.billui.action.linkaction;

import java.awt.event.ActionEvent;

import nc.pubitf.dm.deliverystatus.IDeliveryStatus;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.RefreshSingleAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m4331.billui.model.DeliveryManageModel;
import nc.ui.uif2.NCAction;
import nc.vo.dm.deliverystatus.SourceBillDeliveryStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.enumeration.BillStatus;

public class TransportStatusAction extends NCAction {

  private static final long serialVersionUID = 2443043993311742311L;

  private DeliveryManageModel model;

  private RefreshSingleAction refreshAction;

  public TransportStatusAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    DeliveryVO vo = (DeliveryVO) this.getModel().getSelectedData();
    boolean flag = this.showTransSport(vo);
    if (!flag) {
      return;
    }
    this.refreshAction.doAction(e);
  }

  public DeliveryManageModel getModel() {
    return this.model;
  }

  public RefreshSingleAction getreFreshAction() {
    return this.refreshAction;
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
    boolean isEnable =
        (this.getModel().getAppUiState() == AppUiState.NOT_EDIT)
            && (null != this.getModel().getSelectedData());

    if (isEnable) {
      Object[] selectedRows = this.getModel().getSelectedOperaDatas();
      DeliveryVO selectedData = (DeliveryVO) this.getModel().getSelectedData();
      Integer billstatus = selectedData.getParentVO().getFstatusflag();
      boolean expr1 = (null != selectedRows) && (selectedRows.length == 1);
      boolean expr2 = BillStatus.AUDIT.equalsValue(billstatus);
      boolean expr3 = BillStatus.CLOSED.equalsValue(billstatus);
      isEnable = expr1 && (expr2 || expr3);
    }
    return isEnable;
  }

  private void initializeAction() {
    SCMActionInitializer.initializeAction(this,
        SCMActionCode.SO_TRANSPORTSTATUS);
  }

  private boolean showTransSport(DeliveryVO vo) {
    DeliveryBVO[] bvos = vo.getChildrenVO();
    String pk_group = vo.getParentVO().getPk_group();
    SourceBillDeliveryStatus[] data = new SourceBillDeliveryStatus[bvos.length];
    int i = 0;
    for (DeliveryBVO bvo : bvos) {
      data[i] = new SourceBillDeliveryStatus();
      data[i].setBsendendflag(bvo.getBtransendflag());
      data[i].setCbill_bid(bvo.getCdeliverybid());
      data[i].setCbill_id(bvo.getCdeliveryid());
      data[i].setCbilltype(SOBillType.Delivery.getCode());
      data[i].setCinventoryid(bvo.getCmaterialid());
      data[i].setCinventoryid_v(bvo.getCmaterialvid());
      data[i].setCrowno(bvo.getCrowno());
      UFDouble num = bvo.getNnum();
      UFDouble totalTranNum = bvo.getNtotaltransnum();
      UFDouble value = MathTool.sub(num, totalTranNum);
      data[i].setNcansendnum(value);
      data[i].setNnum(num);
      data[i].setNsendnum(totalTranNum);
      data[i].setPk_group(pk_group);
      data[i].setVpuplancode(bvo.getCrowno());
      data[i].setCunitid(bvo.getCunitid());
      i++;
    }
    boolean flag = false;
    // IDeliveryStatus service =
    // NCLocator.getInstance().lookup(IDeliveryStatus.class);
    IDeliveryStatus service =
        NCUILocator.getInstance().lookup(IDeliveryStatus.class, NCModule.DM);
    try {
      flag = service.show(this.getModel().getContext().getEntranceUI(), data);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return flag;
  }
}
