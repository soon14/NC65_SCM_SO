package nc.pubimpl.so.rule;

import nc.pubitf.so.m30.ReturnAssignMatchVO;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * 参数空值转换规则
 * 
 * @since 6.0
 * @version 2011-4-14 下午08:15:52
 * @author 祝会征
 */
public class ChangeNullValueRule {

  private static final String NULLVALUE = "~";

  /**
   * 空值转换
   * 
   * @param extendparas
   */
  public void changeNullValue(ReturnAssignMatchVO[] extendparas) {
    String[] nullitemkeys =
        new String[] {
          ReturnAssignMatchVO.PK_MATERIAL, ReturnAssignMatchVO.PK_MARBASCLASS,
          ReturnAssignMatchVO.PK_MARSALECLASS, ReturnAssignMatchVO.PK_CUSTOMER,
          ReturnAssignMatchVO.PK_CUSTCLASS,
          ReturnAssignMatchVO.PK_CUSTSALECLASS,
          ReturnAssignMatchVO.PK_PRODUCTLINE
        };
    for (ReturnAssignMatchVO para : extendparas) {
      for (String key : nullitemkeys) {
        String value = (String) para.getAttributeValue(key);
        if (PubAppTool.isNull(value)) {
          para.setAttributeValue(key, ChangeNullValueRule.NULLVALUE);
        }
      }
    }
  }
}
