package nc.ui.so.mbuylargess.view;

import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.model.IRowSelectModel;
import nc.vo.so.pub.util.BaseSaleClassUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>存货买赠设置列表界面
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author fengjb
 * @time 2009-6-4 下午03:22:38
 */
public class BuyLargessList extends ShowUpableBillListView {

  private static final long serialVersionUID = 3980285733144504397L;

  @Override
  public void initUI() {
    super.initUI();
    // 根据参数SO79:物料分类方式 SO80:客户分类方式 设置分类字段显示属性
    String pk_group = this.getModel().getContext().getPk_group();
    boolean isbas = BaseSaleClassUtil.isMarBaseClass(pk_group);
    this.getBillListPanel().getHeadItem("pk_marbasclass").setShow(isbas);
    this.getBillListPanel().getHeadItem("pk_marsaleclass").setShow(!isbas);

    isbas = BaseSaleClassUtil.isCustBaseClass(pk_group);
    this.getBillListPanel().getHeadItem("pk_custclass").setShow(isbas);
    this.getBillListPanel().getHeadItem("pk_custsaleclass").setShow(!isbas);
    this.getBillListPanel().hideBodyTableCol("pk_marsaleclass");
    this.getBillListPanel().hideBodyTableCol("pk_marbasclass");
    this.getBillListPanel().hideBodyTableCol("pk_material.pk_marbasclass");
    this.getBillListPanel().hideBodyTableCol("pk_material.pk_measdoc");
    this.getBillListPanel().hideHeadTableCol("pk_material.pk_measdoc");
    this.getBillListPanel().setListData(
        this.getBillListPanel().getBillListData());
    BuyLargessPrecision.getInstance().setListPrecision(pk_group,
        this.getBillListPanel());

  }
  // add by quyt 为了处理嘉宝莉买赠设置删除错误的问题
  public void bodyRowChange(BillEditEvent e) {
    if (handlingModelEvent) {
      return;
    }
    if (e.getOldRow() != e.getRow()
        && e.getRow() < super.getModel().getRowCount()) {
      if (super.getModel() instanceof IRowSelectModel) {
        ((IRowSelectModel) super.getModel()).setSelectedRow(e.getRow());
      }
    }
    super.bodyRowChange(e);
  }
  /**
   * 同步ListView模型和BillListPanel模型
   */
  protected void synchronizeDataFromModel() {
    super.synchronizeDataFromModel();
    this.getBillListPanel().getHeadBillModel().loadLoadRelationItemValue();
  }
}
