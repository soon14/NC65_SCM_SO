package nc.pubimpl.so.custmatrel.rule;

import nc.pubitf.so.custmatrel.CustMatRelParaVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * 客户与物料关系参数数据的合法性检查
 * 
 * @since 6.0
 * @version 2011-4-14 下午02:37:53
 * @author 祝会征
 */
public class CustMatRelValidateRule {

  /**
   * 参数数据合法性检查
   * 
   * @param paravos
   * @return
   */
  public UFBoolean[] validate(CustMatRelParaVO[] paravos) {

    UFBoolean[] validateNull = new UFBoolean[paravos.length];

    for (CustMatRelParaVO para : paravos) {

      if (PubAppTool.isNull(para.getPk_org())
          || PubAppTool.isNull(para.getPk_material())
          || PubAppTool.isNull(para.getPk_customer())) {
        validateNull[para.getParaindex().intValue()] = UFBoolean.FALSE;
      }
      else {
        validateNull[para.getParaindex().intValue()] = UFBoolean.TRUE;
      }
    }
    return validateNull;
  }
}
