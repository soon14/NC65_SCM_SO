package nc.vo.so.pub.util;

import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.so.pub.keyvalue.AbstractKeyValue;

public class CirVOKeyValue<E extends CircularlyAccessibleValueObject> extends
    AbstractKeyValue {

  private E[] vos;

  public CirVOKeyValue(E[] vos) {
    this.vos = vos;
  }

  @Override
  public int getBodyCount() {
    return 1;
  }

  @Override
  public Object getBodyValue(int index, String key) {
    return this.vos[index].getAttributeValue(key);
  }

  @Override
  public Object getHeadValue(String key) {
    return this.vos[0].getAttributeValue(key);
  }

  @Override
  public RowStatus getRowStatus(int index) {
    return null;
  }

  @Override
  public void setBodyValue(int index, String key, Object value) {
    this.vos[index].setAttributeValue(key, value);
  }

  @Override
  public void setHeadValue(String key, Object value) {
    this.vos[0].setAttributeValue(key, value);
  }

}
