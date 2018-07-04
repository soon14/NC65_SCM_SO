package nc.vo.so.pub.para;

import nc.itf.so.pub.para.IPriorityCode;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public abstract class LevelPriorityCode implements IPriorityCode {

  private String attrvalue;

  private String pkorg;

  public LevelPriorityCode(
      String attrvalue, String pk_org) {

    this.attrvalue = attrvalue;
    this.pkorg = pk_org;
  }

  @Override
  public String getPriorityCode() {
    String prioritycode = null;
    if (this.isNull(this.attrvalue)) {
      prioritycode = "00";
    }
    else {
      int level = this.getLevel(this.attrvalue, this.pkorg);
      if (level < 10) {
        prioritycode = "0" + String.valueOf(level);
      }
      else {
        prioritycode = String.valueOf(level);
      }
    }

    return prioritycode;
  }

  @Override
  public boolean isLevel() {
    return true;
  }

  protected boolean isNull(String value) {
    return PubAppTool.isNull(value);
  }

  protected abstract int getLevel(String value, String pk_org);

}
