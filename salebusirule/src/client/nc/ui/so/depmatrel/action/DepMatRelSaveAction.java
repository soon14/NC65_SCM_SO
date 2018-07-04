package nc.ui.so.depmatrel.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.depmatrel.IDepMatRelMaintain;
import nc.ui.pubapp.uif2app.actions.SaveAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.so.depmatrel.rule.BillDataValidateRule;
import nc.ui.so.depmatrel.view.CardForm;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.HierachicalDataAppModel;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.so.depmatrel.entity.DepMatRelVO;
import nc.vo.trade.checkrule.VOChecker;

@SuppressWarnings("serial")
public class DepMatRelSaveAction extends SaveAction {
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
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006007_0", "04006007-0001")/*@res "必须选择主组织。"*/);
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
    DepMatRelVO bill =
        new ClientBillToServer<DepMatRelVO>()
            .constructInsert(new DepMatRelVO[] {
              (DepMatRelVO) value
            })[0];
    BillDataValidateRule check = new BillDataValidateRule();
    check.validate(bill);
    DepMatRelVO retvo = null;
    try {
      IDepMatRelMaintain service =
          NCLocator.getInstance().lookup(IDepMatRelMaintain.class);
      retvo = service.insert(bill);
      // 后台变化VO与前台合并
      ClientBillCombinServer<DepMatRelVO> util =
          new ClientBillCombinServer<DepMatRelVO>();
      util.combine(new DepMatRelVO[] {
        (DepMatRelVO) value
      }, new DepMatRelVO[] {
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
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006007_0", "04006007-0002")/*@res "修改保存时，获取前台差异VO出错。"*/);
    }

    DepMatRelVO data =
        (DepMatRelVO) ((BillManageModel) this.getModel()).getData().get(index);
    // data为当前model缓存中的数据，value为当前界面上的数据
    DepMatRelVO bill =
        new ClientBillToServer<DepMatRelVO>().construct(new DepMatRelVO[] {
          data
        }, new DepMatRelVO[] {
          (DepMatRelVO) value
        })[0];
    BillDataValidateRule check = new BillDataValidateRule();
    check.validate(data);
    DepMatRelVO ret = null;
    IDepMatRelMaintain service =
        NCLocator.getInstance().lookup(IDepMatRelMaintain.class);
    try {
      ret = service.update(bill);
      // 后台变化VO与前台合并
      ClientBillCombinServer<DepMatRelVO> util =
          new ClientBillCombinServer<DepMatRelVO>();
      util.combine(new DepMatRelVO[] {
        (DepMatRelVO) value
      }, new DepMatRelVO[] {
        ret
      });
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    this.getModel().directlyUpdate(value);
  }

}
