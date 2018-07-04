package nc.pubitf.so.mobile.analy;

/**
 * 我的订单分组方式枚举
 * 
 * @since 6.1
 * @version 2013-06-08 09:46:45
 * @author yixl
 */
public enum MyOrderGrpType {

  /**
   * 我的订单分组方式
   */

  GROUPDATE("1", nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006005_0", "04006005-0027"/*日期*/));

  private String funccode;

  private String funcname;

  private MyOrderGrpType(String funccode, String funcname) {
    this.funccode = funccode;
    this.funcname = funcname;
  }

  /**
   * 获得分组方式编码
   * 
   * @return 分组方式编码
   */
  public String getFuncCode() {
    return this.funccode;
  }

  /**
   * 获得分组方式名称
   * 
   * @return 分组方式名称
   */
  public String getFuncName() {
    return this.funcname;
  }
}
