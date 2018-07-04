package nc.ui.so.m4331.billui.action.assitfunc;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.ic.pub.ReserveUIService;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m4331.billui.view.DeliveryEditor;
import nc.ui.uif2.NCAction;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * 发货单预留
 * 
 * @since 6.0
 * @version 2011-1-21 下午03:50:30
 * @author 祝会征
 */
@SuppressWarnings("restriction")
public class DeliveryQueryReverseAction extends NCAction {
  private static final long serialVersionUID = -4490586267001887381L;

  private DeliveryEditor editor;

  private BillManageModel model;

  public DeliveryQueryReverseAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if(!SysInitGroupQuery.isICEnabled()) {
	  return;
	}
    boolean flag = this.editor.isComponentVisible();
    DeliveryVO[] vos = null;
    if (flag) {
      vos = new DeliveryVO[1];
      vos[0] = (DeliveryVO) this.model.getSelectedData();
    }
    else if (!flag) {
      Object[] objs = this.model.getSelectedOperaDatas();
      vos = new DeliveryVO[objs.length];
      int i = 0;
      for (Object obj : objs) {
        vos[i] = (DeliveryVO) obj;
        i++;
      }
    }
    this.queryReverse(vos);
  }

  public DeliveryEditor getEditor() {
    return this.editor;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setEditor(DeliveryEditor editor) {
    this.editor = editor;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    boolean isEnable =
        this.getModel().getAppUiState() == AppUiState.NOT_EDIT
            && null != this.getModel().getSelectedData();
    return isEnable;
  }

  private String[] getBids(DeliveryVO[] vos) {
    Set<String> bidset = new HashSet<String>();
    for (DeliveryVO vo : vos) {
      DeliveryBVO[] bvos = vo.getChildrenVO();
      for (DeliveryBVO bvo : bvos) {
        bidset.add(bvo.getCdeliverybid());
      }
    }
    if (bidset.size() == 0) {
      return null;
    }
    String[] bids = new String[bidset.size()];
    return bidset.toArray(bids);
  }

  private void initializeAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_QUERYRESERVE);
  }

  /*
   * 预留查询
   */
  private void queryReverse(DeliveryVO[] vos) {
    if (null == vos) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006002_0", "04006002-0002")/*@res "请选中发货单。"*/);
      return;
    }
    String[] bids = this.getBids(vos);
    if (null == bids) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006002_0", "04006002-0002")/*@res "请选中发货单。"*/);
      return;
    }
    String pk_group = AppContext.getInstance().getPkGroup();
    ReserveUIService service = new ReserveUIService(this.editor);
    service.showReserveBill(bids, pk_group);
  }
}
