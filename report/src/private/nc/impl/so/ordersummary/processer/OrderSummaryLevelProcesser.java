package nc.impl.so.ordersummary.processer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.query.ConditionVO;
import nc.vo.so.report.ordersummary.OrderSummaryViewVO;
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
public class OrderSummaryLevelProcesser {

  /**
   * 级次处理方法
   * 
   * @param views
   * @param queryScheme
   */
  public void processLevel(OrderSummaryViewVO[] views, IQueryScheme queryScheme) {

    // 1.物料基本分类级次
    this.processMarLevel(views, queryScheme);

    // 2.销售组织级次
    this.processOrgLevel(views, queryScheme);

  }

  private void processOrgLevel(OrderSummaryViewVO[] views,
      IQueryScheme queryScheme) {
    int saleorglevel = 0;
    ConditionVO[] conds =
        (ConditionVO[]) queryScheme.get(IQueryScheme.KEY_LOGICAL_CONDITION);
    for (ConditionVO cond : conds) {
      if (cond.getFieldCode().equals(ReportContext.SALEORGLEVEL)) {
        saleorglevel = new UFDouble(cond.getValue()).intValue();
      }
    }
    if (!ReportLevelValue.END.equalsValue(Integer.valueOf(saleorglevel))) {
      this.setSaleorg(views, saleorglevel);
    }
  }

  private void setSaleorg(OrderSummaryViewVO[] views, int saleorglevel) {
    Set<String> saleorgids = new HashSet<String>();
    for (OrderSummaryViewVO view : views) {
      saleorgids.add(view.getPk_org());
    }
    Map<String, String> saleorglevelmap = new HashMap<String, String>();
    ReportLevelUtil levelutil = new ReportLevelUtil();
    for (String oldsaleorgid : saleorgids) {
      String newsaleorgid =
          levelutil.querySaleorgIDByLevel(oldsaleorgid, saleorglevel);
      saleorglevelmap.put(oldsaleorgid, newsaleorgid);
    }

    for (OrderSummaryViewVO view : views) {
      String oldsaleorgid = view.getPk_org();
      view.setPk_org(saleorglevelmap.get(oldsaleorgid));
    }
  }

  private void processMarLevel(OrderSummaryViewVO[] views,
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

  private void setMarbasclass(OrderSummaryViewVO[] views, int marbasclasslevel) {
    Set<String> materids = new HashSet<String>();
    for (OrderSummaryViewVO view : views) {
      materids.add(view.getCmaterialid());
    }
    Map<String, String> materialmarbasmap = new HashMap<String, String>();
    String[] cmaterialids = materids.toArray(new String[materids.size()]);
    ReportLevelUtil levelutil = new ReportLevelUtil();
    materialmarbasmap =
        levelutil.queryMarBasClassIDByLevel(cmaterialids, marbasclasslevel);
    for (OrderSummaryViewVO view : views) {
      String cmarid = view.getCmaterialid();
      view.setPk_marbasclass(materialmarbasmap.get(cmarid));
    }
  }
}
