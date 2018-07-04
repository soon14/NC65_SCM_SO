package nc.vo.so.pub.calculator;

import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.VODataSetForCal;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.SOItemKey;

public class SOVODataSetForCal extends VODataSetForCal implements
    INumPriceMnyCheckData {

  private CircularlyAccessibleValueObject voHead;

  private CircularlyAccessibleValueObject voitem;

  public SOVODataSetForCal(CircularlyAccessibleValueObject voHead,
      CircularlyAccessibleValueObject voitem, IRelationForItems item) {
    super(voitem, item);
    this.voHead = voHead;
    this.voitem = voitem;
  }

  @Override
  public UFBoolean getBdiscountflag() {
    return ValueUtils.getUFBoolean(this.voitem
        .getAttributeValue(SOItemKey.BDISCOUNTFLAG));
  }

  @Override
  public UFBoolean getBlaborflag() {
    return ValueUtils.getUFBoolean(this.voitem
        .getAttributeValue(SOItemKey.BLABORFLAG));
  }

  @Override
  public UFBoolean getBlargessflag() {
    return ValueUtils.getUFBoolean(this.voitem
        .getAttributeValue(SOItemKey.BLARGESSFLAG));
  }

  /**
   * 集团表体没有集团，从表头获取
   */
  @Override
  public String getPk_group() {
    String pk_group = super.getPk_group();
    if (PubAppTool.isNull(pk_group)) {
      pk_group = AppContext.getInstance().getPkGroup();
    }
    return pk_group;
  }

}
