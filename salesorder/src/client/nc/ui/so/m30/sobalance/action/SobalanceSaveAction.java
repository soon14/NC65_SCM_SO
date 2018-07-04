package nc.ui.so.m30.sobalance.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30.sobalance.ISOBalanceMaintain;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.so.m30.sobalance.view.SobalanceBillForm;
import nc.ui.uif2.actions.SaveAction;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.trade.checkrule.VOChecker;

@SuppressWarnings("serial")
public class SobalanceSaveAction extends SaveAction {
  private SobalanceBillForm view;

  public SobalanceBillForm getView() {
    return this.view;
  }

  public void setView(SobalanceBillForm view) {
    this.view = view;
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.view.getBillCardPanel().stopEditing();

    if (!this.getView().validateValue()) {
      return;
    }

    if (VOChecker.isEmpty(this.getModel().getContext().getPk_org())) {
      MessageDialog
          .showWarningDlg(this.view, null, nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4006011_0", "04006011-0044")/*@res "必须选择主组织。"*/);
      return;
    }

    SoBalanceVO value = (SoBalanceVO) this.getView().getValue();

    this.validate(value);

    BillManageModel model = (BillManageModel) this.getModel();
    if (model.getAppUiState().getUiState() == AppUiState.ADD.getUiState()) {
      this.insertBill(model, value);

    }
    if (model.getAppUiState().getUiState() == AppUiState.EDIT.getUiState()) {
      this.updateBill(model, value);
    }

    model.setAppUiState(AppUiState.NOT_EDIT);
    this.showSuccessInfo();
  }

  private void insertBill(BillManageModel model, SoBalanceVO value) {
    // 新增保存：表体行为空则清空所有
    if (value.getChildrenVO().length == 0) {
      this.getModel().initModel(null);
      return;
    }
    SoBalanceVO[] bills =
        new ClientBillToServer<SoBalanceVO>()
            .constructInsert(new SoBalanceVO[] {
              value
            });

    SoBalanceVO[] ret = null;
    ISOBalanceMaintain service =
        NCLocator.getInstance().lookup(ISOBalanceMaintain.class);
    try {
      ret = service.insertSoBalanceVO(bills);
      SoBalanceVO[] clientvos = new SoBalanceVO[] {
        value
      };

      // 后台变化VO与前台合并
      ClientBillCombinServer<SoBalanceVO> util =
          new ClientBillCombinServer<SoBalanceVO>();
      util.combine(clientvos, ret);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    model.directlyAdd(value);
  }

  private void updateBill(BillManageModel model, SoBalanceVO value) {
    int index = model.findBusinessData(value);
    if (index == -1) {

      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0019")/*@res "修改保存时，获取前台差异VO出错。"*/);
    }

    SoBalanceVO data = (SoBalanceVO) model.getData().get(index);
    // data为当前model缓存中的数据，value为当前界面上的数据
    SoBalanceVO bill =
        new ClientBillToServer<SoBalanceVO>().construct(new SoBalanceVO[] {
          data
        }, new SoBalanceVO[] {
          value
        })[0];

    SoBalanceVO ret = null;
    ISOBalanceMaintain service =
        NCLocator.getInstance().lookup(ISOBalanceMaintain.class);
    try {
      ret = service.updateSoBalanceVO(new SoBalanceVO[] {
        bill
      })[0];
      if (ret.getParentVO().getDr() != null
          && ret.getParentVO().getDr().intValue() > 0) {
        model.setAppUiState(AppUiState.NOT_EDIT);
        this.showSuccessInfo();
        model.initModel(null);
      }
      else {
        // 后台变化VO与前台合并
        ClientBillCombinServer<SoBalanceVO> util =
            new ClientBillCombinServer<SoBalanceVO>();
        util.combine(new SoBalanceVO[] {
          value
        }, new SoBalanceVO[] {
          ret
        });
        if (null == value || null == value.getChildrenVO()
            || value.getChildrenVO().length == 0) {
          model.initModel(null);
        }
        else {
          model.directlyUpdate(value);
        }
      }
    }
    catch (Exception e) {

      ExceptionUtils.wrappException(e);
    }
  }

}
