package nc.vo.so.pub.util;

import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.parameter.SCMParameterUtils;

/**
 * 基本分类 还是 销售分类判定工具类
 * 
 * @since 6.0
 * @version 2011-3-30 下午04:09:43
 * @author fengjb1
 */
public class BaseSaleClassUtil {

  public static boolean isCustBaseClass(String pk_group) {
    String scm12 = null;

    scm12 = SCMParameterUtils.getSCM12(pk_group);

    return PubAppTool.isNull(scm12) || "客户基本分类".equals(scm12);/*-=notranslate=-*/
  }

  public static boolean isMarBaseClass(String pk_group) {
    String scm11 = null;
    scm11 = SCMParameterUtils.getSCM11(pk_group);

    return PubAppTool.isNull(scm11) || "物料基本分类".equals(scm11);/*-=notranslate=-*/
  }
}
