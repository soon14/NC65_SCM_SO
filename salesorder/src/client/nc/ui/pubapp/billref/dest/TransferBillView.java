package nc.ui.pubapp.billref.dest;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.model.BillManageModel;

public class TransferBillView extends ShowUpableBillListView implements
    PropertyChangeListener {

  private static int debugStaticCount;

  private static final long serialVersionUID = 2849667363943793436L;

  /**
   * 列表是否已经加载过数据，如果已经加载过那么在点返回时切换列表的时候就不再加载
   */
  boolean dataAlreadySyschForList = false;

  /**
   * 是否需要把数据加载到界面，为了考虑效率问题，在只拉一张单据的时候，列表界面先不加载
   * 只有在切换到列表显示的时候才显示，用这个开关来控制
   * 王梓懿 设置此开关可由需要者调用2017-12-29
   * 给出get 和 set 方法
   */
  private boolean needLoad = true;
  
  /**
   * @return the needLoad
   */
  public boolean isNeedLoad() {
  	return needLoad;
  }

  /**
   * @param needLoad the needLoad to set
   */
  public void setNeedLoad(boolean needLoad) {
  	this.needLoad = needLoad;
  }

  @SuppressWarnings("unused")
  private int debugCount;

  private ITransferListViewProcessor listProcessor;

  private ITransferListRowChangeProcessor listRowChangeProcessor;

  public TransferBillView() {
    this.debugCount = TransferBillView.debugStaticCount++;
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if ("componentVisible".equals(evt.getPropertyName())) {
      if (((Boolean) evt.getNewValue()).booleanValue()) {
        this.needLoad = true;
        if (!this.dataAlreadySyschForList) {
          this.synchronizeDataFromModel();
          this.handleSelectionChanged();
          // this.alreadySys = true;
        }
      }
    }
  }

  @Override
  protected void handleSelectionChanged() {
    if (!this.needLoad) {
      return;
    }
    super.handleSelectionChanged();
    this.processRowChange();
  }

  @Override
  protected void synchronizeDataFromModel() {
    if (!this.needLoad) {
      return;
    }
    this.dataAlreadySyschForList = true;
    if (this.getListProcessor() != null) {
      this.getListProcessor().processBefore(this,
          this.getModel().getData().toArray(new Object[0]));
    }
    super.synchronizeDataFromModel();
    if (this.getListProcessor() != null) {
      this.getListProcessor().processAfter(this,
          this.getModel().getData().toArray(new Object[0]));
    }
  }

  void setListProcessor(ITransferListViewProcessor listProcessor) {
    this.listProcessor = listProcessor;
  }

  void setListRowChangeProcessor(
      ITransferListRowChangeProcessor listRowChangeProcessor) {
    this.listRowChangeProcessor = listRowChangeProcessor;
  }

  private ITransferListViewProcessor getListProcessor() {
    return this.listProcessor;
  }

  private ITransferListRowChangeProcessor getListRowChangeProcessor() {
    return this.listRowChangeProcessor;
  }

  private void processRowChange() {
    BillManageModel model = this.getModel();
    Object selectedData = model.getSelectedData();
    int selectedRow = this.getModel().getSelectedRow();
    if (this.getListRowChangeProcessor() != null) {
      this.getListRowChangeProcessor().processRowChange(this, selectedRow,
          selectedData);
    }
  }

}
