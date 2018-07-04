package nc.vo.so.pub.rule;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;

/**
 * 
 * 销售管理模块 购销类型和三角贸易字段规则
 * 注： 按照公共需求 购销类型发生变化的时候 要触发单价金额算法，在调用此方法后要注意考虑是否需要调用
 * 
 * @since 6.0
 * @version 2012-2-5 下午01:52:17
 * @author 么贵敬
 */
public class SOBuysellTriaRule {

  private IKeyValue keyValue;

  private List<Integer> listchgrow;

  /**
   * 构造方法
   * 
   * @param keyValue
   */
  public SOBuysellTriaRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 返回购销类型发生变化的行，供调用者调用数量单价金额算法
   * 
   * @return
   */
  public int[] getBuysellChgRow() {
    if (this.listchgrow.size() == 0) {
      return new int[0];
    }
    int[] chgrows = new int[this.listchgrow.size()];
    int i = 0;
    for (Integer chgrow : this.listchgrow) {
      chgrows[i] = chgrow;
      i++;
    }
    return chgrows;
  }

  /**
   * 指定行购销类型是否为国外
   * 
   * @param row
   * @return
   */
  public boolean isBuysellFlagOut(int row) {

    Integer buysellflag =
        this.keyValue.getBodyIntegerValue(row, SOItemKey.FBUYSELLFLAG);

    if (BuySellFlagEnum.OUTPUT.equalsValue(buysellflag)) {
      return true;
    }
    return false;
  }

  /**
   * 购销类型在表头 判断是否跨国
   * 
   * @return
   */
  public boolean isHeadBuysellFlagOut() {
    Integer buysellflag =
        this.keyValue.getHeadIntegerValue(SOItemKey.FBUYSELLFLAG);

    if (BuySellFlagEnum.OUTPUT.equalsValue(buysellflag)) {
      return true;
    }
    return false;
  }

  /**
   * 设置购销类型、三角贸易字段值
   * 
   * @param rows
   */
  public void setBuysellAndTriaFlag(int[] rows) {

    this.listchgrow = new ArrayList<Integer>();
    for (int row : rows) {
      String ctaxcountryid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CTAXCOUNTRYID);
      String crececountryid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CRECECOUNTRYID);
      String csendcountryid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CSENDCOUNTRYID);
      // 缓存旧的购销类型
      Integer oldbuysellflag =
          this.keyValue.getBodyIntegerValue(row, SOItemKey.FBUYSELLFLAG);

      // 设置购销类型，并存储变化的行
      Integer newbuysellflag = null;
      if (PubAppTool.isNull(ctaxcountryid) || PubAppTool.isNull(crececountryid)
          || PubAppTool.isEqual(ctaxcountryid, crececountryid)) {
        newbuysellflag = BuySellFlagEnum.NATIONAL_SELL.value();
      }
      else {
        newbuysellflag = BuySellFlagEnum.OUTPUT.value();
      }
      this.keyValue.setBodyValue(row, SOItemKey.FBUYSELLFLAG, newbuysellflag);

      if (!PubAppTool.isEqual(oldbuysellflag, newbuysellflag)) {
        // 采购订单协同生成销售订单时 采购订单购销类型“国内采购” 到“国内销售”。此时也认为购销类型没有发生变化
        if (!(BuySellFlagEnum.NATIONAL_BUY.equalsValue(oldbuysellflag) && BuySellFlagEnum.NATIONAL_SELL
            .equalsValue(newbuysellflag))) {
          this.listchgrow.add(Integer.valueOf(row));
        }
      }
      // 设置三角贸易
      if (BuySellFlagEnum.OUTPUT.equalsValue(newbuysellflag)
          && !PubAppTool.isNull(ctaxcountryid)
          && !PubAppTool.isNull(csendcountryid)
          && !PubAppTool.isEqual(ctaxcountryid, csendcountryid)) {
        this.keyValue.setBodyValue(row, SOItemKey.BTRIATRADEFLAG,
            UFBoolean.TRUE);
      }
      else {
        this.keyValue.setBodyValue(row, SOItemKey.BTRIATRADEFLAG,
            UFBoolean.FALSE);
      }
    }
  }
}
