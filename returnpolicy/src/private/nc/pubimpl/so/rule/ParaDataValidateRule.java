package nc.pubimpl.so.rule;

import nc.pubitf.so.m30.ReturnAssignMatchVO;
import nc.vo.pub.ValidationException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * 参数数据的合法性检查
 * 
 * @since 6.0
 * @version 2011-4-14 下午02:37:53
 * @author 祝会征
 */
public class ParaDataValidateRule {

  /**
   * 参数数据合法性检查
   * 
   * @param csaleorgid
   * @param matchparas
   */
  public void validate(String csaleorgid, ReturnAssignMatchVO[] matchparas) {
    if (PubAppTool.isNull(csaleorgid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006006_0", "04006006-0036")
      /*@res"退货政策检查时, 销售组织不能为空。"*/);
    }
    for (ReturnAssignMatchVO para : matchparas) {
      try {
        para.validate();
      }
      catch (ValidationException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }
}
