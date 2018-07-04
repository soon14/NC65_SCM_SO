package nc.pubimpl.so.custmatrel.rule;

import nc.pubitf.so.custmatrel.CustMatRelParaVO;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * 参数空值转换规则
 * 
 * @since 6.0
 * @version 2011-4-14 下午08:15:52
 * @author 祝会征
 */
public class CustMatRelNullValueChgRule {

  private static final String NULLVALUE = "~";

  /**
   * 空值转换
   * 
   * @param extendparas
   */
  public void changeNullValue(CustMatRelParaVO[] extendparas) {
    String[] nullitemkeys =
        new String[] {
          CustMatRelParaVO.PK_MATERIAL, CustMatRelParaVO.PK_ORG,
          CustMatRelParaVO.PK_MATERIALBASECLASS,
          CustMatRelParaVO.PK_MATERIALSALECLASS, CustMatRelParaVO.PK_CUSTOMER,
          CustMatRelParaVO.PK_CUSTBASECLASS, CustMatRelParaVO.PK_CUSTSALECLASS,
        };
    for (CustMatRelParaVO para : extendparas) {
      for (String key : nullitemkeys) {
        String value = (String) para.getAttributeValue(key);
        if (PubAppTool.isNull(value)) {
          para.setAttributeValue(key, CustMatRelNullValueChgRule.NULLVALUE);
        }
      }
    }
  }
}
