package nc.ui.so.m30.billui.action;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.bs.framework.common.NCLocator;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.so.m30.revise.IM30ReviseMaintain;
import nc.itf.uap.bbd.func.IFuncRegisterQueryService;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.billref.push.BillPushConst;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.NCAction;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.m30.entity.SaleOrderVO;

public class M30ReviseHistoryAction extends NCAction {
  public static final String HISTORY_FUNCODE = "40060302H";

  private static final long serialVersionUID = -1040049186179282394L;

  private BillForm editor;

  private BillManageModel model;

  public M30ReviseHistoryAction() {
    super();
    this.setBtnName(NCLangRes.getInstance().getStrByID("4006011_0",
        "04006011-0298")/*修订历史*/);
    this.setCode("ReviseHistory");
    this.putValue(Action.SHORT_DESCRIPTION,
        NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0298")/*修订历史*/);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    try {
      SaleOrderVO ordvo = (SaleOrderVO) this.getModel().getSelectedData();
      if (ordvo == null) {
        ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0013")/* @res "请选中销售订单" */);
      }
      IM30ReviseMaintain service =
          NCLocator.getInstance().lookup(IM30ReviseMaintain.class);
      SaleOrderVO[] hisVOs =
          service.queryReviseHistory(ordvo.getParentVO().getCsaleorderid());

      FuncletInitData initData = new FuncletInitData();
      initData.setInitType(BillPushConst.BILL_PUSH);
      initData.setInitData(hisVOs);
      // 修订历史节点
      IFuncRegisterQueryService funQuery =
          NCLocator.getInstance().lookup(IFuncRegisterQueryService.class);
      FuncRegisterVO funvo =
          funQuery.queryFunctionByCode(M30ReviseHistoryAction.HISTORY_FUNCODE);
      // 打开节点
      int screenWidth =
          Toolkit.getDefaultToolkit().getScreenSize().width;
      int screenHeight =
          Toolkit.getDefaultToolkit().getScreenSize().height -1;
      FuncletWindowLauncher.openFuncNodeDialog(this.getEditor(), funvo,
          initData, null, false, false, new Dimension(screenWidth, screenHeight), true);
    }
    catch (BusinessException be) {
      ExceptionUtils.wrappException(be);
    }
  }

  public BillForm getEditor() {
    return this.editor;
  }

  public void setEditor(BillForm editor) {
    this.editor = editor;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }

}
