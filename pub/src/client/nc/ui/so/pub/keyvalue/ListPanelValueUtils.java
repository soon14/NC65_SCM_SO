package nc.ui.so.pub.keyvalue;

import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.IBillItem;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.so.pub.enumeration.ListTemplateType;

/**
 * 销售：列表模板取值工具
 * 
 * @since 6.0
 * @version 2011-6-16 下午04:40:29
 * @author 刘志伟
 */
public class ListPanelValueUtils {

  private BillModel billModel;

  private BillListPanel listPanel;

  private ListTemplateType type;

  public ListPanelValueUtils(BillListPanel billListPanel, ListTemplateType type) {
    this.listPanel = billListPanel;
    this.type = type;
  }
  
  public BillModel getBillModel() {
    return billModel;
  }

  public BillListPanel getListPanel() {
    return listPanel;
  }

  public ListTemplateType getType() {
    return type;
  }

  public String getBodyStringValueAt(int row, String strKey) {
    Object value = this.getBodyValueAt(row, strKey);
    return ValueUtils.getString(value);
  }

  public UFBoolean getBodyUFBooleanValueAt(int row, String strKey) {
    Object value = this.getBodyValueAt(row, strKey);
    return ValueUtils.getUFBoolean(value);
  }

  public UFDate getBodyUFDateValueAt(int row, String strKey) {
    Object value = this.getBodyValueAt(row, strKey);
    return ValueUtils.getUFDate(value);
  }

  public UFDouble getBodyUFDoubleValueAt(int row, String strKey) {
    Object value = this.getBodyValueAt(row, strKey);
    return ValueUtils.getUFDouble(value);
  }

  public Object getBodyValueAt(int row, String strKey) {
    this.initBodyBillModel();
    return this.getValueAt(row, strKey);
  }

  public String getHeadStringValueAt(int row, String strKey) {
    Object value = this.getHeadValueAt(row, strKey);
    return ValueUtils.getString(value);
  }

  public UFBoolean getHeadUFBooleanValueAt(int row, String strKey) {
    Object value = this.getHeadValueAt(row, strKey);
    return ValueUtils.getUFBoolean(value);
  }

  public UFDate getHeadUFDateValueAt(int row, String strKey) {
    Object value = this.getHeadValueAt(row, strKey);
    return ValueUtils.getUFDate(value);
  }

  public UFDouble getHeadUFDoubleValueAt(int row, String strKey) {
    Object value = this.getHeadValueAt(row, strKey);
    return ValueUtils.getUFDouble(value);
  }

  public Object getHeadValueAt(int row, String strKey) {
    this.initHeadBillModel();
    return this.getValueAt(row, strKey);
  }

  /**
   * 获取行数
   * 
   * @return
   */
  public int getRowCount() {
    this.initBodyBillModel();
    return this.billModel.getRowCount();
  }

  public void setBodyValueAt(Object aValue, int row, String strKey) {
    this.initBodyBillModel();
    this.setValueAt(aValue, row, strKey);
  }

  public void setHeadValueAt(Object aValue, int row, String strKey) {
    this.initHeadBillModel();
    this.setValueAt(aValue, row, strKey);
  }

  /**
   * 获取正确的itemkey，对于参照型的itemkey，需要增加一个后缀来操作正确值
   * 
   * @param itemKey
   * @return 正确的itemkey
   */
  private String getCorrectItemKey(String itemKey) {
    if (IBillItem.UFREF == this.getItemType(itemKey)) {
      return itemKey + IBillItem.ID_SUFFIX;
    }
    return itemKey;
  }

  /**
   * 获得字段类型
   * 
   * @param itemKey
   * @return 字段类型
   */
  private int getItemType(String itemKey) {
    BillItem billItem = this.billModel.getItemByKey(itemKey);
    return null == billItem ? IBillItem.UNSET : billItem.getDataType();
  }

  private Object getValueAt(int row, String strKey) {
    if (this.listPanel.getBillListData().isMeataDataTemplate()) {
      int colIndex = this.billModel.getBodyColByKey(strKey);
      if (colIndex < 0) {
        return null;
      }
      Object value = this.billModel.getValueAt(row, colIndex);
      BillItem item = this.billModel.getItemByKey(strKey);
      Object trueValue = item.converType(value);
      return trueValue;
    }
    return this.billModel.getValueAt(row, this.getCorrectItemKey(strKey));
  }

  private void initBodyBillModel() {
    if (this.type == ListTemplateType.MAIN) {
      this.billModel = this.listPanel.getHeadBillModel();
    }
    else if (this.type == ListTemplateType.SUB) {
      this.billModel = this.listPanel.getBodyBillModel();
    }
    else if (this.type == ListTemplateType.MAIN_SUB) {
      this.billModel = this.listPanel.getBodyBillModel();
    }
  }

  private void initHeadBillModel() {
    if (this.type == ListTemplateType.MAIN) {
      this.billModel = this.listPanel.getHeadBillModel();
    }
    else if (this.type == ListTemplateType.SUB) {
      this.billModel = this.listPanel.getBodyBillModel();
    }
    else if (this.type == ListTemplateType.MAIN_SUB) {
      this.billModel = this.listPanel.getHeadBillModel();
    }
  }

  private void setValueAt(Object aValue, int row, String strKey) {
    this.billModel.setValueAt(aValue, row, this.getCorrectItemKey(strKey));
    if (IBillItem.UFREF == this.getItemType(strKey)) {
      this.billModel.loadLoadRelationItemValue(row, strKey);
    }
  }
  
  /**
   * 把item暴露出来
   * jilu v636
   * 
   * @param strKey
   * @return
   */
  public BillItem getBillItem(String strKey) {
    this.initHeadBillModel();
    if (this.listPanel.getBillListData().isMeataDataTemplate()) {
      int colIndex = this.billModel.getBodyColByKey(strKey);
      if (colIndex < 0) {
        return null;
      }
      BillItem item = this.billModel.getItemByKey(strKey);
      return item;
    }
    return this.billModel.getItemByKey(this.getCorrectItemKey(strKey));
  }
}
