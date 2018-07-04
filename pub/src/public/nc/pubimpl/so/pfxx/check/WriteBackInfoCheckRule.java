package nc.pubimpl.so.pfxx.check;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 回写信息校验
 * 
 * @since 6.3
 * @version 2013-4-23 下午07:08:41
 * @author tianft
 */
public class WriteBackInfoCheckRule implements IRule<AggregatedValueObject> {

  private String[] keys;

  public WriteBackInfoCheckRule(String[] keys) {
    this.keys = keys;
  }

  @Override
  public void process(AggregatedValueObject[] vos) {
    for (AggregatedValueObject bill : vos) {
      for (CircularlyAccessibleValueObject child : bill.getChildrenVO()) {
        for (String key : this.keys) {
          UFDouble value = (UFDouble) child.getAttributeValue(key);
          if (!MathTool.equals(value, UFDouble.ZERO_DBL)) {
            ExceptionUtils
                .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
                    .getStrByID("4006004_0", "04006004-0230")/* 存在回写（累计）字段不为空的数据行
                                                              * ！ */);
          }
        }
      }
    }
  }

}
