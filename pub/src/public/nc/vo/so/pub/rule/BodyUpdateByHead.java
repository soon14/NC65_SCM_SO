package nc.vo.so.pub.rule;

import nc.vo.so.pub.keyvalue.IKeyValue;

public class BodyUpdateByHead {

  private IKeyValue keyValue;

  /**
   * BodyUpdateByHead 的构造子
   * 
   * @param keyValue
   */
  public BodyUpdateByHead(
      IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 方法功能描述：根据表头字段值更新所有表体冗余字段值，要求表头表体字段名一样。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bodykeys
   * @param headkeys
   *          <p>
   * @author fengjb
   * @time 2010-5-18 上午11:26:08
   */
  public void updateAllBodyRedunValue(String[] redunkeys) {
    // 行数
    int irowcount = this.keyValue.getBodyCount();
    for (int i = 0; i < irowcount; i++) {
      for (String key : redunkeys) {
        Object headValue = this.keyValue.getHeadValue(key);
        this.keyValue.setBodyValue(i, key, headValue);
      }
    }
  }

  /**
   * 方法功能描述：根据表头headkey字段值更新所有表体bodykey字段。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bodykey
   * @param headkey
   *          <p>
   * @author 冯加滨
   * @time 2010-4-30 上午11:42:45
   */
  public void updateAllBodyValueByHead(String bodykey, String headkey) {
    Object headValue = this.keyValue.getHeadValue(headkey);
    int ilen = this.keyValue.getBodyCount();
    for (int i = 0; i < ilen; i++) {
      this.keyValue.setBodyValue(i, bodykey, headValue);
    }
  }
}
