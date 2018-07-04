package nc.ui.so.m38.billui.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.so.m38.IPreOrderMigrate;
import nc.ui.ls.MessageBox;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.uif2.NCAction;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @author liylr
 */
@SuppressWarnings("restriction")
public class PreOrderMigrateAction extends NCAction {

  public PreOrderMigrateAction() {
    super();
    this.setBtnName(NCLangRes.getInstance().getStrByID("4006012_0",
        "04006012-0102")/* 迁移 */);
    this.setCode("preorderMigdata");
  }

  private static final long serialVersionUID = -7952608831277926346L;

  @Override
  public synchronized void doAction(ActionEvent arg0) throws Exception {
    try {
      int dlgResult =
          MessageDialog.showYesNoDlg(WorkbenchEnvironment.getInstance()
              .getWorkbench().getParent(), NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0004")/*提示*/,
              NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0122")/*请确认是否进行销售预订单到电子销售预订单迁移？\r\n迁移过程中可能耗时较长。*/);
      if (UIDialog.ID_YES == dlgResult) {
        NCLocator.getInstance().lookup(IPreOrderMigrate.class)
            .migratePreOrder();
        MessageBox
        .showMessageDialog(
            NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0004")/* 提示 */,
            NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0103")/* 升级成功！ */);
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
