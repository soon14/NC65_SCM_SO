package nc.impl.so.multipleprofit.processer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.query.ConditionVO;
import nc.vo.so.report.multipleprofit.MultipleProfitViewVO;
import nc.vo.so.report.reportpub.ReportContext;
import nc.vo.so.report.reportpub.ReportLevelUtil;
import nc.vo.so.report.reportpub.ReportLevelValue;

import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 综合毛利分析中“销售组织级次”和“物料分类级次”处理
 * 
 * @since 6.3
 * @version 2012-10-22 14:23:05
 * @author zhangkai4
 */
public class MulProLevelProcesser {

  /**
   * 销售组织级次”和“物料分类级次”处理
   * 
   * @param views
   * @param queryScheme
   */
  public void processLevel(MultipleProfitViewVO[] views,
      IQueryScheme queryScheme) {
    // 1.物料基本分类级次
    this.processMarLevel(views, queryScheme);

    // 2.销售组织级次
    this.processOrgLevel(views, queryScheme);

  }

  /**
   * 销售组织级次处理
   * 
   * @param views
   * @param queryScheme
   */
  private void processOrgLevel(MultipleProfitViewVO[] views,
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

  /**
   * 设置销售组织级次
   * 
   * @param views
   * @param saleorglevel
   */
  private void setSaleorg(MultipleProfitViewVO[] views, int saleorglevel) {
    Set<String> saleorgids = new HashSet<String>();
    // 获取销售组织ID
    for (MultipleProfitViewVO view : views) {
      saleorgids.add(view.getCsaleorgid());
    }
    Map<String, String> saleorglevelmap = new HashMap<String, String>();
    ReportLevelUtil levelutil = new ReportLevelUtil();
    for (String oldsaleorgid : saleorgids) {
      String newsaleorgid =
          levelutil.querySaleorgIDByLevel(oldsaleorgid, saleorglevel);
      saleorglevelmap.put(oldsaleorgid, newsaleorgid);
    }

    for (MultipleProfitViewVO view : views) {
      String oldsaleorgid = view.getCsaleorgid();
      view.setCsaleorgid(saleorglevelmap.get(oldsaleorgid));
    }
  }

  /**
   * 物料分类级次处理
   * 
   * @param views
   * @param queryScheme
   */
  private void processMarLevel(MultipleProfitViewVO[] views,
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

  /**
   * 物料分类级次处理
   * 
   * @param views
   * @param marbasclasslevel
   */
  private void setMarbasclass(MultipleProfitViewVO[] views, int marbasclasslevel) {
    Set<String> materids = new HashSet<String>();
    // 获取物料ID
    for (MultipleProfitViewVO view : views) {
      materids.add(view.getCmaterialid());
    }
    Map<String, String> materialmarbasmap = new HashMap<String, String>();
    String[] cmaterialids = materids.toArray(new String[materids.size()]);
    ReportLevelUtil levelutil = new ReportLevelUtil();
    materialmarbasmap =
        levelutil.queryMarBasClassIDByLevel(cmaterialids, marbasclasslevel);
    for (MultipleProfitViewVO view : views) {
      String cmarid = view.getCmaterialid();
      view.setPk_marbasclass(materialmarbasmap.get(cmarid));
    }
  }
}
