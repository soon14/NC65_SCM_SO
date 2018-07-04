package nc.pubimpl.so.pfxx.check;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 自由态单据检查规则
 * 
 * @since 6.3
 * @version 2013-4-23 下午07:08:41
 * @author tianft
 */
public class BillFreeStatusCheckRule implements IRule<AggregatedValueObject> {

  private String billStatusKey = "fstatusflag";

  private BillStatus[] billStatusValues = {
    BillStatus.FREE, BillStatus.AUDIT
  };

  public BillFreeStatusCheckRule(String billStatusKey,
      BillStatus[] billStatusValues) {
    this.billStatusKey = billStatusKey;
    this.billStatusValues = billStatusValues;
  }

  public BillFreeStatusCheckRule() {
    //
  }

  @Override
  public void process(AggregatedValueObject[] vos) {
    for (AggregatedValueObject bill : vos) {
      int i = 0;
      for (BillStatus billStatusValue : billStatusValues) {
        if (billStatusValue != null
            && billStatusValue.getIntegerValue().equals(
                bill.getParentVO().getAttributeValue(this.billStatusKey))) {
          i++;
        }
      }
      if (i == 0) {
        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006004_0", "04006004-0228")/* 存在非自由态的单据！ */);
      }
      if (BillStatus.AUDIT.equalsValue(bill.getParentVO().getAttributeValue(
          this.billStatusKey))
          && !BillStatus.FREE.equalsValue(bill.getParentVO().getAttributeValue(
              "fpfstatusflag"))) {
        ExceptionUtils
            .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006004_0", "04006004-0249")/*单据状态错误：fstatusflag=2时，fpfstatusflag应该等于1*/);
      }
    }
  }

}
