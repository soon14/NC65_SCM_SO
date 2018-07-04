package nc.vo.so.mbuylargess.entity;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

/**
 * 买赠设置Meta类
 */

public class BuyLargessMeta extends AbstractBillMeta {
  /**
   * BuylargessMeta 的构造子
   */
  public BuyLargessMeta() {
    this.init();
  }

  /**
   * 方法功能描述：买赠设置聚合VOMeta类初始化。 <b>参数说明</b>
   * 
   * @author fengjb
   * @time 2009-6-3 下午01:57:54
   */
  private void init() {
    this.setParent(BuyLargessHVO.class);
    this.addChildren(BuyLargessBVO.class);
  }

}
