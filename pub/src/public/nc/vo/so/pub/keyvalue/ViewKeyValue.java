package nc.vo.so.pub.keyvalue;

import nc.vo.pubapp.pattern.model.entity.view.IDataView;

public class ViewKeyValue<E extends IDataView> extends AbstractKeyValue {

  private E view;

  public ViewKeyValue(E view) {
    this.view = view;
  }

  @Override
  public int getBodyCount() {
    return 1;
  }

  @Override
  public Object getBodyValue(int index, String key) {
    return this.view.getAttributeValue(key);
  }

  @Override
  public Object getHeadValue(String key) {
    return this.view.getAttributeValue(key);
  }

  @Override
  public RowStatus getRowStatus(int index) {
    return null;
  }

  @Override
  public void setBodyValue(int index, String key, Object value) {
    this.view.setAttributeValue(key, value);
  }

  @Override
  public void setHeadValue(String key, Object value) {
    this.view.setAttributeValue(key, value);
  }

}
