package nc.pubimpl.so.deptmatrel.rule;

import nc.pubitf.so.deptmatrel.DeptMatRelParaVO;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * 部门、业务员、物料关系定义：参数空值转换规则
 * 
 * @since 6.0
 * @version 2011-4-14 下午08:15:52
 * @author 祝会征
 */
public class DeptMatRelNullValueChgRule {

  private static final String NULLVALUE = "~";

  /**
   * 空值转换
   * 
   * @param extendparas
   */
  public void changeNullValue(DeptMatRelParaVO[] extendparas) {
    String[] nullitemkeys =
        new String[] {
          DeptMatRelParaVO.PK_MATERIAL, DeptMatRelParaVO.PK_ORG,
          DeptMatRelParaVO.PK_MATERIALBASECLASS,
          DeptMatRelParaVO.PK_MATERIALSALECLASS, DeptMatRelParaVO.PK_DEPT,
          DeptMatRelParaVO.CEMPLOYEEID
        };
    for (DeptMatRelParaVO para : extendparas) {
      for (String key : nullitemkeys) {
        String value = (String) para.getAttributeValue(key);
        if (PubAppTool.isNull(value)) {
          para.setAttributeValue(key, DeptMatRelNullValueChgRule.NULLVALUE);
        }
      }
    }
  }
}
