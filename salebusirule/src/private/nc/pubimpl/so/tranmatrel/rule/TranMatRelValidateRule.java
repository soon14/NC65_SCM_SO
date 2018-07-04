package nc.pubimpl.so.tranmatrel.rule;

import nc.pubitf.so.tanmatrel.TranMatRelParaVO;
import nc.vo.pub.ValidationException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 单据类型与物料关系定义：参数数据的合法性检查
 * 
 * @since 6.0
 * @version 2011-4-14 下午02:37:53
 * @author 祝会征
 */
public class TranMatRelValidateRule {

  /**
   * 参数数据合法性检查
   * 
   * @param csaleorgid
   * @param paravos
   */
  public void validate(TranMatRelParaVO[] paravos) {
    for (TranMatRelParaVO para : paravos) {
      try {
        para.validate();
      }
      catch (ValidationException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }
}
