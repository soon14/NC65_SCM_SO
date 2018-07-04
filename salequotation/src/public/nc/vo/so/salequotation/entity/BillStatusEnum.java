/**
 * $$文件说明$$
 * 
 * @author chenyyb
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-07-08 08:46:49
 */
package nc.vo.so.salequotation.entity;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>
 * </ul>
 * 
 * <p>
 * ${tags}
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenyyb
 * @time 2010-07-08 08:46:49
 */
public class BillStatusEnum extends MDEnum {
  /**
   * 审批通过
   */
  public static final BillStatusEnum AUDIT = MDEnum.valueOf(
      BillStatusEnum.class, Integer.valueOf(BillStatusEnum.C_AUDIT));

  /**
   * 审批中
   */
  public static final BillStatusEnum AUDITING = MDEnum.valueOf(
      BillStatusEnum.class, Integer.valueOf(BillStatusEnum.C_AUDITING));

  /**
   * 审批通过
   */
  public static final int C_AUDIT = 1;

  /**
   * 审批中
   */
  public static final int C_AUDITING = 2;

  /**
   * 已关闭
   */
  public static final int C_CLOSE = 4;

  /**
   * 提交
   */
  public static final int C_COMMIT = 3;

  /**
   * 自由
   */
  public static final int C_FREE = -1;

  /**
   * 已失效
   */
  public static final int C_INVALIDATE = 5;

  /**
   * 审批不通过
   */
  public static final int C_NOPASS = 0;

  /**
   * 已关闭
   */
  public static final BillStatusEnum CLOSE = MDEnum.valueOf(
      BillStatusEnum.class, Integer.valueOf(BillStatusEnum.C_CLOSE));

  /**
   * 提交
   */
  public static final BillStatusEnum COMMIT = MDEnum.valueOf(
      BillStatusEnum.class, Integer.valueOf(BillStatusEnum.C_COMMIT));

  /**
   * 自由
   */
  public static final BillStatusEnum FREE = MDEnum.valueOf(
      BillStatusEnum.class, Integer.valueOf(BillStatusEnum.C_FREE));

  /**
   * 已失效
   */
  public static final BillStatusEnum INVALIDATE = MDEnum.valueOf(
      BillStatusEnum.class, Integer.valueOf(BillStatusEnum.C_INVALIDATE));

  /**
   * 审批不通过
   */
  public static final BillStatusEnum NOPASS = MDEnum.valueOf(
      BillStatusEnum.class, Integer.valueOf(BillStatusEnum.C_NOPASS));

  public BillStatusEnum(IEnumValue enumvalue) {
    super(enumvalue);
  }

}
