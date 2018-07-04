package nc.vo.so.report.reportpub;

/**
 * 报表级次枚举
 * 
 * @since 6.3
 * @version 2013-03-01 16:05:17
 * @author 梁吉明
 */
public enum ReportLevelValue {

  /**
   * 末级
   */
  END(Integer.valueOf(0)),
  /**
   * 一级
   */
  FIRST(Integer.valueOf(1)),
  /**
   * 二级
   */
  SECOND(Integer.valueOf(2)),
  /**
   * 三级
   */
  THIRD(Integer.valueOf(3)),
  /**
   * 四级
   */
  FOUTH(Integer.valueOf(4)),
  /**
   * 五级
   */
  FIFTH(Integer.valueOf(5)),
  /**
   * 六级
   */
  SIXTH(Integer.valueOf(6))

  ;

  private Integer level;

  private ReportLevelValue(Integer level) {
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
}
