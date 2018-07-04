package nc.impl.so.outsummary.processer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.query.ConditionVO;
import nc.vo.so.report.outsummary.OutSummaryViewVO;
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
public class OutSummaryLevelProcesser {

  /**
   * 级次处理方法
   * 
   * @param views
   * @param queryScheme
   */
  public void processLevel(OutSummaryViewVO[] views, IQueryScheme queryScheme) {

    // 1.物料基本分类级次
    this.processMarLevel(views, queryScheme);

  }

  private void processMarLevel(OutSummaryViewVO[] views,
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

  private void setMarbasclass(OutSummaryViewVO[] views, int marbasclasslevel) {
    Set<String> materids = new HashSet<String>();
    for (OutSummaryViewVO view : views) {
      materids.add(view.getCmaterialoid());
    }
    Map<String, String> materialmarbasmap = new HashMap<String, String>();
    String[] cmaterialids = materids.toArray(new String[materids.size()]);
    ReportLevelUtil levelutil = new ReportLevelUtil();
    materialmarbasmap =
        levelutil.queryMarBasClassIDByLevel(cmaterialids, marbasclasslevel);
    for (OutSummaryViewVO view : views) {
      String cmarid = view.getCmaterialoid();
      view.setPk_marbasclass(materialmarbasmap.get(cmarid));
    }
  }
}
