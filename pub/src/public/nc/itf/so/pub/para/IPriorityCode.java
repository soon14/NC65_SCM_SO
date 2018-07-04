package nc.itf.so.pub.para;

/**
 * 销售优先码接口类
 * 
 * @since 6.0
 * @version 2011-3-30 上午08:59:56
 * @author fengjb1
 */
public interface IPriorityCode {
  /**
   * 返回生成的优先码
   * 
   * @return
   */
  String getPriorityCode();

  /**
   * 是否级次
   * 
   * @return
   */
  boolean isLevel();
}
