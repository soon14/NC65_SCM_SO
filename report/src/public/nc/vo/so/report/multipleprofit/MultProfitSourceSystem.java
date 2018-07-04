package nc.vo.so.report.multipleprofit;

/**
 * 综合毛利分析来源系统
 * 
 * @since 6.3
 * @version 2012-12-10 13:50:10
 * @author liangjm
 */
public enum MultProfitSourceSystem {
  /**
   * 来源销售
   */
  RESOURCE_SALE(Integer.valueOf(0)),
  /**
   * 来源内部交易
   */
  RESOURCE_INNER(Integer.valueOf(1)),
  /**
   * 来源内部交易和销售
   */
  RESOURCE_SALE_INNER(Integer.valueOf(2));

  private Integer level;

  private MultProfitSourceSystem(Integer level) {
    this.level = level;
  }

  /**
   * 
   * @param otherlevel
   * @return b
   */
  public boolean equalsValue(Integer otherlevel) {
    return this.level.equals(otherlevel);
  }

  /**
   * 
   * 
   * @return level
   */
  public Integer getResourceSystem() {
    return this.level;
  }

}
