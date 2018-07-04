package nc.pubimpl.so.deptmatrel.rule;

import nc.pubitf.so.deptmatrel.DeptMatRelParaVO;
import nc.vo.pub.ValidationException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 部门、业务员、物料关系参数数据的合法性检查
 * 
 * @since 6.0
 * @version 2011-4-14 下午02:37:53
 * @author 祝会征
 */
public class DeptMatRelValidateRule {

  /**
   * 参数数据合法性检查
   * 
   * @param csaleorgid
   * @param paravos
   */
  public void validate(DeptMatRelParaVO[] paravos) {
    for (DeptMatRelParaVO para : paravos) {
      try {
        para.validate();
      }
      catch (ValidationException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }
}
