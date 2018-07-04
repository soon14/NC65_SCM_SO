package nc.ui.so.mbuylargess.action;

import java.awt.event.ActionEvent;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;
import nc.vo.trade.checkrule.VOChecker;

import nc.itf.so.mbuylagress.IBuyLargessMaintain;

import nc.bs.framework.common.NCLocator;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.so.mbuylargess.view.BuyLargessEditor;
import nc.ui.uif2.actions.SaveAction;

/**
 * 
 * @since 6.3
 * @version 2013-02-21 14:24:47
 * @author 梁吉明
 */
public class BuylargessSaveAction extends SaveAction {

  private static final long serialVersionUID = 7141467834250734134L;

  private BuyLargessEditor view;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    StringBuffer errMsg = new StringBuffer();
    this.view.getBillCardPanel().stopEditing();
    if (!this.getView().validateValue()) {
      return;
    }
    else if (VOChecker.isEmpty(this.getModel().getContext().getPk_org())) {
      errMsg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006003_0", "04006003-0000")/*@res "必须选择主组织"*/);
    }
    BuyLargessVO value = (BuyLargessVO) this.getView().getValue();
    if (value.getChildrenVO().length == 0) {
      errMsg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006003_0", "04006003-0001")/*@res "表体行不可为空。"*/);
    }
    if (errMsg.length() > 0) {
      // fengjb 2012.03.05 UE提示规范
      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
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

  /**
   * 
   * 
   * @return view
   */
  public BuyLargessEditor getView() {
    return this.view;
  }

  /**
   * 
   * 
   * @param view
   */
  public void setView(BuyLargessEditor view) {
    this.view = view;
  }

  private void insertBill(BillManageModel model, BuyLargessVO value) {
    BuyLargessVO bill =
        new ClientBillToServer<BuyLargessVO>()
            .constructInsert(new BuyLargessVO[] {
              value
            })[0];

    BuyLargessVO ret = null;
    IBuyLargessMaintain service =
        NCLocator.getInstance().lookup(IBuyLargessMaintain.class);
    try {
      ret = service.insertBuylargess(bill);
      // 后台变化VO与前台合并
      ClientBillCombinServer<BuyLargessVO> util =
          new ClientBillCombinServer<BuyLargessVO>();
      util.combine(new BuyLargessVO[] {
        value
      }, new BuyLargessVO[] {
        ret
      });
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    model.directlyAdd(value);
  }

  private void updateBill(BillManageModel model, BuyLargessVO value) {
    int index = model.findBusinessData(value);
    if (index == -1) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006003_0", "04006003-0002")/*@res "修改保存时，获取前台差异VO出错。"*/);
    }

    BuyLargessVO data = (BuyLargessVO) model.getData().get(index);
    // data为当前model缓存中的数据，value为当前界面上的数据
    BuyLargessVO bill =
        new ClientBillToServer<BuyLargessVO>().construct(new BuyLargessVO[] {
          data
        }, new BuyLargessVO[] {
          value
        })[0];

    BuyLargessVO ret = null;
    IBuyLargessMaintain service =
        NCLocator.getInstance().lookup(IBuyLargessMaintain.class);
    try {
      ret = service.updateBuylargess(bill);
      // 后台变化VO与前台合并
      ClientBillCombinServer<BuyLargessVO> util =
          new ClientBillCombinServer<BuyLargessVO>();
      util.combine(new BuyLargessVO[] {
        value
      }, new BuyLargessVO[] {
        ret
      });
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    model.directlyUpdate(value);
  }
}
