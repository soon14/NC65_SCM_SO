package nc.ui.so.pub.keyvalue;

import nc.ui.pub.bill.BillListPanel;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.keyvalue.AbstractKeyValue;

/**
 * <p>
 * <b>所有的值都从列表表头取值</b>
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
 * @author zhangcheng
 * @time 2010-6-29 上午09:16:36
 */
public class ListKeyValue extends AbstractKeyValue {

  // 当前操作列表表头数据所在行
  private int curHeadRow;

  private BillListPanel list;

  private ListPanelValueUtils valueUtil;

  /**
   * 如果需要更改当前行可使用setRow()
   */
  public ListKeyValue(BillListPanel list, int row, ListTemplateType type) {
    this.list = list;
    this.valueUtil = new ListPanelValueUtils(this.list, type);
    this.curHeadRow = row;
  }

  public ListKeyValue(BillListPanel list, ListTemplateType type) {
    this.list = list;
    this.valueUtil = new ListPanelValueUtils(this.list, type);
  }

  @Override
  public int getBodyCount() {
    return this.valueUtil.getRowCount();
  }

  @Override
  public Object getBodyValue(int row, String strKey) {
    return this.valueUtil.getBodyValueAt(row, strKey);
  }

  @Override
  public Object getHeadValue(String strKey) {
    return this.valueUtil.getHeadValueAt(this.getRow(), strKey);
  }

  public BillListPanel getList() {
    return this.list;
  }

  public int getRow() {
    return this.curHeadRow;
  }

  @Override
  public RowStatus getRowStatus(int row) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setBodyValue(int row, String strKey, Object aValue) {
    this.valueUtil.setBodyValueAt(aValue, row, strKey);
  }

  @Override
  public void setHeadValue(String strKey, Object value) {
    this.valueUtil.setHeadValueAt(value, this.getRow(), strKey);
  }

  public void setRow(int row) {
    this.curHeadRow = row;
  }

}
