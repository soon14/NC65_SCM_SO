package nc.vo.so.pub.enumeration;

/**
 * 列表界面模板类型
 * <ol>
 * <li>main：主子数据放在主表位置
 * <li>sub：主子数据放在子表位置
 * <li>main_sub:标准位置(主放在主,子放在子)
 * </ol>
 * 
 * @since 6.0
 * @version 2011-6-16 下午04:06:27
 * @author 刘志伟
 */
public enum ListTemplateType {
  /** 主子数据放在主表位置 */
  MAIN,
  /** 标准位置(主放在主,子放在子) */
  MAIN_SUB,
  /** 主子数据放在子表位置 */
  SUB
}
