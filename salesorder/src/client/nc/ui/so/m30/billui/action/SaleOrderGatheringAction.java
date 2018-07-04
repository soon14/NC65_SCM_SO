/**
 * $文件说明$
 * 
 * @author gdsjw
 * @version
 * @see
 * @since
 * @time 2010-6-30 下午03:50:51
 */
package nc.ui.so.m30.billui.action;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.so.m30.self.ISaleOrderBusi;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.linkoperate.ILinkType;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.funclink.SaleOrderGatheringLinkListener;
import nc.ui.uif2.NCAction;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * 
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author gdsjw
 * @time 2010-6-30 下午03:50:51
 */
@SuppressWarnings("serial")
public class SaleOrderGatheringAction extends NCAction {

  private BillForm editor;

  private BillManageModel model;

  public SaleOrderGatheringAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_ORDERPAY);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!SysInitGroupQuery.isAREnabled()) {
      ExceptionUtils
      .wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0518")/*财务模块未启用！*/);
    }
    try {
      SaleOrderVO ordvo = (SaleOrderVO) this.model.getSelectedData();
      ISaleOrderBusi service =
          NCLocator.getInstance().lookup(ISaleOrderBusi.class);
      AggregatedValueObject[] destVos = service.prepareOrderGathering(ordvo);

      FuncletInitData initData = new FuncletInitData();
      initData.setInitType(ILinkType.LINK_TYPE_ADD);
      initData.setInitData(destVos);

      // 收款单节点号
      FuncRegisterVO funvo =
          WorkbenchEnvironment.getInstance().getFuncRegisterVO("20060GBR");
      if (null == funvo) {
        ExceptionUtils
            .wrappBusinessException(NCLangRes.getInstance().getStrByID(
                "4006011_0", "04006011-0448")/*当前用户没有打开订单收款节点的权限，请检查*/);
      }
      SaleOrderGatheringLinkListener linkListener =
          new SaleOrderGatheringLinkListener();
      linkListener.setModel(this.getModel());
      
      int screenWidth =
          Toolkit.getDefaultToolkit().getScreenSize().width;
      int screenHeight =
          Toolkit.getDefaultToolkit().getScreenSize().height -1;
      FuncletWindowLauncher.openFuncNodeForceModalDialog(this.getModel().getContext()
          .getEntranceUI(), funvo, initData, null, true, new Dimension(
          screenWidth, screenHeight),true);
    }
    catch (BusinessException be) {
      ExceptionUtils.wrappException(be);
    }
  }

  public BillForm getEditor() {
    return this.editor;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setEditor(BillForm editor) {
    this.editor = editor;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    Object selectedData = this.getModel().getSelectedData();
    boolean isEnable =
        this.model.getAppUiState() == AppUiState.NOT_EDIT
            && selectedData != null;

    return isEnable;
  }
}
