package nc.ui.so.m30.sobalance.link;

import nc.ui.pub.linkoperate.ILinkMaintainData;

public class SoBalanceLinkMaintainData implements ILinkMaintainData {

  private String billID;

  private Object userObject;

  @Override
  public String getBillID() {
    return this.billID;
  }

  public void setBillID(String billID) {
    this.billID = billID;
  }

  public void setUserObject(Object userObject) {
    this.userObject = userObject;
  }

  @Override
  public Object getUserObject() {
    return this.userObject;
  }

}
