package nc.ui.so.m30.billui.action;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.pubapp.pub.exception.IResumeException;
import nc.itf.so.m30.sobalance.ISOBalanceQuery;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction;
import nc.ui.scmpub.util.ResumeExceptionUIProcessUtils;
import nc.ui.uif2.UIState;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.util.SpecialBusiUtil;
import nc.vo.so.pub.enumeration.BillStatus;

public class SaleOrderDeleteAction extends DeleteScriptAction {

  private static final long serialVersionUID = -6058400143066110207L;

  public SaleOrderDeleteAction() {
    super();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    Object[] objects = this.getModel().getSelectedOperaDatas();
    if (objects == null || objects.length == 0) {
      return;
    }
    List<String> idList = new ArrayList<String>();

    for (Object obj : objects) {
      SaleOrderVO bill = (SaleOrderVO) obj;
      idList.add(bill.getParentVO().getCsaleorderid());
    }
    ISOBalanceQuery service =
        NCLocator.getInstance().lookup(ISOBalanceQuery.class);
    SoBalanceVO[] balanceVOs =
        service.querySoBalanceVOBySaleOrderIDs(idList.toArray(new String[idList
            .size()]));

    if (balanceVOs != null && balanceVOs.length > 0) {
      StringBuilder sb = new StringBuilder();
      sb.append(NCLangRes.getInstance()
          .getStrByID("4006011_0", "04006011-0283")/*单据号：*/);
      for (SoBalanceVO balanceVO : balanceVOs) {
        sb.append(balanceVO.getParentVO().getVbillcode());
        sb.append(NCLangRes.getInstance().getStrByID("4006011_0",
            "04006011-0284")/*、*/);
      }
      sb.deleteCharAt(sb.length() - 1);
      String sTITLE =
          NCLangRes.getInstance().getStrByID("uif2",
              "CommonConfirmDialogUtils-000002")/*确认删除*/;
      String sQUESTION =
          NCLangRes.getInstance().getStrByID("uif2",
              "CommonConfirmDialogUtils-000003")/*您确定要删除所选数据吗?*/;
      sb.append("\n");
      sb.append(NCLangRes.getInstance()
          .getStrByID("4006011_0", "04006011-0285")/*已订单收款,删除订单将取消订单核销关系！*/);
      sb.append("\n");
      sb.append(sQUESTION);
      Container parent = this.getModel().getContext().getEntranceUI();
      if (UIDialog.ID_YES == MessageDialog.showYesNoDlg(parent, sTITLE,
          sb.toString(), UIDialog.ID_NO)) {
        super.doAction(e);
      }
    }
    else {
      super.doAction(e);
    }
  }

  @Override
  protected boolean isActionEnable() {

    if (this.model.getSelectedOperaRows().length > 1) {
      // 多选，按钮长亮，不符合条件的给出提示
      return true;
    }

    boolean isEnable =
        this.model.getUiState() == UIState.NOT_EDIT
            && this.getModel().getSelectedData() != null;
    if (isEnable) {
      SaleOrderVO vo = (SaleOrderVO) this.getModel().getSelectedData();
      // 自由态的可以删除
      Integer fstatusflag = vo.getParentVO().getFstatusflag();
      if (!BillStatus.FREE.equalsValue(fstatusflag)) {
        isEnable = false;
      }
    }
    
    //return isEnable;
    
    SpecialBusiUtil busiUtil = new SpecialBusiUtil();
	SaleOrderVO bill = (SaleOrderVO) this.getModel().getSelectedData();
	boolean hasLowerBill = false;
	if(bill != null){
		hasLowerBill = busiUtil.hasLowerBill(bill.getPrimaryKey());
	}
	
	if(!isEnable || hasLowerBill){
		  return false;
	}
	  
	return true;
    
  }

  @Override
  protected boolean isResume(IResumeException resumeInfo) {
    return ResumeExceptionUIProcessUtils.isResume(resumeInfo, getFlowContext());
  }
}
