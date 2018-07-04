package nc.ui.so.m38.billui.pub;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m38trantype.IM38TranTypeService;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.enumeration.AskPriceRule;
import nc.vo.so.pub.enumeration.PriceDiscountType;

/**
 * 预订单交易类型服务工具
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>调整金额影响折扣还是单价
 * <li>询价规则
 * <li>...
 * </ul>
 * 
 * @version 6.0
 * @author 刘志伟
 * @time 2010-7-12 上午11:16:46
 */
public class M38TranTypeTool {

  private CardKeyValue keyValue;

  public M38TranTypeTool(
      CardKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public IM38TranTypeService getService() {
    return NCLocator.getInstance().lookup(IM38TranTypeService.class);
  }

  /**
   * 是否需要询价
   * 
   * @author 刘志伟
   * @time 2010-7-12 上午11:16:46
   */
  public boolean isFindPrice() {
    boolean bFindPrice = false;
    String ctrantypeid =
        this.keyValue.getHeadStringValue(PreOrderHVO.CTRANTYPEID);
    try {
      Integer faskqtrule =
          null == this.getService().queryTranTypeVO(ctrantypeid).getFaskqtrule() ? Integer
              .valueOf(0) : this.getService().queryTranTypeVO(ctrantypeid)
              .getFaskqtrule();

      if (AskPriceRule.ASKPRICE_NORMAL.equalsValue(faskqtrule)
          || AskPriceRule.ASKPRICE_UNSPROMOTION.equalsValue(faskqtrule)) {
        bFindPrice = true;
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    return bFindPrice;
  }

  /**
   * 是否调整金额影响单价
   * 
   * @author 刘志伟
   * @time 2010-7-12 上午11:16:46
   */
  public boolean isModifymny() {
    boolean bmodifymny = false;
    String ctrantypeid =
        this.keyValue.getHeadStringValue(PreOrderHVO.CTRANTYPEID);
    try {
      Integer fmodifymny =
          null == this.getService().queryTranTypeVO(ctrantypeid).getFmodifymny() ? Integer
              .valueOf(0) : this.getService().queryTranTypeVO(ctrantypeid)
              .getFmodifymny();

      if (PriceDiscountType.PRICETYPE.equalsValue(fmodifymny)) {
        bmodifymny = true;
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return bmodifymny;
  }
}
