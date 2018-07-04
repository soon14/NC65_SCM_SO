package nc.ui.so.m4331.arrange.action;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import nc.pubitf.ic.onhand.IOnhandPanelFacade;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.style.Style;
import nc.ui.pubapp.billref.push.BillContext;
import nc.ui.pubapp.billref.push.IBillPush;
import nc.ui.pubapp.billref.push.TabBillInfo;
import nc.ui.pubapp.billref.src.view.RefListPanel;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.pubapp.util.RefListPanelValueUtils;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.uif2.LoginContext;

public class ATPShowHiddenAction extends NCAction implements IBillPush {
  private static final long serialVersionUID = 80830283833718816L;

  private BillContext context;

  // 现存量显示组件
  private IOnhandPanelFacade onhandFacade;

  // 是否显示了
  private boolean show;

  // 卡片界面
  private TabBillInfo tabBillInfo;

  private LoginContext logincontext;

  public ATPShowHiddenAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_ONHANDINFO);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {

    if (null == this.getOnhandFacade()) {
      return;
    }
    // 显示控制
    this.getOnhandFacade().getOnhandInfoPanel()
        .setPreferredSize(new Dimension(300, 180));
    this.getOnhandFacade()
        .getOnhandInfoPanel()
        .setBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0,
                Style.getColor("系统按钮条.背景")));/* -=notranslate=- */

    this.show = !this.show;
    this.getBillContext()
        .getBillTabPanel()
        .showExtendedPanel(
            (UIPanel) this.getOnhandFacade().getOnhandInfoPanel(), this.show);

    if (this.show) {
      // 如果没有选中数据 要清空
      int selrow = this.context.getListPanel().getBodyTable().getSelectedRow();
      if (selrow < 0
          || selrow > this.context.getListPanel().getBodyTable().getRowCount()) {
        this.clearData();
      }
      else {
        // 获取现存量维度vo
        OnhandDimVO dimVo = this.getDimVo(selrow);
        // 刷新
        this.queryOnhand(this.getOnhandFacade().getOnhandInfoPanel(), dimVo);
      }
    }

  }

  public LoginContext getLogincontext() {
    return this.logincontext;
  }

  public void setLogincontext(LoginContext logincontext) {
    this.logincontext = logincontext;
  }

  private void queryOnhand(JPanel panel, OnhandDimVO dimvo) throws Exception {
    Method setContextMethod =
        panel.getClass().getMethod("queryOnhand", OnhandDimVO.class);
    setContextMethod.invoke(panel, dimvo);
  }

  private void clearData() throws NoSuchMethodException,
      IllegalAccessException, InvocationTargetException {
    Method clearMethod =
        this.getOnhandFacade().getOnhandInfoPanel().getClass()
            .getMethod("clearData");
    clearMethod.invoke(this.getOnhandFacade().getOnhandInfoPanel());
  }

  private OnhandDimVO getDimVo(int row) {
    RefListPanel reflist = this.context.getListPanel();
    RefListPanelValueUtils valueUtil = new RefListPanelValueUtils(reflist);
    OnhandDimVO paraVO = new OnhandDimVO();
    paraVO.setPk_group(AppContext.getInstance().getPkGroup());
    paraVO.setCastunitid(valueUtil
        .getStringValueAt(row, DeliveryBVO.CASTUNITID));
    paraVO
        .setClocationid(valueUtil.getStringValueAt(row, DeliveryBVO.CSPACEID));

    paraVO.setCmaterialoid(valueUtil.getStringValueAt(row,
        DeliveryBVO.CMATERIALID));
    paraVO.setCmaterialvid(valueUtil.getStringValueAt(row,
        DeliveryBVO.CMATERIALVID));
    paraVO.setCproductorid(valueUtil.getStringValueAt(row,
        DeliveryBVO.CPRODUCTORID));
    paraVO.setCprojectid(valueUtil
        .getStringValueAt(row, DeliveryBVO.CPROJECTID));
    paraVO.setCvendorid(valueUtil.getStringValueAt(row, DeliveryBVO.CVENDORID));
    paraVO.setCwarehouseid(valueUtil.getStringValueAt(row,
        DeliveryBVO.CSENDSTORDOCID));
    paraVO.setPk_batchcode(valueUtil.getStringValueAt(row,
        DeliveryBVO.PK_BATCHCODE));
    paraVO.setVbatchcode(valueUtil
        .getStringValueAt(row, DeliveryBVO.VBATCHCODE));
    paraVO.setPk_org(valueUtil.getStringValueAt(row,
        DeliveryBVO.CSENDSTOCKORGID));
    // paraVO.setPk_org_v(valueUtil.getStringValueAt(row,
    // DeliveryBVO.CSENDSTOCKORGVID));
    paraVO.setVbatchcode(valueUtil
        .getStringValueAt(row, DeliveryBVO.VBATCHCODE));
    paraVO.setVchangerate(valueUtil.getStringValueAt(row,
        DeliveryBVO.VCHANGERATE));
    paraVO.setVfree1(valueUtil.getStringValueAt(row, DeliveryBVO.VFREE1));
    paraVO.setVfree2(valueUtil.getStringValueAt(row, DeliveryBVO.VFREE2));
    paraVO.setVfree3(valueUtil.getStringValueAt(row, DeliveryBVO.VFREE3));
    paraVO.setVfree4(valueUtil.getStringValueAt(row, DeliveryBVO.VFREE4));
    paraVO.setVfree5(valueUtil.getStringValueAt(row, DeliveryBVO.VFREE5));
    paraVO.setVfree6(valueUtil.getStringValueAt(row, DeliveryBVO.VFREE6));
    paraVO.setVfree7(valueUtil.getStringValueAt(row, DeliveryBVO.VFREE7));
    paraVO.setVfree8(valueUtil.getStringValueAt(row, DeliveryBVO.VFREE8));
    paraVO.setVfree9(valueUtil.getStringValueAt(row, DeliveryBVO.VFREE9));
    paraVO.setVfree10(valueUtil.getStringValueAt(row, DeliveryBVO.VFREE10));
    return paraVO;
  }

  @Override
  public BillContext getBillContext() {
    return this.context;
  }

  public IOnhandPanelFacade getOnhandFacade() {
    if (this.onhandFacade == null) {
      this.onhandFacade =
          NCUILocator.getInstance().lookup(IOnhandPanelFacade.class,
              NCModule.IC.getName());
      try {
        Method setContextMethod =
            this.onhandFacade.getOnhandInfoPanel().getClass()
                .getMethod("setLogincontext", new Class[] {
                  this.getLogincontext().getClass()
                });
        setContextMethod.invoke(this.onhandFacade.getOnhandInfoPanel(),
            this.getLogincontext());
        this.onhandFacade.freshData(null);
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
