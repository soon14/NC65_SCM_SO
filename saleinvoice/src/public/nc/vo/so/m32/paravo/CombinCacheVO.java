package nc.vo.so.m32.paravo;

import java.util.Map;

import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.so.m32.entity.SaleInvoiceBVO;

/**
 * 发票合并缓存对象
 * 
 * @since 6.0
 * @version 2011-8-12 上午10:49:24
 * @author 么贵敬
 */
public class CombinCacheVO {
  // 是否汇总标志位
  private boolean bcombinflag;

  // 汇总和明细对照关系
  private MapList<String, SaleInvoiceBVO> combinrela =
      new MapList<String, SaleInvoiceBVO>();

  // key:开票组织 value:合并字段
  private Map<String, String> mapgroupkeys;

  /**
   * 
   * 
   * @param combinflag
   */
  public CombinCacheVO(boolean combinflag) {
    this.bcombinflag = combinflag;
  }

  public boolean getBcombinflag() {
    return this.bcombinflag;
  }

  public MapList<String, SaleInvoiceBVO> getCombinRela() {
    return this.combinrela;
  }

  public String getGroupKeys(String pk_org) {
    return this.mapgroupkeys.get(pk_org);
  }

  public Map<String, String> getMapGroupKeys() {
    return this.mapgroupkeys;
  }

  public void setBcombinflag(boolean combinflag) {
    this.bcombinflag = combinflag;
  }

  public void setCombinrela(MapList<String, SaleInvoiceBVO> combinrela) {
    this.combinrela = combinrela;
  }

  public void setMapGroupKeys(Map<String, String> mapgroupkey) {
    this.mapgroupkeys = mapgroupkey;
  }
}
