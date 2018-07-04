package nc.vo.so.m32.rule;

import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.trade.checkrule.VOChecker;

public class SrcBillInfoRule {

  private IKeyValue keyValue;

  /**
   * SrcBillInfoRule 的构造子
   * 
   * @param keyValue
   */
  public SrcBillInfoRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 方法功能描述：判断index行是否存在来源单据。
   * <p>
   * <b>参数说明</b>
   * 
   * @param index
   * @return <p>
   * @author 冯加滨
   * @time 2010-4-23 上午11:12:04
   */
  public boolean isExitSrc(int index) {
    // 判断是否存在来源单据ID
    String srcbid =
        this.keyValue.getBodyStringValue(index, SaleInvoiceBVO.CSRCBID);
    return VOChecker.isEmpty(srcbid);
  }
}
