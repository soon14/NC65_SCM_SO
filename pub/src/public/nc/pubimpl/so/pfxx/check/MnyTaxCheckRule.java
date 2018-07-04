package nc.pubimpl.so.pfxx.check;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 金额校验
 * 
 * @since 6.3
 * @version 2013-4-23 下午07:08:41
 * @author tianft
 */
public class MnyTaxCheckRule implements IRule<AggregatedValueObject> {

  public String FBUYSELLFLAG = "fbuysellflag";

  private String taxKey;

  private String mnyKey;

  private String taxMnyKey;

  public MnyTaxCheckRule(String taxKey, String mnyKey, String taxMnyKey) {
    this.taxKey = taxKey;
    this.taxMnyKey = taxMnyKey;
    this.mnyKey = mnyKey;
  }

  @Override
  public void process(AggregatedValueObject[] vos) {
    for (AggregatedValueObject bill : vos) {
      for (CircularlyAccessibleValueObject child : bill.getChildrenVO()) {
        UFDouble mny = (UFDouble) child.getAttributeValue(this.mnyKey);
        UFDouble taxmny = (UFDouble) child.getAttributeValue(this.taxMnyKey);
        UFDouble tax = (UFDouble) child.getAttributeValue(this.taxKey);
        Integer buysellflag =
            (Integer) child.getAttributeValue(this.FBUYSELLFLAG);

        // 价税合计=无税金额+税额(国内销售），否则比较价税合计=无税金额
        if ((buysellflag == null || BuySellFlagEnum.NATIONAL_SELL.value()
            .equals(buysellflag))
            && !MathTool.equals(taxmny, MathTool.add(tax, mny))
            || !BuySellFlagEnum.NATIONAL_SELL.value().equals(buysellflag)
            && !MathTool.equals(taxmny, mny)) {
          ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4006004_0", "04006004-0229")/* 存在金额计算错误的数据行
                                                                       * ！ */);
        }

      }
    }
  }

}
