package nc.ui.so.m30.billui.action;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.uap.bbd.func.IFuncRegisterQueryService;
import nc.sfbase.client.ClientToolKit;
import nc.ui.pubapp.billref.push.BillPushConst;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.uif2.NCAction;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.m30.arrange.util.M30ArrangeUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 补货安排 按钮
 * 
 * @version 6.0
 * @since 6.0
 * @author 刘志伟
 * @time 2010-9-15 上午09:37:39
 */
public class SaleOrderBHArrangeAction extends NCAction {

  /**
   * 
   */
  private static final long serialVersionUID = 3440538693982415152L;

  private SaleOrderBillForm editor;

  private BillManageModel model;

  public SaleOrderBHArrangeAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_BHARRANGE);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    Object[] objects = this.model.getSelectedOperaDatas();

    // 过滤可以安排的
    SaleOrderVO[] newBills = new M30ArrangeUtil().filterSrcVOs(objects);

    // 初始数据
    FuncletInitData initData = new FuncletInitData();
    initData.setInitData(newBills);
    initData.setInitType(BillPushConst.BILL_PUSH);
    // 打开发货安排节点
    IFuncRegisterQueryService funcService =
        NCLocator.getInstance().lookup(IFuncRegisterQueryService.class);
    FuncRegisterVO funvo = funcService.queryFunctionByCode("40060403");
    int screenWidth =
        Toolkit.getDefaultToolkit().getScreenSize().width;
    int screenHeight =
        Toolkit.getDefaultToolkit().getScreenSize().height -1;
    FuncletWindowLauncher.openFuncNodeForceModalDialog(this.getModel().getContext()
        .getEntranceUI(), funvo, initData, null, true, new Dimension(
        screenWidth, screenHeight),true);
  }

  public SaleOrderBillForm getEditor() {
    return this.editor;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setEditor(SaleOrderBillForm editor) {
    this.editor = editor;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    Object[] objects = this.model.getSelectedOperaDatas();
    if (objects == null) {
      return false;
    }
    // 对于列表多选按钮可用(由推单补货安排界面监听过滤可安排行数据)
    if (objects.length > 1) {
      return true;
    }

    // 只要一条满足按钮可用：已审批、未安排关闭(由推单补货安排界面监听过滤可安排行数据)
    boolean isBHArrange = false;
    for (Object object : objects) {
      SaleOrderVO vo = (SaleOrderVO) object;
      if (BillStatus.AUDIT.equalsValue(vo.getParentVO().getFstatusflag())) {
        SaleOrderBVO[] bodys = vo.getChildrenVO();
        if (bodys != null) {
          for (SaleOrderBVO body : bodys) {
            if (!body.getBarrangedflag().booleanValue()) {
              isBHArrange = true;
              break;
            }
          }
        }
      }
    }
    return isBHArrange;
  }

}
