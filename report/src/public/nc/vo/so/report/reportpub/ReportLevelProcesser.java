package nc.vo.so.report.reportpub;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;

/**
 * 进行“物料”和“销售组织”的级次处理
 * 
 * @since 6.3
 * @version 2012-10-30 13:37:34
 * @author zhangkai4
 */
public class ReportLevelProcesser {

  private static ReportLevelProcesser instance = new ReportLevelProcesser();

  private ReportLevelProcesser() {
    //
  }

  /**
   * 获得唯一实例
   * 
   * @return instance
   */
  public static ReportLevelProcesser getInstance() {
    return ReportLevelProcesser.instance;
  }

  /**
   * 物料级次处理
   * 
   * @param views 需要处理的数据
   * @param maridField 物料ID字段名
   * @param marClassField 物料级次字段名
   * @param level 级次
   */
  public void processMarLevel(AbstractDataView[] views, String maridField,
      String marClassField, int level) {
    if (ReportLevelValue.END.equalsValue(Integer.valueOf(level))) {
      return;
    }
    // 获取物料ID，不保存重复数据
    Set<String> marterids = new HashSet<String>();
    for (AbstractDataView view : views) {
      String marid = (String) view.getAttributeValue(maridField);
      marterids.add(marid);
    }

    String[] cmaterialids = new String[marterids.size()];
    marterids.toArray(cmaterialids);
    // 级次处理
    ReportLevelUtil levelutil = new ReportLevelUtil();
    Map<String, String> materialMap =
        levelutil.queryMarBasClassIDByLevel(cmaterialids, level);
    // 将数据填入VO
    for (AbstractDataView view : views) {
      String materid = (String) view.getAttributeValue(maridField);
      view.setAttributeValue(marClassField, materialMap.get(materid));
    }
  }

  /**
   * 销售组织级次处理
   * 
   * @param views
   * @param saleorgClassField 销售组织字段名
   * @param level 级次
   */
  public void processSaleorgLevel(AbstractDataView[] views,
      String saleorgClassField, int level) {
    if (ReportLevelValue.END.equalsValue(Integer.valueOf(level))) {
      return;
    }

    // 获得销售组织ID
    Set<String> saleorgids = new HashSet<String>();
    for (AbstractDataView view : views) {
      String sorgid = (String) view.getAttributeValue(saleorgClassField);
      saleorgids.add(sorgid);
    }

    String[] salorgids = new String[saleorgids.size()];
    saleorgids.toArray(salorgids);

    // 级次的处理
    ReportLevelUtil levelUtil = new ReportLevelUtil();
    Map<String, String> saleorgMap =
        levelUtil.querySaleorgIDByLevel(salorgids, level);
    // 将数据填入VO
    for (AbstractDataView view : views) {
      String saleid = (String) view.getAttributeValue(saleorgClassField);
      view.setAttributeValue(saleorgClassField, saleorgMap.get(saleid));
    }
  }
}
