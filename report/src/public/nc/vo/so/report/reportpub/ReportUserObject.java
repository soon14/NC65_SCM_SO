package nc.vo.so.report.reportpub;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import nc.vo.pub.query.ConditionVO;

import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 查询模版信息处理类
 * 
 * @since 6.3
 * @version 2012-09-04 15:25:02
 * @author 梁吉明
 */
public class ReportUserObject implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1705606554457774819L;

  private Set<String> summarykeys;

  private IQueryScheme qryscheme;

  /**
   * 构造方法
   * 
   * @param qryscheme
   */
  public ReportUserObject(IQueryScheme qryscheme) {
    this.qryscheme = qryscheme;
  }

  /**
   * 获得汇总条件
   * 
   * @return summkeys
   */
  public Set<String> getSummaryKeys() {
    if (null == this.summarykeys) {
      this.summarykeys = this.getSummaryKeyFromScheme();
    }
    return this.summarykeys;
  }

  /**
   * 获得查询方案
   * 
   * @return qryscheme
   */
  public IQueryScheme getIQueryScheme() {
    return this.qryscheme;
  }

  private Set<String> getSummaryKeyFromScheme() {
    Set<String> groupset = new HashSet<String>();
    ConditionVO[] conds =
        (ConditionVO[]) this.qryscheme.get(IQueryScheme.KEY_LOGICAL_CONDITION);
    // 1.汇总条件
    for (ConditionVO cond : conds) {
      if (cond.getFieldCode().equals(ReportContext.SUMMARYKEY)) {
        String groupstr = cond.getValue();
        groupstr = groupstr.replace("(", "");
        groupstr = groupstr.replace(")", "");
        groupstr = groupstr.replace("'", "");
        String[] keys = groupstr.substring(0, groupstr.length()).split(",");
        for (String key : keys) {
          groupset.add(key);
        }
      }
    }
    return groupset;
  }
}
