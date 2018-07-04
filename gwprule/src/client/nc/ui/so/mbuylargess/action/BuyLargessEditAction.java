package nc.ui.so.mbuylargess.action;

import java.awt.event.ActionEvent;

import nc.vo.trade.checkrule.VOChecker;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.EditAction;
import nc.ui.so.mbuylargess.view.BuyLargessEditor;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>买赠设置修改时设置编辑性
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号 V60
 * @since 上一版本号
 * @author fengjb
 * @time 2009-6-17 下午06:08:18
 */
public class BuyLargessEditAction extends EditAction {

  // 上限类型：不控制
  private static final int NOTOPLIMIT = 2;

  private static final long serialVersionUID = 1L;

  private BuyLargessEditor view;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.actions.EditAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    // 设置字段编辑性
    super.doAction(e);
    this.setEditenable();
  }

  /**
   * 
   * 
   * @return view
   */
  public BuyLargessEditor getView() {
    return this.view;
  }

  /**
   * 
   * 
   * @param view
   */
  public void setView(BuyLargessEditor view) {
    this.view = view;
  }

  private void setCust(BillCardPanel billCardPanel) {
    // 客户
    Object pk_customer =
        billCardPanel.getHeadItem("pk_customer").getValueObject();

    if (!VOChecker.isEmpty(pk_customer)) {

      billCardPanel.getHeadItem("pk_custclass").setEnabled(false);
      billCardPanel.getHeadItem("pk_custsaleclass").setEnabled(false);
    }
    // 客户分类
    Object pk_custclass =
        billCardPanel.getHeadItem("pk_custclass").getValueObject();

    Object pk_custsaleclass =
        billCardPanel.getHeadItem("pk_custsaleclass").getValueObject();
    if (!VOChecker.isEmpty(pk_custclass)
        || !VOChecker.isEmpty(pk_custsaleclass)) {
      billCardPanel.getHeadItem("pk_customer").setEnabled(false);
    }
  }

  /**
   * 方法功能描述：根据输入情况设置字段编辑性。 <b>参数说明</b>
   * 
   * @author fengjb
   * @time 2009-6-17 下午05:02:49
   */
  private void setEditenable() {
    BillCardPanel billCardPanel = this.getView().getBillCardPanel();
    this.setMaterial(billCardPanel);
    this.setCust(billCardPanel);
    // 表体字段
    // 上限类型
    int col = billCardPanel.getBillModel().getBodyColByKey("ftoplimittype");
    int length = billCardPanel.getRowCount();
    for (int i = 0; i < length; i++) {
      Object objtoplimit = billCardPanel.getBillModel().getValueAt(i, col);
      Object convervalue =
          billCardPanel.getBodyItem("ftoplimittype").converType(objtoplimit);
      int ftoplimittype = Integer.parseInt(convervalue.toString());

      if (BuyLargessEditAction.NOTOPLIMIT == ftoplimittype) {
        billCardPanel.setCellEditable(i, "ntoplimitvalue", false);
      }
      else {
        billCardPanel.setCellEditable(i, "ntoplimitvalue", true);
      }
    }
  }

  private void setMaterial(BillCardPanel billCardPanel) {
    // 表头字段
    // 物料
    Object pk_material =
        billCardPanel.getHeadItem("cbuymarid").getValueObject();
    if (VOChecker.isEmpty(pk_material)) {
      billCardPanel.getHeadItem("cbuymarid").setEnabled(false);
      billCardPanel.getHeadItem("pk_marbasclass").setEnabled(true);
      billCardPanel.getHeadItem("pk_marsaleclass").setEnabled(true);

    }
    else {
      billCardPanel.getHeadItem("cbuymarid").setEnabled(true);
      billCardPanel.getHeadItem("pk_marbasclass").setEnabled(false);
      billCardPanel.getHeadItem("pk_marsaleclass").setEnabled(false);
    }
  }
}
