package nc.ui.so.m30.billui.action;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.itf.uap.bbd.func.IFuncRegisterQueryService;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.billref.push.BillPushConst;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.uif2.NCAction;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.m30.arrange.util.M30ArrangeUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.DirectType;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 直运安排 按钮
 *
 * @version 6.0
 * @since 6.0
 * @author 刘志伟
 * @time 2010-9-15 上午09:37:39
 */
public class SaleOrderZYArrangeAction extends NCAction {

  /**
   *
   */
  private static final long serialVersionUID = 2999584822238025922L;

  private SaleOrderBillForm editor;

  private BillManageModel model;

  private IM30TranTypeService tranTypeService;

  public SaleOrderZYArrangeAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_ZYARRANGE);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    Object[] objects = this.model.getSelectedOperaDatas();

    this.checkActionEnable(objects);
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
        Toolkit.getDefaultToolkit().getScreenSize().height - 1;
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
    // 对于列表多选按钮可用(由推单直运安排界面监听过滤可安排行数据)
    if (objects.length > 1) {
      return true;
    }

    // 只要一条满足按钮可用：已审批、未安排关闭(由推单直运安排界面监听过滤可安排行数据)
    boolean isZYArrange = false;
    for (Object object : objects) {
      SaleOrderVO vo = (SaleOrderVO) object;
      if (BillStatus.AUDIT.equalsValue(vo.getParentVO().getFstatusflag())) {
        SaleOrderBVO[] bodys = vo.getChildrenVO();
        if (bodys != null) {
          for (SaleOrderBVO body : bodys) {
            if (!body.getBarrangedflag().booleanValue()) {
              isZYArrange = true;
              break;
            }
          }
        }
      }
    }
    return isZYArrange;
  }

  private void checkActionEnable(Object[] objects) {
    // 只要一条满足按钮可用：已审批、未安排关闭(由推单直运安排界面监听过滤可安排行数据)
    for (Object object : objects) {
      SaleOrderVO vo = (SaleOrderVO) object;
      int directtype = this.getDirecttype(vo);
      int directtran_to = DirectType.DIRECTTRAN_TO.getIntValue();
      int directtran_po = DirectType.DIRECTTRAN_PO.getIntValue();

      if (BillStatus.AUDIT.equalsValue(vo.getParentVO().getFstatusflag())
          && (directtran_to == directtype || directtran_po == directtype)) {
        SaleOrderBVO[] bodys = vo.getChildrenVO();
          // 2014-01-16 dongli2
          // 直运安排之前有bug，订单按行安排过就不能对其他行安排，增加isBarranged判断是否有未安排的行
          boolean isBarranged = true;
          for (SaleOrderBVO body : bodys) {
            if (!body.getBarrangedflag().booleanValue()) {
              isBarranged = false;
            }
          }
          if (isBarranged) {
            ExceptionUtils
                .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                    .getNCLangRes().getStrByID("4006011_0", "04006011-0023")/*@res "请检查：销售订单是否已经安排关闭"*/);
          }
        }
        else {
          ExceptionUtils
              .wrappBusinessException(NCLangRes.getInstance().getStrByID(
                  "4006011_0", "04006011-0248", null, new String[] {})/*请检查：1.销售订单是否已经审批  2.交易类型中直运类型标记是否是直运调拨或者直运采购*/);
        }
      }

    }

  private int getDirecttype(SaleOrderVO svo) {
    int flag = 0;
    M30TranTypeVO m30type =
        this.getEditor()
            .getM30ClientContext()
            .getTransType(svo.getParentVO().getVtrantypecode(),
                svo.getParentVO().getPk_group());
    if (!VOChecker.isEmpty(m30type)) {
      flag = m30type.getFdirecttype().intValue();
    }
    else {
      try {
        m30type =
            this.getTranTypeService().queryTranTypeVO(
                svo.getParentVO().getCtrantypeid());
        this.getEditor().getM30ClientContext()
            .setTransType(svo.getParentVO().getVtrantypecode(), m30type);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappBusinessException(e.getMessage());
      }
      flag = m30type.getFdirecttype().intValue();
    }
    return flag;
  }

  private IM30TranTypeService getTranTypeService() {
    if (this.tranTypeService == null) {
      this.tranTypeService =
          NCLocator.getInstance().lookup(IM30TranTypeService.class);
    }
    return this.tranTypeService;
  }
}