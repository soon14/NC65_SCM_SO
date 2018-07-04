package nc.ui.so.tranmatrel.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.tranmatrel.ITranMatRelMaintain;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.SaveAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.so.tranmatrel.rule.BillDataValidateRule;
import nc.ui.so.tranmatrel.view.CardForm;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.HierachicalDataAppModel;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.so.tranmatrel.entity.TranMatRelVO;
import nc.vo.trade.checkrule.VOChecker;

@SuppressWarnings("serial")
public class TranMatRelSaveAction extends SaveAction {
  private CardForm view;

  public CardForm getView() {
    return this.view;
  }

  public void setView(CardForm view) {
    this.view = view;
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.view.getBillCardPanel().stopEditing();

    if (VOChecker.isEmpty(this.getModel().getContext().getPk_org())) {
      MessageDialog.showWarningDlg(this.view, null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_0","04006007-0001")/*@res "必须选择主组织。"*/);
      return;
    }
    Object value = this.getEditor().getValue();
    this.view.validateValue();
    this.validate(value);
    if (this.getModel().getUiState() == UIState.ADD) {
      this.insertBill(value);
      this.getModel().setUiState(UIState.NOT_EDIT);
      if (this.getModel() instanceof HierachicalDataAppModel) {
        ((HierachicalDataAppModel) this.getModel()).setSelectedData(value);
        // 以上两句顺序不可调换。
      }
    }
    else if (this.getModel().getUiState() == UIState.EDIT) {
      this.updateBill(value);

      this.getModel().setUiState(UIState.NOT_EDIT);
    }
    this.showSuccessInfo();
  }

  private void insertBill(Object value) {
    TranMatRelVO bill =
        new ClientBillToServer<TranMatRelVO>()
            .constructInsert(new TranMatRelVO[] {
              (TranMatRelVO) value
            })[0];
    BillDataValidateRule check = new BillDataValidateRule();
    check.validate(bill);
    TranMatRelVO retvo = null;
    try {
      ITranMatRelMaintain service =
          NCLocator.getInstance().lookup(ITranMatRelMaintain.class);
      retvo = service.insert(bill);
      // 后台变化VO与前台合并
      ClientBillCombinServer<TranMatRelVO> util =
          new ClientBillCombinServer<TranMatRelVO>();
      util.combine(new TranMatRelVO[] {
        (TranMatRelVO) value
      }, new TranMatRelVO[] {
        retvo
      });
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    this.getModel().directlyAdd(value);
  }

  private void updateBill(Object value) {
    int index = ((BillManageModel) this.getModel()).findBusinessData(value);
    if (index == -1) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_0","04006007-0002")/*@res "修改保存时，获取前台差异VO出错。"*/);
    }

    TranMatRelVO data =
        (TranMatRelVO) ((BillManageModel) this.getModel()).getData().get(index);
    // data为当前model缓存中的数据，value为当前界面上的数据
    TranMatRelVO bill =
        new ClientBillToServer<TranMatRelVO>().construct(new TranMatRelVO[] {
          data
        }, new TranMatRelVO[] {
          (TranMatRelVO) value
        })[0];
    BillDataValidateRule check = new BillDataValidateRule();
    check.validate(data);
    TranMatRelVO ret = null;
    ITranMatRelMaintain service =
        NCLocator.getInstance().lookup(ITranMatRelMaintain.class);
    try {
      ret = service.update(bill);
      // 后台变化VO与前台合并
      ClientBillCombinServer<TranMatRelVO> util =
          new ClientBillCombinServer<TranMatRelVO>();
      util.combine(new TranMatRelVO[] {
        (TranMatRelVO) value
      }, new TranMatRelVO[] {
        ret
      });
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    this.getModel().directlyUpdate(value);
  }

}