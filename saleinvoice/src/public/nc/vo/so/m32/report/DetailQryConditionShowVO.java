package nc.vo.so.m32.report;

import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ValidationException;

public class DetailQryConditionShowVO extends CircularlyAccessibleValueObject {

  private static final long serialVersionUID = 2953851486787379830L;

  private String billdateshow;

  private String orgnameshow;

  @Override
  public String[] getAttributeNames() {
    return null;
  }

  @Override
  public Object getAttributeValue(String arg0) {
    return null;
  }

  public String getBilldateshow() {
    return this.billdateshow;
  }

  @Override
  public String getEntityName() {
    return null;
  }

  public String getOrgnameshow() {
    return this.orgnameshow;
  }

  @Override
  public void setAttributeValue(String arg0, Object arg1) {
    // TODO
  }

  public void setBilldateshow(String billdate) {
    this.billdateshow = billdate;
  }

  public void setOrgnameshow(String orgname) {
    this.orgnameshow = orgname;
  }

  @Override
  public void validate() throws ValidationException {
    // TODO
  }
}
