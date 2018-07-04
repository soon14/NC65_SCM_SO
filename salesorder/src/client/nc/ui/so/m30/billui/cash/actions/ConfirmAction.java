package nc.ui.so.m30.billui.cash.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.AbstractFunclet;
import nc.funcnode.ui.IFuncletWindow;
import nc.ui.so.m30.billui.cash.model.CashSaleArsubModel;
import nc.ui.so.m30.billui.cash.model.CashSaleSobalanceModel;
import nc.ui.so.m30.billui.cash.view.CashSaleArsubView;
import nc.ui.so.m30.billui.cash.view.CashSaleSobalanceView;
import nc.ui.so.m30.billui.cash.view.CashSaleTopPanel;
import nc.ui.uif2.NCAction;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderUserObject;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceViewVO;
import nc.vo.so.m35.entity.ArsubViewVO;

/**
 * 现销处理界面《确认》按钮
 * 
 * @since 6.0
 * @version 2011-4-26 下午01:40:00
 * @author 刘志伟
 */
public class ConfirmAction extends NCAction {

  private static final long serialVersionUID = -3634508087293775886L;

  private CashSaleArsubView arsublistView;

  private CashSaleArsubModel arsubmodel;

  private CashSaleSobalanceView sobalanceListView;

  private CashSaleSobalanceModel sobalancemodel;

  private CashSaleTopPanel toppanel;

  /**
   * 把现销处理界面费用冲抵、收款核销、本次收款金额最终结果传递给现销处理按钮监听
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    SaleOrderUserObject userObject = new SaleOrderUserObject();
    // 1.费用冲抵
    ArsubViewVO[] arsubViews =
        (ArsubViewVO[]) this.getArsublistView().getBillCardPanel()
            .getBillModel().getBodyValueVOs(ArsubViewVO.class.getName());
    userObject.setArsubViews(arsubViews);

    // 2.收款核销
    SoBalanceViewVO[] soBalanceViews =
        (SoBalanceViewVO[]) this.getSobalanceListView().getBillCardPanel()
            .getBillModel().getBodyValueVOs(SoBalanceViewVO.class.getName());

    // 补充数据
    for (SoBalanceViewVO view : soBalanceViews) {
      view.getHead().setPk_org(view.getBody().getPk_org());
    }

    if (soBalanceViews.length > 0) {
      List<SoBalanceBVO> bodyvos = new ArrayList<SoBalanceBVO>();
      SoBalanceHVO headvo =
          (SoBalanceHVO) soBalanceViews[0].getVO(SoBalanceHVO.class);
      for (SoBalanceViewVO viewvo : soBalanceViews) {
        SoBalanceBVO bodyvo = (SoBalanceBVO) viewvo.getVO(SoBalanceBVO.class);
        // 过滤掉本次核销金额为0的
        if (MathTool.absCompareTo(bodyvo.getNorigthisbalmny(),
            UFDouble.ZERO_DBL) > 0) {
          bodyvos.add(bodyvo);
        }
      }
      SoBalanceVO soBalanceVO = new SoBalanceVO();
      soBalanceVO.setParentVO(headvo);
      soBalanceVO.setChildrenVO(bodyvos.toArray(new SoBalanceBVO[0]));
      userObject.setSoBalanceVO(soBalanceVO);
    }
    // 3.本次收款金额
    userObject.setThisGatheringMny((UFDouble) this.getToppanel()
        .getThisreceivemnyTextField().getValue());

    // 现销处理按钮监听
    ((AbstractFunclet) this.getSobalancemodel().getContext().getEntranceUI())
        .fireFuncletLinkEvent(userObject, 0);

    IFuncletWindow window =
        WorkbenchEnvironment.getInstance().findOpenedFuncletWindow("40060399");
    window.closeWindow();
  }

  public CashSaleArsubView getArsublistView() {
    return this.arsublistView;
  }

  public CashSaleArsubModel getArsubmodel() {
    return this.arsubmodel;
  }

  public CashSaleSobalanceView getSobalanceListView() {
    return this.sobalanceListView;
  }

  public CashSaleSobalanceModel getSobalancemodel() {
    return this.sobalancemodel;
  }

  public CashSaleTopPanel getToppanel() {
    return this.toppanel;
  }

  public void setArsublistView(CashSaleArsubView arsublistView) {
    this.arsublistView = arsublistView;
  }

  public void setArsubmodel(CashSaleArsubModel arsubmodel) {
    this.arsubmodel = arsubmodel;
  }

  public void setSobalanceListView(CashSaleSobalanceView sobalanceListView) {
    this.sobalanceListView = sobalanceListView;
  }

  public void setSobalancemodel(CashSaleSobalanceModel sobalancemodel) {
    this.sobalancemodel = sobalancemodel;
  }

  public void setToppanel(CashSaleTopPanel toppanel) {
    this.toppanel = toppanel;
  }

  @Override
  protected boolean isActionEnable() {
    return super.isActionEnable();
  }

}
