package nc.ui.so.mreturnpolicy.ref;

/**
 * ?user> 功能： 日期：(2004-7-1 8:33:35)
 */
public interface IRefReturn {
  /**
   * ?user> 功能： 参数： 返回： 例外： 日期：(2004-7-1 13:04:39) 修改日期，修改人，修改原因，注释标志：
   * 
   * @return java.lang.String
   * @param code
   *          java.lang.String
   */
  String getNameByCode(String code);

  /**
   * ?user> 功能： 参数： 返回： 例外： 日期：(2004-7-1 8:34:59) 修改日期，修改人，修改原因，注释标志：
   * 
   * @return java.lang.String
   */
  String getRefReturnCode();

  /**
   * ?user> 功能： 参数： 返回： 例外： 日期：(2004-7-1 8:34:59) 修改日期，修改人，修改原因，注释标志：
   * 
   * @return java.lang.String
   */
  String getRefReturnName();

  /**
   * ?user> 功能： 参数： 返回： 例外： 日期：(2004-7-1 9:07:11) 修改日期，修改人，修改原因，注释标志：
   * 
   * @return int
   */
  int showModal();
}
