package nc.ui.so.m30.arrange.action;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.lang.reflect.Method;

import javax.swing.BorderFactory;

import nc.pubitf.ic.onhand.IOnhandPanelFacade;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.style.Style;
import nc.ui.pubapp.billref.push.BillContext;
import nc.ui.pubapp.billref.push.IBillPush;
import nc.ui.pubapp.billref.push.TabBillInfo;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.uif2.LoginContext;

@SuppressWarnings("restriction")
public class SaleorderATPShowHiddenAction extends NCAction implements IBillPush {
  private static final long serialVersionUID = 80830283833718816L;

  private BillContext context;

  // 现存量显示组件
  private IOnhandPanelFacade onhandFacade;

  // 是否显示了
  private boolean show;

  // 卡片界面
  private TabBillInfo tabBillInfo;

  private LoginContext logincontext;

  public SaleorderATPShowHiddenAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_ONHANDINFO);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (this.getOnhandFacade() != null) {
      // 显示控制
      this.getOnhandFacade().getOnhandInfoPanel()
          .setPreferredSize(new Dimension(300, 180));
      this.getOnhandFacade()
          .getOnhandInfoPanel()
          .setBorder(
              BorderFactory.createMatteBorder(1, 0, 0, 0,
                  Style.getColor("系统按钮条.背景")));/* -=notranslate=- */
      // 如果没有选中数据 要清空
      this.tabBillInfo.setViewShow(true);
      if (this.tabBillInfo.getBillTabPanel() != null) {
        if (this.tabBillInfo.getBillTabPanel().getBodySelectVOs() == null
            || this.tabBillInfo.getBillTabPanel().getBodySelectVOs().length == 0) {
          Method clearMethod =
              this.getOnhandFacade().getOnhandInfoPanel().getClass()
                  .getMethod("clearData");
          clearMethod.invoke(this.onhandFacade.getOnhandInfoPanel());
        }
      }
      this.show = !this.show;
      this.getBillContext()
          .getBillTabPanel()
          .showExtendedPanel(
              (UIPanel) this.getOnhandFacade().getOnhandInfoPanel(), this.show);
      // 重新设置viewshow为false，否则重新查询报错
      this.tabBillInfo.setViewShow(false);
    }
  }

  @Override
  public BillContext getBillContext() {
    return this.context;
  }

  public LoginContext getLogincontext() {
    return this.logincontext;
  }

  public void setLogincontext(LoginContext logincontext) {
    this.logincontext = logincontext;
  }

  /**
   * 联查现存量门面。
   * 
   * @return
   * @author liutao
   * @throws BusinessException
   * @time 2011-1-10 下午04:57:16
   */
  public IOnhandPanelFacade getOnhandFacade() throws BusinessException {
    // 如果模块没有启用
    if (this.onhandFacade == null) {
      this.onhandFacade =
          NCUILocator.getInstance().lookup(IOnhandPanelFacade.class,
              NCModule.IC.getName());
      this.onhandFacade.freshData(null);
      try {
        Method setContextMethod =
            this.onhandFacade.getOnhandInfoPanel().getClass()
                .getMethod("setLogincontext", new Class[] {
                  this.getLogincontext().getClass()
                });
        setContextMethod.invoke(this.onhandFacade.getOnhandInfoPanel(),
            this.getLogincontext());
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
    }
    return this.onhandFacade;
  }

  public TabBillInfo getTabBillInfo() {
    return this.tabBillInfo;
  }

  @Override
  public void setBillContext(BillContext billcontext) {
    this.context = billcontext;
  }

  public void setTabBillInfo(TabBillInfo table) {
    this.tabBillInfo = table;
  }
}
