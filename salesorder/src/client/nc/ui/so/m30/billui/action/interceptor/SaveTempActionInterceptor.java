package nc.ui.so.m30.billui.action.interceptor;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.ml.NCLangRes;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.actions.ActionInterceptor;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * @description
 *
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015年8月26日 下午4:23:11
 * @author wangweir
 */

public class SaveTempActionInterceptor implements ActionInterceptor {

  private SaleOrderBillForm billFormEditor;

  private String errormsg = NCLangRes.getInstance().getStrByID("uif2",
      "DefaultExceptionHanler-000005")/* 操作失败! */;

  @Override
  public boolean beforeDoAction(Action action, ActionEvent e) {
    SaleOrderVO saleOrder = (SaleOrderVO) billFormEditor.getValue();

    boolean pass = this.checkSouce(saleOrder);
    if (!pass) {
      ShowStatusBarMsgUtil.showErrorMsg(errormsg,
          NCLangRes.getInstance().getStrByID("4006011_0",
              "04006011-0530")/*单据存在来源，不能支持暂存！*/,
          this.getBillFormEditor().getModel().getContext());
      return false;
    }

    boolean passOffSetCheck = this.checkOffsetFlag(saleOrder);
    boolean passReceiveMnyCheck = this.checkNreceivedmny(saleOrder);

    if (!passOffSetCheck || !passReceiveMnyCheck) {
      ShowStatusBarMsgUtil.showErrorMsg(errormsg,
          NCLangRes.getInstance().getStrByID("4006011_0",
              "04006011-0531")/*销售订单已经做过费用冲抵或者收款核销，不能支持暂存！*/,
          this.getBillFormEditor().getModel().getContext());
      return false;
    }
    
    if(saleOrder.getParentVO().getNthisreceivemny()!=null){
      ShowStatusBarMsgUtil.showErrorMsg(errormsg,
          NCLangRes.getInstance().getStrByID("4006011_0",
              "04006011-0535")/*销售订单存在收款金额，不能支持暂存！*/,
          this.getBillFormEditor().getModel().getContext());
      
      return false;
    }
    
    IKeyValue keyValue =
        new CardKeyValue(this.getBillFormEditor().getBillCardPanel());
    int rowcount = keyValue.getBodyCount();
    for (int i = 0; i < rowcount; i++) {
      if (keyValue.getBodyValue(i, SOConstant.AGGFFILEVO) != null) {
        ShowStatusBarMsgUtil.showErrorMsg(errormsg, NCLangRes.getInstance()
            .getStrByID("4006011_0", "04006011-0536")/*销售订单做过特征选配，不能支持暂存！*/,
            this.getBillFormEditor().getModel().getContext());
        return false;
      }
    }
    return true;
  }

  /**
   * 检查收款核销
   * 
   * @param saleOrder
   * @return
   */
  private boolean checkNreceivedmny(SaleOrderVO saleOrder) {
    if (saleOrder == null || saleOrder.getParent() == null) {
      return true;
    }
    UFDouble nreceivedmny = saleOrder.getParentVO().getNreceivedmny();
    if (nreceivedmny != null
        && nreceivedmny.compareTo(UFDouble.ZERO_DBL) != 0) {
      return false;
    }
    return true;
  }

  private boolean checkOffsetFlag(SaleOrderVO saleOrder) {
    if (saleOrder == null || saleOrder.getParent() == null) {
      return true;
    }
    UFBoolean flag = saleOrder.getParentVO().getBoffsetflag();

    if (flag != null && flag.booleanValue()) {
      return false;
    }
    return true;
  }

  private boolean checkSouce(SaleOrderVO saleOrder) {
    if (saleOrder == null || saleOrder.getChildrenVO() == null) {
      return true;
    }
    for (SaleOrderBVO bodyVO : saleOrder.getChildrenVO()) {
      String srcid = bodyVO.getCsrcid();
      if (srcid != null) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void afterDoActionSuccessed(Action action, ActionEvent e) {
  }

  @Override
  public boolean afterDoActionFailed(Action action, ActionEvent e,
      Throwable ex) {
    return true;
  }

  /**
   * @return the billFormEditor
   */
  public SaleOrderBillForm getBillFormEditor() {
    return this.billFormEditor;
  }

  /**
   * @param billFormEditor the billFormEditor to set
   */
  public void setBillFormEditor(SaleOrderBillForm billFormEditor) {
    this.billFormEditor = billFormEditor;
  }

}
