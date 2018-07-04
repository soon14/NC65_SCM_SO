package nc.vo.so.report.paravo;

import java.io.Serializable;

import nc.vo.pub.query.ConditionVO;

public class ProfitUserObj implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 界面条件
   */
  private ConditionVO[] conds;

  private String subtablename;

  private String wheresql;

  public ConditionVO[] getConds() {
    return this.conds;
  }

  public String getSubtablename() {
    return this.subtablename;
  }

  public String getWheresql() {
    return this.wheresql;
  }

  public void setConds(ConditionVO[] conds) {
    this.conds = conds;
  }

  public void setSubtablename(String subtablename) {
    this.subtablename = subtablename;
  }

  public void setWheresql(String wheresql) {
    this.wheresql = wheresql;
  }

}
