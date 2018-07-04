package nc.vo.so.pub.para;

import nc.itf.so.pub.para.IPriorityCode;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public class SinglePriorityCode implements IPriorityCode {

  private String attrvalue;

  /**
   * ¹¹Ôì×Ó
   *
   * @param vo
   */
  public SinglePriorityCode(
      String attrvalue) {
    this.attrvalue = attrvalue;
  }

  @Override
  public String getPriorityCode() {
    if (this.isNull(this.attrvalue)) {
      return "0";
    }
    return "1";
  }

  @Override
  public boolean isLevel() {
    return false;
  }

  protected boolean isNull(String value) {
    return PubAppTool.isNull(value);
  }

}
