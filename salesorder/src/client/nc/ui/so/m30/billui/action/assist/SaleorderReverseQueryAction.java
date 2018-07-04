package nc.ui.so.m30.billui.action.assist;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.ic.pub.ReserveUIService;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.uif2.NCAction;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;

@SuppressWarnings("restriction")
public class SaleorderReverseQueryAction extends NCAction {
  private static final long serialVersionUID = -9115425248732509245L;

  private SaleOrderBillForm editor;

  private BillManageModel model;

  public SaleorderReverseQueryAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {

    if (!SysInitGroupQuery.isICEnabled()) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0438")/*请先启用库存模块！*/);
    }

    boolean flag = this.editor.isComponentVisible();
    SaleOrderVO[] vos = null;
    if (flag) {
      vos = new SaleOrderVO[1];
      vos[0] = (SaleOrderVO) this.model.getSelectedData();
    }
    else if (!flag) {
      Object[] objs = this.model.getSelectedOperaDatas();
      vos = new SaleOrderVO[objs.length];
      int i = 0;
      for (Object obj : objs) {
        vos[i] = (SaleOrderVO) obj;
        i++;
      }
    }
    this.revserQuery(vos);
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
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {

    boolean isEnable =
        (this.getModel().getAppUiState() == AppUiState.NOT_EDIT)
            && (null != this.getModel().getSelectedData());
    return isEnable;
  }

  private void initializeAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_QUERYRESERVE);
  }

  private void revserQuery(SaleOrderVO[] vos) {
    if (null == vos) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0018")/*@res "请选中销售订单进行预留查询。"*/);
      return;
    }
    Set<String> bidSet = new HashSet<String>();
    for (SaleOrderVO vo : vos) {
      SaleOrderBVO[] bvos = vo.getChildrenVO();
      for (SaleOrderBVO bvo : bvos) {
        bidSet.add(bvo.getCsaleorderbid());
      }
    }
    String[] bids = new String[bidSet.size()];
    bidSet.toArray(bids);
    ReserveUIService service = new ReserveUIService(this.editor);
    String pk_group = AppContext.getInstance().getPkGroup();
    service.showReserveBill(bids, pk_group);
  }

}
