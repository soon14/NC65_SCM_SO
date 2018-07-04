package nc.ui.so.m38.billui.action;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.bbd.func.IFuncRegisterQueryService;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.uif2app.actions.AddMenuAction;
import nc.ui.so.m30.billui.action.SaleOrderAddFrom38Action;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.sm.funcreg.FuncRegisterVO;

/**
 * 判断预订单是否停用，如果停用，则停用销售订单参照预订单拉单按钮
 * @author liylr
 */
@SuppressWarnings("restriction")
public class PreOrderMenuAndNodeAction extends AddMenuAction {
  private static final long serialVersionUID = 4765557478355013491L;

  private boolean initializated = false;

  @Override
  protected void preShowPopMenu() {
    super.preShowPopMenu();
    if (!this.initializated) {
      afterPropertiesSet();
    }
  }

  private void afterPropertiesSet() {
    this.initializated = true;
    javax.swing.Action[] childs = this.getAllChild();

    FuncRegisterVO[] vos = null;
    try {
      vos =
          NCLocator.getInstance().lookup(IFuncRegisterQueryService.class)
              .queryFunctionWhere("sm_menuitemreg.FUNCODE='40060201'");
    }catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006012_0", "04006012-0101")/* 无法获取销售预订单的启用状态！ */);
    }
    for (javax.swing.Action child : childs) {
      if (!(child instanceof SaleOrderAddFrom38Action)) {
        continue;
      }
      SaleOrderAddFrom38Action btn = (SaleOrderAddFrom38Action) child;

      // 判断预订单是否停用，如果停用,就把预订单参照拉单按钮移除
      if (vos == null || vos.length == 0) {
        return;
      }else if (!vos[0].getIsenable().booleanValue()) {
        this.removeChildAction(btn);
      }
    }
  }
}
