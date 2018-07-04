package nc.ui.so.m30.billui.action;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.style.Style;
import nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare;
import nc.ui.scmf.ic.onhand.OnhandPanelAdaptor;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.TangramContainer;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * 现存量面板显示与隐藏动作
 * 
 * @author wangfengd
 * @time 2010-4-21 下午03:59:40
 */

public class SPShowHidAction extends NCAction {

  private static final long serialVersionUID = -2099832720860500161L;

  private SaleOrderBillForm card;

  private TangramContainer contain;

  private OnhandPanelAdaptor adaptor;

  private CompositeBillDataPrepare userdefitemPreparator;

  public CompositeBillDataPrepare getUserdefitemPreparator() {
    return this.userdefitemPreparator;
  }

  public void setUserdefitemPreparator(
      CompositeBillDataPrepare userdefitemPreparator) {
    this.userdefitemPreparator = userdefitemPreparator;
  }

  public nc.vo.uif2.LoginContext getLogincontext() {
    return this.logincontext;
  }

  public void setLogincontext(nc.vo.uif2.LoginContext logincontext) {
    this.logincontext = logincontext;
  }

  private nc.vo.uif2.LoginContext logincontext;

  public SPShowHidAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_ONHANDINFO);
  }

  /**
   * 展示/隐藏动作
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {

    if (this.card.getExtendedPanel() == null) {
      if (!SysInitGroupQuery.isICEnabled()) {
        return;
      }
      OnhandPanelAdaptor adaptor =
          new OnhandPanelAdaptor("nc.ui.ic.onhand.pane.QueryOnHandInfoPanel");
      if (this.adaptor == null) {

        UIPanel refpanel = adaptor.getRefPane();
        this.adaptor = adaptor;
        adaptor.setOnhandPanelSrc(this.card);
        adaptor.setUserdefitemPreparator(this.userdefitemPreparator);
        adaptor.setLogincontext(this.logincontext);
        adaptor.initialize();
        // 显示控制
        refpanel.setPreferredSize(new Dimension(300, 180));
        refpanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0,
            Style.getColor("系统按钮条.背景"))); /*-=notranslate=-*/
        this.card.add(refpanel, BorderLayout.SOUTH);
        this.card.setExtendedPanel(adaptor);
      }
    }
    else {
      this.adaptor = this.card.getExtendedPanel();
    }

    UIPanel refpanel = this.adaptor.getRefPane();
    if (this.adaptor.isComponentDisplayable()) {
      refpanel.setVisible(false);
      this.adaptor.setIsComponentDisplayable(false);
    }
    else {
      refpanel.setVisible(true);
      this.adaptor.setIsComponentDisplayable(true);

    }
    this.contain.resetLayout();
    this.card.showMeUp();
    int selectRow =
        this.card.getBillCardPanel().getBillTable().getSelectedRow();
    if (selectRow == -1) {
      this.adaptor.clearData();
    }
    else {
      this.adaptor.freshData(selectRow);
    }

  }

  public SaleOrderBillForm getCard() {
    return this.card;
  }

  public nc.ui.uif2.TangramContainer getContain() {
    return this.contain;
  }

  public void setCard(SaleOrderBillForm card) {
    this.card = card;
  }

  public void setContain(nc.ui.uif2.TangramContainer contain) {
    this.contain = contain;
  }

}
