package nc.impl.so.invoicesummary.processer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.query.ConditionVO;
import nc.vo.so.report.invoicesummary.InvSummaryViewVO;
import nc.vo.so.report.reportpub.ReportContext;
import nc.vo.so.report.reportpub.ReportLevelUtil;
import nc.vo.so.report.reportpub.ReportLevelValue;

/**
 * 级次处理
 * 
 * @since 6.3
 * @version 2012-09-04 13:36:38
 * @author 梁吉明
 */
public class InvSummaryLevelProcesser {

  /**
   * 级次处理方法
   * 
   * @param views
   * @param queryScheme
   */
  public void processLevel(InvSummaryViewVO[] views, IQueryScheme queryScheme) {

    // 1.物料基本分类级次
    this.processMarLevel(views, queryScheme);

  }

  private void processMarLevel(InvSummaryViewVO[] views,
      IQueryScheme queryScheme) {
    int marbasclasslevel = 0;
    ConditionVO[] conds =
        (ConditionVO[]) queryScheme.get(IQueryScheme.KEY_LOGICAL_CONDITION);
    for (ConditionVO cond : conds) {
      if (cond.getFieldCode().equals(ReportContext.CMATERIALMARBASCLASSLEVEL)) {
        marbasclasslevel = new UFDouble(cond.getValue()).intValue();
      }
    }
    if (!ReportLevelValue.END.equalsValue(Integer.valueOf(marbasclasslevel))) {
      this.setMarbasclass(views, marbasclasslevel);
    }
  }

  private void setMarbasclass(InvSummaryViewVO[] views, int marbasclasslevel) {
    Set<String> materids = new HashSet<String>();
    for (InvSummaryViewVO view : views) {
      materids.add(view.getCmaterialid());
    }
    Map<String, String> materialmarbasmap = new HashMap<String, String>();
    String[] cmaterialids = materids.toArray(new String[materids.size()]);
    ReportLevelUtil levelutil = new ReportLevelUtil();
    materialmarbasmap =
        levelutil.queryMarBasClassIDByLevel(cmaterialids, marbasclasslevel);
    for (InvSummaryViewVO view : views) {
      String cmarid = view.getCmaterialid();
      view.setPk_marbasclass(materialmarbasmap.get(cmarid));
    }
  }
}
