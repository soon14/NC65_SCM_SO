package nc.vo.so.pub.rule;

import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 客户相关默认值设置
 * 
 * @since 6.0
 * @version 2012-3-12 上午11:20:51
 * @author 么贵敬
 */
public class SOCustRelaDefValueByHeadRecInfo {

  private IKeyValue keyValue;

  /**
   * 
   * 
   * @param keyValue
   */
  public SOCustRelaDefValueByHeadRecInfo(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
 * 
 */
  public void setCustRelaDefValue() {

    // 7.设置表体收货客户
    BodyValueRowRule countutil = new BodyValueRowRule(this.keyValue);
    int[] rows = countutil.getMarNotNullRows();
    String rececust =
        this.keyValue.getHeadStringValue(SOItemKey.CRECEIVECUSTID);

    for (int row : rows) {
      this.keyValue.setBodyValue(row, SOItemKey.CRECEIVECUSTID, rececust);
    }

  }

}
