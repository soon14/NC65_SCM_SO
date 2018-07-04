package nc.ui.so.pub.closingcheck;

import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.view.BatchBillTable;
import nc.ui.uif2.components.AutoShowUpEventSource;
import nc.ui.uif2.components.IAutoShowUpComponent;
import nc.ui.uif2.components.IAutoShowUpEventListener;
import nc.ui.uif2.components.ITabbedPaneAwareComponent;
import nc.ui.uif2.components.ITabbedPaneAwareComponentListener;
import nc.ui.uif2.components.TabbedPaneAwareCompnonetDelegate;
import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * 单表界面, 可配置单独的菜单按钮
 * 
 * @since 6.0
 * @version 2010-11-25 下午07:57:56
 * @author 皮之兵
 */
public class IABatchTable extends BatchBillTable implements
    IAutoShowUpComponent, ITabbedPaneAwareComponent {

  private static final long serialVersionUID = -8342748470289897668L;

  private AutoShowUpEventSource autoShowUpComponent =
      new AutoShowUpEventSource(this);

  /**
   * 缓存界面查询条件
   */
  private Object condition;

  private TabbedPaneAwareCompnonetDelegate tabbedPaneAwareCompnonetDelegate =
      new TabbedPaneAwareCompnonetDelegate();

  @Override
  public void addTabbedPaneAwareComponentListener(
      ITabbedPaneAwareComponentListener l) {
    this.tabbedPaneAwareCompnonetDelegate
        .addTabbedPaneAwareComponentListener(l);
  }

  @Override
  public boolean canBeHidden() {
    return this.tabbedPaneAwareCompnonetDelegate.canBeHidden();

  }

  public Object getCondition() {
    return this.condition;
  }

  @Override
  public Object getEditingLineVO(int rowIndex) {
    CircularlyAccessibleValueObject obj =
        this.getBillCardPanel().getBillModel()
            .getBodyValueRowVO(rowIndex, super.getVoClassName());
    BillItem[] items = this.getBillCardPanel().getBodyShowItems();
    CircularlyAccessibleValueObject oldobj =
        (CircularlyAccessibleValueObject) super.getModel().getRow(rowIndex);

    for (BillItem item : items) {
      Object value = obj.getAttributeValue(item.getKey());
      oldobj.setAttributeValue(item.getKey(), value);
    }
    return oldobj;
  }

  @Override
  public void initUI() {
    super.initUI();
  }

  @Override
  public boolean isComponentVisible() {
    return this.tabbedPaneAwareCompnonetDelegate.isComponentVisible();
  }

  @Override
  public void setAutoShowUpEventListener(IAutoShowUpEventListener l) {
    this.autoShowUpComponent.setAutoShowUpEventListener(l);
  }

  @Override
  public void setComponentVisible(boolean visible) {
    this.tabbedPaneAwareCompnonetDelegate.setComponentVisible(visible);
  }

  public void setCondition(Object condition) {
    this.condition = condition;
  }

  @Override
  public void showMeUp() {
    this.autoShowUpComponent.showMeUp();
  }
}
